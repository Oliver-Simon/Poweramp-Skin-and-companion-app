package io.ktor.client.request;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLBuilderKt;
import io.ktor.http.URLParserKt;
import io.ktor.http.URLUtilsKt;
import io.ktor.util.AttributesKt;
import io.ktor.util.InternalAPI;
import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpRequest.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a#\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u001a&\u0010\u0007\u001a\u00020\b*\u00020\t2\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H\u0086\u0002\u001a]\u0010\u0007\u001a\u00020\b*\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0019\b\u0002\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H\u0086\u0002¢\u0006\u0002\u0010\u0011\u001a\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0014H\u0007\u001a\u0012\u0010\u0015\u001a\u00020\b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017\u001a\u0012\u0010\u0015\u001a\u00020\b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0014\u001a#\u0010\u0018\u001a\u00020\u0005*\u00020\b2\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u001a\u0012\u0010\u0018\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u0019\u001a\u00020\f\u001aZ\u0010\u0018\u001a\u00020\u0005*\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0019\b\u0002\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006¢\u0006\u0002\u0010\u001a¨\u0006\u001b"}, d2 = {TrackProviderConsts.COLUMN_HEADERS, "Lio/ktor/http/HeadersBuilder;", "Lio/ktor/http/HttpMessageBuilder;", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "invoke", "Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/client/request/HttpRequestBuilder$Companion;", "Lio/ktor/http/URLBuilder;", "scheme", "", "host", "port", "", "path", "(Lio/ktor/client/request/HttpRequestBuilder$Companion;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lio/ktor/client/request/HttpRequestBuilder;", "isUpgradeRequest", "", "Lio/ktor/client/request/HttpRequestData;", "takeFrom", "request", "Lio/ktor/client/request/HttpRequest;", TrackProviderConsts.COLUMN_URL, "urlString", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpRequestKt {
    public static final HeadersBuilder headers(HttpMessageBuilder $this$headers, Function1<? super HeadersBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter($this$headers, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        HeadersBuilder headers = $this$headers.getHeaders();
        block.invoke(headers);
        return headers;
    }

    public static final HttpRequestBuilder takeFrom(HttpRequestBuilder $this$takeFrom, HttpRequest request) {
        Intrinsics.checkNotNullParameter($this$takeFrom, "<this>");
        Intrinsics.checkNotNullParameter(request, "request");
        $this$takeFrom.setMethod(request.getMethod());
        $this$takeFrom.setBody(request.getContent());
        $this$takeFrom.setBodyType((TypeInfo) $this$takeFrom.getAttributes().getOrNull(RequestBodyKt.getBodyTypeAttributeKey()));
        URLUtilsKt.takeFrom($this$takeFrom.getUrl(), request.getUrl());
        $this$takeFrom.getHeaders().appendAll(request.getHeaders());
        AttributesKt.putAll($this$takeFrom.getAttributes(), request.getAttributes());
        return $this$takeFrom;
    }

    public static final void url(HttpRequestBuilder $this$url, Function1<? super URLBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter($this$url, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        block.invoke($this$url.getUrl());
    }

    public static final HttpRequestBuilder takeFrom(HttpRequestBuilder $this$takeFrom, HttpRequestData request) {
        Intrinsics.checkNotNullParameter($this$takeFrom, "<this>");
        Intrinsics.checkNotNullParameter(request, "request");
        $this$takeFrom.setMethod(request.getMethod());
        $this$takeFrom.setBody(request.getBody());
        $this$takeFrom.setBodyType((TypeInfo) $this$takeFrom.getAttributes().getOrNull(RequestBodyKt.getBodyTypeAttributeKey()));
        URLUtilsKt.takeFrom($this$takeFrom.getUrl(), request.getUrl());
        $this$takeFrom.getHeaders().appendAll(request.getHeaders());
        AttributesKt.putAll($this$takeFrom.getAttributes(), request.getAttributes());
        return $this$takeFrom;
    }

    public static final HttpRequestBuilder invoke(HttpRequestBuilder.Companion $this$invoke, Function1<? super URLBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter($this$invoke, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        HttpRequestBuilder $this$invoke_u24lambda_u240 = new HttpRequestBuilder();
        url($this$invoke_u24lambda_u240, block);
        return $this$invoke_u24lambda_u240;
    }

    public static /* synthetic */ void url$default(HttpRequestBuilder httpRequestBuilder, String str, String str2, Integer num, String str3, Function1 function1, int i, Object obj) {
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
            function1 = new Function1<URLBuilder, Unit>() { // from class: io.ktor.client.request.HttpRequestKt$url$1
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
        url(httpRequestBuilder, str, str2, num, str3, function1);
    }

    public static final void url(HttpRequestBuilder $this$url, String scheme, String host, Integer port, String path, Function1<? super URLBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter($this$url, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        URLBuilderKt.set($this$url.getUrl(), scheme, host, port, path, block);
    }

    public static /* synthetic */ HttpRequestBuilder invoke$default(HttpRequestBuilder.Companion companion, String str, String str2, Integer num, String str3, Function1 function1, int i, Object obj) {
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
            function1 = new Function1<URLBuilder, Unit>() { // from class: io.ktor.client.request.HttpRequestKt$invoke$2
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
        return invoke(companion, str, str2, num, str3, function1);
    }

    public static final HttpRequestBuilder invoke(HttpRequestBuilder.Companion $this$invoke, String scheme, String host, Integer port, String path, Function1<? super URLBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter($this$invoke, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        HttpRequestBuilder $this$invoke_u24lambda_u241 = new HttpRequestBuilder();
        url($this$invoke_u24lambda_u241, scheme, host, port, path, block);
        return $this$invoke_u24lambda_u241;
    }

    public static final void url(HttpRequestBuilder $this$url, String urlString) {
        Intrinsics.checkNotNullParameter($this$url, "<this>");
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        URLParserKt.takeFrom($this$url.getUrl(), urlString);
    }

    @InternalAPI
    public static final boolean isUpgradeRequest(HttpRequestData $this$isUpgradeRequest) {
        Intrinsics.checkNotNullParameter($this$isUpgradeRequest, "<this>");
        return $this$isUpgradeRequest.getBody() instanceof ClientUpgradeContent;
    }
}
