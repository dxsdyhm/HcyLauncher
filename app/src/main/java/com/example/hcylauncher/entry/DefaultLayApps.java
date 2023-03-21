package com.example.hcylauncher.entry;

import android.text.TextUtils;

import com.example.hcylauncher.adapter.AppClickListner;
import com.example.hcylauncher.comm.Constans;
import com.example.hcylauncher.utils.AppLayoutUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultLayApps {
    private String app1="";
    private String app2="";
    private String app3="";
    private String app4="";
    private String app5="";
    private String app6="";
    private String clean="";
    private String[] apps;

    public String getApp1() {
        return app1;
    }

    public void setApp1(String app1) {
        this.app1 = app1;
    }

    public String getApp2() {
        return app2;
    }

    public void setApp2(String app2) {
        this.app2 = app2;
    }

    public String getApp3() {
        return app3;
    }

    public void setApp3(String app3) {
        this.app3 = app3;
    }

    public String getApp4() {
        return app4;
    }

    public String getClean() {
        return clean;
    }

    public void setClean(String clean) {
        this.clean = clean;
    }

    public void setApp4(String app4) {
        this.app4 = app4;
    }

    public String getApp5() {
        return app5;
    }

    public void setApp5(String app5) {
        this.app5 = app5;
    }

    public String getApp6() {
        return app6;
    }

    public void setApp6(String app6) {
        this.app6 = app6;
    }

    public String[] getApps() {
        return apps;
    }

    public void setApps(String[] apps) {
        this.apps = apps;
    }

    public void addApp(String pks){
        try {
            Set<String> allpack=new HashSet<>();
            //先点的加在前面
            for (String s:this.apps){
                allpack.add(s);
            }
            allpack.add(pks);
            String[] temp=new String[allpack.size()];
            allpack.toArray(temp);
            this.apps=temp;
            AppLayoutUtils.saveData(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removeApp(String pks){
        try {
            if(TextUtils.isEmpty(pks)){
                if(pks.equals(app1)){
                    app1="";
                }
                if(pks.equals(app2)){
                    app2="";
                }
                if(pks.equals(app3)){
                    app3="";
                }
                if(pks.equals(app4)){
                    app4="";
                }
                if(pks.equals(app5)){
                    app5="";
                }
                if(pks.equals(app6)){
                    app6="";
                }
            }

            Set<String> allpack=new HashSet<>();
            for (String s:this.apps){
                allpack.add(s);
            }
            allpack.remove(pks);
            String[] temp=new String[allpack.size()];
            allpack.toArray(temp);
            this.apps=temp;
            AppLayoutUtils.saveData(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasApp(String pks){
        if(TextUtils.isEmpty(pks)){
            return false;
        }
        for(String s:apps){
            if(pks.equals(s)){
                return true;
            }
        }
        return false;
    }

    public void replaceItem(int index, String pack){
        switch (index){
            case 1: this.app1=pack;break;
            case 2: this.app2=pack;break;
            case 3: this.app3=pack;break;
            case 4: this.app4=pack;break;
            case 5: this.app5=pack;break;
            case 6: this.app6=pack;break;
            default:
                break;
        }
        AppLayoutUtils.saveData(this);
    }
}
