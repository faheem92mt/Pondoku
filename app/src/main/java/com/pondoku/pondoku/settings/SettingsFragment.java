package com.pondoku.pondoku.settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pondoku.pondoku.R;
import com.pondoku.pondoku.databinding.FragmentSettingsBinding;
import com.pondoku.pondoku.login.LoginActivity;
import com.pondoku.pondoku.profile.ChangePasswordActivity;


public class SettingsFragment extends Fragment {

    private SettingsViewModel SettingsViewModel;
    private FragmentSettingsBinding binding;

    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // binding to the MainActivity
        SettingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // getting instance of the database
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        // binding the text settings
        final TextView textSettings = binding.textSettings;
        SettingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textSettings.setText(s);
            }
        });

        // binding the username
        final TextView usernameSettings = binding.usernameSettings;
        assert user != null;
        usernameSettings.setText(user.getDisplayName());

        // binding the email of the user
        final TextView emailSettings = binding.emailSettings;
        emailSettings.setText(user.getEmail());

        // setting a log out button
        final Button logOutButton = binding.logOutButton;
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                getActivity().onBackPressed();
                getActivity().finish();
                startActivity(intent);
            }
        });

        final Button logOutButtton = binding.logOutButtton;
        logOutButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                firebaseAuth.signOut();
                getActivity().onBackPressed();
                getActivity().finish();
                startActivity(intent);
            }
        });

        return root;
    }

//    public void btnLogoutClick(View view) {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        startActivity(new Intent(getActivity(), LoginActivity.class));
//        firebaseAuth.signOut();
////        finishAndRemoveTask();
////        finish();
//
//    }


    /**
     * When view is destroyed, set binding to null
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}