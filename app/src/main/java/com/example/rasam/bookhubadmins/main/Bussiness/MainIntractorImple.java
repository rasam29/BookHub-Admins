package com.example.rasam.bookhubadmins.main.Bussiness;


import com.example.rasam.bookhubadmins.main.entity.MainDataBaseActions;
import com.example.rasam.bookhubadmins.main.entity.MainRequests;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnDAOJobFinish;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

import java.util.List;

public class MainIntractorImple implements MainIntractorFacade {
    MainDataBaseActions dbOperations;
    MainRequests mainRequests;

    public MainIntractorImple(MainDataBaseActions dbOperations, MainRequests mainRequests) {
        this.dbOperations = dbOperations;
        this.mainRequests = mainRequests;
    }


    @Override
    public void deleteAdvertisment(int advertismentID, OnIntractor<MainState> onIntractor) {

    }

    @Override
    public void promoteAdvertisment(int advertismentID, OnIntractor<MainState> onIntractor) {

    }

    @Override
    public void getMoreAds(OnIntractor<MainState> onIntractor) {

    }

    @Override
    public void refreshList(OnIntractor<MainState> onIntractor) {

    }
}
