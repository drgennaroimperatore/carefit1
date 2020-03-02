package com.example.game_.other01_app.ViewModelTests;

import android.app.Application;

import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.LiveDataTestUtil;
import com.example.game_.other01_app.ViewModels.ReminderViewModel;

import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.platform.app.InstrumentationRegistry;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ReminderViewModelTests {

    ReminderViewModel reminderViewModel = new ReminderViewModel(
            (Application)
                    InstrumentationRegistry.getInstrumentation()
                            .getTargetContext().getApplicationContext()
    );

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

//    @Test
//    public void testInsertAndDeleteReminder() throws InterruptedException {
//        CountDownLatch latch = new CountDownLatch(1);
//        Reminder reminder = new Reminder(
//                10,10,"Monday"
//        );
//        reminderViewModel.insert(reminder);
//        latch.await(1, TimeUnit.SECONDS);
//
//        List<Reminder> reminders = LiveDataTestUtil.getValue(reminderViewModel.getAllRemidners());
//        latch.await(1, TimeUnit.SECONDS);
//
//        assertEquals(1, reminders.size());
//
//        reminderViewModel.deleteReminder(reminders.get(0));
//        latch.await(1, TimeUnit.SECONDS);
//
//        List<Reminder> remindersAgain = LiveDataTestUtil.getValue(reminderViewModel.getAllRemidners());
//        latch.await(1, TimeUnit.SECONDS);
//        assertEquals(0, remindersAgain.size());
//    }
}
