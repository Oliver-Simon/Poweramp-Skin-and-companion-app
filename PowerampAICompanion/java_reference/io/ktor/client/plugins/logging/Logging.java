package io.ktor.client.plugins.logging;

import io.ktor.client.HttpClient;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.plugins.observer.ResponseObserver;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpSendPipeline;
import io.ktor.client.statement.HttpReceivePipeline;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.KtorDsl;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteChannelKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: Logging.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 /2\u00020\u0001:\u0002/0BA\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\u0002\u0010\rJ\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ!\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u001eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020$H\u0002J$\u0010%\u001a\u00020!2\n\u0010&\u001a\u00060'j\u0002`(2\u0006\u0010\u001a\u001a\u00020)2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020,H\u0002J\u0010\u0010-\u001a\u00020!2\u0006\u0010+\u001a\u00020,H\u0002J\u0010\u0010.\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\tH\u0002R,\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00061"}, d2 = {"Lio/ktor/client/plugins/logging/Logging;", "", "logger", "Lio/ktor/client/plugins/logging/Logger;", "level", "Lio/ktor/client/plugins/logging/LogLevel;", "filters", "", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "", "sanitizedHeaders", "Lio/ktor/client/plugins/logging/SanitizedHeader;", "(Lio/ktor/client/plugins/logging/Logger;Lio/ktor/client/plugins/logging/LogLevel;Ljava/util/List;Ljava/util/List;)V", "getFilters", "()Ljava/util/List;", "setFilters", "(Ljava/util/List;)V", "getLevel", "()Lio/ktor/client/plugins/logging/LogLevel;", "setLevel", "(Lio/ktor/client/plugins/logging/LogLevel;)V", "getLogger", "()Lio/ktor/client/plugins/logging/Logger;", "logRequest", "Lio/ktor/http/content/OutgoingContent;", "request", "(Lio/ktor/client/request/HttpRequestBuilder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logRequestBody", "content", "Lio/ktor/client/plugins/logging/HttpClientCallLogger;", "(Lio/ktor/http/content/OutgoingContent;Lio/ktor/client/plugins/logging/HttpClientCallLogger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logRequestException", "", "context", "cause", "", "logResponseException", "log", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "Lio/ktor/client/request/HttpRequest;", "setupRequestLogging", "client", "Lio/ktor/client/HttpClient;", "setupResponseLogging", "shouldBeLogged", "Companion", "Config", "ktor-client-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class Logging {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<Logging> key = new AttributeKey<>("ClientLogging");
    private List<? extends Function1<? super HttpRequestBuilder, Boolean>> filters;
    private LogLevel level;
    private final Logger logger;
    private final List<SanitizedHeader> sanitizedHeaders;

    public /* synthetic */ Logging(Logger logger, LogLevel logLevel, List list, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(logger, logLevel, list, list2);
    }

    private Logging(Logger logger, LogLevel level, List<? extends Function1<? super HttpRequestBuilder, Boolean>> list, List<SanitizedHeader> list2) {
        this.logger = logger;
        this.level = level;
        this.filters = list;
        this.sanitizedHeaders = list2;
    }

    /* synthetic */ Logging(Logger logger, LogLevel logLevel, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(logger, logLevel, (i & 4) != 0 ? CollectionsKt.emptyList() : list, list2);
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final LogLevel getLevel() {
        return this.level;
    }

    public final void setLevel(LogLevel logLevel) {
        Intrinsics.checkNotNullParameter(logLevel, "<set-?>");
        this.level = logLevel;
    }

    public final List<Function1<HttpRequestBuilder, Boolean>> getFilters() {
        return this.filters;
    }

    public final void setFilters(List<? extends Function1<? super HttpRequestBuilder, Boolean>> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.filters = list;
    }

    /* compiled from: Logging.kt */
    @KtorDsl
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u001d\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007J$\u0010 \u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020\"2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\t0\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000b¨\u0006#"}, d2 = {"Lio/ktor/client/plugins/logging/Logging$Config;", "", "()V", "_logger", "Lio/ktor/client/plugins/logging/Logger;", "filters", "", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "", "getFilters$ktor_client_logging", "()Ljava/util/List;", "setFilters$ktor_client_logging", "(Ljava/util/List;)V", "level", "Lio/ktor/client/plugins/logging/LogLevel;", "getLevel", "()Lio/ktor/client/plugins/logging/LogLevel;", "setLevel", "(Lio/ktor/client/plugins/logging/LogLevel;)V", "value", "logger", "getLogger", "()Lio/ktor/client/plugins/logging/Logger;", "setLogger", "(Lio/ktor/client/plugins/logging/Logger;)V", "sanitizedHeaders", "Lio/ktor/client/plugins/logging/SanitizedHeader;", "getSanitizedHeaders$ktor_client_logging", "filter", "", "predicate", "sanitizeHeader", "placeholder", "", "ktor-client-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Config {
        private Logger _logger;
        private List<Function1<HttpRequestBuilder, Boolean>> filters = new ArrayList();
        private final List<SanitizedHeader> sanitizedHeaders = new ArrayList();
        private LogLevel level = LogLevel.HEADERS;

        public final List<Function1<HttpRequestBuilder, Boolean>> getFilters$ktor_client_logging() {
            return this.filters;
        }

        public final void setFilters$ktor_client_logging(List<Function1<HttpRequestBuilder, Boolean>> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.filters = list;
        }

        public final List<SanitizedHeader> getSanitizedHeaders$ktor_client_logging() {
            return this.sanitizedHeaders;
        }

        public final Logger getLogger() {
            Logger logger = this._logger;
            return logger == null ? LoggerJvmKt.getDEFAULT(Logger.INSTANCE) : logger;
        }

        public final void setLogger(Logger value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this._logger = value;
        }

        public final LogLevel getLevel() {
            return this.level;
        }

        public final void setLevel(LogLevel logLevel) {
            Intrinsics.checkNotNullParameter(logLevel, "<set-?>");
            this.level = logLevel;
        }

        public final void filter(Function1<? super HttpRequestBuilder, Boolean> predicate) {
            Intrinsics.checkNotNullParameter(predicate, "predicate");
            this.filters.add(predicate);
        }

        public static /* synthetic */ void sanitizeHeader$default(Config config, String str, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "***";
            }
            config.sanitizeHeader(str, function1);
        }

        public final void sanitizeHeader(String placeholder, Function1<? super String, Boolean> predicate) {
            Intrinsics.checkNotNullParameter(placeholder, "placeholder");
            Intrinsics.checkNotNullParameter(predicate, "predicate");
            this.sanitizedHeaders.add(new SanitizedHeader(placeholder, predicate));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupRequestLogging(HttpClient client) {
        client.getSendPipeline().intercept(HttpSendPipeline.INSTANCE.getMonitoring(), new Logging$setupRequestLogging$1(this, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object logRequest(HttpRequestBuilder request, Continuation<? super OutgoingContent> continuation) {
        AttributeKey attributeKey;
        Object element$iv;
        Object element$iv2;
        Object body = request.getBody();
        Intrinsics.checkNotNull(body, "null cannot be cast to non-null type io.ktor.http.content.OutgoingContent");
        OutgoingContent content = (OutgoingContent) body;
        HttpClientCallLogger logger = new HttpClientCallLogger(this.logger);
        Attributes attributes = request.getAttributes();
        attributeKey = LoggingKt.ClientCallLogger;
        attributes.put(attributeKey, logger);
        StringBuilder $this$logRequest_u24lambda_u244 = new StringBuilder();
        if (this.level.getInfo()) {
            StringBuilder append = $this$logRequest_u24lambda_u244.append("REQUEST: " + URLUtilsKt.Url(request.getUrl()));
            Intrinsics.checkNotNullExpressionValue(append, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
            StringBuilder append2 = $this$logRequest_u24lambda_u244.append("METHOD: " + request.getMethod());
            Intrinsics.checkNotNullExpressionValue(append2, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append2.append('\n'), "append('\\n')");
        }
        if (this.level.getHeaders()) {
            StringBuilder append3 = $this$logRequest_u24lambda_u244.append("COMMON HEADERS");
            Intrinsics.checkNotNullExpressionValue(append3, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append3.append('\n'), "append('\\n')");
            LoggingUtilsKt.logHeaders($this$logRequest_u24lambda_u244, request.getHeaders().entries(), this.sanitizedHeaders);
            StringBuilder append4 = $this$logRequest_u24lambda_u244.append("CONTENT HEADERS");
            Intrinsics.checkNotNullExpressionValue(append4, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append4.append('\n'), "append('\\n')");
            Iterable $this$firstOrNull$iv = this.sanitizedHeaders;
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    element$iv = it.next();
                    if (((SanitizedHeader) element$iv).getPredicate().invoke(HttpHeaders.INSTANCE.getContentLength()).booleanValue()) {
                        break;
                    }
                } else {
                    element$iv = null;
                    break;
                }
            }
            SanitizedHeader sanitizedHeader = (SanitizedHeader) element$iv;
            String contentLengthPlaceholder = sanitizedHeader != null ? sanitizedHeader.getPlaceholder() : null;
            Iterable $this$firstOrNull$iv2 = this.sanitizedHeaders;
            Iterator it2 = $this$firstOrNull$iv2.iterator();
            while (true) {
                if (it2.hasNext()) {
                    element$iv2 = it2.next();
                    if (((SanitizedHeader) element$iv2).getPredicate().invoke(HttpHeaders.INSTANCE.getContentType()).booleanValue()) {
                        break;
                    }
                } else {
                    element$iv2 = null;
                    break;
                }
            }
            SanitizedHeader sanitizedHeader2 = (SanitizedHeader) element$iv2;
            String contentTypePlaceholder = sanitizedHeader2 != null ? sanitizedHeader2.getPlaceholder() : null;
            Long contentLength = content.getContentLength();
            if (contentLength != null) {
                LoggingUtilsKt.logHeader($this$logRequest_u24lambda_u244, HttpHeaders.INSTANCE.getContentLength(), contentLengthPlaceholder == null ? String.valueOf(contentLength.longValue()) : contentLengthPlaceholder);
            }
            ContentType it3 = content.getContentType();
            if (it3 != null) {
                LoggingUtilsKt.logHeader($this$logRequest_u24lambda_u244, HttpHeaders.INSTANCE.getContentType(), contentTypePlaceholder == null ? it3.toString() : contentTypePlaceholder);
            }
            LoggingUtilsKt.logHeaders($this$logRequest_u24lambda_u244, content.getHeaders().entries(), this.sanitizedHeaders);
        }
        String message = $this$logRequest_u24lambda_u244.toString();
        Intrinsics.checkNotNullExpressionValue(message, "StringBuilder().apply(builderAction).toString()");
        if (message.length() > 0) {
            logger.logRequest(message);
        }
        if (!(message.length() == 0) && this.level.getBody()) {
            return logRequestBody(content, logger, continuation);
        }
        logger.closeRequestLog();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object logRequestBody(OutgoingContent content, final HttpClientCallLogger logger, Continuation<? super OutgoingContent> continuation) {
        Charset charset;
        Job launch$default;
        final StringBuilder requestLog = new StringBuilder();
        StringBuilder append = requestLog.append("BODY Content-Type: " + content.getContentType());
        Intrinsics.checkNotNullExpressionValue(append, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
        ContentType contentType = content.getContentType();
        if (contentType == null || (charset = ContentTypesKt.charset(contentType)) == null) {
            charset = Charsets.UTF_8;
        }
        ByteChannel channel = ByteChannelKt.ByteChannel$default(false, 1, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getUnconfined(), null, new Logging$logRequestBody$2(channel, charset, requestLog, null), 2, null);
        launch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: io.ktor.client.plugins.logging.Logging$logRequestBody$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                HttpClientCallLogger httpClientCallLogger = HttpClientCallLogger.this;
                String sb = requestLog.toString();
                Intrinsics.checkNotNullExpressionValue(sb, "requestLog.toString()");
                httpClientCallLogger.logRequest(sb);
                HttpClientCallLogger.this.closeRequestLog();
            }
        });
        return ObservingUtilsKt.observe(content, channel, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logRequestException(HttpRequestBuilder context, Throwable cause) {
        if (this.level.getInfo()) {
            this.logger.log("REQUEST " + URLUtilsKt.Url(context.getUrl()) + " failed with exception: " + cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupResponseLogging(HttpClient client) {
        client.getReceivePipeline().intercept(HttpReceivePipeline.INSTANCE.getState(), new Logging$setupResponseLogging$1(this, null));
        client.getResponsePipeline().intercept(HttpResponsePipeline.INSTANCE.getReceive(), new Logging$setupResponseLogging$2(this, null));
        if (this.level.getBody()) {
            Function2 observer = new Logging$setupResponseLogging$observer$1(this, null);
            ResponseObserver.INSTANCE.install(new ResponseObserver(observer, null, 2, null), client);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logResponseException(StringBuilder log, HttpRequest request, Throwable cause) {
        if (this.level.getInfo()) {
            log.append("RESPONSE " + request.getUrl() + " failed with exception: " + cause);
        }
    }

    /* compiled from: Logging.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J!\u0010\u000e\u001a\u00020\u00032\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\u0010¢\u0006\u0002\b\u0011H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lio/ktor/client/plugins/logging/Logging$Companion;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/logging/Logging$Config;", "Lio/ktor/client/plugins/logging/Logging;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "plugin", "scope", "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion implements HttpClientPlugin<Config, Logging> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<Logging> getKey() {
            return Logging.key;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public Logging prepare(Function1<? super Config, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Config config = new Config();
            block.invoke(config);
            return new Logging(config.getLogger(), config.getLevel(), config.getFilters$ktor_client_logging(), config.getSanitizedHeaders$ktor_client_logging(), null);
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(Logging plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            plugin.setupRequestLogging(scope);
            plugin.setupResponseLogging(scope);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldBeLogged(HttpRequestBuilder request) {
        Iterable $this$any$iv;
        if (this.filters.isEmpty()) {
            return true;
        }
        Iterable $this$any$iv2 = this.filters;
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it = $this$any$iv2.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    Function1 it2 = (Function1) element$iv;
                    if (((Boolean) it2.invoke(request)).booleanValue()) {
                        $this$any$iv = 1;
                        break;
                    }
                } else {
                    $this$any$iv = null;
                    break;
                }
            }
        } else {
            $this$any$iv = null;
        }
        return $this$any$iv != null;
    }
}
