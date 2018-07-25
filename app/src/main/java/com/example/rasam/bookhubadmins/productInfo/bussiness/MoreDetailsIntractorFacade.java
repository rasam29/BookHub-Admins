package com.example.rasam.bookhubadmins.productInfo.bussiness;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;
import com.example.rasam.bookhubadmins.productInfo.presenter.MoreDetailsState;

/**
 * Created by R.Arabzadeh Taktell on 7/23/2018.
 */

public interface MoreDetailsIntractorFacade {
    void loadCach(OnIntractor<MoreDetailsState> onIntractor);
    void promote(OnIntractor<MoreDetailsState> onIntractor);
    void delete(OnIntractor<MoreDetailsState> onIntractor);
}

