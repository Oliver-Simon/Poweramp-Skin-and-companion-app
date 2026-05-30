package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BytePacketBuilder.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0013\u001a\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0016H\u0016J\"\u0010\u0013\u001a\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u001cH\u0004J-\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0010H\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#J\b\u0010$\u001a\u00020%H\u0016R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0011\u0010\u000e\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lio/ktor/utils/io/core/BytePacketBuilder;", "Lio/ktor/utils/io/core/Output;", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "(Lio/ktor/utils/io/pool/ObjectPool;)V", "_pool", "get_pool$annotations", "()V", "get_pool", "()Lio/ktor/utils/io/pool/ObjectPool;", "isEmpty", "", "()Z", "isNotEmpty", ContentDisposition.Parameters.Size, "", "getSize", "()I", "append", "value", "", "", "startIndex", "endIndex", "build", "Lio/ktor/utils/io/core/ByteReadPacket;", "closeDestination", "", "flush", "source", "Lio/ktor/utils/io/bits/Memory;", TypedValues.CycleType.S_WAVE_OFFSET, "length", "flush-62zg_DM", "(Ljava/nio/ByteBuffer;II)V", "toString", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BytePacketBuilder extends Output {
    /* JADX WARN: Multi-variable type inference failed */
    public BytePacketBuilder() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ void get_pool$annotations() {
    }

    public /* synthetic */ BytePacketBuilder(ObjectPool objectPool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ChunkBuffer.INSTANCE.getPool() : objectPool);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BytePacketBuilder(ObjectPool<ChunkBuffer> pool) {
        super(pool);
        Intrinsics.checkNotNullParameter(pool, "pool");
    }

    public final int getSize() {
        return get_size();
    }

    public final boolean isEmpty() {
        return get_size() == 0;
    }

    public final boolean isNotEmpty() {
        return get_size() > 0;
    }

    public final ObjectPool<ChunkBuffer> get_pool() {
        return getPool();
    }

    @Override // io.ktor.utils.io.core.Output
    protected final void closeDestination() {
    }

    @Override // io.ktor.utils.io.core.Output
    /* renamed from: flush-62zg_DM, reason: not valid java name */
    protected final void mo414flush62zg_DM(ByteBuffer source, int offset, int length) {
        Intrinsics.checkNotNullParameter(source, "source");
    }

    @Override // io.ktor.utils.io.core.Output, java.lang.Appendable
    public BytePacketBuilder append(char value) {
        Output append = super.append(value);
        Intrinsics.checkNotNull(append, "null cannot be cast to non-null type io.ktor.utils.io.core.BytePacketBuilder");
        return (BytePacketBuilder) append;
    }

    @Override // io.ktor.utils.io.core.Output, java.lang.Appendable
    public BytePacketBuilder append(CharSequence value) {
        Output append = super.append(value);
        Intrinsics.checkNotNull(append, "null cannot be cast to non-null type io.ktor.utils.io.core.BytePacketBuilder");
        return (BytePacketBuilder) append;
    }

    @Override // io.ktor.utils.io.core.Output, java.lang.Appendable
    public BytePacketBuilder append(CharSequence value, int startIndex, int endIndex) {
        Output append = super.append(value, startIndex, endIndex);
        Intrinsics.checkNotNull(append, "null cannot be cast to non-null type io.ktor.utils.io.core.BytePacketBuilder");
        return (BytePacketBuilder) append;
    }

    public final ByteReadPacket build() {
        int size = getSize();
        ChunkBuffer head = stealAll$ktor_io();
        return head == null ? ByteReadPacket.INSTANCE.getEmpty() : new ByteReadPacket(head, size, getPool());
    }

    public String toString() {
        return "BytePacketBuilder(" + getSize() + " bytes written)";
    }
}
