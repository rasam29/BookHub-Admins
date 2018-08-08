package com.example.rasam.bookhubadmins.contactUs.view.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rasam.bookhubadmins.TestFragment;
import com.example.rasam.bookhubadmins.contactUs.view.contact_us.ContactUsFragment;
import com.example.rasam.bookhubadmins.contactUs.view.history.ReportsHistory;

import java.util.ArrayList;
import java.util.List;

public class ContactusPagerAdapter extends FragmentStatePagerAdapter {


    public ContactusPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment =  new ContactUsFragment();
                break;
            case 1:
                fragment = new ReportsHistory() ;
                break;


        }

        return fragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "ارسال پیام";
            case 1:
                return "تاریخچه";
            default:
                return null;
        }
    }



    @Override
    public int getCount() {
        return 2;
    }

}
