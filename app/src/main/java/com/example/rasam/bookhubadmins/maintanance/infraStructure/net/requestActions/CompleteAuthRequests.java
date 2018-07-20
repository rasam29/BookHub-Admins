package com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;

public interface CompleteAuthRequests {
    void completeAuth(CompleteAuthRequests completeAuth, OnRequestDone<String> onRequestDone);
}
