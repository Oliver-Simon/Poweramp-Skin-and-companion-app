package io.ktor.client.plugins.logging;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

/* compiled from: HttpClientCallLogger.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\b\u0000\u0018\u00002\u00020\u001dB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000fJ\u0015\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0011\u0010\rR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0012R\u0018\u0010\u0015\u001a\u00060\u0013j\u0002`\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0018\u0010\u001b\u001a\u00060\u0013j\u0002`\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lio/ktor/client/plugins/logging/HttpClientCallLogger;", "Lio/ktor/client/plugins/logging/Logger;", "logger", "<init>", "(Lio/ktor/client/plugins/logging/Logger;)V", "", "closeRequestLog", "()V", "closeResponseLog", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "message", "logRequest", "(Ljava/lang/String;)V", "logResponseBody", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logResponseException", "logResponseHeader", "Lio/ktor/client/plugins/logging/Logger;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "requestLog", "Ljava/lang/StringBuilder;", "Lkotlinx/coroutines/CompletableJob;", "requestLoggedMonitor", "Lkotlinx/coroutines/CompletableJob;", "responseHeaderMonitor", "responseLog", "ktor-client-logging", ""}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpClientCallLogger {
    private static final /* synthetic */ AtomicIntegerFieldUpdater requestLogged$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClientCallLogger.class, "requestLogged");
    private static final /* synthetic */ AtomicIntegerFieldUpdater responseLogged$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClientCallLogger.class, "responseLogged");
    private final Logger logger;
    private final StringBuilder requestLog;
    private volatile /* synthetic */ int requestLogged;
    private final CompletableJob requestLoggedMonitor;
    private final CompletableJob responseHeaderMonitor;
    private final StringBuilder responseLog;
    private volatile /* synthetic */ int responseLogged;

    public HttpClientCallLogger(Logger logger) {
        CompletableJob Job$default;
        CompletableJob Job$default2;
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
        this.requestLog = new StringBuilder();
        this.responseLog = new StringBuilder();
        Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        this.requestLoggedMonitor = Job$default;
        Job$default2 = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        this.responseHeaderMonitor = Job$default2;
        this.requestLogged = 0;
        this.responseLogged = 0;
    }

    public final void logRequest(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        StringBuilder append = this.requestLog.append(StringsKt.trim((CharSequence) message).toString());
        Intrinsics.checkNotNullExpressionValue(append, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
    }

    public final void logResponseHeader(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        StringBuilder append = this.responseLog.append(StringsKt.trim((CharSequence) message).toString());
        Intrinsics.checkNotNullExpressionValue(append, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
        this.responseHeaderMonitor.complete();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object logResponseException(java.lang.String r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.client.plugins.logging.HttpClientCallLogger$logResponseException$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.client.plugins.logging.HttpClientCallLogger$logResponseException$1 r0 = (io.ktor.client.plugins.logging.HttpClientCallLogger$logResponseException$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.client.plugins.logging.HttpClientCallLogger$logResponseException$1 r0 = new io.ktor.client.plugins.logging.HttpClientCallLogger$logResponseException$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            java.lang.Object r6 = r0.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.client.plugins.logging.HttpClientCallLogger r1 = (io.ktor.client.plugins.logging.HttpClientCallLogger) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4d
        L38:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
            kotlinx.coroutines.CompletableJob r3 = r2.requestLoggedMonitor
            r0.L$0 = r2
            r0.L$1 = r6
            r4 = 1
            r0.label = r4
            java.lang.Object r3 = r3.join(r0)
            if (r3 != r1) goto L4c
            return r1
        L4c:
            r1 = r2
        L4d:
            io.ktor.client.plugins.logging.Logger r2 = r1.logger
            r3 = r6
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.CharSequence r3 = kotlin.text.StringsKt.trim(r3)
            java.lang.String r3 = r3.toString()
            r2.log(r3)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.logging.HttpClientCallLogger.logResponseException(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object logResponseBody(java.lang.String r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.client.plugins.logging.HttpClientCallLogger$logResponseBody$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.client.plugins.logging.HttpClientCallLogger$logResponseBody$1 r0 = (io.ktor.client.plugins.logging.HttpClientCallLogger$logResponseBody$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.client.plugins.logging.HttpClientCallLogger$logResponseBody$1 r0 = new io.ktor.client.plugins.logging.HttpClientCallLogger$logResponseBody$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            java.lang.Object r6 = r0.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.client.plugins.logging.HttpClientCallLogger r1 = (io.ktor.client.plugins.logging.HttpClientCallLogger) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4d
        L38:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
            kotlinx.coroutines.CompletableJob r3 = r2.responseHeaderMonitor
            r0.L$0 = r2
            r0.L$1 = r6
            r4 = 1
            r0.label = r4
            java.lang.Object r3 = r3.join(r0)
            if (r3 != r1) goto L4c
            return r1
        L4c:
            r1 = r2
        L4d:
            java.lang.StringBuilder r2 = r1.responseLog
            r2.append(r6)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.logging.HttpClientCallLogger.logResponseBody(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void closeRequestLog() {
        if (requestLogged$FU.compareAndSet(this, 0, 1)) {
            try {
                String message = StringsKt.trim(this.requestLog).toString();
                if (message.length() > 0) {
                    this.logger.log(message);
                }
            } finally {
                this.requestLoggedMonitor.complete();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object closeResponseLog(kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof io.ktor.client.plugins.logging.HttpClientCallLogger$closeResponseLog$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.client.plugins.logging.HttpClientCallLogger$closeResponseLog$1 r0 = (io.ktor.client.plugins.logging.HttpClientCallLogger$closeResponseLog$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.client.plugins.logging.HttpClientCallLogger$closeResponseLog$1 r0 = new io.ktor.client.plugins.logging.HttpClientCallLogger$closeResponseLog$1
            r0.<init>(r6, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L2e:
            java.lang.Object r1 = r0.L$0
            io.ktor.client.plugins.logging.HttpClientCallLogger r1 = (io.ktor.client.plugins.logging.HttpClientCallLogger) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L53
        L36:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r6
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r5 = io.ktor.client.plugins.logging.HttpClientCallLogger.responseLogged$FU
            boolean r5 = r5.compareAndSet(r2, r3, r4)
            if (r5 != 0) goto L45
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L45:
            kotlinx.coroutines.CompletableJob r5 = r2.requestLoggedMonitor
            r0.L$0 = r2
            r0.label = r4
            java.lang.Object r5 = r5.join(r0)
            if (r5 != r1) goto L52
            return r1
        L52:
            r1 = r2
        L53:
            java.lang.StringBuilder r2 = r1.responseLog
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.CharSequence r2 = kotlin.text.StringsKt.trim(r2)
            java.lang.String r2 = r2.toString()
            r5 = r2
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            int r5 = r5.length()
            if (r5 <= 0) goto L69
            r3 = r4
        L69:
            if (r3 == 0) goto L70
            io.ktor.client.plugins.logging.Logger r3 = r1.logger
            r3.log(r2)
        L70:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.logging.HttpClientCallLogger.closeResponseLog(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
