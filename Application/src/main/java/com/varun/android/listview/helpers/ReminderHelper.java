package com.varun.android.listview.helpers;

import android.content.Context;
import android.provider.ContactsContract;

import com.varun.android.listview.data.DataHandler;
import com.varun.android.listview.interfaces.IHandleReminder;
import com.varun.android.listview.interfaces.IReminder;
import com.varun.android.listview.model.Reminder;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by VarunSasidharan_Nair on 3/08/2017.
 */

public class ReminderHelper implements IHandleReminder {


    private JSONArray jsonArray;

    public ReminderHelper(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public boolean setReminder(Dictionary<String, String> reminder) {
        return true;
    }

    @Override
    public boolean deleteReminder(String reminderId) {
        return true;
    }

    @Override
    public boolean updateReminder(Dictionary<String, String> reminder) {
        return true;
    }


    @Override
    public List<Reminder> getReminders(String param, CRITERIA criteria) {
        DataHandler dataHandler = new DataHandler();
        return dataHandler.displayLocalReminderList(this.jsonArray);
    }

    @Override
    public boolean destroyReminders(List<String> reminderIds) {
        return true;
    }
}
