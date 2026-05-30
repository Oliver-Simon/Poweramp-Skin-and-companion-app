package io.ktor.utils.io.core;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InputPrimitives.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\u0002\u001a\f\u0010\t\u001a\u00020\b*\u00020\u0002H\u0002\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\u0002\u001a\f\u0010\f\u001a\u00020\u000b*\u00020\u0002H\u0002\u001aK\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\b2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u000e0\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0014H\u0082\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a4\u0010\u0016\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\b2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u0002H\u000e0\u0018H\u0082\b¢\u0006\u0002\u0010\u001a\u001a\n\u0010\u001b\u001a\u00020\u001c*\u00020\u0002\u001a\f\u0010\u001d\u001a\u00020\u001c*\u00020\u0002H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"readDouble", "", "Lio/ktor/utils/io/core/Input;", "readDoubleFallback", "readFloat", "", "readFloatFallback", "readInt", "", "readIntFallback", "readLong", "", "readLongFallback", "readPrimitive", "R", ContentDisposition.Parameters.Size, "main", "Lkotlin/Function2;", "Lio/ktor/utils/io/bits/Memory;", "fallback", "Lkotlin/Function0;", "(Lio/ktor/utils/io/core/Input;ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "readPrimitiveFallback", "read", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Buffer;", "(Lio/ktor/utils/io/core/Input;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "readShort", "", "readShortFallback", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class InputPrimitivesKt {
    public static final short readShort(Input $this$readShort) {
        Intrinsics.checkNotNullParameter($this$readShort, "<this>");
        if ($this$readShort.getHeadEndExclusive() - $this$readShort.getHeadPosition() <= 2) {
            return readShortFallback($this$readShort);
        }
        int index$iv = $this$readShort.getHeadPosition();
        $this$readShort.setHeadPosition(index$iv + 2);
        ByteBuffer memory = $this$readShort.getHeadMemory();
        return memory.getShort(index$iv);
    }

    private static final short readShortFallback(Input $this$readShortFallback) {
        ChunkBuffer head$iv = UnsafeKt.prepareReadFirstHead($this$readShortFallback, 2);
        if (head$iv == null) {
            StringsKt.prematureEndOfStream(2);
            throw new KotlinNothingValueException();
        }
        ChunkBuffer it = head$iv;
        short value$iv = BufferPrimitivesKt.readShort((Buffer) it);
        UnsafeKt.completeReadHead($this$readShortFallback, head$iv);
        return value$iv;
    }

    public static final int readInt(Input $this$readInt) {
        Intrinsics.checkNotNullParameter($this$readInt, "<this>");
        if ($this$readInt.getHeadEndExclusive() - $this$readInt.getHeadPosition() <= 4) {
            return readIntFallback($this$readInt);
        }
        int index$iv = $this$readInt.getHeadPosition();
        $this$readInt.setHeadPosition(index$iv + 4);
        ByteBuffer memory = $this$readInt.getHeadMemory();
        return memory.getInt(index$iv);
    }

    private static final int readIntFallback(Input $this$readIntFallback) {
        ChunkBuffer head$iv = UnsafeKt.prepareReadFirstHead($this$readIntFallback, 4);
        if (head$iv == null) {
            StringsKt.prematureEndOfStream(4);
            throw new KotlinNothingValueException();
        }
        ChunkBuffer it = head$iv;
        int value$iv = BufferPrimitivesKt.readInt((Buffer) it);
        UnsafeKt.completeReadHead($this$readIntFallback, head$iv);
        return value$iv;
    }

    public static final long readLong(Input $this$readLong) {
        Intrinsics.checkNotNullParameter($this$readLong, "<this>");
        if ($this$readLong.getHeadEndExclusive() - $this$readLong.getHeadPosition() <= 8) {
            return readLongFallback($this$readLong);
        }
        int index$iv = $this$readLong.getHeadPosition();
        $this$readLong.setHeadPosition(index$iv + 8);
        ByteBuffer memory = $this$readLong.getHeadMemory();
        return memory.getLong(index$iv);
    }

    private static final long readLongFallback(Input $this$readLongFallback) {
        ChunkBuffer head$iv = UnsafeKt.prepareReadFirstHead($this$readLongFallback, 8);
        if (head$iv == null) {
            StringsKt.prematureEndOfStream(8);
            throw new KotlinNothingValueException();
        }
        ChunkBuffer it = head$iv;
        long value$iv = BufferPrimitivesKt.readLong((Buffer) it);
        UnsafeKt.completeReadHead($this$readLongFallback, head$iv);
        return value$iv;
    }

    public static final float readFloat(Input $this$readFloat) {
        Intrinsics.checkNotNullParameter($this$readFloat, "<this>");
        if ($this$readFloat.getHeadEndExclusive() - $this$readFloat.getHeadPosition() <= 4) {
            return readFloatFallback($this$readFloat);
        }
        int index$iv = $this$readFloat.getHeadPosition();
        $this$readFloat.setHeadPosition(index$iv + 4);
        ByteBuffer memory = $this$readFloat.getHeadMemory();
        return memory.getFloat(index$iv);
    }

    public static final float readFloatFallback(Input $this$readFloatFallback) {
        Intrinsics.checkNotNullParameter($this$readFloatFallback, "<this>");
        ChunkBuffer head$iv = UnsafeKt.prepareReadFirstHead($this$readFloatFallback, 4);
        if (head$iv == null) {
            StringsKt.prematureEndOfStream(4);
            throw new KotlinNothingValueException();
        }
        ChunkBuffer it = head$iv;
        float value$iv = BufferPrimitivesKt.readFloat((Buffer) it);
        UnsafeKt.completeReadHead($this$readFloatFallback, head$iv);
        return value$iv;
    }

    public static final double readDouble(Input $this$readDouble) {
        Intrinsics.checkNotNullParameter($this$readDouble, "<this>");
        if ($this$readDouble.getHeadEndExclusive() - $this$readDouble.getHeadPosition() <= 8) {
            return readDoubleFallback($this$readDouble);
        }
        int index$iv = $this$readDouble.getHeadPosition();
        $this$readDouble.setHeadPosition(index$iv + 8);
        ByteBuffer memory = $this$readDouble.getHeadMemory();
        return memory.getDouble(index$iv);
    }

    public static final double readDoubleFallback(Input $this$readDoubleFallback) {
        Intrinsics.checkNotNullParameter($this$readDoubleFallback, "<this>");
        ChunkBuffer head$iv = UnsafeKt.prepareReadFirstHead($this$readDoubleFallback, 8);
        if (head$iv == null) {
            StringsKt.prematureEndOfStream(8);
            throw new KotlinNothingValueException();
        }
        ChunkBuffer it = head$iv;
        double value$iv = BufferPrimitivesKt.readDouble((Buffer) it);
        UnsafeKt.completeReadHead($this$readDoubleFallback, head$iv);
        return value$iv;
    }

    private static final <R> R readPrimitive(Input $this$readPrimitive, int size, Function2<? super Memory, ? super Integer, ? extends R> function2, Function0<? extends R> function0) {
        if ($this$readPrimitive.getHeadEndExclusive() - $this$readPrimitive.getHeadPosition() > size) {
            int index = $this$readPrimitive.getHeadPosition();
            $this$readPrimitive.setHeadPosition(index + size);
            return function2.invoke(Memory.m234boximpl($this$readPrimitive.getHeadMemory()), Integer.valueOf(index));
        }
        return function0.invoke();
    }

    private static final <R> R readPrimitiveFallback(Input $this$readPrimitiveFallback, int size, Function1<? super Buffer, ? extends R> function1) {
        ChunkBuffer head = UnsafeKt.prepareReadFirstHead($this$readPrimitiveFallback, size);
        if (head == null) {
            StringsKt.prematureEndOfStream(size);
            throw new KotlinNothingValueException();
        }
        R invoke = function1.invoke(head);
        UnsafeKt.completeReadHead($this$readPrimitiveFallback, head);
        return invoke;
    }
}
