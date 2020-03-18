package com.opening.VibratorControl.vibratorcontrol;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by HOME on 25/11/2017.
 */
@TargetApi(Build.VERSION_CODES.N)
public class BackgroundService extends Service {
    private boolean isRunning  = false;
    private Context context;
    private Thread backgroundThread;
    private AudioManager myaudio;


    Timebase tm;
    Cursor res;
    private Calendar cal= Calendar.getInstance();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public  Boolean restore2(){
        SharedPreferences pref= getSharedPreferences("fil",MODE_PRIVATE);
        Boolean s=pref.getBoolean("mode",true);
        return s;
    }

    public void set(Integer b){
        SharedPreferences pref=getSharedPreferences("fil",MODE_PRIVATE);
        SharedPreferences.Editor mEditor= mEditor=pref.edit();
        mEditor.putInt("index",b);
        mEditor.apply();
    }
    public Integer get(){
        SharedPreferences pref= getSharedPreferences("fil",MODE_PRIVATE);
        Integer s=pref.getInt("index",0);
        return s;
    }

    private Runnable myTask = new Runnable() {
        public void run() {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            Calendar calenda = Calendar.getInstance();

            calenda.setTimeInMillis(System.currentTimeMillis());
            calenda.set(Calendar.HOUR_OF_DAY,0);
            calenda.set(Calendar.MINUTE,0);
            calenda.set(Calendar.SECOND,0);
            if(cal.get( Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY){
                if(cal.get(Calendar.HOUR_OF_DAY)<20)
                res = tm.getData();
                if (res != null && res.moveToPosition(get())) {
                    long n1 = (int) (Float.parseFloat(res.getString(1)) * 3600000);
                    long n2 = (int) (Float.parseFloat(res.getString(2)) * 3600000);
                    calendar.setTimeInMillis(calendar.getTimeInMillis() + n1);
                    calenda.setTimeInMillis(calenda.getTimeInMillis() + n2);
                    if (cal.getTimeInMillis() < calendar.getTimeInMillis()) {
                    } else {
                        if (cal.getTimeInMillis() < calenda.getTimeInMillis()) {
                            if (restore2()) {
                                myaudio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                            } else {
                                myaudio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                            }
                        } else {

                            if (res.moveToNext()) {
                                set(res.getPosition());
                            } else{  if(cal.getTimeInMillis()>18) {
                                set(0);}
                            }
                            myaudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        }


                    }
                }
            }
            stopSelf();
        }
    };

    @Override
    public void onCreate() {
       // this.context = this;
        this.isRunning = true;
        //this.backgroundThread= new Thread(myTask);
        myaudio=(AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        tm=new Timebase(this);
    }






    @Override
    public void onDestroy() {
        this.isRunning = false;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //if(!this.isRunning) {
            //this.isRunning = true;
            this.backgroundThread= new Thread(myTask);
            this.backgroundThread.start();
        //}

        return START_STICKY;
    }


}
