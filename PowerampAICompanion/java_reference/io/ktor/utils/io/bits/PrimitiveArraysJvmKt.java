package io.ktor.utils.io.bits;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.core.internal.NumbersKt;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrimitiveArraysJvm.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0010\u0017\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u001a;\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\f\u001a;\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a;\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0011\u001a;\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00132\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a;\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00132\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0016\u001a;\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001a\u001a;\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00182\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001b\u001a;\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001d2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a;\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u001d2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010 \u001a;\u0010!\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\n\u001a;\u0010!\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\f\u001a;\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0010\u001a;\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0011\u001a;\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00132\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010\u0015\u001a;\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u00132\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010\u0016\u001a;\u0010)\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00182\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\u001a\u001a;\u0010)\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u00182\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\u001b\u001a;\u0010+\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u001d2\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010\u001f\u001a;\u0010+\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u001d2\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010 \u001a\u0015\u0010-\u001a\u00020.*\u00020.2\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006/"}, d2 = {"loadDoubleArray", "", "Lio/ktor/utils/io/bits/Memory;", TypedValues.CycleType.S_WAVE_OFFSET, "", "destination", "", "destinationOffset", "count", "loadDoubleArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[DII)V", "", "(Ljava/nio/ByteBuffer;J[DII)V", "loadFloatArray", "", "loadFloatArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[FII)V", "(Ljava/nio/ByteBuffer;J[FII)V", "loadIntArray", "", "loadIntArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[III)V", "(Ljava/nio/ByteBuffer;J[III)V", "loadLongArray", "", "loadLongArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[JII)V", "(Ljava/nio/ByteBuffer;J[JII)V", "loadShortArray", "", "loadShortArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[SII)V", "(Ljava/nio/ByteBuffer;J[SII)V", "storeDoubleArray", "source", "sourceOffset", "storeDoubleArray-9zorpBc", "storeFloatArray", "storeFloatArray-9zorpBc", "storeIntArray", "storeIntArray-9zorpBc", "storeLongArray", "storeLongArray-9zorpBc", "storeShortArray", "storeShortArray-9zorpBc", "withOffset", "Ljava/nio/ByteBuffer;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class PrimitiveArraysJvmKt {
    /* renamed from: loadShortArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m357loadShortArray9zorpBc$default(ByteBuffer byteBuffer, int i, short[] sArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sArr.length - i2;
        }
        m355loadShortArray9zorpBc(byteBuffer, i, sArr, i2, i3);
    }

    /* renamed from: loadShortArray-9zorpBc, reason: not valid java name */
    public static final void m355loadShortArray9zorpBc(ByteBuffer loadShortArray, int offset, short[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadShortArray, "$this$loadShortArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ByteBuffer $this$withOffset_u24lambda_u240$iv = loadShortArray.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240$iv);
        $this$withOffset_u24lambda_u240$iv.position(offset);
        $this$withOffset_u24lambda_u240$iv.asShortBuffer().get(destination, destinationOffset, count);
    }

    /* renamed from: loadShortArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m358loadShortArray9zorpBc$default(ByteBuffer byteBuffer, long j, short[] sArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 8) == 0) {
            i5 = i2;
        } else {
            i5 = sArr.length - i4;
        }
        m356loadShortArray9zorpBc(byteBuffer, j, sArr, i4, i5);
    }

    /* renamed from: loadShortArray-9zorpBc, reason: not valid java name */
    public static final void m356loadShortArray9zorpBc(ByteBuffer loadShortArray, long offset, short[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadShortArray, "$this$loadShortArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (offset < 2147483647L) {
            m355loadShortArray9zorpBc(loadShortArray, (int) offset, destination, destinationOffset, count);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadIntArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m349loadIntArray9zorpBc$default(ByteBuffer byteBuffer, int i, int[] iArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = iArr.length - i2;
        }
        m347loadIntArray9zorpBc(byteBuffer, i, iArr, i2, i3);
    }

    /* renamed from: loadIntArray-9zorpBc, reason: not valid java name */
    public static final void m347loadIntArray9zorpBc(ByteBuffer loadIntArray, int offset, int[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadIntArray, "$this$loadIntArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ByteBuffer $this$withOffset_u24lambda_u240$iv = loadIntArray.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240$iv);
        $this$withOffset_u24lambda_u240$iv.position(offset);
        $this$withOffset_u24lambda_u240$iv.asIntBuffer().get(destination, destinationOffset, count);
    }

    /* renamed from: loadIntArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m350loadIntArray9zorpBc$default(ByteBuffer byteBuffer, long j, int[] iArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 8) == 0) {
            i5 = i2;
        } else {
            i5 = iArr.length - i4;
        }
        m348loadIntArray9zorpBc(byteBuffer, j, iArr, i4, i5);
    }

    /* renamed from: loadIntArray-9zorpBc, reason: not valid java name */
    public static final void m348loadIntArray9zorpBc(ByteBuffer loadIntArray, long offset, int[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadIntArray, "$this$loadIntArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (offset < 2147483647L) {
            m347loadIntArray9zorpBc(loadIntArray, (int) offset, destination, destinationOffset, count);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadLongArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m353loadLongArray9zorpBc$default(ByteBuffer byteBuffer, int i, long[] jArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = jArr.length - i2;
        }
        m351loadLongArray9zorpBc(byteBuffer, i, jArr, i2, i3);
    }

    /* renamed from: loadLongArray-9zorpBc, reason: not valid java name */
    public static final void m351loadLongArray9zorpBc(ByteBuffer loadLongArray, int offset, long[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadLongArray, "$this$loadLongArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ByteBuffer $this$withOffset_u24lambda_u240$iv = loadLongArray.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240$iv);
        $this$withOffset_u24lambda_u240$iv.position(offset);
        $this$withOffset_u24lambda_u240$iv.asLongBuffer().get(destination, destinationOffset, count);
    }

    /* renamed from: loadLongArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m354loadLongArray9zorpBc$default(ByteBuffer byteBuffer, long j, long[] jArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 8) == 0) {
            i5 = i2;
        } else {
            i5 = jArr.length - i4;
        }
        m352loadLongArray9zorpBc(byteBuffer, j, jArr, i4, i5);
    }

    /* renamed from: loadLongArray-9zorpBc, reason: not valid java name */
    public static final void m352loadLongArray9zorpBc(ByteBuffer loadLongArray, long offset, long[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadLongArray, "$this$loadLongArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (offset < 2147483647L) {
            m351loadLongArray9zorpBc(loadLongArray, (int) offset, destination, destinationOffset, count);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadFloatArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m345loadFloatArray9zorpBc$default(ByteBuffer byteBuffer, int i, float[] fArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = fArr.length - i2;
        }
        m343loadFloatArray9zorpBc(byteBuffer, i, fArr, i2, i3);
    }

    /* renamed from: loadFloatArray-9zorpBc, reason: not valid java name */
    public static final void m343loadFloatArray9zorpBc(ByteBuffer loadFloatArray, int offset, float[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadFloatArray, "$this$loadFloatArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ByteBuffer $this$withOffset_u24lambda_u240$iv = loadFloatArray.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240$iv);
        $this$withOffset_u24lambda_u240$iv.position(offset);
        $this$withOffset_u24lambda_u240$iv.asFloatBuffer().get(destination, destinationOffset, count);
    }

    /* renamed from: loadFloatArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m346loadFloatArray9zorpBc$default(ByteBuffer byteBuffer, long j, float[] fArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 8) == 0) {
            i5 = i2;
        } else {
            i5 = fArr.length - i4;
        }
        m344loadFloatArray9zorpBc(byteBuffer, j, fArr, i4, i5);
    }

    /* renamed from: loadFloatArray-9zorpBc, reason: not valid java name */
    public static final void m344loadFloatArray9zorpBc(ByteBuffer loadFloatArray, long offset, float[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadFloatArray, "$this$loadFloatArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (offset < 2147483647L) {
            m343loadFloatArray9zorpBc(loadFloatArray, (int) offset, destination, destinationOffset, count);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: loadDoubleArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m341loadDoubleArray9zorpBc$default(ByteBuffer byteBuffer, int i, double[] dArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = dArr.length - i2;
        }
        m339loadDoubleArray9zorpBc(byteBuffer, i, dArr, i2, i3);
    }

    /* renamed from: loadDoubleArray-9zorpBc, reason: not valid java name */
    public static final void m339loadDoubleArray9zorpBc(ByteBuffer loadDoubleArray, int offset, double[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadDoubleArray, "$this$loadDoubleArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ByteBuffer $this$withOffset_u24lambda_u240$iv = loadDoubleArray.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240$iv);
        $this$withOffset_u24lambda_u240$iv.position(offset);
        $this$withOffset_u24lambda_u240$iv.asDoubleBuffer().get(destination, destinationOffset, count);
    }

    /* renamed from: loadDoubleArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m342loadDoubleArray9zorpBc$default(ByteBuffer byteBuffer, long j, double[] dArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 8) == 0) {
            i5 = i2;
        } else {
            i5 = dArr.length - i4;
        }
        m340loadDoubleArray9zorpBc(byteBuffer, j, dArr, i4, i5);
    }

    /* renamed from: loadDoubleArray-9zorpBc, reason: not valid java name */
    public static final void m340loadDoubleArray9zorpBc(ByteBuffer loadDoubleArray, long offset, double[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadDoubleArray, "$this$loadDoubleArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (offset < 2147483647L) {
            m339loadDoubleArray9zorpBc(loadDoubleArray, (int) offset, destination, destinationOffset, count);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeShortArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m377storeShortArray9zorpBc$default(ByteBuffer byteBuffer, int i, short[] sArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sArr.length - i2;
        }
        m375storeShortArray9zorpBc(byteBuffer, i, sArr, i2, i3);
    }

    /* renamed from: storeShortArray-9zorpBc, reason: not valid java name */
    public static final void m375storeShortArray9zorpBc(ByteBuffer storeShortArray, int offset, short[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeShortArray, "$this$storeShortArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer $this$withOffset_u24lambda_u240$iv = storeShortArray.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240$iv);
        $this$withOffset_u24lambda_u240$iv.position(offset);
        $this$withOffset_u24lambda_u240$iv.asShortBuffer().put(source, sourceOffset, count);
    }

    /* renamed from: storeShortArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m378storeShortArray9zorpBc$default(ByteBuffer byteBuffer, long j, short[] sArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 8) == 0) {
            i5 = i2;
        } else {
            i5 = sArr.length - i4;
        }
        m376storeShortArray9zorpBc(byteBuffer, j, sArr, i4, i5);
    }

    /* renamed from: storeShortArray-9zorpBc, reason: not valid java name */
    public static final void m376storeShortArray9zorpBc(ByteBuffer storeShortArray, long offset, short[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeShortArray, "$this$storeShortArray");
        Intrinsics.checkNotNullParameter(source, "source");
        if (offset < 2147483647L) {
            m375storeShortArray9zorpBc(storeShortArray, (int) offset, source, sourceOffset, count);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeIntArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m369storeIntArray9zorpBc$default(ByteBuffer byteBuffer, int i, int[] iArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = iArr.length - i2;
        }
        m367storeIntArray9zorpBc(byteBuffer, i, iArr, i2, i3);
    }

    /* renamed from: storeIntArray-9zorpBc, reason: not valid java name */
    public static final void m367storeIntArray9zorpBc(ByteBuffer storeIntArray, int offset, int[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeIntArray, "$this$storeIntArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer $this$withOffset_u24lambda_u240$iv = storeIntArray.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240$iv);
        $this$withOffset_u24lambda_u240$iv.position(offset);
        $this$withOffset_u24lambda_u240$iv.asIntBuffer().put(source, sourceOffset, count);
    }

    /* renamed from: storeIntArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m370storeIntArray9zorpBc$default(ByteBuffer byteBuffer, long j, int[] iArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 8) == 0) {
            i5 = i2;
        } else {
            i5 = iArr.length - i4;
        }
        m368storeIntArray9zorpBc(byteBuffer, j, iArr, i4, i5);
    }

    /* renamed from: storeIntArray-9zorpBc, reason: not valid java name */
    public static final void m368storeIntArray9zorpBc(ByteBuffer storeIntArray, long offset, int[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeIntArray, "$this$storeIntArray");
        Intrinsics.checkNotNullParameter(source, "source");
        if (offset < 2147483647L) {
            m367storeIntArray9zorpBc(storeIntArray, (int) offset, source, sourceOffset, count);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeLongArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m373storeLongArray9zorpBc$default(ByteBuffer byteBuffer, int i, long[] jArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = jArr.length - i2;
        }
        m371storeLongArray9zorpBc(byteBuffer, i, jArr, i2, i3);
    }

    /* renamed from: storeLongArray-9zorpBc, reason: not valid java name */
    public static final void m371storeLongArray9zorpBc(ByteBuffer storeLongArray, int offset, long[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeLongArray, "$this$storeLongArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer $this$withOffset_u24lambda_u240$iv = storeLongArray.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240$iv);
        $this$withOffset_u24lambda_u240$iv.position(offset);
        $this$withOffset_u24lambda_u240$iv.asLongBuffer().put(source, sourceOffset, count);
    }

    /* renamed from: storeLongArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m374storeLongArray9zorpBc$default(ByteBuffer byteBuffer, long j, long[] jArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 8) == 0) {
            i5 = i2;
        } else {
            i5 = jArr.length - i4;
        }
        m372storeLongArray9zorpBc(byteBuffer, j, jArr, i4, i5);
    }

    /* renamed from: storeLongArray-9zorpBc, reason: not valid java name */
    public static final void m372storeLongArray9zorpBc(ByteBuffer storeLongArray, long offset, long[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeLongArray, "$this$storeLongArray");
        Intrinsics.checkNotNullParameter(source, "source");
        if (offset < 2147483647L) {
            m371storeLongArray9zorpBc(storeLongArray, (int) offset, source, sourceOffset, count);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeFloatArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m365storeFloatArray9zorpBc$default(ByteBuffer byteBuffer, int i, float[] fArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = fArr.length - i2;
        }
        m363storeFloatArray9zorpBc(byteBuffer, i, fArr, i2, i3);
    }

    /* renamed from: storeFloatArray-9zorpBc, reason: not valid java name */
    public static final void m363storeFloatArray9zorpBc(ByteBuffer storeFloatArray, int offset, float[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeFloatArray, "$this$storeFloatArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer $this$withOffset_u24lambda_u240$iv = storeFloatArray.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240$iv);
        $this$withOffset_u24lambda_u240$iv.position(offset);
        $this$withOffset_u24lambda_u240$iv.asFloatBuffer().put(source, sourceOffset, count);
    }

    /* renamed from: storeFloatArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m366storeFloatArray9zorpBc$default(ByteBuffer byteBuffer, long j, float[] fArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 8) == 0) {
            i5 = i2;
        } else {
            i5 = fArr.length - i4;
        }
        m364storeFloatArray9zorpBc(byteBuffer, j, fArr, i4, i5);
    }

    /* renamed from: storeFloatArray-9zorpBc, reason: not valid java name */
    public static final void m364storeFloatArray9zorpBc(ByteBuffer storeFloatArray, long offset, float[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeFloatArray, "$this$storeFloatArray");
        Intrinsics.checkNotNullParameter(source, "source");
        if (offset < 2147483647L) {
            m363storeFloatArray9zorpBc(storeFloatArray, (int) offset, source, sourceOffset, count);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: storeDoubleArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m361storeDoubleArray9zorpBc$default(ByteBuffer byteBuffer, int i, double[] dArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = dArr.length - i2;
        }
        m359storeDoubleArray9zorpBc(byteBuffer, i, dArr, i2, i3);
    }

    /* renamed from: storeDoubleArray-9zorpBc, reason: not valid java name */
    public static final void m359storeDoubleArray9zorpBc(ByteBuffer storeDoubleArray, int offset, double[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeDoubleArray, "$this$storeDoubleArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer $this$withOffset_u24lambda_u240$iv = storeDoubleArray.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240$iv);
        $this$withOffset_u24lambda_u240$iv.position(offset);
        $this$withOffset_u24lambda_u240$iv.asDoubleBuffer().put(source, sourceOffset, count);
    }

    /* renamed from: storeDoubleArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m362storeDoubleArray9zorpBc$default(ByteBuffer byteBuffer, long j, double[] dArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 8) == 0) {
            i5 = i2;
        } else {
            i5 = dArr.length - i4;
        }
        m360storeDoubleArray9zorpBc(byteBuffer, j, dArr, i4, i5);
    }

    /* renamed from: storeDoubleArray-9zorpBc, reason: not valid java name */
    public static final void m360storeDoubleArray9zorpBc(ByteBuffer storeDoubleArray, long offset, double[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeDoubleArray, "$this$storeDoubleArray");
        Intrinsics.checkNotNullParameter(source, "source");
        if (offset < 2147483647L) {
            m359storeDoubleArray9zorpBc(storeDoubleArray, (int) offset, source, sourceOffset, count);
        } else {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
    }

    private static final ByteBuffer withOffset(ByteBuffer $this$withOffset, int offset) {
        ByteBuffer $this$withOffset_u24lambda_u240 = $this$withOffset.duplicate();
        Intrinsics.checkNotNull($this$withOffset_u24lambda_u240);
        $this$withOffset_u24lambda_u240.position(offset);
        return $this$withOffset_u24lambda_u240;
    }
}
