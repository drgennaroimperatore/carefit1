package com.example.game_.other01_app.ViewModels;

import android.app.Application;

import com.example.game_.other01_app.AssistanceClasses.ListAssist;
import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.Repositories.ExerciseRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ExerciseListViewModel extends AndroidViewModel {

    private final LiveData<List<Exercise>> mAllExercises;

    public ExerciseListViewModel(Application application) {
        super(application);
        ExerciseRepository mExerciseRepository = new ExerciseRepository(application);
        mAllExercises = mExerciseRepository.getmAllExercises();
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return mAllExercises;
    }

    /**
     * Filters the list of exercises by the categories selected in Search
     * @param exercises
     * @param filters
     * @return
     */
    public List<Exercise> filterExercises(List<Exercise> exercises, String filters) {
        if(filters != null && !filters.equals("")){
            List<Exercise> filteredExercises = new ArrayList<>();
            List<String> filterList = ListAssist.convertStringToListOf(filters);
            for(Exercise exercise : exercises){
                if(exerciseFitsCategories(exercise, filterList)){
                    filteredExercises.add(exercise);
                }
            }
            return filteredExercises;
        } else {
            return exercises;
        }
    }

    private boolean exerciseFitsCategories(Exercise current, List<String> filterList) {
        List<String> currentCategories = ListAssist.convertStringToListOf(current.getCategories());
        for(String filter : filterList){
            if (!currentCategories.contains(filter)){
                return false;
            }
        }

        return true;
    }
}
