package com.varun.android.listview.activities;


import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.varun.android.listview.R;
import com.varun.android.listview.app.AppController;
import com.varun.android.listview.data.AppDatabase;
import com.varun.android.listview.model.Reminder;

public class AddReminderActivity extends AppCompatActivity {
    static EditText DateEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeBlue);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder_table_layout);

        initToolbar();

        DateEdit = (EditText) findViewById(R.id.fg_reminder_et_select_date);
        TextInputLayout calendarInputLayout = (TextInputLayout)  findViewById(R.id.fg_reminder_til_select_date);
        /*ImageButton fg_reminder_img_btn_select_date = (ImageButton) findViewById(R.id.fg_reminder_img_btn_select_date);*/
        DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*showTruitonTimePickerDialog(v);*/
                showTruitonDatePickerDialog(v);
            }
        });

        final Spinner recurringInput = (Spinner) findViewById(R.id.fg_reminder_spn_recurring_type);
        final Spinner categoryInput = (Spinner) findViewById(R.id.fg_reminder_spn_category);
        final EditText reminderTextInput = (EditText) findViewById(R.id.fg_reminder_et_reminder_title);

        FloatingActionButton saveReminderButton = (FloatingActionButton) findViewById(R.id.fg_reminder_fab_save_reminder);
        saveReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Reminder reminder = Reminder.builder().setDueDate(DateEdit.getText().toString()
                    ).setGroup(categoryInput.getSelectedItem().toString()).setRecurringType(
                            recurringInput.getSelectedItem().toString()).setStatus(
                                    "Scheduled").setTitle(reminderTextInput.getText().toString()).build();

                new DatabaseAsync().execute(reminder);
                /*final AppDatabase appDatabase=AppDatabase.getDatabase(getApplicationContext());*/
                // run the sentence in a new thread
/*                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        appDatabase.reminderModel().addReminder(reminder);
                        Reminder reminder = appDatabase.reminderModel().getReminder(123);
                        List<Reminder> reminders = appDatabase.reminderModel().getAllReminders();
                        String myvar = reminder.getTitle();
                    }
                }).start();*/

               /* new AsyncTask<Reminder,Void,Void>(){
                    @Override
                    protected Void doInBackground(Reminder... params) {
                        appDatabase.reminderModel().addReminder(reminder);
                        return null;
                    }
                }.execute();*/



            }
        });
    }

    private class DatabaseAsync extends AsyncTask<Reminder, Void, Void>{

        @Override
        protected Void doInBackground(Reminder... reminder) {
            final AppDatabase appDatabase=AppDatabase.getDatabase(getApplicationContext());
            appDatabase.reminderModel().addReminder(reminder[0]);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Perform pre-adding operation here.
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //To after addition operation here.
        }
    }



    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateEdit.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public void showTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            DateEdit.setText(DateEdit.getText() + " -" + hourOfDay + ":" + minute);
        }
    }
}