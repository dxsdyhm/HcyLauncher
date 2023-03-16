package com.example.hcylauncher.view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;

public class MoveAndRePopWindow extends PopupMenu {
    public MoveAndRePopWindow(@NonNull Context context, @NonNull View anchor) {
        super(context, anchor);
    }

    public MoveAndRePopWindow(@NonNull Context context, @NonNull View anchor, int gravity) {
        super(context, anchor, gravity);
    }

    public MoveAndRePopWindow(@NonNull Context context, @NonNull View anchor, int gravity, int popupStyleAttr, int popupStyleRes) {
        super(context, anchor, gravity, popupStyleAttr, popupStyleRes);
    }
}
