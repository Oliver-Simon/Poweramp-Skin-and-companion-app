package io.ktor.http;

import com.maxmpz.poweramp.player.PowerampAPI;
import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: HttpMessageProperties.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003\u001a\u0012\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006*\u00020\u0003\u001a\u0012\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006*\u00020\u0007\u001a\u001f\u0010\u0004\u001a\u0004\u0018\u00010\b*\u00020\u00072\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0007¢\u0006\u0002\u0010\t\u001a\u0011\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u0003¢\u0006\u0002\u0010\f\u001a\u0011\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u0007¢\u0006\u0002\u0010\r\u001a\u0014\u0010\n\u001a\u00020\b*\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\f\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u0003\u001a\f\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u0007\u001a\u0012\u0010\u0010\u001a\u00020\b*\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0011\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0001*\u00020\u0007\u001a\f\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u0003\u001a\f\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u0007\u001a\u0012\u0010\u0017\u001a\u00020\b*\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0016\u001a\u0012\u0010\u0019\u001a\u00020\b*\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u000f\u001a\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u0001*\u00020\u0003\u001a\u0012\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u0001*\u00020\u0016H\u0000\u001a\u0012\u0010\u001d\u001a\u00020\b*\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0016\u001a\u0012\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0001*\u00020\u0003\u001a\u0012\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0001*\u00020\u0007¨\u0006 "}, d2 = {"cacheControl", "", "Lio/ktor/http/HeaderValue;", "Lio/ktor/http/HttpMessage;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "Lio/ktor/http/HttpMessageBuilder;", "", "(Lio/ktor/http/HttpMessageBuilder;Ljava/nio/charset/Charset;)Lkotlin/Unit;", "contentLength", "", "(Lio/ktor/http/HttpMessage;)Ljava/lang/Long;", "(Lio/ktor/http/HttpMessageBuilder;)Ljava/lang/Long;", "length", "", "contentType", "Lio/ktor/http/ContentType;", "type", TrackProviderConsts.COLUMN_COOKIES, "Lio/ktor/http/Cookie;", "etag", "", "ifNoneMatch", "value", "maxAge", PowerampAPI.EXTRA_SECONDS, "setCookie", "splitSetCookieHeader", "userAgent", "content", "vary", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpMessagePropertiesKt {
    public static final void contentType(HttpMessageBuilder $this$contentType, ContentType type) {
        Intrinsics.checkNotNullParameter($this$contentType, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        $this$contentType.getHeaders().set(HttpHeaders.INSTANCE.getContentType(), type.toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Content-Length is controlled by underlying engine. Don't specify it explicitly.")
    public static final void contentLength(HttpMessageBuilder $this$contentLength, int length) {
        Intrinsics.checkNotNullParameter($this$contentLength, "<this>");
        $this$contentLength.getHeaders().set(HttpHeaders.INSTANCE.getContentLength(), String.valueOf(length));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use content with particular content type and charset instead")
    public static final Unit charset(HttpMessageBuilder $this$charset, Charset charset) {
        Intrinsics.checkNotNullParameter($this$charset, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        ContentType it = contentType($this$charset);
        if (it == null) {
            return null;
        }
        contentType($this$charset, ContentTypesKt.withCharset(it, charset));
        return Unit.INSTANCE;
    }

    public static final void maxAge(HttpMessageBuilder $this$maxAge, int seconds) {
        Intrinsics.checkNotNullParameter($this$maxAge, "<this>");
        $this$maxAge.getHeaders().append(HttpHeaders.INSTANCE.getCacheControl(), "max-age=" + seconds);
    }

    public static final void ifNoneMatch(HttpMessageBuilder $this$ifNoneMatch, String value) {
        Intrinsics.checkNotNullParameter($this$ifNoneMatch, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        $this$ifNoneMatch.getHeaders().set(HttpHeaders.INSTANCE.getIfNoneMatch(), value);
    }

    public static final void userAgent(HttpMessageBuilder $this$userAgent, String content) {
        Intrinsics.checkNotNullParameter($this$userAgent, "<this>");
        Intrinsics.checkNotNullParameter(content, "content");
        $this$userAgent.getHeaders().set(HttpHeaders.INSTANCE.getUserAgent(), content);
    }

    public static final ContentType contentType(HttpMessageBuilder $this$contentType) {
        Intrinsics.checkNotNullParameter($this$contentType, "<this>");
        String it = $this$contentType.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        if (it != null) {
            return ContentType.INSTANCE.parse(it);
        }
        return null;
    }

    public static final Charset charset(HttpMessageBuilder $this$charset) {
        Intrinsics.checkNotNullParameter($this$charset, "<this>");
        ContentType contentType = contentType($this$charset);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    public static final String etag(HttpMessageBuilder $this$etag) {
        Intrinsics.checkNotNullParameter($this$etag, "<this>");
        return $this$etag.getHeaders().get(HttpHeaders.INSTANCE.getETag());
    }

    public static final List<String> vary(HttpMessageBuilder $this$vary) {
        Iterable split$default;
        Intrinsics.checkNotNullParameter($this$vary, "<this>");
        String str = $this$vary.getHeaders().get(HttpHeaders.INSTANCE.getVary());
        if (str == null || (split$default = StringsKt.split$default((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        Iterable $this$map$iv = split$default;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        return (List) destination$iv$iv;
    }

    public static final Long contentLength(HttpMessageBuilder $this$contentLength) {
        Intrinsics.checkNotNullParameter($this$contentLength, "<this>");
        String str = $this$contentLength.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        if (str != null) {
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }

    public static final ContentType contentType(HttpMessage $this$contentType) {
        Intrinsics.checkNotNullParameter($this$contentType, "<this>");
        String it = $this$contentType.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        if (it != null) {
            return ContentType.INSTANCE.parse(it);
        }
        return null;
    }

    public static final Charset charset(HttpMessage $this$charset) {
        Intrinsics.checkNotNullParameter($this$charset, "<this>");
        ContentType contentType = contentType($this$charset);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    public static final String etag(HttpMessage $this$etag) {
        Intrinsics.checkNotNullParameter($this$etag, "<this>");
        return $this$etag.getHeaders().get(HttpHeaders.INSTANCE.getETag());
    }

    public static final List<String> vary(HttpMessage $this$vary) {
        Iterable split$default;
        Intrinsics.checkNotNullParameter($this$vary, "<this>");
        String str = $this$vary.getHeaders().get(HttpHeaders.INSTANCE.getVary());
        if (str == null || (split$default = StringsKt.split$default((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        Iterable $this$map$iv = split$default;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        return (List) destination$iv$iv;
    }

    public static final Long contentLength(HttpMessage $this$contentLength) {
        Intrinsics.checkNotNullParameter($this$contentLength, "<this>");
        String str = $this$contentLength.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        if (str != null) {
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }

    public static final List<Cookie> setCookie(HttpMessage $this$setCookie) {
        Intrinsics.checkNotNullParameter($this$setCookie, "<this>");
        Iterable all = $this$setCookie.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie());
        if (all != null) {
            Iterable $this$flatMap$iv = all;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$flatMap$iv) {
                String it = (String) element$iv$iv;
                Iterable list$iv$iv = splitSetCookieHeader(it);
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            }
            Iterable $this$map$iv = (List) destination$iv$iv;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String it2 = (String) item$iv$iv;
                destination$iv$iv2.add(CookieKt.parseServerSetCookieHeader(it2));
            }
            return (List) destination$iv$iv2;
        }
        return CollectionsKt.emptyList();
    }

    public static final List<Cookie> cookies(HttpMessageBuilder $this$cookies) {
        Intrinsics.checkNotNullParameter($this$cookies, "<this>");
        Iterable all = $this$cookies.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie());
        if (all == null) {
            return CollectionsKt.emptyList();
        }
        Iterable $this$map$iv = all;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(CookieKt.parseServerSetCookieHeader(it));
        }
        return (List) destination$iv$iv;
    }

    public static final List<HeaderValue> cacheControl(HttpMessage $this$cacheControl) {
        List<HeaderValue> parseHeaderValue;
        Intrinsics.checkNotNullParameter($this$cacheControl, "<this>");
        String it = $this$cacheControl.getHeaders().get(HttpHeaders.INSTANCE.getCacheControl());
        return (it == null || (parseHeaderValue = HttpHeaderValueParserKt.parseHeaderValue(it)) == null) ? CollectionsKt.emptyList() : parseHeaderValue;
    }

    public static final List<String> splitSetCookieHeader(String $this$splitSetCookieHeader) {
        Intrinsics.checkNotNullParameter($this$splitSetCookieHeader, "<this>");
        int comma = StringsKt.indexOf$default((CharSequence) $this$splitSetCookieHeader, AbstractJsonLexerKt.COMMA, 0, false, 6, (Object) null);
        if (comma == -1) {
            return CollectionsKt.listOf($this$splitSetCookieHeader);
        }
        List result = new ArrayList();
        int current = 0;
        int equals = StringsKt.indexOf$default((CharSequence) $this$splitSetCookieHeader, '=', comma, false, 4, (Object) null);
        int semicolon = StringsKt.indexOf$default((CharSequence) $this$splitSetCookieHeader, ';', comma, false, 4, (Object) null);
        int comma2 = comma;
        while (current < $this$splitSetCookieHeader.length() && comma2 > 0) {
            if (equals < comma2) {
                equals = StringsKt.indexOf$default((CharSequence) $this$splitSetCookieHeader, '=', comma2, false, 4, (Object) null);
            }
            int nextComma = StringsKt.indexOf$default((CharSequence) $this$splitSetCookieHeader, AbstractJsonLexerKt.COMMA, comma2 + 1, false, 4, (Object) null);
            int comma3 = comma2;
            while (nextComma >= 0 && nextComma < equals) {
                comma3 = nextComma;
                nextComma = StringsKt.indexOf$default((CharSequence) $this$splitSetCookieHeader, AbstractJsonLexerKt.COMMA, nextComma + 1, false, 4, (Object) null);
            }
            if (semicolon < comma3) {
                semicolon = StringsKt.indexOf$default((CharSequence) $this$splitSetCookieHeader, ';', comma3, false, 4, (Object) null);
            }
            if (equals < 0) {
                String substring = $this$splitSetCookieHeader.substring(current);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                result.add(substring);
                return result;
            }
            if (semicolon == -1 || semicolon > equals) {
                String substring2 = $this$splitSetCookieHeader.substring(current, comma3);
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                result.add(substring2);
                current = comma3 + 1;
            }
            comma2 = nextComma;
        }
        if (current < $this$splitSetCookieHeader.length()) {
            String substring3 = $this$splitSetCookieHeader.substring(current);
            Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String).substring(startIndex)");
            result.add(substring3);
        }
        return result;
    }
}
