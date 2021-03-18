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
    private final List<Debt> debtList;
    private final List<Credit> creditList;
    private final List<Contact> contactList;
    private final List<Account> accountList;
    private final List<Job> jobList;

    public myAdapter(Context context, List<User> userList, List<Debt> debt, List<Credit> credit, List<Contact> contact, List<Account> account, List<Job> job) {
        this.context = context;
        this.userList = userList;
        this.debtList = debt;
        this.creditList = credit;
        this.contactList = contact;
        this.accountList = account;
        this.jobList = job;
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
        Debt debt = debtList.get(position);
        Credit credit = creditList.get(position);
        Contact contact = contactList.get(position);
        Account account = accountList.get(position);
        Job job = jobList.get(position);

        holder.user.setText(user.getClass().getSimpleName() + " " + user.getId());
        holder.debt.setText(debt.getClass().getSimpleName() + " " + debt.getId());
        holder.credit.setText(credit.getClass().getSimpleName() + " " + credit.getId());
        holder.contact.setText(contact.getClass().getSimpleName() + " " + contact.getId());
        holder.account.setText(account.getClass().getSimpleName() + " " + account.getId());
        holder.job.setText(job.getClass().getSimpleName() + " " + job.getId());

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
