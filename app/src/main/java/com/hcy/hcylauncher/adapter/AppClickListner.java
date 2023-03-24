package com.hcy.hcylauncher.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.hcy.hcylauncher.AppsActivity;
import com.hcy.hcylauncher.CustomAppsActivity;
import com.hcy.hcylauncher.comm.Constans;
import com.hcy.hcylauncher.entry.AppItem;

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
                    toSelecFuncton(view,CustomAppsActivity.FUNCTION_REPLACE);
                }else {
                    if(!appItem.isLegal()){
                        toSelecFuncton(view,CustomAppsActivity.FUNCTION_REPLACE);
                    }else {
                        AppUtils.launchApp(appItem.getPakcgename());
                    }
                }
            }else {
                //启动指定页面
                if(CustomAppsActivity.class.getName().equals(appItem.getPage())){
                    toSelecFuncton(view,CustomAppsActivity.FUNCTION_SELECT);
                } else if (AppsActivity.class.getName().equals(appItem.getPage())) {
                    toSelecFuncton(view,CustomAppsActivity.FUNCTION_SHOW);
                } else {
                    if(!appItem.isLegal()){
                        toSelecFuncton(view,CustomAppsActivity.FUNCTION_REPLACE);
                    }else {
                        ActivityUtils.startActivity(appItem.getPakcgename(),appItem.getPage());
                    }
                }
            }
        }else {
            Log.e(TAG,"click null obj");
            toSelecFuncton(view,CustomAppsActivity.FUNCTION_REPLACE);
        }
    }

    public static void toReplace(AppItem appItem,View view){
        Log.e(TAG,"toReplace");
        Intent intent=new Intent();
        intent.setClassName(AppUtils.getAppPackageName(),CustomAppsActivity.class.getName());
        intent.putExtra(Constans.BUND_ACTIVITY_FUN,CustomAppsActivity.FUNCTION_REPLACE);
        intent.putExtra(Constans.BUND_REPLACE_INDEX,appItem.getIndex());
        view.getContext().startActivity(intent);
    }

    private void toSelecFuncton(View view,int function){
        Intent intent=new Intent();
        intent.putExtra(Constans.BUND_ACTIVITY_FUN,function);
        if(function==CustomAppsActivity.FUNCTION_SELECT){
            intent.setClassName(AppUtils.getAppPackageName(),CustomAppsActivity.class.getName());
        } else if (function==CustomAppsActivity.FUNCTION_REPLACE) {
            intent.putExtra(Constans.BUND_REPLACE_INDEX,appItem.getIndex());
            intent.setClassName(AppUtils.getAppPackageName(),CustomAppsActivity.class.getName());
        } else {
            intent.setClassName(AppUtils.getAppPackageName(),AppsActivity.class.getName());
        }
        view.getContext().startActivity(intent);
    }
}
