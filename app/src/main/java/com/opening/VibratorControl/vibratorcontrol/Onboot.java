package com.opening.VibratorControl.vibratorcontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by HOME on 30/01/2018.
 */
public class Onboot extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Alarm.set(context);
    }
}
