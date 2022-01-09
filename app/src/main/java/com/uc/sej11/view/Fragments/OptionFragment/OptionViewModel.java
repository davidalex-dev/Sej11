package com.uc.sej11.view.Fragments.OptionFragment;

import android.app.Application;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.Pilgan;
import com.uc.sej11.model.User;
import com.uc.sej11.repositories.ProfileRepository;

import java.util.List;

public class OptionViewModel extends AndroidViewModel {
    private ProfileRepository profileRepository;
    private static final String TAG = "OptionViewModel";

    public OptionViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "token: " + token);
        profileRepository = ProfileRepository.getInstance(token);
    }

//    private MutableLiveData<List<Pilgan.Sej11OpsiPilgan>> resultPilgan = new MutableLiveData<>();
//    public void getSej11_opsi_pilgan(String id){
//        resultPilgan = pilganRepository.getSej11_opsi_pilgan(id);
//    }
//    public LiveData<List<Pilgan.Sej11OpsiPilgan>> getResultPilgan(){
//        return resultPilgan;
//    }

//    public LiveData<User> getUser(){
//        profileRepository.getUser();
//    }

    private MutableLiveData<User> resultUser = new MutableLiveData<>();
    public void getUser(){
        resultUser = profileRepository.getUser();
    }
    public LiveData<User> getResultUser(){
        return resultUser;
    }

    public LiveData<String> logout(){
        profileRepository.resetInstance();
        return profileRepository.logout();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        profileRepository.resetInstance();
    }

}
