package com.hcy.hcylauncher.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int mHorizontalSpaceHeight;

    public HorizontalSpaceItemDecoration(int horizontalSpaceHeight) {
        this.mHorizontalSpaceHeight = horizontalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = mHorizontalSpaceHeight;
        }

        // 对于每个项目，除了最后一个，都添加间距。
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.right = mHorizontalSpaceHeight;
        }else {
            outRect.right = 260;
        }
    }
}
