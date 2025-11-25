package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSendEmail;
    private ImageView btnBack;
    private TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize views
        etEmail = findViewById(R.id.etEmail);
        btnSendEmail = findViewById(R.id.btnSendEmail);
        btnBack = findViewById(R.id.btnBack);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Send Email Button
        btnSendEmail.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            } else {
                // Call API to send reset email
                sendPasswordResetEmail(email);
            }
        });

        // Back Button
        btnBack.setOnClickListener(v -> onBackPressed());

        // Back to Login Link
        tvBackToLogin.setOnClickListener(v -> {
            startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void sendPasswordResetEmail(String email) {
        // TODO: Implement API call to send password reset email
        Toast.makeText(this, "Email xác nhận đã được gửi tới: " + email, Toast.LENGTH_SHORT).show();
        
        // Go to OTP verification screen
        Intent intent = new Intent(ForgotPasswordActivity.this, VerifyOtpActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }
}
