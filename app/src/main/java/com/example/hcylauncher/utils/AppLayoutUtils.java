package com.example.hcylauncher.utils;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.JsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.example.hcylauncher.comm.Constans;
import com.example.hcylauncher.comm.SPkey;
import com.example.hcylauncher.entry.AppItem;
import com.example.hcylauncher.entry.DefaultLayApps;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 快捷app布局
 * 获取与保存
 */
public class AppLayoutUtils {
    private static String DEFAULT_APPS_EMPTY="{\"app1\":\"\",\"app2\":\"\",\"app3\":\"\",\"app4\":\"\",\"app5\":\"\",\"app6\":\"\",\"apps\":[]}";
    private static String TAG="AppLayoutUtils";

    public static DefaultLayApps loadData(){
        String apps=SPUtils.getInstance().getString(SPkey.KEY_APP_LAY,SPkey.KEY_APP_LAY_DEFAULT).trim();
        if(TextUtils.isEmpty(apps)){
            //没有添加过任何配置,读取并使用系统配置
            apps= FileIOUtils.readFile2String(Constans.PATH_CONFIG);
        }
        DefaultLayApps defaultLayApps;
        try {
            if(TextUtils.isEmpty(apps)){
                throw new Exception();
            }
            defaultLayApps=GsonUtils.fromJson(apps,DefaultLayApps.class);
        } catch (Exception e) {
            Log.e(TAG,"default config is errot :"+apps);
            defaultLayApps=GsonUtils.fromJson(DEFAULT_APPS_EMPTY,DefaultLayApps.class);
        }
        //如果这段字符串不符合要求，将会导入全空配置
        SPUtils.getInstance().put(SPkey.KEY_APP_LAY,GsonUtils.toJson(defaultLayApps));
        //加载数据到app
        return defaultLayApps;
    }

    public static void saveData(DefaultLayApps defaultLayApps){
        if(defaultLayApps==null){
            return;
        }
        SPUtils.getInstance().put(SPkey.KEY_APP_LAY,GsonUtils.toJson(defaultLayApps));
        //ShellUtils.execCmd("sync",false);
    }
}
