package com.example.onlinevehicleparking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinevehicleparking.R;
public class ForgotpassActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        findViewById(R.id.button_need_to_reg).setOnClickListener(this);
        findViewById(R.id.button_back_to_LogIn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_need_to_reg:
                Intent intent = new Intent(ForgotpassActivity.this, RegistrationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.button_back_to_LogIn:
                Intent intenta = new Intent(ForgotpassActivity.this, LoginAvtivity.class);
                intenta.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intenta);
                Toast.makeText(getApplicationContext(), "Back to LogIn",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}


