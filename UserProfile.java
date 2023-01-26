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

public class UserProfile extends AppCompatActivity {
    TextInputLayout fullName,email,phoneNo,password;
    TextView fullNameLabel,matricnoLabel;

    String _USERNAME, _NAME, _EMAIL, _PHONENO, _PASSWORD;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        //Hooks
        fullName = findViewById(R.id.reg_adName);
        email = findViewById(R.id.reg_email);
        phoneNo = findViewById(R.id.reg_phoneNo);
        password = findViewById(R.id.reg_password);
        fullNameLabel = findViewById(R.id.full_name_field);
        matricnoLabel = findViewById(R.id.matric_no_field);

        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        _USERNAME= intent.getStringExtra("matricnofdb");
        _NAME= intent.getStringExtra("namefdb");
        _EMAIL= intent.getStringExtra("emailfdb");
        _PHONENO= intent.getStringExtra("phoneNofdb");
        _PASSWORD= intent.getStringExtra("passwordfdb");

        fullNameLabel.setText(_NAME);
        matricnoLabel.setText(_USERNAME);
        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(_PHONENO);
        password.getEditText().setText(_PASSWORD);
    }
        // fullNameLabel.setText(_NAME);
        // matricnoLabel.setText(_USERNAME);
        // fullName.getEditText().setText(_NAME);
        // email.getEditText().setText(_EMAIL);
        //phoneNo.getEditText().setText(_PHONENO);
        // password.getEditText().setText(_PASSWORD);
        ///show user data
        //showAllUserData();

        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                _USERNAME= snapshot.child("matricno").getValue(String.class);
                _NAME= snapshot.child("name").getValue(String.class);
                _EMAIL= snapshot.child("email").getValue(String.class);
                _PHONENO= snapshot.child("phoneNo").getValue(String.class);
                _PASSWORD= snapshot.child("password").getValue(String.class);
                fullNameLabel.setText(_NAME);
                matricnoLabel.setText(_USERNAME);
                fullName.getEditText().setText(_NAME);
                email.getEditText().setText(_EMAIL);
                phoneNo.getEditText().setText(_PHONENO);
                password.getEditText().setText(_PASSWORD);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /*private void showAllUserData() {
        Intent intent = getIntent();
        _USERNAME= intent.getStringExtra("matricno");
        _NAME= intent.getStringExtra("name");
        _EMAIL= intent.getStringExtra("email");
        _PHONENO= intent.getStringExtra("phoneNo");
        _PASSWORD= intent.getStringExtra("password");

        fullNameLabel.setText(_NAME);
        matricnoLabel.setText(_USERNAME);
        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(_PHONENO);
        password.getEditText().setText(_PASSWORD);
        }





        /*Intent intent = getIntent();
        _USERNAME= intent.getStringExtra("matricno");
        _NAME= intent.getStringExtra("name");
        _EMAIL= intent.getStringExtra("email");
        _PHONENO= intent.getStringExtra("phoneNo");
        _PASSWORD= intent.getStringExtra("password");*/


    //}

    public void update(View view){
        if(isNameChanged()||isPasswordChanged()||isPhoneChanged()||isEmailChanged()){
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

    private boolean isPhoneChanged() {
        if(!_PHONENO.equals(phoneNo.getEditText().getText().toString())){
            reference.child(_USERNAME).child("phoneNo").setValue(phoneNo.getEditText().getText().toString().trim());
            _PHONENO = phoneNo.getEditText().getText().toString();
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
        if(!_NAME.equals(fullName.getEditText().getText().toString())){
            reference.child(_USERNAME).child("name").setValue(fullName.getEditText().getText().toString().trim());
            _NAME = fullName.getEditText().getText().toString();
            _NAME = fullNameLabel.getText().toString();

            return true;
        }
        else{
            return false;
        }
    }

    public void healthbutton(View view) {
        Intent intent = new Intent(this, HealthStatus.class);
        startActivity(intent);

    }
}