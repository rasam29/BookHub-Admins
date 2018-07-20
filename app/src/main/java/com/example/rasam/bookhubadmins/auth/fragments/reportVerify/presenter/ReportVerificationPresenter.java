package com.example.rasam.bookhubadmins.auth.fragments.reportVerify.presenter;

import android.content.Context;

import com.example.rasam.bookhubadmins.auth.fragments.reportVerify.bussiness.ReportViewState;
import com.example.rasam.bookhubadmins.auth.fragments.reportVerify.bussiness.intractor.ReportIntractorFacade;
import com.example.rasam.bookhubadmins.auth.fragments.reportVerify.view.ReportVerifyView;
import com.example.rasam.bookhubadmins.maintanance.MockUuid;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.authModels.ReportVerificationModel;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class ReportVerificationPresenter extends MvpBasePresenter<ReportVerifyView> {
    ReportIntractorFacade intractor;
    ReportVerifyView reportView;


    public ReportVerificationPresenter(ReportIntractorFacade intractor, ReportVerifyView view) {
        this.intractor = intractor;
        this.reportView = view;
    }


    @Override
    public ReportVerifyView getView() {
        return reportView;
    }


    public void sendCodeToServer(String code, Context context){
        intractor.sendCodeToServer(new ReportVerificationModel("Android", MockUuid.getUuid(context), code), new OnIntractor<ReportViewState>() {
            @Override
            public void onDone(ReportViewState viewState) {
                if (getView()!= null){
                    getView().render(viewState);
                }
            }
        });
    }
}
