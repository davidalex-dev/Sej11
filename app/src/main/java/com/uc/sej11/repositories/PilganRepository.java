package com.uc.sej11.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.Pilgan;
import com.uc.sej11.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilganRepository {
    private static PilganRepository pilganRepository;
    private RetrofitService apiService;
    private static final String TAG = "PilganRepository";

    private PilganRepository(String token){
        Log.d(TAG, "Token: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static PilganRepository getInstance(String token){
        Log.d(TAG, "Hello from GetInstance");
        if(pilganRepository == null){
            pilganRepository = new PilganRepository(token);
        }

        return pilganRepository;
    }

    public synchronized void resetInstance(){
        Log.d(TAG, "Hello from resetInstance");
        if(pilganRepository!=null){
            pilganRepository=null;
        }
    }

    public MutableLiveData<List<Pilgan.Sej11OpsiPilgan>> getSej11_opsi_pilgan(String id){
        final MutableLiveData<List<Pilgan.Sej11OpsiPilgan>> listData = new MutableLiveData<>();

        apiService.getSej11_opsi_pilgan(id).enqueue(new Callback<Pilgan>() {
            @Override
            public void onResponse(Call<Pilgan> call, Response<Pilgan> response) {
                Log.d(TAG, "Hello theree!!");
                if(response.body() !=null){
                    Log.d(TAG, "onResponse: " + response.body().getSej11_opsi_pilgan().size());
                    listData.postValue(response.body().getSej11_opsi_pilgan());
                }
            }

            @Override
            public void onFailure(Call<Pilgan> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listData;
    }

}
