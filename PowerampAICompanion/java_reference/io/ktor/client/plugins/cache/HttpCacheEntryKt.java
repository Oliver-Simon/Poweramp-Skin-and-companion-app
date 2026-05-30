package io.ktor.client.plugins.cache;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.HeaderValue;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.GMTDate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: HttpCacheEntry.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\u001a!\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a$\u0010\t\u001a\u00020\n*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010H\u0000\u001a\u0018\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u0005H\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"HttpCacheEntry", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "isShared", "", "response", "Lio/ktor/client/statement/HttpResponse;", "(ZLio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldValidate", "Lio/ktor/client/plugins/cache/ValidateStatus;", "cacheExpires", "Lio/ktor/util/date/GMTDate;", "responseHeaders", "Lio/ktor/http/Headers;", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "fallback", "Lkotlin/Function0;", "varyKeys", "", "", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpCacheEntryKt {
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object HttpCacheEntry(boolean r8, io.ktor.client.statement.HttpResponse r9, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.HttpCacheEntry> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1 r0 = (io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1 r0 = new io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1
            r0.<init>(r10)
        L19:
            r4 = r0
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 1
            switch(r1) {
                case 0: goto L39;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2e:
            boolean r8 = r4.Z$0
            java.lang.Object r9 = r4.L$0
            io.ktor.client.statement.HttpResponse r9 = (io.ktor.client.statement.HttpResponse) r9
            kotlin.ResultKt.throwOnFailure(r10)
            r1 = r10
            goto L51
        L39:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.utils.io.ByteReadChannel r1 = r9.getContent()
            r4.L$0 = r9
            r4.Z$0 = r8
            r4.label = r7
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r1 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r1, r2, r4, r5, r6)
            if (r1 != r0) goto L51
            return r0
        L51:
            io.ktor.utils.io.core.ByteReadPacket r1 = (io.ktor.utils.io.core.ByteReadPacket) r1
            r0 = 0
            r2 = 0
            byte[] r1 = io.ktor.utils.io.core.StringsKt.readBytes$default(r1, r0, r7, r2)
            io.ktor.client.statement.HttpResponseKt.complete(r9)
            io.ktor.client.plugins.cache.HttpCacheEntry r3 = new io.ktor.client.plugins.cache.HttpCacheEntry
            if (r8 == 0) goto L61
            goto L62
        L61:
            r7 = r0
        L62:
            r8 = 2
            io.ktor.util.date.GMTDate r8 = cacheExpires$default(r9, r7, r2, r8, r2)
            java.util.Map r0 = varyKeys(r9)
            r3.<init>(r8, r0, r9, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheEntryKt.HttpCacheEntry(boolean, io.ktor.client.statement.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Map<String, String> varyKeys(HttpResponse $this$varyKeys) {
        Intrinsics.checkNotNullParameter($this$varyKeys, "<this>");
        List validationKeys = HttpMessagePropertiesKt.vary($this$varyKeys);
        if (validationKeys == null) {
            return MapsKt.emptyMap();
        }
        Map result = new LinkedHashMap();
        Headers requestHeaders = $this$varyKeys.getCall().getRequest().getHeaders();
        for (String key : validationKeys) {
            String str = requestHeaders.get(key);
            if (str == null) {
                str = "";
            }
            result.put(key, str);
        }
        return result;
    }

    public static /* synthetic */ GMTDate cacheExpires$default(HttpResponse httpResponse, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = new Function0<GMTDate>() { // from class: io.ktor.client.plugins.cache.HttpCacheEntryKt$cacheExpires$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final GMTDate invoke() {
                    return DateJvmKt.GMTDate$default(null, 1, null);
                }
            };
        }
        return cacheExpires(httpResponse, z, function0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x004f, code lost:
    
        if (r7 != null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final io.ktor.util.date.GMTDate cacheExpires(io.ktor.client.statement.HttpResponse r17, boolean r18, kotlin.jvm.functions.Function0<io.ktor.util.date.GMTDate> r19) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheEntryKt.cacheExpires(io.ktor.client.statement.HttpResponse, boolean, kotlin.jvm.functions.Function0):io.ktor.util.date.GMTDate");
    }

    public static final ValidateStatus shouldValidate(GMTDate cacheExpires, Headers responseHeaders, HttpRequestBuilder request) {
        int maxStale;
        Object element$iv;
        Integer requestMaxAge;
        long j;
        String value;
        Integer intOrNull;
        String value2;
        List split$default;
        String it;
        Intrinsics.checkNotNullParameter(cacheExpires, "cacheExpires");
        Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
        Intrinsics.checkNotNullParameter(request, "request");
        HeadersBuilder requestHeaders = request.getHeaders();
        List<String> all = responseHeaders.getAll(HttpHeaders.INSTANCE.getCacheControl());
        Object obj = null;
        List responseCacheControl = HttpHeaderValueParserKt.parseHeaderValue(all != null ? CollectionsKt.joinToString$default(all, ",", null, null, 0, null, null, 62, null) : null);
        List<String> all2 = requestHeaders.getAll(HttpHeaders.INSTANCE.getCacheControl());
        List requestCacheControl = HttpHeaderValueParserKt.parseHeaderValue(all2 != null ? CollectionsKt.joinToString$default(all2, ",", null, null, 0, null, null, 62, null) : null);
        if (requestCacheControl.contains(CacheControl.INSTANCE.getNO_CACHE$ktor_client_core())) {
            HttpCacheKt.getLOGGER().trace("\"no-cache\" is set for " + request.getUrl() + ", should validate cached response");
            return ValidateStatus.ShouldValidate;
        }
        List $this$firstOrNull$iv = requestCacheControl;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        while (true) {
            maxStale = 0;
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            HeadersBuilder requestHeaders2 = requestHeaders;
            if (StringsKt.startsWith$default(((HeaderValue) element$iv).getValue(), "max-age=", false, 2, (Object) null)) {
                break;
            }
            requestHeaders = requestHeaders2;
        }
        HeaderValue headerValue = (HeaderValue) element$iv;
        if (headerValue == null || (value2 = headerValue.getValue()) == null || (split$default = StringsKt.split$default((CharSequence) value2, new String[]{"="}, false, 0, 6, (Object) null)) == null || (it = (String) split$default.get(1)) == null) {
            requestMaxAge = null;
        } else {
            Integer intOrNull2 = StringsKt.toIntOrNull(it);
            requestMaxAge = Integer.valueOf(intOrNull2 != null ? intOrNull2.intValue() : 0);
        }
        if (requestMaxAge != null && requestMaxAge.intValue() == 0) {
            HttpCacheKt.getLOGGER().trace("\"max-age\" is not set for " + request.getUrl() + ", should validate cached response");
            return ValidateStatus.ShouldValidate;
        }
        if (responseCacheControl.contains(CacheControl.INSTANCE.getNO_CACHE$ktor_client_core())) {
            HttpCacheKt.getLOGGER().trace("\"no-cache\" is set for " + request.getUrl() + ", should validate cached response");
            return ValidateStatus.ShouldValidate;
        }
        long validMillis = cacheExpires.getTimestamp() - DateJvmKt.getTimeMillis();
        long j2 = 0;
        if (validMillis > 0) {
            HttpCacheKt.getLOGGER().trace("Cached response is valid for " + request.getUrl() + ", should not validate");
            return ValidateStatus.ShouldNotValidate;
        }
        if (responseCacheControl.contains(CacheControl.INSTANCE.getMUST_REVALIDATE$ktor_client_core())) {
            HttpCacheKt.getLOGGER().trace("\"must-revalidate\" is set for " + request.getUrl() + ", should validate cached response");
            return ValidateStatus.ShouldValidate;
        }
        List $this$firstOrNull$iv2 = requestCacheControl;
        Iterator it3 = $this$firstOrNull$iv2.iterator();
        while (true) {
            if (!it3.hasNext()) {
                j = j2;
                break;
            }
            Object element$iv2 = it3.next();
            j = j2;
            if (StringsKt.startsWith$default(((HeaderValue) element$iv2).getValue(), "max-stale=", false, 2, (Object) null)) {
                obj = element$iv2;
                break;
            }
            j2 = j;
        }
        HeaderValue headerValue2 = (HeaderValue) obj;
        if (headerValue2 != null && (value = headerValue2.getValue()) != null) {
            String substring = value.substring(10);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            if (substring != null && (intOrNull = StringsKt.toIntOrNull(substring)) != null) {
                maxStale = intOrNull.intValue();
            }
        }
        long maxStaleMillis = maxStale * 1000;
        if (validMillis + maxStaleMillis > j) {
            HttpCacheKt.getLOGGER().trace("Cached response is stale for " + request.getUrl() + " but less than max-stale, should warn");
            return ValidateStatus.ShouldWarn;
        }
        HttpCacheKt.getLOGGER().trace("Cached response is stale for " + request.getUrl() + ", should validate cached response");
        return ValidateStatus.ShouldValidate;
    }
}
