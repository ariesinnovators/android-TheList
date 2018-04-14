package com.varun.android.listview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.varun.android.listview.R;
import com.varun.android.listview.fragments.ReminderListFragmentThisDay;
import com.varun.android.listview.holders.RecyclerItemViewHolder;
import com.varun.android.listview.model.Reminder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VarunSasidharan_Nair on 14/08/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Reminder> reminderwModelList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<Reminder> borrowModelList, View.OnLongClickListener longClickListener) {
        this.reminderwModelList = borrowModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Reminder reminder = reminderwModelList.get(position);
        holder.itemTextView.setText(reminder.getTitle());
        holder.nameTextView.setText(reminder.getGroup());
        holder.dateTextView.setText(reminder.getStatus());
        holder.itemView.setTag(reminder);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return reminderwModelList.size();
    }

    public void addItems(List<Reminder> reminderwModelList) {
        this.reminderwModelList = reminderwModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;

        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = (TextView) view.findViewById(R.id.title  );
            nameTextView = (TextView) view.findViewById(R.id.group);
            dateTextView = (TextView) view.findViewById(R.id.status);
        }
    }
}
