package com.maxmpz.poweramp.companion.db;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScrobbleEntity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006$"}, d2 = {"Lcom/maxmpz/poweramp/companion/db/ScrobbleEntity;", "", "id", "", "trackId", "title", "", "artist", "album", "timestamp", "durationMs", "<init>", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getId", "()J", "getTrackId", "getTitle", "()Ljava/lang/String;", "getArtist", "getAlbum", "getTimestamp", "getDurationMs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes8.dex */
public final /* data */ class ScrobbleEntity {
    private final String album;
    private final String artist;
    private final long durationMs;
    private final long id;
    private final long timestamp;
    private final String title;
    private final long trackId;

    public static /* synthetic */ ScrobbleEntity copy$default(ScrobbleEntity scrobbleEntity, long j, long j2, String str, String str2, String str3, long j3, long j4, int i, Object obj) {
        if ((i & 1) != 0) {
            j = scrobbleEntity.id;
        }
        return scrobbleEntity.copy(j, (i & 2) != 0 ? scrobbleEntity.trackId : j2, (i & 4) != 0 ? scrobbleEntity.title : str, (i & 8) != 0 ? scrobbleEntity.artist : str2, (i & 16) != 0 ? scrobbleEntity.album : str3, (i & 32) != 0 ? scrobbleEntity.timestamp : j3, (i & 64) != 0 ? scrobbleEntity.durationMs : j4);
    }

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final long getTrackId() {
        return this.trackId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component4, reason: from getter */
    public final String getArtist() {
        return this.artist;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAlbum() {
        return this.album;
    }

    /* renamed from: component6, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component7, reason: from getter */
    public final long getDurationMs() {
        return this.durationMs;
    }

    public final ScrobbleEntity copy(long id, long trackId, String title, String artist, String album, long timestamp, long durationMs) {
        return new ScrobbleEntity(id, trackId, title, artist, album, timestamp, durationMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScrobbleEntity)) {
            return false;
        }
        ScrobbleEntity scrobbleEntity = (ScrobbleEntity) other;
        return this.id == scrobbleEntity.id && this.trackId == scrobbleEntity.trackId && Intrinsics.areEqual(this.title, scrobbleEntity.title) && Intrinsics.areEqual(this.artist, scrobbleEntity.artist) && Intrinsics.areEqual(this.album, scrobbleEntity.album) && this.timestamp == scrobbleEntity.timestamp && this.durationMs == scrobbleEntity.durationMs;
    }

    public int hashCode() {
        return (((((((((((Long.hashCode(this.id) * 31) + Long.hashCode(this.trackId)) * 31) + this.title.hashCode()) * 31) + this.artist.hashCode()) * 31) + this.album.hashCode()) * 31) + Long.hashCode(this.timestamp)) * 31) + Long.hashCode(this.durationMs);
    }

    public String toString() {
        return "ScrobbleEntity(id=" + this.id + ", trackId=" + this.trackId + ", title=" + this.title + ", artist=" + this.artist + ", album=" + this.album + ", timestamp=" + this.timestamp + ", durationMs=" + this.durationMs + ")";
    }

    public ScrobbleEntity(long id, long trackId, String title, String artist, String album, long timestamp, long durationMs) {
        this.id = id;
        this.trackId = trackId;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.timestamp = timestamp;
        this.durationMs = durationMs;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ ScrobbleEntity(long r15, long r17, java.lang.String r19, java.lang.String r20, java.lang.String r21, long r22, long r24, int r26, kotlin.jvm.internal.DefaultConstructorMarker r27) {
        /*
            r14 = this;
            r0 = r26 & 1
            if (r0 == 0) goto L8
            r0 = 0
            r3 = r0
            goto L9
        L8:
            r3 = r15
        L9:
            r2 = r14
            r5 = r17
            r7 = r19
            r8 = r20
            r9 = r21
            r10 = r22
            r12 = r24
            r2.<init>(r3, r5, r7, r8, r9, r10, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.db.ScrobbleEntity.<init>(long, long, java.lang.String, java.lang.String, java.lang.String, long, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final long getId() {
        return this.id;
    }

    public final long getTrackId() {
        return this.trackId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final String getAlbum() {
        return this.album;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }
}
