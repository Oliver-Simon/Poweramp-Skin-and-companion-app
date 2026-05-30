package com.google.ai.client.generativeai.common;

import androidx.core.location.LocationRequestCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: APIController.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.common.APIController", f = "APIController.kt", i = {}, l = {LocationRequestCompat.QUALITY_LOW_POWER, 259, 106, 261}, m = "generateContent", n = {}, s = {})
/* loaded from: classes.dex */
public final class APIController$generateContent$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ APIController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public APIController$generateContent$1(APIController aPIController, Continuation<? super APIController$generateContent$1> continuation) {
        super(continuation);
        this.this$0 = aPIController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.generateContent(null, this);
    }
}
