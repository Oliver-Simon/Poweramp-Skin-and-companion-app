package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Preview.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0001\u001aK\u0010\u0000\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0003*\u00020\u00022!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\t\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"preview", "Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "R", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "tmp", "(Lio/ktor/utils/io/core/BytePacketBuilder;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class PreviewKt {
    public static final <R> R preview(BytePacketBuilder $this$preview, Function1<? super ByteReadPacket, ? extends R> block) {
        Intrinsics.checkNotNullParameter($this$preview, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ByteReadPacket packet = preview($this$preview);
        try {
            return block.invoke(packet);
        } finally {
            packet.release();
        }
    }

    public static final ByteReadPacket preview(BytePacketBuilder $this$preview) {
        Intrinsics.checkNotNullParameter($this$preview, "<this>");
        ChunkBuffer head = $this$preview.getHead$ktor_io();
        return head == ChunkBuffer.INSTANCE.getEmpty() ? ByteReadPacket.INSTANCE.getEmpty() : new ByteReadPacket(BuffersKt.copyAll(head), $this$preview.get_pool());
    }
}
