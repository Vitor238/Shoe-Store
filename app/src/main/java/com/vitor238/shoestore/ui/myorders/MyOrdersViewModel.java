package com.vitor238.shoestore.ui.myorders;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.vitor238.shoestore.data.model.Product;
import com.vitor238.shoestore.data.repository.FirebaseQueryLiveData;
import com.vitor238.shoestore.utils.FirebaseDatabaseUtils;

import java.util.ArrayList;

public class MyOrdersViewModel extends ViewModel {

    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(FirebaseDatabaseUtils
            .getMyOrdersReference());

    private final LiveData<ArrayList<Product>> myOrdersLiveData =
            Transformations.map(liveData, new Deserializer());

    @NonNull
    public LiveData<ArrayList<Product>> getHotStockLiveData() {
        return myOrdersLiveData;
    }

    private class Deserializer implements Function<DataSnapshot, ArrayList<Product>> {
        @Override
        public ArrayList<Product> apply(DataSnapshot dataSnapshot) {
            ArrayList<Product> orders = new ArrayList<>();
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                orders.add(ds.getValue(Product.class));
            }
            return orders;
        }
    }
}
