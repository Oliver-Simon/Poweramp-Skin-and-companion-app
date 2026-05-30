package com.maxmpz.poweramp.player;

/* loaded from: classes3.dex */
public interface RouterConsts {
    public static final int DEVICE_BT = 2;
    public static final int DEVICE_CHROMECAST = 5;
    public static final int DEVICE_COUNT = 6;
    public static final int DEVICE_HEADSET = 0;
    public static final String DEVICE_NAME_BT = "bt";
    public static final String DEVICE_NAME_CHROMECAST = "chromecast";
    public static final String DEVICE_NAME_HEADSET = "headset";
    public static final String DEVICE_NAME_OTHER = "other";
    public static final String DEVICE_NAME_SPEAKER = "speaker";
    public static final String DEVICE_NAME_USB = "usb";
    public static final int DEVICE_OTHER = 4;
    public static final int DEVICE_SAFE_DEFAULT = 0;
    public static final int DEVICE_SPEAKER = 1;
    public static final int DEVICE_UNKNOWN = 255;
    public static final int DEVICE_USB = 3;

    static int toAndroidDeviceType(int device) {
        switch (device) {
            case 1:
                return 2;
            case 2:
                return 8;
            case 3:
                return 11;
            case 4:
            default:
                return 3;
            case 5:
                return 20;
        }
    }

    static boolean isValidKnownDevice(int device) {
        return device >= 0 && device < 6;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static int getDeviceId(String device) {
        boolean z;
        if (device == null) {
            return -1;
        }
        switch (device.hashCode()) {
            case -2008522753:
                if (device.equals(DEVICE_NAME_SPEAKER)) {
                    z = true;
                    break;
                }
                z = -1;
                break;
            case -1509195591:
                if (device.equals(DEVICE_NAME_CHROMECAST)) {
                    z = 5;
                    break;
                }
                z = -1;
                break;
            case 3154:
                if (device.equals(DEVICE_NAME_BT)) {
                    z = 2;
                    break;
                }
                z = -1;
                break;
            case 116100:
                if (device.equals(DEVICE_NAME_USB)) {
                    z = 3;
                    break;
                }
                z = -1;
                break;
            case 106069776:
                if (device.equals(DEVICE_NAME_OTHER)) {
                    z = 4;
                    break;
                }
                z = -1;
                break;
            case 795320962:
                if (device.equals(DEVICE_NAME_HEADSET)) {
                    z = false;
                    break;
                }
                z = -1;
                break;
            default:
                z = -1;
                break;
        }
        switch (z) {
            case false:
                return 0;
            case true:
                return 1;
            case true:
                return 2;
            case true:
                return 3;
            case true:
                return 4;
            case true:
                return 5;
            default:
                return -1;
        }
    }

    static String getDeviceName(int device) {
        switch (device) {
            case 0:
                return DEVICE_NAME_HEADSET;
            case 1:
                return DEVICE_NAME_SPEAKER;
            case 2:
                return DEVICE_NAME_BT;
            case 3:
                return DEVICE_NAME_USB;
            case 4:
                return DEVICE_NAME_OTHER;
            case 5:
                return DEVICE_NAME_CHROMECAST;
            default:
                return "Unknown_" + device;
        }
    }
}
