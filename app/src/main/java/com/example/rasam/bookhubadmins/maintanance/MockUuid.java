package com.example.rasam.bookhubadmins.maintanance;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by R.Arabzadeh Taktell on 3/14/2018.
 */

public class MockUuid {

    private static String mockUuid;



    private MockUuid(){}

    public static String getUuid(Context context){


        if (mockUuid ==null){
            mockUuid = Build.BOARD +
                    Build.getRadioVersion()+
                    Build.PRODUCT+
                    Build.TIME+
                    Build.CPU_ABI+
                    Build.VERSION.CODENAME+
                    Build.VERSION.INCREMENTAL+

                    Settings.Secure.getString(context.getContentResolver(),
                            Settings.Secure.ANDROID_ID);

        }
        return mockUuid;

    }



}