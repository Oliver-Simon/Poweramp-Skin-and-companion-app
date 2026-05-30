package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

/* compiled from: DefaultTransform.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "defaultTransformers", "", "Lio/ktor/client/HttpClient;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DefaultTransformKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.defaultTransformers");

    public static final void defaultTransformers(HttpClient $this$defaultTransformers) {
        Intrinsics.checkNotNullParameter($this$defaultTransformers, "<this>");
        $this$defaultTransformers.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getRender(), new DefaultTransformKt$defaultTransformers$1(null));
        $this$defaultTransformers.getResponsePipeline().intercept(HttpResponsePipeline.INSTANCE.getParse(), new DefaultTransformKt$defaultTransformers$2(null));
        DefaultTransformersJvmKt.platformResponseDefaultTransformers($this$defaultTransformers);
    }
}
