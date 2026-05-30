package com.maxmpz.poweramp.companion;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.maxmpz.poweramp.companion.StatsEngine;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExploreFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.ExploreFragment$loadStats$3", f = "ExploreFragment.kt", i = {3}, l = {342, 343, 344, 349, 352}, m = "invokeSuspend", n = {"stats"}, s = {"L$0"})
/* loaded from: classes7.dex */
public final class ExploreFragment$loadStats$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $cacheKey;
    final /* synthetic */ boolean $hasData;
    final /* synthetic */ StatsEngine.TimeRange $range;
    final /* synthetic */ Integer $timeYear;
    final /* synthetic */ StatsEngine.ItemType $type;
    Object L$0;
    int label;
    final /* synthetic */ ExploreFragment this$0;

    /* compiled from: ExploreFragment.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StatsEngine.ItemType.values().length];
            try {
                iArr[StatsEngine.ItemType.TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[StatsEngine.ItemType.ARTIST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[StatsEngine.ItemType.ALBUM.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[StatsEngine.ItemType.TIME.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExploreFragment$loadStats$3(StatsEngine.ItemType itemType, ExploreFragment exploreFragment, StatsEngine.TimeRange timeRange, Integer num, String str, boolean z, Continuation<? super ExploreFragment$loadStats$3> continuation) {
        super(2, continuation);
        this.$type = itemType;
        this.this$0 = exploreFragment;
        this.$range = timeRange;
        this.$timeYear = num;
        this.$cacheKey = str;
        this.$hasData = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExploreFragment$loadStats$3(this.$type, this.this$0, this.$range, this.$timeYear, this.$cacheKey, this.$hasData, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExploreFragment$loadStats$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0009. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:13:0x011a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f0  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.ExploreFragment$loadStats$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ExploreFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.maxmpz.poweramp.companion.ExploreFragment$loadStats$3$1", f = "ExploreFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.maxmpz.poweramp.companion.ExploreFragment$loadStats$3$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $cacheKey;
        final /* synthetic */ boolean $hasData;
        final /* synthetic */ int[] $hourlyDist;
        final /* synthetic */ StatsEngine.TimeRange $range;
        final /* synthetic */ List<StatsEngine.StatItem> $stats;
        final /* synthetic */ StatsEngine.ItemType $type;
        int label;
        final /* synthetic */ ExploreFragment this$0;

        /* compiled from: ExploreFragment.kt */
        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        /* renamed from: com.maxmpz.poweramp.companion.ExploreFragment$loadStats$3$1$WhenMappings */
        /* loaded from: classes7.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[StatsEngine.ItemType.values().length];
                try {
                    iArr[StatsEngine.ItemType.TRACK.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[StatsEngine.ItemType.ARTIST.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[StatsEngine.ItemType.ALBUM.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ExploreFragment exploreFragment, StatsEngine.ItemType itemType, int[] iArr, StatsEngine.TimeRange timeRange, List<StatsEngine.StatItem> list, String str, boolean z, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = exploreFragment;
            this.$type = itemType;
            this.$hourlyDist = iArr;
            this.$range = timeRange;
            this.$stats = list;
            this.$cacheKey = str;
            this.$hasData = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$type, this.$hourlyDist, this.$range, this.$stats, this.$cacheKey, this.$hasData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CircularProgressIndicator circularProgressIndicator;
            ExploreViewModel viewModel;
            ExploreViewModel viewModel2;
            ExploreViewModel viewModel3;
            ExploreViewModel viewModel4;
            StatsAdapter statsAdapter;
            View view;
            View view2;
            RecyclerView recyclerView;
            View view3;
            RecyclerView recyclerView2;
            View view4;
            View view5;
            RecyclerView recyclerView3;
            View view6;
            View view7;
            View view8;
            HourlyBarChartView hourlyBarChartView;
            ExploreViewModel viewModel5;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    circularProgressIndicator = this.this$0.progressStats;
                    View view9 = null;
                    if (circularProgressIndicator == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("progressStats");
                        circularProgressIndicator = null;
                    }
                    circularProgressIndicator.setVisibility(8);
                    if (this.$type == StatsEngine.ItemType.TIME && this.$hourlyDist != null) {
                        viewModel5 = this.this$0.getViewModel();
                        viewModel5.getCachedTimeDist().put(this.$range, this.$hourlyDist);
                    } else if (!this.$stats.isEmpty()) {
                        switch (WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()]) {
                            case 1:
                                viewModel = this.this$0.getViewModel();
                                viewModel.getCachedTracks().put(this.$range, this.$stats);
                                break;
                            case 2:
                                viewModel2 = this.this$0.getViewModel();
                                viewModel2.getCachedArtists().put(this.$range, this.$stats);
                                break;
                            case 3:
                                viewModel3 = this.this$0.getViewModel();
                                viewModel3.getCachedAlbums().put(this.$range, this.$stats);
                                break;
                        }
                    }
                    viewModel4 = this.this$0.getViewModel();
                    viewModel4.getLastUpdateMap().put(this.$cacheKey, Boxing.boxLong(System.currentTimeMillis()));
                    if (this.$type == StatsEngine.ItemType.TIME) {
                        int[] iArr = this.$hourlyDist;
                        if (iArr != null) {
                            hourlyBarChartView = this.this$0.chartTimeDistribution;
                            if (hourlyBarChartView == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("chartTimeDistribution");
                                hourlyBarChartView = null;
                            }
                            hourlyBarChartView.setData(iArr);
                        }
                        recyclerView3 = this.this$0.rvStats;
                        if (recyclerView3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("rvStats");
                            recyclerView3 = null;
                        }
                        recyclerView3.setVisibility(8);
                        view6 = this.this$0.layoutEmptyState;
                        if (view6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("layoutEmptyState");
                            view6 = null;
                        }
                        view6.setVisibility(8);
                        view7 = this.this$0.layoutExport;
                        if (view7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("layoutExport");
                            view7 = null;
                        }
                        view7.setVisibility(8);
                        view8 = this.this$0.layoutChart;
                        if (view8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("layoutChart");
                        } else {
                            view9 = view8;
                        }
                        view9.setVisibility(0);
                    } else if (this.$stats.isEmpty() && !this.$hasData) {
                        recyclerView2 = this.this$0.rvStats;
                        if (recyclerView2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("rvStats");
                            recyclerView2 = null;
                        }
                        recyclerView2.setVisibility(8);
                        view4 = this.this$0.layoutChart;
                        if (view4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("layoutChart");
                            view4 = null;
                        }
                        view4.setVisibility(8);
                        view5 = this.this$0.layoutEmptyState;
                        if (view5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("layoutEmptyState");
                        } else {
                            view9 = view5;
                        }
                        view9.setVisibility(0);
                    } else if (!this.$stats.isEmpty()) {
                        statsAdapter = this.this$0.statsAdapter;
                        if (statsAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
                            statsAdapter = null;
                        }
                        statsAdapter.updateData(this.$stats);
                        view = this.this$0.layoutEmptyState;
                        if (view == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("layoutEmptyState");
                            view = null;
                        }
                        view.setVisibility(8);
                        view2 = this.this$0.layoutChart;
                        if (view2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("layoutChart");
                            view2 = null;
                        }
                        view2.setVisibility(8);
                        recyclerView = this.this$0.rvStats;
                        if (recyclerView == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("rvStats");
                            recyclerView = null;
                        }
                        recyclerView.setVisibility(0);
                        view3 = this.this$0.layoutExport;
                        if (view3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("layoutExport");
                        } else {
                            view9 = view3;
                        }
                        view9.setVisibility(0);
                        this.this$0.updateSelectionUI(0);
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
