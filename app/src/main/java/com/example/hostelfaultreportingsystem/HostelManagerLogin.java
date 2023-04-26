package com.example.hostelfaultreportingsystem;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class HostelManagerLogin extends AppCompatActivity {
    Button hmLoginButton;
    EditText hmLoginEmail,hmLoginPassword;
    ProgressBar progressBar;
    FirebaseAuth mAuth =FirebaseAuth.getInstance();
    FirebaseFirestore db =FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_manager_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        hmLoginButton =findViewById(R.id.HostelManagerLoginButton);
        hmLoginEmail =findViewById(R.id.HostelManagerEmail);
        hmLoginPassword =findViewById(R.id.HostelManagerPassword);
        hmLoginButton.setOnClickListener(view -> getUserDetails());
        progressBar =findViewById(R.id.progressBar4);
        endSession();



    }

    private void getUserDetails()
    {
        String HMUserID = hmLoginEmail.getText().toString().trim();
        String HMUserPassword = hmLoginPassword.getText().toString().trim();

        if(HMUserID.isEmpty())
        {
            hmLoginEmail.setError("Email cannot be empty");
            hmLoginEmail.requestFocus();

        } else if (HMUserPassword.isEmpty()) {
            hmLoginPassword.setError("Password cannot be empty");
            hmLoginPassword.requestFocus();
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(HMUserID,HMUserPassword).addOnSuccessListener(task1 ->{

                String userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                db.collection("Users").document(userID).get().addOnSuccessListener(documentSnapshot -> {

                        if(documentSnapshot.getString("isManager")!=null)
                        {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(HostelManagerLogin.this, "Login successful", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(getApplicationContext(),HostelMangerMainScreen.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            endSession();
                            Toast.makeText(HostelManagerLogin.this, "User is not a hostel manager", Toast.LENGTH_SHORT).show();
                        }
                }).addOnFailureListener(e ->{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(HostelManagerLogin.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    endSession();
                });

            }).addOnFailureListener(task2 ->{
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Details do not match", Toast.LENGTH_SHORT).show();
            });

        }
    }
    public void endSession()
    {
        mAuth.signOut();
    }

}