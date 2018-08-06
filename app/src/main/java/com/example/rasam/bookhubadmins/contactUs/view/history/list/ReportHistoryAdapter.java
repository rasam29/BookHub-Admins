package com.example.rasam.bookhubadmins.contactUs.view.history.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rasam.bookhubadmins.R;
import com.example.rasam.bookhubadmins.pojos.AdminMassageReports;

import java.util.List;

public class ReportHistoryAdapter extends RecyclerView.Adapter<ReportHistoryViewHolder> {
    List<AdminMassageReports> list ;
    Context context;

    public ReportHistoryAdapter(List<AdminMassageReports> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @Override
    public ReportHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_history_report,parent);

        return new ReportHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportHistoryViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.massage.setText(list.get(position).getMassageFromAdmin());
        if (list.get(position).isSeenFrom()){
            holder.state_image.setImageResource(R.drawable.ic_maps_and_flags);
            holder.bookHubReply.setText(list.get(position).getMassageFromAdmin());
        }else {
            holder.bookHubReply.setText("__");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void fillList(List<AdminMassageReports> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
