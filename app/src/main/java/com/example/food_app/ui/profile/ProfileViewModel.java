package com.example.food_app.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> userName;
    private MutableLiveData<String> userEmail;
    private MutableLiveData<String> userBio;

    public ProfileViewModel() {
        userName = new MutableLiveData<>();
        userEmail = new MutableLiveData<>();
        userBio = new MutableLiveData<>();
        loadUserData();
    }

    private void loadUserData() {
        // TODO: Fetch user data from API or SharedPreferences
        // For now, using mock data
        userName.setValue("Vân Trang");
        userEmail.setValue("vantrang@gmail.com");
        userBio.setValue("Tôi thích đồ ăn nhanh");
    }

    public LiveData<String> getUserName() {
        return userName;
    }

    public LiveData<String> getUserEmail() {
        return userEmail;
    }

    public LiveData<String> getUserBio() {
        return userBio;
    }
}
