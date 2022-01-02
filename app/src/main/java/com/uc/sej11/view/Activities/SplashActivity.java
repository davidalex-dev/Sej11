package com.uc.sej11.view.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.uc.sej11.R;
import com.uc.sej11.helper.SharedPreferenceHelper;
import com.uc.sej11.view.Activities.LoginActivity.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferenceHelper helper = SharedPreferenceHelper.getInstance(SplashActivity.this);

        new Handler().postDelayed (new Runnable(){
            @Override
            public void run(){

                //this one is for testing purposes only
//                Intent i = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(i);

                if (helper.getAccessToken().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Not logged in.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Logged in!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}