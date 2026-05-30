package io.ktor.client;

import androidx.core.app.NotificationCompat;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.client.engine.HttpClientEngineConfig;
import io.ktor.client.plugins.BodyProgress;
import io.ktor.client.plugins.DefaultResponseValidationKt;
import io.ktor.client.plugins.DefaultTransformKt;
import io.ktor.client.plugins.HttpCallValidator;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.client.plugins.HttpPlainText;
import io.ktor.client.plugins.HttpRedirect;
import io.ktor.client.plugins.HttpRequestLifecycle;
import io.ktor.client.plugins.HttpSend;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.request.HttpSendPipeline;
import io.ktor.client.statement.HttpReceivePipeline;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.client.utils.ClientEventsKt;
import io.ktor.client.utils.HttpResponseReceiveFail;
import io.ktor.events.Events;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.pipeline.PipelineContext;
import java.io.Closeable;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: HttpClient.kt */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0018\u00002\u00020R2\u00060Sj\u0002`TB)\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB!\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0004\b\b\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ*\u0010\u0011\u001a\u00020\u00002\u001b\u0010\u0010\u001a\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u000b0\u000e¢\u0006\u0002\b\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0013H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u001b\u001a\u00020\u00062\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010!\u001a\u00020 8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0011\u0010(\u001a\u0004\b)\u0010*R\u001a\u0010,\u001a\u00020+8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u0017\u0010\u0002\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0002\u00100\u001a\u0004\b1\u00102R\u0017\u00103\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u00107R\u0017\u00109\u001a\u0002088\u0006¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u0017\u0010>\u001a\u00020=8\u0006¢\u0006\f\n\u0004\b>\u0010?\u001a\u0004\b@\u0010AR\u0017\u0010C\u001a\u00020B8\u0006¢\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bE\u0010FR\u0017\u0010H\u001a\u00020G8\u0006¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010KR\u0017\u0010M\u001a\u00020L8\u0006¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010PR\u001c\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Q"}, d2 = {"Lio/ktor/client/HttpClient;", "Lio/ktor/client/engine/HttpClientEngine;", "engine", "Lio/ktor/client/HttpClientConfig;", "Lio/ktor/client/engine/HttpClientEngineConfig;", "userConfig", "", "manageEngine", "<init>", "(Lio/ktor/client/engine/HttpClientEngine;Lio/ktor/client/HttpClientConfig;Z)V", "(Lio/ktor/client/engine/HttpClientEngine;Lio/ktor/client/HttpClientConfig;)V", "", "close", "()V", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "block", "config", "(Lkotlin/jvm/functions/Function1;)Lio/ktor/client/HttpClient;", "Lio/ktor/client/request/HttpRequestBuilder;", "builder", "Lio/ktor/client/call/HttpClientCall;", "execute$ktor_client_core", "(Lio/ktor/client/request/HttpRequestBuilder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "execute", "Lio/ktor/client/engine/HttpClientEngineCapability;", "capability", "isSupported", "(Lio/ktor/client/engine/HttpClientEngineCapability;)Z", "", "toString", "()Ljava/lang/String;", "Lio/ktor/util/Attributes;", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "Lkotlinx/coroutines/CompletableJob;", "clientJob", "Lkotlinx/coroutines/CompletableJob;", "Lio/ktor/client/HttpClientConfig;", "getConfig$ktor_client_core", "()Lio/ktor/client/HttpClientConfig;", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "Lio/ktor/client/engine/HttpClientEngine;", "getEngine", "()Lio/ktor/client/engine/HttpClientEngine;", "engineConfig", "Lio/ktor/client/engine/HttpClientEngineConfig;", "getEngineConfig", "()Lio/ktor/client/engine/HttpClientEngineConfig;", "Z", "Lio/ktor/events/Events;", "monitor", "Lio/ktor/events/Events;", "getMonitor", "()Lio/ktor/events/Events;", "Lio/ktor/client/statement/HttpReceivePipeline;", "receivePipeline", "Lio/ktor/client/statement/HttpReceivePipeline;", "getReceivePipeline", "()Lio/ktor/client/statement/HttpReceivePipeline;", "Lio/ktor/client/request/HttpRequestPipeline;", "requestPipeline", "Lio/ktor/client/request/HttpRequestPipeline;", "getRequestPipeline", "()Lio/ktor/client/request/HttpRequestPipeline;", "Lio/ktor/client/statement/HttpResponsePipeline;", "responsePipeline", "Lio/ktor/client/statement/HttpResponsePipeline;", "getResponsePipeline", "()Lio/ktor/client/statement/HttpResponsePipeline;", "Lio/ktor/client/request/HttpSendPipeline;", "sendPipeline", "Lio/ktor/client/request/HttpSendPipeline;", "getSendPipeline", "()Lio/ktor/client/request/HttpSendPipeline;", "ktor-client-core", "Lkotlinx/coroutines/CoroutineScope;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpClient implements CoroutineScope, Closeable {
    private static final /* synthetic */ AtomicIntegerFieldUpdater closed$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClient.class, "closed");
    private final Attributes attributes;
    private final CompletableJob clientJob;
    private volatile /* synthetic */ int closed;
    private final HttpClientConfig<HttpClientEngineConfig> config;
    private final CoroutineContext coroutineContext;
    private final HttpClientEngine engine;
    private final HttpClientEngineConfig engineConfig;
    private boolean manageEngine;
    private final Events monitor;
    private final HttpReceivePipeline receivePipeline;
    private final HttpRequestPipeline requestPipeline;
    private final HttpResponsePipeline responsePipeline;
    private final HttpSendPipeline sendPipeline;
    private final HttpClientConfig<? extends HttpClientEngineConfig> userConfig;

    public HttpClient(HttpClientEngine engine, HttpClientConfig<? extends HttpClientEngineConfig> userConfig) {
        Intrinsics.checkNotNullParameter(engine, "engine");
        Intrinsics.checkNotNullParameter(userConfig, "userConfig");
        this.engine = engine;
        this.userConfig = userConfig;
        this.closed = 0;
        this.clientJob = JobKt.Job((Job) this.engine.getCoroutineContext().get(Job.INSTANCE));
        this.coroutineContext = this.engine.getCoroutineContext().plus(this.clientJob);
        this.requestPipeline = new HttpRequestPipeline(this.userConfig.getDevelopmentMode());
        this.responsePipeline = new HttpResponsePipeline(this.userConfig.getDevelopmentMode());
        this.sendPipeline = new HttpSendPipeline(this.userConfig.getDevelopmentMode());
        this.receivePipeline = new HttpReceivePipeline(this.userConfig.getDevelopmentMode());
        this.attributes = AttributesJvmKt.Attributes(true);
        this.engineConfig = this.engine.getConfig();
        this.monitor = new Events();
        this.config = new HttpClientConfig<>();
        if (this.manageEngine) {
            this.clientJob.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: io.ktor.client.HttpClient.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable it) {
                    if (it != null) {
                        CoroutineScopeKt.cancel$default(HttpClient.this.getEngine(), null, 1, null);
                    }
                }
            });
        }
        this.engine.install(this);
        this.sendPipeline.intercept(HttpSendPipeline.INSTANCE.getReceive(), new AnonymousClass2(null));
        HttpClientConfig $this$_init__u24lambda_u240 = this.userConfig;
        HttpClientConfig.install$default(this.config, HttpRequestLifecycle.INSTANCE, null, 2, null);
        HttpClientConfig.install$default(this.config, BodyProgress.INSTANCE, null, 2, null);
        if ($this$_init__u24lambda_u240.getUseDefaultTransformers()) {
            this.config.install("DefaultTransformers", new Function1<HttpClient, Unit>() { // from class: io.ktor.client.HttpClient$3$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpClient httpClient) {
                    invoke2(httpClient);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpClient install) {
                    Intrinsics.checkNotNullParameter(install, "$this$install");
                    DefaultTransformKt.defaultTransformers(install);
                }
            });
        }
        HttpClientConfig.install$default(this.config, HttpSend.INSTANCE, null, 2, null);
        HttpClientConfig.install$default(this.config, HttpCallValidator.INSTANCE, null, 2, null);
        if ($this$_init__u24lambda_u240.getFollowRedirects()) {
            HttpClientConfig.install$default(this.config, HttpRedirect.INSTANCE, null, 2, null);
        }
        this.config.plusAssign($this$_init__u24lambda_u240);
        if ($this$_init__u24lambda_u240.getUseDefaultTransformers()) {
            HttpClientConfig.install$default(this.config, HttpPlainText.INSTANCE, null, 2, null);
        }
        DefaultResponseValidationKt.addDefaultResponseValidation(this.config);
        this.config.install(this);
        this.responsePipeline.intercept(HttpResponsePipeline.INSTANCE.getReceive(), new AnonymousClass4(null));
    }

    public /* synthetic */ HttpClient(HttpClientEngine httpClientEngine, HttpClientConfig httpClientConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(httpClientEngine, (i & 2) != 0 ? new HttpClientConfig() : httpClientConfig);
    }

    public final HttpClientEngine getEngine() {
        return this.engine;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpClient(HttpClientEngine engine, HttpClientConfig<? extends HttpClientEngineConfig> userConfig, boolean manageEngine) {
        this(engine, userConfig);
        Intrinsics.checkNotNullParameter(engine, "engine");
        Intrinsics.checkNotNullParameter(userConfig, "userConfig");
        this.manageEngine = manageEngine;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final HttpRequestPipeline getRequestPipeline() {
        return this.requestPipeline;
    }

    public final HttpResponsePipeline getResponsePipeline() {
        return this.responsePipeline;
    }

    public final HttpSendPipeline getSendPipeline() {
        return this.sendPipeline;
    }

    public final HttpReceivePipeline getReceivePipeline() {
        return this.receivePipeline;
    }

    public final Attributes getAttributes() {
        return this.attributes;
    }

    public final HttpClientEngineConfig getEngineConfig() {
        return this.engineConfig;
    }

    public final Events getMonitor() {
        return this.monitor;
    }

    public final HttpClientConfig<HttpClientEngineConfig> getConfig$ktor_client_core() {
        return this.config;
    }

    /* compiled from: HttpClient.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", NotificationCompat.CATEGORY_CALL}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.client.HttpClient$2", f = "HttpClient.kt", i = {0, 0}, l = {144, 146}, m = "invokeSuspend", n = {"$this$intercept", NotificationCompat.CATEGORY_CALL}, s = {"L$0", "L$1"})
    /* renamed from: io.ktor.client.HttpClient$2, reason: invalid class name */
    /* loaded from: classes9.dex */
    static final class AnonymousClass2 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = pipelineContext;
            anonymousClass2.L$1 = obj;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0074 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:13:0x0075  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                switch(r1) {
                    case 0: goto L26;
                    case 1: goto L17;
                    case 2: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L11:
                r0 = r10
                kotlin.ResultKt.throwOnFailure(r11)
                goto L77
            L17:
                r1 = r10
                java.lang.Object r2 = r1.L$1
                java.lang.Object r3 = r1.L$0
                io.ktor.util.pipeline.PipelineContext r3 = (io.ktor.util.pipeline.PipelineContext) r3
                kotlin.ResultKt.throwOnFailure(r11)
                r4 = r3
                r3 = r2
                r2 = r1
                r1 = r11
                goto L5b
            L26:
                kotlin.ResultKt.throwOnFailure(r11)
                r1 = r10
                java.lang.Object r2 = r1.L$0
                r3 = r2
                io.ktor.util.pipeline.PipelineContext r3 = (io.ktor.util.pipeline.PipelineContext) r3
                java.lang.Object r2 = r1.L$1
                boolean r4 = r2 instanceof io.ktor.client.call.HttpClientCall
                if (r4 == 0) goto L7a
                io.ktor.client.HttpClient r4 = io.ktor.client.HttpClient.this
                io.ktor.client.statement.HttpReceivePipeline r4 = r4.getReceivePipeline()
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                r6 = r2
                io.ktor.client.call.HttpClientCall r6 = (io.ktor.client.call.HttpClientCall) r6
                io.ktor.client.statement.HttpResponse r6 = r6.getResponse()
                r7 = r1
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r1.L$0 = r3
                r1.L$1 = r2
                r8 = 1
                r1.label = r8
                java.lang.Object r4 = r4.execute(r5, r6, r7)
                if (r4 != r0) goto L55
                return r0
            L55:
                r9 = r1
                r1 = r11
                r11 = r4
                r4 = r3
                r3 = r2
                r2 = r9
            L5b:
                io.ktor.client.statement.HttpResponse r11 = (io.ktor.client.statement.HttpResponse) r11
                r5 = r3
                io.ktor.client.call.HttpClientCall r5 = (io.ktor.client.call.HttpClientCall) r5
                r5.setResponse$ktor_client_core(r11)
                r11 = r2
                kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
                r5 = 0
                r2.L$0 = r5
                r2.L$1 = r5
                r5 = 2
                r2.label = r5
                java.lang.Object r11 = r4.proceedWith(r3, r11)
                if (r11 != r0) goto L75
                return r0
            L75:
                r11 = r1
                r0 = r2
            L77:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            L7a:
                r0 = 0
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "Error: HttpClientCall expected, but found "
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.StringBuilder r4 = r4.append(r2)
                r5 = 40
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.Class r5 = r2.getClass()
                kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r5 = ")."
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r0 = r4.toString()
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r0 = r0.toString()
                r4.<init>(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.HttpClient.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: HttpClient.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.client.HttpClient$4", f = "HttpClient.kt", i = {0}, l = {177}, m = "invokeSuspend", n = {"$this$intercept"}, s = {"L$0"})
    /* renamed from: io.ktor.client.HttpClient$4, reason: invalid class name */
    /* loaded from: classes9.dex */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(continuation);
            anonymousClass4.L$0 = pipelineContext;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Throwable cause;
            AnonymousClass4 anonymousClass4;
            PipelineContext $this$intercept;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PipelineContext $this$intercept2 = (PipelineContext) this.L$0;
                    try {
                        this.L$0 = $this$intercept2;
                        this.label = 1;
                        if ($this$intercept2.proceed(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        cause = th;
                        anonymousClass4 = this;
                        $this$intercept = $this$intercept2;
                        HttpClient.this.getMonitor().raise(ClientEventsKt.getHttpResponseReceiveFailed(), new HttpResponseReceiveFail(((HttpClientCall) $this$intercept.getContext()).getResponse(), cause));
                        throw cause;
                    }
                case 1:
                    anonymousClass4 = this;
                    $this$intercept = (PipelineContext) anonymousClass4.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    } catch (Throwable th2) {
                        cause = th2;
                        HttpClient.this.getMonitor().raise(ClientEventsKt.getHttpResponseReceiveFailed(), new HttpResponseReceiveFail(((HttpClientCall) $this$intercept.getContext()).getResponse(), cause));
                        throw cause;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object execute$ktor_client_core(io.ktor.client.request.HttpRequestBuilder r7, kotlin.coroutines.Continuation<? super io.ktor.client.call.HttpClientCall> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.client.HttpClient$execute$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.client.HttpClient$execute$1 r0 = (io.ktor.client.HttpClient$execute$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.client.HttpClient$execute$1 r0 = new io.ktor.client.HttpClient$execute$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L31;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            kotlin.ResultKt.throwOnFailure(r8)
            r7 = r8
            goto L4e
        L31:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            io.ktor.events.Events r3 = r2.monitor
            io.ktor.events.EventDefinition r4 = io.ktor.client.utils.ClientEventsKt.getHttpRequestCreated()
            r3.raise(r4, r7)
            io.ktor.client.request.HttpRequestPipeline r3 = r2.requestPipeline
            java.lang.Object r4 = r7.getBody()
            r5 = 1
            r0.label = r5
            java.lang.Object r7 = r3.execute(r7, r4, r0)
            if (r7 != r1) goto L4e
            return r1
        L4e:
            java.lang.String r1 = "null cannot be cast to non-null type io.ktor.client.call.HttpClientCall"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7, r1)
            io.ktor.client.call.HttpClientCall r7 = (io.ktor.client.call.HttpClientCall) r7
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.HttpClient.execute$ktor_client_core(io.ktor.client.request.HttpRequestBuilder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean isSupported(HttpClientEngineCapability<?> capability) {
        Intrinsics.checkNotNullParameter(capability, "capability");
        return this.engine.getSupportedCapabilities().contains(capability);
    }

    public final HttpClient config(Function1<? super HttpClientConfig<?>, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        HttpClientEngine httpClientEngine = this.engine;
        HttpClientConfig $this$config_u24lambda_u241 = new HttpClientConfig();
        $this$config_u24lambda_u241.plusAssign(this.userConfig);
        block.invoke($this$config_u24lambda_u241);
        return new HttpClient(httpClientEngine, $this$config_u24lambda_u241, this.manageEngine);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        boolean success = closed$FU.compareAndSet(this, 0, 1);
        if (success) {
            Attributes installedFeatures = (Attributes) this.attributes.get(HttpClientPluginKt.getPLUGIN_INSTALLED_LIST());
            Iterable $this$forEach$iv = installedFeatures.getAllKeys();
            for (Object element$iv : $this$forEach$iv) {
                AttributeKey key = (AttributeKey) element$iv;
                Intrinsics.checkNotNull(key, "null cannot be cast to non-null type io.ktor.util.AttributeKey<kotlin.Any>");
                Object plugin = installedFeatures.get(key);
                if (plugin instanceof Closeable) {
                    ((Closeable) plugin).close();
                }
            }
            this.clientJob.complete();
            if (this.manageEngine) {
                this.engine.close();
            }
        }
    }

    public String toString() {
        return "HttpClient[" + this.engine + AbstractJsonLexerKt.END_LIST;
    }
}
