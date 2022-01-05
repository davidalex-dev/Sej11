package com.uc.sej11.view.Activities.PlayPilganActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uc.sej11.R;
import com.uc.sej11.helper.SharedPreferenceHelper;
import com.uc.sej11.model.Materi;
import com.uc.sej11.model.Soal;
import com.uc.sej11.view.Fragments.MateriFragment.MateriViewModel;

import java.util.List;

public class PlayPilganActivity extends AppCompatActivity {

    private int soaltry = 0;
    private String materiId;
    private List<Soal.Sej11Soal> listSoal;
    TextView txt_timer, txt_question, txt_outof;
    Button pilgan_a, pilgan_b, pilgan_c, pilgan_d, pilgan_e;

    private PlayPilganViewModel playPilganViewModel;
    private SharedPreferenceHelper helper;

    private static final String TAG = "PlayPilganActivity";
    private static final int SOAL_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_pilgan);

        //get bundle
        if(savedInstanceState == null){
            Bundle id = getIntent().getExtras();
            if(id == null){
                materiId = null;
            }else{
                materiId = id.getString("materi_id");
            }
        }else{
            materiId = (String) savedInstanceState.getSerializable("materi_id");
        }

        Log.d(TAG, "Intent bundle is: " + materiId);

        //init the view
        InitView();

        playPilganViewModel.getResultSoal().observe(this, showData);


        //get timer
        //get question
        //get answer

        SoalInitialize();

        pilgan_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
                soaltry++;
                Log.d(TAG, "SoalTry after add: " + soaltry);
                SoalChange();

            }
        });

        pilgan_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
            }
        });

        pilgan_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
            }
        });

        pilgan_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
            }
        });

        pilgan_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
            }
        });

    }

    private Observer<List<Soal.Sej11Soal>> showData = new Observer<List<Soal.Sej11Soal>>() {

        @Override
        public void onChanged(List<Soal.Sej11Soal> sej11Soals) {

            Log.d(TAG, "SoalTry: " + soaltry);

            listSoal = sej11Soals;

//            int soalid = sej11Soals.get(Integer.parseInt(materiId)+soaltry).getId();
//            String title = sej11Soals.get(Integer.parseInt(materiId)+soaltry).getSoal();


//            soaltry++;
//            Log.d(TAG, "SoalTry after add: " + soaltry);
//            Log.d(TAG, "Test title: " + title);
//
//            txt_question.setText(title);
        }
    };

    private void SoalInitialize(){
        Log.d(TAG, "ARE YOU READY?");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SoalChange();
            }
        }, SOAL_TIME_OUT);
    }

    private void SoalChange(){
        Log.d(TAG, "Hello from SoalChange");

        if(soaltry >= listSoal.size()){
            Log.d(TAG, "le end");
            finish();
        }else{
            Log.d(TAG, "Soal has changed");
            txt_question.setText(listSoal.get(soaltry).getSoal());
            txt_outof.setText("Question " + (soaltry+1) + " out of " + listSoal.size());
        }


    }

    private void InitView() {
        txt_timer = findViewById(R.id.textView_play_pilgan_timer);
        txt_question = findViewById(R.id.textView_play_pilgan_question);
        txt_outof = findViewById(R.id.textView_play_pilgan_outof);

        pilgan_a = findViewById(R.id.btn_play_pilgan_a);
        pilgan_b = findViewById(R.id.btn_play_pilgan_b);
        pilgan_c = findViewById(R.id.btn_play_pilgan_c);
        pilgan_d = findViewById(R.id.btn_play_pilgan_d);
        pilgan_e = findViewById(R.id.btn_play_pilgan_e);

        helper = SharedPreferenceHelper.getInstance(this);

        playPilganViewModel = new ViewModelProvider(PlayPilganActivity.this).get(PlayPilganViewModel.class);
        playPilganViewModel.init(helper.getAccessToken());
        playPilganViewModel.getSej11_soal(materiId);
    }
}