package com.vitor238.shoestore.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.vitor238.shoestore.databinding.FragmentAccountBinding;
import com.vitor238.shoestore.ui.signup.SignUpActivity;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false);

        binding.buttonSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), SignUpActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}