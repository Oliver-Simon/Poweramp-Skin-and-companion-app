package io.ktor.utils.io;

import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* compiled from: ReadSession.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001ao\u0010\b\u001a\u00020\u0006*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00062K\u0010\n\u001aG\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00060\u000bH\u0086Hø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u0002H\u0082\b\u001a\u001f\u0010\u0016\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a\u001d\u0010\u0018\u001a\u00020\u0019*\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a\u001f\u0010\u001a\u001a\u0004\u0018\u00010\u0004*\u00020\u00152\u0006\u0010\t\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"completeReadingFromBuffer", "", "Lio/ktor/utils/io/ByteReadChannel;", "buffer", "Lio/ktor/utils/io/core/Buffer;", "bytesRead", "", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/core/Buffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "read", "desiredSize", "block", "Lkotlin/Function3;", "Lio/ktor/utils/io/bits/Memory;", "Lkotlin/ParameterName;", "name", "source", "", "start", "endExclusive", "(Lio/ktor/utils/io/ByteReadChannel;ILkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readSessionFor", "Lio/ktor/utils/io/SuspendableReadSession;", "requestBuffer", "(Lio/ktor/utils/io/ByteReadChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestBufferFallback", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "requestBufferSuspend", "(Lio/ktor/utils/io/SuspendableReadSession;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ReadSessionKt {
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ac A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object read(io.ktor.utils.io.ByteReadChannel r8, int r9, kotlin.jvm.functions.Function3<? super io.ktor.utils.io.bits.Memory, ? super java.lang.Long, ? super java.lang.Long, java.lang.Integer> r10, kotlin.coroutines.Continuation<? super java.lang.Integer> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.utils.io.ReadSessionKt$read$1
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.utils.io.ReadSessionKt$read$1 r0 = (io.ktor.utils.io.ReadSessionKt$read$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            io.ktor.utils.io.ReadSessionKt$read$1 r0 = new io.ktor.utils.io.ReadSessionKt$read$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L59;
                case 1: goto L49;
                case 2: goto L36;
                case 3: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            r8 = 0
            java.lang.Object r9 = r0.L$0
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto Lc8
        L36:
            r8 = 0
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$1
            io.ktor.utils.io.core.Buffer r10 = (io.ktor.utils.io.core.Buffer) r10
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r2 = (io.ktor.utils.io.ByteReadChannel) r2
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L46
            goto Laf
        L46:
            r9 = move-exception
            goto Lb8
        L49:
            r8 = 0
            java.lang.Object r9 = r0.L$1
            kotlin.jvm.functions.Function3 r9 = (kotlin.jvm.functions.Function3) r9
            java.lang.Object r10 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r10
            r10 = r9
            r9 = r11
            goto L6e
        L59:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = 0
            r0.L$0 = r8
            r0.L$1 = r10
            r3 = 1
            r0.label = r3
            java.lang.Object r9 = requestBuffer(r8, r9, r0)
            if (r9 != r1) goto L6b
            return r1
        L6b:
            r7 = r2
            r2 = r8
            r8 = r7
        L6e:
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9
            if (r9 != 0) goto L78
            io.ktor.utils.io.core.Buffer$Companion r9 = io.ktor.utils.io.core.Buffer.INSTANCE
            io.ktor.utils.io.core.Buffer r9 = r9.getEmpty()
        L78:
            java.nio.ByteBuffer r3 = r9.getMemory()     // Catch: java.lang.Throwable -> Lb4
            io.ktor.utils.io.bits.Memory r3 = io.ktor.utils.io.bits.Memory.m234boximpl(r3)     // Catch: java.lang.Throwable -> Lb4
            int r4 = r9.getReadPosition()     // Catch: java.lang.Throwable -> Lb4
            long r4 = (long) r4     // Catch: java.lang.Throwable -> Lb4
            java.lang.Long r4 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)     // Catch: java.lang.Throwable -> Lb4
            int r5 = r9.getWritePosition()     // Catch: java.lang.Throwable -> Lb4
            long r5 = (long) r5     // Catch: java.lang.Throwable -> Lb4
            java.lang.Long r5 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r5)     // Catch: java.lang.Throwable -> Lb4
            java.lang.Object r3 = r10.invoke(r3, r4, r5)     // Catch: java.lang.Throwable -> Lb4
            java.lang.Number r3 = (java.lang.Number) r3     // Catch: java.lang.Throwable -> Lb4
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> Lb4
            r0.L$0 = r2     // Catch: java.lang.Throwable -> Lb4
            r0.L$1 = r9     // Catch: java.lang.Throwable -> Lb4
            r0.I$0 = r3     // Catch: java.lang.Throwable -> Lb4
            r10 = 2
            r0.label = r10     // Catch: java.lang.Throwable -> Lb4
            java.lang.Object r10 = completeReadingFromBuffer(r2, r9, r3, r0)     // Catch: java.lang.Throwable -> Lb4
            if (r10 != r1) goto Lad
            return r1
        Lad:
            r10 = r9
            r9 = r3
        Laf:
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)     // Catch: java.lang.Throwable -> L46
            return r1
        Lb4:
            r10 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
        Lb8:
            r0.L$0 = r9
            r3 = 0
            r0.L$1 = r3
            r3 = 3
            r0.label = r3
            r3 = 0
            java.lang.Object r10 = completeReadingFromBuffer(r2, r10, r3, r0)
            if (r10 != r1) goto Lc8
            return r1
        Lc8:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ReadSessionKt.read(io.ktor.utils.io.ByteReadChannel, int, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object read$default(ByteReadChannel $this$read_u24default, int desiredSize, Function3 block, Continuation $completion, int i, Object obj) {
        if ((i & 1) != 0) {
            desiredSize = 1;
        }
        Buffer buffer = (Buffer) requestBuffer($this$read_u24default, desiredSize, $completion);
        if (buffer == null) {
            buffer = Buffer.INSTANCE.getEmpty();
        }
        try {
            int bytesRead = ((Number) block.invoke(Memory.m234boximpl(buffer.getMemory()), Long.valueOf(buffer.getReadPosition()), Long.valueOf(buffer.getWritePosition()))).intValue();
            completeReadingFromBuffer($this$read_u24default, buffer, bytesRead, $completion);
            return Integer.valueOf(bytesRead);
        } catch (Throwable cause) {
            completeReadingFromBuffer($this$read_u24default, buffer, 0, $completion);
            throw cause;
        }
    }

    private static final Object read$$forInline(ByteReadChannel $this$read, int desiredSize, Function3<? super Memory, ? super Long, ? super Long, Integer> function3, Continuation<? super Integer> continuation) {
        Buffer buffer = (Buffer) requestBuffer($this$read, desiredSize, continuation);
        if (buffer == null) {
            buffer = Buffer.INSTANCE.getEmpty();
        }
        try {
            int bytesRead = function3.invoke(Memory.m234boximpl(buffer.getMemory()), Long.valueOf(buffer.getReadPosition()), Long.valueOf(buffer.getWritePosition())).intValue();
            completeReadingFromBuffer($this$read, buffer, bytesRead, continuation);
            return Integer.valueOf(bytesRead);
        } catch (Throwable cause) {
            completeReadingFromBuffer($this$read, buffer, 0, continuation);
            throw cause;
        }
    }

    public static final Object requestBuffer(ByteReadChannel $this$requestBuffer, int desiredSize, Continuation<? super Buffer> continuation) {
        SuspendableReadSession readSession;
        if ($this$requestBuffer instanceof SuspendableReadSession) {
            readSession = (SuspendableReadSession) $this$requestBuffer;
        } else {
            readSession = $this$requestBuffer instanceof HasReadSession ? ((HasReadSession) $this$requestBuffer).startReadSession() : null;
        }
        if (readSession != null) {
            ChunkBuffer buffer = readSession.request(RangesKt.coerceAtMost(desiredSize, 8));
            if (buffer != null) {
                return buffer;
            }
            return requestBufferSuspend(readSession, desiredSize, continuation);
        }
        return requestBufferFallback($this$requestBuffer, desiredSize, continuation);
    }

    public static final Object completeReadingFromBuffer(ByteReadChannel $this$completeReadingFromBuffer, Buffer buffer, int bytesRead, Continuation<? super Unit> continuation) {
        SuspendableReadSession readSession;
        if (!(bytesRead >= 0)) {
            throw new IllegalStateException(("bytesRead shouldn't be negative: " + bytesRead).toString());
        }
        if ($this$completeReadingFromBuffer instanceof HasReadSession) {
            readSession = ((HasReadSession) $this$completeReadingFromBuffer).startReadSession();
        } else {
            readSession = null;
        }
        if (readSession != null) {
            readSession.discard(bytesRead);
            if ($this$completeReadingFromBuffer instanceof HasReadSession) {
                ((HasReadSession) $this$completeReadingFromBuffer).endReadSession();
            }
            return Unit.INSTANCE;
        }
        if (!(buffer instanceof ChunkBuffer) || buffer == ChunkBuffer.INSTANCE.getEmpty()) {
            return Unit.INSTANCE;
        }
        ((ChunkBuffer) buffer).release(ChunkBuffer.INSTANCE.getPool());
        Object discard = $this$completeReadingFromBuffer.discard(bytesRead, continuation);
        return discard == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? discard : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object requestBufferSuspend(io.ktor.utils.io.SuspendableReadSession r4, int r5, kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.Buffer> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ReadSessionKt$requestBufferSuspend$1
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.utils.io.ReadSessionKt$requestBufferSuspend$1 r0 = (io.ktor.utils.io.ReadSessionKt$requestBufferSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.utils.io.ReadSessionKt$requestBufferSuspend$1 r0 = new io.ktor.utils.io.ReadSessionKt$requestBufferSuspend$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.SuspendableReadSession r4 = (io.ktor.utils.io.SuspendableReadSession) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L43
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.await(r5, r0)
            if (r5 != r1) goto L43
            return r1
        L43:
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = r4.request(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ReadSessionKt.requestBufferSuspend(io.ktor.utils.io.SuspendableReadSession, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object requestBufferFallback(io.ktor.utils.io.ByteReadChannel r12, int r13, kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.internal.ChunkBuffer> r14) {
        /*
            boolean r0 = r14 instanceof io.ktor.utils.io.ReadSessionKt$requestBufferFallback$1
            if (r0 == 0) goto L14
            r0 = r14
            io.ktor.utils.io.ReadSessionKt$requestBufferFallback$1 r0 = (io.ktor.utils.io.ReadSessionKt$requestBufferFallback$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            io.ktor.utils.io.ReadSessionKt$requestBufferFallback$1 r0 = new io.ktor.utils.io.ReadSessionKt$requestBufferFallback$1
            r0.<init>(r14)
        L19:
            r11 = r0
            java.lang.Object r14 = r11.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            switch(r1) {
                case 0: goto L36;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L2d:
            java.lang.Object r12 = r11.L$0
            io.ktor.utils.io.core.internal.ChunkBuffer r12 = (io.ktor.utils.io.core.internal.ChunkBuffer) r12
            kotlin.ResultKt.throwOnFailure(r14)
            r13 = r14
            goto L6c
        L36:
            kotlin.ResultKt.throwOnFailure(r14)
            r1 = r12
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r12 = io.ktor.utils.io.core.internal.ChunkBuffer.INSTANCE
            io.ktor.utils.io.pool.ObjectPool r12 = r12.getPool()
            java.lang.Object r12 = r12.borrow()
            io.ktor.utils.io.core.internal.ChunkBuffer r12 = (io.ktor.utils.io.core.internal.ChunkBuffer) r12
            java.nio.ByteBuffer r2 = r12.getMemory()
            int r3 = r12.getWritePosition()
            long r3 = (long) r3
            long r7 = (long) r13
            r13 = r12
            io.ktor.utils.io.core.Buffer r13 = (io.ktor.utils.io.core.Buffer) r13
            r5 = 0
            int r6 = r13.getLimit()
            int r9 = r13.getWritePosition()
            int r6 = r6 - r9
            long r9 = (long) r6
            r11.L$0 = r12
            r13 = 1
            r11.label = r13
            r5 = 0
            java.lang.Object r13 = r1.mo224peekTolBXzO7A(r2, r3, r5, r7, r9, r11)
            if (r13 != r0) goto L6c
            return r0
        L6c:
            java.lang.Number r13 = (java.lang.Number) r13
            long r0 = r13.longValue()
            int r13 = (int) r0
            r12.commitWritten(r13)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ReadSessionKt.requestBufferFallback(io.ktor.utils.io.ByteReadChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final SuspendableReadSession readSessionFor(ByteReadChannel $this$readSessionFor) {
        if ($this$readSessionFor instanceof HasReadSession) {
            return ((HasReadSession) $this$readSessionFor).startReadSession();
        }
        return null;
    }
}
