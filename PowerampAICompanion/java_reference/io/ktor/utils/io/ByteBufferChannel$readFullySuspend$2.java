package io.ktor.utils.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteBufferChannel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", i = {0, 0, 0, 0}, l = {TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE}, m = "readFullySuspend", n = {"this", "dst", "n", "copied"}, s = {"L$0", "L$1", "I$0", "I$1"})
/* loaded from: classes9.dex */
public final class ByteBufferChannel$readFullySuspend$2 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$readFullySuspend$2(ByteBufferChannel byteBufferChannel, Continuation<? super ByteBufferChannel$readFullySuspend$2> continuation) {
        super(continuation);
        this.this$0 = byteBufferChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object readFullySuspend;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        readFullySuspend = this.this$0.readFullySuspend((ChunkBuffer) null, 0, (Continuation<? super Unit>) this);
        return readFullySuspend;
    }
}
