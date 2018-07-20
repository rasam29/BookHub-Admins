package com.example.rasam.bookhubadmins.auth.fragments.reportVerify.view;

import com.example.rasam.bookhubadmins.auth.fragments.reportVerify.bussiness.ReportViewState;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface ReportVerifyView extends MvpView {
    void onCodeWrong();
    void  onCodeOK();
    void render(ReportViewState viewState);
}
