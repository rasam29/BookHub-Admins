package com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.intractor;

import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.AuthKeyViewState;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.authModels.AuthKeyVerify;

public interface AuthKeyFacadeIntractorFacade {
    void validationAndVerify(AuthKeyVerify verifyModel, OnIntractor<AuthKeyViewState> onDone);

}
