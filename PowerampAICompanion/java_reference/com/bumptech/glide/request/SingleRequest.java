package com.bumptech.glide.request;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.GlideTrace;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback {
    private static final String GLIDE_TAG = "Glide";
    private final TransitionFactory<? super R> animationFactory;
    private final Executor callbackExecutor;
    private final Context context;
    private int cookie;
    private volatile Engine engine;
    private Drawable errorDrawable;
    private Drawable fallbackDrawable;
    private final GlideContext glideContext;
    private int height;
    private boolean isCallingCallbacks;
    private Engine.LoadStatus loadStatus;
    private final Object model;
    private final int overrideHeight;
    private final int overrideWidth;
    private Drawable placeholderDrawable;
    private final Priority priority;
    private final RequestCoordinator requestCoordinator;
    private final List<RequestListener<R>> requestListeners;
    private final Object requestLock;
    private final BaseRequestOptions<?> requestOptions;
    private RuntimeException requestOrigin;
    private Resource<R> resource;
    private long startTime;
    private final StateVerifier stateVerifier;
    private Status status;
    private final String tag;
    private final Target<R> target;
    private final RequestListener<R> targetListener;
    private final Class<R> transcodeClass;
    private int width;
    private static final String TAG = "GlideRequest";
    private static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable(TAG, 2);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    public static <R> SingleRequest<R> obtain(Context context, GlideContext glideContext, Object requestLock, Object model, Class<R> transcodeClass, BaseRequestOptions<?> requestOptions, int overrideWidth, int overrideHeight, Priority priority, Target<R> target, RequestListener<R> targetListener, List<RequestListener<R>> requestListeners, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> animationFactory, Executor callbackExecutor) {
        return new SingleRequest<>(context, glideContext, requestLock, model, transcodeClass, requestOptions, overrideWidth, overrideHeight, priority, target, targetListener, requestListeners, requestCoordinator, engine, animationFactory, callbackExecutor);
    }

    private SingleRequest(Context context, GlideContext glideContext, Object requestLock, Object model, Class<R> transcodeClass, BaseRequestOptions<?> requestOptions, int overrideWidth, int overrideHeight, Priority priority, Target<R> target, RequestListener<R> targetListener, List<RequestListener<R>> requestListeners, RequestCoordinator requestCoordinator, Engine engine, TransitionFactory<? super R> animationFactory, Executor callbackExecutor) {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(super.hashCode()) : null;
        this.stateVerifier = StateVerifier.newInstance();
        this.requestLock = requestLock;
        this.context = context;
        this.glideContext = glideContext;
        this.model = model;
        this.transcodeClass = transcodeClass;
        this.requestOptions = requestOptions;
        this.overrideWidth = overrideWidth;
        this.overrideHeight = overrideHeight;
        this.priority = priority;
        this.target = target;
        this.targetListener = targetListener;
        this.requestListeners = requestListeners;
        this.requestCoordinator = requestCoordinator;
        this.engine = engine;
        this.animationFactory = animationFactory;
        this.callbackExecutor = callbackExecutor;
        this.status = Status.PENDING;
        if (this.requestOrigin == null && glideContext.getExperiments().isEnabled(GlideBuilder.LogRequestOrigins.class)) {
            this.requestOrigin = new RuntimeException("Glide request origin trace");
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void begin() {
        synchronized (this.requestLock) {
            assertNotCallingCallbacks();
            this.stateVerifier.throwIfRecycled();
            this.startTime = LogTime.getLogTime();
            if (this.model == null) {
                if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                    this.width = this.overrideWidth;
                    this.height = this.overrideHeight;
                }
                int logLevel = getFallbackDrawable() == null ? 5 : 3;
                onLoadFailed(new GlideException("Received null model"), logLevel);
                return;
            }
            if (this.status == Status.RUNNING) {
                throw new IllegalArgumentException("Cannot restart a running request");
            }
            if (this.status == Status.COMPLETE) {
                onResourceReady(this.resource, DataSource.MEMORY_CACHE, false);
                return;
            }
            experimentalNotifyRequestStarted(this.model);
            this.cookie = GlideTrace.beginSectionAsync(TAG);
            this.status = Status.WAITING_FOR_SIZE;
            if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                onSizeReady(this.overrideWidth, this.overrideHeight);
            } else {
                this.target.getSize(this);
            }
            if ((this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE) && canNotifyStatusChanged()) {
                this.target.onLoadStarted(getPlaceholderDrawable());
            }
            if (IS_VERBOSE_LOGGABLE) {
                logV("finished run method in " + LogTime.getElapsedMillis(this.startTime));
            }
        }
    }

    private void experimentalNotifyRequestStarted(Object model) {
        if (this.requestListeners == null) {
            return;
        }
        for (RequestListener<R> requestListener : this.requestListeners) {
            if (requestListener instanceof ExperimentalRequestListener) {
                ((ExperimentalRequestListener) requestListener).onRequestStarted(model);
            }
        }
    }

    private void cancel() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.target.removeCallback(this);
        if (this.loadStatus != null) {
            this.loadStatus.cancel();
            this.loadStatus = null;
        }
    }

    private void assertNotCallingCallbacks() {
        if (this.isCallingCallbacks) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void clear() {
        Resource<R> toRelease = null;
        synchronized (this.requestLock) {
            assertNotCallingCallbacks();
            this.stateVerifier.throwIfRecycled();
            if (this.status == Status.CLEARED) {
                return;
            }
            cancel();
            if (this.resource != null) {
                toRelease = this.resource;
                this.resource = null;
            }
            if (canNotifyCleared()) {
                this.target.onLoadCleared(getPlaceholderDrawable());
            }
            GlideTrace.endSectionAsync(TAG, this.cookie);
            this.status = Status.CLEARED;
            if (toRelease != null) {
                this.engine.release(toRelease);
            }
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void pause() {
        synchronized (this.requestLock) {
            if (isRunning()) {
                clear();
            }
        }
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isRunning() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isComplete() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.COMPLETE;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isCleared() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.CLEARED;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isAnyResourceSet() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.COMPLETE;
        }
        return z;
    }

    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null) {
            this.errorDrawable = this.requestOptions.getErrorPlaceholder();
            if (this.errorDrawable == null && this.requestOptions.getErrorId() > 0) {
                this.errorDrawable = loadDrawable(this.requestOptions.getErrorId());
            }
        }
        return this.errorDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null) {
            this.placeholderDrawable = this.requestOptions.getPlaceholderDrawable();
            if (this.placeholderDrawable == null && this.requestOptions.getPlaceholderId() > 0) {
                this.placeholderDrawable = loadDrawable(this.requestOptions.getPlaceholderId());
            }
        }
        return this.placeholderDrawable;
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null) {
            this.fallbackDrawable = this.requestOptions.getFallbackDrawable();
            if (this.fallbackDrawable == null && this.requestOptions.getFallbackId() > 0) {
                this.fallbackDrawable = loadDrawable(this.requestOptions.getFallbackId());
            }
        }
        return this.fallbackDrawable;
    }

    private Drawable loadDrawable(int resourceId) {
        Resources.Theme theme = this.requestOptions.getTheme() != null ? this.requestOptions.getTheme() : this.context.getTheme();
        return DrawableDecoderCompat.getDrawable(this.context, resourceId, theme);
    }

    private void setErrorPlaceholder() {
        if (!canNotifyStatusChanged()) {
            return;
        }
        Drawable error = null;
        if (this.model == null) {
            error = getFallbackDrawable();
        }
        if (error == null) {
            error = getErrorDrawable();
        }
        if (error == null) {
            error = getPlaceholderDrawable();
        }
        this.target.onLoadFailed(error);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.request.target.SizeReadyCallback
    public void onSizeReady(int width, int height) {
        SingleRequest<R> singleRequest = this;
        singleRequest.stateVerifier.throwIfRecycled();
        Object obj = singleRequest.requestLock;
        synchronized (obj) {
            try {
                try {
                    if (IS_VERBOSE_LOGGABLE) {
                        singleRequest.logV("Got onSizeReady in " + LogTime.getElapsedMillis(singleRequest.startTime));
                    }
                    if (singleRequest.status == Status.WAITING_FOR_SIZE) {
                        singleRequest.status = Status.RUNNING;
                        float sizeMultiplier = singleRequest.requestOptions.getSizeMultiplier();
                        singleRequest.width = maybeApplySizeMultiplier(width, sizeMultiplier);
                        singleRequest.height = maybeApplySizeMultiplier(height, sizeMultiplier);
                        if (IS_VERBOSE_LOGGABLE) {
                            singleRequest.logV("finished setup for calling load in " + LogTime.getElapsedMillis(singleRequest.startTime));
                        }
                        try {
                            Engine engine = singleRequest.engine;
                            GlideContext glideContext = singleRequest.glideContext;
                            Object obj2 = singleRequest.model;
                            Key signature = singleRequest.requestOptions.getSignature();
                            try {
                                int i = singleRequest.width;
                                try {
                                    int i2 = singleRequest.height;
                                    Class<?> resourceClass = singleRequest.requestOptions.getResourceClass();
                                    try {
                                        Class<R> cls = singleRequest.transcodeClass;
                                        try {
                                            Priority priority = singleRequest.priority;
                                            DiskCacheStrategy diskCacheStrategy = singleRequest.requestOptions.getDiskCacheStrategy();
                                            Map<Class<?>, Transformation<?>> transformations = singleRequest.requestOptions.getTransformations();
                                            boolean isTransformationRequired = singleRequest.requestOptions.isTransformationRequired();
                                            boolean isScaleOnlyOrNoTransform = singleRequest.requestOptions.isScaleOnlyOrNoTransform();
                                            Options options = singleRequest.requestOptions.getOptions();
                                            boolean isMemoryCacheable = singleRequest.requestOptions.isMemoryCacheable();
                                            boolean useUnlimitedSourceGeneratorsPool = singleRequest.requestOptions.getUseUnlimitedSourceGeneratorsPool();
                                            boolean useAnimationPool = singleRequest.requestOptions.getUseAnimationPool();
                                            boolean onlyRetrieveFromCache = singleRequest.requestOptions.getOnlyRetrieveFromCache();
                                            Executor executor = singleRequest.callbackExecutor;
                                            singleRequest = obj;
                                            try {
                                                singleRequest.loadStatus = engine.load(glideContext, obj2, signature, i, i2, resourceClass, cls, priority, diskCacheStrategy, transformations, isTransformationRequired, isScaleOnlyOrNoTransform, options, isMemoryCacheable, useUnlimitedSourceGeneratorsPool, useAnimationPool, onlyRetrieveFromCache, singleRequest, executor);
                                                if (singleRequest.status != Status.RUNNING) {
                                                    singleRequest.loadStatus = null;
                                                }
                                                if (IS_VERBOSE_LOGGABLE) {
                                                    singleRequest.logV("finished onSizeReady in " + LogTime.getElapsedMillis(singleRequest.startTime));
                                                }
                                            } catch (Throwable th) {
                                                th = th;
                                                throw th;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            singleRequest = obj;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        singleRequest = obj;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    singleRequest = obj;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                singleRequest = obj;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            singleRequest = obj;
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                    singleRequest = obj;
                }
            } catch (Throwable th8) {
                th = th8;
            }
        }
    }

    private static int maybeApplySizeMultiplier(int size, float sizeMultiplier) {
        return size == Integer.MIN_VALUE ? size : Math.round(size * sizeMultiplier);
    }

    private boolean canSetResource() {
        return this.requestCoordinator == null || this.requestCoordinator.canSetImage(this);
    }

    private boolean canNotifyCleared() {
        return this.requestCoordinator == null || this.requestCoordinator.canNotifyCleared(this);
    }

    private boolean canNotifyStatusChanged() {
        return this.requestCoordinator == null || this.requestCoordinator.canNotifyStatusChanged(this);
    }

    private boolean isFirstReadyResource() {
        return this.requestCoordinator == null || !this.requestCoordinator.getRoot().isAnyResourceSet();
    }

    private void notifyRequestCoordinatorLoadSucceeded() {
        if (this.requestCoordinator != null) {
            this.requestCoordinator.onRequestSuccess(this);
        }
    }

    private void notifyRequestCoordinatorLoadFailed() {
        if (this.requestCoordinator != null) {
            this.requestCoordinator.onRequestFailed(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.request.ResourceCallback
    public void onResourceReady(Resource<?> resource, DataSource dataSource, boolean isLoadedFromAlternateCacheKey) {
        this.stateVerifier.throwIfRecycled();
        try {
            synchronized (this.requestLock) {
                this.loadStatus = null;
                if (resource == null) {
                    GlideException exception = new GlideException("Expected to receive a Resource<R> with an object of " + this.transcodeClass + " inside, but instead got null.");
                    onLoadFailed(exception);
                    if (toRelease != null) {
                        return;
                    } else {
                        return;
                    }
                }
                Object received = resource.get();
                if (received != null && this.transcodeClass.isAssignableFrom(received.getClass())) {
                    if (canSetResource()) {
                        onResourceReady(resource, received, dataSource, isLoadedFromAlternateCacheKey);
                        if (0 != 0) {
                            this.engine.release(null);
                            return;
                        }
                        return;
                    }
                    this.resource = null;
                    this.status = Status.COMPLETE;
                    GlideTrace.endSectionAsync(TAG, this.cookie);
                    if (resource != null) {
                        this.engine.release(resource);
                        return;
                    }
                    return;
                }
                this.resource = null;
                GlideException exception2 = new GlideException("Expected to receive an object of " + this.transcodeClass + " but instead got " + (received != null ? received.getClass() : "") + "{" + received + "} inside Resource{" + resource + "}." + (received != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data."));
                onLoadFailed(exception2);
                if (resource != null) {
                    this.engine.release(resource);
                }
            }
        } finally {
            if (0 != 0) {
                this.engine.release(null);
            }
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:10:0x008f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void onResourceReady(com.bumptech.glide.load.engine.Resource<R> r12, R r13, com.bumptech.glide.load.DataSource r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.onResourceReady(com.bumptech.glide.load.engine.Resource, java.lang.Object, com.bumptech.glide.load.DataSource, boolean):void");
    }

    @Override // com.bumptech.glide.request.ResourceCallback
    public void onLoadFailed(GlideException e) {
        onLoadFailed(e, 5);
    }

    @Override // com.bumptech.glide.request.ResourceCallback
    public Object getLock() {
        this.stateVerifier.throwIfRecycled();
        return this.requestLock;
    }

    private void onLoadFailed(GlideException e, int maxLogLevel) {
        this.stateVerifier.throwIfRecycled();
        synchronized (this.requestLock) {
            e.setOrigin(this.requestOrigin);
            int logLevel = this.glideContext.getLogLevel();
            if (logLevel <= maxLogLevel) {
                Log.w(GLIDE_TAG, "Load failed for [" + this.model + "] with dimensions [" + this.width + "x" + this.height + "]", e);
                if (logLevel <= 4) {
                    e.logRootCauses(GLIDE_TAG);
                }
            }
            this.loadStatus = null;
            this.status = Status.FAILED;
            notifyRequestCoordinatorLoadFailed();
            boolean z = true;
            this.isCallingCallbacks = true;
            boolean anyListenerHandledUpdatingTarget = false;
            try {
                if (this.requestListeners != null) {
                    for (RequestListener<R> listener : this.requestListeners) {
                        anyListenerHandledUpdatingTarget |= listener.onLoadFailed(e, this.model, this.target, isFirstReadyResource());
                    }
                }
                if (this.targetListener == null || !this.targetListener.onLoadFailed(e, this.model, this.target, isFirstReadyResource())) {
                    z = false;
                }
                if (!(z | anyListenerHandledUpdatingTarget)) {
                    setErrorPlaceholder();
                }
                this.isCallingCallbacks = false;
                GlideTrace.endSectionAsync(TAG, this.cookie);
            } catch (Throwable th) {
                this.isCallingCallbacks = false;
                throw th;
            }
        }
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isEquivalentTo(Request o) {
        int localOverrideWidth;
        int localOverrideHeight;
        Object localModel;
        Class<R> cls;
        BaseRequestOptions<?> localRequestOptions;
        Priority localPriority;
        int localListenerCount;
        int otherLocalOverrideWidth;
        int otherLocalOverrideHeight;
        Object otherLocalModel;
        Class<R> cls2;
        BaseRequestOptions<?> otherLocalRequestOptions;
        Priority otherLocalPriority;
        int otherLocalListenerCount;
        if (!(o instanceof SingleRequest)) {
            return false;
        }
        synchronized (this.requestLock) {
            localOverrideWidth = this.overrideWidth;
            localOverrideHeight = this.overrideHeight;
            localModel = this.model;
            cls = this.transcodeClass;
            localRequestOptions = this.requestOptions;
            localPriority = this.priority;
            localListenerCount = this.requestListeners != null ? this.requestListeners.size() : 0;
        }
        SingleRequest<?> other = (SingleRequest) o;
        synchronized (other.requestLock) {
            otherLocalOverrideWidth = other.overrideWidth;
            otherLocalOverrideHeight = other.overrideHeight;
            otherLocalModel = other.model;
            cls2 = other.transcodeClass;
            otherLocalRequestOptions = other.requestOptions;
            otherLocalPriority = other.priority;
            otherLocalListenerCount = other.requestListeners != null ? other.requestListeners.size() : 0;
        }
        return localOverrideWidth == otherLocalOverrideWidth && localOverrideHeight == otherLocalOverrideHeight && Util.bothModelsNullEquivalentOrEquals(localModel, otherLocalModel) && cls.equals(cls2) && Util.bothBaseRequestOptionsNullEquivalentOrEquals(localRequestOptions, otherLocalRequestOptions) && localPriority == otherLocalPriority && localListenerCount == otherLocalListenerCount;
    }

    private void logV(String message) {
        Log.v(TAG, message + " this: " + this.tag);
    }

    public String toString() {
        Object localModel;
        Class<R> cls;
        synchronized (this.requestLock) {
            localModel = this.model;
            cls = this.transcodeClass;
        }
        return super.toString() + "[model=" + localModel + ", transcodeClass=" + cls + "]";
    }
}
