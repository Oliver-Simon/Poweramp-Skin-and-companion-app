package io.ktor.utils.io;

import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ByteReadChannel.kt */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a'\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u001d\u0010\t\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u001d\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0004H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u001d\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a\u001d\u0010\u0015\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a\u001d\u0010\u0015\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a\u0015\u0010\u0018\u001a\u00020\u0019*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u0017\u0010\u001a\u001a\u0004\u0018\u00010\u001b*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a!\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"cancel", "", "Lio/ktor/utils/io/ByteReadChannel;", "copyAndClose", "", "dst", "Lio/ktor/utils/io/ByteWriteChannel;", "limit", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyTo", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discard", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discardExact", "", "n", "(Lio/ktor/utils/io/ByteReadChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAvailable", "", "", "(Lio/ktor/utils/io/ByteReadChannel;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFully", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/core/internal/ChunkBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readRemaining", "Lio/ktor/utils/io/core/ByteReadPacket;", "readUTF8Line", "", "readUTF8LineTo", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Appendable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ByteReadChannelKt {
    public static final Object readRemaining(ByteReadChannel $this$readRemaining, Continuation<? super ByteReadPacket> continuation) {
        return $this$readRemaining.readRemaining(Long.MAX_VALUE, continuation);
    }

    public static final Object readFully(ByteReadChannel $this$readFully, ChunkBuffer dst, Continuation<? super Unit> continuation) {
        ChunkBuffer this_$iv = dst;
        Object readFully = $this$readFully.readFully(dst, this_$iv.getLimit() - this_$iv.getWritePosition(), continuation);
        return readFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readFully : Unit.INSTANCE;
    }

    public static final Object readUTF8LineTo(ByteReadChannel $this$readUTF8LineTo, Appendable out, Continuation<? super Boolean> continuation) {
        return $this$readUTF8LineTo.readUTF8LineTo(out, Integer.MAX_VALUE, continuation);
    }

    public static final Object readUTF8Line(ByteReadChannel $this$readUTF8Line, Continuation<? super String> continuation) {
        return $this$readUTF8Line.readUTF8Line(Integer.MAX_VALUE, continuation);
    }

    public static final boolean cancel(ByteReadChannel $this$cancel) {
        Intrinsics.checkNotNullParameter($this$cancel, "<this>");
        return $this$cancel.cancel(null);
    }

    public static final Object discard(ByteReadChannel $this$discard, Continuation<? super Long> continuation) {
        return $this$discard.discard(Long.MAX_VALUE, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object discardExact(io.ktor.utils.io.ByteReadChannel r5, long r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteReadChannelKt$discardExact$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteReadChannelKt$discardExact$1 r0 = (io.ktor.utils.io.ByteReadChannelKt$discardExact$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteReadChannelKt$discardExact$1 r0 = new io.ktor.utils.io.ByteReadChannelKt$discardExact$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2c:
            r5 = 0
            long r6 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r5
            r5 = r8
            goto L45
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = 0
            r0.J$0 = r6
            r3 = 1
            r0.label = r3
            java.lang.Object r5 = r5.discard(r6, r0)
            if (r5 != r1) goto L45
            return r1
        L45:
            java.lang.Number r5 = (java.lang.Number) r5
            long r3 = r5.longValue()
            int r5 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r5 != 0) goto L52
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L52:
            java.io.EOFException r5 = new java.io.EOFException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Unable to discard "
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r6)
            java.lang.String r3 = " bytes"
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            r5.<init>(r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelKt.discardExact(io.ktor.utils.io.ByteReadChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object discardExact$$forInline(ByteReadChannel $this$discardExact, long n, Continuation<? super Unit> continuation) {
        if (((Number) $this$discardExact.discard(n, continuation)).longValue() != n) {
            throw new EOFException("Unable to discard " + n + " bytes");
        }
        return Unit.INSTANCE;
    }

    public static final Object readAvailable(ByteReadChannel $this$readAvailable, byte[] dst, Continuation<? super Integer> continuation) {
        return $this$readAvailable.readAvailable(dst, 0, dst.length, continuation);
    }

    public static final Object readFully(ByteReadChannel $this$readFully, byte[] dst, Continuation<? super Unit> continuation) {
        Object readFully = $this$readFully.readFully(dst, 0, dst.length, continuation);
        return readFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readFully : Unit.INSTANCE;
    }

    public static final Object copyTo(ByteReadChannel $this$copyTo, ByteWriteChannel dst, Continuation<? super Long> continuation) {
        return ByteReadChannelJVMKt.copyTo($this$copyTo, dst, Long.MAX_VALUE, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyAndClose(io.ktor.utils.io.ByteReadChannel r3, io.ktor.utils.io.ByteWriteChannel r4, long r5, kotlin.coroutines.Continuation<? super java.lang.Long> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteReadChannelKt$copyAndClose$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteReadChannelKt$copyAndClose$1 r0 = (io.ktor.utils.io.ByteReadChannelKt$copyAndClose$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteReadChannelKt$copyAndClose$1 r0 = new io.ktor.utils.io.ByteReadChannelKt$copyAndClose$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        L2c:
            java.lang.Object r3 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r3 = (io.ktor.utils.io.ByteWriteChannel) r3
            kotlin.ResultKt.throwOnFailure(r7)
            r4 = r3
            r3 = r7
            goto L45
        L36:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r4
            r2 = 1
            r0.label = r2
            java.lang.Object r3 = io.ktor.utils.io.ByteReadChannelJVMKt.copyTo(r3, r4, r5, r0)
            if (r3 != r1) goto L45
            return r1
        L45:
            java.lang.Number r3 = (java.lang.Number) r3
            long r5 = r3.longValue()
            io.ktor.utils.io.ByteWriteChannelKt.close(r4)
            java.lang.Long r3 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelKt.copyAndClose(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object copyAndClose$default(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyAndClose(byteReadChannel, byteWriteChannel, j, continuation);
    }
}
