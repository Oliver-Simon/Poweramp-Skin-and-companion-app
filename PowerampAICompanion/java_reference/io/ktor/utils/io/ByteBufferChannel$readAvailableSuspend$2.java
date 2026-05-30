package io.ktor.utils.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteBufferChannel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", i = {0, 0}, l = {731, 735}, m = "readAvailableSuspend", n = {"this", "dst"}, s = {"L$0", "L$1"})
/* loaded from: classes9.dex */
public final class ByteBufferChannel$readAvailableSuspend$2 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$readAvailableSuspend$2(ByteBufferChannel byteBufferChannel, Continuation<? super ByteBufferChannel$readAvailableSuspend$2> continuation) {
        super(continuation);
        this.this$0 = byteBufferChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object readAvailableSuspend;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        readAvailableSuspend = this.this$0.readAvailableSuspend((ByteBuffer) null, (Continuation<? super Integer>) this);
        return readAvailableSuspend;
    }
}
