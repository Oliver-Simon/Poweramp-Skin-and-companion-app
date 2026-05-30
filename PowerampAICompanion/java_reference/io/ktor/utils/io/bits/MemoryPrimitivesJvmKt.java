package io.ktor.utils.io.bits;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.core.internal.NumbersKt;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryPrimitivesJvm.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0015\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\b\u001a\"\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a\"\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\r\u001a\"\u0010\u000e\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a\"\u0010\u000e\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0011\u001a\"\u0010\u0012\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u001a\"\u0010\u0012\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0015\u001a\"\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a\"\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a*\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010 \u001a*\u0010!\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\nH\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#\u001a*\u0010!\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\nH\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010$\u001a*\u0010%\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'\u001a*\u0010%\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010(\u001a*\u0010)\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010+\u001a*\u0010)\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010,\u001a*\u0010-\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0017H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/\u001a*\u0010-\u001a\u00020\u001c*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0017H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u00100\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00061"}, d2 = {"loadDoubleAt", "", "Lio/ktor/utils/io/bits/Memory;", TypedValues.CycleType.S_WAVE_OFFSET, "", "loadDoubleAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)D", "", "(Ljava/nio/ByteBuffer;J)D", "loadFloatAt", "", "loadFloatAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)F", "(Ljava/nio/ByteBuffer;J)F", "loadIntAt", "loadIntAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)I", "(Ljava/nio/ByteBuffer;J)I", "loadLongAt", "loadLongAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)J", "(Ljava/nio/ByteBuffer;J)J", "loadShortAt", "", "loadShortAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)S", "(Ljava/nio/ByteBuffer;J)S", "storeDoubleAt", "", "value", "storeDoubleAt-62zg_DM", "(Ljava/nio/ByteBuffer;ID)V", "(Ljava/nio/ByteBuffer;JD)V", "storeFloatAt", "storeFloatAt-62zg_DM", "(Ljava/nio/ByteBuffer;IF)V", "(Ljava/nio/ByteBuffer;JF)V", "storeIntAt", "storeIntAt-62zg_DM", "(Ljava/nio/ByteBuffer;II)V", "(Ljava/nio/ByteBuffer;JI)V", "storeLongAt", "storeLongAt-62zg_DM", "(Ljava/nio/ByteBuffer;IJ)V", "(Ljava/nio/ByteBuffer;JJ)V", "storeShortAt", "storeShortAt-62zg_DM", "(Ljava/nio/ByteBuffer;IS)V", "(Ljava/nio/ByteBuffer;JS)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class MemoryPrimitivesJvmKt {
    /* renamed from: loadShortAt-eY85DW0, reason: not valid java name */
    public static final short m275loadShortAteY85DW0(ByteBuffer loadShortAt, int offset) {
        Intrinsics.checkNotNullParameter(loadShortAt, "$this$loadShortAt");
        return loadShortAt.getShort(offset);
    }

    /* renamed from: loadShortAt-eY85DW0, reason: not valid java name */
    public static final short m276loadShortAteY85DW0(ByteBuffer loadShortAt, long offset) {
        Intrinsics.checkNotNullParameter(loadShortAt, "$this$loadShortAt");
        if (offset < 2147483647L) {
            return loadShortAt.getShort((int) offset);
        }
        NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: loadIntAt-eY85DW0, reason: not valid java name */
    public static final int m271loadIntAteY85DW0(ByteBuffer loadIntAt, int offset) {
        Intrinsics.checkNotNullParameter(loadIntAt, "$this$loadIntAt");
        return loadIntAt.getInt(offset);
    }

    /* renamed from: loadIntAt-eY85DW0, reason: not valid java name */
    public static final int m272loadIntAteY85DW0(ByteBuffer loadIntAt, long offset) {
        Intrinsics.checkNotNullParameter(loadIntAt, "$this$loadIntAt");
        if (offset < 2147483647L) {
            return loadIntAt.getInt((int) offset);
        }
        NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: loadLongAt-eY85DW0, reason: not valid java name */
    public static final long m273loadLongAteY85DW0(ByteBuffer loadLongAt, int offset) {
        Intrinsics.checkNotNullParameter(loadLongAt, "$this$loadLongAt");
        return loadLongAt.getLong(offset);
    }

    /* renamed from: loadLongAt-eY85DW0, reason: not valid java name */
    public static final long m274loadLongAteY85DW0(ByteBuffer loadLongAt, long offset) {
        Intrinsics.checkNotNullParameter(loadLongAt, "$this$loadLongAt");
        if (offset < 2147483647L) {
            return loadLongAt.getLong((int) offset);
        }
        NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: loadFloatAt-eY85DW0, reason: not valid java name */
    public static final float m269loadFloatAteY85DW0(ByteBuffer loadFloatAt, int offset) {
        Intrinsics.checkNotNullParameter(loadFloatAt, "$this$loadFloatAt");
        return loadFloatAt.getFloat(offset);
    }

    /* renamed from: loadFloatAt-eY85DW0, reason: not valid java name */
    public static final float m270loadFloatAteY85DW0(ByteBuffer loadFloatAt, long offset) {
        Intrinsics.checkNotNullParameter(loadFloatAt, "$this$loadFloatAt");
        if (offset < 2147483647L) {
            return loadFloatAt.getFloat((int) offset);
        }
        NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: loadDoubleAt-eY85DW0, reason: not valid java name */
    public static final double m267loadDoubleAteY85DW0(ByteBuffer loadDoubleAt, int offset) {
        Intrinsics.checkNotNullParameter(loadDoubleAt, "$this$loadDoubleAt");
        return loadDoubleAt.getDouble(offset);
    }

    /* renamed from: loadDoubleAt-eY85DW0, reason: not valid java name */
    public static final double m268loadDoubleAteY85DW0(ByteBuffer loadDoubleAt, long offset) {
        Intrinsics.checkNotNullParameter(loadDoubleAt, "$this$loadDoubleAt");
        if (offset < 2147483647L) {
            return loadDoubleAt.getDouble((int) offset);
        }
        NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: storeIntAt-62zg_DM, reason: not valid java name */
    public static final void m281storeIntAt62zg_DM(ByteBuffer storeIntAt, int offset, int value) {
        Intrinsics.checkNotNullParameter(storeIntAt, "$this$storeIntAt");
        storeIntAt.putInt(offset, value);
    }

    /* renamed from: storeIntAt-62zg_DM, reason: not valid java name */
    public static final void m282storeIntAt62zg_DM(ByteBuffer storeIntAt, long offset, int value) {
        Intrinsics.checkNotNullParameter(storeIntAt, "$this$storeIntAt");
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int offset$iv = (int) offset;
        storeIntAt.putInt(offset$iv, value);
    }

    /* renamed from: storeShortAt-62zg_DM, reason: not valid java name */
    public static final void m285storeShortAt62zg_DM(ByteBuffer storeShortAt, int offset, short value) {
        Intrinsics.checkNotNullParameter(storeShortAt, "$this$storeShortAt");
        storeShortAt.putShort(offset, value);
    }

    /* renamed from: storeShortAt-62zg_DM, reason: not valid java name */
    public static final void m286storeShortAt62zg_DM(ByteBuffer storeShortAt, long offset, short value) {
        Intrinsics.checkNotNullParameter(storeShortAt, "$this$storeShortAt");
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int offset$iv = (int) offset;
        storeShortAt.putShort(offset$iv, value);
    }

    /* renamed from: storeLongAt-62zg_DM, reason: not valid java name */
    public static final void m283storeLongAt62zg_DM(ByteBuffer storeLongAt, int offset, long value) {
        Intrinsics.checkNotNullParameter(storeLongAt, "$this$storeLongAt");
        storeLongAt.putLong(offset, value);
    }

    /* renamed from: storeLongAt-62zg_DM, reason: not valid java name */
    public static final void m284storeLongAt62zg_DM(ByteBuffer storeLongAt, long offset, long value) {
        Intrinsics.checkNotNullParameter(storeLongAt, "$this$storeLongAt");
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int offset$iv = (int) offset;
        storeLongAt.putLong(offset$iv, value);
    }

    /* renamed from: storeFloatAt-62zg_DM, reason: not valid java name */
    public static final void m279storeFloatAt62zg_DM(ByteBuffer storeFloatAt, int offset, float value) {
        Intrinsics.checkNotNullParameter(storeFloatAt, "$this$storeFloatAt");
        storeFloatAt.putFloat(offset, value);
    }

    /* renamed from: storeFloatAt-62zg_DM, reason: not valid java name */
    public static final void m280storeFloatAt62zg_DM(ByteBuffer storeFloatAt, long offset, float value) {
        Intrinsics.checkNotNullParameter(storeFloatAt, "$this$storeFloatAt");
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int offset$iv = (int) offset;
        storeFloatAt.putFloat(offset$iv, value);
    }

    /* renamed from: storeDoubleAt-62zg_DM, reason: not valid java name */
    public static final void m277storeDoubleAt62zg_DM(ByteBuffer storeDoubleAt, int offset, double value) {
        Intrinsics.checkNotNullParameter(storeDoubleAt, "$this$storeDoubleAt");
        storeDoubleAt.putDouble(offset, value);
    }

    /* renamed from: storeDoubleAt-62zg_DM, reason: not valid java name */
    public static final void m278storeDoubleAt62zg_DM(ByteBuffer storeDoubleAt, long offset, double value) {
        Intrinsics.checkNotNullParameter(storeDoubleAt, "$this$storeDoubleAt");
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int offset$iv = (int) offset;
        storeDoubleAt.putDouble(offset$iv, value);
    }
}
