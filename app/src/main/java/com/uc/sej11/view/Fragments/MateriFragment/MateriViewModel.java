package com.uc.sej11.view.Fragments.MateriFragment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.Materi;
import com.uc.sej11.repositories.MateriRepository;

public class MateriViewModel extends AndroidViewModel {
    private MateriRepository materiRepository;
    private static final String TAG = "MateriViewModel";

    public MateriViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "Init: " + token);
        materiRepository = MateriRepository.getInstance(token);
    }

    private MutableLiveData<Materi> resultData = new MutableLiveData<>();

    public void getData(){
        resultData = materiRepository.getData();
    }

    public LiveData<Materi> getResultData(){
        return resultData;
    }

    public void getDatabyID(String id){
        resultData = materiRepository.getDatabyId(id);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        MateriRepository.resetInstance();
    }

}
