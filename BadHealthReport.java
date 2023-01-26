package com.example.med4ukm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BadHealthReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_health_report);
    }

    public void bookappointment(View view) {
        Intent intent = new Intent(this, BookAppointment.class);
        startActivity(intent);
    }
}