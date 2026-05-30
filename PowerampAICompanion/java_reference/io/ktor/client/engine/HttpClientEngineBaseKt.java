package io.ktor.client.engine;

import java.io.Closeable;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

/* compiled from: HttpClientEngineBase.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"close", "", "Lkotlinx/coroutines/CoroutineDispatcher;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpClientEngineBaseKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void close(CoroutineDispatcher coroutineDispatcher) {
        try {
            if (coroutineDispatcher instanceof ExecutorCoroutineDispatcher) {
                ((ExecutorCoroutineDispatcher) coroutineDispatcher).close();
            } else if (coroutineDispatcher instanceof Closeable) {
                ((Closeable) coroutineDispatcher).close();
            }
        } catch (Throwable th) {
        }
    }
}
