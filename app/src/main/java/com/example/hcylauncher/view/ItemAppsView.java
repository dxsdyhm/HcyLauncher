package com.example.hcylauncher.view;

import android.content.Context;
import android.util.AttributeSet;

public class ItemAppsView extends ScalRelativelayout{

    public ItemAppsView(Context context) {
        this(context,null);
    }

    public ItemAppsView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ItemAppsView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public ItemAppsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
