package com.example.game_.other01_app.AssistanceClasses;

import com.example.game_.other01_app.Activities.DashboardActivity;
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

    public static String convertDateToPreferredFormat(Date date)
    {
        SimpleDateFormat ddmmyyytFormat = new SimpleDateFormat("dd/MM/yy");
        return ddmmyyytFormat.format(date.getTime());

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

    public static boolean isDateInCurrentWeek(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return week == targetWeek && year == targetYear;
    }

    public static boolean isDateInNextWeek(Date date)
    {
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.add(Calendar.DAY_OF_MONTH,7);
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return week == targetWeek && year == targetYear;
    }

    public static boolean isDateToday(Date date)
    {
        Calendar currentCalendar = Calendar.getInstance();
        int day = currentCalendar.get(Calendar.DAY_OF_MONTH);
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int month = currentCalendar.get(Calendar.MONTH);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetDay = targetCalendar.get(Calendar.DAY_OF_MONTH);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetMonth = currentCalendar.get(Calendar.MONTH);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return  day== targetDay && targetMonth == month && week == targetWeek && year == targetYear;

    }

    public static boolean isBefore(Date date)
    {
        Calendar currentCalendar = Calendar.getInstance();
        int day = currentCalendar.get(Calendar.DAY_OF_MONTH);
        int month = currentCalendar.get(Calendar.MONTH);
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetDay = targetCalendar.get(Calendar.DAY_OF_MONTH);
        int targetMonth = targetCalendar.get(Calendar.MONTH);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        if(year > targetYear)
        return true;
        if(year== targetYear)
        {
            if(month > targetMonth)
                return true;
            else
            if(month== targetMonth)
                return day > targetDay;
            else
                return false;

        }
        return false;
    }

    public static boolean isAfter(Date date)
    {
        Calendar currentCalendar = Calendar.getInstance();
        int day = currentCalendar.get(Calendar.DAY_OF_MONTH);
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int month = currentCalendar.get(Calendar.MONTH);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetDay = targetCalendar.get(Calendar.DAY_OF_MONTH);
        int targetMonth = targetCalendar.get(Calendar.MONTH);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);

        if(year < targetYear)
            return true;
        if(year== targetYear)
        {
            if(month < targetMonth)
                return true;
            else
                if(month== targetMonth)
                    return day < targetDay;
                else
                    return false;

        }
        return false;
    }

    public static Date getDateForNextWeek(Date date)
    {
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(date);
        currentCalendar.add(Calendar.DAY_OF_MONTH,7);
        return currentCalendar.getTime();
    }



    public static boolean isDateInPastWeek(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return ((week-targetWeek)==1) && year == targetYear;
    }

    public static Date getWeekDayDate(Date date,int day)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(day);
        c.set(Calendar.DAY_OF_WEEK, day);
        return c.getTime();
    }

    public static Date[] getWeekDates(Date startDate) {

        Date[] dates = new Date[6];
        Calendar c = Calendar.getInstance();

        //c.setFirstDayOfWeek( c.get(Calendar.DAY_OF_WEEK));
        c.setTime(startDate);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        for (int i = 0; i < 6; i++) {
            dates[i] = c.getTime();
            c.add(Calendar.DATE, 1);
        }

        return dates;
    }


}
