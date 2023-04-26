package com.example.hostelfaultreportingsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class StudentSettingsFragment extends Fragment {

    View view;
    Button logout;
    FirebaseAuth mAuth =FirebaseAuth.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        view =inflater.inflate(R.layout.fragment_student_settings, container, false);

        logout =(Button) view.findViewById(R.id.STLogout);

        logout.setOnClickListener(view1 ->
        {
            mAuth.signOut();
            Intent intent =new Intent(getContext(),MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        return view;
    }
}