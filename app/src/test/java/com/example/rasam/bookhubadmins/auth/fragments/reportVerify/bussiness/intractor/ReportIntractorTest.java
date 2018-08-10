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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReportIntractorTest {
    ReportVerificationRequest reportVerificationRequest;
    AuthKeyDAO authKeyDAO;
    ReportIntractorImple reportIntractorImple;

    @Before
    public void before(){
        authKeyDAO = new AuthKeyDAO() {
            @Override
            public void deleteAuthKey(OnDAOJobFinish<AuthKey> onDone) {

            }

            @Override
            public void getAuthKey(OnDAOJobFinish<AuthKey> onDone) {

            }

            @Override
            public void insertAuthKey(String authKey, OnDAOJobFinish<AuthKey> onDone) {
                onDone.onDone(new DataBaseModel<AuthKey>(true));
            }
        };

    }


    @Test
    public void sendCodeToServer_codeWrong() {
        reportVerificationRequest = (reportModel, onRequestDone) -> onRequestDone.onResponse(new ResponseModel<String>(203,"adssdf"));
        reportIntractorImple = new ReportIntractorImple(reportVerificationRequest,authKeyDAO);
        reportIntractorImple.sendCodeToServer(null, viewState -> assertTrue(viewState instanceof ReportViewState.OnCodeWrong));


    }

    @Test
    public void sendCodeToServer_ok() {
        reportVerificationRequest = (reportModel, onRequestDone) -> onRequestDone.onResponse(new ResponseModel<String>(200,"dfadfasf"));
        reportIntractorImple = new ReportIntractorImple(reportVerificationRequest,authKeyDAO);
        reportIntractorImple.sendCodeToServer(null, viewState -> reportIntractorImple.saveTokenToDataBase("myToken", viewState1 -> assertTrue(viewState1 instanceof ReportViewState.OnCodeOk)));
    }


    @Test
    public void sendCodeToServer_OnNetError() {
        reportVerificationRequest = (reportModel, onRequestDone) -> onRequestDone.onResponse(new ResponseModel<String>(new Throwable()));
        reportIntractorImple = new ReportIntractorImple(reportVerificationRequest,authKeyDAO);
        reportIntractorImple.sendCodeToServer(null, viewState -> assertTrue(viewState instanceof ReportViewState.OnNetError));
    }




}