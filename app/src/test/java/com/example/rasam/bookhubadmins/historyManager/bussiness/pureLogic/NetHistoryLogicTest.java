package com.example.rasam.bookhubadmins.historyManager.bussiness.pureLogic;

import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.example.rasam.bookhubadmins.pojos.ads.Book;
import com.example.rasam.bookhubadmins.pojos.ads.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by R.Arabzadeh Taktell on 7/29/2018.
 */
public class NetHistoryLogicTest {
    NetHistoryLogic netHistoryLogic;
    ResponseModel<List<Ads>> responseModel;

    @Before
    public void setUp() throws Exception {
        responseModel = new ResponseModel<List<Ads>>(200,getMockAds());
        netHistoryLogic = new NetHistoryLogic(responseModel);
    }
    public void setUp_NetError(){
        responseModel = new ResponseModel<List<Ads>>(new Throwable());
        netHistoryLogic = new NetHistoryLogic(responseModel);
    }
    public void setUp_EmptyHistory(){
        responseModel = new ResponseModel<List<Ads>>(204, Collections.<Ads>emptyList());
        netHistoryLogic = new NetHistoryLogic(responseModel);
    }


    private List<Ads> getMockAds() {
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


    @Test
    public void requestSuccessFul() throws Exception {
        assertTrue(netHistoryLogic.hasHistory());
        assertTrue(netHistoryLogic.RequestSuccessFul());
    }


    @Test
    public void NetError() throws Exception {
        setUp_NetError();
        assertFalse(netHistoryLogic.RequestSuccessFul());
    }

    @Test
    public void emptyHistory() throws Exception {
        setUp_EmptyHistory();
        assertFalse(netHistoryLogic.hasHistory());
    }

}