package com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.pojos.authModels.ReportVerificationModel;

public interface ReportVerificationRequest {
    void verifyReport(ReportVerificationModel reportModel, OnRequestDone<String> onRequestDone);
}
