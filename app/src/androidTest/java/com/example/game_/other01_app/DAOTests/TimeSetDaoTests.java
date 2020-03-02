package com.example.game_.other01_app.DAOTests;

import android.content.Context;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.AssistanceClasses.ListAssist;
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.TimeSetDao;
import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.Database.entities.TimeSet;
import com.example.game_.other01_app.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TimeSetDaoTests {
    @Mock
    private Observer<List<TimeSet>> observer;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private TimeSetDao timeSetDao;
    private static final String updateExerciseName = "Chest Stretch";
    private static final long updateTodaysTime =  99L;
    private static final String updateLastAccessed = DateTimeAssist.getCurrentDateInddMMyyyyFormat();
    AppDatabase db;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        timeSetDao = db.timeSetDao();
        timeSetDao.deleteAll();
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void testTimeSetsAreFilledCorrectly() throws InterruptedException {
        timeSetDao.insertAll(TimeSet.populateData(Exercise.populateData()));
        List<TimeSet> timeSets = LiveDataTestUtil.getValue(timeSetDao.getAll());
        assertNotNull(timeSets);
        assertEquals(Exercise.populateData().length, timeSets.size());
        assertEquals(TimeSet.populateData(Exercise.populateData()).length, timeSets.size());
        assertEquals(0L, timeSets.get(0).getRecentTime());
    }

    @Test
    public void testUpdateTimeSet() {
        timeSetDao.insertAll(TimeSet.populateData(Exercise.populateData()));
        timeSetDao.updateTimeSet(updateExerciseName, updateTodaysTime, updateLastAccessed);
        TimeSet actualTimeSet = timeSetDao.findByExercise(updateExerciseName);
        assertEquals(updateExerciseName, actualTimeSet.getExercisename());
        assertEquals(updateTodaysTime, actualTimeSet.getRecentTime());
        assertEquals(updateLastAccessed, actualTimeSet.getLastaccessed());
    }

    @Test
    public void testResetAllRecentTimes() throws InterruptedException {
        TimeSet[] timeSetArray = new TimeSet[]{
            new TimeSet("A", 400L,
                    99L,  "never", "A"),
                new TimeSet("B", 0L,
                        99L,  "never", "B"),
                new TimeSet("C", 83L,
                        99L,  "never", "C")
        };
        timeSetDao.insertAll(timeSetArray);
        timeSetDao.resetAllRecentTimes();
        List<TimeSet> timeSets = LiveDataTestUtil.getValue(timeSetDao.getAll());
        assertEquals(3, timeSets.size());
        assertEquals(0L, timeSets.get(0).getRecentTime());
        assertEquals(0L, timeSets.get(1).getRecentTime());
        assertEquals(0L, timeSets.get(2).getRecentTime());
        assertEquals(99L, timeSets.get(0).getBestTime());
        assertEquals(99L, timeSets.get(1).getBestTime());
        assertEquals(99L, timeSets.get(2).getBestTime());
    }

}
