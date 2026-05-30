package io.ktor.client.plugins;

import io.ktor.client.HttpClientConfig;
import io.ktor.client.plugins.UserAgent;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

/* compiled from: UserAgent.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0003\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0005\u001a\u000e\u0010\u0006\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0005\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "BrowserUserAgent", "", "Lio/ktor/client/HttpClientConfig;", "CurlUserAgent", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UserAgentKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.UserAgent");

    public static final /* synthetic */ Logger access$getLOGGER$p() {
        return LOGGER;
    }

    public static final void BrowserUserAgent(HttpClientConfig<?> httpClientConfig) {
        Intrinsics.checkNotNullParameter(httpClientConfig, "<this>");
        httpClientConfig.install(UserAgent.INSTANCE, new Function1<UserAgent.Config, Unit>() { // from class: io.ktor.client.plugins.UserAgentKt$BrowserUserAgent$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserAgent.Config config) {
                invoke2(config);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserAgent.Config install) {
                Intrinsics.checkNotNullParameter(install, "$this$install");
                install.setAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/70.0.3538.77 Chrome/70.0.3538.77 Safari/537.36");
            }
        });
    }

    public static final void CurlUserAgent(HttpClientConfig<?> httpClientConfig) {
        Intrinsics.checkNotNullParameter(httpClientConfig, "<this>");
        httpClientConfig.install(UserAgent.INSTANCE, new Function1<UserAgent.Config, Unit>() { // from class: io.ktor.client.plugins.UserAgentKt$CurlUserAgent$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserAgent.Config config) {
                invoke2(config);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserAgent.Config install) {
                Intrinsics.checkNotNullParameter(install, "$this$install");
                install.setAgent("curl/7.61.0");
            }
        });
    }
}
