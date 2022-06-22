package com.example.onlinevehicleparking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinevehicleparking.R;
import com.example.onlinevehicleparking.api.RetrofitClient;
import com.example.onlinevehicleparking.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText _fullName, _phnNum, _address, _vehicleRegNo, _passwprd;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView vhl_type;
    private String vehicleType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        _fullName = findViewById(R.id.editText_nmfull);
        _phnNum = findViewById(R.id.editText_phnnumReg);
        _address = findViewById(R.id.editText_add);
        _vehicleRegNo = findViewById(R.id.editText_VehicleRegNum);
        _passwprd = findViewById(R.id.editText_passwasdordsReg);

        findViewById(R.id.button_regdffdisterLogIn).setOnClickListener(this);
        findViewById(R.id.button_regSave).setOnClickListener(this);
        findViewById(R.id.radioOne).setOnClickListener(this);
        findViewById(R.id.radioTwo).setOnClickListener(this);


    }


  /*  protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, HomePage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }*/



   /* protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, HomePage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }*/

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.button_regdffdisterLogIn:
                startActivity(new Intent(RegistrationActivity.this, LoginAvtivity.class));
                Toast.makeText(getApplicationContext(), "Clicked on Need to LogIn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioOne:
                vehicleType = "Car";
                Toast.makeText(getApplicationContext(), vehicleType, Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioTwo:
                vehicleType = "Bike";
                Toast.makeText(getApplicationContext(), vehicleType, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_regSave:
                // System.out.println("Before Infor_Save");
                //  Toast.makeText(getApplicationContext(), "Before Infor_Save", Toast.LENGTH_SHORT).show();
                info_save();
                // System.out.println("after info_save()");
                // Toast.makeText(getApplicationContext(), "after info_save()", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void info_save() {
        // System.out.println("In info_save method");
        // Toast.makeText(getApplicationContext(), "In info_save method", Toast.LENGTH_SHORT).show();
        String fullName = _fullName.getText().toString().trim();
        String phnNum = _phnNum.getText().toString().trim();
        String address = _address.getText().toString().trim();
        String vehicleRegNo = _vehicleRegNo.getText().toString().trim();
        String password = _passwprd.getText().toString().trim();

        if (fullName.isEmpty()) {
            _fullName.setError("Name is required");
            _fullName.requestFocus();
            return;
        } else if (phnNum.isEmpty()) {
            _phnNum.setError("Phone number is required");
            _phnNum.requestFocus();
            return;
        } else if (address.isEmpty()) {
            _address.setError("Address is required");
            _address.requestFocus();
            return;
        } else if (vehicleRegNo.isEmpty()) {
            _vehicleRegNo.setError("Registration number is required");
            _vehicleRegNo.requestFocus();
            return;
        } else if (password.isEmpty()) {
            _passwprd.setError("Password is required");
            _passwprd.requestFocus();
            return;
        } else if (password.length() < 5) {
            _passwprd.setError("Password length should be 5 or more!");
            _passwprd.requestFocus();
            return;
        } else {
            /*
            Intent intent = new Intent( RegistrationActivity.this, PhoneNumVerifyActivity.class );
             intent.putExtra( "fullName", fullName );
            intent.putExtra( "phnNum", phnNum );
            intent.putExtra( "address", address );
            intent.putExtra( "vehicleType", vehicleType );
             intent.putExtra( "vehicleRegNo", vehicleRegNo );
             intent.putExtra( "password", password );
             startActivity( intent );
            */
            //  Toast.makeText(RegistrationActivity.this, "before create method transition", Toast.LENGTH_LONG).show();
            Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().createUser(fullName, phnNum, address, vehicleType, vehicleRegNo, password);

           // startActivity(new Intent(RegistrationActivity.this, LoginAvtivity.class));
              call.enqueue(new Callback<DefaultResponse>() {

                @Override
             public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
              // Toast.makeText(RegistrationActivity.this, "after create method transition", Toast.LENGTH_LONG).show();
                if (response.body().isErr() == false) {
                       /* Intent intent = new Intent(RegistrationActivity.this, LoginAvtivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);*/
           // Toast.makeText(RegistrationActivity.this, "before transition", Toast.LENGTH_LONG).show();
            startActivity(new Intent(RegistrationActivity.this, LoginAvtivity.class));
              Toast.makeText(RegistrationActivity.this, (CharSequence) response.body().getMsg(), Toast.LENGTH_LONG).show();
            //   Toast.makeText(RegistrationActivity.this, "after transition", Toast.LENGTH_LONG).show();
             } else {
                  Toast.makeText(RegistrationActivity.this, (CharSequence) response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
             }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {
                    Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }


        }
    }



