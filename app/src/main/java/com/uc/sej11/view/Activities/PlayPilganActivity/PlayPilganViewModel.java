package com.uc.sej11.view.Activities.PlayPilganActivity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.Soal;
import com.uc.sej11.repositories.SoalRepository;

public class PlayPilganViewModel extends AndroidViewModel {
    private SoalRepository soalRepository;
    private static final String TAG = "PlayPilganViewModel";

    public PlayPilganViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "Init: " + token);
        soalRepository = SoalRepository.getInstance(token);
    }

    private MutableLiveData<Soal> resultSoal = new MutableLiveData<>();
    public void getSoal(){
        resultSoal = soalRepository.getSoal();
    }
    public LiveData<Soal> getResultSoal(){
        return resultSoal;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        soalRepository.resetInstance();
    }

}
