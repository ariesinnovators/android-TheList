package com.varun.android.listview.holders;

/**
 * Created by VarunSasidharan_Nair on 2/08/2017.
 */
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.varun.android.listview.R;
import com.varun.android.listview.app.AppController;
import com.varun.android.listview.model.Reminder;


public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView mTitle;
    private final TextView mGroup;
    private final TextView mStatus;
    private final TextView mRecurringType;
    private final TextView mListId;
    private final ImageView mThumbnail;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    public RecyclerItemViewHolder(final View parent,
                                  TextView mTitle,
                                  TextView mGroup,
                                  TextView mStatus,
                                  TextView mRecurringType, TextView mListId, ImageView mThumbnail
                                  ) {
        super(parent);
        this.mTitle = mTitle;
        this.mGroup = mGroup;
        this.mStatus = mStatus;
        this.mRecurringType = mRecurringType;
        this.mListId = mListId;
        this.mThumbnail = mThumbnail;
    }

    public static RecyclerItemViewHolder newInstance(View parent) {
        //TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
        TextView lmTitle = (TextView) parent.findViewById(R.id.title);
        TextView lmGroup = (TextView) parent.findViewById(R.id.group);
        TextView lmStatus = (TextView) parent.findViewById(R.id.status);
        TextView lmRecurringType = (TextView) parent.findViewById(R.id.recurringType);
        TextView lmListId = (TextView) parent.findViewById(R.id.listid);
        ImageView lmThumbnail = (ImageView) parent.findViewById(R.id.thumbnail);

        return new RecyclerItemViewHolder(parent, lmTitle, lmGroup, lmStatus, lmRecurringType, lmListId,lmThumbnail );
    }

    public void setItemDisplay(Reminder reminder) {
        mTitle.setText(reminder.getTitle());
        mGroup.setText(reminder.getGroup());
        mRecurringType.setText(reminder.getRecurringType());
        mListId.setText(reminder.getId());
        mStatus.setText(reminder.getStatus());
/*        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        mThumbnail.setImageUrl(reminder.getThumbnailUrl(), imageLoader);*/

        mThumbnail.setImageResource(AppController.getInstance().getImageId(reminder.getThumbnailUrl(),
                "mipmap"));
    }
}