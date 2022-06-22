package com.example.onlinevehicleparking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.onlinevehicleparking.R;
import com.example.onlinevehicleparking.fragments.FindParkFragment;
import com.example.onlinevehicleparking.fragments.MyProfileFragment;
import com.example.onlinevehicleparking.fragments.ParkListFragment;
import com.example.onlinevehicleparking.fragments.ParkStatusFragment;
import com.example.onlinevehicleparking.fragments.RentYourSpaceFragment;
import com.example.onlinevehicleparking.fragments.ReportFragment;
import com.example.onlinevehicleparking.models.User;
import com.example.onlinevehicleparking.storage.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private TextView textView;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        User user = SharedPrefManager.getInstance(this).getUser();



        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new FindParkFragment());

    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.item_findPark:
                fragment = new FindParkFragment();
                break;
            case R.id.item_parkStatus:
                fragment = new ParkStatusFragment();
                break;
            case R.id.item_parkingList:
                fragment = new ParkListFragment();
                break;
        }

        if (fragment != null) {
            displayFragment(fragment);
        }
        return false;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        User user = SharedPrefManager.getInstance(this).getUser();
        menuItem = menu.findItem(R.id.itemMenuTitle);
        menuItem.setTitle(user.getFullName());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           case R.id.item12:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new ReportFragment()).commit();
                return true;

            case R.id.item1:
                  getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new MyProfileFragment()).commit();
                return true;

            case R.id.item3:
                 getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new RentYourSpaceFragment()).commit();

                return true;

            case R.id.item4:
                log_out();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void log_out() {
        SharedPrefManager.getInstance(HomePage.this).clear();
        Intent intent = new Intent(HomePage.this, LoginAvtivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

