package com.example.hcylauncher.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

public class AppInstallUtils {
    public static List<ResolveInfo> getAllApps(Context context) {
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

    public static List<ResolveInfo> getAllTVApps(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER);
        List<ResolveInfo> apps = packageManager.queryIntentActivities(intent, 0);
        return apps;
    }
}
