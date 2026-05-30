package io.ktor.client.plugins.contentnegotiation;

import com.maxmpz.poweramp.player.PowerampAPI;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ContentNegotiation.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.contentnegotiation.ContentNegotiation", f = "ContentNegotiation.kt", i = {0}, l = {PowerampAPI.Cats.COMPOSERS_ID_ALBUMS}, m = "convertResponse$ktor_client_content_negotiation", n = {"requestUrl"}, s = {"L$0"})
/* loaded from: classes9.dex */
public final class ContentNegotiation$convertResponse$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ContentNegotiation this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentNegotiation$convertResponse$1(ContentNegotiation contentNegotiation, Continuation<? super ContentNegotiation$convertResponse$1> continuation) {
        super(continuation);
        this.this$0 = contentNegotiation;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.convertResponse$ktor_client_content_negotiation(null, null, null, null, null, this);
    }
}
