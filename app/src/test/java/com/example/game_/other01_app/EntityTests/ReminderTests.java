package com.example.game_.other01_app.EntityTests;

import android.os.Parcel;

import com.example.game_.other01_app.Database.entities.Reminder;

import net.bytebuddy.build.ToStringPlugin;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ReminderTests {
    private static final int testHrs = 2;
    private static final int testMins = 3;
    private static final String testRepeating = "test";

    private Reminder testReminder;

    @Before
    public void setUp(){
        testReminder = new Reminder(testHrs, testMins, testRepeating);
    }

    @Test
    public void testGetId(){
        assertEquals(0, testReminder.getId());
    }

    @Test
    public void testSetId(){
        testReminder.setId(1);
        assertEquals(1, testReminder.getId());
    }

    @Test
    public void testGetTimeHrs(){
        assertEquals(testHrs, testReminder.getTimeHrs());
    }

    @Test
    public void testGetTimeMins(){
        assertEquals(testMins, testReminder.getTimeMins());
    }

    @Test
    public void testGetRepeating(){
        assertEquals(testRepeating, testReminder.getRepeating());
    }

    @Test
    public void testDescribeContents(){
        assertEquals(0, testReminder.describeContents());
    }


}
