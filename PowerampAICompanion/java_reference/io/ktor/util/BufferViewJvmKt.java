package io.ktor.util;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.internal.jvm.ErrorsKt;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BufferViewJvm.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0007"}, d2 = {"read", "", "Ljava/nio/channels/ReadableByteChannel;", "buffer", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "write", "Ljava/nio/channels/WritableByteChannel;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BufferViewJvmKt {
    public static final int read(ReadableByteChannel $this$read, ChunkBuffer buffer) {
        Intrinsics.checkNotNullParameter($this$read, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        ChunkBuffer this_$iv = buffer;
        if (this_$iv.getLimit() - this_$iv.getWritePosition() == 0) {
            return 0;
        }
        ChunkBuffer this_$iv$iv = buffer;
        int rem$iv = this_$iv$iv.getLimit() - this_$iv$iv.getWritePosition();
        if (!(1 <= rem$iv)) {
            throw new IllegalArgumentException(("size 1 is greater than buffer's remaining capacity " + rem$iv).toString());
        }
        ByteBuffer buffer$iv = buffer.getMemory().duplicate();
        Intrinsics.checkNotNull(buffer$iv);
        int writePosition$iv = buffer.getWritePosition();
        int limit$iv = buffer.getLimit();
        buffer$iv.limit(limit$iv);
        buffer$iv.position(writePosition$iv);
        int count = $this$read.read(buffer$iv);
        int delta$iv = buffer$iv.position() - writePosition$iv;
        if (delta$iv < 0 || delta$iv > rem$iv) {
            ErrorsKt.wrongBufferPositionChangeError(delta$iv, 1);
            throw new KotlinNothingValueException();
        }
        buffer.commitWritten(delta$iv);
        return count;
    }

    @InternalAPI
    public static final int write(WritableByteChannel $this$write, ChunkBuffer buffer) {
        Intrinsics.checkNotNullParameter($this$write, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int readPosition$iv = buffer.getReadPosition();
        int writePosition$iv = buffer.getWritePosition();
        ByteBuffer bb$iv = buffer.getMemory().duplicate();
        Intrinsics.checkNotNull(bb$iv);
        bb$iv.limit(writePosition$iv);
        bb$iv.position(readPosition$iv);
        int count = $this$write.write(bb$iv);
        int delta$iv = bb$iv.position() - readPosition$iv;
        if (delta$iv < 0) {
            ErrorsKt.negativeShiftError(delta$iv);
            throw new KotlinNothingValueException();
        }
        if (bb$iv.limit() != writePosition$iv) {
            ErrorsKt.limitChangeError();
            throw new KotlinNothingValueException();
        }
        buffer.discardExact(delta$iv);
        return count;
    }
}
