package io.ktor.client.engine.okhttp;

import com.google.common.net.HttpHeaders;
import io.ktor.client.network.sockets.SocketTimeoutException;
import io.ktor.client.plugins.HttpTimeoutKt;
import io.ktor.client.request.HttpRequestData;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: OkUtils.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a%\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\f\u0010\f\u001a\u00020\r*\u00020\u000eH\u0000\u001a\f\u0010\f\u001a\u00020\u000f*\u00020\u0010H\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0005H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"mapOkHttpException", "", "requestData", "Lio/ktor/client/request/HttpRequestData;", HttpHeaders.ReferrerPolicyValues.ORIGIN, "Ljava/io/IOException;", "execute", "Lokhttp3/Response;", "Lokhttp3/OkHttpClient;", "request", "Lokhttp3/Request;", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Lio/ktor/client/request/HttpRequestData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fromOkHttp", "Lio/ktor/http/Headers;", "Lokhttp3/Headers;", "Lio/ktor/http/HttpProtocolVersion;", "Lokhttp3/Protocol;", "isConnectException", "", "ktor-client-okhttp"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OkUtilsKt {

    /* compiled from: OkUtils.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Protocol.values().length];
            try {
                iArr[Protocol.HTTP_1_0.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Protocol.HTTP_1_1.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Protocol.SPDY_3.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Protocol.HTTP_2.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Protocol.H2_PRIOR_KNOWLEDGE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[Protocol.QUIC.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Object execute(OkHttpClient $this$execute, Request request, HttpRequestData requestData, Continuation<? super Response> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl continuation2 = cancellable$iv;
        final Call call = $this$execute.newCall(request);
        call.enqueue(new OkHttpCallback(requestData, continuation2));
        continuation2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: io.ktor.client.engine.okhttp.OkUtilsKt$execute$2$1
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
                Call.this.cancel();
            }
        });
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static final Headers fromOkHttp(final okhttp3.Headers $this$fromOkHttp) {
        Intrinsics.checkNotNullParameter($this$fromOkHttp, "<this>");
        return new Headers() { // from class: io.ktor.client.engine.okhttp.OkUtilsKt$fromOkHttp$1
            private final boolean caseInsensitiveName = true;

            @Override // io.ktor.util.StringValues
            public boolean contains(String name) {
                return Headers.DefaultImpls.contains(this, name);
            }

            @Override // io.ktor.util.StringValues
            public boolean contains(String name, String value) {
                return Headers.DefaultImpls.contains(this, name, value);
            }

            @Override // io.ktor.util.StringValues
            public void forEach(Function2<? super String, ? super List<String>, Unit> function2) {
                Headers.DefaultImpls.forEach(this, function2);
            }

            @Override // io.ktor.util.StringValues
            public String get(String name) {
                return Headers.DefaultImpls.get(this, name);
            }

            @Override // io.ktor.util.StringValues
            public boolean getCaseInsensitiveName() {
                return this.caseInsensitiveName;
            }

            @Override // io.ktor.util.StringValues
            public List<String> getAll(String name) {
                Intrinsics.checkNotNullParameter(name, "name");
                List it = okhttp3.Headers.this.values(name);
                if (it.isEmpty()) {
                    return null;
                }
                return it;
            }

            @Override // io.ktor.util.StringValues
            public Set<String> names() {
                return okhttp3.Headers.this.names();
            }

            @Override // io.ktor.util.StringValues
            public Set<Map.Entry<String, List<String>>> entries() {
                return okhttp3.Headers.this.toMultimap().entrySet();
            }

            @Override // io.ktor.util.StringValues
            public boolean isEmpty() {
                return okhttp3.Headers.this.size() == 0;
            }
        };
    }

    public static final HttpProtocolVersion fromOkHttp(Protocol $this$fromOkHttp) {
        Intrinsics.checkNotNullParameter($this$fromOkHttp, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$fromOkHttp.ordinal()]) {
            case 1:
                return HttpProtocolVersion.INSTANCE.getHTTP_1_0();
            case 2:
                return HttpProtocolVersion.INSTANCE.getHTTP_1_1();
            case 3:
                return HttpProtocolVersion.INSTANCE.getSPDY_3();
            case 4:
                return HttpProtocolVersion.INSTANCE.getHTTP_2_0();
            case 5:
                return HttpProtocolVersion.INSTANCE.getHTTP_2_0();
            case 6:
                return HttpProtocolVersion.INSTANCE.getQUIC();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Throwable mapOkHttpException(HttpRequestData requestData, IOException origin) {
        SocketTimeoutException SocketTimeoutException;
        if (origin instanceof StreamAdapterIOException) {
            Throwable cause = origin.getCause();
            return cause == null ? origin : cause;
        }
        if (origin instanceof java.net.SocketTimeoutException) {
            if (isConnectException(origin)) {
                SocketTimeoutException = HttpTimeoutKt.ConnectTimeoutException(requestData, origin);
            } else {
                SocketTimeoutException = HttpTimeoutKt.SocketTimeoutException(requestData, origin);
            }
            return SocketTimeoutException;
        }
        return origin;
    }

    private static final boolean isConnectException(IOException $this$isConnectException) {
        String message = $this$isConnectException.getMessage();
        return message != null && StringsKt.contains((CharSequence) message, (CharSequence) "connect", true);
    }
}
