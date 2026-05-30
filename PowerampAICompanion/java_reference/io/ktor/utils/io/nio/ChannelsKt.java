package io.ktor.utils.io.nio;

import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Channels.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0007\u001a\u0012\u0010\u000b\u001a\u00020\f*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u000f\u001a\u00020\f*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u0010\u001a\u00020\f*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e\u001a\u001c\u0010\u0011\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002\u001a3\u0010\u0014\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\t\u001a\u00020\nH\u0007\u001a%\u0010\u001a\u001a\u0004\u0018\u00010\f*\u00020\u00152\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\u001c¢\u0006\u0002\b\u001f\u001a\u0012\u0010\u001a\u001a\u00020 *\u00020\u00152\u0006\u0010!\u001a\u00020\f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\""}, d2 = {"read", "", "Ljava/nio/channels/ReadableByteChannel;", "destination", "Lio/ktor/utils/io/bits/Memory;", "destinationOffset", "maxLength", "read-UAd2zVI", "(Ljava/nio/channels/ReadableByteChannel;Ljava/nio/ByteBuffer;II)I", "buffer", "Lio/ktor/utils/io/core/Buffer;", "readPacketAtLeast", "Lio/ktor/utils/io/core/ByteReadPacket;", "n", "", "readPacketAtMost", "readPacketExact", "readPacketImpl", "min", "max", "write", "Ljava/nio/channels/WritableByteChannel;", "source", "sourceOffset", "write-UAd2zVI", "(Ljava/nio/channels/WritableByteChannel;Ljava/nio/ByteBuffer;II)I", "writePacket", "builder", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "", "Lkotlin/ExtensionFunctionType;", "", "p", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ChannelsKt {
    public static final ByteReadPacket writePacket(WritableByteChannel $this$writePacket, Function1<? super BytePacketBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter($this$writePacket, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            builder.invoke(builder$iv);
            ByteReadPacket p = builder$iv.build();
            try {
                if (writePacket($this$writePacket, p)) {
                    return null;
                }
                return p;
            } catch (Throwable t) {
                p.release();
                throw t;
            }
        } catch (Throwable t$iv) {
            builder$iv.release();
            throw t$iv;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b2, code lost:
    
        throw new java.lang.IllegalStateException("Buffer's limit change is not allowed".toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean writePacket(java.nio.channels.WritableByteChannel r22, io.ktor.utils.io.core.ByteReadPacket r23) {
        /*
            r1 = r22
            r2 = r23
            java.lang.String r3 = "Buffer's position shouldn't be rewinded"
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "p"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
        L11:
            r4 = 0
            r0 = r2
            io.ktor.utils.io.core.Input r0 = (io.ktor.utils.io.core.Input) r0     // Catch: java.lang.Throwable -> Ld8
            r5 = r0
            r6 = 1
            r7 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r0 = r5.prepareRead(r6)     // Catch: java.lang.Throwable -> Ld8
            if (r0 == 0) goto Lcf
            r8 = r0
            int r0 = r8.getReadPosition()     // Catch: java.lang.Throwable -> Ld8
            r9 = r0
            r0 = r8
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch: java.lang.Throwable -> Lb3
            r10 = 0
            r11 = r0
            r12 = 0
            r13 = r11
            r14 = 0
            java.nio.ByteBuffer r15 = r13.getMemory()     // Catch: java.lang.Throwable -> Lb3
            int r16 = r13.getReadPosition()     // Catch: java.lang.Throwable -> Lb3
            int r17 = r13.getWritePosition()     // Catch: java.lang.Throwable -> Lb3
            r18 = r16
            r16 = 0
            r19 = r0
            int r0 = r17 - r18
            r2 = r18
            java.nio.ByteBuffer r0 = io.ktor.utils.io.bits.Memory.m245slice87lwejk(r15, r2, r0)     // Catch: java.lang.Throwable -> Lb3
            r18 = r0
            r20 = 0
            r21 = r0
            int r18 = r1.write(r0)     // Catch: java.lang.Throwable -> Lb3
            r4 = r18
            int r0 = r21.limit()     // Catch: java.lang.Throwable -> Lb3
            int r1 = r17 - r2
            r18 = 1
            r20 = 0
            if (r0 != r1) goto L68
            r0 = r18
            goto L6a
        L68:
            r0 = r20
        L6a:
            if (r0 == 0) goto La6
            int r0 = r21.position()     // Catch: java.lang.Throwable -> Lb3
            r13.discardExact(r0)     // Catch: java.lang.Throwable -> Lb3
            int r0 = r8.getReadPosition()     // Catch: java.lang.Throwable -> Ld8
            if (r0 < r9) goto La0
            int r1 = r8.getWritePosition()     // Catch: java.lang.Throwable -> Ld8
            if (r0 != r1) goto L88
            r5.ensureNext(r8)     // Catch: java.lang.Throwable -> Ld8
            goto L8b
        L88:
            r5.setHeadPosition(r0)     // Catch: java.lang.Throwable -> Ld8
        L8b:
            r0 = r23
            r1 = 0
            boolean r2 = r0.getEndOfInput()     // Catch: java.lang.Throwable -> Ld8
            if (r2 == 0) goto L97
            return r18
        L97:
            if (r4 != 0) goto L9a
            return r20
        L9a:
            r1 = r22
            r2 = r23
            goto L11
        La0:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Ld8
            r1.<init>(r3)     // Catch: java.lang.Throwable -> Ld8
            throw r1     // Catch: java.lang.Throwable -> Ld8
        La6:
            r0 = 0
            java.lang.String r1 = "Buffer's limit change is not allowed"
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lb3
            r0.<init>(r1)     // Catch: java.lang.Throwable -> Lb3
            throw r0     // Catch: java.lang.Throwable -> Lb3
        Lb3:
            r0 = move-exception
            int r1 = r8.getReadPosition()     // Catch: java.lang.Throwable -> Ld8
            if (r1 < r9) goto Lc9
            int r2 = r8.getWritePosition()     // Catch: java.lang.Throwable -> Ld8
            if (r1 != r2) goto Lc4
            r5.ensureNext(r8)     // Catch: java.lang.Throwable -> Ld8
            goto Lc7
        Lc4:
            r5.setHeadPosition(r1)     // Catch: java.lang.Throwable -> Ld8
        Lc7:
            throw r0     // Catch: java.lang.Throwable -> Ld8
        Lc9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Ld8
            r0.<init>(r3)     // Catch: java.lang.Throwable -> Ld8
            throw r0     // Catch: java.lang.Throwable -> Ld8
        Lcf:
            io.ktor.utils.io.core.StringsKt.prematureEndOfStream(r6)     // Catch: java.lang.Throwable -> Ld8
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> Ld8
            r0.<init>()     // Catch: java.lang.Throwable -> Ld8
            throw r0     // Catch: java.lang.Throwable -> Ld8
        Ld8:
            r0 = move-exception
            r23.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.nio.ChannelsKt.writePacket(java.nio.channels.WritableByteChannel, io.ktor.utils.io.core.ByteReadPacket):boolean");
    }

    public static final ByteReadPacket readPacketExact(ReadableByteChannel $this$readPacketExact, long n) {
        Intrinsics.checkNotNullParameter($this$readPacketExact, "<this>");
        return readPacketImpl($this$readPacketExact, n, n);
    }

    public static final ByteReadPacket readPacketAtLeast(ReadableByteChannel $this$readPacketAtLeast, long n) {
        Intrinsics.checkNotNullParameter($this$readPacketAtLeast, "<this>");
        return readPacketImpl($this$readPacketAtLeast, n, Long.MAX_VALUE);
    }

    public static final ByteReadPacket readPacketAtMost(ReadableByteChannel $this$readPacketAtMost, long n) {
        Intrinsics.checkNotNullParameter($this$readPacketAtMost, "<this>");
        return readPacketImpl($this$readPacketAtMost, 1L, n);
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0137, code lost:
    
        io.ktor.utils.io.internal.jvm.ErrorsKt.wrongBufferPositionChangeError(r0, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x013f, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final io.ktor.utils.io.core.ByteReadPacket readPacketImpl(java.nio.channels.ReadableByteChannel r29, long r30, long r32) {
        /*
            Method dump skipped, instructions count: 503
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.nio.ChannelsKt.readPacketImpl(java.nio.channels.ReadableByteChannel, long, long):io.ktor.utils.io.core.ByteReadPacket");
    }

    @Deprecated(message = "Use read(Memory) instead.")
    public static final int read(ReadableByteChannel $this$read, Buffer buffer) {
        Intrinsics.checkNotNullParameter($this$read, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (buffer.getLimit() - buffer.getWritePosition() == 0) {
            return 0;
        }
        ByteBuffer memory = buffer.getMemory();
        int start = buffer.getWritePosition();
        int endExclusive = buffer.getLimit();
        int rc = $this$read.read(MemoryJvmKt.sliceSafe(memory, start, endExclusive - start));
        if (rc == -1) {
            return -1;
        }
        buffer.commitWritten(rc);
        return rc;
    }

    /* renamed from: read-UAd2zVI$default, reason: not valid java name */
    public static /* synthetic */ int m503readUAd2zVI$default(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = byteBuffer.limit() - i;
        }
        return m502readUAd2zVI(readableByteChannel, byteBuffer, i, i2);
    }

    /* renamed from: read-UAd2zVI, reason: not valid java name */
    public static final int m502readUAd2zVI(ReadableByteChannel read, ByteBuffer destination, int destinationOffset, int maxLength) {
        Intrinsics.checkNotNullParameter(read, "$this$read");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ByteBuffer nioBuffer = MemoryJvmKt.sliceSafe(destination, destinationOffset, maxLength);
        return read.read(nioBuffer);
    }

    @Deprecated(message = "Use write(Memory) instead.")
    public static final int write(WritableByteChannel $this$write, Buffer buffer) {
        Intrinsics.checkNotNullParameter($this$write, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        ByteBuffer memory = buffer.getMemory();
        int start = buffer.getReadPosition();
        int endExclusive = buffer.getWritePosition();
        int rc$iv = $this$write.write(MemoryJvmKt.sliceSafe(memory, start, endExclusive - start));
        buffer.discardExact(rc$iv);
        return rc$iv;
    }

    /* renamed from: write-UAd2zVI$default, reason: not valid java name */
    public static /* synthetic */ int m505writeUAd2zVI$default(WritableByteChannel writableByteChannel, ByteBuffer byteBuffer, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = byteBuffer.limit() - i;
        }
        return m504writeUAd2zVI(writableByteChannel, byteBuffer, i, i2);
    }

    /* renamed from: write-UAd2zVI, reason: not valid java name */
    public static final int m504writeUAd2zVI(WritableByteChannel write, ByteBuffer source, int sourceOffset, int maxLength) {
        Intrinsics.checkNotNullParameter(write, "$this$write");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer nioBuffer = MemoryJvmKt.sliceSafe(source, sourceOffset, maxLength);
        return write.write(nioBuffer);
    }
}
