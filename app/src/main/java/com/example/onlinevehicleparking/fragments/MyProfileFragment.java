package com.example.onlinevehicleparking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.onlinevehicleparking.R;
import com.example.onlinevehicleparking.models.User;
import com.example.onlinevehicleparking.storage.SharedPrefManager;


public class MyProfileFragment extends Fragment {
    private TextView fullName, add, vhType, vhRg, phnNm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_profile , container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        User user = SharedPrefManager.getInstance(getActivity()).getUser();
        fullName = view.findViewById(R.id.txtName);
        fullName.setText(user.getFullName());
        add = view.findViewById(R.id.txtUserName);
        add.setText(user.getAddress());
        vhType = view.findViewById(R.id.txtAddress);
        vhType.setText(user.getVehicleType());
        vhRg = view.findViewById(R.id.txtphoneNum);
        vhRg.setText(user.getVehicleRegNo());
        phnNm = view.findViewById(R.id.txtMail);
        phnNm.setText(user.getPhoneNum());

    }


}
