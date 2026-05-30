package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DataCacheGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    private File cacheFile;
    private final List<Key> cacheKeys;
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int modelLoaderIndex;
    private List<ModelLoader<File, ?>> modelLoaders;
    private int sourceIdIndex;
    private Key sourceKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataCacheGenerator(DecodeHelper<?> helper, DataFetcherGenerator.FetcherReadyCallback cb) {
        this(helper.getCacheKeys(), helper, cb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataCacheGenerator(List<Key> cacheKeys, DecodeHelper<?> helper, DataFetcherGenerator.FetcherReadyCallback cb) {
        this.sourceIdIndex = -1;
        this.cacheKeys = cacheKeys;
        this.helper = helper;
        this.cb = cb;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0014, code lost:
    
        if (r0 != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
    
        if (hasNextModelLoader() == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001c, code lost:
    
        r1 = r6.modelLoaders;
        r2 = r6.modelLoaderIndex;
        r6.modelLoaderIndex = r2 + 1;
        r1 = r1.get(r2);
        r6.loadData = r1.buildLoadData(r6.cacheFile, r6.helper.getWidth(), r6.helper.getHeight(), r6.helper.getOptions());
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0046, code lost:
    
        if (r6.loadData == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0056, code lost:
    
        if (r6.helper.hasLoadPath(r6.loadData.fetcher.getDataClass()) == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0058, code lost:
    
        r0 = true;
        r6.loadData.fetcher.loadData(r6.helper.getPriority(), r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006b, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0010, code lost:
    
        r6.loadData = null;
        r0 = false;
     */
    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean startNext() {
        /*
            r6 = this;
            java.lang.String r0 = "DataCacheGenerator.startNext"
            com.bumptech.glide.util.pool.GlideTrace.beginSection(r0)
        L5:
            java.util.List<com.bumptech.glide.load.model.ModelLoader<java.io.File, ?>> r0 = r6.modelLoaders     // Catch: java.lang.Throwable -> Lb7
            if (r0 == 0) goto L6c
            boolean r0 = r6.hasNextModelLoader()     // Catch: java.lang.Throwable -> Lb7
            if (r0 != 0) goto L10
            goto L6c
        L10:
            r0 = 0
            r6.loadData = r0     // Catch: java.lang.Throwable -> Lb7
            r0 = 0
        L14:
            if (r0 != 0) goto L67
            boolean r1 = r6.hasNextModelLoader()     // Catch: java.lang.Throwable -> Lb7
            if (r1 == 0) goto L67
            java.util.List<com.bumptech.glide.load.model.ModelLoader<java.io.File, ?>> r1 = r6.modelLoaders     // Catch: java.lang.Throwable -> Lb7
            int r2 = r6.modelLoaderIndex     // Catch: java.lang.Throwable -> Lb7
            int r3 = r2 + 1
            r6.modelLoaderIndex = r3     // Catch: java.lang.Throwable -> Lb7
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.model.ModelLoader r1 = (com.bumptech.glide.load.model.ModelLoader) r1     // Catch: java.lang.Throwable -> Lb7
            java.io.File r2 = r6.cacheFile     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.engine.DecodeHelper<?> r3 = r6.helper     // Catch: java.lang.Throwable -> Lb7
            int r3 = r3.getWidth()     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.engine.DecodeHelper<?> r4 = r6.helper     // Catch: java.lang.Throwable -> Lb7
            int r4 = r4.getHeight()     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.engine.DecodeHelper<?> r5 = r6.helper     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.Options r5 = r5.getOptions()     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.model.ModelLoader$LoadData r2 = r1.buildLoadData(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> Lb7
            r6.loadData = r2     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r2 = r6.loadData     // Catch: java.lang.Throwable -> Lb7
            if (r2 == 0) goto L66
            com.bumptech.glide.load.engine.DecodeHelper<?> r2 = r6.helper     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r3 = r6.loadData     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.data.DataFetcher<Data> r3 = r3.fetcher     // Catch: java.lang.Throwable -> Lb7
            java.lang.Class r3 = r3.getDataClass()     // Catch: java.lang.Throwable -> Lb7
            boolean r2 = r2.hasLoadPath(r3)     // Catch: java.lang.Throwable -> Lb7
            if (r2 == 0) goto L66
            r0 = 1
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r2 = r6.loadData     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.data.DataFetcher<Data> r2 = r2.fetcher     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.engine.DecodeHelper<?> r3 = r6.helper     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.Priority r3 = r3.getPriority()     // Catch: java.lang.Throwable -> Lb7
            r2.loadData(r3, r6)     // Catch: java.lang.Throwable -> Lb7
        L66:
            goto L14
        L67:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r0
        L6c:
            int r0 = r6.sourceIdIndex     // Catch: java.lang.Throwable -> Lb7
            int r0 = r0 + 1
            r6.sourceIdIndex = r0     // Catch: java.lang.Throwable -> Lb7
            int r0 = r6.sourceIdIndex     // Catch: java.lang.Throwable -> Lb7
            java.util.List<com.bumptech.glide.load.Key> r1 = r6.cacheKeys     // Catch: java.lang.Throwable -> Lb7
            int r1 = r1.size()     // Catch: java.lang.Throwable -> Lb7
            r2 = 0
            if (r0 < r1) goto L82
        L7e:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r2
        L82:
            java.util.List<com.bumptech.glide.load.Key> r0 = r6.cacheKeys     // Catch: java.lang.Throwable -> Lb7
            int r1 = r6.sourceIdIndex     // Catch: java.lang.Throwable -> Lb7
            java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.Key r0 = (com.bumptech.glide.load.Key) r0     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.engine.DataCacheKey r1 = new com.bumptech.glide.load.engine.DataCacheKey     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.engine.DecodeHelper<?> r3 = r6.helper     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.Key r3 = r3.getSignature()     // Catch: java.lang.Throwable -> Lb7
            r1.<init>(r0, r3)     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.engine.DecodeHelper<?> r3 = r6.helper     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.engine.cache.DiskCache r3 = r3.getDiskCache()     // Catch: java.lang.Throwable -> Lb7
            java.io.File r3 = r3.get(r1)     // Catch: java.lang.Throwable -> Lb7
            r6.cacheFile = r3     // Catch: java.lang.Throwable -> Lb7
            java.io.File r3 = r6.cacheFile     // Catch: java.lang.Throwable -> Lb7
            if (r3 == 0) goto Lb5
            r6.sourceKey = r0     // Catch: java.lang.Throwable -> Lb7
            com.bumptech.glide.load.engine.DecodeHelper<?> r3 = r6.helper     // Catch: java.lang.Throwable -> Lb7
            java.io.File r4 = r6.cacheFile     // Catch: java.lang.Throwable -> Lb7
            java.util.List r3 = r3.getModelLoaders(r4)     // Catch: java.lang.Throwable -> Lb7
            r6.modelLoaders = r3     // Catch: java.lang.Throwable -> Lb7
            r6.modelLoaderIndex = r2     // Catch: java.lang.Throwable -> Lb7
        Lb5:
            goto L5
        Lb7:
            r0 = move-exception
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.DataCacheGenerator.startNext():boolean");
    }

    private boolean hasNextModelLoader() {
        return this.modelLoaderIndex < this.modelLoaders.size();
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> local = this.loadData;
        if (local != null) {
            local.fetcher.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onDataReady(Object data) {
        this.cb.onDataFetcherReady(this.sourceKey, data, this.loadData.fetcher, DataSource.DATA_DISK_CACHE, this.sourceKey);
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(Exception e) {
        this.cb.onDataFetcherFailed(this.sourceKey, e, this.loadData.fetcher, DataSource.DATA_DISK_CACHE);
    }
}
