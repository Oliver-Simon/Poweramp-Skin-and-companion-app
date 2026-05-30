package com.maxmpz.poweramp.companion;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.HttpUrl;
import org.json.JSONArray;

/* compiled from: GenerateFragment.kt */
@Metadata(d1 = {"\u0000\u0083\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u000b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010*\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010+\u001a\u00020\u000e2\b\u0010,\u001a\u0004\u0018\u00010-H\u0002J\u0010\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u000200H\u0002J\u0016\u00101\u001a\u00020\u000e2\f\u00102\u001a\b\u0012\u0004\u0012\u00020403H\u0002J\u0010\u00105\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020-H\u0002J\b\u00106\u001a\u00020\u000eH\u0002J\u0010\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u00020-H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082.¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/maxmpz/poweramp/companion/GenerateFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "viewModel", "Lcom/maxmpz/poweramp/companion/GenerateViewModel;", "getViewModel", "()Lcom/maxmpz/poweramp/companion/GenerateViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "backPressedCallback", "com/maxmpz/poweramp/companion/GenerateFragment$backPressedCallback$1", "Lcom/maxmpz/poweramp/companion/GenerateFragment$backPressedCallback$1;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "tvStatus", "Landroid/widget/TextView;", "svLog", "Landroid/widget/ScrollView;", "etPrompt", "Lcom/google/android/material/textfield/TextInputEditText;", "etMaxTracks", "etMaxDuration", "btnGenerate", "Lcom/google/android/material/button/MaterialButton;", "btnSmartGenerate", "rvSuggestions", "Landroidx/recyclerview/widget/RecyclerView;", "layoutExport", "Landroid/widget/LinearLayout;", "btnSendToPlaylist", "btnSendToQueue", "btnPlayNext", "progressIndicator", "Lcom/google/android/material/progressindicator/CircularProgressIndicator;", "layoutHistory", "cgHistory", "Lcom/google/android/material/chip/ChipGroup;", "setupViews", "generateSuggestions", "prompt", "", "showLoading", "active", "", "displayTracks", "tracks", "", "Lcom/maxmpz/poweramp/companion/PowerampTrack;", "saveToHistory", "loadHistoryChips", "logMessage", "message", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class GenerateFragment extends Fragment {
    private final GenerateFragment$backPressedCallback$1 backPressedCallback;
    private MaterialButton btnGenerate;
    private MaterialButton btnPlayNext;
    private MaterialButton btnSendToPlaylist;
    private MaterialButton btnSendToQueue;
    private MaterialButton btnSmartGenerate;
    private ChipGroup cgHistory;
    private TextInputEditText etMaxDuration;
    private TextInputEditText etMaxTracks;
    private TextInputEditText etPrompt;
    private LinearLayout layoutExport;
    private View layoutHistory;
    private CircularProgressIndicator progressIndicator;
    private RecyclerView rvSuggestions;
    private ScrollView svLog;
    private TextView tvStatus;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Type inference failed for: r0v3, types: [com.maxmpz.poweramp.companion.GenerateFragment$backPressedCallback$1] */
    public GenerateFragment() {
        super(R.layout.fragment_generate);
        final GenerateFragment generateFragment = this;
        final Function0 function0 = null;
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(generateFragment, Reflection.getOrCreateKotlinClass(GenerateViewModel.class), new Function0<ViewModelStore>() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$special$$inlined$activityViewModels$default$2
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
                CreationExtras defaultViewModelCreationExtras = generateFragment.requireActivity().getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$special$$inlined$activityViewModels$default$3
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
        this.backPressedCallback = new OnBackPressedCallback() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$backPressedCallback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(false);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                RecyclerView recyclerView;
                recyclerView = GenerateFragment.this.rvSuggestions;
                if (recyclerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rvSuggestions");
                    recyclerView = null;
                }
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                TrackAdapter adapter2 = adapter instanceof TrackAdapter ? (TrackAdapter) adapter : null;
                if (adapter2 != null) {
                    adapter2.setSelectionModeEnabled(false);
                }
                setEnabled(false);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GenerateViewModel getViewModel() {
        return (GenerateViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        if (!getViewModel().getGeneratedTracks().isEmpty()) {
            displayTracks(getViewModel().getGeneratedTracks());
        }
        if (getViewModel().getLastPrompt().length() > 0) {
            TextInputEditText textInputEditText = this.etPrompt;
            if (textInputEditText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("etPrompt");
                textInputEditText = null;
            }
            textInputEditText.setText(getViewModel().getLastPrompt());
        }
        if (getViewModel().getIsGenerating()) {
            showLoading(true);
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), this.backPressedCallback);
    }

    private final void setupViews(View view) {
        this.tvStatus = (TextView) view.findViewById(R.id.tvStatus);
        this.svLog = (ScrollView) view.findViewById(R.id.svLog);
        this.etPrompt = (TextInputEditText) view.findViewById(R.id.etPrompt);
        this.etMaxTracks = (TextInputEditText) view.findViewById(R.id.etMaxTracks);
        this.etMaxDuration = (TextInputEditText) view.findViewById(R.id.etMaxDuration);
        this.btnGenerate = (MaterialButton) view.findViewById(R.id.btnGenerate);
        this.btnSmartGenerate = (MaterialButton) view.findViewById(R.id.btnSmartGenerate);
        this.rvSuggestions = (RecyclerView) view.findViewById(R.id.rvSuggestions);
        this.layoutExport = (LinearLayout) view.findViewById(R.id.layoutExport);
        this.btnSendToPlaylist = (MaterialButton) view.findViewById(R.id.btnSendToPlaylist);
        this.btnSendToQueue = (MaterialButton) view.findViewById(R.id.btnSendToQueue);
        this.btnPlayNext = (MaterialButton) view.findViewById(R.id.btnPlayNext);
        this.progressIndicator = (CircularProgressIndicator) view.findViewById(R.id.progressIndicator);
        this.layoutHistory = view.findViewById(R.id.layoutHistory);
        this.cgHistory = (ChipGroup) view.findViewById(R.id.cgHistory);
        MaterialButton btnClearLog = (MaterialButton) view.findViewById(R.id.btnClearLog);
        MaterialButton btnCopyLog = (MaterialButton) view.findViewById(R.id.btnCopyLog);
        btnClearLog.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GenerateFragment.setupViews$lambda$0(GenerateFragment.this, view2);
            }
        });
        btnCopyLog.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GenerateFragment.setupViews$lambda$1(GenerateFragment.this, view2);
            }
        });
        loadHistoryChips();
        final TextView btnToggleSelection = (TextView) view.findViewById(R.id.btnToggleSelection);
        btnToggleSelection.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GenerateFragment.setupViews$lambda$2(GenerateFragment.this, btnToggleSelection, view2);
            }
        });
        RecyclerView recyclerView = this.rvSuggestions;
        MaterialButton materialButton = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvSuggestions");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        View.OnClickListener chipClickListener = new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GenerateFragment.setupViews$lambda$3(GenerateFragment.this, view2);
            }
        };
        ((MaterialButton) view.findViewById(R.id.chipNeu)).setOnClickListener(chipClickListener);
        ((MaterialButton) view.findViewById(R.id.chipRuhig)).setOnClickListener(chipClickListener);
        ((MaterialButton) view.findViewById(R.id.chipParty)).setOnClickListener(chipClickListener);
        ((MaterialButton) view.findViewById(R.id.chipSoundtrack)).setOnClickListener(chipClickListener);
        ((MaterialButton) view.findViewById(R.id.chipAbend)).setOnClickListener(chipClickListener);
        ((MaterialButton) view.findViewById(R.id.chipUnderrated)).setOnClickListener(chipClickListener);
        ((MaterialButton) view.findViewById(R.id.chipCurrent)).setOnClickListener(chipClickListener);
        MaterialButton materialButton2 = this.btnGenerate;
        if (materialButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnGenerate");
            materialButton2 = null;
        }
        materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GenerateFragment.setupViews$lambda$4(GenerateFragment.this, view2);
            }
        });
        MaterialButton materialButton3 = this.btnSmartGenerate;
        if (materialButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnSmartGenerate");
            materialButton3 = null;
        }
        materialButton3.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GenerateFragment.setupViews$lambda$5(GenerateFragment.this, view2);
            }
        });
        MaterialButton materialButton4 = this.btnSendToPlaylist;
        if (materialButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnSendToPlaylist");
            materialButton4 = null;
        }
        materialButton4.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GenerateFragment.setupViews$lambda$6(GenerateFragment.this, view2);
            }
        });
        MaterialButton materialButton5 = this.btnSendToQueue;
        if (materialButton5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnSendToQueue");
            materialButton5 = null;
        }
        materialButton5.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GenerateFragment.setupViews$lambda$7(GenerateFragment.this, view2);
            }
        });
        MaterialButton materialButton6 = this.btnPlayNext;
        if (materialButton6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnPlayNext");
        } else {
            materialButton = materialButton6;
        }
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GenerateFragment.setupViews$lambda$8(GenerateFragment.this, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$0(GenerateFragment this$0, View it) {
        TextView textView = this$0.tvStatus;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvStatus");
            textView = null;
        }
        textView.setText("Bereit zur Wiedergabelisten-Erstellung.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$1(GenerateFragment this$0, View it) {
        Object systemService = this$0.requireContext().getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipboardManager clipboard = (ClipboardManager) systemService;
        TextView textView = this$0.tvStatus;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvStatus");
            textView = null;
        }
        ClipData clip = ClipData.newPlainText(r1, textView.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this$0.requireContext(), "Log kopiert!", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$2(GenerateFragment this$0, TextView $btnToggleSelection, View it) {
        RecyclerView recyclerView = this$0.rvSuggestions;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvSuggestions");
            recyclerView = null;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        TrackAdapter adapter2 = adapter instanceof TrackAdapter ? (TrackAdapter) adapter : null;
        if (adapter2 != null) {
            adapter2.setSelectionModeEnabled(!adapter2.getIsSelectionModeEnabled());
            $btnToggleSelection.setText(adapter2.getIsSelectionModeEnabled() ? "AUSWAHL AUFHEBEN" : "MEHRERE AUSWÄHLEN");
            this$0.backPressedCallback.setEnabled(adapter2.getIsSelectionModeEnabled());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$3(GenerateFragment this$0, View v) {
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
        MainActivity mainActivity = (MainActivity) requireActivity;
        if (mainActivity.hasPermission()) {
            Intrinsics.checkNotNull(v, "null cannot be cast to non-null type com.google.android.material.button.MaterialButton");
            String promptText = ((MaterialButton) v).getText().toString();
            if (Intrinsics.areEqual(promptText, "Aktueller Song")) {
                if (mainActivity.getCurrentPlayingInfo() != null) {
                    promptText = "Musik ähnlich wie: " + mainActivity.getCurrentPlayingInfo();
                } else {
                    promptText = "Spiele einen ähnlichen Vibe wie der letzte Song nochmal";
                }
            }
            TextInputEditText textInputEditText = this$0.etPrompt;
            if (textInputEditText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("etPrompt");
                textInputEditText = null;
            }
            textInputEditText.setText(promptText);
            this$0.generateSuggestions(promptText);
            return;
        }
        mainActivity.checkPermissions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$4(GenerateFragment this$0, View it) {
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
        MainActivity mainActivity = (MainActivity) requireActivity;
        if (mainActivity.hasPermission()) {
            this$0.generateSuggestions(null);
        } else {
            mainActivity.checkPermissions();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$5(GenerateFragment this$0, View it) {
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
        MainActivity mainActivity = (MainActivity) requireActivity;
        if (mainActivity.hasPermission()) {
            TextInputEditText textInputEditText = this$0.etPrompt;
            if (textInputEditText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("etPrompt");
                textInputEditText = null;
            }
            String prompt = StringsKt.trim((CharSequence) String.valueOf(textInputEditText.getText())).toString();
            if (prompt.length() == 0) {
                Toast.makeText(this$0.requireContext(), "Bitte zuerst einen Prompt eingeben.", 0).show();
                return;
            } else {
                this$0.generateSuggestions(prompt);
                return;
            }
        }
        mainActivity.checkPermissions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$6(GenerateFragment this$0, View it) {
        List tracksToExport;
        Set<PowerampTrack> selectedTracks;
        if (!this$0.getViewModel().getGeneratedTracks().isEmpty()) {
            RecyclerView recyclerView = this$0.rvSuggestions;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rvSuggestions");
                recyclerView = null;
            }
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            TrackAdapter adapter2 = adapter instanceof TrackAdapter ? (TrackAdapter) adapter : null;
            boolean z = false;
            if (adapter2 != null && (selectedTracks = adapter2.getSelectedTracks()) != null && (!selectedTracks.isEmpty())) {
                z = true;
            }
            if (z) {
                tracksToExport = CollectionsKt.toList(adapter2.getSelectedTracks());
            } else {
                tracksToExport = this$0.getViewModel().getGeneratedTracks();
            }
            this$0.logMessage("Exportiere nach Poweramp...");
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0.getViewLifecycleOwner()), Dispatchers.getIO(), null, new GenerateFragment$setupViews$6$1(this$0, tracksToExport, adapter2, null), 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$7(GenerateFragment this$0, View it) {
        List tracksToExport;
        Set<PowerampTrack> selectedTracks;
        if (!this$0.getViewModel().getGeneratedTracks().isEmpty()) {
            RecyclerView recyclerView = this$0.rvSuggestions;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rvSuggestions");
                recyclerView = null;
            }
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            TrackAdapter adapter2 = adapter instanceof TrackAdapter ? (TrackAdapter) adapter : null;
            boolean z = false;
            if (adapter2 != null && (selectedTracks = adapter2.getSelectedTracks()) != null && (!selectedTracks.isEmpty())) {
                z = true;
            }
            if (z) {
                tracksToExport = CollectionsKt.toList(adapter2.getSelectedTracks());
            } else {
                tracksToExport = this$0.getViewModel().getGeneratedTracks();
            }
            this$0.logMessage("Zur Warteschlange hinzufügen...");
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0.getViewLifecycleOwner()), Dispatchers.getIO(), null, new GenerateFragment$setupViews$7$1(this$0, tracksToExport, adapter2, null), 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$8(GenerateFragment this$0, View it) {
        List tracksToExport;
        Set<PowerampTrack> selectedTracks;
        if (!this$0.getViewModel().getGeneratedTracks().isEmpty()) {
            RecyclerView recyclerView = this$0.rvSuggestions;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rvSuggestions");
                recyclerView = null;
            }
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            TrackAdapter adapter2 = adapter instanceof TrackAdapter ? (TrackAdapter) adapter : null;
            boolean z = false;
            if (adapter2 != null && (selectedTracks = adapter2.getSelectedTracks()) != null && (!selectedTracks.isEmpty())) {
                z = true;
            }
            if (z) {
                tracksToExport = CollectionsKt.toList(adapter2.getSelectedTracks());
            } else {
                tracksToExport = this$0.getViewModel().getGeneratedTracks();
            }
            this$0.logMessage("Als Nächstes einreihen...");
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0.getViewLifecycleOwner()), Dispatchers.getIO(), null, new GenerateFragment$setupViews$8$1(this$0, tracksToExport, adapter2, null), 2, null);
        }
    }

    private final void generateSuggestions(String prompt) {
        showLoading(true);
        getViewModel().setLastPrompt(prompt == null ? "" : prompt);
        getViewModel().setGenerating(true);
        TextInputEditText textInputEditText = this.etMaxTracks;
        if (textInputEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etMaxTracks");
            textInputEditText = null;
        }
        String maxTracksStr = String.valueOf(textInputEditText.getText());
        TextInputEditText textInputEditText2 = this.etMaxDuration;
        if (textInputEditText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etMaxDuration");
            textInputEditText2 = null;
        }
        String maxDurationStr = String.valueOf(textInputEditText2.getText());
        Integer limitTracks = StringsKt.toIntOrNull(maxTracksStr);
        Long limitDurationMs = StringsKt.toIntOrNull(maxDurationStr) != null ? Long.valueOf(r3.intValue() * 60 * 1000) : null;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(getViewLifecycleOwner()), Dispatchers.getIO(), null, new GenerateFragment$generateSuggestions$1(this, prompt, limitTracks, limitDurationMs, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading(boolean active) {
        TextView textView;
        MaterialButton materialButton = this.btnGenerate;
        LinearLayout linearLayout = null;
        if (materialButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnGenerate");
            materialButton = null;
        }
        materialButton.setEnabled(!active);
        MaterialButton materialButton2 = this.btnSmartGenerate;
        if (materialButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnSmartGenerate");
            materialButton2 = null;
        }
        materialButton2.setEnabled(!active);
        CircularProgressIndicator circularProgressIndicator = this.progressIndicator;
        if (circularProgressIndicator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressIndicator");
            circularProgressIndicator = null;
        }
        circularProgressIndicator.setVisibility(active ? 0 : 8);
        if (active) {
            LinearLayout linearLayout2 = this.layoutExport;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutExport");
            } else {
                linearLayout = linearLayout2;
            }
            linearLayout.setVisibility(8);
            View view = getView();
            if (view == null || (textView = (TextView) view.findViewById(R.id.btnToggleSelection)) == null) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayTracks(List<PowerampTrack> tracks) {
        if (tracks.isEmpty()) {
            return;
        }
        logMessage(tracks.size() + " passende Tracks gefunden!");
        View view = getView();
        LinearLayout linearLayout = null;
        final TextView btnToggleSelection = view != null ? (TextView) view.findViewById(R.id.btnToggleSelection) : null;
        TrackAdapter adapter = new TrackAdapter(tracks, new Function1() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit displayTracks$lambda$10;
                displayTracks$lambda$10 = GenerateFragment.displayTracks$lambda$10(GenerateFragment.this, (PowerampTrack) obj);
                return displayTracks$lambda$10;
            }
        }, new Function1() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit displayTracks$lambda$11;
                displayTracks$lambda$11 = GenerateFragment.displayTracks$lambda$11(GenerateFragment.this, (PowerampTrack) obj);
                return displayTracks$lambda$11;
            }
        }, new Function1() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit displayTracks$lambda$12;
                displayTracks$lambda$12 = GenerateFragment.displayTracks$lambda$12(GenerateFragment.this, (PowerampTrack) obj);
                return displayTracks$lambda$12;
            }
        }, new Function0() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit displayTracks$lambda$13;
                displayTracks$lambda$13 = GenerateFragment.displayTracks$lambda$13(GenerateFragment.this, btnToggleSelection);
                return displayTracks$lambda$13;
            }
        }, new Function1() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit displayTracks$lambda$14;
                displayTracks$lambda$14 = GenerateFragment.displayTracks$lambda$14(btnToggleSelection, this, ((Integer) obj).intValue());
                return displayTracks$lambda$14;
            }
        });
        RecyclerView recyclerView = this.rvSuggestions;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvSuggestions");
            recyclerView = null;
        }
        recyclerView.setAdapter(adapter);
        LinearLayout linearLayout2 = this.layoutExport;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutExport");
        } else {
            linearLayout = linearLayout2;
        }
        linearLayout.setVisibility(0);
        if (btnToggleSelection != null) {
            btnToggleSelection.setVisibility(0);
        }
        if (btnToggleSelection != null) {
            btnToggleSelection.setText("MEHRERE AUSWÄHLEN");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit displayTracks$lambda$10(GenerateFragment this$0, PowerampTrack clickedTrack) {
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
        ((MainActivity) requireActivity).getPowerampController().playTrack(clickedTrack.getId());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit displayTracks$lambda$11(GenerateFragment this$0, PowerampTrack clickedTrack) {
        this$0.logMessage("\"" + clickedTrack.getTitle() + "\" zur Warteschlange...");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0.getViewLifecycleOwner()), Dispatchers.getIO(), null, new GenerateFragment$displayTracks$adapter$2$1(this$0, clickedTrack, null), 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit displayTracks$lambda$12(GenerateFragment this$0, PowerampTrack clickedTrack) {
        this$0.logMessage("\"" + clickedTrack.getTitle() + "\" als nächstes spielen...");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0.getViewLifecycleOwner()), Dispatchers.getIO(), null, new GenerateFragment$displayTracks$adapter$3$1(this$0, clickedTrack, null), 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit displayTracks$lambda$13(GenerateFragment this$0, TextView $btnToggleSelection) {
        LinearLayout linearLayout = this$0.layoutExport;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutExport");
            linearLayout = null;
        }
        linearLayout.setVisibility(0);
        if ($btnToggleSelection != null) {
            $btnToggleSelection.setVisibility(0);
        }
        if ($btnToggleSelection != null) {
            $btnToggleSelection.setText("AUSWAHL AUFHEBEN");
        }
        this$0.backPressedCallback.setEnabled(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit displayTracks$lambda$14(TextView $btnToggleSelection, GenerateFragment this$0, int count) {
        if ($btnToggleSelection != null) {
            $btnToggleSelection.setText(count == 0 ? "MEHRERE AUSWÄHLEN" : count + " AUSGEWÄHLT");
        }
        if (count == -1) {
            this$0.backPressedCallback.setEnabled(false);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveToHistory(String prompt) {
        if (StringsKt.isBlank(prompt)) {
            return;
        }
        SharedPreferences prefs = requireActivity().getSharedPreferences("PowerampCompanionPrefs", 0);
        String raw = prefs.getString("gen_history", HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        JSONArray arr = new JSONArray(raw);
        List historyList = new ArrayList();
        int length = arr.length();
        for (int i = 0; i < length; i++) {
            historyList.add(arr.getString(i));
        }
        historyList.remove(prompt);
        historyList.add(0, prompt);
        JSONArray trimmed = new JSONArray();
        Iterator it = CollectionsKt.take(historyList, 3).iterator();
        while (it.hasNext()) {
            trimmed.put((String) it.next());
        }
        prefs.edit().putString("gen_history", trimmed.toString()).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadHistoryChips() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("PowerampCompanionPrefs", 0);
        String raw = prefs.getString("gen_history", HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        JSONArray arr = new JSONArray(raw);
        View view = null;
        if (arr.length() == 0) {
            View view2 = this.layoutHistory;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutHistory");
            } else {
                view = view2;
            }
            view.setVisibility(8);
            return;
        }
        View view3 = this.layoutHistory;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutHistory");
            view3 = null;
        }
        view3.setVisibility(0);
        ChipGroup chipGroup = this.cgHistory;
        if (chipGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cgHistory");
            chipGroup = null;
        }
        chipGroup.removeAllViews();
        int length = arr.length();
        for (int i = 0; i < length; i++) {
            final String text = arr.getString(i);
            Chip chip = new Chip(requireContext());
            chip.setText(text.length() > 28 ? StringsKt.take(text, 25) + "…" : text);
            chip.setCheckable(false);
            chip.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$$ExternalSyntheticLambda14
                @Override // android.view.View.OnClickListener
                public final void onClick(View view4) {
                    GenerateFragment.loadHistoryChips$lambda$17$lambda$16(GenerateFragment.this, text, view4);
                }
            });
            ChipGroup chipGroup2 = this.cgHistory;
            if (chipGroup2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cgHistory");
                chipGroup2 = null;
            }
            chipGroup2.addView(chip);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadHistoryChips$lambda$17$lambda$16(GenerateFragment this$0, String $text, View it) {
        TextInputEditText textInputEditText = this$0.etPrompt;
        if (textInputEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etPrompt");
            textInputEditText = null;
        }
        textInputEditText.setText($text);
        this$0.generateSuggestions($text);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logMessage(String message) {
        if (isAdded()) {
            String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(getViewLifecycleOwner()), Dispatchers.getMain(), null, new GenerateFragment$logMessage$1(this, time, message, null), 2, null);
        }
    }
}
