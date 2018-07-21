package com.example.rasam.bookhubadmins.main.view.mainList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rasam.bookhubadmins.R;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> implements OnSwipeButtonClicked{
    private Context context;
    private List<Ads> advertismentList;
    private OnSwipeData onSwipeData;

    public MainAdapter(Context context, List<Ads> advertismentList, OnSwipeData onSwipeData) {
        this.context = context;
        this.advertismentList = advertismentList;
        this.onSwipeData = onSwipeData;

    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_main,parent,false);
        return new MainViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return advertismentList.size();
    }

    @Override
    public void onDelete(View view, int adsItem) {
        onSwipeData.onDelete(view,advertismentList.get(adsItem));
    }

    @Override
    public void onPromote(View view, int adsItem) {
        onSwipeData.onPromote(view,advertismentList.get(adsItem));

    }

    public void appendList(List<Ads> ads){
        advertismentList.addAll(ads);
        notifyDataSetChanged();
    }

    public void refreshList(List<Ads> ads){
        advertismentList = ads;
        notifyDataSetChanged();
    }

    public void delete(Ads ads){
        advertismentList.remove(ads);
        notifyDataSetChanged();
    }
}
