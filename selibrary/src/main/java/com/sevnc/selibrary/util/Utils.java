package com.sevnc.selibrary.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by QuangVan on 27/12/2017.
 */

public class Utils {
    public static String GET_ADS_ID_URL = "http://service.sevnc.com/api/get-ads?package_name=";
    public static String ADVERTISE = "advertise";
    public static String ADS_NAME = "ads_name";
    public static String STATUS = "status";
    public static String ID_BANNER = "id_banner";
    public static String ID_FULL = "id_full";


    public static String getAdsName(Context mContext) {
        SharedPreferences pre = mContext.getSharedPreferences(ADS_NAME, mContext.MODE_PRIVATE);
        String ads_name;
        ads_name = pre.getString(ADS_NAME, "null");
        Log.e("get ads_name", ads_name);
        return ads_name;
    }

    public static void saveAdsName(Context mContext, String adsName) {
        SharedPreferences pre = mContext.getSharedPreferences(ADS_NAME, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putString(ADS_NAME, adsName);
        editor.commit();
        Log.e("save ads_name", adsName);
    }

    public static String getIdBanner(Context mContext) {
        SharedPreferences pre = mContext.getSharedPreferences(ID_BANNER, mContext.MODE_PRIVATE);
        String id_banner;
        id_banner = pre.getString(ID_BANNER, "null");
        Log.e("get id_banner", id_banner);
        return id_banner;
    }

    public static void saveIdBanner(Context mContext, String id_banner) {
        SharedPreferences pre = mContext.getSharedPreferences(ID_BANNER, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putString(ID_BANNER, id_banner);
        editor.commit();
        Log.e("save id_banner", id_banner);
    }

    public static String getIdFull(Context mContext) {
        SharedPreferences pre = mContext.getSharedPreferences(ID_FULL, mContext.MODE_PRIVATE);
        String id_full;
        id_full = pre.getString(ID_FULL, "null");
        Log.e("get id_full", id_full);
        return id_full;
    }

    public static void saveIdFull(Context mContext, String id_full) {
        SharedPreferences pre = mContext.getSharedPreferences(ID_FULL, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putString(ID_FULL, id_full);
        editor.commit();
        Log.e("save id_full", id_full);
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    /*

    status: "ok",
error_code: 0,
developer: "Software Evolution",
message: "Success!",
advertise: [
{
ads_name: "admob",
id_banner: "ca-app-pub-6771983271710470/4246976826",
id_full: "ca-app-pub-6771983271710470/3480690067",
ads_id: "1",
status: "1"
}
],
     */
}
