package com.hcy.hcylauncher.utils;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.Utils;

import java.util.List;

public class LunchUtils {
    public static String getLauncherActivity(@NonNull final String pkg) {
        if (TextUtils.isEmpty(pkg)) return "";
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(pkg);
        PackageManager pm = Utils.getApp().getPackageManager();
        List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
        if (info == null || info.size() == 0) {
            intent.removeCategory(Intent.CATEGORY_LAUNCHER);
            intent.addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER);
            List<ResolveInfo> leaninfo = pm.queryIntentActivities(intent, 0);
            if(leaninfo == null || leaninfo.size() == 0){
                return "";
            }
            return leaninfo.get(0).activityInfo.name;
        }
        return info.get(0).activityInfo.name;
    }

    public static Intent getLaunchAppIntent(final String pkgName) {
        String launcherActivity = getLauncherActivity(pkgName);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClassName(pkgName, launcherActivity);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    public static void launchApp(final String packageName) {
        Intent launchAppIntent = getLaunchAppIntent(packageName);
        if (launchAppIntent == null) {
            Log.e("AppUtils", "Didn't exist launcher activity.");
            return;
        }
        Utils.getApp().startActivity(launchAppIntent);
    }
}
