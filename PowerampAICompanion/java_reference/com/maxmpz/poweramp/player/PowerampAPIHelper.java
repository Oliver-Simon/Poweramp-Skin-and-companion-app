package com.maxmpz.poweramp.player;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.maxmpz.poweramp.player.PowerampAPI;
import java.io.FileNotFoundException;

/* loaded from: classes3.dex */
public class PowerampAPIHelper {
    private static final boolean DEBUG_ALWAYS_SEND_TO_SERVICE = false;
    private static final boolean LOG = false;
    private static final String TAG = "PowerampAPIHelper";
    private static ComponentName sApiActivityComponentName;
    private static ComponentName sApiReceiverComponentName;
    private static ComponentName sBrowserServiceComponentName;
    private static ComponentName sMilkScanServiceComponentName;
    private static int sPowerampBuild;
    private static ComponentName sPowerampPSComponentName;
    private static String sPowerampPak;
    private static ComponentName sScanServiceComponentName;

    public static String getPowerampPackageName(Context context) {
        String pak = sPowerampPak;
        if (pak == null) {
            ComponentName componentName = getPlayerServiceComponentNameImpl(context);
            if (componentName != null) {
                String pak2 = componentName.getPackageName();
                sPowerampPak = pak2;
                return pak2;
            }
            sPowerampPak = PowerampAPI.PACKAGE_NAME;
            return PowerampAPI.PACKAGE_NAME;
        }
        return pak;
    }

    @Deprecated
    public static ComponentName getPlayerServiceComponentName(Context context) {
        return getPlayerServiceComponentNameImpl(context);
    }

    private static ComponentName getPlayerServiceComponentNameImpl(Context context) {
        ComponentName componentName = sPowerampPSComponentName;
        if (componentName == null) {
            try {
                ResolveInfo info = context.getPackageManager().resolveService(new Intent(PowerampAPI.ACTION_API_COMMAND), 0);
                if (info != null && info.serviceInfo != null) {
                    ComponentName componentName2 = new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name);
                    sPowerampPSComponentName = componentName2;
                    return componentName2;
                }
                return componentName;
            } catch (Throwable th) {
                Log.e(TAG, "", th);
                ComponentName componentName3 = PowerampAPI.PLAYER_SERVICE_COMPONENT_NAME;
                sPowerampPSComponentName = componentName3;
                return componentName3;
            }
        }
        return componentName;
    }

    public static ComponentName getBrowserServiceComponentName(Context context) {
        ComponentName componentName = sBrowserServiceComponentName;
        if (componentName == null) {
            try {
                ResolveInfo info = context.getPackageManager().resolveService(new Intent("android.media.browse.MediaBrowserService").setPackage(getPowerampPackageName(context)), 0);
                if (info != null && info.serviceInfo != null) {
                    ComponentName componentName2 = new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name);
                    sBrowserServiceComponentName = componentName2;
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

    public static ComponentName getScannerServiceComponentName(Context context) {
        ComponentName componentName = sScanServiceComponentName;
        if (componentName == null) {
            try {
                ResolveInfo info = context.getPackageManager().resolveService(new Intent(PowerampAPI.Scanner.ACTION_SCAN_DIRS).setPackage(getPowerampPackageName(context)), 0);
                if (info != null && info.serviceInfo != null) {
                    ComponentName componentName2 = new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name);
                    sScanServiceComponentName = componentName2;
                    return componentName2;
                }
                return componentName;
            } catch (Throwable th) {
                Log.e(TAG, "", th);
                ComponentName componentName3 = new ComponentName(PowerampAPI.PACKAGE_NAME, PowerampAPI.Scanner.SCANNER_SERVICE_NAME);
                sScanServiceComponentName = componentName3;
                return componentName3;
            }
        }
        return componentName;
    }

    public static ComponentName getMilkScannerServiceComponentName(Context context) {
        ComponentName componentName = sMilkScanServiceComponentName;
        if (componentName == null) {
            try {
                ResolveInfo info = context.getPackageManager().resolveService(new Intent(PowerampAPI.MilkScanner.ACTION_SCAN).setPackage(getPowerampPackageName(context)), 0);
                if (info != null && info.serviceInfo != null) {
                    ComponentName componentName2 = new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name);
                    sMilkScanServiceComponentName = componentName2;
                    return componentName2;
                }
                return componentName;
            } catch (Throwable th) {
                Log.e(TAG, "", th);
                ComponentName componentName3 = new ComponentName(PowerampAPI.PACKAGE_NAME, PowerampAPI.MilkScanner.MILK_SCANNER_SERVICE_NAME);
                sMilkScanServiceComponentName = componentName3;
                return componentName3;
            }
        }
        return componentName;
    }

    public static ComponentName getApiReceiverComponentName(Context context) {
        String pak;
        ComponentName componentName = sApiReceiverComponentName;
        if (componentName == null && (pak = getPowerampPackageName(context)) != null) {
            ComponentName componentName2 = new ComponentName(pak, PowerampAPI.API_RECEIVER_NAME);
            sApiReceiverComponentName = componentName2;
            return componentName2;
        }
        return componentName;
    }

    public static ComponentName getApiActivityComponentName(Context context) {
        String pak;
        ComponentName componentName = sApiActivityComponentName;
        if (componentName == null && (pak = getPowerampPackageName(context)) != null) {
            ComponentName componentName2 = new ComponentName(pak, PowerampAPI.API_ACTIVITY_NAME);
            sApiActivityComponentName = componentName2;
            return componentName2;
        }
        return componentName;
    }

    public static int getPowerampBuild(Context context) {
        String pak;
        if (sPowerampBuild == 0 && (pak = getPowerampPackageName(context)) != null) {
            try {
                PackageInfo pi = context.getPackageManager().getPackageInfo(pak, 0);
                int powerampBuild = pi.versionCode > 1000 ? pi.versionCode / 1000 : pi.versionCode;
                sPowerampBuild = powerampBuild;
                return powerampBuild;
            } catch (Throwable th) {
                Log.e(TAG, "", th);
            }
        }
        sPowerampBuild = Integer.MAX_VALUE;
        return Integer.MAX_VALUE;
    }

    public static Intent newAPIIntent(Context context) {
        return new Intent(PowerampAPI.ACTION_API_COMMAND);
    }

    public static boolean sendPAIntent(Context context, Intent intent) {
        return sendPAIntent(context, intent, false);
    }

    public static boolean sendPAIntent(Context context, Intent intent, boolean sendToActivity) {
        try {
            int buildNum = getPowerampBuild(context);
            intent.putExtra("pak", context.getPackageName());
            if (sendToActivity && buildNum >= 862) {
                intent.setComponent(getApiActivityComponentName(context));
                if (!(context instanceof Activity)) {
                    intent.addFlags(268435456);
                }
                context.startActivity(intent);
                return true;
            }
            if (buildNum >= 855) {
                intent.setComponent(getApiReceiverComponentName(context));
                context.sendBroadcast(intent);
                return true;
            }
            intent.setComponent(getPlayerServiceComponentNameImpl(context));
            context.startForegroundService(intent);
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "intent=" + intent, ex);
            return false;
        }
    }

    @Deprecated
    public static void startPAServiceOld(Context context, Intent intent) {
        intent.setComponent(getPlayerServiceComponentNameImpl(context));
        context.startForegroundService(intent);
    }

    public static Bitmap getAlbumArt(Context context, Bundle track, int subsampleWidth, int subsampleHeight) {
        ParcelFileDescriptor pfd;
        if (track == null) {
            return null;
        }
        Uri aaUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(Long.toString(track.getLong(PowerampAPI.Track.REAL_ID))).build();
        try {
            pfd = context.getContentResolver().openFileDescriptor(aaUri, "r");
        } catch (FileNotFoundException e) {
        } catch (Throwable th) {
            Log.e(TAG, "", th);
        }
        if (pfd != null) {
            try {
                BitmapFactory.Options opts = new BitmapFactory.Options();
                if (pfd.getStatSize() > 0) {
                    opts.inJustDecodeBounds = true;
                    BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor(), null, opts);
                    opts.inJustDecodeBounds = false;
                    if (subsampleWidth > 0 && subsampleHeight > 0) {
                        opts.inSampleSize = calcSubsample(subsampleWidth, subsampleHeight, opts.outWidth, opts.outHeight);
                    }
                }
                Bitmap b = BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor(), null, opts);
                if (pfd != null) {
                    pfd.close();
                }
                return b;
            } catch (Throwable th2) {
                if (pfd != null) {
                    try {
                        pfd.close();
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
        if (pfd != null) {
            pfd.close();
        }
        return null;
    }

    private static int calcSubsample(int maxW, int maxH, int outWidth, int outHeight) {
        int sampleSize = 1;
        int nextWidth = outWidth >> 1;
        for (int nextHeight = outHeight >> 1; nextWidth >= maxW && nextHeight >= maxH; nextHeight >>= 1) {
            sampleSize <<= 1;
            nextWidth >>= 1;
        }
        return sampleSize;
    }
}
