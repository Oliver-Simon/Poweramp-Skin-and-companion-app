package io.ktor.utils.io.core.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.core.Buffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTF8.kt */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0014\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\u0011\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0082\b\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0000\u001a_\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u00122\u0006\u0010\u0013\u001a\u00020\u00012$\u0010\u0014\u001a \b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00152\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001b0\u001aH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\u0010\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010 \u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u0001H\u0001\u001a\u0010\u0010!\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0001H\u0001\u001a\u0010\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u0001H\u0001\u001a\u0010\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0001H\u0002\u001a$\u0010)\u001a\u00020\u000f*\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000f0\u001aH\u0080\bø\u0001\u0001\u001a$\u0010,\u001a\u00020\u0001*\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000f0\u001aH\u0080\bø\u0001\u0001\u001aA\u0010-\u001a\u00020.*\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00012\u0006\u00105\u001a\u00020\u0001H\u0000ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b6\u00107\u001aQ\u00108\u001a\u00020.*\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u00012\u0006\u0010<\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u0001H\u0002ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001aQ\u0010?\u001a\u00020.*\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u00012\u0006\u0010<\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u0001H\u0002ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b@\u0010>\u001a*\u0010A\u001a\u00020\u0001*\u00020/2\u0006\u0010B\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0080\bø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010D\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0012\n\u0002\b\u0019\n\u0005\b\u009920\u0001\n\u0005\b¡\u001e0\u0001¨\u0006E"}, d2 = {"HighSurrogateMagic", "", "MaxCodePoint", "MinHighSurrogate", "MinLowSurrogate", "MinSupplementary", "byteCountUtf8", "firstByte", "charactersSize", "v", "codePoint", "high", "", "low", "decodeUTF8LineLoopSuspend", "", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "limit", "nextChunk", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lio/ktor/utils/io/core/Input;", "", "afterRead", "Lkotlin/Function1;", "", "(Ljava/lang/Appendable;ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "highSurrogate", "cp", "isBmpCodePoint", "isValidCodePoint", "lowSurrogate", "malformedByteCount", "", "byteCount", "malformedCodePoint", "value", "prematureEndOfStreamUtf", ContentDisposition.Parameters.Size, "decodeASCII", "Lio/ktor/utils/io/core/Buffer;", "consumer", "decodeUTF8", "encodeUTF8", "Lio/ktor/utils/io/core/internal/EncodeResult;", "Lio/ktor/utils/io/bits/Memory;", "text", "", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "dstOffset", "dstLimit", "encodeUTF8-lBXzO7A", "(Ljava/nio/ByteBuffer;Ljava/lang/CharSequence;IIII)I", "encodeUTF8Stage1", "index1", "lastCharIndex", "resultPosition1", "resultLimit", "encodeUTF8Stage1-Vm9B2pQ", "(Ljava/nio/ByteBuffer;Ljava/lang/CharSequence;IIIIII)I", "encodeUTF8Stage2", "encodeUTF8Stage2-Vm9B2pQ", "putUtf8Char", TypedValues.CycleType.S_WAVE_OFFSET, "putUtf8Char-62zg_DM", "(Ljava/nio/ByteBuffer;II)I", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UTF8Kt {
    private static final int HighSurrogateMagic = 55232;
    private static final int MaxCodePoint = 1114111;
    private static final int MinHighSurrogate = 55296;
    private static final int MinLowSurrogate = 56320;
    private static final int MinSupplementary = 65536;

    public static final boolean decodeASCII(Buffer $this$decodeASCII, Function1<? super Character, Boolean> consumer) {
        Intrinsics.checkNotNullParameter($this$decodeASCII, "<this>");
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        ByteBuffer memory = $this$decodeASCII.getMemory();
        int start = $this$decodeASCII.getReadPosition();
        int endExclusive = $this$decodeASCII.getWritePosition();
        for (int index = start; index < endExclusive; index++) {
            int codepoint = memory.get(index) & 255;
            if ((codepoint & 128) == 128 || !consumer.invoke(Character.valueOf((char) codepoint)).booleanValue()) {
                $this$decodeASCII.discardExact(index - start);
                return false;
            }
        }
        int rc$iv = endExclusive - start;
        $this$decodeASCII.discardExact(rc$iv);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:200:0x03c3, code lost:
    
        r14.discardExact(((r31 - r26) - r2.element) + 1);
        r1 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x04c9, code lost:
    
        if (r17 == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x04cb, code lost:
    
        io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r0, r1);
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0024. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x050a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00b2 -> B:12:0x00c1). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object decodeUTF8LineLoopSuspend(java.lang.Appendable r39, int r40, kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.Input>, ? extends java.lang.Object> r41, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r42, kotlin.coroutines.Continuation<? super java.lang.Boolean> r43) {
        /*
            Method dump skipped, instructions count: 1342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.internal.UTF8Kt.decodeUTF8LineLoopSuspend(java.lang.Appendable, int, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Void prematureEndOfStreamUtf(int size) {
        throw new EOFException("Premature end of stream: expected " + size + " bytes to decode UTF-8 char");
    }

    public static final int byteCountUtf8(int firstByte) {
        int byteCount = 0;
        int mask = 128;
        int value = firstByte;
        for (int i = 1; i < 7 && (value & mask) != 0; i++) {
            value &= ~mask;
            mask >>= 1;
            byteCount++;
        }
        return byteCount;
    }

    public static final int decodeUTF8(Buffer $this$decodeUTF8, Function1<? super Character, Boolean> consumer) {
        int $i$f$decodeUTF8;
        Intrinsics.checkNotNullParameter($this$decodeUTF8, "<this>");
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        int $i$f$decodeUTF82 = 0;
        int byteCount = 0;
        int value = 0;
        int lastByteCount = 0;
        ByteBuffer memory = $this$decodeUTF8.getMemory();
        int start = $this$decodeUTF8.getReadPosition();
        int endExclusive = $this$decodeUTF8.getWritePosition();
        int index = start;
        while (index < endExclusive) {
            int v = memory.get(index) & 255;
            if ((v & 128) == 0) {
                if (byteCount != 0) {
                    malformedByteCount(byteCount);
                    throw new KotlinNothingValueException();
                }
                if (consumer.invoke(Character.valueOf((char) v)).booleanValue()) {
                    $i$f$decodeUTF8 = $i$f$decodeUTF82;
                } else {
                    $this$decodeUTF8.discardExact(index - start);
                    return -1;
                }
            } else if (byteCount == 0) {
                int mask = 128;
                value = v;
                int i = 1;
                while (true) {
                    $i$f$decodeUTF8 = $i$f$decodeUTF82;
                    if (i >= 7 || (value & mask) == 0) {
                        break;
                    }
                    value &= ~mask;
                    mask >>= 1;
                    byteCount++;
                    i++;
                    $i$f$decodeUTF82 = $i$f$decodeUTF8;
                }
                int lastByteCount2 = byteCount;
                byteCount--;
                if (lastByteCount2 <= endExclusive - index) {
                    lastByteCount = lastByteCount2;
                } else {
                    $this$decodeUTF8.discardExact(index - start);
                    return lastByteCount2;
                }
            } else {
                $i$f$decodeUTF8 = $i$f$decodeUTF82;
                int $i$f$decodeUTF83 = value << 6;
                int value2 = $i$f$decodeUTF83 | (v & 127);
                byteCount--;
                if (byteCount != 0) {
                    value = value2;
                } else {
                    if (isBmpCodePoint(value2)) {
                        if (!consumer.invoke(Character.valueOf((char) value2)).booleanValue()) {
                            $this$decodeUTF8.discardExact(((index - start) - lastByteCount) + 1);
                            return -1;
                        }
                    } else {
                        if (!isValidCodePoint(value2)) {
                            malformedCodePoint(value2);
                            throw new KotlinNothingValueException();
                        }
                        if (!consumer.invoke(Character.valueOf((char) highSurrogate(value2))).booleanValue() || !consumer.invoke(Character.valueOf((char) lowSurrogate(value2))).booleanValue()) {
                            $this$decodeUTF8.discardExact(((index - start) - lastByteCount) + 1);
                            return -1;
                        }
                    }
                    value = 0;
                }
            }
            index++;
            $i$f$decodeUTF82 = $i$f$decodeUTF8;
        }
        int rc$iv = endExclusive - start;
        $this$decodeUTF8.discardExact(rc$iv);
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x005e, code lost:
    
        return io.ktor.utils.io.core.internal.EncodeResult.m489constructorimpl(kotlin.UShort.m791constructorimpl((short) (r2 - r6)), kotlin.UShort.m791constructorimpl((short) (r7 - r9)));
     */
    /* renamed from: encodeUTF8-lBXzO7A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int m497encodeUTF8lBXzO7A(java.nio.ByteBuffer r10, java.lang.CharSequence r11, int r12, int r13, int r14, int r15) {
        /*
            java.lang.String r0 = "$this$encodeUTF8"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            r0 = 65535(0xffff, float:9.1834E-41)
            int r1 = r12 + r0
            int r5 = java.lang.Math.min(r13, r1)
            int r8 = kotlin.ranges.RangesKt.coerceAtMost(r15, r0)
            r1 = r14
            r2 = r12
            r7 = r1
        L1a:
            if (r7 >= r8) goto L48
            if (r2 < r5) goto L23
            r3 = r11
            r6 = r12
            r9 = r14
            r11 = r10
            goto L4c
        L23:
            int r1 = r2 + 1
            char r2 = r11.charAt(r2)
            r2 = r2 & r0
            r3 = 65408(0xff80, float:9.1656E-41)
            r3 = r3 & r2
            if (r3 != 0) goto L3b
            int r3 = r7 + 1
            byte r4 = (byte) r2
            r6 = 0
            r10.put(r7, r4)
            r2 = r1
            r7 = r3
            goto L1a
        L3b:
            int r4 = r1 + (-1)
            r2 = r10
            r3 = r11
            r6 = r12
            r9 = r14
            int r10 = m498encodeUTF8Stage1Vm9B2pQ(r2, r3, r4, r5, r6, r7, r8, r9)
            r11 = r2
            return r10
        L48:
            r3 = r11
            r6 = r12
            r9 = r14
            r11 = r10
        L4c:
            int r10 = r2 - r6
            short r10 = (short) r10
            short r10 = kotlin.UShort.m791constructorimpl(r10)
            int r12 = r7 - r9
            short r12 = (short) r12
            short r12 = kotlin.UShort.m791constructorimpl(r12)
            int r10 = io.ktor.utils.io.core.internal.EncodeResult.m489constructorimpl(r10, r12)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.internal.UTF8Kt.m497encodeUTF8lBXzO7A(java.nio.ByteBuffer, java.lang.CharSequence, int, int, int, int):int");
    }

    /* renamed from: encodeUTF8Stage1-Vm9B2pQ, reason: not valid java name */
    private static final int m498encodeUTF8Stage1Vm9B2pQ(ByteBuffer $this$encodeUTF8Stage1_u2dVm9B2pQ, CharSequence text, int index1, int lastCharIndex, int from, int resultPosition1, int resultLimit, int dstOffset) {
        int index;
        int stage1Limit = resultLimit - 3;
        int resultPosition = resultPosition1;
        int resultPosition2 = index1;
        while (stage1Limit - resultPosition > 0 && resultPosition2 < lastCharIndex) {
            int index2 = resultPosition2 + 1;
            char character = text.charAt(resultPosition2);
            if (!Character.isHighSurrogate(character)) {
                index = character;
            } else if (index2 == lastCharIndex || !Character.isLowSurrogate(text.charAt(index2))) {
                index = 63;
            } else {
                index = codePoint(character, text.charAt(index2));
                index2++;
            }
            boolean z = false;
            int size = 1;
            if (index >= 0 && index < 128) {
                byte value$iv$iv = (byte) index;
                $this$encodeUTF8Stage1_u2dVm9B2pQ.put(resultPosition, value$iv$iv);
            } else {
                if (128 <= index && index < 2048) {
                    byte value$iv$iv2 = (byte) (((index >> 6) & 31) | 192);
                    $this$encodeUTF8Stage1_u2dVm9B2pQ.put(resultPosition, value$iv$iv2);
                    int index$iv$iv = resultPosition + 1;
                    byte value$iv$iv3 = (byte) (128 | (index & 63));
                    $this$encodeUTF8Stage1_u2dVm9B2pQ.put(index$iv$iv, value$iv$iv3);
                    size = 2;
                } else {
                    if (2048 <= index && index < 65536) {
                        byte value$iv$iv4 = (byte) (((index >> 12) & 15) | 224);
                        $this$encodeUTF8Stage1_u2dVm9B2pQ.put(resultPosition, value$iv$iv4);
                        int index$iv$iv2 = resultPosition + 1;
                        byte value$iv$iv5 = (byte) ((63 & (index >> 6)) | 128);
                        $this$encodeUTF8Stage1_u2dVm9B2pQ.put(index$iv$iv2, value$iv$iv5);
                        int index$iv$iv3 = resultPosition + 2;
                        byte value$iv$iv6 = (byte) (128 | (index & 63));
                        $this$encodeUTF8Stage1_u2dVm9B2pQ.put(index$iv$iv3, value$iv$iv6);
                        size = 3;
                    } else {
                        if (65536 <= index && index < 1114112) {
                            z = true;
                        }
                        if (!z) {
                            malformedCodePoint(index);
                            throw new KotlinNothingValueException();
                        }
                        byte value$iv$iv7 = (byte) (((index >> 18) & 7) | 240);
                        $this$encodeUTF8Stage1_u2dVm9B2pQ.put(resultPosition, value$iv$iv7);
                        int index$iv$iv4 = resultPosition + 1;
                        byte value$iv$iv8 = (byte) (((index >> 12) & 63) | 128);
                        $this$encodeUTF8Stage1_u2dVm9B2pQ.put(index$iv$iv4, value$iv$iv8);
                        int index$iv$iv5 = resultPosition + 2;
                        byte value$iv$iv9 = (byte) ((63 & (index >> 6)) | 128);
                        $this$encodeUTF8Stage1_u2dVm9B2pQ.put(index$iv$iv5, value$iv$iv9);
                        int index$iv$iv6 = resultPosition + 3;
                        byte value$iv$iv10 = (byte) (128 | (index & 63));
                        $this$encodeUTF8Stage1_u2dVm9B2pQ.put(index$iv$iv6, value$iv$iv10);
                        size = 4;
                    }
                }
            }
            resultPosition += size;
            resultPosition2 = index2;
        }
        return resultPosition == stage1Limit ? m499encodeUTF8Stage2Vm9B2pQ($this$encodeUTF8Stage1_u2dVm9B2pQ, text, resultPosition2, lastCharIndex, from, resultPosition, resultLimit, dstOffset) : EncodeResult.m489constructorimpl(UShort.m791constructorimpl((short) (resultPosition2 - from)), UShort.m791constructorimpl((short) (resultPosition - dstOffset)));
    }

    /* renamed from: encodeUTF8Stage2-Vm9B2pQ, reason: not valid java name */
    private static final int m499encodeUTF8Stage2Vm9B2pQ(ByteBuffer $this$encodeUTF8Stage2_u2dVm9B2pQ, CharSequence text, int index1, int lastCharIndex, int from, int resultPosition1, int resultLimit, int dstOffset) {
        int codepoint;
        int i;
        int index = index1;
        int resultPosition = resultPosition1;
        while (true) {
            int freeSpace = resultLimit - resultPosition;
            if (freeSpace <= 0 || index >= lastCharIndex) {
                break;
            }
            int index2 = index + 1;
            char character = text.charAt(index);
            if (!Character.isHighSurrogate(character)) {
                codepoint = character;
            } else if (index2 == lastCharIndex || !Character.isLowSurrogate(text.charAt(index2))) {
                codepoint = 63;
            } else {
                codepoint = codePoint(character, text.charAt(index2));
                index2++;
            }
            int size = 1;
            if (1 <= codepoint && codepoint < 128) {
                i = 1;
            } else {
                if (128 <= codepoint && codepoint < 2048) {
                    i = 2;
                } else {
                    if (2048 <= codepoint && codepoint < 65536) {
                        i = 3;
                    } else {
                        if (!(65536 <= codepoint && codepoint < 1114112)) {
                            malformedCodePoint(codepoint);
                            throw new KotlinNothingValueException();
                        }
                        i = 4;
                    }
                }
            }
            if (i > freeSpace) {
                index = index2 - 1;
                break;
            }
            if (codepoint >= 0 && codepoint < 128) {
                byte value$iv$iv = (byte) codepoint;
                $this$encodeUTF8Stage2_u2dVm9B2pQ.put(resultPosition, value$iv$iv);
            } else {
                if (128 <= codepoint && codepoint < 2048) {
                    byte value$iv$iv2 = (byte) (((codepoint >> 6) & 31) | 192);
                    $this$encodeUTF8Stage2_u2dVm9B2pQ.put(resultPosition, value$iv$iv2);
                    int index$iv$iv = resultPosition + 1;
                    byte value$iv$iv3 = (byte) (128 | (codepoint & 63));
                    $this$encodeUTF8Stage2_u2dVm9B2pQ.put(index$iv$iv, value$iv$iv3);
                    size = 2;
                } else {
                    if (2048 <= codepoint && codepoint < 65536) {
                        byte value$iv$iv4 = (byte) (((codepoint >> 12) & 15) | 224);
                        $this$encodeUTF8Stage2_u2dVm9B2pQ.put(resultPosition, value$iv$iv4);
                        int index$iv$iv2 = resultPosition + 1;
                        byte value$iv$iv5 = (byte) (((codepoint >> 6) & 63) | 128);
                        $this$encodeUTF8Stage2_u2dVm9B2pQ.put(index$iv$iv2, value$iv$iv5);
                        int index$iv$iv3 = resultPosition + 2;
                        byte value$iv$iv6 = (byte) (128 | (codepoint & 63));
                        $this$encodeUTF8Stage2_u2dVm9B2pQ.put(index$iv$iv3, value$iv$iv6);
                        size = 3;
                    } else {
                        if (!(65536 <= codepoint && codepoint < 1114112)) {
                            malformedCodePoint(codepoint);
                            throw new KotlinNothingValueException();
                        }
                        byte value$iv$iv7 = (byte) (((codepoint >> 18) & 7) | 240);
                        $this$encodeUTF8Stage2_u2dVm9B2pQ.put(resultPosition, value$iv$iv7);
                        int index$iv$iv4 = resultPosition + 1;
                        byte value$iv$iv8 = (byte) (((codepoint >> 12) & 63) | 128);
                        $this$encodeUTF8Stage2_u2dVm9B2pQ.put(index$iv$iv4, value$iv$iv8);
                        int index$iv$iv5 = resultPosition + 2;
                        byte value$iv$iv9 = (byte) (((codepoint >> 6) & 63) | 128);
                        $this$encodeUTF8Stage2_u2dVm9B2pQ.put(index$iv$iv5, value$iv$iv9);
                        int index$iv$iv6 = resultPosition + 3;
                        byte value$iv$iv10 = (byte) (128 | (codepoint & 63));
                        $this$encodeUTF8Stage2_u2dVm9B2pQ.put(index$iv$iv6, value$iv$iv10);
                        size = 4;
                    }
                }
            }
            resultPosition += size;
            index = index2;
        }
        return EncodeResult.m489constructorimpl(UShort.m791constructorimpl((short) (index - from)), UShort.m791constructorimpl((short) (resultPosition - dstOffset)));
    }

    private static final int charactersSize(int v) {
        boolean z = false;
        if (1 <= v && v < 128) {
            return 1;
        }
        if (128 <= v && v < 2048) {
            return 2;
        }
        if (2048 <= v && v < 65536) {
            return 3;
        }
        if (65536 <= v && v < 1114112) {
            z = true;
        }
        if (z) {
            return 4;
        }
        malformedCodePoint(v);
        throw new KotlinNothingValueException();
    }

    /* renamed from: putUtf8Char-62zg_DM, reason: not valid java name */
    public static final int m500putUtf8Char62zg_DM(ByteBuffer putUtf8Char, int offset, int v) {
        Intrinsics.checkNotNullParameter(putUtf8Char, "$this$putUtf8Char");
        if (v >= 0 && v < 128) {
            byte value$iv = (byte) v;
            putUtf8Char.put(offset, value$iv);
            return 1;
        }
        if (!(128 <= v && v < 2048)) {
            if (2048 <= v && v < 65536) {
                byte value$iv2 = (byte) (((v >> 12) & 15) | 224);
                putUtf8Char.put(offset, value$iv2);
                int index$iv = offset + 1;
                byte value$iv3 = (byte) (((v >> 6) & 63) | 128);
                putUtf8Char.put(index$iv, value$iv3);
                int index$iv2 = offset + 2;
                byte value$iv4 = (byte) (128 | (v & 63));
                putUtf8Char.put(index$iv2, value$iv4);
                return 3;
            }
            if (65536 <= v && v < 1114112) {
                byte value$iv5 = (byte) (((v >> 18) & 7) | 240);
                putUtf8Char.put(offset, value$iv5);
                int index$iv3 = offset + 1;
                byte value$iv6 = (byte) (((v >> 12) & 63) | 128);
                putUtf8Char.put(index$iv3, value$iv6);
                int index$iv4 = offset + 2;
                byte value$iv7 = (byte) (((v >> 6) & 63) | 128);
                putUtf8Char.put(index$iv4, value$iv7);
                int index$iv5 = offset + 3;
                byte value$iv8 = (byte) (128 | (v & 63));
                putUtf8Char.put(index$iv5, value$iv8);
                return 4;
            }
            malformedCodePoint(v);
            throw new KotlinNothingValueException();
        }
        byte value$iv9 = (byte) (((v >> 6) & 31) | 192);
        putUtf8Char.put(offset, value$iv9);
        int index$iv6 = offset + 1;
        byte value$iv10 = (byte) (128 | (v & 63));
        putUtf8Char.put(index$iv6, value$iv10);
        return 2;
    }

    public static final Void malformedByteCount(int byteCount) {
        throw new MalformedUTF8InputException("Expected " + byteCount + " more character bytes");
    }

    public static final Void malformedCodePoint(int value) {
        throw new IllegalArgumentException("Malformed code-point " + value + " found");
    }

    public static final boolean isBmpCodePoint(int cp) {
        return (cp >>> 16) == 0;
    }

    public static final boolean isValidCodePoint(int codePoint) {
        return codePoint <= MaxCodePoint;
    }

    public static final int lowSurrogate(int cp) {
        return (cp & 1023) + 56320;
    }

    public static final int highSurrogate(int cp) {
        return (cp >>> 10) + 55232;
    }

    public static final int codePoint(char high, char low) {
        int highValue = high - 55232;
        int lowValue = low - CharCompanionObject.MIN_LOW_SURROGATE;
        return (highValue << 10) | lowValue;
    }
}
