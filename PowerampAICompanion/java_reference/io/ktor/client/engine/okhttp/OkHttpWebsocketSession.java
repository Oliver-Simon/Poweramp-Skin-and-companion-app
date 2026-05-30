package io.ktor.client.engine.okhttp;

import io.ktor.client.plugins.websocket.WebSocketException;
import io.ktor.websocket.CloseReason;
import io.ktor.websocket.DefaultWebSocketSession;
import io.ktor.websocket.Frame;
import io.ktor.websocket.WebSocketExtension;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/* compiled from: OkHttpWebsocketSession.kt */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0011\u0010?\u001a\u00020@H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010AJ \u0010B\u001a\u00020@2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0016J \u0010I\u001a\u00020@2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0016J\"\u0010J\u001a\u00020@2\u0006\u0010C\u001a\u00020D2\u0006\u0010K\u001a\u00020L2\b\u0010M\u001a\u0004\u0018\u00010/H\u0016J\u0018\u0010N\u001a\u00020@2\u0006\u0010C\u001a\u00020D2\u0006\u0010O\u001a\u00020HH\u0016J\u0018\u0010N\u001a\u00020@2\u0006\u0010C\u001a\u00020D2\u0006\u0010P\u001a\u00020QH\u0016J\u0018\u0010R\u001a\u00020@2\u0006\u0010C\u001a\u00020D2\u0006\u0010M\u001a\u00020/H\u0016J\u0006\u0010S\u001a\u00020@J\u001a\u0010S\u001a\u00020@2\u0010\u0010T\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0\u0019H\u0016J\b\u0010U\u001a\u00020@H\u0017R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R$\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010)\u001a\u00020(2\u0006\u0010!\u001a\u00020(8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R \u00102\u001a\b\u0012\u0004\u0012\u00020\u001103X\u0096\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u00105\u001a\u0004\b6\u00107R$\u00108\u001a\u00020(2\u0006\u0010!\u001a\u00020(8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b9\u0010+\"\u0004\b:\u0010-R\u0014\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010<\u001a\u00020(2\u0006\u0010!\u001a\u00020(8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b=\u0010+\"\u0004\b>\u0010-R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006V"}, d2 = {"Lio/ktor/client/engine/okhttp/OkHttpWebsocketSession;", "Lio/ktor/websocket/DefaultWebSocketSession;", "Lokhttp3/WebSocketListener;", "engine", "Lokhttp3/OkHttpClient;", "webSocketFactory", "Lokhttp3/WebSocket$Factory;", "engineRequest", "Lokhttp3/Request;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lokhttp3/OkHttpClient;Lokhttp3/WebSocket$Factory;Lokhttp3/Request;Lkotlin/coroutines/CoroutineContext;)V", "_closeReason", "Lkotlinx/coroutines/CompletableDeferred;", "Lio/ktor/websocket/CloseReason;", "_incoming", "Lkotlinx/coroutines/channels/Channel;", "Lio/ktor/websocket/Frame;", "closeReason", "Lkotlinx/coroutines/Deferred;", "getCloseReason", "()Lkotlinx/coroutines/Deferred;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "extensions", "", "Lio/ktor/websocket/WebSocketExtension;", "getExtensions", "()Ljava/util/List;", "incoming", "Lkotlinx/coroutines/channels/ReceiveChannel;", "getIncoming", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "<anonymous parameter 0>", "", "masking", "getMasking", "()Z", "setMasking", "(Z)V", "", "maxFrameSize", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "originResponse", "Lokhttp3/Response;", "getOriginResponse$ktor_client_okhttp", "()Lkotlinx/coroutines/CompletableDeferred;", "outgoing", "Lkotlinx/coroutines/channels/SendChannel;", "getOutgoing$annotations", "()V", "getOutgoing", "()Lkotlinx/coroutines/channels/SendChannel;", "pingIntervalMillis", "getPingIntervalMillis", "setPingIntervalMillis", "self", "timeoutMillis", "getTimeoutMillis", "setTimeoutMillis", "flush", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClosed", "webSocket", "Lokhttp3/WebSocket;", "code", "", "reason", "", "onClosing", "onFailure", "t", "", "response", "onMessage", "text", "bytes", "Lokio/ByteString;", "onOpen", "start", "negotiatedExtensions", "terminate", "ktor-client-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OkHttpWebsocketSession extends WebSocketListener implements DefaultWebSocketSession {
    private final CompletableDeferred<CloseReason> _closeReason;
    private final Channel<Frame> _incoming;
    private final CoroutineContext coroutineContext;
    private final OkHttpClient engine;
    private final CompletableDeferred<Response> originResponse;
    private final SendChannel<Frame> outgoing;
    private final CompletableDeferred<OkHttpWebsocketSession> self;
    private final WebSocket.Factory webSocketFactory;

    public static /* synthetic */ void getOutgoing$annotations() {
    }

    @Override // io.ktor.websocket.WebSocketSession
    public Object send(Frame frame, Continuation<? super Unit> continuation) {
        return DefaultWebSocketSession.DefaultImpls.send(this, frame, continuation);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public OkHttpWebsocketSession(OkHttpClient engine, WebSocket.Factory webSocketFactory, Request engineRequest, CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(engine, "engine");
        Intrinsics.checkNotNullParameter(webSocketFactory, "webSocketFactory");
        Intrinsics.checkNotNullParameter(engineRequest, "engineRequest");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        this.engine = engine;
        this.webSocketFactory = webSocketFactory;
        this.coroutineContext = coroutineContext;
        this.self = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.originResponse = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this._incoming = ChannelKt.Channel$default(0, null, null, 7, null);
        this._closeReason = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.outgoing = ActorKt.actor$default(this, null, 0, null, null, new OkHttpWebsocketSession$outgoing$1(this, engineRequest, null), 15, null);
    }

    public final CompletableDeferred<Response> getOriginResponse$ktor_client_okhttp() {
        return this.originResponse;
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public long getPingIntervalMillis() {
        return this.engine.pingIntervalMillis();
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public void setPingIntervalMillis(long j) {
        throw new WebSocketException("OkHttp doesn't support dynamic ping interval. You could switch it in the engine configuration.");
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public long getTimeoutMillis() {
        return this.engine.readTimeoutMillis();
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public void setTimeoutMillis(long j) {
        throw new WebSocketException("Websocket timeout should be configured in OkHttpEngine.");
    }

    @Override // io.ktor.websocket.WebSocketSession
    public boolean getMasking() {
        return true;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public void setMasking(boolean z) {
        throw new WebSocketException("Masking switch is not supported in OkHttp engine.");
    }

    @Override // io.ktor.websocket.WebSocketSession
    public long getMaxFrameSize() {
        return Long.MAX_VALUE;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public void setMaxFrameSize(long j) {
        throw new WebSocketException("Max frame size switch is not supported in OkHttp engine.");
    }

    @Override // io.ktor.websocket.WebSocketSession
    public ReceiveChannel<Frame> getIncoming() {
        return this._incoming;
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public Deferred<CloseReason> getCloseReason() {
        return this._closeReason;
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public void start(List<? extends WebSocketExtension<?>> negotiatedExtensions) {
        Intrinsics.checkNotNullParameter(negotiatedExtensions, "negotiatedExtensions");
        if (!negotiatedExtensions.isEmpty()) {
            throw new IllegalArgumentException("Extensions are not supported.".toString());
        }
    }

    @Override // io.ktor.websocket.WebSocketSession
    public SendChannel<Frame> getOutgoing() {
        return this.outgoing;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public List<WebSocketExtension<?>> getExtensions() {
        return CollectionsKt.emptyList();
    }

    @Override // okhttp3.WebSocketListener
    public void onOpen(WebSocket webSocket, Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(response, "response");
        super.onOpen(webSocket, response);
        this.originResponse.complete(response);
    }

    @Override // okhttp3.WebSocketListener
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        super.onMessage(webSocket, bytes);
        ChannelsKt.trySendBlocking(this._incoming, new Frame.Binary(true, bytes.toByteArray()));
    }

    @Override // okhttp3.WebSocketListener
    public void onMessage(WebSocket webSocket, String text) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(text, "text");
        super.onMessage(webSocket, text);
        Channel<Frame> channel = this._incoming;
        byte[] bytes = text.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        ChannelsKt.trySendBlocking(channel, new Frame.Text(true, bytes));
    }

    @Override // okhttp3.WebSocketListener
    public void onClosed(WebSocket webSocket, int code, String reason) {
        Object valueOf;
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(reason, "reason");
        super.onClosed(webSocket, code, reason);
        this._closeReason.complete(new CloseReason((short) code, reason));
        SendChannel.DefaultImpls.close$default(this._incoming, null, 1, null);
        SendChannel<Frame> outgoing = getOutgoing();
        StringBuilder append = new StringBuilder().append("WebSocket session closed with code ");
        CloseReason.Codes byCode = CloseReason.Codes.INSTANCE.byCode((short) code);
        if (byCode == null || (valueOf = byCode.toString()) == null) {
            valueOf = Integer.valueOf(code);
        }
        outgoing.close(new CancellationException(append.append(valueOf).append('.').toString()));
    }

    @Override // okhttp3.WebSocketListener
    public void onClosing(WebSocket webSocket, int code, String reason) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(reason, "reason");
        super.onClosing(webSocket, code, reason);
        this._closeReason.complete(new CloseReason((short) code, reason));
        try {
            ChannelsKt.trySendBlocking(getOutgoing(), new Frame.Close(new CloseReason((short) code, reason)));
        } catch (Throwable th) {
        }
        SendChannel.DefaultImpls.close$default(this._incoming, null, 1, null);
    }

    @Override // okhttp3.WebSocketListener
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(t, "t");
        super.onFailure(webSocket, t, response);
        this._closeReason.completeExceptionally(t);
        this.originResponse.completeExceptionally(t);
        this._incoming.close(t);
        getOutgoing().close(t);
    }

    @Override // io.ktor.websocket.WebSocketSession
    public Object flush(Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    public final void start() {
        this.self.complete(this);
    }

    @Override // io.ktor.websocket.WebSocketSession
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = {"kotlinx.coroutines.cancel"}))
    public void terminate() {
        JobKt__JobKt.cancel$default(getCoroutineContext(), (CancellationException) null, 1, (Object) null);
    }
}
