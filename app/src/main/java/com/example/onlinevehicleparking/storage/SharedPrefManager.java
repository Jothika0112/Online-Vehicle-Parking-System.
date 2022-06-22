package com.example.onlinevehicleparking.storage;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.onlinevehicleparking.models.SensorData;
import com.example.onlinevehicleparking.models.User;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "mySharedPreff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }


    public void saveUser(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", user.getId());
        editor.putString("fullName", user.getFullName());
        editor.putString("phoneNum", user.getPhoneNum());
        editor.putString("address", user.getAddress());
        editor.putString("vehicleType", user.getVehicleType());
        editor.putString("vehicleRegNo", user.getVehicleRegNo());
        editor.apply();
    }

    public void saveSensorData(SensorData sensorData) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences.edit();
        editor2.putInt("id", sensorData.getId());
        editor2.putString("p1", sensorData.getP1());
        editor2.putString("p2", sensorData.getP2());
        editor2.putString("p3", sensorData.getP3());
        editor2.putString("p4", sensorData.getP4());
        editor2.putString("p5", sensorData.getP5());
        editor2.putString("p6", sensorData.getP6());
        editor2.putString("available", sensorData.getAvailable());
        editor2.putString("date", sensorData.getAvailable());
        editor2.apply();
    }


    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }


    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("fullName",null),
                sharedPreferences.getString("phoneNum", null),
                sharedPreferences.getString("address", null),
                sharedPreferences.getString("vehicleType", null),
                sharedPreferences.getString("vehicleRegNo", null)

        );
    }


    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}

