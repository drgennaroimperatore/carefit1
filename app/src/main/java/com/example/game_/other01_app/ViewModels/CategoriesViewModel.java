package com.example.game_.other01_app.ViewModels;

import android.app.Application;
import android.util.Pair;

import com.example.game_.other01_app.Database.entities.Category;
import com.example.game_.other01_app.Repositories.CategoriesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CategoriesViewModel extends AndroidViewModel {

    private final CategoriesRepository mCategoriesRepository;
    private final LiveData<List<Category>> mAllCategories;

    public CategoriesViewModel(@NonNull Application application) {
        super(application);
        mCategoriesRepository = new CategoriesRepository(application);
        mAllCategories = mCategoriesRepository.getmAllCategories();
    }

    /**
     * @return All categories as LiveData
     */
    public LiveData<List<Category>> getAllCategories() { return mAllCategories; }

    /**
     * Sets list of exercises the user is interested in
     * @param interested
     * @param notInterested
     */
    public void setPreferences(ArrayList<String> interested, ArrayList<String> notInterested) {
        ArrayList<Pair<String, Boolean>> preferencePairs = new ArrayList<>();
        for(String preference : interested){
            Pair<String, Boolean> preferencePair = new Pair<>(preference, true);
            preferencePairs.add(preferencePair);
        }
        for(String preference : notInterested){
            Pair<String, Boolean> preferencePair = new Pair<>(preference, false);
            preferencePairs.add(preferencePair);
        }
        mCategoriesRepository.updatePreferences(preferencePairs);
    }

    /**
     * @return Retrieves all categories when LiveData is not required
     */
    public List<Category> getAllCategoriesNotLive() {
        List<Category> listOfCategories = null;
        try {
            listOfCategories = mCategoriesRepository.getmAllCategoriesNotNull();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listOfCategories;
    }
}
