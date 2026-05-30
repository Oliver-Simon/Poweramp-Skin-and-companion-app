package com.maxmpz.poweramp.companion.db;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public final class ScrobbleDao_Impl implements ScrobbleDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<ScrobbleEntity> __insertionAdapterOfScrobbleEntity;
    private final SharedSQLiteStatement __preparedStmtOfClearAll;

    public ScrobbleDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfScrobbleEntity = new EntityInsertionAdapter<ScrobbleEntity>(__db) { // from class: com.maxmpz.poweramp.companion.db.ScrobbleDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `scrobbles` (`id`,`trackId`,`title`,`artist`,`album`,`timestamp`,`durationMs`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, ScrobbleEntity value) {
                stmt.bindLong(1, value.getId());
                stmt.bindLong(2, value.getTrackId());
                if (value.getTitle() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getTitle());
                }
                if (value.getArtist() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getArtist());
                }
                if (value.getAlbum() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getAlbum());
                }
                stmt.bindLong(6, value.getTimestamp());
                stmt.bindLong(7, value.getDurationMs());
            }
        };
        this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) { // from class: com.maxmpz.poweramp.companion.db.ScrobbleDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM scrobbles";
            }
        };
    }

    @Override // com.maxmpz.poweramp.companion.db.ScrobbleDao
    public long insert(final ScrobbleEntity scrobble) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long _result = this.__insertionAdapterOfScrobbleEntity.insertAndReturnId(scrobble);
            this.__db.setTransactionSuccessful();
            return _result;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.maxmpz.poweramp.companion.db.ScrobbleDao
    public void clearAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement _stmt = this.__preparedStmtOfClearAll.acquire();
        this.__db.beginTransaction();
        try {
            _stmt.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfClearAll.release(_stmt);
        }
    }

    @Override // com.maxmpz.poweramp.companion.db.ScrobbleDao
    public List<ScrobbleEntity> getAllScrobbles() {
        String _tmpTitle;
        String _tmpArtist;
        String _tmpAlbum;
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM scrobbles ORDER BY timestamp DESC", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfTrackId = CursorUtil.getColumnIndexOrThrow(_cursor, "trackId");
            int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
            int _cursorIndexOfAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "album");
            int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
            List<ScrobbleEntity> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                long _tmpId = _cursor.getLong(_cursorIndexOfId);
                long _tmpTrackId = _cursor.getLong(_cursorIndexOfTrackId);
                if (_cursor.isNull(_cursorIndexOfTitle)) {
                    _tmpTitle = null;
                } else {
                    String _tmpTitle2 = _cursor.getString(_cursorIndexOfTitle);
                    _tmpTitle = _tmpTitle2;
                }
                if (_cursor.isNull(_cursorIndexOfArtist)) {
                    _tmpArtist = null;
                } else {
                    String _tmpArtist2 = _cursor.getString(_cursorIndexOfArtist);
                    _tmpArtist = _tmpArtist2;
                }
                if (_cursor.isNull(_cursorIndexOfAlbum)) {
                    _tmpAlbum = null;
                } else {
                    String _tmpAlbum2 = _cursor.getString(_cursorIndexOfAlbum);
                    _tmpAlbum = _tmpAlbum2;
                }
                long _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
                long _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
                ScrobbleEntity _item = new ScrobbleEntity(_tmpId, _tmpTrackId, _tmpTitle, _tmpArtist, _tmpAlbum, _tmpTimestamp, _tmpDurationMs);
                _result.add(_item);
            }
            return _result;
        } finally {
            _cursor.close();
            _statement.release();
        }
    }

    @Override // com.maxmpz.poweramp.companion.db.ScrobbleDao
    public List<ScrobbleEntity> getScrobblesSince(final long sinceTimestamp) {
        String _tmpTitle;
        String _tmpArtist;
        String _tmpAlbum;
        RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM scrobbles WHERE timestamp >= ? ORDER BY timestamp DESC", 1);
        _statement.bindLong(1, sinceTimestamp);
        this.__db.assertNotSuspendingTransaction();
        Cursor _cursor = DBUtil.query(this.__db, _statement, false, null);
        try {
            int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            int _cursorIndexOfTrackId = CursorUtil.getColumnIndexOrThrow(_cursor, "trackId");
            int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
            int _cursorIndexOfAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "album");
            int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
            List<ScrobbleEntity> _result = new ArrayList<>(_cursor.getCount());
            while (_cursor.moveToNext()) {
                long _tmpId = _cursor.getLong(_cursorIndexOfId);
                long _tmpTrackId = _cursor.getLong(_cursorIndexOfTrackId);
                if (_cursor.isNull(_cursorIndexOfTitle)) {
                    _tmpTitle = null;
                } else {
                    String _tmpTitle2 = _cursor.getString(_cursorIndexOfTitle);
                    _tmpTitle = _tmpTitle2;
                }
                if (_cursor.isNull(_cursorIndexOfArtist)) {
                    _tmpArtist = null;
                } else {
                    String _tmpArtist2 = _cursor.getString(_cursorIndexOfArtist);
                    _tmpArtist = _tmpArtist2;
                }
                if (_cursor.isNull(_cursorIndexOfAlbum)) {
                    _tmpAlbum = null;
                } else {
                    String _tmpAlbum2 = _cursor.getString(_cursorIndexOfAlbum);
                    _tmpAlbum = _tmpAlbum2;
                }
                long _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
                long _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
                ScrobbleEntity _item = new ScrobbleEntity(_tmpId, _tmpTrackId, _tmpTitle, _tmpArtist, _tmpAlbum, _tmpTimestamp, _tmpDurationMs);
                _result.add(_item);
            }
            return _result;
        } finally {
            _cursor.close();
            _statement.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
