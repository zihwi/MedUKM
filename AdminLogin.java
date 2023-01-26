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

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
    Button adminSignIn, login_btn, adminSignUp, userSignIn;
    ImageView image;
    TextView logoText;
    TextInputLayout adUsername, adPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        adUsername = findViewById(R.id.admin_username);
        adPassword = findViewById(R.id.admin_password);
        login_btn = findViewById(R.id.admin_login_btn);

        adminSignIn = findViewById(R.id.admin_signin);
        adminSignUp = findViewById(R.id.signup_adminscreen);
        userSignIn = findViewById(R.id.user_signin);

/*
        adminSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogin.this, Login.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(adUsername, "full_name");
                pairs[3] = new Pair<View, String>(adPassword, "pass_word");
                pairs[4] = new Pair<View, String>(adminSignIn, "button_go");

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = makeSceneTransitionAnimation(AdminLogin.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });

 */

        adminSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogin.this, AdminSignUp.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(adUsername, "full_name");
                pairs[3] = new Pair<View, String>(adPassword, "pass_word");
                pairs[4] = new Pair<View, String>(adminSignUp, "button_go");

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = makeSceneTransitionAnimation(AdminLogin.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });

        userSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogin.this, Login.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(adUsername, "full_name");
                pairs[3] = new Pair<View, String>(adPassword, "pass_word");
                pairs[4] = new Pair<View, String>(userSignIn, "button_go");

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = makeSceneTransitionAnimation(AdminLogin.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }

    private Boolean validateUsername() {
        String val = adUsername.getEditText().getText().toString();

        if (val.isEmpty()) {
            adUsername.setError("Field cannot be empty");
            return false;
        } else {
            adUsername.setError(null);
            adUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = adPassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            adPassword.setError("Field cannot be empty");
            return false;
        } else {
            adPassword.setError(null);
            adPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void loginAdmin(View view) {
        //validate
        if (!validateUsername() || !validatePassword()) {
            return;
        } else {
            isAdmin();
        }
    }

    private void isAdmin() {

        final String adminEnteredUsername = adUsername.getEditText().getText().toString().trim();
        final String adminEnteredPassword = adPassword.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("admin");
        Query checkUser = reference.orderByChild("username").equalTo(adminEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adUsername.setError(null);
                    adUsername.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(adminEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(adminEnteredPassword)) {
                        adUsername.setError(null);
                        adUsername.setErrorEnabled(false);

                       String nameFromDB = snapshot.child(adminEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = snapshot.child(adminEnteredUsername).child("username").getValue(String.class);
//                        String phonenoFromDB = snapshot.child(adminEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = snapshot.child(adminEnteredUsername).child("email").getValue(String.class);
                        passwordFromDB = snapshot.child(adminEnteredUsername).child("password").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);

                       intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
//                        intent.putExtra("phoneNo", phonenoFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);

                    } else {
                        adPassword.setError("Wrong Password");
                        adPassword.requestFocus();
                    }

                } else {
                    adUsername.setError("No such admin user");
                    adUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public void forgetAdmin(View view) {
        Intent intent = new Intent(this, ForgetPassword.class);
        startActivity(intent);
    }
}