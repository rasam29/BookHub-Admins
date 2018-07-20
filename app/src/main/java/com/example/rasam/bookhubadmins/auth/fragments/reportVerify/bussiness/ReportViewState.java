package com.example.rasam.bookhubadmins.auth.fragments.reportVerify.bussiness;

public interface ReportViewState {
    class OnNetError implements ReportViewState {
    }

    class OnCodeWrong implements ReportViewState {
    }

    class OnCodeOk implements ReportViewState {
    }


     class SaveToken {

    }
}
