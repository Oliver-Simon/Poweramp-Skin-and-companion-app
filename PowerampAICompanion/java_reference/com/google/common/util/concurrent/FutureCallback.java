package com.google.common.util.concurrent;

@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public interface FutureCallback<V> {
    void onFailure(Throwable th);

    void onSuccess(@ParametricNullness V v);
}
