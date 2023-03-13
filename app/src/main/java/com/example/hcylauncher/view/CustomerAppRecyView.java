package com.example.hcylauncher.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hcylauncher.adapter.CustomerItemAdapter;

public class CustomerAppRecyView extends RecyclerView {
    public CustomerAppRecyView(@NonNull Context context) {
        this(context,null);
    }

    public CustomerAppRecyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomerAppRecyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //initUI(context);
    }

    private void initUI(Context context) {
        setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        setAdapter(new CustomerItemAdapter());
    }
}
