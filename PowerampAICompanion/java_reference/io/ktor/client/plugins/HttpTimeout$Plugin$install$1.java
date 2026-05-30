package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.request.ClientUpgradeContent;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.URLProtocolKt;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpTimeout.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/plugins/Sender;", "request", "Lio/ktor/client/request/HttpRequestBuilder;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.HttpTimeout$Plugin$install$1", f = "HttpTimeout.kt", i = {}, l = {146, 174}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
public final class HttpTimeout$Plugin$install$1 extends SuspendLambda implements Function3<Sender, HttpRequestBuilder, Continuation<? super HttpClientCall>, Object> {
    final /* synthetic */ HttpTimeout $plugin;
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpTimeout$Plugin$install$1(HttpTimeout httpTimeout, HttpClient httpClient, Continuation<? super HttpTimeout$Plugin$install$1> continuation) {
        super(3, continuation);
        this.$plugin = httpTimeout;
        this.$scope = httpClient;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Sender sender, HttpRequestBuilder httpRequestBuilder, Continuation<? super HttpClientCall> continuation) {
        HttpTimeout$Plugin$install$1 httpTimeout$Plugin$install$1 = new HttpTimeout$Plugin$install$1(this.$plugin, this.$scope, continuation);
        httpTimeout$Plugin$install$1.L$0 = sender;
        httpTimeout$Plugin$install$1.L$1 = httpRequestBuilder;
        return httpTimeout$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        final Job killer;
        boolean hasNotNullTimeouts;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Sender $this$intercept = (Sender) this.L$0;
                HttpRequestBuilder request = (HttpRequestBuilder) this.L$1;
                boolean isWebSocket = URLProtocolKt.isWebsocket(request.getUrl().getProtocol());
                if (isWebSocket || (request.getBody() instanceof ClientUpgradeContent)) {
                    this.L$0 = null;
                    this.label = 1;
                    Object execute = $this$intercept.execute(request, this);
                    if (execute == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return execute;
                }
                HttpTimeout.HttpTimeoutCapabilityConfiguration configuration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) request.getCapabilityOrNull(HttpTimeout.INSTANCE);
                if (configuration == null) {
                    hasNotNullTimeouts = this.$plugin.hasNotNullTimeouts();
                    if (hasNotNullTimeouts) {
                        configuration = new HttpTimeout.HttpTimeoutCapabilityConfiguration(null, null, null, 7, null);
                        request.setCapability(HttpTimeout.INSTANCE, configuration);
                    }
                }
                if (configuration != null) {
                    HttpTimeout httpTimeout = this.$plugin;
                    HttpClient httpClient = this.$scope;
                    Long l = configuration.get_connectTimeoutMillis();
                    if (l == null) {
                        l = httpTimeout.connectTimeoutMillis;
                    }
                    configuration.setConnectTimeoutMillis(l);
                    Long l2 = configuration.get_socketTimeoutMillis();
                    if (l2 == null) {
                        l2 = httpTimeout.socketTimeoutMillis;
                    }
                    configuration.setSocketTimeoutMillis(l2);
                    Long l3 = configuration.get_requestTimeoutMillis();
                    if (l3 == null) {
                        l3 = httpTimeout.requestTimeoutMillis;
                    }
                    configuration.setRequestTimeoutMillis(l3);
                    Long requestTimeout = configuration.get_requestTimeoutMillis();
                    if (requestTimeout == null) {
                        requestTimeout = httpTimeout.requestTimeoutMillis;
                    }
                    if (requestTimeout != null && requestTimeout.longValue() != Long.MAX_VALUE) {
                        Job executionContext = request.getExecutionContext();
                        killer = BuildersKt__Builders_commonKt.launch$default(httpClient, null, null, new HttpTimeout$Plugin$install$1$1$killer$1(requestTimeout, request, executionContext, null), 3, null);
                        request.getExecutionContext().invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: io.ktor.client.plugins.HttpTimeout$Plugin$install$1$1$1
                            /* JADX INFO: Access modifiers changed from: package-private */
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
                                Job.DefaultImpls.cancel$default(Job.this, (CancellationException) null, 1, (Object) null);
                            }
                        });
                    }
                }
                this.L$0 = null;
                this.label = 2;
                Object execute2 = $this$intercept.execute(request, this);
                return execute2 == coroutine_suspended ? coroutine_suspended : execute2;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            case 2:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
