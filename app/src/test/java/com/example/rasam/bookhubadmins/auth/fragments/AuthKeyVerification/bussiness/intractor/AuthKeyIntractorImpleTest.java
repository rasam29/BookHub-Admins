package com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.intractor;

import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.AuthKeyViewState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.AuthKeyRequests;
import com.example.rasam.bookhubadmins.pojos.authModels.AuthKeyVerify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AuthKeyIntractorImpleTest {

    private AuthKeyRequests authKeyRequests;
    private AuthKeyIntractorImple authKeyIntractorImple;

    private void stressConditions() {
        authKeyRequests = new AuthKeyRequests() {
            @Override
            public void sendAuthKeyToServer(AuthKeyVerify authKey, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(new Throwable()));
            }
        };
        authKeyIntractorImple = new AuthKeyIntractorImple(authKeyRequests);

    }

    private void blockCondittion() {
        authKeyRequests = new AuthKeyRequests() {
            @Override
            public void sendAuthKeyToServer(AuthKeyVerify authKey, OnRequestDone<Void> onRequestDone) {
                authKeyRequests = new AuthKeyRequests() {
                    @Override
                    public void sendAuthKeyToServer(AuthKeyVerify authKey, OnRequestDone<Void> onRequestDone) {
                        onRequestDone.onResponse(new ResponseModel<Void>(401, null));
                    }
                };
            }
        };
        authKeyIntractorImple = new AuthKeyIntractorImple(authKeyRequests);

    }

    private void okConditions() {
        authKeyRequests = new AuthKeyRequests() {
            @Override
            public void sendAuthKeyToServer(AuthKeyVerify authKey, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(200, null));
            }
        };
        authKeyIntractorImple = new AuthKeyIntractorImple(authKeyRequests);
    }

    private void authKeyWrongConditions() {
        authKeyRequests = new AuthKeyRequests() {
            @Override
            public void sendAuthKeyToServer(AuthKeyVerify authKey, OnRequestDone<Void> onRequestDone) {
                onRequestDone.onResponse(new ResponseModel<Void>(203, null));
            }
        };
        authKeyIntractorImple = new AuthKeyIntractorImple(authKeyRequests);
    }

    @Test
    public void validationAndVerify_ok() {
        okConditions();
        authKeyIntractorImple.validationAndVerify(new AuthKeyVerify(null,null,"salam"), new OnIntractor<AuthKeyViewState>() {
            @Override
            public void onDone(AuthKeyViewState viewState) {
                assertTrue(viewState instanceof AuthKeyViewState.OnAuthOk);
            }
        });
    }

    @Test
    public void validationAndVerify_netError() {
        stressConditions();
        authKeyIntractorImple.validationAndVerify(new AuthKeyVerify(null,null,"salam"), new OnIntractor<AuthKeyViewState>() {
            @Override
            public void onDone(AuthKeyViewState viewState) {
                assertTrue(viewState instanceof AuthKeyViewState.OnNetError);
            }
        });
    }

    @Test
    public void validationAndVerify_userBlocked() {
        blockCondittion();
        authKeyIntractorImple.validationAndVerify(new AuthKeyVerify(null,null,""), new OnIntractor<AuthKeyViewState>() {
            @Override
            public void onDone(AuthKeyViewState viewState) {
                assertTrue(viewState instanceof AuthKeyViewState.OnUserMoreThan3Attemts);
            }
        });

    }

    @Test
    public void validationAndVerify_AuthKeyWrong() {
        authKeyWrongConditions();
        authKeyIntractorImple.validationAndVerify(new AuthKeyVerify(null,null,""), new OnIntractor<AuthKeyViewState>() {
            @Override
            public void onDone(AuthKeyViewState viewState) {
                assertTrue(viewState instanceof AuthKeyViewState.OnAuthKeyNotFound);
            }
        });
    }

    @Test
    public void validationAndVerify_authKeyNotValid() {
        authKeyIntractorImple = new AuthKeyIntractorImple(Mockito.mock(AuthKeyRequests.class));
        authKeyIntractorImple.validationAndVerify(new AuthKeyVerify("Andorid", "sdkjs", "salam"), new OnIntractor<AuthKeyViewState>() {
            @Override
            public void onDone(AuthKeyViewState viewState) {
                assertTrue(viewState instanceof AuthKeyViewState.OnAuthNotValid);
            }
        });

    }
}