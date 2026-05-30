package com.maxmpz.poweramp.companion;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RecommendationEngine.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlin/Pair;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.RecommendationEngine$runHybridPlaylisting$topTracks$1", f = "RecommendationEngine.kt", i = {}, l = {TypedValues.CycleType.TYPE_ALPHA}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class RecommendationEngine$runHybridPlaylisting$topTracks$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Pair<? extends String, ? extends String>>>, Object> {
    final /* synthetic */ StatsEngine $statsEngine;
    final /* synthetic */ String $tag;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecommendationEngine$runHybridPlaylisting$topTracks$1(StatsEngine statsEngine, String str, Continuation<? super RecommendationEngine$runHybridPlaylisting$topTracks$1> continuation) {
        super(2, continuation);
        this.$statsEngine = statsEngine;
        this.$tag = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecommendationEngine$runHybridPlaylisting$topTracks$1(this.$statsEngine, this.$tag, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Pair<? extends String, ? extends String>>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<Pair<String, String>>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Pair<String, String>>> continuation) {
        return ((RecommendationEngine$runHybridPlaylisting$topTracks$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object fetchTopTracksForTag = this.$statsEngine.fetchTopTracksForTag(this.$tag, this);
                return fetchTopTracksForTag == coroutine_suspended ? coroutine_suspended : fetchTopTracksForTag;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
