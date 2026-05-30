package io.ktor.utils.io.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WriteSessionImpl.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.internal.WriteSessionImpl", f = "WriteSessionImpl.kt", i = {0}, l = {86}, m = "tryAwaitJoinSwitch", n = {"this"}, s = {"L$0"})
/* loaded from: classes9.dex */
public final class WriteSessionImpl$tryAwaitJoinSwitch$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WriteSessionImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WriteSessionImpl$tryAwaitJoinSwitch$1(WriteSessionImpl writeSessionImpl, Continuation<? super WriteSessionImpl$tryAwaitJoinSwitch$1> continuation) {
        super(continuation);
        this.this$0 = writeSessionImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object tryAwaitJoinSwitch;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        tryAwaitJoinSwitch = this.this$0.tryAwaitJoinSwitch(0, this);
        return tryAwaitJoinSwitch;
    }
}
