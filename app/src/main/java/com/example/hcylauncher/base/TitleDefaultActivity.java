package com.example.hcylauncher.base;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.example.hcylauncher.entry.DefaultLayApps;
import com.example.hcylauncher.utils.AppLayoutUtils;
import com.example.hcylauncher.view.StatusBarView;
import com.example.hcylauncher.view.TimeDefaultView;

/**
 * 处理布局状态栏上的状态更新
 * 1.外设接口状态变更
 * 2.时间更新
 */
public class TitleDefaultActivity extends BaseActicity{
    private static String TAG="TitleDefaultActivity";

    private TitleBroadReceviver receviver=new TitleBroadReceviver();
    public StatusBarView statusBarView;
    public TimeDefaultView timeDefaultView;
    public ImageView ivClean;

    @Override
    protected void onResume() {
        super.onResume();
        registBroadCast();
        updateDate();
        diaystuts();
        checkClean();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receviver);
    }

    private void registBroadCast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_MEDIA_EJECT);
        filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
        filter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        filter.addDataScheme("file");
        registerReceiver(receviver,filter);
    }

    private class TitleBroadReceviver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            Log.e(TAG,"action:"+action);
            if(Intent.ACTION_TIME_TICK.equals(action)||Intent.ACTION_TIME_CHANGED.equals(action)){
                updateDate();
            }else if(UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)){
                statusBarView.setIndexState(StatusBarView.INDEX_USB,StatusBarView.STATE_ON);
            }else if(UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)){
                statusBarView.setIndexState(StatusBarView.INDEX_USB,StatusBarView.STATE_OFF);
            }else {
                diaystuts();
            }
        }
    }

    /**
     * 更新状态栏时间
     */
    public void updateDate(){
        if(timeDefaultView!=null){
            timeDefaultView.UpdateTime();
        }else {
            Log.e(TAG,"timeDefaultView is null");
        }
    }

    private void diaystuts() {
        if(statusBarView!=null){
            changeNet();
            isSD();
            isUSB();
            isBluetooth();
        }
    }

    private void checkClean(){
        if(ivClean!=null){
            DefaultLayApps apps= AppLayoutUtils.loadData();
            boolean hasCleanApp= AppUtils.isAppInstalled(apps.getClean());
            ivClean.setVisibility(hasCleanApp? View.VISIBLE:View.GONE);
            ivClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppUtils.launchApp(apps.getClean());
                }
            });
        }
    }

    private void changeNet() {
        int type =getNetType();
        if(type==ConnectivityManager.TYPE_WIFI){
            statusBarView.setIndexState(StatusBarView.INDEX_WIFI,StatusBarView.STATE_ON);
            statusBarView.setIndexState(StatusBarView.INDEX_ETHERNET,StatusBarView.STATE_OFF);
        }else if(type==ConnectivityManager.TYPE_ETHERNET){
            statusBarView.setIndexState(StatusBarView.INDEX_ETHERNET,StatusBarView.STATE_ON);
            statusBarView.setIndexState(StatusBarView.INDEX_WIFI,StatusBarView.STATE_OFF);
        }else {
            statusBarView.setIndexState(StatusBarView.INDEX_ETHERNET,StatusBarView.STATE_OFF);
            statusBarView.setIndexState(StatusBarView.INDEX_WIFI,StatusBarView.STATE_OFF);
        }
    }

    public void iswifi() {
        int wifi_level = getWifiLevel();
        statusBarView.setIndexState(StatusBarView.INDEX_WIFI,wifi_level != -1?StatusBarView.STATE_ON:StatusBarView.STATE_OFF);
    }

    private void isSD() {
        statusBarView.setIndexState(StatusBarView.INDEX_SDCARD,isSdcardExist()?StatusBarView.STATE_ON:StatusBarView.STATE_OFF);
    }

    private void isUSB() {
        statusBarView.setIndexState(StatusBarView.INDEX_USB,isUdiskExist()?StatusBarView.STATE_ON:StatusBarView.STATE_OFF);
    }

    private void iswan() {
        statusBarView.setIndexState(StatusBarView.INDEX_ETHERNET,isEthernetOn()?StatusBarView.STATE_ON:StatusBarView.STATE_OFF);
    }

    private void isBluetooth() {
        BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();
        if(adapter!=null&&adapter.isEnabled()){
            statusBarView.setIndexState(StatusBarView.INDEX_BLUTOOTH,StatusBarView.STATE_ON);
        }else {
            statusBarView.setIndexState(StatusBarView.INDEX_BLUTOOTH,StatusBarView.STATE_OFF);
        }
    }

    private boolean isSdcardExist() {
//        List<VolumeInfo> volumes = mStorageManager.getVolumes();
//        Collections.sort(volumes, VolumeInfo.getDescriptionComparator());
//        for (VolumeInfo vol : volumes) {
//            if (vol != null && vol.isMountedReadable() && vol.getType() == VolumeInfo.TYPE_PUBLIC) {
//                DiskInfo disk = vol.getDisk();
//                if (disk.isSd()) {
//                    return true;
//                }
//            }
//        }
        return false;
    }

    private boolean isUdiskExist() {
//        List<VolumeInfo> volumes = mStorageManager.getVolumes();
//        Collections.sort(volumes, VolumeInfo.getDescriptionComparator());
//        for (VolumeInfo vol : volumes) {
//            if (vol != null && vol.isMountedReadable() && vol.getType() == VolumeInfo.TYPE_PUBLIC) {
//                DiskInfo disk = vol.getDisk();
//                if (disk.isUsb()) {
//                    return true;
//                }
//            }
//        }
        return false;
    }

    private int getNetType(){
        ConnectivityManager mConnectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(mConnectivityManager==null){
            return -1;
        }
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            int networkType = networkInfo.getType();
            String networkTypeName = networkInfo.getTypeName();
            Log.e(TAG,"networkTypeName:"+networkTypeName+"  networkType:"+networkType);
            return networkType;
        }
        return -1;
    }

    private int getWifiLevel() {
        if (NetworkUtils.isWifiConnected()) {
            return 1;
        } else {
            return -1;
        }
    }

    private boolean isEthernetOn() {
        ConnectivityManager mConnectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        if (info != null && info.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
