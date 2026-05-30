package io.ktor.client.plugins;

import androidx.core.app.FrameMetricsAggregator;
import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLBuilderKt;
import io.ktor.http.URLParserKt;
import io.ktor.http.URLProtocol;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.KtorDsl;
import io.ktor.util.StringValuesKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultRequest.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \t2\u00020\u0001:\u0002\b\tB \b\u0002\u0012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¢\u0006\u0002\u0010\u0007R\u001f\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/ktor/client/plugins/DefaultRequest;", "", "block", "Lkotlin/Function1;", "Lio/ktor/client/plugins/DefaultRequest$DefaultRequestBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)V", "DefaultRequestBuilder", "Plugin", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DefaultRequest {

    /* renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<DefaultRequest> key = new AttributeKey<>("DefaultRequest");
    private final Function1<DefaultRequestBuilder, Unit> block;

    public /* synthetic */ DefaultRequest(Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DefaultRequest(Function1<? super DefaultRequestBuilder, Unit> function1) {
        this.block = function1;
    }

    /* compiled from: DefaultRequest.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J*\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J!\u0010\u0018\u001a\u00020\u00032\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\u001a¢\u0006\u0002\b\u001bH\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u001c"}, d2 = {"Lio/ktor/client/plugins/DefaultRequest$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/DefaultRequest$DefaultRequestBuilder;", "Lio/ktor/client/plugins/DefaultRequest;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "concatenatePath", "", "", "parent", "child", "install", "", "plugin", "scope", "Lio/ktor/client/HttpClient;", "mergeUrls", "baseUrl", "Lio/ktor/http/Url;", "requestUrl", "Lio/ktor/http/URLBuilder;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: io.ktor.client.plugins.DefaultRequest$Plugin, reason: from kotlin metadata */
    /* loaded from: classes9.dex */
    public static final class Companion implements HttpClientPlugin<DefaultRequestBuilder, DefaultRequest> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<DefaultRequest> getKey() {
            return DefaultRequest.key;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public DefaultRequest prepare(Function1<? super DefaultRequestBuilder, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            return new DefaultRequest(block, null);
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(DefaultRequest plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            scope.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getBefore(), new DefaultRequest$Plugin$install$1(plugin, null));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void mergeUrls(Url baseUrl, URLBuilder requestUrl) {
            if (Intrinsics.areEqual(requestUrl.getProtocol(), URLProtocol.INSTANCE.getHTTP())) {
                requestUrl.setProtocol(baseUrl.getProtocol());
            }
            if (requestUrl.getHost().length() > 0) {
                return;
            }
            URLBuilder resultUrl = URLUtilsKt.URLBuilder(baseUrl);
            resultUrl.setProtocol(requestUrl.getProtocol());
            if (requestUrl.getPort() != 0) {
                resultUrl.setPort(requestUrl.getPort());
            }
            resultUrl.setEncodedPathSegments(DefaultRequest.INSTANCE.concatenatePath(resultUrl.getEncodedPathSegments(), requestUrl.getEncodedPathSegments()));
            if (requestUrl.getEncodedFragment().length() > 0) {
                resultUrl.setEncodedFragment(requestUrl.getEncodedFragment());
            }
            ParametersBuilder defaultParameters = ParametersKt.ParametersBuilder$default(0, 1, null);
            StringValuesKt.appendAll(defaultParameters, resultUrl.getEncodedParameters());
            resultUrl.setEncodedParameters(requestUrl.getEncodedParameters());
            Iterable $this$forEach$iv = defaultParameters.entries();
            for (Object element$iv : $this$forEach$iv) {
                Map.Entry entry = (Map.Entry) element$iv;
                String key = (String) entry.getKey();
                List values = (List) entry.getValue();
                if (!resultUrl.getEncodedParameters().contains(key)) {
                    resultUrl.getEncodedParameters().appendAll(key, values);
                }
            }
            URLUtilsKt.takeFrom(requestUrl, resultUrl);
        }

        private final List<String> concatenatePath(List<String> parent, List<String> child) {
            if (child.isEmpty()) {
                return parent;
            }
            if (parent.isEmpty()) {
                return child;
            }
            if (((CharSequence) CollectionsKt.first((List) child)).length() == 0) {
                return child;
            }
            List $this$concatenatePath_u24lambda_u243 = CollectionsKt.createListBuilder((parent.size() + child.size()) - 1);
            int size = parent.size() - 1;
            for (int index = 0; index < size; index++) {
                $this$concatenatePath_u24lambda_u243.add(parent.get(index));
            }
            $this$concatenatePath_u24lambda_u243.addAll(child);
            return CollectionsKt.build($this$concatenatePath_u24lambda_u243);
        }
    }

    /* compiled from: DefaultRequest.kt */
    @KtorDsl
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u001f\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001f¢\u0006\u0002\b J\u001f\u0010\u0018\u001a\u00020\u001d2\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001d0\u001f¢\u0006\u0002\b J\u000e\u0010\u0018\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\fJV\u0010\u0018\u001a\u00020\u001d2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\f2\u0019\b\u0002\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001d0\u001f¢\u0006\u0002\b ¢\u0006\u0002\u0010$R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006%"}, d2 = {"Lio/ktor/client/plugins/DefaultRequest$DefaultRequestBuilder;", "Lio/ktor/http/HttpMessageBuilder;", "()V", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", TrackProviderConsts.COLUMN_HEADERS, "Lio/ktor/http/HeadersBuilder;", "getHeaders", "()Lio/ktor/http/HeadersBuilder;", "value", "", "host", "getHost", "()Ljava/lang/String;", "setHost", "(Ljava/lang/String;)V", "", "port", "getPort", "()I", "setPort", "(I)V", TrackProviderConsts.COLUMN_URL, "Lio/ktor/http/URLBuilder;", "getUrl", "()Lio/ktor/http/URLBuilder;", "setAttributes", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "urlString", "scheme", "path", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class DefaultRequestBuilder implements HttpMessageBuilder {
        private final HeadersBuilder headers = new HeadersBuilder(0, 1, null);
        private final URLBuilder url = new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
        private final Attributes attributes = AttributesJvmKt.Attributes(true);

        @Override // io.ktor.http.HttpMessageBuilder
        public HeadersBuilder getHeaders() {
            return this.headers;
        }

        public final URLBuilder getUrl() {
            return this.url;
        }

        public final Attributes getAttributes() {
            return this.attributes;
        }

        public final void url(Function1<? super URLBuilder, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            block.invoke(this.url);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void url$default(DefaultRequestBuilder defaultRequestBuilder, String str, String str2, Integer num, String str3, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            if ((i & 2) != 0) {
                str2 = null;
            }
            if ((i & 4) != 0) {
                num = null;
            }
            if ((i & 8) != 0) {
                str3 = null;
            }
            if ((i & 16) != 0) {
                function1 = new Function1<URLBuilder, Unit>() { // from class: io.ktor.client.plugins.DefaultRequest$DefaultRequestBuilder$url$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(URLBuilder uRLBuilder) {
                        invoke2(uRLBuilder);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(URLBuilder $this$null) {
                        Intrinsics.checkNotNullParameter($this$null, "$this$null");
                    }
                };
            }
            defaultRequestBuilder.url(str, str2, num, str3, function1);
        }

        public final void url(String scheme, String host, Integer port, String path, Function1<? super URLBuilder, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            URLBuilderKt.set(this.url, scheme, host, port, path, block);
        }

        public final void url(String urlString) {
            Intrinsics.checkNotNullParameter(urlString, "urlString");
            URLParserKt.takeFrom(this.url, urlString);
        }

        public final String getHost() {
            return this.url.getHost();
        }

        public final void setHost(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this.url.setHost(value);
        }

        public final int getPort() {
            return this.url.getPort();
        }

        public final void setPort(int value) {
            this.url.setPort(value);
        }

        public final void setAttributes(Function1<? super Attributes, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            block.invoke(this.attributes);
        }
    }
}
