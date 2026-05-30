package io.ktor.client.engine.okhttp;

import io.ktor.client.call.UnsupportedContentTypeException;
import io.ktor.client.engine.UtilsKt;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.plugins.HttpTimeoutKt;
import io.ktor.client.request.HttpRequestData;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;
import okio.BufferedSource;

/* compiled from: OkHttpEngine.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u0014\u0010\f\u001a\u00020\r*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001a\u001c\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0004H\u0002¨\u0006\u0015"}, d2 = {"mapExceptions", "", "cause", "request", "Lio/ktor/client/request/HttpRequestData;", "convertToOkHttpBody", "Lokhttp3/RequestBody;", "Lio/ktor/http/content/OutgoingContent;", "callContext", "Lkotlin/coroutines/CoroutineContext;", "convertToOkHttpRequest", "Lokhttp3/Request;", "setupTimeoutAttributes", "Lokhttp3/OkHttpClient$Builder;", "timeoutAttributes", "Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "toChannel", "Lio/ktor/utils/io/ByteReadChannel;", "Lokio/BufferedSource;", "context", "requestData", "ktor-client-okhttp"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OkHttpEngineKt {
    public static final /* synthetic */ Throwable access$mapExceptions(Throwable cause, HttpRequestData request) {
        return mapExceptions(cause, request);
    }

    public static final ByteReadChannel toChannel(BufferedSource $this$toChannel, CoroutineContext context, HttpRequestData requestData) {
        return CoroutinesKt.writer$default((CoroutineScope) GlobalScope.INSTANCE, context, false, (Function2) new OkHttpEngineKt$toChannel$1($this$toChannel, context, requestData, null), 2, (Object) null).getChannel();
    }

    public static final Throwable mapExceptions(Throwable cause, HttpRequestData request) {
        return cause instanceof SocketTimeoutException ? HttpTimeoutKt.SocketTimeoutException(request, cause) : cause;
    }

    public static final Request convertToOkHttpRequest(HttpRequestData $this$convertToOkHttpRequest, CoroutineContext callContext) {
        RequestBody bodyBytes;
        final Request.Builder builder = new Request.Builder();
        builder.url($this$convertToOkHttpRequest.getUrl().getUrlString());
        UtilsKt.mergeHeaders($this$convertToOkHttpRequest.getHeaders(), $this$convertToOkHttpRequest.getBody(), new Function2<String, String, Unit>() { // from class: io.ktor.client.engine.okhttp.OkHttpEngineKt$convertToOkHttpRequest$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String key, String value) {
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(value, "value");
                if (Intrinsics.areEqual(key, HttpHeaders.INSTANCE.getContentLength())) {
                    return;
                }
                Request.Builder.this.addHeader(key, value);
            }
        });
        if (HttpMethod.permitsRequestBody($this$convertToOkHttpRequest.getMethod().getValue())) {
            bodyBytes = convertToOkHttpBody($this$convertToOkHttpRequest.getBody(), callContext);
        } else {
            bodyBytes = null;
        }
        builder.method($this$convertToOkHttpRequest.getMethod().getValue(), bodyBytes);
        return builder.build();
    }

    public static final RequestBody convertToOkHttpBody(final OutgoingContent $this$convertToOkHttpBody, final CoroutineContext callContext) {
        Intrinsics.checkNotNullParameter($this$convertToOkHttpBody, "<this>");
        Intrinsics.checkNotNullParameter(callContext, "callContext");
        if ($this$convertToOkHttpBody instanceof OutgoingContent.ByteArrayContent) {
            byte[] it = ((OutgoingContent.ByteArrayContent) $this$convertToOkHttpBody).getBytes();
            return RequestBody.INSTANCE.create(it, MediaType.INSTANCE.parse(String.valueOf($this$convertToOkHttpBody.getContentType())), 0, it.length);
        }
        if ($this$convertToOkHttpBody instanceof OutgoingContent.ReadChannelContent) {
            return new StreamRequestBody($this$convertToOkHttpBody.getContentLength(), new Function0<ByteReadChannel>() { // from class: io.ktor.client.engine.okhttp.OkHttpEngineKt$convertToOkHttpBody$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ByteReadChannel invoke() {
                    return ((OutgoingContent.ReadChannelContent) OutgoingContent.this).getChannel();
                }
            });
        }
        if ($this$convertToOkHttpBody instanceof OutgoingContent.WriteChannelContent) {
            return new StreamRequestBody($this$convertToOkHttpBody.getContentLength(), new Function0<ByteReadChannel>() { // from class: io.ktor.client.engine.okhttp.OkHttpEngineKt$convertToOkHttpBody$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: OkHttpEngine.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "io.ktor.client.engine.okhttp.OkHttpEngineKt$convertToOkHttpBody$3$1", f = "OkHttpEngine.kt", i = {}, l = {214}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: io.ktor.client.engine.okhttp.OkHttpEngineKt$convertToOkHttpBody$3$1, reason: invalid class name */
                /* loaded from: classes9.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ OutgoingContent $this_convertToOkHttpBody;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(OutgoingContent outgoingContent, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$this_convertToOkHttpBody = outgoingContent;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_convertToOkHttpBody, continuation);
                        anonymousClass1.L$0 = obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                WriterScope $this$writer = (WriterScope) this.L$0;
                                this.label = 1;
                                if (((OutgoingContent.WriteChannelContent) this.$this_convertToOkHttpBody).writeTo($this$writer.getChannel(), this) != coroutine_suspended) {
                                    break;
                                } else {
                                    return coroutine_suspended;
                                }
                            case 1:
                                ResultKt.throwOnFailure($result);
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ByteReadChannel invoke() {
                    return CoroutinesKt.writer$default((CoroutineScope) GlobalScope.INSTANCE, CoroutineContext.this, false, (Function2) new AnonymousClass1($this$convertToOkHttpBody, null), 2, (Object) null).getChannel();
                }
            });
        }
        if ($this$convertToOkHttpBody instanceof OutgoingContent.NoContent) {
            return RequestBody.INSTANCE.create(new byte[0], (MediaType) null, 0, 0);
        }
        throw new UnsupportedContentTypeException($this$convertToOkHttpBody);
    }

    public static final OkHttpClient.Builder setupTimeoutAttributes(OkHttpClient.Builder $this$setupTimeoutAttributes, HttpTimeout.HttpTimeoutCapabilityConfiguration timeoutAttributes) {
        Long l = timeoutAttributes.get_connectTimeoutMillis();
        if (l != null) {
            $this$setupTimeoutAttributes.connectTimeout(HttpTimeoutKt.convertLongTimeoutToLongWithInfiniteAsZero(l.longValue()), TimeUnit.MILLISECONDS);
        }
        Long l2 = timeoutAttributes.get_socketTimeoutMillis();
        if (l2 != null) {
            long it = l2.longValue();
            $this$setupTimeoutAttributes.readTimeout(HttpTimeoutKt.convertLongTimeoutToLongWithInfiniteAsZero(it), TimeUnit.MILLISECONDS);
            $this$setupTimeoutAttributes.writeTimeout(HttpTimeoutKt.convertLongTimeoutToLongWithInfiniteAsZero(it), TimeUnit.MILLISECONDS);
        }
        return $this$setupTimeoutAttributes;
    }
}
