package com.example.rasam.bookhubadmins.maintanance.parent.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ParentViewHolder<T> extends RecyclerView.ViewHolder {


    public ParentViewHolder(View view) {
        super(view);
    }

    public abstract void bindView(T objectModel);


}
