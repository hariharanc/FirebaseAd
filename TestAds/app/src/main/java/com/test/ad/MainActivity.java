package com.test.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnInterstitial,mBtnBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnInterstitial=(Button)findViewById(R.id.btn_interstitial);
        mBtnBanner=(Button)findViewById(R.id.btn_banner);
        mBtnBanner.setOnClickListener(this);
        mBtnInterstitial.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_banner:

                Intent mIntentBanner=new Intent(MainActivity.this,BannerAdActivity.class);
                startActivity(mIntentBanner);

                break;
            case R.id.btn_interstitial:
                Intent mIntentInterstitial=new Intent(MainActivity.this,InterstitialActivity.class);
                startActivity(mIntentInterstitial);
                break;
        }

    }
}
