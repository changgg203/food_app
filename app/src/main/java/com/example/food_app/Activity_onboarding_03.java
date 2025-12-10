package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_onboarding_03 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_03);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Button btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_onboarding_03.this, IntroActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.tv_skip).setOnClickListener(v -> {
            Intent intent = new Intent(Activity_onboarding_03.this, IntroActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
