package com.varun.android.listview.fragments;


import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.varun.android.listview.R;
import com.varun.android.listview.adapter.RecyclerAdapter;
import com.varun.android.listview.adapter.RecyclerViewAdapter;
import com.varun.android.listview.data.JsonHandler;
import com.varun.android.listview.helpers.ReminderHelper;
import com.varun.android.listview.interfaces.IReminder;
import com.varun.android.listview.model.Reminder;
import com.varun.android.listview.view.ReminderListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderListFragmentThisDay extends Fragment implements LifecycleRegistryOwner, View.OnLongClickListener {

    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private RecyclerViewAdapter recyclerViewAdapter;
    private ReminderListViewModel viewModel;

    public final static String ITEMS_COUNT_KEY = "ReminderListFragmentThisDay$ItemsCount";
    public ReminderListFragmentThisDay() {
        // Required empty public constructor
    }

    public static ReminderListFragmentThisDay createInstance(int itemsCount) {
        ReminderListFragmentThisDay reminderListFragmentThisDay = new ReminderListFragmentThisDay();
        Bundle bundle = new Bundle();
        bundle.putInt(ITEMS_COUNT_KEY, itemsCount);
        reminderListFragmentThisDay.setArguments(bundle);
        return reminderListFragmentThisDay;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_reminder_list_thisday, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        setupRecyclerLiveView(recyclerView);
        return  view;
    }

    private void setupRecyclerLiveView(RecyclerView recyclerView)
    {
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<Reminder>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(getActivity()).get(ReminderListViewModel.class);
        viewModel.getItemReminderList().observe(ReminderListFragmentThisDay.this, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(@Nullable List<Reminder> reminders) {
                recyclerViewAdapter.addItems(reminders);
            }
        });

    }

  /*  private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createReminderList());
        recyclerView.setAdapter(recyclerAdapter);
    }*/

/*    private List<String> createItemList() {
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



/*    private List<Reminder> createReminderList() {
        IReminder reminderObj = new ReminderHelper(JsonHandler.getJSONObjectLocal());
        List<Reminder> itemList = reminderObj.getReminders("2", IReminder.CRITERIA.DATE);
        return itemList;
    }*/

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public boolean onLongClick(View v) {
        Reminder reminder = (Reminder) v.getTag();
        viewModel.deleteItem(reminder);
        return true;
    }

    /*private JSONArray getJSONObjectLocal()
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
    }*/
}
