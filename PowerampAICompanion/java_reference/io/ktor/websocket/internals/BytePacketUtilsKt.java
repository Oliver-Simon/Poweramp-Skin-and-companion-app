package io.ktor.websocket.internals;

import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.StringsKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BytePacketUtils.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"endsWith", "", "Lio/ktor/utils/io/core/ByteReadPacket;", "data", "", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BytePacketUtilsKt {
    public static final boolean endsWith(ByteReadPacket $this$endsWith, byte[] data) {
        Intrinsics.checkNotNullParameter($this$endsWith, "<this>");
        Intrinsics.checkNotNullParameter(data, "data");
        ByteReadPacket $this$endsWith_u24lambda_u240 = $this$endsWith.copy();
        $this$endsWith_u24lambda_u240.discard($this$endsWith_u24lambda_u240.getRemaining() - data.length);
        return Arrays.equals(StringsKt.readBytes$default($this$endsWith_u24lambda_u240, 0, 1, null), data);
    }
}
