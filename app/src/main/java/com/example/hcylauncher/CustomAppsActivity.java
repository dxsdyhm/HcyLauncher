package com.example.hcylauncher;

import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.example.hcylauncher.adapter.AppsSelectAdapter;
import com.example.hcylauncher.adapter.HorizontalSpaceItemDecoration;
import com.example.hcylauncher.adapter.SpaceItemDecoration;
import com.example.hcylauncher.entry.AppItem;
import com.example.hcylauncher.utils.AppInstallUtils;
import com.example.hcylauncher.view.CustomerAppRecyView;

import java.util.ArrayList;
import java.util.List;

public class CustomAppsActivity extends AppCompatActivity {

    private RecyclerView appRecyView;
    private AppsSelectAdapter adapter = new AppsSelectAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom);
        initDialog();
        initUi();
    }

    public void initDialog() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.height = (int) (ScreenUtils.getScreenHeight() *0.7);
        getWindow().setAttributes(lp);
    }

    private void initUi() {
        appRecyView = findViewById(R.id.rl_custom);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false);
        appRecyView.setLayoutManager(layoutManager);
        int pix= ConvertUtils.dp2px(10);
        appRecyView.addItemDecoration(new SpaceItemDecoration(5,pix,true));
        appRecyView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fackData();
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
