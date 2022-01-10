package com.uc.sej11.view.Fragments.ScoreboardFragment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.Materi;
import com.uc.sej11.model.User;
import com.uc.sej11.model.UserLevel;
import com.uc.sej11.repositories.MateriRepository;
import com.uc.sej11.repositories.UserLevelRepository;

public class ScoreboardViewModel extends AndroidViewModel {
    private UserLevelRepository userLevelRepository;
    private MateriRepository materiRepository;
    private static final String TAG = "ScoreboardViewModel";

    public ScoreboardViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "Token: " + token);
        userLevelRepository = UserLevelRepository.getInstance(token);
        materiRepository = MateriRepository.getInstance(token);
    }

    private MutableLiveData<UserLevel> resultData = new MutableLiveData<UserLevel>();
    public void getAll_level(){
        resultData = userLevelRepository.getAll_level();
    }
    public LiveData<UserLevel> getResultData(){
        return resultData;
    }

    private MutableLiveData<Materi> resultMateri = new MutableLiveData<>();
    public void getDatabyID(String id){
        resultMateri = materiRepository.getDatabyId(id);
    }
    public LiveData<Materi> getResultMateri(){
        return resultMateri;
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        Log.d(TAG, "onCleared");
        UserLevelRepository.resetInstance();
        MateriRepository.resetInstance();
    }


}
