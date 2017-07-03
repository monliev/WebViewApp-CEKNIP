package com.infotechnodev.ceknippns;

/**
 * Created by monliev on 2/26/16.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;


/**
 * Created by Monliev on 7/27/2015.
 */

public class Main extends Activity {

    private InterstitialAd interstitial;
    private AdView mAdView;
    private FirebaseAnalytics mFirebaseAnalytics;
    ImageButton imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        interstitial=new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-2953879827169015/9101129480"); // Interstitial Image gallery 1
        //interstitial.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // test AdUnit
        interstitial.loadAd(adRequest);
        //displayMyAd();

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        imgButton =(ImageButton)findViewById(R.id.imageButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToButton1();
                // Handler which will run after 2 seconds.
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(Main.this,
                                "Masukkan NIP Anda :",
                                Toast.LENGTH_LONG).show();
                    }
                }, 5000);
            }
        });

    }

    public void displayMyAd() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    protected void sendToButton1() {
        Intent intent1 = new Intent (this, MainActivity.class);
        startActivity(intent1);
        displayMyAd();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Selesai & Keluar")
                .setMessage("Apakah Anda Ingin Keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Tidak", null)
                .show();
    }

}

