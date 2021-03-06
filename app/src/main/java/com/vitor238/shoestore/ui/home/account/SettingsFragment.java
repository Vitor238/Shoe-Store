package com.vitor238.shoestore.ui.home.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.vitor238.shoestore.R;
import com.vitor238.shoestore.ui.home.MainActivity;
import com.vitor238.shoestore.ui.myorders.MyOrdersActivity;

public class SettingsFragment extends PreferenceFragmentCompat {
    LoggedInViewModel loggedInViewModel;


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        loggedInViewModel = new ViewModelProvider(this)
                .get(LoggedInViewModel.class);

        Preference preferenceLogout = findPreference("pref_log_out");

        if (preferenceLogout != null) {
            preferenceLogout.setOnPreferenceClickListener(preference -> {
                loggedInViewModel.logOut();
                return true;
            });
        }

        Preference preferenceOrders = findPreference("pref_my_orders");

        if (preferenceOrders != null) {
            preferenceOrders.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(requireActivity(), MyOrdersActivity.class);
                startActivity(intent);
                return true;
            });
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loggedInViewModel.getLoggedOutLiveData().observe(getViewLifecycleOwner(), loggedOut -> {
            if (loggedOut) {
                Intent intent = new Intent(requireActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}