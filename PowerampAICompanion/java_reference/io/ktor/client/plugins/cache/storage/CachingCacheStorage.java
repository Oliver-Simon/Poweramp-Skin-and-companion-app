package io.ktor.client.plugins.cache.storage;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.http.Url;
import io.ktor.util.collections.ConcurrentMap;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileCacheStorage.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J/\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\n\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J!\u0010\u0004\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/client/plugins/cache/storage/CachingCacheStorage;", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "delegate", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;)V", "store", "Lio/ktor/util/collections/ConcurrentMap;", "Lio/ktor/http/Url;", "", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "find", TrackProviderConsts.COLUMN_URL, "varyKeys", "", "", "(Lio/ktor/http/Url;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findAll", "(Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "data", "(Lio/ktor/http/Url;Lio/ktor/client/plugins/cache/storage/CachedResponseData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CachingCacheStorage implements CacheStorage {
    private final CacheStorage delegate;
    private final ConcurrentMap<Url, Set<CachedResponseData>> store;

    public CachingCacheStorage(CacheStorage delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        this.store = new ConcurrentMap<>(0, 1, null);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.util.Map] */
    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object store(io.ktor.http.Url r6, io.ktor.client.plugins.cache.storage.CachedResponseData r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.client.plugins.cache.storage.CachingCacheStorage$store$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$store$1 r0 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage$store$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$store$1 r0 = new io.ktor.client.plugins.cache.storage.CachingCacheStorage$store$1
            r0.<init>(r5, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L46;
                case 1: goto L3a;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            java.lang.Object r6 = r0.L$1
            io.ktor.http.Url r6 = (io.ktor.http.Url) r6
            java.lang.Object r7 = r0.L$0
            java.util.Map r7 = (java.util.Map) r7
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r7
            r7 = r8
            goto L6f
        L3a:
            java.lang.Object r6 = r0.L$1
            io.ktor.http.Url r6 = (io.ktor.http.Url) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.client.plugins.cache.storage.CachingCacheStorage r7 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5b
        L46:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r5
            io.ktor.client.plugins.cache.storage.CacheStorage r3 = r2.delegate
            r0.L$0 = r2
            r0.L$1 = r6
            r4 = 1
            r0.label = r4
            java.lang.Object r7 = r3.store(r6, r7, r0)
            if (r7 != r1) goto L5a
            return r1
        L5a:
            r7 = r2
        L5b:
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r2 = r7.store
            java.util.Map r2 = (java.util.Map) r2
            io.ktor.client.plugins.cache.storage.CacheStorage r3 = r7.delegate
            r0.L$0 = r2
            r0.L$1 = r6
            r4 = 2
            r0.label = r4
            java.lang.Object r7 = r3.findAll(r6, r0)
            if (r7 != r1) goto L6f
            return r1
        L6f:
            r2.put(r6, r7)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.CachingCacheStorage.store(io.ktor.http.Url, io.ktor.client.plugins.cache.storage.CachedResponseData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object find(io.ktor.http.Url r13, java.util.Map<java.lang.String, java.lang.String> r14, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r15) {
        /*
            r12 = this;
            boolean r0 = r15 instanceof io.ktor.client.plugins.cache.storage.CachingCacheStorage$find$1
            if (r0 == 0) goto L14
            r0 = r15
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$find$1 r0 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage$find$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L19
        L14:
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$find$1 r0 = new io.ktor.client.plugins.cache.storage.CachingCacheStorage$find$1
            r0.<init>(r12, r15)
        L19:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L46;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L2d:
            java.lang.Object r13 = r0.L$4
            io.ktor.http.Url r13 = (io.ktor.http.Url) r13
            java.lang.Object r14 = r0.L$3
            java.util.Map r14 = (java.util.Map) r14
            java.lang.Object r1 = r0.L$2
            java.util.Map r1 = (java.util.Map) r1
            java.lang.Object r2 = r0.L$1
            io.ktor.http.Url r2 = (io.ktor.http.Url) r2
            java.lang.Object r4 = r0.L$0
            io.ktor.client.plugins.cache.storage.CachingCacheStorage r4 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage) r4
            kotlin.ResultKt.throwOnFailure(r15)
            r5 = r15
            goto L6e
        L46:
            kotlin.ResultKt.throwOnFailure(r15)
            r4 = r12
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r2 = r4.store
            boolean r2 = r2.containsKey(r13)
            if (r2 != 0) goto L73
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r2 = r4.store
            java.util.Map r2 = (java.util.Map) r2
            io.ktor.client.plugins.cache.storage.CacheStorage r5 = r4.delegate
            r0.L$0 = r4
            r0.L$1 = r13
            r0.L$2 = r14
            r0.L$3 = r2
            r0.L$4 = r13
            r0.label = r3
            java.lang.Object r5 = r5.findAll(r13, r0)
            if (r5 != r1) goto L6b
            return r1
        L6b:
            r1 = r14
            r14 = r2
            r2 = r13
        L6e:
            r14.put(r13, r5)
            r14 = r1
            r13 = r2
        L73:
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r1 = r4.store
            java.util.Map r1 = (java.util.Map) r1
            java.lang.Object r1 = kotlin.collections.MapsKt.getValue(r1, r13)
            r13 = r1
            java.util.Set r13 = (java.util.Set) r13
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.Iterator r13 = r13.iterator()
        L84:
            boolean r1 = r13.hasNext()
            if (r1 == 0) goto Ld2
            java.lang.Object r1 = r13.next()
            r2 = r1
            io.ktor.client.plugins.cache.storage.CachedResponseData r2 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r2
            r4 = 0
            r5 = r14
            r6 = 0
            boolean r7 = r5.isEmpty()
            if (r7 == 0) goto L9c
            r5 = r3
            goto Lce
        L9c:
            java.util.Set r7 = r5.entrySet()
            java.util.Iterator r5 = r7.iterator()
        La4:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto Lcd
            java.lang.Object r7 = r5.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            r8 = 0
            java.lang.Object r9 = r7.getKey()
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r10 = r7.getValue()
            java.lang.String r10 = (java.lang.String) r10
            java.util.Map r11 = r2.getVaryKeys()
            java.lang.Object r11 = r11.get(r9)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r10)
            if (r8 != 0) goto La4
            r5 = 0
            goto Lce
        Lcd:
            r5 = r3
        Lce:
            if (r5 == 0) goto L84
            goto Ld3
        Ld2:
            r1 = 0
        Ld3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.CachingCacheStorage.find(io.ktor.http.Url, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object findAll(io.ktor.http.Url r7, kotlin.coroutines.Continuation<? super java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.client.plugins.cache.storage.CachingCacheStorage$findAll$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$findAll$1 r0 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage$findAll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$findAll$1 r0 = new io.ktor.client.plugins.cache.storage.CachingCacheStorage$findAll$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L41;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            java.lang.Object r7 = r0.L$3
            io.ktor.http.Url r7 = (io.ktor.http.Url) r7
            java.lang.Object r1 = r0.L$2
            java.util.Map r1 = (java.util.Map) r1
            java.lang.Object r2 = r0.L$1
            io.ktor.http.Url r2 = (io.ktor.http.Url) r2
            java.lang.Object r3 = r0.L$0
            io.ktor.client.plugins.cache.storage.CachingCacheStorage r3 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage) r3
            kotlin.ResultKt.throwOnFailure(r8)
            r4 = r8
            goto L67
        L41:
            kotlin.ResultKt.throwOnFailure(r8)
            r3 = r6
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r2 = r3.store
            boolean r2 = r2.containsKey(r7)
            if (r2 != 0) goto L6b
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r2 = r3.store
            java.util.Map r2 = (java.util.Map) r2
            io.ktor.client.plugins.cache.storage.CacheStorage r4 = r3.delegate
            r0.L$0 = r3
            r0.L$1 = r7
            r0.L$2 = r2
            r0.L$3 = r7
            r5 = 1
            r0.label = r5
            java.lang.Object r4 = r4.findAll(r7, r0)
            if (r4 != r1) goto L65
            return r1
        L65:
            r1 = r2
            r2 = r7
        L67:
            r1.put(r7, r4)
            r7 = r2
        L6b:
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r1 = r3.store
            java.util.Map r1 = (java.util.Map) r1
            java.lang.Object r1 = kotlin.collections.MapsKt.getValue(r1, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.CachingCacheStorage.findAll(io.ktor.http.Url, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
