package com.maxmpz.poweramp.player;

import kotlin.Deprecated;
import kotlin.Metadata;

/* compiled from: TableDefs.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\"\bÆ\u0002\u0018\u00002\u00020\u0001: \t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'(B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs;", "", "<init>", "()V", "CATEGORY_ALIAS", "", "UNKNOWN_ID", "", "CATEGORY_ALIAS_ID", "Files", "RawFiles", "SoFiles", "Folders", "Albums", "Artists", "MultiArtists", "AlbumArtists", "MultiAlbumArtists", "AlbumsByArtist", "Composers", "MultiComposers", "Genres", "GenreEntries", "Years", "CatStats", "PlaylistEntries", "Playlists", "Queue", "Bookmarks", "ShuffleSessionIds", "EqPresets", "EqPresetSongs", "EqPresetDevices", "KnownDevices", "ReverbPresets", "PrefSearch", "PrefSearchFts", "SearchHistory", "SettingsSearchHistory", "LrcFiles", "CachedLyrics", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TableDefs {
    public static final String CATEGORY_ALIAS = "cat";
    public static final String CATEGORY_ALIAS_ID = "cat._id";
    public static final TableDefs INSTANCE = new TableDefs();
    public static final long UNKNOWN_ID = 1000;

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$SearchHistory;", "", "<init>", "()V", "Companion", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static class SearchHistory {
        public static final String TABLE = "search_history";
        public static final String TERM = "term";
        public static final String UPDATED_AT = "updated_at";
        public static final String _ID = "search_history._id";
    }

    private TableDefs() {
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b3\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010/\u001a\u00020\u00058\u0006X\u0087D¢\u0006\b\n\u0000\u0012\u0004\b0\u0010\u0003R\u0016\u00101\u001a\u00020\u00058\u0006X\u0087D¢\u0006\b\n\u0000\u0012\u0004\b2\u0010\u0003R\u000e\u00103\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$Files;", "", "<init>", "()V", "INVALID_TRACK_NUMBER", "", "TABLE", "", "_ID", "NAME", "TRACK_NUMBER", "TRACK_TAG", "DISC", "NAME_WITHOUT_NUMBER", "TAG_STATUS", "FOLDER_ID", "TITLE_TAG", "ALBUM_TAG", "ARTIST_TAG", "DURATION", "UPDATED_AT", "FILE_TYPE", "PLAYED_AT", "PLAYED_FULLY_AT", "FILE_CREATED_AT", "AA_STATUS", "RATING", "PLAYED_TIMES", "TOTAL_PLAYED_TIMES", "ALBUM_ID", "ARTIST_ID", "ALBUM_ARTIST_ID", "COMPOSER_ID", "YEAR", "OFFSET_MS", "CUE_FOLDER_ID", "CREATED_AT", "WAVE", "META", "LAST_POS", "SHUFFLE_ORDER", "USER_ADDED", "URL", "FILE_PATH", "BIT_RATE", "FULL_PATH", "TRACK_NUMBER_ALT", "TAG_NOT_SCANNED", "getTAG_NOT_SCANNED$annotations", "TAG_SCANNED", "getTAG_SCANNED$annotations", "LRC_FILES_ID", "LRC_FILES_PRIO", "HAS_LYRICS_TAG", "IN_PLAYLIST", "CACHED_LYRICS_ID", "CACHED_LYRICS_LOADING_STARTED_AT", "HAS_LYRICS", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Files {
        public static final String AA_STATUS = "folder_files.aa_status";
        public static final String ALBUM_ARTIST_ID = "folder_files.album_artist_id";
        public static final String ALBUM_ID = "folder_files.album_id";
        public static final String ALBUM_TAG = "album_tag";
        public static final String ARTIST_ID = "folder_files.artist_id";
        public static final String ARTIST_TAG = "artist_tag";
        public static final String BIT_RATE = "folder_files.bit_rate";
        public static final String CACHED_LYRICS_ID = "cached_lyrics_id";
        public static final String CACHED_LYRICS_LOADING_STARTED_AT = "cached_lyrics_loading_started_at";
        public static final String COMPOSER_ID = "folder_files.composer_id";
        public static final String CREATED_AT = "folder_files.created_at";
        public static final String CUE_FOLDER_ID = "cue_folder_id";
        public static final String DISC = "disc";
        public static final String DURATION = "folder_files.duration";
        public static final String FILE_CREATED_AT = "file_created_at";
        public static final String FILE_PATH = "folder_files.file_path";
        public static final String FILE_TYPE = "file_type";
        public static final String FOLDER_ID = "folder_id";
        public static final String FULL_PATH = "COALESCE(folder_files.file_path,path||folder_files.name,folder_files.name)";
        public static final String HAS_LYRICS = "(has_lyrics_tag OR lrc_files_id IS NOT NULL OR cached_lyrics_id IS NOT NULL AND cached_lyrics_loading_started_at IS NULL AND file_type!=23) AS _has_lyrics";
        public static final String HAS_LYRICS_TAG = "has_lyrics_tag";
        public static final int INVALID_TRACK_NUMBER = 10000;
        public static final String IN_PLAYLIST = "in_playlist";
        public static final String LAST_POS = "last_pos";
        public static final String LRC_FILES_ID = "lrc_files_id";
        public static final String LRC_FILES_PRIO = "lrc_files_prio";
        public static final String META = "folder_files.meta";
        public static final String NAME = "folder_files.name";
        public static final String NAME_WITHOUT_NUMBER = "name_without_number";
        public static final String OFFSET_MS = "folder_files.offset_ms";
        public static final String PLAYED_AT = "folder_files.played_at";
        public static final String PLAYED_FULLY_AT = "folder_files.played_fully_at";
        public static final String PLAYED_TIMES = "folder_files.played_times";
        public static final String RATING = "rating";
        public static final String SHUFFLE_ORDER = "folder_files.shuffle_order";
        public static final String TABLE = "folder_files";
        public static final int TAG_NOT_SCANNED = 0;
        public static final String TAG_STATUS = "tag_status";
        public static final String TITLE_TAG = "title_tag";
        public static final String TOTAL_PLAYED_TIMES = "folder_files.total_played_times";
        public static final String TRACK_NUMBER = "track_number";
        public static final String TRACK_NUMBER_ALT = "track_number_alt";
        public static final String TRACK_TAG = "track_tag";
        public static final String UPDATED_AT = "folder_files.updated_at";
        public static final String URL = "folder_files.url";
        public static final String USER_ADDED = "folder_files.user_added";
        public static final String WAVE = "wave";
        public static final String YEAR = "folder_files.year";
        public static final String _ID = "folder_files._id";
        public static final Files INSTANCE = new Files();
        public static final int TAG_SCANNED = 1;

        @Deprecated(message = " ")
        public static /* synthetic */ void getTAG_NOT_SCANNED$annotations() {
        }

        @Deprecated(message = " ")
        public static /* synthetic */ void getTAG_SCANNED$annotations() {
        }

        private Files() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$RawFiles;", "", "<init>", "()V", "TABLE", "", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class RawFiles {
        public static final RawFiles INSTANCE = new RawFiles();
        public static final String TABLE = "raw_files";

        private RawFiles() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$SoFiles;", "", "<init>", "()V", "TABLE", "", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class SoFiles {
        public static final SoFiles INSTANCE = new SoFiles();
        public static final String TABLE = "so_files";

        private SoFiles() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b0\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0003\u001a\u0004\b#\u0010$R\u001c\u0010%\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u0003\u001a\u0004\b'\u0010$R\u001c\u0010(\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u0003\u001a\u0004\b*\u0010$R\u001c\u0010+\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b,\u0010\u0003\u001a\u0004\b-\u0010$R\u001c\u0010.\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b/\u0010\u0003\u001a\u0004\b0\u0010$R\u0016\u00101\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b2\u0010\u0003R\u0016\u00103\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b4\u0010\u0003¨\u00065"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$Folders;", "", "<init>", "()V", "TABLE", "", "_ID", "NAME", "SHORT_NAME", "PARENT_NAME", "PARENT_LABEL", "PATH", "SORT_PATH", "THUMB", "DIR_MODIFIED_AT", "UPDATED_AT", "PARENT_ID", "IS_CUE", "CREATED_AT", "NUM_SUBFOLDERS", "NUM_FILES", "NUM_ALL_FILES", "HIER_NUM_FILES", "DURATION", "HIER_DURATION", "DUR_META", "HIER_DUR_META", "AA_STATUS", "KEEP_LIST_POS", "KEEP_TRACK_POS", "KEEP_LIST_AND_TRACK_POS_COMBINED", "SORT_ORDER", "PLAYED_AT", "HIER_NUM_ALL_FILES", "getHIER_NUM_ALL_FILES$annotations", "getHIER_NUM_ALL_FILES", "()Ljava/lang/String;", "DURATION_ALL", "getDURATION_ALL$annotations", "getDURATION_ALL", "HIER_DUR_ALL_META", "getHIER_DUR_ALL_META$annotations", "getHIER_DUR_ALL_META", "DUR_ALL_META", "getDUR_ALL_META$annotations", "getDUR_ALL_META", "HIER_DURATION_ALL", "getHIER_DURATION_ALL$annotations", "getHIER_DURATION_ALL", "PARENT_NAME_SUBQUERY", "getPARENT_NAME_SUBQUERY$annotations", "PARENT_SHORT_NAME_SUBQUERY", "getPARENT_SHORT_NAME_SUBQUERY$annotations", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Folders {
        public static final String AA_STATUS = "folders.aa_status";
        public static final String CREATED_AT = "folders.created_at";
        public static final String DIR_MODIFIED_AT = "folders.dir_modified_at";
        public static final String DURATION = "folders.duration";
        public static final String DUR_META = "folders.dur_meta";
        public static final String HIER_DURATION = "folders.hier_duration";
        public static final String HIER_DUR_META = "folders.hier_dur_meta";
        public static final String HIER_NUM_FILES = "folders.hier_num_files";
        public static final String IS_CUE = "folders.is_cue";
        public static final String KEEP_LIST_AND_TRACK_POS_COMBINED = "(folders.keep_track_pos<<1)+folders.keep_list_pos";
        public static final String KEEP_LIST_POS = "folders.keep_list_pos";
        public static final String KEEP_TRACK_POS = "folders.keep_track_pos";
        public static final String NAME = "folders.name";
        public static final String NUM_ALL_FILES = "folders.num_all_files";
        public static final String NUM_FILES = "folders.num_files";
        public static final String NUM_SUBFOLDERS = "folders.num_subfolders";
        public static final String PARENT_ID = "folders.parent_id";
        public static final String PARENT_LABEL = "folders.parent_label";
        public static final String PARENT_NAME = "folders.parent_name";
        public static final String PARENT_NAME_SUBQUERY = "(SELECT name FROM folders AS f2 WHERE f2._id=folders.parent_id) AS parent_name_subquery";
        public static final String PARENT_SHORT_NAME_SUBQUERY = "(SELECT short_name FROM folders AS f2 WHERE f2._id=folders.parent_id) AS parent_short_name_subquery";
        public static final String PATH = "path";
        public static final String PLAYED_AT = "folders.played_at";
        public static final String SHORT_NAME = "folders.short_name";
        public static final String SORT_ORDER = "sort_order";
        public static final String SORT_PATH = "sort_path";
        public static final String TABLE = "folders";
        public static final String THUMB = "thumb";
        public static final String UPDATED_AT = "folders.updated_at";
        public static final String _ID = "folders._id";
        public static final Folders INSTANCE = new Folders();
        private static final String HIER_NUM_ALL_FILES = "folders.hier_num_all_files";
        private static final String DURATION_ALL = "folders.duration_all";
        private static final String HIER_DUR_ALL_META = "folders.hier_dur_all_meta";
        private static final String DUR_ALL_META = "folders.dur_all_meta";
        private static final String HIER_DURATION_ALL = "folders.hier_duration_all";

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDURATION_ALL$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDUR_ALL_META$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getHIER_DURATION_ALL$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getHIER_DUR_ALL_META$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getHIER_NUM_ALL_FILES$annotations() {
        }

        @Deprecated(message = "use {@link #PARENT_LABEL}")
        public static /* synthetic */ void getPARENT_NAME_SUBQUERY$annotations() {
        }

        @Deprecated(message = "use {@link #PARENT_NAME} ")
        public static /* synthetic */ void getPARENT_SHORT_NAME_SUBQUERY$annotations() {
        }

        private Folders() {
        }

        public final String getHIER_NUM_ALL_FILES() {
            return HIER_NUM_ALL_FILES;
        }

        public final String getDURATION_ALL() {
            return DURATION_ALL;
        }

        public final String getHIER_DUR_ALL_META() {
            return HIER_DUR_ALL_META;
        }

        public final String getDUR_ALL_META() {
            return DUR_ALL_META;
        }

        public final String getHIER_DURATION_ALL() {
            return HIER_DURATION_ALL;
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0016\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0003\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0017\u0010\u0014R\u001c\u0010\u0018\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0003\u001a\u0004\b\u001a\u0010\u0014¨\u0006\u001b"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$Albums;", "", "<init>", "()V", "TABLE", "", "_ID", "ALBUM", "ALBUM_SORT", "ALBUM_ARTIST_ID", "CREATED_AT", "NUM_FILES", "AA_STATUS", "ALBUM_YEAR", "DURATION", "DUR_META", "PLAYED_AT", "NUM_ALL_FILES", "getNUM_ALL_FILES$annotations", "getNUM_ALL_FILES", "()Ljava/lang/String;", "DURATION_ALL", "getDURATION_ALL$annotations", "getDURATION_ALL", "DUR_ALL_META", "getDUR_ALL_META$annotations", "getDUR_ALL_META", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Albums {
        public static final String AA_STATUS = "albums.aa_status";
        public static final String ALBUM = "album";
        public static final String ALBUM_ARTIST_ID = "albums.album_artist_id";
        public static final String ALBUM_SORT = "album_sort";
        public static final String ALBUM_YEAR = "album_year";
        public static final String CREATED_AT = "albums.created_at";
        public static final String DURATION = "albums.duration";
        public static final String DUR_META = "albums.dur_meta";
        public static final String NUM_FILES = "albums.num_files";
        public static final String PLAYED_AT = "albums.played_at";
        public static final String TABLE = "albums";
        public static final String _ID = "albums._id";
        public static final Albums INSTANCE = new Albums();
        private static final String NUM_ALL_FILES = "albums.num_all_files";
        private static final String DURATION_ALL = "albums.duration_all";
        private static final String DUR_ALL_META = "albums.dur_all_meta";

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDURATION_ALL$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDUR_ALL_META$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getNUM_ALL_FILES$annotations() {
        }

        private Albums() {
        }

        public final String getNUM_ALL_FILES() {
            return NUM_ALL_FILES;
        }

        public final String getDURATION_ALL() {
            return DURATION_ALL;
        }

        public final String getDUR_ALL_META() {
            return DUR_ALL_META;
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0015\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0003\u001a\u0004\b\u0016\u0010\u0013R\u001c\u0010\u0017\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0003\u001a\u0004\b\u0019\u0010\u0013¨\u0006\u001a"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$Artists;", "", "<init>", "()V", "TABLE", "", "_ID", "ARTIST", "ARTIST_SORT", "CREATED_AT", "AA_STATUS", "NUM_FILES", "DURATION", "DUR_META", "IS_UNSPLIT", "PLAYED_AT", "NUM_ALL_FILES", "getNUM_ALL_FILES$annotations", "getNUM_ALL_FILES", "()Ljava/lang/String;", "DURATION_ALL", "getDURATION_ALL$annotations", "getDURATION_ALL", "DUR_ALL_META", "getDUR_ALL_META$annotations", "getDUR_ALL_META", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Artists {
        public static final String AA_STATUS = "artists.aa_status";
        public static final String ARTIST = "artist";
        public static final String ARTIST_SORT = "artist_sort";
        public static final String CREATED_AT = "artists.created_at";
        public static final String DURATION = "artists.duration";
        public static final String DUR_META = "artists.dur_meta";
        public static final String IS_UNSPLIT = "artists.is_unsplit";
        public static final String NUM_FILES = "artists.num_files";
        public static final String PLAYED_AT = "artists.played_at";
        public static final String TABLE = "artists";
        public static final String _ID = "artists._id";
        public static final Artists INSTANCE = new Artists();
        private static final String NUM_ALL_FILES = "artists.num_all_files";
        private static final String DURATION_ALL = "artists.duration_all";
        private static final String DUR_ALL_META = "artists.dur_all_meta";

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDURATION_ALL$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDUR_ALL_META$annotations() {
        }

        @Deprecated(message = "since 864. NUM_FILES is now dynamically updated depending on show cue images preference")
        public static /* synthetic */ void getNUM_ALL_FILES$annotations() {
        }

        private Artists() {
        }

        public final String getNUM_ALL_FILES() {
            return NUM_ALL_FILES;
        }

        public final String getDURATION_ALL() {
            return DURATION_ALL;
        }

        public final String getDUR_ALL_META() {
            return DUR_ALL_META;
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$MultiArtists;", "", "<init>", "()V", "TABLE", "", "_ID", "ARTIST_ID", "FILE_ID", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class MultiArtists {
        public static final String ARTIST_ID = "multi_artists.artist_id";
        public static final String FILE_ID = "multi_artists.file_id";
        public static final MultiArtists INSTANCE = new MultiArtists();
        public static final String TABLE = "multi_artists";
        public static final String _ID = "multi_artists._id";

        private MultiArtists() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0015\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0003\u001a\u0004\b\u0016\u0010\u0013R\u001c\u0010\u0017\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0003\u001a\u0004\b\u0019\u0010\u0013¨\u0006\u001a"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$AlbumArtists;", "", "<init>", "()V", "TABLE", "", "_ID", "ALBUM_ARTIST", "ALBUM_ARTIST_SORT", "CREATED_AT", "AA_STATUS", "NUM_FILES", "DURATION", "DUR_META", "IS_UNSPLIT", "PLAYED_AT", "DUR_ALL_META", "getDUR_ALL_META$annotations", "getDUR_ALL_META", "()Ljava/lang/String;", "DURATION_ALL", "getDURATION_ALL$annotations", "getDURATION_ALL", "NUM_ALL_FILES", "getNUM_ALL_FILES$annotations", "getNUM_ALL_FILES", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class AlbumArtists {
        public static final String AA_STATUS = "album_artists.aa_status";
        public static final String ALBUM_ARTIST = "album_artist";
        public static final String ALBUM_ARTIST_SORT = "album_artist_sort";
        public static final String CREATED_AT = "album_artists.created_at";
        public static final String DURATION = "album_artists.duration";
        public static final String DUR_META = "album_artists.dur_meta";
        public static final String IS_UNSPLIT = "album_artists.is_unsplit";
        public static final String NUM_FILES = "album_artists.num_files";
        public static final String PLAYED_AT = "album_artists.played_at";
        public static final String TABLE = "album_artists";
        public static final String _ID = "album_artists._id";
        public static final AlbumArtists INSTANCE = new AlbumArtists();
        private static final String DUR_ALL_META = "album_artists.dur_all_meta";
        private static final String DURATION_ALL = "album_artists.duration_all";
        private static final String NUM_ALL_FILES = "album_artists.num_all_files";

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDURATION_ALL$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDUR_ALL_META$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getNUM_ALL_FILES$annotations() {
        }

        private AlbumArtists() {
        }

        public final String getDUR_ALL_META() {
            return DUR_ALL_META;
        }

        public final String getDURATION_ALL() {
            return DURATION_ALL;
        }

        public final String getNUM_ALL_FILES() {
            return NUM_ALL_FILES;
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$MultiAlbumArtists;", "", "<init>", "()V", "TABLE", "", "_ID", "ALBUM_ARTIST_ID", "FILE_ID", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class MultiAlbumArtists {
        public static final String ALBUM_ARTIST_ID = "multi_album_artists.album_artist_id";
        public static final String FILE_ID = "multi_album_artists.file_id";
        public static final MultiAlbumArtists INSTANCE = new MultiAlbumArtists();
        public static final String TABLE = "multi_album_artists";
        public static final String _ID = "multi_album_artists._id";

        private MultiAlbumArtists() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0003\u001a\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0017\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$AlbumsByArtist;", "", "<init>", "()V", "TABLE", "", "_ID", "ARTIST_ID", "ALBUM_ID", "CREATED_AT", "NUM_FILES", "DURATION", "PLAYED_AT", "NUM_ALL_FILES", "getNUM_ALL_FILES$annotations", "getNUM_ALL_FILES", "()Ljava/lang/String;", "DURATION_ALL", "getDURATION_ALL$annotations", "getDURATION_ALL", "DUR_META", "DUR_ALL_META", "getDUR_ALL_META$annotations", "getDUR_ALL_META", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class AlbumsByArtist {
        public static final String ALBUM_ID = "artist_albums.album_id";
        public static final String ARTIST_ID = "artist_albums.artist_id";
        public static final String CREATED_AT = "artist_albums.created_at";
        public static final String DURATION = "artist_albums.duration";
        public static final String DUR_META = "artist_albums.dur_meta";
        public static final String NUM_FILES = "artist_albums.num_files";
        public static final String PLAYED_AT = "artist_albums.played_at";
        public static final String TABLE = "artist_albums";
        public static final String _ID = "artist_albums._id";
        public static final AlbumsByArtist INSTANCE = new AlbumsByArtist();
        private static final String NUM_ALL_FILES = "artist_albums.num_all_files";
        private static final String DURATION_ALL = "artist_albums.duration_all";
        private static final String DUR_ALL_META = "artist_albums.dur_all_meta";

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDURATION_ALL$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDUR_ALL_META$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getNUM_ALL_FILES$annotations() {
        }

        private AlbumsByArtist() {
        }

        public final String getNUM_ALL_FILES() {
            return NUM_ALL_FILES;
        }

        public final String getDURATION_ALL() {
            return DURATION_ALL;
        }

        public final String getDUR_ALL_META() {
            return DUR_ALL_META;
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0015\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0003\u001a\u0004\b\u0016\u0010\u0013R\u001c\u0010\u0017\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0003\u001a\u0004\b\u0019\u0010\u0013¨\u0006\u001a"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$Composers;", "", "<init>", "()V", "TABLE", "", "_ID", "COMPOSER", "COMPOSER_SORT", "CREATED_AT", "AA_STATUS", "NUM_FILES", "DURATION", "DUR_META", "IS_UNSPLIT", "PLAYED_AT", "NUM_ALL_FILES", "getNUM_ALL_FILES$annotations", "getNUM_ALL_FILES", "()Ljava/lang/String;", "DURATION_ALL", "getDURATION_ALL$annotations", "getDURATION_ALL", "DUR_ALL_META", "getDUR_ALL_META$annotations", "getDUR_ALL_META", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Composers {
        public static final String AA_STATUS = "composers.aa_status";
        public static final String COMPOSER = "composer";
        public static final String COMPOSER_SORT = "composer_sort";
        public static final String CREATED_AT = "composers.created_at";
        public static final String DURATION = "composers.duration";
        public static final String DUR_META = "composers.dur_meta";
        public static final String IS_UNSPLIT = "composers.is_unsplit";
        public static final String NUM_FILES = "composers.num_files";
        public static final String PLAYED_AT = "composers.played_at";
        public static final String TABLE = "composers";
        public static final String _ID = "composers._id";
        public static final Composers INSTANCE = new Composers();
        private static final String NUM_ALL_FILES = "composers.num_all_files";
        private static final String DURATION_ALL = "composers.duration_all";
        private static final String DUR_ALL_META = "composers.dur_all_meta";

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDURATION_ALL$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDUR_ALL_META$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getNUM_ALL_FILES$annotations() {
        }

        private Composers() {
        }

        public final String getNUM_ALL_FILES() {
            return NUM_ALL_FILES;
        }

        public final String getDURATION_ALL() {
            return DURATION_ALL;
        }

        public final String getDUR_ALL_META() {
            return DUR_ALL_META;
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$MultiComposers;", "", "<init>", "()V", "TABLE", "", "_ID", "COMPOSER_ID", "FILE_ID", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class MultiComposers {
        public static final String COMPOSER_ID = "multi_composers.composer_id";
        public static final String FILE_ID = "multi_composers.file_id";
        public static final MultiComposers INSTANCE = new MultiComposers();
        public static final String TABLE = "multi_composers";
        public static final String _ID = "multi_composers._id";

        private MultiComposers() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0003\u001a\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0017\u0010\u0011¨\u0006\u0018"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$Genres;", "", "<init>", "()V", "TABLE", "", "_ID", "GENRE", "CREATED_AT", "NUM_FILES", "DURATION", "DUR_META", "AA_STATUS", "PLAYED_AT", "NUM_ALL_FILES", "getNUM_ALL_FILES$annotations", "getNUM_ALL_FILES", "()Ljava/lang/String;", "DUR_ALL_META", "getDUR_ALL_META$annotations", "getDUR_ALL_META", "DURATION_ALL", "getDURATION_ALL$annotations", "getDURATION_ALL", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Genres {
        public static final String AA_STATUS = "genres.aa_status";
        public static final String CREATED_AT = "genres.created_at";
        public static final String DURATION = "genres.duration";
        public static final String DUR_META = "genres.dur_meta";
        public static final String GENRE = "genre";
        public static final String NUM_FILES = "genres.num_files";
        public static final String PLAYED_AT = "genres.played_at";
        public static final String TABLE = "genres";
        public static final String _ID = "genres._id";
        public static final Genres INSTANCE = new Genres();
        private static final String NUM_ALL_FILES = "genres.num_all_files";
        private static final String DUR_ALL_META = "genres.dur_all_meta";
        private static final String DURATION_ALL = "genres.duration_all";

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDURATION_ALL$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDUR_ALL_META$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getNUM_ALL_FILES$annotations() {
        }

        private Genres() {
        }

        public final String getNUM_ALL_FILES() {
            return NUM_ALL_FILES;
        }

        public final String getDUR_ALL_META() {
            return DUR_ALL_META;
        }

        public final String getDURATION_ALL() {
            return DURATION_ALL;
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$GenreEntries;", "", "<init>", "()V", "TABLE", "", "_ID", "FOLDER_FILE_ID", "GENRE_ID", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class GenreEntries {
        public static final String FOLDER_FILE_ID = "folder_file_id";
        public static final String GENRE_ID = "genre_id";
        public static final GenreEntries INSTANCE = new GenreEntries();
        public static final String TABLE = "genre_entries";
        public static final String _ID = "genre_entries._id";

        private GenreEntries() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0003\u001a\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0017\u0010\u0011¨\u0006\u0018"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$Years;", "", "<init>", "()V", "TABLE", "", "_ID", "YEAR", "CREATED_AT", "NUM_FILES", "DURATION", "DUR_META", "AA_STATUS", "PLAYED_AT", "DUR_ALL_META", "getDUR_ALL_META$annotations", "getDUR_ALL_META", "()Ljava/lang/String;", "DURATION_ALL", "getDURATION_ALL$annotations", "getDURATION_ALL", "NUM_ALL_FILES", "getNUM_ALL_FILES$annotations", "getNUM_ALL_FILES", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Years {
        public static final String AA_STATUS = "years.aa_status";
        public static final String CREATED_AT = "years.created_at";
        public static final String DURATION = "years.duration";
        public static final String DUR_META = "years.dur_meta";
        public static final String NUM_FILES = "years.num_files";
        public static final String PLAYED_AT = "years.played_at";
        public static final String TABLE = "years";
        public static final String YEAR = "years.year";
        public static final String _ID = "years._id";
        public static final Years INSTANCE = new Years();
        private static final String DUR_ALL_META = "years.dur_all_meta";
        private static final String DURATION_ALL = "years.duration_all";
        private static final String NUM_ALL_FILES = "years.num_all_files";

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDURATION_ALL$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getDUR_ALL_META$annotations() {
        }

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getNUM_ALL_FILES$annotations() {
        }

        private Years() {
        }

        public final String getDUR_ALL_META() {
            return DUR_ALL_META;
        }

        public final String getDURATION_ALL() {
            return DURATION_ALL;
        }

        public final String getNUM_ALL_FILES() {
            return NUM_ALL_FILES;
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$CatStats;", "", "<init>", "()V", "TABLE", "", "_ID", "TYPE", "REF_ID", "REF_ID2", "NUM_FILES", "DURATION", "DUR_META", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class CatStats {
        public static final String DURATION = "cat_stats.duration";
        public static final String DUR_META = "cat_stats.dur_meta";
        public static final CatStats INSTANCE = new CatStats();
        public static final String NUM_FILES = "cat_stats.num_files";
        public static final String REF_ID = "cat_stats.ref_id";
        public static final String REF_ID2 = "cat_stats.ref_id2";
        public static final String TABLE = "cat_stats";
        public static final String TYPE = "cat_stats.type";
        public static final String _ID = "cat_stats._id";

        private CatStats() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$PlaylistEntries;", "", "<init>", "()V", "TABLE", "", "_ID", "FOLDER_FILE_ID", "PLAYLIST_ID", "SORT", "FILE_NAME", "FOLDER_PATH", "CUE_OFFSET_MS", "PLAYED_AT", "SHUFFLE_ORDER", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class PlaylistEntries {
        public static final String CUE_OFFSET_MS = "playlist_entries.cue_offset_ms";
        public static final String FILE_NAME = "file_name";
        public static final String FOLDER_FILE_ID = "folder_file_id";
        public static final String FOLDER_PATH = "folder_path";
        public static final PlaylistEntries INSTANCE = new PlaylistEntries();
        public static final String PLAYED_AT = "playlist_entries.played_at";
        public static final String PLAYLIST_ID = "playlist_id";
        public static final String SHUFFLE_ORDER = "playlist_entries.shuffle_order";
        public static final String SORT = "sort";
        public static final String TABLE = "playlist_entries";
        public static final String _ID = "playlist_entries._id";

        private PlaylistEntries() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$Playlists;", "", "<init>", "()V", "TABLE", "", "_ID", "PLAYLIST", "MTIME", "PATH", "CREATED_AT", "UPDATED_AT", "NUM_FILES", "AA_STATUS", "KEEP_LIST_POS", "KEEP_TRACK_POS", "KEEP_LIST_AND_TRACK_POS_COMBINED", "DURATION", "DUR_META", "IS_FILE", "PLAYED_AT", "NUM_ALL_FILES", "getNUM_ALL_FILES$annotations", "getNUM_ALL_FILES", "()Ljava/lang/String;", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Playlists {
        public static final String AA_STATUS = "playlists.aa_status";
        public static final String CREATED_AT = "playlists.created_at";
        public static final String DURATION = "playlists.duration";
        public static final String DUR_META = "playlists.dur_meta";
        public static final String IS_FILE = "playlists.playlist_path IS NOT NULL AS _is_file";
        public static final String KEEP_LIST_AND_TRACK_POS_COMBINED = "(playlists.keep_track_pos<<1)+playlists.keep_list_pos";
        public static final String KEEP_LIST_POS = "playlists.keep_list_pos";
        public static final String KEEP_TRACK_POS = "playlists.keep_track_pos";
        public static final String MTIME = "playlists.mtime";
        public static final String NUM_FILES = "playlists.num_files";
        public static final String PATH = "playlists.playlist_path";
        public static final String PLAYED_AT = "playlists.played_at";
        public static final String PLAYLIST = "playlists.playlist";
        public static final String TABLE = "playlists";
        public static final String UPDATED_AT = "playlists.updated_at";
        public static final String _ID = "playlists._id";
        public static final Playlists INSTANCE = new Playlists();
        private static final String NUM_ALL_FILES = "playlists.num_all_files";

        @Deprecated(message = "since 864")
        public static /* synthetic */ void getNUM_ALL_FILES$annotations() {
        }

        private Playlists() {
        }

        public final String getNUM_ALL_FILES() {
            return NUM_ALL_FILES;
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$Queue;", "", "<init>", "()V", "TABLE", "", "_ID", "FOLDER_FILE_ID", "CREATED_AT", "SORT", "SHUFFLE_ORDER", "CALC_PLAYED", "CALC_UNPLAYED", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Queue {
        public static final String CALC_PLAYED = "folder_files.played_at >= queue.created_at";
        public static final String CALC_UNPLAYED = "folder_files.played_at < queue.created_at";
        public static final String CREATED_AT = "queue.created_at";
        public static final String FOLDER_FILE_ID = "queue.folder_file_id";
        public static final Queue INSTANCE = new Queue();
        public static final String SHUFFLE_ORDER = "queue.shuffle_order";
        public static final String SORT = "queue.sort";
        public static final String TABLE = "queue";
        public static final String _ID = "queue._id";

        private Queue() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$Bookmarks;", "", "<init>", "()V", "TABLE", "", "_ID", "FOLDER_FILE_ID", "OFFSET_MS", "SORT", "META", "CREATED_AT", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Bookmarks {
        public static final String CREATED_AT = "bookmarks.created_at";
        public static final String FOLDER_FILE_ID = "bookmarks.folder_file_id";
        public static final Bookmarks INSTANCE = new Bookmarks();
        public static final String META = "bookmarks.meta";
        public static final String OFFSET_MS = "bookmarks.offset_ms";
        public static final String SORT = "bookmarks.sort";
        public static final String TABLE = "bookmarks";
        public static final String _ID = "bookmarks._id";

        private Bookmarks() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Deprecated(message = "")
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$ShuffleSessionIds;", "", "<init>", "()V", "TABLE", "", "_ID", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ShuffleSessionIds {
        public static final ShuffleSessionIds INSTANCE = new ShuffleSessionIds();
        public static final String TABLE = "shuffle_session_ids";
        public static final String _ID = "shuffle_session_ids._id";

        private ShuffleSessionIds() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b+\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010&\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b'\u0010\u0003R\u0016\u0010(\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b)\u0010\u0003R\u0016\u0010*\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b+\u0010\u0003R\u001c\u0010,\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u0003\u001a\u0004\b.\u0010/¨\u00060"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$EqPresets;", "", "<init>", "()V", "TABLE", "", "_ID", "PRESET", "_DATA", "DATA_BLOB", "DEFAULT_BLOB", "RESOLVED_BLOB", "PARAMETRIC", "NAME", "META", "UPDATED_AT", "SHARE_BLOB", "SHARE_BLOB_UPDATED_AT", "TYPE", "BIND_TO_SPEAKER", "BIND_TO_WIRED", "BIND_TO_BT", "BIND_TO_USB", "BIND_TO_OTHER", "BIND_TO_CHROMECAST", "META_BOUND_DEVICES", "META_BOUND_DEVICE_NAME", "BIND_TO_TRACK", "BOUND_TRACK_ID", "BIND_TO_CAT", "BOUND_CAT_URI", "UNBIND_FROM_ALL_TRACK_IDS", "UNBIND_FROM_ALL_DEVICES", "NUM_BIND_DEVICES", "BIND_TO_DEVICE_PREFIX", "DEVICE_PREFIX", "DEVICE_ADDRESS_PREFIX", "DEVICE_NAME_PREFIX", "BIND_TO_TRACK_ID", "getBIND_TO_TRACK_ID$annotations", "UNBIND_FROM_TRACK_ID", "getUNBIND_FROM_TRACK_ID$annotations", "BIND_TO_CAT_URI", "getBIND_TO_CAT_URI$annotations", "SORT", "getSORT$annotations", "getSORT", "()Ljava/lang/String;", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class EqPresets {
        public static final String BIND_TO_BT = "bind_to_bt";
        public static final String BIND_TO_CAT = "__bind_to_cat";
        public static final String BIND_TO_CAT_URI = "__bind_to_cat_uri";
        public static final String BIND_TO_CHROMECAST = "bind_to_cc";
        public static final String BIND_TO_DEVICE_PREFIX = "__bind_to_device_";
        public static final String BIND_TO_OTHER = "bind_to_other";
        public static final String BIND_TO_SPEAKER = "bind_to_speaker";
        public static final String BIND_TO_TRACK = "__bind_to_track";
        public static final String BIND_TO_TRACK_ID = "__bind_to_track_id";
        public static final String BIND_TO_USB = "bind_to_usb";
        public static final String BIND_TO_WIRED = "bind_to_wired";
        public static final String BOUND_CAT_URI = "__bound_cat_uri";
        public static final String BOUND_TRACK_ID = "__bound_track_id";
        public static final String DATA_BLOB = "eq_presets.data_blob";
        public static final String DEFAULT_BLOB = "eq_presets.default_blob";
        public static final String DEVICE_ADDRESS_PREFIX = "__device_address_";
        public static final String DEVICE_NAME_PREFIX = "__device_name_";
        public static final String DEVICE_PREFIX = "__device_";
        public static final String META = "eq_presets.meta";
        public static final String META_BOUND_DEVICES = "meta_bound_devices";
        public static final String META_BOUND_DEVICE_NAME = "meta_bound_device_name";
        public static final String NAME = "eq_presets.name";
        public static final String NUM_BIND_DEVICES = "__num_bind_devices";
        public static final String PARAMETRIC = "parametric";
        public static final String PRESET = "preset";
        public static final String RESOLVED_BLOB = "COALESCE(eq_presets.data_blob,eq_presets.default_blob)";
        public static final String SHARE_BLOB = "share_blob";
        public static final String SHARE_BLOB_UPDATED_AT = "share_blob_updated_at";
        public static final String TABLE = "eq_presets";
        public static final String TYPE = "eq_presets.type";
        public static final String UNBIND_FROM_ALL_DEVICES = "__unbind_from_all_devices";
        public static final String UNBIND_FROM_ALL_TRACK_IDS = "__unbind_from_all_track_ids";
        public static final String UNBIND_FROM_TRACK_ID = "__unbind_to_track_id";
        public static final String UPDATED_AT = "eq_presets.updated_at";
        public static final String _DATA = "eq_presets._data";
        public static final String _ID = "eq_presets._id";
        public static final EqPresets INSTANCE = new EqPresets();
        private static final String SORT = "eq_presets.sort";

        @Deprecated(message = "see {@link #BIND_TO_CAT}")
        public static /* synthetic */ void getBIND_TO_CAT_URI$annotations() {
        }

        @Deprecated(message = "see {@link #BIND_TO_TRACK} ")
        public static /* synthetic */ void getBIND_TO_TRACK_ID$annotations() {
        }

        @Deprecated(message = "not used since 962")
        public static /* synthetic */ void getSORT$annotations() {
        }

        @Deprecated(message = "see {@link #BIND_TO_TRACK} ")
        public static /* synthetic */ void getUNBIND_FROM_TRACK_ID$annotations() {
        }

        private EqPresets() {
        }

        public final String getSORT() {
            return SORT;
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$EqPresetSongs;", "", "<init>", "()V", "TABLE", "", "_ID", "FILE_ID", "PRESET_ID", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class EqPresetSongs {
        public static final String FILE_ID = "eq_preset_songs.file_id";
        public static final EqPresetSongs INSTANCE = new EqPresetSongs();
        public static final String PRESET_ID = "eq_preset_songs.preset_id";
        public static final String TABLE = "eq_preset_songs";
        public static final String _ID = "eq_preset_songs._id";

        private EqPresetSongs() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$EqPresetDevices;", "", "<init>", "()V", "TABLE", "", "_ID", "PRESET_ID", "DEVICE", "DEVICE_NAME", "DEVICE_ADDRESS", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class EqPresetDevices {
        public static final String DEVICE = "device";
        public static final String DEVICE_ADDRESS = "device_address";
        public static final String DEVICE_NAME = "device_name";
        public static final EqPresetDevices INSTANCE = new EqPresetDevices();
        public static final String PRESET_ID = "eq_preset_devices.preset_id";
        public static final String TABLE = "eq_preset_devices";
        public static final String _ID = "eq_preset_devices._id";

        private EqPresetDevices() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$KnownDevices;", "", "<init>", "()V", "TABLE", "", "_ID", "DEVICE_NAME", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class KnownDevices {
        public static final String DEVICE_NAME = "known_devices.device_name";
        public static final KnownDevices INSTANCE = new KnownDevices();
        public static final String TABLE = "known_devices";
        public static final String _ID = "known_devices._id";

        private KnownDevices() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$ReverbPresets;", "", "<init>", "()V", "TABLE", "", "_ID", "_DATA", "NAME", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ReverbPresets {
        public static final ReverbPresets INSTANCE = new ReverbPresets();
        public static final String NAME = "reverb_presets.name";
        public static final String TABLE = "reverb_presets";
        public static final String _DATA = "reverb_presets._data";
        public static final String _ID = "reverb_presets._id";

        private ReverbPresets() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$PrefSearch;", "", "<init>", "()V", "TABLE", "", "_ID", "BREADCRUMB", "PREF_URI", "PREF_KEY", "ICON", "TYPE", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class PrefSearch {
        public static final String BREADCRUMB = "breadcrumb";
        public static final String ICON = "icon";
        public static final PrefSearch INSTANCE = new PrefSearch();
        public static final String PREF_KEY = "pref_key";
        public static final String PREF_URI = "pref_uri";
        public static final String TABLE = "pref_search";
        public static final String TYPE = "type";
        public static final String _ID = "pref_search._id";

        private PrefSearch() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$PrefSearchFts;", "", "<init>", "()V", "TABLE", "", "DOCID", "TITLE", "SUMMARY", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class PrefSearchFts {
        public static final String DOCID = "docid";
        public static final PrefSearchFts INSTANCE = new PrefSearchFts();
        public static final String SUMMARY = "summary";
        public static final String TABLE = "pref_search_fts";
        public static final String TITLE = "title";

        private PrefSearchFts() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$SettingsSearchHistory;", "Lcom/maxmpz/poweramp/player/TableDefs$SearchHistory;", "<init>", "()V", "TABLE", "", "_ID", "URI", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class SettingsSearchHistory extends SearchHistory {
        public static final SettingsSearchHistory INSTANCE = new SettingsSearchHistory();
        public static final String TABLE = "settings_search_history";
        public static final String URI = "uri";
        public static final String _ID = "settings_search_history._id";

        private SettingsSearchHistory() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$LrcFiles;", "", "<init>", "()V", "TABLE", "", "_ID", "CREATED_AT", "UPDATED_AT", "MTIME", "TITLE", "ARTIST", "ALBUM", "LENGTH", "SIMPLE_FILENAME", "EXTENSION", "FOLDER_PATH", "IS_UTF8", "TAG_STATUS", "FULL_PATH", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class LrcFiles {
        public static final String ALBUM = "lrc_files.album";
        public static final String ARTIST = "lrc_files.artist";
        public static final String CREATED_AT = "lrc_files.created_at";
        public static final String EXTENSION = "lrc_files.extension";
        public static final String FOLDER_PATH = "lrc_files.folder_path";
        public static final String FULL_PATH = "lrc_files.folder_path||lrc_files.simple_filename||lrc_files.extension";
        public static final LrcFiles INSTANCE = new LrcFiles();
        public static final String IS_UTF8 = "lrc_files.is_utf8";
        public static final String LENGTH = "lrc_files.length";
        public static final String MTIME = "lrc_files.mtime";
        public static final String SIMPLE_FILENAME = "lrc_files.simple_filename";
        public static final String TABLE = "lrc_files";
        public static final String TAG_STATUS = "lrc_files.tag_status";
        public static final String TITLE = "lrc_files.title";
        public static final String UPDATED_AT = "lrc_files.updated_at";
        public static final String _ID = "lrc_files._id";

        private LrcFiles() {
        }
    }

    /* compiled from: TableDefs.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/maxmpz/poweramp/player/TableDefs$CachedLyrics;", "", "<init>", "()V", "TABLE", "", "_ID", "CREATED_AT", "UPDATED_AT", "CREATED_BY_PAK", "INFO_LINE", "CONTENT", "poweramp_api_lib_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class CachedLyrics {
        public static final String CONTENT = "cached_lyrics.content";
        public static final String CREATED_AT = "cached_lyrics.created_at";
        public static final String CREATED_BY_PAK = "cached_lyrics.created_by_pak";
        public static final String INFO_LINE = "cached_lyrics.info_line";
        public static final CachedLyrics INSTANCE = new CachedLyrics();
        public static final String TABLE = "cached_lyrics";
        public static final String UPDATED_AT = "cached_lyrics.updated_at";
        public static final String _ID = "cached_lyrics._id";

        private CachedLyrics() {
        }
    }
}
