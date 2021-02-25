package com.vitor238.shoestore.ui.signup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vitor238.shoestore.data.repository.AuthRepository;
import com.vitor238.shoestore.utils.SignUpStatus;

public class SignUpViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<SignUpStatus> status;
    private final MutableLiveData<String> errorMessage;

    public SignUpViewModel() {
        authRepository = new AuthRepository();
        status = authRepository.getStatusLiveData();
        errorMessage = authRepository.getErrorMessage();
    }

    public void createUserWithEmailAndPassword(String email, String password) {
        authRepository.createUserWithEmailAndPassword(email, password);
    }

    public MutableLiveData<SignUpStatus> getStatusLiveData() {
        return status;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
