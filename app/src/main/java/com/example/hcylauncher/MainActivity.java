package com.example.hcylauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.example.hcylauncher.adapter.CustomerItemAdapter;
import com.example.hcylauncher.adapter.HorizontalSpaceItemDecoration;
import com.example.hcylauncher.base.BaseActicity;
import com.example.hcylauncher.base.TitleDefaultActivity;
import com.example.hcylauncher.comm.SPkey;
import com.example.hcylauncher.entry.AppItem;
import com.example.hcylauncher.entry.DefaultLayApps;
import com.example.hcylauncher.utils.AppLayoutUtils;
import com.example.hcylauncher.view.CustomerAppRecyView;
import com.example.hcylauncher.view.ItemMainView;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储结构
 * key群 6个位置  value 是包名|启动页
 * key 快捷栏标志  value 一长串符合格式的包名
 *
 * 优先读取自己存储的数据，如果没有数据才去读取系统文件
 * 系统文件读过一次后不再读取
 */
public class MainActivity extends TitleDefaultActivity {

    private CustomerAppRecyView appRecyView;
    private CustomerItemAdapter adapter=new CustomerItemAdapter();

    private ItemMainView item1,item2,item3,item4,item5,item6;
    private DefaultLayApps apps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        apps=AppLayoutUtils.loadData();
        setBackGround();
        loadMainApps();
        loadCustomerApps();
    }

    private void initUi() {
        statusBarView=findViewById(R.id.ststus);
        timeDefaultView=findViewById(R.id.time);
        appRecyView=findViewById(R.id.rl_apps);

        item1=findViewById(R.id.item1);
        item2=findViewById(R.id.item2);
        item3=findViewById(R.id.item3);
        item4=findViewById(R.id.item4);
        item5=findViewById(R.id.item5);
        item6=findViewById(R.id.item6);

        ivClean=findViewById(R.id.iv_clean);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        appRecyView.setLayoutManager(layoutManager);
        appRecyView.addItemDecoration(new HorizontalSpaceItemDecoration(10));
        appRecyView.setAdapter(adapter);
    }

    private void loadMainApps(){
        item1.UpdateUi(new AppItem(apps.getApp1(),AppItem.TYPE_DEFAULT,1));
        item2.UpdateUi(new AppItem(apps.getApp2(),AppItem.TYPE_DEFAULT,2));
        item3.UpdateUi(new AppItem(apps.getApp3(),AppItem.TYPE_DEFAULT,3));
        item4.UpdateUi(new AppItem(apps.getApp4(),AppItem.TYPE_DEFAULT,4));
        item5.UpdateUi(new AppItem(apps.getApp5(),AppItem.TYPE_DEFAULT,5));
        item6.UpdateUi(new AppItem(apps.getApp6(),AppItem.TYPE_DEFAULT,6));


    }

    private void loadCustomerApps() {
        List<AppItem> items= new ArrayList<>();
        for(String pack:apps.getApps()){
            items.add(new AppItem(pack));
        }
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