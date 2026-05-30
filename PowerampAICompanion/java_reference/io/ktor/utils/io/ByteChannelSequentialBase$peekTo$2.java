package io.ktor.utils.io;

import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteChannelSequential.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/SuspendableReadSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteChannelSequentialBase$peekTo$2", f = "ByteChannelSequential.kt", i = {0}, l = {823}, m = "invokeSuspend", n = {"$this$readSuspendableSession"}, s = {"L$0"})
/* loaded from: classes9.dex */
public final class ByteChannelSequentialBase$peekTo$2 extends SuspendLambda implements Function2<SuspendableReadSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.LongRef $bytesCopied;
    final /* synthetic */ ByteBuffer $destination;
    final /* synthetic */ long $destinationOffset;
    final /* synthetic */ long $max;
    final /* synthetic */ long $min;
    final /* synthetic */ long $offset;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$peekTo$2(long j, long j2, Ref.LongRef longRef, long j3, ByteBuffer byteBuffer, long j4, Continuation<? super ByteChannelSequentialBase$peekTo$2> continuation) {
        super(2, continuation);
        this.$min = j;
        this.$offset = j2;
        this.$bytesCopied = longRef;
        this.$max = j3;
        this.$destination = byteBuffer;
        this.$destinationOffset = j4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ByteChannelSequentialBase$peekTo$2 byteChannelSequentialBase$peekTo$2 = new ByteChannelSequentialBase$peekTo$2(this.$min, this.$offset, this.$bytesCopied, this.$max, this.$destination, this.$destinationOffset, continuation);
        byteChannelSequentialBase$peekTo$2.L$0 = obj;
        return byteChannelSequentialBase$peekTo$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SuspendableReadSession suspendableReadSession, Continuation<? super Unit> continuation) {
        return ((ByteChannelSequentialBase$peekTo$2) create(suspendableReadSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        ByteChannelSequentialBase$peekTo$2 byteChannelSequentialBase$peekTo$2;
        SuspendableReadSession $this$readSuspendableSession;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                SuspendableReadSession $this$readSuspendableSession2 = (SuspendableReadSession) this.L$0;
                int desiredSize = (int) RangesKt.coerceAtMost(this.$min + this.$offset, 4088L);
                this.L$0 = $this$readSuspendableSession2;
                this.label = 1;
                if ($this$readSuspendableSession2.await(desiredSize, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                byteChannelSequentialBase$peekTo$2 = this;
                $this$readSuspendableSession = $this$readSuspendableSession2;
                break;
            case 1:
                byteChannelSequentialBase$peekTo$2 = this;
                $this$readSuspendableSession = (SuspendableReadSession) byteChannelSequentialBase$peekTo$2.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ChunkBuffer buffer = $this$readSuspendableSession.request(1);
        if (buffer == null) {
            buffer = ChunkBuffer.INSTANCE.getEmpty();
        }
        ChunkBuffer this_$iv = buffer;
        if (this_$iv.getWritePosition() - this_$iv.getReadPosition() > byteChannelSequentialBase$peekTo$2.$offset) {
            Ref.LongRef longRef = byteChannelSequentialBase$peekTo$2.$bytesCopied;
            ChunkBuffer this_$iv2 = buffer;
            long writePosition = (this_$iv2.getWritePosition() - this_$iv2.getReadPosition()) - byteChannelSequentialBase$peekTo$2.$offset;
            long j = byteChannelSequentialBase$peekTo$2.$max;
            ByteBuffer arg0$iv = byteChannelSequentialBase$peekTo$2.$destination;
            longRef.element = Math.min(writePosition, Math.min(j, arg0$iv.limit() - byteChannelSequentialBase$peekTo$2.$destinationOffset));
            Memory.m237copyToJT6ljtQ(buffer.getMemory(), byteChannelSequentialBase$peekTo$2.$destination, byteChannelSequentialBase$peekTo$2.$offset, byteChannelSequentialBase$peekTo$2.$bytesCopied.element, byteChannelSequentialBase$peekTo$2.$destinationOffset);
        }
        return Unit.INSTANCE;
    }
}
