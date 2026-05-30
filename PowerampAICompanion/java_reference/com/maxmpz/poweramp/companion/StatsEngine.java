package com.maxmpz.poweramp.companion;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import com.maxmpz.poweramp.player.PowerampAPI;
import com.maxmpz.poweramp.player.RouterConsts;
import com.maxmpz.poweramp.player.TableDefs;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: StatsEngine.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 82\u00020\u0001:\u00072345678B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u0011H\u0086@¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u0018J2\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u00122\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0012H\u0086@¢\u0006\u0002\u0010\u001eJ2\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u00122\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0012H\u0086@¢\u0006\u0002\u0010\u001eJ2\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u00122\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0012H\u0086@¢\u0006\u0002\u0010\u001eJ\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001aH\u0002J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001aH\u0002J\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0086@¢\u0006\u0002\u0010\u0013J\u001c\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u001a2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u0018J\u001c\u0010(\u001a\b\u0012\u0004\u0012\u00020'0\u001a2\u0006\u0010)\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010*J&\u0010+\u001a\b\u0012\u0004\u0012\u00020'0\u001a2\u0006\u0010,\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\rH\u0002J(\u0010/\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u00110\u001a2\u0006\u00100\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u00101R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/maxmpz/poweramp/companion/StatsEngine;", "", "context", "Landroid/content/Context;", "powerampController", "Lcom/maxmpz/poweramp/companion/PowerampController;", "<init>", "(Landroid/content/Context;Lcom/maxmpz/poweramp/companion/PowerampController;)V", "getAlbumArt", "Landroid/graphics/Bitmap;", "type", "", "id", "", "getAlbumArtUri", "Landroid/net/Uri;", "getListeningStreak", "Lkotlin/Pair;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHourlyDistribution", "", "range", "Lcom/maxmpz/poweramp/companion/StatsEngine$TimeRange;", "(Lcom/maxmpz/poweramp/companion/StatsEngine$TimeRange;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTopTracks", "", "Lcom/maxmpz/poweramp/companion/StatsEngine$StatItem;", "limit", "timeMachineYear", "(Lcom/maxmpz/poweramp/companion/StatsEngine$TimeRange;ILjava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTopArtists", "getTopAlbums", "getAllTracksFromPoweramp", "Lcom/maxmpz/poweramp/companion/StatsEngine$CachedTrack;", "getAllArtistsFromPoweramp", "Lcom/maxmpz/poweramp/companion/StatsEngine$CachedArtist;", "getAllAlbumsFromPoweramp", "getMergedScrobbles", "Lcom/maxmpz/poweramp/companion/Scrobble;", "getScrobblesForTimeMachine", "yearsAgo", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchLiveLastFmScrobbles", "username", "apiKey", "cutOffTime", "fetchTopTracksForTag", "tag", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ItemType", "StatItem", "TimeRange", "CachedTrack", "CachedArtist", "CachedAlbum", "Companion", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class StatsEngine {
    private static String cachedCsvPath;
    private static List<Scrobble> cachedCsvScrobbles;
    private final Context context;
    private final PowerampController powerampController;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<TimeRange, Pair<Long, List<Scrobble>>> sessionMergedScrobbles = new LinkedHashMap();

    public StatsEngine(Context context, PowerampController powerampController) {
        this.context = context;
        this.powerampController = powerampController;
    }

    /* compiled from: StatsEngine.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/maxmpz/poweramp/companion/StatsEngine$ItemType;", "", "<init>", "(Ljava/lang/String;I)V", "TRACK", "ARTIST", "ALBUM", "TIME", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public enum ItemType {
        TRACK,
        ARTIST,
        ALBUM,
        TIME;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<ItemType> getEntries() {
            return $ENTRIES;
        }
    }

    /* compiled from: StatsEngine.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010(\u001a\u00020\rHÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J[\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\tHÆ\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020\u0007HÖ\u0001J\t\u0010/\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u000e\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010!¨\u00060"}, d2 = {"Lcom/maxmpz/poweramp/companion/StatsEngine$StatItem;", "", "title", "", "subtitle", "album", "playCount", "", "firstScrobbleTime", "", "albumArtUri", "Landroid/net/Uri;", "type", "Lcom/maxmpz/poweramp/companion/StatsEngine$ItemType;", "pampId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLandroid/net/Uri;Lcom/maxmpz/poweramp/companion/StatsEngine$ItemType;J)V", "getTitle", "()Ljava/lang/String;", "getSubtitle", "getAlbum", "getPlayCount", "()I", "getFirstScrobbleTime", "()J", "getAlbumArtUri", "()Landroid/net/Uri;", "setAlbumArtUri", "(Landroid/net/Uri;)V", "getType", "()Lcom/maxmpz/poweramp/companion/StatsEngine$ItemType;", "getPampId", "setPampId", "(J)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final /* data */ class StatItem {
        private final String album;
        private Uri albumArtUri;
        private final long firstScrobbleTime;
        private long pampId;
        private final int playCount;
        private final String subtitle;
        private final String title;
        private final ItemType type;

        public static /* synthetic */ StatItem copy$default(StatItem statItem, String str, String str2, String str3, int i, long j, Uri uri, ItemType itemType, long j2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = statItem.title;
            }
            if ((i2 & 2) != 0) {
                str2 = statItem.subtitle;
            }
            if ((i2 & 4) != 0) {
                str3 = statItem.album;
            }
            if ((i2 & 8) != 0) {
                i = statItem.playCount;
            }
            if ((i2 & 16) != 0) {
                j = statItem.firstScrobbleTime;
            }
            if ((i2 & 32) != 0) {
                uri = statItem.albumArtUri;
            }
            if ((i2 & 64) != 0) {
                itemType = statItem.type;
            }
            if ((i2 & 128) != 0) {
                j2 = statItem.pampId;
            }
            long j3 = j;
            String str4 = str3;
            int i3 = i;
            return statItem.copy(str, str2, str4, i3, j3, uri, itemType, j2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* renamed from: component2, reason: from getter */
        public final String getSubtitle() {
            return this.subtitle;
        }

        /* renamed from: component3, reason: from getter */
        public final String getAlbum() {
            return this.album;
        }

        /* renamed from: component4, reason: from getter */
        public final int getPlayCount() {
            return this.playCount;
        }

        /* renamed from: component5, reason: from getter */
        public final long getFirstScrobbleTime() {
            return this.firstScrobbleTime;
        }

        /* renamed from: component6, reason: from getter */
        public final Uri getAlbumArtUri() {
            return this.albumArtUri;
        }

        /* renamed from: component7, reason: from getter */
        public final ItemType getType() {
            return this.type;
        }

        /* renamed from: component8, reason: from getter */
        public final long getPampId() {
            return this.pampId;
        }

        public final StatItem copy(String title, String subtitle, String album, int playCount, long firstScrobbleTime, Uri albumArtUri, ItemType type, long pampId) {
            return new StatItem(title, subtitle, album, playCount, firstScrobbleTime, albumArtUri, type, pampId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StatItem)) {
                return false;
            }
            StatItem statItem = (StatItem) other;
            return Intrinsics.areEqual(this.title, statItem.title) && Intrinsics.areEqual(this.subtitle, statItem.subtitle) && Intrinsics.areEqual(this.album, statItem.album) && this.playCount == statItem.playCount && this.firstScrobbleTime == statItem.firstScrobbleTime && Intrinsics.areEqual(this.albumArtUri, statItem.albumArtUri) && this.type == statItem.type && this.pampId == statItem.pampId;
        }

        public int hashCode() {
            return (((((((((((((this.title.hashCode() * 31) + this.subtitle.hashCode()) * 31) + this.album.hashCode()) * 31) + Integer.hashCode(this.playCount)) * 31) + Long.hashCode(this.firstScrobbleTime)) * 31) + (this.albumArtUri == null ? 0 : this.albumArtUri.hashCode())) * 31) + this.type.hashCode()) * 31) + Long.hashCode(this.pampId);
        }

        public String toString() {
            return "StatItem(title=" + this.title + ", subtitle=" + this.subtitle + ", album=" + this.album + ", playCount=" + this.playCount + ", firstScrobbleTime=" + this.firstScrobbleTime + ", albumArtUri=" + this.albumArtUri + ", type=" + this.type + ", pampId=" + this.pampId + ")";
        }

        public StatItem(String title, String subtitle, String album, int playCount, long firstScrobbleTime, Uri albumArtUri, ItemType type, long pampId) {
            this.title = title;
            this.subtitle = subtitle;
            this.album = album;
            this.playCount = playCount;
            this.firstScrobbleTime = firstScrobbleTime;
            this.albumArtUri = albumArtUri;
            this.type = type;
            this.pampId = pampId;
        }

        public /* synthetic */ StatItem(String str, String str2, String str3, int i, long j, Uri uri, ItemType itemType, long j2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i2 & 4) != 0 ? "" : str3, i, (i2 & 16) != 0 ? 0L : j, (i2 & 32) != 0 ? null : uri, (i2 & 64) != 0 ? ItemType.TRACK : itemType, (i2 & 128) != 0 ? -1L : j2);
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getSubtitle() {
            return this.subtitle;
        }

        public final String getAlbum() {
            return this.album;
        }

        public final int getPlayCount() {
            return this.playCount;
        }

        public final long getFirstScrobbleTime() {
            return this.firstScrobbleTime;
        }

        public final Uri getAlbumArtUri() {
            return this.albumArtUri;
        }

        public final void setAlbumArtUri(Uri uri) {
            this.albumArtUri = uri;
        }

        public final ItemType getType() {
            return this.type;
        }

        public final long getPampId() {
            return this.pampId;
        }

        public final void setPampId(long j) {
            this.pampId = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap getAlbumArt(String type, long id) {
        if (id <= 0) {
            return null;
        }
        Uri uri = getAlbumArtUri(type, id);
        Log.d("StatsDEBUG", "getAlbumArt: Attempting to open AA URI: " + uri);
        try {
            InputStream inputStream = this.context.getContentResolver().openInputStream(uri);
            if (inputStream == null) {
                Log.e("StatsDEBUG", "getAlbumArt: openInputStream returned NULL for " + uri);
                return null;
            }
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            if (bitmap == null) {
                Log.w("StatsDEBUG", "getAlbumArt: Bitmap decoding returned null for " + type + " id=" + id);
            } else {
                Log.d("StatsDEBUG", "getAlbumArt: Successfully decoded bitmap (" + bitmap.getWidth() + "x" + bitmap.getHeight() + ") for " + type + " id=" + id);
            }
            return bitmap;
        } catch (Exception e) {
            Log.e("StatsDEBUG", "getAlbumArt: Error decoding bitmap for " + type + " id=" + id + ". Uri: " + uri + ". Error: " + e.getMessage(), e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Uri getAlbumArtUri(String type, long id) {
        return PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath(type).appendEncodedPath(String.valueOf(id)).build();
    }

    public final Object getListeningStreak(Continuation<? super Pair<Integer, Integer>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new StatsEngine$getListeningStreak$2(this, null), continuation);
    }

    public final Object getHourlyDistribution(TimeRange range, Continuation<? super int[]> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new StatsEngine$getHourlyDistribution$2(this, range, null), continuation);
    }

    /* compiled from: StatsEngine.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/maxmpz/poweramp/companion/StatsEngine$TimeRange;", "", "<init>", "(Ljava/lang/String;I)V", "LAST_24_HOURS", "LAST_7_DAYS", "LAST_30_DAYS", "LAST_60_DAYS", "LAST_180_DAYS", "LAST_365_DAYS", "ALL_TIME", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public enum TimeRange {
        LAST_24_HOURS,
        LAST_7_DAYS,
        LAST_30_DAYS,
        LAST_60_DAYS,
        LAST_180_DAYS,
        LAST_365_DAYS,
        ALL_TIME;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<TimeRange> getEntries() {
            return $ENTRIES;
        }
    }

    public static /* synthetic */ Object getTopTracks$default(StatsEngine statsEngine, TimeRange timeRange, int i, Integer num, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 50;
        }
        if ((i2 & 4) != 0) {
            num = null;
        }
        return statsEngine.getTopTracks(timeRange, i, num, continuation);
    }

    public final Object getTopTracks(TimeRange range, int limit, Integer timeMachineYear, Continuation<? super List<StatItem>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new StatsEngine$getTopTracks$2(timeMachineYear, this, range, limit, null), continuation);
    }

    public static /* synthetic */ Object getTopArtists$default(StatsEngine statsEngine, TimeRange timeRange, int i, Integer num, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 50;
        }
        if ((i2 & 4) != 0) {
            num = null;
        }
        return statsEngine.getTopArtists(timeRange, i, num, continuation);
    }

    public final Object getTopArtists(TimeRange range, int limit, Integer timeMachineYear, Continuation<? super List<StatItem>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new StatsEngine$getTopArtists$2(timeMachineYear, this, range, limit, null), continuation);
    }

    public static /* synthetic */ Object getTopAlbums$default(StatsEngine statsEngine, TimeRange timeRange, int i, Integer num, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 50;
        }
        if ((i2 & 4) != 0) {
            num = null;
        }
        return statsEngine.getTopAlbums(timeRange, i, num, continuation);
    }

    public final Object getTopAlbums(TimeRange range, int limit, Integer timeMachineYear, Continuation<? super List<StatItem>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new StatsEngine$getTopAlbums$2(timeMachineYear, this, range, limit, null), continuation);
    }

    /* compiled from: StatsEngine.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/maxmpz/poweramp/companion/StatsEngine$CachedTrack;", "", "id", "", "title", "", "artist", "name", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getTitle", "()Ljava/lang/String;", "getArtist", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    private static final /* data */ class CachedTrack {
        private final String artist;
        private final long id;
        private final String name;
        private final String title;

        public static /* synthetic */ CachedTrack copy$default(CachedTrack cachedTrack, long j, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                j = cachedTrack.id;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                str = cachedTrack.title;
            }
            String str4 = str;
            if ((i & 4) != 0) {
                str2 = cachedTrack.artist;
            }
            String str5 = str2;
            if ((i & 8) != 0) {
                str3 = cachedTrack.name;
            }
            return cachedTrack.copy(j2, str4, str5, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* renamed from: component3, reason: from getter */
        public final String getArtist() {
            return this.artist;
        }

        /* renamed from: component4, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final CachedTrack copy(long id, String title, String artist, String name) {
            return new CachedTrack(id, title, artist, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CachedTrack)) {
                return false;
            }
            CachedTrack cachedTrack = (CachedTrack) other;
            return this.id == cachedTrack.id && Intrinsics.areEqual(this.title, cachedTrack.title) && Intrinsics.areEqual(this.artist, cachedTrack.artist) && Intrinsics.areEqual(this.name, cachedTrack.name);
        }

        public int hashCode() {
            return (((((Long.hashCode(this.id) * 31) + this.title.hashCode()) * 31) + this.artist.hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode());
        }

        public String toString() {
            return "CachedTrack(id=" + this.id + ", title=" + this.title + ", artist=" + this.artist + ", name=" + this.name + ")";
        }

        public CachedTrack(long id, String title, String artist, String name) {
            this.id = id;
            this.title = title;
            this.artist = artist;
            this.name = name;
        }

        public final String getArtist() {
            return this.artist;
        }

        public final long getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getTitle() {
            return this.title;
        }
    }

    /* compiled from: StatsEngine.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/maxmpz/poweramp/companion/StatsEngine$CachedArtist;", "", "id", "", "name", "", "<init>", "(JLjava/lang/String;)V", "getId", "()J", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    private static final /* data */ class CachedArtist {
        private final long id;
        private final String name;

        public static /* synthetic */ CachedArtist copy$default(CachedArtist cachedArtist, long j, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                j = cachedArtist.id;
            }
            if ((i & 2) != 0) {
                str = cachedArtist.name;
            }
            return cachedArtist.copy(j, str);
        }

        /* renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final CachedArtist copy(long id, String name) {
            return new CachedArtist(id, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CachedArtist)) {
                return false;
            }
            CachedArtist cachedArtist = (CachedArtist) other;
            return this.id == cachedArtist.id && Intrinsics.areEqual(this.name, cachedArtist.name);
        }

        public int hashCode() {
            return (Long.hashCode(this.id) * 31) + this.name.hashCode();
        }

        public String toString() {
            return "CachedArtist(id=" + this.id + ", name=" + this.name + ")";
        }

        public CachedArtist(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public final long getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* compiled from: StatsEngine.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/maxmpz/poweramp/companion/StatsEngine$CachedAlbum;", "", "id", "", "name", "", "artist", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getName", "()Ljava/lang/String;", "getArtist", "component1", "component2", "component3", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    private static final /* data */ class CachedAlbum {
        private final String artist;
        private final long id;
        private final String name;

        public static /* synthetic */ CachedAlbum copy$default(CachedAlbum cachedAlbum, long j, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = cachedAlbum.id;
            }
            if ((i & 2) != 0) {
                str = cachedAlbum.name;
            }
            if ((i & 4) != 0) {
                str2 = cachedAlbum.artist;
            }
            return cachedAlbum.copy(j, str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component3, reason: from getter */
        public final String getArtist() {
            return this.artist;
        }

        public final CachedAlbum copy(long id, String name, String artist) {
            return new CachedAlbum(id, name, artist);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CachedAlbum)) {
                return false;
            }
            CachedAlbum cachedAlbum = (CachedAlbum) other;
            return this.id == cachedAlbum.id && Intrinsics.areEqual(this.name, cachedAlbum.name) && Intrinsics.areEqual(this.artist, cachedAlbum.artist);
        }

        public int hashCode() {
            return (((Long.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + (this.artist == null ? 0 : this.artist.hashCode());
        }

        public String toString() {
            return "CachedAlbum(id=" + this.id + ", name=" + this.name + ", artist=" + this.artist + ")";
        }

        public CachedAlbum(long id, String name, String artist) {
            this.id = id;
            this.name = name;
            this.artist = artist;
        }

        public final String getArtist() {
            return this.artist;
        }

        public final long getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }
    }

    private final List<CachedTrack> getAllTracksFromPoweramp() {
        String str;
        String str2;
        String str3;
        List tracks = new ArrayList();
        Uri filesUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build();
        try {
            Cursor query = this.context.getContentResolver().query(filesUri, null, null, null, null);
            if (query != null) {
                Cursor cursor = query;
                try {
                    Cursor cursor2 = cursor;
                    int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("_id");
                    int columnIndex = cursor2.getColumnIndex(TableDefs.Files.TITLE_TAG);
                    int columnIndex2 = cursor2.getColumnIndex(TableDefs.Files.ARTIST_TAG);
                    int columnIndex3 = cursor2.getColumnIndex("artist");
                    int columnIndex4 = cursor2.getColumnIndex("name");
                    while (true) {
                        if (!cursor2.moveToNext()) {
                            break;
                        }
                        String str4 = "";
                        if (columnIndex < 0 || (str = cursor2.getString(columnIndex)) == null) {
                            str = "";
                        }
                        String string = columnIndex4 >= 0 ? cursor2.getString(columnIndex4) : null;
                        if (columnIndex2 < 0 || (str2 = cursor2.getString(columnIndex2)) == null) {
                            str2 = "";
                        }
                        if (columnIndex3 < 0 || (str3 = cursor2.getString(columnIndex3)) == null) {
                            str3 = "";
                        }
                        String str5 = str3;
                        String str6 = str2;
                        if (str6.length() == 0) {
                            str6 = str5;
                        }
                        String str7 = str6;
                        long j = cursor2.getLong(columnIndexOrThrow);
                        String str8 = str;
                        if (str8.length() == 0) {
                            if (string != null) {
                                str4 = string;
                            }
                            str8 = str4;
                        }
                        tracks.add(new CachedTrack(j, str8, str7, string));
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } finally {
                }
            }
        } catch (Exception e) {
            Log.e("StatsEngine", "getAllTracksFromPoweramp failed: " + e.getMessage());
            e.printStackTrace();
        }
        return tracks;
    }

    private final List<CachedArtist> getAllArtistsFromPoweramp() {
        String string;
        List artists = new ArrayList();
        Uri uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Artists.TABLE).build();
        try {
            Cursor query = this.context.getContentResolver().query(uri, null, null, null, null);
            if (query != null) {
                Cursor cursor = query;
                try {
                    Cursor cursor2 = cursor;
                    int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("_id");
                    int columnIndex = cursor2.getColumnIndex("artist");
                    while (cursor2.moveToNext()) {
                        long j = cursor2.getLong(columnIndexOrThrow);
                        String str = "";
                        if (columnIndex >= 0 && (string = cursor2.getString(columnIndex)) != null) {
                            str = string;
                        }
                        artists.add(new CachedArtist(j, str));
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } finally {
                }
            }
        } catch (Exception e) {
            Log.e("StatsEngine", "getAllArtistsFromPoweramp failed: " + e.getMessage());
            e.printStackTrace();
        }
        return artists;
    }

    public final Object getAllAlbumsFromPoweramp(Continuation<? super List<StatItem>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new StatsEngine$getAllAlbumsFromPoweramp$2(this, null), continuation);
    }

    /* compiled from: StatsEngine.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010\t\u001a \u0012\u0004\u0012\u00020\u000b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/maxmpz/poweramp/companion/StatsEngine$Companion;", "", "<init>", "()V", "cachedCsvScrobbles", "", "Lcom/maxmpz/poweramp/companion/Scrobble;", "cachedCsvPath", "", "sessionMergedScrobbles", "", "Lcom/maxmpz/poweramp/companion/StatsEngine$TimeRange;", "Lkotlin/Pair;", "", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final Object getMergedScrobbles(TimeRange range, Continuation<? super List<Scrobble>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new StatsEngine$getMergedScrobbles$2(range, this, null), continuation);
    }

    public final Object getScrobblesForTimeMachine(int yearsAgo, Continuation<? super List<Scrobble>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new StatsEngine$getScrobblesForTimeMachine$2(this, yearsAgo, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Scrobble> fetchLiveLastFmScrobbles(String username, String apiKey, long cutOffTime) {
        String urlString;
        HttpURLConnection connection;
        BufferedReader bufferedReader;
        int limit;
        String str;
        String urlString2;
        String artist;
        JSONObject optJSONObject;
        String album;
        String optString;
        String optString2;
        List liveScrobbles = new ArrayList();
        int limit2 = 200;
        try {
            urlString = "https://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=" + username + "&api_key=" + apiKey + "&format=json&limit=200";
            URL url = new URL(urlString);
            URLConnection openConnection = url.openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            connection = (HttpURLConnection) openConnection;
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
        } catch (Exception e) {
            e = e;
        }
        try {
            if (connection.getResponseCode() == 200) {
                Reader inputStreamReader = new InputStreamReader(connection.getInputStream(), Charsets.UTF_8);
                if (inputStreamReader instanceof BufferedReader) {
                    try {
                        bufferedReader = (BufferedReader) inputStreamReader;
                    } catch (Exception e2) {
                        e = e2;
                        Log.e("StatsDEBUG", "Exception fetching Last.fm API: " + e.getMessage());
                        return liveScrobbles;
                    }
                } else {
                    bufferedReader = new BufferedReader(inputStreamReader, 8192);
                }
                BufferedReader bufferedReader2 = bufferedReader;
                try {
                    String response = TextStreamsKt.readText(bufferedReader2);
                    CloseableKt.closeFinally(bufferedReader2, null);
                    JSONObject json = new JSONObject(response);
                    JSONObject recentTracks = json.optJSONObject("recenttracks");
                    JSONArray trackArray = recentTracks != null ? recentTracks.optJSONArray("track") : null;
                    if (trackArray != null) {
                        int i = 0;
                        int length = trackArray.length();
                        while (i < length) {
                            JSONObject trackObj = trackArray.optJSONObject(i);
                            if (trackObj == null) {
                                limit = limit2;
                                urlString2 = urlString;
                            } else {
                                JSONObject optJSONObject2 = trackObj.optJSONObject("@attr");
                                if (optJSONObject2 != null) {
                                    limit = limit2;
                                    str = optJSONObject2.optString("nowplaying");
                                } else {
                                    limit = limit2;
                                    str = null;
                                }
                                boolean isNowPlaying = Intrinsics.areEqual(str, "true");
                                if (isNowPlaying) {
                                    urlString2 = urlString;
                                } else {
                                    JSONObject dateObj = trackObj.optJSONObject("date");
                                    if (dateObj != null) {
                                        long timestamp = 1000 * dateObj.optLong("uts");
                                        if (cutOffTime <= 0 || timestamp >= cutOffTime) {
                                            JSONObject optJSONObject3 = trackObj.optJSONObject("artist");
                                            urlString2 = urlString;
                                            if (optJSONObject3 != null && (optString2 = optJSONObject3.optString("#text", "")) != null) {
                                                artist = optString2;
                                                optJSONObject = trackObj.optJSONObject("album");
                                                if (optJSONObject != null && (optString = optJSONObject.optString("#text", "")) != null) {
                                                    album = optString;
                                                    String title = trackObj.optString("name", "");
                                                    Calendar cal = Calendar.getInstance();
                                                    cal.setTimeInMillis(timestamp);
                                                    liveScrobbles.add(new Scrobble(artist, album, title, timestamp, cal.get(11), cal.get(1), cal.get(6)));
                                                }
                                                album = "";
                                                String title2 = trackObj.optString("name", "");
                                                Calendar cal2 = Calendar.getInstance();
                                                cal2.setTimeInMillis(timestamp);
                                                liveScrobbles.add(new Scrobble(artist, album, title2, timestamp, cal2.get(11), cal2.get(1), cal2.get(6)));
                                            }
                                            artist = "";
                                            optJSONObject = trackObj.optJSONObject("album");
                                            if (optJSONObject != null) {
                                                album = optString;
                                                String title22 = trackObj.optString("name", "");
                                                Calendar cal22 = Calendar.getInstance();
                                                cal22.setTimeInMillis(timestamp);
                                                liveScrobbles.add(new Scrobble(artist, album, title22, timestamp, cal22.get(11), cal22.get(1), cal22.get(6)));
                                            }
                                            album = "";
                                            String title222 = trackObj.optString("name", "");
                                            Calendar cal222 = Calendar.getInstance();
                                            cal222.setTimeInMillis(timestamp);
                                            liveScrobbles.add(new Scrobble(artist, album, title222, timestamp, cal222.get(11), cal222.get(1), cal222.get(6)));
                                        } else {
                                            urlString2 = urlString;
                                        }
                                    } else {
                                        urlString2 = urlString;
                                    }
                                }
                            }
                            i++;
                            limit2 = limit;
                            urlString = urlString2;
                        }
                    }
                    Log.d("StatsDEBUG", "Successfully fetched " + liveScrobbles.size() + " live scrobbles from Last.fm API");
                } finally {
                }
            } else {
                Log.e("StatsDEBUG", "Last.fm API returned HTTP " + connection.getResponseCode());
            }
        } catch (Exception e3) {
            e = e3;
            Log.e("StatsDEBUG", "Exception fetching Last.fm API: " + e.getMessage());
            return liveScrobbles;
        }
        return liveScrobbles;
    }

    public final Object fetchTopTracksForTag(String tag, Continuation<? super List<Pair<String, String>>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new StatsEngine$fetchTopTracksForTag$2(this, tag, null), continuation);
    }
}
