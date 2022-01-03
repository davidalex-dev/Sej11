package com.uc.sej11.view.Activities.PlayUraianActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.uc.sej11.R;

public class PlayUraianActivity extends AppCompatActivity {

    TextView txt_timer, txt_question;
    EditText uraian;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_uraian);
        InitView();

        //get timer
        //get question



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(uraian.getEditableText().toString().isEmpty()){
                    uraian.setError("You have not filled out the answer!");
                }else{
                    uraian.setError(null);
                    //check answer
                }

            }
        });
    }

    private void InitView() {
        txt_timer = findViewById(R.id.textView_play_uraian_timer);
        txt_question = findViewById(R.id.textView_play_uraian_question);

        uraian = findViewById(R.id.textinputEditText_answer);

        btn_submit = findViewById(R.id.btn_submit);
    }
}