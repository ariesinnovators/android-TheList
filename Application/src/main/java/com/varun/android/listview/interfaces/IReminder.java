package com.varun.android.listview.interfaces;

import android.content.Context;

import com.varun.android.listview.model.Reminder;

import java.util.Dictionary;
import java.util.List;

/**
 * Created by VarunSasidharan_Nair on 3/08/2017.
 */

public interface IReminder {
    public enum CRITERIA
    {
        DATE,MONTH
    }
    public List<Reminder> getReminders(String param, IReminder.CRITERIA criteria);
    public boolean destroyReminders (List<String> reminderIds);
}
