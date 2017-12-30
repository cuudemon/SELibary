package com.sevnc.selibrary.ads;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sevnc.selibrary.R;


import java.util.List;

/**
 * Created by QuangVan on 10/10/2017.
 */

public class AdapterAds extends RecyclerView.Adapter<AdapterAds.BackgroundHolder> {
    private Context mContext;
    private List<AdsItems.ResultsBean> mAdsItem;

    public AdapterAds(Context mContext, List<AdsItems.ResultsBean> mAdsItem) {
        this.mContext = mContext;
        this.mAdsItem = mAdsItem;
    }


    @Override
    public BackgroundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ads, null);
        BackgroundHolder backgroundHolder = new BackgroundHolder(view);
        return backgroundHolder;
    }

    @Override
    public void onBindViewHolder(BackgroundHolder holder, int position) {
        Glide.with(mContext).load(mAdsItem.get(position).getAds_banner())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return mAdsItem.size();
    }

    public class BackgroundHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public BackgroundHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imBannerAds);
        }
    }

}