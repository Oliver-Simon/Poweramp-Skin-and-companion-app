package com.maxmpz.poweramp.companion;

import com.maxmpz.poweramp.companion.StatsEngine;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RecommendationEngine.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/maxmpz/poweramp/companion/Scrobble;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.RecommendationEngine$runLocalHeuristics$scrobbles$1", f = "RecommendationEngine.kt", i = {}, l = {495}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class RecommendationEngine$runLocalHeuristics$scrobbles$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Scrobble>>, Object> {
    final /* synthetic */ StatsEngine $statsEngine;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecommendationEngine$runLocalHeuristics$scrobbles$1(StatsEngine statsEngine, Continuation<? super RecommendationEngine$runLocalHeuristics$scrobbles$1> continuation) {
        super(2, continuation);
        this.$statsEngine = statsEngine;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecommendationEngine$runLocalHeuristics$scrobbles$1(this.$statsEngine, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Scrobble>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<Scrobble>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Scrobble>> continuation) {
        return ((RecommendationEngine$runLocalHeuristics$scrobbles$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object mergedScrobbles = this.$statsEngine.getMergedScrobbles(StatsEngine.TimeRange.ALL_TIME, this);
                if (mergedScrobbles == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return mergedScrobbles;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
