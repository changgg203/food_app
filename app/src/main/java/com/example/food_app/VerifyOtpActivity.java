package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VerifyOtpActivity extends AppCompatActivity {

    private EditText etOtp1, etOtp2, etOtp3, etOtp4, etOtp5;
    private Button btnVerify;
    private ImageView btnBack;
    private TextView tvResendOtp, tvTimer, tvChangeEmail, tvEmailDisplay;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        // Initialize views
        etOtp1 = findViewById(R.id.etOtp1);
        etOtp2 = findViewById(R.id.etOtp2);
        etOtp3 = findViewById(R.id.etOtp3);
        etOtp4 = findViewById(R.id.etOtp4);
        etOtp5 = findViewById(R.id.etOtp5);
        btnVerify = findViewById(R.id.btnVerify);
        btnBack = findViewById(R.id.btnBack);
        tvResendOtp = findViewById(R.id.tvResendOtp);
        tvTimer = findViewById(R.id.tvTimer);
        tvChangeEmail = findViewById(R.id.tvChangeEmail);
        tvEmailDisplay = findViewById(R.id.tvEmailDisplay);

        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Get email from intent
        String email = getIntent().getStringExtra("email");
        if (email != null) {
            tvEmailDisplay.setText(email);
        }

        // Setup OTP input fields with auto-focus
        setupOtpInputs();

        // Start timer
        startTimer();

        // Verify Button
        btnVerify.setOnClickListener(v -> {
            String otp = etOtp1.getText().toString() +
                    etOtp2.getText().toString() +
                    etOtp3.getText().toString() +
                    etOtp4.getText().toString() +
                    etOtp5.getText().toString();

            if (otp.length() < 5) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ mã xác minh", Toast.LENGTH_SHORT).show();
            } else {
                verifyOtp(otp);
            }
        });

        // Resend OTP
        tvResendOtp.setOnClickListener(v -> {
            if (!isTimerRunning) {
                resendOtp();
                startTimer();
            }
        });

        // Back Button
        btnBack.setOnClickListener(v -> onBackPressed());

        // Change Email
        tvChangeEmail.setOnClickListener(v -> {
            startActivity(new Intent(VerifyOtpActivity.this, ForgotPasswordActivity.class));
            finish();
        });
    }

    private void setupOtpInputs() {
        etOtp1.addTextChangedListener(new OtpTextWatcher(etOtp1, etOtp2));
        etOtp2.addTextChangedListener(new OtpTextWatcher(etOtp2, etOtp3));
        etOtp3.addTextChangedListener(new OtpTextWatcher(etOtp3, etOtp4));
        etOtp4.addTextChangedListener(new OtpTextWatcher(etOtp4, etOtp5));
        etOtp5.addTextChangedListener(new OtpTextWatcher(etOtp5, null));
    }

    private void startTimer() {
        isTimerRunning = true;
        tvResendOtp.setEnabled(false);
        tvResendOtp.setAlpha(0.5f);

        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(" (" + (millisUntilFinished / 1000) + "s)");
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                tvResendOtp.setEnabled(true);
                tvResendOtp.setAlpha(1f);
                tvTimer.setText(" (Hết hạn)");
            }
        }.start();
    }

    private void resendOtp() {
        // TODO: Call API to resend OTP
        Toast.makeText(this, "Mã xác minh mới đã được gửi", Toast.LENGTH_SHORT).show();
        
        // Clear OTP fields
        etOtp1.setText("");
        etOtp2.setText("");
        etOtp3.setText("");
        etOtp4.setText("");
        etOtp5.setText("");
        etOtp1.requestFocus();
    }

    private void verifyOtp(String otp) {
        // TODO: Call API to verify OTP
        Toast.makeText(this, "Xác minh OTP: " + otp, Toast.LENGTH_SHORT).show();
        
        // If verification successful, go to reset password screen
        Intent intent = new Intent(VerifyOtpActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
        finish();
    }

    // Text Watcher for OTP input
    private class OtpTextWatcher implements TextWatcher {
        private EditText currentEditText;
        private EditText nextEditText;

        OtpTextWatcher(EditText currentEditText, EditText nextEditText) {
            this.currentEditText = currentEditText;
            this.nextEditText = nextEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 1 && nextEditText != null) {
                nextEditText.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
