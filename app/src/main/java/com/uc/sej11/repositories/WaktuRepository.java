package com.uc.sej11.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.Waktu;
import com.uc.sej11.retrofit.RetrofitService;

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
        if(waktuRepository !=null){
            waktuRepository = null;
        }
    }

//    public MutableLiveData<Waktu.Data> getData(){
//        final MutableLiveData<Waktu.Data> listData = new MutableLiveData<>();
//
//        apiService.getdataWaktu().enqueue(new Callback<Waktu>() {
//            public void onResponse(Call<Waktu> call, Response<Waktu> response) {
//                if (response.body() !=null){
//                    Log.d(TAG, "onResponse: " + response.body().getdataWaktu().size());
//                    listData.postValue(response.body().getdataWaktu().get(0));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Waktu> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.getMessage());
//            }
//        });
//
//        return listData;
//    }

    public MutableLiveData<Waktu> getData(){
        final MutableLiveData<Waktu> listData = new MutableLiveData<>();

        apiService.getdataWaktu().enqueue(new Callback<Waktu>() {
            @Override
            public void onResponse(Call<Waktu> call, Response<Waktu> response) {
                if(response.body() !=null){
                    Log.d(TAG, "onResponse: " + response.body().getdataWaktu().size());
                    listData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Waktu> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return listData;
    }

}
