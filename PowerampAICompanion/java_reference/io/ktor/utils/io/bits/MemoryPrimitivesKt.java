package io.ktor.utils.io.bits;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.core.internal.NumbersKt;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryPrimitives.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\r\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\b\u001a\"\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a\"\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\r\u001a\"\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a\"\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0012\u001a*\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a*\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0018\u001a*\u0010\u0019\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\nH\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001b\u001a*\u0010\u0019\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\nH\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001c\u001a*\u0010\u001d\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u000fH\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a*\u0010\u001d\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u000fH\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010 \u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006!"}, d2 = {"loadUIntAt", "Lkotlin/UInt;", "Lio/ktor/utils/io/bits/Memory;", TypedValues.CycleType.S_WAVE_OFFSET, "", "loadUIntAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)I", "", "(Ljava/nio/ByteBuffer;J)I", "loadULongAt", "Lkotlin/ULong;", "loadULongAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)J", "(Ljava/nio/ByteBuffer;J)J", "loadUShortAt", "Lkotlin/UShort;", "loadUShortAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)S", "(Ljava/nio/ByteBuffer;J)S", "storeUIntAt", "", "value", "storeUIntAt-c9EmHqw", "(Ljava/nio/ByteBuffer;II)V", "(Ljava/nio/ByteBuffer;JI)V", "storeULongAt", "storeULongAt-zwzI6Wg", "(Ljava/nio/ByteBuffer;IJ)V", "(Ljava/nio/ByteBuffer;JJ)V", "storeUShortAt", "storeUShortAt-4ET0KQI", "(Ljava/nio/ByteBuffer;IS)V", "(Ljava/nio/ByteBuffer;JS)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class MemoryPrimitivesKt {
    /* renamed from: loadUShortAt-eY85DW0, reason: not valid java name */
    public static final short m291loadUShortAteY85DW0(ByteBuffer loadUShortAt, int offset) {
        Intrinsics.checkNotNullParameter(loadUShortAt, "$this$loadUShortAt");
        return UShort.m791constructorimpl(loadUShortAt.getShort(offset));
    }

    /* renamed from: loadUShortAt-eY85DW0, reason: not valid java name */
    public static final short m292loadUShortAteY85DW0(ByteBuffer loadUShortAt, long offset) {
        Intrinsics.checkNotNullParameter(loadUShortAt, "$this$loadUShortAt");
        if (offset < 2147483647L) {
            return UShort.m791constructorimpl(loadUShortAt.getShort((int) offset));
        }
        NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: storeUShortAt-4ET0KQI, reason: not valid java name */
    public static final void m297storeUShortAt4ET0KQI(ByteBuffer storeUShortAt, int offset, short value) {
        Intrinsics.checkNotNullParameter(storeUShortAt, "$this$storeUShortAt");
        storeUShortAt.putShort(offset, value);
    }

    /* renamed from: storeUShortAt-4ET0KQI, reason: not valid java name */
    public static final void m298storeUShortAt4ET0KQI(ByteBuffer storeUShortAt, long offset, short value) {
        Intrinsics.checkNotNullParameter(storeUShortAt, "$this$storeUShortAt");
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int offset$iv$iv = (int) offset;
        storeUShortAt.putShort(offset$iv$iv, value);
    }

    /* renamed from: loadUIntAt-eY85DW0, reason: not valid java name */
    public static final int m287loadUIntAteY85DW0(ByteBuffer loadUIntAt, int offset) {
        Intrinsics.checkNotNullParameter(loadUIntAt, "$this$loadUIntAt");
        return UInt.m605constructorimpl(loadUIntAt.getInt(offset));
    }

    /* renamed from: loadUIntAt-eY85DW0, reason: not valid java name */
    public static final int m288loadUIntAteY85DW0(ByteBuffer loadUIntAt, long offset) {
        Intrinsics.checkNotNullParameter(loadUIntAt, "$this$loadUIntAt");
        if (offset < 2147483647L) {
            return UInt.m605constructorimpl(loadUIntAt.getInt((int) offset));
        }
        NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: storeUIntAt-c9EmHqw, reason: not valid java name */
    public static final void m293storeUIntAtc9EmHqw(ByteBuffer storeUIntAt, int offset, int value) {
        Intrinsics.checkNotNullParameter(storeUIntAt, "$this$storeUIntAt");
        storeUIntAt.putInt(offset, value);
    }

    /* renamed from: storeUIntAt-c9EmHqw, reason: not valid java name */
    public static final void m294storeUIntAtc9EmHqw(ByteBuffer storeUIntAt, long offset, int value) {
        Intrinsics.checkNotNullParameter(storeUIntAt, "$this$storeUIntAt");
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int offset$iv$iv = (int) offset;
        storeUIntAt.putInt(offset$iv$iv, value);
    }

    /* renamed from: loadULongAt-eY85DW0, reason: not valid java name */
    public static final long m289loadULongAteY85DW0(ByteBuffer loadULongAt, int offset) {
        Intrinsics.checkNotNullParameter(loadULongAt, "$this$loadULongAt");
        return ULong.m684constructorimpl(loadULongAt.getLong(offset));
    }

    /* renamed from: loadULongAt-eY85DW0, reason: not valid java name */
    public static final long m290loadULongAteY85DW0(ByteBuffer loadULongAt, long offset) {
        Intrinsics.checkNotNullParameter(loadULongAt, "$this$loadULongAt");
        if (offset < 2147483647L) {
            return ULong.m684constructorimpl(loadULongAt.getLong((int) offset));
        }
        NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
        throw new KotlinNothingValueException();
    }

    /* renamed from: storeULongAt-zwzI6Wg, reason: not valid java name */
    public static final void m295storeULongAtzwzI6Wg(ByteBuffer storeULongAt, int offset, long value) {
        Intrinsics.checkNotNullParameter(storeULongAt, "$this$storeULongAt");
        storeULongAt.putLong(offset, value);
    }

    /* renamed from: storeULongAt-zwzI6Wg, reason: not valid java name */
    public static final void m296storeULongAtzwzI6Wg(ByteBuffer storeULongAt, long offset, long value) {
        Intrinsics.checkNotNullParameter(storeULongAt, "$this$storeULongAt");
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int offset$iv$iv = (int) offset;
        storeULongAt.putLong(offset$iv$iv, value);
    }
}
