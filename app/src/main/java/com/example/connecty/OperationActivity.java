package com.example.connecty;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;

public class OperationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public TextView timeView;
    public int second = 0;
    public int minute = 0;
    public int hours = 0;
    public boolean isPause = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
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
       // LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        Intent intent = getIntent();
        Group group = (Group) intent.getSerializableExtra("Group");

        LatLng pos = new LatLng(group.x,group.y);
        mMap.addMarker(new MarkerOptions().position(pos).title("Customer Position"));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .zoom(17)
                .target(pos)
                .build();
        CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.animateCamera(cu);

        //View view = inflater.inflate(R.layout.fragment_list_of_group, container, false);

    }

    public void Complete(View view) {

        Toast.makeText(OperationActivity.this,"Order completed",Toast.LENGTH_SHORT).show();
        finish();


    }


}
