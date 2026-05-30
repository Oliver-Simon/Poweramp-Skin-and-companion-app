package io.ktor.utils.io.charsets;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Strings.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0000\u001a@\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0080\bø\u0001\u0000\u001a$\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a9\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0082\b\u001a$\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a9\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0082\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"decodeASCII", "", "Ljava/nio/ByteBuffer;", "out", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "predicate", "Lkotlin/Function1;", "", "", "decodeASCII3_array", "decodeASCII3_buffer", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class StringsKt {
    public static /* synthetic */ int decodeASCII$default(ByteBuffer $this$decodeASCII_u24default, char[] out, int offset, int length, Function1 predicate, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = out.length;
        }
        Intrinsics.checkNotNullParameter($this$decodeASCII_u24default, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if ($this$decodeASCII_u24default.hasArray()) {
            int pos$iv = offset;
            int end$iv = offset + length;
            byte[] array$iv = $this$decodeASCII_u24default.array();
            int srcPos$iv = $this$decodeASCII_u24default.arrayOffset() + $this$decodeASCII_u24default.position();
            int srcEnd$iv = $this$decodeASCII_u24default.remaining() + srcPos$iv;
            if (end$iv <= out.length && srcEnd$iv <= array$iv.length) {
                while (true) {
                    if (srcPos$iv >= srcEnd$iv || pos$iv >= end$iv) {
                        break;
                    }
                    byte b$iv = array$iv[srcPos$iv];
                    if (b$iv < 0) {
                        break;
                    }
                    char ch$iv = (char) b$iv;
                    if (!((Boolean) predicate.invoke(Character.valueOf(ch$iv))).booleanValue()) {
                        srcPos$iv--;
                        break;
                    }
                    out[pos$iv] = ch$iv;
                    pos$iv++;
                    srcPos$iv++;
                }
                $this$decodeASCII_u24default.position(srcPos$iv - $this$decodeASCII_u24default.arrayOffset());
            }
            return pos$iv - offset;
        }
        int pos$iv2 = offset;
        int end$iv2 = offset + length;
        boolean pushBack$iv = false;
        if (end$iv2 <= out.length) {
            while (true) {
                if (!$this$decodeASCII_u24default.hasRemaining()) {
                    break;
                }
                byte b$iv2 = $this$decodeASCII_u24default.get();
                if (b$iv2 < 0) {
                    pushBack$iv = true;
                    break;
                }
                if (pos$iv2 >= end$iv2) {
                    pushBack$iv = true;
                    break;
                }
                char ch$iv2 = (char) b$iv2;
                if (!((Boolean) predicate.invoke(Character.valueOf(ch$iv2))).booleanValue()) {
                    pushBack$iv = true;
                    break;
                }
                out[pos$iv2] = ch$iv2;
                pos$iv2++;
            }
        }
        if (pushBack$iv) {
            $this$decodeASCII_u24default.position($this$decodeASCII_u24default.position() - 1);
        }
        return pos$iv2 - offset;
    }

    public static final int decodeASCII(ByteBuffer $this$decodeASCII, char[] out, int offset, int length, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter($this$decodeASCII, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if ($this$decodeASCII.hasArray()) {
            int pos$iv = offset;
            int end$iv = offset + length;
            byte[] array$iv = $this$decodeASCII.array();
            int srcPos$iv = $this$decodeASCII.arrayOffset() + $this$decodeASCII.position();
            int srcEnd$iv = $this$decodeASCII.remaining() + srcPos$iv;
            if (end$iv <= out.length && srcEnd$iv <= array$iv.length) {
                while (true) {
                    if (srcPos$iv >= srcEnd$iv || pos$iv >= end$iv) {
                        break;
                    }
                    byte b$iv = array$iv[srcPos$iv];
                    if (b$iv < 0) {
                        break;
                    }
                    char ch$iv = (char) b$iv;
                    if (!predicate.invoke(Character.valueOf(ch$iv)).booleanValue()) {
                        srcPos$iv--;
                        break;
                    }
                    out[pos$iv] = ch$iv;
                    pos$iv++;
                    srcPos$iv++;
                }
                $this$decodeASCII.position(srcPos$iv - $this$decodeASCII.arrayOffset());
            }
            return pos$iv - offset;
        }
        int pos$iv2 = offset;
        int end$iv2 = offset + length;
        boolean pushBack$iv = false;
        if (end$iv2 <= out.length) {
            while (true) {
                if (!$this$decodeASCII.hasRemaining()) {
                    break;
                }
                byte b$iv2 = $this$decodeASCII.get();
                if (b$iv2 < 0) {
                    pushBack$iv = true;
                    break;
                }
                if (pos$iv2 >= end$iv2) {
                    pushBack$iv = true;
                    break;
                }
                char ch$iv2 = (char) b$iv2;
                if (!predicate.invoke(Character.valueOf(ch$iv2)).booleanValue()) {
                    pushBack$iv = true;
                    break;
                }
                out[pos$iv2] = ch$iv2;
                pos$iv2++;
            }
        }
        if (pushBack$iv) {
            $this$decodeASCII.position($this$decodeASCII.position() - 1);
        }
        return pos$iv2 - offset;
    }

    public static /* synthetic */ int decodeASCII$default(ByteBuffer byteBuffer, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return decodeASCII(byteBuffer, cArr, i, i2);
    }

    public static final int decodeASCII(ByteBuffer $this$decodeASCII, char[] out, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$decodeASCII, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        if ($this$decodeASCII.hasArray()) {
            return decodeASCII3_array($this$decodeASCII, out, offset, length);
        }
        return decodeASCII3_buffer($this$decodeASCII, out, offset, length);
    }

    private static final int decodeASCII3_array(ByteBuffer $this$decodeASCII3_array, char[] out, int offset, int length) {
        int pos = offset;
        int end = offset + length;
        byte[] array = $this$decodeASCII3_array.array();
        int srcPos = $this$decodeASCII3_array.arrayOffset() + $this$decodeASCII3_array.position();
        int srcEnd = $this$decodeASCII3_array.remaining() + srcPos;
        if (end <= out.length && srcEnd <= array.length) {
            while (srcPos < srcEnd && pos < end) {
                byte b = array[srcPos];
                if (b < 0) {
                    break;
                }
                out[pos] = (char) b;
                pos++;
                srcPos++;
            }
            $this$decodeASCII3_array.position(srcPos - $this$decodeASCII3_array.arrayOffset());
        }
        return pos - offset;
    }

    private static final int decodeASCII3_buffer(ByteBuffer $this$decodeASCII3_buffer, char[] out, int offset, int length) {
        int pos = offset;
        int end = offset + length;
        boolean pushBack = false;
        if (end <= out.length) {
            while (true) {
                if (!$this$decodeASCII3_buffer.hasRemaining()) {
                    break;
                }
                byte b = $this$decodeASCII3_buffer.get();
                if (b < 0) {
                    pushBack = true;
                    break;
                }
                if (pos >= end) {
                    pushBack = true;
                    break;
                }
                out[pos] = (char) b;
                pos++;
            }
        }
        if (pushBack) {
            $this$decodeASCII3_buffer.position($this$decodeASCII3_buffer.position() - 1);
        }
        return pos - offset;
    }

    private static final int decodeASCII3_array(ByteBuffer $this$decodeASCII3_array, char[] out, int offset, int length, Function1<? super Character, Boolean> function1) {
        int pos = offset;
        int end = offset + length;
        byte[] array = $this$decodeASCII3_array.array();
        int srcPos = $this$decodeASCII3_array.arrayOffset() + $this$decodeASCII3_array.position();
        int srcEnd = $this$decodeASCII3_array.remaining() + srcPos;
        if (end <= out.length && srcEnd <= array.length) {
            while (true) {
                if (srcPos >= srcEnd || pos >= end) {
                    break;
                }
                byte b = array[srcPos];
                if (b < 0) {
                    break;
                }
                char ch = (char) b;
                if (!function1.invoke(Character.valueOf(ch)).booleanValue()) {
                    srcPos--;
                    break;
                }
                out[pos] = ch;
                pos++;
                srcPos++;
            }
            $this$decodeASCII3_array.position(srcPos - $this$decodeASCII3_array.arrayOffset());
        }
        return pos - offset;
    }

    private static final int decodeASCII3_buffer(ByteBuffer $this$decodeASCII3_buffer, char[] out, int offset, int length, Function1<? super Character, Boolean> function1) {
        int pos = offset;
        int end = offset + length;
        boolean pushBack = false;
        if (end <= out.length) {
            while (true) {
                if (!$this$decodeASCII3_buffer.hasRemaining()) {
                    break;
                }
                byte b = $this$decodeASCII3_buffer.get();
                if (b < 0) {
                    pushBack = true;
                    break;
                }
                if (pos >= end) {
                    pushBack = true;
                    break;
                }
                char ch = (char) b;
                if (!function1.invoke(Character.valueOf(ch)).booleanValue()) {
                    pushBack = true;
                    break;
                }
                out[pos] = ch;
                pos++;
            }
        }
        if (pushBack) {
            $this$decodeASCII3_buffer.position($this$decodeASCII3_buffer.position() - 1);
        }
        return pos - offset;
    }
}
