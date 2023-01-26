package com.example.med4ukm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class BookSuccess extends AppCompatActivity {

    EditText nameSuccess, dateSuccess, typeSuccess;

    String _BOOKNAME, _BOOKDATE, _BOOKTYPE;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_success);

        nameSuccess = findViewById(R.id.editTextTextName);
        dateSuccess = findViewById(R.id.editTextTextDate);
        typeSuccess = findViewById(R.id.editTextTextBookingType);

        showAllUserData();
    }

    private void showAllUserData(){
        Intent intent = getIntent();
        _BOOKNAME= intent.getStringExtra("name");
        _BOOKDATE= intent.getStringExtra("date");
        _BOOKTYPE= intent.getStringExtra("type");

        nameSuccess.setText((_BOOKNAME));
        dateSuccess.setText((_BOOKDATE));
        typeSuccess.setText((_BOOKTYPE));
    }

}