package com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness;

import com.example.rasam.bookhubadmins.auth.bussiness.RegisterViewState;

public interface AuthKeyViewState {
    class OnNetError implements AuthKeyViewState {
    }

    class OnAuthKeyNotFound implements AuthKeyViewState {
    }

    class OnAuthOk implements AuthKeyViewState {
    }

    class OnAuthNotValid implements AuthKeyViewState {
        private String massage;

        public OnAuthNotValid(String massage) {
            this.massage = massage;
        }

        public String getMassage() {
            return massage;
        }
    }

    class OnUserMoreThan3Attemts implements AuthKeyViewState {}
}
