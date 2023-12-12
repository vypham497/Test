package com.example.project_dbreathuit_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_dbreathuit_app.model.Asset;
import com.example.project_dbreathuit_app.model.UserResponseModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private API_SERVICE apiService;
    private TokenManager tokenManager;
    private Timer timer;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        handler = new Handler();

        RelativeLayout logoutLayout = findViewById(R.id.LayoutLogout);
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        tokenManager = new TokenManager(this);
        apiService  = API_CLIENT.getClient("https://uiot.ixxc.dev/",tokenManager.getAccessToken()).create(API_SERVICE.class);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), DashBoardMainActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_map) {
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_chart) {
                startActivity(new Intent(getApplicationContext(), ChartActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                return true;
            }

            return false;
        });

        scheduleApiCall();
    }
    private void logout() {
        // Clear tokens and navigate to login
        tokenManager.clearToken();
        Intent intent = new Intent(ProfileActivity.this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();

    }
    private void scheduleApiCall() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    getUser();
                });
            }
        }, 0, 5000); // 5000 milliseconds = 5 seconds
    }
    private void getUser(){
        Call<UserResponseModel> calllUser = apiService.getUser();
        calllUser.enqueue(new Callback<UserResponseModel>() {
            @Override
            public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                if(response.isSuccessful()){
                    UserResponseModel dataUser = response.body();
                    TextView username = findViewById(R.id.txtName);
                    TextView email = findViewById(R.id.txtEmail);

                    username.setText(String.valueOf(dataUser.getUsername()));
                    email.setText(String.valueOf(dataUser.getEmail()));

                }
                else{
                    Log.e("API Call", "Failed to retrieve data from the API");
                }
            }
            @Override
            public void onFailure(Call<UserResponseModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove callbacks to prevent memory leaks
        handler.removeCallbacksAndMessages(null);
    }
}