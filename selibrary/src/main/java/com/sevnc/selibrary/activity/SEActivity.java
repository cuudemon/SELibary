package com.sevnc.selibrary.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sevnc.selibrary.R;
import com.sevnc.selibrary.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by QuangVan on 27/12/2017.
 */

public abstract class SEActivity extends AppCompatActivity {

    protected abstract int getLayoutID();

    protected abstract void settingView();

    private AsyncHttpClient adsIdClient;
    protected String id_banner;
    protected String id_full;
    protected String ads_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        id_banner = Utils.getIdBanner(this);
        id_full = Utils.getIdFull(this);
        ads_name = Utils.getAdsName(this);
        getIdAds();
        settingView();
//        ads = new ExitAds(this);

    }

    protected void getIdAds() {
        if(Utils.isConnectingToInternet(this)) {
            adsIdClient = new AsyncHttpClient();
            adsIdClient.get(this, Utils.GET_ADS_ID_URL + getPackageName(), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String str = new String(responseBody);
                        JSONObject obj = new JSONObject(str);
                        String status = obj.getString(Utils.STATUS);
                        if (status.equals("ok")) {
                            JSONArray arr = obj.getJSONArray(Utils.ADVERTISE);
                            JSONObject adsObj = arr.getJSONObject(0);
                            String idBanner = adsObj.getString(Utils.ID_BANNER);
                            String idFull = adsObj.getString(Utils.ID_FULL);
                            String adsName = adsObj.getString(Utils.ADS_NAME);
                            if (!ads_name.equals(adsName))
                                Utils.saveAdsName(SEActivity.this, adsName);
                            if (!id_banner.equals(idBanner))
                                Utils.saveIdBanner(SEActivity.this, idBanner);
                            if (!id_full.equals(idFull))
                                Utils.saveIdFull(SEActivity.this, idFull);

                        } else {
                            Log.e("messeage", "fail to get adsId");
                        }

                    } catch (Exception e) {
                        Log.e("Catch in parse JSON", e.getMessage());
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }
    }

    /**
     * @param text        Mô tả cho link
     * @param packageName packageName của ứng dụng muốn chia sẻ
     */
    protected void shareApp(String text, String packageName) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, text + "\n\n"
                + "https://play.google.com/store/apps/details?id=" + packageName);
        startActivity(emailIntent);
        Log.v("Link", packageName + "");
    }

    /**
     * @param packageName packageName của ứng dụng muốn người dùng đánh giá
     */
    protected void rateApp(String packageName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + packageName));
        Log.v("packageName", packageName);
        startActivity(intent);
    }

    /**
     * @param email Email của người nhận feedback
     */
    protected void feedback(String email) {
        try {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "quangvan08it@gmail.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from " + getResources().getString(R.string.app_name));
            startActivity(Intent.createChooser(emailIntent, "Input Title"));
        } catch (Exception e) {
            Log.e("catch in feedback", e.getMessage() + " error");
        }
    }


}
