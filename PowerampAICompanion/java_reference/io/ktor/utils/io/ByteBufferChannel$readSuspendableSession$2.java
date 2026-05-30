package io.ktor.utils.io;

import io.ktor.utils.io.internal.ReadSessionImpl;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteBufferChannel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/LookAheadSuspendSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteBufferChannel$readSuspendableSession$2", f = "ByteBufferChannel.kt", i = {}, l = {1624}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
public final class ByteBufferChannel$readSuspendableSession$2 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<SuspendableReadSession, Continuation<? super Unit>, Object> $consumer;
    int label;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ByteBufferChannel$readSuspendableSession$2(Function2<? super SuspendableReadSession, ? super Continuation<? super Unit>, ? extends Object> function2, ByteBufferChannel byteBufferChannel, Continuation<? super ByteBufferChannel$readSuspendableSession$2> continuation) {
        super(2, continuation);
        this.$consumer = function2;
        this.this$0 = byteBufferChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ByteBufferChannel$readSuspendableSession$2(this.$consumer, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Unit> continuation) {
        return ((ByteBufferChannel$readSuspendableSession$2) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Throwable th;
        ByteBufferChannel$readSuspendableSession$2 byteBufferChannel$readSuspendableSession$2;
        ReadSessionImpl readSessionImpl;
        ReadSessionImpl readSessionImpl2;
        ReadSessionImpl readSessionImpl3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    Function2<SuspendableReadSession, Continuation<? super Unit>, Object> function2 = this.$consumer;
                    readSessionImpl = this.this$0.readSession;
                    this.label = 1;
                    if (function2.invoke(readSessionImpl, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    byteBufferChannel$readSuspendableSession$2 = this;
                    readSessionImpl3 = byteBufferChannel$readSuspendableSession$2.this$0.readSession;
                    readSessionImpl3.completed();
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    byteBufferChannel$readSuspendableSession$2 = this;
                    readSessionImpl2 = byteBufferChannel$readSuspendableSession$2.this$0.readSession;
                    readSessionImpl2.completed();
                    throw th;
                }
            case 1:
                byteBufferChannel$readSuspendableSession$2 = this;
                try {
                    ResultKt.throwOnFailure($result);
                    readSessionImpl3 = byteBufferChannel$readSuspendableSession$2.this$0.readSession;
                    readSessionImpl3.completed();
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    readSessionImpl2 = byteBufferChannel$readSuspendableSession$2.this$0.readSession;
                    readSessionImpl2.completed();
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
