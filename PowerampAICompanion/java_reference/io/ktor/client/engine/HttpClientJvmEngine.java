package io.ktor.client.engine;

import io.ktor.client.HttpClient;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.util.CoroutinesUtilsKt;
import io.ktor.util.InternalAPI;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobKt__JobKt;

/* compiled from: HttpClientJvmEngine.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "Use HttpClientEngineBase instead.", replaceWith = @ReplaceWith(expression = "HttpClientEngineBase", imports = {}))
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0011\u0010\u001a\u001a\u00020\fH\u0084@ø\u0001\u0000¢\u0006\u0002\u0010\u001bR\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\r\u001a\u00020\f8VX\u0096\u0084\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\n\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lio/ktor/client/engine/HttpClientJvmEngine;", "Lio/ktor/client/engine/HttpClientEngine;", "engineName", "", "(Ljava/lang/String;)V", "_dispatcher", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "get_dispatcher", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "_dispatcher$delegate", "Lkotlin/Lazy;", "clientContext", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "getCoroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext$delegate", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDispatcher$annotations", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "close", "", "createCallContext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public abstract class HttpClientJvmEngine implements HttpClientEngine {

    /* renamed from: _dispatcher$delegate, reason: from kotlin metadata */
    private final Lazy _dispatcher;
    private final CoroutineContext clientContext;

    /* renamed from: coroutineContext$delegate, reason: from kotlin metadata */
    private final Lazy coroutineContext;

    public static /* synthetic */ void getCoroutineContext$annotations() {
    }

    public static /* synthetic */ void getDispatcher$annotations() {
    }

    public HttpClientJvmEngine(final String engineName) {
        Intrinsics.checkNotNullParameter(engineName, "engineName");
        this.clientContext = CoroutinesUtilsKt.SilentSupervisor$default(null, 1, null);
        this._dispatcher = LazyKt.lazy(new HttpClientJvmEngine$_dispatcher$2(this));
        this.coroutineContext = LazyKt.lazy(new Function0<CoroutineContext>() { // from class: io.ktor.client.engine.HttpClientJvmEngine$coroutineContext$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CoroutineContext invoke() {
                ExecutorCoroutineDispatcher executorCoroutineDispatcher;
                CoroutineContext coroutineContext;
                executorCoroutineDispatcher = HttpClientJvmEngine.this.get_dispatcher();
                coroutineContext = HttpClientJvmEngine.this.clientContext;
                return executorCoroutineDispatcher.plus(coroutineContext).plus(new CoroutineName(engineName + "-context"));
            }
        });
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    public Set<HttpClientEngineCapability<?>> getSupportedCapabilities() {
        return HttpClientEngine.DefaultImpls.getSupportedCapabilities(this);
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    @InternalAPI
    public void install(HttpClient client) {
        HttpClientEngine.DefaultImpls.install(this, client);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ExecutorCoroutineDispatcher get_dispatcher() {
        return (ExecutorCoroutineDispatcher) this._dispatcher.getValue();
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    public CoroutineDispatcher getDispatcher() {
        return get_dispatcher();
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return (CoroutineContext) this.coroutineContext.getValue();
    }

    protected final Object createCallContext(Continuation<? super CoroutineContext> continuation) {
        CompletableJob callJob = JobKt.Job((Job) this.clientContext.get(Job.INSTANCE));
        final CoroutineContext callContext = getCoroutineContext().plus(callJob);
        Job parentCoroutineJob = (Job) continuation.getContext().get(Job.INSTANCE);
        final DisposableHandle onParentCancelCleanupHandle = parentCoroutineJob != null ? Job.DefaultImpls.invokeOnCompletion$default(parentCoroutineJob, true, false, new Function1<Throwable, Unit>() { // from class: io.ktor.client.engine.HttpClientJvmEngine$createCallContext$onParentCancelCleanupHandle$1
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
                if (cause != null) {
                    JobKt__JobKt.cancel$default(CoroutineContext.this, (CancellationException) null, 1, (Object) null);
                }
            }
        }, 2, null) : null;
        callJob.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: io.ktor.client.engine.HttpClientJvmEngine$createCallContext$2
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
            public final void invoke2(Throwable it) {
                DisposableHandle disposableHandle = DisposableHandle.this;
                if (disposableHandle != null) {
                    disposableHandle.dispose();
                }
            }
        });
        return callContext;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CoroutineContext.Element element = this.clientContext.get(Job.INSTANCE);
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type kotlinx.coroutines.CompletableJob");
        CompletableJob job = (CompletableJob) element;
        job.complete();
        job.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: io.ktor.client.engine.HttpClientJvmEngine$close$1
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
            public final void invoke2(Throwable it) {
                ExecutorCoroutineDispatcher executorCoroutineDispatcher;
                executorCoroutineDispatcher = HttpClientJvmEngine.this.get_dispatcher();
                executorCoroutineDispatcher.close();
            }
        });
    }
}
