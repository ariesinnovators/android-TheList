package com.varun.android.listview.activities;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.varun.android.listview.R;
import com.varun.android.listview.fragments.ReminderListFragmentThisDay;
import com.varun.android.listview.fragments.ReminderListFragmentThisMonth;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int ADD_REMINDER = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeBlue);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initViewPagerAndTabs();

        final FloatingActionButton addReminder = (FloatingActionButton) findViewById(R.id.fabButton);
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(),AddReminderActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void initViewPagerAndTabs() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(ReminderListFragmentThisDay.createInstance(20), getString(R.string.this_day));
        pagerAdapter.addFragment(ReminderListFragmentThisMonth.createInstance(4), getString(R.string.this_month));
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }



    static class PagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
