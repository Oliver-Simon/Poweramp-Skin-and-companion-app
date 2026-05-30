package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.MalformedUTF8InputException;
import io.ktor.utils.io.core.internal.UnsafeKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.Closeable;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.DebugKt;

/* compiled from: Input.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b&\u0018\u0000 \u008e\u00012\u00060\u0001j\u0002`\u0002:\u0002\u008e\u0001B)\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\u0010\tJ\u0010\u00107\u001a\u0002082\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0015\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020\u0004H\u0000¢\u0006\u0002\b;J\u0010\u0010<\u001a\u0002082\u0006\u0010=\u001a\u00020\u0004H\u0002J\u0010\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020\u0017H\u0002J\u0006\u0010A\u001a\u00020\u000fJ\b\u0010B\u001a\u000208H\u0016J\b\u0010C\u001a\u000208H$J\u000e\u0010D\u001a\u00020\u00172\u0006\u0010E\u001a\u00020\u0017J\u000e\u0010D\u001a\u00020\u00062\u0006\u0010E\u001a\u00020\u0006J\u0018\u0010F\u001a\u00020\u00172\u0006\u0010E\u001a\u00020\u00172\u0006\u0010G\u001a\u00020\u0017H\u0002J\u0019\u0010F\u001a\u00020\u00062\u0006\u0010E\u001a\u00020\u00062\u0006\u0010G\u001a\u00020\u0006H\u0082\u0010J\u000e\u0010H\u001a\u0002082\u0006\u0010E\u001a\u00020\u0017J\n\u0010I\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010J\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u0006H\u0002J\u0012\u0010K\u001a\u0004\u0018\u00010\u00042\u0006\u0010L\u001a\u00020\u0004H\u0001J\u001b\u0010K\u001a\u0004\u0018\u00010\u00042\u0006\u0010L\u001a\u00020\u00042\u0006\u0010M\u001a\u00020\u0004H\u0082\u0010J\u0017\u0010N\u001a\u0004\u0018\u00010\u00042\u0006\u0010L\u001a\u00020\u0004H\u0000¢\u0006\u0002\bOJ\n\u0010P\u001a\u0004\u0018\u00010\u0004H\u0014J-\u0010P\u001a\u00020\u00172\u0006\u0010Q\u001a\u00020\u001e2\u0006\u0010R\u001a\u00020\u00172\u0006\u0010S\u001a\u00020\u0017H$ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bT\u0010UJ\u0015\u0010V\u001a\u0002082\u0006\u0010L\u001a\u00020\u0004H\u0000¢\u0006\u0002\bWJ\u0010\u0010X\u001a\u0002082\u0006\u0010L\u001a\u00020\u0004H\u0002J \u0010Y\u001a\u0002082\u0006\u0010L\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u00172\u0006\u0010[\u001a\u00020\u0017H\u0002J\u000e\u0010\\\u001a\u00020\u000f2\u0006\u0010E\u001a\u00020\u0017J\b\u0010]\u001a\u000208H\u0004J\u0018\u0010^\u001a\u00020?2\u0006\u0010@\u001a\u00020\u00172\u0006\u0010_\u001a\u00020\u0017H\u0002J\u0010\u0010`\u001a\u00020?2\u0006\u0010a\u001a\u00020\u0017H\u0002J\u0010\u0010b\u001a\u00020?2\u0006\u0010E\u001a\u00020\u0017H\u0002JA\u0010c\u001a\u00020\u00062\u0006\u0010Q\u001a\u00020\u001e2\u0006\u0010d\u001a\u00020\u00062\b\b\u0002\u0010R\u001a\u00020\u00062\b\b\u0002\u0010@\u001a\u00020\u00062\b\b\u0002\u0010_\u001a\u00020\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\be\u0010fJ\u000e\u0010c\u001a\u00020\u00172\u0006\u0010g\u001a\u00020\u0004J\u0015\u0010h\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u0006H\u0000¢\u0006\u0002\biJ\u0018\u0010j\u001a\u00020?2\u0006\u0010@\u001a\u00020\u00172\u0006\u0010k\u001a\u00020\u0017H\u0002J\u0012\u0010l\u001a\u0004\u0018\u00010\u00042\u0006\u0010a\u001a\u00020\u0017H\u0001J\u001a\u0010l\u001a\u0004\u0018\u00010\u00042\u0006\u0010a\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u0004H\u0001J\u0017\u0010m\u001a\u0004\u0018\u00010\u00042\u0006\u0010a\u001a\u00020\u0017H\u0000¢\u0006\u0002\bnJ\u001b\u0010o\u001a\u0004\u0018\u00010\u00042\u0006\u0010a\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\u0010J$\u0010p\u001a\u00020\u00172\n\u0010q\u001a\u00060rj\u0002`s2\u0006\u0010@\u001a\u00020\u00172\u0006\u0010_\u001a\u00020\u0017H\u0002J)\u0010t\u001a\u00020\u00172\u0006\u0010u\u001a\u00020v2\u0006\u0010R\u001a\u00020\u00172\u0006\u0010S\u001a\u00020\u00172\u0006\u0010k\u001a\u00020\u0017H\u0082\u0010J%\u0010w\u001a\u00020\u00172\u0006\u0010Q\u001a\u00020x2\u0006\u0010y\u001a\u00020\u00172\u0006\u0010z\u001a\u00020\u0017H\u0000¢\u0006\u0002\b{J\u0006\u0010|\u001a\u00020}J\b\u0010~\u001a\u00020}H\u0002J\u001b\u0010\u007f\u001a\u00030\u0080\u00012\b\b\u0002\u0010@\u001a\u00020\u00172\b\b\u0002\u0010_\u001a\u00020\u0017J&\u0010\u007f\u001a\u00020\u00172\n\u0010q\u001a\u00060rj\u0002`s2\b\b\u0002\u0010@\u001a\u00020\u00172\b\b\u0002\u0010_\u001a\u00020\u0017J\u0011\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u0007\u0010\u0082\u0001\u001a\u00020\u0017J\u001c\u0010\u0081\u0001\u001a\u0002082\n\u0010q\u001a\u00060rj\u0002`s2\u0007\u0010\u0082\u0001\u001a\u00020\u0017J%\u0010\u0083\u0001\u001a\u00020\u00172\n\u0010q\u001a\u00060rj\u0002`s2\u0006\u0010@\u001a\u00020\u00172\u0006\u0010_\u001a\u00020\u0017H\u0002J\u0007\u0010\u0084\u0001\u001a\u000208J\u0017\u0010\u0085\u0001\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¢\u0006\u0003\b\u0086\u0001J\u0011\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0003\b\u0088\u0001J\u0011\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0003\b\u008a\u0001J\u0007\u0010\u008b\u0001\u001a\u00020\u0017J\u0017\u0010\u008c\u0001\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u0004H\u0000¢\u0006\u0003\b\u008d\u0001R\u001e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0003\u001a\u00020\u00048@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00178\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR/\u0010\u001d\u001a\u00020\u001e8\u0000@\u0000X\u0081\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0016\n\u0002\u0010$\u0012\u0004\b\u001f\u0010\u0013\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010%\u001a\u00020\u00178\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b&\u0010\u0013\u001a\u0004\b'\u0010\u001a\"\u0004\b(\u0010\u001cR\u001b\u0010)\u001a\u00020\u00178À\u0002X\u0081\u0004¢\u0006\f\u0012\u0004\b*\u0010\u0013\u001a\u0004\b+\u0010\u001aR\u000e\u0010,\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b/\u00100R,\u00102\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u00068\u0000@@X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b3\u0010\u0013\u001a\u0004\b4\u00100\"\u0004\b5\u00106\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u008f\u0001"}, d2 = {"Lio/ktor/utils/io/core/Input;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "head", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "remaining", "", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;JLio/ktor/utils/io/pool/ObjectPool;)V", "newHead", "_head", "set_head", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)V", "endOfInput", "", "getEndOfInput", "()Z", "getHead$annotations", "()V", "getHead", "()Lio/ktor/utils/io/core/internal/ChunkBuffer;", "headEndExclusive", "", "getHeadEndExclusive$annotations", "getHeadEndExclusive", "()I", "setHeadEndExclusive", "(I)V", "headMemory", "Lio/ktor/utils/io/bits/Memory;", "getHeadMemory-SK3TCg8$annotations", "getHeadMemory-SK3TCg8", "()Ljava/nio/ByteBuffer;", "setHeadMemory-3GNKZMM", "(Ljava/nio/ByteBuffer;)V", "Ljava/nio/ByteBuffer;", "headPosition", "getHeadPosition$annotations", "getHeadPosition", "setHeadPosition", "headRemaining", "getHeadRemaining$annotations", "getHeadRemaining", "noMoreChunksAvailable", "getPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "getRemaining", "()J", "newValue", "tailRemaining", "getTailRemaining$annotations", "getTailRemaining", "setTailRemaining", "(J)V", "afterRead", "", "append", "chain", "append$ktor_io", "appendView", "chunk", "atLeastMinCharactersRequire", "", "min", "canRead", "close", "closeSource", "discard", "n", "discardAsMuchAsPossible", "skipped", "discardExact", "doFill", "doPrefetch", "ensureNext", "current", "empty", "ensureNextHead", "ensureNextHead$ktor_io", "fill", "destination", TypedValues.CycleType.S_WAVE_OFFSET, "length", "fill-62zg_DM", "(Ljava/nio/ByteBuffer;II)I", "fixGapAfterRead", "fixGapAfterRead$ktor_io", "fixGapAfterReadFallback", "fixGapAfterReadFallbackUnreserved", ContentDisposition.Parameters.Size, "overrun", "hasBytes", "markNoMoreChunksAvailable", "minShouldBeLess", "max", "minSizeIsTooBig", "minSize", "notEnoughBytesAvailable", "peekTo", "destinationOffset", "peekTo-9zorpBc", "(Ljava/nio/ByteBuffer;JJJJ)J", "buffer", LinkHeader.Rel.Prefetch, "prefetch$ktor_io", "prematureEndOfStreamChars", "copied", "prepareRead", "prepareReadHead", "prepareReadHead$ktor_io", "prepareReadLoop", "readASCII", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "readAsMuchAsPossible", "array", "", "readAvailableCharacters", "", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "len", "readAvailableCharacters$ktor_io", "readByte", "", "readByteSlow", "readText", "", "readTextExact", "exactCharacters", "readUtf8", "release", "releaseHead", "releaseHead$ktor_io", "steal", "steal$ktor_io", "stealAll", "stealAll$ktor_io", "tryPeek", "tryWriteAppend", "tryWriteAppend$ktor_io", "Companion", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public abstract class Input implements Closeable {
    private ChunkBuffer _head;
    private int headEndExclusive;
    private ByteBuffer headMemory;
    private int headPosition;
    private boolean noMoreChunksAvailable;
    private final ObjectPool<ChunkBuffer> pool;
    private long tailRemaining;

    public Input() {
        this(null, 0L, null, 7, null);
    }

    public static /* synthetic */ void getHead$annotations() {
    }

    public static /* synthetic */ void getHeadEndExclusive$annotations() {
    }

    /* renamed from: getHeadMemory-SK3TCg8$annotations, reason: not valid java name */
    public static /* synthetic */ void m416getHeadMemorySK3TCg8$annotations() {
    }

    public static /* synthetic */ void getHeadPosition$annotations() {
    }

    public static /* synthetic */ void getHeadRemaining$annotations() {
    }

    public static /* synthetic */ void getTailRemaining$annotations() {
    }

    protected abstract void closeSource();

    /* renamed from: fill-62zg_DM */
    protected abstract int mo415fill62zg_DM(ByteBuffer destination, int offset, int length);

    public Input(ChunkBuffer head, long remaining, ObjectPool<ChunkBuffer> pool) {
        Intrinsics.checkNotNullParameter(head, "head");
        Intrinsics.checkNotNullParameter(pool, "pool");
        this.pool = pool;
        this._head = head;
        this.headMemory = head.getMemory();
        this.headPosition = head.getReadPosition();
        this.headEndExclusive = head.getWritePosition();
        this.tailRemaining = remaining - (this.headEndExclusive - this.headPosition);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ Input(io.ktor.utils.io.core.internal.ChunkBuffer r1, long r2, io.ktor.utils.io.pool.ObjectPool r4, int r5, kotlin.jvm.internal.DefaultConstructorMarker r6) {
        /*
            r0 = this;
            r6 = r5 & 1
            if (r6 == 0) goto La
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r1 = io.ktor.utils.io.core.internal.ChunkBuffer.INSTANCE
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = r1.getEmpty()
        La:
            r6 = r5 & 2
            if (r6 == 0) goto L12
            long r2 = io.ktor.utils.io.core.BuffersKt.remainingAll(r1)
        L12:
            r5 = r5 & 4
            if (r5 == 0) goto L1c
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r4 = io.ktor.utils.io.core.internal.ChunkBuffer.INSTANCE
            io.ktor.utils.io.pool.ObjectPool r4 = r4.getPool()
        L1c:
            r0.<init>(r1, r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.Input.<init>(io.ktor.utils.io.core.internal.ChunkBuffer, long, io.ktor.utils.io.pool.ObjectPool, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final ObjectPool<ChunkBuffer> getPool() {
        return this.pool;
    }

    public final boolean getEndOfInput() {
        return getHeadEndExclusive() - getHeadPosition() == 0 && this.tailRemaining == 0 && (this.noMoreChunksAvailable || doFill() == null);
    }

    private final void set_head(ChunkBuffer newHead) {
        this._head = newHead;
        this.headMemory = newHead.getMemory();
        this.headPosition = newHead.getReadPosition();
        this.headEndExclusive = newHead.getWritePosition();
    }

    public final ChunkBuffer getHead() {
        ChunkBuffer it = this._head;
        it.discardUntilIndex$ktor_io(this.headPosition);
        return it;
    }

    /* renamed from: getHeadMemory-SK3TCg8, reason: not valid java name and from getter */
    public final ByteBuffer getHeadMemory() {
        return this.headMemory;
    }

    /* renamed from: setHeadMemory-3GNKZMM, reason: not valid java name */
    public final void m420setHeadMemory3GNKZMM(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<set-?>");
        this.headMemory = byteBuffer;
    }

    public final int getHeadPosition() {
        return this.headPosition;
    }

    public final void setHeadPosition(int i) {
        this.headPosition = i;
    }

    public final int getHeadEndExclusive() {
        return this.headEndExclusive;
    }

    public final void setHeadEndExclusive(int i) {
        this.headEndExclusive = i;
    }

    public final long getTailRemaining() {
        return this.tailRemaining;
    }

    public final void setTailRemaining(long newValue) {
        if (!(newValue >= 0)) {
            throw new IllegalArgumentException(("tailRemaining shouldn't be negative: " + newValue).toString());
        }
        this.tailRemaining = newValue;
    }

    public final int getHeadRemaining() {
        return getHeadEndExclusive() - getHeadPosition();
    }

    public final boolean prefetch$ktor_io(long min) {
        if (min <= 0) {
            return true;
        }
        int headRemaining = getHeadEndExclusive() - getHeadPosition();
        if (headRemaining >= min || headRemaining + this.tailRemaining >= min) {
            return true;
        }
        return doPrefetch(min);
    }

    /* renamed from: peekTo-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ long m417peekTo9zorpBc$default(Input input, ByteBuffer byteBuffer, long j, long j2, long j3, long j4, int i, Object obj) {
        if (obj == null) {
            return input.m419peekTo9zorpBc(byteBuffer, j, (i & 4) != 0 ? 0L : j2, (i & 8) != 0 ? 1L : j3, (i & 16) != 0 ? Long.MAX_VALUE : j4);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: peekTo-9zorpBc");
    }

    /* renamed from: peekTo-9zorpBc, reason: not valid java name */
    public final long m419peekTo9zorpBc(ByteBuffer destination, long destinationOffset, long offset, long min, long max) {
        ChunkBuffer current;
        ByteBuffer destination2 = destination;
        Intrinsics.checkNotNullParameter(destination2, "destination");
        prefetch$ktor_io(min + offset);
        ChunkBuffer current2 = getHead();
        long writePosition = destinationOffset;
        long maxCopySize = Math.min(max, destination2.limit() - destinationOffset);
        long copied = 0;
        long skip = offset;
        while (copied < min && copied < maxCopySize) {
            Buffer this_$iv = current2;
            int chunkSize = this_$iv.getWritePosition() - this_$iv.getReadPosition();
            if (chunkSize > skip) {
                long size = Math.min(chunkSize - skip, maxCopySize - copied);
                current = current2;
                Memory.m237copyToJT6ljtQ(current2.getMemory(), destination2, current2.getReadPosition() + skip, size, writePosition);
                copied += size;
                writePosition += size;
                skip = 0;
            } else {
                current = current2;
                skip -= chunkSize;
            }
            ChunkBuffer next = current.getNext();
            if (next == null) {
                break;
            }
            current2 = next;
            destination2 = destination;
        }
        return copied;
    }

    private final boolean doPrefetch(long min) {
        ChunkBuffer tail = BuffersKt.findTail(this._head);
        long available = (getHeadEndExclusive() - getHeadPosition()) + this.tailRemaining;
        do {
            ChunkBuffer next = fill();
            if (next == null) {
                this.noMoreChunksAvailable = true;
                return false;
            }
            ChunkBuffer this_$iv = next;
            int chunkSize = this_$iv.getWritePosition() - this_$iv.getReadPosition();
            if (tail == ChunkBuffer.INSTANCE.getEmpty()) {
                set_head(next);
                tail = next;
            } else {
                tail.setNext(next);
                setTailRemaining(this.tailRemaining + chunkSize);
            }
            available += chunkSize;
        } while (available < min);
        return true;
    }

    public final long getRemaining() {
        return (getHeadEndExclusive() - getHeadPosition()) + this.tailRemaining;
    }

    public final boolean canRead() {
        return (this.headPosition == this.headEndExclusive && this.tailRemaining == 0) ? false : true;
    }

    public final boolean hasBytes(int n) {
        return ((long) (getHeadEndExclusive() - getHeadPosition())) + this.tailRemaining >= ((long) n);
    }

    public final void release() {
        ChunkBuffer head = getHead();
        ChunkBuffer empty = ChunkBuffer.INSTANCE.getEmpty();
        if (head != empty) {
            set_head(empty);
            setTailRemaining(0L);
            BuffersKt.releaseAll(head, this.pool);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        release();
        if (!this.noMoreChunksAvailable) {
            this.noMoreChunksAvailable = true;
        }
        closeSource();
    }

    public final ChunkBuffer stealAll$ktor_io() {
        ChunkBuffer head = getHead();
        ChunkBuffer empty = ChunkBuffer.INSTANCE.getEmpty();
        if (head == empty) {
            return null;
        }
        set_head(empty);
        setTailRemaining(0L);
        return head;
    }

    public final ChunkBuffer steal$ktor_io() {
        ChunkBuffer head = getHead();
        ChunkBuffer next = head.getNext();
        ChunkBuffer empty = ChunkBuffer.INSTANCE.getEmpty();
        if (head == empty) {
            return null;
        }
        if (next == null) {
            set_head(empty);
            setTailRemaining(0L);
        } else {
            set_head(next);
            ChunkBuffer this_$iv = next;
            setTailRemaining(this.tailRemaining - (this_$iv.getWritePosition() - this_$iv.getReadPosition()));
        }
        head.setNext(null);
        return head;
    }

    public final void append$ktor_io(ChunkBuffer chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        if (chain == ChunkBuffer.INSTANCE.getEmpty()) {
            return;
        }
        long size = BuffersKt.remainingAll(chain);
        if (this._head == ChunkBuffer.INSTANCE.getEmpty()) {
            set_head(chain);
            setTailRemaining(size - (getHeadEndExclusive() - getHeadPosition()));
        } else {
            BuffersKt.findTail(this._head).setNext(chain);
            setTailRemaining(this.tailRemaining + size);
        }
    }

    public final boolean tryWriteAppend$ktor_io(ChunkBuffer chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Buffer tail = BuffersKt.findTail(getHead());
        ChunkBuffer this_$iv = chain;
        int size = this_$iv.getWritePosition() - this_$iv.getReadPosition();
        if (size != 0) {
            Buffer this_$iv2 = tail;
            if (this_$iv2.getLimit() - this_$iv2.getWritePosition() < size) {
                return false;
            }
            BufferAppendKt.writeBufferAppend(tail, chain, size);
            if (getHead() == tail) {
                this.headEndExclusive = tail.getWritePosition();
                return true;
            }
            setTailRemaining(this.tailRemaining + size);
            return true;
        }
        return false;
    }

    public final byte readByte() {
        int index = this.headPosition;
        int nextIndex = index + 1;
        if (nextIndex < this.headEndExclusive) {
            this.headPosition = nextIndex;
            ByteBuffer $this$get_u2deY85DW0$iv = this.headMemory;
            return $this$get_u2deY85DW0$iv.get(index);
        }
        return readByteSlow();
    }

    private final byte readByteSlow() {
        int index = this.headPosition;
        if (index < this.headEndExclusive) {
            ByteBuffer $this$get_u2deY85DW0$iv = this.headMemory;
            byte value = $this$get_u2deY85DW0$iv.get(index);
            this.headPosition = index;
            ChunkBuffer head = this._head;
            head.discardUntilIndex$ktor_io(index);
            ensureNext(head);
            return value;
        }
        ChunkBuffer head2 = prepareRead(1);
        if (head2 == null) {
            StringsKt.prematureEndOfStream(1);
            throw new KotlinNothingValueException();
        }
        byte readByte = head2.readByte();
        UnsafeKt.completeReadHead(this, head2);
        return readByte;
    }

    public final int discard(int n) {
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("Negative discard is not allowed: " + n).toString());
        }
        return discardAsMuchAsPossible(n, 0);
    }

    public final void discardExact(int n) {
        if (discard(n) != n) {
            throw new EOFException("Unable to discard " + n + " bytes due to end of packet");
        }
    }

    public final int tryPeek() {
        ChunkBuffer prepareReadLoop;
        ChunkBuffer head = getHead();
        if (getHeadEndExclusive() - getHeadPosition() > 0) {
            return head.tryPeekByte();
        }
        if ((this.tailRemaining == 0 && this.noMoreChunksAvailable) || (prepareReadLoop = prepareReadLoop(1, head)) == null) {
            return -1;
        }
        return prepareReadLoop.tryPeekByte();
    }

    public final int peekTo(ChunkBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Buffer head = prepareReadHead$ktor_io(1);
        if (head == null) {
            return -1;
        }
        ChunkBuffer this_$iv = buffer;
        int limit = this_$iv.getLimit() - this_$iv.getWritePosition();
        Buffer this_$iv2 = head;
        int size = Math.min(limit, this_$iv2.getWritePosition() - this_$iv2.getReadPosition());
        BufferPrimitivesKt.writeFully(buffer, head, size);
        return size;
    }

    public final long discard(long n) {
        if (n <= 0) {
            return 0L;
        }
        return discardAsMuchAsPossible(n, 0L);
    }

    public final int readAvailableCharacters$ktor_io(final char[] destination, final int off, int len) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (getEndOfInput()) {
            return -1;
        }
        return readText(new Appendable(off, destination) { // from class: io.ktor.utils.io.core.Input$readAvailableCharacters$out$1
            final /* synthetic */ char[] $destination;
            private int idx;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.$destination = destination;
                this.idx = off;
            }

            @Override // java.lang.Appendable
            public Appendable append(char value) {
                char[] cArr = this.$destination;
                int i = this.idx;
                this.idx = i + 1;
                cArr[i] = value;
                return this;
            }

            @Override // java.lang.Appendable
            public Appendable append(CharSequence value) {
                if (value instanceof String) {
                    StringsJVMKt.getCharsInternal((String) value, this.$destination, this.idx);
                    this.idx += ((String) value).length();
                } else if (value != null) {
                    int length = value.length();
                    for (int i = 0; i < length; i++) {
                        char[] cArr = this.$destination;
                        int i2 = this.idx;
                        this.idx = i2 + 1;
                        cArr[i2] = value.charAt(i);
                    }
                }
                return this;
            }

            @Override // java.lang.Appendable
            public Appendable append(CharSequence value, int startIndex, int endIndex) {
                throw new UnsupportedOperationException();
            }
        }, 0, len);
    }

    public static /* synthetic */ int readText$default(Input input, Appendable appendable, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readText");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return input.readText(appendable, i, i2);
    }

    public final int readText(Appendable out, int min, int max) {
        Intrinsics.checkNotNullParameter(out, "out");
        if (max >= getRemaining()) {
            String s = StringsKt.readTextExactBytes$default(this, (int) getRemaining(), (Charset) null, 2, (Object) null);
            out.append(s);
            return s.length();
        }
        return readASCII(out, min, max);
    }

    public final void readTextExact(Appendable out, int exactCharacters) {
        Intrinsics.checkNotNullParameter(out, "out");
        readText(out, exactCharacters, exactCharacters);
    }

    public static /* synthetic */ String readText$default(Input input, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readText");
        }
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return input.readText(i, i2);
    }

    public final String readText(int min, int max) {
        if (min == 0 && (max == 0 || getEndOfInput())) {
            return "";
        }
        long remaining = getRemaining();
        if (remaining > 0 && max >= remaining) {
            return StringsKt.readTextExactBytes$default(this, (int) remaining, (Charset) null, 2, (Object) null);
        }
        StringBuilder $this$readText_u24lambda_u243 = new StringBuilder(RangesKt.coerceAtMost(RangesKt.coerceAtLeast(min, 16), max));
        readASCII($this$readText_u24lambda_u243, min, max);
        String sb = $this$readText_u24lambda_u243.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder(capacity).…builderAction).toString()");
        return sb;
    }

    public final String readTextExact(int exactCharacters) {
        return readText(exactCharacters, exactCharacters);
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int readASCII(java.lang.Appendable r27, int r28, int r29) {
        /*
            Method dump skipped, instructions count: 255
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.Input.readASCII(java.lang.Appendable, int, int):int");
    }

    private final Void atLeastMinCharactersRequire(int min) {
        throw new EOFException("at least " + min + " characters required but no bytes available");
    }

    private final Void minShouldBeLess(int min, int max) {
        throw new IllegalArgumentException("min should be less or equal to max but min = " + min + ", max = " + max);
    }

    private final Void prematureEndOfStreamChars(int min, int copied) {
        throw new MalformedUTF8InputException("Premature end of stream: expected at least " + min + " chars but had only " + copied);
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x013b, code lost:
    
        r0.discardExact(((r30 - r22) - r18) + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x01d6, code lost:
    
        if (r8 == false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x01d8, code lost:
    
        io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r34, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x01db, code lost:
    
        r0 = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int readUtf8(java.lang.Appendable r35, int r36, int r37) {
        /*
            Method dump skipped, instructions count: 512
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.Input.readUtf8(java.lang.Appendable, int, int):int");
    }

    private final long discardAsMuchAsPossible(long n, long skipped) {
        ChunkBuffer current;
        while (n != 0 && (current = prepareRead(1)) != null) {
            ChunkBuffer this_$iv = current;
            int size = (int) Math.min(this_$iv.getWritePosition() - this_$iv.getReadPosition(), n);
            current.discardExact(size);
            this.headPosition += size;
            afterRead(current);
            n -= size;
            skipped += size;
        }
        return skipped;
    }

    private final int discardAsMuchAsPossible(int n, int skipped) {
        int currentCount = n;
        int currentSkipped = skipped;
        while (currentCount != 0) {
            ChunkBuffer current = prepareRead(1);
            if (current == null) {
                return currentSkipped;
            }
            ChunkBuffer this_$iv = current;
            int size = Math.min(this_$iv.getWritePosition() - this_$iv.getReadPosition(), currentCount);
            current.discardExact(size);
            this.headPosition += size;
            afterRead(current);
            currentCount -= size;
            currentSkipped += size;
        }
        return currentSkipped;
    }

    private final int readAsMuchAsPossible(byte[] array, int offset, int length, int copied) {
        while (length != 0) {
            ChunkBuffer current = prepareRead(1);
            if (current == null) {
                return copied;
            }
            ChunkBuffer this_$iv = current;
            int size = Math.min(length, this_$iv.getWritePosition() - this_$iv.getReadPosition());
            BufferPrimitivesKt.readFully((Buffer) current, array, offset, size);
            this.headPosition = current.getReadPosition();
            if (size == length) {
                ChunkBuffer this_$iv2 = current;
                if (this_$iv2.getWritePosition() - this_$iv2.getReadPosition() != 0) {
                    return copied + size;
                }
            }
            afterRead(current);
            offset += size;
            length -= size;
            copied += size;
        }
        return copied;
    }

    private final Void notEnoughBytesAvailable(int n) {
        throw new EOFException("Not enough data in packet (" + getRemaining() + ") to read " + n + " byte(s)");
    }

    public final ChunkBuffer prepareReadHead$ktor_io(int minSize) {
        return prepareReadLoop(minSize, getHead());
    }

    public final ChunkBuffer ensureNextHead$ktor_io(ChunkBuffer current) {
        Intrinsics.checkNotNullParameter(current, "current");
        return ensureNext(current);
    }

    public final ChunkBuffer ensureNext(ChunkBuffer current) {
        Intrinsics.checkNotNullParameter(current, "current");
        return ensureNext(current, ChunkBuffer.INSTANCE.getEmpty());
    }

    public final void fixGapAfterRead$ktor_io(ChunkBuffer current) {
        Intrinsics.checkNotNullParameter(current, "current");
        ChunkBuffer next = current.getNext();
        if (next == null) {
            fixGapAfterReadFallback(current);
            return;
        }
        ChunkBuffer this_$iv = current;
        int remaining = this_$iv.getWritePosition() - this_$iv.getReadPosition();
        ChunkBuffer this_$iv2 = current;
        int overrunSize = Math.min(remaining, 8 - (this_$iv2.getCapacity() - this_$iv2.getLimit()));
        if (next.getStartGap() < overrunSize) {
            fixGapAfterReadFallback(current);
            return;
        }
        BufferKt.restoreStartGap(next, overrunSize);
        if (remaining > overrunSize) {
            current.releaseEndGap$ktor_io();
            this.headEndExclusive = current.getWritePosition();
            setTailRemaining(this.tailRemaining + overrunSize);
        } else {
            set_head(next);
            ChunkBuffer this_$iv3 = next;
            setTailRemaining(this.tailRemaining - ((this_$iv3.getWritePosition() - this_$iv3.getReadPosition()) - overrunSize));
            current.cleanNext();
            current.release(this.pool);
        }
    }

    private final void fixGapAfterReadFallback(ChunkBuffer current) {
        if (this.noMoreChunksAvailable && current.getNext() == null) {
            this.headPosition = current.getReadPosition();
            this.headEndExclusive = current.getWritePosition();
            setTailRemaining(0L);
            return;
        }
        ChunkBuffer this_$iv = current;
        int size = this_$iv.getWritePosition() - this_$iv.getReadPosition();
        ChunkBuffer this_$iv2 = current;
        int overrun = Math.min(size, 8 - (this_$iv2.getCapacity() - this_$iv2.getLimit()));
        if (size > overrun) {
            fixGapAfterReadFallbackUnreserved(current, size, overrun);
        } else {
            ChunkBuffer borrow = this.pool.borrow();
            borrow.reserveEndGap(8);
            borrow.setNext(current.cleanNext());
            BufferAppendKt.writeBufferAppend(borrow, current, size);
            set_head(borrow);
        }
        current.release(this.pool);
    }

    private final void fixGapAfterReadFallbackUnreserved(ChunkBuffer current, int size, int overrun) {
        ChunkBuffer chunk1 = this.pool.borrow();
        ChunkBuffer chunk2 = this.pool.borrow();
        chunk1.reserveEndGap(8);
        chunk2.reserveEndGap(8);
        chunk1.setNext(chunk2);
        chunk2.setNext(current.cleanNext());
        BufferAppendKt.writeBufferAppend(chunk1, current, size - overrun);
        BufferAppendKt.writeBufferAppend(chunk2, current, overrun);
        set_head(chunk1);
        setTailRemaining(BuffersKt.remainingAll(chunk2));
    }

    private final ChunkBuffer ensureNext(ChunkBuffer current, ChunkBuffer empty) {
        while (current != empty) {
            ChunkBuffer next = current.cleanNext();
            current.release(this.pool);
            if (next == null) {
                set_head(empty);
                setTailRemaining(0L);
                current = empty;
            } else {
                ChunkBuffer $this$canRead$iv = next;
                if ($this$canRead$iv.getWritePosition() > $this$canRead$iv.getReadPosition()) {
                    set_head(next);
                    ChunkBuffer this_$iv = next;
                    setTailRemaining(this.tailRemaining - (this_$iv.getWritePosition() - this_$iv.getReadPosition()));
                    return next;
                }
                current = next;
            }
        }
        return doFill();
    }

    protected ChunkBuffer fill() {
        ChunkBuffer buffer = this.pool.borrow();
        try {
            buffer.reserveEndGap(8);
            ChunkBuffer this_$iv = buffer;
            int copied = mo415fill62zg_DM(buffer.getMemory(), buffer.getWritePosition(), this_$iv.getLimit() - this_$iv.getWritePosition());
            if (copied == 0) {
                boolean z = true;
                this.noMoreChunksAvailable = true;
                ChunkBuffer $this$canRead$iv = buffer;
                if ($this$canRead$iv.getWritePosition() <= $this$canRead$iv.getReadPosition()) {
                    z = false;
                }
                if (!z) {
                    buffer.release(this.pool);
                    return null;
                }
            }
            buffer.commitWritten(copied);
            return buffer;
        } catch (Throwable t) {
            buffer.release(this.pool);
            throw t;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void markNoMoreChunksAvailable() {
        if (!this.noMoreChunksAvailable) {
            this.noMoreChunksAvailable = true;
        }
    }

    private final ChunkBuffer doFill() {
        if (this.noMoreChunksAvailable) {
            return null;
        }
        ChunkBuffer chunk = fill();
        if (chunk == null) {
            this.noMoreChunksAvailable = true;
            return null;
        }
        appendView(chunk);
        return chunk;
    }

    private final void appendView(ChunkBuffer chunk) {
        ChunkBuffer tail = BuffersKt.findTail(this._head);
        if (tail == ChunkBuffer.INSTANCE.getEmpty()) {
            set_head(chunk);
            if (!(this.tailRemaining == 0)) {
                throw new IllegalStateException("It should be no tail remaining bytes if current tail is EmptyBuffer");
            }
            ChunkBuffer next = chunk.getNext();
            setTailRemaining(next != null ? BuffersKt.remainingAll(next) : 0L);
            return;
        }
        tail.setNext(chunk);
        setTailRemaining(this.tailRemaining + BuffersKt.remainingAll(chunk));
    }

    public final ChunkBuffer prepareRead(int minSize) {
        ChunkBuffer head = getHead();
        return this.headEndExclusive - this.headPosition >= minSize ? head : prepareReadLoop(minSize, head);
    }

    public final ChunkBuffer prepareRead(int minSize, ChunkBuffer head) {
        Intrinsics.checkNotNullParameter(head, "head");
        return this.headEndExclusive - this.headPosition >= minSize ? head : prepareReadLoop(minSize, head);
    }

    private final ChunkBuffer prepareReadLoop(int minSize, ChunkBuffer head) {
        while (true) {
            int headSize = getHeadEndExclusive() - getHeadPosition();
            if (headSize >= minSize) {
                return head;
            }
            ChunkBuffer next = head.getNext();
            if (next == null && (next = doFill()) == null) {
                return null;
            }
            if (headSize == 0) {
                if (head != ChunkBuffer.INSTANCE.getEmpty()) {
                    releaseHead$ktor_io(head);
                }
                head = next;
            } else {
                int desiredExtraBytes = minSize - headSize;
                int copied = BufferAppendKt.writeBufferAppend(head, next, desiredExtraBytes);
                this.headEndExclusive = head.getWritePosition();
                setTailRemaining(this.tailRemaining - copied);
                Buffer $this$canRead$iv = next;
                if (!($this$canRead$iv.getWritePosition() > $this$canRead$iv.getReadPosition())) {
                    head.setNext(null);
                    head.setNext(next.cleanNext());
                    next.release(this.pool);
                } else {
                    next.reserveStartGap(copied);
                }
                Buffer this_$iv = head;
                if (this_$iv.getWritePosition() - this_$iv.getReadPosition() >= minSize) {
                    return head;
                }
                if (minSize > 8) {
                    minSizeIsTooBig(minSize);
                    throw new KotlinNothingValueException();
                }
            }
        }
    }

    private final Void minSizeIsTooBig(int minSize) {
        throw new IllegalStateException("minSize of " + minSize + " is too big (should be less than 8)");
    }

    private final void afterRead(ChunkBuffer head) {
        ChunkBuffer this_$iv = head;
        if (this_$iv.getWritePosition() - this_$iv.getReadPosition() == 0) {
            releaseHead$ktor_io(head);
        }
    }

    public final ChunkBuffer releaseHead$ktor_io(ChunkBuffer head) {
        Intrinsics.checkNotNullParameter(head, "head");
        ChunkBuffer next = head.cleanNext();
        if (next == null) {
            next = ChunkBuffer.INSTANCE.getEmpty();
        }
        set_head(next);
        Buffer this_$iv = next;
        setTailRemaining(this.tailRemaining - (this_$iv.getWritePosition() - this_$iv.getReadPosition()));
        head.release(this.pool);
        return next;
    }
}
