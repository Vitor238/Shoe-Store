package com.vitor238.shoestore.ui.home.account;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.vitor238.shoestore.data.repository.AuthRepository;

public class LoggedInViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<FirebaseUser> user;
    private final MutableLiveData<Boolean> loggedOut;

    public LoggedInViewModel() {
        authRepository = new AuthRepository();
        user = authRepository.getUserLiveData();
        loggedOut = authRepository.getLoggedOutLiveData();
    }

    public void logOut() {
        authRepository.logOut();
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return user;
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOut;
    }
}
