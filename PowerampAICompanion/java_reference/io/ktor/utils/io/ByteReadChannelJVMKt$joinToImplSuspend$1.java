package io.ktor.utils.io;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteReadChannelJVM.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelJVMKt", f = "ByteReadChannelJVM.kt", i = {0, 0}, l = {261}, m = "joinToImplSuspend", n = {"dst", "close"}, s = {"L$0", "Z$0"})
/* loaded from: classes9.dex */
public final class ByteReadChannelJVMKt$joinToImplSuspend$1 extends ContinuationImpl {
    Object L$0;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteReadChannelJVMKt$joinToImplSuspend$1(Continuation<? super ByteReadChannelJVMKt$joinToImplSuspend$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object joinToImplSuspend;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        joinToImplSuspend = ByteReadChannelJVMKt.joinToImplSuspend(null, null, false, this);
        return joinToImplSuspend;
    }
}
