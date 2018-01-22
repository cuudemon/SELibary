package com.sevnc.selibrary;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sevnc.selibrary.activity.SEActivity;
import com.sevnc.selibrary.ads.NetworkAds;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends SEActivity {

    Button mFeedback;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void settingView() {
        NetworkAds.BannerAds(this, R.id.ads);
        NetworkAds.loadFullAds(this);

        mFeedback = (Button) findViewById(R.id.feedBack);
        mFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbackDialog̣();
            }
        });

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
