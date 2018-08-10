package com.example.rasam.bookhubadmins.splash.Bussiness;


import com.example.rasam.bookhubadmins.pojos.UpdateModel;

public interface SplashViewState {

    class OnHaveToken implements SplashViewState{}
    class OnDoesntToken implements SplashViewState{}
    class OnForceUpdate implements SplashViewState{
        private UpdateModel updateModel;
        private boolean isOK;
        private Throwable throwable;

        public OnForceUpdate( Throwable throwable) {
            this.isOK = isOK;
            this.throwable = throwable;
        }

        public OnForceUpdate(UpdateModel updateModel, boolean isOK) {
            this.updateModel = updateModel;
            this.isOK = isOK;
        }

        public UpdateModel getUpdateModel() {
            return updateModel;
        }

        public boolean isOK() {
            return isOK;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }

    class NoUpdateState implements SplashViewState{}
    class OnNetError implements SplashViewState{
        private Throwable throwable;

        public OnNetError(Throwable throwable) {
            this.throwable = throwable;
        }



        public Throwable getThrowable() {
            return throwable;
        }
    }
}
