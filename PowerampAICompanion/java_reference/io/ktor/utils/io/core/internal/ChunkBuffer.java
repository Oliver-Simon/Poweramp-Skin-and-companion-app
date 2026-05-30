package io.ktor.utils.io.core.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.net.HttpHeaders;
import io.ktor.http.LinkHeader;
import io.ktor.utils.io.bits.DefaultAllocator;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BufferFactoryKt;
import io.ktor.utils.io.pool.NoPoolImpl;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChunkBuffer.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0016\u0018\u0000 +2\u00020-:\u0001+B,\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0000\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0004ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u000b\u001a\u00020\bH\u0000¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\u0011\u0010\u0010J\u000f\u0010\u0015\u001a\u00020\u0012H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00000\u0004H\u0016¢\u0006\u0004\b\u0015\u0010\u0017J\r\u0010\u0018\u001a\u00020\b¢\u0006\u0004\b\u0018\u0010\nJ\u000f\u0010\u001a\u001a\u00020\bH\u0000¢\u0006\u0004\b\u0019\u0010\nJ\u000f\u0010\u001c\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001b\u0010\nR(\u0010 \u001a\u0004\u0018\u00010\u00002\b\u0010\u001d\u001a\u0004\u0018\u00010\u00008F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010\u000eR(\u0010\u0003\u001a\u0004\u0018\u00010\u00002\b\u0010!\u001a\u0004\u0018\u00010\u00008\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u0010R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010$\u001a\u0004\b%\u0010&R\u0011\u0010*\u001a\u00020'8F¢\u0006\u0006\u001a\u0004\b(\u0010)\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lio/ktor/utils/io/core/internal/ChunkBuffer;", "Lio/ktor/utils/io/bits/Memory;", "memory", HttpHeaders.ReferrerPolicyValues.ORIGIN, "Lio/ktor/utils/io/pool/ObjectPool;", "parentPool", "<init>", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/core/internal/ChunkBuffer;Lio/ktor/utils/io/pool/ObjectPool;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "", "acquire$ktor_io", "()V", "acquire", "chunk", "appendNext", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)V", "cleanNext", "()Lio/ktor/utils/io/core/internal/ChunkBuffer;", "duplicate", "", "release$ktor_io", "()Z", "release", "pool", "(Lio/ktor/utils/io/pool/ObjectPool;)V", "reset", "unlink$ktor_io", "unlink", "unpark$ktor_io", "unpark", "newValue", "getNext", "setNext", LinkHeader.Rel.Next, "<set-?>", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "getOrigin", "Lio/ktor/utils/io/pool/ObjectPool;", "getParentPool$ktor_io", "()Lio/ktor/utils/io/pool/ObjectPool;", "", "getReferenceCount", "()I", "referenceCount", "Companion", "ktor-io", "Lio/ktor/utils/io/core/Buffer;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public class ChunkBuffer extends Buffer {
    private volatile /* synthetic */ Object nextRef;
    private ChunkBuffer origin;
    private final ObjectPool<ChunkBuffer> parentPool;
    private volatile /* synthetic */ int refCount;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ObjectPool<ChunkBuffer> Pool = new ObjectPool<ChunkBuffer>() { // from class: io.ktor.utils.io.core.internal.ChunkBuffer$Companion$Pool$1
        @Override // io.ktor.utils.io.pool.ObjectPool, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ObjectPool.DefaultImpls.close(this);
        }

        @Override // io.ktor.utils.io.pool.ObjectPool
        public int getCapacity() {
            return BufferFactoryKt.getDefaultChunkedBufferPool().getCapacity();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.utils.io.pool.ObjectPool
        public ChunkBuffer borrow() {
            return BufferFactoryKt.getDefaultChunkedBufferPool().borrow();
        }

        @Override // io.ktor.utils.io.pool.ObjectPool
        public void recycle(ChunkBuffer instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            BufferFactoryKt.getDefaultChunkedBufferPool().recycle(instance);
        }

        @Override // io.ktor.utils.io.pool.ObjectPool
        public void dispose() {
            BufferFactoryKt.getDefaultChunkedBufferPool().dispose();
        }
    };
    private static final ObjectPool<ChunkBuffer> EmptyPool = new ObjectPool<ChunkBuffer>() { // from class: io.ktor.utils.io.core.internal.ChunkBuffer$Companion$EmptyPool$1
        @Override // io.ktor.utils.io.pool.ObjectPool, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ObjectPool.DefaultImpls.close(this);
        }

        @Override // io.ktor.utils.io.pool.ObjectPool
        public int getCapacity() {
            return 1;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.utils.io.pool.ObjectPool
        public ChunkBuffer borrow() {
            return ChunkBuffer.INSTANCE.getEmpty();
        }

        @Override // io.ktor.utils.io.pool.ObjectPool
        public void recycle(ChunkBuffer instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            if (!(instance == ChunkBuffer.INSTANCE.getEmpty())) {
                throw new IllegalArgumentException("Only ChunkBuffer.Empty instance could be recycled.".toString());
            }
        }

        @Override // io.ktor.utils.io.pool.ObjectPool
        public void dispose() {
        }
    };
    private static final ChunkBuffer Empty = new ChunkBuffer(Memory.INSTANCE.m251getEmptySK3TCg8(), 0 == true ? 1 : 0, EmptyPool, 0 == true ? 1 : 0);
    private static final ObjectPool<ChunkBuffer> NoPool = new NoPoolImpl<ChunkBuffer>() { // from class: io.ktor.utils.io.core.internal.ChunkBuffer$Companion$NoPool$1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.ktor.utils.io.pool.ObjectPool
        public ChunkBuffer borrow() {
            return new ChunkBuffer(DefaultAllocator.INSTANCE.mo228allocgFvZug(4096), null, this, 0 == true ? 1 : 0);
        }

        @Override // io.ktor.utils.io.pool.NoPoolImpl, io.ktor.utils.io.pool.ObjectPool
        public void recycle(ChunkBuffer instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            DefaultAllocator.INSTANCE.mo230free3GNKZMM(instance.getMemory());
        }
    };
    private static final ObjectPool<ChunkBuffer> NoPoolManuallyManaged = new NoPoolImpl<ChunkBuffer>() { // from class: io.ktor.utils.io.core.internal.ChunkBuffer$Companion$NoPoolManuallyManaged$1
        @Override // io.ktor.utils.io.pool.ObjectPool
        public ChunkBuffer borrow() {
            throw new UnsupportedOperationException("This pool doesn't support borrow");
        }

        @Override // io.ktor.utils.io.pool.NoPoolImpl, io.ktor.utils.io.pool.ObjectPool
        public void recycle(ChunkBuffer instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
        }
    };
    private static final /* synthetic */ AtomicReferenceFieldUpdater nextRef$FU = AtomicReferenceFieldUpdater.newUpdater(ChunkBuffer.class, Object.class, "nextRef");
    private static final /* synthetic */ AtomicIntegerFieldUpdater refCount$FU = AtomicIntegerFieldUpdater.newUpdater(ChunkBuffer.class, "refCount");

    public /* synthetic */ ChunkBuffer(ByteBuffer byteBuffer, ChunkBuffer chunkBuffer, ObjectPool objectPool, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteBuffer, chunkBuffer, objectPool);
    }

    public final ObjectPool<ChunkBuffer> getParentPool$ktor_io() {
        return this.parentPool;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private ChunkBuffer(ByteBuffer memory, ChunkBuffer origin, ObjectPool<ChunkBuffer> objectPool) {
        super(memory, null);
        Intrinsics.checkNotNullParameter(memory, "memory");
        this.parentPool = objectPool;
        if (!(origin != this)) {
            throw new IllegalArgumentException("A chunk couldn't be a view of itself.".toString());
        }
        this.nextRef = null;
        this.refCount = 1;
        this.origin = origin;
    }

    public final ChunkBuffer getOrigin() {
        return this.origin;
    }

    public final ChunkBuffer getNext() {
        return (ChunkBuffer) this.nextRef;
    }

    public final void setNext(ChunkBuffer newValue) {
        if (newValue == null) {
            cleanNext();
        } else {
            appendNext(newValue);
        }
    }

    /* renamed from: getReferenceCount, reason: from getter */
    public final int getRefCount() {
        return this.refCount;
    }

    private final void appendNext(ChunkBuffer chunk) {
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(nextRef$FU, this, null, chunk)) {
            throw new IllegalStateException("This chunk has already a next chunk.");
        }
    }

    public final ChunkBuffer cleanNext() {
        return (ChunkBuffer) nextRef$FU.getAndSet(this, null);
    }

    @Override // io.ktor.utils.io.core.Buffer
    public ChunkBuffer duplicate() {
        ChunkBuffer chunkBuffer = this.origin;
        if (chunkBuffer == null) {
            chunkBuffer = this;
        }
        ChunkBuffer newOrigin = chunkBuffer;
        newOrigin.acquire$ktor_io();
        ChunkBuffer copy = new ChunkBuffer(getMemory(), newOrigin, this.parentPool, null);
        duplicateTo(copy);
        return copy;
    }

    public void release(ObjectPool<ChunkBuffer> pool) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        if (release$ktor_io()) {
            ChunkBuffer origin = this.origin;
            if (origin != null) {
                unlink$ktor_io();
                origin.release(pool);
            } else {
                ObjectPool poolToUse = this.parentPool;
                if (poolToUse == null) {
                    poolToUse = pool;
                }
                poolToUse.recycle(this);
            }
        }
    }

    public final void unlink$ktor_io() {
        if (!refCount$FU.compareAndSet(this, 0, -1)) {
            throw new IllegalStateException("Unable to unlink: buffer is in use.");
        }
        cleanNext();
        this.origin = null;
    }

    public final void acquire$ktor_io() {
        int cur$iv;
        int upd$iv;
        do {
            cur$iv = this.refCount;
            if (cur$iv <= 0) {
                throw new IllegalStateException("Unable to acquire chunk: it is already released.");
            }
            upd$iv = cur$iv + 1;
        } while (!refCount$FU.compareAndSet(this, cur$iv, upd$iv));
    }

    public final void unpark$ktor_io() {
        int cur$iv;
        do {
            cur$iv = this.refCount;
            if (cur$iv < 0) {
                throw new IllegalStateException("This instance is already disposed and couldn't be borrowed.");
            }
            if (cur$iv > 0) {
                throw new IllegalStateException("This instance is already in use but somehow appeared in the pool.");
            }
        } while (!refCount$FU.compareAndSet(this, cur$iv, 1));
    }

    public final boolean release$ktor_io() {
        int cur$iv;
        int upd$iv;
        do {
            cur$iv = this.refCount;
            if (cur$iv <= 0) {
                throw new IllegalStateException("Unable to release: it is already released.");
            }
            upd$iv = cur$iv - 1;
        } while (!refCount$FU.compareAndSet(this, cur$iv, upd$iv));
        return upd$iv == 0;
    }

    @Override // io.ktor.utils.io.core.Buffer
    public final void reset() {
        if (!(this.origin == null)) {
            throw new IllegalArgumentException("Unable to reset buffer with origin".toString());
        }
        super.reset();
        this.nextRef = null;
    }

    /* compiled from: ChunkBuffer.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\n¨\u0006\u0011"}, d2 = {"Lio/ktor/utils/io/core/internal/ChunkBuffer$Companion;", "", "()V", "Empty", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "getEmpty", "()Lio/ktor/utils/io/core/internal/ChunkBuffer;", "EmptyPool", "Lio/ktor/utils/io/pool/ObjectPool;", "getEmptyPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "NoPool", "getNoPool$ktor_io", "NoPoolManuallyManaged", "getNoPoolManuallyManaged$ktor_io", "Pool", "getPool", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ObjectPool<ChunkBuffer> getPool() {
            return ChunkBuffer.Pool;
        }

        public final ObjectPool<ChunkBuffer> getEmptyPool() {
            return ChunkBuffer.EmptyPool;
        }

        public final ChunkBuffer getEmpty() {
            return ChunkBuffer.Empty;
        }

        public final ObjectPool<ChunkBuffer> getNoPool$ktor_io() {
            return ChunkBuffer.NoPool;
        }

        public final ObjectPool<ChunkBuffer> getNoPoolManuallyManaged$ktor_io() {
            return ChunkBuffer.NoPoolManuallyManaged;
        }
    }
}
