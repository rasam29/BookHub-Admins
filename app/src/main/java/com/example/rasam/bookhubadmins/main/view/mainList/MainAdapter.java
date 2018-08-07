package com.example.rasam.bookhubadmins.main.view.mainList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rasam.bookhubadmins.R;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnSwipeButtonClicked {
    private static final int TYPE_LOADING = 0;
    private static final int TYPE_ITEM = 1;
    private Context context;
    private List<Ads> advertismentList;
    private OnSwipeData onSwipeData;

    public MainAdapter(Context context, List<Ads> advertismentList, OnSwipeData onSwipeData) {
        this.context = context;
        this.advertismentList = advertismentList;
        this.onSwipeData = onSwipeData;

    }


    @Override
    public int getItemViewType(int position) {
        if (advertismentList.get(position) == null){
            return TYPE_LOADING;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.item_list_main, parent, false);
            return new MainViewHolder(view, this);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_main_recycler_view, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MainViewHolder){
            MainViewHolder mainViewHolder = (MainViewHolder) holder;
            mainViewHolder.writer.setText(advertismentList.get(position).getBook().getAuthor());
            mainViewHolder.locationDesc.setText(advertismentList.get(position).getLocation());
            mainViewHolder.name.setText(advertismentList.get(position).getBook().getName());
            mainViewHolder.price.setText(advertismentList.get(position).getBook().getPrice()+"");
            mainViewHolder.publisher.setText(advertismentList.get(position).getBook().getPublisher());
        }
    }

    @Override
    public int getItemCount() {
        return advertismentList.size();
    }

    @Override
    public void onDelete(View view, int adsItem) {
        onSwipeData.onDelete(view, advertismentList.get(adsItem));
    }

    @Override
    public void onPromote(View view, int adsItem) {
        onSwipeData.onPromote(view, advertismentList.get(adsItem));

    }

    @Override
    public void onClick(View view, int adsItem) {
        onSwipeData.onItemClick(view, advertismentList.get(adsItem));
    }

    public void appendList(List<Ads> ads) {
        advertismentList.addAll(ads);
        advertismentList.remove(null);
        notifyDataSetChanged();
    }

    public void refreshList(List<Ads> ads) {
        advertismentList = ads;
        notifyDataSetChanged();
    }

    public void delete(Ads ads) {
        advertismentList.remove(ads);
        notifyDataSetChanged();
    }

    public void addLoading(){
        advertismentList.add(null);
        notifyDataSetChanged();
    }

}
