package com.example.game_.other01_app.Activities.UserInterface.Progress;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgressViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProgressViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the progress fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}