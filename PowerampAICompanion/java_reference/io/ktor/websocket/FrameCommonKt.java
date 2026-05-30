package io.ktor.websocket;

import io.ktor.utils.io.charsets.EncodingKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.InputPrimitivesKt;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.websocket.Frame;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: FrameCommon.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\b¨\u0006\t"}, d2 = {"readBytes", "", "Lio/ktor/websocket/Frame;", "readReason", "Lio/ktor/websocket/CloseReason;", "Lio/ktor/websocket/Frame$Close;", "readText", "", "Lio/ktor/websocket/Frame$Text;", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class FrameCommonKt {
    public static final String readText(Frame.Text $this$readText) {
        Intrinsics.checkNotNullParameter($this$readText, "<this>");
        if (!$this$readText.getFin()) {
            throw new IllegalArgumentException("Text could be only extracted from non-fragmented frame".toString());
        }
        CharsetDecoder newDecoder = Charsets.UTF_8.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "UTF_8.newDecoder()");
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            OutputKt.writeFully$default((Output) builder$iv, $this$readText.getData(), 0, 0, 6, (Object) null);
            return EncodingKt.decode$default(newDecoder, builder$iv.build(), 0, 2, null);
        } catch (Throwable t$iv) {
            builder$iv.release();
            throw t$iv;
        }
    }

    public static final byte[] readBytes(Frame $this$readBytes) {
        Intrinsics.checkNotNullParameter($this$readBytes, "<this>");
        byte[] data = $this$readBytes.getData();
        byte[] copyOf = Arrays.copyOf(data, data.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return copyOf;
    }

    public static final CloseReason readReason(Frame.Close $this$readReason) {
        Intrinsics.checkNotNullParameter($this$readReason, "<this>");
        if ($this$readReason.getData().length < 2) {
            return null;
        }
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            OutputKt.writeFully$default((Output) builder$iv, $this$readReason.getData(), 0, 0, 6, (Object) null);
            ByteReadPacket packet = builder$iv.build();
            short code = InputPrimitivesKt.readShort(packet);
            String message = Input.readText$default(packet, 0, 0, 3, null);
            return new CloseReason(code, message);
        } catch (Throwable t$iv) {
            builder$iv.release();
            throw t$iv;
        }
    }
}
