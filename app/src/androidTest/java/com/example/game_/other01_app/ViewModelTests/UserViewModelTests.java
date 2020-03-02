package com.example.game_.other01_app.ViewModelTests;

import android.app.Application;

import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.LiveDataTestUtil;
import com.example.game_.other01_app.ViewModels.UserViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.platform.app.InstrumentationRegistry;

import static junit.framework.TestCase.assertEquals;

public class UserViewModelTests {

    UserViewModel userViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        userViewModel = new UserViewModel(
                (Application)
                        InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getApplicationContext()
        );
    }

    @Test
    public void testCreateOrUpdateUser() throws InterruptedException, ExecutionException {
        User testUser = new User( 1,
                "TEST_UN", "TEST_CN", true, System.currentTimeMillis(),
                0L, 0L, "low",
                "mid", "", 0
        );
        userViewModel.createOrUpdateUser(testUser);
        assertEquals(testUser.getUserName(), userViewModel.getUserNotLive().getUserName());
        assertEquals(testUser.getCareName(), userViewModel.getUserNotLive().getCareName());
        assertEquals(testUser.getLast_log_in(), userViewModel.getUserNotLive().getLast_log_in());
    }

    @Test
    public void testIncreaseTodaysTotal() throws ExecutionException, InterruptedException {
        User testUser = new User( 1,
                "TEST_UN", "TEST_CN", true, System.currentTimeMillis(),
                0L, 0L, "low",
                "mid", "", 0
        );
        userViewModel.createOrUpdateUser(testUser);
        userViewModel.increaseTodaysTotal(100L, "mid");
        User toTest = userViewModel.getUserNotLive();
        assertEquals(100L, toTest.getRecentTotalExerciseTime().longValue());
        assertEquals("mid", toTest.getRecentHighestIntensity());
    }
}
