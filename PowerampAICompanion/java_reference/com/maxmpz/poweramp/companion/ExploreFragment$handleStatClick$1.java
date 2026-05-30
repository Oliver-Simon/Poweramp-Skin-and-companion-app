package com.maxmpz.poweramp.companion;

import android.widget.Toast;
import com.maxmpz.poweramp.companion.ExploreFragment;
import com.maxmpz.poweramp.companion.StatsEngine;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExploreFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.ExploreFragment$handleStatClick$1", f = "ExploreFragment.kt", i = {}, l = {486}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class ExploreFragment$handleStatClick$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PowerampController $controller;
    final /* synthetic */ ExploreFragment.ClickMode $mode;
    final /* synthetic */ StatsEngine.StatItem $statItem;
    int label;
    final /* synthetic */ ExploreFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExploreFragment$handleStatClick$1(PowerampController powerampController, StatsEngine.StatItem statItem, ExploreFragment.ClickMode clickMode, ExploreFragment exploreFragment, Continuation<? super ExploreFragment$handleStatClick$1> continuation) {
        super(2, continuation);
        this.$controller = powerampController;
        this.$statItem = statItem;
        this.$mode = clickMode;
        this.this$0 = exploreFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExploreFragment$handleStatClick$1(this.$controller, this.$statItem, this.$mode, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExploreFragment$handleStatClick$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                List foundTracks = this.$controller.findTracks(CollectionsKt.listOf(new Pair(this.$statItem.getSubtitle(), this.$statItem.getTitle())));
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(foundTracks, this.$mode, this.$controller, this.this$0, this.$statItem, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ExploreFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.maxmpz.poweramp.companion.ExploreFragment$handleStatClick$1$1", f = "ExploreFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.maxmpz.poweramp.companion.ExploreFragment$handleStatClick$1$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PowerampController $controller;
        final /* synthetic */ List<PowerampTrack> $foundTracks;
        final /* synthetic */ ExploreFragment.ClickMode $mode;
        final /* synthetic */ StatsEngine.StatItem $statItem;
        int label;
        final /* synthetic */ ExploreFragment this$0;

        /* compiled from: ExploreFragment.kt */
        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        /* renamed from: com.maxmpz.poweramp.companion.ExploreFragment$handleStatClick$1$1$WhenMappings */
        /* loaded from: classes7.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ExploreFragment.ClickMode.values().length];
                try {
                    iArr[ExploreFragment.ClickMode.PLAY.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[ExploreFragment.ClickMode.QUEUE.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[ExploreFragment.ClickMode.PLAY_NEXT.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<PowerampTrack> list, ExploreFragment.ClickMode clickMode, PowerampController powerampController, ExploreFragment exploreFragment, StatsEngine.StatItem statItem, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$foundTracks = list;
            this.$mode = clickMode;
            this.$controller = powerampController;
            this.this$0 = exploreFragment;
            this.$statItem = statItem;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$foundTracks, this.$mode, this.$controller, this.this$0, this.$statItem, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    if (!this.$foundTracks.isEmpty()) {
                        long trackId = ((PowerampTrack) CollectionsKt.first((List) this.$foundTracks)).getId();
                        switch (WhenMappings.$EnumSwitchMapping$0[this.$mode.ordinal()]) {
                            case 1:
                                this.$controller.playTrack(trackId);
                                Toast.makeText(this.this$0.requireContext(), "Spiele: " + this.$statItem.getTitle(), 0).show();
                                break;
                            case 2:
                                this.$controller.sendToPowerampQueue(CollectionsKt.listOf(Boxing.boxLong(trackId)));
                                Toast.makeText(this.this$0.requireContext(), "Warteschlange: " + this.$statItem.getTitle(), 0).show();
                                break;
                            case 3:
                                this.$controller.sendToPowerampQueueNext(CollectionsKt.listOf(Boxing.boxLong(trackId)));
                                Toast.makeText(this.this$0.requireContext(), "Als nächstes: " + this.$statItem.getTitle(), 0).show();
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                    } else {
                        Toast.makeText(this.this$0.requireContext(), "Nicht in lokaler Poweramp-Mediathek gefunden.", 0).show();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
