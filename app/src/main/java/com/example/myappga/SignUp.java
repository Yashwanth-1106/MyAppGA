package com.example.myappga;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class SignUp extends Fragment {

    private EditText editTextFullName;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextPassword;
    private Button registerButton;
    private Auth_ViewModel viewModel;

    private DataBase database;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI elements
        editTextFullName = view.findViewById(R.id.editTextText3);
        editTextEmail = view.findViewById(R.id.editTextTextEmailAddress);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        editTextPassword = view.findViewById(R.id.editTextTextPassword);
        registerButton = view.findViewById(R.id.button3);

        // Use the shared ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(Auth_ViewModel.class);


        //List<Student> entities = dao.getAllStudent();




        //dataBase = Room.databaseBuilder(this,SignUp.super,"student").build().dao();

        // Set click listener for the register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle registration button click
                String fullName = editTextFullName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String password = editTextPassword.getText().toString();

                Student student = DataBase.getDB(requireContext()).userDao().getUserByEmail(email);
                if (student != null) {
                    Toast.makeText(getContext(),"User already exist",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fullName.isEmpty()) {
                    editTextFullName.setError("Enter the Name");
                    return;
                }
                if(!fullName.matches("[a-zA-Z]+")){
                    editTextFullName.setError("Name must have characters only");
                    return;
                }

                if (email.isEmpty() ) {
                    editTextEmail.setError("Enter the E-mail");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Invalid email address");
                    return;
                }
                if (phone.isEmpty()) {
                    editTextPhone.setError("Enter the Mobile number");
                }
                if(!Patterns.PHONE.matcher(phone).matches()
                ) {
                    editTextPhone.setError("Invalid mobile number");
                    return;
                }

                if (password.isEmpty()) {
                    editTextPassword.setError("Enter the password");
                    return;
                }
                if(password.length() < 8)
                 {
                    editTextPassword.setError("Password must have minimum 8 characters");
                 }


                else {

                    student = new Student(fullName, phone, email, password);
                    DataBase.getDB(requireContext()).userDao().adduser(student);


                    viewModel.setUsername(fullName);
                    viewModel.setEmail(email);
                    viewModel.setMob(phone);
                    viewModel.setPassword(password);


//

                    // Navigate to the next fragment or perform other actions
                    NavController navController = Navigation.findNavController(v);
                    navController.navigateUp();
                }
            }
        });



    }

}