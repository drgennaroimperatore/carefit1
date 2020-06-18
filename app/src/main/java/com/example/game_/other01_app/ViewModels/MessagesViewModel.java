package com.example.game_.other01_app.ViewModels;


import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.AssistanceClasses.ListAssist;
import com.example.game_.other01_app.Database.entities.Category;
import com.example.game_.other01_app.NonDBObjects.Messages;
import com.example.game_.other01_app.Database.entities.TimeSet;
import com.example.game_.other01_app.Database.entities.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class MessagesViewModel {
    private UserViewModel mUserViewModel;
    private TimeSetViewModel mTimeSetViewModel;
    private CategoriesViewModel categoriesViewModel;
    private User user;
    private List<TimeSet> allTimeSets;
    private List<String> praiseList;

    public MessagesViewModel(UserViewModel mUserViewModel, TimeSetViewModel mTimeSetViewModel, CategoriesViewModel categoriesViewModel) {
        this.mUserViewModel = mUserViewModel;
        this.mTimeSetViewModel = mTimeSetViewModel;
        this.categoriesViewModel = categoriesViewModel;
        try {
            user = mUserViewModel.getUserNotLive();
           /* if(user==null)
            {
                mUserViewModel.createOrUpdateUser(user);
            }*/

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            allTimeSets = mTimeSetViewModel.getAllTimeSetsNotLive();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        praiseList = new ArrayList<>();
    }

    /**
     * Checks that at least a day has passed since last log in
     * @param lastLogin
     * @return
     */
    public boolean atLeastADayHasPassed(Long lastLogin){
        Date dbDate = new Date(lastLogin);
        Date currentDate = new Date(System.currentTimeMillis());
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(currentDate);
        Calendar dbCal = Calendar.getInstance();
        dbCal.setTime(dbDate);

        if(currentCal.get(Calendar.DAY_OF_MONTH) > dbCal.get(Calendar.DAY_OF_MONTH) &&
        currentDate.getTime() > dbDate.getTime()){
            return true;
        } else if(currentCal.get(Calendar.MONTH) > dbCal.get(Calendar.MONTH) &&
                currentDate.getTime() > dbDate.getTime()) {
            return true;
        } else if(currentCal.get(Calendar.YEAR) > dbCal.get(Calendar.YEAR)) {
            return true;
        }

        return false;
    }

    /**
     * Checks for users achievements. Creates notification if day has passed.
     * @return
     */
    public boolean buildNotification(){

        if(user==null)
            return false;
        if(atLeastADayHasPassed(user.getLast_log_in().longValue())) {
            if (increasedTotalExerciseTime()) {
                praiseList.add("Yesterday you improved your total exercise time by:  " +
                        DateTimeAssist.longToTimerString(
                        ((user.getRecentTotalExerciseTime() + user.getBestTotalExerciseTime())
                                - user.getBestTotalExerciseTime() ) ) );

            } else if (!getExercisesWithImprovedTimes(findMostRecentlyTriedExercises(allTimeSets), allTimeSets).isEmpty()) {
                String improvedExercises = "";
                for(TimeSet timeSet :
                        getExercisesWithImprovedTimes(findMostRecentlyTriedExercises(allTimeSets), allTimeSets)){
                    improvedExercises += timeSet.getExercisename();
                    improvedExercises += ", ";
                }
                praiseList.add("You improved your times on the following exercises: " +
                        improvedExercises + "well done!");

            } else if (greaterIntensityThanTheLast(user.getBestHighestIntensity(), user.getRecentHighestIntensity())) {

                praiseList.add("You tried a higher intensity of exercise than you did last time!");

            } else if (!getUntriedExercises(ListAssist.convertStringToListOf(user.getTriedExercises()),
                    findMostRecentlyTriedExercises(allTimeSets)).isEmpty()) {

                praiseList.add("Last time, you tried exercises you hadn't before! Including: "
                + ListAssist.convertListToString(getUntriedExercises(ListAssist.convertStringToListOf(user.getTriedExercises()),
                        findMostRecentlyTriedExercises(allTimeSets))));

            } else if (checkForDateStreak(user.getLast_log_in_as_Date())) {
                praiseList.add("You've been using CareFit to keep fit for: " + user.getStreak() +
                        " days");
            } else {
                praiseList.add("Keep it up, let's get back into exercising.");
            }
            String tip = getRandomSuitableTip(categoriesViewModel.getAllCategoriesNotLive(), user.isExerciseWith());
            praiseList.add(tip);
            databaseAdmin();
            return true;
        }
        user.setLast_log_in(System.currentTimeMillis());
        mUserViewModel.createOrUpdateUser(user);
        return false;
    }

    public List<String> getSuitableCategories(List<Category> categories){
        List<String> suitableCategories = new ArrayList<>();
        for (Category category : categories){
            if(category.isInterested()){
                suitableCategories.add(category.getCategory());
            }
        }
        return suitableCategories;
    }


    public List<String> getAllSuitableMessages(List<String> suitableCategories, boolean exerciseWith){
        List<String> suitableMessages = new ArrayList<>();
        for (Messages message : Messages.getAllMessages()){
            List<String> messageCategories = ListAssist.convertStringToListOf(message.getCategories());
            boolean found = false;
            if(messageCategories.contains("All")){
                suitableMessages.add(message.getMessage());
                found = true;
            }
            if(!found && exerciseWith){
                if (messageCategories.contains("Together")){
                    suitableMessages.add(message.getMessage());
                    found = true;
                }
            }
            for (String catName : suitableCategories){
                if(!found){
                    if(messageCategories.contains(catName)){
                        suitableMessages.add(message.getMessage());
                        found = true;
                    }
                }
            }
        }
        return suitableMessages;
    }

    /**
     * Selects one random tip from Messages.java that fits the users preferences
     * @param categories
     * @param exerciseWith
     * @return
     */
    private String getRandomSuitableTip(List<Category> categories, boolean exerciseWith) {
        List<String> suitableCategories = getSuitableCategories(categories);
        List<String> suitableMessages = getAllSuitableMessages(suitableCategories, exerciseWith);
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(suitableMessages.size());
        return suitableMessages.get(index);
    }

    /**
     * Cleans up the all 'recent' database information
     */
    private void databaseAdmin() {

        if(checkForDateStreak(user.getLast_log_in_as_Date())){
            user.setStreak(user.getStreak() + 1);
        } else {
            user.setStreak(0);
        }
        mTimeSetViewModel.postMessageAdmin(getExercisesWithImprovedTimes(findMostRecentlyTriedExercises(allTimeSets), allTimeSets));
        user.setRecentTotalExerciseTime(0L);
        user.setLast_log_in(System.currentTimeMillis());
        user.setRecentHighestIntensity("low");
        user.setTriedExercises(user.getTriedExercises() + "," +  ListAssist.convertListToString(getUntriedExercises
                (ListAssist.convertStringToListOf(user.getTriedExercises()),
                findMostRecentlyTriedExercises(allTimeSets))));
        mUserViewModel.createOrUpdateUser(user);
    }

    /**
     * Checks how many days in a row the user has been using the app
     * @param dbDate
     * @return
     */
    public boolean checkForDateStreak(Date dbDate){
        Date currentDate = new Date(System.currentTimeMillis());
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(currentDate);
        Calendar dbCal = Calendar.getInstance();
        dbCal.setTime(dbDate);
        int currentDay = currentCal.get(Calendar.DAY_OF_WEEK);
        int dbDay = dbCal.get(Calendar.DAY_OF_WEEK);

        if(oneWeekdayApart(currentDay, dbDay)
        && notTooMuchTimeHasPassed(currentDate, dbDate)){
            return true;
        }

        return false;
    }

    public boolean oneWeekdayApart(int currentDay, int dbDay){
        if(dbDay == Calendar.SATURDAY){
            if(currentDay == Calendar.SUNDAY){
                return true;
            }
        } else if(currentDay == dbDay + 1) {
            return true;
        }
        return false;
    }

    private boolean notTooMuchTimeHasPassed(Date currentDate, Date dbDate){
        long MAX_DURATION = MILLISECONDS.convert(48, HOURS);
        long duration = currentDate.getTime() - dbDate.getTime();
        return duration < MAX_DURATION;
    }

    private Date stringToDate(String dateString){
        SimpleDateFormat ddmmyyytFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return ddmmyyytFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Examines the last_accessed data and finds the most recently tried exercises in the database
     * @param timeSets
     * @return
     */
    public List<String> findMostRecentlyTriedExercises(List<TimeSet> timeSets){
        String mostRecentDateString = "placeHolderString";
        long mostRecentDateTime = Long.MAX_VALUE;
        for(TimeSet timeSet : timeSets){
            if(!timeSet.getLastaccessed().equals("never")){
                Date lastDate = stringToDate(timeSet.getLastaccessed());
                long timeBetween = System.currentTimeMillis() -  lastDate.getTime();
                if(timeBetween < mostRecentDateTime){
                    mostRecentDateString = timeSet.getLastaccessed();
                    mostRecentDateTime = timeBetween;
                }
            }
        }

        List<String> mostRecentExercises = new ArrayList<>();
        for (TimeSet timeSet : timeSets){
            if (timeSet.getLastaccessed().equals(mostRecentDateString)){
                mostRecentExercises.add(timeSet.getExercisename());
            }
        }

        return mostRecentExercises;
    }

    /**
     * Checks for
     * @param tried
     * @param mostRecent
     * @return
     */
    public List<String> getUntriedExercises(List<String> tried,
                                            List<String> mostRecent){
        List<String> untried = new ArrayList<>();
        for (String exercise : mostRecent){
            if(!tried.contains(exercise)){
                untried.add(exercise);
            }
        }
        return untried;
    }

    private boolean increasedTotalExerciseTime(){
        if(user.getRecentTotalExerciseTime()
                > user.getBestTotalExerciseTime()){
            user.setBestTotalExerciseTime(user.getRecentTotalExerciseTime());
            return true;
        }
        return false;
    }

    public boolean greaterIntensityThanTheLast(String lastHighest,
                                               String recentHighest){
        if(lastHighest.equals("low")){
            if(recentHighest.equals("mid") || recentHighest.equals("high")){
                return true;
            }
        } else if(lastHighest.equals("mid")){
            if(recentHighest.equals("high")){
                return true;
            }
        }
        return false;
    }

    public List<TimeSet> getExercisesWithImprovedTimes(List<String> mostRecentExercises, List<TimeSet> allTimeSets){
        List<TimeSet> timeSetsToExamine = new ArrayList<>();
        for(TimeSet timeSet : allTimeSets){
            if(mostRecentExercises.contains(timeSet.getExercisename())){
                timeSetsToExamine.add(timeSet);
            }
        }
        List<TimeSet> improved = new ArrayList<>();
        for (TimeSet toExamine : timeSetsToExamine){
            if(toExamine.getRecentTime() > toExamine.getBestTime()){
                improved.add(toExamine);
            }
        }
        return improved;
    }

    public List<String> getPraiseList(){
        return praiseList;
    }

}
