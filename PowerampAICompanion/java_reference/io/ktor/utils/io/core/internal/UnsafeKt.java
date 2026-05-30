package io.ktor.utils.io.core.internal;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.PacketJVMKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Unsafe.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001\u001a\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0006*\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0001\u001a\u0016\u0010\n\u001a\u0004\u0018\u00010\u0006*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001\u001a\u001e\u0010\u000b\u001a\u00020\u0006*\u00020\f2\u0006\u0010\r\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000\u001a\u0014\u0010\u000e\u001a\u00020\t*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"EmptyByteArray", "", "completeReadHead", "", "Lio/ktor/utils/io/core/Input;", "current", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "prepareReadFirstHead", "minSize", "", "prepareReadNextHead", "prepareWriteHead", "Lio/ktor/utils/io/core/Output;", "capacity", "unsafeAppend", "Lio/ktor/utils/io/core/ByteReadPacket;", "builder", "Lio/ktor/utils/io/core/BytePacketBuilder;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UnsafeKt {
    public static final byte[] EmptyByteArray = new byte[0];

    public static final int unsafeAppend(ByteReadPacket $this$unsafeAppend, BytePacketBuilder builder) {
        Intrinsics.checkNotNullParameter($this$unsafeAppend, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        int builderSize = builder.getSize();
        ChunkBuffer builderHead = builder.stealAll$ktor_io();
        if (builderHead == null) {
            return 0;
        }
        if (builderSize <= PacketJVMKt.getPACKET_MAX_COPY_SIZE() && builderHead.getNext() == null && $this$unsafeAppend.tryWriteAppend$ktor_io(builderHead)) {
            builder.afterBytesStolen$ktor_io();
            return builderSize;
        }
        $this$unsafeAppend.append$ktor_io(builderHead);
        return builderSize;
    }

    public static final ChunkBuffer prepareReadFirstHead(Input $this$prepareReadFirstHead, int minSize) {
        Intrinsics.checkNotNullParameter($this$prepareReadFirstHead, "<this>");
        return $this$prepareReadFirstHead.prepareReadHead$ktor_io(minSize);
    }

    public static final void completeReadHead(Input $this$completeReadHead, ChunkBuffer current) {
        Intrinsics.checkNotNullParameter($this$completeReadHead, "<this>");
        Intrinsics.checkNotNullParameter(current, "current");
        if (current == $this$completeReadHead) {
            return;
        }
        ChunkBuffer $this$canRead$iv = current;
        if ($this$canRead$iv.getWritePosition() > $this$canRead$iv.getReadPosition()) {
            ChunkBuffer this_$iv = current;
            if (this_$iv.getCapacity() - this_$iv.getLimit() >= 8) {
                $this$completeReadHead.setHeadPosition(current.getReadPosition());
                return;
            } else {
                $this$completeReadHead.fixGapAfterRead$ktor_io(current);
                return;
            }
        }
        $this$completeReadHead.ensureNext(current);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ChunkBuffer prepareReadNextHead(Input input, ChunkBuffer current) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(current, "current");
        if (current == input) {
            if (input.canRead()) {
                return (ChunkBuffer) input;
            }
            return null;
        }
        return input.ensureNextHead$ktor_io(current);
    }

    public static final ChunkBuffer prepareWriteHead(Output $this$prepareWriteHead, int capacity, ChunkBuffer current) {
        Intrinsics.checkNotNullParameter($this$prepareWriteHead, "<this>");
        if (current != null) {
            $this$prepareWriteHead.afterHeadWrite();
        }
        return $this$prepareWriteHead.prepareWriteHead(capacity);
    }
}
