package com.maxmpz.poweramp.companion;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: DiscoveryFragment.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0002J\u0012\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/maxmpz/poweramp/companion/DiscoveryFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "rvDiscovery", "Landroidx/recyclerview/widget/RecyclerView;", "swipeRefresh", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "progressDiscovery", "Lcom/google/android/material/progressindicator/CircularProgressIndicator;", "layoutEmpty", "Landroid/view/View;", "currentDiscoveryType", "", "onViewCreated", "", "view", "savedInstanceState", "Landroid/os/Bundle;", "showDebugDialog", "startDiscovery", "isRefresh", "", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class DiscoveryFragment extends Fragment {
    private String currentDiscoveryType;
    private View layoutEmpty;
    private CircularProgressIndicator progressDiscovery;
    private RecyclerView rvDiscovery;
    private SwipeRefreshLayout swipeRefresh;

    public DiscoveryFragment() {
        super(R.layout.fragment_discovery);
        this.currentDiscoveryType = "ALL";
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rvDiscovery = (RecyclerView) view.findViewById(R.id.rvDiscovery);
        this.swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshDiscovery);
        this.progressDiscovery = (CircularProgressIndicator) view.findViewById(R.id.progressDiscovery);
        this.layoutEmpty = view.findViewById(R.id.layoutEmptyDiscovery);
        RecyclerView recyclerView = this.rvDiscovery;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvDiscovery");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        MaterialButtonToggleGroup toggleGroup = (MaterialButtonToggleGroup) view.findViewById(R.id.toggleDiscoveryType);
        toggleGroup.check(R.id.btnDiscoveryAll);
        toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() { // from class: com.maxmpz.poweramp.companion.DiscoveryFragment$$ExternalSyntheticLambda0
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public final void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
                DiscoveryFragment.onViewCreated$lambda$0(DiscoveryFragment.this, materialButtonToggleGroup, i, z);
            }
        });
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefresh;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefresh");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.maxmpz.poweramp.companion.DiscoveryFragment$$ExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                DiscoveryFragment.this.startDiscovery(true);
            }
        });
        View findViewById = view.findViewById(R.id.fragment_discovery_title);
        if (findViewById != null) {
            findViewById.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.maxmpz.poweramp.companion.DiscoveryFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    boolean onViewCreated$lambda$2;
                    onViewCreated$lambda$2 = DiscoveryFragment.onViewCreated$lambda$2(DiscoveryFragment.this, view2);
                    return onViewCreated$lambda$2;
                }
            });
        }
        startDiscovery$default(this, false, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(DiscoveryFragment this$0, MaterialButtonToggleGroup materialButtonToggleGroup, int checkedId, boolean isChecked) {
        String str;
        if (isChecked) {
            if (checkedId == R.id.btnDiscoveryTracks) {
                str = "SONG";
            } else if (checkedId == R.id.btnDiscoveryAlbums) {
                str = "ALBUM";
            } else {
                str = checkedId == R.id.btnDiscoveryArtists ? "ARTIST" : "ALL";
            }
            this$0.currentDiscoveryType = str;
            this$0.startDiscovery(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$2(DiscoveryFragment this$0, View it) {
        this$0.showDebugDialog();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDebugDialog() {
        final String message = RecommendationEngine.INSTANCE.getLastDebugInfo();
        new AlertDialog.Builder(requireContext()).setTitle("🔍 Discovery Debug Info").setMessage(message).setPositiveButton("Kopieren", new DialogInterface.OnClickListener() { // from class: com.maxmpz.poweramp.companion.DiscoveryFragment$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DiscoveryFragment.showDebugDialog$lambda$3(DiscoveryFragment.this, message, dialogInterface, i);
            }
        }).setNegativeButton("Schließen", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDebugDialog$lambda$3(DiscoveryFragment this$0, String $message, DialogInterface dialogInterface, int i) {
        Object systemService = this$0.requireContext().getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipboardManager clipboard = (ClipboardManager) systemService;
        ClipData clip = ClipData.newPlainText("DiscoveryDebug", $message);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this$0.getContext(), "Kopiert!", 0).show();
    }

    static /* synthetic */ void startDiscovery$default(DiscoveryFragment discoveryFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        discoveryFragment.startDiscovery(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDiscovery(boolean isRefresh) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
        MainActivity mainActivity = (MainActivity) requireActivity;
        String apiKey = requireContext().getSharedPreferences("PowerampCompanionPrefs", 0).getString("gemini_api_key", "");
        String str = apiKey;
        SwipeRefreshLayout swipeRefreshLayout = null;
        if (str == null || StringsKt.isBlank(str)) {
            Toast.makeText(requireContext(), "Bitte Gemini API Key in den Einstellungen hinterlegen!", 1).show();
            View view = this.layoutEmpty;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutEmpty");
                view = null;
            }
            view.setVisibility(0);
            SwipeRefreshLayout swipeRefreshLayout2 = this.swipeRefresh;
            if (swipeRefreshLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("swipeRefresh");
            } else {
                swipeRefreshLayout = swipeRefreshLayout2;
            }
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        if (isRefresh) {
            SwipeRefreshLayout swipeRefreshLayout3 = this.swipeRefresh;
            if (swipeRefreshLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("swipeRefresh");
                swipeRefreshLayout3 = null;
            }
            swipeRefreshLayout3.setRefreshing(true);
        } else {
            CircularProgressIndicator circularProgressIndicator = this.progressDiscovery;
            if (circularProgressIndicator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressDiscovery");
                circularProgressIndicator = null;
            }
            circularProgressIndicator.setVisibility(0);
        }
        View view2 = this.layoutEmpty;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutEmpty");
            view2 = null;
        }
        view2.setVisibility(8);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(getViewLifecycleOwner()), null, null, new DiscoveryFragment$startDiscovery$1(this, mainActivity, apiKey, null), 3, null);
    }
}
