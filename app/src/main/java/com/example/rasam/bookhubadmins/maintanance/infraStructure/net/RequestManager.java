package com.example.rasam.bookhubadmins.maintanance.infraStructure.net;

import com.example.rasam.bookhubadmins.main.entity.MainRequests;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.AuthKeyRequests;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.CompleteAuthRequests;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.RegisterRequest;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.ReportVerificationRequest;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.SplashRequests;
import com.example.rasam.bookhubadmins.pojos.UpdateModel;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.example.rasam.bookhubadmins.pojos.ads.Book;
import com.example.rasam.bookhubadmins.pojos.authModels.AuthKeyVerify;
import com.example.rasam.bookhubadmins.pojos.authModels.DeviceRegisterModel;
import com.example.rasam.bookhubadmins.pojos.authModels.RegisterModel;
import com.example.rasam.bookhubadmins.pojos.authModels.ReportVerificationModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestManager implements SplashRequests,AuthKeyRequests,CompleteAuthRequests,ReportVerificationRequest,RegisterRequest,MainRequests{
    public void checkForUpdate(OnRequestDone<UpdateModel> onRequestDone){
        //todo call the backend whenever the back end is ready
        onRequestDone.onResponse(new ResponseModel<>(200,new UpdateModel(false,null,null)));
    }


    @Override
    public void userRegister(RegisterModel registerModel, OnRequestDone<Void> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<Void>(200,null));
    }

    @Override
    public void deviceRegister(DeviceRegisterModel registerModel, OnRequestDone<Void> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<Void>(200,null));

    }

    @Override
    public void sendAuthKeyToServer(AuthKeyVerify authKey, OnRequestDone<Void> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<Void>(200,null));
    }



    @Override
    public void verifyReport(ReportVerificationModel reportModel, OnRequestDone<Void> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<Void>(200,null));

    }


    @Override
    public void completeAuth(CompleteAuthRequests completeAuth, OnRequestDone<String> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<String>(200,"(MockToken)Bearer LHkjLKjHlKJHSLNjlkjlJHlkjHLKjHlkjHLkjhkljHJlkjhlkJHLKjhLKjhlkjhLK"));

    }

    @Override
    public void deleteAds(int adsId, OnRequestDone<Void> onRequestDone) {

    }

    @Override
    public void promoteAds(int adsId, OnRequestDone<Void> onRequestDone) {

    }

    @Override
    public void refreshList(OnRequestDone<List<Ads>> onRequestDone) {

    }

    @Override
    public void getNextPsge(int lastItem, OnRequestDone<List<Ads>> onRequestDone) {


        onRequestDone.onResponse(new ResponseModel<List<Ads>>(200, Collections.<Ads>emptyList()));

    }
}
