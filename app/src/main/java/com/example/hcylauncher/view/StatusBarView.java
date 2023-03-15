package com.example.hcylauncher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hcylauncher.R;

public class StatusBarView extends LinearLayout {
    public static int STATE_ON = 1;
    public static int STATE_OFF = 0;
    public static int INDEX_ETHERNET = 0;
    public static int INDEX_WIFI = 1;
    public static int INDEX_BLUTOOTH = 2;
    public static int INDEX_USB = 3;
    public static int INDEX_SDCARD = 4;

    private ImageView erth,wifi,bluetooth,usb,sdcard;

    public StatusBarView(Context context) {
        this(context, null);
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public StatusBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.status_bar_default, this, true);
        initUI();
    }

    private void initUI() {
        erth=findViewById(R.id.img_ethernet);
        wifi=findViewById(R.id.img_wifi);
        bluetooth=findViewById(R.id.img_blutooth);
        usb=findViewById(R.id.img_usb);
        sdcard=findViewById(R.id.img_sdcard);
    }

    public void setIndexState(int index,int state){
        if(index==INDEX_BLUTOOTH){
            bluetooth.setVisibility(state==STATE_ON?VISIBLE:GONE);
        }else if(index==INDEX_ETHERNET){
            erth.setVisibility(state==STATE_ON?VISIBLE:GONE);
        }else if(index==INDEX_WIFI){
            wifi.setVisibility(state==STATE_ON?VISIBLE:GONE);
        }else if(index==INDEX_USB){
            usb.setVisibility(state==STATE_ON?VISIBLE:GONE);
        }else if(index==INDEX_SDCARD){
            sdcard.setVisibility(state==STATE_ON?VISIBLE:GONE);
        }
    }
}
