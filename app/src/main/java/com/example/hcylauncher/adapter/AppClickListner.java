package com.example.hcylauncher.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.example.hcylauncher.CustomAppsActivity;
import com.example.hcylauncher.comm.Constans;
import com.example.hcylauncher.entry.AppItem;

public class AppClickListner implements View.OnClickListener {
    private static String TAG = "AppClickListner";
    private AppItem appItem;

    public AppClickListner(AppItem appItem) {
        this.appItem = appItem;
    }

    @Override
    public void onClick(View view) {
        if(appItem!=null){
            if(TextUtils.isEmpty(appItem.getPage())){
                //直接启动
                if(TextUtils.isEmpty(appItem.getPakcgename())){
                    toReplace(view);
                }else {
                    AppUtils.launchApp(appItem.getPakcgename());
                }
            }else {
                //启动指定页面
                if(CustomAppsActivity.class.getName().equals(appItem.getPage())){
                    toAdd(view);
                }else {
                    ActivityUtils.startActivity(appItem.getPakcgename(),appItem.getPage());
                }
            }
        }else {
            Log.e(TAG,"click null obj");
            toReplace(view);
        }
    }

    private void toReplace(View view){
        Log.e(TAG,"toReplace");
        Intent intent=new Intent();
        intent.setClassName(AppUtils.getAppPackageName(),CustomAppsActivity.class.getName());
        intent.putExtra(Constans.BUND_ACTIVITY_FUN,CustomAppsActivity.FUNCTION_REPLACE);
        intent.putExtra(Constans.BUND_REPLACE_INDEX,appItem.getIndex());
        view.getContext().startActivity(intent);
    }

    public static void toReplace(AppItem appItem,View view){
        Log.e(TAG,"toReplace");
        Intent intent=new Intent();
        intent.setClassName(AppUtils.getAppPackageName(),CustomAppsActivity.class.getName());
        intent.putExtra(Constans.BUND_ACTIVITY_FUN,CustomAppsActivity.FUNCTION_REPLACE);
        intent.putExtra(Constans.BUND_REPLACE_INDEX,appItem.getIndex());
        view.getContext().startActivity(intent);
    }

    private void toAdd(View view){
        Log.e(TAG,"toAdd");
        Intent intent=new Intent();
        intent.setClassName(AppUtils.getAppPackageName(),CustomAppsActivity.class.getName());
        intent.putExtra(Constans.BUND_ACTIVITY_FUN,CustomAppsActivity.FUNCTION_SELECT);
        view.getContext().startActivity(intent);
    }
}
