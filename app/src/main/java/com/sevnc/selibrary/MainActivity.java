package com.sevnc.selibrary;

import android.util.Log;
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

    AsyncHttpClient client;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void settingView() {
        String url1 = "http://api.sevnc.com/feedback/push";
        NetworkAds.BannerAds(this, R.id.ads);
        NetworkAds.loadFullAds(this);


        //feedback
//        client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//        params.put("package_name", getPackageName());
//        params.put("app_name", R.string.app_name);
//        params.put("email", "quangvan08it@gmail.com");
//        params.put("content", "Anh Văn test feedback! 10h41");
//        client.post(this, url1, params, new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//                String str = new String(responseBody);
//                Log.e("feedback ok", str + " se");
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                String str = new String(responseBody);
//                Log.e("fail to feedback", str + " se");
//            }
//        });
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
