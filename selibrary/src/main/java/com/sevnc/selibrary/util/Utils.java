package com.sevnc.selibrary.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by QuangVan on 27/12/2017.
 */

public class Utils {
    public static String GET_ADS_ID_URL = "Ey5w0OaNkotlqXIaZ8jpkRhyTgCTeAOJbRGj1E1k1UJdo4icqTwr6MR39lhpAF0gkFtqsJwVnAR2HbN+W+zLOQ==";
    public static String POST_FEEDBACK_URL = "";
    public static String ITEMS = "items";
    public static String ADS_NAME = "ads_name";
    public static String STATUS = "status";
    public static String ID_BANNER = "id_banner";
    public static String ID_FULL = "id_full";


    public static String getAdsName(Context mContext) {
        SharedPreferences pre = mContext.getSharedPreferences(ADS_NAME, mContext.MODE_PRIVATE);
        String ads_name;
        ads_name = pre.getString(ADS_NAME, "admob");
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



    // Set font
    public static void setFontsTextView(Activity mContext, TextView mTextView,
                                        String fontName) {
        try {
            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "fonts/"
                    + fontName);
            mTextView.setTypeface(type);
        } catch (Exception e) {
            Log.e("Lỗi setFontsTextView", e.getMessage());
        }

    }

    public static void setFontsEditText(Activity mContext, EditText mEditText,
                                        String fontName) {
        try {
            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "fonts/"
                    + fontName);
            mEditText.setTypeface(type);
        } catch (Exception e) {
            Log.e("Lỗi setFontsTextView", e.getMessage());
        }

    }


    public static void setFontsButton(Activity mContext, Button mButton,
                                      String fontName) {
        try {
            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "fonts"
                    + fontName);
            mButton.setTypeface(type);
        } catch (Exception e) {
            Log.e("Lỗi setFontsButton", e.getMessage());
        }

    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static void onClickCopy(Context mContext, String str, String toast) {
        // User-defined onClick Listener
        try {
            int sdk_Version = android.os.Build.VERSION.SDK_INT;
            if (sdk_Version < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) mContext
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(str); // Assuming that you are copying the text
                // from a TextView
                Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) mContext
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText("aloapp", str);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
            }
            Log.v("", "Copy nội dung.");
        } catch (Exception e) {
            Log.e("Lỗi onClickCopy", e.getMessage());
        }


    }
}
