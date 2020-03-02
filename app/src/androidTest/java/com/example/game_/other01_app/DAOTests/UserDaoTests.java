package com.example.game_.other01_app.DAOTests;

import android.content.Context;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.UserDao;
import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class UserDaoTests {
    @Mock
    private Observer<User> observer;
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private AppDatabase db;
    private UserDao userDao;
    private User TEST_USER;
    private static final User UPDATE_USER =
            new User(1, "UpdateName", "UpdateCareName", false, 0L, 0L, 0L, "low", "low", "", 0);

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

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDao = db.userDao();
        userDao.deleteAll();
    }

    @Before
    public void setUpUser(){
        TEST_USER =  new User(1, "TestName", "TestCareName", true, 0L, 0L, 0L, "low", "low", "", 0);
        try {
            testLastLogin = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        testLastLoginTime = testLastLogin.getTime();
        TEST_USER.setLast_log_in(testLastLoginTime);
        TEST_USER.setStreak(testStreak);
        TEST_USER.setTriedExercises(testTriedExercies);
        TEST_USER.setRecentHighestIntensity(testRecentHighest);
        TEST_USER.setBestTotalExerciseTime(testBestTotal);
        TEST_USER.setBestHighestIntensity(testLastHighest);
        TEST_USER.setRecentTotalExerciseTime(testRecentTotal);
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void testInsertion() throws InterruptedException {
        userDao.insert(TEST_USER);
        User actualUser = LiveDataTestUtil.getValue(userDao.getUser());
        assertNotNull(actualUser);
        assertEquals(TEST_USER.getUserName(), actualUser.getUserName());
    }

    @Test
    public void testReplacement() throws InterruptedException {
        userDao.insert(TEST_USER);
        userDao.insert(UPDATE_USER);
        User actualUser = LiveDataTestUtil.getValue(userDao.getUser());
        assertNotNull(actualUser);
        assertEquals(UPDATE_USER.getUserName(), actualUser.getUserName());
        assertEquals(UPDATE_USER.getCareName(), actualUser.getCareName());
        assertEquals(UPDATE_USER.isExerciseWith(), actualUser.isExerciseWith());
    }

    @Test
    public void testIncreaseTodaysTotal() throws InterruptedException {
        userDao.insert(TEST_USER);
        userDao.increaseTodaysTotal(1L);
        User actualUser = LiveDataTestUtil.getValue(userDao.getUser());
        assertNotNull(actualUser);
        assertEquals(TEST_USER.getUserName(), actualUser.getUserName());
        assertEquals(2L, actualUser.getRecentTotalExerciseTime().longValue());
    }
}
