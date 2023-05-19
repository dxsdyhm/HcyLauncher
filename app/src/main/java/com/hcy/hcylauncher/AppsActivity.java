package com.hcy.hcylauncher;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.hardware.usb.UsbManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.hcy.hcylauncher.adapter.AppsSelectAdapter;
import com.hcy.hcylauncher.adapter.SpaceItemDecoration;
import com.hcy.hcylauncher.base.TitleDefaultActivity;
import com.hcy.hcylauncher.comm.Constans;
import com.hcy.hcylauncher.entry.AppItem;
import com.hcy.hcylauncher.utils.AppInstallUtils;
import com.hcy.hcylauncher.view.StatusBarView;
import  com.hcy.hcylauncher.R;

import java.util.ArrayList;
import java.util.List;

public class AppsActivity extends TitleDefaultActivity {
    public static String HCY_ACTION_UPDATEAPP="HCY_ACTION_UPDATEAPP";
    private RecyclerView appRecyView;
    private PackageChange change=new PackageChange();
    private AppsSelectAdapter adapter = new AppsSelectAdapter(CustomAppsActivity.FUNCTION_SHOW);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        setBackColor();
        initUi();
    }
    private void setBackColor() {
        try {
            String color= SystemProperties.get(Constans.PRE_COLOR, "");
            findViewById(R.id.ll_bg).setBackgroundColor(ColorUtils.string2Int(color));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUi() {
        statusBarView=findViewById(R.id.ststus);
        timeDefaultView=findViewById(R.id.time);
        ivClean=findViewById(R.id.iv_clean);
        appRecyView = findViewById(R.id.rl_custom);
        int spanCount= ScreenUtils.isLandscape()?5:4;
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        appRecyView.setLayoutManager(layoutManager);
        int pix= ConvertUtils.dp2px(10);
        appRecyView.addItemDecoration(new SpaceItemDecoration(spanCount,pix,true));
        appRecyView.setAdapter(adapter);
        fackData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registBroadCast();
        AppInstallUtils.UpdateApps(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(change!=null){
            LocalBroadcastManager.getInstance(this).unregisterReceiver(change);
        }
    }

    private void registBroadCast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(HCY_ACTION_UPDATEAPP);
        LocalBroadcastManager.getInstance(this).registerReceiver(change,filter);
    }

    private class PackageChange extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            Log.e("dxsTest","action:"+action);
            fackData();
        }
    }

    private void fackData() {
        List<AppItem> appItems=new ArrayList<>();
        List<ResolveInfo> infoList = AppInstallUtils.getInstance(this.getApplicationContext()).getAllApps();
        for (ResolveInfo info : infoList) {
            AppItem item=new AppItem(info.activityInfo.packageName,AppItem.TYPE_DEFAULT);
            appItems.add(item);
        }
        adapter.UpdateData(appItems);
    }
}
