package com.opening.VibratorControl.vibratorcontrol;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by HOME on 30/01/2018.
 */
public class Alarm   {

    public static void set(Context context){
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY,8 );
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
        Intent alarm = new Intent(context, AlarmReceiverActivity.class);
    // boolean alarmRunning = (PendingIntent.getBroadcast(this.context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
    //if (alarmRunning == false) {
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0, alarm,PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),1800000, pendingIntent);}


}
