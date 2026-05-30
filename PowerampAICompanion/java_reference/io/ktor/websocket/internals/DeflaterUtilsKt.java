package io.ktor.websocket.internals;

import io.ktor.util.cio.ByteBufferPoolKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.OutputArraysJVMKt;
import io.ktor.utils.io.core.StringsKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeflaterUtils.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a$\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002\u001a\u0014\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"EMPTY_CHUNK", "", "PADDED_EMPTY_CHUNK", "deflateFully", "Ljava/util/zip/Deflater;", "data", "deflateTo", "", "Lio/ktor/utils/io/core/BytePacketBuilder;", "deflater", "buffer", "Ljava/nio/ByteBuffer;", "flush", "", "inflateFully", "Ljava/util/zip/Inflater;", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DeflaterUtilsKt {
    private static final byte[] PADDED_EMPTY_CHUNK = {0, 0, 0, -1, -1};
    private static final byte[] EMPTY_CHUNK = {0, 0, -1, -1};

    public static final byte[] deflateFully(Deflater $this$deflateFully, byte[] data) {
        Intrinsics.checkNotNullParameter($this$deflateFully, "<this>");
        Intrinsics.checkNotNullParameter(data, "data");
        $this$deflateFully.setInput(data);
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            ObjectPool $this$useInstance$iv = ByteBufferPoolKt.getKtorDefaultPool();
            ByteBuffer borrow = $this$useInstance$iv.borrow();
            try {
                ByteBuffer buffer = borrow;
                while (!$this$deflateFully.needsInput()) {
                    deflateTo(builder$iv, $this$deflateFully, buffer, false);
                }
                do {
                } while (deflateTo(builder$iv, $this$deflateFully, buffer, true) != 0);
                Unit unit = Unit.INSTANCE;
                $this$useInstance$iv.recycle(borrow);
                ByteReadPacket deflatedBytes = builder$iv.build();
                if (BytePacketUtilsKt.endsWith(deflatedBytes, PADDED_EMPTY_CHUNK)) {
                    byte[] readBytes = StringsKt.readBytes(deflatedBytes, ((int) deflatedBytes.getRemaining()) - EMPTY_CHUNK.length);
                    deflatedBytes.release();
                    return readBytes;
                }
                builder$iv = new BytePacketBuilder(null, 1, null);
                try {
                    builder$iv.writePacket(deflatedBytes);
                    builder$iv.writeByte((byte) 0);
                    return StringsKt.readBytes$default(builder$iv.build(), 0, 1, null);
                } finally {
                }
            } catch (Throwable t$iv) {
                $this$useInstance$iv.recycle(borrow);
                throw t$iv;
            }
        } finally {
        }
    }

    public static final byte[] inflateFully(Inflater $this$inflateFully, byte[] data) {
        Intrinsics.checkNotNullParameter($this$inflateFully, "<this>");
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] dataToInflate = ArraysKt.plus(data, EMPTY_CHUNK);
        $this$inflateFully.setInput(dataToInflate);
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            ObjectPool $this$useInstance$iv = ByteBufferPoolKt.getKtorDefaultPool();
            ByteBuffer borrow = $this$useInstance$iv.borrow();
            try {
                ByteBuffer buffer = borrow;
                long limit = dataToInflate.length + $this$inflateFully.getBytesRead();
                while ($this$inflateFully.getBytesRead() < limit) {
                    buffer.clear();
                    int inflated = $this$inflateFully.inflate(buffer.array(), buffer.position(), buffer.limit());
                    buffer.position(buffer.position() + inflated);
                    buffer.flip();
                    OutputArraysJVMKt.writeFully(builder$iv, buffer);
                }
                Unit unit = Unit.INSTANCE;
                $this$useInstance$iv.recycle(borrow);
                ByteReadPacket packet = builder$iv.build();
                return StringsKt.readBytes$default(packet, 0, 1, null);
            } catch (Throwable th) {
                $this$useInstance$iv.recycle(borrow);
                throw th;
            }
        } catch (Throwable t$iv) {
            builder$iv.release();
            throw t$iv;
        }
    }

    private static final int deflateTo(BytePacketBuilder $this$deflateTo, Deflater deflater, ByteBuffer buffer, boolean flush) {
        int deflated;
        buffer.clear();
        if (flush) {
            deflated = deflater.deflate(buffer.array(), buffer.position(), buffer.limit(), 2);
        } else {
            deflated = deflater.deflate(buffer.array(), buffer.position(), buffer.limit());
        }
        if (deflated == 0) {
            return 0;
        }
        buffer.position(buffer.position() + deflated);
        buffer.flip();
        OutputArraysJVMKt.writeFully($this$deflateTo, buffer);
        return deflated;
    }
}
