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
import com.uc.sej11.model.Pilgan;
import com.uc.sej11.model.Soal;
import com.uc.sej11.model.Waktu;

import java.util.List;

public class PlayPilganActivity extends AppCompatActivity {

    private int soaltry = 0; //soal ke brp (mulai dri 0 hingga 9)
    private int timerId; //timer ID
    private int score; //skor
    private String materiId;
    private List<Soal.Sej11Soal> listSoal;
    private List<Pilgan.Sej11OpsiPilgan> listPilgan;
    private List<Waktu> listWaktu;
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

        //get question, answer, and timer
        playPilganViewModel.getResultSoal().observe(this, showData);
        //playPilganViewModel.getResultWaktu().observe(this, showWaktu);
        SoalInitialize();

        //kalau pilgan diteken
        pilgan_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check answer
                SoalTryIncrease();

                //score check
                Log.d(TAG, "soalBenarcheck: " + listPilgan.get(0).getStatus_benar());

                if(listPilgan.get(0).getStatus_benar() == 1){
                    Log.d(TAG, "soal benar");
                    score = score+10;
                    Log.d(TAG, "Current score: " + score);
                }else{ //temp. else to be removed
                    Log.d(TAG, "soal salah");
                }

                //change answer
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


    //waktu
//    private Observer<Waktu> showWaktu = new Observer<Waktu>() {
//        @Override
//        public void onChanged(Waktu waktu) {
//            Log.d(TAG, "Test: " + waktu.getdataWaktu().size());
//
//        }
//    };

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
        Log.d(TAG, "Hello from SoalChange");

        if(soaltry >= listSoal.size()){
            //soal selesai
            Log.d(TAG, "le end");
            Log.d(TAG, "Total score: " + score);

            //intent bundle ke result screen
            //bundle = materiId dan skor

            finish();

        }else{
            Log.d(TAG, "Soal has changed");
            txt_question.setText(listSoal.get(soaltry).getSoal());
            txt_outof.setText("Question " + (soaltry+1) + " out of " + listSoal.size());

            timerId = listSoal.get(soaltry).getSej11_waktu_id();

            playPilganViewModel.getSej11_opsi_pilgan(Integer.toString(soaltry+1));
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
        Log.d(TAG, "SoalTry after add: " + soaltry);
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

    //initview
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
        //playPilganViewModel.getResultWaktu();
    }
}