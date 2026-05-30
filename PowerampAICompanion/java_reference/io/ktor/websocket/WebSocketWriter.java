package io.ktor.websocket;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.maxmpz.poweramp.player.TableDefs;
import io.ktor.util.cio.ByteBufferPoolKt;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.SendChannel;

/* compiled from: WebSocketWriter.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u00010B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\b\u0010\"\u001a\u00020#H\u0007J\b\u0010$\u001a\u00020#H\u0002J!\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0011\u0010)\u001a\u00020#H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010*J\u0019\u0010+\u001a\u00020#2\u0006\u0010,\u001a\u00020\u0014H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010-J\u0019\u0010.\u001a\u00020#2\u0006\u0010'\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010/R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u00061"}, d2 = {"Lio/ktor/websocket/WebSocketWriter;", "Lkotlinx/coroutines/CoroutineScope;", "writeChannel", "Lio/ktor/utils/io/ByteWriteChannel;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "masking", "", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/CoroutineContext;ZLio/ktor/utils/io/pool/ObjectPool;)V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getMasking", "()Z", "setMasking", "(Z)V", "outgoing", "Lkotlinx/coroutines/channels/SendChannel;", "Lio/ktor/websocket/Frame;", "getOutgoing", "()Lkotlinx/coroutines/channels/SendChannel;", "getPool", "()Lio/ktor/utils/io/pool/ObjectPool;", TableDefs.Queue.TABLE, "Lkotlinx/coroutines/channels/Channel;", "", "serializer", "Lio/ktor/websocket/Serializer;", "writeLoopJob", "Lkotlinx/coroutines/Job;", "getWriteLoopJob$annotations", "()V", "close", "", "drainQueueAndDiscard", "drainQueueAndSerialize", "firstMsg", "buffer", "(Lio/ktor/websocket/Frame;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flush", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "send", TypedValues.AttributesType.S_FRAME, "(Lio/ktor/websocket/Frame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeLoop", "(Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "FlushRequest", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class WebSocketWriter implements CoroutineScope {
    private final CoroutineContext coroutineContext;
    private boolean masking;
    private final ObjectPool<ByteBuffer> pool;
    private final Channel<Object> queue;
    private final Serializer serializer;
    private final ByteWriteChannel writeChannel;
    private final Job writeLoopJob;

    private static /* synthetic */ void getWriteLoopJob$annotations() {
    }

    public WebSocketWriter(ByteWriteChannel writeChannel, CoroutineContext coroutineContext, boolean masking, ObjectPool<ByteBuffer> pool) {
        Intrinsics.checkNotNullParameter(writeChannel, "writeChannel");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        Intrinsics.checkNotNullParameter(pool, "pool");
        this.writeChannel = writeChannel;
        this.coroutineContext = coroutineContext;
        this.masking = masking;
        this.pool = pool;
        this.queue = ChannelKt.Channel$default(8, null, null, 6, null);
        this.serializer = new Serializer();
        this.writeLoopJob = BuildersKt.launch(this, new CoroutineName("ws-writer"), CoroutineStart.ATOMIC, new WebSocketWriter$writeLoopJob$1(this, null));
    }

    public /* synthetic */ WebSocketWriter(ByteWriteChannel byteWriteChannel, CoroutineContext coroutineContext, boolean z, ObjectPool objectPool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteWriteChannel, coroutineContext, (i & 4) != 0 ? false : z, (i & 8) != 0 ? ByteBufferPoolKt.getKtorDefaultPool() : objectPool);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final boolean getMasking() {
        return this.masking;
    }

    public final void setMasking(boolean z) {
        this.masking = z;
    }

    public final ObjectPool<ByteBuffer> getPool() {
        return this.pool;
    }

    public final SendChannel<Frame> getOutgoing() {
        return this.queue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(7:1|(2:3|(4:5|6|7|8))|60|6|7|8|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0114, code lost:
    
        r11 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00f9, code lost:
    
        r11 = th;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0024. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0079 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0089 A[Catch: all -> 0x00ef, ChannelWriteException -> 0x00f4, TryCatch #5 {ChannelWriteException -> 0x00f4, all -> 0x00ef, blocks: (B:16:0x00a6, B:27:0x0081, B:29:0x0089, B:31:0x0092, B:35:0x00b5, B:37:0x00b9, B:38:0x00c5, B:39:0x00dd), top: B:15:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00a5 -> B:15:0x00a6). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00b9 -> B:23:0x006a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeLoop(java.nio.ByteBuffer r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketWriter.writeLoop(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0054, code lost:
    
        throw new java.lang.IllegalArgumentException("unknown message " + r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void drainQueueAndDiscard() {
        /*
            r4 = this;
            kotlinx.coroutines.channels.Channel<java.lang.Object> r0 = r4.queue
            kotlinx.coroutines.channels.SendChannel r0 = (kotlinx.coroutines.channels.SendChannel) r0
            r1 = 0
            r2 = 1
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r1, r2, r1)
        La:
            kotlinx.coroutines.channels.Channel<java.lang.Object> r0 = r4.queue     // Catch: java.util.concurrent.CancellationException -> L56
            java.lang.Object r0 = r0.mo2015tryReceivePtdJZtk()     // Catch: java.util.concurrent.CancellationException -> L56
            java.lang.Object r0 = kotlinx.coroutines.channels.ChannelResult.m2025getOrNullimpl(r0)     // Catch: java.util.concurrent.CancellationException -> L56
            if (r0 != 0) goto L17
            goto L57
        L17:
            boolean r1 = r0 instanceof io.ktor.websocket.Frame.Close     // Catch: java.util.concurrent.CancellationException -> L56
            if (r1 != 0) goto L55
            boolean r1 = r0 instanceof io.ktor.websocket.Frame.Ping     // Catch: java.util.concurrent.CancellationException -> L56
            if (r1 == 0) goto L22
            r1 = r2
            goto L24
        L22:
            boolean r1 = r0 instanceof io.ktor.websocket.Frame.Pong     // Catch: java.util.concurrent.CancellationException -> L56
        L24:
            if (r1 != 0) goto L55
            boolean r1 = r0 instanceof io.ktor.websocket.WebSocketWriter.FlushRequest     // Catch: java.util.concurrent.CancellationException -> L56
            if (r1 == 0) goto L31
            r1 = r0
            io.ktor.websocket.WebSocketWriter$FlushRequest r1 = (io.ktor.websocket.WebSocketWriter.FlushRequest) r1     // Catch: java.util.concurrent.CancellationException -> L56
            r1.complete()     // Catch: java.util.concurrent.CancellationException -> L56
            goto L55
        L31:
            boolean r1 = r0 instanceof io.ktor.websocket.Frame.Text     // Catch: java.util.concurrent.CancellationException -> L56
            if (r1 == 0) goto L37
            r1 = r2
            goto L39
        L37:
            boolean r1 = r0 instanceof io.ktor.websocket.Frame.Binary     // Catch: java.util.concurrent.CancellationException -> L56
        L39:
            if (r1 == 0) goto L3c
            goto L55
        L3c:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch: java.util.concurrent.CancellationException -> L56
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.util.concurrent.CancellationException -> L56
            r2.<init>()     // Catch: java.util.concurrent.CancellationException -> L56
            java.lang.String r3 = "unknown message "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.util.concurrent.CancellationException -> L56
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch: java.util.concurrent.CancellationException -> L56
            java.lang.String r2 = r2.toString()     // Catch: java.util.concurrent.CancellationException -> L56
            r1.<init>(r2)     // Catch: java.util.concurrent.CancellationException -> L56
            throw r1     // Catch: java.util.concurrent.CancellationException -> L56
        L55:
            goto La
        L56:
            r0 = move-exception
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketWriter.drainQueueAndDiscard():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00ab, code lost:
    
        kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r2.queue, null, 1, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x012e, code lost:
    
        if (r2.hasRemaining() == false) goto L65;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0023. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0128 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0075 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0072 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0102 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /* JADX WARN: Type inference failed for: r10v1, types: [int] */
    /* JADX WARN: Type inference failed for: r10v21, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v23, types: [int] */
    /* JADX WARN: Type inference failed for: r10v27 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0100 -> B:12:0x0103). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object drainQueueAndSerialize(io.ktor.websocket.Frame r10, java.nio.ByteBuffer r11, kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketWriter.drainQueueAndSerialize(io.ktor.websocket.Frame, java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object send(Frame frame, Continuation<? super Unit> continuation) {
        Object send = this.queue.send(frame, continuation);
        return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:1|(2:3|(4:5|6|7|8))|36|6|7|8|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007f, code lost:
    
        r4.complete();
        r4 = r6.writeLoopJob;
        r0.L$0 = r5;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.label = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0091, code lost:
    
        if (r4.join(r0) == r1) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0093, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0094, code lost:
    
        r4 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0079, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007a, code lost:
    
        r4.complete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007d, code lost:
    
        throw r1;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object flush(kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof io.ktor.websocket.WebSocketWriter$flush$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.websocket.WebSocketWriter$flush$1 r0 = (io.ktor.websocket.WebSocketWriter$flush$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.websocket.WebSocketWriter$flush$1 r0 = new io.ktor.websocket.WebSocketWriter$flush$1
            r0.<init>(r9, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            switch(r2) {
                case 0: goto L4c;
                case 1: goto L3b;
                case 2: goto L32;
                case 3: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L2d:
            kotlin.ResultKt.throwOnFailure(r10)
            goto La8
        L32:
            r2 = 0
            java.lang.Object r4 = r0.L$0
            io.ktor.websocket.WebSocketWriter$FlushRequest r4 = (io.ktor.websocket.WebSocketWriter.FlushRequest) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L95
        L3b:
            r2 = 0
            java.lang.Object r4 = r0.L$2
            io.ktor.websocket.WebSocketWriter$FlushRequest r4 = (io.ktor.websocket.WebSocketWriter.FlushRequest) r4
            java.lang.Object r5 = r0.L$1
            io.ktor.websocket.WebSocketWriter$FlushRequest r5 = (io.ktor.websocket.WebSocketWriter.FlushRequest) r5
            java.lang.Object r6 = r0.L$0
            io.ktor.websocket.WebSocketWriter r6 = (io.ktor.websocket.WebSocketWriter) r6
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L79 kotlinx.coroutines.channels.ClosedSendChannelException -> L7e
            goto L78
        L4c:
            kotlin.ResultKt.throwOnFailure(r10)
            r6 = r9
            io.ktor.websocket.WebSocketWriter$FlushRequest r5 = new io.ktor.websocket.WebSocketWriter$FlushRequest
            kotlin.coroutines.CoroutineContext r2 = r6.getCoroutineContext()
            kotlinx.coroutines.Job$Key r4 = kotlinx.coroutines.Job.INSTANCE
            kotlin.coroutines.CoroutineContext$Key r4 = (kotlin.coroutines.CoroutineContext.Key) r4
            kotlin.coroutines.CoroutineContext$Element r2 = r2.get(r4)
            kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
            r5.<init>(r2)
            r4 = r5
            r2 = 0
            kotlinx.coroutines.channels.Channel<java.lang.Object> r7 = r6.queue     // Catch: java.lang.Throwable -> L79 kotlinx.coroutines.channels.ClosedSendChannelException -> L7e
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L79 kotlinx.coroutines.channels.ClosedSendChannelException -> L7e
            r0.L$1 = r5     // Catch: java.lang.Throwable -> L79 kotlinx.coroutines.channels.ClosedSendChannelException -> L7e
            r0.L$2 = r4     // Catch: java.lang.Throwable -> L79 kotlinx.coroutines.channels.ClosedSendChannelException -> L7e
            r8 = 1
            r0.label = r8     // Catch: java.lang.Throwable -> L79 kotlinx.coroutines.channels.ClosedSendChannelException -> L7e
            java.lang.Object r7 = r7.send(r4, r0)     // Catch: java.lang.Throwable -> L79 kotlinx.coroutines.channels.ClosedSendChannelException -> L7e
            if (r7 != r1) goto L78
            return r1
        L78:
            goto L96
        L79:
            r1 = move-exception
            r4.complete()
            throw r1
        L7e:
            r7 = move-exception
            r4.complete()
            kotlinx.coroutines.Job r4 = r6.writeLoopJob
            r0.L$0 = r5
            r0.L$1 = r3
            r0.L$2 = r3
            r7 = 2
            r0.label = r7
            java.lang.Object r4 = r4.join(r0)
            if (r4 != r1) goto L94
            return r1
        L94:
            r4 = r5
        L95:
            r5 = r4
        L96:
            r0.L$0 = r3
            r0.L$1 = r3
            r0.L$2 = r3
            r2 = 3
            r0.label = r2
            java.lang.Object r2 = r5.await(r0)
            if (r2 != r1) goto La8
            return r1
        La8:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketWriter.flush(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Will be removed")
    public final void close() {
        SendChannel.DefaultImpls.close$default(this.queue, null, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WebSocketWriter.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lio/ktor/websocket/WebSocketWriter$FlushRequest;", "", "parent", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "done", "Lkotlinx/coroutines/CompletableJob;", "await", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "complete", "", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class FlushRequest {
        private final CompletableJob done;

        public FlushRequest(Job parent) {
            this.done = JobKt.Job(parent);
        }

        public final boolean complete() {
            return this.done.complete();
        }

        public final Object await(Continuation<? super Unit> continuation) {
            Object join = this.done.join(continuation);
            return join == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? join : Unit.INSTANCE;
        }
    }
}
