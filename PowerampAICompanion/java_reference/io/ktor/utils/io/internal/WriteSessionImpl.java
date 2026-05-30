package io.ktor.utils.io.internal;

import io.ktor.utils.io.ByteBufferChannel;
import io.ktor.utils.io.WriterSuspendSession;
import io.ktor.utils.io.core.BufferUtilsJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WriteSessionImpl.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u0001\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\b\u0010\u0011\u001a\u00020\u000fH\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u0019\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u0019\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\tH\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\tH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lio/ktor/utils/io/internal/WriteSessionImpl;", "Lio/ktor/utils/io/WriterSuspendSession;", "channel", "Lio/ktor/utils/io/ByteBufferChannel;", "(Lio/ktor/utils/io/ByteBufferChannel;)V", "byteBuffer", "Ljava/nio/ByteBuffer;", "current", "locked", "", "ringBufferCapacity", "Lio/ktor/utils/io/internal/RingBufferCapacity;", "view", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "begin", "", "complete", "flush", "request", "min", "tryAwait", "n", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryAwaitJoinSwitch", "written", "writtenFailed", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class WriteSessionImpl implements WriterSuspendSession {
    private ByteBuffer byteBuffer;
    private ByteBufferChannel current;
    private int locked;
    private RingBufferCapacity ringBufferCapacity;
    private ChunkBuffer view;

    public WriteSessionImpl(ByteBufferChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        this.current = channel.resolveChannelInstance$ktor_io();
        this.byteBuffer = ChunkBuffer.INSTANCE.getEmpty().getMemory();
        this.view = ChunkBuffer.INSTANCE.getEmpty();
        this.ringBufferCapacity = this.current.currentState$ktor_io().capacity;
    }

    public final void begin() {
        this.current = this.current.resolveChannelInstance$ktor_io();
        ByteBuffer byteBuffer = this.current.setupStateForWrite$ktor_io();
        if (byteBuffer == null) {
            return;
        }
        this.byteBuffer = byteBuffer;
        this.view = BufferUtilsJvmKt.ChunkBuffer$default(this.current.currentState$ktor_io().backingBuffer, null, 2, null);
        BufferUtilsJvmKt.resetFromContentToWrite(this.view, this.byteBuffer);
        this.ringBufferCapacity = this.current.currentState$ktor_io().capacity;
    }

    public final void complete() {
        if (this.locked > 0) {
            this.ringBufferCapacity.completeRead(this.locked);
            this.locked = 0;
        }
        this.current.restoreStateAfterWrite$ktor_io();
        this.current.tryTerminate$ktor_io();
    }

    @Override // io.ktor.utils.io.WriterSession
    public ChunkBuffer request(int min) {
        this.locked += this.ringBufferCapacity.tryWriteAtLeast(0);
        if (this.locked < min) {
            return null;
        }
        this.current.prepareWriteBuffer$ktor_io(this.byteBuffer, this.locked);
        if (this.byteBuffer.remaining() < min) {
            return null;
        }
        BufferUtilsJvmKt.resetFromContentToWrite(this.view, this.byteBuffer);
        return this.view;
    }

    @Override // io.ktor.utils.io.WriterSession
    public void written(int n) {
        if (n < 0 || n > this.locked) {
            writtenFailed(n);
            throw new KotlinNothingValueException();
        }
        this.locked -= n;
        this.current.bytesWrittenFromSession$ktor_io(this.byteBuffer, this.ringBufferCapacity, n);
    }

    private final Void writtenFailed(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Written bytes count shouldn't be negative: " + n);
        }
        throw new IllegalStateException("Unable to mark " + n + " bytes as written: only " + this.locked + " were pre-locked.");
    }

    @Override // io.ktor.utils.io.WriterSuspendSession
    public Object tryAwait(int n, Continuation<? super Unit> continuation) {
        JoiningState joining = this.current.getJoining();
        if (joining != null) {
            Object tryAwaitJoinSwitch = tryAwaitJoinSwitch(n, continuation);
            return tryAwaitJoinSwitch == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? tryAwaitJoinSwitch : Unit.INSTANCE;
        }
        if (this.locked >= n) {
            return Unit.INSTANCE;
        }
        if (this.locked > 0) {
            this.ringBufferCapacity.completeRead(this.locked);
            this.locked = 0;
        }
        Object tryWriteSuspend$ktor_io = this.current.tryWriteSuspend$ktor_io(n, continuation);
        return tryWriteSuspend$ktor_io == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? tryWriteSuspend$ktor_io : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tryAwaitJoinSwitch(int r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.internal.WriteSessionImpl$tryAwaitJoinSwitch$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.internal.WriteSessionImpl$tryAwaitJoinSwitch$1 r0 = (io.ktor.utils.io.internal.WriteSessionImpl$tryAwaitJoinSwitch$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.internal.WriteSessionImpl$tryAwaitJoinSwitch$1 r0 = new io.ktor.utils.io.internal.WriteSessionImpl$tryAwaitJoinSwitch$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.internal.WriteSessionImpl r6 = (io.ktor.utils.io.internal.WriteSessionImpl) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L62
        L34:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
            int r3 = r2.locked
            if (r3 <= 0) goto L46
            io.ktor.utils.io.internal.RingBufferCapacity r3 = r2.ringBufferCapacity
            int r4 = r2.locked
            r3.completeRead(r4)
            r3 = 0
            r2.locked = r3
        L46:
            r2.flush()
            io.ktor.utils.io.ByteBufferChannel r3 = r2.current
            r3.restoreStateAfterWrite$ktor_io()
            io.ktor.utils.io.ByteBufferChannel r3 = r2.current
            r3.tryTerminate$ktor_io()
            io.ktor.utils.io.ByteBufferChannel r3 = r2.current
            r0.L$0 = r2
            r4 = 1
            r0.label = r4
            java.lang.Object r6 = r3.tryWriteSuspend$ktor_io(r6, r0)
            if (r6 != r1) goto L61
            return r1
        L61:
            r6 = r2
        L62:
            io.ktor.utils.io.ByteBufferChannel r1 = r6.current
            io.ktor.utils.io.ByteBufferChannel r1 = r1.resolveChannelInstance$ktor_io()
            r6.current = r1
            io.ktor.utils.io.ByteBufferChannel r1 = r6.current
            java.nio.ByteBuffer r1 = r1.setupStateForWrite$ktor_io()
            if (r1 != 0) goto L73
            goto L96
        L73:
            r6.byteBuffer = r1
            io.ktor.utils.io.ByteBufferChannel r1 = r6.current
            io.ktor.utils.io.internal.ReadWriteBufferState r1 = r1.currentState$ktor_io()
            java.nio.ByteBuffer r1 = r1.backingBuffer
            r2 = 2
            r3 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.BufferUtilsJvmKt.ChunkBuffer$default(r1, r3, r2, r3)
            r6.view = r1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = r6.view
            java.nio.ByteBuffer r2 = r6.byteBuffer
            io.ktor.utils.io.core.BufferUtilsJvmKt.resetFromContentToWrite(r1, r2)
            io.ktor.utils.io.ByteBufferChannel r1 = r6.current
            io.ktor.utils.io.internal.ReadWriteBufferState r1 = r1.currentState$ktor_io()
            io.ktor.utils.io.internal.RingBufferCapacity r1 = r1.capacity
            r6.ringBufferCapacity = r1
        L96:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.WriteSessionImpl.tryAwaitJoinSwitch(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.utils.io.WriterSession
    public void flush() {
        this.current.flush();
    }
}
