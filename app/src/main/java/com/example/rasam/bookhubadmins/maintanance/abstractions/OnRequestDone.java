package com.example.rasam.bookhubadmins.maintanance.abstractions;

import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;

public interface OnRequestDone<T> {
    void onResponse(ResponseModel<T> responseModel);
}
