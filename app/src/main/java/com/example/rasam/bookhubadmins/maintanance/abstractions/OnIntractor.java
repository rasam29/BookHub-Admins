package com.example.rasam.bookhubadmins.maintanance.abstractions;

import com.example.rasam.bookhubadmins.splash.Bussiness.SplashViewState;

public interface OnIntractor<T> {
    void onDone(T viewState);

}
