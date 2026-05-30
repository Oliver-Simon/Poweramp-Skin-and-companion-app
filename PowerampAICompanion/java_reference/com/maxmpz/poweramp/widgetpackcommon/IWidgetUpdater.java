package com.maxmpz.poweramp.widgetpackcommon;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public interface IWidgetUpdater {
    WidgetUpdateData pushUpdate(Context context, SharedPreferences sharedPreferences, int[] iArr, boolean z, WidgetUpdateData widgetUpdateData);
}
