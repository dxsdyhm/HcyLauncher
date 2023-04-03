package com.hcy.hcylauncher.comm;

import android.app.Application;
import android.content.Context;
import android.os.SystemProperties;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.hcy.hcylauncher.InatallActivity;

public class HcyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        int state = SystemProperties.getInt(Constans.PERSI, 0);
        if(state==0){
            ActivityUtils.startActivity(InatallActivity.class);
        }else {
            boolean comple = FileUtils.isFileExists(Constans.PATH_SETUP_FLAG);
            if (!comple) {
                AppUtils.launchApp(Constans.PACKAGE_SETUP);
            }
        }
    }
}
