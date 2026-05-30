package com.maxmpz.poweramp.companion;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Metadata;

/* compiled from: PowerampController.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/maxmpz/poweramp/companion/QueueStats;", "", "playedCount", "", "totalCount", "totalDurationMs", "", "remainingDurationMs", "<init>", "(IIJJ)V", "getPlayedCount", "()I", "getTotalCount", "getTotalDurationMs", "()J", "getRemainingDurationMs", "component1", "component2", "component3", "component4", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class QueueStats {
    private final int playedCount;
    private final long remainingDurationMs;
    private final int totalCount;
    private final long totalDurationMs;

    public static /* synthetic */ QueueStats copy$default(QueueStats queueStats, int i, int i2, long j, long j2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = queueStats.playedCount;
        }
        if ((i3 & 2) != 0) {
            i2 = queueStats.totalCount;
        }
        if ((i3 & 4) != 0) {
            j = queueStats.totalDurationMs;
        }
        if ((i3 & 8) != 0) {
            j2 = queueStats.remainingDurationMs;
        }
        long j3 = j2;
        return queueStats.copy(i, i2, j, j3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getPlayedCount() {
        return this.playedCount;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTotalCount() {
        return this.totalCount;
    }

    /* renamed from: component3, reason: from getter */
    public final long getTotalDurationMs() {
        return this.totalDurationMs;
    }

    /* renamed from: component4, reason: from getter */
    public final long getRemainingDurationMs() {
        return this.remainingDurationMs;
    }

    public final QueueStats copy(int playedCount, int totalCount, long totalDurationMs, long remainingDurationMs) {
        return new QueueStats(playedCount, totalCount, totalDurationMs, remainingDurationMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueStats)) {
            return false;
        }
        QueueStats queueStats = (QueueStats) other;
        return this.playedCount == queueStats.playedCount && this.totalCount == queueStats.totalCount && this.totalDurationMs == queueStats.totalDurationMs && this.remainingDurationMs == queueStats.remainingDurationMs;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.playedCount) * 31) + Integer.hashCode(this.totalCount)) * 31) + Long.hashCode(this.totalDurationMs)) * 31) + Long.hashCode(this.remainingDurationMs);
    }

    public String toString() {
        return "QueueStats(playedCount=" + this.playedCount + ", totalCount=" + this.totalCount + ", totalDurationMs=" + this.totalDurationMs + ", remainingDurationMs=" + this.remainingDurationMs + ")";
    }

    public QueueStats(int playedCount, int totalCount, long totalDurationMs, long remainingDurationMs) {
        this.playedCount = playedCount;
        this.totalCount = totalCount;
        this.totalDurationMs = totalDurationMs;
        this.remainingDurationMs = remainingDurationMs;
    }

    public final int getPlayedCount() {
        return this.playedCount;
    }

    public final long getRemainingDurationMs() {
        return this.remainingDurationMs;
    }

    public final int getTotalCount() {
        return this.totalCount;
    }

    public final long getTotalDurationMs() {
        return this.totalDurationMs;
    }
}
