package com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.intractor;

import com.example.rasam.bookhubadmins.maintanance.Constants;
import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.AuthKeyViewState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.AuthKeyRequests;
import com.example.rasam.bookhubadmins.pojos.authModels.AuthKeyVerify;

import java.lang.ref.PhantomReference;

public class AuthKeyIntractorImple implements AuthKeyFacadeIntractorFacade {
    private AuthKeyRequests verifyRequest;

    private static final int OK_HTTP_CODE_VERIFY = 200;
    private static final int AUTH_KEY_NOT_FOUND_HTTP_CODE = 203;
    private static final int TOO_MANY_ATEMPTS_CODE = 401;

    public AuthKeyIntractorImple(AuthKeyRequests verifyRequest) {
        this.verifyRequest = verifyRequest;
    }

    @Override
    public void validationAndVerify(AuthKeyVerify authKeyModel, final OnIntractor<AuthKeyViewState> onDone) {
        if (authKeyModel.getAuthKey() != null && !authKeyModel.getAuthKey().isEmpty()) {
            verifyRequest.sendAuthKeyToServer(authKeyModel, new OnRequestDone<Void>() {
                @Override
                public void onResponse(ResponseModel<Void> responseModel) {
                    if (responseModel.getThrowable() == null) {
                        if (responseModel.getStatusCode() == OK_HTTP_CODE_VERIFY) {
                            onDone.onDone(new AuthKeyViewState.OnAuthOk());

                        } else if (responseModel.getStatusCode() == AUTH_KEY_NOT_FOUND_HTTP_CODE) {
                            onDone.onDone(new AuthKeyViewState.OnAuthKeyNotFound());

                        } else if (responseModel.getStatusCode() == TOO_MANY_ATEMPTS_CODE) {
                            onDone.onDone(new AuthKeyViewState.OnUserMoreThan3Attemts());
                        } else throw new IllegalArgumentException("teest");
                    } else {

                        onDone.onDone(new AuthKeyViewState.OnNetError());

                    }
                }
            });
        }else {
            onDone.onDone(new AuthKeyViewState.OnAuthNotValid("empty"));
        }
    }


}
