package com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.pojos.authModels.AuthKeyVerify;

public interface AuthKeyRequests {

    void sendAuthKeyToServer(AuthKeyVerify authKey, OnRequestDone<Void> onRequestDone);


}
