package io.ktor.client.plugins.contentnegotiation;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: ContentNegotiation.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "<name for destructuring parameter 0>"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.contentnegotiation.ContentNegotiation$Plugin$install$2", f = "ContentNegotiation.kt", i = {0, 0}, l = {262, 265}, m = "invokeSuspend", n = {"$this$intercept", "info"}, s = {"L$0", "L$1"})
/* loaded from: classes9.dex */
final class ContentNegotiation$Plugin$install$2 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    final /* synthetic */ ContentNegotiation $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentNegotiation$Plugin$install$2(ContentNegotiation contentNegotiation, Continuation<? super ContentNegotiation$Plugin$install$2> continuation) {
        super(3, continuation);
        this.$plugin = contentNegotiation;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        ContentNegotiation$Plugin$install$2 contentNegotiation$Plugin$install$2 = new ContentNegotiation$Plugin$install$2(this.$plugin, continuation);
        contentNegotiation$Plugin$install$2.L$0 = pipelineContext;
        contentNegotiation$Plugin$install$2.L$1 = httpResponseContainer;
        return contentNegotiation$Plugin$install$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x009a  */
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
            r2 = 0
            switch(r1) {
                case 0: goto L28;
                case 1: goto L18;
                case 2: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L12:
            r0 = r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto Lb2
        L18:
            r1 = r13
            java.lang.Object r3 = r1.L$1
            io.ktor.util.reflect.TypeInfo r3 = (io.ktor.util.reflect.TypeInfo) r3
            java.lang.Object r4 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r4 = (io.ktor.util.pipeline.PipelineContext) r4
            kotlin.ResultKt.throwOnFailure(r14)
            r7 = r3
            r3 = r1
            r1 = r14
            goto L95
        L28:
            kotlin.ResultKt.throwOnFailure(r14)
            r1 = r13
            java.lang.Object r3 = r1.L$0
            r4 = r3
            io.ktor.util.pipeline.PipelineContext r4 = (io.ktor.util.pipeline.PipelineContext) r4
            java.lang.Object r3 = r1.L$1
            io.ktor.client.statement.HttpResponseContainer r3 = (io.ktor.client.statement.HttpResponseContainer) r3
            io.ktor.util.reflect.TypeInfo r7 = r3.getExpectedType()
            java.lang.Object r8 = r3.getResponse()
            java.lang.Object r3 = r4.getContext()
            io.ktor.client.call.HttpClientCall r3 = (io.ktor.client.call.HttpClientCall) r3
            io.ktor.client.statement.HttpResponse r3 = r3.getResponse()
            io.ktor.http.HttpMessage r3 = (io.ktor.http.HttpMessage) r3
            io.ktor.http.ContentType r9 = io.ktor.http.HttpMessagePropertiesKt.contentType(r3)
            if (r9 != 0) goto L5c
            r0 = 0
            org.slf4j.Logger r2 = io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt.access$getLOGGER$p()
            java.lang.String r3 = "Response doesn't have \"Content-Type\" header, skipping ContentNegotiation plugin"
            r2.trace(r3)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        L5c:
            java.lang.Object r3 = r4.getContext()
            io.ktor.client.call.HttpClientCall r3 = (io.ktor.client.call.HttpClientCall) r3
            io.ktor.client.request.HttpRequest r3 = r3.getRequest()
            io.ktor.http.Headers r3 = r3.getHeaders()
            r5 = 1
            java.nio.charset.Charset r10 = io.ktor.serialization.ContentConverterKt.suitableCharset$default(r3, r2, r5, r2)
            r3 = r5
            io.ktor.client.plugins.contentnegotiation.ContentNegotiation r5 = r1.$plugin
            java.lang.Object r6 = r4.getContext()
            io.ktor.client.call.HttpClientCall r6 = (io.ktor.client.call.HttpClientCall) r6
            io.ktor.client.request.HttpRequest r6 = r6.getRequest()
            io.ktor.http.Url r6 = r6.getUrl()
            r11 = r1
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r1.L$0 = r4
            r1.L$1 = r7
            r1.label = r3
            java.lang.Object r3 = r5.convertResponse$ktor_client_content_negotiation(r6, r7, r8, r9, r10, r11)
            if (r3 != r0) goto L91
            return r0
        L91:
            r12 = r1
            r1 = r14
            r14 = r3
            r3 = r12
        L95:
            if (r14 != 0) goto L9a
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L9a:
            io.ktor.client.statement.HttpResponseContainer r5 = new io.ktor.client.statement.HttpResponseContainer
            r5.<init>(r7, r14)
            r14 = r3
            kotlin.coroutines.Continuation r14 = (kotlin.coroutines.Continuation) r14
            r3.L$0 = r2
            r3.L$1 = r2
            r2 = 2
            r3.label = r2
            java.lang.Object r14 = r4.proceedWith(r5, r14)
            if (r14 != r0) goto Lb0
            return r0
        Lb0:
            r14 = r1
            r0 = r3
        Lb2:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.contentnegotiation.ContentNegotiation$Plugin$install$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
