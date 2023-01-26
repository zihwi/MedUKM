package com.example.med4ukm;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    Button regBtn, regToLogInBtn, alreadyLogin;

    ImageView image;
    TextView logoText;
    TextInputLayout username, password, matricno, email, phoneno;
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_matric);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);

        regBtn = findViewById(R.id.reg_btn);

//        regToLogInBtn = findViewById(R.id.reg_login_btn);
        alreadyLogin = findViewById(R.id.alrd_acc);

/*
        alreadyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(regName, "full_name");
//                pairs[2] = new Pair<View, String>(regUsername, "matric_no");
//              pairs[4] = new Pair<View, String>(regEmail, "user_email");
//              pairs[5] = new Pair<View, String>(regPhoneNo, "phone_no");
                pairs[3] = new Pair<View, String>(regPassword, "pass_word");
                pairs[4] = new Pair<View, String>(alreadyLogin, "button_go");

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = makeSceneTransitionAnimation(SignUp.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });*/
    }


    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Matric No too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed.");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+[.]+[a-z]+[.]+[a-z]+[.]+my";


        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +   //any letter
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                ".{4,}" +     //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regName.setError(null);
            return true;
        }
    }


    public void registerUser(View view) {

        if (!validateName() || !validateEmail() || !validatePhoneNo() || !validateUsername() || !validatePassword()) {
            return;
        } else {

            regBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rootNode = FirebaseDatabase.getInstance(); //call data from fb
                    reference = rootNode.getReference("users");

                    String name = regName.getEditText().getText().toString();
                    String matricno = regUsername.getEditText().getText().toString();
                    String email = regEmail.getEditText().getText().toString();
                    String phoneNo = regPhoneNo.getEditText().getText().toString();
                    String password = regPassword.getEditText().getText().toString();
                    UserHelperClass helperClass = new UserHelperClass(name, matricno, email, phoneNo, password);
                    reference.child(matricno).setValue(helperClass);
                    Toast.makeText(SignUp.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);

                }

            });
        }
    }

    public void loginuser(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}