package com.example.rasam.bookhubadmins.contactUs.presenter;

import com.example.rasam.bookhubadmins.pojos.AdminMassageReports;

import java.util.List;

/**
 * Created by R.Arabzadeh Taktell on 7/27/2018.
 */

public interface ContactUsState {
    class OnNetError implements ContactUsState{
    }

    class MassageSentState implements ContactUsState {
    }

    class GetHistoryState implements ContactUsState{
        List<AdminMassageReports> list;

        public GetHistoryState(List<AdminMassageReports> list) {
            this.list = list;
        }

        public List<AdminMassageReports> getList() {
            return list;
        }
    }



}
