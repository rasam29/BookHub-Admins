package com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.pojos.UpdateModel;

public interface SplashRequests {
    void checkForUpdate(OnRequestDone<UpdateModel> onRequestDone);


}
