package com.example.rasam.bookhubadmins.main.presenter;

import com.example.rasam.bookhubadmins.main.Bussiness.MainIntractorFacade;
import com.example.rasam.bookhubadmins.main.Bussiness.MainState;
import com.example.rasam.bookhubadmins.main.view.MainView;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;


public class MainPresenter extends MvpBasePresenter<MainView> {
    private MainView mainView;
    private MainIntractorFacade mainIntractorFacade;


    public MainPresenter(MainView mainView, MainIntractorFacade mainIntractorFacade) {
        this.mainView = mainView;
        this.mainIntractorFacade = mainIntractorFacade;
    }

    public void deleteAdvertisment(Ads advertisMent) {
        mainIntractorFacade.deleteAdvertisment(advertisMent, new OnIntractor<MainState>() {
            @Override
            public void onDone(MainState viewState) {
                if (getView() != null) {
                    getView().render(viewState);
                }
            }
        });
    }

    public void promoteAdvertisment(Ads advertisMent) {
        mainIntractorFacade.promoteAdvertisment(advertisMent, new OnIntractor<MainState>() {
            @Override
            public void onDone(MainState viewState) {
                if (getView() != null) {
                    getView().render(viewState);
                }
            }
        });
    }

    public void getMoreAds() {
        mainIntractorFacade.getMoreAds(new OnIntractor<MainState>() {
            @Override
            public void onDone(MainState viewState) {
                if (getView() != null) {
                    getView().render(viewState);
                }
            }
        });
    }

    public void refreshList() {
        mainIntractorFacade.refreshList(new OnIntractor<MainState>() {
            @Override
            public void onDone(MainState viewState) {
                if (getView() != null) {
                    getView().render(viewState);
                }
            }
        });
    }


}
