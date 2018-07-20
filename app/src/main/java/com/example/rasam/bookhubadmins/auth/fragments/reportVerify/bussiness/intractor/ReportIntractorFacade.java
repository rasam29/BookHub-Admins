package com.example.rasam.bookhubadmins.auth.fragments.reportVerify.bussiness.intractor;

import com.example.rasam.bookhubadmins.auth.fragments.reportVerify.bussiness.ReportViewState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.authModels.ReportVerificationModel;

public interface ReportIntractorFacade {
    void sendCodeToServer(ReportVerificationModel reportModel, OnIntractor<ReportViewState> onDone);
    void saveTokenToDataBase(String token, OnIntractor<ReportViewState> onDone);

}
