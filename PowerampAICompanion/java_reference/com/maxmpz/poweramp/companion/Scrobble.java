package com.maxmpz.poweramp.companion;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecommendationEngine.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\tHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\tHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006%"}, d2 = {"Lcom/maxmpz/poweramp/companion/Scrobble;", "", "artist", "", "album", "track", "timestamp", "", "hour", "", "year", "dayOfYear", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JIII)V", "getArtist", "()Ljava/lang/String;", "getAlbum", "getTrack", "getTimestamp", "()J", "getHour", "()I", "getYear", "getDayOfYear", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class Scrobble {
    private final String album;
    private final String artist;
    private final int dayOfYear;
    private final int hour;
    private final long timestamp;
    private final String track;
    private final int year;

    public static /* synthetic */ Scrobble copy$default(Scrobble scrobble, String str, String str2, String str3, long j, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = scrobble.artist;
        }
        if ((i4 & 2) != 0) {
            str2 = scrobble.album;
        }
        if ((i4 & 4) != 0) {
            str3 = scrobble.track;
        }
        if ((i4 & 8) != 0) {
            j = scrobble.timestamp;
        }
        if ((i4 & 16) != 0) {
            i = scrobble.hour;
        }
        if ((i4 & 32) != 0) {
            i2 = scrobble.year;
        }
        if ((i4 & 64) != 0) {
            i3 = scrobble.dayOfYear;
        }
        int i5 = i3;
        int i6 = i;
        long j2 = j;
        String str4 = str3;
        return scrobble.copy(str, str2, str4, j2, i6, i2, i5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getArtist() {
        return this.artist;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAlbum() {
        return this.album;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTrack() {
        return this.track;
    }

    /* renamed from: component4, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component5, reason: from getter */
    public final int getHour() {
        return this.hour;
    }

    /* renamed from: component6, reason: from getter */
    public final int getYear() {
        return this.year;
    }

    /* renamed from: component7, reason: from getter */
    public final int getDayOfYear() {
        return this.dayOfYear;
    }

    public final Scrobble copy(String artist, String album, String track, long timestamp, int hour, int year, int dayOfYear) {
        return new Scrobble(artist, album, track, timestamp, hour, year, dayOfYear);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Scrobble)) {
            return false;
        }
        Scrobble scrobble = (Scrobble) other;
        return Intrinsics.areEqual(this.artist, scrobble.artist) && Intrinsics.areEqual(this.album, scrobble.album) && Intrinsics.areEqual(this.track, scrobble.track) && this.timestamp == scrobble.timestamp && this.hour == scrobble.hour && this.year == scrobble.year && this.dayOfYear == scrobble.dayOfYear;
    }

    public int hashCode() {
        return (((((((((((this.artist.hashCode() * 31) + this.album.hashCode()) * 31) + this.track.hashCode()) * 31) + Long.hashCode(this.timestamp)) * 31) + Integer.hashCode(this.hour)) * 31) + Integer.hashCode(this.year)) * 31) + Integer.hashCode(this.dayOfYear);
    }

    public String toString() {
        return "Scrobble(artist=" + this.artist + ", album=" + this.album + ", track=" + this.track + ", timestamp=" + this.timestamp + ", hour=" + this.hour + ", year=" + this.year + ", dayOfYear=" + this.dayOfYear + ")";
    }

    public Scrobble(String artist, String album, String track, long timestamp, int hour, int year, int dayOfYear) {
        this.artist = artist;
        this.album = album;
        this.track = track;
        this.timestamp = timestamp;
        this.hour = hour;
        this.year = year;
        this.dayOfYear = dayOfYear;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final String getAlbum() {
        return this.album;
    }

    public final String getTrack() {
        return this.track;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final int getHour() {
        return this.hour;
    }

    public final int getYear() {
        return this.year;
    }

    public final int getDayOfYear() {
        return this.dayOfYear;
    }
}
