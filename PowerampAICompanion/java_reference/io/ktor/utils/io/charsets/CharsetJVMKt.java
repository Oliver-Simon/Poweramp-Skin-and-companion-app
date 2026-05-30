package io.ktor.utils.io.charsets;

import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CharsetJVM.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0015\u001a\u00020\u0001*\u00060\nj\u0002`\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001a2\u0006\u0010\u001b\u001a\u00020\u0001\u001a6\u0010\u001c\u001a\u00020\u0001*\u00060\nj\u0002`\u000b2\u0006\u0010\u0016\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060\u0019j\u0002`\u001a2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u001b\u001a\u00020\u0001H\u0000\u001a\u001e\u0010!\u001a\u00020\u0012*\u00060\nj\u0002`\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u0001\u001a \u0010#\u001a\u00020\u0012*\u00060\nj\u0002`\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u0001H\u0002\u001a \u0010$\u001a\u00020\u0012*\u00060\nj\u0002`\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u0001H\u0002\u001a\u0018\u0010%\u001a\u00020 *\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0018\u001a\u00020\u001dH\u0000\u001a0\u0010&\u001a\u00020\u0001*\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0016\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u001dH\u0000\u001a*\u0010*\u001a\u00020+*\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0016\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020\u00012\b\b\u0002\u0010)\u001a\u00020\u0001\u001a(\u0010,\u001a\u00020+*\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0016\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u0001H\u0002\u001a\u001e\u0010-\u001a\u00020.*\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0016\u001a\u00020/2\u0006\u0010\u0018\u001a\u000200\u001a\f\u00101\u001a\u00020.*\u000202H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001d\u0010\u0007\u001a\u00060\bj\u0002`\t*\u00060\nj\u0002`\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u001d\u0010\u0007\u001a\u00060\bj\u0002`\t*\u00060\u000ej\u0002`\u000f8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u0010\"\u0019\u0010\u0011\u001a\u00020\u0012*\u00060\bj\u0002`\t8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014*\n\u00103\"\u00020\b2\u00020\b*\n\u00104\"\u00020\n2\u00020\n*\n\u00105\"\u00020\u000e2\u00020\u000e*\n\u00106\"\u0002072\u000207¨\u00068"}, d2 = {"DECODE_CHAR_BUFFER_SIZE", "", "EmptyByteBuffer", "Ljava/nio/ByteBuffer;", "EmptyCharBuffer", "Ljava/nio/CharBuffer;", "kotlin.jvm.PlatformType", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "Ljava/nio/charset/CharsetDecoder;", "Lio/ktor/utils/io/charsets/CharsetDecoder;", "getCharset", "(Ljava/nio/charset/CharsetDecoder;)Ljava/nio/charset/Charset;", "Ljava/nio/charset/CharsetEncoder;", "Lio/ktor/utils/io/charsets/CharsetEncoder;", "(Ljava/nio/charset/CharsetEncoder;)Ljava/nio/charset/Charset;", "name", "", "getName", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "decode", "input", "Lio/ktor/utils/io/core/Input;", "dst", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "max", "decodeBuffer", "Lio/ktor/utils/io/core/Buffer;", "out", "lastBuffer", "", "decodeExactBytes", "inputLength", "decodeImplByteBuffer", "decodeImplSlow", "encodeComplete", "encodeImpl", "", "fromIndex", "toIndex", "encodeToByteArray", "", "encodeToByteArraySlow", "encodeUTF8", "", "Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/core/Output;", "throwExceptionWrapped", "Ljava/nio/charset/CoderResult;", "Charset", "CharsetDecoder", "CharsetEncoder", "Charsets", "Lkotlin/text/Charsets;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CharsetJVMKt {
    private static final int DECODE_CHAR_BUFFER_SIZE = 8192;
    private static final ByteBuffer EmptyByteBuffer;
    private static final CharBuffer EmptyCharBuffer = CharBuffer.allocate(0);

    public static /* synthetic */ void Charset$annotations() {
    }

    public static final String getName(Charset $this$name) {
        Intrinsics.checkNotNullParameter($this$name, "<this>");
        String name = $this$name.name();
        Intrinsics.checkNotNullExpressionValue(name, "name()");
        return name;
    }

    public static final Charset getCharset(CharsetEncoder $this$charset) {
        Intrinsics.checkNotNullParameter($this$charset, "<this>");
        Charset charset = $this$charset.charset();
        Intrinsics.checkNotNullExpressionValue(charset, "charset()");
        return charset;
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return encodeToByteArray(charsetEncoder, charSequence, i, i2);
    }

    public static final byte[] encodeToByteArray(CharsetEncoder $this$encodeToByteArray, CharSequence input, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter($this$encodeToByteArray, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        if (input instanceof String) {
            if (fromIndex == 0 && toIndex == input.length()) {
                byte[] bytes = ((String) input).getBytes($this$encodeToByteArray.charset());
                Intrinsics.checkNotNullExpressionValue(bytes, "input as java.lang.String).getBytes(charset())");
                return bytes;
            }
            String substring = ((String) input).substring(fromIndex, toIndex);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            Intrinsics.checkNotNull(substring, "null cannot be cast to non-null type java.lang.String");
            byte[] bytes2 = substring.getBytes($this$encodeToByteArray.charset());
            Intrinsics.checkNotNullExpressionValue(bytes2, "input.substring(fromInde…ring).getBytes(charset())");
            return bytes2;
        }
        return encodeToByteArraySlow($this$encodeToByteArray, input, fromIndex, toIndex);
    }

    private static final byte[] encodeToByteArraySlow(CharsetEncoder $this$encodeToByteArraySlow, CharSequence input, int fromIndex, int toIndex) {
        ByteBuffer result = $this$encodeToByteArraySlow.encode(CharBuffer.wrap(input, fromIndex, toIndex));
        byte[] existingArray = null;
        if (result.hasArray() && result.arrayOffset() == 0) {
            byte[] it = result.array();
            if (it.length == result.remaining()) {
                existingArray = it;
            }
        }
        if (existingArray != null) {
            return existingArray;
        }
        byte[] it2 = new byte[result.remaining()];
        result.get(it2);
        return it2;
    }

    public static final int encodeImpl(CharsetEncoder $this$encodeImpl, CharSequence input, int fromIndex, int toIndex, Buffer dst) {
        Intrinsics.checkNotNullParameter($this$encodeImpl, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(dst, "dst");
        CharBuffer cb = CharBuffer.wrap(input, fromIndex, toIndex);
        int before = cb.remaining();
        ByteBuffer memory$iv = dst.getMemory();
        int start$iv = dst.getWritePosition();
        int endExclusive$iv = dst.getLimit();
        ByteBuffer nioBuffer$iv = Memory.m245slice87lwejk(memory$iv, start$iv, endExclusive$iv - start$iv);
        CoderResult result = $this$encodeImpl.encode(cb, nioBuffer$iv, false);
        if (result.isMalformed() || result.isUnmappable()) {
            Intrinsics.checkNotNullExpressionValue(result, "result");
            throwExceptionWrapped(result);
        }
        if (!(nioBuffer$iv.limit() == endExclusive$iv - start$iv)) {
            throw new IllegalStateException("Buffer's limit change is not allowed".toString());
        }
        int rc$iv$iv = nioBuffer$iv.position();
        dst.commitWritten(rc$iv$iv);
        return before - cb.remaining();
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnoreUnknown(TypeUpdate.java:74)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:75)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    /* JADX WARN: Not initialized variable reg: 31, insn: 0x0433: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r31 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('readSize' int)]), block:B:211:0x0433 */
    public static final void encodeUTF8(java.nio.charset.CharsetEncoder r42, io.ktor.utils.io.core.ByteReadPacket r43, io.ktor.utils.io.core.Output r44) {
        /*
            Method dump skipped, instructions count: 1091
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.CharsetJVMKt.encodeUTF8(java.nio.charset.CharsetEncoder, io.ktor.utils.io.core.ByteReadPacket, io.ktor.utils.io.core.Output):void");
    }

    public static final boolean encodeComplete(CharsetEncoder $this$encodeComplete, Buffer dst) {
        boolean completed;
        Intrinsics.checkNotNullParameter($this$encodeComplete, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        ByteBuffer memory$iv = dst.getMemory();
        int start$iv = dst.getWritePosition();
        int endExclusive$iv = dst.getLimit();
        ByteBuffer nioBuffer$iv = Memory.m245slice87lwejk(memory$iv, start$iv, endExclusive$iv - start$iv);
        CoderResult result = $this$encodeComplete.encode(EmptyCharBuffer, nioBuffer$iv, true);
        if (result.isMalformed() || result.isUnmappable()) {
            Intrinsics.checkNotNullExpressionValue(result, "result");
            throwExceptionWrapped(result);
        }
        if (!result.isUnderflow()) {
            completed = false;
        } else {
            completed = true;
        }
        if (!(nioBuffer$iv.limit() == endExclusive$iv - start$iv)) {
            throw new IllegalStateException("Buffer's limit change is not allowed".toString());
        }
        int rc$iv$iv = nioBuffer$iv.position();
        dst.commitWritten(rc$iv$iv);
        return completed;
    }

    public static /* synthetic */ int decodeBuffer$default(CharsetDecoder charsetDecoder, Buffer buffer, Appendable appendable, boolean z, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = Integer.MAX_VALUE;
        }
        return decodeBuffer(charsetDecoder, buffer, appendable, z, i);
    }

    public static final int decodeBuffer(CharsetDecoder $this$decodeBuffer, Buffer input, Appendable out, boolean lastBuffer, int max) {
        int partSize;
        CharsetDecoder charsetDecoder = $this$decodeBuffer;
        int i = max;
        Intrinsics.checkNotNullParameter(charsetDecoder, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(out, "out");
        ByteBuffer memory$iv = input.getMemory();
        int start$iv = input.getReadPosition();
        int endExclusive$iv = input.getWritePosition();
        ByteBuffer nioBuffer$iv = Memory.m245slice87lwejk(memory$iv, start$iv, endExclusive$iv - start$iv);
        ChunkBuffer tmpBuffer = ChunkBuffer.INSTANCE.getPool().borrow();
        CharBuffer cb = tmpBuffer.getMemory().asCharBuffer();
        int charactersCopied = 0;
        while (nioBuffer$iv.hasRemaining() && charactersCopied < i) {
            try {
                int partSize2 = Math.min(cb.capacity(), max - charactersCopied);
                cb.clear();
                cb.limit(partSize2);
                try {
                    CoderResult result = charsetDecoder.decode(nioBuffer$iv, cb, lastBuffer);
                    if (!result.isMalformed() && !result.isUnmappable()) {
                        partSize = partSize2;
                        charactersCopied += partSize;
                        charsetDecoder = $this$decodeBuffer;
                        i = max;
                    }
                    partSize = partSize2;
                    Intrinsics.checkNotNullExpressionValue(result, "result");
                    throwExceptionWrapped(result);
                    charactersCopied += partSize;
                    charsetDecoder = $this$decodeBuffer;
                    i = max;
                } catch (Throwable th) {
                    th = th;
                    tmpBuffer.release(ChunkBuffer.INSTANCE.getPool());
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        tmpBuffer.release(ChunkBuffer.INSTANCE.getPool());
        if (!(nioBuffer$iv.limit() == endExclusive$iv - start$iv)) {
            throw new IllegalStateException("Buffer's limit change is not allowed".toString());
        }
        int rc$iv$iv = nioBuffer$iv.position();
        input.discardExact(rc$iv$iv);
        return charactersCopied;
    }

    public static final Charset getCharset(CharsetDecoder $this$charset) {
        Intrinsics.checkNotNullParameter($this$charset, "<this>");
        Charset charset = $this$charset.charset();
        Intrinsics.checkNotNull(charset);
        return charset;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e3 A[Catch: all -> 0x0111, TRY_LEAVE, TryCatch #1 {all -> 0x0111, blocks: (B:28:0x0046, B:34:0x0052, B:36:0x0087, B:37:0x008a, B:39:0x00aa, B:43:0x00c2, B:45:0x00c8, B:47:0x00ce, B:48:0x00d2, B:52:0x00e3, B:56:0x0103, B:57:0x0110, B:62:0x00b6), top: B:27:0x0046, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0100 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int decode(java.nio.charset.CharsetDecoder r32, io.ktor.utils.io.core.Input r33, java.lang.Appendable r34, int r35) {
        /*
            Method dump skipped, instructions count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.CharsetJVMKt.decode(java.nio.charset.CharsetDecoder, io.ktor.utils.io.core.Input, java.lang.Appendable, int):int");
    }

    public static final String decodeExactBytes(CharsetDecoder $this$decodeExactBytes, Input input, int inputLength) {
        Intrinsics.checkNotNullParameter($this$decodeExactBytes, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        if (inputLength == 0) {
            return "";
        }
        if (input.getHeadEndExclusive() - input.getHeadPosition() >= inputLength) {
            if (input.getHeadMemory().hasArray()) {
                ByteBuffer bb = input.getHeadMemory();
                byte[] bytes$iv = bb.array();
                Intrinsics.checkNotNullExpressionValue(bytes$iv, "bb.array()");
                int offset$iv = bb.arrayOffset() + bb.position() + input.getHead().getReadPosition();
                Charset charset$iv = $this$decodeExactBytes.charset();
                Intrinsics.checkNotNullExpressionValue(charset$iv, "charset()");
                String text = new String(bytes$iv, offset$iv, inputLength, charset$iv);
                input.discardExact(inputLength);
                return text;
            }
            return decodeImplByteBuffer($this$decodeExactBytes, input, inputLength);
        }
        return decodeImplSlow($this$decodeExactBytes, input, inputLength);
    }

    private static final String decodeImplByteBuffer(CharsetDecoder $this$decodeImplByteBuffer, Input input, int inputLength) {
        CharBuffer cb = CharBuffer.allocate(inputLength);
        ByteBuffer bb = Memory.m245slice87lwejk(input.getHeadMemory(), input.getHead().getReadPosition(), inputLength);
        CoderResult rc = $this$decodeImplByteBuffer.decode(bb, cb, true);
        if (rc.isMalformed() || rc.isUnmappable()) {
            Intrinsics.checkNotNullExpressionValue(rc, "rc");
            throwExceptionWrapped(rc);
        }
        cb.flip();
        input.discardExact(bb.position());
        String charBuffer = cb.toString();
        Intrinsics.checkNotNullExpressionValue(charBuffer, "cb.toString()");
        return charBuffer;
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00ff A[Catch: all -> 0x011f, TryCatch #6 {all -> 0x011f, blocks: (B:71:0x00f3, B:75:0x00ff, B:77:0x0110, B:78:0x011e), top: B:70:0x00f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0110 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.lang.String decodeImplSlow(java.nio.charset.CharsetDecoder r33, io.ktor.utils.io.core.Input r34, int r35) {
        /*
            Method dump skipped, instructions count: 534
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.CharsetJVMKt.decodeImplSlow(java.nio.charset.CharsetDecoder, io.ktor.utils.io.core.Input, int):java.lang.String");
    }

    private static final void throwExceptionWrapped(CoderResult $this$throwExceptionWrapped) {
        try {
            $this$throwExceptionWrapped.throwException();
        } catch (java.nio.charset.MalformedInputException original) {
            String message = original.getMessage();
            if (message == null) {
                message = "Failed to decode bytes";
            }
            throw new MalformedInputException(message);
        }
    }

    static {
        ByteBuffer allocate = ByteBuffer.allocate(0);
        Intrinsics.checkNotNull(allocate);
        EmptyByteBuffer = allocate;
    }
}
