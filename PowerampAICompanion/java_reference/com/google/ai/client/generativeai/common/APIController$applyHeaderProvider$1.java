package com.google.ai.client.generativeai.common;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: APIController.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.common.APIController", f = "APIController.kt", i = {}, l = {149}, m = "applyHeaderProvider", n = {}, s = {})
/* loaded from: classes.dex */
public final class APIController$applyHeaderProvider$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ APIController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public APIController$applyHeaderProvider$1(APIController aPIController, Continuation<? super APIController$applyHeaderProvider$1> continuation) {
        super(continuation);
        this.this$0 = aPIController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object applyHeaderProvider;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        applyHeaderProvider = this.this$0.applyHeaderProvider(null, this);
        return applyHeaderProvider;
    }
}
