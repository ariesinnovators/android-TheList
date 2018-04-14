package com.varun.android.listview.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.varun.android.listview.R;
import com.varun.android.listview.adapter.RecyclerAdapter;
import com.varun.android.listview.helpers.ReminderHelper;
import com.varun.android.listview.interfaces.IReminder;
import com.varun.android.listview.model.Reminder;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderListFragmentThisMonth extends Fragment {

    public final static String ITEMS_COUNT_KEY = "ReminderListFragmentThisMonth$ItemsCount";
    public ReminderListFragmentThisMonth() {
        // Required empty public constructor
    }

    public static ReminderListFragmentThisMonth createInstance(int itemsCount) {
        ReminderListFragmentThisMonth reminderListFragment = new ReminderListFragmentThisMonth();
        Bundle bundle = new Bundle();
        bundle.putInt(ITEMS_COUNT_KEY, itemsCount);
        reminderListFragment.setArguments(bundle);
        return reminderListFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_reminder_list_thismonth, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewMonth);
        setupRecyclerView(recyclerView);
        return  view;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createReminderList());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private List<Reminder> createReminderList() {
        IReminder reminderObj = new ReminderHelper(getJSONObjectLocal());
        List<Reminder> itemList = reminderObj.getReminders("2", IReminder.CRITERIA.DATE);
        return itemList;
    }

    private JSONArray getJSONObjectLocal()
    {
        String fileName = "cache_reminders.json";
        String content = "[{\n" +
                "        \"id\": \"3\",\n" +
                "        \"title\": \"Arrange a car Loan\",\n" +
                "        \"imageId\": \"25\",\n" +
                "        \"group\": \"Personal\",\n" +
                "        \"status\": \"In progress\",\n" +
                "        \"recurring\": \"None\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"4\",\n" +
                "        \"title\": \"Arrange a house paint\",\n" +
                "        \"imageId\": \"25\",\n" +
                "        \"group\": \"Official\",\n" +
                "        \"status\": \"Scheduled\",\n" +
                "        \"recurring\": \"Monthly\" \n" +
                "    }]";
        try {
            FileOutputStream fos = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = null;
        FileInputStream fis = null;
        try {
            fis = getActivity().openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader streamReader = new BufferedReader(isr);

            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
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
    }

    /*private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();
        Bundle bundle = getArguments();
        if(bundle!=null) {
            int itemsCount = bundle.getInt(ITEMS_COUNT_KEY);
            for (int i = 0; i < itemsCount; i++) {
                itemList.add("Item " + i);
            }
        }
        return itemList;
    }*/
}
