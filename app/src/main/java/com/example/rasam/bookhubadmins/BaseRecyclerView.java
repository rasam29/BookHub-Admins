package com.example.rasam.bookhubadmins;

import android.support.v7.widget.RecyclerView;

/**
 * Created by R.Arabzadeh Taktell on 8/6/2018.
 */

public abstract class BaseRecyclerView<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>  {
    public abstract void onListArrivedEnd();




}
