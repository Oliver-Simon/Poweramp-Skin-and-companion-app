package io.ktor.http;

import androidx.core.app.FrameMetricsAggregator;
import com.maxmpz.poweramp.player.TableDefs;
import com.maxmpz.poweramp.player.TrackProviderConsts;
import java.net.URI;
import java.net.URL;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: URLUtilsJvm.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\n\u0010\b\u001a\u00020\u0003*\u00020\u0001¨\u0006\t"}, d2 = {"Url", "Lio/ktor/http/Url;", TableDefs.SettingsSearchHistory.URI, "Ljava/net/URI;", "takeFrom", "Lio/ktor/http/URLBuilder;", TrackProviderConsts.COLUMN_URL, "Ljava/net/URL;", "toURI", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class URLUtilsJvmKt {
    public static final URLBuilder takeFrom(URLBuilder $this$takeFrom, URI uri) {
        Intrinsics.checkNotNullParameter($this$takeFrom, "<this>");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String it = uri.getScheme();
        if (it != null) {
            $this$takeFrom.setProtocol(URLProtocol.INSTANCE.createOrDefault(it));
            $this$takeFrom.setPort($this$takeFrom.getProtocol().getDefaultPort());
        }
        if (uri.getPort() > 0) {
            $this$takeFrom.setPort(uri.getPort());
        } else {
            String scheme = uri.getScheme();
            if (Intrinsics.areEqual(scheme, "http")) {
                $this$takeFrom.setPort(80);
            } else if (Intrinsics.areEqual(scheme, "https")) {
                $this$takeFrom.setPort(443);
            }
        }
        boolean z = false;
        if (uri.getRawUserInfo() != null) {
            String rawUserInfo = uri.getRawUserInfo();
            Intrinsics.checkNotNullExpressionValue(rawUserInfo, "uri.rawUserInfo");
            if (rawUserInfo.length() > 0) {
                String rawUserInfo2 = uri.getRawUserInfo();
                Intrinsics.checkNotNullExpressionValue(rawUserInfo2, "uri.rawUserInfo");
                List parts = StringsKt.split$default((CharSequence) rawUserInfo2, new String[]{":"}, false, 0, 6, (Object) null);
                $this$takeFrom.setEncodedUser((String) CollectionsKt.first(parts));
                $this$takeFrom.setEncodedPassword((String) CollectionsKt.getOrNull(parts, 1));
            }
        }
        String it2 = uri.getHost();
        if (it2 != null) {
            $this$takeFrom.setHost(it2);
        }
        String it3 = uri.getRawPath();
        Intrinsics.checkNotNullExpressionValue(it3, "uri.rawPath");
        URLBuilderKt.setEncodedPath($this$takeFrom, it3);
        String it4 = uri.getRawQuery();
        if (it4 != null) {
            ParametersBuilder $this$takeFrom_u24lambda_u243_u24lambda_u242 = ParametersKt.ParametersBuilder$default(0, 1, null);
            $this$takeFrom_u24lambda_u243_u24lambda_u242.appendAll(QueryKt.parseQueryString$default(it4, 0, 0, false, 6, null));
            $this$takeFrom.setEncodedParameters($this$takeFrom_u24lambda_u243_u24lambda_u242);
        }
        String query = uri.getQuery();
        if (query != null) {
            if (query.length() == 0) {
                z = true;
            }
        }
        if (z) {
            $this$takeFrom.setTrailingQuery(true);
        }
        String it5 = uri.getRawFragment();
        if (it5 != null) {
            $this$takeFrom.setEncodedFragment(it5);
        }
        return $this$takeFrom;
    }

    public static final URLBuilder takeFrom(URLBuilder $this$takeFrom, URL url) {
        Intrinsics.checkNotNullParameter($this$takeFrom, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        String host = url.getHost();
        Intrinsics.checkNotNullExpressionValue(host, "url.host");
        if (StringsKt.contains$default((CharSequence) host, '_', false, 2, (Object) null)) {
            String url2 = url.toString();
            Intrinsics.checkNotNullExpressionValue(url2, "url.toString()");
            return URLParserKt.takeFrom($this$takeFrom, url2);
        }
        URI uri = url.toURI();
        Intrinsics.checkNotNullExpressionValue(uri, "url.toURI()");
        return takeFrom($this$takeFrom, uri);
    }

    public static final URI toURI(Url $this$toURI) {
        Intrinsics.checkNotNullParameter($this$toURI, "<this>");
        return new URI($this$toURI.getUrlString());
    }

    public static final Url Url(URI uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null), uri).build();
    }
}
