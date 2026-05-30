package io.ktor.websocket;

import io.ktor.websocket.Frame;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.SendChannel;

/* compiled from: PingPong.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.PingPongKt$pinger$1$rc$1", f = "PingPong.kt", i = {}, l = {75, 79}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class PingPongKt$pinger$1$rc$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Channel<Frame.Pong> $channel;
    final /* synthetic */ SendChannel<Frame> $outgoing;
    final /* synthetic */ String $pingMessage;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PingPongKt$pinger$1$rc$1(SendChannel<? super Frame> sendChannel, String str, Channel<Frame.Pong> channel, Continuation<? super PingPongKt$pinger$1$rc$1> continuation) {
        super(2, continuation);
        this.$outgoing = sendChannel;
        this.$pingMessage = str;
        this.$channel = channel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PingPongKt$pinger$1$rc$1(this.$outgoing, this.$pingMessage, this.$channel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PingPongKt$pinger$1$rc$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0075 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0095  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0076 -> B:7:0x007b). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            switch(r1) {
                case 0: goto L1e;
                case 1: goto L19;
                case 2: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L11:
            r1 = r12
            kotlin.ResultKt.throwOnFailure(r13)
            r2 = r1
            r1 = r0
            r0 = r13
            goto L7b
        L19:
            r1 = r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L66
        L1e:
            kotlin.ResultKt.throwOnFailure(r13)
            r1 = r12
            org.slf4j.Logger r2 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.String r3 = "WebSocket Pinger: sending ping frame"
            r2.trace(r3)
            kotlinx.coroutines.channels.SendChannel<io.ktor.websocket.Frame> r2 = r1.$outgoing
            io.ktor.websocket.Frame$Ping r3 = new io.ktor.websocket.Frame$Ping
            java.lang.String r4 = r1.$pingMessage
            java.nio.charset.Charset r5 = kotlin.text.Charsets.ISO_8859_1
            r6 = 0
            java.nio.charset.Charset r7 = kotlin.text.Charsets.UTF_8
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r7)
            if (r7 == 0) goto L41
            byte[] r7 = kotlin.text.StringsKt.encodeToByteArray(r4)
            goto L56
        L41:
            java.nio.charset.CharsetEncoder r7 = r5.newEncoder()
            java.lang.String r8 = "charset.newEncoder()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            r8 = r4
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9 = 0
            int r10 = r4.length()
            byte[] r7 = io.ktor.utils.io.charsets.CharsetJVMKt.encodeToByteArray(r7, r8, r9, r10)
        L56:
            r3.<init>(r7)
            r4 = r1
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5 = 1
            r1.label = r5
            java.lang.Object r2 = r2.send(r3, r4)
            if (r2 != r0) goto L66
            return r0
        L66:
        L67:
            kotlinx.coroutines.channels.Channel<io.ktor.websocket.Frame$Pong> r2 = r1.$channel
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 2
            r1.label = r4
            java.lang.Object r2 = r2.receive(r3)
            if (r2 != r0) goto L76
            return r0
        L76:
            r11 = r0
            r0 = r13
            r13 = r2
            r2 = r1
            r1 = r11
        L7b:
            io.ktor.websocket.Frame$Pong r13 = (io.ktor.websocket.Frame.Pong) r13
            byte[] r3 = r13.getData()
            java.nio.charset.Charset r4 = kotlin.text.Charsets.ISO_8859_1
            r5 = 0
            int r6 = r3.length
            r7 = 0
            java.lang.String r8 = new java.lang.String
            r8.<init>(r3, r5, r6, r4)
            java.lang.String r3 = r2.$pingMessage
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r3)
            if (r3 == 0) goto Lb3
            org.slf4j.Logger r1 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "WebSocket Pinger: received valid pong frame "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r13)
            java.lang.String r3 = r3.toString()
            r1.trace(r3)
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        Lb3:
            org.slf4j.Logger r3 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "WebSocket Pinger: received invalid pong frame "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r13)
            java.lang.String r5 = ", continue waiting"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.trace(r4)
            r13 = r0
            r0 = r1
            r1 = r2
            goto L67
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.PingPongKt$pinger$1$rc$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
