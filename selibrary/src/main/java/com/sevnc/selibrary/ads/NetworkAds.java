package com.sevnc.selibrary.ads;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.sevnc.selibrary.util.Utils;

/**
 * Created by QuangVan on 27/12/2017.
 */

public class NetworkAds {

    private static InterstitialAd interstitialAd;
    private static com.facebook.ads.InterstitialAd fbinterstitialAd;


    public static void BannerAds(final Context mContext, final int layout_id) {
        String ads_name = Utils.getAdsName(mContext);
        if (ads_name.equals("admob")) {
            try {
                final AdView mAdView = new AdView((Activity) mContext);
                mAdView.setAdSize(AdSize.SMART_BANNER);
                mAdView.setAdUnitId(Utils.getIdBanner(mContext));
                final AdRequest adRequest = new AdRequest.Builder().build();
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
                        BannerAds(mContext, layout_id);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ads_name.equals("facebook")) {

            try {
                com.facebook.ads.AdView fbAdView = new com.facebook.ads.AdView(mContext, Utils.getIdBanner(mContext), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                LinearLayout adContainer = (LinearLayout) ((Activity) mContext).findViewById(layout_id);
                adContainer.addView(fbAdView);
                fbAdView.loadAd();
            } catch (Exception e) {
                Log.e("catch in load fbAds", e.getMessage());
            }
        }

    }


    public static void loadFullAds(Context mContext) {
        String ads_name = Utils.getAdsName(mContext);
        if (ads_name.equals("admob")) {
            interstitialAd = new InterstitialAd(mContext);
            interstitialAd.setAdUnitId(Utils.getIdFull(mContext));
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        } else {
            fbinterstitialAd = new com.facebook.ads.InterstitialAd(mContext, Utils.getIdFull(mContext));
            fbinterstitialAd.setAdListener(new AbstractAdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    super.onError(ad, adError);
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    super.onAdLoaded(ad);
                }

                @Override
                public void onAdClicked(Ad ad) {
                    super.onAdClicked(ad);
                }
            });
            fbinterstitialAd.loadAd();
        }

    }

    public static void showInterstitialAd(String ads_name) {
        try {
            if (ads_name.equals("admob")) {
                if (interstitialAd.isLoaded())
                    interstitialAd.show();
            } else {
                if (fbinterstitialAd.isAdLoaded())
                    fbinterstitialAd.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
