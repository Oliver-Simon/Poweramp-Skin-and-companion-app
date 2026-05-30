package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScannerJVM.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0002\u001a0\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a)\u0010\u000e\u001a\u00020\u0001*\u00020\u00032\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\n\u001a\u00020\u0012H\u0082\b\u001aA\u0010\u000e\u001a\u00020\u0001*\u00020\u00132\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0082\b\u001a9\u0010\u0015\u001a\u00020\u0001*\u00020\u00132\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0082\b\u001a\u0014\u0010\u0016\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u001c\u0010\u0017\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a\u001c\u0010\t\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a\u001c\u0010\u0018\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a,\u0010\u0018\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a\u001c\u0010\u0019\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a,\u0010\u0019\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0000\u001a$\u0010\u001a\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a4\u0010\u001a\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a$\u0010\u001b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a4\u0010\u001b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a$\u0010\u001c\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a4\u0010\u001c\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0000¨\u0006\u001d"}, d2 = {"discardUntilDelimiterImplArrays", "", "buffer", "Lio/ktor/utils/io/core/Buffer;", "delimiter", "", "discardUntilDelimitersImplArrays", "delimiter1", "delimiter2", "readUntilDelimiterArrays", "dst", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "copyUntilArrays", "predicate", "Lkotlin/Function1;", "", "Lio/ktor/utils/io/core/Output;", "Ljava/nio/ByteBuffer;", "bufferOffset", "copyUntilDirect", "discardUntilDelimiterImpl", "discardUntilDelimitersImpl", "readUntilDelimiterDirect", "readUntilDelimiterImpl", "readUntilDelimitersArrays", "readUntilDelimitersDirect", "readUntilDelimitersImpl", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ScannerJVMKt {
    public static final int discardUntilDelimiterImpl(Buffer $this$discardUntilDelimiterImpl, byte delimiter) {
        Intrinsics.checkNotNullParameter($this$discardUntilDelimiterImpl, "<this>");
        if (ByteBuffersKt.hasArray($this$discardUntilDelimiterImpl)) {
            return discardUntilDelimiterImplArrays($this$discardUntilDelimiterImpl, delimiter);
        }
        return ScannerKt.discardUntilDelimiterImplMemory($this$discardUntilDelimiterImpl, delimiter);
    }

    private static final int discardUntilDelimiterImplArrays(Buffer buffer, byte delimiter) {
        ByteBuffer bb = buffer.getMemory();
        byte[] array = bb.array();
        int start = bb.arrayOffset() + bb.position() + buffer.getReadPosition();
        int i = start;
        int end = (buffer.getWritePosition() - buffer.getReadPosition()) + i;
        if (end <= array.length) {
            while (i < end && array[i] != delimiter) {
                i++;
            }
        }
        buffer.discardUntilIndex$ktor_io(i);
        return i - start;
    }

    public static final int discardUntilDelimitersImpl(Buffer $this$discardUntilDelimitersImpl, byte delimiter1, byte delimiter2) {
        Intrinsics.checkNotNullParameter($this$discardUntilDelimitersImpl, "<this>");
        if (ByteBuffersKt.hasArray($this$discardUntilDelimitersImpl)) {
            return discardUntilDelimitersImplArrays($this$discardUntilDelimitersImpl, delimiter1, delimiter2);
        }
        return ScannerKt.discardUntilDelimitersImplMemory($this$discardUntilDelimitersImpl, delimiter1, delimiter2);
    }

    private static final int discardUntilDelimitersImplArrays(Buffer buffer, byte delimiter1, byte delimiter2) {
        ByteBuffer bb = buffer.getMemory();
        byte[] array = bb.array();
        int start = bb.arrayOffset() + bb.position() + buffer.getReadPosition();
        int i = start;
        int end = (buffer.getWritePosition() - buffer.getReadPosition()) + i;
        if (end <= array.length) {
            while (i < end) {
                byte v = array[i];
                if (v == delimiter1 || v == delimiter2) {
                    break;
                }
                i++;
            }
        }
        buffer.discardUntilIndex$ktor_io(i);
        return i - start;
    }

    public static final int readUntilDelimiterImpl(Buffer $this$readUntilDelimiterImpl, byte delimiter, byte[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readUntilDelimiterImpl, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        if (!(offset >= 0)) {
            throw new AssertionError("Assertion failed");
        }
        if (!(length >= 0)) {
            throw new AssertionError("Assertion failed");
        }
        if (!(offset + length <= dst.length)) {
            throw new AssertionError("Assertion failed");
        }
        if (ByteBuffersKt.hasArray($this$readUntilDelimiterImpl)) {
            return readUntilDelimiterArrays($this$readUntilDelimiterImpl, delimiter, dst, offset, length);
        }
        return readUntilDelimiterDirect($this$readUntilDelimiterImpl, delimiter, dst, offset, length);
    }

    private static final int readUntilDelimiterDirect(Buffer $this$readUntilDelimiterDirect, byte delimiter, byte[] dst, int offset, int length) {
        int readPosition$iv = $this$readUntilDelimiterDirect.getReadPosition();
        int end$iv = Math.min($this$readUntilDelimiterDirect.getWritePosition(), readPosition$iv + length);
        ByteBuffer memory$iv = $this$readUntilDelimiterDirect.getMemory();
        int index$iv = readPosition$iv;
        while (true) {
            if (index$iv >= end$iv) {
                break;
            }
            if (!(memory$iv.get(index$iv) == delimiter)) {
                index$iv++;
            } else {
                end$iv = index$iv;
                break;
            }
        }
        int index$iv2 = end$iv - readPosition$iv;
        MemoryJvmKt.m254copyTo9zorpBc(memory$iv, dst, readPosition$iv, index$iv2, offset);
        $this$readUntilDelimiterDirect.discardExact(index$iv2);
        return index$iv2;
    }

    private static final int readUntilDelimiterArrays(Buffer buffer, byte delimiter, byte[] dst, int offset, int length) {
        ByteBuffer $this$copyUntilArrays$iv = buffer.getMemory();
        int bufferOffset$iv = buffer.getReadPosition();
        int length$iv = Math.min(length, buffer.getWritePosition() - buffer.getReadPosition());
        byte[] array$iv = $this$copyUntilArrays$iv.array();
        int start$iv = $this$copyUntilArrays$iv.position() + bufferOffset$iv + $this$copyUntilArrays$iv.arrayOffset();
        int i$iv = start$iv;
        int end$iv = Math.min(length$iv, $this$copyUntilArrays$iv.remaining()) + i$iv;
        if (end$iv <= array$iv.length) {
            while (i$iv < end$iv) {
                byte it = array$iv[i$iv];
                if (it == delimiter) {
                    break;
                }
                i$iv++;
            }
        }
        int copied$iv = i$iv - start$iv;
        System.arraycopy(array$iv, start$iv, dst, offset, copied$iv);
        buffer.discardExact(copied$iv);
        return copied$iv;
    }

    public static final int readUntilDelimitersImpl(Buffer $this$readUntilDelimitersImpl, byte delimiter1, byte delimiter2, byte[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readUntilDelimitersImpl, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        if (!(offset >= 0)) {
            throw new AssertionError("Assertion failed");
        }
        if (!(length >= 0)) {
            throw new AssertionError("Assertion failed");
        }
        if (!(offset + length <= dst.length)) {
            throw new AssertionError("Assertion failed");
        }
        if (!(delimiter1 != delimiter2)) {
            throw new AssertionError("Assertion failed");
        }
        if (ByteBuffersKt.hasArray($this$readUntilDelimitersImpl)) {
            return readUntilDelimitersArrays($this$readUntilDelimitersImpl, delimiter1, delimiter2, dst, offset, length);
        }
        return readUntilDelimitersDirect($this$readUntilDelimitersImpl, delimiter1, delimiter2, dst, offset, length);
    }

    private static final int readUntilDelimitersDirect(Buffer $this$readUntilDelimitersDirect, byte delimiter1, byte delimiter2, byte[] dst, int offset, int length) {
        int readPosition$iv = $this$readUntilDelimitersDirect.getReadPosition();
        int end$iv = Math.min($this$readUntilDelimitersDirect.getWritePosition(), readPosition$iv + length);
        ByteBuffer memory$iv = $this$readUntilDelimitersDirect.getMemory();
        int index$iv = readPosition$iv;
        while (true) {
            if (index$iv >= end$iv) {
                break;
            }
            int i = memory$iv.get(index$iv);
            if (!(i == delimiter1 || i == delimiter2)) {
                index$iv++;
            } else {
                end$iv = index$iv;
                break;
            }
        }
        int index$iv2 = end$iv - readPosition$iv;
        MemoryJvmKt.m254copyTo9zorpBc(memory$iv, dst, readPosition$iv, index$iv2, offset);
        $this$readUntilDelimitersDirect.discardExact(index$iv2);
        return index$iv2;
    }

    private static final int readUntilDelimitersArrays(Buffer $this$readUntilDelimitersArrays, byte delimiter1, byte delimiter2, byte[] dst, int offset, int length) {
        ByteBuffer $this$copyUntilArrays$iv = $this$readUntilDelimitersArrays.getMemory();
        int bufferOffset$iv = $this$readUntilDelimitersArrays.getReadPosition();
        int length$iv = Math.min(length, $this$readUntilDelimitersArrays.getWritePosition() - $this$readUntilDelimitersArrays.getReadPosition());
        byte[] array$iv = $this$copyUntilArrays$iv.array();
        int start$iv = $this$copyUntilArrays$iv.position() + bufferOffset$iv + $this$copyUntilArrays$iv.arrayOffset();
        int i$iv = start$iv;
        int end$iv = Math.min(length$iv, $this$copyUntilArrays$iv.remaining()) + i$iv;
        if (end$iv <= array$iv.length) {
            while (i$iv < end$iv) {
                byte it = array$iv[i$iv];
                if (it == delimiter1 || it == delimiter2) {
                    break;
                }
                i$iv++;
            }
        }
        int copied$iv = i$iv - start$iv;
        System.arraycopy(array$iv, start$iv, dst, offset, copied$iv);
        $this$readUntilDelimitersArrays.discardExact(copied$iv);
        return copied$iv;
    }

    public static final int readUntilDelimiterImpl(Buffer $this$readUntilDelimiterImpl, byte delimiter, Output dst) {
        Intrinsics.checkNotNullParameter($this$readUntilDelimiterImpl, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        if (ByteBuffersKt.hasArray($this$readUntilDelimiterImpl)) {
            return readUntilDelimiterArrays($this$readUntilDelimiterImpl, delimiter, dst);
        }
        return readUntilDelimiterDirect($this$readUntilDelimiterImpl, delimiter, dst);
    }

    public static final int readUntilDelimiterDirect(Buffer $this$readUntilDelimiterDirect, byte delimiter, Output dst) {
        Intrinsics.checkNotNullParameter($this$readUntilDelimiterDirect, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int index$iv = $this$readUntilDelimiterDirect.getReadPosition();
        int end$iv = $this$readUntilDelimiterDirect.getWritePosition();
        ByteBuffer memory$iv = $this$readUntilDelimiterDirect.getMemory();
        while (index$iv != end$iv) {
            if (memory$iv.get(index$iv) == delimiter) {
                break;
            }
            index$iv++;
        }
        int size$iv = index$iv - $this$readUntilDelimiterDirect.getReadPosition();
        OutputKt.writeFully(dst, $this$readUntilDelimiterDirect, size$iv);
        return size$iv;
    }

    public static final int readUntilDelimiterArrays(Buffer $this$readUntilDelimiterArrays, byte delimiter, Output dst) {
        Intrinsics.checkNotNullParameter($this$readUntilDelimiterArrays, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        ByteBuffer bb$iv = $this$readUntilDelimiterArrays.getMemory();
        byte[] array = bb$iv.array();
        int i$iv = bb$iv.position() + bb$iv.arrayOffset() + $this$readUntilDelimiterArrays.getReadPosition();
        int i$iv2 = bb$iv.position();
        int sourceEndPosition$iv = $this$readUntilDelimiterArrays.getWritePosition() + i$iv2 + bb$iv.arrayOffset();
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead(dst, 1, null);
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        int i$iv3 = i$iv;
        int copiedTotal$iv = 0;
        while (true) {
            try {
                Buffer chunk$iv = tail$iv$iv2;
                int start$iv = i$iv3;
                int end$iv = Math.min(i$iv3 + (chunk$iv.getLimit() - chunk$iv.getWritePosition()), sourceEndPosition$iv);
                boolean z = false;
                if (end$iv <= array.length) {
                    while (i$iv3 < end$iv) {
                        try {
                            byte it = array[i$iv3];
                            byte it2 = it == delimiter ? (byte) 1 : (byte) 0;
                            if (it2 != 0) {
                                break;
                            }
                            i$iv3++;
                        } catch (Throwable th) {
                            th = th;
                            dst.afterHeadWrite();
                            throw th;
                        }
                    }
                }
                int size$iv = i$iv3 - start$iv;
                Intrinsics.checkNotNullExpressionValue(array, "array");
                BufferPrimitivesKt.writeFully(chunk$iv, array, start$iv, size$iv);
                copiedTotal$iv += size$iv;
                if (!(chunk$iv.getLimit() > chunk$iv.getWritePosition()) && i$iv3 < sourceEndPosition$iv) {
                    z = true;
                }
                if (z) {
                    tail$iv$iv2 = UnsafeKt.prepareWriteHead(dst, 1, tail$iv$iv2);
                } else {
                    dst.afterHeadWrite();
                    $this$readUntilDelimiterArrays.discardUntilIndex$ktor_io(i$iv3);
                    return copiedTotal$iv;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public static final int readUntilDelimitersImpl(Buffer $this$readUntilDelimitersImpl, byte delimiter1, byte delimiter2, Output dst) {
        Intrinsics.checkNotNullParameter($this$readUntilDelimitersImpl, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        if (!(delimiter1 != delimiter2)) {
            throw new AssertionError("Assertion failed");
        }
        if (ByteBuffersKt.hasArray($this$readUntilDelimitersImpl)) {
            return readUntilDelimitersArrays($this$readUntilDelimitersImpl, delimiter1, delimiter2, dst);
        }
        return readUntilDelimitersDirect($this$readUntilDelimitersImpl, delimiter1, delimiter2, dst);
    }

    public static final int readUntilDelimitersDirect(Buffer $this$readUntilDelimitersDirect, byte delimiter1, byte delimiter2, Output dst) {
        Intrinsics.checkNotNullParameter($this$readUntilDelimitersDirect, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int index$iv = $this$readUntilDelimitersDirect.getReadPosition();
        int end$iv = $this$readUntilDelimitersDirect.getWritePosition();
        ByteBuffer memory$iv = $this$readUntilDelimitersDirect.getMemory();
        while (index$iv != end$iv) {
            int i = memory$iv.get(index$iv);
            if (i == delimiter1 || i == delimiter2) {
                break;
            }
            index$iv++;
        }
        int size$iv = index$iv - $this$readUntilDelimitersDirect.getReadPosition();
        OutputKt.writeFully(dst, $this$readUntilDelimitersDirect, size$iv);
        return size$iv;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0079 A[LOOP:1: B:7:0x0062->B:16:0x0079, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0087 A[EDGE_INSN: B:17:0x0087->B:18:0x0087 BREAK  A[LOOP:1: B:7:0x0062->B:16:0x0079], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int readUntilDelimitersArrays(io.ktor.utils.io.core.Buffer r21, byte r22, byte r23, io.ktor.utils.io.core.Output r24) {
        /*
            java.lang.String r0 = "<this>"
            r1 = r21
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "dst"
            r2 = r24
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            r3 = r21
            r4 = 0
            java.nio.ByteBuffer r5 = r3.getMemory()
            byte[] r6 = r5.array()
            r0 = 0
            int r7 = r5.position()
            int r8 = r5.arrayOffset()
            int r7 = r7 + r8
            int r8 = r3.getReadPosition()
            int r7 = r7 + r8
            int r0 = r5.position()
            int r8 = r5.arrayOffset()
            int r0 = r0 + r8
            int r8 = r3.getWritePosition()
            int r8 = r8 + r0
            r0 = 0
            r9 = r24
            r10 = 0
            r11 = 0
            r12 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r11 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r9, r12, r11)
            r13 = r11
            r11 = r7
            r7 = r0
        L43:
            r0 = r13
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch: java.lang.Throwable -> Lc7
            r14 = 0
            r15 = r11
            r16 = r0
            r17 = 0
            int r18 = r16.getLimit()     // Catch: java.lang.Throwable -> Lc7
            int r19 = r16.getWritePosition()     // Catch: java.lang.Throwable -> Lc7
            int r18 = r18 - r19
            int r12 = r11 + r18
            int r12 = java.lang.Math.min(r12, r8)     // Catch: java.lang.Throwable -> Lc7
            int r1 = r6.length     // Catch: java.lang.Throwable -> Lc7
            r17 = 0
            if (r12 > r1) goto L85
        L62:
            if (r11 >= r12) goto L82
            r1 = r6[r11]     // Catch: java.lang.Throwable -> L7e
            r18 = 0
            r2 = r22
            if (r1 == r2) goto L74
            r2 = r23
            if (r1 != r2) goto L71
            goto L76
        L71:
            r1 = r17
            goto L77
        L74:
            r2 = r23
        L76:
            r1 = 1
        L77:
            if (r1 != 0) goto L87
            int r11 = r11 + 1
            r2 = r24
            goto L62
        L7e:
            r0 = move-exception
            r2 = r23
            goto Lc8
        L82:
            r2 = r23
            goto L87
        L85:
            r2 = r23
        L87:
            int r1 = r11 - r15
            java.lang.String r2 = "array"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)     // Catch: java.lang.Throwable -> Lc7
            io.ktor.utils.io.core.BufferPrimitivesKt.writeFully(r0, r6, r15, r1)     // Catch: java.lang.Throwable -> Lc7
            int r7 = r7 + r1
            r2 = r0
            r18 = 0
            r19 = r0
            int r0 = r2.getLimit()     // Catch: java.lang.Throwable -> Lc7
            r20 = r1
            int r1 = r2.getWritePosition()     // Catch: java.lang.Throwable -> Lc7
            if (r0 <= r1) goto La5
            r0 = 1
            goto La7
        La5:
            r0 = r17
        La7:
            if (r0 != 0) goto Lae
            if (r11 >= r8) goto Lae
            r17 = 1
            goto Laf
        Lae:
        Laf:
            if (r17 == 0) goto Lbd
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r9, r0, r13)     // Catch: java.lang.Throwable -> Lc7
            r13 = r1
            r1 = r21
            r2 = r24
            r12 = r0
            goto L43
        Lbd:
            r9.afterHeadWrite()
            r3.discardUntilIndex$ktor_io(r11)
            return r7
        Lc7:
            r0 = move-exception
        Lc8:
            r9.afterHeadWrite()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.ScannerJVMKt.readUntilDelimitersArrays(io.ktor.utils.io.core.Buffer, byte, byte, io.ktor.utils.io.core.Output):int");
    }

    private static final int copyUntilDirect(ByteBuffer $this$copyUntilDirect, Function1<? super Byte, Boolean> function1, byte[] dst, int offset, int length) {
        int start = $this$copyUntilDirect.position();
        int i = start;
        int end = i + length;
        while (i < $this$copyUntilDirect.limit() && i < end && !function1.invoke(Byte.valueOf($this$copyUntilDirect.get(i))).booleanValue()) {
            i++;
        }
        int copied = i - start;
        $this$copyUntilDirect.get(dst, offset, copied);
        return copied;
    }

    private static final int copyUntilArrays(ByteBuffer $this$copyUntilArrays, Function1<? super Byte, Boolean> function1, int bufferOffset, byte[] dst, int offset, int length) {
        byte[] array = $this$copyUntilArrays.array();
        int start = $this$copyUntilArrays.position() + bufferOffset + $this$copyUntilArrays.arrayOffset();
        int i = start;
        int end = Math.min(length, $this$copyUntilArrays.remaining()) + i;
        if (end <= array.length) {
            while (i < end && !function1.invoke(Byte.valueOf(array[i])).booleanValue()) {
                i++;
            }
        }
        int copied = i - start;
        System.arraycopy(array, start, dst, offset, copied);
        return copied;
    }

    private static final int copyUntilArrays(Buffer $this$copyUntilArrays, Function1<? super Byte, Boolean> function1, Output dst) {
        Buffer chunk;
        int limit;
        int $i$f$copyUntilArrays;
        int $i$f$copyUntilArrays2 = 0;
        ByteBuffer bb = $this$copyUntilArrays.getMemory();
        byte[] array = bb.array();
        int i = bb.position() + bb.arrayOffset() + $this$copyUntilArrays.getReadPosition();
        int i2 = bb.position();
        int sourceEndPosition = $this$copyUntilArrays.getWritePosition() + i2 + bb.arrayOffset();
        ChunkBuffer tail$iv = UnsafeKt.prepareWriteHead(dst, 1, null);
        ChunkBuffer tail$iv2 = tail$iv;
        int i3 = i;
        int copiedTotal = 0;
        while (true) {
            try {
                chunk = tail$iv2;
                int start = i3;
                int end = Math.min((chunk.getLimit() - chunk.getWritePosition()) + i3, sourceEndPosition);
                if (end <= array.length) {
                    while (i3 < end) {
                        try {
                            try {
                                if (function1.invoke(Byte.valueOf(array[i3])).booleanValue()) {
                                    break;
                                }
                                i3++;
                            } catch (Throwable th) {
                                th = th;
                                dst.afterHeadWrite();
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
                int size = i3 - start;
                try {
                    Intrinsics.checkNotNullExpressionValue(array, "array");
                    BufferPrimitivesKt.writeFully(chunk, array, start, size);
                    copiedTotal += size;
                    limit = chunk.getLimit();
                    $i$f$copyUntilArrays = $i$f$copyUntilArrays2;
                } catch (Throwable th3) {
                    th = th3;
                    dst.afterHeadWrite();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
            try {
                int $i$f$copyUntilArrays3 = chunk.getWritePosition();
                boolean z = false;
                if (!(limit > $i$f$copyUntilArrays3) && i3 < sourceEndPosition) {
                    z = true;
                }
                if (z) {
                    tail$iv2 = UnsafeKt.prepareWriteHead(dst, 1, tail$iv2);
                    $i$f$copyUntilArrays2 = $i$f$copyUntilArrays;
                } else {
                    dst.afterHeadWrite();
                    $this$copyUntilArrays.discardUntilIndex$ktor_io(i3);
                    return copiedTotal;
                }
            } catch (Throwable th5) {
                th = th5;
                dst.afterHeadWrite();
                throw th;
            }
        }
    }
}
