package com.example.game_.other01_app.AssistanceTests;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.AssistanceClasses.StringsAssist;
import com.example.game_.other01_app.Database.entities.Reminder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(JUnit4.class)
public class DateTimeAssistTests {

    @Test
    public void testGetHourFromString() throws ParseException {
        assertEquals(22, DateTimeAssist.getHourFromString("22:11"));
    }

   @Test
    public void testGetMinuteFromString() throws ParseException {
        assertEquals(22, DateTimeAssist.getMinuteFromString("11:22"));
   }

   @Test
    public void testGetCalendarTimeFromReminder() throws ParseException {
       Reminder testReminder = new Reminder(11,22, "Monday");
       Calendar testCalendar = Calendar.getInstance();
       testCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
       testCalendar.set(Calendar.HOUR_OF_DAY, 11);
       testCalendar.set(Calendar.MINUTE, 22);
       testCalendar.set(Calendar.MILLISECOND, 0);
       assertEquals(testCalendar.getTimeInMillis(),
               DateTimeAssist.getCalendarTimeFromReminder(testReminder, "Monday"));
   }

   @Test
    public void testDayToInts(){
        assertEquals(Calendar.MONDAY, DateTimeAssist.dayToInt("Monday"));
        assertEquals(Calendar.TUESDAY, DateTimeAssist.dayToInt("Tuesday"));
        assertEquals(Calendar.WEDNESDAY, DateTimeAssist.dayToInt("Wednesday"));
        assertEquals(Calendar.THURSDAY, DateTimeAssist.dayToInt("Thursday"));
        assertEquals(Calendar.FRIDAY, DateTimeAssist.dayToInt("Friday"));
        assertEquals(Calendar.SATURDAY, DateTimeAssist.dayToInt("Saturday"));
        assertEquals(Calendar.SUNDAY, DateTimeAssist.dayToInt("Sunday"));
        assertEquals(0, DateTimeAssist.dayToInt("Purple"));
   }

   @Test
    public void testGetcurrentDateDDMMYYYY(){
       SimpleDateFormat ddmmyyytFormat = new SimpleDateFormat("dd-MM-yyyy");
       String expected = ddmmyyytFormat.format(System.currentTimeMillis());
       assertEquals(expected, DateTimeAssist.getCurrentDateInddMMyyyyFormat());
   }

   @Test
    public void testGetCurrentWeekday(){
       List<String> dowList = new ArrayList<>();
       dowList.add("Monday");
       dowList.add("Tuesday");
       dowList.add("Wednesday");
       dowList.add("Thursday");
       dowList.add("Friday");
       dowList.add("Saturday");
       dowList.add("Sunday");
       assertTrue(dowList.contains(DateTimeAssist.getCurrentWeekday()));
   }

   @Test
    public void testLongTotimerString(){
        assertEquals("05:05:005", DateTimeAssist.longToTimerString(305005));
   }

}
