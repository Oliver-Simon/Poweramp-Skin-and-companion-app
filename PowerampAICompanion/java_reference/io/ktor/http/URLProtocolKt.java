package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: URLProtocol.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0004"}, d2 = {"isSecure", "", "Lio/ktor/http/URLProtocol;", "isWebsocket", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class URLProtocolKt {
    public static final boolean isWebsocket(URLProtocol $this$isWebsocket) {
        Intrinsics.checkNotNullParameter($this$isWebsocket, "<this>");
        return Intrinsics.areEqual($this$isWebsocket.getName(), "ws") || Intrinsics.areEqual($this$isWebsocket.getName(), "wss");
    }

    public static final boolean isSecure(URLProtocol $this$isSecure) {
        Intrinsics.checkNotNullParameter($this$isSecure, "<this>");
        return Intrinsics.areEqual($this$isSecure.getName(), "https") || Intrinsics.areEqual($this$isSecure.getName(), "wss");
    }
}
