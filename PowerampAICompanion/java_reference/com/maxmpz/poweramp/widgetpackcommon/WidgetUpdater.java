package com.maxmpz.poweramp.widgetpackcommon;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.PowerManager;
import com.maxmpz.poweramp.player.PowerampAPI;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class WidgetUpdater {
    private static final boolean ALWAYS_USE_PERSISTANT_DATA = true;
    private static final boolean LOG = false;
    private static final String TAG = "WidgetUpdater";
    public static final String WIDGETS_PREFS_NAME = "appwidgets";
    private static SharedPreferences sCachedPrefs;
    private static boolean sUpdatedOnce;
    private final Context mContext;
    protected final Object mLock;
    private final PowerManager mPowerManager;
    protected final List<IWidgetUpdater> mProviders;
    public static final IntentFilter sTrackFilter = new IntentFilter(PowerampAPI.ACTION_TRACK_CHANGED);
    private static final IntentFilter sStatusFilter = new IntentFilter(PowerampAPI.ACTION_STATUS_CHANGED);
    private static final IntentFilter sModeFilter = new IntentFilter(PowerampAPI.ACTION_PLAYING_MODE_CHANGED);

    protected abstract void loadDefaultOrPersistantUpdateData(Context context, WidgetUpdateData widgetUpdateData);

    public WidgetUpdater(Context context) {
        this.mLock = new Object();
        this.mProviders = new ArrayList(4);
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            throw new AssertionError();
        }
        this.mPowerManager = powerManager;
        this.mContext = context;
    }

    public WidgetUpdater(Context context, BaseWidgetProvider prov) {
        this(context);
        synchronized (this.mLock) {
            this.mProviders.add(prov);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateSafe(BaseWidgetProvider provider, boolean ignorePowerState, boolean updateByOs, int[] appWidgetIds) {
        synchronized (this.mLock) {
            if (!ignorePowerState) {
                if (!this.mPowerManager.isInteractive() && sUpdatedOnce) {
                    return;
                }
            }
            WidgetUpdateData data = generateUpdateData(this.mContext);
            pushUpdateCore(data, appWidgetIds);
        }
    }

    private void pushUpdateCore(WidgetUpdateData data, int[] ids) {
        SharedPreferences prefs = getCachedSharedPreferences(this.mContext);
        for (IWidgetUpdater prov : this.mProviders) {
            prov.pushUpdate(this.mContext, prefs, ids, false, data);
        }
        if (data.hasTrack && !sUpdatedOnce) {
            sUpdatedOnce = ALWAYS_USE_PERSISTANT_DATA;
        }
    }

    public boolean updateDirectSafe(WidgetUpdateData data, boolean ignorePowerState, boolean isScreenOn) {
        synchronized (this.mLock) {
            if (!ignorePowerState && !isScreenOn) {
                if (sUpdatedOnce) {
                    return false;
                }
            }
            pushUpdateCore(data, null);
            return ALWAYS_USE_PERSISTANT_DATA;
        }
    }

    public static SharedPreferences getCachedSharedPreferences(Context context) {
        SharedPreferences cachedPrefs = sCachedPrefs;
        if (cachedPrefs == null) {
            Context app = context.getApplicationContext();
            SharedPreferences cachedPrefs2 = app.getSharedPreferences(WIDGETS_PREFS_NAME, 0);
            sCachedPrefs = cachedPrefs2;
            return cachedPrefs2;
        }
        return cachedPrefs;
    }

    public static String getOldSharedPreferencesName(Context context) {
        return context.getPackageName() + "_appwidgets";
    }

    public WidgetUpdateData generateUpdateData(Context context) {
        WidgetUpdateData data = new WidgetUpdateData();
        getPlayingState(context, data);
        loadDefaultOrPersistantUpdateData(context, data);
        return data;
    }

    private void getPlayingState(Context context, WidgetUpdateData data) {
        Intent statusIntent = context.registerReceiver(null, sStatusFilter);
        if (statusIntent != null) {
            boolean paused = statusIntent.getBooleanExtra("paused", ALWAYS_USE_PERSISTANT_DATA);
            data.playing = paused ^ ALWAYS_USE_PERSISTANT_DATA;
            data.apiVersion = statusIntent.getIntExtra("api", 0);
        }
    }
}
