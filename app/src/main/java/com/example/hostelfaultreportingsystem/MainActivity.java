package com.example.hostelfaultreportingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    Button STlogin,HMlogin;
    FirebaseAuth mAuth =FirebaseAuth.getInstance();
    FirebaseFirestore db= FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        STlogin = findViewById(R.id.STLoginbtn);
        HMlogin =findViewById(R.id.HMLoginbtn);
        STlogin.setOnClickListener(view -> {
            Intent intent =new Intent(getApplicationContext(),StudentLogin.class);
            startActivity(intent);


        });
        HMlogin.setOnClickListener(view -> {
            Intent intent =new Intent(getApplicationContext(),HostelManagerLogin.class);
            startActivity(intent);

        });

        if (mAuth.getCurrentUser()!=null) {
            String userID =mAuth.getCurrentUser().getUid();
            setContentView(R.layout.loading_screen);
            db.collection("Users").document(userID).get().addOnSuccessListener(result ->
            {
                if (result.getString("isManager")!=null)
                {
                    Intent intent =new Intent(getApplicationContext(),HostelMangerMainScreen.class);
                    finish();
                    startActivity(intent);

                } else {
                    Intent intent =new Intent(getApplicationContext(),StudentMainScreen.class);
                    finish();
                    startActivity(intent);
                }

            }).addOnFailureListener(result ->
                    Toast.makeText(this, "Please contact admin", Toast.LENGTH_SHORT).show());
        }

        }




}


