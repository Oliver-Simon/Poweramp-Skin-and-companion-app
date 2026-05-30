package io.ktor.websocket;

import com.maxmpz.poweramp.player.TableDefs;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ReceiveChannel;

/* compiled from: WebSocketReader.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001*B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u0011\u0010#\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010%J\u0019\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0019\u0010)\u001a\u00020$2\u0006\u0010'\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lio/ktor/websocket/WebSocketReader;", "Lkotlinx/coroutines/CoroutineScope;", "byteChannel", "Lio/ktor/utils/io/ByteReadChannel;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "maxFrameSize", "", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/CoroutineContext;JLio/ktor/utils/io/pool/ObjectPool;)V", "collector", "Lio/ktor/websocket/SimpleFrameCollector;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "frameParser", "Lio/ktor/websocket/FrameParser;", "incoming", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/websocket/Frame;", "getIncoming", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", TableDefs.Queue.TABLE, "Lkotlinx/coroutines/channels/Channel;", "readerJob", "Lkotlinx/coroutines/Job;", "getReaderJob$annotations", "()V", "state", "Lio/ktor/websocket/WebSocketReader$State;", "handleFrameIfProduced", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseLoop", "buffer", "(Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLoop", "State", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class WebSocketReader implements CoroutineScope {
    private final ByteReadChannel byteChannel;
    private final SimpleFrameCollector collector;
    private final CoroutineContext coroutineContext;
    private final FrameParser frameParser;
    private long maxFrameSize;
    private final Channel<Frame> queue;
    private final Job readerJob;
    private State state;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WebSocketReader.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/websocket/WebSocketReader$State;", "", "(Ljava/lang/String;I)V", "HEADER", "BODY", "CLOSED", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public enum State {
        HEADER,
        BODY,
        CLOSED
    }

    /* compiled from: WebSocketReader.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[State.values().length];
            try {
                iArr[State.HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[State.BODY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[State.CLOSED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static /* synthetic */ void getReaderJob$annotations() {
    }

    public WebSocketReader(ByteReadChannel byteChannel, CoroutineContext coroutineContext, long maxFrameSize, ObjectPool<ByteBuffer> pool) {
        Intrinsics.checkNotNullParameter(byteChannel, "byteChannel");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        Intrinsics.checkNotNullParameter(pool, "pool");
        this.byteChannel = byteChannel;
        this.coroutineContext = coroutineContext;
        this.maxFrameSize = maxFrameSize;
        this.state = State.HEADER;
        this.frameParser = new FrameParser();
        this.collector = new SimpleFrameCollector();
        this.queue = ChannelKt.Channel$default(8, null, null, 6, null);
        this.readerJob = BuildersKt.launch(this, new CoroutineName("ws-reader"), CoroutineStart.ATOMIC, new WebSocketReader$readerJob$1(pool, this, null));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ WebSocketReader(io.ktor.utils.io.ByteReadChannel r7, kotlin.coroutines.CoroutineContext r8, long r9, io.ktor.utils.io.pool.ObjectPool r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r6 = this;
            r12 = r12 & 8
            if (r12 == 0) goto La
            io.ktor.utils.io.pool.ObjectPool r11 = io.ktor.util.cio.ByteBufferPoolKt.getKtorDefaultPool()
            r5 = r11
            goto Lb
        La:
            r5 = r11
        Lb:
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r0.<init>(r1, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketReader.<init>(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.CoroutineContext, long, io.ktor.utils.io.pool.ObjectPool, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final long getMaxFrameSize() {
        return this.maxFrameSize;
    }

    public final void setMaxFrameSize(long j) {
        this.maxFrameSize = j;
    }

    public final ReceiveChannel<Frame> getIncoming() {
        return this.queue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x008d -> B:12:0x0091). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readLoop(java.nio.ByteBuffer r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.websocket.WebSocketReader$readLoop$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.websocket.WebSocketReader$readLoop$1 r0 = (io.ktor.websocket.WebSocketReader$readLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.websocket.WebSocketReader$readLoop$1 r0 = new io.ktor.websocket.WebSocketReader$readLoop$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L49;
                case 1: goto L39;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.websocket.WebSocketReader r2 = (io.ktor.websocket.WebSocketReader) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L91
        L39:
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.websocket.WebSocketReader r2 = (io.ktor.websocket.WebSocketReader) r2
            kotlin.ResultKt.throwOnFailure(r8)
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r8
            goto L6c
        L49:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            r7.clear()
        L50:
            io.ktor.websocket.WebSocketReader$State r3 = r2.state
            io.ktor.websocket.WebSocketReader$State r4 = io.ktor.websocket.WebSocketReader.State.CLOSED
            if (r3 == r4) goto L95
            io.ktor.utils.io.ByteReadChannel r3 = r2.byteChannel
            r0.L$0 = r2
            r0.L$1 = r7
            r4 = 1
            r0.label = r4
            java.lang.Object r3 = r3.readAvailable(r7, r0)
            if (r3 != r1) goto L66
            return r1
        L66:
            r5 = r0
            r0 = r8
            r8 = r3
            r3 = r2
            r2 = r1
            r1 = r5
        L6c:
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            r4 = -1
            if (r8 != r4) goto L7c
            io.ktor.websocket.WebSocketReader$State r8 = io.ktor.websocket.WebSocketReader.State.CLOSED
            r3.state = r8
            r8 = r0
            r0 = r1
            goto L95
        L7c:
            r7.flip()
            r1.L$0 = r3
            r1.L$1 = r7
            r8 = 2
            r1.label = r8
            java.lang.Object r8 = r3.parseLoop(r7, r1)
            if (r8 != r2) goto L8d
            return r2
        L8d:
            r8 = r0
            r0 = r1
            r1 = r2
            r2 = r3
        L91:
            r7.compact()
            goto L50
        L95:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketReader.readLoop(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object parseLoop(java.nio.ByteBuffer r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ktor.websocket.WebSocketReader$parseLoop$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.websocket.WebSocketReader$parseLoop$1 r0 = (io.ktor.websocket.WebSocketReader$parseLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.websocket.WebSocketReader$parseLoop$1 r0 = new io.ktor.websocket.WebSocketReader$parseLoop$1
            r0.<init>(r7, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L45;
                case 1: goto L38;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            java.lang.Object r8 = r0.L$1
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.websocket.WebSocketReader r2 = (io.ktor.websocket.WebSocketReader) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L73
        L38:
            java.lang.Object r8 = r0.L$1
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.websocket.WebSocketReader r2 = (io.ktor.websocket.WebSocketReader) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto Lb8
        L45:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
        L49:
            boolean r3 = r8.hasRemaining()
            if (r3 == 0) goto Lc8
            io.ktor.websocket.WebSocketReader$State r3 = r2.state
            int[] r4 = io.ktor.websocket.WebSocketReader.WhenMappings.$EnumSwitchMapping$0
            int r3 = r3.ordinal()
            r3 = r4[r3]
            switch(r3) {
                case 1: goto L74;
                case 2: goto L60;
                case 3: goto L5d;
                default: goto L5c;
            }
        L5c:
            goto L49
        L5d:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L60:
            io.ktor.websocket.SimpleFrameCollector r3 = r2.collector
            r3.handle(r8)
            r0.L$0 = r2
            r0.L$1 = r8
            r3 = 2
            r0.label = r3
            java.lang.Object r3 = r2.handleFrameIfProduced(r0)
            if (r3 != r1) goto L73
            return r1
        L73:
            goto L49
        L74:
            io.ktor.websocket.FrameParser r3 = r2.frameParser
            r3.frame(r8)
            io.ktor.websocket.FrameParser r3 = r2.frameParser
            boolean r3 = r3.getBodyReady()
            if (r3 == 0) goto Lc5
            io.ktor.websocket.WebSocketReader$State r3 = io.ktor.websocket.WebSocketReader.State.BODY
            r2.state = r3
            io.ktor.websocket.FrameParser r3 = r2.frameParser
            long r3 = r3.getLength()
            r5 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 > 0) goto Lb9
            io.ktor.websocket.FrameParser r3 = r2.frameParser
            long r3 = r3.getLength()
            long r5 = r2.maxFrameSize
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 > 0) goto Lb9
            io.ktor.websocket.SimpleFrameCollector r3 = r2.collector
            io.ktor.websocket.FrameParser r4 = r2.frameParser
            long r4 = r4.getLength()
            int r4 = (int) r4
            r3.start(r4, r8)
            r0.L$0 = r2
            r0.L$1 = r8
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.handleFrameIfProduced(r0)
            if (r3 != r1) goto Lb8
            return r1
        Lb8:
            goto L49
        Lb9:
            io.ktor.websocket.FrameTooBigException r8 = new io.ktor.websocket.FrameTooBigException
            io.ktor.websocket.FrameParser r1 = r2.frameParser
            long r3 = r1.getLength()
            r8.<init>(r3)
            throw r8
        Lc5:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        Lc8:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketReader.parseLoop(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object handleFrameIfProduced(kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r12 = this;
            boolean r0 = r13 instanceof io.ktor.websocket.WebSocketReader$handleFrameIfProduced$1
            if (r0 == 0) goto L14
            r0 = r13
            io.ktor.websocket.WebSocketReader$handleFrameIfProduced$1 r0 = (io.ktor.websocket.WebSocketReader$handleFrameIfProduced$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            io.ktor.websocket.WebSocketReader$handleFrameIfProduced$1 r0 = new io.ktor.websocket.WebSocketReader$handleFrameIfProduced$1
            r0.<init>(r12, r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L2c:
            java.lang.Object r1 = r0.L$0
            io.ktor.websocket.WebSocketReader r1 = (io.ktor.websocket.WebSocketReader) r1
            kotlin.ResultKt.throwOnFailure(r13)
            goto L8c
        L34:
            kotlin.ResultKt.throwOnFailure(r13)
            r2 = r12
            io.ktor.websocket.SimpleFrameCollector r3 = r2.collector
            boolean r3 = r3.getHasRemaining()
            if (r3 != 0) goto L91
            io.ktor.websocket.FrameParser r3 = r2.frameParser
            io.ktor.websocket.FrameType r3 = r3.getFrameType()
            io.ktor.websocket.FrameType r4 = io.ktor.websocket.FrameType.CLOSE
            if (r3 != r4) goto L4d
            io.ktor.websocket.WebSocketReader$State r3 = io.ktor.websocket.WebSocketReader.State.CLOSED
            goto L4f
        L4d:
            io.ktor.websocket.WebSocketReader$State r3 = io.ktor.websocket.WebSocketReader.State.HEADER
        L4f:
            r2.state = r3
            io.ktor.websocket.FrameParser r3 = r2.frameParser
            r4 = 0
            io.ktor.websocket.Frame$Companion r5 = io.ktor.websocket.Frame.INSTANCE
            boolean r6 = r3.getFin()
            io.ktor.websocket.FrameType r7 = r3.getFrameType()
            io.ktor.websocket.SimpleFrameCollector r8 = r2.collector
            java.lang.Integer r9 = r3.getMaskKey()
            java.nio.ByteBuffer r8 = r8.take(r9)
            byte[] r8 = io.ktor.util.NIOKt.moveToByteArray(r8)
            boolean r9 = r3.getRsv1()
            boolean r10 = r3.getRsv2()
            boolean r11 = r3.getRsv3()
            io.ktor.websocket.Frame r3 = r5.byType(r6, r7, r8, r9, r10, r11)
            kotlinx.coroutines.channels.Channel<io.ktor.websocket.Frame> r4 = r2.queue
            r0.L$0 = r2
            r5 = 1
            r0.label = r5
            java.lang.Object r3 = r4.send(r3, r0)
            if (r3 != r1) goto L8b
            return r1
        L8b:
            r1 = r2
        L8c:
            io.ktor.websocket.FrameParser r2 = r1.frameParser
            r2.bodyComplete()
        L91:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketReader.handleFrameIfProduced(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
