package com.uc.sej11.view.Activities.BeforeQuizActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uc.sej11.R;
import com.uc.sej11.helper.SharedPreferenceHelper;
import com.uc.sej11.model.Materi;
import com.uc.sej11.view.Activities.PlayPilganActivity.PlayPilganActivity;
import com.uc.sej11.view.Fragments.MateriFragment.MateriViewModel;

public class BeforeQuizActivity extends AppCompatActivity {

    private TextView material_name, material_level;
    private Button btn_start, btn_back;
    private String materiId;
    private MateriViewModel viewModel;
    private SharedPreferenceHelper helper;

    private static final String TAG = "BeforeQuizActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_quiz);

        //bundle materiId
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



        //show materi name and level

        //if start is pressed
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show are you ready?

                //if user clicks on ok
                //go to playpilgan
                //intent bundle materiId

                Intent i = new Intent(BeforeQuizActivity.this, PlayPilganActivity.class);
                i.putExtra("materi_id", materiId);
                startActivity(i);
                finish();

            }
        });

        //if back is pressed
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go back to menu;
                finish();
            }
        });

    }

    private void InitView() {
        material_name = findViewById(R.id.textView_before_quiz_material_name);
        material_level = findViewById(R.id.textView_before_quiz_material_level);
        btn_start = findViewById(R.id.button_before_quiz_start);
        btn_back = findViewById(R.id.button_before_quiz_back);

        helper = SharedPreferenceHelper.getInstance(this);
        viewModel = new ViewModelProvider(this).get(MateriViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getDatabyID(materiId);
        viewModel.getResultData().observe(this, showData);
    }

    private Observer<Materi> showData = new Observer<Materi>() {
        @Override
        public void onChanged(Materi materi) {
            String title = materi.getData().get(Integer.parseInt(materiId)-1).getJudul_sub_bab();
            int level = materi.getData().get(Integer.parseInt(materiId)-1).getId();

            Log.d(TAG, "title: " + title);
            Log.d(TAG, "level: " + level);

            material_name.setText(title);
            material_level.setText("Level " + level);
        }
    };

}