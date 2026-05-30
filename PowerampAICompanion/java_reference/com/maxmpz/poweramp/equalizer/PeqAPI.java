package com.maxmpz.poweramp.equalizer;

import com.maxmpz.poweramp.player.PowerampAPI;

/* loaded from: classes4.dex */
public final class PeqAPI {
    public static final String ACTION_API_COMMAND = "com.maxmpz.equalizer.API_COMMAND";
    public static final String ACTION_ASK_FOR_DATA_PERMISSION = "com.maxmpz.audioplayer.ACTION_ASK_FOR_DATA_PERMISSION";
    public static final String ACTION_IMPORT_PRESETS = "com.maxmpz.equalizer.IMPORT_PRESETS";
    public static final String ACTION_RELOAD_DATA = "com.maxmpz.audioplayer.ACTION_RELOAD_DATA";
    public static final String API_RECEIVER_NAME = "com.maxmpz.equalizer.PeqAPIReceiver";
    public static final String CALL_SET_PREFERENCE = "set_preference";
    public static final String EXTRA_COMMAND = "cmd";
    public static final String EXTRA_NAMES = "names";
    public static final String EXTRA_PACKAGE = "pak";
    public static final String EXTRA_PRESETS = "presets";
    public static final String EXTRA_SOURCE = "src";

    /* loaded from: classes4.dex */
    public static final class Commands {
        public static final int SET_EQU_PRESET = 50;
        public static final int START_SERVICE = 101;
        public static final int STOP_SERVICE = 100;
        public static final int TOGGLE_SERVICE = 102;
    }

    /* loaded from: classes4.dex */
    public static final class MilkScanner extends PowerampAPI.MilkScanner {
    }

    /* loaded from: classes4.dex */
    public static class Settings extends PowerampAPI.Settings {
        public static final String ACTIVITY_SETTINGS = "com.maxmpz.equalizer.preference.SettingsActivity";
    }
}
