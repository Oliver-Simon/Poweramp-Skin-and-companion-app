package io.ktor.client.engine.okhttp;

import io.ktor.websocket.CloseReason;
import kotlin.Metadata;

/* compiled from: OkHttpWebsocketSession.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"DEFAULT_CLOSE_REASON_ERROR", "Lio/ktor/websocket/CloseReason;", "isReserved", "", "ktor-client-okhttp"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OkHttpWebsocketSessionKt {
    private static final CloseReason DEFAULT_CLOSE_REASON_ERROR = new CloseReason(CloseReason.Codes.INTERNAL_ERROR, "Client failure");

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isReserved(CloseReason $this$isReserved) {
        CloseReason.Codes recognized = CloseReason.Codes.INSTANCE.byCode($this$isReserved.getCode());
        return recognized == null || recognized == CloseReason.Codes.CLOSED_ABNORMALLY;
    }
}
