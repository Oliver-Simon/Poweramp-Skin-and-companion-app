package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Buffer.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0005\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0016\u0018\u0000 <2\u00020\u0001:\u0001<B\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006H\u0001J\u0010\u0010!\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u0006J\u0015\u0010\"\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u0006H\u0000¢\u0006\u0002\b#J\b\u0010$\u001a\u00020\u0000H\u0016J\u0010\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u0000H\u0014J\u0006\u0010'\u001a\u00020(J\r\u0010)\u001a\u00020\u001cH\u0000¢\u0006\u0002\b*J\r\u0010+\u001a\u00020\u001cH\u0000¢\u0006\u0002\b,J\u0015\u0010-\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020\u0006H\u0000¢\u0006\u0002\b/J\u000e\u00100\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\u0006J\u000e\u00101\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u0006J\b\u00102\u001a\u00020\u001cH\u0016J\u0006\u00103\u001a\u00020\u001cJ\u0006\u00104\u001a\u00020\u001cJ\u000e\u00104\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\u0006J\u0010\u00105\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u0006J\b\u00106\u001a\u000207H\u0016J\u0006\u00108\u001a\u00020\u0006J\u0006\u00109\u001a\u00020\u0006J\u000e\u0010:\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020(R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\u00068Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u001e\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u001c\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0012\u0010\u0013\u001a\u00020\u00068Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\bR\u001e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u001e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0012\u0010\u0019\u001a\u00020\u00068Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001a\u0010\b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006="}, d2 = {"Lio/ktor/utils/io/core/Buffer;", "", "memory", "Lio/ktor/utils/io/bits/Memory;", "(Ljava/nio/ByteBuffer;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "capacity", "", "getCapacity", "()I", "endGap", "getEndGap", "<set-?>", "limit", "getLimit", "getMemory-SK3TCg8", "()Ljava/nio/ByteBuffer;", "Ljava/nio/ByteBuffer;", "readPosition", "getReadPosition", "readRemaining", "getReadRemaining", "startGap", "getStartGap", "writePosition", "getWritePosition", "writeRemaining", "getWriteRemaining", "commitWritten", "", "count", "commitWrittenUntilIndex", "", "position", "discardExact", "discardUntilIndex", "discardUntilIndex$ktor_io", "duplicate", "duplicateTo", "copy", "readByte", "", "releaseEndGap", "releaseEndGap$ktor_io", "releaseGaps", "releaseGaps$ktor_io", "releaseStartGap", "newReadPosition", "releaseStartGap$ktor_io", "reserveEndGap", "reserveStartGap", "reset", "resetForRead", "resetForWrite", "rewind", "toString", "", "tryPeekByte", "tryReadByte", "writeByte", "value", "Companion", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public class Buffer {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int ReservedSize = 8;
    private final int capacity;
    private int limit;
    private final ByteBuffer memory;
    private int readPosition;
    private int startGap;
    private int writePosition;

    public /* synthetic */ Buffer(ByteBuffer byteBuffer, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteBuffer);
    }

    private Buffer(ByteBuffer memory) {
        Intrinsics.checkNotNullParameter(memory, "memory");
        this.memory = memory;
        ByteBuffer arg0$iv = this.memory;
        this.limit = arg0$iv.limit();
        ByteBuffer arg0$iv2 = this.memory;
        this.capacity = arg0$iv2.limit();
    }

    /* renamed from: getMemory-SK3TCg8, reason: not valid java name and from getter */
    public final ByteBuffer getMemory() {
        return this.memory;
    }

    public final int getReadPosition() {
        return this.readPosition;
    }

    public final int getWritePosition() {
        return this.writePosition;
    }

    public final int getStartGap() {
        return this.startGap;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final int getEndGap() {
        return getCapacity() - getLimit();
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public final int getReadRemaining() {
        return getWritePosition() - getReadPosition();
    }

    public final int getWriteRemaining() {
        return getLimit() - getWritePosition();
    }

    public static /* synthetic */ void discardExact$default(Buffer this_$iv, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: discardExact");
        }
        if ((i2 & 1) != 0) {
            i = this_$iv.getWritePosition() - this_$iv.getReadPosition();
        }
        this_$iv.discardExact(i);
    }

    public final void discardExact(int count) {
        if (count == 0) {
            return;
        }
        int newReadPosition = this.readPosition + count;
        if (count < 0 || newReadPosition > this.writePosition) {
            BufferKt.discardFailed(count, getWritePosition() - getReadPosition());
            throw new KotlinNothingValueException();
        }
        this.readPosition = newReadPosition;
    }

    public final void commitWritten(int count) {
        int newWritePosition = this.writePosition + count;
        if (count < 0 || newWritePosition > this.limit) {
            BufferKt.commitWrittenFailed(count, getLimit() - getWritePosition());
            throw new KotlinNothingValueException();
        }
        this.writePosition = newWritePosition;
    }

    public final boolean commitWrittenUntilIndex(int position) {
        int limit = this.limit;
        if (position < this.writePosition) {
            BufferKt.commitWrittenFailed(position - this.writePosition, getLimit() - getWritePosition());
            throw new KotlinNothingValueException();
        }
        if (position >= limit) {
            if (position == limit) {
                this.writePosition = position;
                return false;
            }
            BufferKt.commitWrittenFailed(position - this.writePosition, getLimit() - getWritePosition());
            throw new KotlinNothingValueException();
        }
        this.writePosition = position;
        return true;
    }

    public final void discardUntilIndex$ktor_io(int position) {
        if (position < 0 || position > this.writePosition) {
            BufferKt.discardFailed(position - this.readPosition, getWritePosition() - getReadPosition());
            throw new KotlinNothingValueException();
        }
        if (this.readPosition != position) {
            this.readPosition = position;
        }
    }

    public static /* synthetic */ void rewind$default(Buffer buffer, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: rewind");
        }
        if ((i2 & 1) != 0) {
            i = buffer.readPosition - buffer.startGap;
        }
        buffer.rewind(i);
    }

    public final void rewind(int count) {
        int newReadPosition = this.readPosition - count;
        if (newReadPosition < this.startGap) {
            BufferKt.rewindFailed(count, this.readPosition - this.startGap);
            throw new KotlinNothingValueException();
        }
        this.readPosition = newReadPosition;
    }

    public final void reserveStartGap(int startGap) {
        if (!(startGap >= 0)) {
            throw new IllegalArgumentException(("startGap shouldn't be negative: " + startGap).toString());
        }
        if (this.readPosition >= startGap) {
            this.startGap = startGap;
            return;
        }
        if (this.readPosition == this.writePosition) {
            if (startGap > this.limit) {
                BufferKt.startGapReservationFailedDueToLimit(this, startGap);
                throw new KotlinNothingValueException();
            }
            this.writePosition = startGap;
            this.readPosition = startGap;
            this.startGap = startGap;
            return;
        }
        BufferKt.startGapReservationFailed(this, startGap);
        throw new KotlinNothingValueException();
    }

    public final void reserveEndGap(int endGap) {
        if (!(endGap >= 0)) {
            throw new IllegalArgumentException(("endGap shouldn't be negative: " + endGap).toString());
        }
        int newLimit = this.capacity - endGap;
        if (newLimit >= this.writePosition) {
            this.limit = newLimit;
            return;
        }
        if (newLimit < 0) {
            BufferKt.endGapReservationFailedDueToCapacity(this, endGap);
        }
        if (newLimit < this.startGap) {
            BufferKt.endGapReservationFailedDueToStartGap(this, endGap);
        }
        if (this.readPosition == this.writePosition) {
            this.limit = newLimit;
            this.readPosition = newLimit;
            this.writePosition = newLimit;
            return;
        }
        BufferKt.endGapReservationFailedDueToContent(this, endGap);
    }

    public final void resetForRead() {
        this.startGap = 0;
        this.readPosition = 0;
        int capacity = this.capacity;
        this.writePosition = capacity;
    }

    public final void resetForWrite() {
        resetForWrite(this.capacity - this.startGap);
    }

    public final void resetForWrite(int limit) {
        int startGap = this.startGap;
        this.readPosition = startGap;
        this.writePosition = startGap;
        this.limit = limit;
    }

    public final void releaseGaps$ktor_io() {
        releaseStartGap$ktor_io(0);
        releaseEndGap$ktor_io();
    }

    public final void releaseEndGap$ktor_io() {
        this.limit = this.capacity;
    }

    public final void releaseStartGap$ktor_io(int newReadPosition) {
        if (!(newReadPosition >= 0)) {
            throw new IllegalArgumentException(("newReadPosition shouldn't be negative: " + newReadPosition).toString());
        }
        if (!(newReadPosition <= this.readPosition)) {
            throw new IllegalArgumentException(("newReadPosition shouldn't be ahead of the read position: " + newReadPosition + " > " + this.readPosition).toString());
        }
        this.readPosition = newReadPosition;
        if (this.startGap > newReadPosition) {
            this.startGap = newReadPosition;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void duplicateTo(Buffer copy) {
        Intrinsics.checkNotNullParameter(copy, "copy");
        copy.limit = this.limit;
        copy.startGap = this.startGap;
        copy.readPosition = this.readPosition;
        copy.writePosition = this.writePosition;
    }

    public Buffer duplicate() {
        Buffer $this$duplicate_u24lambda_u244 = new Buffer(this.memory, null);
        $this$duplicate_u24lambda_u244.duplicateTo($this$duplicate_u24lambda_u244);
        return $this$duplicate_u24lambda_u244;
    }

    public final int tryPeekByte() {
        int readPosition = this.readPosition;
        if (readPosition == this.writePosition) {
            return -1;
        }
        ByteBuffer $this$get_u2deY85DW0$iv = this.memory;
        return $this$get_u2deY85DW0$iv.get(readPosition) & 255;
    }

    public final int tryReadByte() {
        int readPosition = this.readPosition;
        if (readPosition == this.writePosition) {
            return -1;
        }
        this.readPosition = readPosition + 1;
        ByteBuffer $this$get_u2deY85DW0$iv = this.memory;
        return $this$get_u2deY85DW0$iv.get(readPosition) & 255;
    }

    public final byte readByte() {
        int readPosition = this.readPosition;
        if (readPosition == this.writePosition) {
            throw new EOFException("No readable bytes available.");
        }
        this.readPosition = readPosition + 1;
        ByteBuffer $this$get_u2deY85DW0$iv = this.memory;
        return $this$get_u2deY85DW0$iv.get(readPosition);
    }

    public final void writeByte(byte value) {
        int writePosition = this.writePosition;
        if (writePosition == this.limit) {
            throw new InsufficientSpaceException("No free space in the buffer to write a byte");
        }
        ByteBuffer $this$set_u2d62zg_DM$iv = this.memory;
        $this$set_u2d62zg_DM$iv.put(writePosition, value);
        this.writePosition = writePosition + 1;
    }

    public void reset() {
        releaseGaps$ktor_io();
        resetForWrite();
    }

    public String toString() {
        return "Buffer(" + (getWritePosition() - getReadPosition()) + " used, " + (getLimit() - getWritePosition()) + " free, " + (this.startGap + (getCapacity() - getLimit())) + " reserved of " + this.capacity + ')';
    }

    /* compiled from: Buffer.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/ktor/utils/io/core/Buffer$Companion;", "", "()V", "Empty", "Lio/ktor/utils/io/core/Buffer;", "getEmpty", "()Lio/ktor/utils/io/core/Buffer;", "ReservedSize", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Buffer getEmpty() {
            return ChunkBuffer.INSTANCE.getEmpty();
        }
    }
}
