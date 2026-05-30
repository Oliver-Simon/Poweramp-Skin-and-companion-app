package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OutputArraysJVM.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"writeFully", "", "Lio/ktor/utils/io/core/Output;", "bb", "Ljava/nio/ByteBuffer;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OutputArraysJVMKt {
    public static final void writeFully(Output $this$writeFully, ByteBuffer bb) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(bb, "bb");
        int l = bb.limit();
        ChunkBuffer tail$iv = UnsafeKt.prepareWriteHead($this$writeFully, 1, null);
        while (true) {
            try {
                Buffer chunk = tail$iv;
                int size = Math.min(bb.remaining(), chunk.getLimit() - chunk.getWritePosition());
                bb.limit(bb.position() + size);
                BufferPrimitivesJvmKt.writeFully(chunk, bb);
                bb.limit(l);
                if (!bb.hasRemaining()) {
                    return;
                } else {
                    tail$iv = UnsafeKt.prepareWriteHead($this$writeFully, 1, tail$iv);
                }
            } finally {
                $this$writeFully.afterHeadWrite();
            }
        }
    }
}
