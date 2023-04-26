package com.example.hostelfaultreportingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class IssueDetails extends AppCompatActivity {
    ImageView imageView;
    TextView title,hostel,room,email,status_value;
    String title1,hostel1,room1,email1,imageUrl,status_value1;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    Button changeStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_details);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent intent =getIntent();

        imageView =findViewById(R.id.issueImage);
        changeStatus =findViewById(R.id.change_status);

        title =findViewById(R.id.IssueTitleValue1);
        hostel =findViewById(R.id.HostelNameValue1);
        room = findViewById(R.id.RoomNoValue1);
        email =findViewById(R.id.SenderEmailValue1);
        status_value =findViewById(R.id.status1);

        title1 =intent.getStringExtra("IssueTitleValue1");
        hostel1 =intent.getStringExtra("HostelNameValue1");
        room1 =intent.getStringExtra("RoomNoValue1");
        email1 =intent.getStringExtra("SenderEmailValue1");
        imageUrl =intent.getStringExtra("ImageAttachedValue1");
        status_value1 =intent.getStringExtra("Status");

        title.setText(title1);
        hostel.setText(hostel1);
        room.setText(room1);
        email.setText(email1);
        status_value.setText(status_value1);


        if(status_value1.equals("Resolved")) {
            status_value.setTextColor(Color.GREEN);
            changeStatus.setVisibility(View.INVISIBLE);
        }
        else {
            status_value.setTextColor(Color.RED);
        }

        progressDialog = new ProgressDialog(IssueDetails.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        storageReference = FirebaseStorage.getInstance().getReference("IssuesImages/"+imageUrl);

        try {
            File localfile =File.createTempFile("tempfile",".jpg");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(task1 ->
                    {
                        if (progressDialog.isShowing())
                        {
                            progressDialog.dismiss();
                        }
                        Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                        imageView.setImageBitmap(bitmap);
                    }).addOnFailureListener(task2 ->
                    {
                        if (progressDialog.isShowing())
                        {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        changeStatus.setOnClickListener(v ->
        {
            changeCurrentStatus();
        });
    }
    public void changeCurrentStatus()
    {
        progressDialog.show();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("Issues/Hostels/"+hostel1);

// Create a query to find the document(s) to update
        Query query = collectionRef.whereEqualTo("ImageAttached", imageUrl);

        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
                    // Iterate through the results and update the "name" field
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        DocumentReference docRef = collectionRef.document(documentSnapshot.getId());
                        docRef.update("Status", "Resolved")
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        progressDialog.dismiss();
                                        status_value.setTextColor(Color.GREEN);
                                        status_value.setText("Resolved");
                                        changeStatus.setVisibility(View.INVISIBLE);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                    }
                })
                .addOnFailureListener(e -> {

                });

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),HostelMangerMainScreen.class);
        finish();
        startActivity(intent);
    }
}