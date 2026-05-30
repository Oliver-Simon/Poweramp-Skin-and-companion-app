package io.ktor.client.plugins.api;

import androidx.appcompat.app.AppCompatDelegate;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KtorCallContexts.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.api.TransformResponseBodyHook$install$1", f = "KtorCallContexts.kt", i = {0, 0}, l = {AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 115}, m = "invokeSuspend", n = {"$this$intercept", "typeInfo"}, s = {"L$0", "L$1"})
/* loaded from: classes9.dex */
public final class TransformResponseBodyHook$install$1 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function5<TransformResponseBodyContext, HttpResponse, ByteReadChannel, TypeInfo, Continuation<Object>, Object> $handler;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TransformResponseBodyHook$install$1(Function5<? super TransformResponseBodyContext, ? super HttpResponse, ? super ByteReadChannel, ? super TypeInfo, ? super Continuation<Object>, ? extends Object> function5, Continuation<? super TransformResponseBodyHook$install$1> continuation) {
        super(3, continuation);
        this.$handler = function5;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        TransformResponseBodyHook$install$1 transformResponseBodyHook$install$1 = new TransformResponseBodyHook$install$1(this.$handler, continuation);
        transformResponseBodyHook$install$1.L$0 = pipelineContext;
        return transformResponseBodyHook$install$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006c  */
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
            switch(r1) {
                case 0: goto L26;
                case 1: goto L17;
                case 2: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L11:
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto Lb7
        L17:
            r1 = r10
            java.lang.Object r2 = r1.L$1
            io.ktor.util.reflect.TypeInfo r2 = (io.ktor.util.reflect.TypeInfo) r2
            java.lang.Object r3 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r3 = (io.ktor.util.pipeline.PipelineContext) r3
            kotlin.ResultKt.throwOnFailure(r11)
            r9 = r1
            r1 = r11
            goto L67
        L26:
            kotlin.ResultKt.throwOnFailure(r11)
            r9 = r10
            java.lang.Object r1 = r9.L$0
            r3 = r1
            io.ktor.util.pipeline.PipelineContext r3 = (io.ktor.util.pipeline.PipelineContext) r3
            java.lang.Object r1 = r3.getSubject()
            io.ktor.client.statement.HttpResponseContainer r1 = (io.ktor.client.statement.HttpResponseContainer) r1
            io.ktor.util.reflect.TypeInfo r8 = r1.getExpectedType()
            java.lang.Object r7 = r1.getResponse()
            boolean r1 = r7 instanceof io.ktor.utils.io.ByteReadChannel
            if (r1 != 0) goto L44
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L44:
            kotlin.jvm.functions.Function5<io.ktor.client.plugins.api.TransformResponseBodyContext, io.ktor.client.statement.HttpResponse, io.ktor.utils.io.ByteReadChannel, io.ktor.util.reflect.TypeInfo, kotlin.coroutines.Continuation<java.lang.Object>, java.lang.Object> r4 = r9.$handler
            io.ktor.client.plugins.api.TransformResponseBodyContext r5 = new io.ktor.client.plugins.api.TransformResponseBodyContext
            r5.<init>()
            java.lang.Object r1 = r3.getContext()
            io.ktor.client.call.HttpClientCall r1 = (io.ktor.client.call.HttpClientCall) r1
            io.ktor.client.statement.HttpResponse r6 = r1.getResponse()
            r9.L$0 = r3
            r9.L$1 = r8
            r1 = 1
            r9.label = r1
            java.lang.Object r1 = r4.invoke(r5, r6, r7, r8, r9)
            if (r1 != r0) goto L63
            return r0
        L63:
            r2 = r1
            r1 = r11
            r11 = r2
            r2 = r8
        L67:
            if (r11 != 0) goto L6c
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L6c:
            boolean r4 = r11 instanceof io.ktor.http.content.NullBody
            if (r4 != 0) goto L9e
            kotlin.reflect.KClass r4 = r2.getType()
            boolean r4 = r4.isInstance(r11)
            if (r4 == 0) goto L7b
            goto L9e
        L7b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "transformResponseBody returned "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r11)
            java.lang.String r4 = " but expected value of type "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r2)
            java.lang.String r11 = r3.toString()
            r0.<init>(r11)
            throw r0
        L9e:
            io.ktor.client.statement.HttpResponseContainer r4 = new io.ktor.client.statement.HttpResponseContainer
            r4.<init>(r2, r11)
            r5 = r9
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6 = 0
            r9.L$0 = r6
            r9.L$1 = r6
            r6 = 2
            r9.label = r6
            java.lang.Object r11 = r3.proceedWith(r4, r5)
            if (r11 != r0) goto Lb5
            return r0
        Lb5:
            r11 = r1
            r0 = r9
        Lb7:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.api.TransformResponseBodyHook$install$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
