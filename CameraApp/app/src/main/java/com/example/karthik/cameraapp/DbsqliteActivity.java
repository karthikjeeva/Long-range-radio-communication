package com.example.karthik.cameraapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

public class DbsqliteActivity extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "photo_db";
    private static final int DATABASE_VERSION = 22;
    private static final String TABLE1_NAME = "photo_table";

    private static final String COUNTRY_NAME = "country";
    private static final String KEY_ID = "id";
    private static final String PHOTO = "photo";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";


    public DbsqliteActivity(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table
        String CREATE_TABLE_NAME = "CREATE TABLE " + TABLE1_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COUNTRY_NAME + " TEXT , "
                + PHOTO + " BLOB ," + LATITUDE + " REAL ," + LONGITUDE + " REAL " + ")";

        db.execSQL(CREATE_TABLE_NAME);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);

        onCreate(db);
    }

    public boolean addToDB(String countryName, byte[] img, double lat, double lon) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_NAME, countryName);
        values.put(PHOTO, img);
        values.put(LATITUDE, lat);
        values.put(LONGITUDE, lon);
        // Inserting Row
        long r = db.insert(TABLE1_NAME, null, values);

        if (r != -1)
            return true;

        db.close();
        return false;
    }


    public Bundle fetchFromDb(String countryName) {
        Cursor cs = null;
        try {
            SQLiteDatabase base = getReadableDatabase();

            cs = base.query(TABLE1_NAME, new String[]{COUNTRY_NAME, PHOTO, LATITUDE, LONGITUDE}, "country=?", new String[]{String.valueOf(countryName)}, null, null, null);
            cs.moveToFirst();

            Bundle bun;
            if (cs != null && cs.getCount() > 0) {
                String countryName1 = cs.getString(0);
                byte[] imgbyte = cs.getBlob(1);
                double lat = cs.getDouble(2);
                double lon = cs.getDouble(3);


                bun = new Bundle();
                bun.putString("countryName", countryName1);
                bun.putByteArray("imagebits", imgbyte);
                bun.putDouble("lon", lon);
                bun.putDouble("lat", lat);

                return bun;
            } else
                return bun = null;

        } catch (IndexOutOfBoundsException ie) {

            Log.d("error", "error in db");
        } finally {
            cs.close();
        }

        return null;


    }


    public Bundle fetchCountry() {

        Cursor cs = null;
        Bundle bb=null;
        try {
            SQLiteDatabase base = getReadableDatabase();

            cs = base.rawQuery(" select " + COUNTRY_NAME + " from photo_table " , null);
            cs.moveToFirst();


            if(cs!=null && cs.getCount()>0){
                int count=cs.getCount()-1;
                String[] str=new String[count];
                int i=0;
                bb=new Bundle();
                while(count>0){

                    str[i]=cs.getString(0);
                    i++;
                    count--;
                    cs.moveToNext();



                }
                bb.putStringArray("v",str);
                return bb;
            }

            else
                return bb=null;
        } catch (IndexOutOfBoundsException ie) {

            Log.d("error", "error in db");
        } finally {
            cs.close();
        }


        return bb;

    }
}


