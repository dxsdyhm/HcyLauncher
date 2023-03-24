package com.hcy.hcylauncher.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.blankj.utilcode.util.ThreadUtils;
import com.hcy.hcylauncher.utils.AppInstallUtils;

public class ChangeAppsReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("dxsTest","onReceive:"+intent.getAction());
        AppInstallUtils.UpdateApps(context);
    }
}
