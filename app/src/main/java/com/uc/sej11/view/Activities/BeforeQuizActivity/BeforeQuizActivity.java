package com.uc.sej11.view.Activities.BeforeQuizActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uc.sej11.R;

public class BeforeQuizActivity extends AppCompatActivity {

    private TextView material_name, material_level;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_quiz);
        InitView();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show are you ready?

                //if user clicks on ok
                //go to playpilgan

            }
        });

    }

    private void InitView() {
        material_name = findViewById(R.id.textView_before_quiz_material_name);
        material_level = findViewById(R.id.textView_before_quiz_material_level);
        btn_start = findViewById(R.id.button_before_quiz_start);
    }
}