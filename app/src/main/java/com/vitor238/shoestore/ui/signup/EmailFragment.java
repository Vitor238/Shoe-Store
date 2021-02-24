package com.vitor238.shoestore.ui.signup;

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

public class EmailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentEmailBinding binding;

    private String mParam1;
    private String mParam2;

    public EmailFragment() {
        // Required empty public constructor
    }

    public static EmailFragment newInstance(String param1, String param2) {
        EmailFragment fragment = new EmailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEmailBinding.inflate(getLayoutInflater(), container, false);

        binding.buttonNext.setOnClickListener(v -> {
            String email = binding.fieldEmail.getEditText().getText().toString();
            if (!email.isEmpty()) {
                NavDirections navDirections = EmailFragmentDirections.actionEmailFragmentToPasswordFragment(email);
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