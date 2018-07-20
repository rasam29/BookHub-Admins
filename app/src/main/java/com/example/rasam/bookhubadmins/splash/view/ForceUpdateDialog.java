package com.example.rasam.bookhubadmins.splash.view;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;

import com.example.rasam.bookhubadmins.pojos.UpdateModel;

public class ForceUpdateDialog extends AppCompatDialog {
    private UpdateModel updateModel;
    public ForceUpdateDialog(UpdateModel updateModel, Context context) {
        super(context);
        this.updateModel = updateModel;
    }
}
