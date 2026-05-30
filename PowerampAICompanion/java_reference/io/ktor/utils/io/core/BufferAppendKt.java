package io.ktor.utils.io.core;

import com.maxmpz.poweramp.player.RouterConsts;
import io.ktor.utils.io.bits.Memory;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BufferAppend.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¨\u0006\t"}, d2 = {"writeBufferAppend", "", "Lio/ktor/utils/io/core/Buffer;", RouterConsts.DEVICE_NAME_OTHER, "maxSize", "writeBufferAppendUnreserve", "", "writeSize", "writeBufferPrepend", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BufferAppendKt {
    public static final int writeBufferAppend(Buffer $this$writeBufferAppend, Buffer other, int maxSize) {
        Intrinsics.checkNotNullParameter($this$writeBufferAppend, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int size = Math.min(other.getWritePosition() - other.getReadPosition(), maxSize);
        if ($this$writeBufferAppend.getLimit() - $this$writeBufferAppend.getWritePosition() <= size) {
            writeBufferAppendUnreserve($this$writeBufferAppend, size);
        }
        ByteBuffer dst = $this$writeBufferAppend.getMemory();
        int dstOffset = $this$writeBufferAppend.getWritePosition();
        $this$writeBufferAppend.getLimit();
        ByteBuffer src = other.getMemory();
        int srcOffset = other.getReadPosition();
        other.getWritePosition();
        Memory.m236copyToJT6ljtQ(src, dst, srcOffset, size, dstOffset);
        other.discardExact(size);
        $this$writeBufferAppend.commitWritten(size);
        return size;
    }

    public static final int writeBufferPrepend(Buffer $this$writeBufferPrepend, Buffer other) {
        Intrinsics.checkNotNullParameter($this$writeBufferPrepend, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int size = other.getWritePosition() - other.getReadPosition();
        int readPosition = $this$writeBufferPrepend.getReadPosition();
        if (readPosition < size) {
            throw new IllegalArgumentException("Not enough space in the beginning to prepend bytes");
        }
        int newReadPosition = readPosition - size;
        Memory.m236copyToJT6ljtQ(other.getMemory(), $this$writeBufferPrepend.getMemory(), other.getReadPosition(), size, newReadPosition);
        other.discardExact(size);
        $this$writeBufferPrepend.releaseStartGap$ktor_io(newReadPosition);
        return size;
    }

    private static final void writeBufferAppendUnreserve(Buffer $this$writeBufferAppendUnreserve, int writeSize) {
        if (($this$writeBufferAppendUnreserve.getLimit() - $this$writeBufferAppendUnreserve.getWritePosition()) + ($this$writeBufferAppendUnreserve.getCapacity() - $this$writeBufferAppendUnreserve.getLimit()) < writeSize) {
            throw new IllegalArgumentException("Can't append buffer: not enough free space at the end");
        }
        int newWritePosition = $this$writeBufferAppendUnreserve.getWritePosition() + writeSize;
        int overrunSize = newWritePosition - $this$writeBufferAppendUnreserve.getLimit();
        if (overrunSize > 0) {
            $this$writeBufferAppendUnreserve.releaseEndGap$ktor_io();
        }
    }
}
