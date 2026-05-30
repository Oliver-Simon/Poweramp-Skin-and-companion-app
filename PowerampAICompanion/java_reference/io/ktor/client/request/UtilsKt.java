package io.ktor.client.request;

import io.ktor.http.ContentType;
import io.ktor.http.Cookie;
import io.ktor.http.CookieKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.util.Base64Kt;
import io.ktor.util.date.GMTDate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: utils.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u0012\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001\u001a\u0012\u0010\u0016\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0001\u001at\u0010\u0018\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u001a\u001a\u00020\b2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010#\u001a\u001c\u0010$\u001a\u00020\u000f*\u00020\u00102\u0006\u0010%\u001a\u00020\u00012\b\u0010\u0000\u001a\u0004\u0018\u00010&\u001a\u001c\u0010'\u001a\u00020\u000f*\u00020\u00032\u0006\u0010%\u001a\u00020\u00012\b\u0010\u0000\u001a\u0004\u0018\u00010&\"(\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"(\u0010\t\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006("}, d2 = {"value", "", "host", "Lio/ktor/client/request/HttpRequestBuilder;", "getHost", "(Lio/ktor/client/request/HttpRequestBuilder;)Ljava/lang/String;", "setHost", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/String;)V", "", "port", "getPort", "(Lio/ktor/client/request/HttpRequestBuilder;)I", "setPort", "(Lio/ktor/client/request/HttpRequestBuilder;I)V", "accept", "", "Lio/ktor/http/HttpMessageBuilder;", "contentType", "Lio/ktor/http/ContentType;", "basicAuth", "username", "password", "bearerAuth", "token", "cookie", "name", "maxAge", "expires", "Lio/ktor/util/date/GMTDate;", "domain", "path", "secure", "", "httpOnly", "extensions", "", "header", "key", "", "parameter", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UtilsKt {
    public static final String getHost(HttpRequestBuilder $this$host) {
        Intrinsics.checkNotNullParameter($this$host, "<this>");
        return $this$host.getUrl().getHost();
    }

    public static final void setHost(HttpRequestBuilder $this$host, String value) {
        Intrinsics.checkNotNullParameter($this$host, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        $this$host.getUrl().setHost(value);
    }

    public static final int getPort(HttpRequestBuilder $this$port) {
        Intrinsics.checkNotNullParameter($this$port, "<this>");
        return $this$port.getUrl().getPort();
    }

    public static final void setPort(HttpRequestBuilder $this$port, int value) {
        Intrinsics.checkNotNullParameter($this$port, "<this>");
        $this$port.getUrl().setPort(value);
    }

    public static final void header(HttpMessageBuilder $this$header, String key, Object value) {
        Intrinsics.checkNotNullParameter($this$header, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (value != null) {
            $this$header.getHeaders().append(key, value.toString());
            Object it = Unit.INSTANCE;
        }
    }

    public static /* synthetic */ void cookie$default(HttpMessageBuilder httpMessageBuilder, String str, String str2, int i, GMTDate gMTDate, String str3, String str4, boolean z, boolean z2, Map map, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        if ((i2 & 8) != 0) {
            gMTDate = null;
        }
        if ((i2 & 16) != 0) {
            str3 = null;
        }
        if ((i2 & 32) != 0) {
            str4 = null;
        }
        if ((i2 & 64) != 0) {
            z = false;
        }
        if ((i2 & 128) != 0) {
            z2 = false;
        }
        if ((i2 & 256) != 0) {
            map = MapsKt.emptyMap();
        }
        cookie(httpMessageBuilder, str, str2, i, gMTDate, str3, str4, z, z2, map);
    }

    public static final void cookie(HttpMessageBuilder $this$cookie, String name, String value, int maxAge, GMTDate expires, String domain, String path, boolean secure, boolean httpOnly, Map<String, String> extensions) {
        Intrinsics.checkNotNullParameter($this$cookie, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(extensions, "extensions");
        Cookie p0 = new Cookie(name, value, null, maxAge, expires, domain, path, secure, httpOnly, extensions, 4, null);
        String renderedCookie = CookieKt.renderCookieHeader(p0);
        if (!$this$cookie.getHeaders().contains(HttpHeaders.INSTANCE.getCookie())) {
            $this$cookie.getHeaders().append(HttpHeaders.INSTANCE.getCookie(), renderedCookie);
        } else {
            $this$cookie.getHeaders().set(HttpHeaders.INSTANCE.getCookie(), $this$cookie.getHeaders().get(HttpHeaders.INSTANCE.getCookie()) + "; " + renderedCookie);
        }
    }

    public static final void parameter(HttpRequestBuilder $this$parameter, String key, Object value) {
        Intrinsics.checkNotNullParameter($this$parameter, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (value != null) {
            $this$parameter.getUrl().getParameters().append(key, value.toString());
            Object it = Unit.INSTANCE;
        }
    }

    public static final void accept(HttpMessageBuilder $this$accept, ContentType contentType) {
        Intrinsics.checkNotNullParameter($this$accept, "<this>");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        $this$accept.getHeaders().append(HttpHeaders.INSTANCE.getAccept(), contentType.toString());
    }

    public static final void basicAuth(HttpMessageBuilder $this$basicAuth, String username, String password) {
        Intrinsics.checkNotNullParameter($this$basicAuth, "<this>");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        header($this$basicAuth, HttpHeaders.INSTANCE.getAuthorization(), "Basic " + Base64Kt.encodeBase64(username + AbstractJsonLexerKt.COLON + password));
    }

    public static final void bearerAuth(HttpMessageBuilder $this$bearerAuth, String token) {
        Intrinsics.checkNotNullParameter($this$bearerAuth, "<this>");
        Intrinsics.checkNotNullParameter(token, "token");
        header($this$bearerAuth, HttpHeaders.INSTANCE.getAuthorization(), "Bearer " + token);
    }
}
