package com.example.rasam.bookhubadmins.maintanance.infraStructure.net;

import android.os.Handler;

import com.example.rasam.bookhubadmins.SimpleAdvertismentAction;
import com.example.rasam.bookhubadmins.contactUs.entity.ContactUSRequest;
import com.example.rasam.bookhubadmins.historyManager.entity.HistoryRequests;
import com.example.rasam.bookhubadmins.main.entity.MainRequests;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.maintanance.abstractions.OnRequestDone;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.AuthKeyRequests;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.CompleteAuthRequests;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.RegisterRequest;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.ReportVerificationRequest;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.requestActions.SplashRequests;
import com.example.rasam.bookhubadmins.pojos.AdminMassageReports;
import com.example.rasam.bookhubadmins.pojos.ContactUsMassagePayLoad;
import com.example.rasam.bookhubadmins.pojos.UpdateModel;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.example.rasam.bookhubadmins.pojos.ads.Book;
import com.example.rasam.bookhubadmins.pojos.ads.User;
import com.example.rasam.bookhubadmins.pojos.authModels.AuthKeyVerify;
import com.example.rasam.bookhubadmins.pojos.authModels.DeviceRegisterModel;
import com.example.rasam.bookhubadmins.pojos.authModels.RegisterModel;
import com.example.rasam.bookhubadmins.pojos.authModels.ReportVerificationModel;

import java.util.ArrayList;
import java.util.List;

public class RequestManager implements SplashRequests, AuthKeyRequests, ReportVerificationRequest, RegisterRequest, MainRequests,ContactUSRequest,SimpleAdvertismentAction,HistoryRequests {
    public void checkForUpdate(OnRequestDone<UpdateModel> onRequestDone) {
        //todo call the backend whenever the back end is ready
        onRequestDone.onResponse(new ResponseModel<>(200, new UpdateModel(false, null, null)));
    }


    @Override
    public void userRegister(RegisterModel registerModel, OnRequestDone<Void> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<Void>(200, null));
    }

    @Override
    public void deviceRegister(DeviceRegisterModel registerModel, OnRequestDone<Void> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<Void>(200, null));

    }

    @Override
    public void sendAuthKeyToServer(AuthKeyVerify authKey, OnRequestDone<Void> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<Void>(200, null));
    }


    @Override
    public void verifyReport(ReportVerificationModel reportModel, OnRequestDone<String> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<>(200, "YourAythRsisfjkgnjwuj2398432ui4iu24jh"));

    }



    @Override
    public void deleteAds(String token,String adsId, OnRequestDone<Void> onRequestDone) {
        delay(() -> {
            onRequestDone.onResponse(new ResponseModel<>(200, null));
        });

    }

    @Override
    public void promoteAds(String token,String adsId, OnRequestDone<Void> onRequestDone) {
        delay(() -> {
            onRequestDone.onResponse(new ResponseModel<>(200, null));
        });
    }

    @Override
    public void refreshList(String token,OnRequestDone<List<Ads>> onRequestDone) {
       delay(() -> {
           onRequestDone.onResponse(new ResponseModel<>(200, getMockAds()));
       });

    }

    @Override
    public void getNextPsge(String token,int lastItem, OnRequestDone<List<Ads>> onRequestDone) {

       delay(() -> {
           onRequestDone.onResponse(new ResponseModel<>(200, getMockAds()));
       });

    }





    @Override
    public void sendMassageToServer(String token, ContactUsMassagePayLoad massage, OnRequestDone<Void> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<>(200,null));
    }

    @Override
    public void getMassagesHistoryFromBookHub(String token, OnRequestDone<List<AdminMassageReports>> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<>(200,getMocks()));

    }



    @Override
    public void getDeletedAddHistory(String token, OnRequestDone<List<Ads>> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<>(200, getDeletedAds()));
    }

    @Override
    public void getPromotedAdsHistory(String token, OnRequestDone<List<Ads>> onRequestDone) {
        onRequestDone.onResponse(new ResponseModel<>(200, getPromotedAds()));
    }

    private List<Ads> getMockAds() {
        List<Ads> adsList = new ArrayList<>();
        Book book = new Book.BookBuilder()
                .setAuthor("Robert C Martin")
                .setName("Clean code")
                .setNumberOfPages(350)
                .setPrice(3500)
                .setPublisher("انتشارات آرزو")
                .setShabak("ldfksnndfjdvnd")
                .build();

        User user = new User.UserBuilder()
                .Name("Rasam")
                .FamilyName("Ararbzadeh")
                .Location("tehran")
                .PhoneNumber("09193078323")
                .ProfilePicture("")
                .UserId("sjnflkdfjns")
                .build();
        for (int i = 0; i < 20; i++) {
            adsList.add(new Ads(book, "تهران خیابان عشق", user, 1000));
        }

        return adsList;
    }
    private List<Ads> getDeletedAds() {
        List<Ads> adsList = new ArrayList<>();
        Book book = new Book.BookBuilder()
                .setAuthor("Robert C Martin")
                .setName("Clean code")
                .setNumberOfPages(350)
                .setPrice(3500)
                .setShabak("ldfksnndfjdvnd")
                .build();

        User user = new User.UserBuilder()
                .Name("Rasam")
                .FamilyName("Ararbzadeh")
                .Location("tehran")
                .PhoneNumber("09193078323")
                .ProfilePicture("")
                .UserId("sjnflkdfjns")
                .build();
        for (int i = 0; i < 20; i++) {
            adsList.add(new Ads(book, "تهران خیابان عشق", user, 1000));
        }

        return adsList;
    }
    private List<Ads> getPromotedAds() {
        List<Ads> adsList = new ArrayList<>();
        Book book = new Book.BookBuilder()
                .setAuthor("Robert C Martin")
                .setName("Clean code")
                .setNumberOfPages(350)
                .setPrice(3500)
                .setShabak("ldfksnndfjdvnd")
                .build();

        User user = new User.UserBuilder()
                .Name("Rasam")
                .FamilyName("Ararbzadeh")
                .Location("tehran")
                .PhoneNumber("09193078323")
                .ProfilePicture("")
                .UserId("sjnflkdfjns")
                .build();
        for (int i = 0; i < 20; i++) {
            adsList.add(new Ads(book, "تهران خیابان عشق", user, 1000));
        }

        return adsList;
    }

    private List<AdminMassageReports> getMocks(){
        List<AdminMassageReports> mockMassage = new ArrayList<>();
        AdminMassageReports adminMassageReportsRead = new AdminMassageReports("مشکل ارسال دلیل","من  نمی توان دلیل رد آگهی رو اعلام کنم",true,"مشکل حل شد");
        AdminMassageReports adminMassageReportsNotRead = new AdminMassageReports("مشکل ارسال دلیل","من  نمی توان دلیل رد آگهی رو اعلام کنم",false,"مشکل حل شد");

        for (int i = 0;i<10;i++){
            mockMassage.add(adminMassageReportsNotRead);
            mockMassage.add(adminMassageReportsRead);
        }
        return mockMassage;
    }

    public void delay(Runnable runnable){
        new Handler().postDelayed(runnable,1000);
    }

}
