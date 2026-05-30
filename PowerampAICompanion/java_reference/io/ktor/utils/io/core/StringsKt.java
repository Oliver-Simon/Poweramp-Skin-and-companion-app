package io.ktor.utils.io.core;

import io.ktor.http.ContentDisposition;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.charsets.EncodingKt;
import io.ktor.utils.io.core.internal.CharArraySequence;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.EncodeResult;
import io.ktor.utils.io.core.internal.UTF8Kt;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

/* compiled from: Strings.kt */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003H\u0001\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0001\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0003H\u0002\u001a\r\u0010\t\u001a\u00020\n*\u00020\u000bH\u0082\b\u001a\u0014\u0010\f\u001a\u00020\r*\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u001a\n\u0010\f\u001a\u00020\r*\u00020\u0010\u001a\u0012\u0010\f\u001a\u00020\r*\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0003\u001a\u001e\u0010\u0011\u001a\u00020\r*\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u001a\"\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u001a\"\u0010\u0014\u001a\u00020\u0015*\u00020\u00102\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u001a\"\u0010\u0014\u001a\u00020\u0015*\u00020\u00102\n\u0010\u001a\u001a\u00060\u001bj\u0002`\u001c2\b\b\u0002\u0010\u0013\u001a\u00020\u0003H\u0007\u001a.\u0010\u0014\u001a\u00020\u0003*\u00020\u00102\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u001a\"\u0010 \u001a\u00020\u0015*\u00020\u00102\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010\u000f\u001a\u00020\u0003H\u0007\u001a\"\u0010!\u001a\u00020\u0015*\u00020\u00102\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010\"\u001a\u00020\u0003H\u0007\u001a \u0010!\u001a\u00020\u0015*\u00020\u00102\u0006\u0010#\u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a \u0010$\u001a\u00020\u0015*\u00020\u00102\u0006\u0010\b\u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a \u0010%\u001a\u0004\u0018\u00010\u0015*\u00020\u000e2\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a \u0010%\u001a\u0004\u0018\u00010\u0015*\u00020\u00102\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u001e\u0010'\u001a\u00020\n*\u00020\u00102\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001c\u0010(\u001a\u00020\u0015*\u00020\u00102\u0006\u0010)\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a$\u0010*\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u001d\u001a\u00020+2\u0006\u0010)\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a(\u0010*\u001a\u00020\u0003*\u00020\u00102\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\u0006\u0010)\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a,\u0010,\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u001d\u001a\u00020+2\u0006\u0010)\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H\u0002\u001a0\u0010,\u001a\u00020\u0003*\u00020\u00102\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\u0006\u0010)\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H\u0002\u001a$\u0010.\u001a\u00020\u0003*\u00020\u00102\u0006\u0010)\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020+H\u0002\u001a\u001b\u0010/\u001a\u00020\r*\u00020\u00152\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0086\b\u001a4\u00100\u001a\u000201*\u00020+2\u0006\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a4\u00100\u001a\u000201*\u00020+2\u0006\u00102\u001a\u0002062\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a$\u00107\u001a\u000201*\u00020+2\u0006\u00102\u001a\u0002062\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u0003H\u0002¨\u00068"}, d2 = {"bufferLimitExceeded", "", "limit", "", "prematureEndOfStream", ContentDisposition.Parameters.Size, "", "prematureEndOfStreamToReadChars", "charactersCount", "isAsciiChar", "", "", "readBytes", "", "Lio/ktor/utils/io/core/ByteReadPacket;", "n", "Lio/ktor/utils/io/core/Input;", "readBytesOf", "min", "max", "readText", "", "Lio/ktor/utils/io/core/Buffer;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "decoder", "Ljava/nio/charset/CharsetDecoder;", "Lio/ktor/utils/io/charsets/CharsetDecoder;", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "readTextExact", "readTextExactBytes", "bytes", "bytesCount", "readTextExactCharacters", "readUTF8Line", "estimate", "readUTF8LineTo", "readUTF8UntilDelimiter", "delimiters", "readUTF8UntilDelimiterTo", "Lio/ktor/utils/io/core/Output;", "readUTF8UntilDelimiterToSlowUtf8", "decoded0", "readUTFUntilDelimiterToSlowAscii", "toByteArray", "writeText", "", "text", "", "fromIndex", "toIndex", "", "writeTextUtf8", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class StringsKt {
    public static /* synthetic */ byte[] toByteArray$default(String $this$toByteArray_u24default, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter($this$toByteArray_u24default, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            return kotlin.text.StringsKt.encodeToByteArray($this$toByteArray_u24default);
        }
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
        return CharsetJVMKt.encodeToByteArray(newEncoder, $this$toByteArray_u24default, 0, $this$toByteArray_u24default.length());
    }

    public static final byte[] toByteArray(String $this$toByteArray, Charset charset) {
        Intrinsics.checkNotNullParameter($this$toByteArray, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            return kotlin.text.StringsKt.encodeToByteArray($this$toByteArray);
        }
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
        return CharsetJVMKt.encodeToByteArray(newEncoder, $this$toByteArray, 0, $this$toByteArray.length());
    }

    public static /* synthetic */ String readUTF8Line$default(ByteReadPacket byteReadPacket, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 16;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return readUTF8Line(byteReadPacket, i, i2);
    }

    public static final String readUTF8Line(ByteReadPacket $this$readUTF8Line, int estimate, int limit) {
        Intrinsics.checkNotNullParameter($this$readUTF8Line, "<this>");
        if ($this$readUTF8Line.getEndOfInput()) {
            return null;
        }
        StringBuilder sb = new StringBuilder(estimate);
        if (readUTF8LineTo($this$readUTF8Line, sb, limit)) {
            return sb.toString();
        }
        return null;
    }

    public static /* synthetic */ String readUTF8Line$default(Input input, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 16;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return readUTF8Line(input, i, i2);
    }

    public static final String readUTF8Line(Input $this$readUTF8Line, int estimate, int limit) {
        Intrinsics.checkNotNullParameter($this$readUTF8Line, "<this>");
        StringBuilder sb = new StringBuilder(estimate);
        if (readUTF8LineTo($this$readUTF8Line, sb, limit)) {
            return sb.toString();
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00be, code lost:
    
        r20.discardExact(r33 - r28);
        r0 = r19;
     */
    /* JADX WARN: Removed duplicated region for block: B:116:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x016e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01c3 A[Catch: all -> 0x00e3, TRY_ENTER, TryCatch #1 {all -> 0x00e3, blocks: (B:58:0x00be, B:84:0x00b6, B:86:0x00cd, B:87:0x00d7, B:89:0x00d8, B:90:0x00e2, B:120:0x0174, B:142:0x01c3, B:149:0x0206, B:158:0x01ec, B:160:0x01fb, B:161:0x0203), top: B:57:0x00be }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0204 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean readUTF8LineTo(io.ktor.utils.io.core.Input r38, java.lang.Appendable r39, int r40) {
        /*
            Method dump skipped, instructions count: 765
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.StringsKt.readUTF8LineTo(io.ktor.utils.io.core.Input, java.lang.Appendable, int):boolean");
    }

    public static /* synthetic */ String readUTF8UntilDelimiter$default(Input input, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8UntilDelimiter(input, str, i);
    }

    public static final String readUTF8UntilDelimiter(Input $this$readUTF8UntilDelimiter, String delimiters, int limit) {
        Intrinsics.checkNotNullParameter($this$readUTF8UntilDelimiter, "<this>");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        StringBuilder $this$readUTF8UntilDelimiter_u24lambda_u242 = new StringBuilder();
        readUTF8UntilDelimiterTo($this$readUTF8UntilDelimiter, $this$readUTF8UntilDelimiter_u24lambda_u242, delimiters, limit);
        String sb = $this$readUTF8UntilDelimiter_u24lambda_u242.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static /* synthetic */ int readUTF8UntilDelimiterTo$default(Input input, Appendable appendable, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8UntilDelimiterTo(input, appendable, str, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x009c, code lost:
    
        bufferLimitExceeded(r32);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a4, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int readUTF8UntilDelimiterTo(io.ktor.utils.io.core.Input r29, java.lang.Appendable r30, java.lang.String r31, int r32) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.StringsKt.readUTF8UntilDelimiterTo(io.ktor.utils.io.core.Input, java.lang.Appendable, java.lang.String, int):int");
    }

    public static /* synthetic */ int readUTF8UntilDelimiterTo$default(Input input, Output output, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8UntilDelimiterTo(input, output, str, i);
    }

    public static final int readUTF8UntilDelimiterTo(Input $this$readUTF8UntilDelimiterTo, Output out, String delimiters, int limit) {
        Intrinsics.checkNotNullParameter($this$readUTF8UntilDelimiterTo, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        int delimitersCount = delimiters.length();
        if (delimitersCount == 1) {
            char $this$isAsciiChar$iv = delimiters.charAt(0);
            char $this$isAsciiChar$iv2 = $this$isAsciiChar$iv <= 127 ? (char) 1 : (char) 0;
            if ($this$isAsciiChar$iv2 != 0) {
                return (int) ScannerKt.readUntilDelimiter($this$readUTF8UntilDelimiterTo, (byte) delimiters.charAt(0), out);
            }
        }
        if (delimitersCount == 2) {
            char $this$isAsciiChar$iv3 = delimiters.charAt(0);
            char $this$isAsciiChar$iv4 = $this$isAsciiChar$iv3 <= 127 ? (char) 1 : (char) 0;
            if ($this$isAsciiChar$iv4 != 0) {
                char $this$isAsciiChar$iv5 = delimiters.charAt(1);
                if ($this$isAsciiChar$iv5 <= 127) {
                    return (int) ScannerKt.readUntilDelimiters($this$readUTF8UntilDelimiterTo, (byte) delimiters.charAt(0), (byte) delimiters.charAt(1), out);
                }
            }
        }
        return readUTFUntilDelimiterToSlowAscii($this$readUTF8UntilDelimiterTo, delimiters, limit, out);
    }

    public static /* synthetic */ byte[] readBytes$default(ByteReadPacket byteReadPacket, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            long $this$coerceAtMostMaxIntOrFail$iv = byteReadPacket.getRemaining();
            if ($this$coerceAtMostMaxIntOrFail$iv > 2147483647L) {
                throw new IllegalArgumentException("Unable to convert to a ByteArray: packet is too big");
            }
            i = (int) $this$coerceAtMostMaxIntOrFail$iv;
        }
        return readBytes(byteReadPacket, i);
    }

    public static final byte[] readBytes(ByteReadPacket $this$readBytes, int n) {
        Intrinsics.checkNotNullParameter($this$readBytes, "<this>");
        if (n != 0) {
            byte[] it = new byte[n];
            InputArraysKt.readFully((Input) $this$readBytes, it, 0, n);
            return it;
        }
        return UnsafeKt.EmptyByteArray;
    }

    public static final byte[] readBytes(Input $this$readBytes, int n) {
        Intrinsics.checkNotNullParameter($this$readBytes, "<this>");
        return readBytesOf($this$readBytes, n, n);
    }

    public static final byte[] readBytes(Input $this$readBytes) {
        Intrinsics.checkNotNullParameter($this$readBytes, "<this>");
        return readBytesOf$default($this$readBytes, 0, 0, 3, null);
    }

    public static final byte[] readBytesOf(Input $this$readBytesOf, int min, int max) {
        Intrinsics.checkNotNullParameter($this$readBytesOf, "<this>");
        if (min == max && min == 0) {
            byte[] array = UnsafeKt.EmptyByteArray;
            return array;
        }
        if (min == max) {
            byte[] array2 = new byte[min];
            InputArraysKt.readFully($this$readBytesOf, array2, 0, min);
            return array2;
        }
        byte[] array3 = new byte[(int) RangesKt.coerceAtLeast(RangesKt.coerceAtMost(max, EncodingKt.sizeEstimate($this$readBytesOf)), min)];
        int size = 0;
        while (size < max) {
            int partSize = Math.min(max, array3.length) - size;
            int rc = InputArraysKt.readAvailable($this$readBytesOf, array3, size, partSize);
            if (rc <= 0) {
                break;
            }
            size += rc;
            if (array3.length == size) {
                byte[] copyOf = Arrays.copyOf(array3, size * 2);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                array3 = copyOf;
            }
        }
        if (size < min) {
            throw new EOFException("Not enough bytes available to read " + min + " bytes: " + (min - size) + " more required");
        }
        if (size != array3.length) {
            byte[] array4 = Arrays.copyOf(array3, size);
            Intrinsics.checkNotNullExpressionValue(array4, "copyOf(this, newSize)");
            return array4;
        }
        return array3;
    }

    public static /* synthetic */ byte[] readBytesOf$default(Input input, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return readBytesOf(input, i, i2);
    }

    public static /* synthetic */ int readText$default(Input input, Appendable appendable, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, appendable, charset, i);
    }

    public static final int readText(Input $this$readText, Appendable out, Charset charset, int max) {
        Intrinsics.checkNotNullParameter($this$readText, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(charset, "charset");
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "charset.newDecoder()");
        return CharsetJVMKt.decode(newDecoder, $this$readText, out, max);
    }

    public static /* synthetic */ String readText$default(Input input, CharsetDecoder charsetDecoder, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, charsetDecoder, i);
    }

    @Deprecated(message = "Use CharsetDecoder.decode instead", replaceWith = @ReplaceWith(expression = "decoder.decode(this, max)", imports = {"io.ktor.utils.io.charsets.decode"}))
    public static final String readText(Input $this$readText, CharsetDecoder decoder, int max) {
        Intrinsics.checkNotNullParameter($this$readText, "<this>");
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return EncodingKt.decode(decoder, $this$readText, max);
    }

    public static /* synthetic */ String readText$default(Input input, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, charset, i);
    }

    public static final String readText(Input $this$readText, Charset charset, int max) {
        Intrinsics.checkNotNullParameter($this$readText, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "charset.newDecoder()");
        return EncodingKt.decode(newDecoder, $this$readText, max);
    }

    public static final String readText(Buffer $this$readText, Charset charset, int max) {
        Intrinsics.checkNotNullParameter($this$readText, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        StringBuilder $this$readText_u24lambda_u247 = new StringBuilder();
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "charset.newDecoder()");
        CharsetJVMKt.decodeBuffer(newDecoder, $this$readText, $this$readText_u24lambda_u247, true, max);
        String sb = $this$readText_u24lambda_u247.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static /* synthetic */ String readText$default(Buffer buffer, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(buffer, charset, i);
    }

    public static /* synthetic */ String readTextExact$default(Input input, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExact(input, charset, i);
    }

    @Deprecated(message = "Use readTextExactCharacters instead.", replaceWith = @ReplaceWith(expression = "readTextExactCharacters(n, charset)", imports = {}))
    public static final String readTextExact(Input $this$readTextExact, Charset charset, int n) {
        Intrinsics.checkNotNullParameter($this$readTextExact, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return readTextExactCharacters($this$readTextExact, n, charset);
    }

    public static /* synthetic */ String readTextExactCharacters$default(Input input, int i, Charset charset, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExactCharacters(input, i, charset);
    }

    public static final String readTextExactCharacters(Input $this$readTextExactCharacters, int charactersCount, Charset charset) {
        Intrinsics.checkNotNullParameter($this$readTextExactCharacters, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String s = readText($this$readTextExactCharacters, charset, charactersCount);
        if (s.length() < charactersCount) {
            prematureEndOfStreamToReadChars(charactersCount);
            throw new KotlinNothingValueException();
        }
        return s;
    }

    public static /* synthetic */ String readTextExactBytes$default(Input input, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExactBytes(input, charset, i);
    }

    @Deprecated(message = "Parameters order is changed.", replaceWith = @ReplaceWith(expression = "readTextExactBytes(bytes, charset)", imports = {}))
    public static final String readTextExactBytes(Input $this$readTextExactBytes, Charset charset, int bytes) {
        Intrinsics.checkNotNullParameter($this$readTextExactBytes, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return readTextExactBytes($this$readTextExactBytes, bytes, charset);
    }

    public static /* synthetic */ String readTextExactBytes$default(Input input, int i, Charset charset, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExactBytes(input, i, charset);
    }

    public static final String readTextExactBytes(Input $this$readTextExactBytes, int bytesCount, Charset charset) {
        Intrinsics.checkNotNullParameter($this$readTextExactBytes, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "charset.newDecoder()");
        return CharsetJVMKt.decodeExactBytes(newDecoder, $this$readTextExactBytes, bytesCount);
    }

    public static /* synthetic */ void writeText$default(Output output, CharSequence charSequence, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(output, charSequence, i, i2, charset);
    }

    public static final void writeText(Output $this$writeText, CharSequence text, int fromIndex, int toIndex, Charset charset) {
        Intrinsics.checkNotNullParameter($this$writeText, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (charset == Charsets.UTF_8) {
            writeTextUtf8($this$writeText, text, fromIndex, toIndex);
            return;
        }
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
        EncodingKt.encodeToImpl(newEncoder, $this$writeText, text, fromIndex, toIndex);
    }

    public static /* synthetic */ void writeText$default(Output output, char[] cArr, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(output, cArr, i, i2, charset);
    }

    public static final void writeText(Output $this$writeText, char[] text, int fromIndex, int toIndex, Charset charset) {
        Intrinsics.checkNotNullParameter($this$writeText, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (charset == Charsets.UTF_8) {
            writeTextUtf8($this$writeText, new CharArraySequence(text, 0, text.length), fromIndex, toIndex);
            return;
        }
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
        EncodingKt.encode(newEncoder, text, fromIndex, toIndex, $this$writeText);
    }

    private static final void writeTextUtf8(Output $this$writeTextUtf8, CharSequence text, int fromIndex, int toIndex) {
        int i;
        ChunkBuffer tail$iv = UnsafeKt.prepareWriteHead($this$writeTextUtf8, 1, null);
        ChunkBuffer tail$iv2 = tail$iv;
        int index = fromIndex;
        while (true) {
            try {
                Buffer buffer = tail$iv2;
                ByteBuffer memory = buffer.getMemory();
                int dstOffset = buffer.getWritePosition();
                int dstLimit = buffer.getLimit();
                try {
                    int m497encodeUTF8lBXzO7A = UTF8Kt.m497encodeUTF8lBXzO7A(memory, text, index, toIndex, dstOffset, dstLimit);
                    short characters = EncodeResult.m486component1Mh2AYeg(m497encodeUTF8lBXzO7A);
                    short bytes = EncodeResult.m487component2Mh2AYeg(m497encodeUTF8lBXzO7A);
                    index += characters & UShort.MAX_VALUE;
                    buffer.commitWritten(bytes & UShort.MAX_VALUE);
                    if ((65535 & characters) != 0 || index >= toIndex) {
                        i = index < toIndex ? 1 : 0;
                    } else {
                        i = 8;
                    }
                    int size$iv = i;
                    if (size$iv > 0) {
                        tail$iv2 = UnsafeKt.prepareWriteHead($this$writeTextUtf8, size$iv, tail$iv2);
                    } else {
                        $this$writeTextUtf8.afterHeadWrite();
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    $this$writeTextUtf8.afterHeadWrite();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private static final boolean isAsciiChar(char $this$isAsciiChar) {
        return $this$isAsciiChar <= 127;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a3, code lost:
    
        r7 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a7, code lost:
    
        r0.discardExact(r23 - r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00aa, code lost:
    
        r9 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x012a, code lost:
    
        io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r29, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0113, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0114, code lost:
    
        r9 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0095, code lost:
    
        bufferLimitExceeded(r31);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x009d, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x012a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int readUTFUntilDelimiterToSlowAscii(io.ktor.utils.io.core.Input r29, java.lang.String r30, int r31, io.ktor.utils.io.core.Output r32) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.StringsKt.readUTFUntilDelimiterToSlowAscii(io.ktor.utils.io.core.Input, java.lang.String, int, io.ktor.utils.io.core.Output):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x019a, code lost:
    
        r0.discardExact(((r36 - r23) - r19) + 1);
        r6 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0283, code lost:
    
        if (r4 == false) goto L315;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0285, code lost:
    
        io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r37, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0288, code lost:
    
        r0 = r2;
        r2 = r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x02ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int readUTF8UntilDelimiterToSlowUtf8(io.ktor.utils.io.core.Input r37, io.ktor.utils.io.core.Output r38, java.lang.String r39, int r40, int r41) {
        /*
            Method dump skipped, instructions count: 688
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.StringsKt.readUTF8UntilDelimiterToSlowUtf8(io.ktor.utils.io.core.Input, io.ktor.utils.io.core.Output, java.lang.String, int, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:129:0x019a, code lost:
    
        r0.discardExact(((r9 - r23) - r19) + 1);
        r6 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0261, code lost:
    
        if (r8 == false) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0263, code lost:
    
        io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r37, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0266, code lost:
    
        r0 = r3;
        r3 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0288, code lost:
    
        io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r37, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01e8, code lost:
    
        if (r6 != (-1)) goto L264;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01ea, code lost:
    
        r0 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01f3, code lost:
    
        r7 = r0;
        r11 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01f6, code lost:
    
        r0 = r10;
        r6 = r0.getWritePosition() - r0.getReadPosition();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0224, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0225, code lost:
    
        r8 = r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01f2, code lost:
    
        r0 = kotlin.ranges.RangesKt.coerceAtLeast(r6, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0205, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0288  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int readUTF8UntilDelimiterToSlowUtf8(io.ktor.utils.io.core.Input r37, java.lang.Appendable r38, java.lang.String r39, int r40, int r41) {
        /*
            Method dump skipped, instructions count: 652
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.StringsKt.readUTF8UntilDelimiterToSlowUtf8(io.ktor.utils.io.core.Input, java.lang.Appendable, java.lang.String, int, int):int");
    }

    private static final Void bufferLimitExceeded(int limit) {
        throw new BufferLimitExceededException("Too many characters before delimiter: limit " + limit + " exceeded");
    }

    public static final Void prematureEndOfStream(int size) {
        throw new EOFException("Premature end of stream: expected " + size + " bytes");
    }

    public static final Void prematureEndOfStream(long size) {
        throw new EOFException("Premature end of stream: expected " + size + " bytes");
    }

    private static final Void prematureEndOfStreamToReadChars(int charactersCount) {
        throw new EOFException("Not enough input bytes to read " + charactersCount + " characters.");
    }
}
