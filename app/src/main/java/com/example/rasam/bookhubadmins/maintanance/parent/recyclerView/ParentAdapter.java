package com.example.rasam.bookhubadmins.maintanance.parent.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class ParentAdapter<T extends BaseListModel> extends RecyclerView.Adapter<ParentViewHolder<T>> {
    List<T> ads;
    Context context;

    public ParentAdapter(List<T> ads, Context context) {
        this.ads = ads;
        this.context = context;
    }


    @Override
    public ParentViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ParentViewHolder<T>(parent,context);
    }

    @Override
    public void onBindViewHolder(ParentViewHolder<T> holder, int position) {
        holder.bindView(ads.get(position));
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ads.get(position).getItemViewType();
    }
}
