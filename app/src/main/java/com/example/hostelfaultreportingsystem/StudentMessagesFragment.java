package com.example.hostelfaultreportingsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class StudentMessagesFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    View view;
    Button  submitIssue,viewRecent;
    EditText hostelName,roomNo,issueType;
    FirebaseAuth mAuth  =FirebaseAuth.getInstance();
    FirebaseFirestore db =FirebaseFirestore.getInstance();
    Uri imageUrl;
    ImageView imageView;
    StorageReference storageReference;
    SimpleDateFormat formatter;
    String userID;
    String fileName;
    Date now;
    ProgressBar progressBar;
    RadioGroup radioGroup;
    RadioButton hostelA,hostelB,hostelC,hostelD,hostelE;
    String hostelNameValue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
       view = inflater.inflate(R.layout.fragment_student_messages, container, false);
       hostelName = view.findViewById(R.id.IssueTitleValue);
       roomNo =view.findViewById(R.id.RoomNumber);
       issueType =view.findViewById(R.id.IssueType);

       submitIssue =view.findViewById(R.id.SubmitIssue);
       viewRecent =view.findViewById(R.id.ViewRecentIssues);

       imageView =view.findViewById(R.id.imageAttachment);
       progressBar =view.findViewById(R.id.progressBar5);

       radioGroup =view.findViewById(R.id.SelectHostelStudent);
       hostelA =view.findViewById(R.id.HostelA);
       hostelB =view.findViewById(R.id.HostelB);
       hostelC =view.findViewById(R.id.HostelC);
       hostelD =view.findViewById(R.id.HostelD);
       hostelE =view.findViewById(R.id.HostelE);



        formatter =new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
       now =new Date();

       submitIssue.setOnClickListener(view1 ->
               checker());
       viewRecent.setOnClickListener(view3->
       {
            StudentMainScreen studentMainScreen =(StudentMainScreen) requireActivity();
            studentMainScreen.studentViewIssues();
       });
       imageView.setOnClickListener(view2 ->
               selectImage());
       return view;
    }
    private void selectImage()
    {
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==100 && data !=null &&data.getData() != null)

        {
            imageUrl =data.getData();
            imageView.setImageURI(imageUrl);
            imageView.setBackground(getResources().getDrawable(R.drawable.black_rectangular_border));


        }
    }
    public void uploadImage()
    {

        fileName =formatter.format(now);
        userID = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
        storageReference =FirebaseStorage.getInstance().getReference("IssuesImages/"+fileName+userID);
        storageReference.putFile(imageUrl).addOnSuccessListener(task ->
        {
            Toast.makeText(getContext(), "Issue recorded successfully", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }).addOnFailureListener(task ->
                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show());

    }

    public void checker()
    {


        String roomNo1 =roomNo.getText().toString().trim();
        String issueType1 =issueType.getText().toString().trim();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId ==-1)
        {
            Toast.makeText(getContext(), "Ensure a hostel has been selected", Toast.LENGTH_SHORT).show();

        } else if (roomNo1.isEmpty()) {

            roomNo.setError("This field cannot be empty");
            roomNo.requestFocus();

        } else if (issueType1.isEmpty()) {

            issueType.setError("This field cannot be empty");
            issueType.requestFocus();

        }  else if (imageView.getDrawable() == null) {
            Toast.makeText(getContext(), "Ensure an image is attached", Toast.LENGTH_SHORT).show();
            
        } else {
            progressBar.setVisibility(View.VISIBLE);
            sendIssue(roomNo1,issueType1);

        }
    }
    private void sendIssue(String rN1,String iT1)
    {
        if(hostelA.isChecked())
        {
            hostelNameValue ="Hostel A";
        } else if (hostelB.isChecked()) {
            hostelNameValue ="Hostel B";
        } else if (hostelC.isChecked()) {
            hostelNameValue ="Hostel C";
        } else if (hostelD.isChecked()) {
            hostelNameValue ="Hostel D";
        } else if (hostelE.isChecked()) {
            hostelNameValue ="Hostel E";
        }
        Map<String, Object> issue =new HashMap<>();
        uploadImage();
        issue.put("HostelName",hostelNameValue);
        issue.put("RoomNo",rN1);
        issue.put("IssueTitle",iT1);
        issue.put("StudentEmail",userID);
        issue.put("ImageAttached",fileName+userID);
        issue.put("Status","Pending");

        db.collection("Issues")
                .document("Hostels")
                .collection(hostelNameValue)
                .add(issue).addOnSuccessListener(task ->
        {

        }).addOnFailureListener(task2 ->
                        Toast.makeText(getContext(),"Issue recorded", Toast.LENGTH_SHORT).show());
    }


}