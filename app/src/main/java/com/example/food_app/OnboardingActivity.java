package com.example.food_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private Button btnNext;
    private TextView tvSkip;
    private OnboardingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if onboarding is already completed
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        if (prefs.getBoolean("onboarding_complete", false)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_onboarding);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btn_next);
        tvSkip = findViewById(R.id.tv_skip);

        // Define layouts for onboarding screens, excluding the main container
        final List<Integer> layouts = Arrays.asList(
                R.layout.onboarding_page_1,
                R.layout.onboarding_page_2,
                R.layout.onboarding_page_3
        );

        // Set up adapter for ViewPager2
        adapter = new OnboardingAdapter(this, layouts);
        viewPager.setAdapter(adapter);

        // Next button click listener
        btnNext.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < layouts.size() - 1) {
                viewPager.setCurrentItem(currentItem + 1);
            } else {
                markOnboardingComplete();
                goToIntro();
            }
        });

        // Skip button click listener
        tvSkip.setOnClickListener(v -> {
            markOnboardingComplete();
            goToIntro();
        });

        // Page change callback
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateIndicators(position);
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

    private void updateIndicators(int position) {
        View indicator1 = findViewById(R.id.indicator1);
        View indicator2 = findViewById(R.id.indicator2);
        View indicator3 = findViewById(R.id.indicator3);

        indicator1.setBackgroundResource(position == 0 ? R.drawable.dot_active : R.drawable.dot_inactive);
        indicator2.setBackgroundResource(position == 1 ? R.drawable.dot_active : R.drawable.dot_inactive);
        indicator3.setBackgroundResource(position == 2 ? R.drawable.dot_active : R.drawable.dot_inactive);
    }

    private void markOnboardingComplete() {
        getSharedPreferences("app_prefs", MODE_PRIVATE)
                .edit()
                .putBoolean("onboarding_complete", true)
                .apply();
    }

    private void goToIntro() {
        Intent intent = new Intent(this, IntroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
