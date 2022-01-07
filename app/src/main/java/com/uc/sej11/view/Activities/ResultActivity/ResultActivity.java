package com.uc.sej11.view.Activities.ResultActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uc.sej11.R;

public class ResultActivity extends AppCompatActivity {

    TextView desc, score;
    Button retry, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        InitView();

        //bundle for retry

        //if score >= 75
        //save to scoreboard database
        //high score!!

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //end the activity and go back to menu.
                finish();
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to beforequizactivity
                //carry the bundle
            }
        });

    }

    private void InitView() {
        desc = findViewById(R.id.textView_result_text);
        score = findViewById(R.id.textView_result_score);

        retry = findViewById(R.id.btn_result_retry);
        back = findViewById(R.id.btn_result_menu_back);
    }
}