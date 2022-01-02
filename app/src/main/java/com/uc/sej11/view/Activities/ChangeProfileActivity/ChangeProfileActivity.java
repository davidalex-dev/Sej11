package com.uc.sej11.view.Activities.ChangeProfileActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.uc.sej11.R;

public class ChangeProfileActivity extends AppCompatActivity {

    TextInputEditText changeProfile_name, changeProfile_email, changeProfile_username,
            changeProfile_pass, changeProfile_pass_confirm, changeProfile_year, changeProfile_city,
            changeProfile_school;
    Button btn_changeProfile;

    private static final String TAG = "ChangeProfileActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);
        InitView();

        btn_changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = changeProfile_name.getEditableText().toString().trim();
                String email = changeProfile_email.getEditableText().toString().trim();
                String username = changeProfile_username.getEditableText().toString().trim();
                String pass = changeProfile_pass.getEditableText().toString().trim();
                String cpass = changeProfile_pass_confirm.getEditableText().toString().trim();
                String year = changeProfile_year.getEditableText().toString().trim();
                String city = changeProfile_city.getEditableText().toString().trim();
                String school  = changeProfile_school.getEditableText().toString().trim();

                Log.d(TAG, "name is: " +name);
                Log.d(TAG, "username is: " +username);
                Log.d(TAG, "email is: " + email);
                Log.d(TAG, "password is: " + pass);
                Log.d(TAG, "password confirm: " + cpass);
                Log.d(TAG, "year: " + year);
                Log.d(TAG, "city: " + city);
                Log.d(TAG, "school: " + school);

                if(!changeProfile_name.getEditableText().toString().isEmpty()
                        && !changeProfile_email.getEditableText().toString().isEmpty()
                        && !changeProfile_username.getEditableText().toString().isEmpty()
                        && !changeProfile_pass.getEditableText().toString().isEmpty()
                        && !changeProfile_pass_confirm.getEditableText().toString().isEmpty()
                        && !changeProfile_year.getEditableText().toString().isEmpty()
                        && !changeProfile_city.getEditableText().toString().isEmpty()
                        && !changeProfile_school.getEditableText().toString().isEmpty()){


                    changeProfile_name.setError(null);
                    changeProfile_email.setError(null);
                    changeProfile_username.setError(null);
                    changeProfile_pass.setError(null);
                    changeProfile_year.setError(null);
                    changeProfile_city.setError(null);
                    changeProfile_school.setError(null);


                    if(!pass.equals(cpass)){
                        changeProfile_pass_confirm.setError("Password not the same as password confirmation!");
                    }else{
                        changeProfile_pass_confirm.setError(null);

                        //changeprofileaction

                    }

                }else{

                    if(changeProfile_name.getEditableText().toString().isEmpty()){
                        changeProfile_name.setError("Name cannot be empty");
                    }

                    if(changeProfile_email.getEditableText().toString().isEmpty()){
                        changeProfile_email.setError("Email cannot be empty");
                    }

                    if(changeProfile_username.getEditableText().toString().isEmpty()){
                        changeProfile_username.setError("Username cannot be empty");
                    }

                    if(changeProfile_pass.getEditableText().toString().isEmpty()){
                        changeProfile_pass.setError("Password cannot be empty");
                    }

                    if(changeProfile_pass_confirm.getEditableText().toString().isEmpty()){
                        changeProfile_pass_confirm.setError("Password confirmation cannot be empty");
                    }

                    if(changeProfile_year.getEditableText().toString().isEmpty()){
                        changeProfile_year.setError("Birthyear cannot be empty");
                    }

                    if(changeProfile_city.getEditableText().toString().isEmpty()){
                        changeProfile_city.setError("City cannot be empty");
                    }

                    if(changeProfile_school.getEditableText().toString().isEmpty()){
                        changeProfile_school.setError("School cannot be empty");
                    }

                }

            }
        });

    }

    private void InitView() {
        changeProfile_name = findViewById(R.id.editText_changeProfile_name);
        changeProfile_email = findViewById(R.id.editText_changeProfile_email);
        changeProfile_username = findViewById(R.id.editText_changeProfile_username);
        changeProfile_pass = findViewById(R.id.editText_changeProfile_password);
        changeProfile_pass_confirm = findViewById(R.id.editText_changeProfile_password_confirm);
        changeProfile_year = findViewById(R.id.editText_changeProfile_birthyear);
        changeProfile_city = findViewById(R.id.editText_changeProfile_city);
        changeProfile_school = findViewById(R.id.editText_changeProfile_school);

        btn_changeProfile = findViewById(R.id.btn_changeProfile);

    }
}