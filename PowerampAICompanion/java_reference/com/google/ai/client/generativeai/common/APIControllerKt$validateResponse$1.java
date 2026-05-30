package com.google.ai.client.generativeai.common;

import com.maxmpz.poweramp.player.PowerampAPI;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: APIController.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.common.APIControllerKt", f = "APIController.kt", i = {}, l = {PowerampAPI.Cats.ARTISTS_ID_ALBUMS}, m = "validateResponse", n = {}, s = {})
/* loaded from: classes.dex */
public final class APIControllerKt$validateResponse$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public APIControllerKt$validateResponse$1(Continuation<? super APIControllerKt$validateResponse$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object validateResponse;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        validateResponse = APIControllerKt.validateResponse(null, this);
        return validateResponse;
    }
}
