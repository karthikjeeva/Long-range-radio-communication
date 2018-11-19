package com.example.karthik.cameraapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapActivity extends FragmentActivity implements LocationListener,OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mCurrent;
    Location mLast;
    Marker mCurrentLocationMarker;
    Double latitude;
    Double longitude;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);



    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap=googleMap;

       if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
                mMap.setOnMapClickListener(this);


            }
        }

        else
        {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        }

    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient=new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest= new LocationRequest();

        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            LocationServices.getFusedLocationProviderClient(this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLast=location;

        if(mCurrentLocationMarker!=null)
            mCurrentLocationMarker.remove();

        //place current location marker
        LatLng latlng=new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title("currentPositon");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrentLocationMarker=mMap.addMarker(markerOptions);

        //move camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));


    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    @Override
    public void onMapClick(LatLng point) {
        List<Address> myList;
        String country = null;
       mMap.addMarker(new MarkerOptions()
                .position(point)
                .title(point.toString())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


        Toast.makeText(getApplicationContext(),point.latitude+""+point.longitude,Toast.LENGTH_SHORT).show();
        latitude=point.latitude;
        longitude=point.longitude;

        //get locaton name from lattitude and long.
        Geocoder geo=new Geocoder(getApplicationContext(), Locale.US);
        try {
             myList=geo.getFromLocation(latitude,longitude,1);

             for(Address a:myList) {
                 country=a.getCountryName();
                 Toast.makeText(getApplicationContext(), "The photo is saved to:" + "" + a.getCountryName(), Toast.LENGTH_LONG).show();
             }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"something went wrong while fetching address",Toast.LENGTH_LONG);
        }


        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("Bitmapimage");


        Intent i=new Intent(MapActivity.this,SaveActivity.class);
        i.putExtra("Bitmapimage",byteArray);
        //Bundle bb=new Bundle();
        extras.putString("CountryName",country);
        extras.putDouble("lat",latitude);
        extras.putDouble("lon",longitude);
        i.putExtras(extras);
        startActivity(i);

    }



}
