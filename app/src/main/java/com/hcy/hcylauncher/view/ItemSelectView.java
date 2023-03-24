package com.hcy.hcylauncher.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.blankj.utilcode.util.LogUtils;
import  com.hcy.hcylauncher.R;
import com.hcy.hcylauncher.entry.AppItem;

public class ItemSelectView extends RelativeLayout {
    private ImageView icon;
    private TextView txName;

    public ItemSelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public ItemSelectView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_select_view, this, true);
        icon = findViewById(R.id.img_icon);
        txName = findViewById(R.id.tx_appname);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        txName.setSelected(gainFocus);
    }

    public void UpdateUi(AppItem item){
        if(item==null||!item.isLegal()){
            return;
        }
        if(item.getAppInfo()!=null){
            if(txName!=null){
                txName.setText(item.getAppInfo().getName());
            }
            if(icon!=null){
                icon.setImageDrawable(item.getAppInfo().getIcon());
            }
        }else {
            if(txName!=null){
                txName.setText("");
            }
        }
    }
}
