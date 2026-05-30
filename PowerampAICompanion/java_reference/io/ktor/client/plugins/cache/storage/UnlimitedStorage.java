package io.ktor.client.plugins.cache.storage;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.http.Url;
import io.ktor.util.collections.ConcurrentMap;
import io.ktor.util.collections.ConcurrentSetKt;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UnlimitedCacheStorage.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J/\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u00052\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\u0006\u0010\t\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J!\u0010\u0003\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0013R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/client/plugins/cache/storage/UnlimitedStorage;", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "()V", "store", "Lio/ktor/util/collections/ConcurrentMap;", "Lio/ktor/http/Url;", "", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "find", TrackProviderConsts.COLUMN_URL, "varyKeys", "", "", "(Lio/ktor/http/Url;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findAll", "", "(Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "data", "(Lio/ktor/http/Url;Lio/ktor/client/plugins/cache/storage/CachedResponseData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UnlimitedStorage implements CacheStorage {
    private final ConcurrentMap<Url, Set<CachedResponseData>> store = new ConcurrentMap<>(0, 1, null);

    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    public Object store(Url url, CachedResponseData data, Continuation<? super Unit> continuation) {
        Set cache = this.store.computeIfAbsent((ConcurrentMap<Url, Set<CachedResponseData>>) url, (Function0<? extends Set>) new Function0<Set<CachedResponseData>>() { // from class: io.ktor.client.plugins.cache.storage.UnlimitedStorage$store$cache$1
            @Override // kotlin.jvm.functions.Function0
            public final Set<CachedResponseData> invoke() {
                return ConcurrentSetKt.ConcurrentSet();
            }
        });
        if (!cache.add(data)) {
            cache.remove(data);
            cache.add(data);
        }
        return Unit.INSTANCE;
    }

    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    public Object find(Url url, Map<String, String> map, Continuation<? super CachedResponseData> continuation) {
        Set data = this.store.computeIfAbsent((ConcurrentMap<Url, Set<CachedResponseData>>) url, (Function0<? extends Set>) new Function0<Set<CachedResponseData>>() { // from class: io.ktor.client.plugins.cache.storage.UnlimitedStorage$find$data$1
            @Override // kotlin.jvm.functions.Function0
            public final Set<CachedResponseData> invoke() {
                return ConcurrentSetKt.ConcurrentSet();
            }
        });
        for (Object obj : data) {
            CachedResponseData it = (CachedResponseData) obj;
            boolean z = true;
            if (!map.isEmpty()) {
                Iterator<Map.Entry<String, String>> it2 = map.entrySet().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Map.Entry element$iv = it2.next();
                    String key = element$iv.getKey();
                    String value = element$iv.getValue();
                    if (!Intrinsics.areEqual(it.getVaryKeys().get(key), value)) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                return obj;
            }
        }
        return null;
    }

    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    public Object findAll(Url url, Continuation<? super Set<CachedResponseData>> continuation) {
        Set<CachedResponseData> set = this.store.get(url);
        return set == null ? SetsKt.emptySet() : set;
    }
}
