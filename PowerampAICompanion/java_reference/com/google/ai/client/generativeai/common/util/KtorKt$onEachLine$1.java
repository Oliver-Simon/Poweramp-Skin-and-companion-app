package com.google.ai.client.generativeai.common.util;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ktor.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.common.util.KtorKt", f = "ktor.kt", i = {0, 0, 1, 1, 2, 2}, l = {50, 51, 52}, m = "onEachLine", n = {"$this$onEachLine", "block", "$this$onEachLine", "block", "$this$onEachLine", "block"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes.dex */
public final class KtorKt$onEachLine$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KtorKt$onEachLine$1(Continuation<? super KtorKt$onEachLine$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return KtorKt.onEachLine(null, null, this);
    }
}
