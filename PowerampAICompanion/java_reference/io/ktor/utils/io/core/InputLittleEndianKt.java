package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InputLittleEndian.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\u001a?\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00010\u0006¢\u0006\u0002\b\u0007H\u0082\b¢\u0006\u0002\u0010\b\u001aG\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00010\u0006¢\u0006\u0002\b\u0007H\u0082\b¢\u0006\u0002\u0010\u000b\u001a&\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00132\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00142\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00152\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00162\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a3\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00172\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a3\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u001a2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001a3\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u001d2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a&\u0010\f\u001a\u00020\r*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010\f\u001a\u00020\r*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00132\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010\f\u001a\u00020\r*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00142\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010\f\u001a\u00020\r*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00152\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010\f\u001a\u00020\r*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00162\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a3\u0010\f\u001a\u00020\r*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00172\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010!\u001a3\u0010\f\u001a\u00020\r*\u00020 2\u0006\u0010\u000f\u001a\u00020\u001a2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\"\u001a3\u0010\f\u001a\u00020\r*\u00020 2\u0006\u0010\u000f\u001a\u00020\u001d2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010#\u001a\u0012\u0010$\u001a\u00020%*\u00020 2\u0006\u0010\t\u001a\u00020\n\u001a\n\u0010&\u001a\u00020%*\u00020\u000e\u001a\n\u0010&\u001a\u00020%*\u00020 \u001a\u0012\u0010'\u001a\u00020(*\u00020 2\u0006\u0010\t\u001a\u00020\n\u001a\n\u0010)\u001a\u00020(*\u00020\u000e\u001a\n\u0010)\u001a\u00020(*\u00020 \u001a&\u0010*\u001a\u00020+*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010*\u001a\u00020+*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00132\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010*\u001a\u00020+*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00142\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010*\u001a\u00020+*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00152\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010*\u001a\u00020+*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00162\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a3\u0010*\u001a\u00020+*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00172\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-\u001a3\u0010*\u001a\u00020+*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u001a2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/\u001a3\u0010*\u001a\u00020+*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u001d2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101\u001a&\u0010*\u001a\u00020+*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010*\u001a\u00020+*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00132\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010*\u001a\u00020+*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00142\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010*\u001a\u00020+*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00152\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a&\u0010*\u001a\u00020+*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00162\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r\u001a3\u0010*\u001a\u00020+*\u00020 2\u0006\u0010\u000f\u001a\u00020\u00172\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u00102\u001a3\u0010*\u001a\u00020+*\u00020 2\u0006\u0010\u000f\u001a\u00020\u001a2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u00103\u001a3\u0010*\u001a\u00020+*\u00020 2\u0006\u0010\u000f\u001a\u00020\u001d2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00104\u001a\u0012\u00105\u001a\u00020\r*\u00020 2\u0006\u0010\t\u001a\u00020\n\u001a\n\u00106\u001a\u00020\r*\u00020\u000e\u001a\n\u00106\u001a\u00020\r*\u00020 \u001a\u0012\u00107\u001a\u000208*\u00020 2\u0006\u0010\t\u001a\u00020\n\u001a\n\u00109\u001a\u000208*\u00020\u000e\u001a\n\u00109\u001a\u000208*\u00020 \u001a\u0012\u0010:\u001a\u00020;*\u00020 2\u0006\u0010\t\u001a\u00020\n\u001a\n\u0010<\u001a\u00020;*\u00020\u000e\u001a\n\u0010<\u001a\u00020;*\u00020 \u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006="}, d2 = {"readPrimitiveTemplate", ExifInterface.GPS_DIRECTION_TRUE, "", "read", "Lkotlin/Function0;", "reverse", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "byteOrder", "Lio/ktor/utils/io/core/ByteOrder;", "(Lio/ktor/utils/io/core/ByteOrder;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "readAvailableLittleEndian", "", "Lio/ktor/utils/io/core/Buffer;", "dst", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "", "", "", "", "Lkotlin/UIntArray;", "readAvailableLittleEndian-o2ZM2JE", "(Lio/ktor/utils/io/core/Buffer;[III)I", "Lkotlin/ULongArray;", "readAvailableLittleEndian-pqYNikA", "(Lio/ktor/utils/io/core/Buffer;[JII)I", "Lkotlin/UShortArray;", "readAvailableLittleEndian-Wt3Bwxc", "(Lio/ktor/utils/io/core/Buffer;[SII)I", "Lio/ktor/utils/io/core/Input;", "(Lio/ktor/utils/io/core/Input;[III)I", "(Lio/ktor/utils/io/core/Input;[JII)I", "(Lio/ktor/utils/io/core/Input;[SII)I", "readDouble", "", "readDoubleLittleEndian", "readFloat", "", "readFloatLittleEndian", "readFullyLittleEndian", "", "readFullyLittleEndian-o2ZM2JE", "(Lio/ktor/utils/io/core/Buffer;[III)V", "readFullyLittleEndian-pqYNikA", "(Lio/ktor/utils/io/core/Buffer;[JII)V", "readFullyLittleEndian-Wt3Bwxc", "(Lio/ktor/utils/io/core/Buffer;[SII)V", "(Lio/ktor/utils/io/core/Input;[III)V", "(Lio/ktor/utils/io/core/Input;[JII)V", "(Lio/ktor/utils/io/core/Input;[SII)V", "readInt", "readIntLittleEndian", "readLong", "", "readLongLittleEndian", "readShort", "", "readShortLittleEndian", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class InputLittleEndianKt {

    /* compiled from: InputLittleEndian.kt */
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

    public static final short readShort(Input $this$readShort, ByteOrder byteOrder) {
        Intrinsics.checkNotNullParameter($this$readShort, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            return InputPrimitivesKt.readShort($this$readShort);
        }
        short $this$readShort_u24lambda_u241 = InputPrimitivesKt.readShort($this$readShort);
        short $this$reverseByteOrder$iv = Short.reverseBytes($this$readShort_u24lambda_u241);
        return $this$reverseByteOrder$iv;
    }

    public static final int readInt(Input $this$readInt, ByteOrder byteOrder) {
        Intrinsics.checkNotNullParameter($this$readInt, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            return InputPrimitivesKt.readInt($this$readInt);
        }
        int $this$reverseByteOrder$iv = Integer.reverseBytes(InputPrimitivesKt.readInt($this$readInt));
        return $this$reverseByteOrder$iv;
    }

    public static final long readLong(Input $this$readLong, ByteOrder byteOrder) {
        Intrinsics.checkNotNullParameter($this$readLong, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            return InputPrimitivesKt.readLong($this$readLong);
        }
        long $this$readLong_u24lambda_u245 = InputPrimitivesKt.readLong($this$readLong);
        long $this$reverseByteOrder$iv = Long.reverseBytes($this$readLong_u24lambda_u245);
        return $this$reverseByteOrder$iv;
    }

    public static final float readFloat(Input $this$readFloat, ByteOrder byteOrder) {
        Intrinsics.checkNotNullParameter($this$readFloat, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            return InputPrimitivesKt.readFloat($this$readFloat);
        }
        float $this$readFloat_u24lambda_u247 = InputPrimitivesKt.readFloat($this$readFloat);
        return Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$readFloat_u24lambda_u247)));
    }

    public static final double readDouble(Input $this$readDouble, ByteOrder byteOrder) {
        Intrinsics.checkNotNullParameter($this$readDouble, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            return InputPrimitivesKt.readDouble($this$readDouble);
        }
        double $this$readDouble_u24lambda_u249 = InputPrimitivesKt.readDouble($this$readDouble);
        return Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$readDouble_u24lambda_u249)));
    }

    public static final short readShortLittleEndian(Input $this$readShortLittleEndian) {
        Intrinsics.checkNotNullParameter($this$readShortLittleEndian, "<this>");
        short $this$readShortLittleEndian_u24lambda_u2411 = InputPrimitivesKt.readShort($this$readShortLittleEndian);
        short $this$reverseByteOrder$iv = Short.reverseBytes($this$readShortLittleEndian_u24lambda_u2411);
        return $this$reverseByteOrder$iv;
    }

    public static final int readIntLittleEndian(Input $this$readIntLittleEndian) {
        Intrinsics.checkNotNullParameter($this$readIntLittleEndian, "<this>");
        int $this$reverseByteOrder$iv = Integer.reverseBytes(InputPrimitivesKt.readInt($this$readIntLittleEndian));
        return $this$reverseByteOrder$iv;
    }

    public static final long readLongLittleEndian(Input $this$readLongLittleEndian) {
        Intrinsics.checkNotNullParameter($this$readLongLittleEndian, "<this>");
        long $this$readLongLittleEndian_u24lambda_u2415 = InputPrimitivesKt.readLong($this$readLongLittleEndian);
        long $this$reverseByteOrder$iv = Long.reverseBytes($this$readLongLittleEndian_u24lambda_u2415);
        return $this$reverseByteOrder$iv;
    }

    public static final float readFloatLittleEndian(Input $this$readFloatLittleEndian) {
        Intrinsics.checkNotNullParameter($this$readFloatLittleEndian, "<this>");
        float $this$readFloatLittleEndian_u24lambda_u2417 = InputPrimitivesKt.readFloat($this$readFloatLittleEndian);
        return Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$readFloatLittleEndian_u24lambda_u2417)));
    }

    public static final double readDoubleLittleEndian(Input $this$readDoubleLittleEndian) {
        Intrinsics.checkNotNullParameter($this$readDoubleLittleEndian, "<this>");
        double $this$readDoubleLittleEndian_u24lambda_u2419 = InputPrimitivesKt.readDouble($this$readDoubleLittleEndian);
        return Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$readDoubleLittleEndian_u24lambda_u2419)));
    }

    public static final short readShortLittleEndian(Buffer $this$readShortLittleEndian) {
        Intrinsics.checkNotNullParameter($this$readShortLittleEndian, "<this>");
        short $this$readShortLittleEndian_u24lambda_u2421 = BufferPrimitivesKt.readShort($this$readShortLittleEndian);
        short $this$reverseByteOrder$iv = Short.reverseBytes($this$readShortLittleEndian_u24lambda_u2421);
        return $this$reverseByteOrder$iv;
    }

    public static final int readIntLittleEndian(Buffer $this$readIntLittleEndian) {
        Intrinsics.checkNotNullParameter($this$readIntLittleEndian, "<this>");
        int $this$reverseByteOrder$iv = Integer.reverseBytes(BufferPrimitivesKt.readInt($this$readIntLittleEndian));
        return $this$reverseByteOrder$iv;
    }

    public static final long readLongLittleEndian(Buffer $this$readLongLittleEndian) {
        Intrinsics.checkNotNullParameter($this$readLongLittleEndian, "<this>");
        long $this$readLongLittleEndian_u24lambda_u2425 = BufferPrimitivesKt.readLong($this$readLongLittleEndian);
        long $this$reverseByteOrder$iv = Long.reverseBytes($this$readLongLittleEndian_u24lambda_u2425);
        return $this$reverseByteOrder$iv;
    }

    public static final float readFloatLittleEndian(Buffer $this$readFloatLittleEndian) {
        Intrinsics.checkNotNullParameter($this$readFloatLittleEndian, "<this>");
        float $this$readFloatLittleEndian_u24lambda_u2427 = BufferPrimitivesKt.readFloat($this$readFloatLittleEndian);
        return Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$readFloatLittleEndian_u24lambda_u2427)));
    }

    public static final double readDoubleLittleEndian(Buffer $this$readDoubleLittleEndian) {
        Intrinsics.checkNotNullParameter($this$readDoubleLittleEndian, "<this>");
        double $this$readDoubleLittleEndian_u24lambda_u2429 = BufferPrimitivesKt.readDouble($this$readDoubleLittleEndian);
        return Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$readDoubleLittleEndian_u24lambda_u2429)));
    }

    /* renamed from: readFullyLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ void m440readFullyLittleEndianWt3Bwxc$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m850getSizeimpl(sArr) - i;
        }
        m438readFullyLittleEndianWt3Bwxc(input, sArr, i, i2);
    }

    /* renamed from: readFullyLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final void m438readFullyLittleEndianWt3Bwxc(Input readFullyLittleEndian, short[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFullyLittleEndian, "$this$readFullyLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        readFullyLittleEndian(readFullyLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        readFullyLittleEndian(input, sArr, i, i2);
    }

    public static final void readFullyLittleEndian(Input $this$readFullyLittleEndian, short[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully($this$readFullyLittleEndian, dst, offset, length);
        int lastIndex = (offset + length) - 1;
        int index = offset;
        if (index > lastIndex) {
            return;
        }
        while (true) {
            short $this$reverseByteOrder$iv = dst[index];
            dst[index] = Short.reverseBytes($this$reverseByteOrder$iv);
            if (index == lastIndex) {
                return;
            } else {
                index++;
            }
        }
    }

    /* renamed from: readFullyLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ void m444readFullyLittleEndiano2ZM2JE$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m666getSizeimpl(iArr) - i;
        }
        m442readFullyLittleEndiano2ZM2JE(input, iArr, i, i2);
    }

    /* renamed from: readFullyLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final void m442readFullyLittleEndiano2ZM2JE(Input readFullyLittleEndian, int[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFullyLittleEndian, "$this$readFullyLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        readFullyLittleEndian(readFullyLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        readFullyLittleEndian(input, iArr, i, i2);
    }

    public static final void readFullyLittleEndian(Input $this$readFullyLittleEndian, int[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully($this$readFullyLittleEndian, dst, offset, length);
        int lastIndex = (offset + length) - 1;
        int index = offset;
        if (index > lastIndex) {
            return;
        }
        while (true) {
            int $this$reverseByteOrder$iv = dst[index];
            dst[index] = Integer.reverseBytes($this$reverseByteOrder$iv);
            if (index == lastIndex) {
                return;
            } else {
                index++;
            }
        }
    }

    /* renamed from: readFullyLittleEndian-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ void m448readFullyLittleEndianpqYNikA$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m745getSizeimpl(jArr) - i;
        }
        m446readFullyLittleEndianpqYNikA(input, jArr, i, i2);
    }

    /* renamed from: readFullyLittleEndian-pqYNikA, reason: not valid java name */
    public static final void m446readFullyLittleEndianpqYNikA(Input readFullyLittleEndian, long[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFullyLittleEndian, "$this$readFullyLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        readFullyLittleEndian(readFullyLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        readFullyLittleEndian(input, jArr, i, i2);
    }

    public static final void readFullyLittleEndian(Input $this$readFullyLittleEndian, long[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully($this$readFullyLittleEndian, dst, offset, length);
        int lastIndex = (offset + length) - 1;
        int index = offset;
        if (index > lastIndex) {
            return;
        }
        while (true) {
            long $this$reverseByteOrder$iv = dst[index];
            dst[index] = Long.reverseBytes($this$reverseByteOrder$iv);
            if (index == lastIndex) {
                return;
            } else {
                index++;
            }
        }
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        readFullyLittleEndian(input, fArr, i, i2);
    }

    public static final void readFullyLittleEndian(Input $this$readFullyLittleEndian, float[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully($this$readFullyLittleEndian, dst, offset, length);
        int lastIndex = (offset + length) - 1;
        int index = offset;
        if (index > lastIndex) {
            return;
        }
        while (true) {
            float $this$reverseByteOrder$iv = dst[index];
            dst[index] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$reverseByteOrder$iv)));
            if (index == lastIndex) {
                return;
            } else {
                index++;
            }
        }
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        readFullyLittleEndian(input, dArr, i, i2);
    }

    public static final void readFullyLittleEndian(Input $this$readFullyLittleEndian, double[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully($this$readFullyLittleEndian, dst, offset, length);
        int lastIndex = (offset + length) - 1;
        int index = offset;
        if (index > lastIndex) {
            return;
        }
        while (true) {
            double $this$reverseByteOrder$iv = dst[index];
            dst[index] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$reverseByteOrder$iv)));
            if (index == lastIndex) {
                return;
            } else {
                index++;
            }
        }
    }

    /* renamed from: readAvailableLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ int m428readAvailableLittleEndianWt3Bwxc$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m850getSizeimpl(sArr) - i;
        }
        return m426readAvailableLittleEndianWt3Bwxc(input, sArr, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final int m426readAvailableLittleEndianWt3Bwxc(Input readAvailableLittleEndian, short[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readAvailableLittleEndian, "$this$readAvailableLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        return readAvailableLittleEndian(readAvailableLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        return readAvailableLittleEndian(input, sArr, i, i2);
    }

    public static final int readAvailableLittleEndian(Input $this$readAvailableLittleEndian, short[] dst, int offset, int length) {
        int lastIndex;
        int index;
        Intrinsics.checkNotNullParameter($this$readAvailableLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int result = InputArraysKt.readAvailable($this$readAvailableLittleEndian, dst, offset, length);
        if (result > 0 && (index = offset) <= (offset + result) - 1) {
            while (true) {
                short $this$reverseByteOrder$iv = dst[index];
                dst[index] = Short.reverseBytes($this$reverseByteOrder$iv);
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    /* renamed from: readAvailableLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ int m432readAvailableLittleEndiano2ZM2JE$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m666getSizeimpl(iArr) - i;
        }
        return m430readAvailableLittleEndiano2ZM2JE(input, iArr, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final int m430readAvailableLittleEndiano2ZM2JE(Input readAvailableLittleEndian, int[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readAvailableLittleEndian, "$this$readAvailableLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        return readAvailableLittleEndian(readAvailableLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        return readAvailableLittleEndian(input, iArr, i, i2);
    }

    public static final int readAvailableLittleEndian(Input $this$readAvailableLittleEndian, int[] dst, int offset, int length) {
        int lastIndex;
        int index;
        Intrinsics.checkNotNullParameter($this$readAvailableLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int result = InputArraysKt.readAvailable($this$readAvailableLittleEndian, dst, offset, length);
        if (result > 0 && (index = offset) <= (offset + result) - 1) {
            while (true) {
                int $this$reverseByteOrder$iv = dst[index];
                dst[index] = Integer.reverseBytes($this$reverseByteOrder$iv);
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    /* renamed from: readAvailableLittleEndian-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ int m436readAvailableLittleEndianpqYNikA$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m745getSizeimpl(jArr) - i;
        }
        return m434readAvailableLittleEndianpqYNikA(input, jArr, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-pqYNikA, reason: not valid java name */
    public static final int m434readAvailableLittleEndianpqYNikA(Input readAvailableLittleEndian, long[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readAvailableLittleEndian, "$this$readAvailableLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        return readAvailableLittleEndian(readAvailableLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        return readAvailableLittleEndian(input, jArr, i, i2);
    }

    public static final int readAvailableLittleEndian(Input $this$readAvailableLittleEndian, long[] dst, int offset, int length) {
        int lastIndex;
        int index;
        Intrinsics.checkNotNullParameter($this$readAvailableLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int result = InputArraysKt.readAvailable($this$readAvailableLittleEndian, dst, offset, length);
        if (result > 0 && (index = offset) <= (offset + result) - 1) {
            while (true) {
                long $this$reverseByteOrder$iv = dst[index];
                dst[index] = Long.reverseBytes($this$reverseByteOrder$iv);
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        return readAvailableLittleEndian(input, fArr, i, i2);
    }

    public static final int readAvailableLittleEndian(Input $this$readAvailableLittleEndian, float[] dst, int offset, int length) {
        int lastIndex;
        int index;
        Intrinsics.checkNotNullParameter($this$readAvailableLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int result = InputArraysKt.readAvailable($this$readAvailableLittleEndian, dst, offset, length);
        if (result > 0 && (index = offset) <= (offset + result) - 1) {
            while (true) {
                float $this$reverseByteOrder$iv = dst[index];
                dst[index] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$reverseByteOrder$iv)));
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        return readAvailableLittleEndian(input, dArr, i, i2);
    }

    public static final int readAvailableLittleEndian(Input $this$readAvailableLittleEndian, double[] dst, int offset, int length) {
        int lastIndex;
        int index;
        Intrinsics.checkNotNullParameter($this$readAvailableLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int result = InputArraysKt.readAvailable($this$readAvailableLittleEndian, dst, offset, length);
        if (result > 0 && (index = offset) <= (offset + result) - 1) {
            while (true) {
                double $this$reverseByteOrder$iv = dst[index];
                dst[index] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$reverseByteOrder$iv)));
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    /* renamed from: readFullyLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ void m439readFullyLittleEndianWt3Bwxc$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m850getSizeimpl(sArr) - i;
        }
        m437readFullyLittleEndianWt3Bwxc(buffer, sArr, i, i2);
    }

    /* renamed from: readFullyLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final void m437readFullyLittleEndianWt3Bwxc(Buffer readFullyLittleEndian, short[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFullyLittleEndian, "$this$readFullyLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        readFullyLittleEndian(readFullyLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        readFullyLittleEndian(buffer, sArr, i, i2);
    }

    public static final void readFullyLittleEndian(Buffer $this$readFullyLittleEndian, short[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        BufferPrimitivesKt.readFully($this$readFullyLittleEndian, dst, offset, length);
        int lastIndex = (offset + length) - 1;
        int index = offset;
        if (index > lastIndex) {
            return;
        }
        while (true) {
            short $this$reverseByteOrder$iv = dst[index];
            dst[index] = Short.reverseBytes($this$reverseByteOrder$iv);
            if (index == lastIndex) {
                return;
            } else {
                index++;
            }
        }
    }

    /* renamed from: readFullyLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ void m443readFullyLittleEndiano2ZM2JE$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m666getSizeimpl(iArr) - i;
        }
        m441readFullyLittleEndiano2ZM2JE(buffer, iArr, i, i2);
    }

    /* renamed from: readFullyLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final void m441readFullyLittleEndiano2ZM2JE(Buffer readFullyLittleEndian, int[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFullyLittleEndian, "$this$readFullyLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        readFullyLittleEndian(readFullyLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        readFullyLittleEndian(buffer, iArr, i, i2);
    }

    public static final void readFullyLittleEndian(Buffer $this$readFullyLittleEndian, int[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        BufferPrimitivesKt.readFully($this$readFullyLittleEndian, dst, offset, length);
        int lastIndex = (offset + length) - 1;
        int index = offset;
        if (index > lastIndex) {
            return;
        }
        while (true) {
            int $this$reverseByteOrder$iv = dst[index];
            dst[index] = Integer.reverseBytes($this$reverseByteOrder$iv);
            if (index == lastIndex) {
                return;
            } else {
                index++;
            }
        }
    }

    /* renamed from: readFullyLittleEndian-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ void m447readFullyLittleEndianpqYNikA$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m745getSizeimpl(jArr) - i;
        }
        m445readFullyLittleEndianpqYNikA(buffer, jArr, i, i2);
    }

    /* renamed from: readFullyLittleEndian-pqYNikA, reason: not valid java name */
    public static final void m445readFullyLittleEndianpqYNikA(Buffer readFullyLittleEndian, long[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFullyLittleEndian, "$this$readFullyLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        readFullyLittleEndian(readFullyLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        readFullyLittleEndian(buffer, jArr, i, i2);
    }

    public static final void readFullyLittleEndian(Buffer $this$readFullyLittleEndian, long[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        BufferPrimitivesKt.readFully($this$readFullyLittleEndian, dst, offset, length);
        int lastIndex = (offset + length) - 1;
        int index = offset;
        if (index > lastIndex) {
            return;
        }
        while (true) {
            long $this$reverseByteOrder$iv = dst[index];
            dst[index] = Long.reverseBytes($this$reverseByteOrder$iv);
            if (index == lastIndex) {
                return;
            } else {
                index++;
            }
        }
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Buffer buffer, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        readFullyLittleEndian(buffer, fArr, i, i2);
    }

    public static final void readFullyLittleEndian(Buffer $this$readFullyLittleEndian, float[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        BufferPrimitivesKt.readFully($this$readFullyLittleEndian, dst, offset, length);
        int lastIndex = (offset + length) - 1;
        int index = offset;
        if (index > lastIndex) {
            return;
        }
        while (true) {
            float $this$reverseByteOrder$iv = dst[index];
            dst[index] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$reverseByteOrder$iv)));
            if (index == lastIndex) {
                return;
            } else {
                index++;
            }
        }
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Buffer buffer, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        readFullyLittleEndian(buffer, dArr, i, i2);
    }

    public static final void readFullyLittleEndian(Buffer $this$readFullyLittleEndian, double[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFullyLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        BufferPrimitivesKt.readFully($this$readFullyLittleEndian, dst, offset, length);
        int lastIndex = (offset + length) - 1;
        int index = offset;
        if (index > lastIndex) {
            return;
        }
        while (true) {
            double $this$reverseByteOrder$iv = dst[index];
            dst[index] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$reverseByteOrder$iv)));
            if (index == lastIndex) {
                return;
            } else {
                index++;
            }
        }
    }

    /* renamed from: readAvailableLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ int m427readAvailableLittleEndianWt3Bwxc$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m850getSizeimpl(sArr) - i;
        }
        return m425readAvailableLittleEndianWt3Bwxc(buffer, sArr, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final int m425readAvailableLittleEndianWt3Bwxc(Buffer readAvailableLittleEndian, short[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readAvailableLittleEndian, "$this$readAvailableLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        return readAvailableLittleEndian(readAvailableLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        return readAvailableLittleEndian(buffer, sArr, i, i2);
    }

    public static final int readAvailableLittleEndian(Buffer $this$readAvailableLittleEndian, short[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailableLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int result = BufferPrimitivesKt.readAvailable($this$readAvailableLittleEndian, dst, offset, length);
        int lastIndex = (offset + result) - 1;
        int index = offset;
        if (index <= lastIndex) {
            while (true) {
                short $this$reverseByteOrder$iv = dst[index];
                dst[index] = Short.reverseBytes($this$reverseByteOrder$iv);
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    /* renamed from: readAvailableLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ int m431readAvailableLittleEndiano2ZM2JE$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m666getSizeimpl(iArr) - i;
        }
        return m429readAvailableLittleEndiano2ZM2JE(buffer, iArr, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final int m429readAvailableLittleEndiano2ZM2JE(Buffer readAvailableLittleEndian, int[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readAvailableLittleEndian, "$this$readAvailableLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        return readAvailableLittleEndian(readAvailableLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        return readAvailableLittleEndian(buffer, iArr, i, i2);
    }

    public static final int readAvailableLittleEndian(Buffer $this$readAvailableLittleEndian, int[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailableLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int result = BufferPrimitivesKt.readAvailable($this$readAvailableLittleEndian, dst, offset, length);
        int lastIndex = (offset + result) - 1;
        int index = offset;
        if (index <= lastIndex) {
            while (true) {
                int $this$reverseByteOrder$iv = dst[index];
                dst[index] = Integer.reverseBytes($this$reverseByteOrder$iv);
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    /* renamed from: readAvailableLittleEndian-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ int m435readAvailableLittleEndianpqYNikA$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m745getSizeimpl(jArr) - i;
        }
        return m433readAvailableLittleEndianpqYNikA(buffer, jArr, i, i2);
    }

    /* renamed from: readAvailableLittleEndian-pqYNikA, reason: not valid java name */
    public static final int m433readAvailableLittleEndianpqYNikA(Buffer readAvailableLittleEndian, long[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readAvailableLittleEndian, "$this$readAvailableLittleEndian");
        Intrinsics.checkNotNullParameter(dst, "dst");
        return readAvailableLittleEndian(readAvailableLittleEndian, dst, offset, length);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        return readAvailableLittleEndian(buffer, jArr, i, i2);
    }

    public static final int readAvailableLittleEndian(Buffer $this$readAvailableLittleEndian, long[] dst, int offset, int length) {
        int lastIndex;
        int index;
        Intrinsics.checkNotNullParameter($this$readAvailableLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int result = BufferPrimitivesKt.readAvailable($this$readAvailableLittleEndian, dst, offset, length);
        if (result > 0 && (index = offset) <= (offset + result) - 1) {
            while (true) {
                long $this$reverseByteOrder$iv = dst[index];
                dst[index] = Long.reverseBytes($this$reverseByteOrder$iv);
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Buffer buffer, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        return readAvailableLittleEndian(buffer, fArr, i, i2);
    }

    public static final int readAvailableLittleEndian(Buffer $this$readAvailableLittleEndian, float[] dst, int offset, int length) {
        int lastIndex;
        int index;
        Intrinsics.checkNotNullParameter($this$readAvailableLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int result = BufferPrimitivesKt.readAvailable($this$readAvailableLittleEndian, dst, offset, length);
        if (result > 0 && (index = offset) <= (offset + result) - 1) {
            while (true) {
                float $this$reverseByteOrder$iv = dst[index];
                dst[index] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$reverseByteOrder$iv)));
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Buffer buffer, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        return readAvailableLittleEndian(buffer, dArr, i, i2);
    }

    public static final int readAvailableLittleEndian(Buffer $this$readAvailableLittleEndian, double[] dst, int offset, int length) {
        int lastIndex;
        int index;
        Intrinsics.checkNotNullParameter($this$readAvailableLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int result = BufferPrimitivesKt.readAvailable($this$readAvailableLittleEndian, dst, offset, length);
        if (result > 0 && (index = offset) <= (offset + result) - 1) {
            while (true) {
                double $this$reverseByteOrder$iv = dst[index];
                dst[index] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$reverseByteOrder$iv)));
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return result;
    }

    private static final <T> T readPrimitiveTemplate(Function0<? extends T> function0, Function1<? super T, ? extends T> function1) {
        return function1.invoke(function0.invoke());
    }

    private static final <T> T readPrimitiveTemplate(ByteOrder byteOrder, Function0<? extends T> function0, Function1<? super T, ? extends T> function1) {
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? function0.invoke() : function1.invoke(function0.invoke());
    }
}
