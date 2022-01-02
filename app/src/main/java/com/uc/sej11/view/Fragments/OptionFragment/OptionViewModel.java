package com.uc.sej11.view.Fragments.OptionFragment;

import android.app.Application;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.uc.sej11.repositories.ProfileRepository;

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
