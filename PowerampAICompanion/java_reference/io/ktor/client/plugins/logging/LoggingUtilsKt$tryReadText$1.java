package io.ktor.client.plugins.logging;

import io.ktor.http.auth.HttpAuthHeader;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoggingUtils.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "io.ktor.client.plugins.logging.LoggingUtilsKt", f = "LoggingUtils.kt", i = {0}, l = {50}, m = "tryReadText", n = {HttpAuthHeader.Parameters.Charset}, s = {"L$0"})
/* loaded from: classes9.dex */
public final class LoggingUtilsKt$tryReadText$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoggingUtilsKt$tryReadText$1(Continuation<? super LoggingUtilsKt$tryReadText$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return LoggingUtilsKt.tryReadText(null, null, this);
    }
}
