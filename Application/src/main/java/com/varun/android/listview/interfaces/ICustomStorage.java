package com.varun.android.listview.interfaces;

import com.varun.android.listview.model.Reminder;

import java.util.List;

/**
 * Created by VarunSasidharan_Nair on 10/08/2017.
 */

public interface ICustomStorage extends IStorageMethodDAO {
    public enum SearchType
    {
        DATE,MONTH
    }
    public Reminder PUT(Reminder reminder);
    public Reminder UPDATE (Reminder reminder);
    public List<Reminder> GET(String param, SearchType paramType);
}
