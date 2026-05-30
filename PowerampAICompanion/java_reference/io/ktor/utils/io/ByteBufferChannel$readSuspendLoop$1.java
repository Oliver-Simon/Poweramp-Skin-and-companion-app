package io.ktor.utils.io;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteBufferChannel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", i = {0, 0}, l = {2183}, m = "readSuspendLoop", n = {"this", ContentDisposition.Parameters.Size}, s = {"L$0", "I$0"})
/* loaded from: classes9.dex */
public final class ByteBufferChannel$readSuspendLoop$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$readSuspendLoop$1(ByteBufferChannel byteBufferChannel, Continuation<? super ByteBufferChannel$readSuspendLoop$1> continuation) {
        super(continuation);
        this.this$0 = byteBufferChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object readSuspendLoop;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        readSuspendLoop = this.this$0.readSuspendLoop(0, this);
        return readSuspendLoop;
    }
}
