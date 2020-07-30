package com.example.game_.other01_app.ViewModels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.game_.other01_app.Database.entities.CompendiumActivities;
import com.example.game_.other01_app.Repositories.CompendiumRepository;

import java.util.List;

public class CompendiumActivitiesViewModel extends ViewModel {
    private LiveData<List<CompendiumActivities>> mCompendiums;

    public CompendiumActivitiesViewModel(Application app)
    {
        CompendiumRepository repository = new CompendiumRepository(app);
        mCompendiums = repository.getmCompendiums();
    }


    public LiveData<List<CompendiumActivities>> getAllCompendiums() {return mCompendiums;}
}
