package com.uc.sej11.view.Activities.PlayPilganActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uc.sej11.R;
import com.uc.sej11.helper.SharedPreferenceHelper;
import com.uc.sej11.model.Pilgan;
import com.uc.sej11.model.Soal;
import com.uc.sej11.view.Activities.MateriReadyActivity.MateriReadActivity;
import com.uc.sej11.view.Activities.ResultActivity.ResultActivity;
import com.uc.sej11.view.LoadingDialog;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PlayPilganActivity extends AppCompatActivity {

    private int soaltry = 0; //soal ke brp (mulai dri 0 hingga 9)
    private int pilganNo;
    private int timerId; //timer ID
    private int score = 0; //skor
    private int soalWrong = 0; //skor yang salah
    private int soalCorrect = 0; //skor yang benar
    private String materiId;
    private List<Soal.Sej11Soal> listSoal;
    private List<Pilgan.Sej11OpsiPilgan> listPilgan;
    //private List<Waktu> listWaktu;
    TextView txt_timer, txt_question, txt_outof, txt_score;
    Button pilgan_a, pilgan_b, pilgan_c, pilgan_d, pilgan_e;

    private PlayPilganViewModel playPilganViewModel;
    private SharedPreferenceHelper helper;

    private static final long COUNTDOWN = 16000;
    private CountDownTimer countdownTimer;
    private long timeLeftInMillis;

    private static final String TAG = "PlayPilganActivity";
    private static final int SOAL_TIME_OUT = 1500;


    LoadingDialog loadingDialog = new LoadingDialog(PlayPilganActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_pilgan);

        //get bundle
//        if(savedInstanceState == null){
//            Bundle id = getIntent().getExtras();
//            if(id == null){
//                materiId = null;
//            }else{
//                materiId = id.getString("materi_id");
//            }
//        }else{
//            materiId = (String) savedInstanceState.getSerializable("materi_id");
//        }

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            materiId = extras.getString("materi_id");
        }

        Log.d(TAG, "Intent bundle is: " + materiId);

        //init the view
        InitView();
        PilganNoInitialize();

        //get question, answer, and timer
        playPilganViewModel.getResultSoal().observe(this, showData);
        //playPilganViewModel.getResultWaktu().observe(this, showWaktu);
        //playPilganViewModel.getResultTimer().observe(this, showTimer);
        SoalInitialize();

        //kalau pilgan diteken
        pilgan_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
                SoalTryIncrease();

                //score check
                ScoreCheck(listPilgan.get(0).getStatus_benar());

                //change answer
                SoalChange();

            }
        });

        pilgan_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
                SoalTryIncrease();

                //score check
                ScoreCheck(listPilgan.get(1).getStatus_benar());

                //change answer
                SoalChange();
            }
        });

        pilgan_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
                SoalTryIncrease();

                //score check
                ScoreCheck(listPilgan.get(2).getStatus_benar());

                //change answer
                SoalChange();
            }
        });

        pilgan_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
                SoalTryIncrease();

                //score check
                ScoreCheck(listPilgan.get(3).getStatus_benar());

                //change answer
                SoalChange();
            }
        });

        pilgan_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
                SoalTryIncrease();

                //score check
                ScoreCheck(listPilgan.get(4).getStatus_benar());

                //change answer
                SoalChange();
            }
        });

    }

    //waktu
//    private Observer<Waktu> showWaktu = new Observer<Waktu>() {
//        @Override
//        public void onChanged(Waktu waktu) {
//            Log.d(TAG, "Test: " + waktu.getdataWaktu().size());
//
//        }
//    };

//    private Observer<Waktu> showTimer = new Observer<Waktu>() {
//        @Override
//        public void onChanged(Waktu waktu) {
//            Log.d(TAG, "Test: " + waktu.getTimer().size());
//        }
//    };

    //initialize pilganNo
    private void PilganNoInitialize() {
        if(materiId.equals("1")){
            pilganNo = 1;
        }else if(materiId.equals("2")){
            pilganNo = 11;
        }else if(materiId.equals("3")){
            pilganNo = 21;
        }else if(materiId.equals("4")){
            pilganNo = 31;
        }else if(materiId.equals("5")){
            pilganNo = 41;
        }else if(materiId.equals("6")){
            pilganNo = 51;
        }

        Log.d(TAG, "pilganNo is: " + pilganNo);

    }

    //soal
    private void SoalInitialize(){
        Log.d(TAG, "Soal is initializing...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SoalChange();
            }
        }, SOAL_TIME_OUT);
    }

    private void SoalChange(){
        Log.d(TAG, "SoalChecking, soalCorrect: " + soalCorrect);
        Log.d(TAG, "SoalChecking, soalWrong: " + soalWrong);

        if(soaltry >= listSoal.size()){
            //soal selesai
            Log.d(TAG, "le end");
            Log.d(TAG, "Total score: " + score);

            //intent bundle ke result screen
            //bundle = materiId dan skor

            if(countdownTimer!=null){
                countdownTimer.cancel();
            }

            Intent i = new Intent(PlayPilganActivity.this, ResultActivity.class);
            i.putExtra("materi_id", materiId);
            i.putExtra("score", score);
            i.putExtra("soalCorrect", soalCorrect);
            i.putExtra("soalWrong", soalWrong);
            loadingDialog.dismissDialog();
            startActivity(i);
            finish();

        }else{
            Log.d(TAG, "Soal has changed");

            txt_score.setText("Score: " + score);
            txt_question.setText(listSoal.get(soaltry).getSoal());
            txt_outof.setText("Question " + (soaltry+1) + " out of " + listSoal.size());

            timerId = listSoal.get(soaltry).getSej11_waktu_id();

            playPilganViewModel.getSej11_opsi_pilgan(Integer.toString(pilganNo));
            playPilganViewModel.getResultPilgan().observe(PlayPilganActivity.this, showPilgan);
            PilganInitialize();

        }


    }

    private Observer<List<Soal.Sej11Soal>> showData = new Observer<List<Soal.Sej11Soal>>() {

        @Override
        public void onChanged(List<Soal.Sej11Soal> sej11Soals) {
            Log.d(TAG, "SoalTry: " + soaltry);
            listSoal = sej11Soals;

        }
    };

    private void SoalTryIncrease() {
        soaltry++;
        pilganNo++;
        Log.d(TAG, "SoalTry after add: " + soaltry);
        countdownTimer.cancel();
        loadingDialog.startLoadingDialog();
    }

    //pilgan
    private void PilganInitialize() {
        Log.d(TAG, "PILGAN IS INIT. ARE YOU READY?");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pilgan_a.setText(listPilgan.get(0).getOpsi_pg());
                pilgan_b.setText(listPilgan.get(1).getOpsi_pg());
                pilgan_c.setText(listPilgan.get(2).getOpsi_pg());
                pilgan_d.setText(listPilgan.get(3).getOpsi_pg());
                pilgan_e.setText(listPilgan.get(4).getOpsi_pg());

                txt_question.setVisibility(View.VISIBLE);
                pilgan_a.setVisibility(View.VISIBLE);
                pilgan_b.setVisibility(View.VISIBLE);
                pilgan_c.setVisibility(View.VISIBLE);
                pilgan_d.setVisibility(View.VISIBLE);
                pilgan_e.setVisibility(View.VISIBLE);
                txt_timer.setVisibility(View.VISIBLE);
                txt_outof.setVisibility(View.VISIBLE);
                txt_score.setVisibility(View.VISIBLE);

                loadingDialog.dismissDialog();

                //start countdown

                timeLeftInMillis = COUNTDOWN;
                startCountdown();
            }
        }, SOAL_TIME_OUT);
    }

    private Observer<List<Pilgan.Sej11OpsiPilgan>> showPilgan = new Observer<List<Pilgan.Sej11OpsiPilgan>>() {
        @Override
        public void onChanged(List<Pilgan.Sej11OpsiPilgan> sej11OpsiPilgans) {
            listPilgan = sej11OpsiPilgans;

            //Log.d(TAG, "PilganA: " + listPilgan.get(1).getOpsi_pg());
        }
    };

    //countdown
    private void startCountdown(){
        countdownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis = l;
                String timeFormatted = String.format(Locale.ENGLISH, "%02d", TimeUnit.MILLISECONDS.toSeconds(l));

                txt_timer.setText(timeFormatted);
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                //updateCountDownText();
                Log.d(TAG, "Time has ran out");
                soalWrong++;
                SoalTryIncrease();
                SoalChange();
            }
        }.start();
    }


    //initview
    private void InitView() {
        loadingDialog.startLoadingDialog();

        txt_timer = findViewById(R.id.textView_play_pilgan_timer);
        txt_question = findViewById(R.id.textView_play_pilgan_question);
        txt_outof = findViewById(R.id.textView_play_pilgan_outof);
        txt_score = findViewById(R.id.textView_play_pilgan_score);

        pilgan_a = findViewById(R.id.btn_play_pilgan_a);
        pilgan_b = findViewById(R.id.btn_play_pilgan_b);
        pilgan_c = findViewById(R.id.btn_play_pilgan_c);
        pilgan_d = findViewById(R.id.btn_play_pilgan_d);
        pilgan_e = findViewById(R.id.btn_play_pilgan_e);

        helper = SharedPreferenceHelper.getInstance(this);

        playPilganViewModel = new ViewModelProvider(PlayPilganActivity.this).get(PlayPilganViewModel.class);
        playPilganViewModel.init(helper.getAccessToken());
        playPilganViewModel.getSej11_soal(materiId);
        //playPilganViewModel.getResultTimer();
    }

    @Override
    public void onBackPressed() {
        //back button is disabled.
    }

    private void ScoreCheck(int a){
        Log.d(TAG, "ScoreCheck...");
        if(a == 1){
            Log.d(TAG, "soal benar");
            score = score+10;
            soalCorrect++;
            Log.d(TAG, "Current score: " + score);
        }else{ //temp. else to be removed
            Log.d(TAG, "soal salah");
            soalWrong++;
        }

    }

}