package io.ktor.utils.io.core;

import io.ktor.utils.io.bits.MemoryJvmKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BufferPrimitivesJvm.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"readFully", "", "Lio/ktor/utils/io/core/Buffer;", "destination", "Ljava/nio/ByteBuffer;", "writeFully", "source", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BufferPrimitivesJvmKt {
    public static final void readFully(Buffer $this$readFully, ByteBuffer destination) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        int size = destination.remaining();
        ByteBuffer memory$iv = $this$readFully.getMemory();
        int start$iv = $this$readFully.getReadPosition();
        int endExclusive$iv = $this$readFully.getWritePosition();
        if (endExclusive$iv - start$iv >= size) {
            MemoryJvmKt.m252copyTo62zg_DM(memory$iv, destination, start$iv);
            Unit unit = Unit.INSTANCE;
            $this$readFully.discardExact(size);
            return;
        }
        throw new EOFException("Not enough bytes to read a buffer content of size " + size + '.');
    }

    public static final void writeFully(Buffer $this$writeFully, ByteBuffer source) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int size = source.remaining();
        ByteBuffer memory$iv = $this$writeFully.getMemory();
        int start$iv = $this$writeFully.getWritePosition();
        int endExclusive$iv = $this$writeFully.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv >= size) {
            MemoryJvmKt.m256copyToSG11BkQ(source, memory$iv, start$iv);
            $this$writeFully.commitWritten(size);
            return;
        }
        throw new InsufficientSpaceException("buffer content", size, writeRemaining$iv);
    }
}
