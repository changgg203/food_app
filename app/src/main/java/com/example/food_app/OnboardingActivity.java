package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private Button btnNext;
    private TextView tvSkip;
    private OnboardingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btn_next);
        tvSkip = findViewById(R.id.tv_skip);

        final java.util.List<Integer> layouts = java.util.Arrays.asList(
                R.layout.activity_onboarding,
                R.layout.activity_onboarding_02,
                R.layout.activity_onboarding_03
        );

            btnNext.setOnClickListener(v -> {
                Intent intent = new Intent(OnboardingActivity.this, Activity_onboarding_02.class);
                startActivity(intent);
                finish();
            });

            tvSkip.setOnClickListener(v -> {
                Intent intent = new Intent(OnboardingActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == layouts.size() - 1) {
                    btnNext.setText("BẮT ĐẦU");
                    tvSkip.setVisibility(View.GONE);
                } else {
                    btnNext.setText("TIẾP THEO");
                    tvSkip.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void goToIntro() {
        Intent intent = new Intent(this, IntroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
