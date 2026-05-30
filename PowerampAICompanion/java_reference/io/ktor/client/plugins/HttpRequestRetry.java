package io.ktor.client.plugins;

import androidx.core.app.NotificationCompat;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpRequestRetry;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.events.EventDefinition;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;

/* compiled from: HttpRequestRetry.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 )2\u00020\u0001:\u0006&'()*+B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001eJ\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0012H\u0002JE\u0010\u0013\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2#\u0010\u0013\u001a\u001f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0014¢\u0006\u0002\b\u000e2\u0006\u0010\"\u001a\u00020#H\u0002JM\u0010\u0019\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2#\u0010\u0013\u001a\u001f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00180\u0014¢\u0006\u0002\b\u000e2\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u001aH\u0002R/\u0010\u0005\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\nR%\u0010\u000b\u001a\u0019\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\u0010\u001a\u0019\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0013\u001a\u001f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0014¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0019\u001a\u001f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00180\u0014¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry;", "", "configuration", "Lio/ktor/client/plugins/HttpRequestRetry$Configuration;", "(Lio/ktor/client/plugins/HttpRequestRetry$Configuration;)V", "delay", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function2;", "delayMillis", "Lio/ktor/client/plugins/HttpRequestRetry$DelayContext;", "", "Lkotlin/ExtensionFunctionType;", "maxRetries", "modifyRequest", "Lio/ktor/client/plugins/HttpRequestRetry$ModifyRequestContext;", "Lio/ktor/client/request/HttpRequestBuilder;", "shouldRetry", "Lkotlin/Function3;", "Lio/ktor/client/plugins/HttpRequestRetry$ShouldRetryContext;", "Lio/ktor/client/request/HttpRequest;", "Lio/ktor/client/statement/HttpResponse;", "", "shouldRetryOnException", "", "intercept", "client", "Lio/ktor/client/HttpClient;", "intercept$ktor_client_core", "prepareRequest", "request", "retryCount", NotificationCompat.CATEGORY_CALL, "Lio/ktor/client/call/HttpClientCall;", "subRequest", "cause", "Configuration", "DelayContext", "ModifyRequestContext", "Plugin", "RetryEventData", "ShouldRetryContext", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpRequestRetry {
    private final Function2<Long, Continuation<? super Unit>, Object> delay;
    private final Function2<DelayContext, Integer, Long> delayMillis;
    private final int maxRetries;
    private final Function2<ModifyRequestContext, HttpRequestBuilder, Unit> modifyRequest;
    private final Function3<ShouldRetryContext, HttpRequest, HttpResponse, Boolean> shouldRetry;
    private final Function3<ShouldRetryContext, HttpRequestBuilder, Throwable, Boolean> shouldRetryOnException;

    /* renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<HttpRequestRetry> key = new AttributeKey<>("RetryFeature");
    private static final EventDefinition<RetryEventData> HttpRequestRetryEvent = new EventDefinition<>();

    public HttpRequestRetry(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.shouldRetry = configuration.getShouldRetry$ktor_client_core();
        this.shouldRetryOnException = configuration.getShouldRetryOnException$ktor_client_core();
        this.delayMillis = configuration.getDelayMillis$ktor_client_core();
        this.delay = configuration.getDelay$ktor_client_core();
        this.maxRetries = configuration.getMaxRetries();
        this.modifyRequest = configuration.getModifyRequest$ktor_client_core();
    }

    /* compiled from: HttpRequestRetry.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$ShouldRetryContext;", "", "retryCount", "", "(I)V", "getRetryCount", "()I", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class ShouldRetryContext {
        private final int retryCount;

        public ShouldRetryContext(int retryCount) {
            this.retryCount = retryCount;
        }

        public final int getRetryCount() {
            return this.retryCount;
        }
    }

    /* compiled from: HttpRequestRetry.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\b\u0018\u00002\u00020\u0001B#\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$DelayContext;", "", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "response", "Lio/ktor/client/statement/HttpResponse;", "cause", "", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/client/statement/HttpResponse;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getRequest", "()Lio/ktor/client/request/HttpRequestBuilder;", "getResponse", "()Lio/ktor/client/statement/HttpResponse;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class DelayContext {
        private final Throwable cause;
        private final HttpRequestBuilder request;
        private final HttpResponse response;

        public DelayContext(HttpRequestBuilder request, HttpResponse response, Throwable cause) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.request = request;
            this.response = response;
            this.cause = cause;
        }

        public final HttpRequestBuilder getRequest() {
            return this.request;
        }

        public final HttpResponse getResponse() {
            return this.response;
        }

        public final Throwable getCause() {
            return this.cause;
        }
    }

    /* compiled from: HttpRequestRetry.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B+\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$ModifyRequestContext;", "", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "response", "Lio/ktor/client/statement/HttpResponse;", "cause", "", "retryCount", "", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/client/statement/HttpResponse;Ljava/lang/Throwable;I)V", "getCause", "()Ljava/lang/Throwable;", "getRequest", "()Lio/ktor/client/request/HttpRequestBuilder;", "getResponse", "()Lio/ktor/client/statement/HttpResponse;", "getRetryCount", "()I", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class ModifyRequestContext {
        private final Throwable cause;
        private final HttpRequestBuilder request;
        private final HttpResponse response;
        private final int retryCount;

        public ModifyRequestContext(HttpRequestBuilder request, HttpResponse response, Throwable cause, int retryCount) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.request = request;
            this.response = response;
            this.cause = cause;
            this.retryCount = retryCount;
        }

        public final HttpRequestBuilder getRequest() {
            return this.request;
        }

        public final HttpResponse getResponse() {
            return this.response;
        }

        public final Throwable getCause() {
            return this.cause;
        }

        public final int getRetryCount() {
            return this.retryCount;
        }
    }

    /* compiled from: HttpRequestRetry.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\n\u0018\u00002\u00020\u0001B+\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$RetryEventData;", "", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "retryCount", "", "response", "Lio/ktor/client/statement/HttpResponse;", "cause", "", "(Lio/ktor/client/request/HttpRequestBuilder;ILio/ktor/client/statement/HttpResponse;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getRequest", "()Lio/ktor/client/request/HttpRequestBuilder;", "getResponse", "()Lio/ktor/client/statement/HttpResponse;", "getRetryCount", "()I", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class RetryEventData {
        private final Throwable cause;
        private final HttpRequestBuilder request;
        private final HttpResponse response;
        private final int retryCount;

        public RetryEventData(HttpRequestBuilder request, int retryCount, HttpResponse response, Throwable cause) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.request = request;
            this.retryCount = retryCount;
            this.response = response;
            this.cause = cause;
        }

        public final HttpRequestBuilder getRequest() {
            return this.request;
        }

        public final int getRetryCount() {
            return this.retryCount;
        }

        public final HttpResponse getResponse() {
            return this.response;
        }

        public final Throwable getCause() {
            return this.cause;
        }
    }

    /* compiled from: HttpRequestRetry.kt */
    @KtorDsl
    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010+\u001a\u00020\u00072\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020\u00052\b\b\u0002\u0010.\u001a\u00020\"J2\u0010\u0003\u001a\u00020\u00072\"\u0010/\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ>\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010.\u001a\u00020\"2,\u0010/\u001a(\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0010J.\u00103\u001a\u00020\u00072\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020\u00052\b\b\u0002\u0010.\u001a\u00020\"J%\u0010\u0018\u001a\u00020\u00072\u001d\u0010/\u001a\u0019\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0002\b\u0010J\u0006\u00107\u001a\u00020\u0007J\u0010\u00108\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u0005H\u0002J5\u00109\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u000f2#\u0010/\u001a\u001f\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0\u001e¢\u0006\u0002\b\u0010J\u0012\u0010:\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u000fH\u0007J\u001a\u0010:\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010;\u001a\u00020\"J5\u0010<\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u000f2#\u0010/\u001a\u001f\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0\u001e¢\u0006\u0002\b\u0010J\u0010\u0010=\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u000fJ\u0010\u0010>\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u000fR;\u0010\u0003\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004X\u0080\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR1\u0010\r\u001a\u0019\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0010X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\t\"\u0004\b\u0012\u0010\u000bR\u001a\u0010\u0013\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R1\u0010\u0018\u001a\u0019\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0002\b\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\t\"\u0004\b\u001c\u0010\u000bR7\u0010\u001d\u001a\u001f\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0\u001e¢\u0006\u0002\b\u0010X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R7\u0010'\u001a\u001f\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\"0\u001e¢\u0006\u0002\b\u0010X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010$\"\u0004\b*\u0010&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006?"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$Configuration;", "", "()V", "delay", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "", "getDelay$ktor_client_core", "()Lkotlin/jvm/functions/Function2;", "setDelay$ktor_client_core", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "delayMillis", "Lio/ktor/client/plugins/HttpRequestRetry$DelayContext;", "", "Lkotlin/ExtensionFunctionType;", "getDelayMillis$ktor_client_core", "setDelayMillis$ktor_client_core", "maxRetries", "getMaxRetries", "()I", "setMaxRetries", "(I)V", "modifyRequest", "Lio/ktor/client/plugins/HttpRequestRetry$ModifyRequestContext;", "Lio/ktor/client/request/HttpRequestBuilder;", "getModifyRequest$ktor_client_core", "setModifyRequest$ktor_client_core", "shouldRetry", "Lkotlin/Function3;", "Lio/ktor/client/plugins/HttpRequestRetry$ShouldRetryContext;", "Lio/ktor/client/request/HttpRequest;", "Lio/ktor/client/statement/HttpResponse;", "", "getShouldRetry$ktor_client_core", "()Lkotlin/jvm/functions/Function3;", "setShouldRetry$ktor_client_core", "(Lkotlin/jvm/functions/Function3;)V", "shouldRetryOnException", "", "getShouldRetryOnException$ktor_client_core", "setShouldRetryOnException$ktor_client_core", "constantDelay", "millis", "randomizationMs", "respectRetryAfterHeader", "block", "Lkotlin/ParameterName;", "name", "retry", "exponentialDelay", "base", "", "maxDelayMs", "noRetry", "randomMs", "retryIf", "retryOnException", "retryOnTimeout", "retryOnExceptionIf", "retryOnExceptionOrServerErrors", "retryOnServerErrors", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Configuration {
        public Function2<? super DelayContext, ? super Integer, Long> delayMillis;
        private int maxRetries;
        public Function3<? super ShouldRetryContext, ? super HttpRequest, ? super HttpResponse, Boolean> shouldRetry;
        public Function3<? super ShouldRetryContext, ? super HttpRequestBuilder, ? super Throwable, Boolean> shouldRetryOnException;
        private Function2<? super ModifyRequestContext, ? super HttpRequestBuilder, Unit> modifyRequest = new Function2<ModifyRequestContext, HttpRequestBuilder, Unit>() { // from class: io.ktor.client.plugins.HttpRequestRetry$Configuration$modifyRequest$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(HttpRequestRetry.ModifyRequestContext modifyRequestContext, HttpRequestBuilder httpRequestBuilder) {
                invoke2(modifyRequestContext, httpRequestBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HttpRequestRetry.ModifyRequestContext $this$null, HttpRequestBuilder it) {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
                Intrinsics.checkNotNullParameter(it, "it");
            }
        };
        private Function2<? super Long, ? super Continuation<? super Unit>, ? extends Object> delay = new HttpRequestRetry$Configuration$delay$1(null);

        public Configuration() {
            retryOnExceptionOrServerErrors(3);
            exponentialDelay$default(this, 0.0d, 0L, 0L, false, 15, null);
        }

        public final Function3<ShouldRetryContext, HttpRequest, HttpResponse, Boolean> getShouldRetry$ktor_client_core() {
            Function3 function3 = this.shouldRetry;
            if (function3 != null) {
                return function3;
            }
            Intrinsics.throwUninitializedPropertyAccessException("shouldRetry");
            return null;
        }

        public final void setShouldRetry$ktor_client_core(Function3<? super ShouldRetryContext, ? super HttpRequest, ? super HttpResponse, Boolean> function3) {
            Intrinsics.checkNotNullParameter(function3, "<set-?>");
            this.shouldRetry = function3;
        }

        public final Function3<ShouldRetryContext, HttpRequestBuilder, Throwable, Boolean> getShouldRetryOnException$ktor_client_core() {
            Function3 function3 = this.shouldRetryOnException;
            if (function3 != null) {
                return function3;
            }
            Intrinsics.throwUninitializedPropertyAccessException("shouldRetryOnException");
            return null;
        }

        public final void setShouldRetryOnException$ktor_client_core(Function3<? super ShouldRetryContext, ? super HttpRequestBuilder, ? super Throwable, Boolean> function3) {
            Intrinsics.checkNotNullParameter(function3, "<set-?>");
            this.shouldRetryOnException = function3;
        }

        public final Function2<DelayContext, Integer, Long> getDelayMillis$ktor_client_core() {
            Function2 function2 = this.delayMillis;
            if (function2 != null) {
                return function2;
            }
            Intrinsics.throwUninitializedPropertyAccessException("delayMillis");
            return null;
        }

        public final void setDelayMillis$ktor_client_core(Function2<? super DelayContext, ? super Integer, Long> function2) {
            Intrinsics.checkNotNullParameter(function2, "<set-?>");
            this.delayMillis = function2;
        }

        public final Function2<ModifyRequestContext, HttpRequestBuilder, Unit> getModifyRequest$ktor_client_core() {
            return this.modifyRequest;
        }

        public final void setModifyRequest$ktor_client_core(Function2<? super ModifyRequestContext, ? super HttpRequestBuilder, Unit> function2) {
            Intrinsics.checkNotNullParameter(function2, "<set-?>");
            this.modifyRequest = function2;
        }

        public final Function2<Long, Continuation<? super Unit>, Object> getDelay$ktor_client_core() {
            return this.delay;
        }

        public final void setDelay$ktor_client_core(Function2<? super Long, ? super Continuation<? super Unit>, ? extends Object> function2) {
            Intrinsics.checkNotNullParameter(function2, "<set-?>");
            this.delay = function2;
        }

        public final int getMaxRetries() {
            return this.maxRetries;
        }

        public final void setMaxRetries(int i) {
            this.maxRetries = i;
        }

        public final void noRetry() {
            this.maxRetries = 0;
            setShouldRetry$ktor_client_core(new Function3<ShouldRetryContext, HttpRequest, HttpResponse, Boolean>() { // from class: io.ktor.client.plugins.HttpRequestRetry$Configuration$noRetry$1
                @Override // kotlin.jvm.functions.Function3
                public final Boolean invoke(HttpRequestRetry.ShouldRetryContext $this$null, HttpRequest httpRequest, HttpResponse httpResponse) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                    Intrinsics.checkNotNullParameter(httpRequest, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(httpResponse, "<anonymous parameter 1>");
                    return false;
                }
            });
            setShouldRetryOnException$ktor_client_core(new Function3<ShouldRetryContext, HttpRequestBuilder, Throwable, Boolean>() { // from class: io.ktor.client.plugins.HttpRequestRetry$Configuration$noRetry$2
                @Override // kotlin.jvm.functions.Function3
                public final Boolean invoke(HttpRequestRetry.ShouldRetryContext $this$null, HttpRequestBuilder httpRequestBuilder, Throwable th) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                    Intrinsics.checkNotNullParameter(httpRequestBuilder, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(th, "<anonymous parameter 1>");
                    return false;
                }
            });
        }

        public final void modifyRequest(Function2<? super ModifyRequestContext, ? super HttpRequestBuilder, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            this.modifyRequest = block;
        }

        public static /* synthetic */ void retryIf$default(Configuration configuration, int i, Function3 function3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = -1;
            }
            configuration.retryIf(i, function3);
        }

        public final void retryIf(int maxRetries, Function3<? super ShouldRetryContext, ? super HttpRequest, ? super HttpResponse, Boolean> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            if (maxRetries != -1) {
                this.maxRetries = maxRetries;
            }
            setShouldRetry$ktor_client_core(block);
        }

        public static /* synthetic */ void retryOnExceptionIf$default(Configuration configuration, int i, Function3 function3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = -1;
            }
            configuration.retryOnExceptionIf(i, function3);
        }

        public final void retryOnExceptionIf(int maxRetries, Function3<? super ShouldRetryContext, ? super HttpRequestBuilder, ? super Throwable, Boolean> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            if (maxRetries != -1) {
                this.maxRetries = maxRetries;
            }
            setShouldRetryOnException$ktor_client_core(block);
        }

        public static /* synthetic */ void retryOnException$default(Configuration configuration, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = -1;
            }
            configuration.retryOnException(i);
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This will be removed")
        public final /* synthetic */ void retryOnException(int maxRetries) {
            retryOnException(maxRetries, false);
        }

        public static /* synthetic */ void retryOnException$default(Configuration configuration, int i, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = -1;
            }
            if ((i2 & 2) != 0) {
                z = false;
            }
            configuration.retryOnException(i, z);
        }

        public final void retryOnException(int maxRetries, final boolean retryOnTimeout) {
            retryOnExceptionIf(maxRetries, new Function3<ShouldRetryContext, HttpRequestBuilder, Throwable, Boolean>() { // from class: io.ktor.client.plugins.HttpRequestRetry$Configuration$retryOnException$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Boolean invoke(HttpRequestRetry.ShouldRetryContext retryOnExceptionIf, HttpRequestBuilder httpRequestBuilder, Throwable cause) {
                    boolean z;
                    Intrinsics.checkNotNullParameter(retryOnExceptionIf, "$this$retryOnExceptionIf");
                    Intrinsics.checkNotNullParameter(httpRequestBuilder, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(cause, "cause");
                    if (HttpRequestRetryKt.access$isTimeoutException(cause)) {
                        z = retryOnTimeout;
                    } else {
                        z = !(cause instanceof CancellationException);
                    }
                    return Boolean.valueOf(z);
                }
            });
        }

        public static /* synthetic */ void retryOnServerErrors$default(Configuration configuration, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = -1;
            }
            configuration.retryOnServerErrors(i);
        }

        public final void retryOnServerErrors(int maxRetries) {
            retryIf(maxRetries, new Function3<ShouldRetryContext, HttpRequest, HttpResponse, Boolean>() { // from class: io.ktor.client.plugins.HttpRequestRetry$Configuration$retryOnServerErrors$1
                @Override // kotlin.jvm.functions.Function3
                public final Boolean invoke(HttpRequestRetry.ShouldRetryContext retryIf, HttpRequest httpRequest, HttpResponse response) {
                    Intrinsics.checkNotNullParameter(retryIf, "$this$retryIf");
                    Intrinsics.checkNotNullParameter(httpRequest, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(response, "response");
                    int it = response.getStatus().getValue();
                    boolean z = false;
                    if (500 <= it && it < 600) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            });
        }

        public static /* synthetic */ void retryOnExceptionOrServerErrors$default(Configuration configuration, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = -1;
            }
            configuration.retryOnExceptionOrServerErrors(i);
        }

        public final void retryOnExceptionOrServerErrors(int maxRetries) {
            retryOnServerErrors(maxRetries);
            retryOnException$default(this, maxRetries, false, 2, null);
        }

        public static /* synthetic */ void delayMillis$default(Configuration configuration, boolean z, Function2 function2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = true;
            }
            configuration.delayMillis(z, function2);
        }

        public final void delayMillis(final boolean respectRetryAfterHeader, final Function2<? super DelayContext, ? super Integer, Long> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            setDelayMillis$ktor_client_core(new Function2<DelayContext, Integer, Long>() { // from class: io.ktor.client.plugins.HttpRequestRetry$Configuration$delayMillis$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Long invoke(HttpRequestRetry.DelayContext delayContext, Integer num) {
                    return invoke(delayContext, num.intValue());
                }

                public final Long invoke(HttpRequestRetry.DelayContext $this$null, int it) {
                    long longValue;
                    Headers headers;
                    String str;
                    Long longOrNull;
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                    if (respectRetryAfterHeader) {
                        HttpResponse response = $this$null.getResponse();
                        Long retryAfter = (response == null || (headers = response.getHeaders()) == null || (str = headers.get(HttpHeaders.INSTANCE.getRetryAfter())) == null || (longOrNull = StringsKt.toLongOrNull(str)) == null) ? null : Long.valueOf(longOrNull.longValue() * 1000);
                        longValue = Math.max(block.invoke($this$null, Integer.valueOf(it)).longValue(), retryAfter != null ? retryAfter.longValue() : 0L);
                    } else {
                        longValue = block.invoke($this$null, Integer.valueOf(it)).longValue();
                    }
                    return Long.valueOf(longValue);
                }
            });
        }

        public static /* synthetic */ void constantDelay$default(Configuration configuration, long j, long j2, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                j = 1000;
            }
            if ((i & 2) != 0) {
                j2 = 1000;
            }
            if ((i & 4) != 0) {
                z = true;
            }
            configuration.constantDelay(j, j2, z);
        }

        public final void constantDelay(final long millis, final long randomizationMs, boolean respectRetryAfterHeader) {
            if (!(millis > 0)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (!(randomizationMs >= 0)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            delayMillis(respectRetryAfterHeader, new Function2<DelayContext, Integer, Long>() { // from class: io.ktor.client.plugins.HttpRequestRetry$Configuration$constantDelay$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Long invoke(HttpRequestRetry.DelayContext delayContext, Integer num) {
                    return invoke(delayContext, num.intValue());
                }

                public final Long invoke(HttpRequestRetry.DelayContext delayMillis, int it) {
                    long randomMs;
                    Intrinsics.checkNotNullParameter(delayMillis, "$this$delayMillis");
                    long j = millis;
                    randomMs = this.randomMs(randomizationMs);
                    return Long.valueOf(j + randomMs);
                }
            });
        }

        public static /* synthetic */ void exponentialDelay$default(Configuration configuration, double d, long j, long j2, boolean z, int i, Object obj) {
            configuration.exponentialDelay((i & 1) != 0 ? 2.0d : d, (i & 2) != 0 ? 60000L : j, (i & 4) != 0 ? 1000L : j2, (i & 8) != 0 ? true : z);
        }

        public final void exponentialDelay(final double base, final long maxDelayMs, final long randomizationMs, boolean respectRetryAfterHeader) {
            if (!(base > 0.0d)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (!(maxDelayMs > 0)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (!(randomizationMs >= 0)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            delayMillis(respectRetryAfterHeader, new Function2<DelayContext, Integer, Long>() { // from class: io.ktor.client.plugins.HttpRequestRetry$Configuration$exponentialDelay$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Long invoke(HttpRequestRetry.DelayContext delayContext, Integer num) {
                    return invoke(delayContext, num.intValue());
                }

                public final Long invoke(HttpRequestRetry.DelayContext delayMillis, int retry) {
                    long randomMs;
                    Intrinsics.checkNotNullParameter(delayMillis, "$this$delayMillis");
                    long delay = Math.min(((long) Math.pow(base, retry)) * 1000, maxDelayMs);
                    randomMs = this.randomMs(randomizationMs);
                    return Long.valueOf(randomMs + delay);
                }
            });
        }

        public final void delay(Function2<? super Long, ? super Continuation<? super Unit>, ? extends Object> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            this.delay = block;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long randomMs(long randomizationMs) {
            if (randomizationMs == 0) {
                return 0L;
            }
            return Random.INSTANCE.nextLong(randomizationMs);
        }
    }

    public final void intercept$ktor_client_core(HttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        ((HttpSend) HttpClientPluginKt.plugin(client, HttpSend.INSTANCE)).intercept(new HttpRequestRetry$intercept$1(this, client, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldRetry(int retryCount, int maxRetries, Function3<? super ShouldRetryContext, ? super HttpRequest, ? super HttpResponse, Boolean> shouldRetry, HttpClientCall call) {
        return retryCount < maxRetries && shouldRetry.invoke(new ShouldRetryContext(retryCount + 1), call.getRequest(), call.getResponse()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldRetryOnException(int retryCount, int maxRetries, Function3<? super ShouldRetryContext, ? super HttpRequestBuilder, ? super Throwable, Boolean> shouldRetry, HttpRequestBuilder subRequest, Throwable cause) {
        return retryCount < maxRetries && shouldRetry.invoke(new ShouldRetryContext(retryCount + 1), subRequest, cause).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HttpRequestBuilder prepareRequest(HttpRequestBuilder request) {
        final HttpRequestBuilder subRequest = new HttpRequestBuilder().takeFrom(request);
        request.getExecutionContext().invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: io.ktor.client.plugins.HttpRequestRetry$prepareRequest$1
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
                Job executionContext = HttpRequestBuilder.this.getExecutionContext();
                Intrinsics.checkNotNull(executionContext, "null cannot be cast to non-null type kotlinx.coroutines.CompletableJob");
                CompletableJob subRequestJob = (CompletableJob) executionContext;
                if (cause == null) {
                    subRequestJob.complete();
                } else {
                    subRequestJob.completeExceptionally(cause);
                }
            }
        });
        return subRequest;
    }

    /* compiled from: HttpRequestRetry.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J!\u0010\u0013\u001a\u00020\u00032\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\u0015¢\u0006\u0002\b\u0016H\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lio/ktor/client/plugins/HttpRequestRetry$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/HttpRequestRetry$Configuration;", "Lio/ktor/client/plugins/HttpRequestRetry;", "()V", "HttpRequestRetryEvent", "Lio/ktor/events/EventDefinition;", "Lio/ktor/client/plugins/HttpRequestRetry$RetryEventData;", "getHttpRequestRetryEvent", "()Lio/ktor/events/EventDefinition;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "plugin", "scope", "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: io.ktor.client.plugins.HttpRequestRetry$Plugin, reason: from kotlin metadata */
    /* loaded from: classes9.dex */
    public static final class Companion implements HttpClientPlugin<Configuration, HttpRequestRetry> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<HttpRequestRetry> getKey() {
            return HttpRequestRetry.key;
        }

        public final EventDefinition<RetryEventData> getHttpRequestRetryEvent() {
            return HttpRequestRetry.HttpRequestRetryEvent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpRequestRetry prepare(Function1<? super Configuration, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Configuration configuration = new Configuration();
            block.invoke(configuration);
            return new HttpRequestRetry(configuration);
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpRequestRetry plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            plugin.intercept$ktor_client_core(scope);
        }
    }
}
