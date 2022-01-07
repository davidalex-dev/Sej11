package com.uc.sej11.view.Activities.PlayPilganActivity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.Pilgan;
import com.uc.sej11.model.Soal;
import com.uc.sej11.repositories.PilganRepository;
import com.uc.sej11.repositories.SoalRepository;

import java.util.List;

public class PlayPilganViewModel extends AndroidViewModel {
    private SoalRepository soalRepository;
    private PilganRepository pilganRepository;
    //private WaktuRepository waktuRepository;
    private static final String TAG = "PlayPilganViewModel";

    public PlayPilganViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "Init: " + token);
        soalRepository = SoalRepository.getInstance(token);
        pilganRepository = PilganRepository.getInstance(token);
        //waktuRepository = WaktuRepository.getInstance(token);
    }

    private MutableLiveData<List<Soal.Sej11Soal>> resultSoal = new MutableLiveData<>();
    public void getSej11_soal(String id){
        resultSoal = soalRepository.getSej11_soal(id);
    }
    public LiveData<List<Soal.Sej11Soal>> getResultSoal(){
        return resultSoal;
    }

    private MutableLiveData<List<Pilgan.Sej11OpsiPilgan>> resultPilgan = new MutableLiveData<>();
    public void getSej11_opsi_pilgan(String id){
        resultPilgan = pilganRepository.getSej11_opsi_pilgan(id);
    }
    public LiveData<List<Pilgan.Sej11OpsiPilgan>> getResultPilgan(){
        return resultPilgan;
    }

//    private MutableLiveData<Waktu> resultTimer = new MutableLiveData<>();
//    public void getTimer(){
//        resultTimer = waktuRepository.getTimer();
//    }
//    public LiveData<Waktu> getResultTimer(){
//        return resultTimer;
//    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        soalRepository.resetInstance();
        pilganRepository.resetInstance();
        //waktuRepository.resetInstance();
    }

}
