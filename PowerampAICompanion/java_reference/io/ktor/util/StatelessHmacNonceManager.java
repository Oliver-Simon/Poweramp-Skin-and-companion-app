package io.ktor.util;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: StatelessHmacNonceManager.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B3\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\u0002\u0010\nB1\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\u0002\u0010\rJ\u0011\u0010\u0018\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/util/StatelessHmacNonceManager;", "Lio/ktor/util/NonceManager;", "key", "", "algorithm", "", "timeoutMillis", "", "nonceGenerator", "Lkotlin/Function0;", "([BLjava/lang/String;JLkotlin/jvm/functions/Function0;)V", "keySpec", "Ljavax/crypto/spec/SecretKeySpec;", "(Ljavax/crypto/spec/SecretKeySpec;Ljava/lang/String;JLkotlin/jvm/functions/Function0;)V", "getAlgorithm", "()Ljava/lang/String;", "getKeySpec", "()Ljavax/crypto/spec/SecretKeySpec;", "macLength", "", "getNonceGenerator", "()Lkotlin/jvm/functions/Function0;", "getTimeoutMillis", "()J", "newNonce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyNonce", "", "nonce", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class StatelessHmacNonceManager implements NonceManager {
    private final String algorithm;
    private final SecretKeySpec keySpec;
    private final int macLength;
    private final Function0<String> nonceGenerator;
    private final long timeoutMillis;

    public StatelessHmacNonceManager(SecretKeySpec keySpec, String algorithm, long timeoutMillis, Function0<String> nonceGenerator) {
        Intrinsics.checkNotNullParameter(keySpec, "keySpec");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(nonceGenerator, "nonceGenerator");
        this.keySpec = keySpec;
        this.algorithm = algorithm;
        this.timeoutMillis = timeoutMillis;
        this.nonceGenerator = nonceGenerator;
        Mac mac = Mac.getInstance(this.algorithm);
        mac.init(this.keySpec);
        this.macLength = mac.getMacLength();
    }

    public /* synthetic */ StatelessHmacNonceManager(SecretKeySpec secretKeySpec, String str, long j, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(secretKeySpec, (i & 2) != 0 ? "HmacSHA256" : str, (i & 4) != 0 ? 60000L : j, (Function0<String>) ((i & 8) != 0 ? new Function0<String>() { // from class: io.ktor.util.StatelessHmacNonceManager.1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return CryptoKt.generateNonce();
            }
        } : function0));
    }

    public final SecretKeySpec getKeySpec() {
        return this.keySpec;
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    public final long getTimeoutMillis() {
        return this.timeoutMillis;
    }

    public final Function0<String> getNonceGenerator() {
        return this.nonceGenerator;
    }

    public /* synthetic */ StatelessHmacNonceManager(byte[] bArr, String str, long j, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, (i & 2) != 0 ? "HmacSHA256" : str, (i & 4) != 0 ? 60000L : j, (Function0<String>) ((i & 8) != 0 ? new Function0<String>() { // from class: io.ktor.util.StatelessHmacNonceManager.2
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return CryptoKt.generateNonce();
            }
        } : function0));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StatelessHmacNonceManager(byte[] key, String algorithm, long timeoutMillis, Function0<String> nonceGenerator) {
        this(new SecretKeySpec(key, algorithm), algorithm, timeoutMillis, nonceGenerator);
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(nonceGenerator, "nonceGenerator");
    }

    @Override // io.ktor.util.NonceManager
    public Object newNonce(Continuation<? super String> continuation) {
        String random = this.nonceGenerator.invoke();
        String l = Long.toString(System.nanoTime(), CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(l, "toString(this, checkRadix(radix))");
        String time = StringsKt.padStart(l, 16, '0');
        Mac $this$newNonce_u24lambda_u241 = Mac.getInstance(this.algorithm);
        $this$newNonce_u24lambda_u241.init(this.keySpec);
        byte[] bytes = (random + AbstractJsonLexerKt.COLON + time).getBytes(Charsets.ISO_8859_1);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        $this$newNonce_u24lambda_u241.update(bytes);
        byte[] doFinal = $this$newNonce_u24lambda_u241.doFinal();
        Intrinsics.checkNotNullExpressionValue(doFinal, "getInstance(algorithm).a…)\n            }.doFinal()");
        String mac = CryptoKt.hex(doFinal);
        return random + '+' + time + '+' + mac;
    }

    @Override // io.ktor.util.NonceManager
    public Object verifyNonce(String nonce, Continuation<? super Boolean> continuation) {
        List parts = StringsKt.split$default((CharSequence) nonce, new char[]{'+'}, false, 0, 6, (Object) null);
        if (parts.size() != 3) {
            return Boxing.boxBoolean(false);
        }
        String random = (String) parts.get(0);
        String time = (String) parts.get(1);
        String mac = (String) parts.get(2);
        if (random.length() >= 8 && mac.length() == this.macLength * 2 && time.length() == 16) {
            long nanoTime = Long.parseLong(time, CharsKt.checkRadix(16));
            if (TimeUnit.MILLISECONDS.toNanos(this.timeoutMillis) + nanoTime < System.nanoTime()) {
                return Boxing.boxBoolean(false);
            }
            Mac $this$verifyNonce_u24lambda_u242 = Mac.getInstance(this.algorithm);
            $this$verifyNonce_u24lambda_u242.init(this.keySpec);
            byte[] bytes = (random + AbstractJsonLexerKt.COLON + time).getBytes(Charsets.ISO_8859_1);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            $this$verifyNonce_u24lambda_u242.update(bytes);
            byte[] doFinal = $this$verifyNonce_u24lambda_u242.doFinal();
            Intrinsics.checkNotNullExpressionValue(doFinal, "getInstance(algorithm).a…)\n            }.doFinal()");
            String computedMac = CryptoKt.hex(doFinal);
            int validCount = 0;
            int min = Math.min(computedMac.length(), mac.length());
            for (int i = 0; i < min; i++) {
                if (computedMac.charAt(i) == mac.charAt(i)) {
                    validCount++;
                }
            }
            int i2 = this.macLength;
            return Boxing.boxBoolean(validCount == i2 * 2);
        }
        return Boxing.boxBoolean(false);
    }
}
