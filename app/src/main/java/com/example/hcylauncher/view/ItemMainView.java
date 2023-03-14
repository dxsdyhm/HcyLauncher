package com.example.hcylauncher.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.example.hcylauncher.R;
import com.example.hcylauncher.entry.AppItem;

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
        if(item==null){
            return;
        }
        if(txName!=null){
            txName.setText(item.getPakcgename());
        }
        if(icon!=null){
            icon.setImageDrawable(item.getAppInfo().getIcon());
        }
    }
}
