package com.example.med4ukm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userlist extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapterShowAppt myAdapterShowAppt;
    ArrayList<User2> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("bookappointment");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapterShowAppt = new MyAdapterShowAppt(this, list);
        recyclerView.setAdapter(myAdapterShowAppt);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    User2 user = dataSnapshot.getValue(User2.class);
                    list.add(user);

            }

            myAdapterShowAppt.notifyDataSetChanged();
        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}