package com.example.projectpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Store2 extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final LatLng karaoke = new LatLng(13.7785119,100.5578992);
    private static final int DEFAULT_ZOOM = 19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store2);

        // Build the map.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapfood2);
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
        mMap.addMarker(new MarkerOptions().position(karaoke).title("The Room Karaoke"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(karaoke,DEFAULT_ZOOM));
    }

    public void showStore2(View view){startActivity(new Intent(this,Store2Map.class));}
    public void backMainFood(View view){startActivity(new Intent(this,MainFoodActivity.class));}
}
