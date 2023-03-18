package com.example.hcylauncher.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.example.hcylauncher.AppsActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppInstallUtils {
    private static AppInstallUtils instance;
    private List<ResolveInfo> apps;
    private AppInstallUtils(Context context) {
        apps = getAllApps(context);
    }

    // TODO: 2023/3/15 内存泄漏风险
    public static AppInstallUtils getInstance(Context context) {
        if (instance == null) {
            instance = new AppInstallUtils(context);
        }
        return instance;
    }

    public List<ResolveInfo> getAllApps(){
        return apps;
    }

    private List<ResolveInfo> getAllApps(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = packageManager.queryIntentActivities(intent, 0);
        //去重
        List<ResolveInfo> appsTv = getAllTVApps(context);
        Iterator<ResolveInfo> iteratorlauncher = apps.iterator();
        while (iteratorlauncher.hasNext()) {
            ResolveInfo infolauncher = iteratorlauncher.next();
            Iterator<ResolveInfo> iteratorleanback = appsTv.iterator();
            while (iteratorleanback.hasNext()) {
                ResolveInfo infoleanback = iteratorleanback.next();
                if (infolauncher.activityInfo.packageName.equals(infoleanback.activityInfo.packageName)) {
                    iteratorleanback.remove();
                }
            }
        }
        apps.addAll(appsTv);
        for (ResolveInfo app : apps) {
            Log.d("APP", app.activityInfo.packageName);
        }
        return apps;
    }

    private List<ResolveInfo> getAllTVApps(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER);
        List<ResolveInfo> apps = packageManager.queryIntentActivities(intent, 0);
        return apps;
    }

    public static void UpdateApps(Context context){
        if(instance==null){
            getInstance(context.getApplicationContext());
        }
        ThreadUtils.executeBySingle(new ThreadUtils.SimpleTask<Object>() {
            @Override
            public Object doInBackground() throws Throwable {
                //todo 注意异步问题
                instance.apps=instance.getAllApps(context.getApplicationContext());
                return null;
            }

            @Override
            public void onSuccess(Object result) {
                Intent intent=new Intent(AppsActivity.HCY_ACTION_UPDATEAPP);
                LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(intent);
            }
        });
    }
}
