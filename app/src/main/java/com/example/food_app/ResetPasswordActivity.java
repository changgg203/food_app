package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText etNewPassword, etConfirmPassword;
    private Button btnResetPassword;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        btnBack = findViewById(R.id.btnBack);

        // Reset Password Button
        btnResetPassword.setOnClickListener(v -> {
            String newPassword = etNewPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền mật khẩu", Toast.LENGTH_SHORT).show();
            } else if (newPassword.length() < 6) {
                Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
            } else if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Call API to reset password
                resetPassword(newPassword);
            }
        });

        // Back Button
        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void resetPassword(String newPassword) {
        // TODO: Implement API call to reset password
        Toast.makeText(this, "Mật khẩu đã được cập nhật", Toast.LENGTH_SHORT).show();
        
        // Go back to login
        startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
        finish();
    }
}
