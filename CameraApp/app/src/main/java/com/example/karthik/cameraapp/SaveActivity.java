package com.example.karthik.cameraapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SaveActivity extends AppCompatActivity {
    DbsqliteActivity dbs=new DbsqliteActivity(this);
    Button saveBtn;
    Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_dbsqlite);
        saveBtn=(Button)findViewById(R.id.savebtn);

        //getting selected country from spinner
       dropdown=findViewById(R.id.spinner);
        String[] str1;
        Bundle b1=dbs.fetchCountry();
        if (b1 != null) {

            str1= b1.getStringArray("v");
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, str1);
            dropdown.setAdapter(adapter);

            Toast.makeText(getApplicationContext(),str1[1],Toast.LENGTH_LONG).show();
        }


    }

    public void AddToDb(View view) {
       // dbs.contactdb(this);
        Bundle extras = getIntent().getExtras();
        String countryName=extras.getString("CountryName");
        byte[] byteArray = extras.getByteArray("Bitmapimage");
        Double latitude=extras.getDouble("lat");
        Double longitude=extras.getDouble("lon");

        //store in db,calling function from dbsqlite
        boolean stmt=dbs.addToDB(countryName,byteArray,latitude,longitude);

        Toast.makeText(getApplicationContext(),"Sucessfully added to db is "+stmt,Toast.LENGTH_LONG).show();
   }

    public void displayImg(View view) {

        EditText txt= (EditText)findViewById(R.id.editBox);
        String phone=txt.getText().toString();



         //retrive selected item from spinner
        String country=dropdown.getSelectedItem().toString();
        //fetch from db
        Bundle bun=dbs.fetchFromDb(country);

        if(bun!=null) {
            byte[] byteArray = bun.getByteArray("imagebits");

            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            ImageView image = (ImageView) findViewById(R.id.dbimage);

            image.setImageBitmap(bmp);

            String country1 = bun.getString("countryName");
            Double latitude = bun.getDouble("lat");
            Double longitude = bun.getDouble("lon");


            Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);


//Get the SmsManager instance and call the sendTextMessage method to send message
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage("+91" + phone, null, "hello this is kar" + " " + latitude + " " + longitude, pi, null);

            Toast.makeText(getApplicationContext(), "Sucessfully sent sms", Toast.LENGTH_LONG).show();
        }

        if (bun==null) {
            Toast.makeText(getApplicationContext(), "Inappropriate country name given, so click a new image then select needed location imn map, then select save btn ", Toast.LENGTH_LONG).show();
            Intent id = new Intent(SaveActivity.this, CameraActivity.class);
            startActivity(id);
        }


    }
}
