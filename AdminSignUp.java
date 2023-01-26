package com.example.med4ukm;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

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

public class AdminSignUp extends AppCompatActivity {

    TextInputLayout regName, adUsername, adEmail, adPassword;
    Button regadminBtn, regToLogInBtn, adLogin;
    ImageView image;
    TextView logoText;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        regName = findViewById(R.id.reg_staff_name);
//        regPhoneNo =findViewById(R.id.reg_phoneNo);
        adUsername = findViewById(R.id.reg_adUsername);
        adEmail = findViewById(R.id.reg_adEmail);
        adPassword = findViewById(R.id.reg_adPassword);
        regadminBtn = findViewById(R.id.reg_adBtn);

//        regToLogInBtn = findViewById(R.id.reg_login_btn);

        adLogin = findViewById(R.id.admin_login);

      /*  adLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminSignUp.this, AdminLogin.class);
                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(regName, "register_name");
                pairs[3] = new Pair<View, String>(adUsername, "full_name");
                pairs[4] = new Pair<View, String>(adEmail, "admin_email");
                pairs[5] = new Pair<View, String>(adPassword, "pass_word");
                pairs[6] = new Pair<View, String>(adLogin, "button_go");

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = makeSceneTransitionAnimation(AdminSignUp.this, pairs);
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
        String val = adUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            adUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            adUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            adUsername.setError("White Spaces are not allowed.");
            return false;
        } else {
            adUsername.setError(null);
            adUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = adEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+[.]+[a-z]+[.]+[a-z]+[.]+my";


        if (val.isEmpty()) {
            adEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            adEmail.setError("Invalid email address");
            return false;
        } else {
            adEmail.setError(null);
            adEmail.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword() {
        String val = adPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +   //any letter
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                ".{4,}" +     //at least 4 characters
                "$";

        if (val.isEmpty()) {
            adPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            adPassword.setError("Password is too weak");
            return false;
        } else {
//            regName.setError(null);
            return true;
        }
    }


    public void registerAdmin(View view) {

        if (!validateName() || !validateEmail() || !validateUsername() || !validatePassword()) {
            return;
        } else {

            regadminBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rootNode = FirebaseDatabase.getInstance(); //call data from fb
                    reference = rootNode.getReference("admin");

                    String name = regName.getEditText().getText().toString();
                    String username = adUsername.getEditText().getText().toString();
                    String email = adEmail.getEditText().getText().toString();
//
//                    String phoneNo = regPhoneNo.getEditText().getText().toString();
                    String password = adPassword.getEditText().getText().toString();
                    UserHelperClass3 helperClass = new UserHelperClass3(name, username, email, password);
                    reference.child(username).setValue(helperClass);
                    Toast.makeText(AdminSignUp.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                    startActivity(intent);

                }

            });
        }
    }


    public void adminlogin(View view) {
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }
}