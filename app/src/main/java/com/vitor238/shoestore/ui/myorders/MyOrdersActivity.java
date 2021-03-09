package com.vitor238.shoestore.ui.myorders;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.vitor238.shoestore.R;
import com.vitor238.shoestore.databinding.ActivityMyOrdersBinding;
import com.vitor238.shoestore.ui.BaseActivity;
import com.vitor238.shoestore.utils.DiffUtilsProduct;

public class MyOrdersActivity extends BaseActivity {

    ActivityMyOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(binding.toolbar, R.string.my_orders, true);

        OrdersAdapter ordersAdapter = new OrdersAdapter(new DiffUtilsProduct());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(ordersAdapter);

        MyOrdersViewModel myOrdersViewModel = new ViewModelProvider(this).get(MyOrdersViewModel.class);

        myOrdersViewModel.getHotStockLiveData().observe(this, products -> {
            if (products.isEmpty()) {
                binding.viewFlipper.setDisplayedChild(1);
            } else {
                binding.viewFlipper.setDisplayedChild(2);
            }

        });

    }
}