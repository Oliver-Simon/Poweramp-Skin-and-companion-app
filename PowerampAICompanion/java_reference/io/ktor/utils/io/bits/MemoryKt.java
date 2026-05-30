package io.ktor.utils.io.bits;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.core.internal.NumbersKt;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Memory.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\u000b\u001a\"\u0010\f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0006H\u0086\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a\"\u0010\f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0086\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0011\u001a*\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\rH\u0086\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a*\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\rH\u0086\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0016\u001a*\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0018H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u0015\u001a*\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0018H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"copyTo", "", "Lio/ktor/utils/io/bits/Memory;", "destination", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "length", "copyTo-JT6ljtQ", "(Ljava/nio/ByteBuffer;[BII)V", "", "(Ljava/nio/ByteBuffer;[BJI)V", "get", "", "index", "get-eY85DW0", "(Ljava/nio/ByteBuffer;I)B", "(Ljava/nio/ByteBuffer;J)B", "set", "value", "set-62zg_DM", "(Ljava/nio/ByteBuffer;IB)V", "(Ljava/nio/ByteBuffer;JB)V", "storeAt", "Lkotlin/UByte;", "storeAt-OEmREl0", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class MemoryKt {
    /* renamed from: get-eY85DW0, reason: not valid java name */
    public static final byte m261geteY85DW0(ByteBuffer get, int index) {
        Intrinsics.checkNotNullParameter(get, "$this$get");
        return get.get(index);
    }

    /* renamed from: get-eY85DW0, reason: not valid java name */
    public static final byte m262geteY85DW0(ByteBuffer get, long index) {
        Intrinsics.checkNotNullParameter(get, "$this$get");
        if (index < 2147483647L) {
            return get.get((int) index);
        }
        NumbersKt.failLongToIntConversion(index, "index");
        throw new KotlinNothingValueException();
    }

    /* renamed from: set-62zg_DM, reason: not valid java name */
    public static final void m264set62zg_DM(ByteBuffer set, long index, byte value) {
        Intrinsics.checkNotNullParameter(set, "$this$set");
        if (index < 2147483647L) {
            set.put((int) index, value);
        } else {
            NumbersKt.failLongToIntConversion(index, "index");
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: set-62zg_DM, reason: not valid java name */
    public static final void m263set62zg_DM(ByteBuffer set, int index, byte value) {
        Intrinsics.checkNotNullParameter(set, "$this$set");
        set.put(index, value);
    }

    /* renamed from: storeAt-OEmREl0, reason: not valid java name */
    public static final void m266storeAtOEmREl0(ByteBuffer storeAt, long index, byte value) {
        Intrinsics.checkNotNullParameter(storeAt, "$this$storeAt");
        if (index < 2147483647L) {
            storeAt.put((int) index, value);
        } else {
            NumbersKt.failLongToIntConversion(index, "index");
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeAt-OEmREl0, reason: not valid java name */
    public static final void m265storeAtOEmREl0(ByteBuffer storeAt, int index, byte value) {
        Intrinsics.checkNotNullParameter(storeAt, "$this$storeAt");
        storeAt.put(index, value);
    }

    /* renamed from: copyTo-JT6ljtQ, reason: not valid java name */
    public static final void m259copyToJT6ljtQ(ByteBuffer copyTo, byte[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        MemoryJvmKt.m254copyTo9zorpBc(copyTo, destination, offset, length, 0);
    }

    /* renamed from: copyTo-JT6ljtQ, reason: not valid java name */
    public static final void m260copyToJT6ljtQ(ByteBuffer copyTo, byte[] destination, long offset, int length) {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        MemoryJvmKt.m255copyTo9zorpBc(copyTo, destination, offset, length, 0);
    }
}
