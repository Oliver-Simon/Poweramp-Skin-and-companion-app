package io.ktor.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.NonCancellable;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import org.slf4j.LoggerFactory;

/* compiled from: Nonce.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u0002\u001a\b\u0010\u0017\u001a\u00020\u0015H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\r\"\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"INSECURE_NONCE_COUNT_FACTOR", "", "NonceGeneratorCoroutineName", "Lkotlinx/coroutines/CoroutineName;", "SECURE_NONCE_COUNT", "SECURE_RANDOM_PROVIDERS", "", "", "SECURE_RESEED_PERIOD", NonceKt.SHA1PRNG, "nonceGeneratorJob", "Lkotlinx/coroutines/Job;", "getNonceGeneratorJob$annotations", "()V", "seedChannel", "Lkotlinx/coroutines/channels/Channel;", "getSeedChannel", "()Lkotlinx/coroutines/channels/Channel;", "ensureNonceGeneratorRunning", "", "getInstanceOrNull", "Ljava/security/SecureRandom;", "name", "lookupSecureRandom", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class NonceKt {
    private static final int INSECURE_NONCE_COUNT_FACTOR = 4;
    private static final int SECURE_NONCE_COUNT = 8;
    private static final int SECURE_RESEED_PERIOD = 30000;
    private static final String SHA1PRNG = "SHA1PRNG";
    private static final List<String> SECURE_RANDOM_PROVIDERS = kotlin.collections.CollectionsKt.listOf((Object[]) new String[]{"NativePRNGNonBlocking", "WINDOWS-PRNG", "DRBG"});
    private static final Channel<String> seedChannel = ChannelKt.Channel$default(1024, null, null, 6, null);
    private static final CoroutineName NonceGeneratorCoroutineName = new CoroutineName("nonce-generator");
    private static final Job nonceGeneratorJob = BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getIO().plus(NonCancellable.INSTANCE).plus(NonceGeneratorCoroutineName), CoroutineStart.LAZY, new NonceKt$nonceGeneratorJob$1(null));

    private static /* synthetic */ void getNonceGeneratorJob$annotations() {
    }

    public static final Channel<String> getSeedChannel() {
        return seedChannel;
    }

    public static final void ensureNonceGeneratorRunning() {
        nonceGeneratorJob.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SecureRandom lookupSecureRandom() {
        SecureRandom it;
        String name = System.getProperty("io.ktor.random.secure.random.provider");
        if (name != null && (it = getInstanceOrNull(name)) != null) {
            return it;
        }
        Iterator<String> it2 = SECURE_RANDOM_PROVIDERS.iterator();
        while (it2.hasNext()) {
            SecureRandom it3 = getInstanceOrNull(it2.next());
            if (it3 != null) {
                return it3;
            }
        }
        LoggerFactory.getLogger("io.ktor.util.random").warn("None of the " + kotlin.collections.CollectionsKt.joinToString$default(SECURE_RANDOM_PROVIDERS, ", ", null, null, 0, null, null, 62, null) + " found, fallback to default");
        SecureRandom instanceOrNull$default = getInstanceOrNull$default(null, 1, null);
        if (instanceOrNull$default != null) {
            return instanceOrNull$default;
        }
        throw new IllegalStateException("No SecureRandom implementation found".toString());
    }

    private static final SecureRandom getInstanceOrNull(String name) {
        SecureRandom secureRandom;
        try {
            if (name != null) {
                secureRandom = SecureRandom.getInstance(name);
            } else {
                secureRandom = new SecureRandom();
            }
            return secureRandom;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    static /* synthetic */ SecureRandom getInstanceOrNull$default(String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return getInstanceOrNull(str);
    }
}
