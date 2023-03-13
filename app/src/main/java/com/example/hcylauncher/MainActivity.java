package com.example.hcylauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;

import com.example.hcylauncher.adapter.CustomerItemAdapter;
import com.example.hcylauncher.adapter.HorizontalSpaceItemDecoration;
import com.example.hcylauncher.base.BaseActicity;
import com.example.hcylauncher.entry.AppItem;
import com.example.hcylauncher.view.CustomerAppRecyView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActicity {

    private CustomerAppRecyView appRecyView;
    private CustomerItemAdapter adapter=new CustomerItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        appRecyView=findViewById(R.id.rl_apps);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        appRecyView.setLayoutManager(layoutManager);
        appRecyView.addItemDecoration(new HorizontalSpaceItemDecoration(16));
        appRecyView.setAdapter(adapter);

        fakeData();
    }

    private void fakeData() {
        List<AppItem> items=new ArrayList<>();
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        items.add(new AppItem());
        adapter.UpdateData(items);
    }
}