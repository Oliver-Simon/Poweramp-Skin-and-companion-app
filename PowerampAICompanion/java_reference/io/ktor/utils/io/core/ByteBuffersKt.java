package io.ktor.utils.io.core;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ByteBuffers.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u001d\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0082\u0010\u001a\u0012\u0010\t\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\n\u001a\u00020\u0007\u001a9\u0010\u000b\u001a\u00020\f*\u00020\u00052\u0006\u0010\r\u001a\u00020\u00042\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f0\u000fH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a9\u0010\u000b\u001a\u00020\f*\u00020\u00102\u0006\u0010\r\u001a\u00020\u00042\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f0\u000fH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a\u0012\u0010\u0011\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\n\u001a\u00020\u0007\u001a9\u0010\u0012\u001a\u00020\u0004*\u00020\u00132\u0006\u0010\r\u001a\u00020\u00042\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f0\u000fH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a9\u0010\u0014\u001a\u00020\f*\u00020\u00132\u0006\u0010\r\u001a\u00020\u00042\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f0\u000fH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0015"}, d2 = {"hasArray", "", "Lio/ktor/utils/io/core/Buffer;", "readAsMuchAsPossible", "", "Lio/ktor/utils/io/core/ByteReadPacket;", "bb", "Ljava/nio/ByteBuffer;", "copied", "readAvailable", "dst", "readDirect", "", ContentDisposition.Parameters.Size, "block", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Input;", "readFully", "writeByteBufferDirect", "Lio/ktor/utils/io/core/BytePacketBuilder;", "writeDirect", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ByteBuffersKt {
    public static final int readAvailable(ByteReadPacket $this$readAvailable, ByteBuffer dst) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        return readAsMuchAsPossible($this$readAvailable, dst, 0);
    }

    public static final int readFully(ByteReadPacket $this$readFully, ByteBuffer dst) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int rc = readAsMuchAsPossible($this$readFully, dst, 0);
        if (dst.hasRemaining()) {
            throw new EOFException("Not enough data in packet to fill buffer: " + dst.remaining() + " more bytes required");
        }
        return rc;
    }

    private static final int readAsMuchAsPossible(ByteReadPacket $this$readAsMuchAsPossible, ByteBuffer bb, int copied) {
        ChunkBuffer current;
        while (bb.hasRemaining() && (current = $this$readAsMuchAsPossible.prepareRead(1)) != null) {
            int destinationCapacity = bb.remaining();
            ChunkBuffer this_$iv = current;
            int available = this_$iv.getWritePosition() - this_$iv.getReadPosition();
            if (destinationCapacity >= available) {
                BufferUtilsJvmKt.readFully(current, bb, available);
                $this$readAsMuchAsPossible.releaseHead$ktor_io(current);
                copied += available;
            } else {
                BufferUtilsJvmKt.readFully(current, bb, destinationCapacity);
                $this$readAsMuchAsPossible.setHeadPosition(current.getReadPosition());
                return copied + destinationCapacity;
            }
        }
        return copied;
    }

    public static final void writeDirect(BytePacketBuilder $this$writeDirect, int size, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$writeDirect, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        BytePacketBuilder this_$iv$iv = $this$writeDirect;
        Buffer buffer$iv$iv = this_$iv$iv.prepareWriteHead(size);
        try {
            Buffer it$iv = buffer$iv$iv;
            ByteBuffer memory$iv$iv = it$iv.getMemory();
            int start$iv$iv = it$iv.getWritePosition();
            int endExclusive$iv$iv = it$iv.getLimit();
            ByteBuffer nioBuffer$iv$iv = Memory.m245slice87lwejk(memory$iv$iv, start$iv$iv, endExclusive$iv$iv - start$iv$iv);
            block.invoke(nioBuffer$iv$iv);
            if (!(nioBuffer$iv$iv.limit() == endExclusive$iv$iv - start$iv$iv)) {
                throw new IllegalStateException("Buffer's limit change is not allowed".toString());
            }
            int rc$iv$iv$iv = nioBuffer$iv$iv.position();
            it$iv.commitWritten(rc$iv$iv$iv);
            if (rc$iv$iv$iv >= 0) {
            } else {
                throw new IllegalStateException("The returned value shouldn't be negative".toString());
            }
        } finally {
            this_$iv$iv.afterHeadWrite();
        }
    }

    public static final int writeByteBufferDirect(BytePacketBuilder $this$writeByteBufferDirect, int size, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$writeByteBufferDirect, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        BytePacketBuilder this_$iv = $this$writeByteBufferDirect;
        Buffer buffer$iv = this_$iv.prepareWriteHead(size);
        try {
            Buffer it = buffer$iv;
            ByteBuffer memory$iv = it.getMemory();
            int start$iv = it.getWritePosition();
            int endExclusive$iv = it.getLimit();
            ByteBuffer nioBuffer$iv = Memory.m245slice87lwejk(memory$iv, start$iv, endExclusive$iv - start$iv);
            block.invoke(nioBuffer$iv);
            if (!(nioBuffer$iv.limit() == endExclusive$iv - start$iv)) {
                throw new IllegalStateException("Buffer's limit change is not allowed".toString());
            }
            int rc$iv$iv = nioBuffer$iv.position();
            it.commitWritten(rc$iv$iv);
            if (rc$iv$iv >= 0) {
                return rc$iv$iv;
            }
            throw new IllegalStateException("The returned value shouldn't be negative".toString());
        } finally {
            this_$iv.afterHeadWrite();
        }
    }

    public static final void readDirect(ByteReadPacket $this$readDirect, int size, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$readDirect, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ByteReadPacket $this$read$iv = $this$readDirect;
        ChunkBuffer buffer$iv = $this$read$iv.prepareRead(size);
        if (buffer$iv == null) {
            StringsKt.prematureEndOfStream(size);
            throw new KotlinNothingValueException();
        }
        int positionBefore$iv = buffer$iv.getReadPosition();
        try {
            ChunkBuffer it = buffer$iv;
            ByteBuffer memory$iv = it.getMemory();
            int start$iv = it.getReadPosition();
            int endExclusive$iv = it.getWritePosition();
            ByteBuffer nioBuffer$iv = Memory.m245slice87lwejk(memory$iv, start$iv, endExclusive$iv - start$iv);
            block.invoke(nioBuffer$iv);
            if (!(nioBuffer$iv.limit() == endExclusive$iv - start$iv)) {
                throw new IllegalStateException("Buffer's limit change is not allowed".toString());
            }
            int rc$iv$iv = nioBuffer$iv.position();
            it.discardExact(rc$iv$iv);
            int positionAfter$iv = buffer$iv.getReadPosition();
            if (positionAfter$iv < positionBefore$iv) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (positionAfter$iv == buffer$iv.getWritePosition()) {
                $this$read$iv.ensureNext(buffer$iv);
            } else {
                $this$read$iv.setHeadPosition(positionAfter$iv);
            }
        } catch (Throwable th) {
            int positionAfter$iv2 = buffer$iv.getReadPosition();
            if (positionAfter$iv2 < positionBefore$iv) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (positionAfter$iv2 == buffer$iv.getWritePosition()) {
                $this$read$iv.ensureNext(buffer$iv);
            } else {
                $this$read$iv.setHeadPosition(positionAfter$iv2);
            }
            throw th;
        }
    }

    @Deprecated(message = "Use read {} instead.")
    public static final void readDirect(Input $this$readDirect, int size, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$readDirect, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer buffer$iv = $this$readDirect.prepareRead(size);
        if (buffer$iv == null) {
            StringsKt.prematureEndOfStream(size);
            throw new KotlinNothingValueException();
        }
        int positionBefore$iv = buffer$iv.getReadPosition();
        try {
            ChunkBuffer view = buffer$iv;
            ByteBuffer memory$iv = view.getMemory();
            int start$iv = view.getReadPosition();
            int endExclusive$iv = view.getWritePosition();
            ByteBuffer it = Memory.m245slice87lwejk(memory$iv, start$iv, endExclusive$iv - start$iv);
            block.invoke(it);
            if (!(it.limit() == endExclusive$iv - start$iv)) {
                throw new IllegalStateException("Buffer's limit change is not allowed".toString());
            }
            int rc$iv$iv = it.position();
            view.discardExact(rc$iv$iv);
            int positionAfter$iv = buffer$iv.getReadPosition();
            if (positionAfter$iv < positionBefore$iv) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (positionAfter$iv == buffer$iv.getWritePosition()) {
                $this$readDirect.ensureNext(buffer$iv);
            } else {
                $this$readDirect.setHeadPosition(positionAfter$iv);
            }
        } catch (Throwable th) {
            int positionAfter$iv2 = buffer$iv.getReadPosition();
            if (positionAfter$iv2 < positionBefore$iv) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (positionAfter$iv2 == buffer$iv.getWritePosition()) {
                $this$readDirect.ensureNext(buffer$iv);
            } else {
                $this$readDirect.setHeadPosition(positionAfter$iv2);
            }
            throw th;
        }
    }

    public static final boolean hasArray(Buffer $this$hasArray) {
        Intrinsics.checkNotNullParameter($this$hasArray, "<this>");
        ByteBuffer it = $this$hasArray.getMemory();
        return it.hasArray() && !it.isReadOnly();
    }
}
