package com.example.game_.other01_app.ViewModelTests;

import android.app.Application;

import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.Database.entities.TimeSet;
import com.example.game_.other01_app.LiveDataTestUtil;
import com.example.game_.other01_app.ViewModels.TimeSetViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.platform.app.InstrumentationRegistry;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class TimeSetViewModelTests {

    TimeSetViewModel timeSetViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        timeSetViewModel = new TimeSetViewModel(
                (Application)
                        InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getApplicationContext()
        );
    }

    @Test
    public void assertGetAllTimeSetsNotNullAndFull() throws InterruptedException {
        assertNotNull(timeSetViewModel.getAllTimeSets());
        assertEquals(TimeSet.populateData(Exercise.populateData()).length,
                LiveDataTestUtil.getValue(timeSetViewModel.getAllTimeSets()).size());
    }

    @Test
    public void assertUpdateTimeSetWorks() throws ExecutionException, InterruptedException {
        timeSetViewModel.updateTimeSet("Chest Stretch", 100000L);
        List<TimeSet> allTimeSets = timeSetViewModel.getAllTimeSetsNotLive();
        TimeSet toCheck = null;
        for(TimeSet timeSet : allTimeSets){
            if (timeSet.getExercisename().equals("Chest Stretch")){
                toCheck = timeSet;
                break;
            }
        }
        assertEquals(100000L, toCheck.getRecentTime());
        timeSetViewModel.resetAllRecentTimes();
    }

    @Test
    public void testPostMessageAdmin() throws ExecutionException, InterruptedException {
        timeSetViewModel.updateTimeSet("Chest Stretch", 100000L);
        List<TimeSet> allTimeSets = timeSetViewModel.getAllTimeSetsNotLive();
        TimeSet toCheck = null;
        for(TimeSet timeSet : allTimeSets){
            if (timeSet.getExercisename().equals("Chest Stretch")){
                toCheck = timeSet;
                break;
            }
        }

        List<TimeSet> timeSetsForAdmin = new ArrayList<>();
        timeSetsForAdmin.add(toCheck);
        timeSetViewModel.postMessageAdmin(timeSetsForAdmin);

        TimeSet postAdminSet = null;
        List<TimeSet> allTimeSetsToCheck = timeSetViewModel.getAllTimeSetsNotLive();
        for(TimeSet timeSet : allTimeSetsToCheck){
            if (timeSet.getExercisename().equals("Chest Stretch")){
                postAdminSet = timeSet;
                break;
            }
        }
        assertEquals(postAdminSet.getBestTime(), 100000L);
    }

}
