package com.opening.VibratorControl.vibratorcontrol;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class Modes extends AppCompatActivity {
    Toolbar myToolbar;
    SwitchCompat simpleSwitch1, simpleSwitch2;
    private AdView mAdView;
    //Button b;


    public void store(Boolean b){
        SharedPreferences pref=getSharedPreferences("fil",MODE_PRIVATE);
        SharedPreferences.Editor mEditor= mEditor=pref.edit();
        mEditor.putBoolean("mode",b);
        mEditor.apply();
    }
    public Boolean restore2(){
        SharedPreferences pref= getSharedPreferences("fil",MODE_PRIVATE);
        Boolean s=pref.getBoolean("mode",true);
        return s;
    }

    public void store(){
        SharedPreferences pref=getSharedPreferences("fil",MODE_PRIVATE);
        SharedPreferences.Editor mEditor= mEditor=pref.edit();
        mEditor.putBoolean("hint",true);
        mEditor.apply();
    }
    public Boolean restore(){
        SharedPreferences pref= getSharedPreferences("fil",MODE_PRIVATE);
        Boolean s=pref.getBoolean("hint",false);
        return s;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.rate_us:
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+this.getPackageName())));}
                catch(ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+this.getPackageName())));
                }
                break;
            // action with ID action_settings was selected
            case R.id.get_out:
                startActivity(new Intent(Modes.this,Pop.class));
                break;
            case android.R.id.home:
                onBackPressed();
            case R.id.done:
                Intent intent= new Intent(Modes.this,MainActivity.class);
                startActivity(intent);
            default:
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle(getResources().getString(R.string.third));
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
        simpleSwitch1 = (SwitchCompat) findViewById(R.id.view);
        simpleSwitch2 = (SwitchCompat) findViewById(R.id.view2);

        if(!restore()){
            simpleSwitch2.setChecked(true);store();
        } else {
            if(restore2()){
                simpleSwitch2.setChecked(true);
            }else{
                simpleSwitch1.setChecked(true);
            }
        }



       simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                       store(false);
                        simpleSwitch2.setChecked(false);
                    } else {simpleSwitch2.setChecked(true); store(true);
                    }

                }


            });
        simpleSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    simpleSwitch1.setChecked(false); store(true);
                }else{
                    simpleSwitch1.setChecked(true);store(false);
                }
            }
        });

      /* b=(Button)findViewById(R.id.buttonx);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Modes.this,MainActivity.class);
                startActivity(intent);
            }
        });*/
    }
}
