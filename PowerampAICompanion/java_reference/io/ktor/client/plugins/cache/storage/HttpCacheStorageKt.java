package io.ktor.client.plugins.cache.storage;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.SavedHttpCall;
import io.ktor.client.plugins.cache.HttpCacheEntryKt;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.InternalAPI;
import io.ktor.util.date.GMTDate;
import io.ktor.utils.io.ByteReadChannel;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpCacheStorage.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u001d\u0010\t\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a1\u0010\t\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000eH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a;\u0010\t\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a-\u0010\t\u001a\u00020\u0014*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"createResponse", "Lio/ktor/client/statement/HttpResponse;", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "client", "Lio/ktor/client/HttpClient;", "request", "Lio/ktor/client/request/HttpRequest;", "responseContext", "Lkotlin/coroutines/CoroutineContext;", "store", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "response", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "varyKeys", "", "", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;Lio/ktor/client/statement/HttpResponse;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isShared", "", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;Lio/ktor/client/statement/HttpResponse;Ljava/util/Map;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", TrackProviderConsts.COLUMN_URL, "Lio/ktor/http/Url;", "value", "(Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;Lio/ktor/http/Url;Lio/ktor/client/statement/HttpResponse;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpCacheStorageKt {
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object store(io.ktor.client.plugins.cache.storage.HttpCacheStorage r4, io.ktor.http.Url r5, io.ktor.client.statement.HttpResponse r6, boolean r7, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.HttpCacheEntry> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1 r0 = (io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1 r0 = new io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L39;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2c:
            java.lang.Object r4 = r0.L$1
            io.ktor.http.Url r4 = (io.ktor.http.Url) r4
            java.lang.Object r5 = r0.L$0
            io.ktor.client.plugins.cache.storage.HttpCacheStorage r5 = (io.ktor.client.plugins.cache.storage.HttpCacheStorage) r5
            kotlin.ResultKt.throwOnFailure(r8)
            r6 = r8
            goto L52
        L39:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = 1
            if (r7 == 0) goto L41
            r7 = r2
            goto L42
        L41:
            r7 = 0
        L42:
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r6 = io.ktor.client.plugins.cache.HttpCacheEntryKt.HttpCacheEntry(r7, r6, r0)
            if (r6 != r1) goto L4f
            return r1
        L4f:
            r3 = r5
            r5 = r4
            r4 = r3
        L52:
            io.ktor.client.plugins.cache.HttpCacheEntry r6 = (io.ktor.client.plugins.cache.HttpCacheEntry) r6
            r5.store(r4, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store(io.ktor.client.plugins.cache.storage.HttpCacheStorage, io.ktor.http.Url, io.ktor.client.statement.HttpResponse, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object store(CacheStorage $this$store, HttpResponse response, Continuation<? super CachedResponseData> continuation) {
        return store$default($this$store, response, HttpCacheEntryKt.varyKeys(response), false, continuation, 4, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00dd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object store(io.ktor.client.plugins.cache.storage.CacheStorage r23, io.ktor.client.statement.HttpResponse r24, java.util.Map<java.lang.String, java.lang.String> r25, boolean r26, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r27) {
        /*
            r0 = r27
            boolean r1 = r0 instanceof io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4
            if (r1 == 0) goto L16
            r1 = r0
            io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4 r1 = (io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4 r1 = new io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4
            r1.<init>(r0)
        L1b:
            r5 = r1
            java.lang.Object r0 = r5.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r5.label
            r8 = 1
            switch(r2) {
                case 0: goto L53;
                case 1: goto L39;
                case 2: goto L30;
                default: goto L28;
            }
        L28:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L30:
            java.lang.Object r1 = r5.L$0
            io.ktor.client.plugins.cache.storage.CachedResponseData r1 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto Ldf
        L39:
            boolean r2 = r5.Z$0
            java.lang.Object r3 = r5.L$3
            io.ktor.http.Url r3 = (io.ktor.http.Url) r3
            java.lang.Object r4 = r5.L$2
            java.util.Map r4 = (java.util.Map) r4
            java.lang.Object r6 = r5.L$1
            io.ktor.client.statement.HttpResponse r6 = (io.ktor.client.statement.HttpResponse) r6
            java.lang.Object r7 = r5.L$0
            io.ktor.client.plugins.cache.storage.CacheStorage r7 = (io.ktor.client.plugins.cache.storage.CacheStorage) r7
            kotlin.ResultKt.throwOnFailure(r0)
            r12 = r2
            r21 = r4
            r2 = r0
            goto L8a
        L53:
            kotlin.ResultKt.throwOnFailure(r0)
            r9 = r23
            r10 = r25
            r11 = r24
            r12 = r26
            io.ktor.client.call.HttpClientCall r2 = r11.getCall()
            io.ktor.client.request.HttpRequest r2 = r2.getRequest()
            io.ktor.http.Url r13 = r2.getUrl()
            io.ktor.utils.io.ByteReadChannel r2 = r11.getContent()
            r5.L$0 = r9
            r5.L$1 = r11
            r5.L$2 = r10
            r5.L$3 = r13
            r5.Z$0 = r12
            r5.label = r8
            r3 = 0
            r6 = 1
            r7 = 0
            java.lang.Object r2 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r2, r3, r5, r6, r7)
            if (r2 != r1) goto L85
            return r1
        L85:
            r7 = r9
            r21 = r10
            r6 = r11
            r3 = r13
        L8a:
            io.ktor.utils.io.core.ByteReadPacket r2 = (io.ktor.utils.io.core.ByteReadPacket) r2
            r4 = 0
            r9 = 0
            byte[] r22 = io.ktor.utils.io.core.StringsKt.readBytes$default(r2, r4, r8, r9)
            io.ktor.client.statement.HttpResponseKt.complete(r6)
            io.ktor.client.call.HttpClientCall r2 = r6.getCall()
            io.ktor.client.request.HttpRequest r2 = r2.getRequest()
            io.ktor.http.Url r14 = r2.getUrl()
            io.ktor.http.HttpStatusCode r15 = r6.getStatus()
            io.ktor.util.date.GMTDate r16 = r6.getRequestTime()
            io.ktor.http.Headers r20 = r6.getHeaders()
            io.ktor.http.HttpProtocolVersion r18 = r6.getVersion()
            io.ktor.util.date.GMTDate r17 = r6.getResponseTime()
            if (r12 == 0) goto Lb8
            goto Lb9
        Lb8:
            r8 = r4
        Lb9:
            r2 = 2
            io.ktor.util.date.GMTDate r19 = io.ktor.client.plugins.cache.HttpCacheEntryKt.cacheExpires$default(r6, r8, r9, r2, r9)
            io.ktor.client.plugins.cache.storage.CachedResponseData r13 = new io.ktor.client.plugins.cache.storage.CachedResponseData
            r13.<init>(r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r5.L$0 = r13
            r5.L$1 = r9
            r5.L$2 = r9
            r5.L$3 = r9
            r5.label = r2
            java.lang.Object r2 = r7.store(r3, r13, r5)
            if (r2 != r1) goto Lde
            return r1
        Lde:
            r1 = r13
        Ldf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store(io.ktor.client.plugins.cache.storage.CacheStorage, io.ktor.client.statement.HttpResponse, java.util.Map, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object store$default(CacheStorage cacheStorage, HttpResponse httpResponse, Map map, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return store(cacheStorage, httpResponse, (Map<String, String>) map, z, (Continuation<? super CachedResponseData>) continuation);
    }

    public static final HttpResponse createResponse(final CachedResponseData $this$createResponse, HttpClient client, HttpRequest request, final CoroutineContext responseContext) {
        Intrinsics.checkNotNullParameter($this$createResponse, "<this>");
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(responseContext, "responseContext");
        return new SavedHttpCall(client, request, new HttpResponse($this$createResponse, responseContext) { // from class: io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$createResponse$response$1
            private final CoroutineContext coroutineContext;
            private final Headers headers;
            private final GMTDate requestTime;
            private final GMTDate responseTime;
            private final HttpStatusCode status;
            private final HttpProtocolVersion version;

            @InternalAPI
            public static /* synthetic */ void getContent$annotations() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.status = $this$createResponse.getStatusCode();
                this.version = $this$createResponse.getVersion();
                this.requestTime = $this$createResponse.getRequestTime();
                this.responseTime = $this$createResponse.getResponseTime();
                this.headers = $this$createResponse.getHeaders();
                this.coroutineContext = responseContext;
            }

            @Override // io.ktor.client.statement.HttpResponse
            public HttpClientCall getCall() {
                throw new IllegalStateException("This is a fake response");
            }

            @Override // io.ktor.client.statement.HttpResponse
            public HttpStatusCode getStatus() {
                return this.status;
            }

            @Override // io.ktor.client.statement.HttpResponse
            public HttpProtocolVersion getVersion() {
                return this.version;
            }

            @Override // io.ktor.client.statement.HttpResponse
            public GMTDate getRequestTime() {
                return this.requestTime;
            }

            @Override // io.ktor.client.statement.HttpResponse
            public GMTDate getResponseTime() {
                return this.responseTime;
            }

            @Override // io.ktor.client.statement.HttpResponse
            public ByteReadChannel getContent() {
                throw new IllegalStateException("This is a fake response");
            }

            @Override // io.ktor.http.HttpMessage
            public Headers getHeaders() {
                return this.headers;
            }

            @Override // kotlinx.coroutines.CoroutineScope
            public CoroutineContext getCoroutineContext() {
                return this.coroutineContext;
            }
        }, $this$createResponse.getBody()).getResponse();
    }
}
