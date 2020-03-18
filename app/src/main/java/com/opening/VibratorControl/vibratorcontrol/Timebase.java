package com.opening.VibratorControl.vibratorcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HOME on 25/11/2017.
 */
public class Timebase extends SQLiteOpenHelper {




    private static final String TAG = "DatabaseHelper";
    private static final String NAME = "tim";
    private static final String IDE = "id";
    private  static final String BEGIN="start";
    private static final String END ="finish";

    public Timebase(Context context) {
        super(context,NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable= "CREATE TABLE "+NAME+"("+IDE +" INTEGER PRIMARY KEY, "+BEGIN+" TEXT NOT NULL, "+END+" TEXT NOT NULL )";
        db.execSQL(CreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXIST" + NAME);
    }

    public boolean addData(int id,double on,double off ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDE,id);
        contentValues.put(BEGIN,on);
        contentValues.put(END,off);
        long result = db.insert(NAME, null, contentValues);
        if (result == -1) {
            return false;

        } else {
            return true;
        }
    }


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM "+NAME,null);
        return data;
    }
    public Integer deleteData( ){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(NAME,null,null);

    }


    public Boolean Updatedata(int id,double on,double off){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDE,id);
        contentValues.put(BEGIN,on);
        contentValues.put(END,off);
        db.update(NAME,contentValues,"id = ?", new String[]{IDE});
        return true;
    }


}









