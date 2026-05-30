package com.google.ai.client.generativeai;

import com.google.ai.client.generativeai.type.Content;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GenerativeModel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.GenerativeModel", f = "GenerativeModel.kt", i = {}, l = {171}, m = "countTokens", n = {}, s = {})
/* loaded from: classes.dex */
public final class GenerativeModel$countTokens$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GenerativeModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenerativeModel$countTokens$1(GenerativeModel generativeModel, Continuation<? super GenerativeModel$countTokens$1> continuation) {
        super(continuation);
        this.this$0 = generativeModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.countTokens((Content[]) null, this);
    }
}
