package com.maxmpz.poweramp.companion;

import androidx.lifecycle.ViewModel;
import com.maxmpz.poweramp.companion.StatsEngine;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: ExploreViewModel.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\fJ\u0016\u0010*\u001a\u00020+2\u0006\u0010)\u001a\u00020\f2\u0006\u0010,\u001a\u00020\u0019R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR#\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR#\u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0018¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u001d\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\"0\u0018¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u001d\u0010$\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0\u0018¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001cR\u001a\u0010-\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\u000602¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u001a\u00105\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010.\"\u0004\b6\u00100R\u001a\u00107\u001a\u000208X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<¨\u0006="}, d2 = {"Lcom/maxmpz/poweramp/companion/ExploreViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "cachedStats", "", "Lcom/maxmpz/poweramp/companion/StatsEngine$StatItem;", "getCachedStats", "()Ljava/util/List;", "setCachedStats", "(Ljava/util/List;)V", "currentType", "Lcom/maxmpz/poweramp/companion/StatsEngine$ItemType;", "getCurrentType", "()Lcom/maxmpz/poweramp/companion/StatsEngine$ItemType;", "setCurrentType", "(Lcom/maxmpz/poweramp/companion/StatsEngine$ItemType;)V", "currentRange", "Lcom/maxmpz/poweramp/companion/StatsEngine$TimeRange;", "getCurrentRange", "()Lcom/maxmpz/poweramp/companion/StatsEngine$TimeRange;", "setCurrentRange", "(Lcom/maxmpz/poweramp/companion/StatsEngine$TimeRange;)V", "gridViewMap", "", "", "cachedTracks", "getCachedTracks", "()Ljava/util/Map;", "cachedArtists", "getCachedArtists", "cachedAlbums", "getCachedAlbums", "cachedTimeDist", "", "getCachedTimeDist", "lastUpdateMap", "", "", "getLastUpdateMap", "isGridView", "type", "setGridView", "", "value", "isSelectionMode", "()Z", "setSelectionMode", "(Z)V", "selectedItems", "", "getSelectedItems", "()Ljava/util/Set;", "isTimeMachineMode", "setTimeMachineMode", "timeMachineYear", "", "getTimeMachineYear", "()I", "setTimeMachineYear", "(I)V", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ExploreViewModel extends ViewModel {
    private final Map<StatsEngine.TimeRange, List<StatsEngine.StatItem>> cachedAlbums;
    private final Map<StatsEngine.TimeRange, List<StatsEngine.StatItem>> cachedArtists;
    private final Map<StatsEngine.TimeRange, int[]> cachedTimeDist;
    private final Map<StatsEngine.TimeRange, List<StatsEngine.StatItem>> cachedTracks;
    private final Map<StatsEngine.ItemType, Boolean> gridViewMap;
    private boolean isSelectionMode;
    private boolean isTimeMachineMode;
    private final Map<String, Long> lastUpdateMap;
    private final Set<StatsEngine.StatItem> selectedItems;
    private int timeMachineYear;
    private List<StatsEngine.StatItem> cachedStats = CollectionsKt.emptyList();
    private StatsEngine.ItemType currentType = StatsEngine.ItemType.TRACK;
    private StatsEngine.TimeRange currentRange = StatsEngine.TimeRange.LAST_7_DAYS;

    public ExploreViewModel() {
        Map linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(StatsEngine.ItemType.ARTIST, true);
        linkedHashMap.put(StatsEngine.ItemType.ALBUM, true);
        this.gridViewMap = linkedHashMap;
        this.cachedTracks = new LinkedHashMap();
        this.cachedArtists = new LinkedHashMap();
        this.cachedAlbums = new LinkedHashMap();
        this.cachedTimeDist = new LinkedHashMap();
        this.lastUpdateMap = new LinkedHashMap();
        this.selectedItems = new LinkedHashSet();
        this.timeMachineYear = 1;
    }

    public final List<StatsEngine.StatItem> getCachedStats() {
        return this.cachedStats;
    }

    public final void setCachedStats(List<StatsEngine.StatItem> list) {
        this.cachedStats = list;
    }

    public final StatsEngine.ItemType getCurrentType() {
        return this.currentType;
    }

    public final void setCurrentType(StatsEngine.ItemType itemType) {
        this.currentType = itemType;
    }

    public final StatsEngine.TimeRange getCurrentRange() {
        return this.currentRange;
    }

    public final void setCurrentRange(StatsEngine.TimeRange timeRange) {
        this.currentRange = timeRange;
    }

    public final Map<StatsEngine.TimeRange, List<StatsEngine.StatItem>> getCachedTracks() {
        return this.cachedTracks;
    }

    public final Map<StatsEngine.TimeRange, List<StatsEngine.StatItem>> getCachedArtists() {
        return this.cachedArtists;
    }

    public final Map<StatsEngine.TimeRange, List<StatsEngine.StatItem>> getCachedAlbums() {
        return this.cachedAlbums;
    }

    public final Map<StatsEngine.TimeRange, int[]> getCachedTimeDist() {
        return this.cachedTimeDist;
    }

    public final Map<String, Long> getLastUpdateMap() {
        return this.lastUpdateMap;
    }

    public final boolean isGridView(StatsEngine.ItemType type) {
        Boolean bool = this.gridViewMap.get(type);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final void setGridView(StatsEngine.ItemType type, boolean value) {
        this.gridViewMap.put(type, Boolean.valueOf(value));
    }

    /* renamed from: isSelectionMode, reason: from getter */
    public final boolean getIsSelectionMode() {
        return this.isSelectionMode;
    }

    public final void setSelectionMode(boolean z) {
        this.isSelectionMode = z;
    }

    public final Set<StatsEngine.StatItem> getSelectedItems() {
        return this.selectedItems;
    }

    /* renamed from: isTimeMachineMode, reason: from getter */
    public final boolean getIsTimeMachineMode() {
        return this.isTimeMachineMode;
    }

    public final void setTimeMachineMode(boolean z) {
        this.isTimeMachineMode = z;
    }

    public final int getTimeMachineYear() {
        return this.timeMachineYear;
    }

    public final void setTimeMachineYear(int i) {
        this.timeMachineYear = i;
    }
}
