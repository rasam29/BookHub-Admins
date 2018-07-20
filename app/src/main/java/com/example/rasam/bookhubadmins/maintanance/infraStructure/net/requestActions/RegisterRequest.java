package com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.pojos.authModels.DeviceRegisterModel;
import com.example.rasam.bookhubadmins.pojos.authModels.RegisterModel;

public interface RegisterRequest {
    void userRegister(RegisterModel registerModel, OnRequestDone<Void> onRequestDone);
    void deviceRegister(DeviceRegisterModel registerModel, OnRequestDone<Void> onRequestDone);
}
