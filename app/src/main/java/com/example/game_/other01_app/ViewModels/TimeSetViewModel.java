package com.example.game_.other01_app.ViewModels;

import android.app.Application;
import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.NonDBObjects.MiniTimeSet;
import com.example.game_.other01_app.Database.entities.TimeSet;
import com.example.game_.other01_app.Repositories.TimeSetRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TimeSetViewModel extends AndroidViewModel {

    private final TimeSetRepository timeSetRepository;
    private final LiveData<List<TimeSet>> mTimeSets;

    public TimeSetViewModel(@NonNull Application application) {
        super(application);
        timeSetRepository = new TimeSetRepository(application);
        mTimeSets = timeSetRepository.getTimeSets();
    }

    public LiveData<List<TimeSet>> getAllTimeSets() { return mTimeSets; }

    /**
     * Updates the timeset with the corresponding exercise name
     * @param exerciseName
     * @param exerciseTime
     */
    public void updateTimeSet(String exerciseName, Long exerciseTime) {
        MiniTimeSet miniTimeSet = new MiniTimeSet(exerciseName, exerciseTime,
                DateTimeAssist.getCurrentDateInddMMyyyyFormat());
        timeSetRepository.updateTimeSet(miniTimeSet);
    }

    public List<TimeSet> getAllTimeSetsNotLive() throws ExecutionException, InterruptedException {
        return timeSetRepository.getTimeSetsNotLive();
    }

    public void resetAllRecentTimes() {
        timeSetRepository.resetAllRecentTimes();
    }

    /**
     * Resets all recent times and increases best times if necessary
     * @param timeSetsForBestUpdate
     */
    public void postMessageAdmin(List<TimeSet> timeSetsForBestUpdate) {
        timeSetRepository.postMessageAdmin(timeSetsForBestUpdate);
    }
}
