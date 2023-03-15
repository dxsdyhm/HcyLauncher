package com.example.hcylauncher.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.example.hcylauncher.R;
import com.example.hcylauncher.entry.AppItem;
import com.example.hcylauncher.view.ItemSelectView;

import java.util.ArrayList;
import java.util.List;

public class AppsSelectAdapter extends RecyclerView.Adapter<AppsSelectAdapter.ItemViewHolder> {
    private boolean showSelect;
    private List<AppItem> appItems=new ArrayList<>();

    public AppsSelectAdapter() {
    }

    public AppsSelectAdapter(boolean showSelect) {
        this.showSelect = showSelect;
    }

    @NonNull
    @Override
    public AppsSelectAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_seleapp, parent, false);
        return new AppsSelectAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppsSelectAdapter.ItemViewHolder holder, int position) {
        AppItem item=appItems.get(position);
        holder.mainView.UpdateUi(item);
        holder.check.setVisibility(showSelect?View.VISIBLE:View.GONE);
        holder.mainView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Uri uri = Uri.fromParts("package", item.getPakcgename(), null);
                Intent intent = new Intent(Intent.ACTION_DELETE, uri);
                ActivityUtils.startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return appItems.size();
    }

    public void UpdateData(List<AppItem> items){
        this.appItems=items;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        public ImageView check;
        public ItemSelectView mainView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            check=itemView.findViewById(R.id.img_select);
            mainView=itemView.findViewById(R.id.item_app);
        }
    }
}
