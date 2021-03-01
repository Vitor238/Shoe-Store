package com.vitor238.shoestore.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vitor238.shoestore.utils.Status;

public class AuthRepository {

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final MutableLiveData<FirebaseUser> user = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loggedOut = new MutableLiveData<>();
    private final MutableLiveData<Status> status = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final String TAG;

    public AuthRepository() {
        this.TAG = AuthRepository.class.getSimpleName();
        if (firebaseAuth.getCurrentUser() != null) {
            user.setValue(firebaseAuth.getCurrentUser());
            loggedOut.setValue(false);
        }
    }

    public void createUserWithEmailAndPassword(@NonNull String email, @NonNull String password) {
        status.setValue(Status.LOADING);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        status.setValue(Status.SUCCESS);
                        user.setValue(firebaseAuth.getCurrentUser());
                    } else {
                        status.setValue(Status.FAILED);
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        String error = task.getException().getMessage();
                        if (error != null) {
                            errorMessage.setValue(error);
                        }
                    }
                });
    }

    public void signInWithEmailAndPassword(@NonNull String email, @NonNull String password) {
        status.setValue(Status.LOADING);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        status.setValue(Status.SUCCESS);
                        user.setValue(firebaseAuth.getCurrentUser());
                    } else {
                        status.setValue(Status.FAILED);
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        String error = task.getException().getMessage();
                        if (error != null) {
                            errorMessage.setValue(error);
                        }
                    }
                });
    }

    public void logOut() {
        firebaseAuth.signOut();
        loggedOut.setValue(true);
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return user;
    }

    public MutableLiveData<Status> getStatusLiveData() {
        return status;
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOut;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
