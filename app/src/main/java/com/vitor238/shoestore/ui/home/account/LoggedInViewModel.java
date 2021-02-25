package com.vitor238.shoestore.ui.home.account;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.vitor238.shoestore.data.repository.AuthRepository;

public class LoggedInViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<FirebaseUser> user;

    public LoggedInViewModel() {
        authRepository = new AuthRepository();
        user = authRepository.getUserLiveData();
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return user;
    }
}
