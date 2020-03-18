package com.opening.VibratorControl.vibratorcontrol;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class TimeSet extends AppCompatActivity implements View.OnClickListener{

    private static final int idarray[] = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8,
            R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19};

    Glass[] starck = {new Glass(8), new Glass(8.5), new Glass(9), new Glass(9.5), new Glass(10), new Glass(10.5), new Glass(11), new Glass(11.5), new Glass(12), new Glass(12.5),
            new Glass(13), new Glass(13.5), new Glass(14), new Glass(14.5), new Glass(15), new Glass(15.5), new Glass(16), new Glass(16.5), new Glass(17), new Glass(17.5)};

    double[] T = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private Button[] button = new Button[idarray.length];
    Timebase db;
    Toolbar myToolbar;
    private AdView mAdView;
    public void store(String s){
        SharedPreferences pref=getSharedPreferences("fil",MODE_PRIVATE);
        SharedPreferences.Editor mEditor= mEditor=pref.edit();
        mEditor.putString("map",s);
        mEditor.apply();
    }
    public String restore(){
        SharedPreferences pref= getSharedPreferences("fil",MODE_PRIVATE);
        String s=pref.getString("map","00000000000000000000");
        return s;
    }
    public String conc(){
        int i=0,ind=0;
        String s="";
        for(i=0;i<20;i++){
            if(starck[i].index==true){
                ind=1;
            }else{
                ind=0;
            }
            s=s+Integer.toString(ind);
        }
        return s;


    }

    public Boolean Save() {
        int i = 0, j = 0,k=0;
        Boolean res=true;
        double x = 0;
        db.deleteData();
        while((res==true)&&(i <= T.length - 1)){
            x = T[i];
            while ((T[i]!= 0) && (i <T.length)) {
                k=i;
                i++;
                if(i==T.length){break;
                }}
            if (x!= 0) {
                j++;
                if(T[k]!=x){
                    db.addData(j, x, T[k]);} else{
                    res=false;
                    db.deleteData();
                }
            }
            i++;
        }
        return res;
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
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+this.getPackageName())));}
               catch(ActivityNotFoundException e){
                   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+this.getPackageName())));
               }
                break;
            // action with ID action_settings was selected
            case R.id.get_out:
                startActivity(new Intent(TimeSet.this,Pop.class));
                break;
            case android.R.id.home:
                onBackPressed();
            default:
                break;
        }

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle(getResources().getString(R.string.second));
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=new Timebase(this);
        MobileAds.initialize(this,"ca-app-pub-7836764191778851~7084437581");
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
        for (i = 0; i <= idarray.length - 1; i++) {
            button[i] = (Button) findViewById(idarray[i]);
            button[i].setOnClickListener(this);
            char j = restore().charAt(i);
            if (j == '1') {
                button[i].setBackgroundColor(Color.rgb(119,187,46));
                T[i] = starck[i].t;
                starck[i].index = true;
            }
            Button v = (Button)findViewById(R.id.button2);
            v.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button2) {
            store(conc());
            Save();
            if(Save()==false){
                Toast.makeText(TimeSet.this, "please choose a valid interval", Toast.LENGTH_SHORT).show();}
            else{
                /*Cursor cursor=db.getData();
                if( cursor != null && cursor.moveToFirst() ){
                    String num = cursor.getString(cursor.getColumnIndex("on")); cursor.close();
                Toast.makeText(TimeSet.this,"hey"+num, Toast.LENGTH_SHORT).show();}*/
                Intent intent = new Intent(TimeSet.this, Modes.class);
                this.startActivity(intent);}
        } else {
            switch (v.getId()) {
                case R.id.btn0:;
                    if (starck[0].index == false) {
                        button[0].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[0].index = true;
                        T[0] = starck[0].t;
                    } else {
                        button[0].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[0].index = false;
                        T[0] = 0;
                    }
                    break;
                case R.id.btn1:
                    if (starck[1].index == false) {
                        button[1].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[1].index = true;
                        T[1] = starck[1].t;
                    } else {
                        button[1].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[1].index = false;
                        T[1] = 0;
                    }
                    break;
                case R.id.btn2:
                    if (starck[2].index == false) {
                        button[2].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[2].index = true;
                        T[2] = starck[2].t;
                    } else {
                        button[2].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[2].index = false;
                        T[2] = 0;
                    }
                    break;
                case R.id.btn3:
                    if (starck[3].index == false) {
                        button[3].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[3].index = true;
                        T[3] = starck[3].t;
                    } else {
                        button[3].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[3].index = false;
                        T[3] = 0;
                    }
                    break;
                case R.id.btn4:
                    if (starck[4].index == false) {
                        button[4].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[4].index = true;
                        T[4] = starck[4].t;
                    } else {
                        button[4].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[4].index = false;
                        T[4] = 0;
                    }
                    break;
                case R.id.btn5:
                    if (starck[5].index == false) {
                        button[5].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[5].index = true;
                        T[5] = starck[5].t;
                    } else {
                        button[5].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[5].index = false;
                        T[5] = 0;
                    }
                    break;
                case R.id.btn6:
                    if (starck[6].index == false) {
                        button[6].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[6].index = true;
                        T[6] = starck[6].t;
                    } else {
                        button[6].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[6].index = false;
                        T[6] = 0;
                    }
                    break;
                case R.id.btn7:
                    if (starck[7].index == false) {
                        button[7].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[7].index = true;
                        T[7] = starck[7].t;
                    } else {
                        button[7].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[7].index = false;
                        T[7] = 0;
                    }
                    break;
                case R.id.btn8:
                    if (starck[8].index == false) {
                        button[8].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[8].index = true;
                        T[8] = starck[8].t;
                    } else {
                        button[8].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[8].index = false;
                        T[8] = 0;
                    }
                    break;
                case R.id.btn9:
                    if (starck[9].index == false) {
                        button[9].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[9].index = true;
                        T[9] = starck[9].t;
                    } else {
                        button[9].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[9].index = false;
                        T[9] = 0;
                    }
                    break;
                case R.id.btn10:
                    if (starck[10].index == false) {
                        button[10].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[10].index = true;
                        T[10] = starck[10].t;
                    } else {
                        button[10].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[10].index = false;
                        T[10] = 0;
                    }
                    break;
                case R.id.btn11:
                    if (starck[11].index == false) {
                        button[11].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[11].index = true;
                        T[11] = starck[11].t;
                    } else {
                        button[11].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[11].index = false;
                        T[11] = 0;
                    }
                    break;
                case R.id.btn12:
                    if (starck[12].index == false) {
                        button[12].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[12].index = true;
                        T[12] = starck[12].t;
                    } else {
                        button[12].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[12].index = false;
                        T[12] = 0;
                    }
                    break;
                case R.id.btn13:
                    if (starck[13].index == false) {
                        button[13].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[13].index = true;
                        T[13] = starck[13].t;
                    } else {
                        button[13].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[13].index = false;
                        T[13] = 0;
                    }
                    break;
                case R.id.btn14:
                    if (starck[14].index == false) {
                        button[14].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[14].index = true;
                        T[14] = starck[14].t;
                    } else {
                        button[14].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[14].index = false;
                        T[14] = 0;
                    }
                    break;
                case R.id.btn15:
                    if (starck[15].index == false) {
                        button[15].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[15].index = true;
                        T[15] = starck[15].t;
                    } else {
                        button[15].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[15].index = false;
                        T[15] = 0;
                    }
                    break;
                case R.id.btn16:
                    if (starck[16].index == false) {
                        button[16].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[16].index = true;
                        T[16] = starck[16].t;
                    } else {
                        button[16].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[16].index = false;
                        T[16] = 0;
                    }
                    break;
                case R.id.btn17:
                    if (starck[17].index == false) {
                        button[17].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[17].index = true;
                        T[17] = starck[17].t;
                    } else {
                        button[17].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[17].index = false;
                        T[17] = 0;
                    }
                    break;
                case R.id.btn18:
                    if (starck[18].index == false) {
                        button[18].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[18].index = true;
                        T[18] = starck[18].t;
                    } else {
                        button[18].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[18].index = false;
                        T[18] = 0;
                    }
                    break;
                case R.id.btn19:
                    if (starck[19].index == false) {
                        button[19].setBackgroundColor(Color.rgb(119, 187, 46));
                        starck[19].index = true;
                        T[19] = starck[19].t;
                    } else {
                        button[19].setBackgroundColor(Color.rgb(255, 255, 255));
                        starck[19].index = false;
                        T[19] = 0;
                    }
                    break;


            }

        }
    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        int i = 0;
        for (i = 0; i < 20; i++) {
            savedInstanceState.putBoolean("b"+i,starck[i].index);
            savedInstanceState.putDouble("d"+i,T[i]);
        }
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        int i=0;
        if (savedInstanceState != null) {
            super.onRestoreInstanceState(savedInstanceState);
            for(i=0;i<20;i++){
                boolean myBoolean = savedInstanceState.getBoolean("b"+i);
                if(myBoolean==true){
                    button[i].setBackgroundColor(Color.rgb(119, 187, 46));
                    starck[i].index=true;}
                else{button[i].setBackgroundColor(Color.rgb(255,255,255));starck[i].index=false;}
                double myDouble = savedInstanceState.getDouble("d"+i);
                T[i]=myDouble;
            }

        }
    }


    @Override
    protected void onStop() {
        super.onStop();

        // The activity is no longer visible (it is now "stopped")
    }
}


