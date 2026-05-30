package com.google.ai.client.generativeai.common;

import androidx.exifinterface.media.ExifInterface;
import com.google.ai.client.generativeai.common.util.KtorKt;
import io.ktor.client.HttpClient;
import io.ktor.client.statement.HttpResponse;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.serialization.json.Json;

/* compiled from: APIController.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "", "R", "Lcom/google/ai/client/generativeai/common/Response;", "Lkotlinx/coroutines/channels/ProducerScope;", "com/google/ai/client/generativeai/common/APIController$postStream$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1", f = "APIController.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class APIController$generateContentStream$$inlined$postStream$1 extends SuspendLambda implements Function2<ProducerScope<? super GenerateContentResponse>, Continuation<? super Unit>, Object> {
    final /* synthetic */ GenerateContentRequest $request$inlined;
    final /* synthetic */ HttpClient $this_postStream;
    final /* synthetic */ String $url;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ APIController this$0;
    final /* synthetic */ APIController this$0$inline_fun;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public APIController$generateContentStream$$inlined$postStream$1(HttpClient httpClient, String str, APIController aPIController, Continuation continuation, APIController aPIController2, GenerateContentRequest generateContentRequest) {
        super(2, continuation);
        this.$this_postStream = httpClient;
        this.$url = str;
        this.this$0$inline_fun = aPIController;
        this.this$0 = aPIController2;
        this.$request$inlined = generateContentRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        APIController$generateContentStream$$inlined$postStream$1 aPIController$generateContentStream$$inlined$postStream$1 = new APIController$generateContentStream$$inlined$postStream$1(this.$this_postStream, this.$url, this.this$0$inline_fun, continuation, this.this$0, this.$request$inlined);
        aPIController$generateContentStream$$inlined$postStream$1.L$0 = obj;
        return aPIController$generateContentStream$$inlined$postStream$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super GenerateContentResponse> producerScope, Continuation<? super Unit> continuation) {
        return ((APIController$generateContentStream$$inlined$postStream$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: APIController.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "", "R", "Lcom/google/ai/client/generativeai/common/Response;", "Lkotlinx/coroutines/CoroutineScope;", "com/google/ai/client/generativeai/common/APIController$postStream$2$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1", f = "APIController.kt", i = {0}, l = {193, 196}, m = "invokeSuspend", n = {"$this$invokeSuspend_u24lambda_u240"}, s = {"L$2"})
    /* renamed from: com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ProducerScope<GenerateContentResponse> $$this$channelFlow;
        final /* synthetic */ GenerateContentRequest $request$inlined;
        final /* synthetic */ HttpClient $this_postStream;
        final /* synthetic */ String $url;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ APIController this$0;
        final /* synthetic */ APIController this$0$inline_fun;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(HttpClient httpClient, String str, APIController aPIController, ProducerScope producerScope, Continuation continuation, APIController aPIController2, GenerateContentRequest generateContentRequest) {
            super(2, continuation);
            this.$this_postStream = httpClient;
            this.$url = str;
            this.this$0$inline_fun = aPIController;
            this.this$0 = aPIController2;
            this.$request$inlined = generateContentRequest;
            this.$$this$channelFlow = producerScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$this_postStream, this.$url, this.this$0$inline_fun, this.$$this$channelFlow, continuation, this.this$0, this.$request$inlined);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: APIController.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@¨\u0006\u0006"}, d2 = {"<anonymous>", "", "R", "Lcom/google/ai/client/generativeai/common/Response;", "it", "Lio/ktor/client/statement/HttpResponse;", "com/google/ai/client/generativeai/common/APIController$postStream$2$1$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "com.google.ai.client.generativeai.common.APIController$postStream$2$1$2", f = "APIController.kt", i = {0}, l = {197, 199, 202}, m = "invokeSuspend", n = {"it"}, s = {"L$0"})
        /* renamed from: com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C00101 extends SuspendLambda implements Function2<HttpResponse, Continuation<? super Unit>, Object> {
            final /* synthetic */ ProducerScope<GenerateContentResponse> $$this$channelFlow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00101(ProducerScope producerScope, Continuation continuation) {
                super(2, continuation);
                this.$$this$channelFlow = producerScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00101 c00101 = new C00101(this.$$this$channelFlow, continuation);
                c00101.L$0 = obj;
                return c00101;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(HttpResponse httpResponse, Continuation<? super Unit> continuation) {
                return ((C00101) create(httpResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* compiled from: ktor.kt */
            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/channels/ProducerScope;", "com/google/ai/client/generativeai/common/util/KtorKt$decodeToFlow$1", "com/google/ai/client/generativeai/common/APIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "com.google.ai.client.generativeai.common.util.KtorKt$decodeToFlow$1", f = "ktor.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1$1$1, reason: invalid class name and collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C00111 extends SuspendLambda implements Function2<ProducerScope<? super GenerateContentResponse>, Continuation<? super Unit>, Object> {
                final /* synthetic */ ByteReadChannel $channel;
                final /* synthetic */ Json $this_decodeToFlow;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00111(ByteReadChannel byteReadChannel, Json json, Continuation continuation) {
                    super(2, continuation);
                    this.$channel = byteReadChannel;
                    this.$this_decodeToFlow = json;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00111 c00111 = new C00111(this.$channel, this.$this_decodeToFlow, continuation);
                    c00111.L$0 = obj;
                    return c00111;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(ProducerScope<? super GenerateContentResponse> producerScope, Continuation<? super Unit> continuation) {
                    return ((C00111) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* compiled from: ktor.kt */
                @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@¨\u0006\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "it", "", "com/google/ai/client/generativeai/common/util/KtorKt$decodeToFlow$1$1", "com/google/ai/client/generativeai/common/APIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "com.google.ai.client.generativeai.common.util.KtorKt$decodeToFlow$1$1", f = "ktor.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                /* loaded from: classes.dex */
                public static final class C00121 extends SuspendLambda implements Function2<String, Continuation<? super Unit>, Object> {
                    final /* synthetic */ ProducerScope<GenerateContentResponse> $$this$channelFlow;
                    final /* synthetic */ Json $this_decodeToFlow;
                    /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00121(ProducerScope producerScope, Json json, Continuation continuation) {
                        super(2, continuation);
                        this.$this_decodeToFlow = json;
                        this.$$this$channelFlow = producerScope;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00121 c00121 = new C00121(this.$$this$channelFlow, this.$this_decodeToFlow, continuation);
                        c00121.L$0 = obj;
                        return c00121;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(String str, Continuation<? super Unit> continuation) {
                        return ((C00121) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                String it = (String) this.L$0;
                                String it2 = StringsKt.removePrefix(it, (CharSequence) "data:");
                                SendChannel sendChannel = this.$$this$channelFlow;
                                Json this_$iv = this.$this_decodeToFlow;
                                this_$iv.getSerializersModule();
                                this.label = 1;
                                if (sendChannel.send(this_$iv.decodeFromString(GenerateContentResponse.INSTANCE.serializer(), it2), this) != coroutine_suspended) {
                                    break;
                                } else {
                                    return coroutine_suspended;
                                }
                            case 1:
                                ResultKt.throwOnFailure($result);
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            ProducerScope $this$channelFlow = (ProducerScope) this.L$0;
                            this.label = 1;
                            if (KtorKt.onEachLine(this.$channel, new C00121($this$channelFlow, this.$this_decodeToFlow, null), this) != coroutine_suspended) {
                                break;
                            } else {
                                return coroutine_suspended;
                            }
                        case 1:
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
            /* JADX WARN: Removed duplicated region for block: B:12:0x007b A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:13:0x007c  */
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
                    goto L7e
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
                    com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1$1$1 r6 = new com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1$1$1
                    r6.<init>(r9, r4, r2)
                    kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                    kotlinx.coroutines.flow.Flow r9 = kotlinx.coroutines.flow.FlowKt.channelFlow(r6)
                    com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1$1$2 r2 = new com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1$1$2
                    kotlinx.coroutines.channels.ProducerScope<com.google.ai.client.generativeai.common.GenerateContentResponse> r4 = r3.$$this$channelFlow
                    r2.<init>()
                    kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                    r4 = r3
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r5 = 3
                    r3.label = r5
                    java.lang.Object r9 = r9.collect(r2, r4)
                    if (r9 != r0) goto L7c
                    return r0
                L7c:
                    r9 = r1
                    r0 = r3
                L7e:
                    kotlin.Unit r1 = kotlin.Unit.INSTANCE
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1.AnonymousClass1.C00101.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0099 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:13:0x009a  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                r13 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r13.label
                switch(r1) {
                    case 0: goto L2c;
                    case 1: goto L17;
                    case 2: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L11:
                r0 = r13
                kotlin.ResultKt.throwOnFailure(r14)
                goto L9b
            L17:
                r1 = r13
                r2 = 0
                r3 = 0
                r4 = 0
                r5 = 0
                java.lang.Object r6 = r1.L$2
                io.ktor.client.request.HttpRequestBuilder r6 = (io.ktor.client.request.HttpRequestBuilder) r6
                java.lang.Object r7 = r1.L$1
                io.ktor.client.request.HttpRequestBuilder r7 = (io.ktor.client.request.HttpRequestBuilder) r7
                java.lang.Object r8 = r1.L$0
                io.ktor.client.HttpClient r8 = (io.ktor.client.HttpClient) r8
                kotlin.ResultKt.throwOnFailure(r14)
                goto L59
            L2c:
                kotlin.ResultKt.throwOnFailure(r14)
                r1 = r13
                io.ktor.client.HttpClient r2 = r1.$this_postStream
                java.lang.String r3 = r1.$url
                com.google.ai.client.generativeai.common.APIController r4 = r1.this$0$inline_fun
                r5 = 0
                r8 = r2
                r2 = 0
                io.ktor.client.request.HttpRequestBuilder r7 = new io.ktor.client.request.HttpRequestBuilder
                r7.<init>()
                r6 = r7
                r9 = 0
                io.ktor.client.request.HttpRequestKt.url(r6, r3)
                r3 = 0
                r1.L$0 = r8
                r1.L$1 = r7
                r1.L$2 = r6
                r10 = 1
                r1.label = r10
                java.lang.Object r4 = com.google.ai.client.generativeai.common.APIController.access$applyHeaderProvider(r4, r6, r1)
                if (r4 != r0) goto L54
                return r0
            L54:
                r4 = r3
                r3 = r2
                r2 = r5
                r5 = r4
                r4 = r9
            L59:
                r9 = r6
                r10 = 0
                com.google.ai.client.generativeai.common.APIController r11 = r1.this$0
                com.google.ai.client.generativeai.common.GenerateContentRequest r12 = r1.$request$inlined
                com.google.ai.client.generativeai.common.Request r12 = (com.google.ai.client.generativeai.common.Request) r12
                com.google.ai.client.generativeai.common.APIController.access$applyCommonConfiguration(r11, r9, r12)
                r4 = 0
                io.ktor.http.HttpMethod$Companion r5 = io.ktor.http.HttpMethod.INSTANCE
                io.ktor.http.HttpMethod r5 = r5.getPost()
                r7.setMethod(r5)
                r5 = r8
                r6 = 0
                io.ktor.client.statement.HttpStatement r9 = new io.ktor.client.statement.HttpStatement
                r9.<init>(r7, r5)
                com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1$1 r2 = new com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1$1$1
                kotlinx.coroutines.channels.ProducerScope<com.google.ai.client.generativeai.common.GenerateContentResponse> r3 = r1.$$this$channelFlow
                r4 = 0
                r2.<init>(r3, r4)
                kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
                r3 = r1
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r1.L$0 = r4
                r1.L$1 = r4
                r1.L$2 = r4
                r4 = 2
                r1.label = r4
                java.lang.Object r2 = r9.execute(r2, r3)
                if (r2 != r0) goto L9a
                return r0
            L9a:
                r0 = r1
            L9b:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.APIController$generateContentStream$$inlined$postStream$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                ProducerScope $this$channelFlow = (ProducerScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default($this$channelFlow, new CoroutineName("postStream"), null, new AnonymousClass1(this.$this_postStream, this.$url, this.this$0$inline_fun, $this$channelFlow, null, this.this$0, this.$request$inlined), 2, null);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
