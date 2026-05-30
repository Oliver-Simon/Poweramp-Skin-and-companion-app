package io.ktor.utils.io.jvm.javaio;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoopKt;
import kotlinx.coroutines.Job;

/* compiled from: Blocking.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b\"\u0018\u00002\u00020\u0010B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0004¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u0007H¤@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0005H\u0084Hø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0015\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u000bJ\r\u0010\u0016\u001a\u00020\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0010¢\u0006\u0004\b\u0019\u0010\u001aJ%\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005¢\u0006\u0004\b\u0019\u0010\u001fR\u0016\u0010!\u001a\u0004\u0018\u00010 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00070\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R$\u0010\u001e\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00058\u0004@BX\u0084\u000e¢\u0006\f\n\u0004\b\u001e\u0010&\u001a\u0004\b'\u0010(R$\u0010\u001d\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00058\u0004@BX\u0084\u000e¢\u0006\f\n\u0004\b\u001d\u0010&\u001a\u0004\b)\u0010(R\u0019\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006¢\u0006\f\n\u0004\b\u0002\u0010*\u001a\u0004\b+\u0010,\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lio/ktor/utils/io/jvm/javaio/BlockingAdapter;", "Lkotlinx/coroutines/Job;", "parent", "<init>", "(Lkotlinx/coroutines/Job;)V", "", "rc", "", "finish", "(I)V", "loop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/lang/Thread;", "thread", "parkingLoop", "(Ljava/lang/Thread;)V", "", "rendezvous", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/Continuation;", "ucont", "rendezvousBlock", "shutdown", "()V", "jobToken", "submitAndAwait", "(Ljava/lang/Object;)I", "", "buffer", TypedValues.CycleType.S_WAVE_OFFSET, "length", "([BII)I", "Lkotlinx/coroutines/DisposableHandle;", "disposable", "Lkotlinx/coroutines/DisposableHandle;", "end", "Lkotlin/coroutines/Continuation;", "<set-?>", "I", "getLength", "()I", "getOffset", "Lkotlinx/coroutines/Job;", "getParent", "()Lkotlinx/coroutines/Job;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
abstract class BlockingAdapter {
    static final /* synthetic */ AtomicReferenceFieldUpdater state$FU = AtomicReferenceFieldUpdater.newUpdater(BlockingAdapter.class, Object.class, "state");
    private final DisposableHandle disposable;
    private final Continuation<Unit> end;
    private int length;
    private int offset;
    private final Job parent;
    volatile /* synthetic */ int result;
    volatile /* synthetic */ Object state;

    /* JADX WARN: Multi-variable type inference failed */
    public BlockingAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    private static /* synthetic */ void getState$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object loop(Continuation<? super Unit> continuation);

    public BlockingAdapter(Job parent) {
        this.parent = parent;
        this.end = new Continuation<Unit>() { // from class: io.ktor.utils.io.jvm.javaio.BlockingAdapter$end$1
            private final CoroutineContext context;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.context = BlockingAdapter.this.getParent() != null ? UnsafeBlockingTrampoline.INSTANCE.plus(BlockingAdapter.this.getParent()) : UnsafeBlockingTrampoline.INSTANCE;
            }

            @Override // kotlin.coroutines.Continuation
            public CoroutineContext getContext() {
                return this.context;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.Continuation
            public void resumeWith(Object result) {
                Object cur$iv;
                Object current;
                Throwable it;
                DisposableHandle disposableHandle;
                Job parent2;
                Object value = Result.m513exceptionOrNullimpl(result);
                if (value == null) {
                    value = Unit.INSTANCE;
                }
                BlockingAdapter $this$getAndUpdate$iv = BlockingAdapter.this;
                do {
                    cur$iv = $this$getAndUpdate$iv.state;
                    if (!(cur$iv instanceof Thread ? true : cur$iv instanceof Continuation ? true : Intrinsics.areEqual(cur$iv, this))) {
                        return;
                    } else {
                        current = value;
                    }
                } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(BlockingAdapter.state$FU, $this$getAndUpdate$iv, cur$iv, current));
                if (cur$iv instanceof Thread) {
                    PollersKt.getParkingImpl().unpark(cur$iv);
                } else if ((cur$iv instanceof Continuation) && (it = Result.m513exceptionOrNullimpl(result)) != null) {
                    Result.Companion companion = Result.INSTANCE;
                    ((Continuation) cur$iv).resumeWith(Result.m510constructorimpl(ResultKt.createFailure(it)));
                }
                if (Result.m516isFailureimpl(result) && !(Result.m513exceptionOrNullimpl(result) instanceof CancellationException) && (parent2 = BlockingAdapter.this.getParent()) != null) {
                    Job.DefaultImpls.cancel$default(parent2, (CancellationException) null, 1, (Object) null);
                }
                disposableHandle = BlockingAdapter.this.disposable;
                if (disposableHandle != null) {
                    disposableHandle.dispose();
                }
            }
        };
        this.state = this;
        this.result = 0;
        Job job = this.parent;
        this.disposable = job != null ? job.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: io.ktor.utils.io.jvm.javaio.BlockingAdapter$disposable$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable cause) {
                Continuation continuation;
                if (cause != null) {
                    continuation = BlockingAdapter.this.end;
                    Result.Companion companion = Result.INSTANCE;
                    continuation.resumeWith(Result.m510constructorimpl(ResultKt.createFailure(cause)));
                }
            }
        }) : null;
        Function1 block = new BlockingAdapter$block$1(this, null);
        ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(block, 1)).invoke(this.end);
        if (this.state != this) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ BlockingAdapter(Job job, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : job);
    }

    public final Job getParent() {
        return this.parent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getOffset() {
        return this.offset;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getLength() {
        return this.length;
    }

    public final void shutdown() {
        DisposableHandle disposableHandle = this.disposable;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        Continuation<Unit> continuation = this.end;
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m510constructorimpl(ResultKt.createFailure(new CancellationException("Stream closed"))));
    }

    public final int submitAndAwait(byte[] buffer, int offset, int length) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.offset = offset;
        this.length = length;
        return submitAndAwait(buffer);
    }

    public final int submitAndAwait(Object jobToken) {
        Object cur$iv;
        Object upd$iv;
        Intrinsics.checkNotNullParameter(jobToken, "jobToken");
        Thread thread = Thread.currentThread();
        Continuation continuation = null;
        do {
            cur$iv = this.state;
            if (!(cur$iv instanceof Continuation)) {
                if (cur$iv instanceof Unit) {
                    return this.result;
                }
                if (cur$iv instanceof Throwable) {
                    throw ((Throwable) cur$iv);
                }
                if (cur$iv instanceof Thread) {
                    throw new IllegalStateException("There is already thread owning adapter");
                }
                if (Intrinsics.areEqual(cur$iv, this)) {
                    throw new IllegalStateException("Not yet started");
                }
                upd$iv = new NoWhenBranchMatchedException();
            } else {
                Intrinsics.checkNotNull(cur$iv, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any>");
                continuation = (Continuation) cur$iv;
                upd$iv = thread;
            }
            Intrinsics.checkNotNullExpressionValue(upd$iv, "when (value) {\n         …Exception()\n            }");
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$FU, this, cur$iv, upd$iv));
        Intrinsics.checkNotNull(continuation);
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m510constructorimpl(jobToken));
        Intrinsics.checkNotNullExpressionValue(thread, "thread");
        parkingLoop(thread);
        Object state = this.state;
        if (state instanceof Throwable) {
            throw ((Throwable) state);
        }
        return this.result;
    }

    private final void parkingLoop(Thread thread) {
        if (this.state != thread) {
            return;
        }
        if (!PollersKt.isParkingAllowed()) {
            BlockingKt.access$getADAPTER_LOGGER().warn("Blocking network thread detected. \nIt can possible lead to a performance decline or even a deadlock.\nPlease make sure you're using blocking IO primitives like InputStream and OutputStream only in \nthe context of Dispatchers.IO:\n```\nwithContext(Dispatchers.IO) {\n    myInputStream.read()\n}\n```");
        }
        while (true) {
            long nextEventTimeNanos = EventLoopKt.processNextEventInCurrentThread();
            if (this.state != thread) {
                return;
            }
            if (nextEventTimeNanos > 0) {
                PollersKt.getParkingImpl().park(nextEventTimeNanos);
            }
        }
    }

    private final Object rendezvous$$forInline(int rc, Continuation<Object> continuation) {
        this.result = rc;
        Continuation<Object> ucont = continuation;
        Object rendezvousBlock = rendezvousBlock(ucont);
        if (rendezvousBlock == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return rendezvousBlock;
    }

    protected final Object rendezvous(int rc, Continuation<Object> continuation) {
        this.result = rc;
        Object rendezvousBlock = rendezvousBlock(continuation);
        if (rendezvousBlock == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return rendezvousBlock;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object rendezvousBlock(Continuation<Object> ucont) {
        Object cur$iv;
        Object upd$iv;
        Object thread = null;
        do {
            cur$iv = this.state;
            if (cur$iv instanceof Thread) {
                thread = cur$iv;
                upd$iv = IntrinsicsKt.intercepted(ucont);
            } else {
                if (!Intrinsics.areEqual(cur$iv, this)) {
                    throw new IllegalStateException("Already suspended or in finished state");
                }
                upd$iv = IntrinsicsKt.intercepted(ucont);
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$FU, this, cur$iv, upd$iv));
        if (thread != null) {
            PollersKt.getParkingImpl().unpark(thread);
        }
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void finish(int rc) {
        this.result = rc;
    }
}
