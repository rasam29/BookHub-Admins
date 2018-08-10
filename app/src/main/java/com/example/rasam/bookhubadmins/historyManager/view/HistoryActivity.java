package com.example.rasam.bookhubadmins.historyManager.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;
import com.example.rasam.bookhubadmins.R;


public class HistoryActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Switch switchButton = findViewById(R.id.switchButton);
        switchButton.setOnCheckedChangeListener((compoundButton, b) -> {
            if (!b) {
                gotoPromoted();
            } else {
                gotoDeleted();

            }
        });

    }

    private void gotoPromoted() {
     PromotedFragment promotedFragment = new PromotedFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.viewpager, promotedFragment);
        transaction.commit();
    }

    private void gotoDeleted() {

        DeletedFragment fragment = new DeletedFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.viewpager, fragment);
        transaction.commit();
    }

}
