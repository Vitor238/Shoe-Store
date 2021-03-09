package com.vitor238.shoestore.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseUtils {

    private static final String userID = FirebaseAuthUtils.getFirebaseUid();
    private static DatabaseReference databaseReference;

    //retorna a referencia do database
    public static DatabaseReference getDatabaseReference() {
        if (databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;
    }

    public static DatabaseReference getMyOrdersReference() {
        return getDatabaseReference().child("orders").child(userID);
    }

}
