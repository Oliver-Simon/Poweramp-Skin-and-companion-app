package com.maxmpz.poweramp.companion;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
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
/* compiled from: RecommendationEngine.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.RecommendationEngine$extractIntentWithGemini$responseText$response$1", f = "RecommendationEngine.kt", i = {}, l = {120}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class RecommendationEngine$extractIntentWithGemini$responseText$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super GenerateContentResponse>, Object> {
    final /* synthetic */ GenerativeModel $generativeModel;
    final /* synthetic */ Content $inputContent;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecommendationEngine$extractIntentWithGemini$responseText$response$1(GenerativeModel generativeModel, Content content, Continuation<? super RecommendationEngine$extractIntentWithGemini$responseText$response$1> continuation) {
        super(2, continuation);
        this.$generativeModel = generativeModel;
        this.$inputContent = content;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecommendationEngine$extractIntentWithGemini$responseText$response$1(this.$generativeModel, this.$inputContent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super GenerateContentResponse> continuation) {
        return ((RecommendationEngine$extractIntentWithGemini$responseText$response$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                GenerativeModel generativeModel = this.$generativeModel;
                Content[] contentArr = {this.$inputContent};
                this.label = 1;
                Object generateContent = generativeModel.generateContent(contentArr, this);
                return generateContent == coroutine_suspended ? coroutine_suspended : generateContent;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
