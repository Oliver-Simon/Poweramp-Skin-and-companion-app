package com.maxmpz.poweramp.player;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructPollfd;
import android.util.Log;
import java.io.FileDescriptor;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class TrackProviderProto implements AutoCloseable {
    private static final boolean DEBUG_CHECKS = false;
    private static final int INITIAL_PACKET_SIZE = 24;
    private static final int INTEGER_BYTES = 4;
    public static final long INVALID_SEEK_POS = Long.MIN_VALUE;
    private static final boolean LOG = false;
    private static final int LONG_BYTES = 8;
    public static final int MAX_DATA_SIZE = 4096;
    private static final int MAX_PACKET_HEADER_SIZE = 12;
    private static final int PACKET_DATA_IX = 12;
    private static final int PACKET_DATA_SIZE_IX = 6;
    private static final int PACKET_TAG = -235798527;
    private static final short PACKET_TYPE_DATA = 2;
    private static final short PACKET_TYPE_HEADER = 1;
    private static final short PACKET_TYPE_SEEK = 3;
    private static final short PACKET_TYPE_SEEK_RES = 4;
    private static final int STATE_CLOSED = 1;
    private static final int STATE_DATA = 2;
    private static final int STATE_INITIAL = 0;
    private static final String TAG = "TrackProviderProto";
    private final long mFileLength;
    private final ByteBuffer mHeaderBuffer;
    private final FileDescriptor mSocket;
    private final StructPollfd[] mStructPollFds;
    private int mState = 0;
    private final SeekRequest mTempSeekRequest = new SeekRequest();

    /* loaded from: classes3.dex */
    public static class TrackProviderProtoException extends RuntimeException {
        public TrackProviderProtoException(Throwable ex) {
            super(ex);
        }

        public TrackProviderProtoException(String msg) {
            super(msg);
        }
    }

    /* loaded from: classes3.dex */
    public static class TrackProviderProtoClosed extends RuntimeException {
        public TrackProviderProtoClosed(Throwable ex) {
            super(ex);
        }
    }

    /* loaded from: classes3.dex */
    public static class SeekRequest {
        public int ms = Integer.MIN_VALUE;
        public long offsetBytes;

        public String toString() {
            return super.toString() + " offsetBytes=" + this.offsetBytes + " ms=" + this.ms;
        }
    }

    public TrackProviderProto(ParcelFileDescriptor pfd, long fileLength) {
        if (fileLength <= 0) {
            throw new IllegalArgumentException("bad fileLength=" + fileLength);
        }
        FileDescriptor socket = pfd.getFileDescriptor();
        if (socket != null) {
            try {
                if (OsConstants.S_ISSOCK(Os.fstat(socket).st_mode)) {
                    this.mSocket = socket;
                    this.mFileLength = fileLength;
                    ByteBuffer headerBuffer = ByteBuffer.allocateDirect(24);
                    if (headerBuffer == null) {
                        throw new TrackProviderProtoException("headerBuffer");
                    }
                    this.mHeaderBuffer = headerBuffer;
                    this.mHeaderBuffer.order(ByteOrder.nativeOrder());
                    this.mStructPollFds = new StructPollfd[]{new StructPollfd()};
                    this.mStructPollFds[0].fd = this.mSocket;
                    this.mStructPollFds[0].events = (short) OsConstants.POLLIN;
                    return;
                }
            } catch (ErrnoException ex) {
                throw new TrackProviderProtoException(ex);
            }
        }
        throw new IllegalArgumentException("bad pfd=" + pfd);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.mState != 1) {
            try {
                Os.shutdown(this.mSocket, 0);
            } catch (ErrnoException ex) {
                Log.e(TAG, "", ex);
            }
            try {
                Os.close(this.mSocket);
            } catch (ErrnoException ex2) {
                Log.e(TAG, "", ex2);
            }
            this.mState = 1;
        }
    }

    private ByteBuffer preparePacketHeader(short packetType, int dataSize) {
        ByteBuffer buf = this.mHeaderBuffer;
        buf.clear();
        buf.putInt(PACKET_TAG);
        buf.putShort(packetType);
        if (dataSize > 4096) {
            throw new AssertionError(dataSize);
        }
        buf.putShort((short) dataSize);
        buf.putInt(0);
        return buf;
    }

    public void sendHeader() {
        if (this.mState == 0) {
            try {
                ByteBuffer buf = preparePacketHeader((short) 1, 12);
                buf.putLong(this.mFileLength);
                buf.putInt(4096);
                buf.flip();
                while (buf.hasRemaining()) {
                    Os.sendto(this.mSocket, buf, 0, null, 0);
                }
                this.mState = 2;
            } catch (ErrnoException | SocketException ex) {
                throw new TrackProviderProtoException(ex);
            }
        }
    }

    public long sendData(ByteBuffer data) {
        SeekRequest request = sendData2(data);
        if (request != null) {
            return request.offsetBytes;
        }
        return Long.MIN_VALUE;
    }

    public SeekRequest sendData2(ByteBuffer data) {
        SeekRequest seekPosEncoded;
        if (this.mState == 2) {
            int packetsSent = 0;
            int originalDataLimit = data.limit();
            while (data.hasRemaining()) {
                try {
                    try {
                        int size = data.remaining();
                        if (size > 4096) {
                            size = 4096;
                        }
                        data.limit(data.position() + size);
                        ByteBuffer buf = preparePacketHeader((short) 2, size);
                        buf.flip();
                        while (buf.hasRemaining()) {
                            Os.sendto(this.mSocket, buf, 0, null, 0);
                        }
                        while (data.hasRemaining()) {
                            Os.sendto(this.mSocket, data, 0, null, 0);
                        }
                        packetsSent++;
                        buf.clear();
                        try {
                            int fdsReady = Os.poll(this.mStructPollFds, 0);
                            if (fdsReady == 1 && (seekPosEncoded = readSeekRequest(true)) != null) {
                                return seekPosEncoded;
                            }
                        } catch (ErrnoException e) {
                        }
                    } catch (ErrnoException ex) {
                        if (ex.errno != OsConstants.ECONNRESET && ex.errno != OsConstants.EPIPE) {
                            throw new TrackProviderProtoException(ex);
                        }
                        throw new TrackProviderProtoClosed(ex);
                    } catch (SocketException ex2) {
                        throw new TrackProviderProtoException(ex2);
                    }
                } finally {
                    data.limit(originalDataLimit);
                }
            }
        }
        return null;
    }

    public long sendEOFAndWaitForSeekOrClose() {
        SeekRequest seekRequest = sendEOFAndWaitForSeekOrClose2();
        if (seekRequest != null) {
            return seekRequest.offsetBytes;
        }
        return Long.MIN_VALUE;
    }

    public SeekRequest sendEOFAndWaitForSeekOrClose2() {
        try {
            ByteBuffer buf = preparePacketHeader((short) 2, 0);
            buf.flip();
            while (buf.hasRemaining()) {
                Os.sendto(this.mSocket, buf, 0, null, 0);
            }
            try {
                return readSeekRequest(false);
            } catch (TrackProviderProtoException e) {
                return null;
            }
        } catch (ErrnoException | SocketException ex) {
            throw new TrackProviderProtoException(ex);
        }
    }

    private SeekRequest readSeekRequest(boolean noBlock) {
        try {
            ByteBuffer buf = this.mHeaderBuffer;
            buf.clear();
            buf.limit(12);
            int res = Os.recvfrom(this.mSocket, buf, 0, null);
            if (res == 12) {
                int type = getPacketType(buf);
                int dataSize = getPacketDataSize(buf);
                if (type != 3 || dataSize < 8) {
                    Log.e(TAG, "readSeekRequest FAIL recvfrom type=" + type + " dataSize=" + dataSize);
                } else {
                    buf.limit(buf.limit() + dataSize);
                    int res2 = Os.recvfrom(this.mSocket, buf, noBlock ? OsConstants.O_NONBLOCK : 0, null);
                    if (res2 < 8) {
                        Log.e(TAG, "readSeekRequest FAIL recvfrom data res=" + res2);
                    } else {
                        SeekRequest seekRequest = this.mTempSeekRequest;
                        seekRequest.offsetBytes = buf.getLong(12);
                        if (res2 >= 12) {
                            seekRequest.ms = buf.getInt(20);
                        } else {
                            seekRequest.ms = Integer.MIN_VALUE;
                        }
                        return seekRequest;
                    }
                }
            } else {
                if (res == 0) {
                    return null;
                }
                Log.e(TAG, "readSeekRequest FAIL recvfrom res=" + res);
            }
            return null;
        } catch (ErrnoException ex) {
            if (ex.errno == OsConstants.ECONNRESET || ex.errno == OsConstants.EPIPE) {
                throw new TrackProviderProtoClosed(ex);
            }
            if (ex.errno == OsConstants.EAGAIN) {
                return null;
            }
            throw new TrackProviderProtoException(ex);
        } catch (SocketException ex2) {
            throw new TrackProviderProtoException(ex2);
        }
    }

    public void sendSeekResult(long newPos) {
        ByteBuffer buf = preparePacketHeader((short) 4, 8);
        buf.putLong(newPos);
        buf.flip();
        while (buf.hasRemaining()) {
            try {
                Os.sendto(this.mSocket, buf, 0, null, 0);
            } catch (ErrnoException | SocketException ex) {
                throw new TrackProviderProtoException(ex);
            }
        }
    }

    private static int getPacketType(ByteBuffer buf) {
        if (buf.limit() >= 12 && buf.getInt(0) == PACKET_TAG) {
            int type = buf.getShort(4);
            int dataSize = getPacketDataSize(buf);
            if (type > 0 && dataSize > 0) {
                return type;
            }
            return -1;
        }
        return -1;
    }

    private static int getPacketDataSize(ByteBuffer buf) {
        return buf.getShort(6);
    }

    private static void maybeUpdateBufferPosition(ByteBuffer buffer, int bytesReadOrWritten) {
        if (bytesReadOrWritten > 0) {
            buffer.position(buffer.position() + bytesReadOrWritten);
        }
    }
}
