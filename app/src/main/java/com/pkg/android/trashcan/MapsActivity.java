package com.pkg.android.trashcan;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.maps.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.pkg.android.trashcan.R.attr.height;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private double mLatitude;
    private double mLongitude;
    private ArrayList<BinInfo> binList;
    private BinInfoLab binInfoLab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        TrashCan trashCan = (TrashCan)getApplicationContext();
        binList = trashCan.getBinList();


        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        mGoogleApiClient.connect();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */



    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng mumbai = new LatLng(mLatitude, mLongitude);
        googleMap.addMarker(new MarkerOptions().position(mumbai).title("You are here").icon(getMarkerIcon("#303F9F")));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mumbai,12.0f));

        getLocations(googleMap);
    }

    public void getLocations(GoogleMap googleMap){
        ArrayList<LatLng> points = new ArrayList<>();
        for(BinInfo b: binList){
            String addr = b.getLocation();
            LatLng l = GetLocationPoint.getLocationFromAddress(addr, getApplicationContext());

            String color = " ";
            double total_height = 100;
            double height = total_height-Double.parseDouble(b.getLevel());


            if(height<total_height && height>(6*total_height/10)){
                Toast.makeText(this, "red" + b.toString(), Toast.LENGTH_SHORT).show();
                color = "#D50000"; //red
            }else if(height<=(6*total_height/10) && height>(3*total_height/10)){
                Toast.makeText(this, "yellow" + b.toString(), Toast.LENGTH_SHORT).show();
                color = "#FFC107"; //yellow
            }else if(height<=(3*total_height/10) && height>0){
                Toast.makeText(this, "green" + b.toString(), Toast.LENGTH_SHORT).show();
                color = "#8BC34A"; // green
            }else{
                color = "#000000";
            }


            //Toast.makeText(getApplicationContext(), l.toString(), Toast.LENGTH_SHORT).show();
            if(l!=null) {
                Marker marker = googleMap.addMarker(new MarkerOptions().position(l).title("Bin" + b.getBin_id()).icon(getMarkerIcon(color)));


                googleMap.moveCamera(CameraUpdateFactory.newLatLng(l));
                marker.setSnippet(b.getBin_id());
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (!marker.getTitle().equals("You are here")) {
                            Intent i = TrashActivity.newIntent(MapsActivity.this, marker.getSnippet());
                            startActivity(i);
                        }
                        return false;
                    }
                });
            }

        }

    }

    public BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.connect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitude = mLastLocation.getLatitude();
            mLongitude = mLastLocation.getLongitude();
            Toast.makeText(getApplicationContext(), "Lattitude = "+String.valueOf(mLatitude), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Longitude = "+String.valueOf(mLongitude), Toast.LENGTH_SHORT).show();
        }

        makeMap();

    }

    private void makeMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
