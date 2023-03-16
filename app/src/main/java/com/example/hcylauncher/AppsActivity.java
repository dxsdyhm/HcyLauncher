package com.example.hcylauncher;

import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;
import com.example.hcylauncher.adapter.AppsSelectAdapter;
import com.example.hcylauncher.adapter.SpaceItemDecoration;
import com.example.hcylauncher.base.TitleDefaultActivity;
import com.example.hcylauncher.entry.AppItem;
import com.example.hcylauncher.utils.AppInstallUtils;

import java.util.ArrayList;
import java.util.List;

public class AppsActivity extends TitleDefaultActivity {
    private RecyclerView appRecyView;
    private AppsSelectAdapter adapter = new AppsSelectAdapter(false);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        initUi();
    }

    private void initUi() {
        statusBarView=findViewById(R.id.ststus);
        timeDefaultView=findViewById(R.id.time);
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
