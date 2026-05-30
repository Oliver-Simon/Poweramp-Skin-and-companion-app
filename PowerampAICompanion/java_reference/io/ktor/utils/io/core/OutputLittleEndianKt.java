package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import kotlin.Metadata;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OutputLittleEndian.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\u001aM\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00020\u0006¢\u0006\u0002\b\bH\u0082\b¢\u0006\u0002\u0010\t\u001aU\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00020\u0006¢\u0006\u0002\b\bH\u0082\b¢\u0006\u0002\u0010\f\u001aD\u0010\r\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u001d\u0010\u0013\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\bH\u0082\b\u001aD\u0010\r\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u001d\u0010\u0013\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\bH\u0082\b\u001a\u001a\u0010\u0016\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000b\u001a\u0012\u0010\u0018\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0017\u001a\u0012\u0010\u0018\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0017\u001a\u001a\u0010\u0019\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0012\u0010\u001b\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u001a\u001a\u0012\u0010\u001b\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u001a\u001a&\u0010\u001c\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u001a&\u0010\u001c\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u001a&\u0010\u001c\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u001d\u001a\u00020 2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u001a&\u0010\u001c\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u001d\u001a\u00020!2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u001a&\u0010\u001c\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\"2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u001a3\u0010\u001c\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u001d\u001a\u00020#2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%\u001a3\u0010\u001c\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u001d\u001a\u00020&2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(\u001a3\u0010\u001c\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u001d\u001a\u00020)2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010+\u001a&\u0010\u001c\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u001a&\u0010\u001c\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u001a&\u0010\u001c\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u001d\u001a\u00020 2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u001a&\u0010\u001c\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u001d\u001a\u00020!2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u001a&\u0010\u001c\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u001d\u001a\u00020\"2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u001a3\u0010\u001c\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u001d\u001a\u00020#2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010,\u001a3\u0010\u001c\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u001d\u001a\u00020&2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010-\u001a3\u0010\u001c\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u001d\u001a\u00020)2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010.\u001a\u001a\u0010/\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b\u001a\u0012\u00100\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0010\u001a\u0012\u00100\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0010\u001a\u001a\u00101\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0004\u001a\u0002022\u0006\u0010\n\u001a\u00020\u000b\u001a\u0012\u00103\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u0004\u001a\u000202\u001a\u0012\u00103\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0004\u001a\u000202\u001a\u001a\u00104\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0004\u001a\u0002052\u0006\u0010\n\u001a\u00020\u000b\u001a\u0012\u00106\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u0004\u001a\u000205\u001a\u0012\u00106\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0004\u001a\u000205\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00067"}, d2 = {"writePrimitiveTemplate", "", ExifInterface.GPS_DIRECTION_TRUE, "", "value", "write", "Lkotlin/Function1;", "reverse", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "byteOrder", "Lio/ktor/utils/io/core/ByteOrder;", "(Ljava/lang/Object;Lio/ktor/utils/io/core/ByteOrder;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "writeArrayTemplate", "Lio/ktor/utils/io/core/Buffer;", TypedValues.CycleType.S_WAVE_OFFSET, "", "length", "componentSize", "writeComponent", "Lkotlin/Function2;", "Lio/ktor/utils/io/core/Output;", "writeDouble", "", "writeDoubleLittleEndian", "writeFloat", "", "writeFloatLittleEndian", "writeFullyLittleEndian", "source", "", "", "", "", "", "Lkotlin/UIntArray;", "writeFullyLittleEndian-o2ZM2JE", "(Lio/ktor/utils/io/core/Buffer;[III)V", "Lkotlin/ULongArray;", "writeFullyLittleEndian-pqYNikA", "(Lio/ktor/utils/io/core/Buffer;[JII)V", "Lkotlin/UShortArray;", "writeFullyLittleEndian-Wt3Bwxc", "(Lio/ktor/utils/io/core/Buffer;[SII)V", "(Lio/ktor/utils/io/core/Output;[III)V", "(Lio/ktor/utils/io/core/Output;[JII)V", "(Lio/ktor/utils/io/core/Output;[SII)V", "writeInt", "writeIntLittleEndian", "writeLong", "", "writeLongLittleEndian", "writeShort", "", "writeShortLittleEndian", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OutputLittleEndianKt {

    /* compiled from: OutputLittleEndian.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ByteOrder.values().length];
            try {
                iArr[ByteOrder.BIG_ENDIAN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void writeShort(Output $this$writeShort, short value, ByteOrder byteOrder) {
        short it;
        Intrinsics.checkNotNullParameter($this$writeShort, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            it = value;
        } else {
            it = Short.reverseBytes(value);
        }
        OutputPrimitivesKt.writeShort($this$writeShort, it);
    }

    public static final void writeInt(Output $this$writeInt, int value, ByteOrder byteOrder) {
        int it;
        Intrinsics.checkNotNullParameter($this$writeInt, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            it = value;
        } else {
            it = Integer.reverseBytes(value);
        }
        OutputPrimitivesKt.writeInt($this$writeInt, it);
    }

    public static final void writeLong(Output $this$writeLong, long value, ByteOrder byteOrder) {
        long it;
        Intrinsics.checkNotNullParameter($this$writeLong, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            it = value;
        } else {
            it = Long.reverseBytes(value);
        }
        OutputPrimitivesKt.writeLong($this$writeLong, it);
    }

    public static final void writeFloat(Output $this$writeFloat, float value, ByteOrder byteOrder) {
        float it;
        Intrinsics.checkNotNullParameter($this$writeFloat, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            it = value;
        } else {
            it = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(value)));
        }
        OutputPrimitivesKt.writeFloat($this$writeFloat, it);
    }

    public static final void writeDouble(Output $this$writeDouble, double value, ByteOrder byteOrder) {
        double it;
        Intrinsics.checkNotNullParameter($this$writeDouble, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            it = value;
        } else {
            it = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(value)));
        }
        OutputPrimitivesKt.writeDouble($this$writeDouble, it);
    }

    public static final void writeShortLittleEndian(Output $this$writeShortLittleEndian, short value) {
        Intrinsics.checkNotNullParameter($this$writeShortLittleEndian, "<this>");
        short $this$reverseByteOrder$iv = Short.reverseBytes(value);
        OutputPrimitivesKt.writeShort($this$writeShortLittleEndian, $this$reverseByteOrder$iv);
    }

    public static final void writeIntLittleEndian(Output $this$writeIntLittleEndian, int value) {
        Intrinsics.checkNotNullParameter($this$writeIntLittleEndian, "<this>");
        int $this$reverseByteOrder$iv = Integer.reverseBytes(value);
        OutputPrimitivesKt.writeInt($this$writeIntLittleEndian, $this$reverseByteOrder$iv);
    }

    public static final void writeLongLittleEndian(Output $this$writeLongLittleEndian, long value) {
        Intrinsics.checkNotNullParameter($this$writeLongLittleEndian, "<this>");
        long $this$reverseByteOrder$iv = Long.reverseBytes(value);
        OutputPrimitivesKt.writeLong($this$writeLongLittleEndian, $this$reverseByteOrder$iv);
    }

    public static final void writeFloatLittleEndian(Output $this$writeFloatLittleEndian, float value) {
        Intrinsics.checkNotNullParameter($this$writeFloatLittleEndian, "<this>");
        float it = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(value)));
        OutputPrimitivesKt.writeFloat($this$writeFloatLittleEndian, it);
    }

    public static final void writeDoubleLittleEndian(Output $this$writeDoubleLittleEndian, double value) {
        Intrinsics.checkNotNullParameter($this$writeDoubleLittleEndian, "<this>");
        double it = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(value)));
        OutputPrimitivesKt.writeDouble($this$writeDoubleLittleEndian, it);
    }

    /* renamed from: writeFullyLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ void m456writeFullyLittleEndianWt3Bwxc$default(Output output, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m850getSizeimpl(sArr) - i;
        }
        m454writeFullyLittleEndianWt3Bwxc(output, sArr, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final void m454writeFullyLittleEndianWt3Bwxc(Output writeFullyLittleEndian, short[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFullyLittleEndian, "$this$writeFullyLittleEndian");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFullyLittleEndian(writeFullyLittleEndian, source, offset, length);
    }

    public static final void writeShortLittleEndian(Buffer $this$writeShortLittleEndian, short value) {
        Intrinsics.checkNotNullParameter($this$writeShortLittleEndian, "<this>");
        short $this$reverseByteOrder$iv = Short.reverseBytes(value);
        BufferPrimitivesKt.writeShort($this$writeShortLittleEndian, $this$reverseByteOrder$iv);
    }

    public static final void writeIntLittleEndian(Buffer $this$writeIntLittleEndian, int value) {
        Intrinsics.checkNotNullParameter($this$writeIntLittleEndian, "<this>");
        int $this$reverseByteOrder$iv = Integer.reverseBytes(value);
        BufferPrimitivesKt.writeInt($this$writeIntLittleEndian, $this$reverseByteOrder$iv);
    }

    public static final void writeLongLittleEndian(Buffer $this$writeLongLittleEndian, long value) {
        Intrinsics.checkNotNullParameter($this$writeLongLittleEndian, "<this>");
        long $this$reverseByteOrder$iv = Long.reverseBytes(value);
        BufferPrimitivesKt.writeLong($this$writeLongLittleEndian, $this$reverseByteOrder$iv);
    }

    public static final void writeFloatLittleEndian(Buffer $this$writeFloatLittleEndian, float value) {
        Intrinsics.checkNotNullParameter($this$writeFloatLittleEndian, "<this>");
        float it = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(value)));
        BufferPrimitivesKt.writeFloat($this$writeFloatLittleEndian, it);
    }

    public static final void writeDoubleLittleEndian(Buffer $this$writeDoubleLittleEndian, double value) {
        Intrinsics.checkNotNullParameter($this$writeDoubleLittleEndian, "<this>");
        double it = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(value)));
        BufferPrimitivesKt.writeDouble($this$writeDoubleLittleEndian, it);
    }

    /* renamed from: writeFullyLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ void m455writeFullyLittleEndianWt3Bwxc$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m850getSizeimpl(sArr) - i;
        }
        m453writeFullyLittleEndianWt3Bwxc(buffer, sArr, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final void m453writeFullyLittleEndianWt3Bwxc(Buffer writeFullyLittleEndian, short[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFullyLittleEndian, "$this$writeFullyLittleEndian");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFullyLittleEndian(writeFullyLittleEndian, source, offset, length);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        writeFullyLittleEndian(output, sArr, i, i2);
    }

    public static final void writeFullyLittleEndian(Output $this$writeFullyLittleEndian, short[] source, int offset, int length) {
        short[] source2 = source;
        Intrinsics.checkNotNullParameter($this$writeFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(source2, "source");
        int untilIndex$iv = offset + length;
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFullyLittleEndian, 2, null);
        int start$iv = offset;
        int size$iv$iv = 0;
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min((buffer$iv.getLimit() - buffer$iv.getWritePosition()) / 2, untilIndex$iv - start$iv);
                int lastIndex$iv = (start$iv + size$iv) - 1;
                int index$iv = start$iv;
                if (index$iv <= lastIndex$iv) {
                    while (true) {
                        int it = index$iv;
                        short $this$reverseByteOrder$iv = source2[it];
                        int size$iv$iv2 = size$iv$iv;
                        BufferPrimitivesKt.writeShort(buffer$iv, Short.reverseBytes($this$reverseByteOrder$iv));
                        if (index$iv == lastIndex$iv) {
                            break;
                        }
                        index$iv++;
                        source2 = source;
                        size$iv$iv = size$iv$iv2;
                    }
                }
                start$iv += size$iv;
                size$iv$iv = start$iv < untilIndex$iv ? 2 : 0;
                if (size$iv$iv <= 0) {
                    return;
                }
                tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFullyLittleEndian, size$iv$iv, tail$iv$iv2);
                source2 = source;
            } finally {
                $this$writeFullyLittleEndian.afterHeadWrite();
            }
        }
    }

    /* renamed from: writeFullyLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ void m460writeFullyLittleEndiano2ZM2JE$default(Output output, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m666getSizeimpl(iArr) - i;
        }
        m458writeFullyLittleEndiano2ZM2JE(output, iArr, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final void m458writeFullyLittleEndiano2ZM2JE(Output writeFullyLittleEndian, int[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFullyLittleEndian, "$this$writeFullyLittleEndian");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFullyLittleEndian(writeFullyLittleEndian, source, offset, length);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        writeFullyLittleEndian(output, iArr, i, i2);
    }

    public static final void writeFullyLittleEndian(Output $this$writeFullyLittleEndian, int[] source, int offset, int length) {
        int[] source2 = source;
        Intrinsics.checkNotNullParameter($this$writeFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(source2, "source");
        int untilIndex$iv = offset + length;
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFullyLittleEndian, 4, null);
        int start$iv = offset;
        int size$iv$iv = 0;
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min((buffer$iv.getLimit() - buffer$iv.getWritePosition()) / 4, untilIndex$iv - start$iv);
                int lastIndex$iv = (start$iv + size$iv) - 1;
                int index$iv = start$iv;
                if (index$iv <= lastIndex$iv) {
                    while (true) {
                        int it = index$iv;
                        int $this$reverseByteOrder$iv = source2[it];
                        int size$iv$iv2 = size$iv$iv;
                        int size$iv$iv3 = Integer.reverseBytes($this$reverseByteOrder$iv);
                        BufferPrimitivesKt.writeInt(buffer$iv, size$iv$iv3);
                        if (index$iv == lastIndex$iv) {
                            break;
                        }
                        index$iv++;
                        source2 = source;
                        size$iv$iv = size$iv$iv2;
                    }
                }
                start$iv += size$iv;
                size$iv$iv = start$iv < untilIndex$iv ? 4 : 0;
                if (size$iv$iv <= 0) {
                    return;
                }
                tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFullyLittleEndian, size$iv$iv, tail$iv$iv2);
                source2 = source;
            } finally {
                $this$writeFullyLittleEndian.afterHeadWrite();
            }
        }
    }

    /* renamed from: writeFullyLittleEndian-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ void m464writeFullyLittleEndianpqYNikA$default(Output output, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m745getSizeimpl(jArr) - i;
        }
        m462writeFullyLittleEndianpqYNikA(output, jArr, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-pqYNikA, reason: not valid java name */
    public static final void m462writeFullyLittleEndianpqYNikA(Output writeFullyLittleEndian, long[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFullyLittleEndian, "$this$writeFullyLittleEndian");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFullyLittleEndian(writeFullyLittleEndian, source, offset, length);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        writeFullyLittleEndian(output, jArr, i, i2);
    }

    public static final void writeFullyLittleEndian(Output $this$writeFullyLittleEndian, long[] source, int offset, int length) {
        long[] source2 = source;
        Intrinsics.checkNotNullParameter($this$writeFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(source2, "source");
        int untilIndex$iv = offset + length;
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFullyLittleEndian, 8, null);
        int start$iv = offset;
        int size$iv$iv = 0;
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min((buffer$iv.getLimit() - buffer$iv.getWritePosition()) / 8, untilIndex$iv - start$iv);
                int lastIndex$iv = (start$iv + size$iv) - 1;
                int index$iv = start$iv;
                if (index$iv <= lastIndex$iv) {
                    while (true) {
                        int it = index$iv;
                        long $this$reverseByteOrder$iv = source2[it];
                        int size$iv$iv2 = size$iv$iv;
                        BufferPrimitivesKt.writeLong(buffer$iv, Long.reverseBytes($this$reverseByteOrder$iv));
                        if (index$iv == lastIndex$iv) {
                            break;
                        }
                        index$iv++;
                        source2 = source;
                        size$iv$iv = size$iv$iv2;
                    }
                }
                start$iv += size$iv;
                size$iv$iv = start$iv < untilIndex$iv ? 8 : 0;
                if (size$iv$iv <= 0) {
                    return;
                }
                tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFullyLittleEndian, size$iv$iv, tail$iv$iv2);
                source2 = source;
            } finally {
                $this$writeFullyLittleEndian.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        writeFullyLittleEndian(output, fArr, i, i2);
    }

    public static final void writeFullyLittleEndian(Output $this$writeFullyLittleEndian, float[] source, int offset, int length) {
        float[] source2 = source;
        Intrinsics.checkNotNullParameter($this$writeFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(source2, "source");
        int untilIndex$iv = offset + length;
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFullyLittleEndian, 4, null);
        int start$iv = offset;
        int size$iv$iv = 0;
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min((buffer$iv.getLimit() - buffer$iv.getWritePosition()) / 4, untilIndex$iv - start$iv);
                int lastIndex$iv = (start$iv + size$iv) - 1;
                int index$iv = start$iv;
                if (index$iv <= lastIndex$iv) {
                    while (true) {
                        int it = index$iv;
                        float $this$reverseByteOrder$iv = source2[it];
                        int size$iv$iv2 = size$iv$iv;
                        BufferPrimitivesKt.writeFloat(buffer$iv, Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$reverseByteOrder$iv))));
                        if (index$iv == lastIndex$iv) {
                            break;
                        }
                        index$iv++;
                        source2 = source;
                        size$iv$iv = size$iv$iv2;
                    }
                }
                start$iv += size$iv;
                size$iv$iv = start$iv < untilIndex$iv ? 4 : 0;
                if (size$iv$iv <= 0) {
                    return;
                }
                tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFullyLittleEndian, size$iv$iv, tail$iv$iv2);
                source2 = source;
            } finally {
                $this$writeFullyLittleEndian.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        writeFullyLittleEndian(output, dArr, i, i2);
    }

    public static final void writeFullyLittleEndian(Output $this$writeFullyLittleEndian, double[] source, int offset, int length) {
        double[] source2 = source;
        Intrinsics.checkNotNullParameter($this$writeFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(source2, "source");
        int untilIndex$iv = offset + length;
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFullyLittleEndian, 8, null);
        int start$iv = offset;
        int size$iv$iv = 0;
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min((buffer$iv.getLimit() - buffer$iv.getWritePosition()) / 8, untilIndex$iv - start$iv);
                int lastIndex$iv = (start$iv + size$iv) - 1;
                int index$iv = start$iv;
                if (index$iv <= lastIndex$iv) {
                    while (true) {
                        int it = index$iv;
                        double $this$reverseByteOrder$iv = source2[it];
                        int size$iv$iv2 = size$iv$iv;
                        BufferPrimitivesKt.writeDouble(buffer$iv, Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$reverseByteOrder$iv))));
                        if (index$iv == lastIndex$iv) {
                            break;
                        }
                        index$iv++;
                        source2 = source;
                        size$iv$iv = size$iv$iv2;
                    }
                }
                start$iv += size$iv;
                size$iv$iv = start$iv < untilIndex$iv ? 8 : 0;
                if (size$iv$iv <= 0) {
                    return;
                }
                tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFullyLittleEndian, size$iv$iv, tail$iv$iv2);
                source2 = source;
            } finally {
                $this$writeFullyLittleEndian.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        writeFullyLittleEndian(buffer, sArr, i, i2);
    }

    public static final void writeFullyLittleEndian(Buffer $this$writeFullyLittleEndian, short[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int untilIndex$iv = offset + length;
        int size$iv = Math.min(($this$writeFullyLittleEndian.getLimit() - $this$writeFullyLittleEndian.getWritePosition()) / 2, untilIndex$iv - offset);
        int lastIndex$iv = (offset + size$iv) - 1;
        int index$iv = offset;
        if (index$iv <= lastIndex$iv) {
            while (true) {
                int it = index$iv;
                short $this$reverseByteOrder$iv = source[it];
                BufferPrimitivesKt.writeShort($this$writeFullyLittleEndian, Short.reverseBytes($this$reverseByteOrder$iv));
                if (index$iv == lastIndex$iv) {
                    break;
                } else {
                    index$iv++;
                }
            }
        }
        int i = offset + size$iv;
    }

    /* renamed from: writeFullyLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ void m459writeFullyLittleEndiano2ZM2JE$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m666getSizeimpl(iArr) - i;
        }
        m457writeFullyLittleEndiano2ZM2JE(buffer, iArr, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final void m457writeFullyLittleEndiano2ZM2JE(Buffer writeFullyLittleEndian, int[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFullyLittleEndian, "$this$writeFullyLittleEndian");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFullyLittleEndian(writeFullyLittleEndian, source, offset, length);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        writeFullyLittleEndian(buffer, iArr, i, i2);
    }

    public static final void writeFullyLittleEndian(Buffer $this$writeFullyLittleEndian, int[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int untilIndex$iv = offset + length;
        int size$iv = Math.min(($this$writeFullyLittleEndian.getLimit() - $this$writeFullyLittleEndian.getWritePosition()) / 4, untilIndex$iv - offset);
        int lastIndex$iv = (offset + size$iv) - 1;
        int index$iv = offset;
        if (index$iv <= lastIndex$iv) {
            while (true) {
                int it = index$iv;
                int $this$reverseByteOrder$iv = source[it];
                BufferPrimitivesKt.writeInt($this$writeFullyLittleEndian, Integer.reverseBytes($this$reverseByteOrder$iv));
                if (index$iv == lastIndex$iv) {
                    break;
                } else {
                    index$iv++;
                }
            }
        }
        int i = offset + size$iv;
    }

    /* renamed from: writeFullyLittleEndian-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ void m463writeFullyLittleEndianpqYNikA$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m745getSizeimpl(jArr) - i;
        }
        m461writeFullyLittleEndianpqYNikA(buffer, jArr, i, i2);
    }

    /* renamed from: writeFullyLittleEndian-pqYNikA, reason: not valid java name */
    public static final void m461writeFullyLittleEndianpqYNikA(Buffer writeFullyLittleEndian, long[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFullyLittleEndian, "$this$writeFullyLittleEndian");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFullyLittleEndian(writeFullyLittleEndian, source, offset, length);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        writeFullyLittleEndian(buffer, jArr, i, i2);
    }

    public static final void writeFullyLittleEndian(Buffer $this$writeFullyLittleEndian, long[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int untilIndex$iv = offset + length;
        int size$iv = Math.min(($this$writeFullyLittleEndian.getLimit() - $this$writeFullyLittleEndian.getWritePosition()) / 8, untilIndex$iv - offset);
        int lastIndex$iv = (offset + size$iv) - 1;
        int index$iv = offset;
        if (index$iv <= lastIndex$iv) {
            while (true) {
                int it = index$iv;
                long $this$reverseByteOrder$iv = source[it];
                BufferPrimitivesKt.writeLong($this$writeFullyLittleEndian, Long.reverseBytes($this$reverseByteOrder$iv));
                if (index$iv == lastIndex$iv) {
                    break;
                } else {
                    index$iv++;
                }
            }
        }
        int i = offset + size$iv;
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Buffer buffer, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        writeFullyLittleEndian(buffer, fArr, i, i2);
    }

    public static final void writeFullyLittleEndian(Buffer $this$writeFullyLittleEndian, float[] source, int offset, int length) {
        float[] source2 = source;
        Intrinsics.checkNotNullParameter($this$writeFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(source2, "source");
        int untilIndex$iv = offset + length;
        int size$iv = Math.min(($this$writeFullyLittleEndian.getLimit() - $this$writeFullyLittleEndian.getWritePosition()) / 4, untilIndex$iv - offset);
        int lastIndex$iv = (offset + size$iv) - 1;
        int index$iv = offset;
        if (index$iv <= lastIndex$iv) {
            while (true) {
                int it = index$iv;
                float $this$reverseByteOrder$iv = source2[it];
                BufferPrimitivesKt.writeFloat($this$writeFullyLittleEndian, Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$reverseByteOrder$iv))));
                if (index$iv == lastIndex$iv) {
                    break;
                }
                index$iv++;
                source2 = source;
            }
        }
        int i = offset + size$iv;
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Buffer buffer, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        writeFullyLittleEndian(buffer, dArr, i, i2);
    }

    public static final void writeFullyLittleEndian(Buffer $this$writeFullyLittleEndian, double[] source, int offset, int length) {
        double[] source2 = source;
        Intrinsics.checkNotNullParameter($this$writeFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(source2, "source");
        Buffer $this$writeArrayTemplate$iv = $this$writeFullyLittleEndian;
        int untilIndex$iv = offset + length;
        int size$iv = Math.min(($this$writeArrayTemplate$iv.getLimit() - $this$writeArrayTemplate$iv.getWritePosition()) / 8, untilIndex$iv - offset);
        int lastIndex$iv = (offset + size$iv) - 1;
        int index$iv = offset;
        if (index$iv <= lastIndex$iv) {
            while (true) {
                int it = index$iv;
                double $this$reverseByteOrder$iv = source2[it];
                Buffer $this$writeArrayTemplate$iv2 = $this$writeArrayTemplate$iv;
                BufferPrimitivesKt.writeDouble($this$writeArrayTemplate$iv, Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$reverseByteOrder$iv))));
                if (index$iv == lastIndex$iv) {
                    break;
                }
                index$iv++;
                source2 = source;
                $this$writeArrayTemplate$iv = $this$writeArrayTemplate$iv2;
            }
        }
        int i = offset + size$iv;
    }

    private static final <T> void writePrimitiveTemplate(T t, Function1<? super T, Unit> function1, Function1<? super T, ? extends T> function12) {
        function1.invoke(function12.invoke(t));
    }

    private static final <T> void writePrimitiveTemplate(T t, ByteOrder byteOrder, Function1<? super T, Unit> function1, Function1<? super T, ? extends T> function12) {
        function1.invoke(WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? (Object) t : function12.invoke(t));
    }

    private static final void writeArrayTemplate(Output $this$writeArrayTemplate, int offset, int length, int componentSize, Function2<? super Buffer, ? super Integer, Unit> function2) {
        int untilIndex = offset + length;
        ChunkBuffer tail$iv = UnsafeKt.prepareWriteHead($this$writeArrayTemplate, componentSize, null);
        int start = offset;
        ChunkBuffer tail$iv2 = tail$iv;
        while (true) {
            try {
                Buffer buffer = tail$iv2;
                int size = Math.min((buffer.getLimit() - buffer.getWritePosition()) / componentSize, untilIndex - start);
                int lastIndex = (start + size) - 1;
                int index = start;
                if (index <= lastIndex) {
                    while (true) {
                        try {
                            function2.invoke(buffer, Integer.valueOf(index));
                            if (index == lastIndex) {
                                break;
                            } else {
                                index++;
                            }
                        } catch (Throwable th) {
                            th = th;
                            $this$writeArrayTemplate.afterHeadWrite();
                            throw th;
                        }
                    }
                }
                start += size;
                int size$iv = start < untilIndex ? componentSize : 0;
                if (size$iv > 0) {
                    tail$iv2 = UnsafeKt.prepareWriteHead($this$writeArrayTemplate, size$iv, tail$iv2);
                } else {
                    $this$writeArrayTemplate.afterHeadWrite();
                    return;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private static final void writeArrayTemplate(Buffer $this$writeArrayTemplate, int offset, int length, int componentSize, Function2<? super Buffer, ? super Integer, Unit> function2) {
        int untilIndex = offset + length;
        int size = Math.min(($this$writeArrayTemplate.getLimit() - $this$writeArrayTemplate.getWritePosition()) / componentSize, untilIndex - offset);
        int lastIndex = (offset + size) - 1;
        int index = offset;
        if (index <= lastIndex) {
            while (true) {
                function2.invoke($this$writeArrayTemplate, Integer.valueOf(index));
                if (index == lastIndex) {
                    break;
                } else {
                    index++;
                }
            }
        }
        int i = offset + size;
    }
}
