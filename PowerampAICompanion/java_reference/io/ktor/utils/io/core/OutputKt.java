package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Output.kt */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007\u001a*\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007\u001a\u001c\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u001a/\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a/\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0017\u001a\u001c\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00182\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00192\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u001a2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u001b2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u001c2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u001d2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a&\u0010\u0010\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u001e2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u001a[\u0010\u001f\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072<\u0010 \u001a8\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u000b0!H\u0082\b\u001a\u0082\u0001\u0010\u001f\u001a\u00020\u000b*\u00020\u00032\u0006\u0010&\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2`\u0010 \u001a\\\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b()\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u000b0'H\u0082\bø\u0001\u0001\u001ac\u0010*\u001a\u00020\u000b*\u00020\u00032\u0006\u0010+\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072<\u0010 \u001a8\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u000b0!H\u0082\b\u001a$\u0010,\u001a\u00020\u000b*\u00020\u00032\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020.0-H\u0080\bø\u0001\u0002\u001a.\u0010/\u001a\u00020\u000b*\u00020\u00032\b\b\u0002\u00100\u001a\u00020\u00072\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00070-H\u0080\bø\u0001\u0002\u0082\u0002\u0012\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u00061"}, d2 = {"append", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "Lio/ktor/utils/io/core/Output;", "csq", "", "start", "", "end", "", "fill", "", "times", "", "value", "", "writeFully", "src", "Lio/ktor/utils/io/bits/Memory;", TypedValues.CycleType.S_WAVE_OFFSET, "length", "writeFully-UAd2zVI", "(Lio/ktor/utils/io/core/Output;Ljava/nio/ByteBuffer;II)V", "(Lio/ktor/utils/io/core/Output;Ljava/nio/ByteBuffer;JJ)V", "Lio/ktor/utils/io/core/Buffer;", "", "", "", "", "", "", "writeFullyBytesTemplate", "block", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "currentOffset", "count", "initialOffset", "Lkotlin/Function4;", "destination", "destinationOffset", "writeFullyTemplate", "componentSize", "writeWhile", "Lkotlin/Function1;", "", "writeWhileSize", "initialSize", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OutputKt {
    public static /* synthetic */ Appendable append$default(Output output, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return append(output, charSequence, i, i2);
    }

    public static final Appendable append(Output $this$append, CharSequence csq, int start, int end) {
        Intrinsics.checkNotNullParameter($this$append, "<this>");
        Intrinsics.checkNotNullParameter(csq, "csq");
        return $this$append.append(csq, start, end);
    }

    public static /* synthetic */ Appendable append$default(Output output, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return append(output, cArr, i, i2);
    }

    public static final Appendable append(Output $this$append, char[] csq, int start, int end) {
        Intrinsics.checkNotNullParameter($this$append, "<this>");
        Intrinsics.checkNotNullParameter(csq, "csq");
        return $this$append.append(csq, start, end);
    }

    public static /* synthetic */ void writeFully$default(Output output, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        writeFully(output, bArr, i, i2);
    }

    public static final void writeFully(Output $this$writeFully, byte[] src, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFully, 1, null);
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        int remaining$iv = length;
        int remaining$iv2 = offset;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min(remaining$iv, buffer$iv.getLimit() - buffer$iv.getWritePosition());
                int currentOffset = remaining$iv2;
                BufferPrimitivesKt.writeFully(buffer$iv, src, currentOffset, size$iv);
                remaining$iv2 += size$iv;
                remaining$iv -= size$iv;
                if (!(remaining$iv > 0)) {
                    return;
                } else {
                    tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFully, 1, tail$iv$iv2);
                }
            } finally {
                $this$writeFully.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFully$default(Output output, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        writeFully(output, sArr, i, i2);
    }

    public static final void writeFully(Output $this$writeFully, short[] src, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFully, 2, null);
        int remaining$iv = offset;
        int size$iv$iv = 0;
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        int remaining$iv2 = length;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min(remaining$iv2, buffer$iv.getLimit() - buffer$iv.getWritePosition());
                int currentOffset = remaining$iv;
                BufferPrimitivesKt.writeFully(buffer$iv, src, currentOffset, size$iv);
                remaining$iv += size$iv;
                remaining$iv2 -= size$iv;
                size$iv$iv = remaining$iv2 * 2;
                if (size$iv$iv <= 0) {
                    return;
                } else {
                    tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFully, size$iv$iv, tail$iv$iv2);
                }
            } finally {
                $this$writeFully.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFully$default(Output output, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        writeFully(output, iArr, i, i2);
    }

    public static final void writeFully(Output $this$writeFully, int[] src, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFully, 4, null);
        int remaining$iv = offset;
        int size$iv$iv = 0;
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        int remaining$iv2 = length;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min(remaining$iv2, buffer$iv.getLimit() - buffer$iv.getWritePosition());
                int currentOffset = remaining$iv;
                BufferPrimitivesKt.writeFully(buffer$iv, src, currentOffset, size$iv);
                remaining$iv += size$iv;
                remaining$iv2 -= size$iv;
                size$iv$iv = remaining$iv2 * 4;
                if (size$iv$iv <= 0) {
                    return;
                } else {
                    tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFully, size$iv$iv, tail$iv$iv2);
                }
            } finally {
                $this$writeFully.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFully$default(Output output, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        writeFully(output, jArr, i, i2);
    }

    public static final void writeFully(Output $this$writeFully, long[] src, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFully, 8, null);
        int remaining$iv = offset;
        int size$iv$iv = 0;
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        int remaining$iv2 = length;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min(remaining$iv2, buffer$iv.getLimit() - buffer$iv.getWritePosition());
                int currentOffset = remaining$iv;
                BufferPrimitivesKt.writeFully(buffer$iv, src, currentOffset, size$iv);
                remaining$iv += size$iv;
                remaining$iv2 -= size$iv;
                size$iv$iv = remaining$iv2 * 8;
                if (size$iv$iv <= 0) {
                    return;
                } else {
                    tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFully, size$iv$iv, tail$iv$iv2);
                }
            } finally {
                $this$writeFully.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFully$default(Output output, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        writeFully(output, fArr, i, i2);
    }

    public static final void writeFully(Output $this$writeFully, float[] src, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFully, 4, null);
        int remaining$iv = offset;
        int size$iv$iv = 0;
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        int remaining$iv2 = length;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min(remaining$iv2, buffer$iv.getLimit() - buffer$iv.getWritePosition());
                int currentOffset = remaining$iv;
                BufferPrimitivesKt.writeFully(buffer$iv, src, currentOffset, size$iv);
                remaining$iv += size$iv;
                remaining$iv2 -= size$iv;
                size$iv$iv = remaining$iv2 * 4;
                if (size$iv$iv <= 0) {
                    return;
                } else {
                    tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFully, size$iv$iv, tail$iv$iv2);
                }
            } finally {
                $this$writeFully.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFully$default(Output output, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        writeFully(output, dArr, i, i2);
    }

    public static final void writeFully(Output $this$writeFully, double[] src, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFully, 8, null);
        int remaining$iv = offset;
        int size$iv$iv = 0;
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        int remaining$iv2 = length;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min(remaining$iv2, buffer$iv.getLimit() - buffer$iv.getWritePosition());
                int currentOffset = remaining$iv;
                BufferPrimitivesKt.writeFully(buffer$iv, src, currentOffset, size$iv);
                remaining$iv += size$iv;
                remaining$iv2 -= size$iv;
                size$iv$iv = remaining$iv2 * 8;
                if (size$iv$iv <= 0) {
                    return;
                } else {
                    tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFully, size$iv$iv, tail$iv$iv2);
                }
            } finally {
                $this$writeFully.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFully$default(Output output, Buffer this_$iv, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = this_$iv.getWritePosition() - this_$iv.getReadPosition();
        }
        writeFully(output, this_$iv, i);
    }

    public static final void writeFully(Output $this$writeFully, Buffer src, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead($this$writeFully, 1, null);
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        int remaining$iv = length;
        int remaining$iv2 = 0;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                int size$iv = Math.min(remaining$iv, buffer$iv.getLimit() - buffer$iv.getWritePosition());
                BufferPrimitivesKt.writeFully(buffer$iv, src, size$iv);
                remaining$iv2 += size$iv;
                remaining$iv -= size$iv;
                if (!(remaining$iv > 0)) {
                    return;
                } else {
                    tail$iv$iv2 = UnsafeKt.prepareWriteHead($this$writeFully, 1, tail$iv$iv2);
                }
            } finally {
                $this$writeFully.afterHeadWrite();
            }
        }
    }

    /* renamed from: writeFully-UAd2zVI, reason: not valid java name */
    public static final void m451writeFullyUAd2zVI(Output writeFully, ByteBuffer src, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(src, "src");
        m452writeFullyUAd2zVI(writeFully, src, offset, length);
    }

    /* renamed from: writeFully-UAd2zVI, reason: not valid java name */
    public static final void m452writeFullyUAd2zVI(Output writeFully, ByteBuffer src, long offset, long length) {
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        ByteBuffer src2 = src;
        Intrinsics.checkNotNullParameter(src2, "src");
        ChunkBuffer tail$iv$iv = UnsafeKt.prepareWriteHead(writeFully, 1, null);
        ChunkBuffer tail$iv$iv2 = tail$iv$iv;
        long currentOffset$iv = offset;
        long remaining$iv = length;
        while (true) {
            try {
                Buffer buffer$iv = tail$iv$iv2;
                long size$iv = Math.min(remaining$iv, buffer$iv.getLimit() - buffer$iv.getWritePosition());
                ByteBuffer memory = buffer$iv.getMemory();
                long destinationOffset = buffer$iv.getWritePosition();
                long remaining$iv2 = remaining$iv;
                long sourceOffset = currentOffset$iv;
                ChunkBuffer tail$iv$iv3 = tail$iv$iv2;
                try {
                    Memory.m237copyToJT6ljtQ(src2, memory, sourceOffset, size$iv, destinationOffset);
                    buffer$iv.commitWritten((int) size$iv);
                    currentOffset$iv += size$iv;
                    remaining$iv = remaining$iv2 - size$iv;
                    if (remaining$iv > 0) {
                        try {
                            tail$iv$iv2 = UnsafeKt.prepareWriteHead(writeFully, 1, tail$iv$iv3);
                            src2 = src;
                        } catch (Throwable th) {
                            th = th;
                            writeFully.afterHeadWrite();
                            throw th;
                        }
                    } else {
                        writeFully.afterHeadWrite();
                        return;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    public static /* synthetic */ void fill$default(Output output, long j, byte b, int i, Object obj) {
        if ((i & 2) != 0) {
            b = 0;
        }
        fill(output, j, b);
    }

    public static final void fill(Output $this$fill, long times, byte value) {
        Intrinsics.checkNotNullParameter($this$fill, "<this>");
        long written = 0;
        ChunkBuffer tail$iv = UnsafeKt.prepareWriteHead($this$fill, 1, null);
        while (true) {
            try {
                Buffer buffer = tail$iv;
                int partTimes = (int) Math.min(buffer.getLimit() - buffer.getWritePosition(), times - written);
                BufferCompatibilityKt.fill(buffer, partTimes, value);
                written += partTimes;
                if (!(written < times)) {
                    return;
                } else {
                    tail$iv = UnsafeKt.prepareWriteHead($this$fill, 1, tail$iv);
                }
            } finally {
                $this$fill.afterHeadWrite();
            }
        }
    }

    public static final void writeWhile(Output $this$writeWhile, Function1<? super Buffer, Boolean> block) {
        Intrinsics.checkNotNullParameter($this$writeWhile, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer tail = UnsafeKt.prepareWriteHead($this$writeWhile, 1, null);
        while (block.invoke(tail).booleanValue()) {
            try {
                tail = UnsafeKt.prepareWriteHead($this$writeWhile, 1, tail);
            } finally {
                $this$writeWhile.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeWhileSize$default(Output $this$writeWhileSize_u24default, int initialSize, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            initialSize = 1;
        }
        Intrinsics.checkNotNullParameter($this$writeWhileSize_u24default, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer tail = UnsafeKt.prepareWriteHead($this$writeWhileSize_u24default, initialSize, null);
        while (true) {
            try {
                int size = ((Number) block.invoke(tail)).intValue();
                if (size > 0) {
                    tail = UnsafeKt.prepareWriteHead($this$writeWhileSize_u24default, size, tail);
                } else {
                    return;
                }
            } finally {
                $this$writeWhileSize_u24default.afterHeadWrite();
            }
        }
    }

    public static final void writeWhileSize(Output $this$writeWhileSize, int initialSize, Function1<? super Buffer, Integer> block) {
        Intrinsics.checkNotNullParameter($this$writeWhileSize, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer tail = UnsafeKt.prepareWriteHead($this$writeWhileSize, initialSize, null);
        while (true) {
            try {
                int size = block.invoke(tail).intValue();
                if (size > 0) {
                    tail = UnsafeKt.prepareWriteHead($this$writeWhileSize, size, tail);
                } else {
                    return;
                }
            } finally {
                $this$writeWhileSize.afterHeadWrite();
            }
        }
    }

    private static final void writeFullyBytesTemplate(Output $this$writeFullyBytesTemplate, int offset, int length, Function3<? super Buffer, ? super Integer, ? super Integer, Unit> function3) {
        ChunkBuffer tail$iv = UnsafeKt.prepareWriteHead($this$writeFullyBytesTemplate, 1, null);
        ChunkBuffer tail$iv2 = tail$iv;
        int remaining = length;
        int remaining2 = offset;
        while (true) {
            try {
                Buffer buffer = tail$iv2;
                int size = Math.min(remaining, buffer.getLimit() - buffer.getWritePosition());
                try {
                    function3.invoke(buffer, Integer.valueOf(remaining2), Integer.valueOf(size));
                    remaining2 += size;
                    remaining -= size;
                    if (remaining > 0) {
                        tail$iv2 = UnsafeKt.prepareWriteHead($this$writeFullyBytesTemplate, 1, tail$iv2);
                    } else {
                        $this$writeFullyBytesTemplate.afterHeadWrite();
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    $this$writeFullyBytesTemplate.afterHeadWrite();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private static final void writeFullyBytesTemplate(Output $this$writeFullyBytesTemplate, long initialOffset, long length, Function4<? super Memory, ? super Long, ? super Long, ? super Long, Unit> function4) {
        int $i$f$writeFullyBytesTemplate = 0;
        ChunkBuffer tail$iv = UnsafeKt.prepareWriteHead($this$writeFullyBytesTemplate, 1, null);
        long remaining = length;
        long currentOffset = initialOffset;
        ChunkBuffer tail$iv2 = tail$iv;
        while (true) {
            try {
                Buffer buffer = tail$iv2;
                long size = Math.min(remaining, buffer.getLimit() - buffer.getWritePosition());
                int $i$f$writeFullyBytesTemplate2 = $i$f$writeFullyBytesTemplate;
                try {
                    function4.invoke(Memory.m234boximpl(buffer.getMemory()), Long.valueOf(buffer.getWritePosition()), Long.valueOf(currentOffset), Long.valueOf(size));
                    buffer.commitWritten((int) size);
                    currentOffset += size;
                    remaining -= size;
                    if (remaining > 0) {
                        tail$iv2 = UnsafeKt.prepareWriteHead($this$writeFullyBytesTemplate, 1, tail$iv2);
                        $i$f$writeFullyBytesTemplate = $i$f$writeFullyBytesTemplate2;
                    } else {
                        $this$writeFullyBytesTemplate.afterHeadWrite();
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    $this$writeFullyBytesTemplate.afterHeadWrite();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private static final void writeFullyTemplate(Output $this$writeFullyTemplate, int componentSize, int offset, int length, Function3<? super Buffer, ? super Integer, ? super Integer, Unit> function3) {
        ChunkBuffer tail$iv = UnsafeKt.prepareWriteHead($this$writeFullyTemplate, componentSize, null);
        int remaining = offset;
        ChunkBuffer tail$iv2 = tail$iv;
        int remaining2 = length;
        while (true) {
            try {
                Buffer buffer = tail$iv2;
                int size = Math.min(remaining2, buffer.getLimit() - buffer.getWritePosition());
                try {
                    function3.invoke(buffer, Integer.valueOf(remaining), Integer.valueOf(size));
                    remaining += size;
                    remaining2 -= size;
                    int i = remaining2 * componentSize;
                    if (i > 0) {
                        tail$iv2 = UnsafeKt.prepareWriteHead($this$writeFullyTemplate, i, tail$iv2);
                    } else {
                        $this$writeFullyTemplate.afterHeadWrite();
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    $this$writeFullyTemplate.afterHeadWrite();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }
}
