package com.maxmpz.poweramp.companion;

import com.maxmpz.poweramp.companion.StatsEngine;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StatsEngine.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/maxmpz/poweramp/companion/Scrobble;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.StatsEngine$getMergedScrobbles$2", f = "StatsEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class StatsEngine$getMergedScrobbles$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Scrobble>>, Object> {
    final /* synthetic */ StatsEngine.TimeRange $range;
    int label;
    final /* synthetic */ StatsEngine this$0;

    /* compiled from: StatsEngine.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StatsEngine.TimeRange.values().length];
            try {
                iArr[StatsEngine.TimeRange.LAST_24_HOURS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[StatsEngine.TimeRange.LAST_7_DAYS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[StatsEngine.TimeRange.LAST_30_DAYS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[StatsEngine.TimeRange.LAST_60_DAYS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[StatsEngine.TimeRange.LAST_180_DAYS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[StatsEngine.TimeRange.LAST_365_DAYS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[StatsEngine.TimeRange.ALL_TIME.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatsEngine$getMergedScrobbles$2(StatsEngine.TimeRange timeRange, StatsEngine statsEngine, Continuation<? super StatsEngine$getMergedScrobbles$2> continuation) {
        super(2, continuation);
        this.$range = timeRange;
        this.this$0 = statsEngine;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StatsEngine$getMergedScrobbles$2(this.$range, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Scrobble>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<Scrobble>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Scrobble>> continuation) {
        return ((StatsEngine$getMergedScrobbles$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x031d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02fc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x01ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0361  */
    /* JADX WARN: Type inference failed for: r0v25, types: [T, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r33) {
        /*
            Method dump skipped, instructions count: 1152
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.StatsEngine$getMergedScrobbles$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
