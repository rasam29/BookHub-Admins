package com.example.rasam.bookhubadmins.historyManager.view.promoted;

import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryPresenter;
import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryState;
import com.example.rasam.bookhubadmins.historyManager.view.HistoryView;
import com.example.rasam.bookhubadmins.maintanance.parent.ParentActivity;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public class PromotedHistoryActivity extends ParentActivity<HistoryView,HistoryPresenter> implements HistoryView{

    @Override
    public void render(HistoryState historyState) {

    }

    @Override
    protected HistoryPresenter stablishPresenter() {
        return null;
    }
}
