package com.varun.android.listview.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.varun.android.listview.model.Reminder;

/**
 * Created by VarunSasidharan_Nair on 3/08/2017.
 */

public class DataHandler {


    public List<Reminder> displayLocalReminderList(JSONArray jsonArray) {
        List<Reminder> reminderList = new ArrayList<Reminder>();

        // Parsing json
        for (int i = 0; i < jsonArray.length(); i++) {
            try {

                JSONObject obj = jsonArray.getJSONObject(i);
                Reminder reminder = new Reminder();
                reminder.setTitle(obj.getString("title"));
                reminder.setThumbnailUrl("calendar_" + obj.getString("imageId"));
                reminder.setGroup(obj.getString("group"));
                reminder.setId(obj.getString("id"));
                reminder.setRecurringType(obj.getString("recurring"));

                ArrayList<String> genre = new ArrayList<String>();

                // adding movie to movies array
                reminderList.add(reminder);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // notifying list adapter about data changes
        // so that it renders the list view with updated data
        return reminderList;
    }
}
