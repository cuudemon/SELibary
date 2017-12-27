package com.sevnc.selibrary.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sevnc.selibrary.R;

/**
 * Created by QuangVan on 27/12/2017.
 */

public abstract class SEActivity extends AppCompatActivity{

    protected abstract int getLayoutID();

    protected abstract void settingView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        settingView();
//        ads = new ExitAds(this);

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
    protected void feedback(String email){
        try {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "quangvan08it@gmail.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from " + getResources().getString(R.string.app_name));
            startActivity(Intent.createChooser(emailIntent, "Input Title"));
        } catch (Exception e) {
            Log.e("catch in feedback", e.getMessage() + " error");
        }
    }


}
