package com.uc.sej11.view.Activities.ResultActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.uc.sej11.R;

public class ResultActivity extends AppCompatActivity {

    TextView score;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        InitView();
    }

    private void InitView() {
        score = findViewById(R.id.textView_result_score);
        back = findViewById(R.id.btn_result_menu_back);
    }
}