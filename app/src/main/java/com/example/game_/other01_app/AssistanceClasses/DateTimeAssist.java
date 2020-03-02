package com.example.game_.other01_app.AssistanceClasses;

import com.example.game_.other01_app.Database.entities.Reminder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateTimeAssist {

    public static String getCurrentDateInddMMyyyyFormat(){
        SimpleDateFormat ddmmyyytFormat = new SimpleDateFormat("dd-MM-yyyy");
        return ddmmyyytFormat.format(System.currentTimeMillis());
    }

    public static String getCurrentWeekday() {
        SimpleDateFormat weekdayFormat = new SimpleDateFormat("EEEE");
        return weekdayFormat.format(Calendar.getInstance().getTime());
    }

    private static Date getTimeFromString(String oldTime) throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
        return timeFormat.parse(oldTime);
    }

    public static int getHourFromString(String oldTime) throws ParseException {
        Date time = getTimeFromString(oldTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        //hour of day SHOULD activate the AM and PM on the clock :)
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public static int getMinuteFromString(String oldTime) throws ParseException {
        Date time = getTimeFromString(oldTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int minute = calendar.get(Calendar.MINUTE);
        return minute;
    }

    public static long getCalendarTimeFromReminder(Reminder reminder, String day) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, dayToInt(day));
        cal.set(Calendar.HOUR_OF_DAY, reminder.getTimeHrs());
        cal.set(Calendar.MINUTE, reminder.getTimeMins());
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static int dayToInt(String day) {
        switch (day){
            case "Monday":
                return Calendar.MONDAY;
            case "Tuesday":
                return Calendar.TUESDAY;
            case "Wednesday":
                return Calendar.WEDNESDAY;
            case "Thursday":
                return Calendar.THURSDAY;
            case "Friday":
                return Calendar.FRIDAY;
            case "Saturday":
                return Calendar.SATURDAY;
            case "Sunday":
                return Calendar.SUNDAY;
        }
        return 0;
    }

    public static String longToTimerString(long timeInMillis){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss:SSS");
        return simpleDateFormat.format(new Date(timeInMillis));
    }
}
