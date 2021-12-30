package com.uc.sej11.view.Activities.RegisterActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.uc.sej11.model.RegisterResponse;
import com.uc.sej11.repositories.AuthRepository;

public class RegisterViewModel extends AndroidViewModel {
    private AuthRepository authRepository;
    private static final String TAG = "RegistryViewModel";

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance();
    }

    public MutableLiveData<RegisterResponse> register(String name, String email,
                                                      String password, String password_confirmation,
                                                      String school, String city, String birthyear){
        return authRepository.register(name, email, password, password_confirmation, school, city,
                birthyear);
    }
}
