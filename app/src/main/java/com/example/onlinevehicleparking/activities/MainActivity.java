package com.example.onlinevehicleparking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinevehicleparking.R;

public class MainActivity extends AppCompatActivity {
    public static int TIME_LAPS=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run( )
            {
                Intent intent=new Intent(MainActivity.this, LoginAvtivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_LAPS);
    }
}