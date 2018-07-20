package com.example.rasam.bookhubadmins.auth.bussiness;


public interface RegisterViewState {
    class UserRegisterState implements RegisterViewState {


    }

    class DeviceRegisterState implements RegisterViewState {


    }

    class OnUUidBlockedState implements RegisterViewState {
    }

    class ONetError implements RegisterViewState {
        private Throwable reason;

        public ONetError(Throwable reason) {
            this.reason = reason;
        }

        public Throwable getAction() {
            return reason;
        }
    }
}
