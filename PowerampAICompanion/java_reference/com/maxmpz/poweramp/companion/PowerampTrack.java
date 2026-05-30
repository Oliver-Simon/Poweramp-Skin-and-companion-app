package com.maxmpz.poweramp.companion;

import android.net.Uri;
import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PowerampTrack.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u000bHÆ\u0003JG\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\tHÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lcom/maxmpz/poweramp/companion/PowerampTrack;", "", "id", "", "title", "", "artist", "durationMs", "playedTimes", "", "albumArtUri", "Landroid/net/Uri;", "<init>", "(JLjava/lang/String;Ljava/lang/String;JILandroid/net/Uri;)V", "getId", "()J", "getTitle", "()Ljava/lang/String;", "getArtist", "getDurationMs", "getPlayedTimes", "()I", "getAlbumArtUri", "()Landroid/net/Uri;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class PowerampTrack {
    private final Uri albumArtUri;
    private final String artist;
    private final long durationMs;
    private final long id;
    private final int playedTimes;
    private final String title;

    public static /* synthetic */ PowerampTrack copy$default(PowerampTrack powerampTrack, long j, String str, String str2, long j2, int i, Uri uri, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = powerampTrack.id;
        }
        long j3 = j;
        if ((i2 & 2) != 0) {
            str = powerampTrack.title;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            str2 = powerampTrack.artist;
        }
        String str4 = str2;
        if ((i2 & 8) != 0) {
            j2 = powerampTrack.durationMs;
        }
        return powerampTrack.copy(j3, str3, str4, j2, (i2 & 16) != 0 ? powerampTrack.playedTimes : i, (i2 & 32) != 0 ? powerampTrack.albumArtUri : uri);
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
    public final long getDurationMs() {
        return this.durationMs;
    }

    /* renamed from: component5, reason: from getter */
    public final int getPlayedTimes() {
        return this.playedTimes;
    }

    /* renamed from: component6, reason: from getter */
    public final Uri getAlbumArtUri() {
        return this.albumArtUri;
    }

    public final PowerampTrack copy(long id, String title, String artist, long durationMs, int playedTimes, Uri albumArtUri) {
        return new PowerampTrack(id, title, artist, durationMs, playedTimes, albumArtUri);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PowerampTrack)) {
            return false;
        }
        PowerampTrack powerampTrack = (PowerampTrack) other;
        return this.id == powerampTrack.id && Intrinsics.areEqual(this.title, powerampTrack.title) && Intrinsics.areEqual(this.artist, powerampTrack.artist) && this.durationMs == powerampTrack.durationMs && this.playedTimes == powerampTrack.playedTimes && Intrinsics.areEqual(this.albumArtUri, powerampTrack.albumArtUri);
    }

    public int hashCode() {
        return (((((((((Long.hashCode(this.id) * 31) + this.title.hashCode()) * 31) + this.artist.hashCode()) * 31) + Long.hashCode(this.durationMs)) * 31) + Integer.hashCode(this.playedTimes)) * 31) + (this.albumArtUri == null ? 0 : this.albumArtUri.hashCode());
    }

    public String toString() {
        return "PowerampTrack(id=" + this.id + ", title=" + this.title + ", artist=" + this.artist + ", durationMs=" + this.durationMs + ", playedTimes=" + this.playedTimes + ", albumArtUri=" + this.albumArtUri + ")";
    }

    public PowerampTrack(long id, String title, String artist, long durationMs, int playedTimes, Uri albumArtUri) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.durationMs = durationMs;
        this.playedTimes = playedTimes;
        this.albumArtUri = albumArtUri;
    }

    public /* synthetic */ PowerampTrack(long j, String str, String str2, long j2, int i, Uri uri, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, (i2 & 8) != 0 ? 0L : j2, (i2 & 16) != 0 ? 0 : i, (i2 & 32) != 0 ? null : uri);
    }

    public final long getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    public final int getPlayedTimes() {
        return this.playedTimes;
    }

    public final Uri getAlbumArtUri() {
        return this.albumArtUri;
    }
}
