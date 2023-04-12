package com.hcy.hcylauncher.entry;

import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.hcy.hcylauncher.comm.Constans;

public class AppItem {
    public static int TYPE_DEFAULT = 0;
    public static int TYPE_APPS = 1;
    public static int TYPE_ADD = 2;
    private String pakcgename;
    private String page;
    private int type = TYPE_DEFAULT;
    //主界面才有位置
    private int index;

    public int getType() {
        return type;
    }

    public AppItem(String pakcgename, int type) {
        this(pakcgename);
        this.type = type;
    }

    public AppItem(String pakcgename, int type,int indexMain) {
        this(pakcgename);
        this.type = type;
        this.index=indexMain;
    }

    public int getIndex(){
        return index;
    }

    public String getPage() {
        return page;
    }

    public String getPakcgename() {
        return pakcgename;
    }

    public void setPage(String page) {
        this.page = page;
    }

    private AppUtils.AppInfo appInfo;

    public AppItem(String pakcgename) {
        if (TextUtils.isEmpty(pakcgename)) {
            this.pakcgename = "";
            this.appInfo = null;
        } else {
            if (pakcgename.contains("|")) {
                String[] pks = pakcgename.split("\\|");
                if (pks.length >= 2) {
                    this.pakcgename = pks[0];
                    this.page = pks[1];
                } else if (pks.length == 1) {
                    this.pakcgename = pks[0];
                } else {
                    this.pakcgename = "";
                }
            } else {
                this.pakcgename = pakcgename;
                if(AppUtils.isAppInstalled(this.pakcgename)){
                    this.appInfo = AppUtils.getAppInfo(this.pakcgename);
                }
            }
        }
    }

    public AppUtils.AppInfo getAppInfo() {
        return appInfo;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof String) {
            return pakcgename.equals(obj);
        }
        return false;
    }

    public boolean isLegal() {
        if (TextUtils.isEmpty(pakcgename)) {
            return false;
        }

        if (!AppUtils.isAppInstalled(pakcgename)) {
            return false;
        }
        if (this.appInfo == null) {
            return false;
        }
        return true;
    }
}
