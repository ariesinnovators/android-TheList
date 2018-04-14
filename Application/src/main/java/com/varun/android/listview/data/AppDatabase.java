package com.varun.android.listview.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.varun.android.listview.interfaces.IStorageMethodDAO;
import com.varun.android.listview.model.Reminder;

/**
 * Created by VarunSasidharan_Nair on 10/08/2017.
 */

@Database(entities = {Reminder.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract IStorageMethodDAO reminderModel();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, "ReminderDatabase").build();
/*                    Room.databaseBuilder(context, AppDatabase.class, "tasks")
//                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the exercise, allow queries on the main thread.
                            // Don't do this on a real app!
                            .allowMainThreadQueries()
                            .build();*/

        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                    + "`name` TEXT, PRIMARY KEY(`id`))");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Book "
                    + " ADD COLUMN pub_year INTEGER");
        }
    };

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
