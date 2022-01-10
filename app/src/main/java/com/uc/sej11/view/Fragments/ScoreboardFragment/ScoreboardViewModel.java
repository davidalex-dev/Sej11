package com.uc.sej11.view.Fragments.ScoreboardFragment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ScoreboardViewModel extends AndroidViewModel {

    private static final String TAG = "ScoreboardViewModel";

    public ScoreboardViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "Token: " + token);
    }


    @Override
    protected void onCleared(){
        super.onCleared();
        Log.d(TAG, "onCleared");
    }


}
