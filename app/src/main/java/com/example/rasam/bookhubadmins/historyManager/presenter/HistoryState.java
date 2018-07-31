package com.example.rasam.bookhubadmins.historyManager.presenter;

import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public interface HistoryState {
    class OnNetError implements HistoryState {

    }


    class PromoteState implements HistoryState {
    }

    class DeleteState implements HistoryState {
    }

    class AdExpired implements HistoryState {
    }


    class OnEmptyHistory implements HistoryState {

    }

    class OnHistory implements HistoryState {
        private List<Ads> adsList;

        public OnHistory(List<Ads> adsList) {
            this.adsList = adsList;
        }

        public List<Ads> getAdsList() {
            return adsList;
        }

    }
}
