package com.hcy.hcylauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hcy.hcylauncher.comm.Constans;
import  com.hcy.hcylauncher.R;

import java.util.concurrent.TimeUnit;

public class InatallActivity extends AppCompatActivity {
    static int INSTALL_TIMEOUT=6*60;
    private long startTime=0;

    private ThreadUtils.SimpleTask checkTask = new ThreadUtils.SimpleTask<Boolean>() {
        @Override
        public Boolean doInBackground() throws Throwable {
            //检查是否安装完成
            int state = SystemProperties.getInt(Constans.PERSI, 0);
            return state == 0;
        }

        @Override
        public void onSuccess(Boolean result) {
            long span= Math.abs(TimeUtils.getTimeSpanByNow(startTime, TimeConstants.SEC));
            int timeout=SystemProperties.getInt(Constans.PREINSTALL_TIME,INSTALL_TIMEOUT);
            if(span>=timeout){
                //超时强制进入系统
                SystemProperties.set(Constans.PERSI,"1");
                result=false;
            }
            Log.e("dxsTest","result:"+result+"span:"+span);
            if (result) {
                //还在安装
            } else {
                //安装完成，跳转到主页
                if (checkTask != null) {
                    ThreadUtils.cancel(checkTask);
                }
                toMain();
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inatall);
        int state = SystemProperties.getInt(Constans.PERSI, 0);
        if (state == 0) {
            startTime= TimeUtils.getNowMills();
            showProgress();
            //只会在预装未完成时运行
            ThreadUtils.executeBySingleAtFixRate(checkTask, 1000, TimeUnit.MILLISECONDS);
        }else {
            toMain();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress();
    }

    private void toMain(){
        boolean comple = FileUtils.isFileExists(Constans.PATH_SETUP_FLAG);
        if (!comple) {
            AppUtils.launchApp(Constans.PACKAGE_SETUP);
        }
        ActivityUtils.finishActivity(this);
    }

    private void showProgress(){
        ContentLoadingProgressBar bar=findViewById(R.id.progress);
        bar.setActivated(true);
        bar.show();
    }
}