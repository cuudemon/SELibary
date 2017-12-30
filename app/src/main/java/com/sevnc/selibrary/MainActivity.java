package com.sevnc.selibrary;

import com.sevnc.selibrary.activity.SEActivity;
import com.sevnc.selibrary.ads.NetworkAds;

public class MainActivity extends SEActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void settingView() {
        NetworkAds.BannerAds(this, R.id.ads);
        NetworkAds.loadFullAds(this);


    }

    @Override
    public void onBackPressed() {
        NetworkAds.showInterstitialAd(ads_name);
        exitDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
