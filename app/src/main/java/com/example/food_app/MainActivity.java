package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.food_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kiểm tra trạng thái onboarding
        if (!isOnboardingComplete()) {
            startActivity(new Intent(MainActivity.this, OnboardingActivity.class));
            finish();
            return;
        }

        // Kiểm tra trạng thái đăng nhập
        if (!isUserLoggedIn()) {
            startActivity(new Intent(MainActivity.this, IntroActivity.class));
            finish();
            return;
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_all_foods, R.id.navigation_favourite_food, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private boolean isOnboardingComplete() {
        return getSharedPreferences("app_prefs", MODE_PRIVATE).getBoolean("onboarding_complete", false);
    }

    private boolean isUserLoggedIn() {
        // TODO: Check SharedPreferences or local database for login status
        // For now, always return true
        return true;
    }
}