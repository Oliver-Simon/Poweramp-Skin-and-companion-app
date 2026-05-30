package com.maxmpz.poweramp.widgetpackcommon;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

/* loaded from: classes2.dex */
public abstract class BaseWidgetProvider extends AppWidgetProvider implements IWidgetUpdater {
    public static final int API_VERSION_200 = 200;
    private static final boolean LOG = false;
    protected static final int MIN_SIZE = 1;
    private static final String TAG = "BaseWidgetProvider";
    private AppWidgetManager mAppWidgetManager;
    private ComponentName mComponentName;

    /* loaded from: classes2.dex */
    public static final class RepeatModeV140 {
        public static final int REPEAT_ALL = 1;
        public static final int REPEAT_CAT = 3;
        public static final int REPEAT_NONE = 0;
        public static final int REPEAT_SONG = 2;
    }

    /* loaded from: classes2.dex */
    public static final class ShuffleModeV140 {
        public static final int SHUFFLE_ALL = 1;
        public static final int SHUFFLE_HIER = 3;
        public static final int SHUFFLE_IN_CAT = 2;
        public static final int SHUFFLE_NONE = 0;
    }

    /* loaded from: classes2.dex */
    public static class WidgetContext {
        public int id;
        public long lastAATimeStamp;
    }

    protected abstract WidgetUpdater getWidgetUpdater(Context context);

    public abstract RemoteViews update(Context context, WidgetUpdateData widgetUpdateData, SharedPreferences sharedPreferences, int i);

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        if (appWidgetIds.length == 0) {
            return;
        }
        WidgetUpdater widgetUpdater = getWidgetUpdater(context);
        try {
            widgetUpdater.updateSafe(this, true, true, appWidgetIds);
        } catch (Throwable th) {
            Log.e(TAG, "", th);
        }
    }

    @Override // com.maxmpz.poweramp.widgetpackcommon.IWidgetUpdater
    public WidgetUpdateData pushUpdate(Context context, SharedPreferences prefs, int[] ids, boolean mediaRemoved, WidgetUpdateData data) {
        AppWidgetManager appWidgetManager = this.mAppWidgetManager;
        if (appWidgetManager == null) {
            AppWidgetManager appWidgetManager2 = AppWidgetManager.getInstance(context);
            this.mAppWidgetManager = appWidgetManager2;
            appWidgetManager = appWidgetManager2;
        }
        if (ids == null) {
            try {
                if (this.mComponentName == null) {
                    this.mComponentName = new ComponentName(context, getClass());
                }
                ids = appWidgetManager.getAppWidgetIds(this.mComponentName);
            } catch (Exception ex) {
                Log.e(TAG, "", ex);
            }
        }
        if (ids == null || ids.length == 0) {
            return null;
        }
        try {
            for (int id : ids) {
                if (id == 0) {
                    break;
                }
                RemoteViews rv = update(context, data, prefs, id);
                appWidgetManager.updateAppWidget(id, rv);
            }
        } catch (Exception ex2) {
            Log.e(TAG, "", ex2);
        }
        return data;
    }

    protected boolean getAANoAnimState(WidgetUpdateData data, WidgetContext widgetCtx) {
        if (data.albumArtNoAnim || widgetCtx.lastAATimeStamp == data.albumArtTimestamp) {
            return true;
        }
        if (data.hasTrack && (data.flags & 64) != 0) {
            return true;
        }
        return false;
    }

    public static String getReadable(String title, String unknown) {
        return getReadable(title, unknown, false);
    }

    public static String getReadable(String title, String unknown, boolean allowEmpty) {
        if (title != null && (allowEmpty || title.length() > 0)) {
            return title;
        }
        return unknown;
    }
}
