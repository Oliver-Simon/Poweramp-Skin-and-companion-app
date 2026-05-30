package io.ktor.utils.io.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.charsets.UTFKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Strings.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0000\u001a$\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a9\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0082\b\u001a$\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a9\u0010\r\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0082\b\u001a(\u0010\u000e\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0000\u001a$\u0010\u000f\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a$\u0010\u0010\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¨\u0006\u0011"}, d2 = {"decodeASCII", "", "Ljava/nio/ByteBuffer;", "out", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "decodeASCII3_array", "", "predicate", "Lkotlin/Function1;", "", "", "decodeASCII3_buffer", "decodeASCIILine", "decodeASCIILine_array", "decodeASCIILine_buffer", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class StringsKt {
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

    public static /* synthetic */ long decodeASCIILine$default(ByteBuffer byteBuffer, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return decodeASCIILine(byteBuffer, cArr, i, i2);
    }

    public static final long decodeASCIILine(ByteBuffer $this$decodeASCIILine, char[] out, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$decodeASCIILine, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        return $this$decodeASCIILine.hasArray() ? decodeASCIILine_array($this$decodeASCIILine, out, offset, length) : decodeASCIILine_buffer($this$decodeASCIILine, out, offset, length);
    }

    private static final long decodeASCIILine_array(ByteBuffer $this$decodeASCIILine_array, char[] out, int offset, int length) {
        long rc;
        boolean z;
        boolean cr = false;
        int pos$iv = offset;
        int end$iv = offset + length;
        byte[] array$iv = $this$decodeASCIILine_array.array();
        int srcPos$iv = $this$decodeASCIILine_array.arrayOffset() + $this$decodeASCIILine_array.position();
        int srcEnd$iv = $this$decodeASCIILine_array.remaining() + srcPos$iv;
        char c = '\r';
        if (end$iv <= out.length && srcEnd$iv <= array$iv.length) {
            while (srcPos$iv < srcEnd$iv) {
                byte b$iv = array$iv[srcPos$iv];
                if (b$iv < 0) {
                    break;
                }
                char ch$iv = (char) b$iv;
                if (ch$iv != c) {
                    if (ch$iv == '\n') {
                        cr = false;
                        z = false;
                    } else if (cr) {
                        z = false;
                    } else {
                        z = true;
                    }
                } else {
                    cr = true;
                    z = true;
                }
                if (!z) {
                    $this$decodeASCIILine_array.position(srcPos$iv - $this$decodeASCIILine_array.arrayOffset());
                    rc = UTFKt.decodeUtf8Result(pos$iv - offset, -1);
                    break;
                }
                if (pos$iv >= end$iv) {
                    break;
                }
                out[pos$iv] = ch$iv;
                pos$iv++;
                srcPos$iv++;
                c = '\r';
            }
            $this$decodeASCIILine_array.position(srcPos$iv - $this$decodeASCIILine_array.arrayOffset());
        }
        rc = UTFKt.decodeUtf8Result(pos$iv - offset, 0);
        int required = (int) (4294967295L & rc);
        if (required == -1) {
            int decoded = (int) (rc >> 32);
            if (!cr) {
                $this$decodeASCIILine_array.position($this$decodeASCIILine_array.position() + 1);
                if (decoded > 0 && out[decoded - 1] == '\r') {
                    return UTFKt.decodeUtf8Result(decoded - 1, -1);
                }
            } else {
                return UTFKt.decodeUtf8Result(decoded - 1, -1);
            }
        } else if (cr) {
            $this$decodeASCIILine_array.position($this$decodeASCIILine_array.position() - 1);
            return UTFKt.decodeUtf8Result(((int) (rc >> 32)) - 1, 2);
        }
        return rc;
    }

    private static final long decodeASCIILine_buffer(ByteBuffer $this$decodeASCIILine_buffer, char[] out, int offset, int length) {
        boolean z;
        boolean cr = false;
        int pos$iv = offset;
        int end$iv = offset + length;
        boolean pushBack$iv = false;
        boolean predicateFailed$iv = false;
        if (end$iv <= out.length) {
            while (true) {
                if (!$this$decodeASCIILine_buffer.hasRemaining()) {
                    break;
                }
                byte b$iv = $this$decodeASCIILine_buffer.get();
                if (b$iv < 0) {
                    pushBack$iv = true;
                    break;
                }
                char ch$iv = (char) b$iv;
                if (ch$iv == '\r') {
                    cr = true;
                    z = true;
                } else if (ch$iv == '\n') {
                    cr = false;
                    z = false;
                } else if (cr) {
                    z = false;
                } else {
                    z = true;
                }
                if (!z) {
                    pushBack$iv = true;
                    predicateFailed$iv = true;
                    break;
                }
                if (pos$iv >= end$iv) {
                    pushBack$iv = true;
                    break;
                }
                out[pos$iv] = ch$iv;
                pos$iv++;
            }
        }
        if (pushBack$iv) {
            $this$decodeASCIILine_buffer.position($this$decodeASCIILine_buffer.position() - 1);
        }
        long rc = UTFKt.decodeUtf8Result(pos$iv - offset, predicateFailed$iv ? -1 : 0);
        int required = (int) (4294967295L & rc);
        if (required == -1) {
            int decoded = (int) (rc >> 32);
            if (!cr) {
                $this$decodeASCIILine_buffer.position($this$decodeASCIILine_buffer.position() + 1);
                if (decoded > 0 && out[decoded - 1] == '\r') {
                    return UTFKt.decodeUtf8Result(decoded - 1, -1);
                }
            } else {
                return UTFKt.decodeUtf8Result(decoded - 1, -1);
            }
        } else if (cr) {
            $this$decodeASCIILine_buffer.position($this$decodeASCIILine_buffer.position() - 1);
            return UTFKt.decodeUtf8Result(((int) (rc >> 32)) - 1, 2);
        }
        return rc;
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

    private static final long decodeASCII3_array(ByteBuffer $this$decodeASCII3_array, char[] out, int offset, int length, Function1<? super Character, Boolean> function1) {
        int pos = offset;
        int end = offset + length;
        byte[] array = $this$decodeASCII3_array.array();
        int srcPos = $this$decodeASCII3_array.arrayOffset() + $this$decodeASCII3_array.position();
        int srcEnd = $this$decodeASCII3_array.remaining() + srcPos;
        if (end <= out.length && srcEnd <= array.length) {
            while (srcPos < srcEnd) {
                byte b = array[srcPos];
                if (b < 0) {
                    break;
                }
                char ch = (char) b;
                if (!function1.invoke(Character.valueOf(ch)).booleanValue()) {
                    $this$decodeASCII3_array.position(srcPos - $this$decodeASCII3_array.arrayOffset());
                    return UTFKt.decodeUtf8Result(pos - offset, -1);
                }
                if (pos >= end) {
                    break;
                }
                out[pos] = ch;
                pos++;
                srcPos++;
            }
            $this$decodeASCII3_array.position(srcPos - $this$decodeASCII3_array.arrayOffset());
        }
        return UTFKt.decodeUtf8Result(pos - offset, 0);
    }

    private static final long decodeASCII3_buffer(ByteBuffer $this$decodeASCII3_buffer, char[] out, int offset, int length, Function1<? super Character, Boolean> function1) {
        int pos = offset;
        int end = offset + length;
        boolean pushBack = false;
        boolean predicateFailed = false;
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
                char ch = (char) b;
                if (!function1.invoke(Character.valueOf(ch)).booleanValue()) {
                    pushBack = true;
                    predicateFailed = true;
                    break;
                }
                if (pos >= end) {
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
        return UTFKt.decodeUtf8Result(pos - offset, predicateFailed ? -1 : 0);
    }
}
