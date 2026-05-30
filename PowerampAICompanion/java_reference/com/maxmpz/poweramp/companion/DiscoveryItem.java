package com.maxmpz.poweramp.companion;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecommendationEngine.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/maxmpz/poweramp/companion/DiscoveryItem;", "", "title", "", "artist", "reason", "type", "imageUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getArtist", "getReason", "getType", "getImageUrl", "setImageUrl", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class DiscoveryItem {
    private final String artist;
    private String imageUrl;
    private final String reason;
    private final String title;
    private final String type;

    public static /* synthetic */ DiscoveryItem copy$default(DiscoveryItem discoveryItem, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = discoveryItem.title;
        }
        if ((i & 2) != 0) {
            str2 = discoveryItem.artist;
        }
        if ((i & 4) != 0) {
            str3 = discoveryItem.reason;
        }
        if ((i & 8) != 0) {
            str4 = discoveryItem.type;
        }
        if ((i & 16) != 0) {
            str5 = discoveryItem.imageUrl;
        }
        String str6 = str5;
        String str7 = str3;
        return discoveryItem.copy(str, str2, str7, str4, str6);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final String getArtist() {
        return this.artist;
    }

    /* renamed from: component3, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* renamed from: component4, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component5, reason: from getter */
    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final DiscoveryItem copy(String title, String artist, String reason, String type, String imageUrl) {
        return new DiscoveryItem(title, artist, reason, type, imageUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DiscoveryItem)) {
            return false;
        }
        DiscoveryItem discoveryItem = (DiscoveryItem) other;
        return Intrinsics.areEqual(this.title, discoveryItem.title) && Intrinsics.areEqual(this.artist, discoveryItem.artist) && Intrinsics.areEqual(this.reason, discoveryItem.reason) && Intrinsics.areEqual(this.type, discoveryItem.type) && Intrinsics.areEqual(this.imageUrl, discoveryItem.imageUrl);
    }

    public int hashCode() {
        return (((((((this.title.hashCode() * 31) + this.artist.hashCode()) * 31) + this.reason.hashCode()) * 31) + this.type.hashCode()) * 31) + (this.imageUrl == null ? 0 : this.imageUrl.hashCode());
    }

    public String toString() {
        return "DiscoveryItem(title=" + this.title + ", artist=" + this.artist + ", reason=" + this.reason + ", type=" + this.type + ", imageUrl=" + this.imageUrl + ")";
    }

    public DiscoveryItem(String title, String artist, String reason, String type, String imageUrl) {
        this.title = title;
        this.artist = artist;
        this.reason = reason;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ DiscoveryItem(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r6 = this;
            r12 = r12 & 16
            if (r12 == 0) goto L7
            r11 = 0
            r5 = r11
            goto L8
        L7:
            r5 = r11
        L8:
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.DiscoveryItem.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final String getReason() {
        return this.reason;
    }

    public final String getType() {
        return this.type;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final void setImageUrl(String str) {
        this.imageUrl = str;
    }
}
