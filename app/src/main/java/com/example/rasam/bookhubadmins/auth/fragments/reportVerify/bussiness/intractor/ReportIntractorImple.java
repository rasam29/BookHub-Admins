package com.example.rasam.bookhubadmins.auth.fragments.reportVerify.bussiness.intractor;

import com.example.rasam.bookhubadmins.auth.fragments.reportVerify.bussiness.ReportViewState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.AuthKeyDAO;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DataBaseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.ReportVerificationRequest;
import com.example.rasam.bookhubadmins.pojos.AuthKey;
import com.example.rasam.bookhubadmins.pojos.authModels.ReportVerificationModel;

public class ReportIntractorImple implements ReportIntractorFacade {

    private ReportVerificationRequest requestManager;
    private AuthKeyDAO authKeyDAO;

    public ReportIntractorImple(ReportVerificationRequest requestManager, AuthKeyDAO authKeyDAO) {
        this.requestManager = requestManager;
        this.authKeyDAO = authKeyDAO;
    }

    @Override
    public void sendCodeToServer(final ReportVerificationModel reportModel, final OnIntractor<ReportViewState> onDone) {
        requestManager.verifyReport(reportModel, new OnRequestDone<String>() {
            @Override
            public void onResponse(ResponseModel<String> responseModel) {
                if (responseModel.getThrowable() == null) {
                    if (responseModel.getStatusCode() == 200) {
                        saveTokenToDataBase(responseModel.getData(), onDone);
                    } else if (responseModel.getStatusCode() == 203) {
                        onDone.onDone(new ReportViewState.OnCodeWrong());
                    }
                } else {
                    onDone.onDone(new ReportViewState.OnNetError());
                }
            }
        });
    }

    @Override
    public void saveTokenToDataBase(String token, final OnIntractor<ReportViewState> onDone) {
        authKeyDAO.insertAuthKey(token, new OnDAOJobFinish<AuthKey>() {
            @Override
            public void onDone(DataBaseModel<AuthKey> dataBaseModel) {
                if (dataBaseModel.getThrowable() == null) {
                    onDone.onDone(new ReportViewState.OnCodeOk());
                }

            }
        });

    }
}
