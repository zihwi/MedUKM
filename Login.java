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
import android.view.WindowManager;
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

public class Login extends AppCompatActivity {
    Button callSignUp,login_btn, adminSignIn, forgetPassword;
    ImageView image;
    TextView logoText;
    TextInputLayout username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.reg_password);
        login_btn = findViewById(R.id.reg_login_btn);
        callSignUp = findViewById(R.id.signup_screen);
        adminSignIn = findViewById(R.id.admin_signin);

        forgetPassword = findViewById(R.id.forget_password);



        callSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Login.this,SignUp.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(logoText,"logo_text");
                pairs[2] = new Pair<View,String>(username,"full_name");
                pairs[3] = new Pair<View,String>(password,"pass_word");
                pairs[4] = new Pair<View,String>(callSignUp,"button_go");

                if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });

        adminSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Login.this,AdminLogin.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(logoText,"logo_text");
                pairs[2] = new Pair<View,String>(username,"full_name");
                pairs[3] = new Pair<View,String>(password,"pass_word");
                pairs[4] = new Pair<View,String>(adminSignIn,"button_go");

                if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }



    private Boolean validateUsername(){
        String val=username.getEditText().getText().toString();

        if(val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val=password.getEditText().getText().toString();

        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view){
        //validate
        if(!validateUsername()|| !validatePassword() ){
            return;
        }
        else{
            isUser();
        }
    }

    private void isUser() {

        final String userEnteredMatric = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser= reference.orderByChild("matricno").equalTo(userEnteredMatric);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredMatric).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredPassword)){
                        username.setError(null);
                        username.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(userEnteredMatric).child("name").getValue(String.class);
                        String matricnoFromDB = snapshot.child(userEnteredMatric).child("matricno").getValue(String.class);
                        String phonenoFromDB = snapshot.child(userEnteredMatric).child("phoneNo").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredMatric).child("email").getValue(String.class);
                        passwordFromDB = snapshot.child(userEnteredMatric).child("password").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),Dashboard.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("matricno",matricnoFromDB);
                        intent.putExtra("phoneNo",phonenoFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("password",passwordFromDB);

                        startActivity(intent);

               }
               else{
                   password.setError("Wrong Password");
                   password.requestFocus();
               }

                }
                else{
                    username.setError("No such user");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void forgetUser(View view) {
        Intent intent = new Intent(this, ForgetPassword.class);
        startActivity(intent);
    }
}