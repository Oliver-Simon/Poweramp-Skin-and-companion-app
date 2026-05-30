package com.maxmpz.poweramp.equalizer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.maxmpz.poweramp.player.PowerampAPI;

/* loaded from: classes4.dex */
public class PeqAPIHelper {
    private static final boolean LOG = false;
    private static final String TAG = "PeqAPIHelper";
    private static ComponentName sApiActivityComponentName;
    private static ComponentName sApiReceiverComponentName;
    private static int sBuild;
    private static ComponentName sMilkScanServiceComponentName;
    private static String sPak;

    public static String getPackageName(Context context) {
        ComponentName activityName;
        String pak = sPak;
        if (pak == null && (activityName = getApiActivityComponentName(context)) != null) {
            String pak2 = activityName.getPackageName();
            sPak = pak2;
            return pak2;
        }
        return pak;
    }

    public static ComponentName getApiActivityComponentName(Context context) {
        ComponentName componentName = sApiActivityComponentName;
        if (componentName == null) {
            try {
                ResolveInfo info = context.getPackageManager().resolveActivity(new Intent(PeqAPI.ACTION_API_COMMAND), 0);
                if (info != null && info.activityInfo != null) {
                    ComponentName componentName2 = new ComponentName(info.activityInfo.packageName, info.activityInfo.name);
                    sApiActivityComponentName = componentName2;
                    return componentName2;
                }
                return componentName;
            } catch (Throwable th) {
                Log.e(TAG, "", th);
                return componentName;
            }
        }
        return componentName;
    }

    public static ComponentName getMilkScannerServiceComponentName(Context context) {
        ComponentName componentName = sMilkScanServiceComponentName;
        if (componentName == null) {
            try {
                ResolveInfo info = context.getPackageManager().resolveService(new Intent(PowerampAPI.MilkScanner.ACTION_SCAN).setPackage(getPackageName(context)), 0);
                if (info != null && info.serviceInfo != null) {
                    ComponentName componentName2 = new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name);
                    sMilkScanServiceComponentName = componentName2;
                    return componentName2;
                }
                return componentName;
            } catch (Throwable th) {
                Log.e(TAG, "", th);
                return componentName;
            }
        }
        return componentName;
    }

    public static ComponentName getApiReceiverComponentName(Context context) {
        String pak;
        ComponentName componentName = sApiReceiverComponentName;
        if (componentName == null && (pak = getPackageName(context)) != null) {
            ComponentName componentName2 = new ComponentName(pak, PeqAPI.API_RECEIVER_NAME);
            sApiReceiverComponentName = componentName2;
            return componentName2;
        }
        return componentName;
    }

    public static int getPowerampBuild(Context context) {
        String pak;
        if (sBuild == 0 && (pak = getPackageName(context)) != null) {
            try {
                PackageInfo pi = context.getPackageManager().getPackageInfo(pak, 0);
                sBuild = pi.versionCode > 1000 ? pi.versionCode / 1000 : pi.versionCode;
            } catch (Throwable th) {
                Log.e(TAG, "", th);
            }
        }
        return sBuild;
    }

    public static Intent newAPIIntent(Context context) {
        return new Intent(PeqAPI.ACTION_API_COMMAND);
    }

    public static void sendPAIntent(Context context, Intent intent) {
        sendPAIntent(context, intent, false);
    }

    public static void sendPAIntent(Context context, Intent intent, boolean sendToActivity) {
        intent.putExtra("pak", context.getPackageName());
        if (sendToActivity) {
            intent.setComponent(getApiActivityComponentName(context));
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            context.startActivity(intent);
            return;
        }
        intent.setComponent(getApiReceiverComponentName(context));
        context.sendBroadcast(intent);
    }
}
