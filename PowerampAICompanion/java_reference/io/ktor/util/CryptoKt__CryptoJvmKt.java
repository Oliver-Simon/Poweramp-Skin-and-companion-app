package io.ktor.util;

import io.ktor.http.auth.AuthScheme;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.channels.ChannelResult;

/* compiled from: CryptoJvm.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0006\u0010\u0004\u001a\u00020\u0003\u001a\r\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0002\b\u0006\u001a1\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\fH\u0002¢\u0006\u0002\b\r\u001a=\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\n\u001a\u00020\u00032!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000f\u0012\b\b\u0002\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00030\f\u001a\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b¨\u0006\u0013"}, d2 = {AuthScheme.Digest, "Lio/ktor/util/Digest;", "name", "", "generateNonce", "generateNonceBlocking", "generateNonceBlocking$CryptoKt__CryptoJvmKt", "getDigest", "", "text", "algorithm", "salt", "Lkotlin/Function1;", "getDigest$CryptoKt__CryptoJvmKt", "getDigestFunction", "Lkotlin/ParameterName;", "value", "sha1", "bytes", "ktor-utils"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "io/ktor/util/CryptoKt")
/* loaded from: classes9.dex */
final /* synthetic */ class CryptoKt__CryptoJvmKt {
    public static final Function1<String, byte[]> getDigestFunction(final String algorithm, final Function1<? super String, String> salt) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(salt, "salt");
        return new Function1<String, byte[]>() { // from class: io.ktor.util.CryptoKt__CryptoJvmKt$getDigestFunction$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final byte[] invoke(String e) {
                byte[] digest$CryptoKt__CryptoJvmKt;
                Intrinsics.checkNotNullParameter(e, "e");
                digest$CryptoKt__CryptoJvmKt = CryptoKt__CryptoJvmKt.getDigest$CryptoKt__CryptoJvmKt(e, algorithm, salt);
                return digest$CryptoKt__CryptoJvmKt;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] getDigest$CryptoKt__CryptoJvmKt(String text, String algorithm, Function1<? super String, String> function1) {
        MessageDigest $this$getDigest_u24lambda_u240 = MessageDigest.getInstance(algorithm);
        byte[] bytes = function1.invoke(text).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        $this$getDigest_u24lambda_u240.update(bytes);
        byte[] bytes2 = text.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        byte[] digest = $this$getDigest_u24lambda_u240.digest(bytes2);
        Intrinsics.checkNotNullExpressionValue(digest, "with(MessageDigest.getIn…text.toByteArray())\n    }");
        return digest;
    }

    public static final byte[] sha1(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        byte[] digest = MessageDigest.getInstance("SHA1").digest(bytes);
        Intrinsics.checkNotNullExpressionValue(digest, "getInstance(\"SHA1\").digest(bytes)");
        return digest;
    }

    public static final Digest Digest(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        MessageDigest messageDigest = MessageDigest.getInstance(name);
        Intrinsics.checkNotNullExpressionValue(messageDigest, "getInstance(name)");
        return DigestImpl.m208boximpl(DigestImpl.m210constructorimpl(messageDigest));
    }

    public static final String generateNonce() {
        String nonce = (String) ChannelResult.m2025getOrNullimpl(NonceKt.getSeedChannel().mo2015tryReceivePtdJZtk());
        return nonce != null ? nonce : generateNonceBlocking$CryptoKt__CryptoJvmKt();
    }

    private static final String generateNonceBlocking$CryptoKt__CryptoJvmKt() {
        NonceKt.ensureNonceGeneratorRunning();
        return (String) BuildersKt.runBlocking$default(null, new CryptoKt__CryptoJvmKt$generateNonceBlocking$1(null), 1, null);
    }
}
