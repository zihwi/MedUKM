package com.example.med4ukm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffProfile extends AppCompatActivity {
    TextInputLayout fullName,email,phoneNo,password;
    TextView fullNameLabel,staffnoLabel;

    String _USERNAME, _NAME, _EMAIL, _PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_profile);

        reference = FirebaseDatabase.getInstance().getReference("admin");

        //Hooks

        fullName = findViewById(R.id.reg_adName);
        email = findViewById(R.id.reg_adEmail);
        password = findViewById(R.id.reg_adPassword);

        fullNameLabel = findViewById(R.id.full_name_field);
        staffnoLabel = findViewById(R.id.staff_no_field);

        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        _NAME= intent.getStringExtra("namefdb");
        _USERNAME= intent.getStringExtra("matricnofdb");
        _EMAIL= intent.getStringExtra("emailfdb");
        _PASSWORD= intent.getStringExtra("passwordfdb");

        fullNameLabel.setText(_NAME);
        staffnoLabel.setText(_USERNAME);

        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        password.getEditText().setText(_PASSWORD);


    }

    public void update(View view){
        if(isNameChanged()|| isPasswordChanged() ||isEmailChanged()){
            Toast.makeText(this,"Data has been updated",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Data is same",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailChanged() {
        if(!_EMAIL.equals(email.getEditText().getText().toString())){
            reference.child(_USERNAME).child("email").setValue(email.getEditText().getText().toString().trim());
            _EMAIL = email.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if(!_PASSWORD.equals(password.getEditText().getText().toString())){
            reference.child(_USERNAME).child("password").setValue(password.getEditText().getText().toString().trim());
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!_USERNAME.equals(fullName.getEditText().getText().toString())){
            reference.child(_USERNAME).child("name").setValue(fullName.getEditText().getText().toString().trim());
            _USERNAME = fullName.getEditText().getText().toString();
            _USERNAME = fullNameLabel.getText().toString();

            return true;
        }
        else{
            return false;
        }
    }
}