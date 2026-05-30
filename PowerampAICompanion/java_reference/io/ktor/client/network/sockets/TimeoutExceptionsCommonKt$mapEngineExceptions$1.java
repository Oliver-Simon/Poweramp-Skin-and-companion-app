package io.ktor.client.network.sockets;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import io.ktor.utils.io.WriterScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: TimeoutExceptionsCommon.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.network.sockets.TimeoutExceptionsCommonKt$mapEngineExceptions$1", f = "TimeoutExceptionsCommon.kt", i = {}, l = {38}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class TimeoutExceptionsCommonKt$mapEngineExceptions$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteReadChannel $input;
    final /* synthetic */ ByteChannel $replacementChannel;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimeoutExceptionsCommonKt$mapEngineExceptions$1(ByteReadChannel byteReadChannel, ByteChannel byteChannel, Continuation<? super TimeoutExceptionsCommonKt$mapEngineExceptions$1> continuation) {
        super(2, continuation);
        this.$input = byteReadChannel;
        this.$replacementChannel = byteChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TimeoutExceptionsCommonKt$mapEngineExceptions$1(this.$input, this.$replacementChannel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((TimeoutExceptionsCommonKt$mapEngineExceptions$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        TimeoutExceptionsCommonKt$mapEngineExceptions$1 timeoutExceptionsCommonKt$mapEngineExceptions$1 = this.label;
        try {
            switch (timeoutExceptionsCommonKt$mapEngineExceptions$1) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    TimeoutExceptionsCommonKt$mapEngineExceptions$1 timeoutExceptionsCommonKt$mapEngineExceptions$12 = this;
                    timeoutExceptionsCommonKt$mapEngineExceptions$12.label = 1;
                    Object copyAndClose$default = ByteReadChannelKt.copyAndClose$default(timeoutExceptionsCommonKt$mapEngineExceptions$12.$input, timeoutExceptionsCommonKt$mapEngineExceptions$12.$replacementChannel, 0L, timeoutExceptionsCommonKt$mapEngineExceptions$12, 2, null);
                    timeoutExceptionsCommonKt$mapEngineExceptions$1 = timeoutExceptionsCommonKt$mapEngineExceptions$12;
                    if (copyAndClose$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    timeoutExceptionsCommonKt$mapEngineExceptions$1 = this;
                    ResultKt.throwOnFailure(obj);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            timeoutExceptionsCommonKt$mapEngineExceptions$1.$input.cancel(th);
        }
        return Unit.INSTANCE;
    }
}
