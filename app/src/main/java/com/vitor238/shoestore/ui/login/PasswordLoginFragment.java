package com.vitor238.shoestore.ui.login;

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
import com.vitor238.shoestore.databinding.FragmentPasswordLoginBinding;
import com.vitor238.shoestore.ui.home.MainActivity;

public class PasswordLoginFragment extends Fragment {

    private FragmentPasswordLoginBinding binding;
    private String email;
    private String TAG;

    public PasswordLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            PasswordLoginFragmentArgs args = PasswordLoginFragmentArgs.fromBundle(getArguments());
            email = args.getEmail();
            TAG = PasswordLoginFragment.class.getSimpleName();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentPasswordLoginBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.buttonLogIn.setOnClickListener(v -> {
            hideKeyboard();
            String password = binding.fieldPassword.getEditText().getText().toString();
            if (!password.isEmpty()) {
                loginViewModel.signInWithEmailAndPassword(email, password);
            } else {
                Snackbar.make(binding.getRoot(), R.string.type_your_password, Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

        loginViewModel.getStatusLiveData().observe(getViewLifecycleOwner(), signUpStatus -> {
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

        loginViewModel.getErrorMessage().observe(getViewLifecycleOwner(), error ->
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