package com.example.karthik.cameraapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;

public class CameraActivity extends Activity {

    // private GoogleMap mMap;
    public static final int CAMERA_REQUEST = 1888;
    ImageView imageview;
    Button photobutton;
    Bitmap photo;
    byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageview = (ImageView) findViewById(R.id.imageView);
        photobutton = (Button) findViewById(R.id.button);

        photobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(photo);
            photobutton.setVisibility(View.INVISIBLE);


            }

        Button geo=(Button)findViewById(R.id.geo);

        geo.setOnClickListener((v1)->{

            imageview.buildDrawingCache();
            Bitmap bmp=imageview.getDrawingCache();

        Intent i=new Intent(CameraActivity.this,MapActivity.class);
        ByteArrayOutputStream bs=new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,70,bs);
        byte[] bytearray=bs.toByteArray();
        i.putExtra("Bitmapimage",bytearray);
        startActivity(i);



        });


    }

}
