package io.ktor.http;

import androidx.core.app.FrameMetricsAggregator;
import com.google.common.net.HttpHeaders;
import io.ktor.http.URLBuilder;
import io.ktor.http.Url;
import java.net.URI;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: URLBuilderJvm.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0086\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\t"}, d2 = {HttpHeaders.ReferrerPolicyValues.ORIGIN, "", "Lio/ktor/http/URLBuilder$Companion;", "getOrigin", "(Lio/ktor/http/URLBuilder$Companion;)Ljava/lang/String;", "invoke", "Lio/ktor/http/Url;", "Lio/ktor/http/Url$Companion;", "fullUrl", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class URLBuilderJvmKt {
    public static final Url invoke(Url.Companion $this$invoke, String fullUrl) {
        Intrinsics.checkNotNullParameter($this$invoke, "<this>");
        Intrinsics.checkNotNullParameter(fullUrl, "fullUrl");
        URLBuilder $this$invoke_u24lambda_u240 = new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
        URLUtilsJvmKt.takeFrom($this$invoke_u24lambda_u240, new URI(fullUrl));
        return $this$invoke_u24lambda_u240.build();
    }

    public static final String getOrigin(URLBuilder.Companion $this$origin) {
        Intrinsics.checkNotNullParameter($this$origin, "<this>");
        return "http://localhost";
    }
}
