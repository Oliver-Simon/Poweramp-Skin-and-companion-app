package io.ktor.utils.io.charsets;

import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BufferPrimitivesKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.internal.CharArraySequence;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Encoding.kt */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\u001a \u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a.\u0010\b\u001a\u00020\t*\u00060\nj\u0002`\u000b2\u0006\u0010\u0004\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010\u001a*\u0010\b\u001a\u00020\u0011*\u00060\nj\u0002`\u000b2\u0006\u0010\u0004\u001a\u00020\u00122\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u001a0\u0010\b\u001a\u00020\t*\u00060\nj\u0002`\u000b2\u0006\u0010\u0004\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a0\u0010\u0013\u001a\u00020\u0007*\u00060\nj\u0002`\u000b2\u0006\u0010\u0004\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0014H\u0000\u001a\u0018\u0010\u0015\u001a\u00020\u0007*\u00060\nj\u0002`\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a,\u0010\u0016\u001a\u00020\u0017*\u00060\nj\u0002`\u000b2\u0006\u0010\u0004\u001a\u00020\u00122\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u0007H\u0007\u001a,\u0010\u0018\u001a\u00020\u0017*\u00060\nj\u0002`\u000b2\u0006\u0010\u0004\u001a\u00020\u00122\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u0007H\u0000\u001a0\u0010\u0019\u001a\u00020\u0007*\u00060\nj\u0002`\u000b2\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0000\u001a\u0016\u0010\u001b\u001a\u00020\u0011*\u00060\nj\u0002`\u000b2\u0006\u0010\u0004\u001a\u00020\u0011\u001a\f\u0010\u001c\u001a\u00020\u001d*\u00020\u0005H\u0000¨\u0006\u001e"}, d2 = {"decode", "", "Ljava/nio/charset/CharsetDecoder;", "Lio/ktor/utils/io/charsets/CharsetDecoder;", "input", "Lio/ktor/utils/io/core/Input;", "max", "", "encode", "", "Ljava/nio/charset/CharsetEncoder;", "Lio/ktor/utils/io/charsets/CharsetEncoder;", "", "fromIndex", "toIndex", "dst", "Lio/ktor/utils/io/core/Output;", "Lio/ktor/utils/io/core/ByteReadPacket;", "", "encodeArrayImpl", "Lio/ktor/utils/io/core/Buffer;", "encodeCompleteImpl", "encodeToByteArrayImpl", "", "encodeToByteArrayImpl1", "encodeToImpl", "destination", "encodeUTF8", "sizeEstimate", "", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class EncodingKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use writeText on Output instead.", replaceWith = @ReplaceWith(expression = "dst.writeText(input, fromIndex, toIndex, charset)", imports = {"io.ktor.utils.io.core.writeText"}))
    public static final void encode(CharsetEncoder $this$encode, CharSequence input, int fromIndex, int toIndex, Output dst) {
        Intrinsics.checkNotNullParameter($this$encode, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(dst, "dst");
        encodeToImpl($this$encode, dst, input, fromIndex, toIndex);
    }

    public static /* synthetic */ byte[] encodeToByteArrayImpl$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return encodeToByteArrayImpl(charsetEncoder, charSequence, i, i2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Internal API. Will be hidden in future releases. Use encodeToByteArray instead.", replaceWith = @ReplaceWith(expression = "encodeToByteArray(input, fromIndex, toIndex)", imports = {}))
    public static final byte[] encodeToByteArrayImpl(CharsetEncoder $this$encodeToByteArrayImpl, CharSequence input, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter($this$encodeToByteArrayImpl, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        return CharsetJVMKt.encodeToByteArray($this$encodeToByteArrayImpl, input, fromIndex, toIndex);
    }

    public static /* synthetic */ ByteReadPacket encode$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return encode(charsetEncoder, charSequence, i, i2);
    }

    public static final ByteReadPacket encode(CharsetEncoder $this$encode, CharSequence input, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter($this$encode, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            encodeToImpl($this$encode, builder$iv, input, fromIndex, toIndex);
            return builder$iv.build();
        } catch (Throwable t$iv) {
            builder$iv.release();
            throw t$iv;
        }
    }

    public static final ByteReadPacket encodeUTF8(CharsetEncoder $this$encodeUTF8, ByteReadPacket input) {
        Intrinsics.checkNotNullParameter($this$encodeUTF8, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            CharsetJVMKt.encodeUTF8($this$encodeUTF8, input, builder$iv);
            return builder$iv.build();
        } catch (Throwable t$iv) {
            builder$iv.release();
            throw t$iv;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0069, code lost:
    
        throw new java.lang.IllegalStateException("Check failed.".toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void encode(java.nio.charset.CharsetEncoder r17, char[] r18, int r19, int r20, io.ktor.utils.io.core.Output r21) {
        /*
            r1 = r17
            r2 = r18
            r3 = r20
            r4 = r21
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "input"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 0
            r0 = r19
            if (r0 < r3) goto L1d
            return
        L1d:
            r5 = 1
            r6 = r21
            r7 = 0
            r8 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r8 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r6, r5, r8)
            r9 = 0
            r16 = r8
            r8 = r0
            r0 = r9
            r9 = r16
        L2d:
            r10 = r9
            io.ktor.utils.io.core.Buffer r10 = (io.ktor.utils.io.core.Buffer) r10     // Catch: java.lang.Throwable -> L6a
            r11 = 0
            int r12 = encodeArrayImpl(r1, r2, r8, r3, r10)     // Catch: java.lang.Throwable -> L6a
            r13 = 1
            r14 = 0
            if (r12 < 0) goto L3c
            r15 = r13
            goto L3d
        L3c:
            r15 = r14
        L3d:
            if (r15 == 0) goto L5e
            int r8 = r8 + r12
            if (r8 < r3) goto L45
            r13 = r14
            goto L4b
        L45:
            if (r12 != 0) goto L4a
            r13 = 8
            goto L4b
        L4a:
        L4b:
            r0 = r13
            if (r0 <= 0) goto L55
            io.ktor.utils.io.core.internal.ChunkBuffer r10 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r6, r0, r9)     // Catch: java.lang.Throwable -> L6a
            r9 = r10
            goto L2d
        L55:
            r6.afterHeadWrite()
            encodeCompleteImpl(r1, r4)
            return
        L5e:
            java.lang.String r13 = "Check failed."
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L6a
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L6a
            r14.<init>(r13)     // Catch: java.lang.Throwable -> L6a
            throw r14     // Catch: java.lang.Throwable -> L6a
        L6a:
            r0 = move-exception
            r6.afterHeadWrite()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.EncodingKt.encode(java.nio.charset.CharsetEncoder, char[], int, int, io.ktor.utils.io.core.Output):void");
    }

    public static /* synthetic */ String decode$default(CharsetDecoder charsetDecoder, Input input, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return decode(charsetDecoder, input, i);
    }

    public static final String decode(CharsetDecoder $this$decode, Input input, int max) {
        Intrinsics.checkNotNullParameter($this$decode, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        StringBuilder $this$decode_u24lambda_u243 = new StringBuilder((int) Math.min(max, sizeEstimate(input)));
        CharsetJVMKt.decode($this$decode, input, $this$decode_u24lambda_u243, max);
        String sb = $this$decode_u24lambda_u243.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder(capacity).…builderAction).toString()");
        return sb;
    }

    public static final int encodeArrayImpl(CharsetEncoder $this$encodeArrayImpl, char[] input, int fromIndex, int toIndex, Buffer dst) {
        Intrinsics.checkNotNullParameter($this$encodeArrayImpl, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int length = toIndex - fromIndex;
        return CharsetJVMKt.encodeImpl($this$encodeArrayImpl, new CharArraySequence(input, fromIndex, length), 0, length, dst);
    }

    public static /* synthetic */ byte[] encodeToByteArrayImpl1$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return encodeToByteArrayImpl1(charsetEncoder, charSequence, i, i2);
    }

    public static final byte[] encodeToByteArrayImpl1(CharsetEncoder $this$encodeToByteArrayImpl1, CharSequence input, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter($this$encodeToByteArrayImpl1, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        if (fromIndex >= toIndex) {
            return UnsafeKt.EmptyByteArray;
        }
        ChunkBuffer single = ChunkBuffer.INSTANCE.getPool().borrow();
        try {
            int rc = CharsetJVMKt.encodeImpl($this$encodeToByteArrayImpl1, input, fromIndex, toIndex, single);
            int start = fromIndex + rc;
            if (start == toIndex) {
                ChunkBuffer this_$iv = single;
                byte[] result = new byte[this_$iv.getWritePosition() - this_$iv.getReadPosition()];
                int length$iv = result.length - 0;
                Intrinsics.checkNotNull(single, "null cannot be cast to non-null type io.ktor.utils.io.core.Buffer");
                BufferPrimitivesKt.readFully((Buffer) single, result, 0, length$iv);
                return result;
            }
            BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
            try {
                builder$iv.appendSingleChunk$ktor_io(single.duplicate());
                encodeToImpl($this$encodeToByteArrayImpl1, builder$iv, input, start, toIndex);
                return io.ktor.utils.io.core.StringsKt.readBytes$default(builder$iv.build(), 0, 1, null);
            } catch (Throwable t$iv) {
                builder$iv.release();
                throw t$iv;
            }
        } finally {
            single.release(ChunkBuffer.INSTANCE.getPool());
        }
    }

    public static final long sizeEstimate(Input $this$sizeEstimate) {
        Intrinsics.checkNotNullParameter($this$sizeEstimate, "<this>");
        return $this$sizeEstimate instanceof ByteReadPacket ? $this$sizeEstimate.getRemaining() : Math.max($this$sizeEstimate.getRemaining(), 16L);
    }

    private static final int encodeCompleteImpl(CharsetEncoder $this$encodeCompleteImpl, Output dst) {
        int size = 1;
        int bytesWritten = 0;
        ChunkBuffer tail$iv = UnsafeKt.prepareWriteHead(dst, 1, null);
        while (true) {
            try {
                Buffer view = tail$iv;
                int before = view.getLimit() - view.getWritePosition();
                if (CharsetJVMKt.encodeComplete($this$encodeCompleteImpl, view)) {
                    size = 0;
                } else {
                    size++;
                }
                bytesWritten += before - (view.getLimit() - view.getWritePosition());
                if (!(size > 0)) {
                    return bytesWritten;
                }
                tail$iv = UnsafeKt.prepareWriteHead(dst, 1, tail$iv);
            } finally {
                dst.afterHeadWrite();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x008e, code lost:
    
        throw new java.lang.IllegalStateException("Check failed.".toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int encodeToImpl(java.nio.charset.CharsetEncoder r22, io.ktor.utils.io.core.Output r23, java.lang.CharSequence r24, int r25, int r26) {
        /*
            r1 = r22
            r2 = r24
            r3 = r26
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "destination"
            r4 = r23
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "input"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            r0 = 0
            r0 = r25
            r5 = 0
            if (r0 < r3) goto L1e
            return r5
        L1e:
            r6 = 0
            r7 = 1
            r8 = r23
            r9 = 0
            r10 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r10 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r8, r7, r10)
            r11 = 0
            r21 = r6
            r6 = r0
            r0 = r11
            r11 = r10
            r10 = r21
        L30:
            r12 = r11
            io.ktor.utils.io.core.Buffer r12 = (io.ktor.utils.io.core.Buffer) r12     // Catch: java.lang.Throwable -> L8f
            r13 = 0
            r14 = r12
            r15 = 0
            int r16 = r14.getLimit()     // Catch: java.lang.Throwable -> L8f
            int r17 = r14.getWritePosition()     // Catch: java.lang.Throwable -> L8f
            int r16 = r16 - r17
            int r14 = io.ktor.utils.io.charsets.CharsetJVMKt.encodeImpl(r1, r2, r6, r3, r12)     // Catch: java.lang.Throwable -> L8f
            r15 = 1
            if (r14 < 0) goto L4c
            r17 = r15
            goto L4e
        L4c:
            r17 = r5
        L4e:
            if (r17 == 0) goto L83
            int r6 = r6 + r14
            r17 = r12
            r18 = 0
            int r19 = r17.getLimit()     // Catch: java.lang.Throwable -> L8f
            int r20 = r17.getWritePosition()     // Catch: java.lang.Throwable -> L8f
            int r19 = r19 - r20
            int r17 = r16 - r19
            int r10 = r10 + r17
            if (r6 < r3) goto L68
            r15 = r5
            goto L6e
        L68:
            if (r14 != 0) goto L6d
            r15 = 8
            goto L6e
        L6d:
        L6e:
            r0 = r15
            if (r0 <= 0) goto L78
            io.ktor.utils.io.core.internal.ChunkBuffer r12 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r8, r0, r11)     // Catch: java.lang.Throwable -> L8f
            r11 = r12
            goto L30
        L78:
            r8.afterHeadWrite()
            int r0 = encodeCompleteImpl(r22, r23)
            int r10 = r10 + r0
            return r10
        L83:
            java.lang.String r5 = "Check failed."
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L8f
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L8f
            r15.<init>(r5)     // Catch: java.lang.Throwable -> L8f
            throw r15     // Catch: java.lang.Throwable -> L8f
        L8f:
            r0 = move-exception
            r8.afterHeadWrite()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.EncodingKt.encodeToImpl(java.nio.charset.CharsetEncoder, io.ktor.utils.io.core.Output, java.lang.CharSequence, int, int):int");
    }
}
