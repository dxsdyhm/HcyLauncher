package com.example.hcylauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.example.hcylauncher.adapter.CustomerItemAdapter;
import com.example.hcylauncher.adapter.HorizontalSpaceItemDecoration;
import com.example.hcylauncher.base.BaseActicity;
import com.example.hcylauncher.base.TitleDefaultActivity;
import com.example.hcylauncher.entry.AppItem;
import com.example.hcylauncher.utils.AppLayoutUtils;
import com.example.hcylauncher.view.CustomerAppRecyView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TitleDefaultActivity {

    private CustomerAppRecyView appRecyView;
    private CustomerItemAdapter adapter=new CustomerItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setBackGround();
    }

    private void initUi() {
        statusBarView=findViewById(R.id.ststus);
        timeDefaultView=findViewById(R.id.time);
        appRecyView=findViewById(R.id.rl_apps);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        appRecyView.setLayoutManager(layoutManager);
        appRecyView.addItemDecoration(new HorizontalSpaceItemDecoration(10));
        appRecyView.setAdapter(adapter);

        fakeData();
    }

    private void fakeData() {
        List<AppItem> items= AppLayoutUtils.getAppsDefault();
        addFootBoot(items);
        adapter.UpdateData(items);
    }

    private void addFootBoot(List<AppItem> items){
        items.add(0,new AppItem(AppUtils.getAppPackageName()+"|"+AppsActivity.class.getName(),AppItem.TYPE_APPS));
        items.add(new AppItem(AppUtils.getAppPackageName()+"|"+CustomAppsActivity.class.getName(),AppItem.TYPE_ADD));
    }

    private void setBackGround() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {

                    @Override
                    public void onGranted() {
                        @SuppressLint("MissingPermission") Drawable wallpaperDrawable = wallpaperManager.getDrawable();
                        getWindow().setBackgroundDrawable(wallpaperDrawable);
                    }

                    @Override
                    public void onDenied() {
                        Log.e("dxsTest", "STORAGE onDenied");
                    }
                }).request();
    }
}