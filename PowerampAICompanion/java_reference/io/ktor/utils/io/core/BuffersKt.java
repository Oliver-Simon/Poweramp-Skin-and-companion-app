package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Buffers.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0080\b\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\u0007H\u0000\u001a\u001d\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0082\u0010\u001a\r\u0010\n\u001a\u00020\u0007*\u00020\u0007H\u0080\u0010\u001a1\u0010\u000b\u001a\u00020\f*\u00020\u00072\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f0\u000eH\u0080\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002\u001a\r\u0010\u000f\u001a\u00020\u0010*\u00020\u0007H\u0080\u0010\u001a9\u0010\u0011\u001a\u00020\u0002*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002H\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0014\u0010\u001a\u001a\u00020\u001b*\u00020\u00122\b\b\u0002\u0010\u001c\u001a\u00020\u0001\u001a\u001c\u0010\u001d\u001a\u00020\f*\u0004\u0018\u00010\u00072\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u001fH\u0000\u001a\f\u0010 \u001a\u00020\u0002*\u00020\u0007H\u0000\u001a\u0015\u0010 \u001a\u00020\u0002*\u00020\u00072\u0006\u0010!\u001a\u00020\u0002H\u0082\u0010\u0082\u0002\u0012\n\u0005\b\u009920\u0001\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\""}, d2 = {"coerceAtMostMaxInt", "", "", "coerceAtMostMaxIntOrFail", "message", "", "copyAll", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "head", "prev", "findTail", "forEachChunk", "", "block", "Lkotlin/Function1;", "isEmpty", "", "peekTo", "Lio/ktor/utils/io/core/Buffer;", "destination", "Lio/ktor/utils/io/bits/Memory;", "destinationOffset", TypedValues.CycleType.S_WAVE_OFFSET, "max", "peekTo-yRinSxo", "(Lio/ktor/utils/io/core/Buffer;Ljava/nio/ByteBuffer;JJJ)J", "readBytes", "", "count", "releaseAll", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "remainingAll", "n", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BuffersKt {
    public static /* synthetic */ byte[] readBytes$default(Buffer this_$iv, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = this_$iv.getWritePosition() - this_$iv.getReadPosition();
        }
        return readBytes(this_$iv, i);
    }

    public static final byte[] readBytes(Buffer $this$readBytes, int count) {
        Intrinsics.checkNotNullParameter($this$readBytes, "<this>");
        if (count == 0) {
            return UnsafeKt.EmptyByteArray;
        }
        byte[] result = new byte[count];
        BufferPrimitivesKt.readFully$default($this$readBytes, result, 0, 0, 6, (Object) null);
        return result;
    }

    public static final void releaseAll(ChunkBuffer $this$releaseAll, ObjectPool<ChunkBuffer> pool) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        ChunkBuffer current = $this$releaseAll;
        while (current != null) {
            ChunkBuffer next = current.cleanNext();
            current.release(pool);
            current = next;
        }
    }

    public static final void forEachChunk(ChunkBuffer $this$forEachChunk, Function1<? super ChunkBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$forEachChunk, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer current = $this$forEachChunk;
        while (true) {
            block.invoke(current);
            ChunkBuffer next = current.getNext();
            if (next != null) {
                current = next;
            } else {
                return;
            }
        }
    }

    public static final ChunkBuffer copyAll(ChunkBuffer $this$copyAll) {
        Intrinsics.checkNotNullParameter($this$copyAll, "<this>");
        ChunkBuffer copied = $this$copyAll.duplicate();
        ChunkBuffer next = $this$copyAll.getNext();
        return next == null ? copied : copyAll(next, copied, copied);
    }

    private static final ChunkBuffer copyAll(ChunkBuffer $this$copyAll, ChunkBuffer head, ChunkBuffer prev) {
        while (true) {
            ChunkBuffer copied = $this$copyAll.duplicate();
            prev.setNext(copied);
            ChunkBuffer next = $this$copyAll.getNext();
            if (next == null) {
                return head;
            }
            $this$copyAll = next;
            prev = copied;
        }
    }

    public static final ChunkBuffer findTail(ChunkBuffer $this$findTail) {
        Intrinsics.checkNotNullParameter($this$findTail, "<this>");
        while (true) {
            ChunkBuffer next = $this$findTail.getNext();
            if (next == null) {
                return $this$findTail;
            }
            $this$findTail = next;
        }
    }

    public static final long remainingAll(ChunkBuffer $this$remainingAll) {
        Intrinsics.checkNotNullParameter($this$remainingAll, "<this>");
        return remainingAll($this$remainingAll, 0L);
    }

    private static final long remainingAll(ChunkBuffer $this$remainingAll, long n) {
        while (true) {
            Buffer this_$iv = $this$remainingAll;
            long rem = (this_$iv.getWritePosition() - this_$iv.getReadPosition()) + n;
            ChunkBuffer next = $this$remainingAll.getNext();
            if (next == null) {
                return rem;
            }
            $this$remainingAll = next;
            n = rem;
        }
    }

    public static final boolean isEmpty(ChunkBuffer $this$isEmpty) {
        Intrinsics.checkNotNullParameter($this$isEmpty, "<this>");
        while (true) {
            Buffer this_$iv = $this$isEmpty;
            if (this_$iv.getWritePosition() - this_$iv.getReadPosition() > 0) {
                return false;
            }
            ChunkBuffer next = $this$isEmpty.getNext();
            if (next == null) {
                return true;
            }
            $this$isEmpty = next;
        }
    }

    public static final int coerceAtMostMaxInt(long $this$coerceAtMostMaxInt) {
        return (int) Math.min($this$coerceAtMostMaxInt, 2147483647L);
    }

    public static final int coerceAtMostMaxIntOrFail(long $this$coerceAtMostMaxIntOrFail, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if ($this$coerceAtMostMaxIntOrFail > 2147483647L) {
            throw new IllegalArgumentException(message);
        }
        return (int) $this$coerceAtMostMaxIntOrFail;
    }

    /* renamed from: peekTo-yRinSxo, reason: not valid java name */
    public static final long m413peekToyRinSxo(Buffer peekTo, ByteBuffer destination, long destinationOffset, long offset, long max) {
        Intrinsics.checkNotNullParameter(peekTo, "$this$peekTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        long size = Math.min(destination.limit() - destinationOffset, Math.min(max, peekTo.getWritePosition() - peekTo.getReadPosition()));
        Memory.m237copyToJT6ljtQ(peekTo.getMemory(), destination, peekTo.getReadPosition() + offset, size, destinationOffset);
        return size;
    }
}
