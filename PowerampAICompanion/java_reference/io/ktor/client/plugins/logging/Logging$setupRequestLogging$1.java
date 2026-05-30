package io.ktor.client.plugins.logging;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Logging.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.logging.Logging$setupRequestLogging$1", f = "Logging.kt", i = {0, 1}, l = {84, 90}, m = "invokeSuspend", n = {"$this$intercept", "$this$intercept"}, s = {"L$0", "L$0"})
/* loaded from: classes9.dex */
public final class Logging$setupRequestLogging$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Logging this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Logging$setupRequestLogging$1(Logging logging, Continuation<? super Logging$setupRequestLogging$1> continuation) {
        super(3, continuation);
        this.this$0 = logging;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        Logging$setupRequestLogging$1 logging$setupRequestLogging$1 = new Logging$setupRequestLogging$1(this.this$0, continuation);
        logging$setupRequestLogging$1.L$0 = pipelineContext;
        return logging$setupRequestLogging$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0006. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0098 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r2v12, types: [io.ktor.util.pipeline.PipelineContext] */
    /* JADX WARN: Type inference failed for: r2v7, types: [io.ktor.util.pipeline.PipelineContext, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [io.ktor.util.pipeline.PipelineContext, java.lang.Object] */
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
            switch(r1) {
                case 0: goto L2a;
                case 1: goto L1e;
                case 2: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L11:
            r0 = r8
            java.lang.Object r1 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L1b
            goto L9c
        L1b:
            r2 = move-exception
            goto La5
        L1e:
            r1 = r8
            java.lang.Object r2 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L7b
            r3 = r2
            r2 = r1
            r1 = r9
            goto L73
        L2a:
            kotlin.ResultKt.throwOnFailure(r9)
            r1 = r8
            java.lang.Object r2 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            io.ktor.client.plugins.logging.Logging r3 = r1.this$0
            java.lang.Object r4 = r2.getContext()
            io.ktor.client.request.HttpRequestBuilder r4 = (io.ktor.client.request.HttpRequestBuilder) r4
            boolean r3 = io.ktor.client.plugins.logging.Logging.access$shouldBeLogged(r3, r4)
            if (r3 != 0) goto L56
            java.lang.Object r0 = r2.getContext()
            io.ktor.client.request.HttpRequestBuilder r0 = (io.ktor.client.request.HttpRequestBuilder) r0
            io.ktor.util.Attributes r0 = r0.getAttributes()
            io.ktor.util.AttributeKey r3 = io.ktor.client.plugins.logging.LoggingKt.access$getDisableLogging$p()
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            r0.put(r3, r4)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L56:
            io.ktor.client.plugins.logging.Logging r3 = r1.this$0     // Catch: java.lang.Throwable -> L7b
            java.lang.Object r4 = r2.getContext()     // Catch: java.lang.Throwable -> L7b
            io.ktor.client.request.HttpRequestBuilder r4 = (io.ktor.client.request.HttpRequestBuilder) r4     // Catch: java.lang.Throwable -> L7b
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Throwable -> L7b
            r1.L$0 = r2     // Catch: java.lang.Throwable -> L7b
            r6 = 1
            r1.label = r6     // Catch: java.lang.Throwable -> L7b
            java.lang.Object r3 = io.ktor.client.plugins.logging.Logging.access$logRequest(r3, r4, r5)     // Catch: java.lang.Throwable -> L7b
            if (r3 != r0) goto L6e
            return r0
        L6e:
            r7 = r1
            r1 = r9
            r9 = r3
            r3 = r2
            r2 = r7
        L73:
            io.ktor.http.content.OutgoingContent r9 = (io.ktor.http.content.OutgoingContent) r9     // Catch: java.lang.Throwable -> L76
            goto L82
        L76:
            r9 = move-exception
            r9 = r1
            r1 = r2
            r2 = r3
            goto L7c
        L7b:
            r3 = move-exception
        L7c:
            r3 = 0
            r7 = r1
            r1 = r9
            r9 = r3
            r3 = r2
            r2 = r7
        L82:
            if (r9 != 0) goto L8a
            java.lang.Object r9 = r3.getSubject()     // Catch: java.lang.Throwable -> La0
        L8a:
            r4 = r2
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.lang.Throwable -> La0
            r2.L$0 = r3     // Catch: java.lang.Throwable -> La0
            r5 = 2
            r2.label = r5     // Catch: java.lang.Throwable -> La0
            java.lang.Object r9 = r3.proceedWith(r9, r4)     // Catch: java.lang.Throwable -> La0
            if (r9 != r0) goto L99
            return r0
        L99:
            r9 = r1
            r0 = r2
            r1 = r3
        L9c:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        La0:
            r9 = move-exception
            r0 = r2
            r2 = r9
            r9 = r1
            r1 = r3
        La5:
            io.ktor.client.plugins.logging.Logging r3 = r0.this$0     // Catch: java.lang.Throwable -> Lb2
            java.lang.Object r4 = r1.getContext()     // Catch: java.lang.Throwable -> Lb2
            io.ktor.client.request.HttpRequestBuilder r4 = (io.ktor.client.request.HttpRequestBuilder) r4     // Catch: java.lang.Throwable -> Lb2
            io.ktor.client.plugins.logging.Logging.access$logRequestException(r3, r4, r2)     // Catch: java.lang.Throwable -> Lb2
            throw r2     // Catch: java.lang.Throwable -> Lb2
        Lb2:
            r1 = move-exception
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.logging.Logging$setupRequestLogging$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
