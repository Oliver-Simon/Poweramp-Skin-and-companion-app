package com.maxmpz.poweramp.companion.db;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ScrobbleDatabase.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/maxmpz/poweramp/companion/db/ScrobbleDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "scrobbleDao", "Lcom/maxmpz/poweramp/companion/db/ScrobbleDao;", "Companion", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes8.dex */
public abstract class ScrobbleDatabase extends RoomDatabase {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile ScrobbleDatabase INSTANCE;

    public abstract ScrobbleDao scrobbleDao();

    /* compiled from: ScrobbleDatabase.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/maxmpz/poweramp/companion/db/ScrobbleDatabase$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/maxmpz/poweramp/companion/db/ScrobbleDatabase;", "getDatabase", "context", "Landroid/content/Context;", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes8.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScrobbleDatabase getDatabase(Context context) {
            ScrobbleDatabase scrobbleDatabase;
            ScrobbleDatabase scrobbleDatabase2 = ScrobbleDatabase.INSTANCE;
            if (scrobbleDatabase2 != null) {
                return scrobbleDatabase2;
            }
            synchronized (this) {
                scrobbleDatabase = (ScrobbleDatabase) Room.databaseBuilder(context.getApplicationContext(), ScrobbleDatabase.class, "scrobble_database").fallbackToDestructiveMigration().build();
                Companion companion = ScrobbleDatabase.INSTANCE;
                ScrobbleDatabase.INSTANCE = scrobbleDatabase;
            }
            return scrobbleDatabase;
        }
    }
}
