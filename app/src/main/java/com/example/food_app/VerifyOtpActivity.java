package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VerifyOtpActivity extends AppCompatActivity {

    private EditText etOtp1, etOtp2, etOtp3, etOtp4;
    private Button btnVerify;
    private ImageButton btnBack;
    private TextView tvResend;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        etOtp1 = findViewById(R.id.otp1);
        etOtp2 = findViewById(R.id.otp2);
        etOtp3 = findViewById(R.id.otp3);
        etOtp4 = findViewById(R.id.otp4);
        btnVerify = findViewById(R.id.btn_verify);
        btnBack = findViewById(R.id.btnBack);
        tvResend = findViewById(R.id.tv_resend);

        // Setup OTP input fields with auto-focus
        setupOtpInputs();

        // Start timer
        startTimer();

        // Verify Button
        btnVerify.setOnClickListener(v -> {
            String otp = etOtp1.getText().toString() +
                    etOtp2.getText().toString() +
                    etOtp3.getText().toString() +
                    etOtp4.getText().toString();

            if (otp.length() < 4) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ mã xác minh", Toast.LENGTH_SHORT).show();
            } else {
                verifyOtp(otp);
            }
        });

        // Resend OTP
        tvResend.setOnClickListener(v -> {
            if (!isTimerRunning) {
                resendOtp();
                startTimer();
            }
        });

        // Back Button
        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void setupOtpInputs() {
        etOtp1.addTextChangedListener(new OtpTextWatcher(etOtp1, etOtp2));
        etOtp2.addTextChangedListener(new OtpTextWatcher(etOtp2, etOtp3));
        etOtp3.addTextChangedListener(new OtpTextWatcher(etOtp3, etOtp4));
        etOtp4.addTextChangedListener(new OtpTextWatcher(etOtp4, null));
    }

    private void startTimer() {
        isTimerRunning = true;
        tvResend.setEnabled(false);
        tvResend.setAlpha(0.5f);

        countDownTimer = new CountDownTimer(50000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvResend.setText("Resend in " + (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                tvResend.setEnabled(true);
                tvResend.setAlpha(1f);
                tvResend.setText("Resend");
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
