package com.example.game_.other01_app.ViewModels;
import android.app.Application;

import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.Repositories.UserRepository;

import java.util.concurrent.ExecutionException;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private final LiveData<User> mUser;

    public UserViewModel(Application application){
        super(application);
        userRepository = new UserRepository(application);
        mUser = userRepository.getUser();
    }

    public LiveData<User> getUser() {
        return mUser;
    }

    /**
     * Creates a user at the start of the app, updates the user from Change Preferences
     * @param user
     */
    public void createOrUpdateUser(User user) {
        userRepository.createNewUser(user);
    }

    public User getUserNotLive() throws ExecutionException, InterruptedException {
        return userRepository.getUserNotLive();
    }

    /**
     * Increases the total time spent exercising for the day
     * @param exerciseTime
     * @param intensity
     */
    public void increaseTodaysTotal(Long exerciseTime, String intensity) {
        userRepository.increaseTotalAndIntensity(exerciseTime, intensity);
    }
}
