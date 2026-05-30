package io.ktor.http;

import androidx.core.app.FrameMetricsAggregator;
import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.util.StringValuesKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: URLUtils.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t\u001a\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0002\u001a\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0001\u001a\u000e\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\t\u001a\u000e\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0001\u001a&\u0010\u0012\u001a\u00020\u0013*\u00060\u0014j\u0002`\u00152\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\b\u001a(\u0010\u0012\u001a\u00020\u0013*\u00060\u0014j\u0002`\u00152\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\bH\u0000\u001a$\u0010\u001b\u001a\u00020\u0013*\u00060\u001cj\u0002`\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u0000\u001a\u0012\u0010 \u001a\u00020\t*\u00020\t2\u0006\u0010\u000f\u001a\u00020\t\u001a\u0012\u0010 \u001a\u00020\t*\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\n\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u000b\"\u0015\u0010\f\u001a\u00020\b*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\f\u0010\n\"\u0015\u0010\f\u001a\u00020\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000b¨\u0006!"}, d2 = {"fullPath", "", "Lio/ktor/http/Url;", "getFullPath", "(Lio/ktor/http/Url;)Ljava/lang/String;", "hostWithPort", "getHostWithPort", "isAbsolutePath", "", "Lio/ktor/http/URLBuilder;", "(Lio/ktor/http/URLBuilder;)Z", "(Lio/ktor/http/Url;)Z", "isRelativePath", "URLBuilder", "builder", TrackProviderConsts.COLUMN_URL, "urlString", "Url", "appendUrlFullPath", "", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "encodedPath", "encodedQueryParameters", "Lio/ktor/http/ParametersBuilder;", "trailingQuery", "encodedQuery", "appendUserAndPassword", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "encodedUser", "encodedPassword", "takeFrom", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class URLUtilsKt {
    public static final Url Url(String urlString) {
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        return URLBuilder(urlString).build();
    }

    public static final Url Url(URLBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null), builder).build();
    }

    public static final URLBuilder URLBuilder(String urlString) {
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        return URLParserKt.takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null), urlString);
    }

    public static final URLBuilder URLBuilder(Url url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null), url);
    }

    public static final URLBuilder URLBuilder(URLBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null), builder);
    }

    public static final URLBuilder takeFrom(URLBuilder $this$takeFrom, URLBuilder url) {
        Intrinsics.checkNotNullParameter($this$takeFrom, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        $this$takeFrom.setProtocol(url.getProtocol());
        $this$takeFrom.setHost(url.getHost());
        $this$takeFrom.setPort(url.getPort());
        $this$takeFrom.setEncodedPathSegments(url.getEncodedPathSegments());
        $this$takeFrom.setEncodedUser(url.getEncodedUser());
        $this$takeFrom.setEncodedPassword(url.getEncodedPassword());
        ParametersBuilder $this$takeFrom_u24lambda_u240 = ParametersKt.ParametersBuilder$default(0, 1, null);
        StringValuesKt.appendAll($this$takeFrom_u24lambda_u240, url.getEncodedParameters());
        $this$takeFrom.setEncodedParameters($this$takeFrom_u24lambda_u240);
        $this$takeFrom.setEncodedFragment(url.getEncodedFragment());
        $this$takeFrom.setTrailingQuery(url.getTrailingQuery());
        return $this$takeFrom;
    }

    public static final URLBuilder takeFrom(URLBuilder $this$takeFrom, Url url) {
        Intrinsics.checkNotNullParameter($this$takeFrom, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        $this$takeFrom.setProtocol(url.getProtocol());
        $this$takeFrom.setHost(url.getHost());
        $this$takeFrom.setPort(url.getPort());
        URLBuilderKt.setEncodedPath($this$takeFrom, url.getEncodedPath());
        $this$takeFrom.setEncodedUser(url.getEncodedUser());
        $this$takeFrom.setEncodedPassword(url.getEncodedPassword());
        ParametersBuilder $this$takeFrom_u24lambda_u241 = ParametersKt.ParametersBuilder$default(0, 1, null);
        $this$takeFrom_u24lambda_u241.appendAll(QueryKt.parseQueryString$default(url.getEncodedQuery(), 0, 0, false, 6, null));
        $this$takeFrom.setEncodedParameters($this$takeFrom_u24lambda_u241);
        $this$takeFrom.setEncodedFragment(url.getEncodedFragment());
        $this$takeFrom.setTrailingQuery(url.getTrailingQuery());
        return $this$takeFrom;
    }

    public static final String getFullPath(Url $this$fullPath) {
        Intrinsics.checkNotNullParameter($this$fullPath, "<this>");
        StringBuilder $this$_get_fullPath__u24lambda_u242 = new StringBuilder();
        appendUrlFullPath($this$_get_fullPath__u24lambda_u242, $this$fullPath.getEncodedPath(), $this$fullPath.getEncodedQuery(), $this$fullPath.getTrailingQuery());
        String sb = $this$_get_fullPath__u24lambda_u242.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static final String getHostWithPort(Url $this$hostWithPort) {
        Intrinsics.checkNotNullParameter($this$hostWithPort, "<this>");
        return $this$hostWithPort.getHost() + AbstractJsonLexerKt.COLON + $this$hostWithPort.getPort();
    }

    public static final void appendUrlFullPath(Appendable $this$appendUrlFullPath, String encodedPath, String encodedQuery, boolean trailingQuery) {
        Intrinsics.checkNotNullParameter($this$appendUrlFullPath, "<this>");
        Intrinsics.checkNotNullParameter(encodedPath, "encodedPath");
        Intrinsics.checkNotNullParameter(encodedQuery, "encodedQuery");
        if (!StringsKt.isBlank(encodedPath) && !StringsKt.startsWith$default(encodedPath, "/", false, 2, (Object) null)) {
            $this$appendUrlFullPath.append('/');
        }
        $this$appendUrlFullPath.append(encodedPath);
        if ((encodedQuery.length() > 0) || trailingQuery) {
            $this$appendUrlFullPath.append("?");
        }
        $this$appendUrlFullPath.append(encodedQuery);
    }

    public static final void appendUrlFullPath(Appendable $this$appendUrlFullPath, String encodedPath, ParametersBuilder encodedQueryParameters, boolean trailingQuery) {
        Iterable $this$flatMap$iv;
        Iterable $this$map$iv;
        Intrinsics.checkNotNullParameter($this$appendUrlFullPath, "<this>");
        Intrinsics.checkNotNullParameter(encodedPath, "encodedPath");
        Intrinsics.checkNotNullParameter(encodedQueryParameters, "encodedQueryParameters");
        Object obj = null;
        if (!StringsKt.isBlank(encodedPath) && !StringsKt.startsWith$default(encodedPath, "/", false, 2, (Object) null)) {
            $this$appendUrlFullPath.append('/');
        }
        $this$appendUrlFullPath.append(encodedPath);
        if (!encodedQueryParameters.isEmpty() || trailingQuery) {
            $this$appendUrlFullPath.append("?");
        }
        Iterable list$iv$iv = encodedQueryParameters.entries();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : list$iv$iv) {
            Map.Entry entry = (Map.Entry) element$iv$iv;
            String key = (String) entry.getKey();
            List value = (List) entry.getValue();
            if (value.isEmpty()) {
                $this$map$iv = CollectionsKt.listOf(TuplesKt.to(key, obj));
                $this$flatMap$iv = list$iv$iv;
            } else {
                List $this$map$iv2 = value;
                $this$flatMap$iv = list$iv$iv;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv : $this$map$iv2) {
                    String it = (String) item$iv$iv;
                    destination$iv$iv2.add(TuplesKt.to(key, it));
                }
                $this$map$iv = (List) destination$iv$iv2;
            }
            CollectionsKt.addAll(destination$iv$iv, $this$map$iv);
            obj = null;
            list$iv$iv = $this$flatMap$iv;
        }
        CollectionsKt.joinTo((List) destination$iv$iv, $this$appendUrlFullPath, (r14 & 2) != 0 ? ", " : "&", (r14 & 4) != 0 ? "" : null, (r14 & 8) != 0 ? "" : null, (r14 & 16) != 0 ? -1 : 0, (r14 & 32) != 0 ? "..." : null, (r14 & 64) != 0 ? null : new Function1<Pair<? extends String, ? extends String>, CharSequence>() { // from class: io.ktor.http.URLUtilsKt$appendUrlFullPath$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Pair<? extends String, ? extends String> pair) {
                return invoke2((Pair<String, String>) pair);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final CharSequence invoke2(Pair<String, String> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                String key2 = it2.getFirst();
                if (it2.getSecond() == null) {
                    return key2;
                }
                String value2 = String.valueOf(it2.getSecond());
                return key2 + '=' + value2;
            }
        });
    }

    public static final boolean isAbsolutePath(Url $this$isAbsolutePath) {
        Intrinsics.checkNotNullParameter($this$isAbsolutePath, "<this>");
        return Intrinsics.areEqual(CollectionsKt.firstOrNull((List) $this$isAbsolutePath.getPathSegments()), "");
    }

    public static final boolean isRelativePath(Url $this$isRelativePath) {
        Intrinsics.checkNotNullParameter($this$isRelativePath, "<this>");
        return !isAbsolutePath($this$isRelativePath);
    }

    public static final boolean isAbsolutePath(URLBuilder $this$isAbsolutePath) {
        Intrinsics.checkNotNullParameter($this$isAbsolutePath, "<this>");
        return Intrinsics.areEqual(CollectionsKt.firstOrNull((List) $this$isAbsolutePath.getPathSegments()), "");
    }

    public static final boolean isRelativePath(URLBuilder $this$isRelativePath) {
        Intrinsics.checkNotNullParameter($this$isRelativePath, "<this>");
        return !isAbsolutePath($this$isRelativePath);
    }

    public static final void appendUserAndPassword(StringBuilder $this$appendUserAndPassword, String encodedUser, String encodedPassword) {
        Intrinsics.checkNotNullParameter($this$appendUserAndPassword, "<this>");
        if (encodedUser == null) {
            return;
        }
        $this$appendUserAndPassword.append(encodedUser);
        if (encodedPassword != null) {
            $this$appendUserAndPassword.append(AbstractJsonLexerKt.COLON);
            $this$appendUserAndPassword.append(encodedPassword);
        }
        $this$appendUserAndPassword.append("@");
    }
}
