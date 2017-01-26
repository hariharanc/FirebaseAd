package com.test.ad;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by hari on 26/1/17.
 */

public class InterstitialActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnInterstitial;
    private InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        mBtnInterstitial = (Button) findViewById(R.id.btn_interstitial);
        mBtnInterstitial.setOnClickListener(this);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_interstitial));
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//
//        // Load ads into Interstitial Ads
//        mInterstitialAd.loadAd(adRequest);

        requestNewInterstitial();


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }
        });


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(unixTime()%2!=0){
                    showInterstitial();
                }
            }
        }, 1000);

    }

    private void requestNewInterstitial() {

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_interstitial:
                showInterstitial();
                break;
        }
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (unixTime() % 2 == 0) {
            finish();
        } else {
            showInterstitial();
        }


    }

    private Long unixTime() {


        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime;

    }
}
