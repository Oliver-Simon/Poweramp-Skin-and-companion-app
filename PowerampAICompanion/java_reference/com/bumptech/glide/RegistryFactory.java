package com.bumptech.glide;

import android.content.Context;
import androidx.tracing.Trace;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.util.GlideSuppliers;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class RegistryFactory {
    private RegistryFactory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GlideSuppliers.GlideSupplier<Registry> lazilyCreateAndInitializeRegistry(final Glide glide, final List<GlideModule> manifestModules, final AppGlideModule annotationGeneratedModule) {
        return new GlideSuppliers.GlideSupplier<Registry>() { // from class: com.bumptech.glide.RegistryFactory.1
            private boolean isInitializing;

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.util.GlideSuppliers.GlideSupplier
            public Registry get() {
                if (this.isInitializing) {
                    throw new IllegalStateException("Recursive Registry initialization! In your AppGlideModule and LibraryGlideModules, Make sure you're using the provided Registry rather calling glide.getRegistry()!");
                }
                Trace.beginSection("Glide registry");
                this.isInitializing = true;
                try {
                    return RegistryFactory.createAndInitRegistry(Glide.this, manifestModules, annotationGeneratedModule);
                } finally {
                    this.isInitializing = false;
                    Trace.endSection();
                }
            }
        };
    }

    static Registry createAndInitRegistry(Glide glide, List<GlideModule> manifestModules, AppGlideModule annotationGeneratedModule) {
        BitmapPool bitmapPool = glide.getBitmapPool();
        ArrayPool arrayPool = glide.getArrayPool();
        Context context = glide.getGlideContext().getApplicationContext();
        GlideExperiments experiments = glide.getGlideContext().getExperiments();
        Registry registry = new Registry();
        initializeDefaults(context, registry, bitmapPool, arrayPool, experiments);
        initializeModules(context, glide, registry, manifestModules, annotationGeneratedModule);
        return registry;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0314  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void initializeDefaults(android.content.Context r26, com.bumptech.glide.Registry r27, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r28, com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r29, com.bumptech.glide.GlideExperiments r30) {
        /*
            Method dump skipped, instructions count: 1037
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RegistryFactory.initializeDefaults(android.content.Context, com.bumptech.glide.Registry, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool, com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool, com.bumptech.glide.GlideExperiments):void");
    }

    private static void initializeModules(Context context, Glide glide, Registry registry, List<GlideModule> manifestModules, AppGlideModule annotationGeneratedModule) {
        for (GlideModule module : manifestModules) {
            try {
                module.registerComponents(context, glide, registry);
            } catch (AbstractMethodError e) {
                throw new IllegalStateException("Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: " + module.getClass().getName(), e);
            }
        }
        if (annotationGeneratedModule != null) {
            annotationGeneratedModule.registerComponents(context, glide, registry);
        }
    }
}
