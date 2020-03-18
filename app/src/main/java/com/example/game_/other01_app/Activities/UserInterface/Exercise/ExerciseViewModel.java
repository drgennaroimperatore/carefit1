package com.example.game_.other01_app.Activities.UserInterface.Exercise;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExerciseViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExerciseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the exercise fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}