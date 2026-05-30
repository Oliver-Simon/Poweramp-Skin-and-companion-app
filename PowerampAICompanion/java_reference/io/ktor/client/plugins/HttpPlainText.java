package io.ktor.client.plugins;

import androidx.core.app.NotificationCompat;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.content.TextContent;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.StringsKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.Charsets;
import org.slf4j.Logger;

/* compiled from: HttpPlainText.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0002\u001f BM\b\u0000\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003\u0012\u0016\u0010\u0006\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\u0010\t\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005\u0012\n\u0010\n\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0002\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\u001d\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\"\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00060\u0004j\u0002`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u0004j\u0002`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lio/ktor/client/plugins/HttpPlainText;", "", "charsets", "", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "charsetQuality", "", "", "sendCharset", "responseCharsetFallback", "(Ljava/util/Set;Ljava/util/Map;Ljava/nio/charset/Charset;Ljava/nio/charset/Charset;)V", "acceptCharsetHeader", "", "requestCharset", "addCharsetHeaders", "", "context", "Lio/ktor/client/request/HttpRequestBuilder;", "addCharsetHeaders$ktor_client_core", "read", NotificationCompat.CATEGORY_CALL, "Lio/ktor/client/call/HttpClientCall;", "body", "Lio/ktor/utils/io/core/Input;", "read$ktor_client_core", "wrapContent", "request", "content", "requestContentType", "Lio/ktor/http/ContentType;", "Config", "Plugin", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpPlainText {

    /* renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<HttpPlainText> key = new AttributeKey<>("HttpPlainText");
    private final String acceptCharsetHeader;
    private final Charset requestCharset;
    private final Charset responseCharsetFallback;

    public HttpPlainText(Set<? extends Charset> charsets, Map<Charset, Float> charsetQuality, Charset sendCharset, Charset responseCharsetFallback) {
        Charset charset;
        Intrinsics.checkNotNullParameter(charsets, "charsets");
        Intrinsics.checkNotNullParameter(charsetQuality, "charsetQuality");
        Intrinsics.checkNotNullParameter(responseCharsetFallback, "responseCharsetFallback");
        this.responseCharsetFallback = responseCharsetFallback;
        Iterable $this$sortedByDescending$iv = MapsKt.toList(charsetQuality);
        List withQuality = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: io.ktor.client.plugins.HttpPlainText$special$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Pair it = (Pair) t2;
                Float f = (Float) it.getSecond();
                Pair it2 = (Pair) t;
                return ComparisonsKt.compareValues(f, (Float) it2.getSecond());
            }
        });
        Set<? extends Charset> $this$filter$iv = charsets;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Charset it = (Charset) element$iv$iv;
            if (!charsetQuality.containsKey(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$sortedBy$iv = (List) destination$iv$iv;
        List withoutQuality = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: io.ktor.client.plugins.HttpPlainText$special$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Charset it2 = (Charset) t;
                Charset it3 = (Charset) t2;
                return ComparisonsKt.compareValues(CharsetJVMKt.getName(it2), CharsetJVMKt.getName(it3));
            }
        });
        StringBuilder $this$_init__u24lambda_u245 = new StringBuilder();
        List $this$forEach$iv = withoutQuality;
        for (Object element$iv : $this$forEach$iv) {
            Charset it2 = (Charset) element$iv;
            if ($this$_init__u24lambda_u245.length() > 0) {
                $this$_init__u24lambda_u245.append(",");
            }
            $this$_init__u24lambda_u245.append(CharsetJVMKt.getName(it2));
        }
        List $this$forEach$iv2 = withQuality;
        for (Object element$iv2 : $this$forEach$iv2) {
            Pair pair = (Pair) element$iv2;
            Charset charset2 = (Charset) pair.component1();
            float quality = ((Number) pair.component2()).floatValue();
            if ($this$_init__u24lambda_u245.length() > 0) {
                $this$_init__u24lambda_u245.append(",");
            }
            double d = quality;
            if (!(0.0d <= d && d <= 1.0d)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            double truncatedQuality = MathKt.roundToInt(100 * quality) / 100.0d;
            $this$_init__u24lambda_u245.append(CharsetJVMKt.getName(charset2) + ";q=" + truncatedQuality);
        }
        if ($this$_init__u24lambda_u245.length() == 0) {
            $this$_init__u24lambda_u245.append(CharsetJVMKt.getName(this.responseCharsetFallback));
        }
        String sb = $this$_init__u24lambda_u245.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        this.acceptCharsetHeader = sb;
        if (sendCharset == null) {
            charset = (Charset) CollectionsKt.firstOrNull(withoutQuality);
            if (charset == null) {
                Pair pair2 = (Pair) CollectionsKt.firstOrNull(withQuality);
                charset = pair2 != null ? (Charset) pair2.getFirst() : null;
                if (charset == null) {
                    charset = Charsets.UTF_8;
                }
            }
        } else {
            charset = sendCharset;
        }
        this.requestCharset = charset;
    }

    /* compiled from: HttpPlainText.kt */
    @KtorDsl
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J#\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0005j\u0002`\u00062\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u001aR$\u0010\u0003\u001a\u0012\u0012\b\u0012\u00060\u0005j\u0002`\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u00060\u0005j\u0002`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012¨\u0006\u001b"}, d2 = {"Lio/ktor/client/plugins/HttpPlainText$Config;", "", "()V", "charsetQuality", "", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "", "getCharsetQuality$ktor_client_core", "()Ljava/util/Map;", "charsets", "", "getCharsets$ktor_client_core", "()Ljava/util/Set;", "responseCharsetFallback", "getResponseCharsetFallback", "()Ljava/nio/charset/Charset;", "setResponseCharsetFallback", "(Ljava/nio/charset/Charset;)V", "sendCharset", "getSendCharset", "setSendCharset", "register", "", HttpAuthHeader.Parameters.Charset, "quality", "(Ljava/nio/charset/Charset;Ljava/lang/Float;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Config {
        private Charset sendCharset;
        private final Set<Charset> charsets = new LinkedHashSet();
        private final Map<Charset, Float> charsetQuality = new LinkedHashMap();
        private Charset responseCharsetFallback = Charsets.UTF_8;

        public final Set<Charset> getCharsets$ktor_client_core() {
            return this.charsets;
        }

        public final Map<Charset, Float> getCharsetQuality$ktor_client_core() {
            return this.charsetQuality;
        }

        public static /* synthetic */ void register$default(Config config, Charset charset, Float f, int i, Object obj) {
            if ((i & 2) != 0) {
                f = null;
            }
            config.register(charset, f);
        }

        public final void register(Charset charset, Float quality) {
            Intrinsics.checkNotNullParameter(charset, "charset");
            if (quality != null) {
                float it = quality.floatValue();
                double d = it;
                boolean z = false;
                if (0.0d <= d && d <= 1.0d) {
                    z = true;
                }
                if (!z) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
            this.charsets.add(charset);
            if (quality == null) {
                this.charsetQuality.remove(charset);
            } else {
                this.charsetQuality.put(charset, quality);
            }
        }

        public final Charset getSendCharset() {
            return this.sendCharset;
        }

        public final void setSendCharset(Charset charset) {
            this.sendCharset = charset;
        }

        public final Charset getResponseCharsetFallback() {
            return this.responseCharsetFallback;
        }

        public final void setResponseCharsetFallback(Charset charset) {
            Intrinsics.checkNotNullParameter(charset, "<set-?>");
            this.responseCharsetFallback = charset;
        }
    }

    /* compiled from: HttpPlainText.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J!\u0010\u000e\u001a\u00020\u00032\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\u0010¢\u0006\u0002\b\u0011H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lio/ktor/client/plugins/HttpPlainText$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/HttpPlainText$Config;", "Lio/ktor/client/plugins/HttpPlainText;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "plugin", "scope", "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: io.ktor.client.plugins.HttpPlainText$Plugin, reason: from kotlin metadata */
    /* loaded from: classes9.dex */
    public static final class Companion implements HttpClientPlugin<Config, HttpPlainText> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<HttpPlainText> getKey() {
            return HttpPlainText.key;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpPlainText prepare(Function1<? super Config, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Config config = new Config();
            block.invoke(config);
            return new HttpPlainText(config.getCharsets$ktor_client_core(), config.getCharsetQuality$ktor_client_core(), config.getSendCharset(), config.getResponseCharsetFallback());
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpPlainText plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            scope.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getRender(), new HttpPlainText$Plugin$install$1(plugin, null));
            scope.getResponsePipeline().intercept(HttpResponsePipeline.INSTANCE.getTransform(), new HttpPlainText$Plugin$install$2(plugin, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object wrapContent(HttpRequestBuilder request, String content, ContentType requestContentType) {
        Charset charset;
        Logger logger;
        ContentType contentType = requestContentType == null ? ContentType.Text.INSTANCE.getPlain() : requestContentType;
        if (requestContentType == null || (charset = ContentTypesKt.charset(requestContentType)) == null) {
            charset = this.requestCharset;
        }
        logger = HttpPlainTextKt.LOGGER;
        logger.trace("Sending request body to " + request.getUrl() + " as text/plain with charset " + charset);
        return new TextContent(content, ContentTypesKt.withCharset(contentType, charset), null, 4, null);
    }

    public final String read$ktor_client_core(HttpClientCall call, Input body) {
        Logger logger;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(body, "body");
        Charset actualCharset = HttpMessagePropertiesKt.charset(call.getResponse());
        if (actualCharset == null) {
            actualCharset = this.responseCharsetFallback;
        }
        logger = HttpPlainTextKt.LOGGER;
        logger.trace("Reading response body for " + call.getRequest().getUrl() + " as String with charset " + actualCharset);
        return StringsKt.readText$default(body, actualCharset, 0, 2, (Object) null);
    }

    public final void addCharsetHeaders$ktor_client_core(HttpRequestBuilder context) {
        Logger logger;
        Intrinsics.checkNotNullParameter(context, "context");
        if (context.getHeaders().get(HttpHeaders.INSTANCE.getAcceptCharset()) != null) {
            return;
        }
        logger = HttpPlainTextKt.LOGGER;
        logger.trace("Adding Accept-Charset=" + this.acceptCharsetHeader + " to " + context.getUrl());
        context.getHeaders().set(HttpHeaders.INSTANCE.getAcceptCharset(), this.acceptCharsetHeader);
    }
}
