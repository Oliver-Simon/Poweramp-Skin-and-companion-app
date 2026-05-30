package io.ktor.client.plugins;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.utils.ExceptionUtilsJvmKt;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpCallValidator.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.HttpCallValidator$Companion$install$1", f = "HttpCallValidator.kt", i = {0, 1}, l = {130, 133}, m = "invokeSuspend", n = {"$this$intercept", "unwrappedCause"}, s = {"L$0", "L$0"})
/* loaded from: classes9.dex */
public final class HttpCallValidator$Companion$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpCallValidator $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCallValidator$Companion$install$1(HttpCallValidator httpCallValidator, Continuation<? super HttpCallValidator$Companion$install$1> continuation) {
        super(3, continuation);
        this.$plugin = httpCallValidator;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        HttpCallValidator$Companion$install$1 httpCallValidator$Companion$install$1 = new HttpCallValidator$Companion$install$1(this.$plugin, continuation);
        httpCallValidator$Companion$install$1.L$0 = pipelineContext;
        httpCallValidator$Companion$install$1.L$1 = obj;
        return httpCallValidator$Companion$install$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        HttpCallValidatorKt$HttpRequest$1 HttpRequest;
        Object processException;
        PipelineContext pipelineContext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        HttpCallValidator$Companion$install$1 httpCallValidator$Companion$install$1 = this.label;
        try {
            switch (httpCallValidator$Companion$install$1) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    HttpCallValidator$Companion$install$1 httpCallValidator$Companion$install$12 = this;
                    pipelineContext = (PipelineContext) httpCallValidator$Companion$install$12.L$0;
                    Object obj2 = httpCallValidator$Companion$install$12.L$1;
                    Attributes attributes = ((HttpRequestBuilder) pipelineContext.getContext()).getAttributes();
                    AttributeKey<Boolean> expectSuccessAttributeKey = HttpCallValidatorKt.getExpectSuccessAttributeKey();
                    final HttpCallValidator httpCallValidator = httpCallValidator$Companion$install$12.$plugin;
                    attributes.computeIfAbsent(expectSuccessAttributeKey, new Function0<Boolean>() { // from class: io.ktor.client.plugins.HttpCallValidator$Companion$install$1.1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            boolean z;
                            z = HttpCallValidator.this.expectSuccess;
                            return Boolean.valueOf(z);
                        }
                    });
                    httpCallValidator$Companion$install$12.L$0 = pipelineContext;
                    httpCallValidator$Companion$install$12.label = 1;
                    Object proceedWith = pipelineContext.proceedWith(obj2, httpCallValidator$Companion$install$12);
                    httpCallValidator$Companion$install$1 = httpCallValidator$Companion$install$12;
                    if (proceedWith == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    HttpCallValidator$Companion$install$1 httpCallValidator$Companion$install$13 = this;
                    pipelineContext = (PipelineContext) httpCallValidator$Companion$install$13.L$0;
                    ResultKt.throwOnFailure(obj);
                    httpCallValidator$Companion$install$1 = httpCallValidator$Companion$install$13;
                    break;
                case 2:
                    Throwable th = (Throwable) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    throw th;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutine_suspended = Unit.INSTANCE;
            return coroutine_suspended;
        } catch (Throwable th2) {
            Throwable unwrapCancellationException = ExceptionUtilsJvmKt.unwrapCancellationException(th2);
            HttpCallValidator httpCallValidator2 = httpCallValidator$Companion$install$1.$plugin;
            HttpRequest = HttpCallValidatorKt.HttpRequest((HttpRequestBuilder) pipelineContext.getContext());
            httpCallValidator$Companion$install$1.L$0 = unwrapCancellationException;
            httpCallValidator$Companion$install$1.label = 2;
            processException = httpCallValidator2.processException(unwrapCancellationException, HttpRequest, httpCallValidator$Companion$install$1);
            if (processException == coroutine_suspended) {
                return coroutine_suspended;
            }
            throw unwrapCancellationException;
        }
    }
}
