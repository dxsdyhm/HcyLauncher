package com.example.hcylauncher.utils;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.example.hcylauncher.comm.Constans;
import com.example.hcylauncher.entry.AppItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 快捷app布局
 * 获取与保存
 */
public class AppLayoutUtils {
    private static String TAG="AppLayoutUtils";


    public static List<AppItem> getMainDefault(){
        return getDefault("main");
    }

    public static List<AppItem> getAppsDefault(){
        return getDefault("apps");
    }

    public static List<AppItem> getDefault(String suffix){
        List<AppItem> items=new ArrayList<>();
        if(TextUtils.isEmpty(suffix)){
            return items;
        }
        String[] packs= FileIOUtils.readFile2String(Constans.PATH_CONFIG).split("\n");
        for(int i=0;i<packs.length;i++){
            if(packs[i].startsWith(suffix)){
                String[] apps=packs[i].replace(suffix+":","").split(";");
                for(int j=0;j<apps.length;j++){
                    AppItem appItem=new AppItem(apps[j]);
                    if(appItem.isLegal()){
                        items.add(appItem);
                    }else {
                        Log.e(TAG,"package info is not use :"+j);
                    }
                }
            }
        }
        return items;
    }
}
