package com.example.onlinevehicleparking.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinevehicleparking.R;
import com.example.onlinevehicleparking.api.RetrofitClient;
import com.example.onlinevehicleparking.models.DefaultResponse;
import com.example.onlinevehicleparking.models.ParkOwnerSensorResponse;
import com.example.onlinevehicleparking.models.SensorData;
import com.example.onlinevehicleparking.models.User;
import com.example.onlinevehicleparking.storage.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParkListItemActivity extends AppCompatActivity implements View.OnClickListener {
    //public static java.lang.Class<?> Class;
    private TextView parkNam, add, phonenNum, capacity, avail, bookNow, backList;
    private String ident1, ident2, ident3, ident4, _ident1, _ident2, _ident3, _ident4;
    private String thisDate;
    private int n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_list_item);

        parkNam = findViewById(R.id.textViewParkName);
        add = findViewById(R.id.textView3Address);
        phonenNum = findViewById(R.id.textViewPhoneNum);
        capacity = findViewById(R.id.textViewCapacity);
        avail = findViewById(R.id.textViewAvailpljavail);

        findViewById(R.id.bookNow).setOnClickListener(this);
        findViewById(R.id.backToList).setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        ident1 = b.getString("parkName");
        ident2 = b.getString("add");
        ident3 = b.getString("phn");
        ident4 = b.getString("cap");


        parkNam.setText(ident1);
        add.setText(ident2);
        phonenNum.setText(ident3);
        capacity.setText(ident4);
        //ident1=parkNam.toString();
        avail.setText("8");



        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = new Date();
        thisDate = currentDate.format(todayDate);

        Call<ParkOwnerSensorResponse> call = RetrofitClient.getInstance().getApi().getParkOwnerSensorData(ident1);
        call.enqueue(new Callback<ParkOwnerSensorResponse>() {
            @Override
            public void onResponse(Call<ParkOwnerSensorResponse> call, Response<ParkOwnerSensorResponse> response) {

                ParkOwnerSensorResponse ownerSensorResponse = response.body();
                SensorData sensorData = ownerSensorResponse.getSensorData();
               // avail.setText("8");
                if (ownerSensorResponse.isError() == false) {
                    if (sensorData == null || sensorData.getAvailable().isEmpty() || sensorData.getAvailable().equals(null)) {
                            avail.setText("8");
                           }else {
                      avail.setText("8");
                        n = Integer.valueOf(sensorData.getAvailable());
                        if (n == 0) {
                            //  findViewById(R.id.bookNow).setVisibility(View.INVISIBLE);
                            findViewById(R.id.bookNow).setVisibility(View.GONE);
                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<ParkOwnerSensorResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bookNow:
                validity_for_booking();
                //Intent intent = new Intent(ParkListItemActivity.this, HomepageActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //startActivity(intent);
                break;
            case R.id.backToList:
                finish();
                break;
        }
    }

    private void validity_for_booking() {
        //if (n > 0){
        Toast.makeText(getApplicationContext(), "Requesting for Park", Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Your vehicle is Parked", Toast.LENGTH_LONG).show();
        //avail.setText("8");
            User user = SharedPrefManager.getInstance(this).getUser();
            Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().bookNow(ident1,user.getFullName(), user.getPhoneNum(), user.getVehicleType(), user.getVehicleRegNo() );
            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    DefaultResponse defaultResponse = response.body();
                  //  Toast.makeText(getApplicationContext(), defaultResponse.getMsg(), Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }//else if(  n <= 0 || String.valueOf(n).equals(null) || String.valueOf(n).isEmpty()){
           //avail.setText("8");
          //  Toast.makeText(getApplicationContext(),"Not available. Please, try to find another else :)", Toast.LENGTH_LONG).show();
        }//else {
           // Toast.makeText(getApplicationContext(),"Not available. Please, try to find another else :)", Toast.LENGTH_LONG).show();
       // }




