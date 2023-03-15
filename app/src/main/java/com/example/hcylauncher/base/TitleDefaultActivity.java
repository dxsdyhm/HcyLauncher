package com.example.hcylauncher.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.hcylauncher.R;
import com.example.hcylauncher.view.StatusBarView;
import com.example.hcylauncher.view.TimeDefaultView;

/**
 * 处理布局状态栏上的状态更新
 * 1.外设接口状态变更
 * 2.时间更新
 */
public class TitleDefaultActivity extends BaseActicity{
    private static String TAG="TitleDefaultActivity";

    private TitleBroadReceviver receviver=new TitleBroadReceviver();
    public StatusBarView statusBarView;
    public TimeDefaultView timeDefaultView;

    @Override
    protected void onResume() {
        super.onResume();
        registBroadCast();
        updateDate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receviver);
    }

    private void registBroadCast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_MEDIA_EJECT);
        filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        filter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
        filter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
        registerReceiver(receviver,filter);
    }

    private class TitleBroadReceviver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            Log.e(TAG,"action:"+action);
            if(Intent.ACTION_TIME_TICK.equals(action)||Intent.ACTION_TIME_CHANGED.equals(action)){
                updateDate();
            }
        }
    }

    /**
     * 更新状态栏时间
     */
    public void updateDate(){
        if(timeDefaultView!=null){
            timeDefaultView.UpdateTime();
        }else {
            Log.e(TAG,"timeDefaultView is null");
        }
    }
}
