package com.uc.sej11.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.Waktu;
import com.uc.sej11.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaktuRepository {
    private static WaktuRepository waktuRepository;
    private RetrofitService apiService;
    private static final String TAG = "WaktuRepository";

    private WaktuRepository(String token){
        Log.d(TAG, "Token: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static WaktuRepository getInstance(String token){
        if(waktuRepository == null){
            waktuRepository = new WaktuRepository(token);
        }

        return waktuRepository;
    }

    public synchronized void resetInstance(){
        if(waktuRepository != null){
            waktuRepository = null;
        }
    }

    public MutableLiveData<List<Waktu.Sej11Waktu>> getSej11_waktu(String id){
        final MutableLiveData<List<Waktu.Sej11Waktu>> listData = new MutableLiveData<>();

        apiService.getSej11_waktu(id).enqueue(new Callback<Waktu>() {
            @Override
            public void onResponse(Call<Waktu> call, Response<Waktu> response) {

                if(response.body() !=null){
                    Log.d(TAG, "onResponse: " + response.body().getSej11_waktu().size());
                    listData.postValue(response.body().getSej11_waktu());
                }

            }

            @Override
            public void onFailure(Call<Waktu> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

//        return null; //temporary
        return listData;
    }

}
