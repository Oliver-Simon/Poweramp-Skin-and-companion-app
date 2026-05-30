package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Input.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0001\u001a$\u0010\u0007\u001a\u00020\u0004*\u00020\u00022\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00040\tH\u0080\bø\u0001\u0000\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\u0002\u001a\u0014\u0010\r\u001a\u00020\f*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0006H\u0002\u001a$\u0010\u000f\u001a\u00020\u0004*\u00020\u00022\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\tH\u0086\bø\u0001\u0000\u001a.\u0010\u0012\u001a\u00020\u0004*\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\tH\u0080\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"discard", "", "Lio/ktor/utils/io/core/Input;", "discardExact", "", "n", "", "forEach", "block", "Lkotlin/Function1;", "", "peekCharUtf8", "", "peekCharUtf8Impl", "first", "takeWhile", "Lio/ktor/utils/io/core/Buffer;", "", "takeWhileSize", "initialSize", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class InputKt {
    public static final long discard(Input $this$discard) {
        Intrinsics.checkNotNullParameter($this$discard, "<this>");
        return $this$discard.discard(Long.MAX_VALUE);
    }

    public static final void discardExact(Input $this$discardExact, long n) {
        Intrinsics.checkNotNullParameter($this$discardExact, "<this>");
        long discarded = $this$discardExact.discard(n);
        if (discarded != n) {
            throw new IllegalStateException("Only " + discarded + " bytes were discarded of " + n + " requested");
        }
    }

    public static final void discardExact(Input $this$discardExact, int n) {
        Intrinsics.checkNotNullParameter($this$discardExact, "<this>");
        discardExact($this$discardExact, n);
    }

    public static final void takeWhile(Input $this$takeWhile, Function1<? super Buffer, Boolean> block) {
        Intrinsics.checkNotNullParameter($this$takeWhile, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        boolean release = true;
        ChunkBuffer current = UnsafeKt.prepareReadFirstHead($this$takeWhile, 1);
        if (current == null) {
            return;
        }
        while (block.invoke(current).booleanValue()) {
            try {
                release = false;
                ChunkBuffer next = UnsafeKt.prepareReadNextHead($this$takeWhile, current);
                if (next == null) {
                    break;
                }
                current = next;
                release = true;
            } finally {
                if (release) {
                    UnsafeKt.completeReadHead($this$takeWhile, current);
                }
            }
        }
    }

    public static /* synthetic */ void takeWhileSize$default(Input $this$takeWhileSize_u24default, int initialSize, Function1 block, int i, Object obj) {
        int after;
        ChunkBuffer next;
        if ((i & 1) != 0) {
            initialSize = 1;
        }
        Intrinsics.checkNotNullParameter($this$takeWhileSize_u24default, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        boolean release = true;
        ChunkBuffer current = UnsafeKt.prepareReadFirstHead($this$takeWhileSize_u24default, initialSize);
        if (current == null) {
            return;
        }
        int size = initialSize;
        do {
            try {
                Buffer this_$iv = current;
                int before = this_$iv.getWritePosition() - this_$iv.getReadPosition();
                if (before >= size) {
                    try {
                        size = ((Number) block.invoke(current)).intValue();
                        Buffer this_$iv2 = current;
                        after = this_$iv2.getWritePosition() - this_$iv2.getReadPosition();
                    } catch (Throwable th) {
                        Buffer this_$iv3 = current;
                        int writePosition = this_$iv3.getWritePosition() - this_$iv3.getReadPosition();
                        throw th;
                    }
                } else {
                    after = before;
                }
                release = false;
                if (after == 0) {
                    next = UnsafeKt.prepareReadNextHead($this$takeWhileSize_u24default, current);
                } else {
                    if (after >= size) {
                        Buffer this_$iv4 = current;
                        if (this_$iv4.getCapacity() - this_$iv4.getLimit() >= 8) {
                            next = current;
                        }
                    }
                    UnsafeKt.completeReadHead($this$takeWhileSize_u24default, current);
                    next = UnsafeKt.prepareReadFirstHead($this$takeWhileSize_u24default, size);
                }
                if (next == null) {
                    break;
                }
                current = next;
                release = true;
            } finally {
                if (release) {
                    UnsafeKt.completeReadHead($this$takeWhileSize_u24default, current);
                }
            }
        } while (size > 0);
    }

    public static final void takeWhileSize(Input $this$takeWhileSize, int initialSize, Function1<? super Buffer, Integer> block) {
        int after;
        ChunkBuffer next;
        Intrinsics.checkNotNullParameter($this$takeWhileSize, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        boolean release = true;
        ChunkBuffer current = UnsafeKt.prepareReadFirstHead($this$takeWhileSize, initialSize);
        if (current == null) {
            return;
        }
        int size = initialSize;
        do {
            try {
                Buffer this_$iv = current;
                int before = this_$iv.getWritePosition() - this_$iv.getReadPosition();
                if (before >= size) {
                    try {
                        size = block.invoke(current).intValue();
                        Buffer this_$iv2 = current;
                        after = this_$iv2.getWritePosition() - this_$iv2.getReadPosition();
                    } catch (Throwable th) {
                        Buffer this_$iv3 = current;
                        int writePosition = this_$iv3.getWritePosition() - this_$iv3.getReadPosition();
                        throw th;
                    }
                } else {
                    after = before;
                }
                release = false;
                if (after == 0) {
                    next = UnsafeKt.prepareReadNextHead($this$takeWhileSize, current);
                } else {
                    if (after >= size) {
                        Buffer this_$iv4 = current;
                        if (this_$iv4.getCapacity() - this_$iv4.getLimit() >= 8) {
                            next = current;
                        }
                    }
                    UnsafeKt.completeReadHead($this$takeWhileSize, current);
                    next = UnsafeKt.prepareReadFirstHead($this$takeWhileSize, size);
                }
                if (next == null) {
                    break;
                }
                current = next;
                release = true;
            } finally {
                if (release) {
                    UnsafeKt.completeReadHead($this$takeWhileSize, current);
                }
            }
        } while (size > 0);
    }

    public static final char peekCharUtf8(Input $this$peekCharUtf8) {
        Intrinsics.checkNotNullParameter($this$peekCharUtf8, "<this>");
        int rc = $this$peekCharUtf8.tryPeek();
        if ((rc & 128) == 0) {
            return (char) rc;
        }
        if (rc == -1) {
            throw new EOFException("Failed to peek a char: end of input");
        }
        return peekCharUtf8Impl($this$peekCharUtf8, rc);
    }

    public static final void forEach(Input $this$forEach, Function1<? super Byte, Unit> block) {
        Intrinsics.checkNotNullParameter($this$forEach, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer current$iv = UnsafeKt.prepareReadFirstHead($this$forEach, 1);
        if (current$iv == null) {
            return;
        }
        ChunkBuffer current$iv2 = current$iv;
        boolean release$iv = true;
        while (true) {
            try {
                Buffer buffer = current$iv2;
                ByteBuffer memory$iv = buffer.getMemory();
                int start$iv = buffer.getReadPosition();
                int endExclusive$iv = buffer.getWritePosition();
                for (int index$iv = start$iv; index$iv < endExclusive$iv; index$iv++) {
                    block.invoke(Byte.valueOf(memory$iv.get(index$iv)));
                }
                int rc$iv$iv = endExclusive$iv - start$iv;
                buffer.discardExact(rc$iv$iv);
                release$iv = false;
                ChunkBuffer next$iv = UnsafeKt.prepareReadNextHead($this$forEach, current$iv2);
                if (next$iv == null) {
                    break;
                }
                current$iv2 = next$iv;
                release$iv = true;
            } finally {
                if (release$iv) {
                    UnsafeKt.completeReadHead($this$forEach, current$iv2);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x018b, code lost:
    
        if (r6 == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x018d, code lost:
    
        io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r30, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0190, code lost:
    
        r0 = r1;
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00d5, code lost:
    
        if (io.ktor.utils.io.core.internal.UTF8Kt.isBmpCodePoint(r0) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00d7, code lost:
    
        r1 = (char) r0;
        r5 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00e3, code lost:
    
        r0.discardExact(((r26 - r20) - r16) + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00e6, code lost:
    
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00e8, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0148, code lost:
    
        r2 = r7;
        r12 = r2.getWritePosition() - r2.getReadPosition();
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0156, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00ef, code lost:
    
        if (io.ktor.utils.io.core.internal.UTF8Kt.isValidCodePoint(r0) == false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00f5, code lost:
    
        r1 = (char) io.ktor.utils.io.core.internal.UTF8Kt.highSurrogate(r0);
        r5 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0101, code lost:
    
        r0.discardExact(((r26 - r20) - r16) + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0104, code lost:
    
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0106, code lost:
    
        io.ktor.utils.io.core.internal.UTF8Kt.malformedCodePoint(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x010e, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final char peekCharUtf8Impl(io.ktor.utils.io.core.Input r30, int r31) {
        /*
            Method dump skipped, instructions count: 429
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputKt.peekCharUtf8Impl(io.ktor.utils.io.core.Input, int):char");
    }
}
