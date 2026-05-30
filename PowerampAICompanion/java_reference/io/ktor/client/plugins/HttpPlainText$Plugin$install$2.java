package io.ktor.client.plugins;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpPlainText.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "<name for destructuring parameter 0>"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.HttpPlainText$Plugin$install$2", f = "HttpPlainText.kt", i = {0, 0}, l = {136, 138}, m = "invokeSuspend", n = {"$this$intercept", "info"}, s = {"L$0", "L$1"})
/* loaded from: classes9.dex */
public final class HttpPlainText$Plugin$install$2 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpPlainText $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpPlainText$Plugin$install$2(HttpPlainText httpPlainText, Continuation<? super HttpPlainText$Plugin$install$2> continuation) {
        super(3, continuation);
        this.$plugin = httpPlainText;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        HttpPlainText$Plugin$install$2 httpPlainText$Plugin$install$2 = new HttpPlainText$Plugin$install$2(this.$plugin, continuation);
        httpPlainText$Plugin$install$2.L$0 = pipelineContext;
        httpPlainText$Plugin$install$2.L$1 = httpResponseContainer;
        return httpPlainText$Plugin$install$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0094 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0095  */
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
                case 0: goto L27;
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
            goto L97
        L17:
            r1 = r13
            java.lang.Object r2 = r1.L$1
            io.ktor.util.reflect.TypeInfo r2 = (io.ktor.util.reflect.TypeInfo) r2
            java.lang.Object r3 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r3 = (io.ktor.util.pipeline.PipelineContext) r3
            kotlin.ResultKt.throwOnFailure(r14)
            r4 = r2
            r2 = r1
            r1 = r14
            goto L6d
        L27:
            kotlin.ResultKt.throwOnFailure(r14)
            r1 = r13
            java.lang.Object r2 = r1.L$0
            r3 = r2
            io.ktor.util.pipeline.PipelineContext r3 = (io.ktor.util.pipeline.PipelineContext) r3
            java.lang.Object r2 = r1.L$1
            io.ktor.client.statement.HttpResponseContainer r2 = (io.ktor.client.statement.HttpResponseContainer) r2
            io.ktor.util.reflect.TypeInfo r4 = r2.getExpectedType()
            java.lang.Object r2 = r2.getResponse()
            kotlin.reflect.KClass r5 = r4.getType()
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto L9a
            boolean r5 = r2 instanceof io.ktor.utils.io.ByteReadChannel
            if (r5 != 0) goto L51
            goto L9a
        L51:
            r6 = r2
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            r9 = r1
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r1.L$0 = r3
            r1.L$1 = r4
            r5 = 1
            r1.label = r5
            r7 = 0
            r10 = 1
            r11 = 0
            java.lang.Object r2 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r6, r7, r9, r10, r11)
            if (r2 != r0) goto L69
            return r0
        L69:
            r12 = r1
            r1 = r14
            r14 = r2
            r2 = r12
        L6d:
            io.ktor.utils.io.core.ByteReadPacket r14 = (io.ktor.utils.io.core.ByteReadPacket) r14
            io.ktor.client.plugins.HttpPlainText r5 = r2.$plugin
            java.lang.Object r6 = r3.getContext()
            io.ktor.client.call.HttpClientCall r6 = (io.ktor.client.call.HttpClientCall) r6
            r7 = r14
            io.ktor.utils.io.core.Input r7 = (io.ktor.utils.io.core.Input) r7
            java.lang.String r14 = r5.read$ktor_client_core(r6, r7)
            io.ktor.client.statement.HttpResponseContainer r5 = new io.ktor.client.statement.HttpResponseContainer
            r5.<init>(r4, r14)
            r6 = r2
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r7 = 0
            r2.L$0 = r7
            r2.L$1 = r7
            r7 = 2
            r2.label = r7
            java.lang.Object r14 = r3.proceedWith(r5, r6)
            if (r14 != r0) goto L95
            return r0
        L95:
            r14 = r1
            r0 = r2
        L97:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L9a:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpPlainText$Plugin$install$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
