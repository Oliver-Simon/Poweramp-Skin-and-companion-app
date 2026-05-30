package com.google.ai.client.generativeai.common;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestKt;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HttpMethod;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: APIController.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "R", "Lcom/google/ai/client/generativeai/common/Response;", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.common.APIController$postStream$2", f = "APIController.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class APIController$postStream$2<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<HttpRequestBuilder, Unit> $config;
    final /* synthetic */ HttpClient $this_postStream;
    final /* synthetic */ String $url;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ APIController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public APIController$postStream$2(HttpClient httpClient, String str, APIController aPIController, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super APIController$postStream$2> continuation) {
        super(2, continuation);
        this.$this_postStream = httpClient;
        this.$url = str;
        this.this$0 = aPIController;
        this.$config = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.needClassReification();
        APIController$postStream$2 aPIController$postStream$2 = new APIController$postStream$2(this.$this_postStream, this.$url, this.this$0, this.$config, continuation);
        aPIController$postStream$2.L$0 = obj;
        return aPIController$postStream$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
        return ((APIController$postStream$2) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: APIController.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "R", "Lcom/google/ai/client/generativeai/common/Response;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "com.google.ai.client.generativeai.common.APIController$postStream$2$1", f = "APIController.kt", i = {0}, l = {193, 196}, m = "invokeSuspend", n = {"$this$invokeSuspend_u24lambda_u240"}, s = {"L$3"})
    /* renamed from: com.google.ai.client.generativeai.common.APIController$postStream$2$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ProducerScope<R> $$this$channelFlow;
        final /* synthetic */ Function1<HttpRequestBuilder, Unit> $config;
        final /* synthetic */ HttpClient $this_postStream;
        final /* synthetic */ String $url;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ APIController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(HttpClient httpClient, String str, APIController aPIController, Function1<? super HttpRequestBuilder, Unit> function1, ProducerScope<? super R> producerScope, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_postStream = httpClient;
            this.$url = str;
            this.this$0 = aPIController;
            this.$config = function1;
            this.$$this$channelFlow = producerScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$this_postStream, this.$url, this.this$0, this.$config, this.$$this$channelFlow, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:12:0x009c A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:13:0x009d  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r12.label
                switch(r1) {
                    case 0: goto L30;
                    case 1: goto L17;
                    case 2: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L11:
                r0 = r12
                kotlin.ResultKt.throwOnFailure(r13)
                goto L9e
            L17:
                r1 = r12
                r2 = 0
                r3 = 0
                r4 = 0
                r5 = 0
                java.lang.Object r6 = r1.L$3
                io.ktor.client.request.HttpRequestBuilder r6 = (io.ktor.client.request.HttpRequestBuilder) r6
                java.lang.Object r7 = r1.L$2
                io.ktor.client.request.HttpRequestBuilder r7 = (io.ktor.client.request.HttpRequestBuilder) r7
                java.lang.Object r8 = r1.L$1
                io.ktor.client.HttpClient r8 = (io.ktor.client.HttpClient) r8
                java.lang.Object r9 = r1.L$0
                kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
                kotlin.ResultKt.throwOnFailure(r13)
                goto L61
            L30:
                kotlin.ResultKt.throwOnFailure(r13)
                r1 = r12
                io.ktor.client.HttpClient r2 = r1.$this_postStream
                java.lang.String r3 = r1.$url
                com.google.ai.client.generativeai.common.APIController r4 = r1.this$0
                kotlin.jvm.functions.Function1<io.ktor.client.request.HttpRequestBuilder, kotlin.Unit> r9 = r1.$config
                r5 = 0
                r8 = r2
                r2 = 0
                io.ktor.client.request.HttpRequestBuilder r7 = new io.ktor.client.request.HttpRequestBuilder
                r7.<init>()
                r6 = r7
                r10 = 0
                io.ktor.client.request.HttpRequestKt.url(r6, r3)
                r3 = 0
                r1.L$0 = r9
                r1.L$1 = r8
                r1.L$2 = r7
                r1.L$3 = r6
                r11 = 1
                r1.label = r11
                java.lang.Object r4 = com.google.ai.client.generativeai.common.APIController.access$applyHeaderProvider(r4, r6, r1)
                if (r4 != r0) goto L5c
                return r0
            L5c:
                r4 = r3
                r3 = r2
                r2 = r5
                r5 = r4
                r4 = r10
            L61:
                r9.invoke(r6)
                r4 = 0
                io.ktor.http.HttpMethod$Companion r5 = io.ktor.http.HttpMethod.INSTANCE
                io.ktor.http.HttpMethod r5 = r5.getPost()
                r7.setMethod(r5)
                r5 = r8
                r6 = 0
                io.ktor.client.statement.HttpStatement r9 = new io.ktor.client.statement.HttpStatement
                r9.<init>(r7, r5)
                kotlin.jvm.internal.Intrinsics.needClassReification()
                com.google.ai.client.generativeai.common.APIController$postStream$2$1$2 r2 = new com.google.ai.client.generativeai.common.APIController$postStream$2$1$2
                kotlinx.coroutines.channels.ProducerScope<R> r3 = r1.$$this$channelFlow
                r4 = 0
                r2.<init>(r3, r4)
                kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
                r3 = r1
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r1.L$0 = r4
                r1.L$1 = r4
                r1.L$2 = r4
                r1.L$3 = r4
                r4 = 2
                r1.label = r4
                java.lang.Object r2 = r9.execute(r2, r3)
                if (r2 != r0) goto L9d
                return r0
            L9d:
                r0 = r1
            L9e:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.APIController$postStream$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        public final Object invokeSuspend$$forInline(Object $result) {
            HttpClient $this$preparePost$iv = this.$this_postStream;
            String urlString$iv = this.$url;
            APIController aPIController = this.this$0;
            Function1<HttpRequestBuilder, Unit> function1 = this.$config;
            HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
            HttpRequestBuilder $this$preparePost_u24lambda_u2412$iv = httpRequestBuilder;
            HttpRequestKt.url($this$preparePost_u24lambda_u2412$iv, urlString$iv);
            HttpRequestBuilder $this$invokeSuspend_u24lambda_u240 = $this$preparePost_u24lambda_u2412$iv;
            aPIController.applyHeaderProvider($this$invokeSuspend_u24lambda_u240, null);
            function1.invoke($this$invokeSuspend_u24lambda_u240);
            Unit unit = Unit.INSTANCE;
            Unit unit2 = Unit.INSTANCE;
            HttpRequestBuilder builder$iv$iv$iv = httpRequestBuilder;
            builder$iv$iv$iv.setMethod(HttpMethod.INSTANCE.getPost());
            HttpStatement httpStatement = new HttpStatement(builder$iv$iv$iv, $this$preparePost$iv);
            Intrinsics.needClassReification();
            httpStatement.execute(new AnonymousClass2(this.$$this$channelFlow, null), this);
            return Unit.INSTANCE;
        }

        /* compiled from: APIController.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "R", "Lcom/google/ai/client/generativeai/common/Response;", "it", "Lio/ktor/client/statement/HttpResponse;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "com.google.ai.client.generativeai.common.APIController$postStream$2$1$2", f = "APIController.kt", i = {0}, l = {197, 199, 202}, m = "invokeSuspend", n = {"it"}, s = {"L$0"})
        /* renamed from: com.google.ai.client.generativeai.common.APIController$postStream$2$1$2, reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<HttpResponse, Continuation<? super Unit>, Object> {
            final /* synthetic */ ProducerScope<R> $$this$channelFlow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass2(ProducerScope<? super R> producerScope, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$$this$channelFlow = producerScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$$this$channelFlow, continuation);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(HttpResponse httpResponse, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(httpResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
            /* JADX WARN: Removed duplicated region for block: B:12:0x007e A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:13:0x007f  */
            /* JADX WARN: Removed duplicated region for block: B:17:0x004d A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                /*
                    r8 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r8.label
                    r2 = 0
                    switch(r1) {
                        case 0: goto L28;
                        case 1: goto L1f;
                        case 2: goto L18;
                        case 3: goto L12;
                        default: goto La;
                    }
                La:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r0)
                    throw r9
                L12:
                    r0 = r8
                    kotlin.ResultKt.throwOnFailure(r9)
                    goto L81
                L18:
                    r1 = r8
                    kotlin.ResultKt.throwOnFailure(r9)
                    r3 = r1
                    r1 = r9
                    goto L52
                L1f:
                    r1 = r8
                    java.lang.Object r3 = r1.L$0
                    io.ktor.client.statement.HttpResponse r3 = (io.ktor.client.statement.HttpResponse) r3
                    kotlin.ResultKt.throwOnFailure(r9)
                    goto L3f
                L28:
                    kotlin.ResultKt.throwOnFailure(r9)
                    r1 = r8
                    java.lang.Object r3 = r1.L$0
                    io.ktor.client.statement.HttpResponse r3 = (io.ktor.client.statement.HttpResponse) r3
                    r4 = r1
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r1.L$0 = r3
                    r5 = 1
                    r1.label = r5
                    java.lang.Object r4 = com.google.ai.client.generativeai.common.APIControllerKt.access$validateResponse(r3, r4)
                    if (r4 != r0) goto L3f
                    return r0
                L3f:
                    r4 = r1
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r1.L$0 = r2
                    r5 = 2
                    r1.label = r5
                    java.lang.Object r3 = io.ktor.client.statement.HttpResponseKt.bodyAsChannel(r3, r4)
                    if (r3 != r0) goto L4e
                    return r0
                L4e:
                    r7 = r1
                    r1 = r9
                    r9 = r3
                    r3 = r7
                L52:
                    io.ktor.utils.io.ByteReadChannel r9 = (io.ktor.utils.io.ByteReadChannel) r9
                    kotlinx.serialization.json.Json r4 = com.google.ai.client.generativeai.common.APIControllerKt.getJSON()
                    r5 = 0
                    kotlin.jvm.internal.Intrinsics.needClassReification()
                    com.google.ai.client.generativeai.common.APIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1 r6 = new com.google.ai.client.generativeai.common.APIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1
                    r6.<init>(r9, r4, r2)
                    kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                    kotlinx.coroutines.flow.Flow r9 = kotlinx.coroutines.flow.FlowKt.channelFlow(r6)
                    com.google.ai.client.generativeai.common.APIController$postStream$2$1$2$1 r2 = new com.google.ai.client.generativeai.common.APIController$postStream$2$1$2$1
                    kotlinx.coroutines.channels.ProducerScope<R> r4 = r3.$$this$channelFlow
                    r2.<init>()
                    kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                    r4 = r3
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r5 = 3
                    r3.label = r5
                    java.lang.Object r9 = r9.collect(r2, r4)
                    if (r9 != r0) goto L7f
                    return r0
                L7f:
                    r9 = r1
                    r0 = r3
                L81:
                    kotlin.Unit r1 = kotlin.Unit.INSTANCE
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.APIController$postStream$2.AnonymousClass1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                ProducerScope $this$channelFlow = (ProducerScope) this.L$0;
                CoroutineName coroutineName = new CoroutineName("postStream");
                Intrinsics.needClassReification();
                BuildersKt__Builders_commonKt.launch$default($this$channelFlow, coroutineName, null, new AnonymousClass1(this.$this_postStream, this.$url, this.this$0, this.$config, $this$channelFlow, null), 2, null);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend$$forInline(Object $result) {
        ProducerScope $this$channelFlow = (ProducerScope) this.L$0;
        CoroutineName coroutineName = new CoroutineName("postStream");
        Intrinsics.needClassReification();
        BuildersKt__Builders_commonKt.launch$default($this$channelFlow, coroutineName, null, new AnonymousClass1(this.$this_postStream, this.$url, this.this$0, this.$config, $this$channelFlow, null), 2, null);
        return Unit.INSTANCE;
    }
}
