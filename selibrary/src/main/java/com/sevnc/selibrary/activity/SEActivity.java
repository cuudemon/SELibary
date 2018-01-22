package com.sevnc.selibrary.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sevnc.selibrary.R;
import com.sevnc.selibrary.ads.ExitAds;
import com.sevnc.selibrary.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.sevnc.selibrary.ads.AESDecrypt.decrypt;

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
    private ExitAds ads;

    private Dialog feedbackDialog;

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
        ads = new ExitAds(this);

    }

    protected void getIdAds() {
        if (Utils.isConnectingToInternet(this)) {
            adsIdClient = new AsyncHttpClient();

            try {
                final String url = decrypt("Ey5w0OaNkotlqXIaZ8jpkRhyTgCTeAOJbRGj1E1k1UJdo4icqTwr6MR39lhpAF0gkFtqsJwVnAR2HbN+W+zLOQ==") + getPackageName() + "&api_key=" + getString(R.string.SE_API_KEY);
                adsIdClient.get(this, url, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.e("url", url);
                        try {
                            String str = new String(responseBody);
                            Log.e("_ok_", str);
                            JSONObject obj = new JSONObject(str);
                            String status = obj.getString(Utils.STATUS);
                            if (status.equals("ok")) {
                                JSONArray arr = obj.getJSONArray(Utils.ITEMS);
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
                                Log.e("messeage", "fail to parse json adsId");
                            }

                        } catch (Exception e) {
                            Log.e("Catch in parse JSON", e.getMessage());
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("get id ads", "Fail to get  adsID");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    protected void exitDialog() {
        if (Utils.isConnectingToInternet(this))
            ads.showExitDialog();
        else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(getBaseContext(), getString(R.string.back_again), Toast.LENGTH_SHORT).show();
            }
            mBackPressed = System.currentTimeMillis();
        }
    }

    int star;

    protected void feedbackDialog̣() {
        if (Utils.isConnectingToInternet(this)) {
            star = 0;
            feedbackDialog = new Dialog(this);
            feedbackDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            feedbackDialog.setContentView(R.layout.feedback_dialog);
            feedbackDialog.setCanceledOnTouchOutside(false);
            feedbackDialog.setCancelable(false);
            final EditText content = (EditText) feedbackDialog.findViewById(R.id.feedbackContent);
            final EditText name = (EditText) feedbackDialog.findViewById(R.id.name);
            final EditText email = (EditText) feedbackDialog.findViewById(R.id.email);
            final ImageView ic01 = (ImageView) feedbackDialog.findViewById(R.id.ic01);
            final ImageView ic02 = (ImageView) feedbackDialog.findViewById(R.id.ic02);
            final ImageView ic03 = (ImageView) feedbackDialog.findViewById(R.id.ic03);
            final ImageView ic04 = (ImageView) feedbackDialog.findViewById(R.id.ic04);
            final ImageView ic05 = (ImageView) feedbackDialog.findViewById(R.id.ic05);
            LinearLayout send = (LinearLayout) feedbackDialog.findViewById(R.id.sendButton);
            LinearLayout exit = (LinearLayout) feedbackDialog.findViewById(R.id.exit);
            final ProgressBar progressBar = (ProgressBar) feedbackDialog.findViewById(R.id.progressBar);

            ic01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (star != 1) {
                        star = 1;
                        ic01.setImageResource(R.mipmap.ic1);
                        ic02.setImageResource(R.mipmap.ic02);
                        ic03.setImageResource(R.mipmap.ic03);
                        ic04.setImageResource(R.mipmap.ic04);
                        ic05.setImageResource(R.mipmap.ic05);
                    }

                }
            });

            ic02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (star != 2) {
                        star = 2;
                        ic01.setImageResource(R.mipmap.ic01);
                        ic02.setImageResource(R.mipmap.ic2);
                        ic03.setImageResource(R.mipmap.ic03);
                        ic04.setImageResource(R.mipmap.ic04);
                        ic05.setImageResource(R.mipmap.ic05);
                    }

                }
            });

            ic03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (star != 3) {
                        star = 3;
                        ic01.setImageResource(R.mipmap.ic01);
                        ic02.setImageResource(R.mipmap.ic02);
                        ic03.setImageResource(R.mipmap.ic3);
                        ic04.setImageResource(R.mipmap.ic04);
                        ic05.setImageResource(R.mipmap.ic05);
                    }

                }
            });

            ic04.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (star != 4) {
                        star = 4;
                        ic01.setImageResource(R.mipmap.ic01);
                        ic02.setImageResource(R.mipmap.ic02);
                        ic03.setImageResource(R.mipmap.ic03);
                        ic04.setImageResource(R.mipmap.ic4);
                        ic05.setImageResource(R.mipmap.ic05);
                    }

                }
            });

            ic05.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (star != 5) {
                        star = 5;
                        ic01.setImageResource(R.mipmap.ic01);
                        ic02.setImageResource(R.mipmap.ic02);
                        ic03.setImageResource(R.mipmap.ic03);
                        ic04.setImageResource(R.mipmap.ic04);
                        ic05.setImageResource(R.mipmap.ic5);
                    }

                }
            });


            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (content.getText().toString().trim().isEmpty()) {
                        showToastUnder(content);
                        requestFocus(content);
                    } else if (name.getText().toString().trim().isEmpty()) {
                        showToastUnder(name);
                        requestFocus(name);

                    } else if (email.getText().toString().trim().isEmpty()) {
                        showToastUnder(email);
                        requestFocus(email);
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {

                        int location[] = new int[2];
                        Toast toast = Toast.makeText(v.getContext(),
                                getString(R.string.email_error), Toast.LENGTH_SHORT);
                        email.getLocationOnScreen(location);
                        toast.setGravity(Gravity.TOP | Gravity.RIGHT, email.getRight(), location[1] - 10);
                        toast.show();
                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                        AsyncHttpClient client;
                        client = new AsyncHttpClient();
                        RequestParams params = new RequestParams();
                        params.put("package_name", getPackageName());
                        params.put("app_name", R.string.app_name);
                        params.put("name", name.getText().toString());
                        params.put("star", star);
                        params.put("email", email.getText().toString());
                        params.put("content", content.getText().toString());
                        params.put("api_key", getString(R.string.SE_API_KEY));
                        client.post(SEActivity.this, Utils.POST_FEEDBACK_URL, params, new AsyncHttpResponseHandler() {

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                                String str = new String(responseBody);
                                Log.e("feedback ok", str + " se");

                                Toast toast = Toast.makeText(SEActivity.this,
                                        getString(R.string.thank_you), Toast.LENGTH_SHORT);
                                toast.show();
                                progressBar.setVisibility(View.GONE);
                                feedbackDialog.dismiss();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                String str = new String(responseBody);
                                Log.e("fail to feedback", str + " se");
                                Toast toast = Toast.makeText(SEActivity.this,
                                        getString(R.string.thank_you), Toast.LENGTH_SHORT);
                                toast.show();
                                progressBar.setVisibility(View.GONE);
                                feedbackDialog.dismiss();
                            }
                        });

                        hideSoftKeyboard(SEActivity.this);
                    }
                }
            });


            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    feedbackDialog.dismiss();
                    hideSoftKeyboard(SEActivity.this);
                }
            });

            feedbackDialog.show();
        } else {
            Toast toast = Toast.makeText(SEActivity.this,
                    getString(R.string.connect_to_internet), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public static void showToastUnder(View v) {

        int location[] = new int[2];
        Toast toast = Toast.makeText(v.getContext(),
                v.getContentDescription(), Toast.LENGTH_SHORT);
        v.getLocationOnScreen(location);
        toast.setGravity(Gravity.TOP | Gravity.RIGHT, v.getRight(), location[1] - 10);
        toast.show();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public static void showKeyboard(Activity activity) {
        if (activity != null) {
            activity.getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void hideSoftKeyboard(Activity activity) {
        try {
            @SuppressWarnings("static-access")
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("catch in hidekeyboard", e.getMessage() + " error");
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
