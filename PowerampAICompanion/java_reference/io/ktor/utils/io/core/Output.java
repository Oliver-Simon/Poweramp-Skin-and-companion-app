package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.NumbersKt;
import io.ktor.utils.io.core.internal.UTF8Kt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.Closeable;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: Output.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b&\u0018\u00002\u00060\u0001j\u0002`\u00022\u00060\u0003j\u0002`\u0004B\u0007\b\u0016¢\u0006\u0002\u0010\u0005B\u0013\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\r\u0010'\u001a\u00020(H\u0000¢\u0006\u0002\b)J\b\u0010*\u001a\u00020(H\u0001J\u0010\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-H\u0016J\"\u0010+\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\f2\u0006\u00101\u001a\u00020\fJ\u0012\u0010+\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u000102H\u0016J\"\u0010+\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u00020\f2\u0006\u00104\u001a\u00020\fH\u0016J\u0015\u00105\u001a\u00020(2\u0006\u0010\u0011\u001a\u00020\bH\u0000¢\u0006\u0002\b6J \u00107\u001a\u00020(2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u00108\u001a\u00020\b2\u0006\u00109\u001a\u00020\fH\u0002J\u0010\u0010:\u001a\u00020(2\u0006\u0010;\u001a\u00020-H\u0002J\b\u0010<\u001a\u00020\bH\u0002J\u0015\u0010=\u001a\u00020(2\u0006\u0010>\u001a\u00020\bH\u0000¢\u0006\u0002\b?J\u0006\u0010@\u001a\u00020(J\b\u0010A\u001a\u00020(H$J\u0006\u0010B\u001a\u00020(J-\u0010B\u001a\u00020(2\u0006\u0010C\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020\f2\u0006\u0010E\u001a\u00020\fH$ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\b\u0010H\u001a\u00020(H\u0002J\u0015\u0010I\u001a\u00020(2\u0006\u0010>\u001a\u00020\bH\u0010¢\u0006\u0002\bJJ\u0010\u0010K\u001a\u00020\b2\u0006\u0010L\u001a\u00020\fH\u0001J\u0006\u0010M\u001a\u00020(J\u000f\u0010N\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0002\bOJ(\u0010P\u001a\u00020\f2\u0006\u0010Q\u001a\u00020\f2\u0012\u0010R\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\f0SH\u0081\bø\u0001\u0003J\u000e\u0010U\u001a\u00020(2\u0006\u0010V\u001a\u00020WJ\u0010\u0010X\u001a\u00020(2\u0006\u0010V\u001a\u00020WH\u0002J\u0015\u0010Y\u001a\u00020(2\u0006\u0010Z\u001a\u00020\bH\u0000¢\u0006\u0002\b[J\u000e\u0010\\\u001a\u00020(2\u0006\u0010]\u001a\u00020^J\u0016\u0010\\\u001a\u00020(2\u0006\u0010_\u001a\u00020^2\u0006\u0010L\u001a\u00020\fJ\u0016\u0010\\\u001a\u00020(2\u0006\u0010_\u001a\u00020^2\u0006\u0010L\u001a\u00020`J&\u0010a\u001a\u00020(2\u0006\u0010b\u001a\u00020\b2\u0006\u0010c\u001a\u00020\b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u0018\u0010d\u001a\u00020(2\u0006\u0010c\u001a\u00020\b2\u0006\u0010b\u001a\u00020\bH\u0002R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R%\u0010\u001b\u001a\u00020\u001cX\u0080\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\"\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0019R\u0015\u0010%\u001a\u00020\f8À\u0002X\u0080\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u000e\u0082\u0002\u0016\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0005\b\u009920\u0001¨\u0006e"}, d2 = {"Lio/ktor/utils/io/core/Output;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "()V", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "(Lio/ktor/utils/io/pool/ObjectPool;)V", "_head", "_size", "", "get_size", "()I", "_tail", "chainedSize", "head", "getHead$ktor_io", "()Lio/ktor/utils/io/core/internal/ChunkBuffer;", "getPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "tailEndExclusive", "getTailEndExclusive$ktor_io", "setTailEndExclusive$ktor_io", "(I)V", "tailInitialPosition", "tailMemory", "Lio/ktor/utils/io/bits/Memory;", "getTailMemory-SK3TCg8$ktor_io", "()Ljava/nio/ByteBuffer;", "setTailMemory-3GNKZMM$ktor_io", "(Ljava/nio/ByteBuffer;)V", "Ljava/nio/ByteBuffer;", "tailPosition", "getTailPosition$ktor_io", "setTailPosition$ktor_io", "tailRemaining", "getTailRemaining$ktor_io", "afterBytesStolen", "", "afterBytesStolen$ktor_io", "afterHeadWrite", "append", "value", "", "csq", "", "start", "end", "", "startIndex", "endIndex", "appendChain", "appendChain$ktor_io", "appendChainImpl", "newTail", "chainedSizeDelta", "appendCharFallback", "c", "appendNewChunk", "appendSingleChunk", "buffer", "appendSingleChunk$ktor_io", "close", "closeDestination", "flush", "source", TypedValues.CycleType.S_WAVE_OFFSET, "length", "flush-62zg_DM", "(Ljava/nio/ByteBuffer;II)V", "flushChain", "last", "last$ktor_io", "prepareWriteHead", "n", "release", "stealAll", "stealAll$ktor_io", "write", ContentDisposition.Parameters.Size, "block", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Buffer;", "writeByte", "v", "", "writeByteFallback", "writeChunkBuffer", "chunkBuffer", "writeChunkBuffer$ktor_io", "writePacket", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "p", "", "writePacketMerging", "tail", "foreignStolen", "writePacketSlowPrepend", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public abstract class Output implements Appendable, Closeable {
    private ChunkBuffer _head;
    private ChunkBuffer _tail;
    private int chainedSize;
    private final ObjectPool<ChunkBuffer> pool;
    private int tailEndExclusive;
    private int tailInitialPosition;
    private ByteBuffer tailMemory;
    private int tailPosition;

    protected abstract void closeDestination();

    /* renamed from: flush-62zg_DM */
    protected abstract void mo414flush62zg_DM(ByteBuffer source, int offset, int length);

    public Output(ObjectPool<ChunkBuffer> pool) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        this.pool = pool;
        this.tailMemory = Memory.INSTANCE.m251getEmptySK3TCg8();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ObjectPool<ChunkBuffer> getPool() {
        return this.pool;
    }

    public Output() {
        this(ChunkBuffer.INSTANCE.getPool());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int get_size() {
        return this.chainedSize + (this.tailPosition - this.tailInitialPosition);
    }

    public final ChunkBuffer getHead$ktor_io() {
        ChunkBuffer chunkBuffer = this._head;
        return chunkBuffer == null ? ChunkBuffer.INSTANCE.getEmpty() : chunkBuffer;
    }

    /* renamed from: getTailMemory-SK3TCg8$ktor_io, reason: not valid java name and from getter */
    public final ByteBuffer getTailMemory() {
        return this.tailMemory;
    }

    /* renamed from: setTailMemory-3GNKZMM$ktor_io, reason: not valid java name */
    public final void m450setTailMemory3GNKZMM$ktor_io(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<set-?>");
        this.tailMemory = byteBuffer;
    }

    /* renamed from: getTailPosition$ktor_io, reason: from getter */
    public final int getTailPosition() {
        return this.tailPosition;
    }

    public final void setTailPosition$ktor_io(int i) {
        this.tailPosition = i;
    }

    /* renamed from: getTailEndExclusive$ktor_io, reason: from getter */
    public final int getTailEndExclusive() {
        return this.tailEndExclusive;
    }

    public final void setTailEndExclusive$ktor_io(int i) {
        this.tailEndExclusive = i;
    }

    public final int getTailRemaining$ktor_io() {
        return getTailEndExclusive() - getTailPosition();
    }

    public final void flush() {
        flushChain();
    }

    private final void flushChain() {
        ChunkBuffer oldTail = stealAll$ktor_io();
        if (oldTail == null) {
            return;
        }
        ChunkBuffer current$iv = oldTail;
        while (true) {
            Buffer chunk = current$iv;
            try {
                Buffer this_$iv = chunk;
                mo414flush62zg_DM(chunk.getMemory(), chunk.getReadPosition(), this_$iv.getWritePosition() - this_$iv.getReadPosition());
                ChunkBuffer next = current$iv.getNext();
                if (next == null) {
                    return;
                } else {
                    current$iv = next;
                }
            } finally {
                BuffersKt.releaseAll(oldTail, this.pool);
            }
        }
    }

    public final ChunkBuffer stealAll$ktor_io() {
        ChunkBuffer head = this._head;
        if (head == null) {
            return null;
        }
        ChunkBuffer chunkBuffer = this._tail;
        if (chunkBuffer != null) {
            chunkBuffer.commitWrittenUntilIndex(this.tailPosition);
        }
        this._head = null;
        this._tail = null;
        this.tailPosition = 0;
        this.tailEndExclusive = 0;
        this.tailInitialPosition = 0;
        this.chainedSize = 0;
        this.tailMemory = Memory.INSTANCE.m251getEmptySK3TCg8();
        return head;
    }

    public final void appendSingleChunk$ktor_io(ChunkBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (!(buffer.getNext() == null)) {
            throw new IllegalStateException("It should be a single buffer chunk.".toString());
        }
        appendChainImpl(buffer, buffer, 0);
    }

    public final void appendChain$ktor_io(ChunkBuffer head) {
        Intrinsics.checkNotNullParameter(head, "head");
        ChunkBuffer tail = BuffersKt.findTail(head);
        ChunkBuffer this_$iv = tail;
        long $this$toIntOrFail$iv = BuffersKt.remainingAll(head) - (this_$iv.getWritePosition() - this_$iv.getReadPosition());
        if ($this$toIntOrFail$iv >= 2147483647L) {
            NumbersKt.failLongToIntConversion($this$toIntOrFail$iv, "total size increase");
            throw new KotlinNothingValueException();
        }
        int chainedSizeDelta = (int) $this$toIntOrFail$iv;
        appendChainImpl(head, tail, chainedSizeDelta);
    }

    private final ChunkBuffer appendNewChunk() {
        ChunkBuffer borrow = this.pool.borrow();
        borrow.reserveEndGap(8);
        appendSingleChunk$ktor_io(borrow);
        return borrow;
    }

    private final void appendChainImpl(ChunkBuffer head, ChunkBuffer newTail, int chainedSizeDelta) {
        ChunkBuffer _tail = this._tail;
        if (_tail == null) {
            this._head = head;
            this.chainedSize = 0;
        } else {
            _tail.setNext(head);
            int tailPosition = this.tailPosition;
            _tail.commitWrittenUntilIndex(tailPosition);
            this.chainedSize += tailPosition - this.tailInitialPosition;
        }
        this._tail = newTail;
        this.chainedSize += chainedSizeDelta;
        this.tailMemory = newTail.getMemory();
        this.tailPosition = newTail.getWritePosition();
        this.tailInitialPosition = newTail.getReadPosition();
        this.tailEndExclusive = newTail.getLimit();
    }

    public final void writeByte(byte v) {
        int index = this.tailPosition;
        if (index < this.tailEndExclusive) {
            this.tailPosition = index + 1;
            ByteBuffer $this$set_u2d62zg_DM$iv = this.tailMemory;
            $this$set_u2d62zg_DM$iv.put(index, v);
            return;
        }
        writeByteFallback(v);
    }

    private final void writeByteFallback(byte v) {
        appendNewChunk().writeByte(v);
        this.tailPosition++;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            flush();
        } finally {
            closeDestination();
        }
    }

    @Override // java.lang.Appendable
    public Output append(char value) {
        int tailPosition = this.tailPosition;
        byte value$iv$iv = 3;
        if (this.tailEndExclusive - tailPosition < 3) {
            appendCharFallback(value);
            return this;
        }
        ByteBuffer $this$putUtf8Char_u2d62zg_DM$iv = this.tailMemory;
        if (value >= 0 && value < 128) {
            byte value$iv$iv2 = (byte) value;
            $this$putUtf8Char_u2d62zg_DM$iv.put(tailPosition, value$iv$iv2);
            value$iv$iv = 1;
        } else {
            if (128 <= value && value < 2048) {
                byte value$iv$iv3 = (byte) (((value >> 6) & 31) | 192);
                $this$putUtf8Char_u2d62zg_DM$iv.put(tailPosition, value$iv$iv3);
                int index$iv$iv = tailPosition + 1;
                byte value$iv$iv4 = (byte) ((value & '?') | 128);
                $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv, value$iv$iv4);
                value$iv$iv = 2;
            } else {
                if (2048 <= value && value < 0) {
                    byte value$iv$iv5 = (byte) (((value >> '\f') & 15) | 224);
                    $this$putUtf8Char_u2d62zg_DM$iv.put(tailPosition, value$iv$iv5);
                    int index$iv$iv2 = tailPosition + 1;
                    byte value$iv$iv6 = (byte) (((value >> 6) & 63) | 128);
                    $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv2, value$iv$iv6);
                    int index$iv$iv3 = tailPosition + 2;
                    byte value$iv$iv7 = (byte) (128 | (value & '?'));
                    $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv3, value$iv$iv7);
                } else {
                    if (!(0 <= value && value < 0)) {
                        UTF8Kt.malformedCodePoint(value);
                        throw new KotlinNothingValueException();
                    }
                    byte value$iv$iv8 = (byte) (((value >> 18) & 7) | 240);
                    $this$putUtf8Char_u2d62zg_DM$iv.put(tailPosition, value$iv$iv8);
                    int index$iv$iv4 = tailPosition + 1;
                    byte value$iv$iv9 = (byte) (((value >> '\f') & 63) | 128);
                    $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv4, value$iv$iv9);
                    int index$iv$iv5 = tailPosition + 2;
                    byte value$iv$iv10 = (byte) (((value >> 6) & 63) | 128);
                    $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv5, value$iv$iv10);
                    int index$iv$iv6 = tailPosition + 3;
                    byte value$iv$iv11 = (byte) ((value & '?') | 128);
                    $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv6, value$iv$iv11);
                    value$iv$iv = 4;
                }
            }
        }
        this.tailPosition = tailPosition + value$iv$iv;
        return this;
    }

    private final void appendCharFallback(char c) {
        byte value$iv$iv;
        Buffer buffer$iv = prepareWriteHead(3);
        try {
            Buffer buffer = buffer$iv;
            ByteBuffer $this$putUtf8Char_u2d62zg_DM$iv = buffer.getMemory();
            int offset$iv = buffer.getWritePosition();
            if (c >= 0 && c < 128) {
                byte value$iv$iv2 = (byte) c;
                $this$putUtf8Char_u2d62zg_DM$iv.put(offset$iv, value$iv$iv2);
                value$iv$iv = 1;
            } else {
                if (128 <= c && c < 2048) {
                    byte value$iv$iv3 = (byte) (((c >> 6) & 31) | 192);
                    $this$putUtf8Char_u2d62zg_DM$iv.put(offset$iv, value$iv$iv3);
                    int index$iv$iv = offset$iv + 1;
                    byte value$iv$iv4 = (byte) (128 | (c & '?'));
                    $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv, value$iv$iv4);
                    value$iv$iv = 2;
                } else {
                    if (2048 <= c && c < 0) {
                        byte value$iv$iv5 = (byte) (((c >> '\f') & 15) | 224);
                        $this$putUtf8Char_u2d62zg_DM$iv.put(offset$iv, value$iv$iv5);
                        int index$iv$iv2 = offset$iv + 1;
                        byte value$iv$iv6 = (byte) (((c >> 6) & 63) | 128);
                        $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv2, value$iv$iv6);
                        int index$iv$iv3 = offset$iv + 2;
                        byte value$iv$iv7 = (byte) (128 | (c & '?'));
                        $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv3, value$iv$iv7);
                        value$iv$iv = 3;
                    } else {
                        if (!(0 <= c && c < 0)) {
                            UTF8Kt.malformedCodePoint(c);
                            throw new KotlinNothingValueException();
                        }
                        byte value$iv$iv8 = (byte) (((c >> 18) & 7) | 240);
                        $this$putUtf8Char_u2d62zg_DM$iv.put(offset$iv, value$iv$iv8);
                        int index$iv$iv4 = offset$iv + 1;
                        byte value$iv$iv9 = (byte) (((c >> '\f') & 63) | 128);
                        $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv4, value$iv$iv9);
                        int index$iv$iv5 = offset$iv + 2;
                        byte value$iv$iv10 = (byte) (((c >> 6) & 63) | 128);
                        $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv5, value$iv$iv10);
                        int index$iv$iv6 = offset$iv + 3;
                        byte value$iv$iv11 = (byte) (128 | (c & '?'));
                        $this$putUtf8Char_u2d62zg_DM$iv.put(index$iv$iv6, value$iv$iv11);
                        value$iv$iv = 4;
                    }
                }
            }
            buffer.commitWritten(value$iv$iv);
            if (!(value$iv$iv >= 0)) {
                throw new IllegalStateException("The returned value shouldn't be negative".toString());
            }
        } finally {
            afterHeadWrite();
        }
    }

    @Override // java.lang.Appendable
    public Output append(CharSequence value) {
        if (value == null) {
            append(AbstractJsonLexerKt.NULL, 0, 4);
        } else {
            append(value, 0, value.length());
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Output append(CharSequence value, int startIndex, int endIndex) {
        if (value == null) {
            return append(AbstractJsonLexerKt.NULL, startIndex, endIndex);
        }
        StringsKt.writeText(this, value, startIndex, endIndex, Charsets.UTF_8);
        return this;
    }

    public final void writePacket(ByteReadPacket packet) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        ChunkBuffer foreignStolen = packet.stealAll$ktor_io();
        if (foreignStolen == null) {
            packet.release();
            return;
        }
        ChunkBuffer tail = this._tail;
        if (tail == null) {
            appendChain$ktor_io(foreignStolen);
        } else {
            writePacketMerging(tail, foreignStolen, packet.getPool());
        }
    }

    public final void writeChunkBuffer$ktor_io(ChunkBuffer chunkBuffer) {
        Intrinsics.checkNotNullParameter(chunkBuffer, "chunkBuffer");
        ChunkBuffer _tail = this._tail;
        if (_tail == null) {
            appendChain$ktor_io(chunkBuffer);
        } else {
            writePacketMerging(_tail, chunkBuffer, this.pool);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void writePacketMerging(io.ktor.utils.io.core.internal.ChunkBuffer r13, io.ktor.utils.io.core.internal.ChunkBuffer r14, io.ktor.utils.io.pool.ObjectPool<io.ktor.utils.io.core.internal.ChunkBuffer> r15) {
        /*
            r12 = this;
            int r0 = r12.tailPosition
            r13.commitWrittenUntilIndex(r0)
            r0 = r13
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0
            r1 = 0
            int r2 = r0.getWritePosition()
            int r3 = r0.getReadPosition()
            int r2 = r2 - r3
            r0 = r14
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0
            r1 = 0
            int r3 = r0.getWritePosition()
            int r4 = r0.getReadPosition()
            int r3 = r3 - r4
            int r0 = io.ktor.utils.io.core.PacketJVMKt.getPACKET_MAX_COPY_SIZE()
            r1 = -1
            if (r3 >= r0) goto L47
            r4 = r13
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4
            r5 = 0
            int r6 = r4.getCapacity()
            int r7 = r4.getLimit()
            int r6 = r6 - r7
            r4 = r13
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4
            r5 = 0
            int r7 = r4.getLimit()
            int r8 = r4.getWritePosition()
            int r7 = r7 - r8
            int r6 = r6 + r7
            if (r3 > r6) goto L47
            r4 = r3
            goto L48
        L47:
            r4 = r1
        L48:
            if (r2 >= r0) goto L59
            int r5 = r14.getStartGap()
            if (r2 > r5) goto L59
            boolean r5 = io.ktor.utils.io.core.internal.ChunkBufferKt.isExclusivelyOwned(r14)
            if (r5 == 0) goto L59
            r5 = r2
            goto L5a
        L59:
            r5 = r1
        L5a:
            if (r4 != r1) goto L64
            if (r5 != r1) goto L64
            r12.appendChain$ktor_io(r14)
            goto Lcb
        L64:
            if (r5 == r1) goto L95
            if (r4 > r5) goto L69
            goto L95
        L69:
            if (r4 == r1) goto L91
            if (r5 >= r4) goto L6e
            goto L91
        L6e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "prep = "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r5)
            java.lang.String r7 = ", app = "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r4)
            java.lang.String r6 = r6.toString()
            r1.<init>(r6)
            throw r1
        L91:
            r12.writePacketSlowPrepend(r14, r13)
            goto Lcb
        L95:
            r1 = r13
            io.ktor.utils.io.core.Buffer r1 = (io.ktor.utils.io.core.Buffer) r1
            r6 = r14
            io.ktor.utils.io.core.Buffer r6 = (io.ktor.utils.io.core.Buffer) r6
            r7 = r13
            io.ktor.utils.io.core.Buffer r7 = (io.ktor.utils.io.core.Buffer) r7
            r8 = 0
            int r9 = r7.getLimit()
            int r10 = r7.getWritePosition()
            int r9 = r9 - r10
            r7 = r13
            io.ktor.utils.io.core.Buffer r7 = (io.ktor.utils.io.core.Buffer) r7
            r8 = 0
            int r10 = r7.getCapacity()
            int r11 = r7.getLimit()
            int r10 = r10 - r11
            int r9 = r9 + r10
            io.ktor.utils.io.core.BufferAppendKt.writeBufferAppend(r1, r6, r9)
            r12.afterHeadWrite()
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = r14.cleanNext()
            if (r1 == 0) goto Lc8
            r6 = 0
            r12.appendChain$ktor_io(r1)
        Lc8:
            r14.release(r15)
        Lcb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.Output.writePacketMerging(io.ktor.utils.io.core.internal.ChunkBuffer, io.ktor.utils.io.core.internal.ChunkBuffer, io.ktor.utils.io.pool.ObjectPool):void");
    }

    private final void writePacketSlowPrepend(ChunkBuffer foreignStolen, ChunkBuffer tail) {
        BufferAppendKt.writeBufferPrepend(foreignStolen, tail);
        ChunkBuffer _head = this._head;
        if (_head == null) {
            throw new IllegalStateException("head should't be null since it is already handled in the fast-path".toString());
        }
        if (_head == tail) {
            this._head = foreignStolen;
        } else {
            ChunkBuffer pre = _head;
            while (true) {
                ChunkBuffer next = pre.getNext();
                Intrinsics.checkNotNull(next);
                if (next == tail) {
                    break;
                } else {
                    pre = next;
                }
            }
            pre.setNext(foreignStolen);
        }
        tail.release(this.pool);
        this._tail = BuffersKt.findTail(foreignStolen);
    }

    public final void writePacket(ByteReadPacket p, int n) {
        Intrinsics.checkNotNullParameter(p, "p");
        int remaining = n;
        while (remaining > 0) {
            ByteReadPacket this_$iv = p;
            int headRemaining = this_$iv.getHeadEndExclusive() - this_$iv.getHeadPosition();
            if (headRemaining <= remaining) {
                remaining -= headRemaining;
                ChunkBuffer steal$ktor_io = p.steal$ktor_io();
                if (steal$ktor_io == null) {
                    throw new EOFException("Unexpected end of packet");
                }
                appendSingleChunk$ktor_io(steal$ktor_io);
            } else {
                ByteReadPacket $this$read_u24default$iv = p;
                ChunkBuffer buffer$iv = $this$read_u24default$iv.prepareRead(1);
                if (buffer$iv == null) {
                    StringsKt.prematureEndOfStream(1);
                    throw new KotlinNothingValueException();
                }
                int positionBefore$iv = buffer$iv.getReadPosition();
                try {
                    ChunkBuffer view = buffer$iv;
                    OutputKt.writeFully(this, view, remaining);
                    int positionAfter$iv = buffer$iv.getReadPosition();
                    if (positionAfter$iv < positionBefore$iv) {
                        throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                    }
                    if (positionAfter$iv == buffer$iv.getWritePosition()) {
                        $this$read_u24default$iv.ensureNext(buffer$iv);
                        return;
                    } else {
                        $this$read_u24default$iv.setHeadPosition(positionAfter$iv);
                        return;
                    }
                } catch (Throwable th) {
                    int positionAfter$iv2 = buffer$iv.getReadPosition();
                    if (positionAfter$iv2 < positionBefore$iv) {
                        throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                    }
                    if (positionAfter$iv2 == buffer$iv.getWritePosition()) {
                        $this$read_u24default$iv.ensureNext(buffer$iv);
                    } else {
                        $this$read_u24default$iv.setHeadPosition(positionAfter$iv2);
                    }
                    throw th;
                }
            }
        }
    }

    public final void writePacket(ByteReadPacket p, long n) {
        Intrinsics.checkNotNullParameter(p, "p");
        long remaining = n;
        while (remaining > 0) {
            ByteReadPacket this_$iv = p;
            long headRemaining = this_$iv.getHeadEndExclusive() - this_$iv.getHeadPosition();
            if (headRemaining <= remaining) {
                remaining -= headRemaining;
                ChunkBuffer steal$ktor_io = p.steal$ktor_io();
                if (steal$ktor_io == null) {
                    throw new EOFException("Unexpected end of packet");
                }
                appendSingleChunk$ktor_io(steal$ktor_io);
            } else {
                ByteReadPacket $this$read_u24default$iv = p;
                ChunkBuffer buffer$iv = $this$read_u24default$iv.prepareRead(1);
                if (buffer$iv == null) {
                    StringsKt.prematureEndOfStream(1);
                    throw new KotlinNothingValueException();
                }
                int positionBefore$iv = buffer$iv.getReadPosition();
                try {
                    ChunkBuffer view = buffer$iv;
                    OutputKt.writeFully(this, view, (int) remaining);
                    int positionAfter$iv = buffer$iv.getReadPosition();
                    if (positionAfter$iv < positionBefore$iv) {
                        throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                    }
                    if (positionAfter$iv == buffer$iv.getWritePosition()) {
                        $this$read_u24default$iv.ensureNext(buffer$iv);
                        return;
                    } else {
                        $this$read_u24default$iv.setHeadPosition(positionAfter$iv);
                        return;
                    }
                } catch (Throwable th) {
                    int positionAfter$iv2 = buffer$iv.getReadPosition();
                    if (positionAfter$iv2 < positionBefore$iv) {
                        throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                    }
                    if (positionAfter$iv2 == buffer$iv.getWritePosition()) {
                        $this$read_u24default$iv.ensureNext(buffer$iv);
                    } else {
                        $this$read_u24default$iv.setHeadPosition(positionAfter$iv2);
                    }
                    throw th;
                }
            }
        }
    }

    public final Appendable append(char[] csq, int start, int end) {
        Intrinsics.checkNotNullParameter(csq, "csq");
        StringsKt.writeText(this, csq, start, end, Charsets.UTF_8);
        return this;
    }

    public final void release() {
        close();
    }

    public final ChunkBuffer prepareWriteHead(int n) {
        ChunkBuffer it;
        if (getTailEndExclusive() - getTailPosition() >= n && (it = this._tail) != null) {
            it.commitWrittenUntilIndex(this.tailPosition);
            return it;
        }
        return appendNewChunk();
    }

    public final void afterHeadWrite() {
        ChunkBuffer it = this._tail;
        if (it != null) {
            this.tailPosition = it.getWritePosition();
        }
    }

    public final int write(int size, Function1<? super Buffer, Integer> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer buffer = prepareWriteHead(size);
        try {
            int result = block.invoke(buffer).intValue();
            if (!(result >= 0)) {
                throw new IllegalStateException("The returned value shouldn't be negative".toString());
            }
            return result;
        } finally {
            afterHeadWrite();
        }
    }

    public void last$ktor_io(ChunkBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        appendSingleChunk$ktor_io(buffer);
    }

    public final void afterBytesStolen$ktor_io() {
        ChunkBuffer head = getHead$ktor_io();
        if (head != ChunkBuffer.INSTANCE.getEmpty()) {
            if (!(head.getNext() == null)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            head.resetForWrite();
            head.reserveEndGap(8);
            this.tailPosition = head.getWritePosition();
            this.tailInitialPosition = this.tailPosition;
            this.tailEndExclusive = head.getLimit();
        }
    }
}
