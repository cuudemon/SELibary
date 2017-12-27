package com.sevnc.selibrary;

import android.util.Log;

import com.sevnc.selibrary.activity.SEActivity;

public class MainActivity extends SEActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void settingView() {
        Log.e("id banner", id_banner);
        Log.e("id full", id_full);
        Log.e("ads_name", ads_name);
    }

}
