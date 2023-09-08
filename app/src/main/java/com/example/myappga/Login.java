package com.example.myappga;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Login extends Fragment {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView buttonSignUp;
    private Auth_ViewModel viewModel;
    private DataBase database;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextEmail = view.findViewById(R.id.editTextTextEmailAddress2);
        editTextPassword = view.findViewById(R.id.editTextTextPassword2);
        buttonLogin = view.findViewById(R.id.button);
        buttonSignUp = view.findViewById(R.id.textView);



        // Use the shared ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(Auth_ViewModel.class);

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("Myprefs", Context.MODE_PRIVATE);

        // Retrieve saved values from SharedPreferences
        String savedEmail = sharedPreferences.getString("Email", "");
        String savedPassword = sharedPreferences.getString("Password", "");

        // Set saved values to EditText fields
//        editTextEmail.setText(savedEmail);
//        editTextPassword.setText(savedPassword);

        if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
            Student student = DataBase.getDB(requireContext()).userDao().getUserByEmailAndPassword(savedEmail,savedPassword);
            if (student != null) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_login_to_home1);

            }
        }

        viewModel.getEmail().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String email) {
                if (email != null) {
                    editTextEmail.setText(email);
                }
            }
        });

        viewModel.getPassword().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String password) {
                if (password != null) {
                    editTextPassword.setText(password);
                }
            }
        });

        // Set click listeners
        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            // Navigate to the next fragment or perform other actions
            if (TextUtils.isEmpty(email)) {
                editTextEmail.setError("Enter the email address");
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError("Invalid email address");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                editTextPassword.setError("Enter the password");
                return;
            }
            if(password.length() < 8)
            {
                editTextPassword.setError("Password must have minimum 8 characters");
            }
            // Check user credentials (replace with your authentication logic)
            Student student = DataBase.getDB(requireContext()).userDao().getUserByEmailAndPassword(email, password);

            if (student != null) {
                // Save email and password to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Email", email);
                editor.putString("Password", password);
                editor.apply();

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_login_to_home1);
            } else {
                Toast.makeText(getContext(), "User doesn't exist. Register first", Toast.LENGTH_SHORT).show();
            }
        });

        buttonSignUp.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_login_to_signUp));
    }
}
