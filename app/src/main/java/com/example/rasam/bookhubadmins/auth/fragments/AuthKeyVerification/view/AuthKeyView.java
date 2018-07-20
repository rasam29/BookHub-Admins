package com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.view;

import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.AuthKeyViewState;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface AuthKeyView extends MvpView {
    void render(AuthKeyViewState viewState);

    void onMoreThanThreeAttemts();
    void onAuthKeyNotFound();


    void gotoReportVerification();

}
