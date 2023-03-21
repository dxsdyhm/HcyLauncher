package com.example.hcylauncher.utils;

import android.util.Log;

public interface UpdateCustomer {
    default boolean updateCustomerRemovie(String pkg){
        Log.e("UpdateCustomer","updateCustomerRemovie:"+pkg);
        return false;
    }
    default boolean updateCustomerAdd(String pkg){
        Log.e("UpdateCustomer","updateCustomerAdd:"+pkg);
        return false;
    }
    default boolean updateCustomer(String pkg){
        Log.e("UpdateCustomer","updateCustomer:"+pkg);
        return false;
    }
}
