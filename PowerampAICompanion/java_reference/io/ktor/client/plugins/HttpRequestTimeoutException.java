package io.ktor.client.plugins;

import androidx.core.os.EnvironmentCompat;
import com.maxmpz.poweramp.player.TrackProviderConsts;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpTimeout.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0017\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lio/ktor/client/plugins/HttpRequestTimeoutException;", "Ljava/io/IOException;", "Lio/ktor/utils/io/errors/IOException;", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "Lio/ktor/client/request/HttpRequestData;", "(Lio/ktor/client/request/HttpRequestData;)V", TrackProviderConsts.COLUMN_URL, "", "timeoutMillis", "", "(Ljava/lang/String;Ljava/lang/Long;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpRequestTimeoutException extends IOException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpRequestTimeoutException(String url, Long timeoutMillis) {
        super("Request timeout has expired [url=" + url + ", request_timeout=" + (timeoutMillis == null ? EnvironmentCompat.MEDIA_UNKNOWN : timeoutMillis) + " ms]");
        Intrinsics.checkNotNullParameter(url, "url");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public HttpRequestTimeoutException(io.ktor.client.request.HttpRequestBuilder r3) {
        /*
            r2 = this;
            java.lang.String r0 = "request"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            io.ktor.http.URLBuilder r0 = r3.getUrl()
            java.lang.String r0 = r0.buildString()
            io.ktor.client.plugins.HttpTimeout$Plugin r1 = io.ktor.client.plugins.HttpTimeout.INSTANCE
            io.ktor.client.engine.HttpClientEngineCapability r1 = (io.ktor.client.engine.HttpClientEngineCapability) r1
            java.lang.Object r1 = r3.getCapabilityOrNull(r1)
            io.ktor.client.plugins.HttpTimeout$HttpTimeoutCapabilityConfiguration r1 = (io.ktor.client.plugins.HttpTimeout.HttpTimeoutCapabilityConfiguration) r1
            if (r1 == 0) goto L1f
            java.lang.Long r1 = r1.get_requestTimeoutMillis()
            goto L20
        L1f:
            r1 = 0
        L20:
            r2.<init>(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpRequestTimeoutException.<init>(io.ktor.client.request.HttpRequestBuilder):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public HttpRequestTimeoutException(io.ktor.client.request.HttpRequestData r3) {
        /*
            r2 = this;
            java.lang.String r0 = "request"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            io.ktor.http.Url r0 = r3.getUrl()
            java.lang.String r0 = r0.getUrlString()
            io.ktor.client.plugins.HttpTimeout$Plugin r1 = io.ktor.client.plugins.HttpTimeout.INSTANCE
            io.ktor.client.engine.HttpClientEngineCapability r1 = (io.ktor.client.engine.HttpClientEngineCapability) r1
            java.lang.Object r1 = r3.getCapabilityOrNull(r1)
            io.ktor.client.plugins.HttpTimeout$HttpTimeoutCapabilityConfiguration r1 = (io.ktor.client.plugins.HttpTimeout.HttpTimeoutCapabilityConfiguration) r1
            if (r1 == 0) goto L1f
            java.lang.Long r1 = r1.get_requestTimeoutMillis()
            goto L20
        L1f:
            r1 = 0
        L20:
            r2.<init>(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpRequestTimeoutException.<init>(io.ktor.client.request.HttpRequestData):void");
    }
}
