package io.ktor.client.plugins.cookies;

import io.ktor.http.Cookie;
import io.ktor.http.IpParserKt;
import io.ktor.http.URLProtocolKt;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.util.TextKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CookiesStorage.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0014\u0010\b\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u0014\u0010\u000b\u001a\u00020\f*\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"addCookie", "", "Lio/ktor/client/plugins/cookies/CookiesStorage;", "urlString", "", "cookie", "Lio/ktor/http/Cookie;", "(Lio/ktor/client/plugins/cookies/CookiesStorage;Ljava/lang/String;Lio/ktor/http/Cookie;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fillDefaults", "requestUrl", "Lio/ktor/http/Url;", "matches", "", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CookiesStorageKt {
    public static final Object addCookie(CookiesStorage $this$addCookie, String urlString, Cookie cookie, Continuation<? super Unit> continuation) {
        Object addCookie = $this$addCookie.addCookie(URLUtilsKt.Url(urlString), cookie, continuation);
        return addCookie == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? addCookie : Unit.INSTANCE;
    }

    public static final boolean matches(Cookie $this$matches, Url requestUrl) {
        String lowerCasePreservingASCIIRules;
        String domain;
        Intrinsics.checkNotNullParameter($this$matches, "<this>");
        Intrinsics.checkNotNullParameter(requestUrl, "requestUrl");
        String domain2 = $this$matches.getDomain();
        if (domain2 == null || (lowerCasePreservingASCIIRules = TextKt.toLowerCasePreservingASCIIRules(domain2)) == null || (domain = StringsKt.trimStart(lowerCasePreservingASCIIRules, '.')) == null) {
            throw new IllegalStateException("Domain field should have the default value".toString());
        }
        $this$matches.getPath();
        String current = $this$matches.getPath();
        if (current == null) {
            throw new IllegalStateException("Path field should have the default value".toString());
        }
        if (!StringsKt.endsWith$default((CharSequence) current, '/', false, 2, (Object) null)) {
            current = $this$matches.getPath() + '/';
        }
        String host = TextKt.toLowerCasePreservingASCIIRules(requestUrl.getHost());
        String pathInRequest = requestUrl.getEncodedPath();
        if (!StringsKt.endsWith$default((CharSequence) pathInRequest, '/', false, 2, (Object) null)) {
            pathInRequest = pathInRequest + '/';
        }
        if (!Intrinsics.areEqual(host, domain) && (IpParserKt.hostIsIp(host) || !StringsKt.endsWith$default(host, '.' + domain, false, 2, (Object) null))) {
            return false;
        }
        if (Intrinsics.areEqual(current, "/") || Intrinsics.areEqual(pathInRequest, current) || StringsKt.startsWith$default(pathInRequest, current, false, 2, (Object) null)) {
            return !$this$matches.getSecure() || URLProtocolKt.isSecure(requestUrl.getProtocol());
        }
        return false;
    }

    public static final Cookie fillDefaults(Cookie $this$fillDefaults, Url requestUrl) {
        boolean z;
        boolean z2;
        Cookie result;
        Intrinsics.checkNotNullParameter($this$fillDefaults, "<this>");
        Intrinsics.checkNotNullParameter(requestUrl, "requestUrl");
        String path = $this$fillDefaults.getPath();
        if (!(path != null && StringsKt.startsWith$default(path, "/", false, 2, (Object) null))) {
            z = true;
            z2 = false;
            Cookie result2 = Cookie.copy$default($this$fillDefaults, null, null, null, 0, null, null, requestUrl.getEncodedPath(), false, false, null, 959, null);
            result = result2;
        } else {
            z = true;
            z2 = false;
            result = $this$fillDefaults;
        }
        String domain = result.getDomain();
        if ((domain == null || StringsKt.isBlank(domain)) ? z : z2) {
            return Cookie.copy$default(result, null, null, null, 0, null, requestUrl.getHost(), null, false, false, null, 991, null);
        }
        return result;
    }
}
