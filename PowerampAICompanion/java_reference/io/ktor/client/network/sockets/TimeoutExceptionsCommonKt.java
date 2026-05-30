package io.ktor.client.network.sockets;

import io.ktor.client.request.HttpRequestData;
import io.ktor.util.InternalAPI;
import io.ktor.util.PlatformUtils;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.CoroutinesKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TimeoutExceptionsCommon.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u001c\u0010\u0000\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\b"}, d2 = {"mapEngineExceptions", "Lio/ktor/utils/io/ByteReadChannel;", "Lkotlinx/coroutines/CoroutineScope;", "input", "request", "Lio/ktor/client/request/HttpRequestData;", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class TimeoutExceptionsCommonKt {
    @InternalAPI
    public static final ByteReadChannel mapEngineExceptions(CoroutineScope $this$mapEngineExceptions, ByteReadChannel input, HttpRequestData request) {
        Intrinsics.checkNotNullParameter($this$mapEngineExceptions, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(request, "request");
        if (PlatformUtils.INSTANCE.getIS_NATIVE()) {
            return input;
        }
        ByteChannel replacementChannel = TimeoutExceptionsKt.ByteChannelWithMappedExceptions(request);
        CoroutinesKt.writer$default($this$mapEngineExceptions, (CoroutineContext) null, replacementChannel, new TimeoutExceptionsCommonKt$mapEngineExceptions$1(input, replacementChannel, null), 1, (Object) null);
        return replacementChannel;
    }

    @InternalAPI
    public static final ByteWriteChannel mapEngineExceptions(CoroutineScope $this$mapEngineExceptions, ByteWriteChannel output, HttpRequestData request) {
        Intrinsics.checkNotNullParameter($this$mapEngineExceptions, "<this>");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(request, "request");
        if (PlatformUtils.INSTANCE.getIS_NATIVE()) {
            return output;
        }
        ByteChannel replacementChannel = TimeoutExceptionsKt.ByteChannelWithMappedExceptions(request);
        CoroutinesKt.writer$default($this$mapEngineExceptions, (CoroutineContext) null, replacementChannel, new TimeoutExceptionsCommonKt$mapEngineExceptions$2(replacementChannel, output, null), 1, (Object) null);
        return replacementChannel;
    }
}
