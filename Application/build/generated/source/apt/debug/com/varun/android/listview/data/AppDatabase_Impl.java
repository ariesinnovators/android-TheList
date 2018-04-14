package com.varun.android.listview.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import com.varun.android.listview.interfaces.IStorageMethodDAO;
import com.varun.android.listview.interfaces.IStorageMethodDAO_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class AppDatabase_Impl extends AppDatabase {
  private volatile IStorageMethodDAO _iStorageMethodDAO;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate() {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Reminder` (`uniqueId` INTEGER PRIMARY KEY AUTOINCREMENT, `title` TEXT, `recurringType` TEXT, `status` TEXT, `group` TEXT, `dueDate` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"3e44baf4ac515bddbd00c6bfbf374e25\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Reminder`");
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsReminder = new HashMap<String, TableInfo.Column>(6);
        _columnsReminder.put("uniqueId", new TableInfo.Column("uniqueId", "INTEGER", 1));
        _columnsReminder.put("title", new TableInfo.Column("title", "TEXT", 0));
        _columnsReminder.put("recurringType", new TableInfo.Column("recurringType", "TEXT", 0));
        _columnsReminder.put("status", new TableInfo.Column("status", "TEXT", 0));
        _columnsReminder.put("group", new TableInfo.Column("group", "TEXT", 0));
        _columnsReminder.put("dueDate", new TableInfo.Column("dueDate", "TEXT", 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReminder = new HashSet<TableInfo.ForeignKey>(0);
        final TableInfo _infoReminder = new TableInfo("Reminder", _columnsReminder, _foreignKeysReminder);
        final TableInfo _existingReminder = TableInfo.read(_db, "Reminder");
        if (! _infoReminder.equals(_existingReminder)) {
          throw new IllegalStateException("Migration didn't properly handle Reminder(com.varun.android.listview.model.Reminder).\n"
                  + " Expected:\n" + _infoReminder + "\n"
                  + " Found:\n" + _existingReminder);
        }
      }
    }, "3e44baf4ac515bddbd00c6bfbf374e25");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .version(1)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Reminder");
  }

  @Override
  public IStorageMethodDAO reminderModel() {
    if (_iStorageMethodDAO != null) {
      return _iStorageMethodDAO;
    } else {
      synchronized(this) {
        if(_iStorageMethodDAO == null) {
          _iStorageMethodDAO = new IStorageMethodDAO_Impl(this);
        }
        return _iStorageMethodDAO;
      }
    }
  }
}
