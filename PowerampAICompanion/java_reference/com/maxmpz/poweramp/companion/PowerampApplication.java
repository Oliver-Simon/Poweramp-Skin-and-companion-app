package com.maxmpz.poweramp.companion;

import android.app.Application;
import com.google.android.material.color.DynamicColors;
import kotlin.Metadata;

/* compiled from: PowerampApplication.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/maxmpz/poweramp/companion/PowerampApplication;", "Landroid/app/Application;", "<init>", "()V", "onCreate", "", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class PowerampApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}
