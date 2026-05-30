package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Scanner.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a,\u0010\t\u001a\u00020\u0001*\u00020\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0080\bø\u0001\u0000\u001a<\u0010\t\u001a\u00020\u0001*\u00020\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0001H\u0080\bø\u0001\u0000\u001a\u0012\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001a\u0010\u0015\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005\u001a\u001a\u0010\u0016\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e\u001a.\u0010\u0016\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u0001\u001a\"\u0010\u0017\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e\u001a6\u0010\u0017\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0018"}, d2 = {"discardUntilDelimiterImplMemory", "", "buffer", "Lio/ktor/utils/io/core/Buffer;", "delimiter", "", "discardUntilDelimitersImplMemory", "delimiter1", "delimiter2", "copyUntil", "predicate", "Lkotlin/Function1;", "", "dst", "Lio/ktor/utils/io/core/Output;", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "discardUntilDelimiter", "", "Lio/ktor/utils/io/core/Input;", "discardUntilDelimiters", "readUntilDelimiter", "readUntilDelimiters", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ScannerKt {
    public static final long discardUntilDelimiter(Input $this$discardUntilDelimiter, byte delimiter) {
        Intrinsics.checkNotNullParameter($this$discardUntilDelimiter, "<this>");
        ChunkBuffer current$iv = UnsafeKt.prepareReadFirstHead($this$discardUntilDelimiter, 1);
        if (current$iv == null) {
            return 0L;
        }
        ChunkBuffer current$iv2 = current$iv;
        long discardedTotal = 0;
        boolean release$iv = true;
        while (true) {
            try {
                Buffer chunk = current$iv2;
                try {
                    int discarded = ScannerJVMKt.discardUntilDelimiterImpl(chunk, delimiter);
                    discardedTotal += discarded;
                    boolean z = false;
                    if (discarded > 0) {
                        if (!(chunk.getWritePosition() > chunk.getReadPosition())) {
                            z = true;
                        }
                    }
                    if (!z) {
                        break;
                    }
                    release$iv = false;
                    ChunkBuffer next$iv = UnsafeKt.prepareReadNextHead($this$discardUntilDelimiter, current$iv2);
                    if (next$iv == null) {
                        break;
                    }
                    current$iv2 = next$iv;
                    release$iv = true;
                } catch (Throwable th) {
                    th = th;
                    if (release$iv) {
                        UnsafeKt.completeReadHead($this$discardUntilDelimiter, current$iv2);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        if (release$iv) {
            UnsafeKt.completeReadHead($this$discardUntilDelimiter, current$iv2);
        }
        return discardedTotal;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0047 A[Catch: all -> 0x005a, TRY_LEAVE, TryCatch #0 {all -> 0x005a, blocks: (B:11:0x0027, B:13:0x002f, B:19:0x0047), top: B:10:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0046 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long discardUntilDelimiters(io.ktor.utils.io.core.Input r18, byte r19, byte r20) {
        /*
            java.lang.String r0 = "<this>"
            r1 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r2 = 0
            r4 = r18
            r5 = 0
            r0 = 1
            r6 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r7 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r4, r6)
            if (r7 != 0) goto L19
            r10 = r19
            r11 = r20
            goto L55
        L19:
            r16 = r2
            r3 = r7
            r7 = r16
            r2 = r0
        L1f:
            r0 = r3
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch: java.lang.Throwable -> L5c
            r9 = 0
            r10 = r19
            r11 = r20
            int r12 = io.ktor.utils.io.core.ScannerJVMKt.discardUntilDelimitersImpl(r0, r10, r11)     // Catch: java.lang.Throwable -> L5a
            long r13 = (long) r12     // Catch: java.lang.Throwable -> L5a
            long r7 = r7 + r13
            if (r12 <= 0) goto L42
            r14 = r0
            r15 = 0
            int r6 = r14.getWritePosition()     // Catch: java.lang.Throwable -> L5a
            int r13 = r14.getReadPosition()     // Catch: java.lang.Throwable -> L5a
            if (r6 <= r13) goto L3d
            r6 = 1
            goto L3e
        L3d:
            r6 = 0
        L3e:
            if (r6 != 0) goto L42
            r13 = 1
            goto L43
        L42:
            r13 = 0
        L43:
            if (r13 != 0) goto L47
            goto L4e
        L47:
            r2 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r0 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r4, r3)     // Catch: java.lang.Throwable -> L5a
            if (r0 != 0) goto L56
        L4e:
            if (r2 == 0) goto L53
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r4, r3)
        L53:
            r2 = r7
        L55:
            return r2
        L56:
            r3 = r0
            r2 = 1
            r6 = 1
            goto L1f
        L5a:
            r0 = move-exception
            goto L61
        L5c:
            r0 = move-exception
            r10 = r19
            r11 = r20
        L61:
            if (r2 == 0) goto L66
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r4, r3)
        L66:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.ScannerKt.discardUntilDelimiters(io.ktor.utils.io.core.Input, byte, byte):long");
    }

    public static /* synthetic */ int readUntilDelimiter$default(Input input, byte b, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = bArr.length;
        }
        return readUntilDelimiter(input, b, bArr, i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004c A[Catch: all -> 0x0062, TRY_LEAVE, TryCatch #0 {all -> 0x0062, blocks: (B:12:0x002c, B:14:0x0034, B:20:0x004c), top: B:11:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int readUntilDelimiter(io.ktor.utils.io.core.Input r16, byte r17, byte[] r18, int r19, int r20) {
        /*
            r1 = r18
            java.lang.String r0 = "<this>"
            r2 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r0 = 0
            r0 = r19
            r3 = 0
            r3 = r20
            r4 = r16
            r5 = 0
            r6 = 1
            r7 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r8 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r4, r7)
            if (r8 != 0) goto L22
            r11 = r17
            goto L5b
        L22:
            r9 = r8
            r8 = r6
            r6 = r3
            r3 = r0
        L26:
            r0 = r9
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch: java.lang.Throwable -> L64
            r10 = 0
            r11 = r17
            int r12 = io.ktor.utils.io.core.ScannerJVMKt.readUntilDelimiterImpl(r0, r11, r1, r3, r6)     // Catch: java.lang.Throwable -> L62
            int r3 = r3 + r12
            int r6 = r6 - r12
            if (r6 <= 0) goto L47
            r14 = r0
            r15 = 0
            int r7 = r14.getWritePosition()     // Catch: java.lang.Throwable -> L62
            int r13 = r14.getReadPosition()     // Catch: java.lang.Throwable -> L62
            if (r7 <= r13) goto L42
            r7 = 1
            goto L43
        L42:
            r7 = 0
        L43:
            if (r7 != 0) goto L47
            r13 = 1
            goto L48
        L47:
            r13 = 0
        L48:
            if (r13 != 0) goto L4c
            goto L53
        L4c:
            r8 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r0 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r4, r9)     // Catch: java.lang.Throwable -> L62
            if (r0 != 0) goto L5e
        L53:
            if (r8 == 0) goto L58
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r4, r9)
        L58:
            r0 = r3
            r3 = r6
        L5b:
            int r4 = r0 - r19
            return r4
        L5e:
            r9 = r0
            r8 = 1
            r7 = 1
            goto L26
        L62:
            r0 = move-exception
            goto L67
        L64:
            r0 = move-exception
            r11 = r17
        L67:
            if (r8 == 0) goto L6c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r4, r9)
        L6c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.ScannerKt.readUntilDelimiter(io.ktor.utils.io.core.Input, byte, byte[], int, int):int");
    }

    public static /* synthetic */ int readUntilDelimiters$default(Input input, byte b, byte b2, byte[] bArr, int i, int i2, int i3, Object obj) {
        int i4;
        int i5;
        if ((i3 & 8) == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        if ((i3 & 16) == 0) {
            i5 = i2;
        } else {
            i5 = bArr.length;
        }
        return readUntilDelimiters(input, b, b2, bArr, i4, i5);
    }

    public static final int readUntilDelimiters(Input $this$readUntilDelimiters, byte delimiter1, byte delimiter2, byte[] dst, int offset, int length) {
        byte b = delimiter1;
        byte[] dst2 = dst;
        Intrinsics.checkNotNullParameter($this$readUntilDelimiters, "<this>");
        Intrinsics.checkNotNullParameter(dst2, "dst");
        byte b2 = delimiter2;
        if (b == b2) {
            return readUntilDelimiter($this$readUntilDelimiters, b, dst2, offset, length);
        }
        int currentOffset = offset;
        ChunkBuffer current$iv = UnsafeKt.prepareReadFirstHead($this$readUntilDelimiters, 1);
        if (current$iv != null) {
            boolean release$iv = true;
            ChunkBuffer current$iv2 = current$iv;
            int currentOffset2 = currentOffset;
            int dstRemaining = length;
            while (true) {
                try {
                    Buffer chunk = current$iv2;
                    int copied = ScannerJVMKt.readUntilDelimitersImpl(chunk, b, b2, dst2, currentOffset2, dstRemaining);
                    currentOffset2 += copied;
                    dstRemaining -= copied;
                    boolean z = false;
                    if (!(chunk.getWritePosition() > chunk.getReadPosition()) && dstRemaining > 0) {
                        z = true;
                    }
                    if (!z) {
                        break;
                    }
                    release$iv = false;
                    ChunkBuffer next$iv = UnsafeKt.prepareReadNextHead($this$readUntilDelimiters, current$iv2);
                    if (next$iv == null) {
                        break;
                    }
                    current$iv2 = next$iv;
                    release$iv = true;
                    b = delimiter1;
                    b2 = delimiter2;
                    dst2 = dst;
                } finally {
                    if (release$iv) {
                        UnsafeKt.completeReadHead($this$readUntilDelimiters, current$iv2);
                    }
                }
            }
            currentOffset = currentOffset2;
        }
        return currentOffset - offset;
    }

    public static final long readUntilDelimiter(Input $this$readUntilDelimiter, byte delimiter, Output dst) {
        Intrinsics.checkNotNullParameter($this$readUntilDelimiter, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        ChunkBuffer current$iv = UnsafeKt.prepareReadFirstHead($this$readUntilDelimiter, 1);
        if (current$iv == null) {
            return 0L;
        }
        ChunkBuffer current$iv2 = current$iv;
        long copiedTotal = 0;
        boolean release$iv = true;
        while (true) {
            try {
                Buffer chunk = current$iv2;
                try {
                    int copied = ScannerJVMKt.readUntilDelimiterImpl(chunk, delimiter, dst);
                    copiedTotal += copied;
                    if (chunk.getWritePosition() > chunk.getReadPosition()) {
                        break;
                    }
                    release$iv = false;
                    ChunkBuffer next$iv = UnsafeKt.prepareReadNextHead($this$readUntilDelimiter, current$iv2);
                    if (next$iv == null) {
                        break;
                    }
                    current$iv2 = next$iv;
                    release$iv = true;
                } catch (Throwable th) {
                    th = th;
                    if (release$iv) {
                        UnsafeKt.completeReadHead($this$readUntilDelimiter, current$iv2);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        if (release$iv) {
            UnsafeKt.completeReadHead($this$readUntilDelimiter, current$iv2);
        }
        return copiedTotal;
    }

    public static final long readUntilDelimiters(Input $this$readUntilDelimiters, byte delimiter1, byte delimiter2, Output dst) {
        Intrinsics.checkNotNullParameter($this$readUntilDelimiters, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        ChunkBuffer current$iv = UnsafeKt.prepareReadFirstHead($this$readUntilDelimiters, 1);
        if (current$iv == null) {
            return 0L;
        }
        ChunkBuffer current$iv2 = current$iv;
        long copiedTotal = 0;
        boolean release$iv = true;
        while (true) {
            try {
                Buffer chunk = current$iv2;
                try {
                    int copied = ScannerJVMKt.readUntilDelimitersImpl(chunk, delimiter1, delimiter2, dst);
                    copiedTotal += copied;
                    if (chunk.getWritePosition() > chunk.getReadPosition()) {
                        break;
                    }
                    release$iv = false;
                    ChunkBuffer next$iv = UnsafeKt.prepareReadNextHead($this$readUntilDelimiters, current$iv2);
                    if (next$iv == null) {
                        break;
                    }
                    current$iv2 = next$iv;
                    release$iv = true;
                } catch (Throwable th) {
                    th = th;
                    if (release$iv) {
                        UnsafeKt.completeReadHead($this$readUntilDelimiters, current$iv2);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        if (release$iv) {
            UnsafeKt.completeReadHead($this$readUntilDelimiters, current$iv2);
        }
        return copiedTotal;
    }

    public static final int discardUntilDelimiterImplMemory(Buffer buffer, byte delimiter) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int start = buffer.getReadPosition();
        int i = start;
        int limit = buffer.getWritePosition();
        ByteBuffer memory = buffer.getMemory();
        while (i < limit && memory.get(i) != delimiter) {
            i++;
        }
        buffer.discardUntilIndex$ktor_io(i);
        return i - start;
    }

    public static final int discardUntilDelimitersImplMemory(Buffer buffer, byte delimiter1, byte delimiter2) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int start = buffer.getReadPosition();
        int i = start;
        int limit = buffer.getWritePosition();
        ByteBuffer memory = buffer.getMemory();
        while (i < limit) {
            int i2 = memory.get(i);
            if (i2 == delimiter1 || i2 == delimiter2) {
                break;
            }
            i++;
        }
        buffer.discardUntilIndex$ktor_io(i);
        return i - start;
    }

    public static final int copyUntil(Buffer $this$copyUntil, Function1<? super Byte, Boolean> predicate, byte[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$copyUntil, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int readPosition = $this$copyUntil.getReadPosition();
        int end = Math.min($this$copyUntil.getWritePosition(), readPosition + length);
        ByteBuffer memory = $this$copyUntil.getMemory();
        int index = readPosition;
        while (true) {
            if (index >= end) {
                break;
            }
            if (!predicate.invoke(Byte.valueOf(memory.get(index))).booleanValue()) {
                index++;
            } else {
                end = index;
                break;
            }
        }
        int index2 = end - readPosition;
        MemoryJvmKt.m254copyTo9zorpBc(memory, dst, readPosition, index2, offset);
        return index2;
    }

    public static final int copyUntil(Buffer $this$copyUntil, Function1<? super Byte, Boolean> predicate, Output dst) {
        Intrinsics.checkNotNullParameter($this$copyUntil, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int index = $this$copyUntil.getReadPosition();
        int end = $this$copyUntil.getWritePosition();
        ByteBuffer memory = $this$copyUntil.getMemory();
        while (index != end && !predicate.invoke(Byte.valueOf(memory.get(index))).booleanValue()) {
            index++;
        }
        int size = index - $this$copyUntil.getReadPosition();
        OutputKt.writeFully(dst, $this$copyUntil, size);
        return size;
    }
}
