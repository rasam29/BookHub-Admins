package com.example.rasam.bookhubadmins.contactUs.view.history.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rasam.bookhubadmins.R;

public class ReportHistoryViewHolder extends RecyclerView.ViewHolder{

    public TextView massage;
    public TextView title;
    public ImageView state_image;
    public TextView bookHubReply;



    public ReportHistoryViewHolder(View itemView) {
        super(itemView);
        massage = itemView.findViewById(R.id.list_massage);
        title = itemView.findViewById(R.id.list_title);
        state_image = itemView.findViewById(R.id.mark_state);
        bookHubReply = itemView.findViewById(R.id.list_bookHubMassage);
    }
}
