package com.maxmpz.poweramp.companion.db;

import java.util.List;
import kotlin.Metadata;

/* compiled from: ScrobbleDao.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H'J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\t\u001a\u00020\u0003H'J\b\u0010\n\u001a\u00020\u000bH'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lcom/maxmpz/poweramp/companion/db/ScrobbleDao;", "", "insert", "", "scrobble", "Lcom/maxmpz/poweramp/companion/db/ScrobbleEntity;", "getAllScrobbles", "", "getScrobblesSince", "sinceTimestamp", "clearAll", "", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes8.dex */
public interface ScrobbleDao {
    void clearAll();

    List<ScrobbleEntity> getAllScrobbles();

    List<ScrobbleEntity> getScrobblesSince(long sinceTimestamp);

    long insert(ScrobbleEntity scrobble);
}
