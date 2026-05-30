package io.ktor.utils.io.bits;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.bits.Memory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryFactoryJvm.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a1\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001aQ\u0010\f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r*\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\r0\u000fH\u0086\bø\u0001\u0001ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0002\u0010\u0010\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006\u0011"}, d2 = {"of", "Lio/ktor/utils/io/bits/Memory;", "Lio/ktor/utils/io/bits/Memory$Companion;", "buffer", "Ljava/nio/ByteBuffer;", "(Lio/ktor/utils/io/bits/Memory$Companion;Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", "array", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "length", "(Lio/ktor/utils/io/bits/Memory$Companion;[BII)Ljava/nio/ByteBuffer;", "useMemory", "R", "block", "Lkotlin/Function1;", "([BIILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class MemoryFactoryJvmKt {
    public static /* synthetic */ Object useMemory$default(byte[] $this$useMemory_u24default, int offset, int length, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            offset = 0;
        }
        Intrinsics.checkNotNullParameter($this$useMemory_u24default, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ByteBuffer order = ByteBuffer.wrap($this$useMemory_u24default, offset, length).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        return block.invoke(Memory.m234boximpl(Memory.m235constructorimpl(order)));
    }

    public static final <R> R useMemory(byte[] $this$useMemory, int offset, int length, Function1<? super Memory, ? extends R> block) {
        Intrinsics.checkNotNullParameter($this$useMemory, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ByteBuffer order = ByteBuffer.wrap($this$useMemory, offset, length).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        return block.invoke(Memory.m234boximpl(Memory.m235constructorimpl(order)));
    }

    public static /* synthetic */ ByteBuffer of$default(Memory.Companion $this$of_u24default, byte[] array, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = array.length - offset;
        }
        Intrinsics.checkNotNullParameter($this$of_u24default, "<this>");
        Intrinsics.checkNotNullParameter(array, "array");
        ByteBuffer order = ByteBuffer.wrap(array, offset, length).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(array, offset, leng…der(ByteOrder.BIG_ENDIAN)");
        return Memory.m235constructorimpl(order);
    }

    public static final ByteBuffer of(Memory.Companion $this$of, byte[] array, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$of, "<this>");
        Intrinsics.checkNotNullParameter(array, "array");
        ByteBuffer order = ByteBuffer.wrap(array, offset, length).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(array, offset, leng…der(ByteOrder.BIG_ENDIAN)");
        return Memory.m235constructorimpl(order);
    }

    public static final ByteBuffer of(Memory.Companion $this$of, ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter($this$of, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        ByteBuffer order = buffer.slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "buffer.slice().order(ByteOrder.BIG_ENDIAN)");
        return Memory.m235constructorimpl(order);
    }
}
