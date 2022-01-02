package com.uc.sej11.view.Activities.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.sej11.R;
import com.uc.sej11.helper.SharedPreferenceHelper;
import com.uc.sej11.view.Activities.MainActivity;
import com.uc.sej11.view.Activities.RegisterActivity.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout email_login, password_login;
    Button btn_login;
    TextView btn_reg;

    private static final String TAG = "LoginActivity";

    private LoginViewModel loginViewModel;
    private SharedPreferenceHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitView();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //

                String email = email_login.getEditText().getText().toString().trim();
                String pass = password_login.getEditText().getText().toString().trim();

                Log.d(TAG, "email is: " + email);
                Log.d(TAG, "pass is: " + pass);

                if(!email_login.getEditText().getText().toString().isEmpty()
                && !password_login.getEditText().getText().toString().isEmpty()){

                    email_login.setError(null);
                    password_login.setError(null);

                                    loginViewModel.login(email, pass).observe(LoginActivity.this, tokenResponse -> {
                                        if(tokenResponse !=null){
                                            helper.saveAccessToken(tokenResponse.getAuthorization());
                                            Toast.makeText(getApplicationContext(), "Login success.", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(i);
                                            finish();

                                        }else{
                                            Toast.makeText(getApplicationContext(), "Login failed.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                    
                }else{

                    if(email_login.getEditText().getText().toString().isEmpty()){
                        email_login.setError("Email cannot be empty");
                    }else{
                        email_login.setError(null);
                    }

                    if(password_login.getEditText().getText().toString().isEmpty()){
                        password_login.setError("Password cannot be empty");
                    }else{
                        password_login.setError(null);
                    }

                }



            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void InitView() {
        email_login = findViewById(R.id.textInputLayout_login_email);
        password_login = findViewById(R.id.textInputLayout_login_password);
        btn_login = findViewById(R.id.button_login);
        btn_reg = findViewById(R.id.textView_login_registerNow);

        loginViewModel = new ViewModelProvider(LoginActivity.this).get(LoginViewModel.class);
    }
}