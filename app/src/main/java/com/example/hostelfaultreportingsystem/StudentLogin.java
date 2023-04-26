package com.example.hostelfaultreportingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class StudentLogin extends AppCompatActivity {
     Button STLogin;
     EditText STEmail,STPassword;
     ProgressBar progressBar;
     FirebaseAuth mAuth =FirebaseAuth.getInstance();
     FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        STEmail =findViewById(R.id.StudentEmail);
        STPassword =findViewById(R.id.StudentLoginPassword);
        STLogin = findViewById(R.id.StudentLoginButton);
        STLogin.setOnClickListener(view -> userInformation());

        progressBar =findViewById(R.id.progressBar3);
        endSession();

    }
    private void userInformation()
    {
        String STUserEmail=STEmail.getText().toString().trim();
        String STUserPassword=STPassword.getText().toString().trim();

        if (STUserEmail.isEmpty())
        {
            STEmail.setError("Email is required");
            STEmail.requestFocus();
        }
        else if (STUserPassword.isEmpty())
        {
            STPassword.setError("Password is required");
            STPassword.requestFocus();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(STUserEmail).matches()) {
            STEmail.setError("Email address is not of the correct format");
            STEmail.requestFocus();

        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(STUserEmail,STUserPassword).addOnSuccessListener(task1 ->
            {
                String userID =mAuth.getCurrentUser().getUid();
                db.collection("Users").document(userID).get().addOnSuccessListener(result ->{
                    if (result.getString("isStudent")!=null)
                    {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),StudentMainScreen.class));
                        finish();
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        endSession();
                        Toast.makeText(this, "User is not a student", Toast.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(result ->
                {
                    endSession();
                    progressBar.setVisibility(View.GONE);
                });

            }).addOnFailureListener(task2 ->
            {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            });

        }



    }
    public void endSession()
    {
        mAuth.signOut();
    }
}