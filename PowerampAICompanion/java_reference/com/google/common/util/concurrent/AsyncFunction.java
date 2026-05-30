package com.google.common.util.concurrent;

@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public interface AsyncFunction<I, O> {
    ListenableFuture<O> apply(@ParametricNullness I i) throws Exception;
}
