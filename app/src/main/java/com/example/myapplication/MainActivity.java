package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.style.AlignmentSpan;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        HashMap<Integer, List<Job>> jobMap = new HashMap<>();

        for (Debt debt : debtList) {
            if (debtMap.containsKey(debt.getUserId())) {
                List<Debt> list = debtMap.get(debt.getUserId());
                list.add(debt);
            } else {
                List<Debt> list = new ArrayList<>();
                list.add(debt);
                debtMap.put(debt.getUserId(), list);
            }
        }


        for (Credit credit : creditList) {
            if (creditMap.containsKey(credit.getUserId())) {
                List<Credit> list = creditMap.get(credit.getUserId());
                list.add(credit);
            } else {
                List<Credit> list = new ArrayList<>();
                list.add(credit);
                creditMap.put(credit.getUserId(), list);
            }
        }
        for (Contact contact : contactList) {
            if (contactMap.containsKey(contact.getUserId())) {
                List<Contact> list = contactMap.get(contact.getUserId());
                list.add(contact);
            } else {
                List<Contact> list = new ArrayList<>();
                list.add(contact);
                contactMap.put(contact.getUserId(), list);
            }
        }
        for (Account account : accountList) {
            if (accountMap.containsKey(account.getUserId())) {
                List<Account> list = accountMap.get(account.getUserId());
                list.add(account);
            } else {
                List<Account> list = new ArrayList<>();
                list.add(account);
                accountMap.put(account.getUserId(), list);
            }
        }
        for (Job job : jobList) {
            if (jobMap.containsKey(job.getUserId())) {
                List<Job> list = jobMap.get(job.getUserId());
                list.add(job);
            } else {
                List<Job> list = new ArrayList<>();
                list.add(job);
                jobMap.put(job.getUserId(), list);
            }
        }


        for (User user : userList) {
            user.setDebtList(debtMap.get(user.getId()));
            user.setAccountList(accountMap.get(user.getId()));
            user.setContactList(contactMap.get(user.getId()));
            user.setCreditList(creditMap.get(user.getId()));
            user.setJobList(jobMap.get(user.getId()));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new myAdapter(this, userList);
        recyclerView.setAdapter(mAdapter);

    }
}