package com.maxmpz.poweramp.plugin;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.HttpUrl;

/* loaded from: classes5.dex */
public class PluginMsgHelper {
    public static final int HEADER_SIZE_BYTES = 32;
    public static final int HEADER_SIZE_INTS = 8;
    public static final int IX_DATA_SIZE = 28;
    public static final int IX_FLAGS = 24;
    public static final int IX_MSG_ID = 20;
    public static final int IX_TAG = 16;
    public static final int MAX_SIZE_BYTES = 4096;
    public static final int MAX_SIZE_INTS = 1024;
    public static final int MSG_TAG = -235736076;
    public static int IX_PLUGIN_ID = 0;
    public static int IX_PLUGIN_ID_INT = 0;
    public static int IX_DATA = 32;
    public static int IX_DATA_INT = 8;
    public static int FLAG_TYPE_MASK = 61440;
    public static int FLAG_TYPE_SYNC_NO_CONTEXT = 4096;
    public static int FLAG_TYPE_SEND_OUTSIDE = 8192;
    public static int FLAG_TYPE_BROADCAST = 16384;
    public static int FLAG_BROADCAST_GROUP_MASK = 63;
    public static int FLAG_TYPE_ALSO_SYNC_NO_CONTEXT = 32768;
    public static int MSG_ID_BROADCAST = -1;

    /* loaded from: classes5.dex */
    public static class PluginMsgException extends RuntimeException {
        private static final long serialVersionUID = -5131019933670409856L;

        public PluginMsgException() {
        }

        public PluginMsgException(String msg) {
            super(msg);
        }
    }

    public static int calcBufferSizeInts(int desiredSizeInts) {
        return desiredSizeInts + 8;
    }

    public static int calcBufferSizeBytes(int desiredSizeBytes) {
        return desiredSizeBytes + 32;
    }

    private static void writeHeader(int[] buf, int pluginID, int msgID, int flags, int desiredSizeInts) {
        buf[0] = pluginID;
        buf[4] = -235736076;
        buf[5] = msgID;
        buf[6] = flags;
        buf[7] = desiredSizeInts * 4;
    }

    private static void writeHeader(ByteBuffer buf, int pluginID, int msgID, int flags, int desiredSizeBytes) {
        buf.putInt(pluginID);
        buf.position(16);
        buf.putInt(MSG_TAG);
        buf.putInt(msgID);
        buf.putInt(flags);
        buf.putInt(desiredSizeBytes);
    }

    public static int[] createIntMsgBuffer(int pluginID, int msgID, int flags, int desiredSizeInts) {
        if (desiredSizeInts > 1024) {
            throw new PluginMsgException("bad desiredSizeInts=" + desiredSizeInts + " MAX_SIZE_INTS=1024");
        }
        int[] buf = new int[calcBufferSizeInts(desiredSizeInts)];
        writeHeader(buf, pluginID, msgID, flags, desiredSizeInts);
        return buf;
    }

    public static ByteBuffer createBufferMsgBuffer(int pluginID, int msgID, int flags, int desiredSizeBytes) {
        if (desiredSizeBytes > 4096) {
            throw new PluginMsgException("bad desiredSizeBytes=4096 MAX_SIZE_BYTES=4096");
        }
        ByteBuffer buf = ByteBuffer.allocate(calcBufferSizeBytes(desiredSizeBytes));
        buf.order(ByteOrder.LITTLE_ENDIAN);
        writeHeader(buf, pluginID, msgID, flags, desiredSizeBytes);
        return buf;
    }

    public static String msgBufferAsString(int[] buf) {
        if (buf == null) {
            return AbstractJsonLexerKt.NULL;
        }
        if (buf.length < 8) {
            throw new PluginMsgException("bad buf length=" + buf.length);
        }
        return toString(buf);
    }

    public static String msgBufferAsString(ByteBuffer buf) {
        if (buf.capacity() < 32) {
            throw new PluginMsgException("bad buf capacity=" + buf.capacity());
        }
        int pos = buf.position();
        buf.position(0);
        IntBuffer intBuf = buf.asIntBuffer();
        buf.position(pos);
        int[] ar = new int[intBuf.capacity()];
        intBuf.get(ar);
        return toString(ar);
    }

    private static String toString(int[] array) {
        if (array == null) {
            return AbstractJsonLexerKt.NULL;
        }
        if (array.length == 0) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder(array.length * 6);
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        sb.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(", 0x");
            sb.append(Integer.toHexString(array[i]));
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
