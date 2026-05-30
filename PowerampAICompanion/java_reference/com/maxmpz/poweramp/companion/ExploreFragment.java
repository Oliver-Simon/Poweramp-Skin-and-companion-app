package com.maxmpz.poweramp.companion;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.maxmpz.poweramp.companion.StatsEngine;
import com.maxmpz.poweramp.player.PowerampAPI;
import com.maxmpz.poweramp.player.TableDefs;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: ExploreFragment.kt */
@Metadata(d1 = {"\u0000·\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005*\u0001-\u0018\u0000 R2\u00020\u0001:\u0002QRB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u000e2\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u000200H\u0002J\b\u00105\u001a\u000200H\u0002J\b\u00106\u001a\u000200H\u0002J\b\u00107\u001a\u000200H\u0002J\b\u00108\u001a\u000200H\u0002J\b\u00109\u001a\u000200H\u0002J\b\u0010:\u001a\u000200H\u0002J\u0010\u0010;\u001a\u0002002\u0006\u0010<\u001a\u00020=H\u0002J\u0010\u0010>\u001a\u0002002\u0006\u0010?\u001a\u00020=H\u0002J\u0018\u0010@\u001a\u0002002\u0006\u0010A\u001a\u00020B2\u0006\u0010?\u001a\u00020CH\u0002J\u0010\u0010D\u001a\u0002002\u0006\u0010A\u001a\u00020BH\u0002J0\u0010E\u001a\b\u0012\u0004\u0012\u00020G0F2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020K2\b\b\u0002\u0010M\u001a\u00020NH\u0002J\u001e\u0010O\u001a\b\u0012\u0004\u0012\u00020G0F2\u0006\u0010H\u001a\u00020I2\u0006\u0010L\u001a\u00020KH\u0002J\b\u0010P\u001a\u000200H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0004\n\u0002\u0010.¨\u0006S"}, d2 = {"Lcom/maxmpz/poweramp/companion/ExploreFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "rvStats", "Landroidx/recyclerview/widget/RecyclerView;", "progressStats", "Lcom/google/android/material/progressindicator/CircularProgressIndicator;", "toggleType", "Lcom/google/android/material/button/MaterialButtonToggleGroup;", "cgTimeframe", "Lcom/google/android/material/chip/ChipGroup;", "cgYearSelection", "scrollTimeframe", "Landroid/view/View;", "scrollYearSelection", "tvTimeMachineLabel", "Landroid/widget/TextView;", "btnViewToggle", "Landroid/widget/ImageButton;", "layoutChart", "chartTimeDistribution", "Lcom/maxmpz/poweramp/companion/HourlyBarChartView;", "viewModel", "Lcom/maxmpz/poweramp/companion/ExploreViewModel;", "getViewModel", "()Lcom/maxmpz/poweramp/companion/ExploreViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "statsEngine", "Lcom/maxmpz/poweramp/companion/StatsEngine;", "statsAdapter", "Lcom/maxmpz/poweramp/companion/StatsAdapter;", "loadJob", "Lkotlinx/coroutines/Job;", "isRestoringState", "", "layoutExport", "layoutEmptyState", "btnPlaylist", "Lcom/google/android/material/button/MaterialButton;", "btnQueue", "btnPlayNext", "btnTimeMachine", "backPressedCallback", "com/maxmpz/poweramp/companion/ExploreFragment$backPressedCallback$1", "Lcom/maxmpz/poweramp/companion/ExploreFragment$backPressedCallback$1;", "onViewCreated", "", "view", "savedInstanceState", "Landroid/os/Bundle;", "loadStreaks", "updateTimeMachineUI", "restoreUIState", "setupListeners", "loadStats", "enterSelectionMode", "exitSelectionMode", "updateSelectionUI", "count", "", "exportStats", "mode", "handleStatClick", "statItem", "Lcom/maxmpz/poweramp/companion/StatsEngine$StatItem;", "Lcom/maxmpz/poweramp/companion/ExploreFragment$ClickMode;", "openPowerampCategory", "findAlbumTracks", "", "Lcom/maxmpz/poweramp/companion/PowerampTrack;", "context", "Landroid/content/Context;", "albumName", "", "artistName", "albumPampId", "", "findArtistTracks", "updateViewMode", "ClickMode", "Companion", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ExploreFragment extends Fragment {
    private static final int EXPORT_MODE_PLAYLIST = 1;
    private static final int EXPORT_MODE_PLAY_NEXT = 2;
    private static final int EXPORT_MODE_QUEUE = 0;
    private final ExploreFragment$backPressedCallback$1 backPressedCallback;
    private MaterialButton btnPlayNext;
    private MaterialButton btnPlaylist;
    private MaterialButton btnQueue;
    private ImageButton btnTimeMachine;
    private ImageButton btnViewToggle;
    private ChipGroup cgTimeframe;
    private ChipGroup cgYearSelection;
    private HourlyBarChartView chartTimeDistribution;
    private boolean isRestoringState;
    private View layoutChart;
    private View layoutEmptyState;
    private View layoutExport;
    private Job loadJob;
    private CircularProgressIndicator progressStats;
    private RecyclerView rvStats;
    private View scrollTimeframe;
    private View scrollYearSelection;
    private StatsAdapter statsAdapter;
    private StatsEngine statsEngine;
    private MaterialButtonToggleGroup toggleType;
    private TextView tvTimeMachineLabel;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* compiled from: ExploreFragment.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

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
            int[] iArr2 = new int[StatsEngine.TimeRange.values().length];
            try {
                iArr2[StatsEngine.TimeRange.LAST_24_HOURS.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[StatsEngine.TimeRange.LAST_7_DAYS.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[StatsEngine.TimeRange.LAST_30_DAYS.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[StatsEngine.TimeRange.LAST_60_DAYS.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[StatsEngine.TimeRange.LAST_180_DAYS.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[StatsEngine.TimeRange.LAST_365_DAYS.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr2[StatsEngine.TimeRange.ALL_TIME.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ClickMode.values().length];
            try {
                iArr3[ClickMode.PLAY.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr3[ClickMode.QUEUE.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr3[ClickMode.PLAY_NEXT.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.maxmpz.poweramp.companion.ExploreFragment$backPressedCallback$1] */
    public ExploreFragment() {
        super(R.layout.fragment_explore);
        final ExploreFragment exploreFragment = this;
        final Function0 function0 = null;
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(exploreFragment, Reflection.getOrCreateKotlinClass(ExploreViewModel.class), new Function0<ViewModelStore>() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 != null && (creationExtras = (CreationExtras) function02.invoke()) != null) {
                    return creationExtras;
                }
                CreationExtras defaultViewModelCreationExtras = exploreFragment.requireActivity().getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.backPressedCallback = new OnBackPressedCallback() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$backPressedCallback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(false);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                ExploreFragment.this.exitSelectionMode();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ExploreViewModel getViewModel() {
        return (ExploreViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ExploreFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/maxmpz/poweramp/companion/ExploreFragment$ClickMode;", "", "<init>", "(Ljava/lang/String;I)V", "PLAY", "QUEUE", "PLAY_NEXT", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public enum ClickMode {
        PLAY,
        QUEUE,
        PLAY_NEXT;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<ClickMode> getEntries() {
            return $ENTRIES;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context requireContext = requireContext();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
        this.statsEngine = new StatsEngine(requireContext, ((MainActivity) requireActivity).getPowerampController());
        this.rvStats = (RecyclerView) view.findViewById(R.id.rvStats);
        this.progressStats = (CircularProgressIndicator) view.findViewById(R.id.progressStats);
        this.toggleType = (MaterialButtonToggleGroup) view.findViewById(R.id.toggleType);
        this.cgTimeframe = (ChipGroup) view.findViewById(R.id.cgTimeframe);
        this.cgYearSelection = (ChipGroup) view.findViewById(R.id.cgYearSelection);
        this.scrollTimeframe = view.findViewById(R.id.scrollTimeframe);
        this.scrollYearSelection = view.findViewById(R.id.scrollYearSelection);
        this.tvTimeMachineLabel = (TextView) view.findViewById(R.id.tvTimeMachineLabel);
        this.btnViewToggle = (ImageButton) view.findViewById(R.id.btnViewToggle);
        this.layoutExport = view.findViewById(R.id.layoutExportExplore);
        this.layoutEmptyState = view.findViewById(R.id.layoutEmptyState);
        this.btnPlaylist = (MaterialButton) view.findViewById(R.id.btnSendToPlaylistExplore);
        this.btnQueue = (MaterialButton) view.findViewById(R.id.btnSendToQueueExplore);
        this.btnPlayNext = (MaterialButton) view.findViewById(R.id.btnPlayNextExplore);
        this.btnTimeMachine = (ImageButton) view.findViewById(R.id.btnTimeMachine);
        this.layoutChart = view.findViewById(R.id.layoutChart);
        this.chartTimeDistribution = (HourlyBarChartView) view.findViewById(R.id.chartTimeDistribution);
        updateViewMode();
        StatsAdapter statsAdapter = new StatsAdapter(CollectionsKt.emptyList(), new Function1() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onViewCreated$lambda$0;
                onViewCreated$lambda$0 = ExploreFragment.onViewCreated$lambda$0(ExploreFragment.this, (StatsEngine.StatItem) obj);
                return onViewCreated$lambda$0;
            }
        }, new Function1() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onViewCreated$lambda$1;
                onViewCreated$lambda$1 = ExploreFragment.onViewCreated$lambda$1(ExploreFragment.this, (StatsEngine.StatItem) obj);
                return onViewCreated$lambda$1;
            }
        }, new Function1() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onViewCreated$lambda$2;
                onViewCreated$lambda$2 = ExploreFragment.onViewCreated$lambda$2(ExploreFragment.this, (StatsEngine.StatItem) obj);
                return onViewCreated$lambda$2;
            }
        }, new Function0() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onViewCreated$lambda$3;
                onViewCreated$lambda$3 = ExploreFragment.onViewCreated$lambda$3(ExploreFragment.this);
                return onViewCreated$lambda$3;
            }
        }, new Function1() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onViewCreated$lambda$4;
                onViewCreated$lambda$4 = ExploreFragment.onViewCreated$lambda$4(ExploreFragment.this, ((Integer) obj).intValue());
                return onViewCreated$lambda$4;
            }
        });
        statsAdapter.setGridView(getViewModel().isGridView(getViewModel().getCurrentType()));
        this.statsAdapter = statsAdapter;
        RecyclerView recyclerView = this.rvStats;
        ImageButton imageButton = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvStats");
            recyclerView = null;
        }
        StatsAdapter statsAdapter2 = this.statsAdapter;
        if (statsAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
            statsAdapter2 = null;
        }
        recyclerView.setAdapter(statsAdapter2);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), this.backPressedCallback);
        View view2 = this.layoutExport;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutExport");
            view2 = null;
        }
        view2.setVisibility(8);
        MaterialButton materialButton = this.btnQueue;
        if (materialButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnQueue");
            materialButton = null;
        }
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                ExploreFragment.this.exportStats(0);
            }
        });
        MaterialButton materialButton2 = this.btnPlayNext;
        if (materialButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnPlayNext");
            materialButton2 = null;
        }
        materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                ExploreFragment.this.exportStats(2);
            }
        });
        ImageButton imageButton2 = this.btnTimeMachine;
        if (imageButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnTimeMachine");
            imageButton2 = null;
        }
        imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                ExploreFragment.onViewCreated$lambda$8(ExploreFragment.this, view3);
            }
        });
        ImageButton imageButton3 = this.btnViewToggle;
        if (imageButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnViewToggle");
        } else {
            imageButton = imageButton3;
        }
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                ExploreFragment.onViewCreated$lambda$9(ExploreFragment.this, view3);
            }
        });
        setupListeners();
        restoreUIState();
        loadStats();
        loadStreaks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$0(ExploreFragment this$0, StatsEngine.StatItem statItem) {
        if (statItem.getType() == StatsEngine.ItemType.TRACK && statItem.getPampId() != -1) {
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
            ((MainActivity) requireActivity).getPowerampController().playTrack(statItem.getPampId());
        } else {
            this$0.handleStatClick(statItem, ClickMode.PLAY);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$1(ExploreFragment this$0, StatsEngine.StatItem statItem) {
        if (statItem.getType() == StatsEngine.ItemType.TRACK && statItem.getPampId() != -1) {
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
            ((MainActivity) requireActivity).getPowerampController().sendToPowerampQueue(CollectionsKt.listOf(Long.valueOf(statItem.getPampId())));
        } else {
            this$0.handleStatClick(statItem, ClickMode.QUEUE);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$2(ExploreFragment this$0, StatsEngine.StatItem statItem) {
        if (statItem.getType() == StatsEngine.ItemType.TRACK && statItem.getPampId() != -1) {
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
            ((MainActivity) requireActivity).getPowerampController().sendToPowerampQueueNext(CollectionsKt.listOf(Long.valueOf(statItem.getPampId())));
        } else {
            this$0.handleStatClick(statItem, ClickMode.PLAY_NEXT);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$3(ExploreFragment this$0) {
        this$0.enterSelectionMode();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$4(ExploreFragment this$0, int count) {
        this$0.getViewModel().getSelectedItems().clear();
        Set<StatsEngine.StatItem> selectedItems = this$0.getViewModel().getSelectedItems();
        StatsAdapter statsAdapter = this$0.statsAdapter;
        if (statsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
            statsAdapter = null;
        }
        selectedItems.addAll(statsAdapter.getSelectedItems());
        this$0.updateSelectionUI(count);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$8(ExploreFragment this$0, View it) {
        this$0.getViewModel().setTimeMachineMode(!this$0.getViewModel().getIsTimeMachineMode());
        this$0.updateTimeMachineUI();
        this$0.loadStats();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$9(ExploreFragment this$0, View it) {
        boolean newGridMode = !this$0.getViewModel().isGridView(this$0.getViewModel().getCurrentType());
        this$0.getViewModel().setGridView(this$0.getViewModel().getCurrentType(), newGridMode);
        RecyclerView recyclerView = this$0.rvStats;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvStats");
            recyclerView = null;
        }
        recyclerView.getRecycledViewPool().clear();
        StatsAdapter statsAdapter = this$0.statsAdapter;
        if (statsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
            statsAdapter = null;
        }
        statsAdapter.setGridView(newGridMode);
        this$0.updateViewMode();
        RecyclerView recyclerView3 = this$0.rvStats;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvStats");
        } else {
            recyclerView2 = recyclerView3;
        }
        recyclerView2.invalidateItemDecorations();
    }

    private final void loadStreaks() {
    }

    private final void updateTimeMachineUI() {
        ImageButton imageButton = null;
        ChipGroup chipGroup = null;
        if (getViewModel().getIsTimeMachineMode()) {
            View view = this.scrollTimeframe;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scrollTimeframe");
                view = null;
            }
            view.setVisibility(8);
            View view2 = this.scrollYearSelection;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scrollYearSelection");
                view2 = null;
            }
            view2.setVisibility(0);
            TextView textView = this.tvTimeMachineLabel;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvTimeMachineLabel");
                textView = null;
            }
            textView.setVisibility(0);
            ImageButton imageButton2 = this.btnTimeMachine;
            if (imageButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnTimeMachine");
                imageButton2 = null;
            }
            imageButton2.setColorFilter(requireContext().getColor(android.R.color.holo_orange_dark));
            ChipGroup chipGroup2 = this.cgYearSelection;
            if (chipGroup2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cgYearSelection");
                chipGroup2 = null;
            }
            if (chipGroup2.getCheckedChipId() == -1) {
                ChipGroup chipGroup3 = this.cgYearSelection;
                if (chipGroup3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cgYearSelection");
                } else {
                    chipGroup = chipGroup3;
                }
                chipGroup.check(R.id.chip1Year);
                return;
            }
            return;
        }
        View view3 = this.scrollTimeframe;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scrollTimeframe");
            view3 = null;
        }
        view3.setVisibility(0);
        View view4 = this.scrollYearSelection;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scrollYearSelection");
            view4 = null;
        }
        view4.setVisibility(8);
        TextView textView2 = this.tvTimeMachineLabel;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvTimeMachineLabel");
            textView2 = null;
        }
        textView2.setVisibility(8);
        ImageButton imageButton3 = this.btnTimeMachine;
        if (imageButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnTimeMachine");
        } else {
            imageButton = imageButton3;
        }
        imageButton.clearColorFilter();
    }

    private final void restoreUIState() {
        int typeButtonId;
        int chipId;
        int yearChipId;
        this.isRestoringState = true;
        switch (WhenMappings.$EnumSwitchMapping$0[getViewModel().getCurrentType().ordinal()]) {
            case 1:
                typeButtonId = R.id.btnTypeTracks;
                break;
            case 2:
                typeButtonId = R.id.btnTypeArtists;
                break;
            case 3:
                typeButtonId = R.id.btnTypeAlbums;
                break;
            case 4:
                typeButtonId = R.id.btnTypeTime;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        MaterialButtonToggleGroup materialButtonToggleGroup = this.toggleType;
        ChipGroup chipGroup = null;
        if (materialButtonToggleGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toggleType");
            materialButtonToggleGroup = null;
        }
        materialButtonToggleGroup.check(typeButtonId);
        switch (WhenMappings.$EnumSwitchMapping$1[getViewModel().getCurrentRange().ordinal()]) {
            case 1:
                chipId = R.id.chip24Hours;
                break;
            case 2:
                chipId = R.id.chip7Days;
                break;
            case 3:
                chipId = R.id.chip30Days;
                break;
            case 4:
                chipId = R.id.chip60Days;
                break;
            case 5:
                chipId = R.id.chip180Days;
                break;
            case 6:
                chipId = R.id.chip365Days;
                break;
            case 7:
                chipId = R.id.chipAllTime;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        ChipGroup chipGroup2 = this.cgTimeframe;
        if (chipGroup2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cgTimeframe");
            chipGroup2 = null;
        }
        chipGroup2.check(chipId);
        switch (getViewModel().getTimeMachineYear()) {
            case 1:
                yearChipId = R.id.chip1Year;
                break;
            case 2:
                yearChipId = R.id.chip2Years;
                break;
            case 3:
                yearChipId = R.id.chip3Years;
                break;
            case 4:
                yearChipId = R.id.chip4Years;
                break;
            case 5:
                yearChipId = R.id.chip5Years;
                break;
            case 6:
                yearChipId = R.id.chip6Years;
                break;
            case 7:
                yearChipId = R.id.chip7Years;
                break;
            case 8:
                yearChipId = R.id.chip8Years;
                break;
            case 9:
                yearChipId = R.id.chip9Years;
                break;
            case 10:
                yearChipId = R.id.chip10Years;
                break;
            default:
                yearChipId = R.id.chip1Year;
                break;
        }
        ChipGroup chipGroup3 = this.cgYearSelection;
        if (chipGroup3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cgYearSelection");
        } else {
            chipGroup = chipGroup3;
        }
        chipGroup.check(yearChipId);
        updateTimeMachineUI();
        this.isRestoringState = false;
    }

    private final void setupListeners() {
        MaterialButtonToggleGroup materialButtonToggleGroup = this.toggleType;
        ChipGroup chipGroup = null;
        if (materialButtonToggleGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toggleType");
            materialButtonToggleGroup = null;
        }
        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda11
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public final void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup2, int i, boolean z) {
                ExploreFragment.setupListeners$lambda$10(ExploreFragment.this, materialButtonToggleGroup2, i, z);
            }
        });
        ChipGroup chipGroup2 = this.cgTimeframe;
        if (chipGroup2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cgTimeframe");
            chipGroup2 = null;
        }
        chipGroup2.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda1
            @Override // com.google.android.material.chip.ChipGroup.OnCheckedStateChangeListener
            public final void onCheckedChanged(ChipGroup chipGroup3, List list) {
                ExploreFragment.setupListeners$lambda$11(ExploreFragment.this, chipGroup3, list);
            }
        });
        ChipGroup chipGroup3 = this.cgYearSelection;
        if (chipGroup3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cgYearSelection");
        } else {
            chipGroup = chipGroup3;
        }
        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() { // from class: com.maxmpz.poweramp.companion.ExploreFragment$$ExternalSyntheticLambda2
            @Override // com.google.android.material.chip.ChipGroup.OnCheckedStateChangeListener
            public final void onCheckedChanged(ChipGroup chipGroup4, List list) {
                ExploreFragment.setupListeners$lambda$12(ExploreFragment.this, chipGroup4, list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupListeners$lambda$10(ExploreFragment this$0, MaterialButtonToggleGroup materialButtonToggleGroup, int checkedId, boolean isChecked) {
        StatsEngine.ItemType newType;
        if (isChecked && !this$0.isRestoringState) {
            if (checkedId == R.id.btnTypeTracks) {
                newType = StatsEngine.ItemType.TRACK;
            } else if (checkedId == R.id.btnTypeArtists) {
                newType = StatsEngine.ItemType.ARTIST;
            } else if (checkedId == R.id.btnTypeAlbums) {
                newType = StatsEngine.ItemType.ALBUM;
            } else {
                newType = checkedId == R.id.btnTypeTime ? StatsEngine.ItemType.TIME : StatsEngine.ItemType.TRACK;
            }
            if (newType != this$0.getViewModel().getCurrentType()) {
                this$0.getViewModel().setCurrentType(newType);
                RecyclerView recyclerView = this$0.rvStats;
                StatsAdapter statsAdapter = null;
                if (recyclerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rvStats");
                    recyclerView = null;
                }
                recyclerView.getRecycledViewPool().clear();
                StatsAdapter statsAdapter2 = this$0.statsAdapter;
                if (statsAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
                } else {
                    statsAdapter = statsAdapter2;
                }
                statsAdapter.setGridView(this$0.getViewModel().isGridView(newType));
                this$0.updateViewMode();
                this$0.exitSelectionMode();
                this$0.loadStats();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupListeners$lambda$11(ExploreFragment this$0, ChipGroup chipGroup, List checkedIds) {
        StatsEngine.TimeRange newRange;
        if (!checkedIds.isEmpty() && !this$0.isRestoringState) {
            Integer num = (Integer) checkedIds.get(0);
            int i = R.id.chip24Hours;
            if (num != null && num.intValue() == i) {
                newRange = StatsEngine.TimeRange.LAST_24_HOURS;
            } else {
                int i2 = R.id.chip7Days;
                if (num != null && num.intValue() == i2) {
                    newRange = StatsEngine.TimeRange.LAST_7_DAYS;
                } else {
                    int i3 = R.id.chip30Days;
                    if (num != null && num.intValue() == i3) {
                        newRange = StatsEngine.TimeRange.LAST_30_DAYS;
                    } else {
                        int i4 = R.id.chip60Days;
                        if (num != null && num.intValue() == i4) {
                            newRange = StatsEngine.TimeRange.LAST_60_DAYS;
                        } else {
                            int i5 = R.id.chip180Days;
                            if (num != null && num.intValue() == i5) {
                                newRange = StatsEngine.TimeRange.LAST_180_DAYS;
                            } else {
                                int i6 = R.id.chip365Days;
                                if (num != null && num.intValue() == i6) {
                                    newRange = StatsEngine.TimeRange.LAST_365_DAYS;
                                } else {
                                    newRange = (num != null && num.intValue() == R.id.chipAllTime) ? StatsEngine.TimeRange.ALL_TIME : StatsEngine.TimeRange.LAST_7_DAYS;
                                }
                            }
                        }
                    }
                }
            }
            if (newRange != this$0.getViewModel().getCurrentRange()) {
                this$0.getViewModel().setCurrentRange(newRange);
                this$0.loadStats();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupListeners$lambda$12(ExploreFragment this$0, ChipGroup chipGroup, List checkedIds) {
        if (!checkedIds.isEmpty() && !this$0.isRestoringState) {
            Integer num = (Integer) checkedIds.get(0);
            int i = R.id.chip1Year;
            int newYear = 1;
            if (num == null || num.intValue() != i) {
                int i2 = R.id.chip2Years;
                if (num != null && num.intValue() == i2) {
                    newYear = 2;
                } else {
                    int i3 = R.id.chip3Years;
                    if (num != null && num.intValue() == i3) {
                        newYear = 3;
                    } else {
                        int i4 = R.id.chip4Years;
                        if (num != null && num.intValue() == i4) {
                            newYear = 4;
                        } else {
                            int i5 = R.id.chip5Years;
                            if (num != null && num.intValue() == i5) {
                                newYear = 5;
                            } else {
                                int i6 = R.id.chip6Years;
                                if (num != null && num.intValue() == i6) {
                                    newYear = 6;
                                } else {
                                    int i7 = R.id.chip7Years;
                                    if (num != null && num.intValue() == i7) {
                                        newYear = 7;
                                    } else {
                                        int i8 = R.id.chip8Years;
                                        if (num != null && num.intValue() == i8) {
                                            newYear = 8;
                                        } else {
                                            int i9 = R.id.chip9Years;
                                            if (num != null && num.intValue() == i9) {
                                                newYear = 9;
                                            } else {
                                                int i10 = R.id.chip10Years;
                                                if (num != null && num.intValue() == i10) {
                                                    newYear = 10;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (newYear != this$0.getViewModel().getTimeMachineYear()) {
                this$0.getViewModel().setTimeMachineYear(newYear);
                this$0.loadStats();
            }
        }
    }

    private final void loadStats() {
        List list;
        boolean hasData;
        Job launch$default;
        StatsEngine.TimeRange range = getViewModel().getCurrentRange();
        StatsEngine.ItemType type = getViewModel().getCurrentType();
        Integer timeYear = getViewModel().getIsTimeMachineMode() ? Integer.valueOf(getViewModel().getTimeMachineYear()) : null;
        String cacheKey = type + "_" + range + "_" + (timeYear == null ? "none" : timeYear);
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                list = getViewModel().getCachedTracks().get(range);
                break;
            case 2:
                list = getViewModel().getCachedArtists().get(range);
                break;
            case 3:
                list = getViewModel().getCachedAlbums().get(range);
                break;
            case 4:
                list = null;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        List cachedItems = list;
        if (type == StatsEngine.ItemType.TIME) {
            hasData = getViewModel().getCachedTimeDist().containsKey(range);
        } else {
            List list2 = cachedItems;
            hasData = !(list2 == null || list2.isEmpty());
        }
        if (!hasData) {
            CircularProgressIndicator circularProgressIndicator = this.progressStats;
            if (circularProgressIndicator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressStats");
                circularProgressIndicator = null;
            }
            circularProgressIndicator.setVisibility(0);
            RecyclerView recyclerView = this.rvStats;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rvStats");
                recyclerView = null;
            }
            recyclerView.setVisibility(8);
            View view = this.layoutChart;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutChart");
                view = null;
            }
            view.setVisibility(8);
            View view2 = this.layoutEmptyState;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutEmptyState");
                view2 = null;
            }
            view2.setVisibility(8);
            View view3 = this.layoutExport;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutExport");
                view3 = null;
            }
            view3.setVisibility(8);
        } else {
            if (type == StatsEngine.ItemType.TIME) {
                int[] iArr = getViewModel().getCachedTimeDist().get(range);
                if (iArr != null) {
                    HourlyBarChartView hourlyBarChartView = this.chartTimeDistribution;
                    if (hourlyBarChartView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("chartTimeDistribution");
                        hourlyBarChartView = null;
                    }
                    hourlyBarChartView.setData(iArr);
                }
                RecyclerView recyclerView2 = this.rvStats;
                if (recyclerView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rvStats");
                    recyclerView2 = null;
                }
                recyclerView2.setVisibility(8);
                View view4 = this.layoutEmptyState;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("layoutEmptyState");
                    view4 = null;
                }
                view4.setVisibility(8);
                View view5 = this.layoutExport;
                if (view5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("layoutExport");
                    view5 = null;
                }
                view5.setVisibility(8);
                View view6 = this.layoutChart;
                if (view6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("layoutChart");
                    view6 = null;
                }
                view6.setVisibility(0);
            } else {
                if (cachedItems != null) {
                    StatsAdapter statsAdapter = this.statsAdapter;
                    if (statsAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
                        statsAdapter = null;
                    }
                    statsAdapter.updateData(cachedItems);
                }
                View view7 = this.layoutEmptyState;
                if (view7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("layoutEmptyState");
                    view7 = null;
                }
                view7.setVisibility(8);
                View view8 = this.layoutChart;
                if (view8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("layoutChart");
                    view8 = null;
                }
                view8.setVisibility(8);
                RecyclerView recyclerView3 = this.rvStats;
                if (recyclerView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rvStats");
                    recyclerView3 = null;
                }
                recyclerView3.setVisibility(0);
                View view9 = this.layoutExport;
                if (view9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("layoutExport");
                    view9 = null;
                }
                view9.setVisibility(0);
                updateSelectionUI(0);
            }
            Long l = getViewModel().getLastUpdateMap().get(cacheKey);
            long lastUpdate = l != null ? l.longValue() : 0L;
            boolean needsRefresh = System.currentTimeMillis() - lastUpdate > 300000;
            if (!needsRefresh) {
                CircularProgressIndicator circularProgressIndicator2 = this.progressStats;
                if (circularProgressIndicator2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("progressStats");
                    circularProgressIndicator2 = null;
                }
                circularProgressIndicator2.setVisibility(8);
                return;
            }
            CircularProgressIndicator circularProgressIndicator3 = this.progressStats;
            if (circularProgressIndicator3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressStats");
                circularProgressIndicator3 = null;
            }
            circularProgressIndicator3.setVisibility(0);
        }
        Job job = this.loadJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(getViewLifecycleOwner()), Dispatchers.getIO(), null, new ExploreFragment$loadStats$3(type, this, range, timeYear, cacheKey, hasData, null), 2, null);
        this.loadJob = launch$default;
    }

    private final void enterSelectionMode() {
        getViewModel().setSelectionMode(true);
        StatsAdapter statsAdapter = this.statsAdapter;
        if (statsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
            statsAdapter = null;
        }
        statsAdapter.setSelectionMode(true);
        setEnabled(true);
        updateSelectionUI(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void exitSelectionMode() {
        getViewModel().setSelectionMode(false);
        StatsAdapter statsAdapter = this.statsAdapter;
        if (statsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
            statsAdapter = null;
        }
        statsAdapter.setSelectionMode(false);
        getViewModel().getSelectedItems().clear();
        setEnabled(false);
        updateSelectionUI(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelectionUI(int count) {
        StatsAdapter statsAdapter = this.statsAdapter;
        MaterialButton materialButton = null;
        if (statsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
            statsAdapter = null;
        }
        if (statsAdapter.getIsSelectionMode()) {
            MaterialButton materialButton2 = this.btnPlaylist;
            if (materialButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnPlaylist");
                materialButton2 = null;
            }
            materialButton2.setText(count > 0 ? "PLAYLIST (" + count + ")" : "PLAYLIST");
            MaterialButton materialButton3 = this.btnQueue;
            if (materialButton3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnQueue");
            } else {
                materialButton = materialButton3;
            }
            materialButton.setText(count > 0 ? "QUEUE (" + count + ")" : "QUEUE");
            return;
        }
        MaterialButton materialButton4 = this.btnPlaylist;
        if (materialButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnPlaylist");
            materialButton4 = null;
        }
        materialButton4.setText("ALLE IN PLAYLIST");
        MaterialButton materialButton5 = this.btnQueue;
        if (materialButton5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnQueue");
        } else {
            materialButton = materialButton5;
        }
        materialButton.setText("ALLE IN QUEUE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0047 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void exportStats(int r10) {
        /*
            r9 = this;
            com.maxmpz.poweramp.companion.StatsAdapter r0 = r9.statsAdapter
            java.lang.String r1 = "statsAdapter"
            r2 = 0
            if (r0 != 0) goto Lb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        Lb:
            boolean r0 = r0.getIsSelectionMode()
            if (r0 == 0) goto L38
            com.maxmpz.poweramp.companion.StatsAdapter r0 = r9.statsAdapter
            if (r0 != 0) goto L19
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L19:
            java.util.Set r0 = r0.getSelectedItems()
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L38
            com.maxmpz.poweramp.companion.StatsAdapter r0 = r9.statsAdapter
            if (r0 != 0) goto L2d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L2d:
            java.util.Set r0 = r0.getSelectedItems()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.List r0 = kotlin.collections.CollectionsKt.toList(r0)
            goto L40
        L38:
            com.maxmpz.poweramp.companion.ExploreViewModel r0 = r9.getViewModel()
            java.util.List r0 = r0.getCachedStats()
        L40:
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L48
            return
        L48:
            android.content.Context r1 = r9.requireContext()
            int r3 = r0.size()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Exportiere "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r4 = " Einträge..."
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r4 = 0
            android.widget.Toast r1 = android.widget.Toast.makeText(r1, r3, r4)
            r1.show()
            androidx.lifecycle.LifecycleOwner r1 = r9.getViewLifecycleOwner()
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r1)
            r3 = r1
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            r4 = r1
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            com.maxmpz.poweramp.companion.ExploreFragment$exportStats$1 r1 = new com.maxmpz.poweramp.companion.ExploreFragment$exportStats$1
            r1.<init>(r9, r0, r10, r2)
            r6 = r1
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = 2
            r8 = 0
            r5 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.ExploreFragment.exportStats(int):void");
    }

    private final void handleStatClick(StatsEngine.StatItem statItem, ClickMode mode) {
        StatsAdapter statsAdapter = this.statsAdapter;
        StatsAdapter statsAdapter2 = null;
        if (statsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
            statsAdapter = null;
        }
        if (statsAdapter.getIsSelectionMode()) {
            StatsAdapter statsAdapter3 = this.statsAdapter;
            if (statsAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("statsAdapter");
            } else {
                statsAdapter2 = statsAdapter3;
            }
            statsAdapter2.toggleSelection(statItem);
            return;
        }
        if (statItem.getType() == StatsEngine.ItemType.TRACK) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
            PowerampController controller = ((MainActivity) requireActivity).getPowerampController();
            if (statItem.getPampId() == -1) {
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(getViewLifecycleOwner()), Dispatchers.getIO(), null, new ExploreFragment$handleStatClick$1(controller, statItem, mode, this, null), 2, null);
                return;
            }
            switch (WhenMappings.$EnumSwitchMapping$2[mode.ordinal()]) {
                case 1:
                    controller.playTrack(statItem.getPampId());
                    return;
                case 2:
                    controller.sendToPowerampQueue(CollectionsKt.listOf(Long.valueOf(statItem.getPampId())));
                    return;
                case 3:
                    controller.sendToPowerampQueueNext(CollectionsKt.listOf(Long.valueOf(statItem.getPampId())));
                    return;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        if (mode != ClickMode.PLAY) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(getViewLifecycleOwner()), Dispatchers.getIO(), null, new ExploreFragment$handleStatClick$2(this, statItem, mode, null), 2, null);
        } else {
            openPowerampCategory(statItem);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void openPowerampCategory(StatsEngine.StatItem statItem) {
        String category;
        Uri uri;
        try {
            switch (WhenMappings.$EnumSwitchMapping$0[statItem.getType().ordinal()]) {
                case 2:
                    category = TableDefs.Artists.TABLE;
                    break;
                case 3:
                    category = TableDefs.Albums.TABLE;
                    break;
                default:
                    return;
            }
            if (statItem.getPampId() != -1) {
                uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(category).appendEncodedPath(String.valueOf(statItem.getPampId())).appendEncodedPath("files").build();
            } else {
                uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(category).build();
            }
            Intent intent = new Intent(PowerampAPI.ACTION_OPEN_LIBRARY);
            intent.setComponent(new ComponentName(PowerampAPI.PACKAGE_NAME, PowerampAPI.ACTIVITY_STARTUP));
            intent.setData(uri);
            intent.addFlags(268435456);
            try {
                startActivity(intent);
            } catch (Exception e) {
                Intent launchIntent = requireContext().getPackageManager().getLaunchIntentForPackage(PowerampAPI.PACKAGE_NAME);
                if (launchIntent == null) {
                    Toast.makeText(requireContext(), "Poweramp nicht installiert.", 0).show();
                } else {
                    startActivity(launchIntent);
                }
            }
        } catch (Exception e2) {
            Toast.makeText(requireContext(), "Fehler beim Öffnen von Poweramp.", 0).show();
        }
    }

    static /* synthetic */ List findAlbumTracks$default(ExploreFragment exploreFragment, Context context, String str, String str2, long j, int i, Object obj) {
        if ((i & 8) != 0) {
            j = -1;
        }
        return exploreFragment.findAlbumTracks(context, str, str2, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0 */
    /* JADX WARN: Type inference failed for: r19v1 */
    /* JADX WARN: Type inference failed for: r19v18 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v6 */
    public final List<PowerampTrack> findAlbumTracks(Context context, String albumName, String artistName, long albumPampId) {
        ?? r19;
        int i;
        int i2;
        String str;
        String str2;
        String[] strArr;
        String[] strArr2;
        Throwable th;
        long j;
        ArrayList arrayList = new ArrayList();
        int i3 = -1;
        ?? r8 = 2;
        int i4 = 0;
        int i5 = 3;
        if (albumPampId != -1) {
            Uri build = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Albums.TABLE).appendEncodedPath(String.valueOf(albumPampId)).appendEncodedPath("files").build();
            try {
                ContentResolver contentResolver = context.getContentResolver();
                i = 0;
                i2 = 0;
                strArr = new String[]{"_id", TableDefs.Files.TITLE_TAG, TypedValues.TransitionType.S_DURATION};
                Cursor query = contentResolver.query(build, strArr, null, null, "track_number ASC");
                if (query != null) {
                    try {
                        Cursor cursor = query;
                        try {
                            Cursor cursor2 = cursor;
                            int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("_id");
                            int columnIndex = cursor2.getColumnIndex(TableDefs.Files.TITLE_TAG);
                            int columnIndex2 = cursor2.getColumnIndex(TypedValues.TransitionType.S_DURATION);
                            while (cursor2.moveToNext()) {
                                strArr = r8;
                                int i6 = columnIndexOrThrow;
                                try {
                                    long j2 = cursor2.getLong(i6);
                                    i = i4;
                                    int i7 = columnIndex;
                                    try {
                                        String string = cursor2.getString(i7);
                                        String str3 = string == null ? "" : string;
                                        i2 = i5;
                                        int i8 = columnIndex2;
                                        if (i8 != i3) {
                                            try {
                                                j = cursor2.getLong(i8);
                                            } catch (Throwable th2) {
                                                th = th2;
                                                th = th;
                                                try {
                                                    throw th;
                                                } catch (Throwable th3) {
                                                    CloseableKt.closeFinally(cursor, th);
                                                    throw th3;
                                                }
                                            }
                                        } else {
                                            j = 0;
                                        }
                                        arrayList.add(new PowerampTrack(j2, str3, artistName, j, 0, null, 48, null));
                                        columnIndex2 = i8;
                                        i5 = i2;
                                        i3 = -1;
                                        columnIndex = i7;
                                        i4 = i;
                                        columnIndexOrThrow = i6;
                                        r8 = strArr;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        strArr = strArr;
                                        i2 = i5;
                                        th = th;
                                        throw th;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    strArr2 = strArr;
                                    i = i4;
                                    strArr = strArr2;
                                    i2 = i5;
                                    th = th;
                                    throw th;
                                }
                            }
                            strArr = r8;
                            i = i4;
                            i2 = i5;
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursor, null);
                        } catch (Throwable th6) {
                            th = th6;
                            strArr2 = r8;
                        }
                    } catch (Exception e) {
                    }
                } else {
                    strArr = 2;
                    i = 0;
                    i2 = 3;
                }
            } catch (Exception e2) {
                strArr = 2;
                i = 0;
                i2 = 3;
            }
            r19 = strArr;
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
        } else {
            r19 = 2;
            i = 0;
            i2 = 3;
        }
        Uri build2 = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build();
        try {
            ContentResolver contentResolver2 = context.getContentResolver();
            String[] strArr3 = new String[5];
            strArr3[i] = "_id";
            strArr3[1] = TableDefs.Files.TITLE_TAG;
            strArr3[r19] = TableDefs.Files.ALBUM_TAG;
            strArr3[i2] = TableDefs.Files.ARTIST_TAG;
            strArr3[4] = TypedValues.TransitionType.S_DURATION;
            Cursor query2 = contentResolver2.query(build2, strArr3, null, null, null);
            if (query2 != null) {
                Cursor cursor3 = query2;
                try {
                    Cursor cursor4 = cursor3;
                    int columnIndexOrThrow2 = cursor4.getColumnIndexOrThrow("_id");
                    int columnIndex3 = cursor4.getColumnIndex(TableDefs.Files.TITLE_TAG);
                    int columnIndex4 = cursor4.getColumnIndex(TableDefs.Files.ALBUM_TAG);
                    int columnIndex5 = cursor4.getColumnIndex(TableDefs.Files.ARTIST_TAG);
                    int columnIndex6 = cursor4.getColumnIndex(TypedValues.TransitionType.S_DURATION);
                    while (cursor4.moveToNext()) {
                        if (columnIndex4 < 0 || (str = cursor4.getString(columnIndex4)) == null) {
                            str = "";
                        }
                        if (columnIndex5 < 0 || (str2 = cursor4.getString(columnIndex5)) == null) {
                            str2 = "";
                        }
                        if (StringsKt.contains((CharSequence) str, (CharSequence) albumName, true) && StringsKt.contains((CharSequence) str2, (CharSequence) artistName, true)) {
                            long j3 = cursor4.getLong(columnIndexOrThrow2);
                            String string2 = cursor4.getString(columnIndex3);
                            arrayList.add(new PowerampTrack(j3, string2 == null ? "" : string2, artistName, columnIndex6 != -1 ? cursor4.getLong(columnIndex6) : 0L, 0, null, 48, null));
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor3, null);
                } finally {
                }
            }
        } catch (Exception e3) {
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00aa, code lost:
    
        r21 = r0.getLong(r13);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.maxmpz.poweramp.companion.PowerampTrack> findArtistTracks(android.content.Context r30, java.lang.String r31) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.ExploreFragment.findArtistTracks(android.content.Context, java.lang.String):java.util.List");
    }

    private final void updateViewMode() {
        ImageButton imageButton = null;
        if (getViewModel().isGridView(getViewModel().getCurrentType())) {
            RecyclerView recyclerView = this.rvStats;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rvStats");
                recyclerView = null;
            }
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
            ImageButton imageButton2 = this.btnViewToggle;
            if (imageButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnViewToggle");
            } else {
                imageButton = imageButton2;
            }
            imageButton.setImageResource(R.drawable.ic_list);
            return;
        }
        RecyclerView recyclerView2 = this.rvStats;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvStats");
            recyclerView2 = null;
        }
        recyclerView2.setLayoutManager(new LinearLayoutManager(requireContext()));
        ImageButton imageButton3 = this.btnViewToggle;
        if (imageButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnViewToggle");
        } else {
            imageButton = imageButton3;
        }
        imageButton.setImageResource(R.drawable.ic_grid);
    }
}
