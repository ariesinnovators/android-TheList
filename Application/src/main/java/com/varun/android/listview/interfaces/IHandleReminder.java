package com.varun.android.listview.interfaces;

import java.util.Dictionary;

/**
 * Created by VarunSasidharan_Nair on 3/08/2017.
 */

public interface IHandleReminder extends IReminder {
    public boolean setReminder (Dictionary<String,String> reminder);
    public boolean deleteReminder (String reminderId);
    public boolean updateReminder (Dictionary<String,String> reminder);
}
