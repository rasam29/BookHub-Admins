package com.example.rasam.bookhubadmins.productInfo.bussiness;

import com.example.rasam.bookhubadmins.maintanance.abstractions.OnIntractor;
import com.example.rasam.bookhubadmins.pojos.ads.Ads;

/**
 * Created by R.Arabzadeh Taktell on 7/23/2018.
 */

public interface MoreDetailsIntractorFacade {
    void loadCach(OnIntractor<Ads> onIntractor);
    void promote(Ads ads,OnIntractor<Void> onIntractor);
    void delete(Ads ads,OnIntractor<Void> onIntractor);
}

