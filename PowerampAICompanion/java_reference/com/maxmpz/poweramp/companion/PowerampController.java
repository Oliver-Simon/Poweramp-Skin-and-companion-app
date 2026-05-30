package com.maxmpz.poweramp.companion;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.maxmpz.poweramp.player.PowerampAPI;
import com.maxmpz.poweramp.player.RouterConsts;
import com.maxmpz.poweramp.player.TableDefs;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PowerampController.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001<B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007J&\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0018\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u00170\u0014J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u0006\u0010\u001c\u001a\u00020\u0018J\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014J\u0006\u0010\u001f\u001a\u00020 J\u0014\u0010!\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014J\b\u0010#\u001a\u00020\u0011H\u0002J \u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0007H\u0002J\u0010\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u0018H\u0002J\u0014\u0010+\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014J\u0014\u0010,\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014J\"\u0010-\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u00170\u00142\b\b\u0002\u0010.\u001a\u00020/J\"\u00100\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u00170\u00142\b\b\u0002\u0010.\u001a\u00020/J\u0006\u00101\u001a\u00020/J\u000e\u00102\u001a\u00020/2\u0006\u0010\u0012\u001a\u00020\u0007J\u0018\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u00072\b\b\u0002\u0010.\u001a\u00020/J\"\u00105\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020/0\u00170\u00142\b\b\u0002\u0010.\u001a\u00020/J\"\u00106\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020/0\u00170\u00142\b\b\u0002\u0010.\u001a\u00020/J\u000e\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u0018J\u0016\u0010:\u001a\u0002082\u0006\u00109\u001a\u00020\u00182\u0006\u0010;\u001a\u00020\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/maxmpz/poweramp/companion/PowerampController;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "currentQueueId", "", "getCurrentQueueId", "()J", "setCurrentQueueId", "(J)V", "mainHandler", "Landroid/os/Handler;", "random", "Ljava/util/Random;", "playTrack", "", "trackId", "findTracks", "", "Lcom/maxmpz/poweramp/companion/PowerampTrack;", "recommendedTracks", "Lkotlin/Pair;", "", "getAlbumArt", "Landroid/graphics/Bitmap;", "folderFileId", "getFullLibraryJsonForGemini", "getTracksByIds", "ids", "getQueueStats", "Lcom/maxmpz/poweramp/companion/QueueStats;", "sendToPoweramp", "fileIds", "askForPowerampPermission", "performShuffleShift", "queueUri", "Landroid/net/Uri;", "startShuffle", "shiftCount", PowerampAPI.SHOW_TOAST, "message", "sendToPowerampQueue", "sendToPowerampQueueNext", "getTopPlayedTracks", "limit", "", "getRecentlyAddedTracks", "getQueueSize", "getQueuePosition", "getRecentQueueHistory", "currentTrackId", "getTopPlayedArtists", "getTopGenres", "findArtist", "", "artistName", "findAlbum", "albumName", "Either", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class PowerampController {
    private final Context context;
    private long currentQueueId = -1;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Random random = new Random();

    public PowerampController(Context context) {
        this.context = context;
    }

    public final long getCurrentQueueId() {
        return this.currentQueueId;
    }

    public final void setCurrentQueueId(long j) {
        this.currentQueueId = j;
    }

    public final void playTrack(long trackId) {
        Log.d("StatsDEBUG", "PowerampController: playTrack called for trackId: " + trackId);
        Uri trackUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(String.valueOf(trackId)).build();
        Intent playIntent = new Intent(PowerampAPI.ACTION_API_COMMAND);
        playIntent.setComponent(new ComponentName(PowerampAPI.PACKAGE_NAME, PowerampAPI.API_RECEIVER_NAME));
        playIntent.putExtra("cmd", 20);
        playIntent.setData(trackUri);
        try {
            Log.d("StatsDEBUG", "PowerampController: Sending broadcast intent: " + playIntent + " with data: " + playIntent.getData());
            this.context.sendBroadcast(playIntent);
        } catch (Exception e) {
            Log.e("StatsDEBUG", "PowerampController: Failed to send broadcast for playTrack", e);
        }
    }

    public final List<PowerampTrack> findTracks(List<Pair<String, String>> recommendedTracks) {
        Object obj;
        boolean isNewTracksRequest;
        boolean isNewTracksRequest2;
        boolean isNewTracksRequest3;
        boolean isUnderratedRequest;
        boolean isUnderratedRequest2;
        Uri filesUri;
        String[] projection;
        String str;
        Pair keywordSearchToken;
        String str2;
        String str3;
        Cursor cursor;
        String str4;
        String str5;
        String str6;
        String str7;
        Iterable iterable;
        Iterable iterable2;
        PowerampController powerampController;
        String str8;
        String str9;
        String str10;
        String str11;
        int randomLimit;
        List bestOfNew;
        boolean z;
        String str12;
        String str13;
        String str14;
        String str15;
        Throwable th;
        Cursor cursor2;
        int i;
        int columnIndexOrThrow;
        int columnIndex;
        int columnIndex2;
        int columnIndex3;
        int columnIndex4;
        int columnIndex5;
        Iterable iterable3;
        Collection arrayList;
        Iterable iterable4;
        Iterator it;
        String str16;
        String str17;
        String str18;
        String str19;
        Set set;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        List foundTracks = new ArrayList();
        Uri filesUri2 = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build();
        String[] projection2 = {TableDefs.Files._ID, TableDefs.Files.TITLE_TAG, TableDefs.Files.NAME, TableDefs.Files.ALBUM_ID, TableDefs.Files.DURATION, "artist"};
        Iterator<T> it2 = recommendedTracks.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (StringsKt.startsWith$default((String) ((Pair) obj).getFirst(), "MAGIC_TOKEN_KEYWORD_SEARCH|", false, 2, (Object) null)) {
                break;
            }
        }
        Pair keywordSearchToken2 = (Pair) obj;
        List<Pair<String, String>> list = recommendedTracks;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator it3 = list.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    isNewTracksRequest = false;
                    break;
                }
                if (Intrinsics.areEqual(((Pair) it3.next()).getFirst(), "MAGIC_TOKEN_NEW_TRACKS")) {
                    isNewTracksRequest = true;
                    break;
                }
            }
        } else {
            isNewTracksRequest = false;
        }
        List<Pair<String, String>> list2 = recommendedTracks;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            isNewTracksRequest2 = isNewTracksRequest;
            isNewTracksRequest3 = false;
        } else {
            Iterator it4 = list2.iterator();
            while (true) {
                if (!it4.hasNext()) {
                    isNewTracksRequest2 = isNewTracksRequest;
                    isNewTracksRequest3 = false;
                    break;
                }
                isNewTracksRequest2 = isNewTracksRequest;
                Iterable iterable5 = list2;
                if (Intrinsics.areEqual(((Pair) it4.next()).getFirst(), "MAGIC_TOKEN_UNDERRATED_TRACKS")) {
                    isNewTracksRequest3 = true;
                    break;
                }
                isNewTracksRequest = isNewTracksRequest2;
                list2 = iterable5;
            }
        }
        List<Pair<String, String>> list3 = recommendedTracks;
        if ((list3 instanceof Collection) && list3.isEmpty()) {
            isUnderratedRequest = isNewTracksRequest3;
            isUnderratedRequest2 = false;
        } else {
            Iterator it5 = list3.iterator();
            while (true) {
                if (!it5.hasNext()) {
                    isUnderratedRequest = isNewTracksRequest3;
                    isUnderratedRequest2 = false;
                    break;
                }
                isUnderratedRequest = isNewTracksRequest3;
                Iterable iterable6 = list3;
                if (Intrinsics.areEqual(((Pair) it5.next()).getFirst(), "MAGIC_TOKEN_FALLBACK")) {
                    isUnderratedRequest2 = true;
                    break;
                }
                isNewTracksRequest3 = isUnderratedRequest;
                list3 = iterable6;
            }
        }
        if (keywordSearchToken2 != null || isNewTracksRequest2 || isUnderratedRequest) {
            filesUri = filesUri2;
            projection = projection2;
            str = TableDefs.Files.ARTIST_TAG;
            keywordSearchToken = keywordSearchToken2;
            str2 = TypedValues.TransitionType.S_DURATION;
            str3 = "name";
        } else {
            if (!isUnderratedRequest2) {
                try {
                    try {
                        Cursor cursor3 = this.context.getContentResolver().query(filesUri2, projection2, null, null, null);
                        if (cursor3 != null) {
                            try {
                                Cursor cursor4 = cursor3;
                                try {
                                    cursor2 = cursor4;
                                    i = 0;
                                    columnIndexOrThrow = cursor2.getColumnIndexOrThrow("_id");
                                    columnIndex = cursor2.getColumnIndex(TableDefs.Files.ARTIST_TAG);
                                    columnIndex2 = cursor2.getColumnIndex("artist");
                                    columnIndex3 = cursor2.getColumnIndex(TableDefs.Files.TITLE_TAG);
                                    columnIndex4 = cursor2.getColumnIndex("name");
                                    columnIndex5 = cursor2.getColumnIndex(TypedValues.TransitionType.S_DURATION);
                                    try {
                                        iterable3 = recommendedTracks;
                                        try {
                                            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                                            iterable4 = iterable3;
                                            it = iterable4.iterator();
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                }
                                while (true) {
                                    Iterable iterable7 = iterable3;
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    Pair pair = (Pair) it.next();
                                    Iterable iterable8 = iterable4;
                                    int i2 = i;
                                    String lowerCase = StringsKt.trim((CharSequence) pair.getFirst()).toString().toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                    Pair keywordSearchToken3 = keywordSearchToken2;
                                    try {
                                        String lowerCase2 = StringsKt.trim((CharSequence) pair.getSecond()).toString().toLowerCase(Locale.ROOT);
                                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                                        arrayList.add(lowerCase + "|" + lowerCase2);
                                        iterable3 = iterable7;
                                        i = i2;
                                        iterable4 = iterable8;
                                        keywordSearchToken2 = keywordSearchToken3;
                                    } catch (Throwable th5) {
                                        th = th5;
                                    }
                                    th = th5;
                                    try {
                                        throw th;
                                    } catch (Throwable th6) {
                                        CloseableKt.closeFinally(cursor4, th);
                                        throw th6;
                                    }
                                }
                                Set set2 = CollectionsKt.toSet((List) arrayList);
                                while (cursor2.moveToNext()) {
                                    long j = cursor2.getLong(columnIndexOrThrow);
                                    if (columnIndex < 0 || (str16 = cursor2.getString(columnIndex)) == null) {
                                        str16 = "";
                                    }
                                    if (columnIndex2 < 0 || (str17 = cursor2.getString(columnIndex2)) == null) {
                                        str17 = "";
                                    }
                                    String str20 = str16;
                                    if (str20.length() == 0) {
                                        str20 = str17;
                                    }
                                    String str21 = str20;
                                    if (columnIndex3 < 0 || (str18 = cursor2.getString(columnIndex3)) == null) {
                                        str18 = "";
                                    }
                                    if (columnIndex4 < 0 || (str19 = cursor2.getString(columnIndex4)) == null) {
                                        str19 = "";
                                    }
                                    long j2 = columnIndex5 >= 0 ? cursor2.getLong(columnIndex5) : 0L;
                                    int i3 = columnIndex5;
                                    int i4 = columnIndex;
                                    String lowerCase3 = StringsKt.trim((CharSequence) str21).toString().toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                                    String lowerCase4 = StringsKt.trim((CharSequence) str18).toString().toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                                    boolean contains = set2.contains(lowerCase3 + "|" + lowerCase4);
                                    if (contains) {
                                        set = set2;
                                    } else {
                                        List<Pair<String, String>> list4 = recommendedTracks;
                                        if ((list4 instanceof Collection) && list4.isEmpty()) {
                                            set = set2;
                                            z2 = false;
                                        } else {
                                            Iterator it6 = list4.iterator();
                                            while (true) {
                                                if (!it6.hasNext()) {
                                                    set = set2;
                                                    z2 = false;
                                                    break;
                                                }
                                                Pair pair2 = (Pair) it6.next();
                                                String obj2 = StringsKt.trim((CharSequence) pair2.getSecond()).toString();
                                                String obj3 = StringsKt.trim((CharSequence) pair2.getFirst()).toString();
                                                Iterator it7 = it6;
                                                boolean z6 = contains;
                                                if (obj2.length() < 3) {
                                                    set = set2;
                                                    z5 = false;
                                                } else {
                                                    set = set2;
                                                    boolean contains2 = StringsKt.contains((CharSequence) str19, (CharSequence) obj2, true);
                                                    if (StringsKt.isBlank(obj3)) {
                                                        z3 = contains2;
                                                    } else {
                                                        z3 = contains2;
                                                        if (!StringsKt.contains((CharSequence) str21, (CharSequence) obj3, true) && !StringsKt.contains((CharSequence) str19, (CharSequence) obj3, true)) {
                                                            z4 = false;
                                                            z5 = !z3 && z4;
                                                        }
                                                    }
                                                    z4 = true;
                                                    if (z3) {
                                                    }
                                                }
                                                if (z5) {
                                                    z2 = true;
                                                    break;
                                                }
                                                it6 = it7;
                                                contains = z6;
                                                set2 = set;
                                            }
                                        }
                                        contains = z2;
                                    }
                                    if (contains) {
                                        Uri build = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(String.valueOf(j)).build();
                                        String str22 = str18;
                                        if (str22.length() == 0) {
                                            str22 = str19;
                                        }
                                        foundTracks.add(new PowerampTrack(j, str22, str21, j2, 0, build, 16, null));
                                        columnIndex = i4;
                                        columnIndex5 = i3;
                                        set2 = set;
                                    } else {
                                        columnIndex = i4;
                                        columnIndex5 = i3;
                                        set2 = set;
                                    }
                                }
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursor4, null);
                                Unit unit2 = Unit.INSTANCE;
                            } catch (Exception e) {
                                e = e;
                                e.printStackTrace();
                                showToast("Failed to query Poweramp. Did you grant data permissions?");
                                return foundTracks;
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
                return foundTracks;
            }
            filesUri = filesUri2;
            projection = projection2;
            str = TableDefs.Files.ARTIST_TAG;
            keywordSearchToken = keywordSearchToken2;
            str2 = TypedValues.TransitionType.S_DURATION;
            str3 = "name";
        }
        try {
            if (keywordSearchToken != null) {
                try {
                    String keywordsStr = StringsKt.substringAfter$default((String) keywordSearchToken.getFirst(), "MAGIC_TOKEN_KEYWORD_SEARCH|", (String) null, 2, (Object) null);
                    Iterable split$default = StringsKt.split$default((CharSequence) keywordsStr, new String[]{","}, false, 0, 6, (Object) null);
                    Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
                    Iterator it8 = split$default.iterator();
                    while (it8.hasNext()) {
                        try {
                            String str23 = str3;
                            arrayList2.add(StringsKt.trim((CharSequence) it8.next()).toString());
                            str3 = str23;
                        } catch (Exception e4) {
                            e = e4;
                            e.printStackTrace();
                            return foundTracks;
                        }
                    }
                    String str24 = str3;
                    Iterable iterable9 = (List) arrayList2;
                    Collection arrayList3 = new ArrayList();
                    for (Object obj4 : iterable9) {
                        if (((String) obj4).length() > 0) {
                            iterable2 = iterable9;
                            arrayList3.add(obj4);
                        } else {
                            iterable2 = iterable9;
                        }
                        iterable9 = iterable2;
                    }
                    Iterable keywords = (List) arrayList3;
                    StringBuilder selectionBuilder = new StringBuilder("(");
                    List selectionArgs = new ArrayList();
                    Iterable iterable10 = keywords;
                    int i5 = 0;
                    int i6 = 0;
                    for (Object obj5 : iterable10) {
                        int i7 = i6 + 1;
                        if (i6 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        String str25 = (String) obj5;
                        if (i6 > 0) {
                            iterable = iterable10;
                            selectionBuilder.append(" OR ");
                        } else {
                            iterable = iterable10;
                        }
                        selectionBuilder.append("(title_tag LIKE ? OR album LIKE ?)");
                        int i8 = i5;
                        selectionArgs.add("%" + str25 + "%");
                        selectionArgs.add("%" + str25 + "%");
                        i6 = i7;
                        iterable10 = iterable;
                        i5 = i8;
                    }
                    selectionBuilder.append(")");
                    try {
                        Cursor cursor5 = this.context.getContentResolver().query(filesUri, projection, selectionBuilder.toString(), (String[]) selectionArgs.toArray(new String[0]), "played_times DESC LIMIT 100");
                        if (cursor5 != null) {
                            cursor = cursor5;
                            try {
                                Cursor cursor6 = cursor;
                                int columnIndexOrThrow2 = cursor6.getColumnIndexOrThrow("_id");
                                int columnIndex6 = cursor6.getColumnIndex(str);
                                int columnIndex7 = cursor6.getColumnIndex("artist");
                                int columnIndex8 = cursor6.getColumnIndex(TableDefs.Files.TITLE_TAG);
                                int columnIndex9 = cursor6.getColumnIndex(str24);
                                int columnIndex10 = cursor6.getColumnIndex(str2);
                                while (cursor6.moveToNext()) {
                                    long j3 = cursor6.getLong(columnIndexOrThrow2);
                                    if (columnIndex6 < 0 || (str4 = cursor6.getString(columnIndex6)) == null) {
                                        str4 = "";
                                    }
                                    if (columnIndex7 < 0 || (str5 = cursor6.getString(columnIndex7)) == null) {
                                        str5 = "";
                                    }
                                    String str26 = str4;
                                    if (str26.length() == 0) {
                                        str26 = str5;
                                    }
                                    String str27 = str26;
                                    if (columnIndex8 < 0 || (str6 = cursor6.getString(columnIndex8)) == null) {
                                        str6 = "";
                                    }
                                    if (columnIndex9 < 0 || (str7 = cursor6.getString(columnIndex9)) == null) {
                                        str7 = "";
                                    }
                                    long j4 = columnIndex10 >= 0 ? cursor6.getLong(columnIndex10) : 0L;
                                    int i9 = columnIndex9;
                                    int i10 = columnIndex6;
                                    Uri build2 = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(String.valueOf(j3)).build();
                                    String str28 = str6;
                                    if (str28.length() == 0) {
                                        str28 = str7;
                                    }
                                    foundTracks.add(new PowerampTrack(j3, str28, str27, j4, 0, build2, 16, null));
                                    columnIndex6 = i10;
                                    columnIndex9 = i9;
                                }
                                Unit unit3 = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursor, null);
                                Unit unit4 = Unit.INSTANCE;
                            } finally {
                            }
                        }
                    } catch (Exception e5) {
                        e = e5;
                        e.printStackTrace();
                        return foundTracks;
                    }
                } catch (Exception e6) {
                    e = e6;
                }
            } else {
                String str29 = str3;
                String[] projection3 = projection;
                Uri filesUri3 = filesUri;
                if (isNewTracksRequest2) {
                    try {
                        try {
                            Cursor recentCursor = this.context.getContentResolver().query(filesUri3, projection3, null, null, "file_created_at DESC LIMIT 100");
                            try {
                                List tempNewTracks = new ArrayList();
                                if (recentCursor != null) {
                                    try {
                                        cursor = recentCursor;
                                        try {
                                            Cursor cursor7 = cursor;
                                            int columnIndexOrThrow3 = cursor7.getColumnIndexOrThrow("_id");
                                            int columnIndex11 = cursor7.getColumnIndex(str);
                                            int columnIndex12 = cursor7.getColumnIndex("artist");
                                            int columnIndex13 = cursor7.getColumnIndex(TableDefs.Files.TITLE_TAG);
                                            int columnIndex14 = cursor7.getColumnIndex(str29);
                                            int columnIndex15 = cursor7.getColumnIndex(str2);
                                            int columnIndex16 = cursor7.getColumnIndex("played_times");
                                            while (cursor7.moveToNext()) {
                                                long j5 = cursor7.getLong(columnIndexOrThrow3);
                                                if (columnIndex11 < 0 || (str8 = cursor7.getString(columnIndex11)) == null) {
                                                    str8 = "";
                                                }
                                                if (columnIndex12 < 0 || (str9 = cursor7.getString(columnIndex12)) == null) {
                                                    str9 = "";
                                                }
                                                String str30 = str8;
                                                if (str30.length() == 0) {
                                                    str30 = str9;
                                                }
                                                String str31 = str30;
                                                if (columnIndex13 < 0 || (str10 = cursor7.getString(columnIndex13)) == null) {
                                                    str10 = "";
                                                }
                                                if (columnIndex14 < 0 || (str11 = cursor7.getString(columnIndex14)) == null) {
                                                    str11 = "";
                                                }
                                                long j6 = columnIndex15 >= 0 ? cursor7.getLong(columnIndex15) : 0L;
                                                int i11 = columnIndex16 >= 0 ? cursor7.getInt(columnIndex16) : 0;
                                                int i12 = columnIndex14;
                                                Uri build3 = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(String.valueOf(j5)).build();
                                                String str32 = str10;
                                                if (str32.length() == 0) {
                                                    str32 = str11;
                                                }
                                                tempNewTracks.add(new PowerampTrack(j5, str32, str31, j6, i11, build3));
                                                columnIndex14 = i12;
                                            }
                                            Unit unit5 = Unit.INSTANCE;
                                            CloseableKt.closeFinally(cursor, null);
                                            Unit unit6 = Unit.INSTANCE;
                                        } finally {
                                            try {
                                                throw th;
                                            } finally {
                                            }
                                        }
                                    } catch (Exception e7) {
                                        e = e7;
                                        e.printStackTrace();
                                        return foundTracks;
                                    }
                                }
                                randomLimit = new Random().nextInt(51) + 50;
                                bestOfNew = CollectionsKt.take(CollectionsKt.sortedWith(tempNewTracks, new Comparator() { // from class: com.maxmpz.poweramp.companion.PowerampController$findTracks$$inlined$sortedByDescending$1
                                    /* JADX WARN: Multi-variable type inference failed */
                                    @Override // java.util.Comparator
                                    public final int compare(T t, T t2) {
                                        return ComparisonsKt.compareValues(Integer.valueOf(((PowerampTrack) t2).getPlayedTimes()), Integer.valueOf(((PowerampTrack) t).getPlayedTimes()));
                                    }
                                }), randomLimit);
                                foundTracks.addAll(bestOfNew);
                                powerampController = this;
                            } catch (Exception e8) {
                                e = e8;
                                powerampController = this;
                            }
                        } catch (Exception e9) {
                            e = e9;
                        }
                    } catch (Exception e10) {
                        e = e10;
                        e.printStackTrace();
                        return foundTracks;
                    }
                    try {
                        Cursor topCursor = powerampController.context.getContentResolver().query(filesUri3, new String[]{TableDefs.Files._ID}, "played_times > 0", null, "played_times DESC LIMIT 500");
                        List tempIds = new ArrayList();
                        if (topCursor != null) {
                            cursor = topCursor;
                            try {
                                Cursor cursor8 = cursor;
                                int columnIndexOrThrow4 = cursor8.getColumnIndexOrThrow("_id");
                                while (cursor8.moveToNext()) {
                                    tempIds.add(Long.valueOf(cursor8.getLong(columnIndexOrThrow4)));
                                }
                                Unit unit7 = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursor, null);
                                Unit unit8 = Unit.INSTANCE;
                            } finally {
                                try {
                                    throw th;
                                } finally {
                                }
                            }
                        }
                        Collections.shuffle(tempIds);
                        int neededCount = Math.max(0, 100 - foundTracks.size());
                        Collection arrayList4 = new ArrayList();
                        for (Object obj6 : tempIds) {
                            long longValue = ((Number) obj6).longValue();
                            List list5 = foundTracks;
                            int randomLimit2 = randomLimit;
                            List bestOfNew2 = bestOfNew;
                            if (!(list5 instanceof Collection) || !list5.isEmpty()) {
                                Iterator it9 = list5.iterator();
                                while (true) {
                                    if (!it9.hasNext()) {
                                        z = true;
                                        break;
                                    }
                                    if (((PowerampTrack) it9.next()).getId() == longValue) {
                                        z = false;
                                        break;
                                    }
                                }
                            } else {
                                z = true;
                            }
                            if (z) {
                                arrayList4.add(obj6);
                            }
                            randomLimit = randomLimit2;
                            bestOfNew = bestOfNew2;
                        }
                        List selectedIds = CollectionsKt.take((List) arrayList4, neededCount);
                        Boolean.valueOf(foundTracks.addAll(powerampController.getTracksByIds(selectedIds)));
                    } catch (Exception e11) {
                        e = e11;
                        e.printStackTrace();
                        return foundTracks;
                    }
                } else if (isUnderratedRequest) {
                    projection3 = projection3;
                    try {
                        Cursor cursor9 = this.context.getContentResolver().query(filesUri3, projection3, "played_times > 0 AND played_times <= 5", null, null);
                        List tempUnderrated = new ArrayList();
                        if (cursor9 != null) {
                            cursor = cursor9;
                            try {
                                Cursor cursor10 = cursor;
                                int columnIndexOrThrow5 = cursor10.getColumnIndexOrThrow("_id");
                                int columnIndex17 = cursor10.getColumnIndex(str);
                                int columnIndex18 = cursor10.getColumnIndex("artist");
                                int columnIndex19 = cursor10.getColumnIndex(TableDefs.Files.TITLE_TAG);
                                int columnIndex20 = cursor10.getColumnIndex(str29);
                                int columnIndex21 = cursor10.getColumnIndex(str2);
                                int columnIndex22 = cursor10.getColumnIndex("played_times");
                                while (cursor10.moveToNext()) {
                                    long j7 = cursor10.getLong(columnIndexOrThrow5);
                                    if (columnIndex17 < 0 || (str12 = cursor10.getString(columnIndex17)) == null) {
                                        str12 = "";
                                    }
                                    if (columnIndex18 < 0 || (str13 = cursor10.getString(columnIndex18)) == null) {
                                        str13 = "";
                                    }
                                    String str33 = str12;
                                    if (str33.length() == 0) {
                                        str33 = str13;
                                    }
                                    String str34 = str33;
                                    if (columnIndex19 < 0 || (str14 = cursor10.getString(columnIndex19)) == null) {
                                        str14 = "";
                                    }
                                    if (columnIndex20 < 0 || (str15 = cursor10.getString(columnIndex20)) == null) {
                                        str15 = "";
                                    }
                                    long j8 = columnIndex21 >= 0 ? cursor10.getLong(columnIndex21) : 0L;
                                    int i13 = columnIndex22 >= 0 ? cursor10.getInt(columnIndex22) : 0;
                                    int i14 = columnIndex20;
                                    int i15 = columnIndex17;
                                    Uri build4 = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(String.valueOf(j7)).build();
                                    String str35 = str14;
                                    if (str35.length() == 0) {
                                        str35 = str15;
                                    }
                                    tempUnderrated.add(new PowerampTrack(j7, str35, str34, j8, i13, build4));
                                    columnIndex17 = i15;
                                    columnIndex20 = i14;
                                }
                                Unit unit9 = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursor, null);
                                Unit unit10 = Unit.INSTANCE;
                            } finally {
                                try {
                                    throw th;
                                } finally {
                                }
                            }
                        }
                        Boolean.valueOf(foundTracks.addAll(CollectionsKt.take(CollectionsKt.shuffled(tempUnderrated), new Random().nextInt(51) + 50)));
                    } catch (Exception e12) {
                        e = e12;
                        e.printStackTrace();
                        return foundTracks;
                    }
                } else {
                    int randomLimit3 = new Random().nextInt(81) + 20;
                    Cursor cursor11 = this.context.getContentResolver().query(filesUri3, new String[]{TableDefs.Files._ID}, "played_times > 0", null, "played_times DESC LIMIT 500");
                    List tempFallbackIds = new ArrayList();
                    if (cursor11 != null) {
                        cursor = cursor11;
                        try {
                            Cursor cursor12 = cursor;
                            int columnIndexOrThrow6 = cursor12.getColumnIndexOrThrow("_id");
                            while (cursor12.moveToNext()) {
                                tempFallbackIds.add(Long.valueOf(cursor12.getLong(columnIndexOrThrow6)));
                            }
                            Unit unit11 = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursor, null);
                            Unit unit12 = Unit.INSTANCE;
                        } finally {
                            try {
                                throw th;
                            } finally {
                            }
                        }
                    }
                    Collections.shuffle(tempFallbackIds);
                    List selectedIds2 = CollectionsKt.take(tempFallbackIds, randomLimit3);
                    Boolean.valueOf(foundTracks.addAll(getTracksByIds(selectedIds2)));
                }
            }
            Collections.shuffle(foundTracks);
        } catch (Exception e13) {
            e = e13;
            e.printStackTrace();
            return foundTracks;
        }
        return foundTracks;
    }

    public final Bitmap getAlbumArt(long folderFileId) {
        if (folderFileId == 0) {
            return null;
        }
        Uri albumArtUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(String.valueOf(folderFileId)).build();
        try {
            InputStream inputStream = this.context.getContentResolver().openInputStream(albumArtUri);
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x013b A[Catch: all -> 0x00b1, TRY_ENTER, TryCatch #1 {all -> 0x00b1, blocks: (B:95:0x00aa, B:31:0x00bd, B:41:0x00e0, B:44:0x00ea, B:54:0x010d, B:57:0x0117, B:68:0x013b, B:71:0x0146), top: B:94:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0146 A[Catch: all -> 0x00b1, TRY_LEAVE, TryCatch #1 {all -> 0x00b1, blocks: (B:95:0x00aa, B:31:0x00bd, B:41:0x00e0, B:44:0x00ea, B:54:0x010d, B:57:0x0117, B:68:0x013b, B:71:0x0146), top: B:94:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getFullLibraryJsonForGemini() {
        /*
            Method dump skipped, instructions count: 533
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.PowerampController.getFullLibraryJsonForGemini():java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x00fa, code lost:
    
        r27 = r8.getLong(r3);
     */
    /* JADX WARN: Removed duplicated region for block: B:9:0x018f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.maxmpz.poweramp.companion.PowerampTrack> getTracksByIds(java.util.List<java.lang.Long> r38) {
        /*
            Method dump skipped, instructions count: 481
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.PowerampController.getTracksByIds(java.util.List):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence getTracksByIds$lambda$32(long it) {
        return String.valueOf(it);
    }

    public final QueueStats getQueueStats() {
        int total;
        int played;
        long totalDuration;
        long remainingDuration;
        Cursor cursor;
        Uri queueUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Queue.TABLE).build();
        int total2 = 0;
        int played2 = 0;
        long totalDuration2 = 0;
        long remainingDuration2 = 0;
        try {
            Cursor query = this.context.getContentResolver().query(queueUri, new String[]{TableDefs.Queue._ID, TableDefs.Files.DURATION, TableDefs.Queue.CREATED_AT, TableDefs.Files.PLAYED_AT}, null, null, null);
            if (query != null) {
                cursor = query;
                try {
                    Cursor cursor2 = cursor;
                    total2 = cursor2.getCount();
                    int columnIndex = cursor2.getColumnIndex(TableDefs.Files.DURATION);
                    int columnIndex2 = cursor2.getColumnIndex(TableDefs.Queue.CREATED_AT);
                    int columnIndex3 = cursor2.getColumnIndex(TableDefs.Files.PLAYED_AT);
                    while (cursor2.moveToNext()) {
                        long j = columnIndex >= 0 ? cursor2.getLong(columnIndex) : 0L;
                        totalDuration2 += j;
                        if ((columnIndex3 >= 0 ? cursor2.getLong(columnIndex3) : 0L) < (columnIndex2 >= 0 ? cursor2.getLong(columnIndex2) : 0L)) {
                            remainingDuration2 += j;
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } finally {
                }
            }
            Cursor query2 = this.context.getContentResolver().query(queueUri, new String[]{TableDefs.Queue._ID}, TableDefs.Queue.CALC_PLAYED, null, null);
            if (query2 == null) {
                total = total2;
                played = 0;
                totalDuration = totalDuration2;
                remainingDuration = remainingDuration2;
            } else {
                cursor = query2;
                try {
                    played2 = cursor.getCount();
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                    total = total2;
                    played = played2;
                    totalDuration = totalDuration2;
                    remainingDuration = remainingDuration2;
                } finally {
                    try {
                        throw th;
                    } finally {
                    }
                }
            }
        } catch (Exception e) {
            Log.e("PowerampController", "Error getting queue stats", e);
            total = total2;
            played = played2;
            totalDuration = totalDuration2;
            remainingDuration = remainingDuration2;
        }
        return new QueueStats(played, total, totalDuration, remainingDuration);
    }

    public final void sendToPoweramp(List<Long> fileIds) {
        Uri entryUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Playlists.TABLE).build();
        String playlistName = "AI Companion (" + (System.currentTimeMillis() % TableDefs.Files.INVALID_TRACK_NUMBER) + ")";
        ContentValues values = new ContentValues();
        values.put("playlist", playlistName);
        try {
            Uri playlistInsertedUri = this.context.getContentResolver().insert(entryUri, values);
            try {
                if (playlistInsertedUri == null) {
                    showToast("Failed to create Poweramp playlist.");
                    return;
                }
                Uri playlistEntriesUri = playlistInsertedUri.buildUpon().appendEncodedPath("files").build();
                int sortOrder = 0;
                Uri uri = null;
                Iterator<Long> it = fileIds.iterator();
                while (it.hasNext()) {
                    long id = it.next().longValue();
                    ContentValues entryValues = new ContentValues();
                    Uri playlistsUri = entryUri;
                    entryValues.put("folder_file_id", Long.valueOf(id));
                    int sortOrder2 = sortOrder + 1;
                    entryValues.put(TableDefs.PlaylistEntries.SORT, Integer.valueOf(sortOrder));
                    Uri entryUri2 = this.context.getContentResolver().insert(playlistEntriesUri, entryValues);
                    if (uri != null || entryUri2 == null) {
                        sortOrder = sortOrder2;
                        entryUri = playlistsUri;
                    } else {
                        uri = entryUri2;
                        sortOrder = sortOrder2;
                        entryUri = playlistsUri;
                    }
                }
                Log.d("PowerampController", "Created playlist " + playlistName + " with " + fileIds.size() + " tracks");
                Intent reloadIntent = new Intent("com.maxmpz.audioplayer.ACTION_RELOAD_DATA");
                reloadIntent.setPackage(PowerampAPI.PACKAGE_NAME);
                reloadIntent.putExtra("table", TableDefs.PlaylistEntries.TABLE);
                this.context.sendBroadcast(reloadIntent);
                if (uri == null) {
                    showToast("Playlist created but could not start playback.");
                    return;
                }
                Intent playIntent = new Intent(PowerampAPI.ACTION_API_COMMAND);
                playIntent.setComponent(new ComponentName(PowerampAPI.PACKAGE_NAME, PowerampAPI.API_RECEIVER_NAME));
                playIntent.putExtra("cmd", 20);
                playIntent.setData(uri);
                Log.d("StatsDEBUG", "PowerampController: Sending playlist play broadcast: " + playIntent);
                this.context.sendBroadcast(playIntent);
                showToast("Playing AI Playlist in Poweramp!");
            } catch (SecurityException e) {
                e = e;
                e.printStackTrace();
                showToast("Security Exception: Poweramp Data Write Permission likely missing. Asking now...");
                askForPowerampPermission();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                showToast("Error communicating with Poweramp: " + e.getMessage());
            }
        } catch (SecurityException e3) {
            e = e3;
        } catch (Exception e4) {
            e = e4;
        }
    }

    private final void askForPowerampPermission() {
        Intent intent = new Intent("com.maxmpz.audioplayer.ACTION_ASK_FOR_DATA_PERMISSION");
        intent.setPackage(PowerampAPI.PACKAGE_NAME);
        intent.setFlags(268435456);
        try {
            this.context.startActivity(intent);
        } catch (Exception e) {
            showToast("Could not request Poweramp permissions. Is Poweramp installed?");
        }
    }

    private final void performShuffleShift(Uri queueUri, long startShuffle, long shiftCount) {
        int i = 0;
        int i2 = 1;
        Cursor shuffleShiftCursor = this.context.getContentResolver().query(queueUri, new String[]{TableDefs.Queue._ID, TableDefs.Queue.SHUFFLE_ORDER}, "queue.shuffle_order >= " + startShuffle, null, "queue.shuffle_order DESC");
        if (shuffleShiftCursor != null) {
            Cursor cursor = shuffleShiftCursor;
            try {
                Cursor cursor2 = cursor;
                while (cursor2.moveToNext()) {
                    long j = cursor2.getLong(i);
                    long j2 = cursor2.getLong(i2) + shiftCount;
                    ContentValues contentValues = new ContentValues();
                    int i3 = i;
                    contentValues.put("shuffle_order", Long.valueOf(j2));
                    ContentResolver contentResolver = this.context.getContentResolver();
                    String[] strArr = new String[1];
                    strArr[i3] = String.valueOf(j);
                    try {
                        contentResolver.update(queueUri, contentValues, "_id = ?", strArr);
                        i = i3;
                        i2 = 1;
                    } catch (Throwable th) {
                        th = th;
                        Throwable th2 = th;
                        try {
                            throw th2;
                        } catch (Throwable th3) {
                            CloseableKt.closeFinally(cursor, th2);
                            throw th3;
                        }
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, null);
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    private final void showToast(final String message) {
        this.mainHandler.post(new Runnable() { // from class: com.maxmpz.poweramp.companion.PowerampController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PowerampController.showToast$lambda$47(PowerampController.this, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showToast$lambda$47(PowerampController this$0, String $message) {
        Toast.makeText(this$0.context, $message, 1).show();
    }

    /* JADX WARN: Finally extract failed */
    public final void sendToPowerampQueue(List<Long> fileIds) {
        int i;
        Throwable th;
        boolean interweaveShuffle;
        Cursor cursor;
        List allItems;
        Iterator it;
        List playedQueueIds;
        boolean interweaveShuffle2;
        int i2;
        int i3;
        Uri queueUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Queue.TABLE).build();
        try {
            SharedPreferences prefs = this.context.getSharedPreferences("PowerampCompanionPrefs", 0);
            boolean interweaveShuffle3 = prefs.getBoolean("shuffle_queue_enabled", false);
            List unplayedQueueIds = new ArrayList();
            List playedQueueIds2 = new ArrayList();
            int i4 = 1;
            Cursor query = this.context.getContentResolver().query(queueUri, new String[]{TableDefs.Queue._ID, TableDefs.PlaylistEntries.SORT, TableDefs.Queue.CREATED_AT, TableDefs.Files.PLAYED_AT}, null, null, "sort ASC");
            if (query != null) {
                cursor = query;
                try {
                    Cursor cursor2 = cursor;
                    int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("_id");
                    int columnIndexOrThrow2 = cursor2.getColumnIndexOrThrow(TableDefs.PlaylistEntries.SORT);
                    int columnIndex = cursor2.getColumnIndex(TableDefs.Queue.CREATED_AT);
                    int columnIndex2 = cursor2.getColumnIndex(TableDefs.Files.PLAYED_AT);
                    while (cursor2.moveToNext()) {
                        long j = cursor2.getLong(columnIndexOrThrow);
                        int i5 = i4;
                        int i6 = columnIndexOrThrow2;
                        int i7 = cursor2.getInt(i6);
                        long j2 = columnIndex >= 0 ? cursor2.getLong(columnIndex) : 0L;
                        long j3 = columnIndex2 >= 0 ? cursor2.getLong(columnIndex2) : 0L;
                        if (j3 >= j2 && j3 > 0) {
                            playedQueueIds2.add(Long.valueOf(j));
                            i2 = columnIndex2;
                            i3 = columnIndex;
                        } else {
                            i2 = columnIndex2;
                            i3 = columnIndex;
                            unplayedQueueIds.add(new Pair(Long.valueOf(j), Integer.valueOf(i7)));
                        }
                        columnIndexOrThrow2 = i6;
                        i4 = i5;
                        columnIndex2 = i2;
                        columnIndex = i3;
                    }
                    i = i4;
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } finally {
                    try {
                        throw th;
                    } finally {
                    }
                }
            } else {
                i = 1;
            }
            boolean hasUnplayedTracks = !unplayedQueueIds.isEmpty();
            if (!hasUnplayedTracks) {
                int deletedCount = this.context.getContentResolver().delete(queueUri, null, null);
                if (deletedCount > 0) {
                    Log.d("PowerampController", "Queue was entirely played. Cleared " + deletedCount + " tracks before adding new ones.");
                    th = null;
                } else {
                    th = null;
                }
            } else if (playedQueueIds2.isEmpty()) {
                th = null;
            } else {
                String idListStr = CollectionsKt.joinToString$default(playedQueueIds2, ",", null, null, 0, null, null, 62, null);
                th = null;
                Log.d("PowerampController", "Cleared " + this.context.getContentResolver().delete(queueUri, "queue._id IN (" + idListStr + ")", null) + " individually played tracks from the queue.");
            }
            showToast("Shuffle: " + (interweaveShuffle3 ? "AN" : "AUS"));
            if (interweaveShuffle3) {
                List shuffledFileIds = CollectionsKt.shuffled(fileIds);
                if (!hasUnplayedTracks) {
                    int sortOrder = 0;
                    long now = System.currentTimeMillis();
                    Iterator it2 = shuffledFileIds.iterator();
                    while (it2.hasNext()) {
                        long id = ((Number) it2.next()).longValue();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("folder_file_id", Long.valueOf(id));
                        contentValues.put(TableDefs.PlaylistEntries.SORT, Integer.valueOf(sortOrder));
                        contentValues.put("shuffle_order", Long.valueOf(this.random.nextLong()));
                        contentValues.put("created_at", Long.valueOf(now));
                        this.context.getContentResolver().insert(queueUri, contentValues);
                        sortOrder += 10;
                        hasUnplayedTracks = hasUnplayedTracks;
                        shuffledFileIds = shuffledFileIds;
                    }
                    Log.d("PowerampController", "Inserted " + shuffledFileIds.size() + " shuffled tracks into empty queue with random shuffle_order");
                    interweaveShuffle = interweaveShuffle3;
                } else {
                    Pair pair = (Pair) CollectionsKt.firstOrNull(unplayedQueueIds);
                    int firstSort = pair != null ? ((Number) pair.getSecond()).intValue() : 0;
                    List allItems2 = new ArrayList();
                    List list = unplayedQueueIds;
                    Iterator it3 = list.iterator();
                    while (it3.hasNext()) {
                        allItems2.add(new Either.Left(((Pair) it3.next()).getFirst()));
                        firstSort = firstSort;
                        list = list;
                    }
                    int firstSort2 = firstSort;
                    List list2 = shuffledFileIds;
                    int i8 = 0;
                    Iterator it4 = list2.iterator();
                    while (it4.hasNext()) {
                        allItems2.add(new Either.Right(Long.valueOf(((Number) it4.next()).longValue())));
                        list2 = list2;
                        i8 = i8;
                    }
                    Collections.shuffle(allItems2);
                    int currentSort = firstSort2;
                    Iterator it5 = allItems2.iterator();
                    while (it5.hasNext()) {
                        Either item = (Either) it5.next();
                        int currentSort2 = currentSort;
                        if (item instanceof Either.Left) {
                            ContentValues contentValues2 = new ContentValues();
                            allItems = allItems2;
                            contentValues2.put(TableDefs.PlaylistEntries.SORT, Integer.valueOf(currentSort2));
                            contentValues2.put("shuffle_order", Long.valueOf(this.random.nextLong()));
                            ContentResolver contentResolver = this.context.getContentResolver();
                            it = it5;
                            playedQueueIds = playedQueueIds2;
                            interweaveShuffle2 = interweaveShuffle3;
                            String[] strArr = new String[i];
                            strArr[0] = String.valueOf(((Number) ((Either.Left) item).getValue()).longValue());
                            Integer.valueOf(contentResolver.update(queueUri, contentValues2, "queue._id = ?", strArr));
                        } else {
                            allItems = allItems2;
                            it = it5;
                            playedQueueIds = playedQueueIds2;
                            interweaveShuffle2 = interweaveShuffle3;
                            if (!(item instanceof Either.Right)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            ContentValues values = new ContentValues();
                            values.put("folder_file_id", (Long) ((Either.Right) item).getValue());
                            values.put(TableDefs.PlaylistEntries.SORT, Integer.valueOf(currentSort2));
                            values.put("shuffle_order", Long.valueOf(this.random.nextLong()));
                            values.put("created_at", Long.valueOf(System.currentTimeMillis()));
                            this.context.getContentResolver().insert(queueUri, values);
                        }
                        currentSort = currentSort2 + 10;
                        it5 = it;
                        playedQueueIds2 = playedQueueIds;
                        interweaveShuffle3 = interweaveShuffle2;
                        allItems2 = allItems;
                        i = 1;
                    }
                    interweaveShuffle = interweaveShuffle3;
                    int currentSort3 = shuffledFileIds.size();
                    Log.d("PowerampController", "Interweaved " + currentSort3 + " tracks into unplayed queue with random shuffle_order");
                }
            } else {
                interweaveShuffle = interweaveShuffle3;
                Throwable th2 = th;
                Cursor cursor3 = this.context.getContentResolver().query(queueUri, new String[]{"MAX(sort)"}, null, null, null);
                if (cursor3 != null) {
                    cursor = cursor3;
                    try {
                        Cursor cursor4 = cursor;
                        sortOrder = cursor4.moveToFirst() ? cursor4.getInt(0) + 10 : 0;
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursor, th2);
                    } catch (Throwable th3) {
                    }
                }
                long now2 = System.currentTimeMillis();
                Iterator<Long> it6 = fileIds.iterator();
                while (it6.hasNext()) {
                    long id2 = it6.next().longValue();
                    ContentValues values2 = new ContentValues();
                    int sortOrder2 = sortOrder;
                    values2.put("folder_file_id", Long.valueOf(id2));
                    values2.put(TableDefs.PlaylistEntries.SORT, Integer.valueOf(sortOrder2));
                    values2.put("created_at", Long.valueOf(now2));
                    this.context.getContentResolver().insert(queueUri, values2);
                    sortOrder = sortOrder2 + 10;
                }
                Log.d("PowerampController", "Appended " + fileIds.size() + " tracks to queue sequentially");
            }
            Intent reloadIntent = new Intent("com.maxmpz.audioplayer.ACTION_RELOAD_DATA");
            reloadIntent.setPackage(PowerampAPI.PACKAGE_NAME);
            reloadIntent.putExtra("table", TableDefs.Queue.TABLE);
            this.context.sendBroadcast(reloadIntent);
            if (interweaveShuffle) {
                Intent shuffleIntent = new Intent(PowerampAPI.ACTION_API_COMMAND);
                shuffleIntent.setComponent(new ComponentName(PowerampAPI.PACKAGE_NAME, PowerampAPI.API_RECEIVER_NAME));
                shuffleIntent.putExtra("cmd", 9);
                shuffleIntent.putExtra("shuffle", 2);
                this.context.sendBroadcast(shuffleIntent);
            }
            showToast("Added " + fileIds.size() + " tracks to Queue!");
        } catch (SecurityException e) {
            e.printStackTrace();
            showToast("Security Exception: Poweramp Data Write Permission missing. Asking now...");
            askForPowerampPermission();
        } catch (Exception e2) {
            e2.printStackTrace();
            showToast("Error adding to queue: " + e2.getMessage());
        }
    }

    /* compiled from: PowerampController.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003:\u0002\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/maxmpz/poweramp/companion/PowerampController$Either;", "L", "R", "", "<init>", "()V", "Left", "Right", "Lcom/maxmpz/poweramp/companion/PowerampController$Either$Left;", "Lcom/maxmpz/poweramp/companion/PowerampController$Either$Right;", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static abstract class Either<L, R> {
        public /* synthetic */ Either(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: PowerampController.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0002\u0010\u0001 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\n\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\bJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0002HÆ\u0001¢\u0006\u0002\u0010\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/maxmpz/poweramp/companion/PowerampController$Either$Left;", "L", "Lcom/maxmpz/poweramp/companion/PowerampController$Either;", "", "value", "<init>", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/maxmpz/poweramp/companion/PowerampController$Either$Left;", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "", "hashCode", "", "toString", "", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* loaded from: classes7.dex */
        public static final /* data */ class Left<L> extends Either {
            private final L value;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Left copy$default(Left left, Object obj, int i, Object obj2) {
                if ((i & 1) != 0) {
                    obj = left.value;
                }
                return left.copy(obj);
            }

            public final L component1() {
                return this.value;
            }

            public final Left<L> copy(L value) {
                return new Left<>(value);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Left) && Intrinsics.areEqual(this.value, ((Left) other).value);
            }

            public int hashCode() {
                if (this.value == null) {
                    return 0;
                }
                return this.value.hashCode();
            }

            public String toString() {
                return "Left(value=" + this.value + ")";
            }

            public Left(L l) {
                super(null);
                this.value = l;
            }

            public final L getValue() {
                return this.value;
            }
        }

        private Either() {
        }

        /* compiled from: PowerampController.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0002\u0010\u0001 \u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\n\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\bJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0002HÆ\u0001¢\u0006\u0002\u0010\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/maxmpz/poweramp/companion/PowerampController$Either$Right;", "R", "Lcom/maxmpz/poweramp/companion/PowerampController$Either;", "", "value", "<init>", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/maxmpz/poweramp/companion/PowerampController$Either$Right;", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "", "hashCode", "", "toString", "", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* loaded from: classes7.dex */
        public static final /* data */ class Right<R> extends Either {
            private final R value;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Right copy$default(Right right, Object obj, int i, Object obj2) {
                if ((i & 1) != 0) {
                    obj = right.value;
                }
                return right.copy(obj);
            }

            public final R component1() {
                return this.value;
            }

            public final Right<R> copy(R value) {
                return new Right<>(value);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Right) && Intrinsics.areEqual(this.value, ((Right) other).value);
            }

            public int hashCode() {
                if (this.value == null) {
                    return 0;
                }
                return this.value.hashCode();
            }

            public String toString() {
                return "Right(value=" + this.value + ")";
            }

            public Right(R r) {
                super(null);
                this.value = r;
            }

            public final R getValue() {
                return this.value;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0495 A[Catch: Exception -> 0x0674, SecurityException -> 0x0693, TRY_ENTER, TRY_LEAVE, TryCatch #31 {SecurityException -> 0x0693, Exception -> 0x0674, blocks: (B:7:0x002e, B:252:0x00b0, B:254:0x00b6, B:272:0x011d, B:14:0x016d, B:17:0x0175, B:19:0x019f, B:26:0x01bc, B:28:0x022a, B:30:0x024b, B:37:0x0267, B:45:0x0271, B:46:0x0274, B:49:0x027c, B:52:0x02f9, B:54:0x031b, B:66:0x034c, B:71:0x037e, B:73:0x03a7, B:80:0x03be, B:88:0x03c8, B:89:0x03cb, B:105:0x048d, B:108:0x0495, B:126:0x0509, B:127:0x050c, B:134:0x04f8, B:135:0x0511, B:136:0x052a, B:138:0x0530, B:145:0x0582, B:147:0x0588, B:149:0x058e, B:153:0x05ac, B:155:0x05f8, B:156:0x0609, B:202:0x0360, B:203:0x0363, B:215:0x02a2, B:217:0x02c4, B:218:0x02e7, B:226:0x01c7, B:227:0x01ca, B:229:0x01cb, B:231:0x01e9, B:238:0x021a, B:247:0x0225, B:248:0x0228, B:281:0x012c, B:282:0x012f, B:233:0x01eb, B:235:0x01f5, B:236:0x0216, B:244:0x0223, B:278:0x012a, B:32:0x024d, B:34:0x0257, B:35:0x0263, B:42:0x026f, B:21:0x01a1, B:23:0x01ab, B:24:0x01b8, B:75:0x03a9, B:77:0x03b3, B:78:0x03ba, B:223:0x01c5, B:85:0x03c6, B:123:0x0507, B:199:0x035e), top: B:6:0x002e, inners: #0, #3, #7, #10, #12, #20, #21, #22, #23, #24, #33 }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0530 A[Catch: Exception -> 0x0674, SecurityException -> 0x0693, TryCatch #31 {SecurityException -> 0x0693, Exception -> 0x0674, blocks: (B:7:0x002e, B:252:0x00b0, B:254:0x00b6, B:272:0x011d, B:14:0x016d, B:17:0x0175, B:19:0x019f, B:26:0x01bc, B:28:0x022a, B:30:0x024b, B:37:0x0267, B:45:0x0271, B:46:0x0274, B:49:0x027c, B:52:0x02f9, B:54:0x031b, B:66:0x034c, B:71:0x037e, B:73:0x03a7, B:80:0x03be, B:88:0x03c8, B:89:0x03cb, B:105:0x048d, B:108:0x0495, B:126:0x0509, B:127:0x050c, B:134:0x04f8, B:135:0x0511, B:136:0x052a, B:138:0x0530, B:145:0x0582, B:147:0x0588, B:149:0x058e, B:153:0x05ac, B:155:0x05f8, B:156:0x0609, B:202:0x0360, B:203:0x0363, B:215:0x02a2, B:217:0x02c4, B:218:0x02e7, B:226:0x01c7, B:227:0x01ca, B:229:0x01cb, B:231:0x01e9, B:238:0x021a, B:247:0x0225, B:248:0x0228, B:281:0x012c, B:282:0x012f, B:233:0x01eb, B:235:0x01f5, B:236:0x0216, B:244:0x0223, B:278:0x012a, B:32:0x024d, B:34:0x0257, B:35:0x0263, B:42:0x026f, B:21:0x01a1, B:23:0x01ab, B:24:0x01b8, B:75:0x03a9, B:77:0x03b3, B:78:0x03ba, B:223:0x01c5, B:85:0x03c6, B:123:0x0507, B:199:0x035e), top: B:6:0x002e, inners: #0, #3, #7, #10, #12, #20, #21, #22, #23, #24, #33 }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x05f8 A[Catch: Exception -> 0x0674, SecurityException -> 0x0693, TryCatch #31 {SecurityException -> 0x0693, Exception -> 0x0674, blocks: (B:7:0x002e, B:252:0x00b0, B:254:0x00b6, B:272:0x011d, B:14:0x016d, B:17:0x0175, B:19:0x019f, B:26:0x01bc, B:28:0x022a, B:30:0x024b, B:37:0x0267, B:45:0x0271, B:46:0x0274, B:49:0x027c, B:52:0x02f9, B:54:0x031b, B:66:0x034c, B:71:0x037e, B:73:0x03a7, B:80:0x03be, B:88:0x03c8, B:89:0x03cb, B:105:0x048d, B:108:0x0495, B:126:0x0509, B:127:0x050c, B:134:0x04f8, B:135:0x0511, B:136:0x052a, B:138:0x0530, B:145:0x0582, B:147:0x0588, B:149:0x058e, B:153:0x05ac, B:155:0x05f8, B:156:0x0609, B:202:0x0360, B:203:0x0363, B:215:0x02a2, B:217:0x02c4, B:218:0x02e7, B:226:0x01c7, B:227:0x01ca, B:229:0x01cb, B:231:0x01e9, B:238:0x021a, B:247:0x0225, B:248:0x0228, B:281:0x012c, B:282:0x012f, B:233:0x01eb, B:235:0x01f5, B:236:0x0216, B:244:0x0223, B:278:0x012a, B:32:0x024d, B:34:0x0257, B:35:0x0263, B:42:0x026f, B:21:0x01a1, B:23:0x01ab, B:24:0x01b8, B:75:0x03a9, B:77:0x03b3, B:78:0x03ba, B:223:0x01c5, B:85:0x03c6, B:123:0x0507, B:199:0x035e), top: B:6:0x002e, inners: #0, #3, #7, #10, #12, #20, #21, #22, #23, #24, #33 }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x050d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0175 A[Catch: Exception -> 0x0674, SecurityException -> 0x0693, TRY_ENTER, TryCatch #31 {SecurityException -> 0x0693, Exception -> 0x0674, blocks: (B:7:0x002e, B:252:0x00b0, B:254:0x00b6, B:272:0x011d, B:14:0x016d, B:17:0x0175, B:19:0x019f, B:26:0x01bc, B:28:0x022a, B:30:0x024b, B:37:0x0267, B:45:0x0271, B:46:0x0274, B:49:0x027c, B:52:0x02f9, B:54:0x031b, B:66:0x034c, B:71:0x037e, B:73:0x03a7, B:80:0x03be, B:88:0x03c8, B:89:0x03cb, B:105:0x048d, B:108:0x0495, B:126:0x0509, B:127:0x050c, B:134:0x04f8, B:135:0x0511, B:136:0x052a, B:138:0x0530, B:145:0x0582, B:147:0x0588, B:149:0x058e, B:153:0x05ac, B:155:0x05f8, B:156:0x0609, B:202:0x0360, B:203:0x0363, B:215:0x02a2, B:217:0x02c4, B:218:0x02e7, B:226:0x01c7, B:227:0x01ca, B:229:0x01cb, B:231:0x01e9, B:238:0x021a, B:247:0x0225, B:248:0x0228, B:281:0x012c, B:282:0x012f, B:233:0x01eb, B:235:0x01f5, B:236:0x0216, B:244:0x0223, B:278:0x012a, B:32:0x024d, B:34:0x0257, B:35:0x0263, B:42:0x026f, B:21:0x01a1, B:23:0x01ab, B:24:0x01b8, B:75:0x03a9, B:77:0x03b3, B:78:0x03ba, B:223:0x01c5, B:85:0x03c6, B:123:0x0507, B:199:0x035e), top: B:6:0x002e, inners: #0, #3, #7, #10, #12, #20, #21, #22, #23, #24, #33 }] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0457 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:212:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x01cb A[Catch: Exception -> 0x0674, SecurityException -> 0x0693, TryCatch #31 {SecurityException -> 0x0693, Exception -> 0x0674, blocks: (B:7:0x002e, B:252:0x00b0, B:254:0x00b6, B:272:0x011d, B:14:0x016d, B:17:0x0175, B:19:0x019f, B:26:0x01bc, B:28:0x022a, B:30:0x024b, B:37:0x0267, B:45:0x0271, B:46:0x0274, B:49:0x027c, B:52:0x02f9, B:54:0x031b, B:66:0x034c, B:71:0x037e, B:73:0x03a7, B:80:0x03be, B:88:0x03c8, B:89:0x03cb, B:105:0x048d, B:108:0x0495, B:126:0x0509, B:127:0x050c, B:134:0x04f8, B:135:0x0511, B:136:0x052a, B:138:0x0530, B:145:0x0582, B:147:0x0588, B:149:0x058e, B:153:0x05ac, B:155:0x05f8, B:156:0x0609, B:202:0x0360, B:203:0x0363, B:215:0x02a2, B:217:0x02c4, B:218:0x02e7, B:226:0x01c7, B:227:0x01ca, B:229:0x01cb, B:231:0x01e9, B:238:0x021a, B:247:0x0225, B:248:0x0228, B:281:0x012c, B:282:0x012f, B:233:0x01eb, B:235:0x01f5, B:236:0x0216, B:244:0x0223, B:278:0x012a, B:32:0x024d, B:34:0x0257, B:35:0x0263, B:42:0x026f, B:21:0x01a1, B:23:0x01ab, B:24:0x01b8, B:75:0x03a9, B:77:0x03b3, B:78:0x03ba, B:223:0x01c5, B:85:0x03c6, B:123:0x0507, B:199:0x035e), top: B:6:0x002e, inners: #0, #3, #7, #10, #12, #20, #21, #22, #23, #24, #33 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x024b A[Catch: Exception -> 0x0674, SecurityException -> 0x0693, TRY_LEAVE, TryCatch #31 {SecurityException -> 0x0693, Exception -> 0x0674, blocks: (B:7:0x002e, B:252:0x00b0, B:254:0x00b6, B:272:0x011d, B:14:0x016d, B:17:0x0175, B:19:0x019f, B:26:0x01bc, B:28:0x022a, B:30:0x024b, B:37:0x0267, B:45:0x0271, B:46:0x0274, B:49:0x027c, B:52:0x02f9, B:54:0x031b, B:66:0x034c, B:71:0x037e, B:73:0x03a7, B:80:0x03be, B:88:0x03c8, B:89:0x03cb, B:105:0x048d, B:108:0x0495, B:126:0x0509, B:127:0x050c, B:134:0x04f8, B:135:0x0511, B:136:0x052a, B:138:0x0530, B:145:0x0582, B:147:0x0588, B:149:0x058e, B:153:0x05ac, B:155:0x05f8, B:156:0x0609, B:202:0x0360, B:203:0x0363, B:215:0x02a2, B:217:0x02c4, B:218:0x02e7, B:226:0x01c7, B:227:0x01ca, B:229:0x01cb, B:231:0x01e9, B:238:0x021a, B:247:0x0225, B:248:0x0228, B:281:0x012c, B:282:0x012f, B:233:0x01eb, B:235:0x01f5, B:236:0x0216, B:244:0x0223, B:278:0x012a, B:32:0x024d, B:34:0x0257, B:35:0x0263, B:42:0x026f, B:21:0x01a1, B:23:0x01ab, B:24:0x01b8, B:75:0x03a9, B:77:0x03b3, B:78:0x03ba, B:223:0x01c5, B:85:0x03c6, B:123:0x0507, B:199:0x035e), top: B:6:0x002e, inners: #0, #3, #7, #10, #12, #20, #21, #22, #23, #24, #33 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x027c A[Catch: Exception -> 0x0674, SecurityException -> 0x0693, TryCatch #31 {SecurityException -> 0x0693, Exception -> 0x0674, blocks: (B:7:0x002e, B:252:0x00b0, B:254:0x00b6, B:272:0x011d, B:14:0x016d, B:17:0x0175, B:19:0x019f, B:26:0x01bc, B:28:0x022a, B:30:0x024b, B:37:0x0267, B:45:0x0271, B:46:0x0274, B:49:0x027c, B:52:0x02f9, B:54:0x031b, B:66:0x034c, B:71:0x037e, B:73:0x03a7, B:80:0x03be, B:88:0x03c8, B:89:0x03cb, B:105:0x048d, B:108:0x0495, B:126:0x0509, B:127:0x050c, B:134:0x04f8, B:135:0x0511, B:136:0x052a, B:138:0x0530, B:145:0x0582, B:147:0x0588, B:149:0x058e, B:153:0x05ac, B:155:0x05f8, B:156:0x0609, B:202:0x0360, B:203:0x0363, B:215:0x02a2, B:217:0x02c4, B:218:0x02e7, B:226:0x01c7, B:227:0x01ca, B:229:0x01cb, B:231:0x01e9, B:238:0x021a, B:247:0x0225, B:248:0x0228, B:281:0x012c, B:282:0x012f, B:233:0x01eb, B:235:0x01f5, B:236:0x0216, B:244:0x0223, B:278:0x012a, B:32:0x024d, B:34:0x0257, B:35:0x0263, B:42:0x026f, B:21:0x01a1, B:23:0x01ab, B:24:0x01b8, B:75:0x03a9, B:77:0x03b3, B:78:0x03ba, B:223:0x01c5, B:85:0x03c6, B:123:0x0507, B:199:0x035e), top: B:6:0x002e, inners: #0, #3, #7, #10, #12, #20, #21, #22, #23, #24, #33 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x02f9 A[Catch: Exception -> 0x0674, SecurityException -> 0x0693, TryCatch #31 {SecurityException -> 0x0693, Exception -> 0x0674, blocks: (B:7:0x002e, B:252:0x00b0, B:254:0x00b6, B:272:0x011d, B:14:0x016d, B:17:0x0175, B:19:0x019f, B:26:0x01bc, B:28:0x022a, B:30:0x024b, B:37:0x0267, B:45:0x0271, B:46:0x0274, B:49:0x027c, B:52:0x02f9, B:54:0x031b, B:66:0x034c, B:71:0x037e, B:73:0x03a7, B:80:0x03be, B:88:0x03c8, B:89:0x03cb, B:105:0x048d, B:108:0x0495, B:126:0x0509, B:127:0x050c, B:134:0x04f8, B:135:0x0511, B:136:0x052a, B:138:0x0530, B:145:0x0582, B:147:0x0588, B:149:0x058e, B:153:0x05ac, B:155:0x05f8, B:156:0x0609, B:202:0x0360, B:203:0x0363, B:215:0x02a2, B:217:0x02c4, B:218:0x02e7, B:226:0x01c7, B:227:0x01ca, B:229:0x01cb, B:231:0x01e9, B:238:0x021a, B:247:0x0225, B:248:0x0228, B:281:0x012c, B:282:0x012f, B:233:0x01eb, B:235:0x01f5, B:236:0x0216, B:244:0x0223, B:278:0x012a, B:32:0x024d, B:34:0x0257, B:35:0x0263, B:42:0x026f, B:21:0x01a1, B:23:0x01ab, B:24:0x01b8, B:75:0x03a9, B:77:0x03b3, B:78:0x03ba, B:223:0x01c5, B:85:0x03c6, B:123:0x0507, B:199:0x035e), top: B:6:0x002e, inners: #0, #3, #7, #10, #12, #20, #21, #22, #23, #24, #33 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x03b3 A[Catch: all -> 0x03c4, TryCatch #21 {all -> 0x03c4, blocks: (B:75:0x03a9, B:77:0x03b3, B:78:0x03ba), top: B:74:0x03a9, outer: #31 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void sendToPowerampQueueNext(java.util.List<java.lang.Long> r61) {
        /*
            Method dump skipped, instructions count: 1696
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.PowerampController.sendToPowerampQueueNext(java.util.List):void");
    }

    public static /* synthetic */ List getTopPlayedTracks$default(PowerampController powerampController, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 40;
        }
        return powerampController.getTopPlayedTracks(i);
    }

    public final List<Pair<String, String>> getTopPlayedTracks(int limit) {
        String str;
        String string;
        List tracks = new ArrayList();
        Uri uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build();
        try {
            Cursor cursor = this.context.getContentResolver().query(uri, new String[]{TableDefs.Files.TITLE_TAG, TableDefs.Files.ARTIST_TAG, "played_times"}, "played_times > 0", null, "played_times DESC LIMIT " + limit);
            if (cursor != null) {
                Cursor cursor2 = cursor;
                try {
                    Cursor cursor3 = cursor2;
                    int columnIndex = cursor3.getColumnIndex(TableDefs.Files.TITLE_TAG);
                    int columnIndex2 = cursor3.getColumnIndex(TableDefs.Files.ARTIST_TAG);
                    while (cursor3.moveToNext()) {
                        String str2 = "";
                        if (columnIndex < 0 || (str = cursor3.getString(columnIndex)) == null) {
                            str = "";
                        }
                        if (columnIndex2 >= 0 && (string = cursor3.getString(columnIndex2)) != null) {
                            str2 = string;
                        }
                        String str3 = str;
                        if (str3.length() == 0) {
                            str3 = "Titel unbekannt";
                        }
                        tracks.add(new Pair(str3, str2));
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor2, null);
                } finally {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PowerampController", "Error getting top played tracks: " + e.getMessage());
        }
        return tracks;
    }

    public static /* synthetic */ List getRecentlyAddedTracks$default(PowerampController powerampController, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 20;
        }
        return powerampController.getRecentlyAddedTracks(i);
    }

    public final List<Pair<String, String>> getRecentlyAddedTracks(int limit) {
        String str;
        String string;
        List tracks = new ArrayList();
        Uri uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build();
        try {
            Cursor cursor = this.context.getContentResolver().query(uri, new String[]{TableDefs.Files.TITLE_TAG, TableDefs.Files.ARTIST_TAG, TableDefs.Files.FILE_CREATED_AT}, null, null, "file_created_at DESC LIMIT " + limit);
            if (cursor != null) {
                Cursor cursor2 = cursor;
                try {
                    Cursor cursor3 = cursor2;
                    int columnIndex = cursor3.getColumnIndex(TableDefs.Files.TITLE_TAG);
                    int columnIndex2 = cursor3.getColumnIndex(TableDefs.Files.ARTIST_TAG);
                    while (cursor3.moveToNext()) {
                        String str2 = "";
                        if (columnIndex < 0 || (str = cursor3.getString(columnIndex)) == null) {
                            str = "";
                        }
                        if (columnIndex2 >= 0 && (string = cursor3.getString(columnIndex2)) != null) {
                            str2 = string;
                        }
                        String str3 = str;
                        if (str3.length() == 0) {
                            str3 = "Titel unbekannt";
                        }
                        tracks.add(new Pair(str3, str2));
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor2, null);
                } finally {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PowerampController", "Error getting recently added tracks: " + e.getMessage());
        }
        return tracks;
    }

    public final int getQueueSize() {
        Uri uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Queue.TABLE).build();
        int count = 0;
        try {
            Cursor cursor = this.context.getContentResolver().query(uri, new String[]{"_id"}, null, null, null);
            count = cursor != null ? cursor.getCount() : 0;
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PowerampController", "Error getting queue size: " + e.getMessage());
        }
        return count;
    }

    public final int getQueuePosition(long trackId) {
        Uri uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Queue.TABLE).build();
        int position = -1;
        try {
            Cursor cursor = this.context.getContentResolver().query(uri, new String[]{"_id", "folder_file_id"}, null, null, "sort ASC");
            if (cursor != null) {
                Cursor cursor2 = cursor;
                try {
                    Cursor cursor3 = cursor2;
                    int columnIndex = cursor3.getColumnIndex("folder_file_id");
                    int columnIndexOrThrow = columnIndex != -1 ? columnIndex : cursor3.getColumnIndexOrThrow("_id");
                    int i = 0;
                    while (true) {
                        if (!cursor3.moveToNext()) {
                            break;
                        }
                        if (cursor3.getLong(columnIndexOrThrow) == trackId) {
                            position = i;
                            break;
                        }
                        i++;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor2, null);
                } finally {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PowerampController", "Error getting queue position for " + trackId + ": " + e.getMessage());
        }
        return position;
    }

    public static /* synthetic */ String getRecentQueueHistory$default(PowerampController powerampController, long j, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 10;
        }
        return powerampController.getRecentQueueHistory(j, i);
    }

    public final String getRecentQueueHistory(long currentTrackId, int limit) {
        int max;
        Uri uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Queue.TABLE).build();
        List history = new ArrayList();
        try {
            Cursor cursor = this.context.getContentResolver().query(uri, new String[]{"_id", "folder_file_id", "title", "artist"}, null, null, "sort_order ASC");
            if (cursor != null) {
                Cursor cursor2 = cursor;
                try {
                    Cursor cursor3 = cursor2;
                    int columnIndex = cursor3.getColumnIndex("folder_file_id");
                    int columnIndexOrThrow = columnIndex != -1 ? columnIndex : cursor3.getColumnIndexOrThrow("_id");
                    int columnIndexOrThrow2 = cursor3.getColumnIndexOrThrow("title");
                    int columnIndexOrThrow3 = cursor3.getColumnIndexOrThrow("artist");
                    List arrayList = new ArrayList();
                    int i = -1;
                    int i2 = 0;
                    while (cursor3.moveToNext()) {
                        long j = cursor3.getLong(columnIndexOrThrow);
                        String string = cursor3.getString(columnIndexOrThrow2);
                        if (string == null) {
                            string = "";
                        }
                        String str = string;
                        String string2 = cursor3.getString(columnIndexOrThrow3);
                        if (string2 == null) {
                            string2 = "";
                        }
                        StringBuilder append = new StringBuilder().append(str);
                        int i3 = columnIndexOrThrow3;
                        arrayList.add(append.append(" by ").append(string2).toString());
                        if (j == currentTrackId) {
                            i = i2;
                        }
                        i2++;
                        columnIndexOrThrow3 = i3;
                    }
                    if (i != -1 && (max = Math.max(0, i - limit)) <= i) {
                        while (true) {
                            history.add(arrayList.get(max));
                            if (max == i) {
                                break;
                            }
                            max++;
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor2, null);
                } finally {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PowerampController", "Error getting queue history: " + e.getMessage());
        }
        return !history.isEmpty() ? CollectionsKt.joinToString$default(history, ", ", null, null, 0, null, null, 62, null) : "";
    }

    public static /* synthetic */ List getTopPlayedArtists$default(PowerampController powerampController, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 10;
        }
        return powerampController.getTopPlayedArtists(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0084, code lost:
    
        r11 = r8.getInt(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<kotlin.Pair<java.lang.String, java.lang.Integer>> getTopPlayedArtists(int r15) {
        /*
            r14 = this;
            java.lang.String r0 = "num_files"
            java.lang.String r1 = "artist"
            java.lang.String r2 = "PowerampController"
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.List r3 = (java.util.List) r3
            android.net.Uri r4 = com.maxmpz.poweramp.player.PowerampAPI.ROOT_URI
            android.net.Uri$Builder r4 = r4.buildUpon()
            java.lang.String r5 = "artists"
            android.net.Uri$Builder r4 = r4.appendEncodedPath(r5)
            android.net.Uri r6 = r4.build()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld1
            r4.<init>()     // Catch: java.lang.Exception -> Ld1
            java.lang.String r5 = "Querying top artists from: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Exception -> Ld1
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch: java.lang.Exception -> Ld1
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> Ld1
            android.util.Log.d(r2, r4)     // Catch: java.lang.Exception -> Ld1
            android.content.Context r4 = r14.context     // Catch: java.lang.Exception -> Ld1
            android.content.ContentResolver r5 = r4.getContentResolver()     // Catch: java.lang.Exception -> Ld1
            r4 = 2
            java.lang.String[] r7 = new java.lang.String[r4]     // Catch: java.lang.Exception -> Ld1
            r4 = 0
            r7[r4] = r1     // Catch: java.lang.Exception -> Ld1
            r8 = 1
            r7[r8] = r0     // Catch: java.lang.Exception -> Ld1
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld1
            r8.<init>()     // Catch: java.lang.Exception -> Ld1
            java.lang.String r9 = "num_files DESC LIMIT "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Exception -> Ld1
            java.lang.StringBuilder r8 = r8.append(r15)     // Catch: java.lang.Exception -> Ld1
            java.lang.String r10 = r8.toString()     // Catch: java.lang.Exception -> Ld1
            r8 = 0
            r9 = 0
            android.database.Cursor r5 = r5.query(r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> Ld1
            if (r5 == 0) goto Lb0
            r7 = r5
            java.io.Closeable r7 = (java.io.Closeable) r7     // Catch: java.lang.Exception -> Ld1
            r8 = r7
            android.database.Cursor r8 = (android.database.Cursor) r8     // Catch: java.lang.Throwable -> La8
            r9 = 0
            int r1 = r8.getColumnIndex(r1)     // Catch: java.lang.Throwable -> La8
            int r0 = r8.getColumnIndex(r0)     // Catch: java.lang.Throwable -> La8
        L70:
            boolean r10 = r8.moveToNext()     // Catch: java.lang.Throwable -> La8
            if (r10 == 0) goto La0
            java.lang.String r10 = ""
            if (r1 < 0) goto L82
            java.lang.String r11 = r8.getString(r1)     // Catch: java.lang.Throwable -> La8
            if (r11 != 0) goto L81
            goto L82
        L81:
            r10 = r11
        L82:
            if (r0 < 0) goto L89
            int r11 = r8.getInt(r0)     // Catch: java.lang.Throwable -> La8
            goto L8a
        L89:
            r11 = r4
        L8a:
            r12 = r10
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12     // Catch: java.lang.Throwable -> La8
            boolean r12 = kotlin.text.StringsKt.isBlank(r12)     // Catch: java.lang.Throwable -> La8
            if (r12 != 0) goto L70
            kotlin.Pair r12 = new kotlin.Pair     // Catch: java.lang.Throwable -> La8
            java.lang.Integer r13 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Throwable -> La8
            r12.<init>(r10, r13)     // Catch: java.lang.Throwable -> La8
            r3.add(r12)     // Catch: java.lang.Throwable -> La8
            goto L70
        La0:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> La8
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r7, r0)     // Catch: java.lang.Exception -> Ld1
            goto Lb0
        La8:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch: java.lang.Throwable -> Lab
        Lab:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r7, r1)     // Catch: java.lang.Exception -> Ld1
            throw r0     // Catch: java.lang.Exception -> Ld1
        Lb0:
            int r0 = r3.size()     // Catch: java.lang.Exception -> Ld1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Ld1
            r1.<init>()     // Catch: java.lang.Exception -> Ld1
            java.lang.String r4 = "Found "
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch: java.lang.Exception -> Ld1
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch: java.lang.Exception -> Ld1
            java.lang.String r1 = " top artists"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> Ld1
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> Ld1
            android.util.Log.d(r2, r0)     // Catch: java.lang.Exception -> Ld1
            goto Lda
        Ld1:
            r0 = move-exception
            java.lang.String r1 = "Error querying top artists"
            r4 = r0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            android.util.Log.e(r2, r1, r4)
        Lda:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.PowerampController.getTopPlayedArtists(int):java.util.List");
    }

    public static /* synthetic */ List getTopGenres$default(PowerampController powerampController, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 10;
        }
        return powerampController.getTopGenres(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x006c, code lost:
    
        r10 = r7.getInt(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<kotlin.Pair<java.lang.String, java.lang.Integer>> getTopGenres(int r14) {
        /*
            r13 = this;
            java.lang.String r0 = "num_files"
            java.lang.String r1 = "genre"
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.List r2 = (java.util.List) r2
            android.net.Uri r3 = com.maxmpz.poweramp.player.PowerampAPI.ROOT_URI
            android.net.Uri$Builder r3 = r3.buildUpon()
            java.lang.String r4 = "genres"
            android.net.Uri$Builder r3 = r3.appendEncodedPath(r4)
            android.net.Uri r5 = r3.build()
            android.content.Context r3 = r13.context     // Catch: java.lang.Exception -> L99
            android.content.ContentResolver r4 = r3.getContentResolver()     // Catch: java.lang.Exception -> L99
            r3 = 2
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: java.lang.Exception -> L99
            r3 = 0
            r6[r3] = r1     // Catch: java.lang.Exception -> L99
            r7 = 1
            r6[r7] = r0     // Catch: java.lang.Exception -> L99
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L99
            r7.<init>()     // Catch: java.lang.Exception -> L99
            java.lang.String r8 = "num_files DESC LIMIT "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.lang.Exception -> L99
            java.lang.StringBuilder r7 = r7.append(r14)     // Catch: java.lang.Exception -> L99
            java.lang.String r9 = r7.toString()     // Catch: java.lang.Exception -> L99
            r7 = 0
            r8 = 0
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L99
            if (r4 == 0) goto L98
            r6 = r4
            java.io.Closeable r6 = (java.io.Closeable) r6     // Catch: java.lang.Exception -> L99
            r7 = r6
            android.database.Cursor r7 = (android.database.Cursor) r7     // Catch: java.lang.Throwable -> L90
            r8 = 0
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L90
            int r0 = r7.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L90
        L58:
            boolean r9 = r7.moveToNext()     // Catch: java.lang.Throwable -> L90
            if (r9 == 0) goto L88
            java.lang.String r9 = ""
            if (r1 < 0) goto L6a
            java.lang.String r10 = r7.getString(r1)     // Catch: java.lang.Throwable -> L90
            if (r10 != 0) goto L69
            goto L6a
        L69:
            r9 = r10
        L6a:
            if (r0 < 0) goto L71
            int r10 = r7.getInt(r0)     // Catch: java.lang.Throwable -> L90
            goto L72
        L71:
            r10 = r3
        L72:
            r11 = r9
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch: java.lang.Throwable -> L90
            boolean r11 = kotlin.text.StringsKt.isBlank(r11)     // Catch: java.lang.Throwable -> L90
            if (r11 != 0) goto L58
            kotlin.Pair r11 = new kotlin.Pair     // Catch: java.lang.Throwable -> L90
            java.lang.Integer r12 = java.lang.Integer.valueOf(r10)     // Catch: java.lang.Throwable -> L90
            r11.<init>(r9, r12)     // Catch: java.lang.Throwable -> L90
            r2.add(r11)     // Catch: java.lang.Throwable -> L90
            goto L58
        L88:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L90
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r6, r0)     // Catch: java.lang.Exception -> L99
            goto L9d
        L90:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch: java.lang.Throwable -> L93
        L93:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r6, r1)     // Catch: java.lang.Exception -> L99
            throw r0     // Catch: java.lang.Exception -> L99
        L98:
            goto L9d
        L99:
            r0 = move-exception
            r0.printStackTrace()
        L9d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.PowerampController.getTopGenres(int):java.util.List");
    }

    public final boolean findArtist(String artistName) {
        Uri uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Artists.TABLE).build();
        try {
            boolean found = true;
            Cursor cursor = this.context.getContentResolver().query(uri, new String[]{"artist"}, "artist = ?", new String[]{artistName}, null);
            if ((cursor != null ? cursor.getCount() : 0) <= 0) {
                found = false;
            }
            if (cursor != null) {
                cursor.close();
            }
            return found;
        } catch (Exception e) {
            return false;
        }
    }

    public final boolean findAlbum(String artistName, String albumName) {
        Uri uri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Albums.TABLE).build();
        try {
            boolean found = true;
            Cursor cursor = this.context.getContentResolver().query(uri, new String[]{"album"}, "artist = ? AND album = ?", new String[]{artistName, albumName}, null);
            if ((cursor != null ? cursor.getCount() : 0) <= 0) {
                found = false;
            }
            if (cursor != null) {
                cursor.close();
            }
            return found;
        } catch (Exception e) {
            return false;
        }
    }
}
