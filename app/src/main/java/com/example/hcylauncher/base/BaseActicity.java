package com.example.hcylauncher.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;

public class BaseActicity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        setBackGround();
    }

    private void setBackGround() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {

                    @Override
                    public void onGranted() {
                        @SuppressLint("MissingPermission") Drawable wallpaperDrawable = wallpaperManager.getDrawable();
                        getWindow().setBackgroundDrawable(wallpaperDrawable);
                    }

                    @Override
                    public void onDenied() {
                        Log.e("dxsTest", "STORAGE onDenied");
                    }
                }).request();
    }
}
