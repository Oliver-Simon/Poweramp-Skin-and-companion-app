package io.ktor.client.plugins.logging;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponseContainer;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Logging.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.logging.Logging$setupResponseLogging$2", f = "Logging.kt", i = {0, 1, 1, 2}, l = {201, 206, 207}, m = "invokeSuspend", n = {"$this$intercept", "cause", "logger", "cause"}, s = {"L$0", "L$0", "L$1", "L$0"})
/* loaded from: classes9.dex */
public final class Logging$setupResponseLogging$2 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Logging this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Logging$setupResponseLogging$2(Logging logging, Continuation<? super Logging$setupResponseLogging$2> continuation) {
        super(3, continuation);
        this.this$0 = logging;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        Logging$setupResponseLogging$2 logging$setupResponseLogging$2 = new Logging$setupResponseLogging$2(this.this$0, continuation);
        logging$setupResponseLogging$2.L$0 = pipelineContext;
        return logging$setupResponseLogging$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0006. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AttributeKey attributeKey;
        HttpClientCallLogger httpClientCallLogger;
        Logging$setupResponseLogging$2 logging$setupResponseLogging$2;
        PipelineContext pipelineContext;
        AttributeKey<?> attributeKey2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Logging$setupResponseLogging$2 logging$setupResponseLogging$22 = this.label;
        try {
        } catch (Throwable th) {
            th = th;
            StringBuilder sb = new StringBuilder();
            Attributes attributes = ((HttpClientCall) pipelineContext.getContext()).getAttributes();
            attributeKey = LoggingKt.ClientCallLogger;
            HttpClientCallLogger httpClientCallLogger2 = (HttpClientCallLogger) attributes.get(attributeKey);
            logging$setupResponseLogging$22.this$0.logResponseException(sb, ((HttpClientCall) pipelineContext.getContext()).getRequest(), th);
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "log.toString()");
            logging$setupResponseLogging$22.L$0 = th;
            logging$setupResponseLogging$22.L$1 = httpClientCallLogger2;
            logging$setupResponseLogging$22.label = 2;
            if (httpClientCallLogger2.logResponseException(sb2, logging$setupResponseLogging$22) == coroutine_suspended) {
                return coroutine_suspended;
            }
            httpClientCallLogger = httpClientCallLogger2;
            logging$setupResponseLogging$2 = logging$setupResponseLogging$22;
        }
        switch (logging$setupResponseLogging$22) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Logging$setupResponseLogging$2 logging$setupResponseLogging$23 = this;
                pipelineContext = (PipelineContext) logging$setupResponseLogging$23.L$0;
                if (logging$setupResponseLogging$23.this$0.getLevel() != LogLevel.NONE) {
                    Attributes attributes2 = ((HttpClientCall) pipelineContext.getContext()).getAttributes();
                    attributeKey2 = LoggingKt.DisableLogging;
                    if (!attributes2.contains(attributeKey2)) {
                        logging$setupResponseLogging$23.L$0 = pipelineContext;
                        logging$setupResponseLogging$23.label = 1;
                        Object proceed = pipelineContext.proceed(logging$setupResponseLogging$23);
                        logging$setupResponseLogging$22 = logging$setupResponseLogging$23;
                        if (proceed == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        coroutine_suspended = Unit.INSTANCE;
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                Logging$setupResponseLogging$2 logging$setupResponseLogging$24 = this;
                pipelineContext = (PipelineContext) logging$setupResponseLogging$24.L$0;
                ResultKt.throwOnFailure(obj);
                logging$setupResponseLogging$22 = logging$setupResponseLogging$24;
                coroutine_suspended = Unit.INSTANCE;
                return coroutine_suspended;
            case 2:
                Logging$setupResponseLogging$2 logging$setupResponseLogging$25 = this;
                httpClientCallLogger = (HttpClientCallLogger) logging$setupResponseLogging$25.L$1;
                th = (Throwable) logging$setupResponseLogging$25.L$0;
                ResultKt.throwOnFailure(obj);
                logging$setupResponseLogging$2 = logging$setupResponseLogging$25;
                logging$setupResponseLogging$2.L$0 = th;
                logging$setupResponseLogging$2.L$1 = null;
                logging$setupResponseLogging$2.label = 3;
                if (httpClientCallLogger.closeResponseLog(logging$setupResponseLogging$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                throw th;
            case 3:
                Throwable th2 = (Throwable) this.L$0;
                ResultKt.throwOnFailure(obj);
                throw th2;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
