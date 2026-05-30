package com.maxmpz.poweramp.companion;

import com.maxmpz.poweramp.player.RouterConsts;
import com.maxmpz.poweramp.player.TableDefs;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecommendationEngine.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J9\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/maxmpz/poweramp/companion/DiscoveryIntent;", "", "tags", "", "", TableDefs.Artists.TABLE, "keywords", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getTags", "()Ljava/util/List;", "getArtists", "getKeywords", "component1", "component2", "component3", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class DiscoveryIntent {
    private final List<String> artists;
    private final List<String> keywords;
    private final List<String> tags;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DiscoveryIntent copy$default(DiscoveryIntent discoveryIntent, List list, List list2, List list3, int i, Object obj) {
        if ((i & 1) != 0) {
            list = discoveryIntent.tags;
        }
        if ((i & 2) != 0) {
            list2 = discoveryIntent.artists;
        }
        if ((i & 4) != 0) {
            list3 = discoveryIntent.keywords;
        }
        return discoveryIntent.copy(list, list2, list3);
    }

    public final List<String> component1() {
        return this.tags;
    }

    public final List<String> component2() {
        return this.artists;
    }

    public final List<String> component3() {
        return this.keywords;
    }

    public final DiscoveryIntent copy(List<String> tags, List<String> artists, List<String> keywords) {
        return new DiscoveryIntent(tags, artists, keywords);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DiscoveryIntent)) {
            return false;
        }
        DiscoveryIntent discoveryIntent = (DiscoveryIntent) other;
        return Intrinsics.areEqual(this.tags, discoveryIntent.tags) && Intrinsics.areEqual(this.artists, discoveryIntent.artists) && Intrinsics.areEqual(this.keywords, discoveryIntent.keywords);
    }

    public int hashCode() {
        return (((this.tags.hashCode() * 31) + this.artists.hashCode()) * 31) + this.keywords.hashCode();
    }

    public String toString() {
        return "DiscoveryIntent(tags=" + this.tags + ", artists=" + this.artists + ", keywords=" + this.keywords + ")";
    }

    public DiscoveryIntent(List<String> list, List<String> list2, List<String> list3) {
        this.tags = list;
        this.artists = list2;
        this.keywords = list3;
    }

    public final List<String> getTags() {
        return this.tags;
    }

    public final List<String> getArtists() {
        return this.artists;
    }

    public final List<String> getKeywords() {
        return this.keywords;
    }
}
