package com.example.game_.other01_app.EntityTests;

import android.os.Parcel;

import com.example.game_.other01_app.Database.entities.Reminder;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ReminderInstrumentationTests {

    private static final int testHrs = 2;
    private static final int testMins = 3;
    private static final String testRepeating = "test";

    private Reminder testReminder;

    @Before
    public void setUp(){
        testReminder = new Reminder(testHrs, testMins, testRepeating);
    }

    @Test
    public void testReminderParceable() {
        Parcel testParcel = Parcel.obtain();
        testReminder.writeToParcel(testParcel, 0);
        testParcel.setDataPosition(0);
        Reminder createdFromParcel = Reminder.CREATOR.createFromParcel(testParcel);
        assertEquals(testReminder.getId(), createdFromParcel.getId());
        assertEquals(testReminder.getTimeHrs(), createdFromParcel.getTimeHrs());
        assertEquals(testReminder.getTimeMins(), createdFromParcel.getTimeMins());
        assertEquals(testReminder.getRepeating(), createdFromParcel.getRepeating());
    }
}
