package com.example.project_dbreathuit_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//
public class OnBoardingActivity extends AppCompatActivity {
    private Button loginButton, signupButton;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        loginButton = findViewById(R.id.ButtonLogin);
        signupButton = findViewById(R.id.ButtonSignup);
        tokenManager = new TokenManager(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, LogInActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        if (tokenManager.getAccessToken() != null && !tokenManager.isTokenExpired()) {
            // User is already logged in and the token is not expired
            // Proceed to the main activity
            //navigateDashBoard();
            navigateMap();
            String accessToken = tokenManager.getAccessToken();
        } else {
            tokenManager.clearToken();
        }
    }
    private void navigateMap() {
        Intent intent = new Intent(this, MapActivity.class);
        // Clear the existing task and start a new task
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();  // Finish the current activity
    }
}

