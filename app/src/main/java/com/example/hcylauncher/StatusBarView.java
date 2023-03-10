package com.example.hcylauncher;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class StatusBarView extends LinearLayout {
    public StatusBarView(Context context) {
        this(context,null);
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public StatusBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.status_bar_default, this, true);
    }
}
