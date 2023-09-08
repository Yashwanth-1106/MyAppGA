package com.example.myappga;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Auth_ViewModel extends ViewModel {
    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> mob = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();





    public LiveData<String> getUsername() {
        return username;
    }

    public LiveData<String> getPassword() {
        return password;
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public LiveData<String> getMob() {
        return mob;
    }

    public void setUsername(String fullName) {
        username.setValue(fullName);
    }

    public void setPassword(String newPassword) {
        password.setValue(newPassword);
    }

    public void setEmail(String newEmail) {
        email.setValue(newEmail);
    }

    public void setMob(String newMob) {
        mob.setValue(newMob);
    }
}