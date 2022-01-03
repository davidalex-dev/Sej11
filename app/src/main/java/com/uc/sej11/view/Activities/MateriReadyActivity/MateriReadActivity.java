package com.uc.sej11.view.Activities.MateriReadyActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.uc.sej11.R;
import com.uc.sej11.helper.SharedPreferenceHelper;
import com.uc.sej11.model.Materi;
import com.uc.sej11.view.Fragments.MateriFragment.MateriAdapter;
import com.uc.sej11.view.Fragments.MateriFragment.MateriViewModel;

import java.util.ArrayList;
import java.util.List;

public class MateriReadActivity extends AppCompatActivity {

    private String materiId;
    private String idMateri;
    private SharedPreferenceHelper helper;
    private MateriViewModel viewModel;

    private static final String TAG = "MateriReadAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_read);


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

        helper = SharedPreferenceHelper.getInstance(this);
        viewModel = new ViewModelProvider(this).get(MateriViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getDatabyID(materiId);
        viewModel.getResultData().observe(this, showData);

    }

    List<Materi.Data> results = new ArrayList<>();


    private Observer<Materi> showData = new Observer<Materi>() {

        @Override
        public void onChanged(Materi materi) {
            Log.d(TAG, "Test: " + materi.getData().get(Integer.parseInt(materiId)-1).getJudul_sub_bab());

        }
    };

}