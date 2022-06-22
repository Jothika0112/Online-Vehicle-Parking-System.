package com.example.onlinevehicleparking.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.onlinevehicleparking.R;
import com.example.onlinevehicleparking.api.RetrofitClient;
import com.example.onlinevehicleparking.models.DefaultResponse;
import com.example.onlinevehicleparking.models.User;
import com.example.onlinevehicleparking.storage.SharedPrefManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetYourLocation extends AppCompatActivity implements OnMapReadyCallback {
    private TextView textView;
    private String park_name, phone, capacity, address, password;
    private GoogleMap mMap;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_your_location);


        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById( R.id.map_loc );
        mapFragment.getMapAsync((OnMapReadyCallback) this);



        Bundle b = getIntent().getExtras();
        park_name = b.getString("park_name");
        phone = b.getString("phone");
        capacity = b.getString("capacity");
        address = b.getString("address");
        password = b.getString("password");

        //textView.setText(park_name+" "+phone+" "+capacity+" "+map_locatin+" "+password);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            boolean success = googleMap.setMapStyle( MapStyleOptions.loadRawResourceStyle( getApplicationContext(), R.raw.mapstyler_stander) ); //map theme
            if (!success) {
                Log.e( "Set Your Location", "Style map failed" );
            }
        } catch (Resources.NotFoundException e) {
            Log.e( "Set Your Location", "Can't find MAP_Style" + e );
        }

        LatLng Vishal_de_mall = new LatLng( 9.9382, 78.1359 ); //starting area for map location
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( Vishal_de_mall, 11 ) );
        mMap.getUiSettings().setZoomControlsEnabled( true );
        final Marker marker = mMap.addMarker( new MarkerOptions().position( Vishal_de_mall ) );
        marker.remove();
        if (ActivityCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled( true );


        mMap.setOnMapClickListener( new GoogleMap.OnMapClickListener() {


            @Override
            public void onMapClick(LatLng point) {
                final double lat = point.latitude ;
                final double lng =   point.longitude  ;
                double zoomV =    mMap.getCameraPosition().zoom  ;
                location = getCompleteAddressString(  point.latitude , point.longitude  );

                try{
                    drawMarker(point,location);
                }catch (Exception e){
                    e.printStackTrace();
                }



                ////////////////////////////////////////////////////////////////////////////////////
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        new AlertDialog.Builder(SetYourLocation.this)
                                .setTitle("My Location")
                                .setMessage(location )
                                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {


                                        //////////////////////////
                                        Toast.makeText(getApplicationContext()," Lat:"+lat+" Lng:"+lng+" Add:"+location, Toast.LENGTH_LONG).show();
                                        User user = SharedPrefManager.getInstance(getApplication()).getUser();

                                        Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().rentYourSpaceReg(park_name,  user.getFullName(), phone, capacity, address, password, lat, lng, location);
                                        call.enqueue(new Callback<DefaultResponse>() {
                                            @Override
                                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                                DefaultResponse defaultResponse = response.body();
                                                if(defaultResponse.isErr() == false){
                                                    Toast.makeText(getApplicationContext(), defaultResponse.getMsg(), Toast.LENGTH_LONG).show();
                                                    Intent  intent = new Intent(SetYourLocation.this, HomePage.class);
                                                    // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                                            }
                                        });
                                        /////////////////////////

                                    }
                                })
                                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .show();
                        return false;
                    }
                });
                ////////////////////////////////////////////////////////////////////////////////////






            }
        } );

    }


    public void drawMarker(LatLng point, String add){
        MarkerOptions markerOptions = new MarkerOptions();
        mMap.addMarker(markerOptions.position(point).title( add ).snippet(add).draggable( true ));
        mMap.addMarker(markerOptions.icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA) ) );

        Toast.makeText( getApplicationContext()," "+point+" "+add,Toast.LENGTH_SHORT ).show();
    }


    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String fullAdd = null;
        try {
            Geocoder geocoder = new Geocoder( getBaseContext(), Locale.getDefault() );
            List<Address> addresses = geocoder.getFromLocation( LATITUDE, LONGITUDE, 1 );
            if (addresses.size() > 0) {
                Address address = addresses.get( 0 );
                fullAdd = address.getAddressLine( 0 );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullAdd;
    }


}


