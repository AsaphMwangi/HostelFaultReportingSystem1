package com.example.hostelfaultreportingsystem;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ManagerMessagesFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    ArrayList<Issues> IssuesArrayList;
    ManagerIssuesAdapter myAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ProgressDialog progressDialog;
    FirebaseAuth mAuth =FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        view= inflater.inflate(R.layout.fragment_manager_messages, container, false);

        recyclerView =view.findViewById(R.id.MangerMessagesRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressDialog =new ProgressDialog(getContext());
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        IssuesArrayList =new ArrayList<Issues>();
        myAdapter =new ManagerIssuesAdapter(getContext(),IssuesArrayList);

        recyclerView.setAdapter(myAdapter);

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
                .collection("Hostel A").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                            myAdapter.notifyDataSetChanged();
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
                .collection("Hostel B").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                            myAdapter.notifyDataSetChanged();
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
                .collection("Hostel C").addSnapshotListener(new EventListener<QuerySnapshot>() {
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

                        assert value != null;
                        for (DocumentChange documentChange:value.getDocumentChanges())
                        {

                            if (documentChange.getType() == DocumentChange.Type.ADDED)
                            {

                                IssuesArrayList.add(documentChange.getDocument().toObject(Issues.class));

                            }
                            myAdapter.notifyDataSetChanged();
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
                .collection("Hostel D").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                            myAdapter.notifyDataSetChanged();
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
                .collection("Hostel E").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                            myAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }
                        }
                    }
                });
    }
}