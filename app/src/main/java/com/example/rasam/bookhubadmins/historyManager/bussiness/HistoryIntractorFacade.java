package com.example.rasam.bookhubadmins.historyManager.bussiness;

import com.example.rasam.bookhubadmins.historyManager.presenter.HistoryState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */

public interface HistoryIntractorFacade {

    void getDeletedAds(OnIntractor<HistoryState> onIntractor);
    void getPromotedAds(OnIntractor<HistoryState> onIntractor);
}
