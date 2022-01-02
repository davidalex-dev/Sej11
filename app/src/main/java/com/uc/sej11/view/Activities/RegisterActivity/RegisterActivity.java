package com.uc.sej11.view.Activities.RegisterActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uc.sej11.R;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText register_name, register_email, register_username, register_pass,
            register_pass_confirm, register_year, register_city, register_school;
    Button btn_register;

    private RegisterViewModel registerViewModel;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InitView();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = register_name.getEditableText().toString().trim();
                String email = register_email.getEditableText().toString().trim();
                String username = register_username.getEditableText().toString().trim();
                String pass = register_pass.getEditableText().toString().trim();
                String cpass = register_pass_confirm.getEditableText().toString().trim();
                String year = register_year.getEditableText().toString().trim();
                String city = register_city.getEditableText().toString().trim();
                String school  = register_school.getEditableText().toString().trim();

                Log.d(TAG, "name is: " +name);
                Log.d(TAG, "username is: " +username);
                Log.d(TAG, "email is: " + email);
                Log.d(TAG, "password is: " + pass);
                Log.d(TAG, "password confirm: " + cpass);
                Log.d(TAG, "year: " + year);
                Log.d(TAG, "city: " + city);
                Log.d(TAG, "school: " + school);

                if(!register_name.getEditableText().toString().isEmpty()
                && !register_email.getEditableText().toString().isEmpty()
                && !register_username.getEditableText().toString().isEmpty()
                && !register_pass.getEditableText().toString().isEmpty()
                && !register_pass_confirm.getEditableText().toString().isEmpty()
                && !register_year.getEditableText().toString().isEmpty()
                && !register_city.getEditableText().toString().isEmpty()
                && !register_school.getEditableText().toString().isEmpty()){


                    register_name.setError(null);
                    register_email.setError(null);
                    register_username.setError(null);
                    register_pass.setError(null);
                    register_year.setError(null);
                    register_city.setError(null);
                    register_school.setError(null);


                    if(!pass.equals(cpass)){
                        register_pass_confirm.setError("Password not the same as password confirmation!");
                    }else{
                        register_pass_confirm.setError(null);
                        Toast.makeText(getApplicationContext(), "Bingo!", Toast.LENGTH_SHORT).
                                show();

                    registerViewModel.register(name, email, username, pass, cpass, school, city, year).observe(
                            RegisterActivity.this, registerResponse -> {

                                if(registerResponse!=null){
                                    Toast.makeText(getApplicationContext(), "User has been registered successfully.",
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Register failed.", Toast.LENGTH_SHORT).
                                            show();
                                }

                            });

                    }

                }else{

                    if(register_name.getEditableText().toString().isEmpty()){
                        register_name.setError("Name cannot be empty");
                    }

                    if(register_email.getEditableText().toString().isEmpty()){
                        register_email.setError("Email cannot be empty");
                    }

                    if(register_username.getEditableText().toString().isEmpty()){
                        register_username.setError("Username cannot be empty");
                    }

                    if(register_pass.getEditableText().toString().isEmpty()){
                        register_pass.setError("Password cannot be empty");
                    }

                    if(register_pass_confirm.getEditableText().toString().isEmpty()){
                        register_pass_confirm.setError("Password confirmation cannot be empty");
                    }

                    if(register_year.getEditableText().toString().isEmpty()){
                        register_year.setError("Birthyear cannot be empty");
                    }

                    if(register_city.getEditableText().toString().isEmpty()){
                        register_city.setError("City cannot be empty");
                    }

                    if(register_school.getEditableText().toString().isEmpty()){
                        register_school.setError("School cannot be empty");
                    }

                }

            }
        });
    }

    private void InitView() {
        register_name = findViewById(R.id.editText_register_name);
        register_email = findViewById(R.id.editText_register_email);
        register_username = findViewById(R.id.editText_register_username);
        register_pass = findViewById(R.id.editText_register_password);
        register_pass_confirm = findViewById(R.id.editText_register_password_confirm);
        register_year = findViewById(R.id.editText_register_birthyear);
        register_city = findViewById(R.id.editText_register_city);
        register_school = findViewById(R.id.editText_register_school);

        btn_register = findViewById(R.id.btn_register);

        registerViewModel = new ViewModelProvider(RegisterActivity.this).get(RegisterViewModel.class);
    }
}