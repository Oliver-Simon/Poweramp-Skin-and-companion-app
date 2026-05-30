package com.maxmpz.poweramp.companion;

import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.maxmpz.poweramp.companion.StatsEngine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExploreFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.ExploreFragment$exportStats$1", f = "ExploreFragment.kt", i = {}, l = {456, 458}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class ExploreFragment$exportStats$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $mode;
    final /* synthetic */ List<StatsEngine.StatItem> $targets;
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
    public ExploreFragment$exportStats$1(ExploreFragment exploreFragment, List<StatsEngine.StatItem> list, int i, Continuation<? super ExploreFragment$exportStats$1> continuation) {
        super(2, continuation);
        this.this$0 = exploreFragment;
        this.$targets = list;
        this.$mode = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExploreFragment$exportStats$1(this.this$0, this.$targets, this.$mode, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExploreFragment$exportStats$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Iterable findArtistTracks;
        Iterable findAlbumTracks;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                FragmentActivity requireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
                PowerampController controller = ((MainActivity) requireActivity).getPowerampController();
                List allTrackIds = new ArrayList();
                for (StatsEngine.StatItem item : this.$targets) {
                    switch (WhenMappings.$EnumSwitchMapping$0[item.getType().ordinal()]) {
                        case 1:
                            if (item.getPampId() != -1) {
                                allTrackIds.add(Boxing.boxLong(item.getPampId()));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            findArtistTracks = this.this$0.findArtistTracks(this.this$0.requireContext(), item.getTitle());
                            Iterable iterable = findArtistTracks;
                            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                            Iterator it = iterable.iterator();
                            while (it.hasNext()) {
                                arrayList.add(Boxing.boxLong(((PowerampTrack) it.next()).getId()));
                            }
                            Boxing.boxBoolean(allTrackIds.addAll((List) arrayList));
                            break;
                        case 3:
                            findAlbumTracks = this.this$0.findAlbumTracks(this.this$0.requireContext(), item.getTitle(), item.getSubtitle(), item.getPampId());
                            Iterable iterable2 = findAlbumTracks;
                            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                            Iterator it2 = iterable2.iterator();
                            while (it2.hasNext()) {
                                arrayList2.add(Boxing.boxLong(((PowerampTrack) it2.next()).getId()));
                            }
                            Boxing.boxBoolean(allTrackIds.addAll((List) arrayList2));
                            break;
                        case 4:
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                }
                if (!allTrackIds.isEmpty()) {
                    switch (this.$mode) {
                        case 0:
                            controller.sendToPowerampQueue(allTrackIds);
                            break;
                        case 1:
                            controller.sendToPoweramp(allTrackIds);
                            break;
                        case 2:
                            controller.sendToPowerampQueueNext(allTrackIds);
                            break;
                    }
                    this.label = 1;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass3(this.this$0, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    this.label = 2;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass4(this.this$0, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            case 2:
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
    @DebugMetadata(c = "com.maxmpz.poweramp.companion.ExploreFragment$exportStats$1$3", f = "ExploreFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.maxmpz.poweramp.companion.ExploreFragment$exportStats$1$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ ExploreFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(ExploreFragment exploreFragment, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.this$0 = exploreFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.this$0.exitSelectionMode();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ExploreFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.maxmpz.poweramp.companion.ExploreFragment$exportStats$1$4", f = "ExploreFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.maxmpz.poweramp.companion.ExploreFragment$exportStats$1$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ ExploreFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(ExploreFragment exploreFragment, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.this$0 = exploreFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass4(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    Toast.makeText(this.this$0.requireContext(), "Keine Songs für den Export gefunden.", 0).show();
                    this.this$0.exitSelectionMode();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
