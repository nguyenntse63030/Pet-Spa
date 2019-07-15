package com.example.petspa_version_2.Activity;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import com.example.petspa_version_2.R;
import com.example.petspa_version_2.Utils.FetchURL;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final LatLng BEN_THANH_MARKET = new LatLng(10.7731, 106.6983);
    private static final LatLng SAIGON_OPERA_HOUSE = new LatLng(10.7767, 106.7032);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(BEN_THANH_MARKET).title("Marker in FPTU"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(BEN_THANH_MARKET));


        MarkerOptions place1 = new MarkerOptions().position(BEN_THANH_MARKET);
        MarkerOptions place2 = new MarkerOptions().position(SAIGON_OPERA_HOUSE);

        mMap.addMarker(place1);
        //mMap.addMarker(place2);

        //String str_origin = "origin=" + place1.getPosition().latitude + "," + place1.getPosition().longitude;
        //String str_dest = "destination=" + place2.getPosition().latitude + "," + place2.getPosition().longitude;
        //String mode = "mode=driving";
        //String parameters = str_origin + "&" + str_dest + "&" + mode;
        //String output = "json";
        //String urlStr = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key) + "&sensor=true";

        //new FetchURL(MapsActivity.this).execute(urlStr, "driving");
    }

    private static final int REQUEST_CODE_GPS_PERMISSION = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //TODO: Get current location
            getCurrentLocation();
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_GPS_PERMISSION);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getCurrentLocation() {
        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location == null) {
                    return;
                }
                LatLng currentLocation = BEN_THANH_MARKET;
                mMap.addMarker(new MarkerOptions().position(currentLocation).title("Marker in current location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            }
        });
    }
}
