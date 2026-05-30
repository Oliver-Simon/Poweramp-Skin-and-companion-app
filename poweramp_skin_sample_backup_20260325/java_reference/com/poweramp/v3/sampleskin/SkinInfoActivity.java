package com.poweramp.v3.sampleskin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: SkinInfoActivity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014J\u0010\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u000b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\r"}, d2 = {"Lcom/poweramp/v3/sampleskin/SkinInfoActivity;", "Landroid/app/Activity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "startWithSampleSkin", "view", "Landroid/view/View;", "openPowerampThemeSettings", "Companion", "poweramp_skin_sample_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class SkinInfoActivity extends Activity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_info);
    }

    public final void startWithSampleSkin(View view) {
        String pak = INSTANCE.getPowerampPackageName(this);
        if (pak == null) {
            Toast.makeText(this, R.string.skin_poweramp_not_installed, 1).show();
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN").setClassName(pak, "com.maxmpz.audioplayer.StartupActivity").putExtra("theme_pak", getPackageName()).putExtra("theme_id", R.style.SampleSkin);
        startActivity(intent);
        finish();
    }

    public final void openPowerampThemeSettings(View view) {
        String pak = INSTANCE.getPowerampPackageName(this);
        if (pak == null) {
            Toast.makeText(this, R.string.skin_poweramp_not_installed, 1).show();
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN").setClassName(pak, "com.maxmpz.audioplayer.SettingsActivity").putExtra("open", "theme").putExtra("theme_pak", getPackageName()).putExtra("theme_id", R.style.SampleSkin);
        startActivity(intent);
        finish();
    }

    /* compiled from: SkinInfoActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/poweramp/v3/sampleskin/SkinInfoActivity$Companion;", "", "<init>", "()V", "getPowerampPackageName", "", "context", "Landroid/content/Context;", "poweramp_skin_sample_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getPowerampPackageName(Context context) {
            try {
                ResolveInfo info = context.getPackageManager().resolveService(new Intent("com.maxmpz.audioplayer.API_COMMAND"), 0);
                if (info != null && info.serviceInfo != null) {
                    return info.serviceInfo.packageName;
                }
                return null;
            } catch (Throwable th) {
                Log.e("SkinInfoActivity", "", th);
                return null;
            }
        }
    }
}
