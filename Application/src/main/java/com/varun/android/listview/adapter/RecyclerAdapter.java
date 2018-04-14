package com.varun.android.listview.adapter;

/**
 * Created by VarunSasidharan_Nair on 2/08/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.varun.android.listview.R;
import com.varun.android.listview.holders.RecyclerItemViewHolder;
import com.varun.android.listview.model.Reminder;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mItemList;
    private List<Reminder> reminderList;

//    public RecyclerAdapter(List<String> itemList) {
//        mItemList = itemList;
//    }

    public RecyclerAdapter(List<Reminder> reminderList) {
        this.reminderList = reminderList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return RecyclerItemViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
        Reminder reminder = reminderList.get(position);
        holder.setItemDisplay(reminder);

    }

    @Override
    public int getItemCount() {
        return reminderList == null ? 0 : reminderList.size();
    }

}

