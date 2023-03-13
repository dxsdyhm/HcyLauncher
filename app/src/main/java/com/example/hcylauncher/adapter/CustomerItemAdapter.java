package com.example.hcylauncher.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hcylauncher.R;
import com.example.hcylauncher.entry.AppItem;

import java.util.ArrayList;
import java.util.List;

public class CustomerItemAdapter extends RecyclerView.Adapter {
    private List<AppItem> appItems=new ArrayList<>();
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_main_app, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return appItems.size();
    }

    public void UpdateData(List<AppItem> items){
        this.appItems=items;
        notifyDataSetChanged();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        private ImageView icon;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.img_app);
        }
    }
}
