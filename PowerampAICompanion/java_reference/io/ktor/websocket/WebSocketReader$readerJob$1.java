package io.ktor.websocket;

import io.ktor.util.cio.ChannelIOException;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WebSocketReader.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.WebSocketReader$readerJob$1", f = "WebSocketReader.kt", i = {0}, l = {40}, m = "invokeSuspend", n = {"buffer"}, s = {"L$0"})
/* loaded from: classes9.dex */
public final class WebSocketReader$readerJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObjectPool<ByteBuffer> $pool;
    Object L$0;
    int label;
    final /* synthetic */ WebSocketReader this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebSocketReader$readerJob$1(ObjectPool<ByteBuffer> objectPool, WebSocketReader webSocketReader, Continuation<? super WebSocketReader$readerJob$1> continuation) {
        super(2, continuation);
        this.$pool = objectPool;
        this.this$0 = webSocketReader;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WebSocketReader$readerJob$1(this.$pool, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WebSocketReader$readerJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0008. Please report as an issue. */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, io.ktor.websocket.WebSocketReader$readerJob$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Channel channel;
        WebSocketReader$readerJob$1 webSocketReader$readerJob$1;
        ByteBuffer buffer;
        ProtocolViolationException cause;
        FrameTooBigException cause2;
        Object readLoop;
        Channel channel2;
        Channel channel3;
        Channel channel4;
        Channel channel5;
        ?? coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            switch (i) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    ByteBuffer buffer2 = this.$pool.borrow();
                    try {
                        this.L$0 = buffer2;
                        this.label = 1;
                        readLoop = this.this$0.readLoop(buffer2, this);
                    } catch (ChannelIOException e) {
                        webSocketReader$readerJob$1 = this;
                        buffer = buffer2;
                        channel4 = webSocketReader$readerJob$1.this$0.queue;
                        ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) channel4, (CancellationException) null, 1, (Object) null);
                    } catch (FrameTooBigException e2) {
                        cause2 = e2;
                        webSocketReader$readerJob$1 = this;
                        buffer = buffer2;
                        channel3 = webSocketReader$readerJob$1.this$0.queue;
                        channel3.close(cause2);
                    } catch (ProtocolViolationException e3) {
                        cause = e3;
                        webSocketReader$readerJob$1 = this;
                        buffer = buffer2;
                        channel2 = webSocketReader$readerJob$1.this$0.queue;
                        channel2.close(cause);
                    } catch (ClosedChannelException e4) {
                        webSocketReader$readerJob$1 = this;
                        buffer = buffer2;
                    } catch (CancellationException e5) {
                        webSocketReader$readerJob$1 = this;
                        buffer = buffer2;
                    } catch (Throwable cause3) {
                        throw cause3;
                    }
                    if (readLoop == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    webSocketReader$readerJob$1 = this;
                    buffer = buffer2;
                    webSocketReader$readerJob$1.$pool.recycle(buffer);
                    channel5 = webSocketReader$readerJob$1.this$0.queue;
                    SendChannel.DefaultImpls.close$default(channel5, null, 1, null);
                    return Unit.INSTANCE;
                case 1:
                    webSocketReader$readerJob$1 = this;
                    buffer = (ByteBuffer) webSocketReader$readerJob$1.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                    } catch (ChannelIOException e6) {
                        channel4 = webSocketReader$readerJob$1.this$0.queue;
                        ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) channel4, (CancellationException) null, 1, (Object) null);
                    } catch (FrameTooBigException e7) {
                        cause2 = e7;
                        channel3 = webSocketReader$readerJob$1.this$0.queue;
                        channel3.close(cause2);
                    } catch (ProtocolViolationException e8) {
                        cause = e8;
                        channel2 = webSocketReader$readerJob$1.this$0.queue;
                        channel2.close(cause);
                    } catch (ClosedChannelException e9) {
                    } catch (CancellationException e10) {
                    } catch (Throwable cause4) {
                        throw cause4;
                    }
                    webSocketReader$readerJob$1.$pool.recycle(buffer);
                    channel5 = webSocketReader$readerJob$1.this$0.queue;
                    SendChannel.DefaultImpls.close$default(channel5, null, 1, null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            coroutine_suspended.$pool.recycle(i);
            channel = coroutine_suspended.this$0.queue;
            SendChannel.DefaultImpls.close$default(channel, null, 1, null);
            throw th;
        }
    }
}
