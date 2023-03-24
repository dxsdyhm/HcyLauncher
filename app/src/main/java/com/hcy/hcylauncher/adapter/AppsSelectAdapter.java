package com.hcy.hcylauncher.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.hcy.hcylauncher.CustomAppsActivity;
import com.hcy.hcylauncher.entry.AppItem;
import com.hcy.hcylauncher.entry.DefaultLayApps;
import com.hcy.hcylauncher.utils.AppLayoutUtils;
import com.hcy.hcylauncher.view.ItemSelectView;
import  com.hcy.hcylauncher.R;

import java.util.ArrayList;
import java.util.List;

public class AppsSelectAdapter extends RecyclerView.Adapter<AppsSelectAdapter.ItemViewHolder> {
    private int function;
    private List<AppItem> appItems = new ArrayList<>();
    private OnAppItemSelec onAppItemSelec;

    private DefaultLayApps apps;

    private int indexReplaceMain;

    public AppsSelectAdapter() {
    }

    public AppsSelectAdapter(int function) {
        this.function = function;
        if(function==CustomAppsActivity.FUNCTION_REPLACE||function==CustomAppsActivity.FUNCTION_SELECT){
            apps= AppLayoutUtils.loadData();
        }
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
        AppItem item = appItems.get(position);
        if (function == CustomAppsActivity.FUNCTION_SHOW) {
            if(item.isLegal()){
                holder.mainView.UpdateUi(item);
            }else {
                appItems.remove(item);
            }
            holder.check.setVisibility(View.GONE);
            holder.mainView.setOnClickListener(new AppClickListner(item));
            holder.mainView.setOnLongClickListener(new AppDeleteLongClick(item));
            if(holder.getAdapterPosition()==0){
                holder.mainView.requestFocus();
            }
        }else if(function == CustomAppsActivity.FUNCTION_REPLACE&&apps!=null){
            holder.mainView.UpdateUi(item);
            holder.check.setVisibility(View.GONE);
            holder.mainView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    apps.replaceItem(indexReplaceMain,item.getPakcgename());
                    ActivityUtils.finishActivity(CustomAppsActivity.class);
                }
            });
        }else if(function == CustomAppsActivity.FUNCTION_SELECT){
            holder.mainView.UpdateUi(item);
            //要展示选中和非选中状态
            boolean isCheck=apps.hasApp(item.getPakcgename());
            holder.check.setVisibility(isCheck?View.VISIBLE:View.GONE);
            holder.mainView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(apps.hasApp(item.getPakcgename())){
                        apps.removeApp(item.getPakcgename());
                    }else {
                        apps.addApp(item.getPakcgename());
                    }
                    notifyItemChanged(holder.getAdapterPosition());
                }
            });
        }else {
            holder.mainView.UpdateUi(item);
            //直接跳转
            holder.mainView.setOnClickListener(new AppClickListner(item));
        }
    }

    @Override
    public int getItemCount() {
        return appItems.size();
    }

    public void UpdateData(List<AppItem> items) {
        this.appItems = items;
        notifyDataSetChanged();
    }

    public void setOnAppItemSelect(OnAppItemSelec onAppItemSelec){
        this.onAppItemSelec=onAppItemSelec;
    }

    public void setReplaceIndex(int index){
        this.indexReplaceMain=index;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        public ImageView check;
        public ItemSelectView mainView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            check = itemView.findViewById(R.id.img_select);
            mainView = itemView.findViewById(R.id.item_app);
        }
    }

    @Deprecated
    public interface OnAppItemSelec{
        void onAppItemSelect(AppItem item,int position);
    }
}
