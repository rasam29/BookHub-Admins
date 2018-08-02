package com.example.rasam.bookhubadmins.historyManager.bussiness.pureLogic;

import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager;
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.ResponseModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by R.Arabzadeh Taktell on 7/28/2018.
 */
public class ManipulateHistoryLogicTest {
    ResponseModel<Void> responseModel;
    ManipulateHistoryLogic manipulateHistoryLogic;
    @Before
    public void setUp() throws Exception {
        responseModel = new ResponseModel<>(200,null);
    }

    public void stressCondition(){
        responseModel = new ResponseModel<>(new Throwable());

    }

    @Test
    public void isAdvertismentDeleted_OK() throws Exception {
        manipulateHistoryLogic = new ManipulateHistoryLogic(responseModel);
        assertTrue(manipulateHistoryLogic.isAdvertismentDeleted());

    }

    @Test
    public void isAdvertismentDeleted_NetError() throws Exception {
        stressCondition();
        manipulateHistoryLogic = new ManipulateHistoryLogic(responseModel);
        assertFalse(manipulateHistoryLogic.isAdvertismentDeleted());
    }

}