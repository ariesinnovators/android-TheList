package com.varun.android.listview.util;

/**
 * Created by VarunSasidharan_Nair on 2/08/2017.
 */

import android.content.Context;
import android.content.res.TypedArray;

import com.varun.android.listview.R;

public class Utils {

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static int getTabsHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
    }
}