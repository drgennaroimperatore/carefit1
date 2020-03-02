package com.example.game_.other01_app.DAOTests;

import android.content.Context;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.ReminderDao;
import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ReminderDaoTests {

    @Mock
    private Observer<List<Reminder>> observer;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private AppDatabase db;
    private ReminderDao reminderDao;
    private Reminder TEST_REMINDER;
    private Reminder UPDATE_REMINDER;
    private Reminder SECONDARY_TEST_REMINDER;

    @Before
    public void setUp() {

        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        reminderDao = db.reminderDao();
        reminderDao.deleteAll();
        TEST_REMINDER = getTestReminder();
        SECONDARY_TEST_REMINDER = getSecondaryReminder();
        UPDATE_REMINDER = getUpdateReminder();

    }

    @After
    public void tearDown() {
        db.close();
    }

    private Reminder getTestReminder(){
        Reminder reminder = new Reminder(
                10,
                15,
                "Monday"
        );
        reminder.setId(1);
        return reminder;
    }

    private Reminder getUpdateReminder(){
        Reminder reminder = new Reminder(
                15,
                15,
                "Monday"
        );
        reminder.setId(1);
        return reminder;
    }

    private Reminder getSecondaryReminder(){
        Reminder reminder = new Reminder(
                15,
                15,
                "Tuesday"
        );
        reminder.setId(2);
        return reminder;
    }

    @Test
    public void testInsertion() throws InterruptedException {
        reminderDao.insert(TEST_REMINDER);
        List<Reminder> reminders = LiveDataTestUtil.getValue(reminderDao.getAllReminders());
        assertNotNull(reminders);
        assertEquals(1, reminders.size());
        assertEquals(TEST_REMINDER.getRepeating(), reminders.get(0).getRepeating());
    }

    @Test
    public void testUpdate() throws InterruptedException {
        reminderDao.insert(TEST_REMINDER);
        reminderDao.insert(UPDATE_REMINDER);
        List<Reminder> reminders = LiveDataTestUtil.getValue(reminderDao.getAllReminders());
        assertNotNull(reminders);
        assertEquals(1, reminders.size());
        assertEquals(UPDATE_REMINDER.getTimeHrs(), reminders.get(0).getTimeHrs());
        assertEquals(UPDATE_REMINDER.getRepeating(), reminders.get(0).getRepeating());
    }

    @Test
    public void testIndividualDeletion() throws InterruptedException {
        reminderDao.insert(TEST_REMINDER);
        reminderDao.insert(SECONDARY_TEST_REMINDER);
        reminderDao.delete(TEST_REMINDER);
        List<Reminder> reminders = LiveDataTestUtil.getValue(reminderDao.getAllReminders());
        assertNotNull(reminders);
        assertEquals(1, reminders.size());
        assertEquals(SECONDARY_TEST_REMINDER.getId(), reminders.get(0).getId());
    }

    @Test
    public void testDeleteAll() throws InterruptedException {
        reminderDao.insert(TEST_REMINDER);
        reminderDao.insert(SECONDARY_TEST_REMINDER);
        reminderDao.deleteAll();
        List<Reminder> reminders = LiveDataTestUtil.getValue(reminderDao.getAllReminders());
        assertTrue(reminders.isEmpty());
    }
}
