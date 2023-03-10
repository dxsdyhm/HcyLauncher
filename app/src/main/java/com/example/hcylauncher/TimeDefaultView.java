package com.example.hcylauncher;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeDefaultView extends LinearLayout {
    private TextView txTime,txDate;
    public TimeDefaultView(Context context) {
        this(context,null);
    }

    public TimeDefaultView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimeDefaultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public TimeDefaultView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.time_view_default, this, true);
        initUI();
    }

    private void initUI() {
        txTime=findViewById(R.id.tx_time);
        txDate=findViewById(R.id.tx_date);
        txDate.setText(TimeUtils.getNowString());

        Date currentTime = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.AM_PM_FIELD, Locale.getDefault());
        String formattedTime = dateFormat.format(currentTime);
        Log.e("dxsTest","formattedTime:"+formattedTime);
    }

    public void UpdateTime(){
        initUI();
    }
}
