package io.ktor.client.engine.okhttp;

import io.ktor.client.engine.HttpClientEngineBase;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.plugins.websocket.WebSocketCapability;
import io.ktor.client.request.HttpResponseData;
import io.ktor.client.utils.CoroutineDispatcherUtilsKt;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.CacheKt;
import io.ktor.util.CoroutinesUtilsKt;
import io.ktor.util.date.GMTDate;
import java.net.Proxy;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* compiled from: OkHttpEngine.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 42\u00020\u0001:\u00014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\fH\u0002J\b\u0010$\u001a\u00020%H\u0016J\u0012\u0010&\u001a\u00020\b2\b\u0010'\u001a\u0004\u0018\u00010\u0007H\u0002J\u0019\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010+J1\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020/2\u0006\u0010#\u001a\u00020\f2\u0006\u00100\u001a\u00020*H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101J)\u00102\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020/2\u0006\u0010#\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0002\u00103R\u001c\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\u00108VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0015\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00180\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u00065"}, d2 = {"Lio/ktor/client/engine/okhttp/OkHttpEngine;", "Lio/ktor/client/engine/HttpClientEngineBase;", "config", "Lio/ktor/client/engine/okhttp/OkHttpConfig;", "(Lio/ktor/client/engine/okhttp/OkHttpConfig;)V", "clientCache", "", "Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "Lokhttp3/OkHttpClient;", "getConfig", "()Lio/ktor/client/engine/okhttp/OkHttpConfig;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher$delegate", "Lkotlin/Lazy;", "requestsJob", "supportedCapabilities", "", "Lio/ktor/client/engine/HttpClientEngineCapability;", "getSupportedCapabilities", "()Ljava/util/Set;", "buildResponseData", "Lio/ktor/client/request/HttpResponseData;", "response", "Lokhttp3/Response;", "requestTime", "Lio/ktor/util/date/GMTDate;", "body", "", "callContext", "close", "", "createOkHttpClient", "timeoutExtension", "execute", "data", "Lio/ktor/client/request/HttpRequestData;", "(Lio/ktor/client/request/HttpRequestData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeHttpRequest", "engine", "engineRequest", "Lokhttp3/Request;", "requestData", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Lkotlin/coroutines/CoroutineContext;Lio/ktor/client/request/HttpRequestData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeWebSocketRequest", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "ktor-client-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OkHttpEngine extends HttpClientEngineBase {
    private static final Companion Companion = new Companion(null);
    private static final Lazy<OkHttpClient> okHttpClientPrototype$delegate = LazyKt.lazy(new Function0<OkHttpClient>() { // from class: io.ktor.client.engine.okhttp.OkHttpEngine$Companion$okHttpClientPrototype$2
        @Override // kotlin.jvm.functions.Function0
        public final OkHttpClient invoke() {
            return new OkHttpClient.Builder().build();
        }
    });
    private final Map<HttpTimeout.HttpTimeoutCapabilityConfiguration, OkHttpClient> clientCache;
    private final OkHttpConfig config;
    private final CoroutineContext coroutineContext;

    /* renamed from: dispatcher$delegate, reason: from kotlin metadata */
    private final Lazy dispatcher;
    private final CoroutineContext requestsJob;
    private final Set<HttpClientEngineCapability<?>> supportedCapabilities;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkHttpEngine(OkHttpConfig config) {
        super("ktor-okhttp");
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        this.dispatcher = LazyKt.lazy(new Function0<CoroutineDispatcher>() { // from class: io.ktor.client.engine.okhttp.OkHttpEngine$dispatcher$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CoroutineDispatcher invoke() {
                return CoroutineDispatcherUtilsKt.clientDispatcher(Dispatchers.INSTANCE, OkHttpEngine.this.getConfig().getThreadsCount(), "ktor-okhttp-dispatcher");
            }
        });
        this.supportedCapabilities = SetsKt.setOf((Object[]) new HttpClientEngineCapability[]{HttpTimeout.INSTANCE, WebSocketCapability.INSTANCE});
        this.clientCache = CacheKt.createLRUCache(new OkHttpEngine$clientCache$1(this), new Function1<OkHttpClient, Unit>() { // from class: io.ktor.client.engine.okhttp.OkHttpEngine$clientCache$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OkHttpClient okHttpClient) {
                invoke2(okHttpClient);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OkHttpClient it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }
        }, getConfig().getClientCacheSize());
        CoroutineContext.Element element = super.getCoroutineContext().get(Job.INSTANCE);
        Intrinsics.checkNotNull(element);
        Job parent = (Job) element;
        this.requestsJob = CoroutinesUtilsKt.SilentSupervisor(parent);
        this.coroutineContext = super.getCoroutineContext().plus(this.requestsJob);
        BuildersKt.launch(GlobalScope.INSTANCE, super.getCoroutineContext(), CoroutineStart.ATOMIC, new AnonymousClass1(null));
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    public OkHttpConfig getConfig() {
        return this.config;
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    public CoroutineDispatcher getDispatcher() {
        return (CoroutineDispatcher) this.dispatcher.getValue();
    }

    @Override // io.ktor.client.engine.HttpClientEngineBase, io.ktor.client.engine.HttpClientEngine
    public Set<HttpClientEngineCapability<?>> getSupportedCapabilities() {
        return this.supportedCapabilities;
    }

    @Override // io.ktor.client.engine.HttpClientEngineBase, kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    /* compiled from: OkHttpEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.client.engine.okhttp.OkHttpEngine$1", f = "OkHttpEngine.kt", i = {}, l = {59}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.ktor.client.engine.okhttp.OkHttpEngine$1, reason: invalid class name */
    /* loaded from: classes9.dex */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0056 A[LOOP:0: B:10:0x0050->B:12:0x0056, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00a3 A[LOOP:1: B:19:0x009d->B:21:0x00a3, LOOP_END] */
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
                java.lang.String r2 = "null cannot be cast to non-null type java.io.Closeable"
                switch(r1) {
                    case 0: goto L1b;
                    case 1: goto L13;
                    default: goto Lb;
                }
            Lb:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L13:
                r0 = r10
                kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L18
                goto L41
            L18:
                r1 = move-exception
                goto L8e
            L1b:
                kotlin.ResultKt.throwOnFailure(r11)
                r1 = r10
                io.ktor.client.engine.okhttp.OkHttpEngine r3 = io.ktor.client.engine.okhttp.OkHttpEngine.this     // Catch: java.lang.Throwable -> L8a
                kotlin.coroutines.CoroutineContext r3 = io.ktor.client.engine.okhttp.OkHttpEngine.access$getRequestsJob$p(r3)     // Catch: java.lang.Throwable -> L8a
                kotlinx.coroutines.Job$Key r4 = kotlinx.coroutines.Job.INSTANCE     // Catch: java.lang.Throwable -> L8a
                kotlin.coroutines.CoroutineContext$Key r4 = (kotlin.coroutines.CoroutineContext.Key) r4     // Catch: java.lang.Throwable -> L8a
                kotlin.coroutines.CoroutineContext$Element r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L8a
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch: java.lang.Throwable -> L8a
                kotlinx.coroutines.Job r3 = (kotlinx.coroutines.Job) r3     // Catch: java.lang.Throwable -> L8a
                r4 = r1
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.lang.Throwable -> L8a
                r5 = 1
                r1.label = r5     // Catch: java.lang.Throwable -> L8a
                java.lang.Object r3 = r3.join(r4)     // Catch: java.lang.Throwable -> L8a
                if (r3 != r0) goto L40
                return r0
            L40:
                r0 = r1
            L41:
                io.ktor.client.engine.okhttp.OkHttpEngine r1 = io.ktor.client.engine.okhttp.OkHttpEngine.this
                java.util.Map r1 = io.ktor.client.engine.okhttp.OkHttpEngine.access$getClientCache$p(r1)
                r3 = 0
                java.util.Set r4 = r1.entrySet()
                java.util.Iterator r1 = r4.iterator()
            L50:
                boolean r4 = r1.hasNext()
                if (r4 == 0) goto L77
                java.lang.Object r4 = r1.next()
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                r5 = 0
                java.lang.Object r6 = r4.getValue()
                okhttp3.OkHttpClient r6 = (okhttp3.OkHttpClient) r6
                okhttp3.ConnectionPool r7 = r6.connectionPool()
                r7.evictAll()
                okhttp3.Dispatcher r7 = r6.dispatcher()
                java.util.concurrent.ExecutorService r7 = r7.executorService()
                r7.shutdown()
                goto L50
            L77:
                io.ktor.client.engine.okhttp.OkHttpEngine r1 = io.ktor.client.engine.okhttp.OkHttpEngine.this
                kotlinx.coroutines.CoroutineDispatcher r1 = r1.getDispatcher()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
                java.io.Closeable r1 = (java.io.Closeable) r1
                r1.close()
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            L8a:
                r0 = move-exception
                r9 = r1
                r1 = r0
                r0 = r9
            L8e:
                io.ktor.client.engine.okhttp.OkHttpEngine r3 = io.ktor.client.engine.okhttp.OkHttpEngine.this
                java.util.Map r3 = io.ktor.client.engine.okhttp.OkHttpEngine.access$getClientCache$p(r3)
                r4 = 0
                java.util.Set r5 = r3.entrySet()
                java.util.Iterator r3 = r5.iterator()
            L9d:
                boolean r5 = r3.hasNext()
                if (r5 == 0) goto Lc4
                java.lang.Object r5 = r3.next()
                java.util.Map$Entry r5 = (java.util.Map.Entry) r5
                r6 = 0
                java.lang.Object r7 = r5.getValue()
                okhttp3.OkHttpClient r7 = (okhttp3.OkHttpClient) r7
                okhttp3.ConnectionPool r8 = r7.connectionPool()
                r8.evictAll()
                okhttp3.Dispatcher r8 = r7.dispatcher()
                java.util.concurrent.ExecutorService r8 = r8.executorService()
                r8.shutdown()
                goto L9d
            Lc4:
                io.ktor.client.engine.okhttp.OkHttpEngine r3 = io.ktor.client.engine.okhttp.OkHttpEngine.this
                kotlinx.coroutines.CoroutineDispatcher r3 = r3.getDispatcher()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r2)
                java.io.Closeable r3 = (java.io.Closeable) r3
                r3.close()
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.okhttp.OkHttpEngine.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // io.ktor.client.engine.HttpClientEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object execute(io.ktor.client.request.HttpRequestData r9, kotlin.coroutines.Continuation<? super io.ktor.client.request.HttpResponseData> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.client.engine.okhttp.OkHttpEngine$execute$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.client.engine.okhttp.OkHttpEngine$execute$1 r0 = (io.ktor.client.engine.okhttp.OkHttpEngine$execute$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.client.engine.okhttp.OkHttpEngine$execute$1 r0 = new io.ktor.client.engine.okhttp.OkHttpEngine$execute$1
            r0.<init>(r8, r10)
        L19:
            r6 = r0
            java.lang.Object r10 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L45;
                case 1: goto L37;
                case 2: goto L32;
                case 3: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2d:
            kotlin.ResultKt.throwOnFailure(r10)
            r9 = r10
            goto L96
        L32:
            kotlin.ResultKt.throwOnFailure(r10)
            r9 = r10
            goto L87
        L37:
            java.lang.Object r9 = r6.L$1
            io.ktor.client.request.HttpRequestData r9 = (io.ktor.client.request.HttpRequestData) r9
            java.lang.Object r1 = r6.L$0
            io.ktor.client.engine.okhttp.OkHttpEngine r1 = (io.ktor.client.engine.okhttp.OkHttpEngine) r1
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r10
            r5 = r9
            goto L58
        L45:
            kotlin.ResultKt.throwOnFailure(r10)
            r1 = r8
            r6.L$0 = r1
            r6.L$1 = r9
            r2 = 1
            r6.label = r2
            java.lang.Object r2 = io.ktor.client.engine.UtilsKt.callContext(r6)
            if (r2 != r0) goto L57
            return r0
        L57:
            r5 = r9
        L58:
            r4 = r2
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            okhttp3.Request r3 = io.ktor.client.engine.okhttp.OkHttpEngineKt.access$convertToOkHttpRequest(r5, r4)
            java.util.Map<io.ktor.client.plugins.HttpTimeout$HttpTimeoutCapabilityConfiguration, okhttp3.OkHttpClient> r9 = r1.clientCache
            io.ktor.client.plugins.HttpTimeout$Plugin r2 = io.ktor.client.plugins.HttpTimeout.INSTANCE
            io.ktor.client.engine.HttpClientEngineCapability r2 = (io.ktor.client.engine.HttpClientEngineCapability) r2
            java.lang.Object r2 = r5.getCapabilityOrNull(r2)
            java.lang.Object r9 = r9.get(r2)
            r2 = r9
            okhttp3.OkHttpClient r2 = (okhttp3.OkHttpClient) r2
            if (r2 == 0) goto L97
            boolean r9 = io.ktor.client.request.HttpRequestKt.isUpgradeRequest(r5)
            r7 = 0
            if (r9 == 0) goto L88
            r6.L$0 = r7
            r6.L$1 = r7
            r9 = 2
            r6.label = r9
            java.lang.Object r9 = r1.executeWebSocketRequest(r2, r3, r4, r6)
            if (r9 != r0) goto L87
            return r0
        L87:
            return r9
        L88:
            r6.L$0 = r7
            r6.L$1 = r7
            r9 = 3
            r6.label = r9
            java.lang.Object r9 = r1.executeHttpRequest(r2, r3, r4, r5, r6)
            if (r9 != r0) goto L96
            return r0
        L96:
            return r9
        L97:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "OkHttpClient can't be constructed because HttpTimeout plugin is not installed"
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.okhttp.OkHttpEngine.execute(io.ktor.client.request.HttpRequestData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.client.engine.HttpClientEngineBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        CoroutineContext.Element element = this.requestsJob.get(Job.INSTANCE);
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type kotlinx.coroutines.CompletableJob");
        ((CompletableJob) element).complete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object executeWebSocketRequest(okhttp3.OkHttpClient r8, okhttp3.Request r9, kotlin.coroutines.CoroutineContext r10, kotlin.coroutines.Continuation<? super io.ktor.client.request.HttpResponseData> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof io.ktor.client.engine.okhttp.OkHttpEngine$executeWebSocketRequest$1
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.client.engine.okhttp.OkHttpEngine$executeWebSocketRequest$1 r0 = (io.ktor.client.engine.okhttp.OkHttpEngine$executeWebSocketRequest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            io.ktor.client.engine.okhttp.OkHttpEngine$executeWebSocketRequest$1 r0 = new io.ktor.client.engine.okhttp.OkHttpEngine$executeWebSocketRequest$1
            r0.<init>(r7, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L42;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            java.lang.Object r8 = r0.L$3
            io.ktor.client.engine.okhttp.OkHttpWebsocketSession r8 = (io.ktor.client.engine.okhttp.OkHttpWebsocketSession) r8
            java.lang.Object r9 = r0.L$2
            io.ktor.util.date.GMTDate r9 = (io.ktor.util.date.GMTDate) r9
            java.lang.Object r10 = r0.L$1
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            java.lang.Object r1 = r0.L$0
            io.ktor.client.engine.okhttp.OkHttpEngine r1 = (io.ktor.client.engine.okhttp.OkHttpEngine) r1
            kotlin.ResultKt.throwOnFailure(r11)
            r3 = r9
            r9 = r11
            goto L7d
        L42:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r7
            r3 = 0
            r4 = 1
            io.ktor.util.date.GMTDate r3 = io.ktor.util.date.DateJvmKt.GMTDate$default(r3, r4, r3)
            io.ktor.client.engine.okhttp.OkHttpWebsocketSession r5 = new io.ktor.client.engine.okhttp.OkHttpWebsocketSession
            io.ktor.client.engine.okhttp.OkHttpConfig r6 = r2.getConfig()
            okhttp3.WebSocket$Factory r6 = r6.getWebSocketFactory()
            if (r6 != 0) goto L5c
            r6 = r8
            okhttp3.WebSocket$Factory r6 = (okhttp3.WebSocket.Factory) r6
        L5c:
            r5.<init>(r8, r6, r9, r10)
            r8 = r5
            r9 = 0
            r8.start()
            kotlinx.coroutines.CompletableDeferred r9 = r8.getOriginResponse$ktor_client_okhttp()
            r0.L$0 = r2
            r0.L$1 = r10
            r0.L$2 = r3
            r0.L$3 = r8
            r0.label = r4
            java.lang.Object r9 = r9.await(r0)
            if (r9 != r1) goto L7c
            return r1
        L7c:
            r1 = r2
        L7d:
            okhttp3.Response r9 = (okhttp3.Response) r9
            io.ktor.client.request.HttpResponseData r2 = r1.buildResponseData(r9, r3, r8, r10)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.okhttp.OkHttpEngine.executeWebSocketRequest(okhttp3.OkHttpClient, okhttp3.Request, kotlin.coroutines.CoroutineContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object executeHttpRequest(okhttp3.OkHttpClient r6, okhttp3.Request r7, kotlin.coroutines.CoroutineContext r8, io.ktor.client.request.HttpRequestData r9, kotlin.coroutines.Continuation<? super io.ktor.client.request.HttpResponseData> r10) {
        /*
            r5 = this;
            boolean r0 = r10 instanceof io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$1 r0 = (io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$1 r0 = new io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$1
            r0.<init>(r5, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L42;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            java.lang.Object r6 = r0.L$3
            io.ktor.util.date.GMTDate r6 = (io.ktor.util.date.GMTDate) r6
            java.lang.Object r7 = r0.L$2
            io.ktor.client.request.HttpRequestData r7 = (io.ktor.client.request.HttpRequestData) r7
            java.lang.Object r8 = r0.L$1
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            java.lang.Object r9 = r0.L$0
            io.ktor.client.engine.okhttp.OkHttpEngine r9 = (io.ktor.client.engine.okhttp.OkHttpEngine) r9
            kotlin.ResultKt.throwOnFailure(r10)
            r3 = r6
            r6 = r10
            goto L5f
        L42:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r5
            r3 = 0
            r4 = 1
            io.ktor.util.date.GMTDate r3 = io.ktor.util.date.DateJvmKt.GMTDate$default(r3, r4, r3)
            r0.L$0 = r2
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r3
            r0.label = r4
            java.lang.Object r6 = io.ktor.client.engine.okhttp.OkUtilsKt.execute(r6, r7, r9, r0)
            if (r6 != r1) goto L5d
            return r1
        L5d:
            r7 = r9
            r9 = r2
        L5f:
            okhttp3.Response r6 = (okhttp3.Response) r6
            okhttp3.ResponseBody r1 = r6.body()
            kotlinx.coroutines.Job$Key r2 = kotlinx.coroutines.Job.INSTANCE
            kotlin.coroutines.CoroutineContext$Key r2 = (kotlin.coroutines.CoroutineContext.Key) r2
            kotlin.coroutines.CoroutineContext$Element r2 = r8.get(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
            io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$2 r4 = new io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$2
            r4.<init>()
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            r2.invokeOnCompletion(r4)
            if (r1 == 0) goto L8a
            okio.BufferedSource r1 = r1.getBodySource()
            if (r1 == 0) goto L8a
            io.ktor.utils.io.ByteReadChannel r7 = io.ktor.client.engine.okhttp.OkHttpEngineKt.access$toChannel(r1, r8, r7)
            if (r7 != 0) goto L90
        L8a:
            io.ktor.utils.io.ByteReadChannel$Companion r7 = io.ktor.utils.io.ByteReadChannel.INSTANCE
            io.ktor.utils.io.ByteReadChannel r7 = r7.getEmpty()
        L90:
            io.ktor.client.request.HttpResponseData r1 = r9.buildResponseData(r6, r3, r7, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.okhttp.OkHttpEngine.executeHttpRequest(okhttp3.OkHttpClient, okhttp3.Request, kotlin.coroutines.CoroutineContext, io.ktor.client.request.HttpRequestData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final HttpResponseData buildResponseData(Response response, GMTDate requestTime, Object body, CoroutineContext callContext) {
        HttpStatusCode status = new HttpStatusCode(response.code(), response.message());
        HttpProtocolVersion version = OkUtilsKt.fromOkHttp(response.protocol());
        Headers headers = OkUtilsKt.fromOkHttp(response.headers());
        return new HttpResponseData(status, requestTime, headers, version, body, callContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: OkHttpEngine.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/client/engine/okhttp/OkHttpEngine$Companion;", "", "()V", "okHttpClientPrototype", "Lokhttp3/OkHttpClient;", "getOkHttpClientPrototype", "()Lokhttp3/OkHttpClient;", "okHttpClientPrototype$delegate", "Lkotlin/Lazy;", "ktor-client-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OkHttpClient getOkHttpClientPrototype() {
            return (OkHttpClient) OkHttpEngine.okHttpClientPrototype$delegate.getValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient createOkHttpClient(HttpTimeout.HttpTimeoutCapabilityConfiguration timeoutExtension) {
        OkHttpClient preconfigured = getConfig().getPreconfigured();
        if (preconfigured == null) {
            preconfigured = Companion.getOkHttpClientPrototype();
        }
        OkHttpClient.Builder builder = preconfigured.newBuilder();
        builder.dispatcher(new Dispatcher());
        getConfig().getConfig$ktor_client_okhttp().invoke(builder);
        Proxy it = getConfig().getProxy();
        if (it != null) {
            builder.proxy(it);
        }
        if (timeoutExtension != null) {
            OkHttpEngineKt.access$setupTimeoutAttributes(builder, timeoutExtension);
        }
        return builder.build();
    }
}
