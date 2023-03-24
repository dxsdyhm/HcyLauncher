package com.hcy.hcylauncher;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.hcy.hcylauncher.adapter.CustomerItemAdapter;
import com.hcy.hcylauncher.adapter.HorizontalSpaceItemDecoration;
import com.hcy.hcylauncher.base.TitleDefaultActivity;
import com.hcy.hcylauncher.comm.Constans;
import com.hcy.hcylauncher.entry.AppItem;
import com.hcy.hcylauncher.entry.DefaultLayApps;
import com.hcy.hcylauncher.utils.AppInstallUtils;
import com.hcy.hcylauncher.utils.AppLayoutUtils;
import com.hcy.hcylauncher.view.CustomerAppRecyView;
import com.hcy.hcylauncher.view.ItemMainView;
import com.hcy.hcylauncher.view.StatusBarView;
import  com.hcy.hcylauncher.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 存储结构
 * key群 6个位置  value 是包名|启动页
 * key 快捷栏标志  value 一长串符合格式的包名
 * <p>
 * 优先读取自己存储的数据，如果没有数据才去读取系统文件
 * 系统文件读过一次后不再读取
 */
public class MainActivity extends TitleDefaultActivity {

    private CustomerAppRecyView appRecyView;
    private CustomerItemAdapter adapter = new CustomerItemAdapter();

    private ItemMainView item1, item2, item3, item4, item5, item6;
    private DefaultLayApps apps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        AppInstallUtils.UpdateApps(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        apps = AppLayoutUtils.loadData();
        setBackGround();
        updateUi();
    }

    private void updateUi() {
        loadMainApps();
        loadCustomerApps();
    }

    private void initUi() {
        statusBarView = findViewById(R.id.ststus);
        timeDefaultView = findViewById(R.id.time);
        appRecyView = findViewById(R.id.rl_apps);

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);
        item6 = findViewById(R.id.item6);

        ivClean = findViewById(R.id.iv_clean);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        appRecyView.setLayoutManager(layoutManager);
        appRecyView.addItemDecoration(new HorizontalSpaceItemDecoration(10));
        appRecyView.setAdapter(adapter);

        if (ViewTest) {
            for (int i = 0; i < 5; i++) {
                statusBarView.setIndexState(i, StatusBarView.STATE_ON);
            }
            ivClean.setVisibility(View.VISIBLE);
            ThreadUtils.runOnUiThreadDelayed(new Runnable() {
                @Override
                public void run() {
                    View v = findViewById(R.id.test);
                    v.setDrawingCacheEnabled(true);
                    ImageUtils.save(v.getDrawingCache(), PathUtils.getInternalAppDataPath() + "/test.png", Bitmap.CompressFormat.PNG);
                }
            }, 7000);
        }
        item1.requestFocus();
    }

    private void loadMainApps() {
        item1.UpdateUi(new AppItem(apps.getApp1(), AppItem.TYPE_DEFAULT, 1));
        item2.UpdateUi(new AppItem(apps.getApp2(), AppItem.TYPE_DEFAULT, 2));
        item3.UpdateUi(new AppItem(apps.getApp3(), AppItem.TYPE_DEFAULT, 3));
        item4.UpdateUi(new AppItem(apps.getApp4(), AppItem.TYPE_DEFAULT, 4));
        item5.UpdateUi(new AppItem(apps.getApp5(), AppItem.TYPE_DEFAULT, 5));
        item6.UpdateUi(new AppItem(apps.getApp6(), AppItem.TYPE_DEFAULT, 6));
    }

    private void loadCustomerApps() {
        List<AppItem> items = new ArrayList<>();
        for (String pack : apps.getApps()) {
            AppItem item = new AppItem(pack);
            if (!item.isLegal()) {
                Log.e("dxsTest", "wait for install");
            } else {
                items.add(item);
            }
        }
        addFootBoot(items);
        adapter.UpdateData(items);
    }

    private void addFootBoot(List<AppItem> items) {
        items.add(0, new AppItem(AppUtils.getAppPackageName() + "|" + AppsActivity.class.getName(), AppItem.TYPE_APPS));
        items.add(new AppItem(AppUtils.getAppPackageName() + "|" + CustomAppsActivity.class.getName(), AppItem.TYPE_ADD));
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

    @Override
    public boolean updateCustomerAdd(String pkg) {
        loadMainApps();
        loadCustomerApps();
        return true;
    }

    @Override
    public boolean updateCustomer(String pkg) {
        loadMainApps();
        loadCustomerApps();
        return true;
    }

    @Override
    public boolean updateCustomerRemovie(String pkg) {
        if (TextUtils.isEmpty(pkg) || apps == null) {
            return false;
        }
        apps.removeApp(pkg);
        loadMainApps();
        loadCustomerApps();
        return true;
    }
}