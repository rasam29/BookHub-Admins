package com.example.rasam.bookhubadmins.contactUs.view.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rasam.bookhubadmins.contactUs.view.contact_us.ContactUsFragment;
import com.example.rasam.bookhubadmins.contactUs.view.history.ReportsHistory;

import java.util.ArrayList;
import java.util.List;

public class ContactusPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();

    public ContactusPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new ContactUsFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new ReportsHistory();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
    }
}
