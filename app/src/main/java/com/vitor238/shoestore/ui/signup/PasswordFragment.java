package com.vitor238.shoestore.ui.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.vitor238.shoestore.R;
import com.vitor238.shoestore.databinding.FragmentPasswordBinding;
import com.vitor238.shoestore.ui.home.MainActivity;

import java.util.Objects;

public class PasswordFragment extends Fragment {

    private String email;
    private FragmentPasswordBinding binding;
    private String TAG;

    public PasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            PasswordFragmentArgs args = PasswordFragmentArgs.fromBundle(getArguments());
            email = args.getEmail();
            TAG = PasswordFragment.class.getSimpleName();
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPasswordBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SignUpViewModel signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        binding.buttonSignUp.setOnClickListener(v -> {
            hideKeyboard();
            String password = Objects.requireNonNull(binding.fieldPassword.getEditText()).getText().toString();

            if (!password.isEmpty()) {
                signUpViewModel.createUserWithEmailAndPassword(email, password);
            } else {
                Snackbar.make(binding.getRoot(), R.string.type_your_password, Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

        signUpViewModel.getStatusLiveData().observe(getViewLifecycleOwner(), signUpStatus -> {
            switch (signUpStatus) {
                case LOADING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    binding.progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(requireActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case FAILED:
                    binding.progressBar.setVisibility(View.GONE);
                    break;
            }
        });

        signUpViewModel.getErrorMessage().observe(getViewLifecycleOwner(), error ->
                Snackbar.make(binding.getRoot(), getString(R.string.error, error), Snackbar.LENGTH_SHORT)
                        .show());

    }

    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}