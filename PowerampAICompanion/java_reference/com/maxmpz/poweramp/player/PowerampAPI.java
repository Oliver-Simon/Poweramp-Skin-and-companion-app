package com.maxmpz.poweramp.player;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;

/* loaded from: classes3.dex */
public final class PowerampAPI {

    @Deprecated
    public static final String ACTION_AA_CHANGED = "com.maxmpz.audioplayer.AA_CHANGED";
    public static final String ACTION_API_COMMAND = "com.maxmpz.audioplayer.API_COMMAND";
    public static final String ACTION_ASK_FOR_DATA_PERMISSION = "com.maxmpz.audioplayer.ACTION_ASK_FOR_DATA_PERMISSION";
    public static final String ACTION_EQU_CHANGED = "com.maxmpz.audioplayer.EQU_CHANGED";
    public static final String ACTION_MEDIA_BUTTON_IGNORED = "com.maxmpz.audioplayer.MEDIA_BUTTON_IGNORED";
    public static final String ACTION_NATIVE_PLUGIN_COMMAND = "com.maxmpz.audioplayer.NATIVE_PLUGIN_COMMAND";
    public static final String ACTION_NATIVE_PLUGIN_INIT = "com.maxmpz.audioplayer.NATIVE_PLUGIN_INIT";
    public static final String ACTION_NOTIFICATION_DELETED = "com.maxmpz.audioplayer.ACTION_NOTIFICATION_DELETED";
    public static final String ACTION_OPEN_EQ = "com.maxmpz.audioplayer.ACTION_OPEN_EQ";
    public static final String ACTION_OPEN_LIBRARY = "com.maxmpz.audioplayer.ACTION_OPEN_LIBRARY";
    public static final String ACTION_OPEN_LIST_AND_PLAY = "com.maxmpz.audioplayer.ACTION_OPEN_LIST_AND_PLAY";
    public static final String ACTION_OPEN_MAIN = "com.maxmpz.audioplayer.ACTION_OPEN_MAIN";
    public static final String ACTION_OPEN_SEARCH = "com.maxmpz.audioplayer.ACTION_OPEN_SEARCH";
    public static final String ACTION_PLAYING_MODE_CHANGED = "com.maxmpz.audioplayer.PLAYING_MODE_CHANGED";
    public static final String ACTION_RELOAD_DATA = "com.maxmpz.audioplayer.ACTION_RELOAD_DATA";
    public static final String ACTION_SHOW_CURRENT = "com.maxmpz.audioplayer.ACTION_SHOW_CURRENT";

    @Deprecated
    public static final String ACTION_SHOW_LIST = "com.maxmpz.audioplayer.ACTION_SHOW_LIST";
    public static final String ACTION_SKIN_MAIN = "com.maxmpz.audioplayer.SKIN_MAIN";
    public static final String ACTION_STATUS_CHANGED = "com.maxmpz.audioplayer.STATUS_CHANGED";
    public static final String ACTION_STATUS_CHANGED_EXPLICIT = "com.maxmpz.audioplayer.STATUS_CHANGED_EXPLICIT";
    public static final String ACTION_TRACK_CHANGED = "com.maxmpz.audioplayer.TRACK_CHANGED";
    public static final String ACTION_TRACK_CHANGED_EXPLICIT = "com.maxmpz.audioplayer.TRACK_CHANGED_EXPLICIT";
    public static final String ACTION_TRACK_POS_SYNC = "com.maxmpz.audioplayer.TPOS_SYNC";

    @Deprecated
    public static final String ACTIVITY_EQ = "com.maxmpz.audioplayer.EqActivity";

    @Deprecated
    public static final String ACTIVITY_PLAYER_UI = "com.maxmpz.audioplayer.PlayerUIActivity";

    @Deprecated
    public static final String ACTIVITY_PLAYLIST = "com.maxmpz.audioplayer.PlayListActivity";
    public static final String ACTIVITY_SETTINGS = "com.maxmpz.audioplayer.preference.SettingsActivity";
    public static final String ACTIVITY_STARTUP = "com.maxmpz.audioplayer.MainActivity";

    @Deprecated
    public static final String ALBUM_ART_BITMAP = "aaBitmap";

    @Deprecated
    public static final String ALBUM_ART_PATH = "aaPath";
    public static final String API_ACTIVITY_NAME = "com.maxmpz.audioplayer.PowerampAPIActivity";
    public static final String API_RECEIVER_NAME = "com.maxmpz.audioplayer.player.PowerampAPIReceiver";

    @Deprecated
    public static final String API_VERSION = "api";

    @Deprecated
    public static final String AUTO_HIDE = "autoHide";

    @Deprecated
    public static final String BEEP = "beep";
    public static final String CALL_PREFERENCE = "preference";
    public static final String CALL_SET_PREFERENCE = "set_preference";

    @Deprecated
    public static final String COMMAND = "cmd";

    @Deprecated
    public static final String CONTENT = "content";

    @Deprecated
    public static final String DATA = "data";

    @Deprecated
    public static final String DELAYED = "delayed";

    @Deprecated
    public static final String EQU = "equ";
    public static final int EQ_TAB_DEFAULT = -1;
    public static final int EQ_TAB_EQUALIZER = 0;
    public static final int EQ_TAB_REVERB = 2;
    public static final int EQ_TAB_VOLUME = 1;
    public static final String EXTRA_API_VERSION = "api";
    public static final String EXTRA_BEEP = "beep";
    public static final String EXTRA_COMMAND = "cmd";
    public static final String EXTRA_CONTENT = "content";
    public static final String EXTRA_DATA = "data";
    public static final String EXTRA_EQU = "equ";
    public static final String EXTRA_EQ_TAB = "eqTab";
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_IGNORE_PLAYING_STATE = "ips";
    public static final String EXTRA_KEEP_SERVICE = "keepService";
    public static final String EXTRA_LABEL = "label";
    public static final String EXTRA_LOCK = "lock";
    public static final String EXTRA_LONG_PRESS = "long_press";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_PACKAGE = "pak";
    public static final String EXTRA_PAUSED = "paused";
    public static final String EXTRA_PLAY_TO_END = "play_to_end";
    public static final String EXTRA_POSITION = "pos";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_RELATIVE_POSITION = "rel_pos";
    public static final String EXTRA_REPEAT = "repeat";
    public static final String EXTRA_SECONDS = "seconds";
    public static final String EXTRA_SHUFFLE = "shuffle";
    public static final String EXTRA_SOURCE = "src";
    public static final String EXTRA_STATE = "state";
    public static final String EXTRA_TABLE = "table";
    public static final String EXTRA_TIMESTAMP = "ts";
    public static final String EXTRA_TONE = "tone";
    public static final String EXTRA_TRACK = "track";
    public static final String EXTRA_VALUE = "value";

    @Deprecated
    public static final String FAILED = "failed";

    @Deprecated
    public static final String ICON = "icon";

    @Deprecated
    public static final String ID = "id";
    public static final long ID_NO_ID = 0;
    public static final String INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH = "android.media.action.MEDIA_PLAY_FROM_SEARCH";

    @Deprecated
    public static final String KEEP_SERVICE = "keepService";

    @Deprecated
    public static final String LABEL = "label";

    @Deprecated
    public static final String MATCH_FILE = "matchFile";
    public static final long MISSING_TRACK_ID = -3;

    @Deprecated
    public static final String NAME = "name";

    @Deprecated
    public static final long NO_ID = 0;

    @Deprecated
    public static final String PACKAGE = "pak";
    public static final String PARAM_AA_DOWNLOAD = "dl";
    public static final String PARAM_AA_HD = "hd";

    @Deprecated
    public static final String PARAM_FILTER = "flt";
    public static final String PARAM_SHUFFLE = "shf";

    @Deprecated
    public static final String PAUSED = "paused";
    public static final String PERMISSION_ACCESS_MILK_PRESETS = "com.maxmpz.audioplayer.permission.ACCESS_MILK_PRESETS";
    public static final long RAW_TRACK_ID = -2;

    @Deprecated
    public static final String REPEAT = "repeat";

    @Deprecated
    public static final String SHOW_TOAST = "showToast";

    @Deprecated
    public static final String SHUFFLE = "shuffle";

    @Deprecated
    public static final String SOURCE = "src";

    @Deprecated
    public static final String STATE = "state";
    public static final int STATE_NO_STATE = -1;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 1;
    public static final int STATE_STOPPED = 0;

    @Deprecated
    public static final String STATUS = "status";

    @Deprecated
    public static final String TABLE = "table";

    @Deprecated
    public static final String TIMESTAMP = "ts";

    @Deprecated
    public static final String TONE = "tone";

    @Deprecated
    public static final String TRACK = "track";

    @Deprecated
    public static final String VALUE = "value";
    public static final int VERSION = 855;
    public static final String AUTHORITY = "com.maxmpz.audioplayer.data";
    public static final Uri ROOT_URI = new Uri.Builder().scheme("content").authority(AUTHORITY).build();
    public static final String AA_AUTHORITY = "com.maxmpz.audioplayer.aa";
    public static final Uri AA_ROOT_URI = new Uri.Builder().scheme("content").authority(AA_AUTHORITY).build();
    public static final Uri MILK_PRESETS_URI = Uri.parse("content://com.maxmpz.audioplayer.milk_presets/");
    public static int MIN_TIME_BETWEEN_SEEKS_MS = 200;

    @Deprecated
    public static final String PACKAGE_NAME = "com.maxmpz.audioplayer";
    public static final String PLAYER_SERVICE_NAME = "com.maxmpz.audioplayer.player.PlayerService";

    @Deprecated
    public static final ComponentName PLAYER_SERVICE_COMPONENT_NAME = new ComponentName(PACKAGE_NAME, PLAYER_SERVICE_NAME);

    /* loaded from: classes3.dex */
    public static class Lyrics {
        public static final String ACTION_LYRICS_LINK = "com.maxmpz.audioplayer.ACTION_LYRICS_LINK";
        public static final String ACTION_NEED_LYRICS = "com.maxmpz.audioplayer.ACTION_NEED_LYRICS";
        public static final String ACTION_UPDATE_LYRICS = "com.maxmpz.audioplayer.ACTION_UPDATE_LYRICS";
        public static final String EXTRA_INFO_LINE = "info_line";
        public static final String EXTRA_LYRICS = "lyrics";
    }

    /* loaded from: classes3.dex */
    public static class MilkScanner {
        public static final String ACTION_SCAN = "com.maxmpz.milk.ACTION_SCAN";
        public static final String EXTRA_CAUSE = "cause";
        public static final String EXTRA_PACKAGE = "pak";

        @Deprecated
        public static final String MILK_SCANNER_SERVICE_NAME = "com.maxmpz.milk.scanner.MilkScanService";
    }

    /* loaded from: classes3.dex */
    public static final class RepeatMode {
        public static final int MAX_REPEAT = 5;
        public static final int REPEAT_ADVANCE = 2;
        public static final int REPEAT_ADVANCE_AND_STOP = 5;
        public static final int REPEAT_NONE = 0;
        public static final int REPEAT_ON = 1;
        public static final int REPEAT_SONG = 3;
        public static final int SINGLE_SONG = 4;
    }

    /* loaded from: classes3.dex */
    public static final class Scanner {
        public static final String ACTION_DIRS_SCAN_FINISHED = "com.maxmpz.audioplayer.ACTION_DIRS_SCAN_FINISHED";
        public static final String ACTION_DIRS_SCAN_STARTED = "com.maxmpz.audioplayer.ACTION_DIRS_SCAN_STARTED";
        public static final String ACTION_FAST_TAGS_SCAN_FINISHED = "com.maxmpz.audioplayer.ACTION_FAST_TAGS_SCAN_FINISHED";
        public static final String ACTION_SCAN_DIRS = "com.maxmpz.audioplayer.ACTION_SCAN_DIRS";
        public static final String ACTION_SCAN_TAGS = "com.maxmpz.audioplayer.ACTION_SCAN_TAGS";
        public static final String ACTION_TAGS_SCAN_FINISHED = "com.maxmpz.audioplayer.ACTION_TAGS_SCAN_FINISHED";

        @Deprecated
        public static final String ACTION_TAGS_SCAN_PROGRESS = "com.maxmpz.audioplayer.ACTION_TAGS_SCAN_PROGRESS";
        public static final String ACTION_TAGS_SCAN_STARTED = "com.maxmpz.audioplayer.ACTION_TAGS_SCAN_STARTED";
        public static final String EXTRA_CAUSE = "cause";
        public static final String EXTRA_ERASE_TAGS = "eraseTags";
        public static final String EXTRA_FAST_SCAN = "fastScan";
        public static final String EXTRA_FULL_RESCAN = "fullRescan";
        public static final String EXTRA_IMPORT_SYSTEM_PLAYLISTS = "importSystemPlaylists";
        public static final String EXTRA_PATH = "path";
        public static final String EXTRA_PROGRESS = "progress";
        public static final String EXTRA_PROVIDER = "provider";
        public static final String EXTRA_REPARSE_PLAYLISTS = "reparsePlaylists";
        public static final String EXTRA_RESCAN_LYRICS_TAGS = "rescanLyricsTags";
        public static final String EXTRA_RESOLVE_PLAYLISTS = "resolvePlaylists";
        public static final String EXTRA_SCAN_PROVIDERS = "scanProviders";
        public static final String EXTRA_TRACK_CONTENT_CHANGED = "trackContentChanged";

        @Deprecated
        public static final String SCANNER_SERVICE_NAME = "com.maxmpz.audioplayer.scanner.ScanDispatcherService";
    }

    /* loaded from: classes3.dex */
    public static class Settings {

        @Deprecated
        public static final String ACTION_EXPORT_SETTINGS = "com.maxmpz.audioplayer.ACTION_EXPORT_SETTINGS";

        @Deprecated
        public static final String ACTION_IMPORT_SETTINGS = "com.maxmpz.audioplayer.ACTION_IMPORT_SETTINGS";
        public static final String ACTIVITY_SETTINGS = "com.maxmpz.audioplayer.preference.SettingsActivity";
        public static final String EXTRA_NO_BACKSTACK = "no_backstack";
        public static final String EXTRA_OPEN = "open";
        public static final String EXTRA_OPEN_PATH = "open_path";
        public static final String EXTRA_SKIN_PACKAGE = "theme_pak";
        public static final String EXTRA_SKIN_PAGE_PATH = "theme_page_path";
        public static final String EXTRA_SKIN_STYLE_ID = "theme_id";
        public static final String EXTRA_UI = "ui";
        public static final String EXTRA_VIS_PRESETS_PAK = "vis_presets_pak";
        public static final String OPEN_THEME = "theme";
        public static final String OPEN_VIS = "vis";

        @Deprecated
        /* loaded from: classes3.dex */
        public static class Preferences {
        }

        /* loaded from: classes3.dex */
        public static class PreferencesConsts {
            public static final int VIS_MODE_MAX = 2;
            public static final int VIS_MODE_VIS = 2;
            public static final int VIS_MODE_VIS_NONE = 0;
            public static final int VIS_MODE_VIS_W_UI = 1;
        }
    }

    @Deprecated
    /* loaded from: classes3.dex */
    public static final class Status {

        @Deprecated
        public static final int PLAYING_ENDED = 3;

        @Deprecated
        public static final int TRACK_ENDED = 2;

        @Deprecated
        public static final int TRACK_PLAYING = 1;
    }

    /* loaded from: classes3.dex */
    public static final class Track {
        public static final String ALBUM = "album";
        public static final String ARTIST = "artist";
        public static final String BITRATE = "bitRate";
        public static final String BITS_PER_SAMPLE = "bitsPerSample";
        public static final String CAT = "cat";
        public static final String CAT_URI = "catUri";
        public static final String CHANNELS = "channels";
        public static final String CODEC = "codec";
        public static final String DURATION = "dur";
        public static final String DURATION_MS = "durMs";
        public static final String FILE_TYPE = "fileType";
        public static final String FLAGS = "flags";
        public static final String ID = "id";
        public static final String IS_CUE = "isCue";
        public static final String LIST_SIZE = "listSize";
        public static final String LYRICS_STATE = "lyricsState";
        public static final int MAX_FILE_NUMBER = 99;
        public static final int MAX_TRACK_NUMBER = 999;
        public static final String PATH = "path";
        public static final String POSITION = "pos";
        public static final String POS_IN_LIST = "posInList";
        public static final String RATING = "rating";
        public static final int RATING_LIKE = 5;
        public static final int RATING_NOT_SET = 0;
        public static final int RATING_UNLIKE = 1;
        public static final String REAL_ID = "realId";
        public static final String SAMPLE_RATE = "sampleRate";
        public static final String SUPPORTS_CAT_NAV = "supportsCatNav";
        public static final String TITLE = "title";

        @Deprecated
        public static final String TYPE = "type";

        /* loaded from: classes3.dex */
        public static class FileType {
            public static final int FIRST_TYPE = 0;
            public static final int LAST_TYPE = 30;
            public static final int TYPE_3GP = 13;
            public static final int TYPE_AAC = 10;
            public static final int TYPE_AIF = 16;
            public static final int TYPE_AIFF = 15;

            @Deprecated
            public static final int TYPE_AMR = 12;
            public static final int TYPE_APE = 8;
            public static final int TYPE_DFF = 19;
            public static final int TYPE_DSF = 20;
            public static final int TYPE_FLAC = 1;
            public static final int TYPE_FLV = 17;
            public static final int TYPE_IT = 28;
            public static final int TYPE_M4A = 2;
            public static final int TYPE_MKA = 21;
            public static final int TYPE_MKV = 24;
            public static final int TYPE_MOD = 25;
            public static final int TYPE_MP3 = 0;
            public static final int TYPE_MP4 = 3;
            public static final int TYPE_MPC = 14;
            public static final int TYPE_MPGA = 11;
            public static final int TYPE_MPTM = 29;
            public static final int TYPE_OGA = 30;
            public static final int TYPE_OGG = 4;
            public static final int TYPE_OPUS = 18;
            public static final int TYPE_S3M = 27;
            public static final int TYPE_STREAM = 23;
            public static final int TYPE_TAK = 22;
            public static final int TYPE_TTA = 7;
            public static final int TYPE_UNKNOWN = -1;
            public static final int TYPE_WAV = 6;
            public static final int TYPE_WMA = 5;
            public static final int TYPE_WV = 9;
            public static final int TYPE_XM = 26;
        }

        /* loaded from: classes3.dex */
        public interface Flags {
            public static final int FLAG_ADVANCE_BACKWARD = 2;
            public static final int FLAG_ADVANCE_BACKWARD_CAT = 4;
            public static final int FLAG_ADVANCE_BY_USER = 5;
            public static final int FLAG_ADVANCE_FORWARD = 1;
            public static final int FLAG_ADVANCE_FORWARD_CAT = 3;
            public static final int FLAG_ADVANCE_MASK = 7;
            public static final int FLAG_ADVANCE_NONE = 0;
            public static final int FLAG_FAILED = 128;
            public static final int FLAG_FIRST_IN_PLAYER_SESSION = 64;
            public static final int FLAG_NOTIFICATION_UI = 32;
        }

        /* loaded from: classes3.dex */
        public static final class LyricsState {
            public static final int LYRICS_STATE_HAS_DATA = 1;
            public static final int LYRICS_STATE_NONE = 0;
        }

        /* loaded from: classes3.dex */
        public static final class TagStatus {
            public static final int TAG_FAILED = 2;
            public static final int TAG_NOT_SCANNED = 0;
            public static final int TAG_SCANNED = 1;
        }
    }

    /* loaded from: classes3.dex */
    public static final class VisMode {
        public static final int VIS_FULL_SCREEN = 2;
        public static final int VIS_NONE = 0;
        public static final int VIS_W_UI = 1;
    }

    /* loaded from: classes3.dex */
    public static final class Commands {
        public static final int BEGIN_FAST_FORWARD = 10;
        public static final int BEGIN_REWIND = 12;
        public static final int END_FAST_FORWARD = 11;
        public static final int END_FF_OR_RW = 11;
        public static final int END_REWIND = 13;
        public static final int LIKE = 18;
        public static final int NEXT = 4;
        public static final int NEXT_IN_CAT = 6;
        public static final int OPEN = 20;
        public static final int OPEN_TO_PLAY = 20;
        public static final int PAUSE = 2;
        public static final int PLAY = 3;
        public static final int POS_SYNC = 16;
        public static final int PREV = 5;
        public static final int PREVIOUS = 5;
        public static final int PREVIOUS_IN_CAT = 7;
        public static final int REPEAT = 8;
        public static final int RESUME = 3;
        public static final int SEEK = 15;
        public static final int SEEK_JUMP_BACKWARD = 23;
        public static final int SEEK_JUMP_FORWARD = 22;
        public static final int SET_EQU_BAND = 52;
        public static final int SET_EQU_ENABLED = 53;
        public static final int SET_EQU_PRESET = 50;
        public static final int SET_EQU_STRING = 51;
        public static final int SET_RATING = 24;
        public static final int SET_VIS_PRESET = 200;
        public static final int SHUFFLE = 9;
        public static final int SLEEP_TIMER = 17;
        public static final int STOP = 14;
        public static final int STOP_SERVICE = 100;
        public static final int TOGGLE_PLAY_PAUSE = 1;
        public static final int TOGGLE_RATING = 21;
        public static final int UNLIKE = 19;

        public static String cmdToString(int cmd) {
            switch (cmd) {
                case 1:
                    return "TOGGLE_PLAY_PAUSE";
                case 2:
                    return "PAUSE";
                case 3:
                    return "RESUME";
                case 4:
                    return "NEXT";
                case 5:
                    return "PREVIOUS";
                case 6:
                    return "NEXT_IN_CAT";
                case 7:
                    return "PREVIOUS_IN_CAT";
                case 8:
                    return "REPEAT";
                case 9:
                    return "SHUFFLE";
                case 10:
                    return "BEGIN_FAST_FORWARD";
                case 11:
                    return "END_FAST_FORWARD";
                case 12:
                    return "BEGIN_REWIND";
                case 13:
                    return "END_REWIND";
                case 14:
                    return "STOP";
                case 15:
                    return "SEEK";
                case 16:
                    return "POS_SYNC";
                case 17:
                    return "SLEEP_TIMER";
                case 18:
                    return "LIKE";
                case 19:
                    return "UNLIKE";
                case 20:
                    return "OPEN_TO_PLAY";
                case 21:
                    return "TOGGLE_RATING";
                case 22:
                    return "SEEK_JUMP_FORWARD";
                case 23:
                    return "SEEK_JUMP_BACKWARD";
                case 24:
                    return "SET_RATING";
                case 50:
                    return "SET_EQU_PRESET";
                case 51:
                    return "SET_EQU_STRING";
                case 52:
                    return "SET_EQU_BAND";
                case 53:
                    return "SET_EQU_ENABLED";
                case 100:
                    return "STOP_SERVICE";
                case 200:
                    return "SET_VIS_PRESET";
                default:
                    return "unknown cmd=" + cmd;
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class ShuffleMode {
        public static final int MAX_SHUFFLE = 4;
        public static final int SHUFFLE_ALL = 1;
        public static final int SHUFFLE_CATS = 3;
        public static final int SHUFFLE_NONE = 0;
        public static final int SHUFFLE_SONGS = 2;
        public static final int SHUFFLE_SONGS_AND_CATS = 4;
        public static final int SHUFFLE_SONGS_HIER = 5;

        public static boolean areSongsShuffled(int shuffle) {
            return shuffle == 1 || shuffle == 2 || shuffle == 4;
        }

        public static boolean areCatsShuffled(int shuffle) {
            return shuffle == 3 || shuffle == 4;
        }
    }

    /* loaded from: classes3.dex */
    public static final class Cats {
        public static final int ALBUMS = 200;
        public static final int ALBUM_ARTISTS = 520;
        public static final int ALBUM_ARTISTS_ID_ALBUMS = 256;
        public static final int ARTISTS = 500;
        public static final int ARTISTS_ID_ALBUMS = 220;
        public static final int ARTISTS__ALBUMS = 250;
        public static final int BOOKMARKS = 810;
        public static final int COMPOSERS = 600;
        public static final int COMPOSERS_ID_ALBUMS = 230;
        public static final int FILES = 30;
        public static final int FOLDERS = 10;
        public static final int FOLDERS_HIER = 20;
        public static final int GENRES = 320;
        public static final int GENRES_ID_ALBUMS = 210;
        public static final int LONG = 55;
        public static final int LOW_RATED = 50;
        public static final int MOST_PLAYED = 43;
        public static final int PLAYLISTS = 100;
        public static final int QUEUE = 800;
        public static final int RECENTLY_ADDED = 53;
        public static final int RECENTLY_PLAYED = 58;

        @Deprecated
        public static final int ROOT = 0;
        public static final int STREAM_FILES = 60;
        public static final int TOP_RATED = 48;
        public static final int YEARS = 330;
        public static final int YEARS_ID_ALBUMS = 340;

        private Cats() {
        }
    }

    @Deprecated
    public static Intent newAPIIntent() {
        return new Intent(ACTION_API_COMMAND).setComponent(PLAYER_SERVICE_COMPONENT_NAME);
    }
}
