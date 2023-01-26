package com.example.med4ukm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {
    TextView matricnoLabel;

   // String _USERNAME;
    String _USERNAME, _NAME, _EMAIL, _PHONENO, _PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        matricnoLabel = findViewById(R.id.matric_no_field2);
        //_USERNAME= getIntent().getStringExtra("matricno");
        //matricnoLabel.setText(_USERNAME);

        Intent intent = getIntent();
        _USERNAME= intent.getStringExtra("matricno");
        _NAME= intent.getStringExtra("name");
        _EMAIL= intent.getStringExtra("email");
        _PHONENO= intent.getStringExtra("phoneNo");
        _PASSWORD= intent.getStringExtra("password");

        //fullNameLabel.setText(_NAME);
        matricnoLabel.setText(_NAME);
        //fullName.getEditText().setText(_NAME);
        //email.getEditText().setText(_EMAIL);
        //phoneNo.getEditText().setText(_PHONENO);
        //password.getEditText().setText(_PASSWORD);
    }


    public void user_profile(View view) {


        Intent intentProfile = new Intent(this, UserProfile.class);
        intentProfile.putExtra("namefdb",_NAME);
        intentProfile.putExtra("matricnofdb",_USERNAME);
        intentProfile.putExtra("phoneNofdb",_PHONENO);
        intentProfile.putExtra("emailfdb",_EMAIL);
        intentProfile.putExtra("passwordfdb",_PASSWORD);
        startActivity(intentProfile);
    }


    public void appointment(View view) {
        Intent intent = new Intent(this, BookAppointment.class);
        startActivity(intent);

    }
    public void emergency(View view) {
        Intent intent = new Intent(this, emergencycall.class);
        startActivity(intent);

    }
    public void docOnDuty(View view) {
        Intent intent = new Intent(this, DocOnDuty.class);
        startActivity(intent);

    }

    public void onCallList(View view) {
        Intent intent = new Intent(this, OnCallList.class);
        startActivity(intent);

    }
    public void healthstatus(View view) {
        Intent intent = new Intent(this, HealthStatus.class);
        startActivity(intent);

    }

    public void logout(View view) {
        AlertDialog logoutDialog = new AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes",null)
                .setNegativeButton("No",null)
                .show();
        Button positiveButton = logoutDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


    }

    public void common_faqs(View view) {
        Intent intent = new Intent(this, CommonFAQs.class);
        startActivity(intent);
    }
}