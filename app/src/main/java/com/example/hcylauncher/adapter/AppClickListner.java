package com.example.hcylauncher.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.example.hcylauncher.CustomAppsActivity;
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
                    ActivityUtils.startActivity(CustomAppsActivity.class);
                }else {
                    AppUtils.launchApp(appItem.getPakcgename());
                }
            }else {
                //启动指定页面
//                Intent intent=IntentUtils.getLaunchAppIntent(appItem.getPakcgename());
//                intent.setClassName(appItem.getPakcgename(),appItem.getPage());
//                ActivityUtils.startActivity(intent);
                ActivityUtils.startActivity(appItem.getPakcgename(),appItem.getPage());
            }
        }else {
            Log.e(TAG,"click null obj");
            ActivityUtils.startActivity(CustomAppsActivity.class);
            //去选择一个
        }
    }
}
