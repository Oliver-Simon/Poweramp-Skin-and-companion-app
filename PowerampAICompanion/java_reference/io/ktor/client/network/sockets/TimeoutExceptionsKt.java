package io.ktor.client.network.sockets;

import io.ktor.client.plugins.HttpTimeoutKt;
import io.ktor.client.request.HttpRequestData;
import io.ktor.util.ThrowableKt;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteChannelKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimeoutExceptions.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"ByteChannelWithMappedExceptions", "Lio/ktor/utils/io/ByteChannel;", "request", "Lio/ktor/client/request/HttpRequestData;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class TimeoutExceptionsKt {
    public static final ByteChannel ByteChannelWithMappedExceptions(final HttpRequestData request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return ByteChannelKt.ByteChannel$default(false, new Function1<Throwable, Throwable>() { // from class: io.ktor.client.network.sockets.TimeoutExceptionsKt$ByteChannelWithMappedExceptions$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Throwable invoke(Throwable cause) {
                return (cause != null ? ThrowableKt.getRootCause(cause) : null) instanceof java.net.SocketTimeoutException ? HttpTimeoutKt.SocketTimeoutException(HttpRequestData.this, cause) : cause;
            }
        }, 1, null);
    }
}
