package com.vitor238.shoestore.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vitor238.shoestore.utils.SignUpStatus;

public class AuthRepository {

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final MutableLiveData<FirebaseUser> user = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loggedOut = new MutableLiveData<>();
    private final MutableLiveData<SignUpStatus> status = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final String TAG;

    public AuthRepository() {
        this.TAG = AuthRepository.class.getSimpleName();
        if (firebaseAuth.getCurrentUser() != null) {
            user.postValue(firebaseAuth.getCurrentUser());
            loggedOut.postValue(false);
        }
    }

    public void createUserWithEmailAndPassword(@NonNull String email, @NonNull String password) {
        status.setValue(SignUpStatus.LOADING);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        status.setValue(SignUpStatus.SUCCESS);
                        user.setValue(firebaseAuth.getCurrentUser());
                    } else {
                        status.setValue(SignUpStatus.FAILED);
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        String error = task.getException().getMessage();
                        if (error != null) {
                            errorMessage.setValue(error);
                        }
                    }
                });
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return user;
    }

    public MutableLiveData<SignUpStatus> getStatusLiveData() {
        return status;
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOut;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
