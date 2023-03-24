package com.hcy.hcylauncher.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.hcy.hcylauncher.comm.PackagName;
import  com.hcy.hcylauncher.R;
import com.hcy.hcylauncher.adapter.AppClickListner;
import com.hcy.hcylauncher.entry.AppItem;
import com.hcy.hcylauncher.utils.AppLayoutUtils;

public class ItemMainView extends ScalRelativelayout {
    private ImageView icon;
    private TextView txName;
    private boolean ScaleMode = true;

    public ItemMainView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public ItemMainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HcyType);
        int maintype=typedArray.getInteger(R.styleable.HcyType_maintype,1);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(maintype==1){
            inflater.inflate(R.layout.item_main_view, this, true);
        } else if (maintype==3) {
            inflater.inflate(R.layout.item_main_view_3, this, true);
        } else {
            inflater.inflate(R.layout.item_main_view_small, this, true);
        }
        icon = findViewById(R.id.img_icon);
        txName = findViewById(R.id.tx_appname);
        setElevation(3);
        setClickListner(null);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if(ScaleMode){
            if (gainFocus) {
                ViewCompat.animate(this).scaleX(1.10f).scaleY(1.10f).translationZ(1).start();
            } else {
                ViewCompat.animate(this).scaleX(1.0f).scaleY(1.0f).translationZ(1).start();
            }
        }
        txName.setSelected(gainFocus);
    }

    public void setScaleMode(boolean scaleMode) {
        ScaleMode = scaleMode;
    }

    public void UpdateUi(AppItem item){
        setClickListner(item);
        if(item==null){
            return;
        }
        if(txName!=null){
            if(item.getAppInfo()!=null){
                txName.setText(item.getAppInfo().getName());
                txName.setVisibility(VISIBLE);
            }else {
                txName.setVisibility(GONE);
            }
        }
        if(icon!=null){
            if(item.getAppInfo()!=null){
                if(PackagName.PKG_YOUTUBE.equals(item.getAppInfo().getPackageName())){
                    icon.setImageResource(R.drawable.youtube);
                }else {
                    icon.setImageDrawable(item.getAppInfo().getIcon());
                }
            }else {
                icon.setImageResource(R.drawable.baseline_add_circle_outline_24);
            }
        }
    }

    private void setClickListner(AppItem item){
        setOnClickListener(new AppClickListner(item));
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //展示popmenu
                PopupMenu popupMenu=new PopupMenu(view.getContext(),view);
                popupMenu.getMenuInflater().inflate(R.menu.remove_replay,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.remove:
                                AppLayoutUtils.loadData().replaceItem(item.getIndex(),"");
                                UpdateUi(new AppItem("",item.getType(),item.getIndex()));
                                break;
                            case R.id.replace:
                                AppClickListner.toReplace(item,view);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
    }
}
