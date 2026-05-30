package io.ktor.client.network.sockets;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.WriterScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: TimeoutExceptionsCommon.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.network.sockets.TimeoutExceptionsCommonKt$mapEngineExceptions$2", f = "TimeoutExceptionsCommon.kt", i = {}, l = {LockFreeTaskQueueCore.CLOSED_SHIFT}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class TimeoutExceptionsCommonKt$mapEngineExceptions$2 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteWriteChannel $output;
    final /* synthetic */ ByteChannel $replacementChannel;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimeoutExceptionsCommonKt$mapEngineExceptions$2(ByteChannel byteChannel, ByteWriteChannel byteWriteChannel, Continuation<? super TimeoutExceptionsCommonKt$mapEngineExceptions$2> continuation) {
        super(2, continuation);
        this.$replacementChannel = byteChannel;
        this.$output = byteWriteChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TimeoutExceptionsCommonKt$mapEngineExceptions$2(this.$replacementChannel, this.$output, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((TimeoutExceptionsCommonKt$mapEngineExceptions$2) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        TimeoutExceptionsCommonKt$mapEngineExceptions$2 timeoutExceptionsCommonKt$mapEngineExceptions$2 = this.label;
        try {
            switch (timeoutExceptionsCommonKt$mapEngineExceptions$2) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    TimeoutExceptionsCommonKt$mapEngineExceptions$2 timeoutExceptionsCommonKt$mapEngineExceptions$22 = this;
                    timeoutExceptionsCommonKt$mapEngineExceptions$22.label = 1;
                    Object copyAndClose$default = ByteReadChannelKt.copyAndClose$default(timeoutExceptionsCommonKt$mapEngineExceptions$22.$replacementChannel, timeoutExceptionsCommonKt$mapEngineExceptions$22.$output, 0L, timeoutExceptionsCommonKt$mapEngineExceptions$22, 2, null);
                    timeoutExceptionsCommonKt$mapEngineExceptions$2 = timeoutExceptionsCommonKt$mapEngineExceptions$22;
                    if (copyAndClose$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    timeoutExceptionsCommonKt$mapEngineExceptions$2 = this;
                    ResultKt.throwOnFailure(obj);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            timeoutExceptionsCommonKt$mapEngineExceptions$2.$replacementChannel.close(th);
        }
        return Unit.INSTANCE;
    }
}
