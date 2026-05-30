package io.ktor.utils.io.core;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Buffer.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0000\u001a\r\u0010\t\u001a\u00020\n*\u00020\u000bH\u0086\b\u001a\r\u0010\f\u001a\u00020\n*\u00020\u000bH\u0086\b\u001a\u0014\u0010\r\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0010\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0011\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0003H\u0000\u001am\u0010\u0012\u001a\u00020\u0003*\u00020\u000b2K\u0010\u0013\u001aG\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00030\u0014H\u0086\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\u0014\u0010\u001b\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u001d\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u001f\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0003H\u0000\u001am\u0010 \u001a\u00020\u0003*\u00020\u000b2K\u0010\u0013\u001aG\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00030\u0014H\u0086\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006!"}, d2 = {"commitWrittenFailed", "", "count", "", "writeRemaining", "discardFailed", "readRemaining", "rewindFailed", "rewindRemaining", "canRead", "", "Lio/ktor/utils/io/core/Buffer;", "canWrite", "endGapReservationFailedDueToCapacity", "", "endGap", "endGapReservationFailedDueToContent", "endGapReservationFailedDueToStartGap", "read", "block", "Lkotlin/Function3;", "Lio/ktor/utils/io/bits/Memory;", "Lkotlin/ParameterName;", "name", "memory", "start", "endExclusive", "restoreStartGap", ContentDisposition.Parameters.Size, "startGapReservationFailed", "startGap", "startGapReservationFailedDueToLimit", "write", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BufferKt {
    public static final boolean canRead(Buffer $this$canRead) {
        Intrinsics.checkNotNullParameter($this$canRead, "<this>");
        return $this$canRead.getWritePosition() > $this$canRead.getReadPosition();
    }

    public static final boolean canWrite(Buffer $this$canWrite) {
        Intrinsics.checkNotNullParameter($this$canWrite, "<this>");
        return $this$canWrite.getLimit() > $this$canWrite.getWritePosition();
    }

    public static final int read(Buffer $this$read, Function3<? super Memory, ? super Integer, ? super Integer, Integer> block) {
        Intrinsics.checkNotNullParameter($this$read, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        int rc = block.invoke(Memory.m234boximpl($this$read.getMemory()), Integer.valueOf($this$read.getReadPosition()), Integer.valueOf($this$read.getWritePosition())).intValue();
        $this$read.discardExact(rc);
        return rc;
    }

    public static final int write(Buffer $this$write, Function3<? super Memory, ? super Integer, ? super Integer, Integer> block) {
        Intrinsics.checkNotNullParameter($this$write, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        int rc = block.invoke(Memory.m234boximpl($this$write.getMemory()), Integer.valueOf($this$write.getWritePosition()), Integer.valueOf($this$write.getLimit())).intValue();
        $this$write.commitWritten(rc);
        return rc;
    }

    public static final Void discardFailed(int count, int readRemaining) {
        throw new EOFException("Unable to discard " + count + " bytes: only " + readRemaining + " available for reading");
    }

    public static final Void commitWrittenFailed(int count, int writeRemaining) {
        throw new EOFException("Unable to discard " + count + " bytes: only " + writeRemaining + " available for writing");
    }

    public static final Void rewindFailed(int count, int rewindRemaining) {
        throw new IllegalArgumentException("Unable to rewind " + count + " bytes: only " + rewindRemaining + " could be rewinded");
    }

    public static final Void startGapReservationFailedDueToLimit(Buffer $this$startGapReservationFailedDueToLimit, int startGap) {
        Intrinsics.checkNotNullParameter($this$startGapReservationFailedDueToLimit, "<this>");
        if (startGap > $this$startGapReservationFailedDueToLimit.getCapacity()) {
            throw new IllegalArgumentException("Start gap " + startGap + " is bigger than the capacity " + $this$startGapReservationFailedDueToLimit.getCapacity());
        }
        throw new IllegalStateException("Unable to reserve " + startGap + " start gap: there are already " + ($this$startGapReservationFailedDueToLimit.getCapacity() - $this$startGapReservationFailedDueToLimit.getLimit()) + " bytes reserved in the end");
    }

    public static final Void startGapReservationFailed(Buffer $this$startGapReservationFailed, int startGap) {
        Intrinsics.checkNotNullParameter($this$startGapReservationFailed, "<this>");
        throw new IllegalStateException("Unable to reserve " + startGap + " start gap: there are already " + ($this$startGapReservationFailed.getWritePosition() - $this$startGapReservationFailed.getReadPosition()) + " content bytes starting at offset " + $this$startGapReservationFailed.getReadPosition());
    }

    public static final void endGapReservationFailedDueToCapacity(Buffer $this$endGapReservationFailedDueToCapacity, int endGap) {
        Intrinsics.checkNotNullParameter($this$endGapReservationFailedDueToCapacity, "<this>");
        throw new IllegalArgumentException("End gap " + endGap + " is too big: capacity is " + $this$endGapReservationFailedDueToCapacity.getCapacity());
    }

    public static final void endGapReservationFailedDueToStartGap(Buffer $this$endGapReservationFailedDueToStartGap, int endGap) {
        Intrinsics.checkNotNullParameter($this$endGapReservationFailedDueToStartGap, "<this>");
        throw new IllegalArgumentException("End gap " + endGap + " is too big: there are already " + $this$endGapReservationFailedDueToStartGap.getStartGap() + " bytes reserved in the beginning");
    }

    public static final void endGapReservationFailedDueToContent(Buffer $this$endGapReservationFailedDueToContent, int endGap) {
        Intrinsics.checkNotNullParameter($this$endGapReservationFailedDueToContent, "<this>");
        throw new IllegalArgumentException("Unable to reserve end gap " + endGap + ": there are already " + ($this$endGapReservationFailedDueToContent.getWritePosition() - $this$endGapReservationFailedDueToContent.getReadPosition()) + " content bytes at offset " + $this$endGapReservationFailedDueToContent.getReadPosition());
    }

    public static final void restoreStartGap(Buffer $this$restoreStartGap, int size) {
        Intrinsics.checkNotNullParameter($this$restoreStartGap, "<this>");
        $this$restoreStartGap.releaseStartGap$ktor_io($this$restoreStartGap.getReadPosition() - size);
    }
}
