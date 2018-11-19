package com.example.karthik.cameraapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class TestActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("Bitmapimage");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.imageView2);

        image.setImageBitmap(bmp);

         Double latitude=extras.getDouble("lat");
        Double longitude=extras.getDouble("lon");

        Toast.makeText(getApplicationContext(),longitude +""+latitude,Toast.LENGTH_LONG).show();

    }
}
