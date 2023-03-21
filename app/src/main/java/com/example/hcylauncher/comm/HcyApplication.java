package com.example.hcylauncher.comm;

import android.app.Application;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;

public class HcyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        boolean comple=FileUtils.isFileExists(Constans.PATH_SETUP_FLAG);
        if(!comple){
            AppUtils.launchApp(Constans.PACKAGE_SETUP);
        }
    }
}
