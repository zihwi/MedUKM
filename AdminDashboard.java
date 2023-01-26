package com.example.med4ukm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminDashboard extends AppCompatActivity {
    TextView usernameLabel;
    String _NAME,_USERNAME, _EMAIL, _PASSWORD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        usernameLabel = findViewById(R.id.staff_no_field);

        Intent intent = getIntent();
        _NAME = intent.getStringExtra("name");
        _USERNAME= intent.getStringExtra("username");
        _EMAIL= intent.getStringExtra("email");
        _PASSWORD= intent.getStringExtra("password");

        usernameLabel.setText(_USERNAME);
    }

    public void checkAppointment(View view) {
        Intent intent = new Intent(this, userlist.class);
        startActivity(intent);

    }

    public void uploadOnCall(View view) {
        Intent intent = new Intent(this, UploadOnCall.class);
        startActivity(intent);

    }

    public void staff_profile(View view) {
        Intent intentProfile = new Intent(this, StaffProfile.class);
        intentProfile.putExtra("namefdb",_NAME);
        intentProfile.putExtra("matricnofdb",_USERNAME);
        intentProfile.putExtra("emailfdb",_EMAIL);
        intentProfile.putExtra("passwordfdb",_PASSWORD);
        startActivity(intentProfile);
    }

    public void logout(View view) {
        AlertDialog logoutDialog = new AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", null)
                .setNegativeButton("No", null)
                .show();
        Button positiveButton = logoutDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    public void admindoc(View view) {
        Intent intent = new Intent(this, DocOnDuty.class);
        startActivity(intent);
    }
}

