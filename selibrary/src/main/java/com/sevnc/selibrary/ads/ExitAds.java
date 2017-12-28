package com.sevnc.selibrary.ads;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;

import com.sevnc.selibrary.R;

/**
 * Created by QuangVan on 28/12/2017.
 */

public class ExitAds {
    private Context mContext;
    private Dialog dialogAds;


    public ExitAds(final Context mContext){
        this.mContext = mContext;
        dialogAds = new Dialog(mContext);
        dialogAds.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogAds.setContentView(R.layout.dialog_exit_fb);

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
        dialogAds.show();
    }

}
