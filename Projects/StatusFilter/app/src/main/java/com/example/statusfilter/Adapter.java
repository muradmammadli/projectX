package com.example.statusfilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.userHolder> {

    Context context;
    ArrayList<Users> userList;

    public Adapter(Context context, ArrayList<Users> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_user, parent, false);
        return new userHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull userHolder holder, int position) {
        Users user = userList.get(position);
        holder.userName.setText(user.getUserName());
        holder.status.setText(String.valueOf(user.getStatusCode()));
        holder.timeStamp.setText(String.valueOf(user.getTimeStamp()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class userHolder extends RecyclerView.ViewHolder {
        TextView userName, status, timeStamp;

        public userHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username);
            status = itemView.findViewById(R.id.status);
            timeStamp = itemView.findViewById(R.id.date);
        }
    }

}
