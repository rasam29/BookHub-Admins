package com.example.rasam.bookhubadmins.splash.Bussiness.interactor;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.splash.Bussiness.SplashViewState;

public interface SplashIntractorFacade {

    void checkForUpdate(OnIntractor<SplashViewState> onIntracto);
    void checkForToken(OnIntractor<SplashViewState> onIntractor);

}
