package com.example.rasam.bookhubadmins.main.Bussiness;

import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

public interface MainState {
    class DeleteState implements MainState{}
    class PromoteState implements MainState{}
    class RefreshState implements MainState{
        List<Ads> list;

        public RefreshState(List<Ads> list) {
            this.list = list;
        }

        public List<Ads> getList() {
            return list;
        }
    }
    class NextPaginationState implements MainState{
        private List<Ads> list;

        public NextPaginationState(List<Ads> list) {
            this.list = list;
        }

        public List<Ads> getList() {
            return list;
        }
    }

}
