package com.uc.sej11.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.User;
import com.uc.sej11.model.UserLevel;
import com.uc.sej11.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLevelRepository {
    private static UserLevelRepository userLevelRepository;
    private RetrofitService apiService;
    private static final String TAG = "UserLevelRepository";

    private UserLevelRepository(String token){
        Log.d(TAG, "Token: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static UserLevelRepository getInstance(String token){
        if(userLevelRepository == null){
            userLevelRepository = new UserLevelRepository(token);
        }
        return userLevelRepository;
    }

    public static synchronized void resetInstance(){
        if(userLevelRepository != null){
            userLevelRepository = null;
        }else{
            userLevelRepository = null;
        }
    }

    public MutableLiveData<UserLevel> getAll_level(){
        final MutableLiveData<UserLevel> listData = new MutableLiveData<>();

        apiService.getAll_level().enqueue(new Callback<UserLevel>() {
            @Override
            public void onResponse(Call<UserLevel> call, Response<UserLevel> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getAll_level().size());
                        listData.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserLevel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listData;
    }

}
