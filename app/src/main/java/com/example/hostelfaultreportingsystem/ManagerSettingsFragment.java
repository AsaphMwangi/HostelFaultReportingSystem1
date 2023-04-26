package com.example.hostelfaultreportingsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ManagerSettingsFragment extends Fragment {

    View view;
    Button logout;
    FirebaseAuth mAuth =FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setRetainInstance(true);
        view= inflater.inflate(R.layout.fragment_manager_settings, container, false);
        logout = (Button) view.findViewById(R.id.HMLogout);

        logout.setOnClickListener(view ->
        {
            mAuth.signOut();
            Intent intent =new Intent(getContext(),MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            HostelMangerMainScreen object = new HostelMangerMainScreen();
            object.finishActivity();
        });
        return view;
    }
}