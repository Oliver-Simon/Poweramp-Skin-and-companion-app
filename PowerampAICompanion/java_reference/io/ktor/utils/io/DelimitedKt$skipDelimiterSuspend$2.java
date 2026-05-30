package io.ktor.utils.io;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Delimited.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/LookAheadSuspendSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.DelimitedKt$skipDelimiterSuspend$2", f = "Delimited.kt", i = {0}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT}, m = "invokeSuspend", n = {"$this$lookAheadSuspend"}, s = {"L$0"})
/* loaded from: classes9.dex */
public final class DelimitedKt$skipDelimiterSuspend$2 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteBuffer $delimiter;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelimitedKt$skipDelimiterSuspend$2(ByteBuffer byteBuffer, Continuation<? super DelimitedKt$skipDelimiterSuspend$2> continuation) {
        super(2, continuation);
        this.$delimiter = byteBuffer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DelimitedKt$skipDelimiterSuspend$2 delimitedKt$skipDelimiterSuspend$2 = new DelimitedKt$skipDelimiterSuspend$2(this.$delimiter, continuation);
        delimitedKt$skipDelimiterSuspend$2.L$0 = obj;
        return delimitedKt$skipDelimiterSuspend$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Unit> continuation) {
        return ((DelimitedKt$skipDelimiterSuspend$2) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        DelimitedKt$skipDelimiterSuspend$2 delimitedKt$skipDelimiterSuspend$2;
        LookAheadSuspendSession $this$lookAheadSuspend;
        int tryEnsureDelimiter;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                LookAheadSuspendSession $this$lookAheadSuspend2 = (LookAheadSuspendSession) this.L$0;
                this.L$0 = $this$lookAheadSuspend2;
                this.label = 1;
                if ($this$lookAheadSuspend2.awaitAtLeast(this.$delimiter.remaining(), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                delimitedKt$skipDelimiterSuspend$2 = this;
                $this$lookAheadSuspend = $this$lookAheadSuspend2;
                break;
            case 1:
                delimitedKt$skipDelimiterSuspend$2 = this;
                $this$lookAheadSuspend = (LookAheadSuspendSession) delimitedKt$skipDelimiterSuspend$2.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        tryEnsureDelimiter = DelimitedKt.tryEnsureDelimiter($this$lookAheadSuspend, delimitedKt$skipDelimiterSuspend$2.$delimiter);
        if (tryEnsureDelimiter != delimitedKt$skipDelimiterSuspend$2.$delimiter.remaining()) {
            throw new IOException("Broken delimiter occurred");
        }
        return Unit.INSTANCE;
    }
}
