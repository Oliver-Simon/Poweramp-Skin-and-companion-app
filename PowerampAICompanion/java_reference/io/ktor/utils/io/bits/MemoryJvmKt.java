package io.ktor.utils.io.bits;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.core.internal.NumbersKt;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryJvm.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u0005\n\u0002\b\b\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\n\u001a7\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a7\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u0010\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\b\u001a/\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a/\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0018\u001a\r\u0010\u0019\u001a\u00020\u0004*\u00020\u0004H\u0082\b\u001a\r\u0010\u001a\u001a\u00020\u0004*\u00020\u0004H\u0082\b\u001a\u001c\u0010\u001b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0000\u001a\r\u0010\u001c\u001a\u00020\u0004*\u00020\u0004H\u0082\b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"copyTo", "", "Lio/ktor/utils/io/bits/Memory;", "destination", "Ljava/nio/ByteBuffer;", TypedValues.CycleType.S_WAVE_OFFSET, "", "copyTo-62zg_DM", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;I)V", "", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;J)V", "", "length", "destinationOffset", "copyTo-9zorpBc", "(Ljava/nio/ByteBuffer;[BIII)V", "(Ljava/nio/ByteBuffer;[BJII)V", "copyTo-SG11BkQ", "fill", "count", "value", "", "fill-JT6ljtQ", "(Ljava/nio/ByteBuffer;IIB)V", "(Ljava/nio/ByteBuffer;JJB)V", "myDuplicate", "mySlice", "sliceSafe", "suppressNullCheck", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class MemoryJvmKt {
    /* renamed from: copyTo-9zorpBc, reason: not valid java name */
    public static final void m254copyTo9zorpBc(ByteBuffer copyTo, byte[] destination, int offset, int length, int destinationOffset) {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (copyTo.hasArray() && !copyTo.isReadOnly()) {
            System.arraycopy(copyTo.array(), copyTo.arrayOffset() + offset, destination, destinationOffset, length);
        } else {
            copyTo.duplicate().get(destination, destinationOffset, length);
        }
    }

    /* renamed from: copyTo-9zorpBc, reason: not valid java name */
    public static final void m255copyTo9zorpBc(ByteBuffer copyTo, byte[] destination, long offset, int length, int destinationOffset) {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (offset < 2147483647L) {
            m254copyTo9zorpBc(copyTo, destination, (int) offset, length, destinationOffset);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: copyTo-62zg_DM, reason: not valid java name */
    public static final void m252copyTo62zg_DM(ByteBuffer copyTo, ByteBuffer destination, int offset) {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        int size = destination.remaining();
        if (copyTo.hasArray() && !copyTo.isReadOnly() && destination.hasArray() && !destination.isReadOnly()) {
            int dstPosition = destination.position();
            System.arraycopy(copyTo.array(), copyTo.arrayOffset() + offset, destination.array(), destination.arrayOffset() + dstPosition, size);
            destination.position(dstPosition + size);
        } else {
            ByteBuffer source = copyTo.duplicate();
            source.limit(offset + size);
            source.position(offset);
            destination.put(source);
        }
    }

    /* renamed from: copyTo-62zg_DM, reason: not valid java name */
    public static final void m253copyTo62zg_DM(ByteBuffer copyTo, ByteBuffer destination, long offset) {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (offset < 2147483647L) {
            m252copyTo62zg_DM(copyTo, destination, (int) offset);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: copyTo-SG11BkQ, reason: not valid java name */
    public static final void m256copyToSG11BkQ(ByteBuffer copyTo, ByteBuffer destination, int offset) {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (copyTo.hasArray() && !copyTo.isReadOnly()) {
            byte[] source$iv = copyTo.array();
            Intrinsics.checkNotNullExpressionValue(source$iv, "array()");
            int sourceOffset$iv = copyTo.arrayOffset() + copyTo.position();
            int count$iv = copyTo.remaining();
            ByteBuffer order = ByteBuffer.wrap(source$iv, sourceOffset$iv, count$iv).slice().order(ByteOrder.BIG_ENDIAN);
            Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
            ByteBuffer sourceMemory$iv = Memory.m235constructorimpl(order);
            Memory.m236copyToJT6ljtQ(sourceMemory$iv, destination, 0, count$iv, offset);
            copyTo.position(copyTo.limit());
            return;
        }
        sliceSafe(destination, offset, copyTo.remaining()).put(copyTo);
    }

    private static final ByteBuffer myDuplicate(ByteBuffer $this$myDuplicate) {
        ByteBuffer myDuplicate$lambda$1 = $this$myDuplicate.duplicate();
        Intrinsics.checkNotNullExpressionValue(myDuplicate$lambda$1, "myDuplicate$lambda$1");
        return myDuplicate$lambda$1;
    }

    private static final ByteBuffer mySlice(ByteBuffer $this$mySlice) {
        ByteBuffer mySlice$lambda$2 = $this$mySlice.slice();
        Intrinsics.checkNotNullExpressionValue(mySlice$lambda$2, "mySlice$lambda$2");
        return mySlice$lambda$2;
    }

    private static final ByteBuffer suppressNullCheck(ByteBuffer $this$suppressNullCheck) {
        return $this$suppressNullCheck;
    }

    public static final ByteBuffer sliceSafe(ByteBuffer $this$sliceSafe, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$sliceSafe, "<this>");
        ByteBuffer myDuplicate$lambda$1 = $this$sliceSafe.duplicate();
        Intrinsics.checkNotNullExpressionValue(myDuplicate$lambda$1, "myDuplicate$lambda$1");
        myDuplicate$lambda$1.position(offset);
        myDuplicate$lambda$1.limit(offset + length);
        ByteBuffer mySlice$lambda$2 = myDuplicate$lambda$1.slice();
        Intrinsics.checkNotNullExpressionValue(mySlice$lambda$2, "mySlice$lambda$2");
        return mySlice$lambda$2;
    }

    /* renamed from: fill-JT6ljtQ, reason: not valid java name */
    public static final void m258fillJT6ljtQ(ByteBuffer fill, long offset, long count, byte value) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int i = (int) offset;
        if (count < 2147483647L) {
            m257fillJT6ljtQ(fill, i, (int) count, value);
        } else {
            NumbersKt.failLongToIntConversion(count, "count");
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: fill-JT6ljtQ, reason: not valid java name */
    public static final void m257fillJT6ljtQ(ByteBuffer fill, int offset, int count, byte value) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        int i = offset + count;
        for (int index = offset; index < i; index++) {
            fill.put(index, value);
        }
    }
}
