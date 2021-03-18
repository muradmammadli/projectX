package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.viewHolder> {

    private final Context context;
    private final List<User> userList;

    public myAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        User user = userList.get(position);
        Debt debt = user.getDebt();
        Credit credit = user.getCredit();
        Contact contact = user.getContact();
        Account account = user.getAccount();
        Job job = user.getJob();

        holder.user.setText(user.getClass().getSimpleName() + " " + user.getId());
        holder.debt.setText(debt.getClass().getSimpleName() + " " + debt.getUserId());
        holder.credit.setText(credit.getClass().getSimpleName() + " " + credit.getUserId());
        holder.contact.setText(contact.getClass().getSimpleName() + " " + contact.getUserId());
        holder.account.setText(account.getClass().getSimpleName() + " " + account.getUserId());
        holder.job.setText(job.getClass().getSimpleName() + " " + job.getUserId());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView user, debt, credit, account, contact, job;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.user);
            debt = itemView.findViewById(R.id.debt);
            credit = itemView.findViewById(R.id.credit);
            account = itemView.findViewById(R.id.account);
            contact = itemView.findViewById(R.id.contact);
            job = itemView.findViewById(R.id.job);
        }
    }

}
