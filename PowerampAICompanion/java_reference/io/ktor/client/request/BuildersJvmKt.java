package io.ktor.client.request;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.HttpClient;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLUtilsJvmKt;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: buildersJvm.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u0012\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u0013\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u0014\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u0015\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u0016\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u0017\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u0018\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a8\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"delete", "Lio/ktor/client/statement/HttpResponse;", "Lio/ktor/client/HttpClient;", TrackProviderConsts.COLUMN_URL, "Ljava/net/URL;", "block", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/client/HttpClient;Ljava/net/URL;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "get", "head", "options", "patch", "post", "prepareDelete", "Lio/ktor/client/statement/HttpStatement;", "prepareGet", "prepareHead", "prepareOptions", "preparePatch", "preparePost", "preparePut", "prepareRequest", "put", "request", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BuildersJvmKt {
    public static /* synthetic */ Object request$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$request$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return request(httpClient, url, function1, continuation);
    }

    public static final Object request(HttpClient $this$request, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder $this$request_u24lambda_u240 = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom($this$request_u24lambda_u240.getUrl(), url);
        function1.invoke($this$request_u24lambda_u240);
        return new HttpStatement($this$request_u24lambda_u240, $this$request).execute(continuation);
    }

    public static /* synthetic */ Object get$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$get$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return get(httpClient, url, function1, continuation);
    }

    public static final Object get(HttpClient $this$get, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(builder$iv$iv, $this$get).execute(continuation);
    }

    public static /* synthetic */ Object post$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$post$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return post(httpClient, url, function1, continuation);
    }

    public static final Object post(HttpClient $this$post, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(builder$iv$iv, $this$post).execute(continuation);
    }

    public static /* synthetic */ Object put$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$put$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return put(httpClient, url, function1, continuation);
    }

    public static final Object put(HttpClient $this$put, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(builder$iv$iv, $this$put).execute(continuation);
    }

    public static /* synthetic */ Object patch$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$patch$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return patch(httpClient, url, function1, continuation);
    }

    public static final Object patch(HttpClient $this$patch, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(builder$iv$iv, $this$patch).execute(continuation);
    }

    public static /* synthetic */ Object options$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$options$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return options(httpClient, url, function1, continuation);
    }

    public static final Object options(HttpClient $this$options, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(builder$iv$iv, $this$options).execute(continuation);
    }

    public static /* synthetic */ Object head$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$head$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return head(httpClient, url, function1, continuation);
    }

    public static final Object head(HttpClient $this$head, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(builder$iv$iv, $this$head).execute(continuation);
    }

    public static /* synthetic */ Object delete$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$delete$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return delete(httpClient, url, function1, continuation);
    }

    public static final Object delete(HttpClient $this$delete, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(builder$iv$iv, $this$delete).execute(continuation);
    }

    public static /* synthetic */ Object prepareRequest$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$prepareRequest$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return prepareRequest(httpClient, url, function1, continuation);
    }

    public static final Object prepareRequest(HttpClient $this$prepareRequest, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder $this$prepareRequest_u24lambda_u248 = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom($this$prepareRequest_u24lambda_u248.getUrl(), url);
        function1.invoke($this$prepareRequest_u24lambda_u248);
        return new HttpStatement($this$prepareRequest_u24lambda_u248, $this$prepareRequest);
    }

    public static /* synthetic */ Object prepareGet$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$prepareGet$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return prepareGet(httpClient, url, function1, continuation);
    }

    public static final Object prepareGet(HttpClient $this$prepareGet, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(builder$iv$iv, $this$prepareGet);
    }

    public static /* synthetic */ Object preparePost$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$preparePost$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return preparePost(httpClient, url, function1, continuation);
    }

    public static final Object preparePost(HttpClient $this$preparePost, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(builder$iv$iv, $this$preparePost);
    }

    public static /* synthetic */ Object preparePut$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$preparePut$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return preparePut(httpClient, url, function1, continuation);
    }

    public static final Object preparePut(HttpClient $this$preparePut, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(builder$iv$iv, $this$preparePut);
    }

    public static /* synthetic */ Object preparePatch$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$preparePatch$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return preparePatch(httpClient, url, function1, continuation);
    }

    public static final Object preparePatch(HttpClient $this$preparePatch, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(builder$iv$iv, $this$preparePatch);
    }

    public static /* synthetic */ Object prepareOptions$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$prepareOptions$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return prepareOptions(httpClient, url, function1, continuation);
    }

    public static final Object prepareOptions(HttpClient $this$prepareOptions, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(builder$iv$iv, $this$prepareOptions);
    }

    public static /* synthetic */ Object prepareHead$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$prepareHead$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return prepareHead(httpClient, url, function1, continuation);
    }

    public static final Object prepareHead(HttpClient $this$prepareHead, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(builder$iv$iv, $this$prepareHead);
    }

    public static /* synthetic */ Object prepareDelete$default(HttpClient httpClient, URL url, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.BuildersJvmKt$prepareDelete$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        return prepareDelete(httpClient, url, function1, continuation);
    }

    public static final Object prepareDelete(HttpClient $this$prepareDelete, URL url, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder builder$iv$iv = new HttpRequestBuilder();
        URLUtilsJvmKt.takeFrom(builder$iv$iv.getUrl(), url);
        function1.invoke(builder$iv$iv);
        builder$iv$iv.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(builder$iv$iv, $this$prepareDelete);
    }
}
