package com.uc.sej11.view.Activities.MateriReadyActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.sej11.R;
import com.uc.sej11.helper.Const;
import com.uc.sej11.helper.SharedPreferenceHelper;
import com.uc.sej11.model.Materi;
import com.uc.sej11.view.Fragments.MateriFragment.MateriAdapter;
import com.uc.sej11.view.Fragments.MateriFragment.MateriViewModel;

import java.util.ArrayList;
import java.util.List;

public class MateriReadActivity extends AppCompatActivity {

    private String materiId;
    private String idMateri;

    private TextView txt_title, txt_description;
    private ImageView img_materi;
    private SharedPreferenceHelper helper;
    private MateriViewModel viewModel;

    private static final String TAG = "MateriReadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_read);

        //initialize bundle
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

        //init view
        InitView();

    }

    private void InitView() {
        txt_title = findViewById(R.id.textView_materi_read_title);
        txt_description = findViewById(R.id.textView_materi_read_description);
        img_materi = findViewById(R.id.imageView_materi_read_picture);

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
            String description = materi.getData().get(Integer.parseInt(materiId)-1).getMateri();
            String img_path = materi.getData().get(Integer.parseInt(materiId)-1).getGambar_utuh();

            //set picture
            Glide.with(MateriReadActivity.this).load(img_path).into(img_materi);

            //set title
            txt_title.setText(title);

            //set description
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                txt_description.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY));
            } else
                txt_description.setText(Html.fromHtml(description));
            };

        };
    };