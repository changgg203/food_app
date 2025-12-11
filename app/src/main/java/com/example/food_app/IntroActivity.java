package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

            if (btnLogin != null) {
                btnLogin.setOnClickListener(v -> {
                    Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                    startActivity(intent);
                });
            }
            if (btnRegister != null) {
                btnRegister.setOnClickListener(v -> {
                    Intent intent = new Intent(IntroActivity.this, RegisterActivity.class);
                    startActivity(intent);
                });
            }
    }
}
