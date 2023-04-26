package com.example.hostelfaultreportingsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManagerIssuesAdapter extends RecyclerView.Adapter<ManagerIssuesAdapter.MyViewHolder> {
    Context context;
    ArrayList<Issues> IssuesArraylist;

    public ManagerIssuesAdapter(Context context, ArrayList<Issues> issuesArraylist) {
        this.context = context;
        this.IssuesArraylist = issuesArraylist;

    }

    @NonNull
    @Override
    public ManagerIssuesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_issue,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerIssuesAdapter.MyViewHolder holder, int position) {
        Issues issues =IssuesArraylist.get(position);
        holder.StudentEmail.setText(issues.StudentEmail);
        holder.IssueTitle.setText(issues.IssueTitle);
        holder.HostelName.setText(issues.HostelName);
        holder.RoomNo.setText(issues.RoomNo);
        holder.status_value.setText(issues.Status);

        holder.cardView.setOnClickListener(v->
        {
            Intent intent = new Intent(context,IssueDetails.class);
            intent.putExtra("IssueTitleValue1",issues.IssueTitle);
            intent.putExtra("HostelNameValue1",issues.HostelName);
            intent.putExtra("RoomNoValue1",issues.RoomNo);
            intent.putExtra("SenderEmailValue1",issues.StudentEmail);
            intent.putExtra("ImageAttachedValue1",issues.ImageAttached);
            intent.putExtra("Status",issues.Status);
            ((Activity) context).finish();

            context.startActivity(intent);
        });




    }

    @Override
    public int getItemCount() {
        return IssuesArraylist.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView IssueTitle;
        TextView HostelName;
        TextView RoomNo;
        TextView StudentEmail;

        CardView cardView;
        TextView status_value;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IssueTitle =itemView.findViewById(R.id.IssueTitleValue);
            HostelName =itemView.findViewById(R.id.HostelNameValue);
            RoomNo =itemView.findViewById(R.id.RoomNoValue);
            StudentEmail =itemView.findViewById(R.id.SenderEmailValue);
            cardView = itemView.findViewById(R.id.card_view_container);
            status_value =itemView.findViewById(R.id.status);

        }
        
    }
}
