package io.ktor.client.engine.okhttp;

import io.ktor.client.plugins.HttpTimeout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import okhttp3.OkHttpClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: OkHttpEngine.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public /* synthetic */ class OkHttpEngine$clientCache$1 extends FunctionReferenceImpl implements Function1<HttpTimeout.HttpTimeoutCapabilityConfiguration, OkHttpClient> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpEngine$clientCache$1(Object obj) {
        super(1, obj, OkHttpEngine.class, "createOkHttpClient", "createOkHttpClient(Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;)Lokhttp3/OkHttpClient;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final OkHttpClient invoke(HttpTimeout.HttpTimeoutCapabilityConfiguration p0) {
        OkHttpClient createOkHttpClient;
        createOkHttpClient = ((OkHttpEngine) this.receiver).createOkHttpClient(p0);
        return createOkHttpClient;
    }
}
