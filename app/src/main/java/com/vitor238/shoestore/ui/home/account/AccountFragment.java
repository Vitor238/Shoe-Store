package com.vitor238.shoestore.ui.home.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.vitor238.shoestore.R;
import com.vitor238.shoestore.databinding.FragmentAccountBinding;
import com.vitor238.shoestore.ui.login.LoginActivity;
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

        binding.buttonLogIn.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
        });

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.container_settings, SettingsFragment.newInstance())
                .commit();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LoggedInViewModel loggedInViewModel = new ViewModelProvider(this).get(LoggedInViewModel.class);
        loggedInViewModel.getUserLiveData().observe(getViewLifecycleOwner(), firebaseUser -> {
            if (firebaseUser == null) {
                binding.viewFlipper.setDisplayedChild(0);
            } else {
                binding.viewFlipper.setDisplayedChild(1);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}