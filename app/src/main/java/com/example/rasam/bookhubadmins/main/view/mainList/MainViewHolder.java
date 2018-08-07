package com.example.rasam.bookhubadmins.main.view.mainList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.rasam.bookhubadmins.R;

public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private OnSwipeButtonClicked onSwipeButtonClicked;

    private LinearLayout promoteButton;
    private LinearLayout deleteButton;


    public TextView price;
    public TextView name;
    public TextView publisher;
    public TextView writer;
    public TextView locationDesc;
    private LinearLayout frontView;


    public MainViewHolder(View itemView, final OnSwipeButtonClicked onSwipeButtonClicked) {
        super(itemView);
        this.onSwipeButtonClicked = onSwipeButtonClicked;


        SwipeLayout swipeLayout =  itemView.findViewById(R.id.sample1);


        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        frontView = itemView.findViewById(R.id.front_view);
        promoteButton = itemView.findViewById(R.id.promote_button);
        deleteButton = itemView.findViewById(R.id.delete_button);

        price = itemView.findViewById(R.id.list_price);
        name = itemView.findViewById(R.id.list_name);
        publisher = itemView.findViewById(R.id.list_publisher);
        writer = itemView.findViewById(R.id.list_author);
        locationDesc = itemView.findViewById(R.id.location_list);


        promoteButton.setOnClickListener(view -> onSwipeButtonClicked.onPromote(view, getAdapterPosition()));
        deleteButton.setOnClickListener(view -> onSwipeButtonClicked.onDelete(view, getAdapterPosition()));

        frontView.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        onSwipeButtonClicked.onClick(view,getAdapterPosition());
    }
}
