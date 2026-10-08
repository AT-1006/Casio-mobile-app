package com.example.casio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {

    EditText signupEmail, signupPassword, signupConfirmPassword;
    Button signupBtn;
    TextView toLoginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        signupEmail = findViewById(R.id.signupemail);
        signupPassword = findViewById(R.id.signuppasswd);
        signupConfirmPassword = findViewById(R.id.signupconfrmpasswd);
        signupBtn = findViewById(R.id.signupbtn);
        toLoginPage = findViewById(R.id.tologinpage);

        // SIGN UP button click
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();
                String confirmPassword = signupConfirmPassword.getText().toString().trim();

                // Validation
                if (email.isEmpty()) {
                    signupEmail.setError("Email required");
                    signupEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    signupPassword.setError("Password required");
                    signupPassword.requestFocus();
                    return;
                }

                if (confirmPassword.isEmpty()) {
                    signupConfirmPassword.setError("Confirm password required");
                    signupConfirmPassword.requestFocus();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    signupConfirmPassword.setError("Passwords do not match");
                    signupConfirmPassword.requestFocus();
                    return;
                }

                // Success (for now)
                Toast.makeText(signup.this, "Signup Successful", Toast.LENGTH_SHORT).show();

                // OPTIONAL: move to login page after signup
                Intent intent = new Intent(signup.this, loginpage.class);
                startActivity(intent);
                finish();
            }
        });

        // LOGIN text click
        toLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(signup.this, loginpage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
