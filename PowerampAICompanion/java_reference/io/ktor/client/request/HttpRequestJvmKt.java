package io.ktor.client.request;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLUtilsJvmKt;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpRequestJvm.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"invoke", "Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/client/request/HttpRequestBuilder$Companion;", TrackProviderConsts.COLUMN_URL, "Ljava/net/URL;", "Lio/ktor/http/URLBuilder;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpRequestJvmKt {
    public static final URLBuilder url(HttpRequestBuilder $this$url, URL url) {
        Intrinsics.checkNotNullParameter($this$url, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        return URLUtilsJvmKt.takeFrom($this$url.getUrl(), url);
    }

    public static final HttpRequestBuilder invoke(HttpRequestBuilder.Companion $this$invoke, URL url) {
        Intrinsics.checkNotNullParameter($this$invoke, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        HttpRequestBuilder $this$invoke_u24lambda_u240 = new HttpRequestBuilder();
        url($this$invoke_u24lambda_u240, url);
        return $this$invoke_u24lambda_u240;
    }
}
