package com.vitor238.shoestore.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.vitor238.shoestore.R;
import com.vitor238.shoestore.databinding.FragmentEmailBinding;


public class EmailLoginFragment extends Fragment {

    FragmentEmailBinding binding;

    public EmailLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEmailBinding.inflate(getLayoutInflater(), container, false);

        binding.buttonNext.setOnClickListener(v -> {
            String email = binding.fieldEmail.getEditText().getText().toString();
            if (!email.isEmpty()) {
                NavDirections navDirections = EmailLoginFragmentDirections.actionEmailLoginFragmentToPasswordLoginFragment(email);
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            } else {
                Snackbar.make(binding.getRoot(), getString(R.string.type_your_email), Snackbar.LENGTH_SHORT)
                        .show();
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}