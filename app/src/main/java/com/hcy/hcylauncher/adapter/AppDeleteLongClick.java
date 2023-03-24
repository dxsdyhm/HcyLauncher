package com.hcy.hcylauncher.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.hcy.hcylauncher.entry.AppItem;

public class AppDeleteLongClick implements View.OnLongClickListener {
    private AppItem appItem;
    public AppDeleteLongClick(AppItem appItem) {
        this.appItem = appItem;
    }
    @Override
    public boolean onLongClick(View view) {
        Uri uri = Uri.fromParts("package", appItem.getPakcgename(), null);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        ActivityUtils.startActivity(intent);
        return false;
    }
}
