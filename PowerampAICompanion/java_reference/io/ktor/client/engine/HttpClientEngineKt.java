package io.ktor.client.engine;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.request.HttpRequestData;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.http.UnsafeHeaderException;
import io.ktor.util.AttributeKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* compiled from: HttpClientEngine.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a9\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\b\b\u0000\u0010\u000f*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u00020\n0\u0012¢\u0006\u0002\b\u0013\u001a\u001d\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0019\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001e\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"CALL_COROUTINE", "Lkotlinx/coroutines/CoroutineName;", "getCALL_COROUTINE", "()Lkotlinx/coroutines/CoroutineName;", "CLIENT_CONFIG", "Lio/ktor/util/AttributeKey;", "Lio/ktor/client/HttpClientConfig;", "getCLIENT_CONFIG", "()Lio/ktor/util/AttributeKey;", "validateHeaders", "", "request", "Lio/ktor/client/request/HttpRequestData;", "config", "Lio/ktor/client/engine/HttpClientEngineFactory;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/client/engine/HttpClientEngineConfig;", "nested", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "createCallContext", "Lkotlin/coroutines/CoroutineContext;", "Lio/ktor/client/engine/HttpClientEngine;", "parentJob", "Lkotlinx/coroutines/Job;", "(Lio/ktor/client/engine/HttpClientEngine;Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpClientEngineKt {
    private static final CoroutineName CALL_COROUTINE = new CoroutineName("call-context");
    private static final AttributeKey<HttpClientConfig<?>> CLIENT_CONFIG = new AttributeKey<>("client-config");

    public static final CoroutineName getCALL_COROUTINE() {
        return CALL_COROUTINE;
    }

    public static final AttributeKey<HttpClientConfig<?>> getCLIENT_CONFIG() {
        return CLIENT_CONFIG;
    }

    public static final <T extends HttpClientEngineConfig> HttpClientEngineFactory<T> config(final HttpClientEngineFactory<? extends T> httpClientEngineFactory, final Function1<? super T, Unit> nested) {
        Intrinsics.checkNotNullParameter(httpClientEngineFactory, "<this>");
        Intrinsics.checkNotNullParameter(nested, "nested");
        return (HttpClientEngineFactory) new HttpClientEngineFactory<T>() { // from class: io.ktor.client.engine.HttpClientEngineKt$config$1
            @Override // io.ktor.client.engine.HttpClientEngineFactory
            public HttpClientEngine create(final Function1<? super T, Unit> block) {
                Intrinsics.checkNotNullParameter(block, "block");
                HttpClientEngineFactory<T> httpClientEngineFactory2 = httpClientEngineFactory;
                final Function1<T, Unit> function1 = nested;
                return httpClientEngineFactory2.create(new Function1<T, Unit>() { // from class: io.ktor.client.engine.HttpClientEngineKt$config$1$create$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Object p1) {
                        invoke((HttpClientEngineConfig) p1);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Incorrect types in method signature: (TT;)V */
                    public final void invoke(HttpClientEngineConfig create) {
                        Intrinsics.checkNotNullParameter(create, "$this$create");
                        function1.invoke(create);
                        block.invoke(create);
                    }
                });
            }
        };
    }

    public static final Object createCallContext(HttpClientEngine $this$createCallContext, Job parentJob, Continuation<? super CoroutineContext> continuation) {
        CompletableJob callJob = JobKt.Job(parentJob);
        CoroutineContext callContext = $this$createCallContext.getCoroutineContext().plus(callJob).plus(CALL_COROUTINE);
        Job userJob$iv = (Job) continuation.get$context().get(Job.INSTANCE);
        if (userJob$iv != null) {
            DisposableHandle cleanupHandler$iv = Job.DefaultImpls.invokeOnCompletion$default(userJob$iv, true, false, new UtilsKt$attachToUserJob$cleanupHandler$1(callJob), 2, null);
            callJob.invokeOnCompletion(new UtilsKt$attachToUserJob$2(cleanupHandler$iv));
        }
        return callContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateHeaders(HttpRequestData request) {
        Headers requestHeaders = request.getHeaders();
        Iterable $this$filter$iv = requestHeaders.names();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (HttpHeaders.INSTANCE.getUnsafeHeadersList().contains(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List unsafeRequestHeaders = (List) destination$iv$iv;
        if (!unsafeRequestHeaders.isEmpty()) {
            throw new UnsafeHeaderException(unsafeRequestHeaders.toString());
        }
    }
}
