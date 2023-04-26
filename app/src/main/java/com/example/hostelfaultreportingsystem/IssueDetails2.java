package com.example.hostelfaultreportingsystem;

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

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class IssueDetails2 extends AppCompatActivity {
    ImageView imageView;
    TextView title,hostel,room,email,status_value;
    String title1,hostel1,room1,email1,imageUrl,status_value1;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_details2);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent intent =getIntent();

        imageView =findViewById(R.id.issueImage2);

        title =findViewById(R.id.IssueTitleValue2);
        hostel =findViewById(R.id.HostelNameValue2);
        room = findViewById(R.id.RoomNoValue2);
        email =findViewById(R.id.SenderEmailValue2);
        status_value =findViewById(R.id.status2);

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
                    }
        else {
            status_value.setTextColor(Color.RED);
        }

        progressDialog = new ProgressDialog(IssueDetails2.this);
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
    }
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(),StudentMainScreen.class);
        finish();
        startActivity(intent);
    }
}