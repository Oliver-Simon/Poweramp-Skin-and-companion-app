package io.ktor.client.plugins.contentnegotiation;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ContentNegotiation.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.contentnegotiation.ContentNegotiation", f = "ContentNegotiation.kt", i = {0, 0, 0, 0, 0}, l = {180}, m = "convertRequest$ktor_client_content_negotiation", n = {"request", "body", "contentType", "matchingRegistrations", "registration"}, s = {"L$0", "L$1", "L$2", "L$3", "L$5"})
/* loaded from: classes9.dex */
public final class ContentNegotiation$convertRequest$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ContentNegotiation this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentNegotiation$convertRequest$1(ContentNegotiation contentNegotiation, Continuation<? super ContentNegotiation$convertRequest$1> continuation) {
        super(continuation);
        this.this$0 = contentNegotiation;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.convertRequest$ktor_client_content_negotiation(null, null, this);
    }
}
