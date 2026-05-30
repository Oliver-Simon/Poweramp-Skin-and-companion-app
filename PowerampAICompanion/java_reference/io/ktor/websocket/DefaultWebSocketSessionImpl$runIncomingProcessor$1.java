package io.ktor.websocket;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.websocket.Frame;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.SendChannel;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DefaultWebSocketSession.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.DefaultWebSocketSessionImpl$runIncomingProcessor$1", f = "DefaultWebSocketSession.kt", i = {0, 0, 0, 0, 1, 1, 1, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6}, l = {345, 171, 219, 177, 178, 180, 204, 219, 219, 219, 219}, m = "invokeSuspend", n = {"$this$launch", "last", "closeFramePresented", "$this$consume$iv$iv", "last", "closeFramePresented", "$this$consume$iv$iv", "$this$launch", "last", "closeFramePresented", "$this$consume$iv$iv", "$this$launch", "last", "closeFramePresented", "$this$consume$iv$iv", "$this$launch", "last", "closeFramePresented", "$this$consume$iv$iv", TypedValues.AttributesType.S_FRAME, "$this$launch", "last", "closeFramePresented", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$5", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$5", "L$0", "L$1", "L$2", "L$5", "L$0", "L$1", "L$2", "L$5", "L$7", "L$0", "L$1", "L$2", "L$5"})
/* loaded from: classes9.dex */
public final class DefaultWebSocketSessionImpl$runIncomingProcessor$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SendChannel<Frame.Ping> $ponger;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    final /* synthetic */ DefaultWebSocketSessionImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DefaultWebSocketSessionImpl$runIncomingProcessor$1(DefaultWebSocketSessionImpl defaultWebSocketSessionImpl, SendChannel<? super Frame.Ping> sendChannel, Continuation<? super DefaultWebSocketSessionImpl$runIncomingProcessor$1> continuation) {
        super(2, continuation);
        this.this$0 = defaultWebSocketSessionImpl;
        this.$ponger = sendChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DefaultWebSocketSessionImpl$runIncomingProcessor$1 defaultWebSocketSessionImpl$runIncomingProcessor$1 = new DefaultWebSocketSessionImpl$runIncomingProcessor$1(this.this$0, this.$ponger, continuation);
        defaultWebSocketSessionImpl$runIncomingProcessor$1.L$0 = obj;
        return defaultWebSocketSessionImpl$runIncomingProcessor$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DefaultWebSocketSessionImpl$runIncomingProcessor$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000a. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 14, insn: 0x0112: MOVE (r4 I:??[OBJECT, ARRAY]) = (r14 I:??[OBJECT, ARRAY] A[D('closeFramePresented' kotlin.jvm.internal.Ref$BooleanRef)]), block:B:231:0x0112 */
    /* JADX WARN: Not initialized variable reg: 15, insn: 0x0113: MOVE (r5 I:??[OBJECT, ARRAY]) = (r15 I:??[OBJECT, ARRAY] A[D('last' kotlin.jvm.internal.Ref$ObjectRef)]), block:B:231:0x0112 */
    /* JADX WARN: Removed duplicated region for block: B:137:0x039d A[Catch: all -> 0x047a, TryCatch #12 {all -> 0x047a, blocks: (B:135:0x0397, B:137:0x039d, B:139:0x03a3), top: B:134:0x0397 }] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03db A[Catch: all -> 0x0473, TryCatch #9 {all -> 0x0473, blocks: (B:142:0x03ab, B:143:0x03b5, B:149:0x03db, B:151:0x03e7, B:155:0x042e), top: B:141:0x03ab }] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0458 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x048d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01d3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x01f2 A[Catch: all -> 0x050b, TRY_LEAVE, TryCatch #6 {all -> 0x050b, blocks: (B:25:0x01ea, B:27:0x01f2), top: B:24:0x01ea }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x05e9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x062c  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0544  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x055a  */
    /* JADX WARN: Type inference failed for: r0v65, types: [T, io.ktor.utils.io.core.BytePacketBuilder] */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v23 */
    /* JADX WARN: Type inference failed for: r11v32 */
    /* JADX WARN: Type inference failed for: r11v35, types: [kotlinx.coroutines.channels.ChannelIterator] */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v25, types: [java.lang.Object, kotlinx.coroutines.channels.ChannelIterator] */
    /* JADX WARN: Type inference failed for: r14v32 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v27, types: [kotlinx.coroutines.channels.ChannelIterator] */
    /* JADX WARN: Type inference failed for: r5v30, types: [kotlinx.coroutines.channels.ChannelIterator] */
    /* JADX WARN: Type inference failed for: r5v57, types: [java.lang.Object, kotlinx.coroutines.channels.ChannelIterator] */
    /* JADX WARN: Type inference failed for: r5v72 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v37, types: [kotlinx.coroutines.channels.SendChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v43 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:154:0x0459 -> B:18:0x0463). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r35) {
        /*
            Method dump skipped, instructions count: 1614
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl$runIncomingProcessor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
