package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    List<User> userList = new ArrayList<>();
    List<Debt> debtList = new ArrayList<>();
    List<Credit> creditList = new ArrayList<>();
    List<Job> jobList = new ArrayList<>();
    List<Contact> contactList = new ArrayList<>();
    List<Account> accountList = new ArrayList<>();
    List<Debt> debt;
    List<Credit> credit;
    List<Contact> contact;
    List<Account> account;
    List<Job> job;

    RecyclerView recyclerView;
    myAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);


        for (int i = 0; i < 50; i++) {
            userList.add(new User(i));
        }
        for (int i = 0; i < 50; i++) {
            debtList.add(new Debt(i, i));
        }
        for (int i = 0; i < 50; i++) {
            creditList.add(new Credit(i, i));
        }
        for (int i = 0; i < 50; i++) {
            jobList.add(new Job(i, i));
        }
        for (int i = 0; i < 50; i++) {
            contactList.add(new Contact(i, i));
        }
        for (int i = 0; i < 50; i++) {
            accountList.add(new Account(i, i));
        }

        HashMap<Integer, List<Debt>> debtMap = new HashMap<>();
        HashMap<Integer, List<Credit>> creditMap = new HashMap<>();
        HashMap<Integer, List<Contact>> contactMap = new HashMap<>();
        HashMap<Integer, List<Account>> accountMap = new HashMap<>();
        HashMap<Integer, List<Job>> jobs = new HashMap<>();
        for (Debt debt : debtList) {
            debtMap.put(debt.getUserId(), debtList);
        }
        for (Credit credit : creditList) {
            creditMap.put(credit.getUserId(), creditList);
        }
        for (Contact contact : contactList) {
            contactMap.put(contact.getUserId(), contactList);
        }
        for (Account account : accountList) {
            accountMap.put(account.getUserId(), accountList);
        }
        for (Job job : jobList) {
            jobs.put(job.getUserId(), jobList);
        }


        for (User user : userList) {
            user.setDebtList(debtMap.get(user.getId()));
            user.setAccountList(accountMap.get(user.getId()));
            user.setContactList(contactMap.get(user.getId()));
            user.setCreditList(creditMap.get(user.getId()));
            user.setJobList(jobs.get(user.getId()));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new myAdapter(this,userList);
        recyclerView.setAdapter(mAdapter);


//        for (User user : userList) {
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                Debt debt = debtList.stream().filter(a -> a.getId() == user.getId()).collect(Collectors.toList()).get(0);
//                Log.d("debt", String.valueOf(debt));
//            }
//        }

    }
}