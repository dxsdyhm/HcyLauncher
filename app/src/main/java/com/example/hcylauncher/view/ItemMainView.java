package com.example.hcylauncher.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.example.hcylauncher.R;

public class ItemMainView extends ScalRelativelayout {
    private ImageView icon;
    private TextView txName;

    public ItemMainView(Context context, AttributeSet attrs) {
        this(context, attrs,0,0);
    }

    public ItemMainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_main_view, this, true);
        icon=findViewById(R.id.img_icon);
        txName=findViewById(R.id.tx_appname);
        txName.setText("dahfhfiahg;hg;agh;afghafgihafgihfdgthtsrjdyjyjfaghiaf");
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if(gainFocus){
            ViewCompat.animate(this).scaleX(1.10f).scaleY(1.10f).translationZ(1).start();
        }else {
            ViewCompat.animate(this).scaleX(1.0f).scaleY(1.0f).translationZ(1).start();
        }
        txName.setSelected(gainFocus);
    }
}
