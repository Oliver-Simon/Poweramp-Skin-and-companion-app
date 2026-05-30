package io.ktor.websocket;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.websocket.CloseReason;
import io.ktor.websocket.Frame;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebSocketSession.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u001d\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a/\u0010\n\u001a\u0002H\u000b\"\f\b\u0000\u0010\u000b*\u0006\u0012\u0002\b\u00030\f*\u00020\u00022\u0010\u0010\n\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u000b0\r¢\u0006\u0002\u0010\u000e\u001a1\u0010\u000f\u001a\u0004\u0018\u0001H\u000b\"\f\b\u0000\u0010\u000b*\u0006\u0012\u0002\b\u00030\f*\u00020\u00022\u0010\u0010\n\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u000b0\r¢\u0006\u0002\u0010\u000e\u001a\u001d\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a\u001d\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0014H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"close", "", "Lio/ktor/websocket/WebSocketSession;", "reason", "Lio/ktor/websocket/CloseReason;", "(Lio/ktor/websocket/WebSocketSession;Lio/ktor/websocket/CloseReason;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cause", "", "(Lio/ktor/websocket/WebSocketSession;Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "closeExceptionally", "extension", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/websocket/WebSocketExtension;", "Lio/ktor/websocket/WebSocketExtensionFactory;", "(Lio/ktor/websocket/WebSocketSession;Lio/ktor/websocket/WebSocketExtensionFactory;)Lio/ktor/websocket/WebSocketExtension;", "extensionOrNull", "send", "content", "", "(Lio/ktor/websocket/WebSocketSession;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Lio/ktor/websocket/WebSocketSession;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class WebSocketSessionKt {
    public static final <T extends WebSocketExtension<?>> T extension(WebSocketSession webSocketSession, WebSocketExtensionFactory<?, T> extension) {
        Intrinsics.checkNotNullParameter(webSocketSession, "<this>");
        Intrinsics.checkNotNullParameter(extension, "extension");
        T t = (T) extensionOrNull(webSocketSession, extension);
        if (t != null) {
            return t;
        }
        throw new IllegalStateException(("Extension " + extension + " not found.").toString());
    }

    public static final <T extends WebSocketExtension<?>> T extensionOrNull(WebSocketSession $this$extensionOrNull, WebSocketExtensionFactory<?, T> extension) {
        Object element$iv;
        Intrinsics.checkNotNullParameter($this$extensionOrNull, "<this>");
        Intrinsics.checkNotNullParameter(extension, "extension");
        List<WebSocketExtension<?>> $this$firstOrNull$iv = $this$extensionOrNull.getExtensions();
        Iterator<T> it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                WebSocketExtension it2 = (WebSocketExtension) element$iv;
                if (it2.getFactory().getKey() == extension.getKey()) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        if (element$iv instanceof WebSocketExtension) {
            return (T) element$iv;
        }
        return null;
    }

    public static final Object send(WebSocketSession $this$send, String content, Continuation<? super Unit> continuation) {
        Object send = $this$send.send(new Frame.Text(content), continuation);
        return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }

    public static final Object send(WebSocketSession $this$send, byte[] content, Continuation<? super Unit> continuation) {
        Object send = $this$send.send(new Frame.Binary(true, content), continuation);
        return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(6:1|(2:3|(4:5|6|7|8))|25|6|7|8) */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c A[Catch: all -> 0x0030, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0030, blocks: (B:12:0x002c, B:16:0x0036, B:17:0x0051, B:21:0x003e), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object close(io.ktor.websocket.WebSocketSession r4, io.ktor.websocket.CloseReason r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.websocket.WebSocketSessionKt$close$1
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.websocket.WebSocketSessionKt$close$1 r0 = (io.ktor.websocket.WebSocketSessionKt$close$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.websocket.WebSocketSessionKt$close$1 r0 = new io.ktor.websocket.WebSocketSessionKt$close$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3a;
                case 1: goto L32;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2c:
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L30
            goto L5e
        L30:
            r4 = move-exception
            goto L5f
        L32:
            java.lang.Object r4 = r0.L$0
            io.ktor.websocket.WebSocketSession r4 = (io.ktor.websocket.WebSocketSession) r4
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L30
            goto L51
        L3a:
            kotlin.ResultKt.throwOnFailure(r6)
            io.ktor.websocket.Frame$Close r2 = new io.ktor.websocket.Frame$Close     // Catch: java.lang.Throwable -> L30
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L30
            io.ktor.websocket.Frame r2 = (io.ktor.websocket.Frame) r2     // Catch: java.lang.Throwable -> L30
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L30
            r3 = 1
            r0.label = r3     // Catch: java.lang.Throwable -> L30
            java.lang.Object r2 = r4.send(r2, r0)     // Catch: java.lang.Throwable -> L30
            if (r2 != r1) goto L51
            return r1
        L51:
            r5 = 0
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L30
            r5 = 2
            r0.label = r5     // Catch: java.lang.Throwable -> L30
            java.lang.Object r5 = r4.flush(r0)     // Catch: java.lang.Throwable -> L30
            if (r5 != r1) goto L5e
            return r1
        L5e:
        L5f:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketSessionKt.close(io.ktor.websocket.WebSocketSession, io.ktor.websocket.CloseReason, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object close$default(WebSocketSession webSocketSession, CloseReason closeReason, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            closeReason = new CloseReason(CloseReason.Codes.NORMAL, "");
        }
        return close(webSocketSession, closeReason, (Continuation<? super Unit>) continuation);
    }

    @Deprecated(message = "Close with reason or terminate instead.")
    public static final Object close(WebSocketSession $this$close, Throwable cause, Continuation<? super Unit> continuation) {
        if (cause == null) {
            Object close$default = close$default($this$close, null, continuation, 1, null);
            return close$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? close$default : Unit.INSTANCE;
        }
        Object closeExceptionally = closeExceptionally($this$close, cause, continuation);
        return closeExceptionally == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? closeExceptionally : Unit.INSTANCE;
    }

    public static final Object closeExceptionally(WebSocketSession $this$closeExceptionally, Throwable cause, Continuation<? super Unit> continuation) {
        CloseReason reason = cause instanceof CancellationException ? new CloseReason(CloseReason.Codes.NORMAL, "") : new CloseReason(CloseReason.Codes.INTERNAL_ERROR, cause.toString());
        Object close = close($this$closeExceptionally, reason, continuation);
        return close == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? close : Unit.INSTANCE;
    }
}
