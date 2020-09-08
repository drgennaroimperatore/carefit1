package com.example.game_.other01_app.ViewModels;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.AssistanceClasses.ListAssist;
import com.example.game_.other01_app.AssistanceClasses.StringsAssist;
import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.Receivers.ReminderBootReceiver;
import com.example.game_.other01_app.Receivers.ReminderReceiver;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

public class AlarmCreator {

    public static final String ALARM_ID = "ALARM_ID";
    private static final String TAG = "";

    /**
     * Creates an alarm for every weekday the Reminder is set for
     * @param reminder
     * @param context
     * @throws ParseException
     */
    public static void createReminder(Reminder reminder, Context context) throws ParseException {

        //Enables the boot receiver since some kind of alarm will be set
        ComponentName receiver = new ComponentName(context, ReminderBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
/*        List<String> days = ListAssist.convertStringToListOf(reminder.getRepeating());
        for(String day : days){
            setWeeklyAlarm(reminder, context, day);
        }*/
    }

    /**
     * Creates deletes all alarms created by the Reminder
     * @param reminder
     * @param context
     */
    public static void deleteReminder(Reminder reminder, Context context) {
        ComponentName receiver = new ComponentName(context, ReminderBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
      /*  List<String> days = ListAssist.convertStringToListOf(reminder.getRepeating());
        for(String day : days){
            deleteWeeklyAlarm(reminder, context, day);
        }*/
    }

    public static void deleteWeeklyAlarm(Reminder reminder, Context context, String day){
        //Disables the receiver for this alarm
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
        /*final int _id = StringsAssist.concatInts(reminder.getTimeHrs(),
                reminder.getTimeMins(), DateTimeAssist.dayToInt(day));*/
        Log.d(TAG, "deleteWeeklyAlarm() called with: reminder = [" + reminder + "], context = [" + context + "], day = [" + day + "]");
      //  Log.d(TAG, "deleteWeeklyAlarm: id" + _id);
      //  PendingIntent deleteIntent = PendingIntent.getBroadcast(context, _id, intent, 0);
      //  alarmManager.cancel(deleteIntent);
     //   deleteIntent.cancel();
    }

    private static void setWeeklyAlarm(Reminder reminder, Context context) throws ParseException {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
       /* final int _id = StringsAssist.concatInts(reminder.getTimeHrs(),
                reminder.getTimeMins() ,DateTimeAssist.dayToInt(day));*/
       // Log.d(TAG, "setWeeklyAlarm() called with: reminder = [" + reminder + "], context = [" + context + "], day = [" + day + "]");
       // Log.d(TAG, "setWeeklyAlarm: id = " + _id);
       // intent.putExtra(ALARM_ID, _id);
      //  PendingIntent alarmIntent = PendingIntent.getBroadcast(context,_id, intent, 0);
        long alarmTime = 0;
        if(DateTimeAssist.getCalendarTimeFromReminder(reminder) <= Calendar.getInstance().getTimeInMillis()){
            alarmTime = DateTimeAssist.getCalendarTimeFromReminder(reminder) + (AlarmManager.INTERVAL_DAY * 7);
        } else {
            alarmTime = DateTimeAssist.getCalendarTimeFromReminder(reminder);
        }
      alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTime,
                alarmIntent);
    }

    /**
     * Resets the alarm for the next week when it goes off
     * @param _id
     * @param context
     */
    public static void resetWeeklyAlarm(int _id, Context context){
        Intent repeatIntent = new Intent(context, ReminderReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context,_id,repeatIntent,0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + (AlarmManager.INTERVAL_DAY * 7),
                pendingIntent);
    }

}
