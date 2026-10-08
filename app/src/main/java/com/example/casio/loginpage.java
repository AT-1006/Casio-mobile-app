package com.example.casio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginpage extends AppCompatActivity {

    EditText logEmail, logPassword;
    Button loginBtn;
    TextView toSignupPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        // Initialize views
        logEmail = findViewById(R.id.logemail);
        logPassword = findViewById(R.id.logpasswd);
        loginBtn = findViewById(R.id.loginbtn);
        toSignupPage = findViewById(R.id.tosignuppage);

        // LOGIN button click
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = logEmail.getText().toString().trim();
                String password = logPassword.getText().toString().trim();

                // Validation
                if (email.isEmpty()) {
                    logEmail.setError("Email required");
                    logEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    logPassword.setError("Password required");
                    logPassword.requestFocus();
                    return;
                }

                // Dummy login success (replace later with backend / Firebase)
                Toast.makeText(loginpage.this, "Login Successful", Toast.LENGTH_SHORT).show();

                // Go to Home page
                Intent intent = new Intent(loginpage.this, CasioHome.class);
                startActivity(intent);
                finish();
            }
        });

        // SIGNUP text click
        toSignupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(loginpage.this, signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
