package com.example.rasam.bookhubadmins.productInfo.presenter;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.productInfo.bussiness.MoreDetailsIntractorFacade;
import com.example.rasam.bookhubadmins.productInfo.view.MoreDetailsView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Created by R.Arabzadeh Taktell on 7/23/2018.
 */

public class MoreDetailsPresenter extends MvpBasePresenter<MoreDetailsView> implements OnIntractor<MoreDetailsState> {

    MoreDetailsIntractorFacade intractor;
    MoreDetailsView moreDetailsView;

    public MoreDetailsPresenter(MoreDetailsIntractorFacade intractor, MoreDetailsView moreDetailsView) {
        this.intractor = intractor;
        this.moreDetailsView = moreDetailsView;
    }

    @Override
    public MoreDetailsView getView() {
        return moreDetailsView;
    }

    public void getAdvertisment() {
        intractor.loadCach(this);
    }

    public void promote() {
        intractor.promote(this);
    }

    public void delete() {
        intractor.promote(this);
    }

    @Override
    public void onDone(MoreDetailsState viewState) {
        if (getView() != null) {
            getView().render(viewState);
        }
    }
}
