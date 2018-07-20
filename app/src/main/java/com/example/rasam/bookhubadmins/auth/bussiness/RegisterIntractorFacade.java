package com.example.rasam.bookhubadmins.auth.bussiness;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.authModels.DeviceRegisterModel;
import com.example.rasam.bookhubadmins.pojos.authModels.RegisterModel;

public interface RegisterIntractorFacade {
    void registerUser(RegisterModel registerModel, OnIntractor<RegisterViewState> onDone);
    void regiterDevice(DeviceRegisterModel deviceModel, OnIntractor<RegisterViewState> onDone);


}
