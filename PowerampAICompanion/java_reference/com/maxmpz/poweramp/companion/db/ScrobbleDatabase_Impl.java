package com.maxmpz.poweramp.companion.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes8.dex */
public final class ScrobbleDatabase_Impl extends ScrobbleDatabase {
    private volatile ScrobbleDao _scrobbleDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) { // from class: com.maxmpz.poweramp.companion.db.ScrobbleDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `scrobbles` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `trackId` INTEGER NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `album` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `durationMs` INTEGER NOT NULL)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd758f646a519528364f0a010fd8544ac')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `scrobbles`");
                if (ScrobbleDatabase_Impl.this.mCallbacks != null) {
                    int _size = ScrobbleDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((RoomDatabase.Callback) ScrobbleDatabase_Impl.this.mCallbacks.get(_i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(SupportSQLiteDatabase _db) {
                if (ScrobbleDatabase_Impl.this.mCallbacks != null) {
                    int _size = ScrobbleDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((RoomDatabase.Callback) ScrobbleDatabase_Impl.this.mCallbacks.get(_i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                ScrobbleDatabase_Impl.this.mDatabase = _db;
                ScrobbleDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (ScrobbleDatabase_Impl.this.mCallbacks != null) {
                    int _size = ScrobbleDatabase_Impl.this.mCallbacks.size();
                    for (int _i = 0; _i < _size; _i++) {
                        ((RoomDatabase.Callback) ScrobbleDatabase_Impl.this.mCallbacks.get(_i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap<String, TableInfo.Column> _columnsScrobbles = new HashMap<>(7);
                _columnsScrobbles.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsScrobbles.put("trackId", new TableInfo.Column("trackId", "INTEGER", true, 0, null, 1));
                _columnsScrobbles.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                _columnsScrobbles.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, 1));
                _columnsScrobbles.put("album", new TableInfo.Column("album", "TEXT", true, 0, null, 1));
                _columnsScrobbles.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
                _columnsScrobbles.put("durationMs", new TableInfo.Column("durationMs", "INTEGER", true, 0, null, 1));
                HashSet<TableInfo.ForeignKey> _foreignKeysScrobbles = new HashSet<>(0);
                HashSet<TableInfo.Index> _indicesScrobbles = new HashSet<>(0);
                TableInfo _infoScrobbles = new TableInfo("scrobbles", _columnsScrobbles, _foreignKeysScrobbles, _indicesScrobbles);
                TableInfo _existingScrobbles = TableInfo.read(_db, "scrobbles");
                if (!_infoScrobbles.equals(_existingScrobbles)) {
                    return new RoomOpenHelper.ValidationResult(false, "scrobbles(com.maxmpz.poweramp.companion.db.ScrobbleEntity).\n Expected:\n" + _infoScrobbles + "\n Found:\n" + _existingScrobbles);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "d758f646a519528364f0a010fd8544ac", "cd5e19260355b29907cecb5843424c57");
        SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(_openCallback).build();
        SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
        return _helper;
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        HashMap<String, String> _shadowTablesMap = new HashMap<>(0);
        HashMap<String, Set<String>> _viewTables = new HashMap<>(0);
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "scrobbles");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            _db.execSQL("DELETE FROM `scrobbles`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            _db.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!_db.inTransaction()) {
                _db.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<>();
        _typeConvertersMap.put(ScrobbleDao.class, ScrobbleDao_Impl.getRequiredConverters());
        return _typeConvertersMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<>();
        return _autoMigrationSpecsSet;
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // com.maxmpz.poweramp.companion.db.ScrobbleDatabase
    public ScrobbleDao scrobbleDao() {
        ScrobbleDao scrobbleDao;
        if (this._scrobbleDao != null) {
            return this._scrobbleDao;
        }
        synchronized (this) {
            if (this._scrobbleDao == null) {
                this._scrobbleDao = new ScrobbleDao_Impl(this);
            }
            scrobbleDao = this._scrobbleDao;
        }
        return scrobbleDao;
    }
}
