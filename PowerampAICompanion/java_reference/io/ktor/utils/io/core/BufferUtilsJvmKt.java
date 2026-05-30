package io.ktor.utils.io.core;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.internal.jvm.ErrorsKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BufferUtilsJvm.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0007\u001a1\u0010\u000b\u001a\u00020\u0007*\u00020\b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\rH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a$\u0010\u000b\u001a\u00020\u0007*\u00020\u00012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\rH\u0086\bø\u0001\u0000\u001a\u001a\u0010\u000f\u001a\u00020\u000e*\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0007\u001a\u0014\u0010\u0010\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0003H\u0000\u001a;\u0010\u0012\u001a\u00020\u0007*\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\rH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a,\u0010\u0012\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\rH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"ChunkBuffer", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "buffer", "Ljava/nio/ByteBuffer;", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "readAvailable", "", "Lio/ktor/utils/io/core/Buffer;", "dst", "length", "readDirect", "block", "Lkotlin/Function1;", "", "readFully", "resetFromContentToWrite", "child", "writeDirect", ContentDisposition.Parameters.Size, "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BufferUtilsJvmKt {
    public static /* synthetic */ ChunkBuffer ChunkBuffer$default(ByteBuffer byteBuffer, ObjectPool objectPool, int i, Object obj) {
        if ((i & 2) != 0) {
            objectPool = null;
        }
        return ChunkBuffer(byteBuffer, objectPool);
    }

    public static final ChunkBuffer ChunkBuffer(ByteBuffer buffer, ObjectPool<ChunkBuffer> objectPool) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Memory.Companion companion = Memory.INSTANCE;
        ByteBuffer order = buffer.slice().order(java.nio.ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "buffer.slice().order(ByteOrder.BIG_ENDIAN)");
        return new ChunkBuffer(Memory.m235constructorimpl(order), null, objectPool, null);
    }

    public static final int readDirect(ChunkBuffer $this$readDirect, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$readDirect, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        int readPosition = $this$readDirect.getReadPosition();
        int writePosition = $this$readDirect.getWritePosition();
        ByteBuffer bb = $this$readDirect.getMemory().duplicate();
        Intrinsics.checkNotNull(bb);
        bb.limit(writePosition);
        bb.position(readPosition);
        block.invoke(bb);
        int delta = bb.position() - readPosition;
        if (delta < 0) {
            ErrorsKt.negativeShiftError(delta);
            throw new KotlinNothingValueException();
        }
        if (bb.limit() != writePosition) {
            ErrorsKt.limitChangeError();
            throw new KotlinNothingValueException();
        }
        $this$readDirect.discardExact(delta);
        return delta;
    }

    public static final int writeDirect(ChunkBuffer $this$writeDirect, int size, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$writeDirect, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer this_$iv = $this$writeDirect;
        int rem = this_$iv.getLimit() - this_$iv.getWritePosition();
        if (!(size <= rem)) {
            throw new IllegalArgumentException(("size " + size + " is greater than buffer's remaining capacity " + rem).toString());
        }
        ByteBuffer buffer = $this$writeDirect.getMemory().duplicate();
        Intrinsics.checkNotNull(buffer);
        int writePosition = $this$writeDirect.getWritePosition();
        int limit = $this$writeDirect.getLimit();
        buffer.limit(limit);
        buffer.position(writePosition);
        block.invoke(buffer);
        int delta = buffer.position() - writePosition;
        if (delta < 0 || delta > rem) {
            ErrorsKt.wrongBufferPositionChangeError(delta, size);
            throw new KotlinNothingValueException();
        }
        $this$writeDirect.commitWritten(delta);
        return delta;
    }

    public static final void resetFromContentToWrite(ChunkBuffer $this$resetFromContentToWrite, ByteBuffer child) {
        Intrinsics.checkNotNullParameter($this$resetFromContentToWrite, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        $this$resetFromContentToWrite.resetForWrite(child.limit());
        $this$resetFromContentToWrite.commitWrittenUntilIndex(child.position());
    }

    public static final void readFully(Buffer $this$readFully, ByteBuffer dst, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        ByteBuffer memory$iv = $this$readFully.getMemory();
        int start$iv = $this$readFully.getReadPosition();
        int endExclusive$iv = $this$readFully.getWritePosition();
        if (endExclusive$iv - start$iv >= length) {
            int limit = dst.limit();
            try {
                dst.limit(dst.position() + length);
                MemoryJvmKt.m252copyTo62zg_DM(memory$iv, dst, start$iv);
                dst.limit(limit);
                Unit unit = Unit.INSTANCE;
                $this$readFully.discardExact(length);
                return;
            } catch (Throwable th) {
                dst.limit(limit);
                throw th;
            }
        }
        throw new EOFException("Not enough bytes to read a buffer content of size " + length + '.');
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, ByteBuffer byteBuffer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer.remaining();
        }
        return readAvailable(buffer, byteBuffer, i);
    }

    public static final int readAvailable(Buffer $this$readAvailable, ByteBuffer dst, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        if (!($this$readAvailable.getWritePosition() > $this$readAvailable.getReadPosition())) {
            return -1;
        }
        int size = Math.min($this$readAvailable.getWritePosition() - $this$readAvailable.getReadPosition(), length);
        readFully($this$readAvailable, dst, size);
        return size;
    }

    public static final int readDirect(Buffer $this$readDirect, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$readDirect, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ByteBuffer memory = $this$readDirect.getMemory();
        int start = $this$readDirect.getReadPosition();
        int endExclusive = $this$readDirect.getWritePosition();
        ByteBuffer nioBuffer = Memory.m245slice87lwejk(memory, start, endExclusive - start);
        block.invoke(nioBuffer);
        if (!(nioBuffer.limit() == endExclusive - start)) {
            throw new IllegalStateException("Buffer's limit change is not allowed".toString());
        }
        int rc$iv = nioBuffer.position();
        $this$readDirect.discardExact(rc$iv);
        return rc$iv;
    }

    public static /* synthetic */ int writeDirect$default(Buffer $this$writeDirect_u24default, int size, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
        }
        Intrinsics.checkNotNullParameter($this$writeDirect_u24default, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ByteBuffer memory = $this$writeDirect_u24default.getMemory();
        int start = $this$writeDirect_u24default.getWritePosition();
        int endExclusive = $this$writeDirect_u24default.getLimit();
        ByteBuffer nioBuffer = Memory.m245slice87lwejk(memory, start, endExclusive - start);
        block.invoke(nioBuffer);
        if (!(nioBuffer.limit() == endExclusive - start)) {
            throw new IllegalStateException("Buffer's limit change is not allowed".toString());
        }
        int rc$iv = nioBuffer.position();
        $this$writeDirect_u24default.commitWritten(rc$iv);
        return rc$iv;
    }

    public static final int writeDirect(Buffer $this$writeDirect, int size, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$writeDirect, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ByteBuffer memory = $this$writeDirect.getMemory();
        int start = $this$writeDirect.getWritePosition();
        int endExclusive = $this$writeDirect.getLimit();
        ByteBuffer nioBuffer = Memory.m245slice87lwejk(memory, start, endExclusive - start);
        block.invoke(nioBuffer);
        if (!(nioBuffer.limit() == endExclusive - start)) {
            throw new IllegalStateException("Buffer's limit change is not allowed".toString());
        }
        int rc$iv = nioBuffer.position();
        $this$writeDirect.commitWritten(rc$iv);
        return rc$iv;
    }
}
