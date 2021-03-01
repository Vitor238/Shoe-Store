package com.vitor238.shoestore.ui.login;

import android.os.Bundle;

import com.vitor238.shoestore.R;
import com.vitor238.shoestore.databinding.ActivityLoginBinding;
import com.vitor238.shoestore.ui.BaseActivity;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(binding.toolbar, R.string.log_in, true);
    }
}