package io.ktor.client.plugins.cache;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.cache.storage.HttpCacheStorage;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.request.HttpResponseData;
import io.ktor.client.request.UtilsKt;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.date.GMTDate;
import io.ktor.util.pipeline.PipelineContext;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpCacheLegacy.kt */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a:\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u001e\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a9\u0010\u0015\u001a\u00020\u0016*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u00172\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a9\u0010\u001c\u001a\u00020\u0016*\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001aH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a1\u0010\u001f\u001a\u00020\u0016*\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u001aH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"cacheResponse", "Lio/ktor/client/statement/HttpResponse;", "Lio/ktor/client/plugins/cache/HttpCache;", "response", "(Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findAndRefresh", "request", "Lio/ktor/client/request/HttpRequest;", "findResponse", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "storage", "Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", "varyKeys", "", "", TrackProviderConsts.COLUMN_URL, "Lio/ktor/http/Url;", "context", "Lio/ktor/client/request/HttpRequestBuilder;", "content", "Lio/ktor/http/content/OutgoingContent;", "interceptReceiveLegacy", "", "Lio/ktor/util/pipeline/PipelineContext;", "plugin", "scope", "Lio/ktor/client/HttpClient;", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponse;Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/client/HttpClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "interceptSendLegacy", "", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/http/content/OutgoingContent;Lio/ktor/client/HttpClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "proceedWithWarning", "cachedCall", "Lio/ktor/client/call/HttpClientCall;", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/call/HttpClientCall;Lio/ktor/client/HttpClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpCacheLegacyKt {
    public static final Object interceptSendLegacy(PipelineContext<Object, HttpRequestBuilder> pipelineContext, HttpCache plugin, OutgoingContent content, HttpClient scope, Continuation<? super Unit> continuation) {
        Object proceedWithMissingCache$ktor_client_core;
        HttpCacheEntry cache = findResponse(plugin, pipelineContext.getContext(), content);
        if (cache == null) {
            List header = HttpHeaderValueParserKt.parseHeaderValue(pipelineContext.getContext().getHeaders().get(HttpHeaders.INSTANCE.getCacheControl()));
            return (header.contains(CacheControl.INSTANCE.getONLY_IF_CACHED$ktor_client_core()) && (proceedWithMissingCache$ktor_client_core = HttpCache.INSTANCE.proceedWithMissingCache$ktor_client_core(pipelineContext, scope, continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? proceedWithMissingCache$ktor_client_core : Unit.INSTANCE;
        }
        HttpClientCall cachedCall = cache.produceResponse$ktor_client_core().getCall();
        ValidateStatus validateStatus = HttpCacheEntryKt.shouldValidate(cache.getExpires(), cache.getResponse().getHeaders(), pipelineContext.getContext());
        if (validateStatus == ValidateStatus.ShouldNotValidate) {
            Object proceedWithCache$ktor_client_core = HttpCache.INSTANCE.proceedWithCache$ktor_client_core(pipelineContext, scope, cachedCall, continuation);
            return proceedWithCache$ktor_client_core == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? proceedWithCache$ktor_client_core : Unit.INSTANCE;
        }
        if (validateStatus == ValidateStatus.ShouldWarn) {
            Object proceedWithWarning = proceedWithWarning(pipelineContext, cachedCall, scope, continuation);
            return proceedWithWarning == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? proceedWithWarning : Unit.INSTANCE;
        }
        String etag = cache.getResponseHeaders().get(HttpHeaders.INSTANCE.getETag());
        if (etag != null) {
            UtilsKt.header(pipelineContext.getContext(), HttpHeaders.INSTANCE.getIfNoneMatch(), etag);
        }
        String it = cache.getResponseHeaders().get(HttpHeaders.INSTANCE.getLastModified());
        if (it != null) {
            UtilsKt.header(pipelineContext.getContext(), HttpHeaders.INSTANCE.getIfModifiedSince(), it);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0064 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object interceptReceiveLegacy(io.ktor.util.pipeline.PipelineContext<io.ktor.client.statement.HttpResponse, kotlin.Unit> r4, io.ktor.client.statement.HttpResponse r5, io.ktor.client.plugins.cache.HttpCache r6, io.ktor.client.HttpClient r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1 r0 = (io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1 r0 = new io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3d;
                case 1: goto L34;
                case 2: goto L30;
                case 3: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2c:
            kotlin.ResultKt.throwOnFailure(r8)
            goto La0
        L30:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L65
        L34:
            java.lang.Object r4 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r4 = (io.ktor.util.pipeline.PipelineContext) r4
            kotlin.ResultKt.throwOnFailure(r8)
            r5 = r8
            goto L56
        L3d:
            kotlin.ResultKt.throwOnFailure(r8)
            io.ktor.http.HttpStatusCode r2 = r5.getStatus()
            boolean r2 = io.ktor.http.HttpStatusCodeKt.isSuccess(r2)
            if (r2 == 0) goto L68
            r0.L$0 = r4
            r7 = 1
            r0.label = r7
            java.lang.Object r5 = cacheResponse(r6, r5, r0)
            if (r5 != r1) goto L56
            return r1
        L56:
            io.ktor.client.statement.HttpResponse r5 = (io.ktor.client.statement.HttpResponse) r5
            r6 = 0
            r0.L$0 = r6
            r6 = 2
            r0.label = r6
            java.lang.Object r4 = r4.proceedWith(r5, r0)
            if (r4 != r1) goto L65
            return r1
        L65:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L68:
            io.ktor.http.HttpStatusCode r2 = r5.getStatus()
            io.ktor.http.HttpStatusCode$Companion r3 = io.ktor.http.HttpStatusCode.INSTANCE
            io.ktor.http.HttpStatusCode r3 = r3.getNotModified()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r2 == 0) goto Lb5
            io.ktor.client.statement.HttpResponseKt.complete(r5)
            io.ktor.client.call.HttpClientCall r2 = r5.getCall()
            io.ktor.client.request.HttpRequest r2 = r2.getRequest()
            io.ktor.client.statement.HttpResponse r6 = findAndRefresh(r6, r2, r5)
            if (r6 == 0) goto La3
            io.ktor.events.Events r5 = r7.getMonitor()
            io.ktor.client.plugins.cache.HttpCache$Companion r2 = io.ktor.client.plugins.cache.HttpCache.INSTANCE
            io.ktor.events.EventDefinition r2 = r2.getHttpResponseFromCache()
            r5.raise(r2, r6)
            r5 = 3
            r0.label = r5
            java.lang.Object r4 = r4.proceedWith(r6, r0)
            if (r4 != r1) goto La0
            return r1
        La0:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        La3:
            io.ktor.client.plugins.cache.InvalidCacheStateException r4 = new io.ktor.client.plugins.cache.InvalidCacheStateException
            io.ktor.client.call.HttpClientCall r6 = r5.getCall()
            io.ktor.client.request.HttpRequest r6 = r6.getRequest()
            io.ktor.http.Url r6 = r6.getUrl()
            r4.<init>(r6)
            throw r4
        Lb5:
            goto La0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheLegacyKt.interceptReceiveLegacy(io.ktor.util.pipeline.PipelineContext, io.ktor.client.statement.HttpResponse, io.ktor.client.plugins.cache.HttpCache, io.ktor.client.HttpClient, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object proceedWithWarning(PipelineContext<Object, HttpRequestBuilder> pipelineContext, HttpClientCall cachedCall, HttpClient scope, Continuation<? super Unit> continuation) {
        HttpRequestData request = pipelineContext.getContext().build();
        HttpStatusCode status = cachedCall.getResponse().getStatus();
        GMTDate requestTime = cachedCall.getResponse().getRequestTime();
        Headers.Companion companion = Headers.INSTANCE;
        HeadersBuilder $this$proceedWithWarning_u24lambda_u242 = new HeadersBuilder(0, 1, null);
        $this$proceedWithWarning_u24lambda_u242.appendAll(cachedCall.getResponse().getHeaders());
        $this$proceedWithWarning_u24lambda_u242.append(HttpHeaders.INSTANCE.getWarning(), "110");
        Unit unit = Unit.INSTANCE;
        HttpResponseData response = new HttpResponseData(status, requestTime, $this$proceedWithWarning_u24lambda_u242.build(), cachedCall.getResponse().getVersion(), cachedCall.getResponse().getContent(), cachedCall.getResponse().getCoroutineContext());
        HttpClientCall call = new HttpClientCall(scope, request, response);
        pipelineContext.finish();
        scope.getMonitor().raise(HttpCache.INSTANCE.getHttpResponseFromCache(), call.getResponse());
        Object proceedWith = pipelineContext.proceedWith(call, continuation);
        return proceedWith == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? proceedWith : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object cacheResponse(io.ktor.client.plugins.cache.HttpCache r7, io.ktor.client.statement.HttpResponse r8, kotlin.coroutines.Continuation<? super io.ktor.client.statement.HttpResponse> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1 r0 = (io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1 r0 = new io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L31;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            kotlin.ResultKt.throwOnFailure(r9)
            r7 = r9
            goto L8a
        L31:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ktor.client.call.HttpClientCall r2 = r8.getCall()
            io.ktor.client.request.HttpRequest r2 = r2.getRequest()
            r3 = r8
            io.ktor.http.HttpMessage r3 = (io.ktor.http.HttpMessage) r3
            java.util.List r3 = io.ktor.http.HttpMessagePropertiesKt.cacheControl(r3)
            r4 = r2
            io.ktor.http.HttpMessage r4 = (io.ktor.http.HttpMessage) r4
            java.util.List r4 = io.ktor.http.HttpMessagePropertiesKt.cacheControl(r4)
            io.ktor.client.plugins.cache.CacheControl r5 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r5 = r5.getPRIVATE$ktor_client_core()
            boolean r5 = r3.contains(r5)
            if (r5 == 0) goto L5b
            io.ktor.client.plugins.cache.storage.HttpCacheStorage r5 = r7.getPrivateStorage()
            goto L5f
        L5b:
            io.ktor.client.plugins.cache.storage.HttpCacheStorage r5 = r7.getPublicStorage()
        L5f:
            io.ktor.client.plugins.cache.CacheControl r6 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r6 = r6.getNO_STORE$ktor_client_core()
            boolean r6 = r3.contains(r6)
            if (r6 != 0) goto L91
            io.ktor.client.plugins.cache.CacheControl r6 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r6 = r6.getNO_STORE$ktor_client_core()
            boolean r6 = r4.contains(r6)
            if (r6 == 0) goto L78
            goto L91
        L78:
            io.ktor.http.Url r3 = r2.getUrl()
            boolean r4 = r7.getIsSharedClient()
            r6 = 1
            r0.label = r6
            java.lang.Object r7 = io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store(r5, r3, r8, r4, r0)
            if (r7 != r1) goto L8a
            return r1
        L8a:
            io.ktor.client.plugins.cache.HttpCacheEntry r7 = (io.ktor.client.plugins.cache.HttpCacheEntry) r7
            io.ktor.client.statement.HttpResponse r8 = r7.produceResponse$ktor_client_core()
            return r8
        L91:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheLegacyKt.cacheResponse(io.ktor.client.plugins.cache.HttpCache, io.ktor.client.statement.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final HttpResponse findAndRefresh(HttpCache $this$findAndRefresh, HttpRequest request, HttpResponse response) {
        Url url = response.getCall().getRequest().getUrl();
        List cacheControl = HttpMessagePropertiesKt.cacheControl(response);
        HttpCacheStorage storage = cacheControl.contains(CacheControl.INSTANCE.getPRIVATE$ktor_client_core()) ? $this$findAndRefresh.getPrivateStorage() : $this$findAndRefresh.getPublicStorage();
        Map varyKeysFrom304 = HttpCacheEntryKt.varyKeys(response);
        HttpCacheEntry cache = findResponse($this$findAndRefresh, storage, varyKeysFrom304, url, request);
        if (cache == null) {
            return null;
        }
        Map newVaryKeys = varyKeysFrom304.isEmpty() ? cache.getVaryKeys() : varyKeysFrom304;
        storage.store(url, new HttpCacheEntry(HttpCacheEntryKt.cacheExpires$default(response, $this$findAndRefresh.getIsSharedClient(), null, 2, null), newVaryKeys, cache.getResponse(), cache.getBody()));
        return cache.produceResponse$ktor_client_core();
    }

    private static final HttpCacheEntry findResponse(HttpCache $this$findResponse, HttpCacheStorage storage, Map<String, String> map, Url url, HttpRequest request) {
        Object element$iv;
        boolean z;
        if (!map.isEmpty()) {
            return storage.find(url, map);
        }
        Function1 requestHeaders = HttpCacheKt.mergedHeadersLookup(request.getContent(), new HttpCacheLegacyKt$findResponse$requestHeaders$1(request.getHeaders()), new HttpCacheLegacyKt$findResponse$requestHeaders$2(request.getHeaders()));
        Iterable $this$sortedByDescending$iv = storage.findByUrl(url);
        Iterable $this$firstOrNull$iv = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: io.ktor.client.plugins.cache.HttpCacheLegacyKt$findResponse$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                HttpCacheEntry it = (HttpCacheEntry) t2;
                HttpCacheEntry it2 = (HttpCacheEntry) t;
                return ComparisonsKt.compareValues(it.getResponse().getResponseTime(), it2.getResponse().getResponseTime());
            }
        });
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                HttpCacheEntry cachedResponse = (HttpCacheEntry) element$iv;
                Map $this$all$iv = cachedResponse.getVaryKeys();
                if (!$this$all$iv.isEmpty()) {
                    Iterator<Map.Entry<String, String>> it2 = $this$all$iv.entrySet().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Map.Entry element$iv2 = it2.next();
                            String key = element$iv2.getKey();
                            String value = element$iv2.getValue();
                            if (!Intrinsics.areEqual(requestHeaders.invoke(key), value)) {
                                z = false;
                                break;
                            }
                        } else {
                            z = true;
                            break;
                        }
                    }
                } else {
                    z = true;
                }
                if (z) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        return (HttpCacheEntry) element$iv;
    }

    private static final HttpCacheEntry findResponse(HttpCache $this$findResponse, HttpRequestBuilder context, OutgoingContent content) {
        Url url = URLUtilsKt.Url(context.getUrl());
        Function1 lookup = HttpCacheKt.mergedHeadersLookup(content, new HttpCacheLegacyKt$findResponse$lookup$1(context.getHeaders()), new HttpCacheLegacyKt$findResponse$lookup$2(context.getHeaders()));
        Set<HttpCacheEntry> cachedResponses = SetsKt.plus((Set) $this$findResponse.getPrivateStorage().findByUrl(url), (Iterable) $this$findResponse.getPublicStorage().findByUrl(url));
        for (HttpCacheEntry item : cachedResponses) {
            Map varyKeys = item.getVaryKeys();
            if (!varyKeys.isEmpty()) {
                boolean z = true;
                if (!varyKeys.isEmpty()) {
                    Iterator<Map.Entry<String, String>> it = varyKeys.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry element$iv = it.next();
                        String key = element$iv.getKey();
                        String value = element$iv.getValue();
                        if (!Intrinsics.areEqual(lookup.invoke(key), value)) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z) {
                }
            }
            return item;
        }
        return null;
    }
}
