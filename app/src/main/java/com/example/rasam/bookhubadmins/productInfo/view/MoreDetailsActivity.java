package com.example.rasam.bookhubadmins.productInfo.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.rasam.bookhubadmins.R;
import com.example.rasam.bookhubadmins.maintanance.parent.ParentActivity;
import com.example.rasam.bookhubadmins.productInfo.presenter.MoreDependency;
import com.example.rasam.bookhubadmins.productInfo.presenter.MoreDetailsPresenter;
import com.example.rasam.bookhubadmins.productInfo.presenter.MoreDetailsState;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by R.Arabzadeh Taktell on 7/23/2018.
 */

public class MoreDetailsActivity extends ParentActivity<MoreDetailsView, MoreDetailsPresenter> implements MoreDetailsView {
    @Override
    protected MoreDetailsPresenter stablishPresenter() {
        return new MoreDetailsPresenter(MoreDependency.inject(), this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);
        presenter.getAdvertisment();

    }

    @Override
    public void render(MoreDetailsState state) {
        if (state instanceof MoreDetailsState.DeleteState) {
            Toast.makeText(this, "آگهی حذف شد", Toast.LENGTH_SHORT).show();
            finish();
        } else if (state instanceof MoreDetailsState.LoadCachState) {
            Toast.makeText(this, "loaded", Toast.LENGTH_SHORT).show();
        } else if (state instanceof MoreDetailsState.NetError) {
            Toast.makeText(this, "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();

        } else if (state instanceof MoreDetailsState.PromoteState) {
            Toast.makeText(this, "آگهی با موفقیت تایید شد", Toast.LENGTH_SHORT).show();
            finish();

        }
    }


}
