package com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.presenter;

import android.content.Context;

import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.AuthKeyViewState;
import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.intractor.AuthKeyFacadeIntractorFacade;
import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.view.AuthKeyView;
import com.example.rasam.bookhubadmins.maintanance.MockUuid;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.authModels.AuthKeyVerify;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class AuthKeyPresenter extends MvpBasePresenter<AuthKeyView> {
   private AuthKeyFacadeIntractorFacade intractor;
   private AuthKeyView authKeyView;



    public AuthKeyPresenter(AuthKeyFacadeIntractorFacade intractor, AuthKeyView authKeyView) {
        this.intractor = intractor;
        this.authKeyView = authKeyView;
    }

    @Override
    public AuthKeyView getView() {
        return authKeyView;
    }

    public void sendPhoneToServer(String authKey, Context context) {

        intractor.validationAndVerify(new AuthKeyVerify("Android", MockUuid.getUuid(context), authKey), new OnIntractor<AuthKeyViewState>() {
            @Override
            public void onDone(AuthKeyViewState viewState) {
                if (getView()!=null){
                    getView().render(viewState);
                }
            }
        });
    }




}
