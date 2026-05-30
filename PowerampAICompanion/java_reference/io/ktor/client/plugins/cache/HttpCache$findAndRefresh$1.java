package io.ktor.client.plugins.cache;

import com.google.android.material.card.MaterialCardViewHelper;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpCache.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.cache.HttpCache", f = "HttpCache.kt", i = {0, 0, 0, 0, 0, 1, 1, 1}, l = {298, MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION}, m = "findAndRefresh", n = {"this", "request", "response", "storage", "varyKeysFrom304", "request", "response", "cache"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2"})
/* loaded from: classes9.dex */
public final class HttpCache$findAndRefresh$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpCache this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCache$findAndRefresh$1(HttpCache httpCache, Continuation<? super HttpCache$findAndRefresh$1> continuation) {
        super(continuation);
        this.this$0 = httpCache;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object findAndRefresh;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        findAndRefresh = this.this$0.findAndRefresh(null, null, this);
        return findAndRefresh;
    }
}
