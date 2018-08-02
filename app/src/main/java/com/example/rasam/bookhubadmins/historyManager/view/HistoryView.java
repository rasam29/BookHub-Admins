package com.example.rasam.bookhubadmins.historyManager.view;

import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryState;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public interface HistoryView extends MvpView {
    void render(HistoryState historyState);
}
