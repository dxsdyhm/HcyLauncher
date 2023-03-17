package com.example.hcylauncher.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.example.hcylauncher.R;
import com.example.hcylauncher.adapter.AppClickListner;
import com.example.hcylauncher.entry.AppItem;
import com.example.hcylauncher.utils.AppLayoutUtils;

public class ItemMainView extends ScalRelativelayout {
    private ImageView icon;
    private TextView txName;
    private boolean ScaleMode = true;

    public ItemMainView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public ItemMainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_main_view, this, true);
        icon = findViewById(R.id.img_icon);
        txName = findViewById(R.id.tx_appname);
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
                icon.setImageDrawable(item.getAppInfo().getIcon());
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
