package com.varun.android.listview.data;

import android.content.Context;

import com.varun.android.listview.app.AppController;
import com.varun.android.listview.model.Reminder;

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

/**
 * Created by VarunSasidharan_Nair on 4/08/2017.
 */

public class JsonHandler {

    public static JSONArray getJSONObjectLocal()
    {
        String fileName = "25-07-2017.json";
        Reminder reminder1 = new Reminder();
        Reminder reminder2 = new Reminder();
        reminder1.setRecurringType("None");
        reminder1.setId("123");
        reminder1.setGroup("Personal");
        reminder1.setStatus("In Progress");
        reminder1.setThumbnailUrl("25");
        reminder1.setTitle("Book an appiointment in the hotel");
        reminder1.setParentDate("25-07-2017");

        reminder2.setRecurringType("None");
        reminder2.setId("123");
        reminder2.setGroup("Personal");
        reminder2.setStatus("In Progress");
        reminder2.setThumbnailUrl("25");
        reminder2.setTitle("Book a car wash");
        reminder2.setParentDate("25-07-2017");

        List<Reminder> reminderInputs = new ArrayList<Reminder>();
        reminderInputs.add(reminder1);
        reminderInputs.add(reminder2);

        JSONArray jsonArr = new JSONArray();
        for (Reminder reminder:reminderInputs)
        {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id",reminder.getId());
                jsonObject.put("title", reminder.getTitle());
                jsonObject.put("imageId", reminder.getThumbnailUrl());
                jsonObject.put("group",reminder.getGroup());
                jsonObject.put("status",reminder.getStatus());
                jsonObject.put("recurring",reminder.getRecurringType());
                jsonObject.put("parentDate", reminder.getParentDate());
                jsonArr.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String content = jsonArr.toString();
        boolean isSaved = AppController.getInstance().SaveLocalFile(content,fileName);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(AppController.getInstance().getLocalFileContent(fileName).toString());
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jsonArray;
    }


   /* public static JSONArray getJSONObjectLocal()
    {
        String fileName = "25-07-2017.json";
       *//* String content = "[{\n" +
                "        \"id\": \"1\",\n" +
                "        \"title\": \"Arrange a house Loan and get into the house and come out of the house\",\n" +
                "        \"imageId\": \"25\",\n" +
                "        \"group\": \"Personal\",\n" +
                "        \"status\": \"In progress\",\n" +
                "        \"recurring\": \"None\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"2\",\n" +
                "        \"title\": \"Arrange a Car wash\",\n" +
                "        \"imageId\": \"25\",\n" +
                "        \"group\": \"Official\",\n" +
                "        \"status\": \"Scheduled\",\n" +
                "        \"recurring\": \"Monthly\"\n" +
                "    }]";*//*

        Reminder reminder1 = new Reminder();
        Reminder reminder2 = new Reminder();
        reminder1.setRecurringType("None");
        reminder1.setId("123");
        reminder1.setGroup("Personal");
        reminder1.setStatus("In Progress");
        reminder1.setThumbnailUrl("25");
        reminder1.setTitle("Book an appiointment in the hotel");
        reminder1.setParentDate("25-07-2017");

        reminder2.setRecurringType("None");
        reminder2.setId("123");
        reminder2.setGroup("Personal");
        reminder2.setStatus("In Progress");
        reminder2.setThumbnailUrl("25");
        reminder2.setTitle("Book a car wash");
        reminder2.setParentDate("25-07-2017");

        List<Reminder> reminderInputs = new ArrayList<Reminder>();
        reminderInputs.add(reminder1);
        reminderInputs.add(reminder2);

        JSONArray jsonArr = new JSONArray();
        for (Reminder reminder:reminderInputs)
        {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id",reminder.getId());
                jsonObject.put("title", reminder.getTitle());
                jsonObject.put("imageId", reminder.getThumbnailUrl());
                jsonObject.put("group",reminder.getGroup());
                jsonObject.put("status",reminder.getStatus());
                jsonObject.put("recurring",reminder.getRecurringType());
                jsonObject.put("parentDate", reminder.getParentDate());
                jsonArr.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String content = jsonArr.toString();
        boolean isSaved = AppController.getInstance().SaveLocalFile(content,fileName);
     *//*   try {
            *//**//*FileOutputStream fos = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();*//**//*
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*//*
        JSONArray jsonArray = null;
        FileInputStream fis = null;
        try {
*//*            fis = getActivity().openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader streamReader = new BufferedReader(isr);

            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);*//*
            jsonArray = new JSONArray(responseStrBuilder.toString());
            streamReader.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }*/
}
