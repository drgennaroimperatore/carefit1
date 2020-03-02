package com.example.game_.other01_app.ViewModelTests;

import android.app.Application;

import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.LiveDataTestUtil;
import com.example.game_.other01_app.ViewModels.ExerciseListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.platform.app.InstrumentationRegistry;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ExerciseListViewModelTests {

    ExerciseListViewModel exerciseListViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        exerciseListViewModel = new ExerciseListViewModel(
            (Application)
                    InstrumentationRegistry.getInstrumentation()
                            .getTargetContext().getApplicationContext()
            );
    }

    @Test
    public void assertLiveDataFull() throws InterruptedException {
        assertEquals(Exercise.populateData().length,
                LiveDataTestUtil.getValue(exerciseListViewModel.getAllExercises()).size());
    }

    @Test
    public void testFilterExercisesWorks(){
        List<Exercise> filteredExercises =
                exerciseListViewModel.filterExercises(Arrays.asList(Exercise.populateData()), "Arms");
        assertEquals(6, filteredExercises.size());
    }

    @Test
    public void testFilterExercisesGetsAllWhenNoFilters(){
        List<Exercise> filteredExercises =
                exerciseListViewModel.filterExercises(Arrays.asList(Exercise.populateData()), "");
        assertEquals(Exercise.populateData().length, filteredExercises.size());
    }

    @Test
    public void testFilterExercisesReturnsEmpty(){
        List<Exercise> filteredExercises =
                exerciseListViewModel.filterExercises(Arrays.asList(Exercise.populateData()), "Arms,Legs,Back,Sitting,Aerobic");
        assertTrue(filteredExercises.isEmpty());
    }
}
