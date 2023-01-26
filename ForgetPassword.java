package com.example.med4ukm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.med4ukm.databinding.ActivityForgetPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;

    TextInputLayout email;
    Button resetPasswordButton;
    ProgressDialog dialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forget_password);

        email = findViewById(R.id.user_email);
        resetPasswordButton = findViewById(R.id.reset_password_btn);
//        dialog = findViewById(R.id.progressBar);

        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(ForgetPassword.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");

        binding.resetPasswordBtn.setOnClickListener(view -> {
            forgotPassword();
        });


    }

    private Boolean validateEmail() {
        String val = binding.email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+[.]+[a-z]+[.]+[a-z]+[.]+my";


        if (val.isEmpty()) {
            binding.email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            binding.email.setError("Invalid email address");
            return false;
        } else {
            binding.email.setError(null);
//            binding.email.setErrorEnabled(false);
            return true;
        }

    }

    private void forgotPassword() {
        if (!validateEmail()){
            return;

        }

        dialog.show();

        auth.sendPasswordResetEmail(binding.email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                dialog.dismiss();
                if (task.isSuccessful()){
                    startActivity(new Intent(ForgetPassword.this, Login.class));
                    finish();
                    Toast.makeText(ForgetPassword.this, "Please Check Your Email Address!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ForgetPassword.this, "Enter Correct Email", Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForgetPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}