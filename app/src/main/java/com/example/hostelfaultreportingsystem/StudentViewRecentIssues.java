package com.example.hostelfaultreportingsystem;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;


public class StudentViewRecentIssues extends Fragment {

    View view;
    Button reportIssue;
    RecyclerView recyclerView;
    ArrayList<Issues> IssuesArrayList;
    StudentIssuesAdapter myAdapter2;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ProgressDialog progressDialog;
    FirebaseAuth mAuth =FirebaseAuth.getInstance();
    String userID;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        view =inflater.inflate(R.layout.fragment_student_view_recent_issues, container, false);
        reportIssue =view.findViewById(R.id.ReportNewIssue);
        recyclerView =view.findViewById(R.id.StudentRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressDialog =new ProgressDialog(getContext());
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        userID = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();

        IssuesArrayList =new ArrayList<Issues>();
        myAdapter2 =new StudentIssuesAdapter(getContext(),IssuesArrayList);

        recyclerView.setAdapter(myAdapter2);

        reportIssue.setOnClickListener(view1 ->
        {
            StudentMainScreen studentMainScreen =(StudentMainScreen) requireActivity();
            studentMainScreen.studentReportNewIssue();
        });
        getIssues();
        return view;
    }
    public void getIssues()
    {

        LoadHostelAIssues();
        LoadHostelBIssues();
        LoadHostelCIssues();
        LoadHostelDIssues();
        LoadHostelEIssues();

    }

    private void LoadHostelAIssues() {

        db.collection("Issues")
                .document("Hostels")
                .collection("Hostel A").whereEqualTo("StudentEmail",userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null)
                        {
                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(getContext(), "Error in getting data", Toast.LENGTH_SHORT).show();
                        }

                        for (DocumentChange documentChange:value.getDocumentChanges())
                        {

                            if (documentChange.getType() == DocumentChange.Type.ADDED)
                            {

                                IssuesArrayList.add(documentChange.getDocument().toObject(Issues.class));

                            }
                            myAdapter2.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                        }
                    }
                });

    }
    private void LoadHostelBIssues()
    {
        db.collection("Issues")
                .document("Hostels")
                .collection("Hostel B").whereEqualTo("StudentEmail",userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null)
                        {
                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(getContext(), "Error in getting data", Toast.LENGTH_SHORT).show();
                        }

                        for (DocumentChange documentChange:value.getDocumentChanges())
                        {

                            if (documentChange.getType() == DocumentChange.Type.ADDED)
                            {

                                IssuesArrayList.add(documentChange.getDocument().toObject(Issues.class));

                            }
                            myAdapter2.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                        }
                    }
                });
    }
    private void LoadHostelCIssues()
    {
        db.collection("Issues")
                .document("Hostels")
                .collection("Hostel C").whereEqualTo("StudentEmail",userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null)
                        {
                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(getContext(), "Error in getting data", Toast.LENGTH_SHORT).show();
                        }

                        for (DocumentChange documentChange:value.getDocumentChanges())
                        {

                            if (documentChange.getType() == DocumentChange.Type.ADDED)
                            {

                                IssuesArrayList.add(documentChange.getDocument().toObject(Issues.class));

                            }
                            myAdapter2.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                        }
                    }
                });
    }
    private void LoadHostelDIssues()
    {
        db.collection("Issues")
                .document("Hostels")
                .collection("Hostel D").whereEqualTo("StudentEmail",userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null)
                        {
                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(getContext(), "Error in getting data", Toast.LENGTH_SHORT).show();
                        }

                        for (DocumentChange documentChange:value.getDocumentChanges())
                        {

                            if (documentChange.getType() == DocumentChange.Type.ADDED)
                            {

                                IssuesArrayList.add(documentChange.getDocument().toObject(Issues.class));

                            }
                            myAdapter2.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                        }
                    }
                });
    }
    private void LoadHostelEIssues()
    {
        db.collection("Issues")
                .document("Hostels")
                .collection("Hostel E").whereEqualTo("StudentEmail",userID).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null)
                        {
                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(getContext(), "Error in getting data", Toast.LENGTH_SHORT).show();
                        }

                        for (DocumentChange documentChange:value.getDocumentChanges())
                        {

                            if (documentChange.getType() == DocumentChange.Type.ADDED)
                            {

                                IssuesArrayList.add(documentChange.getDocument().toObject(Issues.class));


                            }
                            myAdapter2.notifyDataSetChanged();

                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                        }
                    }
                });
    }
}