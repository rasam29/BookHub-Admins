package com.example.rasam.bookhubadmins.historyManager.view;

import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryPresenter;
import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryState;
import com.example.rasam.bookhubadmins.maintanance.parent.androidComponent.ParentFragment;

public class PromotedFragment  extends ParentFragment<HistoryView,HistoryPresenter> implements HistoryView{
    @Override
    public void render(HistoryState historyState) {

    }

    @Override
    public HistoryPresenter stableshPresenter() {
        return null;
    }
}
