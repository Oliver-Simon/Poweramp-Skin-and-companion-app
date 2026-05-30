package io.ktor.utils.io.charsets;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* compiled from: UTF.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0007H\u0000\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001H\u0002\u001a \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u001a\"\u0010\u001f\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001\u001a&\u0010#\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u0001\u001a$\u0010$\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a$\u0010%\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a$\u0010&\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a9\u0010&\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00150(H\u0082\b\u001a$\u0010*\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a9\u0010*\u001a\u00020\u0007*\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00150(H\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"HighSurrogateMagic", "", "MaxCodePoint", "MinHighSurrogate", "MinLowSurrogate", "MinSupplementary", "decodeUtf8Result", "", "numberOfChars", "requireBytes", "decodeUtf8ResultAcc", "preDecoded", "result", "highSurrogate", "cp", "indexOutOfBounds", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "arrayLength", "isBmpCodePoint", "", "isValidCodePoint", "codePoint", "lowSurrogate", "malformedCodePoint", "", "value", "unsupportedByteCount", "b", "", "decodeUTF", "Ljava/nio/ByteBuffer;", "out", "", "decodeUTF8Line", "decodeUTF8Line_array", "decodeUTF8Line_buffer", "decodeUTF8_array", "predicate", "Lkotlin/Function1;", "", "decodeUTF8_buffer", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UTFKt {
    private static final int HighSurrogateMagic = 55232;
    private static final int MaxCodePoint = 1114111;
    private static final int MinHighSurrogate = 55296;
    private static final int MinLowSurrogate = 56320;
    private static final int MinSupplementary = 65536;

    public static final long decodeUtf8Result(int numberOfChars, int requireBytes) {
        return (numberOfChars << 32) | (requireBytes & 4294967295L);
    }

    public static final long decodeUtf8ResultAcc(int preDecoded, long result) {
        return decodeUtf8Result(((int) (result >> 32)) + preDecoded, (int) (4294967295L & result));
    }

    public static final long decodeUTF(ByteBuffer $this$decodeUTF, char[] out, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$decodeUTF, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        int decoded = StringsKt.decodeASCII($this$decodeUTF, out, offset, length);
        if (!$this$decodeUTF.hasRemaining() || decoded == length) {
            return decodeUtf8Result(decoded, 0);
        }
        return $this$decodeUTF.hasArray() ? decodeUtf8ResultAcc(decoded, decodeUTF8_array($this$decodeUTF, out, offset + decoded, length - decoded)) : decodeUtf8ResultAcc(decoded, decodeUTF8_buffer($this$decodeUTF, out, offset + decoded, length - decoded));
    }

    public static /* synthetic */ long decodeUTF8Line$default(ByteBuffer byteBuffer, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return decodeUTF8Line(byteBuffer, cArr, i, i2);
    }

    public static final long decodeUTF8Line(ByteBuffer $this$decodeUTF8Line, char[] out, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$decodeUTF8Line, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        return $this$decodeUTF8Line.hasArray() ? decodeUTF8Line_array($this$decodeUTF8Line, out, offset, length) : decodeUTF8Line_buffer($this$decodeUTF8Line, out, offset, length);
    }

    private static final long decodeUTF8Line_array(ByteBuffer $this$decodeUTF8Line_array, char[] out, int offset, int length) {
        long rc;
        boolean z;
        boolean z2;
        boolean cr;
        boolean z3;
        boolean cr2;
        boolean cr3;
        boolean cr4;
        boolean cr5;
        boolean z4;
        boolean cr6;
        boolean cr7 = false;
        int $i$f$decodeUTF8_array = 0;
        byte[] array$iv = $this$decodeUTF8Line_array.array();
        int srcPos$iv = $this$decodeUTF8Line_array.arrayOffset() + $this$decodeUTF8Line_array.position();
        int srcEnd$iv = $this$decodeUTF8Line_array.remaining() + srcPos$iv;
        if (!(srcPos$iv <= srcEnd$iv)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(srcEnd$iv <= array$iv.length)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int outPos$iv = offset;
        int outEnd$iv = offset + length;
        if (outEnd$iv > out.length) {
            int $i$f$decodeUTF8_array2 = out.length;
            throw indexOutOfBounds(offset, length, $i$f$decodeUTF8_array2);
        }
        while (srcPos$iv < srcEnd$iv && outPos$iv < outEnd$iv) {
            int srcPos$iv2 = srcPos$iv + 1;
            byte v$iv = array$iv[srcPos$iv];
            if (v$iv >= 0) {
                char ch$iv = (char) v$iv;
                if (ch$iv == '\r') {
                    cr7 = true;
                    z = true;
                } else if (ch$iv == '\n') {
                    cr7 = false;
                    z = false;
                } else {
                    z = !cr7;
                }
                if (!z) {
                    $this$decodeUTF8Line_array.position((srcPos$iv2 - 1) - $this$decodeUTF8Line_array.arrayOffset());
                    rc = decodeUtf8Result(outPos$iv - offset, -1);
                    break;
                }
                out[outPos$iv] = ch$iv;
                srcPos$iv = srcPos$iv2;
                outPos$iv++;
            } else if ((v$iv & 224) != 192) {
                boolean cr8 = cr7;
                int $i$f$decodeUTF8_array3 = $i$f$decodeUTF8_array;
                if ((v$iv & 240) != 224) {
                    int outPos$iv2 = v$iv & 248;
                    if (outPos$iv2 != 240) {
                        unsupportedByteCount(v$iv);
                        throw new KotlinNothingValueException();
                    }
                    if (srcEnd$iv - srcPos$iv2 < 3) {
                        $this$decodeUTF8Line_array.position((srcPos$iv2 - 1) - $this$decodeUTF8Line_array.arrayOffset());
                        rc = decodeUtf8Result(outPos$iv - offset, 4);
                        cr7 = cr8;
                        break;
                    }
                    int srcPos$iv3 = srcPos$iv2 + 1;
                    int second$iv = array$iv[srcPos$iv2];
                    int srcPos$iv4 = srcPos$iv3 + 1;
                    int third$iv = array$iv[srcPos$iv3];
                    int srcPos$iv5 = srcPos$iv4 + 1;
                    int fourth$iv = array$iv[srcPos$iv4];
                    int vv$iv = ((v$iv & 7) << 18) | ((second$iv & 63) << 12) | ((third$iv & 63) << 6) | (fourth$iv & 63);
                    if (!isValidCodePoint(vv$iv)) {
                        malformedCodePoint(vv$iv);
                        throw new KotlinNothingValueException();
                    }
                    int third$iv2 = outEnd$iv - outPos$iv;
                    if (third$iv2 < 2) {
                        $this$decodeUTF8Line_array.position((srcPos$iv5 - 4) - $this$decodeUTF8Line_array.arrayOffset());
                        cr7 = cr8;
                        rc = decodeUtf8Result(outPos$iv - offset, 0);
                        break;
                    }
                    char ch = (char) highSurrogate(vv$iv);
                    char low$iv = (char) lowSurrogate(vv$iv);
                    if (ch == '\r') {
                        cr3 = true;
                        cr4 = true;
                    } else if (ch == '\n') {
                        cr3 = false;
                        cr4 = false;
                    } else if (cr8) {
                        cr3 = cr8;
                        cr4 = false;
                    } else {
                        cr3 = cr8;
                        cr4 = true;
                    }
                    if (cr4) {
                        boolean cr9 = cr3;
                        if (low$iv == '\r') {
                            cr6 = true;
                            z4 = true;
                        } else if (low$iv == '\n') {
                            cr6 = false;
                            z4 = false;
                        } else if (cr9) {
                            cr6 = cr9;
                            z4 = false;
                        } else {
                            z4 = true;
                            cr6 = cr9;
                        }
                        if (z4) {
                            int outPos$iv3 = outPos$iv + 1;
                            out[outPos$iv] = ch;
                            outPos$iv = outPos$iv3 + 1;
                            out[outPos$iv3] = low$iv;
                            cr7 = cr6;
                            srcPos$iv = srcPos$iv5;
                            $i$f$decodeUTF8_array = $i$f$decodeUTF8_array3;
                        } else {
                            cr5 = cr6;
                        }
                    } else {
                        cr5 = cr3;
                    }
                    $this$decodeUTF8Line_array.position((srcPos$iv5 - 4) - $this$decodeUTF8Line_array.arrayOffset());
                    cr7 = cr5;
                    rc = decodeUtf8Result(outPos$iv - offset, -1);
                    break;
                }
                if (srcEnd$iv - srcPos$iv2 < 2) {
                    $this$decodeUTF8Line_array.position((srcPos$iv2 - 1) - $this$decodeUTF8Line_array.arrayOffset());
                    rc = decodeUtf8Result(outPos$iv - offset, 3);
                    cr7 = cr8;
                    break;
                }
                int srcPos$iv6 = srcPos$iv2 + 1;
                int second$iv2 = array$iv[srcPos$iv2];
                int srcPos$iv7 = srcPos$iv6 + 1;
                int third$iv3 = array$iv[srcPos$iv6];
                int highest$iv = v$iv & Ascii.SI;
                int vv$iv2 = (highest$iv << 12) | ((second$iv2 & 63) << 6) | (third$iv3 & 63);
                if (highest$iv != 0 && !isBmpCodePoint(vv$iv2)) {
                    malformedCodePoint(vv$iv2);
                    throw new KotlinNothingValueException();
                }
                char ch2 = (char) vv$iv2;
                if (ch2 == '\r') {
                    cr2 = true;
                    z3 = true;
                } else if (ch2 == '\n') {
                    cr2 = false;
                    z3 = false;
                } else if (cr8) {
                    cr2 = cr8;
                    z3 = false;
                } else {
                    z3 = true;
                    cr2 = cr8;
                }
                if (!z3) {
                    $this$decodeUTF8Line_array.position((srcPos$iv7 - 4) - $this$decodeUTF8Line_array.arrayOffset());
                    cr7 = cr2;
                    rc = decodeUtf8Result(outPos$iv - offset, -1);
                    break;
                }
                boolean cr10 = cr2;
                out[outPos$iv] = ch2;
                outPos$iv++;
                srcPos$iv = srcPos$iv7;
                $i$f$decodeUTF8_array = $i$f$decodeUTF8_array3;
                cr7 = cr10;
            } else {
                if (srcPos$iv2 >= srcEnd$iv) {
                    $this$decodeUTF8Line_array.position((srcPos$iv2 - 1) - $this$decodeUTF8Line_array.arrayOffset());
                    rc = decodeUtf8Result(outPos$iv - offset, 2);
                    break;
                }
                int srcPos$iv8 = srcPos$iv2 + 1;
                int second$iv3 = array$iv[srcPos$iv2];
                char ch$iv2 = (char) (((v$iv & Ascii.US) << 6) | (second$iv3 & 63));
                boolean cr11 = cr7;
                int ch3 = $i$f$decodeUTF8_array;
                if (ch$iv2 == '\r') {
                    cr = true;
                    z2 = true;
                } else if (ch$iv2 == '\n') {
                    cr = false;
                    z2 = false;
                } else if (cr11) {
                    cr = cr11;
                    z2 = false;
                } else {
                    z2 = true;
                    cr = cr11;
                }
                if (!z2) {
                    $this$decodeUTF8Line_array.position((srcPos$iv8 - 2) - $this$decodeUTF8Line_array.arrayOffset());
                    cr7 = cr;
                    rc = decodeUtf8Result(outPos$iv - offset, -1);
                    break;
                }
                boolean cr12 = cr;
                out[outPos$iv] = ch$iv2;
                outPos$iv++;
                srcPos$iv = srcPos$iv8;
                $i$f$decodeUTF8_array = ch3;
                cr7 = cr12;
            }
        }
        $this$decodeUTF8Line_array.position(srcPos$iv - $this$decodeUTF8Line_array.arrayOffset());
        rc = decodeUtf8Result(outPos$iv - offset, 0);
        cr7 = cr7;
        int required = (int) (rc & 4294967295L);
        if (required == -1) {
            int decoded = (int) (rc >> 32);
            if (cr7) {
                return decodeUtf8Result(decoded - 1, -1);
            }
            $this$decodeUTF8Line_array.position($this$decodeUTF8Line_array.position() + 1);
            if (decoded > 0 && out[decoded - 1] == '\r') {
                return decodeUtf8Result(decoded - 1, -1);
            }
        } else if (required == 0 && cr7) {
            $this$decodeUTF8Line_array.position($this$decodeUTF8Line_array.position() - 1);
            return decodeUtf8Result(((int) (rc >> 32)) - 1, 2);
        }
        return rc;
    }

    private static final long decodeUTF8Line_buffer(ByteBuffer $this$decodeUTF8Line_buffer, char[] out, int offset, int length) {
        long rc;
        boolean z;
        boolean z2;
        boolean cr;
        boolean cr2;
        boolean cr3;
        boolean cr4;
        boolean z3;
        boolean cr5;
        boolean cr6 = false;
        int $i$f$decodeUTF8_buffer = 0;
        int outPos$iv = offset;
        int outEnd$iv = offset + length;
        if (outEnd$iv > out.length) {
            int $i$f$decodeUTF8_buffer2 = out.length;
            throw indexOutOfBounds(offset, length, $i$f$decodeUTF8_buffer2);
        }
        while ($this$decodeUTF8Line_buffer.hasRemaining() && outPos$iv < outEnd$iv) {
            byte v$iv = $this$decodeUTF8Line_buffer.get();
            if (v$iv >= 0) {
                char ch$iv = (char) v$iv;
                if (ch$iv == '\r') {
                    cr6 = true;
                    z = true;
                } else if (ch$iv == '\n') {
                    cr6 = false;
                    z = false;
                } else {
                    z = !cr6;
                }
                if (!z) {
                    $this$decodeUTF8Line_buffer.position($this$decodeUTF8Line_buffer.position() - 1);
                    rc = decodeUtf8Result(outPos$iv - offset, -1);
                    break;
                }
                out[outPos$iv] = ch$iv;
                outPos$iv++;
            } else {
                int outPos$iv2 = v$iv & 224;
                if (outPos$iv2 == 192) {
                    if (!$this$decodeUTF8Line_buffer.hasRemaining()) {
                        $this$decodeUTF8Line_buffer.position($this$decodeUTF8Line_buffer.position() - 1);
                        rc = decodeUtf8Result(outPos$iv - offset, 2);
                        break;
                    }
                    int second$iv = $this$decodeUTF8Line_buffer.get();
                    char ch$iv2 = (char) (((v$iv & Ascii.US) << 6) | (second$iv & 63));
                    if (ch$iv2 == '\r') {
                        cr6 = true;
                        z2 = true;
                    } else if (ch$iv2 == '\n') {
                        cr6 = false;
                        z2 = false;
                    } else {
                        z2 = !cr6;
                    }
                    if (!z2) {
                        $this$decodeUTF8Line_buffer.position($this$decodeUTF8Line_buffer.position() - 2);
                        rc = decodeUtf8Result(outPos$iv - offset, -1);
                        break;
                    }
                    out[outPos$iv] = ch$iv2;
                    outPos$iv++;
                } else {
                    if ((v$iv & 240) != 224) {
                        boolean cr7 = cr6;
                        if ((v$iv & 248) != 240) {
                            unsupportedByteCount(v$iv);
                            throw new KotlinNothingValueException();
                        }
                        if ($this$decodeUTF8Line_buffer.remaining() < 3) {
                            $this$decodeUTF8Line_buffer.position($this$decodeUTF8Line_buffer.position() - 1);
                            rc = decodeUtf8Result(outPos$iv - offset, 4);
                            cr6 = cr7;
                            break;
                        }
                        int second$iv2 = $this$decodeUTF8Line_buffer.get();
                        int third$iv = $this$decodeUTF8Line_buffer.get();
                        int fourth$iv = $this$decodeUTF8Line_buffer.get();
                        int vv$iv = ((v$iv & 7) << 18) | ((second$iv2 & 63) << 12) | ((third$iv & 63) << 6) | (fourth$iv & 63);
                        if (!isValidCodePoint(vv$iv)) {
                            malformedCodePoint(vv$iv);
                            throw new KotlinNothingValueException();
                        }
                        if (outEnd$iv - outPos$iv < 2) {
                            int second$iv3 = $this$decodeUTF8Line_buffer.position();
                            $this$decodeUTF8Line_buffer.position(second$iv3 - 4);
                            rc = decodeUtf8Result(outPos$iv - offset, 0);
                            cr6 = cr7;
                            break;
                        }
                        char high$iv = (char) highSurrogate(vv$iv);
                        char low$iv = (char) lowSurrogate(vv$iv);
                        int ch = $i$f$decodeUTF8_buffer;
                        if (high$iv == '\r') {
                            cr2 = true;
                            cr3 = true;
                        } else if (high$iv == '\n') {
                            cr2 = false;
                            cr3 = false;
                        } else if (cr7) {
                            cr2 = cr7;
                            cr3 = false;
                        } else {
                            cr2 = cr7;
                            cr3 = true;
                        }
                        if (cr3) {
                            boolean cr8 = cr2;
                            if (low$iv == '\r') {
                                cr5 = true;
                                z3 = true;
                            } else if (low$iv == '\n') {
                                cr5 = false;
                                z3 = false;
                            } else if (cr8) {
                                cr5 = cr8;
                                z3 = false;
                            } else {
                                z3 = true;
                                cr5 = cr8;
                            }
                            if (z3) {
                                int outPos$iv3 = outPos$iv + 1;
                                out[outPos$iv] = high$iv;
                                outPos$iv = outPos$iv3 + 1;
                                out[outPos$iv3] = low$iv;
                                cr6 = cr5;
                                $i$f$decodeUTF8_buffer = ch;
                            } else {
                                cr4 = cr5;
                            }
                        } else {
                            cr4 = cr2;
                        }
                        $this$decodeUTF8Line_buffer.position($this$decodeUTF8Line_buffer.position() - 4);
                        cr6 = cr4;
                        rc = decodeUtf8Result(outPos$iv - offset, -1);
                        break;
                    }
                    if ($this$decodeUTF8Line_buffer.remaining() < 2) {
                        $this$decodeUTF8Line_buffer.position($this$decodeUTF8Line_buffer.position() - 1);
                        rc = decodeUtf8Result(outPos$iv - offset, 3);
                        break;
                    }
                    int second$iv4 = $this$decodeUTF8Line_buffer.get();
                    int third$iv2 = $this$decodeUTF8Line_buffer.get();
                    int highest$iv = v$iv & Ascii.SI;
                    int vv$iv2 = (highest$iv << 12) | ((second$iv4 & 63) << 6) | (third$iv2 & 63);
                    if (highest$iv != 0 && !isBmpCodePoint(vv$iv2)) {
                        malformedCodePoint(vv$iv2);
                        throw new KotlinNothingValueException();
                    }
                    char ch$iv3 = (char) vv$iv2;
                    boolean cr9 = cr6;
                    if (ch$iv3 == '\r') {
                        cr9 = true;
                        cr = true;
                    } else if (ch$iv3 == '\n') {
                        cr9 = false;
                        cr = false;
                    } else {
                        cr = !cr9;
                    }
                    if (!cr) {
                        $this$decodeUTF8Line_buffer.position($this$decodeUTF8Line_buffer.position() - 3);
                        rc = decodeUtf8Result(outPos$iv - offset, -1);
                        cr6 = cr9;
                        break;
                    }
                    out[outPos$iv] = ch$iv3;
                    outPos$iv++;
                    cr6 = cr9;
                }
            }
        }
        rc = decodeUtf8Result(outPos$iv - offset, 0);
        cr6 = cr6;
        int required = (int) (rc & 4294967295L);
        if (required == -1) {
            int decoded = (int) (rc >> 32);
            if (cr6) {
                return decodeUtf8Result(decoded - 1, -1);
            }
            $this$decodeUTF8Line_buffer.position($this$decodeUTF8Line_buffer.position() + 1);
            if (decoded > 0 && out[decoded - 1] == '\r') {
                return decodeUtf8Result(decoded - 1, -1);
            }
        } else if (required == 0 && cr6) {
            $this$decodeUTF8Line_buffer.position($this$decodeUTF8Line_buffer.position() - 1);
            return decodeUtf8Result(((int) (rc >> 32)) - 1, 2);
        }
        return rc;
    }

    private static final long decodeUTF8_array(ByteBuffer $this$decodeUTF8_array, char[] out, int offset, int length) {
        byte[] array = $this$decodeUTF8_array.array();
        int srcPos = $this$decodeUTF8_array.arrayOffset() + $this$decodeUTF8_array.position();
        int srcEnd = $this$decodeUTF8_array.remaining() + srcPos;
        if (!(srcPos <= srcEnd)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(srcEnd <= array.length)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int outPos = offset;
        int outEnd = offset + length;
        if (outEnd > out.length) {
            throw indexOutOfBounds(offset, length, out.length);
        }
        while (srcPos < srcEnd && outPos < outEnd) {
            int srcPos2 = srcPos + 1;
            byte v = array[srcPos];
            if (v >= 0) {
                char ch = (char) v;
                out[outPos] = ch;
                srcPos = srcPos2;
                outPos++;
            } else if ((v & 224) == 192) {
                if (srcPos2 >= srcEnd) {
                    $this$decodeUTF8_array.position((srcPos2 - 1) - $this$decodeUTF8_array.arrayOffset());
                    return decodeUtf8Result(outPos - offset, 2);
                }
                int srcPos3 = srcPos2 + 1;
                int second = array[srcPos2];
                out[outPos] = (char) (((v & Ascii.US) << 6) | (second & 63));
                srcPos = srcPos3;
                outPos++;
            } else if ((v & 240) == 224) {
                if (srcEnd - srcPos2 < 2) {
                    $this$decodeUTF8_array.position((srcPos2 - 1) - $this$decodeUTF8_array.arrayOffset());
                    return decodeUtf8Result(outPos - offset, 3);
                }
                int srcPos4 = srcPos2 + 1;
                int second2 = array[srcPos2];
                int srcPos5 = srcPos4 + 1;
                int third = array[srcPos4];
                int highest = v & Ascii.SI;
                int vv = (highest << 12) | ((second2 & 63) << 6) | (third & 63);
                if (highest == 0 || isBmpCodePoint(vv)) {
                    out[outPos] = (char) vv;
                    srcPos = srcPos5;
                    outPos++;
                } else {
                    malformedCodePoint(vv);
                    throw new KotlinNothingValueException();
                }
            } else {
                if ((v & 248) != 240) {
                    unsupportedByteCount(v);
                    throw new KotlinNothingValueException();
                }
                if (srcEnd - srcPos2 < 3) {
                    $this$decodeUTF8_array.position((srcPos2 - 1) - $this$decodeUTF8_array.arrayOffset());
                    return decodeUtf8Result(outPos - offset, 4);
                }
                int srcPos6 = srcPos2 + 1;
                int second3 = array[srcPos2];
                int srcPos7 = srcPos6 + 1;
                int third2 = array[srcPos6];
                int srcPos8 = srcPos7 + 1;
                int fourth = array[srcPos7];
                int vv2 = ((v & 7) << 18) | ((second3 & 63) << 12) | ((third2 & 63) << 6) | (fourth & 63);
                if (!isValidCodePoint(vv2)) {
                    malformedCodePoint(vv2);
                    throw new KotlinNothingValueException();
                }
                byte[] array2 = array;
                if (outEnd - outPos < 2) {
                    $this$decodeUTF8_array.position((srcPos8 - 4) - $this$decodeUTF8_array.arrayOffset());
                    return decodeUtf8Result(outPos - offset, 0);
                }
                int high = highSurrogate(vv2);
                int low = lowSurrogate(vv2);
                int outPos2 = outPos + 1;
                out[outPos] = (char) high;
                outPos = outPos2 + 1;
                out[outPos2] = (char) low;
                srcPos = srcPos8;
                array = array2;
            }
        }
        $this$decodeUTF8_array.position(srcPos - $this$decodeUTF8_array.arrayOffset());
        return decodeUtf8Result(outPos - offset, 0);
    }

    private static final long decodeUTF8_buffer(ByteBuffer $this$decodeUTF8_buffer, char[] out, int offset, int length) {
        int outPos = offset;
        int outEnd = offset + length;
        if (outEnd > out.length) {
            throw indexOutOfBounds(offset, length, out.length);
        }
        while ($this$decodeUTF8_buffer.hasRemaining() && outPos < outEnd) {
            byte v = $this$decodeUTF8_buffer.get();
            if (v >= 0) {
                char ch = (char) v;
                out[outPos] = ch;
                outPos++;
            } else {
                int outPos2 = v & 224;
                if (outPos2 == 192) {
                    if ($this$decodeUTF8_buffer.hasRemaining()) {
                        $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 1);
                        return decodeUtf8Result(outPos - offset, 2);
                    }
                    int second = $this$decodeUTF8_buffer.get();
                    out[outPos] = (char) (((v & Ascii.US) << 6) | (second & 63));
                    outPos++;
                } else {
                    int outPos3 = v & 240;
                    if (outPos3 == 224) {
                        if ($this$decodeUTF8_buffer.remaining() < 2) {
                            $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 1);
                            return decodeUtf8Result(outPos - offset, 3);
                        }
                        int second2 = $this$decodeUTF8_buffer.get();
                        int third = $this$decodeUTF8_buffer.get();
                        int highest = v & Ascii.SI;
                        int vv = (highest << 12) | ((second2 & 63) << 6) | (third & 63);
                        if (highest == 0 || isBmpCodePoint(vv)) {
                            out[outPos] = (char) vv;
                            outPos++;
                        } else {
                            malformedCodePoint(vv);
                            throw new KotlinNothingValueException();
                        }
                    } else {
                        int third2 = v & 248;
                        if (third2 == 240) {
                            if ($this$decodeUTF8_buffer.remaining() < 3) {
                                $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 1);
                                return decodeUtf8Result(outPos - offset, 4);
                            }
                            int second3 = $this$decodeUTF8_buffer.get();
                            int third3 = $this$decodeUTF8_buffer.get();
                            int fourth = $this$decodeUTF8_buffer.get();
                            int vv2 = ((v & 7) << 18) | ((second3 & 63) << 12) | ((third3 & 63) << 6) | (fourth & 63);
                            if (!isValidCodePoint(vv2)) {
                                malformedCodePoint(vv2);
                                throw new KotlinNothingValueException();
                            }
                            if (outEnd - outPos >= 2) {
                                int high = highSurrogate(vv2);
                                int low = lowSurrogate(vv2);
                                int outPos4 = outPos + 1;
                                out[outPos] = (char) high;
                                outPos = outPos4 + 1;
                                out[outPos4] = (char) low;
                            } else {
                                $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 4);
                                return decodeUtf8Result(outPos - offset, 0);
                            }
                        } else {
                            unsupportedByteCount(v);
                            throw new KotlinNothingValueException();
                        }
                    }
                }
            }
        }
        return decodeUtf8Result(outPos - offset, 0);
    }

    private static final long decodeUTF8_array(ByteBuffer $this$decodeUTF8_array, char[] out, int offset, int length, Function1<? super Character, Boolean> function1) {
        int $i$f$decodeUTF8_array = 0;
        byte[] array = $this$decodeUTF8_array.array();
        int srcPos = $this$decodeUTF8_array.arrayOffset() + $this$decodeUTF8_array.position();
        int srcEnd = $this$decodeUTF8_array.remaining() + srcPos;
        if (!(srcPos <= srcEnd)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(srcEnd <= array.length)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int outPos = offset;
        int outEnd = offset + length;
        if (outEnd > out.length) {
            throw indexOutOfBounds(offset, length, out.length);
        }
        while (srcPos < srcEnd && outPos < outEnd) {
            int srcPos2 = srcPos + 1;
            byte v = array[srcPos];
            if (v >= 0) {
                char ch = (char) v;
                if (!function1.invoke(Character.valueOf(ch)).booleanValue()) {
                    $this$decodeUTF8_array.position((srcPos2 - 1) - $this$decodeUTF8_array.arrayOffset());
                    return decodeUtf8Result(outPos - offset, -1);
                }
                out[outPos] = ch;
                outPos++;
                srcPos = srcPos2;
            } else {
                int outPos2 = v & 224;
                if (outPos2 == 192) {
                    if (srcPos2 >= srcEnd) {
                        $this$decodeUTF8_array.position((srcPos2 - 1) - $this$decodeUTF8_array.arrayOffset());
                        return decodeUtf8Result(outPos - offset, 2);
                    }
                    int srcPos3 = srcPos2 + 1;
                    int second = array[srcPos2];
                    char ch2 = (char) (((v & Ascii.US) << 6) | (second & 63));
                    if (!function1.invoke(Character.valueOf(ch2)).booleanValue()) {
                        $this$decodeUTF8_array.position((srcPos3 - 2) - $this$decodeUTF8_array.arrayOffset());
                        return decodeUtf8Result(outPos - offset, -1);
                    }
                    int $i$f$decodeUTF8_array2 = $i$f$decodeUTF8_array;
                    int $i$f$decodeUTF8_array3 = outPos + 1;
                    out[outPos] = ch2;
                    outPos = $i$f$decodeUTF8_array3;
                    srcPos = srcPos3;
                    $i$f$decodeUTF8_array = $i$f$decodeUTF8_array2;
                } else {
                    int $i$f$decodeUTF8_array4 = $i$f$decodeUTF8_array;
                    int $i$f$decodeUTF8_array5 = v & 240;
                    if ($i$f$decodeUTF8_array5 == 224) {
                        if (srcEnd - srcPos2 < 2) {
                            $this$decodeUTF8_array.position((srcPos2 - 1) - $this$decodeUTF8_array.arrayOffset());
                            return decodeUtf8Result(outPos - offset, 3);
                        }
                        int srcPos4 = srcPos2 + 1;
                        int second2 = array[srcPos2];
                        int srcPos5 = srcPos4 + 1;
                        int third = array[srcPos4];
                        int highest = v & Ascii.SI;
                        int vv = (highest << 12) | ((second2 & 63) << 6) | (third & 63);
                        if (highest == 0 || isBmpCodePoint(vv)) {
                            char ch3 = (char) vv;
                            if (function1.invoke(Character.valueOf(ch3)).booleanValue()) {
                                out[outPos] = ch3;
                                outPos++;
                                srcPos = srcPos5;
                                $i$f$decodeUTF8_array = $i$f$decodeUTF8_array4;
                            } else {
                                $this$decodeUTF8_array.position((srcPos5 - 4) - $this$decodeUTF8_array.arrayOffset());
                                return decodeUtf8Result(outPos - offset, -1);
                            }
                        } else {
                            malformedCodePoint(vv);
                            throw new KotlinNothingValueException();
                        }
                    } else {
                        byte[] array2 = array;
                        if ((v & 248) == 240) {
                            if (srcEnd - srcPos2 < 3) {
                                $this$decodeUTF8_array.position((srcPos2 - 1) - $this$decodeUTF8_array.arrayOffset());
                                return decodeUtf8Result(outPos - offset, 4);
                            }
                            int srcPos6 = srcPos2 + 1;
                            int second3 = array2[srcPos2];
                            int srcPos7 = srcPos6 + 1;
                            int third2 = array2[srcPos6];
                            int srcPos8 = srcPos7 + 1;
                            int fourth = array2[srcPos7];
                            int vv2 = ((v & 7) << 18) | ((second3 & 63) << 12) | ((third2 & 63) << 6) | (fourth & 63);
                            if (!isValidCodePoint(vv2)) {
                                malformedCodePoint(vv2);
                                throw new KotlinNothingValueException();
                            }
                            int third3 = outEnd - outPos;
                            if (third3 >= 2) {
                                char high = (char) highSurrogate(vv2);
                                char low = (char) lowSurrogate(vv2);
                                if (!function1.invoke(Character.valueOf(high)).booleanValue() || !function1.invoke(Character.valueOf(low)).booleanValue()) {
                                    $this$decodeUTF8_array.position((srcPos8 - 4) - $this$decodeUTF8_array.arrayOffset());
                                    return decodeUtf8Result(outPos - offset, -1);
                                }
                                int outPos3 = outPos + 1;
                                out[outPos] = high;
                                outPos = outPos3 + 1;
                                out[outPos3] = low;
                                srcPos = srcPos8;
                                $i$f$decodeUTF8_array = $i$f$decodeUTF8_array4;
                                array = array2;
                            } else {
                                $this$decodeUTF8_array.position((srcPos8 - 4) - $this$decodeUTF8_array.arrayOffset());
                                return decodeUtf8Result(outPos - offset, 0);
                            }
                        } else {
                            unsupportedByteCount(v);
                            throw new KotlinNothingValueException();
                        }
                    }
                }
            }
        }
        $this$decodeUTF8_array.position(srcPos - $this$decodeUTF8_array.arrayOffset());
        return decodeUtf8Result(outPos - offset, 0);
    }

    private static final long decodeUTF8_buffer(ByteBuffer $this$decodeUTF8_buffer, char[] out, int offset, int length, Function1<? super Character, Boolean> function1) {
        int second = 0;
        int outPos = offset;
        int outEnd = offset + length;
        if (outEnd > out.length) {
            throw indexOutOfBounds(offset, length, out.length);
        }
        while ($this$decodeUTF8_buffer.hasRemaining() && outPos < outEnd) {
            byte v = $this$decodeUTF8_buffer.get();
            if (v >= 0) {
                char ch = (char) v;
                if (!function1.invoke(Character.valueOf(ch)).booleanValue()) {
                    $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 1);
                    return decodeUtf8Result(outPos - offset, -1);
                }
                out[outPos] = ch;
                outPos++;
            } else if ((v & 224) == 192) {
                if (!$this$decodeUTF8_buffer.hasRemaining()) {
                    $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 1);
                    return decodeUtf8Result(outPos - offset, 2);
                }
                int second2 = $this$decodeUTF8_buffer.get();
                char ch2 = (char) (((v & Ascii.US) << 6) | (second2 & 63));
                if (!function1.invoke(Character.valueOf(ch2)).booleanValue()) {
                    $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 2);
                    return decodeUtf8Result(outPos - offset, -1);
                }
                out[outPos] = ch2;
                outPos++;
            } else if ((v & 240) == 224) {
                if ($this$decodeUTF8_buffer.remaining() < 2) {
                    $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 1);
                    return decodeUtf8Result(outPos - offset, 3);
                }
                int second3 = $this$decodeUTF8_buffer.get();
                int third = $this$decodeUTF8_buffer.get();
                int highest = v & Ascii.SI;
                int vv = (highest << 12) | ((second3 & 63) << 6) | (third & 63);
                if (highest == 0 || isBmpCodePoint(vv)) {
                    char ch3 = (char) vv;
                    if (!function1.invoke(Character.valueOf(ch3)).booleanValue()) {
                        $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 3);
                        return decodeUtf8Result(outPos - offset, -1);
                    }
                    int $i$f$decodeUTF8_buffer = second;
                    int $i$f$decodeUTF8_buffer2 = outPos + 1;
                    out[outPos] = ch3;
                    outPos = $i$f$decodeUTF8_buffer2;
                    second = $i$f$decodeUTF8_buffer;
                } else {
                    malformedCodePoint(vv);
                    throw new KotlinNothingValueException();
                }
            } else {
                int $i$f$decodeUTF8_buffer3 = second;
                int $i$f$decodeUTF8_buffer4 = v & 248;
                if ($i$f$decodeUTF8_buffer4 == 240) {
                    if ($this$decodeUTF8_buffer.remaining() < 3) {
                        $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 1);
                        return decodeUtf8Result(outPos - offset, 4);
                    }
                    int second4 = $this$decodeUTF8_buffer.get();
                    int third2 = $this$decodeUTF8_buffer.get();
                    int fourth = $this$decodeUTF8_buffer.get();
                    int vv2 = ((v & 7) << 18) | ((second4 & 63) << 12) | ((third2 & 63) << 6) | (fourth & 63);
                    if (!isValidCodePoint(vv2)) {
                        malformedCodePoint(vv2);
                        throw new KotlinNothingValueException();
                    }
                    if (outEnd - outPos >= 2) {
                        char high = (char) highSurrogate(vv2);
                        char low = (char) lowSurrogate(vv2);
                        if (!function1.invoke(Character.valueOf(high)).booleanValue() || !function1.invoke(Character.valueOf(low)).booleanValue()) {
                            $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 4);
                            return decodeUtf8Result(outPos - offset, -1);
                        }
                        int outPos2 = outPos + 1;
                        out[outPos] = high;
                        outPos = outPos2 + 1;
                        out[outPos2] = low;
                        second = $i$f$decodeUTF8_buffer3;
                    } else {
                        $this$decodeUTF8_buffer.position($this$decodeUTF8_buffer.position() - 4);
                        return decodeUtf8Result(outPos - offset, 0);
                    }
                } else {
                    unsupportedByteCount(v);
                    throw new KotlinNothingValueException();
                }
            }
        }
        return decodeUtf8Result(outPos - offset, 0);
    }

    private static final boolean isBmpCodePoint(int cp) {
        return (cp >>> 16) == 0;
    }

    private static final boolean isValidCodePoint(int codePoint) {
        return codePoint <= MaxCodePoint;
    }

    private static final int lowSurrogate(int cp) {
        return (cp & 1023) + 56320;
    }

    private static final int highSurrogate(int cp) {
        return (cp >>> 10) + 55232;
    }

    private static final Throwable indexOutOfBounds(int offset, int length, int arrayLength) {
        return new IndexOutOfBoundsException(offset + " (offset) + " + length + " (length) > " + arrayLength + " (array.length)");
    }

    private static final Void malformedCodePoint(int value) {
        throw new IllegalArgumentException("Malformed code-point " + Integer.toHexString(value) + " found");
    }

    private static final Void unsupportedByteCount(byte b) {
        StringBuilder append = new StringBuilder().append("Unsupported byte code, first byte is 0x");
        String num = Integer.toString(b & 255, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
        throw new IllegalStateException(append.append(kotlin.text.StringsKt.padStart(num, 2, '0')).toString().toString());
    }
}
