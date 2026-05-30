package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.EncodeResult;
import io.ktor.utils.io.core.internal.NumbersKt;
import io.ktor.utils.io.core.internal.UTF8Kt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BufferCompatibility.kt */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a$\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0007\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\fH\u0007\u001a&\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\f2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0007\u001a(\u0010\r\u001a\u00020\u0003*\u00020\u00052\u0006\u0010\b\u001a\u00020\f2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003H\u0000\u001a\u001a\u0010\u000e\u001a\u00020\u000f*\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012\u001a'\u0010\u000e\u001a\u00020\u000f*\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0013ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001c\u0010\u000e\u001a\u00020\u000f*\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0012H\u0007\u001a\f\u0010\u0019\u001a\u00020\u000f*\u00020\u0005H\u0007\u001a\f\u0010\u001a\u001a\u00020\u0005*\u00020\u0005H\u0007\u001a\f\u0010\u001a\u001a\u00020\u001b*\u00020\u001bH\u0007\u001a\u0014\u0010\u001c\u001a\u00020\u000f*\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0003H\u0007\u001a1\u0010\u001d\u001a\u00020\u000f*\u00020\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00120\u001f2\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010!\u001a6\u0010\"\u001a\u00020\u0003*\u00020\u00052\n\u0010#\u001a\u00060$j\u0002`%2\n\u0010&\u001a\u00060'j\u0002`(2\u0006\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020\u0003H\u0007\u001a\f\u0010,\u001a\u00020\u0003*\u00020\u0005H\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006-"}, d2 = {"appendFailed", "", "length", "", "append", "Lio/ktor/utils/io/core/Buffer;", "c", "", "csq", "", "start", "end", "", "appendChars", "fill", "", "times", "value", "", "Lkotlin/UByte;", "fill-sEu17AQ", "(Lio/ktor/utils/io/core/Buffer;IB)V", "n", "", "v", "flush", "makeView", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "pushBack", "readFully", "dst", "", TypedValues.CycleType.S_WAVE_OFFSET, "(Lio/ktor/utils/io/core/Buffer;[Ljava/lang/Byte;II)V", "readText", "decoder", "Ljava/nio/charset/CharsetDecoder;", "Lio/ktor/utils/io/charsets/CharsetDecoder;", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "lastBuffer", "", "max", "tryPeek", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BufferCompatibilityKt {
    public static final void fill(Buffer $this$fill, int times, byte value) {
        Intrinsics.checkNotNullParameter($this$fill, "<this>");
        if (!(times >= 0)) {
            throw new IllegalArgumentException(("times shouldn't be negative: " + times).toString());
        }
        if (!(times <= $this$fill.getLimit() - $this$fill.getWritePosition())) {
            throw new IllegalArgumentException(("times shouldn't be greater than the write remaining space: " + times + " > " + ($this$fill.getLimit() - $this$fill.getWritePosition())).toString());
        }
        MemoryJvmKt.m257fillJT6ljtQ($this$fill.getMemory(), $this$fill.getWritePosition(), times, value);
        $this$fill.commitWritten(times);
    }

    /* renamed from: fill-sEu17AQ, reason: not valid java name */
    public static final void m380fillsEu17AQ(Buffer fill, int times, byte value) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        fill(fill, times, value);
    }

    @Deprecated(message = "Use fill with n with type Int")
    public static final void fill(Buffer $this$fill, long n, byte v) {
        Intrinsics.checkNotNullParameter($this$fill, "<this>");
        if (n < 2147483647L) {
            fill($this$fill, (int) n, v);
        } else {
            NumbersKt.failLongToIntConversion(n, "n");
            throw new KotlinNothingValueException();
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use rewind instead", replaceWith = @ReplaceWith(expression = "rewind(n)", imports = {}))
    public static final void pushBack(Buffer $this$pushBack, int n) {
        Intrinsics.checkNotNullParameter($this$pushBack, "<this>");
        $this$pushBack.rewind(n);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use duplicate instead", replaceWith = @ReplaceWith(expression = "duplicate()", imports = {}))
    public static final Buffer makeView(Buffer $this$makeView) {
        Intrinsics.checkNotNullParameter($this$makeView, "<this>");
        return $this$makeView.duplicate();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use duplicate instead", replaceWith = @ReplaceWith(expression = "duplicate()", imports = {}))
    public static final ChunkBuffer makeView(ChunkBuffer $this$makeView) {
        Intrinsics.checkNotNullParameter($this$makeView, "<this>");
        return $this$makeView.duplicate();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Does nothing.")
    public static final void flush(Buffer $this$flush) {
        Intrinsics.checkNotNullParameter($this$flush, "<this>");
    }

    public static /* synthetic */ int appendChars$default(Buffer buffer, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return appendChars(buffer, charSequence, i, i2);
    }

    public static final int appendChars(Buffer $this$appendChars, CharSequence csq, int start, int end) {
        Intrinsics.checkNotNullParameter($this$appendChars, "<this>");
        Intrinsics.checkNotNullParameter(csq, "csq");
        ByteBuffer dst = $this$appendChars.getMemory();
        int dstStart = $this$appendChars.getWritePosition();
        int dstEndExclusive = $this$appendChars.getLimit();
        int result = UTF8Kt.m497encodeUTF8lBXzO7A(dst, csq, start, end, dstStart, dstEndExclusive);
        int charactersWritten = EncodeResult.m493getCharactersMh2AYeg(result) & UShort.MAX_VALUE;
        int charactersWritten2 = EncodeResult.m492getBytesMh2AYeg(result);
        int rc$iv = 65535 & charactersWritten2;
        $this$appendChars.commitWritten(rc$iv);
        return start + charactersWritten;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is no longer supported. Use a packet builder to append characters instead.")
    public static final Buffer append(Buffer $this$append, char c) {
        byte value$iv$iv;
        Intrinsics.checkNotNullParameter($this$append, "<this>");
        ByteBuffer memory = $this$append.getMemory();
        int start = $this$append.getWritePosition();
        int endExclusive = $this$append.getLimit();
        boolean z = false;
        if (c >= 0 && c < 128) {
            byte value$iv$iv2 = (byte) c;
            memory.put(start, value$iv$iv2);
            value$iv$iv = 1;
        } else {
            if (128 <= c && c < 2048) {
                byte value$iv$iv3 = (byte) (((c >> 6) & 31) | 192);
                memory.put(start, value$iv$iv3);
                int index$iv$iv = start + 1;
                byte value$iv$iv4 = (byte) (128 | (c & '?'));
                memory.put(index$iv$iv, value$iv$iv4);
                value$iv$iv = 2;
            } else {
                if (2048 <= c && c < 0) {
                    byte value$iv$iv5 = (byte) (((c >> '\f') & 15) | 224);
                    memory.put(start, value$iv$iv5);
                    int index$iv$iv2 = start + 1;
                    byte value$iv$iv6 = (byte) (((c >> 6) & 63) | 128);
                    memory.put(index$iv$iv2, value$iv$iv6);
                    int index$iv$iv3 = start + 2;
                    byte value$iv$iv7 = (byte) (128 | (c & '?'));
                    memory.put(index$iv$iv3, value$iv$iv7);
                    value$iv$iv = 3;
                } else {
                    if (0 <= c && c < 0) {
                        z = true;
                    }
                    if (!z) {
                        UTF8Kt.malformedCodePoint(c);
                        throw new KotlinNothingValueException();
                    }
                    byte value$iv$iv8 = (byte) (((c >> 18) & 7) | 240);
                    memory.put(start, value$iv$iv8);
                    int index$iv$iv4 = start + 1;
                    byte value$iv$iv9 = (byte) (((c >> '\f') & 63) | 128);
                    memory.put(index$iv$iv4, value$iv$iv9);
                    int index$iv$iv5 = start + 2;
                    byte value$iv$iv10 = (byte) (((c >> 6) & 63) | 128);
                    memory.put(index$iv$iv5, value$iv$iv10);
                    int index$iv$iv6 = start + 3;
                    byte value$iv$iv11 = (byte) (128 | (c & '?'));
                    memory.put(index$iv$iv6, value$iv$iv11);
                    value$iv$iv = 4;
                }
            }
        }
        if (value$iv$iv <= endExclusive - start) {
            $this$append.commitWritten(value$iv$iv);
            return $this$append;
        }
        appendFailed(1);
        throw new KotlinNothingValueException();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is no longer supported. Use a packet builder to append characters instead.")
    public static final Buffer append(Buffer $this$append, CharSequence csq) {
        Intrinsics.checkNotNullParameter($this$append, "<this>");
        throw new IllegalStateException("This is no longer supported. Use a packet builder to append characters instead.".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is no longer supported. Use a packet builder to append characters instead.")
    public static final Buffer append(Buffer $this$append, CharSequence csq, int start, int end) {
        Intrinsics.checkNotNullParameter($this$append, "<this>");
        throw new IllegalStateException("This is no longer supported. Use a packet builder to append characters instead.".toString());
    }

    private static final Void appendFailed(int length) {
        throw new BufferLimitExceededException("Not enough free space available to write " + length + " character(s).");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is no longer supported. Use a packet builder to append characters instead.")
    public static final Buffer append(Buffer $this$append, char[] csq, int start, int end) {
        Intrinsics.checkNotNullParameter($this$append, "<this>");
        Intrinsics.checkNotNullParameter(csq, "csq");
        throw new IllegalStateException("This is no longer supported. Use a packet builder to append characters instead.".toString());
    }

    public static /* synthetic */ int readText$default(Buffer buffer, CharsetDecoder charsetDecoder, Appendable appendable, boolean z, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(buffer, charsetDecoder, appendable, z, i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is no longer supported. Read from a packet instead.")
    public static final int readText(Buffer $this$readText, CharsetDecoder decoder, Appendable out, boolean lastBuffer, int max) {
        Intrinsics.checkNotNullParameter($this$readText, "<this>");
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(out, "out");
        return CharsetJVMKt.decodeBuffer(decoder, $this$readText, out, lastBuffer, max);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use tryPeekByte instead", replaceWith = @ReplaceWith(expression = "tryPeekByte()", imports = {}))
    public static final int tryPeek(Buffer $this$tryPeek) {
        Intrinsics.checkNotNullParameter($this$tryPeek, "<this>");
        return $this$tryPeek.tryPeekByte();
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, Byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        readFully(buffer, bArr, i, i2);
    }

    public static final void readFully(Buffer $this$readFully, Byte[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        ByteBuffer memory = $this$readFully.getMemory();
        int start = $this$readFully.getReadPosition();
        int endExclusive = $this$readFully.getWritePosition();
        if (endExclusive - start < length) {
            throw new EOFException("Not enough bytes available to read " + length + " bytes");
        }
        for (int index = 0; index < length; index++) {
            int index$iv = index + start;
            dst[index + offset] = Byte.valueOf(memory.get(index$iv));
        }
        $this$readFully.discardExact(length);
    }
}
