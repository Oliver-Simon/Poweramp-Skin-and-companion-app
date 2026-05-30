package io.ktor.client.engine.okhttp;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: OkHttpEngine.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.engine.okhttp.OkHttpEngine", f = "OkHttpEngine.kt", i = {0, 0, 0, 0}, l = {113}, m = "executeHttpRequest", n = {"this", "callContext", "requestData", "requestTime"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes9.dex */
public final class OkHttpEngine$executeHttpRequest$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OkHttpEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkHttpEngine$executeHttpRequest$1(OkHttpEngine okHttpEngine, Continuation<? super OkHttpEngine$executeHttpRequest$1> continuation) {
        super(continuation);
        this.this$0 = okHttpEngine;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object executeHttpRequest;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        executeHttpRequest = this.this$0.executeHttpRequest(null, null, null, null, this);
        return executeHttpRequest;
    }
}
