package com.example.game_.other01_app.EntityTests;

import android.os.Parcel;

import com.example.game_.other01_app.Database.entities.User;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class UserTests {

    private static final int testId = 1;
    private static final String testUserName = "testUserName";
    private static final String testCareName = "testCareName";
    private static final boolean testExerciseWith = true;

    private static final Long testRecentTotal = 1L;
    private static final Long testBestTotal = 2L;
    private static final String testLastHighest = "testLastHighest";
    private static final String testRecentHighest = "testRecentHighest";
    private static final String testTriedExercies = "testtriedExercises";
    private static final int testStreak = 101;

    private final String date = "01/01/2000 01:01:01";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "dd/MM/yyyy hh:mm:ss"
    );
    private Date testLastLogin;
    private Long testLastLoginTime;

    private User testUser;

    @Before
    public void setUp(){
        testUser = new User(testId, testUserName, testCareName, testExerciseWith,
                0L, 0L, 0L,
                "low", "low", "", 0);
        try {
            testLastLogin = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        testLastLoginTime = testLastLogin.getTime();
    }

    @Test
    public void testGetId(){
        assertEquals(testId, testUser.getId());
    }

    @Test
    public void testSetId(){
        testUser.setId(2);
        assertEquals(2, testUser.getId());
    }

    @Test
    public void testGetUserName(){
        assertEquals(testUserName, testUser.getUserName());
    }

    @Test
    public void testSetUserName(){
        testUser.setUserName("NEW");
        assertEquals("NEW", testUser.getUserName());
    }

    @Test
    public void testGetCareName(){
        assertEquals(testCareName, testUser.getCareName());
    }

    @Test
    public void testSetCareName(){
        testUser.setCareName("NEW");
        assertEquals("NEW", testUser.getCareName());
    }

    @Test
    public void testIsExerciseWith(){
        assertEquals(testExerciseWith, testUser.isExerciseWith());
    }

    @Test
    public void testSetExerciseWith(){
        testUser.setExerciseWith(false);
        assertEquals(false, testUser.isExerciseWith());
    }

    @Test
    public void testGetSetLastLogin(){
        testUser.setLast_log_in(testLastLogin);
        assertEquals(testLastLoginTime, testUser.getLast_log_in());
    }

    @Test
    public void testGetSetLastLoginAsDate(){
        testUser.setLast_log_in(testLastLoginTime);
        assertEquals(testLastLogin, testUser.getLast_log_in_as_Date());
    }

    @Test
    public void testGetSetRecentTotal(){
        testUser.setRecentTotalExerciseTime(testRecentTotal);
        assertEquals(testRecentTotal, testUser.getRecentTotalExerciseTime());
    }

    @Test
    public void testGetSetBestTotal(){
        testUser.setBestTotalExerciseTime(testBestTotal);
        assertEquals(testBestTotal, testUser.getBestTotalExerciseTime());
    }

    @Test
    public void testGetSetBestHighestIntensity(){
        testUser.setBestHighestIntensity(testLastHighest);
        assertEquals(testLastHighest, testUser.getBestHighestIntensity());
    }


    @Test
    public void testGetSetRecentHighestIntensity(){
        testUser.setRecentHighestIntensity(testRecentHighest);
        assertEquals(testRecentHighest, testUser.getRecentHighestIntensity());
    }

    @Test
    public void testGetSetTriedExercises(){
        testUser.setTriedExercises(testTriedExercies);
        assertEquals(testTriedExercies, testUser.getTriedExercises());
    }

    @Test
    public void testGetSetDaysInARow(){
        testUser.setStreak(testStreak);
        assertEquals(testStreak, testUser.getStreak());
    }


}
