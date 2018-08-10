package com.example.rasam.bookhubadmins.historyManager.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rasam.bookhubadmins.R;
import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryDependency;
import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryPresenter;
import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryState;
import com.example.rasam.bookhubadmins.maintanance.parent.androidComponent.ParentFragment;

public class DeletedFragment extends ParentFragment<HistoryView, HistoryPresenter> implements HistoryView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_history,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.list);

        return view;

    }

    @Override
    public void render(HistoryState historyState) {

    }

    @Override
    public HistoryPresenter stableshPresenter() {
        return new HistoryPresenter(this, HistoryDependency.inject());
    }
}
