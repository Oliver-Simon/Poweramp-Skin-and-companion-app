package io.ktor.client.plugins.logging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpClientCallLogger.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.logging.HttpClientCallLogger", f = "HttpClientCallLogger.kt", i = {0, 0}, l = {29}, m = "logResponseException", n = {"this", "message"}, s = {"L$0", "L$1"})
/* loaded from: classes9.dex */
public final class HttpClientCallLogger$logResponseException$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpClientCallLogger this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpClientCallLogger$logResponseException$1(HttpClientCallLogger httpClientCallLogger, Continuation<? super HttpClientCallLogger$logResponseException$1> continuation) {
        super(continuation);
        this.this$0 = httpClientCallLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.logResponseException(null, this);
    }
}
