package com.example.rasam.bookhubadmins.historyManager.presenter;

import com.example.rasam.bookhubadmins.historyManager.bussiness.HistoryIntractor;
import com.example.rasam.bookhubadmins.historyManager.view.HistoryView;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public class HistoryPresenter extends MvpBasePresenter<HistoryView> implements OnIntractor<HistoryState>{
    HistoryView historyView;
    HistoryIntractor historyIntractor;

    public HistoryPresenter(HistoryView historyView, HistoryIntractor historyIntractor) {
        this.historyView = historyView;
        this.historyIntractor = historyIntractor;
    }

    @Override
    public HistoryView getView() {
        return historyView;
    }

    public void getPromotedHistory(){
        historyIntractor.getDeletedAds(this);
    }
    public void getDeletedHistory(){
        historyIntractor.getPromotedAds(this);
    }

    @Override
    public void onDone(HistoryState viewState) {
        if (getView()!= null){
            getView().render(viewState);
        }
    }
}
