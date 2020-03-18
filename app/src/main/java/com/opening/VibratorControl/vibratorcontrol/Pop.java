package com.opening.VibratorControl.vibratorcontrol;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by HOME on 22/01/2018.
 */
public class Pop extends Activity {
    @Override
    protected  void onCreate(Bundle savedinstancestate){
       super.onCreate(savedinstancestate);
      setContentView(R.layout.popitem);
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int heigh=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(heigh*.6));
    }
}
