package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpRequestLifecycle.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.HttpRequestLifecycle$Plugin$install$1", f = "HttpRequestLifecycle.kt", i = {0}, l = {38}, m = "invokeSuspend", n = {"executionContext"}, s = {"L$0"})
/* loaded from: classes9.dex */
public final class HttpRequestLifecycle$Plugin$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpRequestLifecycle$Plugin$install$1(HttpClient httpClient, Continuation<? super HttpRequestLifecycle$Plugin$install$1> continuation) {
        super(3, continuation);
        this.$scope = httpClient;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        HttpRequestLifecycle$Plugin$install$1 httpRequestLifecycle$Plugin$install$1 = new HttpRequestLifecycle$Plugin$install$1(this.$scope, continuation);
        httpRequestLifecycle$Plugin$install$1.L$0 = pipelineContext;
        return httpRequestLifecycle$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CompletableJob executionContext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PipelineContext $this$intercept = (PipelineContext) this.L$0;
                CompletableJob executionContext2 = SupervisorKt.SupervisorJob(((HttpRequestBuilder) $this$intercept.getContext()).getExecutionContext());
                CoroutineContext.Element element = this.$scope.getCoroutineContext().get(Job.INSTANCE);
                Intrinsics.checkNotNull(element);
                HttpRequestLifecycleKt.attachToClientEngineJob(executionContext2, (Job) element);
                try {
                    ((HttpRequestBuilder) $this$intercept.getContext()).setExecutionContext$ktor_client_core(executionContext2);
                    this.L$0 = executionContext2;
                    this.label = 1;
                    if ($this$intercept.proceed(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    executionContext = executionContext2;
                    executionContext.complete();
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    cause = th;
                    executionContext = executionContext2;
                    try {
                        executionContext.completeExceptionally(cause);
                        throw cause;
                    } catch (Throwable cause) {
                        executionContext.complete();
                        throw cause;
                    }
                }
            case 1:
                executionContext = (CompletableJob) this.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    executionContext.complete();
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    cause = th2;
                    executionContext.completeExceptionally(cause);
                    throw cause;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
