package io.ktor.utils.io.core;

import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OutputPrimitives.kt */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0006\u001a\u0012\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\b\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\bH\u0002\u001a\u0014\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\bH\u0002\u001a\u0012\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\f\u001a\u0014\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\fH\u0002\u001a)\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\b2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012H\u0082\b\u001aA\u0010\u0014\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\b2'\u0010\u0015\u001a#\u0012\u0004\u0012\u00020\u0017\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0016H\u0082\bø\u0001\u0000\u001a\u0012\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u001c\u001a\u0014\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u001cH\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"writeDouble", "", "Lio/ktor/utils/io/core/Output;", "value", "", "writeFloat", "", "writeInt", "", "writeIntByteByByte", "writeIntFallback", "writeLong", "", "writeLongFallback", "writePrimitiveFallbackTemplate", "", "componentSize", "writeOperation", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Buffer;", "writePrimitiveTemplate", "block", "Lkotlin/Function2;", "Lio/ktor/utils/io/bits/Memory;", "Lkotlin/ParameterName;", "name", "index", "writeShort", "", "writeShortFallback", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OutputPrimitivesKt {
    public static final void writeShort(Output $this$writeShort, short value) {
        boolean z;
        Intrinsics.checkNotNullParameter($this$writeShort, "<this>");
        int index$iv = $this$writeShort.getTailPosition();
        if ($this$writeShort.getTailEndExclusive() - index$iv > 2) {
            $this$writeShort.setTailPosition$ktor_io(index$iv + 2);
            ByteBuffer memory = $this$writeShort.getTailMemory();
            memory.putShort(index$iv, value);
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            writeShortFallback($this$writeShort, value);
        }
    }

    private static final void writeShortFallback(Output $this$writeShortFallback, short value) {
        Buffer tail$iv = $this$writeShortFallback.prepareWriteHead(2);
        Buffer it = tail$iv;
        BufferPrimitivesKt.writeShort(it, value);
        $this$writeShortFallback.afterHeadWrite();
    }

    public static final void writeInt(Output $this$writeInt, int value) {
        boolean z;
        Intrinsics.checkNotNullParameter($this$writeInt, "<this>");
        int index$iv = $this$writeInt.getTailPosition();
        if ($this$writeInt.getTailEndExclusive() - index$iv > 4) {
            $this$writeInt.setTailPosition$ktor_io(index$iv + 4);
            ByteBuffer memory = $this$writeInt.getTailMemory();
            memory.putInt(index$iv, value);
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            writeIntFallback($this$writeInt, value);
        }
    }

    private static final void writeIntFallback(Output $this$writeIntFallback, int value) {
        Buffer tail$iv = $this$writeIntFallback.prepareWriteHead(4);
        Buffer it = tail$iv;
        BufferPrimitivesKt.writeInt(it, value);
        $this$writeIntFallback.afterHeadWrite();
    }

    private static final void writeIntByteByByte(Output $this$writeIntByteByByte, int value) {
        int $this$highShort$iv = (short) (value >>> 16);
        $this$writeIntByteByByte.writeByte((byte) ($this$highShort$iv >>> 8));
        $this$writeIntByteByByte.writeByte((byte) ($this$highShort$iv & 255));
        int $this$lowShort$iv = (short) (65535 & value);
        $this$writeIntByteByByte.writeByte((byte) ($this$lowShort$iv >>> 8));
        $this$writeIntByteByByte.writeByte((byte) ($this$lowShort$iv & 255));
    }

    public static final void writeLong(Output $this$writeLong, long value) {
        boolean z;
        Intrinsics.checkNotNullParameter($this$writeLong, "<this>");
        int index$iv = $this$writeLong.getTailPosition();
        if ($this$writeLong.getTailEndExclusive() - index$iv > 8) {
            $this$writeLong.setTailPosition$ktor_io(index$iv + 8);
            ByteBuffer memory = $this$writeLong.getTailMemory();
            memory.putLong(index$iv, value);
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            writeLongFallback($this$writeLong, value);
        }
    }

    private static final void writeLongFallback(Output $this$writeLongFallback, long value) {
        Buffer tail$iv = $this$writeLongFallback.prepareWriteHead(8);
        Buffer it = tail$iv;
        BufferPrimitivesKt.writeLong(it, value);
        $this$writeLongFallback.afterHeadWrite();
    }

    public static final void writeFloat(Output $this$writeFloat, float value) {
        boolean z;
        Intrinsics.checkNotNullParameter($this$writeFloat, "<this>");
        int index$iv = $this$writeFloat.getTailPosition();
        if ($this$writeFloat.getTailEndExclusive() - index$iv > 4) {
            $this$writeFloat.setTailPosition$ktor_io(index$iv + 4);
            ByteBuffer memory = $this$writeFloat.getTailMemory();
            memory.putFloat(index$iv, value);
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            writeIntFallback($this$writeFloat, Float.floatToRawIntBits(value));
        }
    }

    public static final void writeDouble(Output $this$writeDouble, double value) {
        boolean z;
        Intrinsics.checkNotNullParameter($this$writeDouble, "<this>");
        int index$iv = $this$writeDouble.getTailPosition();
        if ($this$writeDouble.getTailEndExclusive() - index$iv > 8) {
            $this$writeDouble.setTailPosition$ktor_io(index$iv + 8);
            ByteBuffer memory = $this$writeDouble.getTailMemory();
            memory.putDouble(index$iv, value);
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            writeLongFallback($this$writeDouble, Double.doubleToRawLongBits(value));
        }
    }

    private static final boolean writePrimitiveTemplate(Output $this$writePrimitiveTemplate, int componentSize, Function2<? super Memory, ? super Integer, Unit> function2) {
        int index = $this$writePrimitiveTemplate.getTailPosition();
        if ($this$writePrimitiveTemplate.getTailEndExclusive() - index > componentSize) {
            $this$writePrimitiveTemplate.setTailPosition$ktor_io(index + componentSize);
            function2.invoke(Memory.m234boximpl($this$writePrimitiveTemplate.getTailMemory()), Integer.valueOf(index));
            return true;
        }
        return false;
    }

    private static final boolean writePrimitiveFallbackTemplate(Output $this$writePrimitiveFallbackTemplate, int componentSize, Function1<? super Buffer, Unit> function1) {
        ChunkBuffer tail = $this$writePrimitiveFallbackTemplate.prepareWriteHead(componentSize);
        function1.invoke(tail);
        $this$writePrimitiveFallbackTemplate.afterHeadWrite();
        return true;
    }
}
