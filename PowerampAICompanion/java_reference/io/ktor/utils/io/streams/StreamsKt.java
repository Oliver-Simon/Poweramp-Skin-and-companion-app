package io.ktor.utils.io.streams;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.InputArraysKt;
import io.ktor.utils.io.core.Output;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: Streams.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0007\u001a\u0012\u0010\b\u001a\u00020\u0004*\u00020\u00032\u0006\u0010\t\u001a\u00020\n\u001a\u0012\u0010\u000b\u001a\u00020\u0004*\u00020\u00032\u0006\u0010\t\u001a\u00020\n\u001a\u0012\u0010\f\u001a\u00020\u0004*\u00020\u00032\u0006\u0010\t\u001a\u00020\n\u001a\u001c\u0010\r\u001a\u00020\u0004*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nH\u0002\u001a\n\u0010\u0010\u001a\u00020\u0011*\u00020\u0004\u001a#\u0010\u0012\u001a\u00020\u0013*\u00020\u00062\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00130\u0015¢\u0006\u0002\b\u0016\u001a\u0012\u0010\u0012\u001a\u00020\u0013*\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0004\u001a\n\u0010\u0018\u001a\u00020\u0019*\u00020\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"SkipBuffer", "", "inputStream", "Ljava/io/InputStream;", "Lio/ktor/utils/io/core/ByteReadPacket;", "outputStream", "Ljava/io/OutputStream;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "readPacketAtLeast", "n", "", "readPacketAtMost", "readPacketExact", "readPacketImpl", "min", "max", "readerUTF8", "Ljava/io/Reader;", "writePacket", "", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "packet", "writerUTF8", "Ljava/io/Writer;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class StreamsKt {
    private static final char[] SkipBuffer = new char[8192];

    public static final void writePacket(OutputStream $this$writePacket, Function1<? super BytePacketBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter($this$writePacket, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            builder.invoke(builder$iv);
            writePacket($this$writePacket, builder$iv.build());
        } catch (Throwable t$iv) {
            builder$iv.release();
            throw t$iv;
        }
    }

    public static final void writePacket(OutputStream $this$writePacket, ByteReadPacket packet) {
        Intrinsics.checkNotNullParameter($this$writePacket, "<this>");
        Intrinsics.checkNotNullParameter(packet, "packet");
        long s = packet.getRemaining();
        if (s == 0) {
            return;
        }
        byte[] buffer = new byte[(int) RangesKt.coerceAtMost(s, 4096L)];
        while (!packet.getEndOfInput()) {
            try {
                int size = InputArraysKt.readAvailable$default((Input) packet, buffer, 0, 0, 6, (Object) null);
                $this$writePacket.write(buffer, 0, size);
            } finally {
                packet.release();
            }
        }
    }

    public static final ByteReadPacket readPacketExact(InputStream $this$readPacketExact, long n) {
        Intrinsics.checkNotNullParameter($this$readPacketExact, "<this>");
        return readPacketImpl($this$readPacketExact, n, n);
    }

    public static final ByteReadPacket readPacketAtLeast(InputStream $this$readPacketAtLeast, long n) {
        Intrinsics.checkNotNullParameter($this$readPacketAtLeast, "<this>");
        return readPacketImpl($this$readPacketAtLeast, n, Long.MAX_VALUE);
    }

    public static final ByteReadPacket readPacketAtMost(InputStream $this$readPacketAtMost, long n) {
        Intrinsics.checkNotNullParameter($this$readPacketAtMost, "<this>");
        return readPacketImpl($this$readPacketAtMost, 1L, n);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0082, code lost:
    
        throw new java.io.EOFException("Premature end of stream: was read " + r10 + " bytes of " + r17);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final io.ktor.utils.io.core.ByteReadPacket readPacketImpl(java.io.InputStream r16, long r17, long r19) {
        /*
            r1 = r17
            r3 = r19
            r5 = 0
            int r0 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            r7 = 1
            r8 = 0
            if (r0 < 0) goto Le
            r0 = r7
            goto Lf
        Le:
            r0 = r8
        Lf:
            if (r0 == 0) goto Lb6
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r0 > 0) goto L17
            r0 = r7
            goto L18
        L17:
            r0 = r8
        L18:
            if (r0 == 0) goto L8c
            r9 = 4096(0x1000, double:2.0237E-320)
            long r9 = kotlin.ranges.RangesKt.coerceAtMost(r3, r9)
            int r0 = (int) r9
            byte[] r9 = new byte[r0]
            io.ktor.utils.io.core.BytePacketBuilder r0 = new io.ktor.utils.io.core.BytePacketBuilder
            r10 = 0
            r0.<init>(r10, r7, r10)
            r7 = r0
            r10 = 0
        L2d:
            int r0 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r0 < 0) goto L3f
            int r0 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r0 != 0) goto L3a
            int r0 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r0 != 0) goto L3a
            goto L3f
        L3a:
            io.ktor.utils.io.core.ByteReadPacket r0 = r7.build()
            return r0
        L3f:
            long r12 = r3 - r10
            r14 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r12 = java.lang.Math.min(r12, r14)     // Catch: java.lang.Throwable -> L85
            int r0 = (int) r12     // Catch: java.lang.Throwable -> L85
            int r12 = r9.length     // Catch: java.lang.Throwable -> L85
            int r12 = java.lang.Math.min(r0, r12)     // Catch: java.lang.Throwable -> L85
            r13 = r16
            int r12 = r13.read(r9, r8, r12)     // Catch: java.lang.Throwable -> L83
            r14 = -1
            if (r12 == r14) goto L60
            long r14 = (long) r12     // Catch: java.lang.Throwable -> L83
            long r10 = r10 + r14
            r14 = r7
            io.ktor.utils.io.core.Output r14 = (io.ktor.utils.io.core.Output) r14     // Catch: java.lang.Throwable -> L83
            io.ktor.utils.io.core.OutputKt.writeFully(r14, r9, r8, r12)     // Catch: java.lang.Throwable -> L83
            goto L2d
        L60:
            java.io.EOFException r5 = new java.io.EOFException     // Catch: java.lang.Throwable -> L83
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L83
            r6.<init>()     // Catch: java.lang.Throwable -> L83
            java.lang.String r8 = "Premature end of stream: was read "
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch: java.lang.Throwable -> L83
            java.lang.StringBuilder r6 = r6.append(r10)     // Catch: java.lang.Throwable -> L83
            java.lang.String r8 = " bytes of "
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch: java.lang.Throwable -> L83
            java.lang.StringBuilder r6 = r6.append(r1)     // Catch: java.lang.Throwable -> L83
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L83
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L83
            throw r5     // Catch: java.lang.Throwable -> L83
        L83:
            r0 = move-exception
            goto L88
        L85:
            r0 = move-exception
            r13 = r16
        L88:
            r7.release()
            throw r0
        L8c:
            r13 = r16
            r0 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "min shouldn't be greater than max: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r1)
            java.lang.String r6 = " > "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r0 = r5.toString()
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        Lb6:
            r13 = r16
            r0 = 0
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "min shouldn't be negative"
            java.lang.String r5 = r5.toString()
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.streams.StreamsKt.readPacketImpl(java.io.InputStream, long, long):io.ktor.utils.io.core.ByteReadPacket");
    }

    public static final InputStream inputStream(final ByteReadPacket $this$inputStream) {
        Intrinsics.checkNotNullParameter($this$inputStream, "<this>");
        return new InputStream() { // from class: io.ktor.utils.io.streams.StreamsKt$inputStream$1
            @Override // java.io.InputStream
            public int read() {
                ByteReadPacket $this$isEmpty$iv = ByteReadPacket.this;
                if ($this$isEmpty$iv.getEndOfInput()) {
                    return -1;
                }
                return ByteReadPacket.this.readByte() & 255;
            }

            @Override // java.io.InputStream
            public int available() {
                long $this$coerceAtMostMaxInt$iv = ByteReadPacket.this.getRemaining();
                return (int) Math.min($this$coerceAtMostMaxInt$iv, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                ByteReadPacket.this.release();
            }
        };
    }

    public static final Reader readerUTF8(final ByteReadPacket $this$readerUTF8) {
        Intrinsics.checkNotNullParameter($this$readerUTF8, "<this>");
        return new Reader() { // from class: io.ktor.utils.io.streams.StreamsKt$readerUTF8$1
            @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                ByteReadPacket.this.release();
            }

            @Override // java.io.Reader
            public long skip(long n) {
                char[] buffer;
                long skipped = 0;
                buffer = StreamsKt.SkipBuffer;
                int bufferSize = buffer.length;
                while (skipped < n) {
                    int size = (int) Math.min(bufferSize, n - skipped);
                    int rc = read(buffer, 0, size);
                    if (rc == -1) {
                        break;
                    }
                    skipped += rc;
                }
                return skipped;
            }

            @Override // java.io.Reader
            public int read(char[] cbuf, int off, int len) {
                Intrinsics.checkNotNullParameter(cbuf, "cbuf");
                return ByteReadPacket.this.readAvailableCharacters$ktor_io(cbuf, off, len);
            }
        };
    }

    public static final OutputStream outputStream(final BytePacketBuilder $this$outputStream) {
        Intrinsics.checkNotNullParameter($this$outputStream, "<this>");
        return new OutputStream() { // from class: io.ktor.utils.io.streams.StreamsKt$outputStream$1
            @Override // java.io.OutputStream
            public void write(int b) {
                BytePacketBuilder.this.writeByte((byte) b);
            }

            @Override // java.io.OutputStream
            public void write(byte[] b, int off, int len) {
                Intrinsics.checkNotNullParameter(b, "b");
                io.ktor.utils.io.core.OutputKt.writeFully((Output) BytePacketBuilder.this, b, off, len);
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        };
    }

    public static final Writer writerUTF8(final BytePacketBuilder $this$writerUTF8) {
        Intrinsics.checkNotNullParameter($this$writerUTF8, "<this>");
        return new Writer() { // from class: io.ktor.utils.io.streams.StreamsKt$writerUTF8$1
            @Override // java.io.Writer
            public void write(char[] cbuf, int off, int len) {
                Intrinsics.checkNotNullParameter(cbuf, "cbuf");
                BytePacketBuilder.this.append(cbuf, off, off + len);
            }

            @Override // java.io.Writer, java.io.Flushable
            public void flush() {
            }

            @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        };
    }
}
