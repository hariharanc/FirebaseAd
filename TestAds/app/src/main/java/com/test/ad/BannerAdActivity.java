package com.test.ad;

import android.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class BannerAdActivity extends AppCompatActivity {

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");


        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.adView);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.

        mAdView.loadAd(adRequest);
        mAdView.setVisibility(View.GONE);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.i("MainActivity","Main onAdClosed Successfully");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.i("MainActivity","MainAd onAdFailedToLoad Successfully");
                mAdView.setVisibility(View.GONE);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.i("MainActivity","MainAd onAdOpened Successfully");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
                Log.i("MainActivity","MainAd onAdLoaded Successfully");
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.i("MainActivity","MainAd onAdLeftApplication Successfully");
            }
        });

    }
    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

}