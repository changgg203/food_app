package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private ImageView btnBack, btnTogglePassword, btnToggleConfirmPassword;
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);
        btnTogglePassword = findViewById(R.id.btnTogglePassword);
        btnToggleConfirmPassword = findViewById(R.id.btnToggleConfirmPassword);

        // Back Button
        btnBack.setOnClickListener(v -> onBackPressed());

        // Toggle Password Visibility
        btnTogglePassword.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                etPassword.setTransformationMethod(null);
                btnTogglePassword.setImageResource(android.R.drawable.ic_menu_view);
            } else {
                etPassword.setTransformationMethod(new PasswordTransformationMethod());
                btnTogglePassword.setImageResource(android.R.drawable.ic_menu_view);
            }
            etPassword.setSelection(etPassword.getText().length());
        });

        // Toggle Confirm Password Visibility
        btnToggleConfirmPassword.setOnClickListener(v -> {
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            if (isConfirmPasswordVisible) {
                etConfirmPassword.setTransformationMethod(null);
                btnToggleConfirmPassword.setImageResource(android.R.drawable.ic_menu_view);
            } else {
                etConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
                btnToggleConfirmPassword.setImageResource(android.R.drawable.ic_menu_view);
            }
            etConfirmPassword.setSelection(etConfirmPassword.getText().length());
        });

        // Register Button
        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Call API to register
                registerUser(name, email, password);
            }
        });
    }

    private void registerUser(String name, String email, String password) {
        // TODO: Implement API call to register user
        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
        
        // Go back to login
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}
