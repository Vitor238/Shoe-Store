package com.vitor238.shoestore.ui.signup;

import android.os.Bundle;

import com.vitor238.shoestore.R;
import com.vitor238.shoestore.databinding.ActivitySignUpBinding;
import com.vitor238.shoestore.ui.BaseActivity;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(binding.toolbar, R.string.sign_up, true);
    }
}