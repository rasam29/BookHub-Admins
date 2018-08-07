package com.example.rasam.bookhubadmins.contactUs.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import com.example.rasam.bookhubadmins.R;
import com.example.rasam.bookhubadmins.contactUs.view.contact_us.ContactUsFragment;
import com.example.rasam.bookhubadmins.contactUs.view.history.ReportsHistory;
import com.example.rasam.bookhubadmins.contactUs.view.utils.ContactusPagerAdapter;

import java.util.ArrayList;

public class ContactUsActivity extends AppCompatActivity{

    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        viewPager = findViewById(R.id.viewpager);
        ContactusPagerAdapter adapter = new ContactusPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
//
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


}
