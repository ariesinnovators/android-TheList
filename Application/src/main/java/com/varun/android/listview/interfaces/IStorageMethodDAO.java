package com.varun.android.listview.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.varun.android.listview.model.Reminder;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by VarunSasidharan_Nair on 10/08/2017.
 */

@Dao
public interface IStorageMethodDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addReminder(Reminder reminder);

    @Query("select * from reminder")
    public List<Reminder> getAllReminders();

    @Query("select * from reminder")
    public LiveData<List<Reminder>> getAllLiveReminders();

    @Delete
    void deleteReminder(Reminder reminder);

    @Query("select * from reminder where uniqueId = :id")
    public Reminder getReminder(long id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateReminder(Reminder reminder);

    @Query("delete from reminder")
    void removeAllReminders();
}
