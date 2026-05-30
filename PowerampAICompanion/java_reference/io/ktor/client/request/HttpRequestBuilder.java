package io.ktor.client.request;

import androidx.core.app.FrameMetricsAggregator;
import androidx.exifinterface.media.ExifInterface;
import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.client.engine.HttpClientEngineCapabilityKt;
import io.ktor.client.utils.EmptyContent;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.AttributesKt;
import io.ktor.util.InternalAPI;
import io.ktor.util.StringValuesKt;
import io.ktor.util.reflect.TypeInfo;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: HttpRequest.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 <2\u00020\u0001:\u0001<B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010)\u001a\u00020*J%\u0010+\u001a\u0004\u0018\u0001H,\"\b\b\u0000\u0010,*\u00020\b2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0.¢\u0006\u0002\u0010/J\u001f\u00100\u001a\u0002012\u0017\u00102\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020103¢\u0006\u0002\b4J+\u00105\u001a\u000201\"\b\b\u0000\u0010,*\u00020\b2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0.2\u0006\u00106\u001a\u0002H,¢\u0006\u0002\u00107J\u000e\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u0000J\u0010\u0010:\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u0000H\u0007J%\u0010%\u001a\u0002012\u001d\u00102\u001a\u0019\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u0002010;¢\u0006\u0002\b4R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f8F@GX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u0015@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006="}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/http/HttpMessageBuilder;", "()V", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "<set-?>", "", "body", "getBody", "()Ljava/lang/Object;", "setBody", "(Ljava/lang/Object;)V", "value", "Lio/ktor/util/reflect/TypeInfo;", "bodyType", "getBodyType", "()Lio/ktor/util/reflect/TypeInfo;", "setBodyType", "(Lio/ktor/util/reflect/TypeInfo;)V", "Lkotlinx/coroutines/Job;", "executionContext", "getExecutionContext", "()Lkotlinx/coroutines/Job;", "setExecutionContext$ktor_client_core", "(Lkotlinx/coroutines/Job;)V", TrackProviderConsts.COLUMN_HEADERS, "Lio/ktor/http/HeadersBuilder;", "getHeaders", "()Lio/ktor/http/HeadersBuilder;", TrackProviderConsts.COLUMN_HTTP_METHOD, "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "setMethod", "(Lio/ktor/http/HttpMethod;)V", TrackProviderConsts.COLUMN_URL, "Lio/ktor/http/URLBuilder;", "getUrl", "()Lio/ktor/http/URLBuilder;", "build", "Lio/ktor/client/request/HttpRequestData;", "getCapabilityOrNull", ExifInterface.GPS_DIRECTION_TRUE, "key", "Lio/ktor/client/engine/HttpClientEngineCapability;", "(Lio/ktor/client/engine/HttpClientEngineCapability;)Ljava/lang/Object;", "setAttributes", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "setCapability", "capability", "(Lio/ktor/client/engine/HttpClientEngineCapability;Ljava/lang/Object;)V", "takeFrom", "builder", "takeFromWithExecutionContext", "Lkotlin/Function2;", "Companion", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpRequestBuilder implements HttpMessageBuilder {
    private final URLBuilder url = new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
    private HttpMethod method = HttpMethod.INSTANCE.getGet();
    private final HeadersBuilder headers = new HeadersBuilder(0, 1, null);
    private Object body = EmptyContent.INSTANCE;
    private Job executionContext = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
    private final Attributes attributes = AttributesJvmKt.Attributes(true);

    public final URLBuilder getUrl() {
        return this.url;
    }

    public final HttpMethod getMethod() {
        return this.method;
    }

    public final void setMethod(HttpMethod httpMethod) {
        Intrinsics.checkNotNullParameter(httpMethod, "<set-?>");
        this.method = httpMethod;
    }

    @Override // io.ktor.http.HttpMessageBuilder
    public HeadersBuilder getHeaders() {
        return this.headers;
    }

    public final Object getBody() {
        return this.body;
    }

    @InternalAPI
    public final void setBody(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.body = obj;
    }

    public final TypeInfo getBodyType() {
        return (TypeInfo) this.attributes.getOrNull(RequestBodyKt.getBodyTypeAttributeKey());
    }

    @InternalAPI
    public final void setBodyType(TypeInfo value) {
        if (value != null) {
            this.attributes.put(RequestBodyKt.getBodyTypeAttributeKey(), value);
        } else {
            this.attributes.remove(RequestBodyKt.getBodyTypeAttributeKey());
        }
    }

    public final Job getExecutionContext() {
        return this.executionContext;
    }

    public final void setExecutionContext$ktor_client_core(Job job) {
        Intrinsics.checkNotNullParameter(job, "<set-?>");
        this.executionContext = job;
    }

    public final Attributes getAttributes() {
        return this.attributes;
    }

    public final void url(Function2<? super URLBuilder, ? super URLBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        block.invoke(this.url, this.url);
    }

    public final HttpRequestData build() {
        Url build = this.url.build();
        HttpMethod httpMethod = this.method;
        Headers build2 = getHeaders().build();
        Object obj = this.body;
        OutgoingContent outgoingContent = obj instanceof OutgoingContent ? (OutgoingContent) obj : null;
        if (outgoingContent != null) {
            return new HttpRequestData(build, httpMethod, build2, outgoingContent, this.executionContext, this.attributes);
        }
        throw new IllegalStateException(("No request transformation found: " + this.body).toString());
    }

    public final void setAttributes(Function1<? super Attributes, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        block.invoke(this.attributes);
    }

    @InternalAPI
    public final HttpRequestBuilder takeFromWithExecutionContext(HttpRequestBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.executionContext = builder.executionContext;
        return takeFrom(builder);
    }

    public final HttpRequestBuilder takeFrom(HttpRequestBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.method = builder.method;
        this.body = builder.body;
        setBodyType(builder.getBodyType());
        URLUtilsKt.takeFrom(this.url, builder.url);
        this.url.setEncodedPathSegments(this.url.getEncodedPathSegments());
        StringValuesKt.appendAll(getHeaders(), builder.getHeaders());
        AttributesKt.putAll(this.attributes, builder.attributes);
        return this;
    }

    public final <T> void setCapability(HttpClientEngineCapability<T> key, T capability) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(capability, "capability");
        Map capabilities = (Map) this.attributes.computeIfAbsent(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY(), new Function0<Map<HttpClientEngineCapability<?>, Object>>() { // from class: io.ktor.client.request.HttpRequestBuilder$setCapability$capabilities$1
            @Override // kotlin.jvm.functions.Function0
            public final Map<HttpClientEngineCapability<?>, Object> invoke() {
                return new LinkedHashMap();
            }
        });
        capabilities.put(key, capability);
    }

    public final <T> T getCapabilityOrNull(HttpClientEngineCapability<T> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Map map = (Map) this.attributes.getOrNull(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY());
        if (map != null) {
            return (T) map.get(key);
        }
        return null;
    }
}
