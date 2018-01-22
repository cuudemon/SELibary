package com.sevnc.selibrary.ads;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sevnc.selibrary.R;
import com.sevnc.selibrary.util.Utils;

import java.util.ArrayList;
import java.util.Random;

import cz.msebera.android.httpclient.Header;

/**
 * Created by QuangVan on 28/12/2017.
 */

public class ExitAds {
    private Context mContext;
    private Dialog dialogAds;
    private String url = "";
    private Gson gson;
    private AsyncHttpClient client;
    private AdsItems getAdsObj;
    public static ArrayList<AdsItems.ItemsBean> ads = new ArrayList<AdsItems.ItemsBean>();
    ArrayList<AdsItems.ItemsBean> focus_ads = new ArrayList<AdsItems.ItemsBean>();


    public ExitAds(final Context mContext) {
        this.mContext = mContext;
        dialogAds = new Dialog(mContext);
        dialogAds.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogAds.setContentView(R.layout.dialog_exit_fb);

        if (Utils.isConnectingToInternet(mContext)) {
            url = "http://api.sevnc.com/advertise/get-advertise-crossover?package_name=" + mContext.getPackageName() + "&part=snippet&api_key=" + mContext.getString(R.string.SE_API_KEY);
            client = new AsyncHttpClient();
            client.get(mContext, url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String str = new String(responseBody);
//                    Log.e("get ads ok", str);
                        gson = new Gson();
                        getAdsObj = gson.fromJson(str, AdsItems.class);


                        if (getAdsObj.getItems().size() > 0) {
                            Log.e("Tổng số quảng cáo", getAdsObj.getItems().size() + " se");
                            ArrayList<AdsItems.ItemsBean> temp = new ArrayList<AdsItems.ItemsBean>();

                            temp.addAll(getAdsObj.getItems());
                            ads.clear();
                            focus_ads.clear();
                            for (int i = 0; i < temp.size(); i++) {
                                if (!isPackageInstalled(temp.get(i).getPackage_name(), mContext) && temp.get(i).getPriority().equals("0")) {
                                    ads.add(new AdsItems.ItemsBean(temp.get(i).getCat_int(), temp.get(i).getCategory(), temp.get(i).getApp_type(),
                                            temp.get(i).getType(), temp.get(i).getPackage_name(), temp.get(i).getTitle(), temp.get(i).getIcon(),
                                            temp.get(i).getAds_banner(), temp.get(i).getAds_priority_banner(), temp.get(i).getI18n_lang(),
                                            temp.get(i).getDeveloper(),
                                            temp.get(i).getCreated(), temp.get(i).getMarket_url(), temp.get(i).getPriority()));
                                } else if (!isPackageInstalled(temp.get(i).getPackage_name(), mContext) && temp.get(i).getPriority().equals("1")) {
                                    focus_ads.add(new AdsItems.ItemsBean(temp.get(i).getCat_int(), temp.get(i).getCategory(), temp.get(i).getApp_type(),
                                            temp.get(i).getType(), temp.get(i).getPackage_name(), temp.get(i).getTitle(), temp.get(i).getIcon(),
                                            temp.get(i).getAds_banner(), temp.get(i).getAds_priority_banner(), temp.get(i).getI18n_lang(),
                                            temp.get(i).getDeveloper(),
                                            temp.get(i).getCreated(), temp.get(i).getMarket_url(), temp.get(i).getPriority()));
                                }
                            }
                        }
                        if (ads.size() > 0) {
                            try {
                                TextView tvAds = (TextView) dialogAds.findViewById(R.id.tvAds);
                                tvAds.setText(mContext.getResources().getString(R.string.exitTile_on));
                                LinearLayout list = (LinearLayout) dialogAds.findViewById(R.id.listApps);
                                list.setVisibility(View.VISIBLE);
                                RecyclerView listApps = (RecyclerView) dialogAds.findViewById(R.id.recycler);
                                AdapterAds adapterAdsFb = new AdapterAds(mContext, ads);
                                listApps.setHasFixedSize(false);
                                LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                listApps.setItemAnimator(new DefaultItemAnimator());
                                DividerItemDecoration itemDecorator = new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL);
                                itemDecorator.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.divider));
                                listApps.addItemDecoration(itemDecorator);
                                listApps.setAdapter(adapterAdsFb);
                                listApps.setLayoutManager(mLayoutManager);
                                listApps.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        openLink(mContext, ads.get(position).getPackage_name());
                                    }
                                }));
                            } catch (Exception e) {
                                Log.e("catch in bannerAds", e.getMessage() + " se");
                            }
                        }

                        if (focus_ads.size() > 0) {
                            try {
                                Random ran = new Random();
                                final int inter = ran.nextInt(focus_ads.size());
                                LinearLayout llFocusAds = (LinearLayout) dialogAds.findViewById(R.id.focus_ads);
                                llFocusAds.setVisibility(View.VISIBLE);
                                ImageView imFocusAds = (ImageView) dialogAds.findViewById(R.id.imFocus_Ads);
                                Glide.with(mContext).load(focus_ads.get(inter).getAds_priority_banner())
                                        .crossFade()
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .fitCenter()
                                        .into(imFocusAds);

                                llFocusAds.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        openLink(mContext, focus_ads.get(inter).getPackage_name());
                                    }
                                });
                            } catch (Exception e) {
                                Log.e("catch in focusAds", e.getMessage() + " se");
                            }

                        }

                    }catch (Exception e){
                        Log.e("catch onSuccess getADS", e.getMessage());
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.e("fail to get ads", "get ads fail");
                }
            });


        }

        LinearLayout btnYes = (LinearLayout) dialogAds.findViewById(R.id.btnYes);
        LinearLayout btnNo = (LinearLayout) dialogAds.findViewById(R.id.btnNo);


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp(mContext);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAds.dismiss();
            }
        });

    }

    private void exitApp(Context mContext) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        System.exit(0);
    }

    public void showExitDialog() {
        if (ads.size() == 0 && focus_ads.size() == 0)
            exitApp(mContext);
        else
            dialogAds.show();
    }

    protected void openLink(Context mContext, String packageName) {
        try {
            Log.d("onClick Item", "click Quang Cao");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            Log.v("namePakage", packageName);
            mContext.startActivity(intent);
        } catch (Exception e) {
            Log.e("catch in openLink", "catch in openLink");
        }
    }

    private boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;

        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
