package com.example.rasam.bookhubadmins.contactUs.view.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rasam.bookhubadmins.R;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactDependency;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsPresenter;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsState;
import com.example.rasam.bookhubadmins.contactUs.view.contact_us.ContactUsView;
import com.example.rasam.bookhubadmins.contactUs.view.history.list.ReportHistoryAdapter;
import com.example.rasam.bookhubadmins.maintanance.parent.ParentFragment;
import com.example.rasam.bookhubadmins.pojos.AdminMassageReports;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportsHistory extends ParentFragment<ContactUsView, ContactUsPresenter> implements ContactUsView {

    @BindView(R.id.list)
    RecyclerView recyclerView;


    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    List<AdminMassageReports> adminMassageReports = new ArrayList<>();
    ReportHistoryAdapter reportHistoryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_history, container, false);
//        ButterKnife.bind(this, view);

//        recyclerView = view.findViewById(R.id.list);
//        swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
//        setUpRecyclerView();
//        swipeRefreshLayout.setOnRefreshListener(() -> {
//            presenter.getListOfMassagesHistory();
//        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getListOfMassagesHistory();
    }

    @Override
    public void render(ContactUsState contactUsState) {
        if (contactUsState instanceof ContactUsState.GetHistoryState) {
            swipeRefreshLayout.setRefreshing(false);
            reportHistoryAdapter.fillList(((ContactUsState.GetHistoryState) contactUsState).getList());
        } else if (contactUsState instanceof ContactUsState.OnNetError) {
            Toast.makeText(getContext(), "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();
        } else throw new IllegalArgumentException();
    }

    @Override
    public ContactUsPresenter stableshPresenter() {
        return new ContactUsPresenter(this, ContactDependency.inject());
    }

    public void setUpRecyclerView() {
        reportHistoryAdapter = new ReportHistoryAdapter(adminMassageReports, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(reportHistoryAdapter);
    }
}
