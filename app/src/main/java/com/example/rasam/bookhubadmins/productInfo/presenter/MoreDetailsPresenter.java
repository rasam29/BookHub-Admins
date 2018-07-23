package com.example.rasam.bookhubadmins.productInfo.presenter;

import com.example.rasam.bookhubadmins.productInfo.bussiness.MoreDetailsIntractorFacade;
import com.example.rasam.bookhubadmins.productInfo.view.MoreDetailsView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by R.Arabzadeh Taktell on 7/23/2018.
 */

public class MoreDetailsPresenter extends MvpBasePresenter<MoreDetailsView> {

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

    public void getAdvertisment(){
        if (getView()!= null){

        }
    }

    public void promote(){

    }
    public void delete(){

    }
}
