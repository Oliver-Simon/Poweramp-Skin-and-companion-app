package io.ktor.http;

import io.ktor.util.Base64Kt;
import io.ktor.util.TextKt;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: Cookie.kt */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a#\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0082\b\u001a\u001b\u0010\r\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0082\b\u001a\u0019\u0010\u000e\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u000fH\u0082\b\u001a\u001b\u0010\u0010\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0082\b\u001a\u0016\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f\u001a\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f\u001a$\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00152\u0006\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u000f\u001a\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0006\u001a\u000e\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0019\u001a\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0019\u001a\u0084\u0001\u0010\u001c\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020\u000f2\u0016\b\u0002\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00152\b\b\u0002\u0010&\u001a\u00020\u000f\u001a\f\u0010'\u001a\u00020\u0006*\u00020\u0006H\u0002\u001a\f\u0010(\u001a\u00020\u000f*\u00020\u0004H\u0002\u001a\f\u0010)\u001a\u00020\u001e*\u00020\u0006H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"clientCookieHeaderPattern", "Lkotlin/text/Regex;", "cookieCharsShouldBeEscaped", "", "", "loweredPartNames", "", "cookiePart", "name", "value", "", "encoding", "Lio/ktor/http/CookieEncoding;", "cookiePartExt", "cookiePartFlag", "", "cookiePartUnencoded", "decodeCookieValue", "encodedValue", "encodeCookieValue", "parseClientCookiesHeader", "", "cookiesHeader", "skipEscaped", "parseServerSetCookieHeader", "Lio/ktor/http/Cookie;", "renderCookieHeader", "cookie", "renderSetCookieHeader", "maxAge", "", "expires", "Lio/ktor/util/date/GMTDate;", "domain", "path", "secure", "httpOnly", "extensions", "includeEncoding", "assertCookieName", "shouldEscapeInCookies", "toIntClamping", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CookieKt {
    private static final Set<String> loweredPartNames = SetsKt.setOf((Object[]) new String[]{io.ktor.client.utils.CacheControl.MAX_AGE, "expires", "domain", "path", "secure", "httponly", "$x-enc"});
    private static final Regex clientCookieHeaderPattern = new Regex("(^|;)\\s*([^;=\\{\\}\\s]+)\\s*(=\\s*(\"[^\"]*\"|[^;]*))?");
    private static final Set<Character> cookieCharsShouldBeEscaped = SetsKt.setOf((Object[]) new Character[]{';', Character.valueOf(AbstractJsonLexerKt.COMMA), '\"'});

    /* compiled from: Cookie.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CookieEncoding.values().length];
            try {
                iArr[CookieEncoding.RAW.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CookieEncoding.DQUOTES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CookieEncoding.BASE64_ENCODING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CookieEncoding.URI_ENCODING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Cookie parseServerSetCookieHeader(String cookiesHeader) {
        CookieEncoding encoding;
        Intrinsics.checkNotNullParameter(cookiesHeader, "cookiesHeader");
        boolean z = false;
        Map asMap = parseClientCookiesHeader(cookiesHeader, false);
        Iterable $this$first$iv = asMap.entrySet();
        for (Object element$iv : $this$first$iv) {
            if (!StringsKt.startsWith$default((String) ((Map.Entry) element$iv).getKey(), "$", z, 2, (Object) null)) {
                Map.Entry first = (Map.Entry) element$iv;
                String it = asMap.get("$x-enc");
                if (it == null || (encoding = CookieEncoding.valueOf(it)) == null) {
                    encoding = CookieEncoding.RAW;
                }
                Map loweredMap = new LinkedHashMap(MapsKt.mapCapacity(asMap.size()));
                Iterable $this$associateByTo$iv$iv$iv = asMap.entrySet();
                for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                    Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
                    loweredMap.put(TextKt.toLowerCasePreservingASCIIRules((String) ((Map.Entry) element$iv$iv$iv).getKey()), it$iv$iv.getValue());
                }
                String str = (String) first.getKey();
                String decodeCookieValue = decodeCookieValue((String) first.getValue(), encoding);
                String str2 = (String) loweredMap.get(io.ktor.client.utils.CacheControl.MAX_AGE);
                int intClamping = str2 != null ? toIntClamping(str2) : 0;
                String str3 = (String) loweredMap.get("expires");
                GMTDate fromCookieToGmtDate = str3 != null ? DateUtilsKt.fromCookieToGmtDate(str3) : null;
                String str4 = (String) loweredMap.get("domain");
                String str5 = (String) loweredMap.get("path");
                boolean containsKey = loweredMap.containsKey("secure");
                boolean containsKey2 = loweredMap.containsKey("httponly");
                LinkedHashMap result$iv = new LinkedHashMap();
                for (Map.Entry entry$iv : asMap.entrySet()) {
                    String it2 = entry$iv.getKey();
                    if ((loweredPartNames.contains(TextKt.toLowerCasePreservingASCIIRules(it2)) || Intrinsics.areEqual(it2, first.getKey())) ? false : true) {
                        result$iv.put(entry$iv.getKey(), entry$iv.getValue());
                    }
                }
                return new Cookie(str, decodeCookieValue, encoding, intClamping, fromCookieToGmtDate, str4, str5, containsKey, containsKey2, result$iv);
            }
            z = false;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static /* synthetic */ Map parseClientCookiesHeader$default(String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return parseClientCookiesHeader(str, z);
    }

    public static final Map<String, String> parseClientCookiesHeader(String cookiesHeader, final boolean skipEscaped) {
        Intrinsics.checkNotNullParameter(cookiesHeader, "cookiesHeader");
        return MapsKt.toMap(SequencesKt.map(SequencesKt.filter(SequencesKt.map(Regex.findAll$default(clientCookieHeaderPattern, cookiesHeader, 0, 2, null), new Function1<MatchResult, Pair<? extends String, ? extends String>>() { // from class: io.ktor.http.CookieKt$parseClientCookiesHeader$1
            @Override // kotlin.jvm.functions.Function1
            public final Pair<String, String> invoke(MatchResult it) {
                String str;
                String value;
                Intrinsics.checkNotNullParameter(it, "it");
                MatchGroup matchGroup = it.getGroups().get(2);
                String str2 = "";
                if (matchGroup == null || (str = matchGroup.getValue()) == null) {
                    str = "";
                }
                MatchGroup matchGroup2 = it.getGroups().get(4);
                if (matchGroup2 != null && (value = matchGroup2.getValue()) != null) {
                    str2 = value;
                }
                return TuplesKt.to(str, str2);
            }
        }), new Function1<Pair<? extends String, ? extends String>, Boolean>() { // from class: io.ktor.http.CookieKt$parseClientCookiesHeader$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Code restructure failed: missing block: B:4:0x0018, code lost:
            
                if (kotlin.text.StringsKt.startsWith$default(r6.getFirst(), "$", false, 2, (java.lang.Object) null) == false) goto L6;
             */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Boolean invoke2(kotlin.Pair<java.lang.String, java.lang.String> r6) {
                /*
                    r5 = this;
                    java.lang.String r0 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                    boolean r0 = r2
                    if (r0 == 0) goto L1a
                    java.lang.Object r0 = r6.getFirst()
                    java.lang.String r0 = (java.lang.String) r0
                    r1 = 2
                    r2 = 0
                    java.lang.String r3 = "$"
                    r4 = 0
                    boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r3, r4, r1, r2)
                    if (r0 != 0) goto L1b
                L1a:
                    r4 = 1
                L1b:
                    java.lang.Boolean r0 = java.lang.Boolean.valueOf(r4)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.CookieKt$parseClientCookiesHeader$2.invoke2(kotlin.Pair):java.lang.Boolean");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Pair<? extends String, ? extends String> pair) {
                return invoke2((Pair<String, String>) pair);
            }
        }), new Function1<Pair<? extends String, ? extends String>, Pair<? extends String, ? extends String>>() { // from class: io.ktor.http.CookieKt$parseClientCookiesHeader$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Pair<? extends String, ? extends String> invoke(Pair<? extends String, ? extends String> pair) {
                return invoke2((Pair<String, String>) pair);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Pair<String, String> invoke2(Pair<String, String> cookie) {
                Intrinsics.checkNotNullParameter(cookie, "cookie");
                if (StringsKt.startsWith$default(cookie.getSecond(), "\"", false, 2, (Object) null) && StringsKt.endsWith$default(cookie.getSecond(), "\"", false, 2, (Object) null)) {
                    return Pair.copy$default(cookie, null, StringsKt.removeSurrounding(cookie.getSecond(), (CharSequence) "\""), 1, null);
                }
                return cookie;
            }
        }));
    }

    public static final String renderSetCookieHeader(Cookie cookie) {
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return renderSetCookieHeader$default(cookie.getName(), cookie.getValue(), cookie.getEncoding(), cookie.getMaxAgeInt(), cookie.getExpires(), cookie.getDomain(), cookie.getPath(), cookie.getSecure(), cookie.getHttpOnly(), cookie.getExtensions(), false, 1024, null);
    }

    public static final String renderCookieHeader(Cookie cookie) {
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return cookie.getName() + '=' + encodeCookieValue(cookie.getValue(), cookie.getEncoding());
    }

    public static /* synthetic */ String renderSetCookieHeader$default(String str, String str2, CookieEncoding cookieEncoding, int i, GMTDate gMTDate, String str3, String str4, boolean z, boolean z2, Map map, boolean z3, int i2, Object obj) {
        return renderSetCookieHeader(str, str2, (i2 & 4) != 0 ? CookieEncoding.URI_ENCODING : cookieEncoding, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? null : gMTDate, (i2 & 32) != 0 ? null : str3, (i2 & 64) == 0 ? str4 : null, (i2 & 128) != 0 ? false : z, (i2 & 256) == 0 ? z2 : false, (i2 & 512) != 0 ? MapsKt.emptyMap() : map, (i2 & 1024) != 0 ? true : z3);
    }

    public static final String renderSetCookieHeader(String name, String value, CookieEncoding encoding, int maxAge, GMTDate expires, String domain, String path, boolean secure, boolean httpOnly, Map<String, String> extensions, boolean includeEncoding) {
        String str;
        String str2;
        String str3;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        Intrinsics.checkNotNullParameter(extensions, "extensions");
        String[] strArr = new String[7];
        String name$iv = assertCookieName(name);
        strArr[0] = name$iv + '=' + encodeCookieValue(value.toString(), encoding);
        Object value$iv = maxAge > 0 ? Integer.valueOf(maxAge) : null;
        String str4 = "";
        strArr[1] = value$iv != null ? "Max-Age=" + value$iv : "";
        Object value$iv2 = expires != null ? DateUtilsKt.toHttpDate(expires) : null;
        strArr[2] = value$iv2 != null ? com.google.common.net.HttpHeaders.EXPIRES + '=' + value$iv2 : "";
        CookieEncoding encoding$iv = CookieEncoding.RAW;
        if (domain != null) {
            str = "Domain=" + encodeCookieValue(domain.toString(), encoding$iv);
        } else {
            str = "";
        }
        strArr[3] = str;
        CookieEncoding encoding$iv2 = CookieEncoding.RAW;
        if (path != null) {
            str2 = "Path=" + encodeCookieValue(path.toString(), encoding$iv2);
        } else {
            str2 = "";
        }
        strArr[4] = str2;
        String name$iv2 = "Secure";
        if (!secure) {
            name$iv2 = "";
        }
        strArr[5] = name$iv2;
        String name$iv3 = "HttpOnly";
        if (!httpOnly) {
            name$iv3 = "";
        }
        strArr[6] = name$iv3;
        List listOf = CollectionsKt.listOf((Object[]) strArr);
        Collection destination$iv$iv = new ArrayList(extensions.size());
        for (Map.Entry item$iv$iv : extensions.entrySet()) {
            String name$iv4 = assertCookieName(item$iv$iv.getKey());
            String value$iv3 = item$iv$iv.getValue();
            if (value$iv3 != null) {
                CookieEncoding encoding$iv$iv = CookieEncoding.RAW;
                str3 = name$iv4 + '=' + encodeCookieValue(value$iv3.toString(), encoding$iv$iv);
            } else {
                str3 = name$iv4;
            }
            destination$iv$iv.add(str3);
        }
        List plus = CollectionsKt.plus((Collection) listOf, destination$iv$iv);
        if (includeEncoding) {
            String value$iv4 = encoding.name();
            if (value$iv4 != null) {
                CookieEncoding encoding$iv$iv2 = CookieEncoding.RAW;
                str4 = "$x-enc=" + encodeCookieValue(value$iv4.toString(), encoding$iv$iv2);
            } else {
                str4 = "$x-enc";
            }
        }
        Iterable $this$filter$iv = CollectionsKt.plus((Collection<? extends String>) plus, str4);
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (it.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv2, "; ", null, null, 0, null, null, 62, null);
    }

    public static final String encodeCookieValue(String value, CookieEncoding encoding) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        boolean z = true;
        switch (WhenMappings.$EnumSwitchMapping$0[encoding.ordinal()]) {
            case 1:
                String $this$any$iv = value;
                int i = 0;
                while (true) {
                    if (i < $this$any$iv.length()) {
                        char element$iv = $this$any$iv.charAt(i);
                        if (!shouldEscapeInCookies(element$iv)) {
                            i++;
                        }
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    throw new IllegalArgumentException("The cookie value contains characters that cannot be encoded in RAW format.  Consider URL_ENCODING mode");
                }
                break;
            case 2:
                if (StringsKt.contains$default((CharSequence) value, '\"', false, 2, (Object) null)) {
                    throw new IllegalArgumentException("The cookie value contains characters that cannot be encoded in DQUOTES format. Consider URL_ENCODING mode");
                }
                String $this$any$iv2 = value;
                int i2 = 0;
                while (true) {
                    if (i2 < $this$any$iv2.length()) {
                        char element$iv2 = $this$any$iv2.charAt(i2);
                        if (!shouldEscapeInCookies(element$iv2)) {
                            i2++;
                        }
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    return '\"' + value + '\"';
                }
                break;
            case 3:
                return Base64Kt.encodeBase64(value);
            case 4:
                return CodecsKt.encodeURLParameter(value, true);
            default:
                throw new NoWhenBranchMatchedException();
        }
        return value;
    }

    public static final String decodeCookieValue(String encodedValue, CookieEncoding encoding) {
        Intrinsics.checkNotNullParameter(encodedValue, "encodedValue");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        switch (WhenMappings.$EnumSwitchMapping$0[encoding.ordinal()]) {
            case 1:
            case 2:
                if (StringsKt.startsWith$default(StringsKt.trimStart((CharSequence) encodedValue).toString(), "\"", false, 2, (Object) null) && StringsKt.endsWith$default(StringsKt.trimEnd((CharSequence) encodedValue).toString(), "\"", false, 2, (Object) null)) {
                    return StringsKt.removeSurrounding(StringsKt.trim((CharSequence) encodedValue).toString(), (CharSequence) "\"");
                }
                return encodedValue;
            case 3:
                return Base64Kt.decodeBase64String(encodedValue);
            case 4:
                return CodecsKt.decodeURLQueryComponent$default(encodedValue, 0, 0, true, null, 11, null);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final String assertCookieName(String $this$assertCookieName) {
        String $this$any$iv = $this$assertCookieName;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= $this$any$iv.length()) {
                break;
            }
            char element$iv = $this$any$iv.charAt(i);
            if (shouldEscapeInCookies(element$iv)) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            throw new IllegalArgumentException("Cookie name is not valid: " + $this$assertCookieName);
        }
        return $this$assertCookieName;
    }

    private static final boolean shouldEscapeInCookies(char $this$shouldEscapeInCookies) {
        return CharsKt.isWhitespace($this$shouldEscapeInCookies) || Intrinsics.compare((int) $this$shouldEscapeInCookies, 32) < 0 || cookieCharsShouldBeEscaped.contains(Character.valueOf($this$shouldEscapeInCookies));
    }

    private static final String cookiePart(String name, Object value, CookieEncoding encoding) {
        return value != null ? name + '=' + encodeCookieValue(value.toString(), encoding) : "";
    }

    private static final String cookiePartUnencoded(String name, Object value) {
        return value != null ? name + '=' + value : "";
    }

    private static final String cookiePartFlag(String name, boolean value) {
        return value ? name : "";
    }

    private static final String cookiePartExt(String name, String value) {
        if (value != null) {
            CookieEncoding encoding$iv = CookieEncoding.RAW;
            return name + '=' + encodeCookieValue(value.toString(), encoding$iv);
        }
        return name;
    }

    private static final int toIntClamping(String $this$toIntClamping) {
        return (int) kotlin.ranges.RangesKt.coerceIn(Long.parseLong($this$toIntClamping), 0L, 2147483647L);
    }
}
