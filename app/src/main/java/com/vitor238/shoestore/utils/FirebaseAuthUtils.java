package com.vitor238.shoestore.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthUtils {
    private static FirebaseAuth firebaseAuth;

    public static FirebaseAuth getFirebaseAuth() {
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    public static FirebaseUser getCurrentFirebaseUser() {
        return getFirebaseAuth().getCurrentUser();
    }

    public static String getFirebaseUid() {
        return getCurrentFirebaseUser().getUid();
    }
}
