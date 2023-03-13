package com.example.hcylauncher.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

public class ScalRelativelayout extends RelativeLayout {

    public ScalRelativelayout(Context context) {
        super(context);
    }

    public ScalRelativelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScalRelativelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScalRelativelayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if(gainFocus){
            ViewCompat.animate(this).scaleX(1.10f).scaleY(1.10f).translationZ(1).start();
        }else {
            ViewCompat.animate(this).scaleX(1.0f).scaleY(1.0f).translationZ(1).start();
        }
    }
}
