package com.uc.sej11.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.Materi;
import com.uc.sej11.retrofit.RetrofitService;
import com.uc.sej11.view.Fragments.MateriFragment.MateriAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateriRepository {
    private static MateriRepository materiRepository;
    private RetrofitService apiService;
    private static final String TAG = "MateriRepository";

    private MateriRepository(String token){
        Log.d(TAG, "Token: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static MateriRepository getInstance(String token){
        if(materiRepository == null){
            materiRepository = new MateriRepository(token);
        }
        return materiRepository;
    }

    public static synchronized void resetInstance(){
        if(materiRepository != null){
            materiRepository = null;
        }else{
            materiRepository = null;
        }
    }

    public MutableLiveData<Materi> getData(){
        final MutableLiveData<Materi> listData = new MutableLiveData<>();

        apiService.getData().enqueue(new Callback<Materi>() {
            @Override
            public void onResponse(Call<Materi> call, Response<Materi> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getData().size());
                        listData.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Materi> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return listData;
    }

    public MutableLiveData<Materi> getDatabyId(String id){
        final MutableLiveData<Materi> listDatabyID = new MutableLiveData<>();

        apiService.getData().enqueue(new Callback<Materi>() {
            @Override
            public void onResponse(Call<Materi> call, Response<Materi> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getData().size());
                        listDatabyID.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Materi> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return listDatabyID;
    }

}
