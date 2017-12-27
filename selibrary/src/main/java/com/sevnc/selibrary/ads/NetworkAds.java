package com.sevnc.selibrary.ads;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * Created by QuangVan on 27/12/2017.
 */

public class NetworkAds {

    public static void AdsBanner(final Context mContext, final int layout_id, String ads_name, String id_bannner){
        if(ads_name.equals("admob")){
            try {
                final AdView mAdView = new AdView((Activity) mContext);
                mAdView.setAdSize(AdSize.SMART_BANNER);
                mAdView.setAdUnitId(id_bannner);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);
                mAdView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        ((LinearLayout) ((Activity) mContext).findViewById(layout_id))
                                .removeView(mAdView);
                        ((LinearLayout) ((Activity) mContext).findViewById(layout_id))
                                .addView(mAdView);
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(ads_name.equals("facebook")){



            /*
            adView = new AdView(this, getResources().getString(R.string.bannerPlacementID), AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.admob);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();
             */
        }

    }
}
