package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UnsignedTypes.kt */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\n2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00102\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0015\u0010\u0013\u001a\u00020\u0014*\u00020\u0002H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010\u0015\u001a\u0015\u0010\u0016\u001a\u00020\u0017*\u00020\u0002H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010\u0018\u001a\u0015\u0010\u0019\u001a\u00020\u001a*\u00020\u0002H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010\u001b\u001a\u0015\u0010\u001c\u001a\u00020\u001d*\u00020\u0002H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010\u001e\u001a6\u0010\u001f\u001a\u00020\u0001*\u00020 2\u0006\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#\u001a6\u0010\u001f\u001a\u00020\u0001*\u00020 2\u0006\u0010!\u001a\u00020\n2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%\u001a6\u0010\u001f\u001a\u00020\u0001*\u00020 2\u0006\u0010!\u001a\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'\u001a6\u0010\u001f\u001a\u00020\u0001*\u00020 2\u0006\u0010!\u001a\u00020\u00102\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010)\u001a\"\u0010*\u001a\u00020\u0001*\u00020 2\u0006\u0010+\u001a\u00020\u0014H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-\u001a\"\u0010.\u001a\u00020\u0001*\u00020 2\u0006\u0010+\u001a\u00020\u0017H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b/\u00100\u001a\"\u00101\u001a\u00020\u0001*\u00020 2\u0006\u0010+\u001a\u00020\u001aH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u00103\u001a\"\u00104\u001a\u00020\u0001*\u00020 2\u0006\u0010+\u001a\u00020\u001dH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b5\u00106\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00067"}, d2 = {"readFully", "", "Lio/ktor/utils/io/core/Input;", "dst", "Lkotlin/UByteArray;", TypedValues.CycleType.S_WAVE_OFFSET, "", "length", "readFully-o1GoV1E", "(Lio/ktor/utils/io/core/Input;[BII)V", "Lkotlin/UIntArray;", "readFully-o2ZM2JE", "(Lio/ktor/utils/io/core/Input;[III)V", "Lkotlin/ULongArray;", "readFully-pqYNikA", "(Lio/ktor/utils/io/core/Input;[JII)V", "Lkotlin/UShortArray;", "readFully-Wt3Bwxc", "(Lio/ktor/utils/io/core/Input;[SII)V", "readUByte", "Lkotlin/UByte;", "(Lio/ktor/utils/io/core/Input;)B", "readUInt", "Lkotlin/UInt;", "(Lio/ktor/utils/io/core/Input;)I", "readULong", "Lkotlin/ULong;", "(Lio/ktor/utils/io/core/Input;)J", "readUShort", "Lkotlin/UShort;", "(Lio/ktor/utils/io/core/Input;)S", "writeFully", "Lio/ktor/utils/io/core/Output;", "array", "writeFully-o1GoV1E", "(Lio/ktor/utils/io/core/Output;[BII)V", "writeFully-o2ZM2JE", "(Lio/ktor/utils/io/core/Output;[III)V", "writeFully-pqYNikA", "(Lio/ktor/utils/io/core/Output;[JII)V", "writeFully-Wt3Bwxc", "(Lio/ktor/utils/io/core/Output;[SII)V", "writeUByte", "v", "writeUByte-EK-6454", "(Lio/ktor/utils/io/core/Output;B)V", "writeUInt", "writeUInt-Qn1smSk", "(Lio/ktor/utils/io/core/Output;I)V", "writeULong", "writeULong-2TYgG_w", "(Lio/ktor/utils/io/core/Output;J)V", "writeUShort", "writeUShort-i8woANY", "(Lio/ktor/utils/io/core/Output;S)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UnsignedTypesKt {
    public static final byte readUByte(Input $this$readUByte) {
        Intrinsics.checkNotNullParameter($this$readUByte, "<this>");
        return UByte.m528constructorimpl($this$readUByte.readByte());
    }

    public static final short readUShort(Input $this$readUShort) {
        Intrinsics.checkNotNullParameter($this$readUShort, "<this>");
        return UShort.m791constructorimpl(InputPrimitivesKt.readShort($this$readUShort));
    }

    public static final int readUInt(Input $this$readUInt) {
        Intrinsics.checkNotNullParameter($this$readUInt, "<this>");
        return UInt.m605constructorimpl(InputPrimitivesKt.readInt($this$readUInt));
    }

    public static final long readULong(Input $this$readULong) {
        Intrinsics.checkNotNullParameter($this$readULong, "<this>");
        return ULong.m684constructorimpl(InputPrimitivesKt.readLong($this$readULong));
    }

    /* renamed from: readFully-o1GoV1E$default, reason: not valid java name */
    public static /* synthetic */ void m468readFullyo1GoV1E$default(Input readFully, byte[] dst, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = UByteArray.m587getSizeimpl(dst) - offset;
        }
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully(readFully, dst, offset, length);
    }

    /* renamed from: readFully-o1GoV1E, reason: not valid java name */
    public static final void m467readFullyo1GoV1E(Input readFully, byte[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully(readFully, dst, offset, length);
    }

    /* renamed from: readFully-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ void m466readFullyWt3Bwxc$default(Input readFully, short[] dst, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = UShortArray.m850getSizeimpl(dst) - offset;
        }
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully(readFully, dst, offset, length);
    }

    /* renamed from: readFully-Wt3Bwxc, reason: not valid java name */
    public static final void m465readFullyWt3Bwxc(Input readFully, short[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully(readFully, dst, offset, length);
    }

    /* renamed from: readFully-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ void m470readFullyo2ZM2JE$default(Input readFully, int[] dst, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = UIntArray.m666getSizeimpl(dst) - offset;
        }
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully(readFully, dst, offset, length);
    }

    /* renamed from: readFully-o2ZM2JE, reason: not valid java name */
    public static final void m469readFullyo2ZM2JE(Input readFully, int[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully(readFully, dst, offset, length);
    }

    /* renamed from: readFully-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ void m472readFullypqYNikA$default(Input readFully, long[] dst, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = ULongArray.m745getSizeimpl(dst) - offset;
        }
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully(readFully, dst, offset, length);
    }

    /* renamed from: readFully-pqYNikA, reason: not valid java name */
    public static final void m471readFullypqYNikA(Input readFully, long[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(dst, "dst");
        InputArraysKt.readFully(readFully, dst, offset, length);
    }

    /* renamed from: writeUByte-EK-6454, reason: not valid java name */
    public static final void m481writeUByteEK6454(Output writeUByte, byte v) {
        Intrinsics.checkNotNullParameter(writeUByte, "$this$writeUByte");
        writeUByte.writeByte(v);
    }

    /* renamed from: writeUShort-i8woANY, reason: not valid java name */
    public static final void m484writeUShorti8woANY(Output writeUShort, short v) {
        Intrinsics.checkNotNullParameter(writeUShort, "$this$writeUShort");
        OutputPrimitivesKt.writeShort(writeUShort, v);
    }

    /* renamed from: writeUInt-Qn1smSk, reason: not valid java name */
    public static final void m482writeUIntQn1smSk(Output writeUInt, int v) {
        Intrinsics.checkNotNullParameter(writeUInt, "$this$writeUInt");
        OutputPrimitivesKt.writeInt(writeUInt, v);
    }

    /* renamed from: writeULong-2TYgG_w, reason: not valid java name */
    public static final void m483writeULong2TYgG_w(Output writeULong, long v) {
        Intrinsics.checkNotNullParameter(writeULong, "$this$writeULong");
        OutputPrimitivesKt.writeLong(writeULong, v);
    }

    /* renamed from: writeFully-o1GoV1E$default, reason: not valid java name */
    public static /* synthetic */ void m476writeFullyo1GoV1E$default(Output writeFully, byte[] array, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = UByteArray.m587getSizeimpl(array) - offset;
        }
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(array, "array");
        OutputKt.writeFully(writeFully, array, offset, length);
    }

    /* renamed from: writeFully-o1GoV1E, reason: not valid java name */
    public static final void m475writeFullyo1GoV1E(Output writeFully, byte[] array, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(array, "array");
        OutputKt.writeFully(writeFully, array, offset, length);
    }

    /* renamed from: writeFully-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ void m474writeFullyWt3Bwxc$default(Output writeFully, short[] array, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = UShortArray.m850getSizeimpl(array) - offset;
        }
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(array, "array");
        OutputKt.writeFully(writeFully, array, offset, length);
    }

    /* renamed from: writeFully-Wt3Bwxc, reason: not valid java name */
    public static final void m473writeFullyWt3Bwxc(Output writeFully, short[] array, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(array, "array");
        OutputKt.writeFully(writeFully, array, offset, length);
    }

    /* renamed from: writeFully-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ void m478writeFullyo2ZM2JE$default(Output writeFully, int[] array, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = UIntArray.m666getSizeimpl(array) - offset;
        }
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(array, "array");
        OutputKt.writeFully(writeFully, array, offset, length);
    }

    /* renamed from: writeFully-o2ZM2JE, reason: not valid java name */
    public static final void m477writeFullyo2ZM2JE(Output writeFully, int[] array, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(array, "array");
        OutputKt.writeFully(writeFully, array, offset, length);
    }

    /* renamed from: writeFully-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ void m480writeFullypqYNikA$default(Output writeFully, long[] array, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = ULongArray.m745getSizeimpl(array) - offset;
        }
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(array, "array");
        OutputKt.writeFully(writeFully, array, offset, length);
    }

    /* renamed from: writeFully-pqYNikA, reason: not valid java name */
    public static final void m479writeFullypqYNikA(Output writeFully, long[] array, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(array, "array");
        OutputKt.writeFully(writeFully, array, offset, length);
    }
}
