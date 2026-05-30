package com.maxmpz.poweramp.player;

/* loaded from: classes3.dex */
public final class PaSampleFormat {
    public static final int PA_SAMPLE_FMT_DBL = 4;
    public static final int PA_SAMPLE_FMT_DBLP = 9;
    public static final int PA_SAMPLE_FMT_FLT = 3;
    public static final int PA_SAMPLE_FMT_FLTP = 8;
    public static final int PA_SAMPLE_FMT_NB = 22;
    public static final int PA_SAMPLE_FMT_NONE = -1;
    public static final int PA_SAMPLE_FMT_S16 = 1;
    public static final int PA_SAMPLE_FMT_S16P = 6;
    public static final int PA_SAMPLE_FMT_S24 = 20;
    public static final int PA_SAMPLE_FMT_S32 = 2;
    public static final int PA_SAMPLE_FMT_S32P = 7;
    public static final int PA_SAMPLE_FMT_S64 = 10;
    public static final int PA_SAMPLE_FMT_S64P = 11;
    public static final int PA_SAMPLE_FMT_S8_24 = 21;
    public static final int PA_SAMPLE_FMT_U8 = 0;
    public static final int PA_SAMPLE_FMT_U8P = 5;

    public static boolean isValidFormat(int format, boolean allowReserve) {
        if (format < 0 || format >= 22) {
            return false;
        }
        if (!allowReserve && format > 11 && format < 20) {
            return false;
        }
        return true;
    }

    public static int getBitsPerSample(int sampleFormat) {
        switch (sampleFormat) {
            case 0:
                return 8;
            case 1:
                return 16;
            case 2:
                return 32;
            case 3:
                return 32;
            case 4:
                return 64;
            case 5:
                return 8;
            case 6:
                return 16;
            case 7:
                return 32;
            case 8:
                return 32;
            case 9:
                return 64;
            case 10:
            case 11:
                return 64;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            default:
                return 0;
            case 20:
                return 24;
            case 21:
                return 32;
        }
    }

    public static int getBytesPerSample(int sampleFormat) {
        return getBitsPerSample(sampleFormat) / 8;
    }

    public static int getSignificantBitsPerSample(int sampleFormat) {
        switch (sampleFormat) {
            case 0:
                return 8;
            case 1:
                return 16;
            case 2:
                return 32;
            case 3:
                return 24;
            case 4:
                return 53;
            case 5:
                return 8;
            case 6:
                return 16;
            case 7:
                return 32;
            case 8:
                return 24;
            case 9:
                return 53;
            case 10:
            case 11:
                return 53;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            default:
                return 0;
            case 20:
                return 24;
            case 21:
                return 24;
        }
    }
}
