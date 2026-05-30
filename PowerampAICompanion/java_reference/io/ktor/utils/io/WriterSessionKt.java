package io.ktor.utils.io;

import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function3;

/* compiled from: WriterSession.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002\u001a#\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a%\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0006H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001d\u0010\u000e\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001f\u0010\u0010\u001a\u0004\u0018\u00010\u0001*\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001ao\u0010\u0012\u001a\u00020\u0006*\u00020\n2\b\b\u0002\u0010\u0005\u001a\u00020\u00062K\u0010\u0013\u001aG\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00060\u0014H\u0086Hø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\u000f\u0010\u001d\u001a\u0004\u0018\u00010\u0004*\u00020\nH\u0082\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"writeBufferFallback", "Lio/ktor/utils/io/core/Buffer;", "writeBufferSuspend", "session", "Lio/ktor/utils/io/WriterSuspendSession;", "desiredSpace", "", "(Lio/ktor/utils/io/WriterSuspendSession;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeWriting", "", "Lio/ktor/utils/io/ByteWriteChannel;", "buffer", "written", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/utils/io/core/Buffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeWritingFallback", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/utils/io/core/Buffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestWriteBuffer", "(Lio/ktor/utils/io/ByteWriteChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "write", "block", "Lkotlin/Function3;", "Lio/ktor/utils/io/bits/Memory;", "Lkotlin/ParameterName;", "name", "freeSpace", "", "startOffset", "endExclusive", "(Lio/ktor/utils/io/ByteWriteChannel;ILkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeSessionFor", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class WriterSessionKt {
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ab A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object write(io.ktor.utils.io.ByteWriteChannel r10, int r11, kotlin.jvm.functions.Function3<? super io.ktor.utils.io.bits.Memory, ? super java.lang.Long, ? super java.lang.Long, java.lang.Integer> r12, kotlin.coroutines.Continuation<? super java.lang.Integer> r13) {
        /*
            boolean r0 = r13 instanceof io.ktor.utils.io.WriterSessionKt$write$1
            if (r0 == 0) goto L14
            r0 = r13
            io.ktor.utils.io.WriterSessionKt$write$1 r0 = (io.ktor.utils.io.WriterSessionKt$write$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            io.ktor.utils.io.WriterSessionKt$write$1 r0 = new io.ktor.utils.io.WriterSessionKt$write$1
            r0.<init>(r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L50;
                case 1: goto L40;
                case 2: goto L36;
                case 3: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2c:
            r10 = 0
            java.lang.Object r11 = r0.L$0
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            kotlin.ResultKt.throwOnFailure(r13)
            goto Lbf
        L36:
            r10 = 0
            java.lang.Object r11 = r0.L$0
            java.lang.Integer r11 = (java.lang.Integer) r11
            kotlin.ResultKt.throwOnFailure(r13)
            goto Lad
        L40:
            r10 = 0
            java.lang.Object r11 = r0.L$1
            kotlin.jvm.functions.Function3 r11 = (kotlin.jvm.functions.Function3) r11
            java.lang.Object r12 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r12 = (io.ktor.utils.io.ByteWriteChannel) r12
            kotlin.ResultKt.throwOnFailure(r13)
            r2 = r12
            r12 = r11
            r11 = r13
            goto L65
        L50:
            kotlin.ResultKt.throwOnFailure(r13)
            r2 = 0
            r0.L$0 = r10
            r0.L$1 = r12
            r3 = 1
            r0.label = r3
            java.lang.Object r11 = requestWriteBuffer(r10, r11, r0)
            if (r11 != r1) goto L62
            return r1
        L62:
            r9 = r2
            r2 = r10
            r10 = r9
        L65:
            io.ktor.utils.io.core.Buffer r11 = (io.ktor.utils.io.core.Buffer) r11
            if (r11 != 0) goto L6f
            io.ktor.utils.io.core.Buffer$Companion r11 = io.ktor.utils.io.core.Buffer.INSTANCE
            io.ktor.utils.io.core.Buffer r11 = r11.getEmpty()
        L6f:
            r3 = 0
            r4 = 0
            java.nio.ByteBuffer r5 = r11.getMemory()     // Catch: java.lang.Throwable -> Laf
            io.ktor.utils.io.bits.Memory r5 = io.ktor.utils.io.bits.Memory.m234boximpl(r5)     // Catch: java.lang.Throwable -> Laf
            int r6 = r11.getWritePosition()     // Catch: java.lang.Throwable -> Laf
            long r6 = (long) r6     // Catch: java.lang.Throwable -> Laf
            java.lang.Long r6 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r6)     // Catch: java.lang.Throwable -> Laf
            int r7 = r11.getLimit()     // Catch: java.lang.Throwable -> Laf
            long r7 = (long) r7     // Catch: java.lang.Throwable -> Laf
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)     // Catch: java.lang.Throwable -> Laf
            java.lang.Object r5 = r12.invoke(r5, r6, r7)     // Catch: java.lang.Throwable -> Laf
            java.lang.Number r5 = (java.lang.Number) r5     // Catch: java.lang.Throwable -> Laf
            int r5 = r5.intValue()     // Catch: java.lang.Throwable -> Laf
            r3 = r5
            r11.commitWritten(r3)     // Catch: java.lang.Throwable -> Laf
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)     // Catch: java.lang.Throwable -> Laf
            r0.L$0 = r12
            r0.L$1 = r4
            r4 = 2
            r0.label = r4
            java.lang.Object r11 = completeWriting(r2, r11, r3, r0)
            if (r11 != r1) goto Lac
            return r1
        Lac:
            r11 = r12
        Lad:
            return r11
        Laf:
            r12 = move-exception
            r0.L$0 = r12
            r0.L$1 = r4
            r4 = 3
            r0.label = r4
            java.lang.Object r11 = completeWriting(r2, r11, r3, r0)
            if (r11 != r1) goto Lbe
            return r1
        Lbe:
            r11 = r12
        Lbf:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.WriterSessionKt.write(io.ktor.utils.io.ByteWriteChannel, int, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object write$default(ByteWriteChannel $this$write_u24default, int desiredSpace, Function3 block, Continuation $completion, int i, Object obj) {
        if ((i & 1) != 0) {
            desiredSpace = 1;
        }
        Buffer buffer = (Buffer) requestWriteBuffer($this$write_u24default, desiredSpace, $completion);
        if (buffer == null) {
            buffer = Buffer.INSTANCE.getEmpty();
        }
        int bytesWritten = 0;
        try {
            bytesWritten = ((Number) block.invoke(Memory.m234boximpl(buffer.getMemory()), Long.valueOf(buffer.getWritePosition()), Long.valueOf(buffer.getLimit()))).intValue();
            buffer.commitWritten(bytesWritten);
            return Integer.valueOf(bytesWritten);
        } finally {
            completeWriting($this$write_u24default, buffer, bytesWritten, $completion);
        }
    }

    private static final Object write$$forInline(ByteWriteChannel $this$write, int desiredSpace, Function3<? super Memory, ? super Long, ? super Long, Integer> function3, Continuation<? super Integer> continuation) {
        Buffer buffer = (Buffer) requestWriteBuffer($this$write, desiredSpace, continuation);
        if (buffer == null) {
            buffer = Buffer.INSTANCE.getEmpty();
        }
        int bytesWritten = 0;
        try {
            bytesWritten = function3.invoke(Memory.m234boximpl(buffer.getMemory()), Long.valueOf(buffer.getWritePosition()), Long.valueOf(buffer.getLimit())).intValue();
            buffer.commitWritten(bytesWritten);
            return Integer.valueOf(bytesWritten);
        } finally {
            completeWriting($this$write, buffer, bytesWritten, continuation);
        }
    }

    public static final Object requestWriteBuffer(ByteWriteChannel $this$requestWriteBuffer, int desiredSpace, Continuation<? super Buffer> continuation) {
        WriterSuspendSession session;
        if ($this$requestWriteBuffer instanceof HasWriteSession) {
            session = ((HasWriteSession) $this$requestWriteBuffer).beginWriteSession();
        } else {
            session = null;
        }
        if (session != null) {
            ChunkBuffer buffer = session.request(desiredSpace);
            if (buffer != null) {
                return buffer;
            }
            return writeBufferSuspend(session, desiredSpace, continuation);
        }
        return writeBufferFallback();
    }

    public static final Object completeWriting(ByteWriteChannel $this$completeWriting, Buffer buffer, int written, Continuation<? super Unit> continuation) {
        if ($this$completeWriting instanceof HasWriteSession) {
            ((HasWriteSession) $this$completeWriting).endWriteSession(written);
            return Unit.INSTANCE;
        }
        Object completeWritingFallback = completeWritingFallback($this$completeWriting, buffer, continuation);
        return completeWritingFallback == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? completeWritingFallback : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object completeWritingFallback(io.ktor.utils.io.ByteWriteChannel r3, io.ktor.utils.io.core.Buffer r4, kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.utils.io.WriterSessionKt$completeWritingFallback$1
            if (r0 == 0) goto L14
            r0 = r5
            io.ktor.utils.io.WriterSessionKt$completeWritingFallback$1 r0 = (io.ktor.utils.io.WriterSessionKt$completeWritingFallback$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            io.ktor.utils.io.WriterSessionKt$completeWritingFallback$1 r0 = new io.ktor.utils.io.WriterSessionKt$completeWritingFallback$1
            r0.<init>(r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
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
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3
            kotlin.ResultKt.throwOnFailure(r5)
            goto L48
        L34:
            kotlin.ResultKt.throwOnFailure(r5)
            boolean r2 = r4 instanceof io.ktor.utils.io.core.internal.ChunkBuffer
            if (r2 == 0) goto L57
            r0.L$0 = r4
            r2 = 1
            r0.label = r2
            java.lang.Object r3 = r3.writeFully(r4, r0)
            if (r3 != r1) goto L47
            return r1
        L47:
            r3 = r4
        L48:
            r4 = r3
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = (io.ktor.utils.io.core.internal.ChunkBuffer) r4
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r1 = io.ktor.utils.io.core.internal.ChunkBuffer.INSTANCE
            io.ktor.utils.io.pool.ObjectPool r1 = r1.getPool()
            r4.release(r1)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L57:
            java.lang.UnsupportedOperationException r3 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Only ChunkBuffer instance is supported."
            r3.<init>(r1)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.WriterSessionKt.completeWritingFallback(io.ktor.utils.io.ByteWriteChannel, io.ktor.utils.io.core.Buffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object writeBufferSuspend(io.ktor.utils.io.WriterSuspendSession r5, int r6, kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.Buffer> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.WriterSessionKt$writeBufferSuspend$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.WriterSessionKt$writeBufferSuspend$1 r0 = (io.ktor.utils.io.WriterSessionKt$writeBufferSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.WriterSessionKt$writeBufferSuspend$1 r0 = new io.ktor.utils.io.WriterSessionKt$writeBufferSuspend$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L37;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            int r5 = r0.I$0
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.WriterSuspendSession r6 = (io.ktor.utils.io.WriterSuspendSession) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4a
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r2 = r5.tryAwait(r6, r0)
            if (r2 != r1) goto L47
            return r1
        L47:
            r4 = r6
            r6 = r5
            r5 = r4
        L4a:
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = r6.request(r5)
            if (r5 == 0) goto L51
            goto L55
        L51:
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = r6.request(r3)
        L55:
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.WriterSessionKt.writeBufferSuspend(io.ktor.utils.io.WriterSuspendSession, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Buffer writeBufferFallback() {
        ChunkBuffer borrow = ChunkBuffer.INSTANCE.getPool().borrow();
        ChunkBuffer it = borrow;
        it.resetForWrite();
        it.reserveEndGap(8);
        return borrow;
    }

    private static final WriterSuspendSession writeSessionFor(ByteWriteChannel $this$writeSessionFor) {
        if ($this$writeSessionFor instanceof HasWriteSession) {
            return ((HasWriteSession) $this$writeSessionFor).beginWriteSession();
        }
        return null;
    }
}
