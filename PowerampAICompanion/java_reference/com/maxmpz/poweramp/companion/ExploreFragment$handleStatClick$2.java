package com.maxmpz.poweramp.companion;

import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.maxmpz.poweramp.companion.ExploreFragment;
import com.maxmpz.poweramp.companion.StatsEngine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExploreFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.ExploreFragment$handleStatClick$2", f = "ExploreFragment.kt", i = {}, l = {522}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class ExploreFragment$handleStatClick$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ExploreFragment.ClickMode $mode;
    final /* synthetic */ StatsEngine.StatItem $statItem;
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
                iArr[StatsEngine.ItemType.ARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[StatsEngine.ItemType.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExploreFragment$handleStatClick$2(ExploreFragment exploreFragment, StatsEngine.StatItem statItem, ExploreFragment.ClickMode clickMode, Continuation<? super ExploreFragment$handleStatClick$2> continuation) {
        super(2, continuation);
        this.this$0 = exploreFragment;
        this.$statItem = statItem;
        this.$mode = clickMode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExploreFragment$handleStatClick$2(this.this$0, this.$statItem, this.$mode, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExploreFragment$handleStatClick$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        List query;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                FragmentActivity requireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
                PowerampController controller = ((MainActivity) requireActivity).getPowerampController();
                switch (WhenMappings.$EnumSwitchMapping$0[this.$statItem.getType().ordinal()]) {
                    case 1:
                        query = CollectionsKt.listOf(new Pair("MAGIC_TOKEN_KEYWORD_SEARCH|" + this.$statItem.getTitle(), ""));
                        break;
                    case 2:
                        query = CollectionsKt.listOf(new Pair("MAGIC_TOKEN_ALBUM|" + this.$statItem.getPampId(), this.$statItem.getTitle()));
                        break;
                    default:
                        query = CollectionsKt.emptyList();
                        break;
                }
                List foundTracks = controller.findTracks(query);
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(foundTracks, this.$mode, controller, this.this$0, null), this) == coroutine_suspended) {
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
    @DebugMetadata(c = "com.maxmpz.poweramp.companion.ExploreFragment$handleStatClick$2$1", f = "ExploreFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.maxmpz.poweramp.companion.ExploreFragment$handleStatClick$2$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PowerampController $controller;
        final /* synthetic */ List<PowerampTrack> $foundTracks;
        final /* synthetic */ ExploreFragment.ClickMode $mode;
        int label;
        final /* synthetic */ ExploreFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<PowerampTrack> list, ExploreFragment.ClickMode clickMode, PowerampController powerampController, ExploreFragment exploreFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$foundTracks = list;
            this.$mode = clickMode;
            this.$controller = powerampController;
            this.this$0 = exploreFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$foundTracks, this.$mode, this.$controller, this.this$0, continuation);
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
                        Iterable iterable = this.$foundTracks;
                        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                        Iterator it = iterable.iterator();
                        while (it.hasNext()) {
                            arrayList.add(Boxing.boxLong(((PowerampTrack) it.next()).getId()));
                        }
                        List trackIds = (List) arrayList;
                        if (this.$mode == ExploreFragment.ClickMode.QUEUE) {
                            this.$controller.sendToPowerampQueue(trackIds);
                            Toast.makeText(this.this$0.requireContext(), this.$foundTracks.size() + " Songs zur Warteschlange hinzugefügt.", 0).show();
                        } else {
                            this.$controller.sendToPowerampQueueNext(trackIds);
                            Toast.makeText(this.this$0.requireContext(), this.$foundTracks.size() + " Songs werden als nächstes gespielt.", 0).show();
                        }
                    } else {
                        Toast.makeText(this.this$0.requireContext(), "Keine Tracks für dieses Element gefunden.", 0).show();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
