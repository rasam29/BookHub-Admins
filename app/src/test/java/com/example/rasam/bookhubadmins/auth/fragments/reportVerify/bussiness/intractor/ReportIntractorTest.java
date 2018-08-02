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
        reportVerificationRequest = new ReportVerificationRequest() {
            @Override
            public void verifyReport(ReportVerificationModel reportModel, OnRequestDone<String> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<String>(203,"adssdf"));
            }
        };
        reportIntractorImple = new ReportIntractorImple(reportVerificationRequest,authKeyDAO);
        reportIntractorImple.sendCodeToServer(null, new OnIntractor<ReportViewState>() {
            @Override
            public void onDone(ReportViewState viewState) {
                assertTrue(viewState instanceof ReportViewState.OnCodeWrong);
            }
        });


    }

    @Test
    public void sendCodeToServer_ok() {
        reportVerificationRequest = new ReportVerificationRequest() {
            @Override
            public void verifyReport(ReportVerificationModel reportModel, OnRequestDone<String> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<String>(200,"dfadfasf"));
            }
        };
        reportIntractorImple = new ReportIntractorImple(reportVerificationRequest,authKeyDAO);
        reportIntractorImple.sendCodeToServer(null, new OnIntractor<ReportViewState>() {
            @Override
            public void onDone(ReportViewState viewState) {
                reportIntractorImple.saveTokenToDataBase("myToken", new OnIntractor<ReportViewState>() {
                    @Override
                    public void onDone(ReportViewState viewState) {
                        assertTrue(viewState instanceof ReportViewState.OnCodeOk);
                    }
                });

            }
        });
    }


    @Test
    public void sendCodeToServer_OnNetError() {
        reportVerificationRequest = new ReportVerificationRequest() {
            @Override
            public void verifyReport(ReportVerificationModel reportModel, OnRequestDone<String> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<String>(new Throwable()));
            }
        };
        reportIntractorImple = new ReportIntractorImple(reportVerificationRequest,authKeyDAO);
        reportIntractorImple.sendCodeToServer(null, new OnIntractor<ReportViewState>() {
            @Override
            public void onDone(ReportViewState viewState) {
                assertTrue(viewState instanceof ReportViewState.OnNetError);
            }
        });
    }




}