package com.example.game_.other01_app.EntityTests;

import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.Database.entities.TimeSet;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TimeSetTests {
    private static final String testName = "testName";
    private static final int testTodaysTime = 1234;
    private static final int testBestTime = 5678;
    private static final String testLastAccessed = "testDate";
    private static final String testImage = "testImage";

    private TimeSet testTimeSet;

    private static Exercise[] exercises = new Exercise[]{
            new Exercise(1, "A", "B",
                    "C", "D", "E")
    };

    @Before
    public void setUp(){
        testTimeSet = new TimeSet(testName, testTodaysTime,
                testBestTime, testLastAccessed, testImage);
    }

    @Test
    public void testPopulateData(){
        TimeSet[] testTimeSets = TimeSet.populateData(exercises);
        assertEquals(1, testTimeSets.length);
        assertEquals("A", exercises[0].getName());
    }

    @Test
    public void testGetExerciseName(){
        assertEquals(testName, testTimeSet.getExercisename());
    }

    @Test
    public void testSetExerciseName(){
        testTimeSet.setExercisename("NEW");
        assertEquals("NEW", testTimeSet.getExercisename());
    }

    @Test
    public void testGetRecentTime(){
        assertEquals(testTodaysTime, testTimeSet.getRecentTime());
    }

    @Test
    public void testSetRecentTime(){
        testTimeSet.setRecentTime(999);
        assertEquals(999, testTimeSet.getRecentTime());
    }

    @Test
    public void testGetBestTime(){
        assertEquals(testBestTime, testTimeSet.getBestTime());
    }

    @Test
    public void testSetBestTimeInt(){
        testTimeSet.setBestTime(1000);
        assertEquals(1000, testTimeSet.getBestTime());
    }

    @Test
    public void testGetLastAccessed(){
        assertEquals(testLastAccessed, testTimeSet.getLastaccessed());
    }

    @Test
    public void testSetLastAccessed(){
        testTimeSet.setLastaccessed("NEW");
        assertEquals("NEW", testTimeSet.getLastaccessed());
    }

    @Test
    public void testGetID(){
        assertEquals(0, testTimeSet.getTsid());
    }

    @Test
    public void testSetID(){
        testTimeSet.setTsid(999);
        assertEquals(999, testTimeSet.getTsid());
    }

    @Test
    public void testGetImage(){
        assertEquals(testImage, testTimeSet.getImage());
    }

    @Test
    public void testSetImage(){
        testTimeSet.setImage("NEW");
        assertEquals("NEW", testTimeSet.getImage());
    }
}
