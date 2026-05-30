package com.google.ai.client.generativeai.common;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.UtilsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: APIController.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.common.APIController$applyHeaderProvider$2", f = "APIController.kt", i = {}, l = {150}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class APIController$applyHeaderProvider$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpRequestBuilder $this_applyHeaderProvider;
    int label;
    final /* synthetic */ APIController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public APIController$applyHeaderProvider$2(APIController aPIController, HttpRequestBuilder httpRequestBuilder, Continuation<? super APIController$applyHeaderProvider$2> continuation) {
        super(2, continuation);
        this.this$0 = aPIController;
        this.$this_applyHeaderProvider = httpRequestBuilder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new APIController$applyHeaderProvider$2(this.this$0, this.$this_applyHeaderProvider, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((APIController$applyHeaderProvider$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        APIController$applyHeaderProvider$2 aPIController$applyHeaderProvider$2;
        HeaderProvider headerProvider;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                aPIController$applyHeaderProvider$2 = this;
                headerProvider = aPIController$applyHeaderProvider$2.this$0.headerProvider;
                aPIController$applyHeaderProvider$2.label = 1;
                Object generateHeaders = headerProvider.generateHeaders(aPIController$applyHeaderProvider$2);
                if (generateHeaders == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = generateHeaders;
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                aPIController$applyHeaderProvider$2 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        for (Map.Entry entry : ((Map) $result).entrySet()) {
            String tag = (String) entry.getKey();
            String value = (String) entry.getValue();
            UtilsKt.header(aPIController$applyHeaderProvider$2.$this_applyHeaderProvider, tag, value);
        }
        return Unit.INSTANCE;
    }
}
