package com.maxmpz.poweramp.companion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InsightsBottomSheetFragment.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/maxmpz/poweramp/companion/InsightsBottomSheetFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "<init>", "()V", "tvInsightsContent", "Landroid/widget/TextView;", "insightsProgressIndicator", "Lcom/google/android/material/progressindicator/LinearProgressIndicator;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setInsightsText", "", "text", "", "showError", "error", "Companion", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class InsightsBottomSheetFragment extends BottomSheetDialogFragment {
    public static final String TAG = "InsightsBottomSheet";
    private LinearProgressIndicator insightsProgressIndicator;
    private TextView tvInsightsContent;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_insights_bottom_sheet, container, false);
        this.tvInsightsContent = (TextView) view.findViewById(R.id.tvInsightsContent);
        this.insightsProgressIndicator = (LinearProgressIndicator) view.findViewById(R.id.insightsProgressIndicator);
        return view;
    }

    public final void setInsightsText(String text) {
        if (this.tvInsightsContent != null) {
            TextView textView = this.tvInsightsContent;
            LinearProgressIndicator linearProgressIndicator = null;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvInsightsContent");
                textView = null;
            }
            textView.setText(text);
            LinearProgressIndicator linearProgressIndicator2 = this.insightsProgressIndicator;
            if (linearProgressIndicator2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("insightsProgressIndicator");
            } else {
                linearProgressIndicator = linearProgressIndicator2;
            }
            linearProgressIndicator.setVisibility(8);
        }
    }

    public final void showError(String error) {
        if (this.tvInsightsContent != null) {
            TextView textView = this.tvInsightsContent;
            LinearProgressIndicator linearProgressIndicator = null;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvInsightsContent");
                textView = null;
            }
            textView.setText("Fehler bei der Analyse:\n" + error);
            TextView textView2 = this.tvInsightsContent;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvInsightsContent");
                textView2 = null;
            }
            textView2.setTextColor(getResources().getColor(android.R.color.holo_red_dark, null));
            LinearProgressIndicator linearProgressIndicator2 = this.insightsProgressIndicator;
            if (linearProgressIndicator2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("insightsProgressIndicator");
            } else {
                linearProgressIndicator = linearProgressIndicator2;
            }
            linearProgressIndicator.setVisibility(8);
        }
    }
}
