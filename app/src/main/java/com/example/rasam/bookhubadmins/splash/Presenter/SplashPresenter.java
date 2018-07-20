package com.example.rasam.bookhubadmins.splash.Presenter;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.splash.Bussiness.SplashViewState;
import com.example.rasam.bookhubadmins.splash.Bussiness.interactor.SplashIntractor;
import com.example.rasam.bookhubadmins.splash.Bussiness.interactor.SplashIntractorFacade;
import com.example.rasam.bookhubadmins.splash.view.SplashView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class SplashPresenter extends MvpBasePresenter<SplashView>{


    SplashView splashView;
    SplashIntractorFacade splashIntractorFacade;

    public SplashPresenter(SplashView splashView, SplashIntractorFacade splashIntractorFacade) {
        this.splashView = splashView;
        this.splashIntractorFacade = splashIntractorFacade;
    }

    @Override
    public SplashView getView() {
        return splashView;
    }

    public void checkForUpdate() {
           splashIntractorFacade.checkForUpdate(new OnIntractor<SplashViewState>() {
               @Override
               public void onDone(SplashViewState viewState) {
                   if (getView()!=null){
                       getView().render(viewState);
                   }
               }
           });

    }


    public void checkForToken() {
        splashIntractorFacade.checkForToken(new OnIntractor<SplashViewState>() {
            @Override
            public void onDone(SplashViewState viewState) {
                if (getView()!=null){
                    getView().render(viewState);
                }
            }
        });
    }

}
