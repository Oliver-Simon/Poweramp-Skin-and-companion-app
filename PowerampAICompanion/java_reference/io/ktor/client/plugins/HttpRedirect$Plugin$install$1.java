package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpRedirect;
import io.ktor.client.request.HttpRequestBuilder;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpRedirect.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/plugins/Sender;", "context", "Lio/ktor/client/request/HttpRequestBuilder;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.HttpRedirect$Plugin$install$1", f = "HttpRedirect.kt", i = {0, 0}, l = {64, 69}, m = "invokeSuspend", n = {"$this$intercept", "context"}, s = {"L$0", "L$1"})
/* loaded from: classes9.dex */
public final class HttpRedirect$Plugin$install$1 extends SuspendLambda implements Function3<Sender, HttpRequestBuilder, Continuation<? super HttpClientCall>, Object> {
    final /* synthetic */ HttpRedirect $plugin;
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpRedirect$Plugin$install$1(HttpRedirect httpRedirect, HttpClient httpClient, Continuation<? super HttpRedirect$Plugin$install$1> continuation) {
        super(3, continuation);
        this.$plugin = httpRedirect;
        this.$scope = httpClient;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Sender sender, HttpRequestBuilder httpRequestBuilder, Continuation<? super HttpClientCall> continuation) {
        HttpRedirect$Plugin$install$1 httpRedirect$Plugin$install$1 = new HttpRedirect$Plugin$install$1(this.$plugin, this.$scope, continuation);
        httpRedirect$Plugin$install$1.L$0 = sender;
        httpRedirect$Plugin$install$1.L$1 = httpRequestBuilder;
        return httpRedirect$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        HttpRequestBuilder context;
        HttpRedirect$Plugin$install$1 httpRedirect$Plugin$install$1;
        Object $result2;
        Sender $this$intercept;
        boolean z;
        boolean z2;
        Object $result3;
        Set set;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Sender $this$intercept2 = (Sender) this.L$0;
                HttpRequestBuilder context2 = (HttpRequestBuilder) this.L$1;
                this.L$0 = $this$intercept2;
                this.L$1 = context2;
                this.label = 1;
                Object execute = $this$intercept2.execute(context2, this);
                if (execute != coroutine_suspended) {
                    context = context2;
                    httpRedirect$Plugin$install$1 = this;
                    $result2 = $result;
                    $result = execute;
                    $this$intercept = $this$intercept2;
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                HttpRequestBuilder context3 = (HttpRequestBuilder) this.L$1;
                Sender $this$intercept3 = (Sender) this.L$0;
                ResultKt.throwOnFailure($result);
                context = context3;
                httpRedirect$Plugin$install$1 = this;
                $result2 = $result;
                $this$intercept = $this$intercept3;
                break;
            case 2:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        HttpClientCall origin = (HttpClientCall) $result;
        z = httpRedirect$Plugin$install$1.$plugin.checkHttpMethod;
        if (z) {
            set = HttpRedirectKt.ALLOWED_FOR_REDIRECT;
            if (!set.contains(origin.getRequest().getMethod())) {
                return origin;
            }
        }
        HttpRedirect.Companion companion = HttpRedirect.INSTANCE;
        z2 = httpRedirect$Plugin$install$1.$plugin.allowHttpsDowngrade;
        httpRedirect$Plugin$install$1.L$0 = null;
        httpRedirect$Plugin$install$1.L$1 = null;
        httpRedirect$Plugin$install$1.label = 2;
        $result3 = companion.handleCall($this$intercept, context, origin, z2, httpRedirect$Plugin$install$1.$scope, httpRedirect$Plugin$install$1);
        return $result3 == coroutine_suspended ? coroutine_suspended : $result3;
    }
}
