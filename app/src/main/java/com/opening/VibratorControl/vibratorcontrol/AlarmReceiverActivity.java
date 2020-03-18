package com.opening.VibratorControl.vibratorcontrol;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.util.Calendar;


/**
 * Created by HOME on 25/11/2017.
 */
public class AlarmReceiverActivity extends WakefulBroadcastReceiver {
    Calendar cal= Calendar.getInstance();

    @Override

    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, BackgroundService.class);
        context.startService(background);
    }

}