package io.ktor.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.ContentDisposition;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Crypto.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001\u001a\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005\u001a\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\b\u001a\u001d\u0010\u000b\u001a\u00020\u0005*\u00020\f2\u0006\u0010\t\u001a\u00020\u0005H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a+\u0010\u000b\u001a\u00020\u0005*\u00020\f2\u0006\u0010\u000e\u001a\u00020\b2\f\b\u0002\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0012\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"NONCE_SIZE_IN_BYTES", "", "digits", "", "generateNonce", "", ContentDisposition.Parameters.Size, "hex", "", "bytes", "s", "build", "Lio/ktor/util/Digest;", "(Lio/ktor/util/Digest;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", TypedValues.Custom.S_STRING, HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "(Lio/ktor/util/Digest;Ljava/lang/String;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-utils"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "io/ktor/util/CryptoKt")
/* loaded from: classes9.dex */
public final /* synthetic */ class CryptoKt__CryptoKt {
    private static final char[] digits = CharsetKt.toCharArray("0123456789abcdef");

    public static final String hex(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        char[] result = new char[bytes.length * 2];
        int resultIndex = 0;
        char[] digits2 = digits;
        for (byte b : bytes) {
            int b2 = b & 255;
            int resultIndex2 = resultIndex + 1;
            result[resultIndex] = digits2[b2 >> 4];
            resultIndex = resultIndex2 + 1;
            result[resultIndex2] = digits2[b2 & 15];
        }
        return StringsKt.concatToString(result);
    }

    public static final byte[] hex(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        byte[] result = new byte[s.length() / 2];
        int length = result.length;
        for (int idx = 0; idx < length; idx++) {
            int srcIdx = idx * 2;
            int high = Integer.parseInt(String.valueOf(s.charAt(srcIdx)), CharsKt.checkRadix(16)) << 4;
            int low = Integer.parseInt(String.valueOf(s.charAt(srcIdx + 1)), CharsKt.checkRadix(16));
            result[idx] = (byte) (high | low);
        }
        return result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final byte[] generateNonce(int i) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        while (bytePacketBuilder.getSize() < i) {
            try {
                io.ktor.utils.io.core.StringsKt.writeText$default(bytePacketBuilder, CryptoKt.generateNonce(), 0, 0, (Charset) null, 14, (Object) null);
            } catch (Throwable th) {
                bytePacketBuilder.release();
                throw th;
            }
        }
        return io.ktor.utils.io.core.StringsKt.readBytes(bytePacketBuilder.build(), i);
    }

    @InternalAPI
    public static final Object build(Digest $this$build, byte[] bytes, Continuation<? super byte[]> continuation) {
        $this$build.plusAssign(bytes);
        return $this$build.build(continuation);
    }

    public static /* synthetic */ Object build$default(Digest digest, String str, Charset charset, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return CryptoKt.build(digest, str, charset, continuation);
    }

    @InternalAPI
    public static final Object build(Digest $this$build, String string, Charset charset, Continuation<? super byte[]> continuation) {
        byte[] encodeToByteArray;
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            encodeToByteArray = StringsKt.encodeToByteArray(string);
        } else {
            CharsetEncoder newEncoder = charset.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
            encodeToByteArray = CharsetJVMKt.encodeToByteArray(newEncoder, string, 0, string.length());
        }
        $this$build.plusAssign(encodeToByteArray);
        return $this$build.build(continuation);
    }
}
