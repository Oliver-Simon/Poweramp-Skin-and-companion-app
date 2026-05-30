package io.ktor.utils.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteChannelSequential.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", i = {0, 0, 0, 0}, l = {781, 782}, m = "writeAvailableSuspend", n = {"this", "src", TypedValues.CycleType.S_WAVE_OFFSET, "length"}, s = {"L$0", "L$1", "I$0", "I$1"})
/* loaded from: classes9.dex */
public final class ByteChannelSequentialBase$writeAvailableSuspend$2 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$writeAvailableSuspend$2(ByteChannelSequentialBase byteChannelSequentialBase, Continuation<? super ByteChannelSequentialBase$writeAvailableSuspend$2> continuation) {
        super(continuation);
        this.this$0 = byteChannelSequentialBase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object writeAvailableSuspend;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        writeAvailableSuspend = this.this$0.writeAvailableSuspend(null, 0, 0, this);
        return writeAvailableSuspend;
    }
}
