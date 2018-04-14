package com.varun.android.listview.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.varun.android.listview.data.AppDatabase;
import com.varun.android.listview.model.Reminder;

import java.util.List;

/**
 * Created by VarunSasidharan_Nair on 14/08/2017.
 */

public class ReminderListViewModel extends AndroidViewModel {

    private final LiveData<List<Reminder>> itemReminderList;
    private AppDatabase appDatabase;

    public ReminderListViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        itemReminderList = appDatabase.reminderModel().getAllLiveReminders();
    }

    public LiveData<List<Reminder>> getItemReminderList() {
        return itemReminderList;
    }

    public void deleteItem(Reminder reminderModel) {
        new deleteAsyncTask(appDatabase).execute(reminderModel);
    }


    private class deleteAsyncTask extends AsyncTask<Reminder, Void, Void> {
        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }


        @Override
        protected Void doInBackground(Reminder... params) {
            db.reminderModel().deleteReminder(params[0]);
            return null;
        }
    }
}
