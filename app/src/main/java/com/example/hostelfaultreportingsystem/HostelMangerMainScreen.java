package com.example.hostelfaultreportingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class HostelMangerMainScreen extends AppCompatActivity {
    Button btn1,btn2;
    public FirebaseFirestore db =FirebaseFirestore.getInstance();
    public FirebaseAuth user =FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_manger_main_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        String userID = user.getCurrentUser().getUid();

        btn1 =findViewById(R.id.ViewIssues);
        btn2 =findViewById(R.id.Settings2);


        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentViewer2,new ManagerMessagesFragment());
        fragmentTransaction.commit();
        btn1.setTextColor(Color.RED);

        btn1.setOnClickListener(view ->
        {
            replaceFragment(new ManagerMessagesFragment());

            btn1.setTextColor(Color.RED);
            btn2.setTextColor(Color.WHITE);

        });
        btn2.setOnClickListener(view ->
        {
            replaceFragment(new ManagerSettingsFragment());

            btn1.setTextColor(Color.WHITE);
            btn2.setTextColor(Color.RED);
        });


    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentViewer2,fragment);
        fragmentTransaction.commit();
    }
    public void finishActivity()
    {
        finish();
    }

}