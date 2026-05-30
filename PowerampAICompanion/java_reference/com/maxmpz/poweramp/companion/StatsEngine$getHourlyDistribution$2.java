package com.maxmpz.poweramp.companion;

import com.maxmpz.poweramp.companion.StatsEngine;
import java.util.Iterator;
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

/* compiled from: StatsEngine.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0015\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.StatsEngine$getHourlyDistribution$2", f = "StatsEngine.kt", i = {0}, l = {129}, m = "invokeSuspend", n = {"result"}, s = {"L$0"})
/* loaded from: classes7.dex */
final class StatsEngine$getHourlyDistribution$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super int[]>, Object> {
    final /* synthetic */ StatsEngine.TimeRange $range;
    Object L$0;
    int label;
    final /* synthetic */ StatsEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatsEngine$getHourlyDistribution$2(StatsEngine statsEngine, StatsEngine.TimeRange timeRange, Continuation<? super StatsEngine$getHourlyDistribution$2> continuation) {
        super(2, continuation);
        this.this$0 = statsEngine;
        this.$range = timeRange;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StatsEngine$getHourlyDistribution$2(this.this$0, this.$range, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super int[]> continuation) {
        return ((StatsEngine$getHourlyDistribution$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int[] result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                result = new int[24];
                this.L$0 = result;
                this.label = 1;
                Object mergedScrobbles = this.this$0.getMergedScrobbles(this.$range, this);
                if (mergedScrobbles != coroutine_suspended) {
                    $result = mergedScrobbles;
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                int[] result2 = (int[]) this.L$0;
                ResultKt.throwOnFailure($result);
                result = result2;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Iterable scrobbles = (List) $result;
        Iterator it = scrobbles.iterator();
        while (it.hasNext()) {
            int hour = ((Scrobble) it.next()).getHour() % 24;
            result[hour] = result[hour] + 1;
        }
        return result;
    }
}
