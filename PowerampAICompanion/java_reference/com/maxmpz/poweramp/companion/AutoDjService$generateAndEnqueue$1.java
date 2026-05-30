package com.maxmpz.poweramp.companion;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AutoDjService.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.AutoDjService", f = "AutoDjService.kt", i = {}, l = {309}, m = "generateAndEnqueue", n = {}, s = {})
/* loaded from: classes7.dex */
public final class AutoDjService$generateAndEnqueue$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AutoDjService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoDjService$generateAndEnqueue$1(AutoDjService autoDjService, Continuation<? super AutoDjService$generateAndEnqueue$1> continuation) {
        super(continuation);
        this.this$0 = autoDjService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object generateAndEnqueue;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        generateAndEnqueue = this.this$0.generateAndEnqueue(null, null, this);
        return generateAndEnqueue;
    }
}
