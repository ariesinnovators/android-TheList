package com.varun.android.listview.interfaces;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.varun.android.listview.model.Reminder;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class IStorageMethodDAO_Impl implements IStorageMethodDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfReminder;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfReminder;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfReminder;

  private final SharedSQLiteStatement __preparedStmtOfRemoveAllReminders;

  public IStorageMethodDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReminder = new EntityInsertionAdapter<Reminder>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Reminder`(`uniqueId`,`title`,`recurringType`,`status`,`group`,`dueDate`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Reminder value) {
        stmt.bindLong(1, value.getUniqueId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getRecurringType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getRecurringType());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getStatus());
        }
        if (value.getGroup() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getGroup());
        }
        if (value.getDueDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDueDate());
        }
      }
    };
    this.__deletionAdapterOfReminder = new EntityDeletionOrUpdateAdapter<Reminder>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Reminder` WHERE `uniqueId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Reminder value) {
        stmt.bindLong(1, value.getUniqueId());
      }
    };
    this.__updateAdapterOfReminder = new EntityDeletionOrUpdateAdapter<Reminder>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `Reminder` SET `uniqueId` = ?,`title` = ?,`recurringType` = ?,`status` = ?,`group` = ?,`dueDate` = ? WHERE `uniqueId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Reminder value) {
        stmt.bindLong(1, value.getUniqueId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getRecurringType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getRecurringType());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getStatus());
        }
        if (value.getGroup() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getGroup());
        }
        if (value.getDueDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDueDate());
        }
        stmt.bindLong(7, value.getUniqueId());
      }
    };
    this.__preparedStmtOfRemoveAllReminders = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from reminder";
        return _query;
      }
    };
  }

  @Override
  public void addReminder(Reminder reminder) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfReminder.insert(reminder);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteReminder(Reminder reminder) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfReminder.handle(reminder);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateReminder(Reminder reminder) {
    __db.beginTransaction();
    try {
      __updateAdapterOfReminder.handle(reminder);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void removeAllReminders() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveAllReminders.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveAllReminders.release(_stmt);
    }
  }

  @Override
  public List<Reminder> getAllReminders() {
    final String _sql = "select * from reminder";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUniqueId = _cursor.getColumnIndexOrThrow("uniqueId");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfRecurringType = _cursor.getColumnIndexOrThrow("recurringType");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final int _cursorIndexOfGroup = _cursor.getColumnIndexOrThrow("group");
      final int _cursorIndexOfDueDate = _cursor.getColumnIndexOrThrow("dueDate");
      final List<Reminder> _result = new ArrayList<Reminder>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Reminder _item;
        _item = new Reminder();
        final long _tmpUniqueId;
        _tmpUniqueId = _cursor.getLong(_cursorIndexOfUniqueId);
        _item.setUniqueId(_tmpUniqueId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpRecurringType;
        _tmpRecurringType = _cursor.getString(_cursorIndexOfRecurringType);
        _item.setRecurringType(_tmpRecurringType);
        final String _tmpStatus;
        _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        _item.setStatus(_tmpStatus);
        final String _tmpGroup;
        _tmpGroup = _cursor.getString(_cursorIndexOfGroup);
        _item.setGroup(_tmpGroup);
        final String _tmpDueDate;
        _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
        _item.setDueDate(_tmpDueDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Reminder>> getAllLiveReminders() {
    final String _sql = "select * from reminder";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Reminder>>() {
      private Observer _observer;

      @Override
      protected List<Reminder> compute() {
        if (_observer == null) {
          _observer = new Observer("reminder") {
            @Override
            public void onInvalidated() {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUniqueId = _cursor.getColumnIndexOrThrow("uniqueId");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfRecurringType = _cursor.getColumnIndexOrThrow("recurringType");
          final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
          final int _cursorIndexOfGroup = _cursor.getColumnIndexOrThrow("group");
          final int _cursorIndexOfDueDate = _cursor.getColumnIndexOrThrow("dueDate");
          final List<Reminder> _result = new ArrayList<Reminder>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Reminder _item;
            _item = new Reminder();
            final long _tmpUniqueId;
            _tmpUniqueId = _cursor.getLong(_cursorIndexOfUniqueId);
            _item.setUniqueId(_tmpUniqueId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpRecurringType;
            _tmpRecurringType = _cursor.getString(_cursorIndexOfRecurringType);
            _item.setRecurringType(_tmpRecurringType);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            _item.setStatus(_tmpStatus);
            final String _tmpGroup;
            _tmpGroup = _cursor.getString(_cursorIndexOfGroup);
            _item.setGroup(_tmpGroup);
            final String _tmpDueDate;
            _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
            _item.setDueDate(_tmpDueDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public Reminder getReminder(long id) {
    final String _sql = "select * from reminder where uniqueId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUniqueId = _cursor.getColumnIndexOrThrow("uniqueId");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfRecurringType = _cursor.getColumnIndexOrThrow("recurringType");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final int _cursorIndexOfGroup = _cursor.getColumnIndexOrThrow("group");
      final int _cursorIndexOfDueDate = _cursor.getColumnIndexOrThrow("dueDate");
      final Reminder _result;
      if(_cursor.moveToFirst()) {
        _result = new Reminder();
        final long _tmpUniqueId;
        _tmpUniqueId = _cursor.getLong(_cursorIndexOfUniqueId);
        _result.setUniqueId(_tmpUniqueId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _result.setTitle(_tmpTitle);
        final String _tmpRecurringType;
        _tmpRecurringType = _cursor.getString(_cursorIndexOfRecurringType);
        _result.setRecurringType(_tmpRecurringType);
        final String _tmpStatus;
        _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        _result.setStatus(_tmpStatus);
        final String _tmpGroup;
        _tmpGroup = _cursor.getString(_cursorIndexOfGroup);
        _result.setGroup(_tmpGroup);
        final String _tmpDueDate;
        _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
        _result.setDueDate(_tmpDueDate);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
