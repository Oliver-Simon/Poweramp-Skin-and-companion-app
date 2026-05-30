package io.ktor.utils.io.core;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.DefaultAllocator;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BufferFactory.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a>\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\b0\f¢\u0006\u0002\b\rH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a8\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\u0006\u0010\u000f\u001a\u00020\u00012\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\b0\f¢\u0006\u0002\b\rH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a>\u0010\u0011\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\b0\f¢\u0006\u0002\b\rH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"DEFAULT_BUFFER_SIZE", "", "DefaultChunkedBufferPool", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "getDefaultChunkedBufferPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "withBuffer", "R", "pool", "Lio/ktor/utils/io/core/Buffer;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/utils/io/pool/ObjectPool;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", ContentDisposition.Parameters.Size, "(ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withChunkBuffer", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BufferFactoryKt {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final ObjectPool<ChunkBuffer> DefaultChunkedBufferPool = new DefaultBufferPool(0, 0, null, 7, null);

    public static final <R> R withBuffer(int size, Function1<? super Buffer, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(new Buffer(DefaultAllocator.INSTANCE.mo228allocgFvZug(size), null));
    }

    public static final <R> R withBuffer(ObjectPool<Buffer> pool, Function1<? super Buffer, ? extends R> block) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        Intrinsics.checkNotNullParameter(block, "block");
        Buffer instance = pool.borrow();
        try {
            return block.invoke(instance);
        } finally {
            pool.recycle(instance);
        }
    }

    public static final <R> R withChunkBuffer(ObjectPool<ChunkBuffer> pool, Function1<? super ChunkBuffer, ? extends R> block) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer instance = pool.borrow();
        try {
            return block.invoke(instance);
        } finally {
            instance.release(pool);
        }
    }

    public static final ObjectPool<ChunkBuffer> getDefaultChunkedBufferPool() {
        return DefaultChunkedBufferPool;
    }
}
