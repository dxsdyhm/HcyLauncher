package com.hcy.hcylauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemProperties;

import com.blankj.utilcode.util.ThreadUtils;
import com.hcy.hcylauncher.comm.Constans;
import  com.hcy.hcylauncher.R;

import java.util.concurrent.TimeUnit;

public class InatallActivity extends AppCompatActivity {

    private ThreadUtils.SimpleTask checkTask = new ThreadUtils.SimpleTask<Boolean>() {
        @Override
        public Boolean doInBackground() throws Throwable {
            //检查是否安装完成
            int state = SystemProperties.getInt(Constans.PERSI, 0);
            return state == 0;
        }

        @Override
        public void onSuccess(Boolean result) {
            if (result) {
                //还在安装
            } else {
                //安装完成，跳转到主页
                if (checkTask != null) {
                    ThreadUtils.cancel(checkTask);

                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inatall);
        int state = SystemProperties.getInt(Constans.PERSI, 0);
        if (state == 0) {
            //只会在预装未完成时运行
            ThreadUtils.executeBySingleAtFixRate(checkTask, 1000, TimeUnit.MILLISECONDS);
        }
    }
}