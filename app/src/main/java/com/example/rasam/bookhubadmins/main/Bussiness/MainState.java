package com.example.rasam.bookhubadmins.main.Bussiness;

import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

public interface MainState {
    class DeleteState implements MainState {
        private Ads ads;

        public DeleteState(Ads ads) {
            this.ads = ads;
        }

        public Ads getAds() {
            return ads;
        }
    }

    class PromoteState implements MainState {
        Ads ads;

        public PromoteState(Ads ads) {
            this.ads = ads;
        }

        public Ads getAds() {
            return ads;
        }

    }

    class RefreshState implements MainState {
        List<Ads> list;

        public RefreshState(List<Ads> list) {
            this.list = list;
        }

        public List<Ads> getList() {
            return list;
        }
    }

    class NextPaginationState implements MainState {
        private List<Ads> list;

        public NextPaginationState(List<Ads> list) {
            this.list = list;
        }

        public List<Ads> getList() {
            return list;
        }
    }

    class NetError implements MainState{

    }

}
