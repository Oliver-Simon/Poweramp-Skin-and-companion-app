package com.google.ai.client.generativeai.common;

import androidx.exifinterface.media.ExifInterface;
import com.google.ai.client.generativeai.common.util.UtilKt;
import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.HttpClient;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.HttpClientKt;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineFactory;
import io.ktor.client.engine.okhttp.OkHttp;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.UtilsKt;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.content.NullBody;
import io.ktor.http.content.OutgoingContent;
import io.ktor.serialization.kotlinx.json.JsonSupportKt;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.time.Duration;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: APIController.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 (2\u00020\u0001:\u0001(B3\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB9\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u001a2\u0006\u0010\u0012\u001a\u00020\u0017J\u0014\u0010\u001b\u001a\u00020\u001c*\u00020\u001d2\u0006\u0010\u0012\u001a\u00020\u001eH\u0002J\u0015\u0010\u001f\u001a\u00020\u001c*\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010 JB\u0010!\u001a\b\u0012\u0004\u0012\u0002H\"0\u001a\"\n\b\u0000\u0010\"\u0018\u0001*\u00020#*\u00020\u000f2\u0006\u0010$\u001a\u00020\u00032\u0019\b\u0006\u0010%\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001c0&¢\u0006\u0002\b'H\u0082\bR\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lcom/google/ai/client/generativeai/common/APIController;", "", "key", "", "model", "requestOptions", "Lcom/google/ai/client/generativeai/common/RequestOptions;", "apiClient", "headerProvider", "Lcom/google/ai/client/generativeai/common/HeaderProvider;", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/ai/client/generativeai/common/RequestOptions;Ljava/lang/String;Lcom/google/ai/client/generativeai/common/HeaderProvider;)V", "httpEngine", "Lio/ktor/client/engine/HttpClientEngine;", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/ai/client/generativeai/common/RequestOptions;Lio/ktor/client/engine/HttpClientEngine;Ljava/lang/String;Lcom/google/ai/client/generativeai/common/HeaderProvider;)V", "client", "Lio/ktor/client/HttpClient;", "countTokens", "Lcom/google/ai/client/generativeai/common/CountTokensResponse;", "request", "Lcom/google/ai/client/generativeai/common/CountTokensRequest;", "(Lcom/google/ai/client/generativeai/common/CountTokensRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateContent", "Lcom/google/ai/client/generativeai/common/GenerateContentResponse;", "Lcom/google/ai/client/generativeai/common/GenerateContentRequest;", "(Lcom/google/ai/client/generativeai/common/GenerateContentRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateContentStream", "Lkotlinx/coroutines/flow/Flow;", "applyCommonConfiguration", "", "Lio/ktor/client/request/HttpRequestBuilder;", "Lcom/google/ai/client/generativeai/common/Request;", "applyHeaderProvider", "(Lio/ktor/client/request/HttpRequestBuilder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postStream", "R", "Lcom/google/ai/client/generativeai/common/Response;", TrackProviderConsts.COLUMN_URL, "config", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class APIController {
    private static final String TAG = APIController.class.getSimpleName();
    private final String apiClient;
    private final HttpClient client;
    private final HeaderProvider headerProvider;
    private final String key;
    private final String model;
    private final RequestOptions requestOptions;

    public APIController(String key, String model, RequestOptions requestOptions, HttpClientEngine httpEngine, String apiClient, HeaderProvider headerProvider) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(requestOptions, "requestOptions");
        Intrinsics.checkNotNullParameter(httpEngine, "httpEngine");
        Intrinsics.checkNotNullParameter(apiClient, "apiClient");
        this.key = key;
        this.requestOptions = requestOptions;
        this.apiClient = apiClient;
        this.headerProvider = headerProvider;
        this.model = UtilKt.fullModelName(model);
        this.client = HttpClientKt.HttpClient(httpEngine, new Function1<HttpClientConfig<?>, Unit>() { // from class: com.google.ai.client.generativeai.common.APIController$client$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HttpClientConfig<?> httpClientConfig) {
                invoke2(httpClientConfig);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HttpClientConfig<?> HttpClient) {
                Intrinsics.checkNotNullParameter(HttpClient, "$this$HttpClient");
                HttpTimeout.Companion companion = HttpTimeout.INSTANCE;
                final APIController aPIController = APIController.this;
                HttpClient.install(companion, new Function1<HttpTimeout.HttpTimeoutCapabilityConfiguration, Unit>() { // from class: com.google.ai.client.generativeai.common.APIController$client$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration) {
                        invoke2(httpTimeoutCapabilityConfiguration);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(HttpTimeout.HttpTimeoutCapabilityConfiguration install) {
                        RequestOptions requestOptions2;
                        Intrinsics.checkNotNullParameter(install, "$this$install");
                        requestOptions2 = APIController.this.requestOptions;
                        install.setRequestTimeoutMillis(Long.valueOf(Duration.m1866getInWholeMillisecondsimpl(requestOptions2.getTimeout())));
                        install.setSocketTimeoutMillis(80000L);
                    }
                });
                HttpClient.install(ContentNegotiation.Plugin, new Function1<ContentNegotiation.Config, Unit>() { // from class: com.google.ai.client.generativeai.common.APIController$client$1.2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ContentNegotiation.Config config) {
                        invoke2(config);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ContentNegotiation.Config install) {
                        Intrinsics.checkNotNullParameter(install, "$this$install");
                        JsonSupportKt.json$default(install, APIControllerKt.getJSON(), null, 2, null);
                    }
                });
            }
        });
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ APIController(java.lang.String r7, java.lang.String r8, com.google.ai.client.generativeai.common.RequestOptions r9, java.lang.String r10, com.google.ai.client.generativeai.common.HeaderProvider r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r6 = this;
            r12 = r12 & 16
            if (r12 == 0) goto L7
            r11 = 0
            r5 = r11
            goto L8
        L7:
            r5 = r11
        L8:
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.APIController.<init>(java.lang.String, java.lang.String, com.google.ai.client.generativeai.common.RequestOptions, java.lang.String, com.google.ai.client.generativeai.common.HeaderProvider, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public APIController(String key, String model, RequestOptions requestOptions, String apiClient, HeaderProvider headerProvider) {
        this(key, model, requestOptions, HttpClientEngineFactory.DefaultImpls.create$default(OkHttp.INSTANCE, null, 1, null), apiClient, headerProvider);
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(requestOptions, "requestOptions");
        Intrinsics.checkNotNullParameter(apiClient, "apiClient");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0024. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x011c A[Catch: all -> 0x012b, TryCatch #0 {all -> 0x012b, blocks: (B:13:0x0030, B:16:0x011c, B:18:0x0123, B:19:0x012a, B:21:0x0039, B:22:0x00f0, B:25:0x003e, B:27:0x00da, B:32:0x0054, B:33:0x00b8, B:37:0x005d), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0123 A[Catch: all -> 0x012b, TryCatch #0 {all -> 0x012b, blocks: (B:13:0x0030, B:16:0x011c, B:18:0x0123, B:19:0x012a, B:21:0x0039, B:22:0x00f0, B:25:0x003e, B:27:0x00da, B:32:0x0054, B:33:0x00b8, B:37:0x005d), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0119 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003e A[Catch: all -> 0x012b, TRY_LEAVE, TryCatch #0 {all -> 0x012b, blocks: (B:13:0x0030, B:16:0x011c, B:18:0x0123, B:19:0x012a, B:21:0x0039, B:22:0x00f0, B:25:0x003e, B:27:0x00da, B:32:0x0054, B:33:0x00b8, B:37:0x005d), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ed A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object generateContent(com.google.ai.client.generativeai.common.GenerateContentRequest r13, kotlin.coroutines.Continuation<? super com.google.ai.client.generativeai.common.GenerateContentResponse> r14) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.APIController.generateContent(com.google.ai.client.generativeai.common.GenerateContentRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Flow<GenerateContentResponse> generateContentStream(GenerateContentRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        HttpClient $this$postStream$iv = this.client;
        String url$iv = this.requestOptions.getEndpoint() + "/" + this.requestOptions.getApiVersion() + "/" + this.model + ":streamGenerateContent?alt=sse";
        final Flow $this$map$iv = FlowKt.channelFlow(new APIController$generateContentStream$$inlined$postStream$1($this$postStream$iv, url$iv, this, null, this, request));
        return FlowKt.m2039catch(new Flow<GenerateContentResponse>() { // from class: com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
            /* renamed from: com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$map$1$2, reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$map$1$2", f = "APIController.kt", i = {}, l = {223}, m = "emit", n = {}, s = {})
                /* renamed from: com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$map$1$2$1, reason: invalid class name */
                /* loaded from: classes.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
                /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r7
                        com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$map$1$2$1 r0 = (com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r7 = r0.label
                        int r7 = r7 - r2
                        r0.label = r7
                        goto L19
                    L14:
                        com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$map$1$2$1 r0 = new com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L19:
                        java.lang.Object r7 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        switch(r2) {
                            case 0: goto L31;
                            case 1: goto L2c;
                            default: goto L24;
                        }
                    L24:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L2c:
                        r6 = 0
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L4d
                    L31:
                        kotlin.ResultKt.throwOnFailure(r7)
                        r2 = r5
                        kotlinx.coroutines.flow.FlowCollector r2 = r2.$this_unsafeFlow
                        r3 = 0
                        r4 = r0
                        kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                        com.google.ai.client.generativeai.common.GenerateContentResponse r6 = (com.google.ai.client.generativeai.common.GenerateContentResponse) r6
                        r4 = 0
                        com.google.ai.client.generativeai.common.GenerateContentResponse r6 = com.google.ai.client.generativeai.common.APIControllerKt.access$validate(r6)
                        r4 = 1
                        r0.label = r4
                        java.lang.Object r6 = r2.emit(r6, r0)
                        if (r6 != r1) goto L4c
                        return r1
                    L4c:
                        r6 = r3
                    L4d:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super GenerateContentResponse> flowCollector, Continuation $completion) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), $completion);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        }, new APIController$generateContentStream$3(null));
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0024. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x011c A[Catch: all -> 0x0127, TryCatch #0 {all -> 0x0127, blocks: (B:13:0x0030, B:16:0x011c, B:18:0x011f, B:19:0x0126, B:21:0x0039, B:22:0x00f0, B:25:0x003e, B:27:0x00da, B:32:0x0054, B:33:0x00b8, B:37:0x005d), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x011f A[Catch: all -> 0x0127, TryCatch #0 {all -> 0x0127, blocks: (B:13:0x0030, B:16:0x011c, B:18:0x011f, B:19:0x0126, B:21:0x0039, B:22:0x00f0, B:25:0x003e, B:27:0x00da, B:32:0x0054, B:33:0x00b8, B:37:0x005d), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0119 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003e A[Catch: all -> 0x0127, TRY_LEAVE, TryCatch #0 {all -> 0x0127, blocks: (B:13:0x0030, B:16:0x011c, B:18:0x011f, B:19:0x0126, B:21:0x0039, B:22:0x00f0, B:25:0x003e, B:27:0x00da, B:32:0x0054, B:33:0x00b8, B:37:0x005d), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ed A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object countTokens(com.google.ai.client.generativeai.common.CountTokensRequest r13, kotlin.coroutines.Continuation<? super com.google.ai.client.generativeai.common.CountTokensResponse> r14) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.APIController.countTokens(com.google.ai.client.generativeai.common.CountTokensRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyCommonConfiguration(HttpRequestBuilder $this$applyCommonConfiguration, Request request) {
        if (!(request instanceof GenerateContentRequest)) {
            if (request instanceof CountTokensRequest) {
                if (request == null) {
                    $this$applyCommonConfiguration.setBody(NullBody.INSTANCE);
                    KType kType$iv$iv = Reflection.typeOf(CountTokensRequest.class);
                    Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
                    $this$applyCommonConfiguration.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(CountTokensRequest.class), kType$iv$iv));
                } else if (request instanceof OutgoingContent) {
                    $this$applyCommonConfiguration.setBody(request);
                    $this$applyCommonConfiguration.setBodyType(null);
                } else {
                    $this$applyCommonConfiguration.setBody(request);
                    KType kType$iv$iv2 = Reflection.typeOf(CountTokensRequest.class);
                    Type reifiedType$iv$iv2 = TypesJVMKt.getJavaType(kType$iv$iv2);
                    $this$applyCommonConfiguration.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv2, Reflection.getOrCreateKotlinClass(CountTokensRequest.class), kType$iv$iv2));
                }
            }
        } else if (request == null) {
            $this$applyCommonConfiguration.setBody(NullBody.INSTANCE);
            KType kType$iv$iv3 = Reflection.typeOf(GenerateContentRequest.class);
            Type reifiedType$iv$iv3 = TypesJVMKt.getJavaType(kType$iv$iv3);
            $this$applyCommonConfiguration.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv3, Reflection.getOrCreateKotlinClass(GenerateContentRequest.class), kType$iv$iv3));
        } else if (request instanceof OutgoingContent) {
            $this$applyCommonConfiguration.setBody(request);
            $this$applyCommonConfiguration.setBodyType(null);
        } else {
            $this$applyCommonConfiguration.setBody(request);
            KType kType$iv$iv4 = Reflection.typeOf(GenerateContentRequest.class);
            Type reifiedType$iv$iv4 = TypesJVMKt.getJavaType(kType$iv$iv4);
            $this$applyCommonConfiguration.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv4, Reflection.getOrCreateKotlinClass(GenerateContentRequest.class), kType$iv$iv4));
        }
        HttpMessagePropertiesKt.contentType($this$applyCommonConfiguration, ContentType.Application.INSTANCE.getJson());
        UtilsKt.header($this$applyCommonConfiguration, "x-goog-api-key", this.key);
        UtilsKt.header($this$applyCommonConfiguration, "x-goog-api-client", this.apiClient);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(6:1|(2:3|(4:5|6|7|8))|24|6|7|8) */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0054, code lost:
    
        android.util.Log.w(com.google.ai.client.generativeai.common.APIController.TAG, "HeaderProvided timed out without generating headers, ignoring");
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c A[Catch: TimeoutCancellationException -> 0x0030, TRY_ENTER, TRY_LEAVE, TryCatch #0 {TimeoutCancellationException -> 0x0030, blocks: (B:12:0x002c, B:19:0x003b), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object applyHeaderProvider(io.ktor.client.request.HttpRequestBuilder r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.google.ai.client.generativeai.common.APIController$applyHeaderProvider$1
            if (r0 == 0) goto L14
            r0 = r9
            com.google.ai.client.generativeai.common.APIController$applyHeaderProvider$1 r0 = (com.google.ai.client.generativeai.common.APIController$applyHeaderProvider$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            com.google.ai.client.generativeai.common.APIController$applyHeaderProvider$1 r0 = new com.google.ai.client.generativeai.common.APIController$applyHeaderProvider$1
            r0.<init>(r7, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L32;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L30
            goto L53
        L30:
            r8 = move-exception
            goto L54
        L32:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
            com.google.ai.client.generativeai.common.HeaderProvider r3 = r2.headerProvider
            if (r3 == 0) goto L5b
        L3b:
            com.google.ai.client.generativeai.common.HeaderProvider r3 = r2.headerProvider     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L30
            long r3 = r3.m82getTimeoutUwyO8pc()     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L30
            com.google.ai.client.generativeai.common.APIController$applyHeaderProvider$2 r5 = new com.google.ai.client.generativeai.common.APIController$applyHeaderProvider$2     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L30
            r6 = 0
            r5.<init>(r2, r8, r6)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L30
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L30
            r6 = 1
            r0.label = r6     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L30
            java.lang.Object r3 = kotlinx.coroutines.TimeoutKt.m2007withTimeoutKLykuaI(r3, r5, r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L30
            if (r3 != r1) goto L53
            return r1
        L53:
            goto L5b
        L54:
            java.lang.String r8 = com.google.ai.client.generativeai.common.APIController.TAG
            java.lang.String r1 = "HeaderProvided timed out without generating headers, ignoring"
            android.util.Log.w(r8, r1)
        L5b:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.APIController.applyHeaderProvider(io.ktor.client.request.HttpRequestBuilder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Flow postStream$default(APIController $this, HttpClient $receiver, String url, Function1 config, int i, Object obj) {
        Function1 config2;
        if ((i & 2) == 0) {
            config2 = config;
        } else {
            Function1 config3 = new Function1<HttpRequestBuilder, Unit>() { // from class: com.google.ai.client.generativeai.common.APIController$postStream$1
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
            config2 = config3;
        }
        Intrinsics.needClassReification();
        return FlowKt.channelFlow(new APIController$postStream$2($receiver, url, $this, config2, null));
    }

    private final /* synthetic */ <R extends Response> Flow<R> postStream(HttpClient $this$postStream, String url, Function1<? super HttpRequestBuilder, Unit> function1) {
        Intrinsics.needClassReification();
        return FlowKt.channelFlow(new APIController$postStream$2($this$postStream, url, this, function1, null));
    }
}
