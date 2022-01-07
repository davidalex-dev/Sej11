package com.uc.sej11.view.Activities.ResultActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uc.sej11.R;
import com.uc.sej11.view.Activities.PlayPilganActivity.PlayPilganActivity;

import javax.xml.transform.Result;

public class ResultActivity extends AppCompatActivity {

    private TextView desc, score;
    private Button retry, back;
    private String materiId;
    private int total_score;

    private static final String TAG = "ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        InitView();

        Log.d(TAG, "Hello from ResultActivity");

        //bundle for retry
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            materiId = extras.getString("materi_id");
            total_score = extras.getInt("score");
        }

        Log.d(TAG, "Materi ID is: " + materiId);
        Log.d(TAG, "Total score: " + total_score);

        //set score
        score.setText("Your score is " + total_score);

        if(total_score <= 80){
            Log.d(TAG, "You can do better!");
            desc.setText("Belajar lagi goblok");
        }else{
            Log.d(TAG, "You're great");
            desc.setText("Hebat.");
        }

        //if score >= 75
        //save to scoreboard database
        //high score!!

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to beforequizactivity
                //carry the bundle

                Intent i = new Intent(ResultActivity.this, PlayPilganActivity.class);
                i.putExtra("materi_id", materiId);
                startActivity(i);
                finish();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //end the activity and go back to menu.
                finish();
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