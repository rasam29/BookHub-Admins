package com.example.rasam.bookhubadmins.contactUs.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;


import com.example.rasam.bookhubadmins.R;
import com.example.rasam.bookhubadmins.contactUs.view.contact_us.ContactUsFragment;
import com.example.rasam.bookhubadmins.contactUs.view.history.ReportsHistory;
import com.example.rasam.bookhubadmins.contactUs.view.utils.ContactusPagerAdapter;

import java.util.ArrayList;

public class ContactUsActivity extends AppCompatActivity{

    LinearLayout report;
    LinearLayout contactUs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        report = findViewById(R.id.report);
        contactUs = findViewById(R.id.contactUS);


        report.setOnClickListener(view -> {
            ReportsHistory fragment = new ReportsHistory();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.viewpager, fragment);
            transaction.commit();
        });

        contactUs.setOnClickListener(view -> {

            ContactUsFragment fragment = new ContactUsFragment();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.viewpager, fragment);
            transaction.commit();




        });

    }








}
