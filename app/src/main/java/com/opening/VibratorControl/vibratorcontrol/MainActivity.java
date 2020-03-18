package com.opening.VibratorControl.vibratorcontrol;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Timebase tm;
    private Context context;
    Toolbar myToolbar;
    private AdView mAdView;

    @Override
    public void onClick(View arg0) {
        if (arg0.getId() == R.id.btnTime) {
            Intent intent = new Intent(MainActivity.this, TimeSet.class);
            this.startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.rate_us:
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+ context.getPackageName())));}
                catch(ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+ context.getPackageName())));
                }
                break;
            // action with ID action_settings was selected
            case R.id.get_out:
                startActivity(new Intent(MainActivity.this,Pop.class));
                break;
            default:
                break;
        }

        return true;
    }



    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*MobileAds.initialize(getApplicationContext(), getString(R.string.google_ad_id));
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
         myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setTitle(getResources().getString(R.string.app_name));
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

        tm=new Timebase(this);
        View v2 = findViewById(R.id.btnTime);
        v2.setOnClickListener(this) ;
        //MobileAds.initialize(this, "ca-app-pub-9510471178790935~4550421041");
        if(tm.getData()!=null) {
            this.context=this;
            Alarm.set(this.context);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,8 );
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);




            this.context = this;
            Intent alarm = new Intent(this.context, AlarmReceiverActivity.class);
           boolean alarmRunning = (PendingIntent.getBroadcast(this.context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
            if (alarmRunning == false) {
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this.context,0, alarm,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),3600000, pendingIntent);
            }

        }

    }





    @Override
    protected void onStart() {
        super.onStart();

        // The activity is about to become visible.
    }

    @Override
    protected void onResume() {
        super.onResume();

        // The activity has become visible (it is now "resumed").
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Another activity is taking focus (this activity is about to be "paused").
    }

    @Override
    protected void onStop() {
        super.onStop();

        // The activity is no longer visible (it is now "stopped")
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // The activity is about to be destroyed.
    }
}
