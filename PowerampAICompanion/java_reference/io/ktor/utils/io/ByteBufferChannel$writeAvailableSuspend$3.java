package io.ktor.utils.io;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteBufferChannel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", i = {0, 0}, l = {1074, 1076, 1078}, m = "writeAvailableSuspend", n = {"this", "src"}, s = {"L$0", "L$1"})
/* loaded from: classes9.dex */
public final class ByteBufferChannel$writeAvailableSuspend$3 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$writeAvailableSuspend$3(ByteBufferChannel byteBufferChannel, Continuation<? super ByteBufferChannel$writeAvailableSuspend$3> continuation) {
        super(continuation);
        this.this$0 = byteBufferChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object writeAvailableSuspend;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        writeAvailableSuspend = this.this$0.writeAvailableSuspend((ChunkBuffer) null, (Continuation<? super Integer>) this);
        return writeAvailableSuspend;
    }
}
