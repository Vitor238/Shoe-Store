package com.vitor238.shoestore.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vitor238.shoestore.data.repository.AuthRepository;
import com.vitor238.shoestore.utils.Status;

public class LoginViewModel extends ViewModel {

    private final AuthRepository authRepository;
    private final MutableLiveData<Status> status;
    private final MutableLiveData<String> errorMessage;

    public LoginViewModel() {
        authRepository = new AuthRepository();
        status = authRepository.getStatusLiveData();
        errorMessage = authRepository.getErrorMessage();
    }

    public void signInWithEmailAndPassword(String email, String password) {
        authRepository.signInWithEmailAndPassword(email, password);
    }

    public MutableLiveData<Status> getStatusLiveData() {
        return status;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
