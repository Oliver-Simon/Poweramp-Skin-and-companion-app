package io.ktor.client.plugins.contentnegotiation;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: ContentNegotiation.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.contentnegotiation.ContentNegotiation$Plugin$install$1", f = "ContentNegotiation.kt", i = {0}, l = {251, 252}, m = "invokeSuspend", n = {"$this$intercept"}, s = {"L$0"})
/* loaded from: classes9.dex */
final class ContentNegotiation$Plugin$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ ContentNegotiation $plugin;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentNegotiation$Plugin$install$1(ContentNegotiation contentNegotiation, Continuation<? super ContentNegotiation$Plugin$install$1> continuation) {
        super(3, continuation);
        this.$plugin = contentNegotiation;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        ContentNegotiation$Plugin$install$1 contentNegotiation$Plugin$install$1 = new ContentNegotiation$Plugin$install$1(this.$plugin, continuation);
        contentNegotiation$Plugin$install$1.L$0 = pipelineContext;
        return contentNegotiation$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            switch(r1) {
                case 0: goto L22;
                case 1: goto L16;
                case 2: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L11:
            r0 = r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L61
        L16:
            r1 = r9
            java.lang.Object r2 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r3 = r2
            r2 = r1
            r1 = r10
            goto L4a
        L22:
            kotlin.ResultKt.throwOnFailure(r10)
            r1 = r9
            java.lang.Object r2 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            io.ktor.client.plugins.contentnegotiation.ContentNegotiation r3 = r1.$plugin
            java.lang.Object r4 = r2.getContext()
            io.ktor.client.request.HttpRequestBuilder r4 = (io.ktor.client.request.HttpRequestBuilder) r4
            java.lang.Object r5 = r2.getSubject()
            r6 = r1
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r1.L$0 = r2
            r7 = 1
            r1.label = r7
            java.lang.Object r3 = r3.convertRequest$ktor_client_content_negotiation(r4, r5, r6)
            if (r3 != r0) goto L45
            return r0
        L45:
            r8 = r1
            r1 = r10
            r10 = r3
            r3 = r2
            r2 = r8
        L4a:
            if (r10 != 0) goto L4f
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L4f:
            r4 = r2
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5 = 0
            r2.L$0 = r5
            r5 = 2
            r2.label = r5
            java.lang.Object r10 = r3.proceedWith(r10, r4)
            if (r10 != r0) goto L5f
            return r0
        L5f:
            r10 = r1
            r0 = r2
        L61:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.contentnegotiation.ContentNegotiation$Plugin$install$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
