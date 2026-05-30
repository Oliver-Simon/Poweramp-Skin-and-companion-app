package io.ktor.client.plugins.cache.storage;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.plugins.cache.HttpCacheEntry;
import io.ktor.http.Url;
import io.ktor.util.collections.ConcurrentMap;
import io.ktor.util.collections.ConcurrentSetKt;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UnlimitedCacheStorage.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u00052\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0018\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0007H\u0016R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lio/ktor/client/plugins/cache/storage/UnlimitedCacheStorage;", "Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", "()V", "store", "Lio/ktor/util/collections/ConcurrentMap;", "Lio/ktor/http/Url;", "", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "find", TrackProviderConsts.COLUMN_URL, "varyKeys", "", "", "findByUrl", "", "", "value", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UnlimitedCacheStorage extends HttpCacheStorage {
    private final ConcurrentMap<Url, Set<HttpCacheEntry>> store = new ConcurrentMap<>(0, 1, null);

    @Override // io.ktor.client.plugins.cache.storage.HttpCacheStorage
    public void store(Url url, HttpCacheEntry value) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(value, "value");
        Set data = this.store.computeIfAbsent((ConcurrentMap<Url, Set<HttpCacheEntry>>) url, (Function0<? extends Set>) new Function0<Set<HttpCacheEntry>>() { // from class: io.ktor.client.plugins.cache.storage.UnlimitedCacheStorage$store$data$1
            @Override // kotlin.jvm.functions.Function0
            public final Set<HttpCacheEntry> invoke() {
                return ConcurrentSetKt.ConcurrentSet();
            }
        });
        if (!data.add(value)) {
            data.remove(value);
            data.add(value);
        }
    }

    @Override // io.ktor.client.plugins.cache.storage.HttpCacheStorage
    public HttpCacheEntry find(Url url, Map<String, String> varyKeys) {
        Object obj;
        boolean z;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(varyKeys, "varyKeys");
        Set data = this.store.computeIfAbsent((ConcurrentMap<Url, Set<HttpCacheEntry>>) url, (Function0<? extends Set>) new Function0<Set<HttpCacheEntry>>() { // from class: io.ktor.client.plugins.cache.storage.UnlimitedCacheStorage$find$data$1
            @Override // kotlin.jvm.functions.Function0
            public final Set<HttpCacheEntry> invoke() {
                return ConcurrentSetKt.ConcurrentSet();
            }
        });
        Iterator<T> it = data.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            HttpCacheEntry it2 = (HttpCacheEntry) obj;
            if (!varyKeys.isEmpty()) {
                Iterator<Map.Entry<String, String>> it3 = varyKeys.entrySet().iterator();
                while (true) {
                    if (it3.hasNext()) {
                        Map.Entry element$iv = it3.next();
                        String key = element$iv.getKey();
                        String value = element$iv.getValue();
                        if (!Intrinsics.areEqual(it2.getVaryKeys().get(key), value)) {
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
        }
        return (HttpCacheEntry) obj;
    }

    @Override // io.ktor.client.plugins.cache.storage.HttpCacheStorage
    public Set<HttpCacheEntry> findByUrl(Url url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Set<HttpCacheEntry> set = this.store.get(url);
        return set == null ? SetsKt.emptySet() : set;
    }
}
