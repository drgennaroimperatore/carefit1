package com.example.game_.other01_app.ViewModelUnitTests;

import com.example.game_.other01_app.AssistanceClasses.ListAssist;
import com.example.game_.other01_app.Database.entities.Category;
import com.example.game_.other01_app.Database.entities.TimeSet;
import com.example.game_.other01_app.NonDBObjects.Messages;
import com.example.game_.other01_app.ViewModels.CategoriesViewModel;
import com.example.game_.other01_app.ViewModels.MessagesViewModel;
import com.example.game_.other01_app.ViewModels.TimeSetViewModel;
import com.example.game_.other01_app.ViewModels.UserViewModel;

import org.junit.Before;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class MessagesViewModelTests {
    private MessagesViewModel messagesViewModel;
    private UserViewModel userViewModel;
    private TimeSetViewModel timeSetViewModel;
    private CategoriesViewModel categoriesViewModel;

    private List<TimeSet> timeSetList;
    SimpleDateFormat ddmmyyytFormat = new SimpleDateFormat("dd-MM-yyyy");

    private List<String> testTried = new ArrayList<>();
    private List<String> testMostRecent = new ArrayList<>();


    @Before
    public void setUp(){
        userViewModel = mock(UserViewModel.class);
        timeSetViewModel = mock(TimeSetViewModel.class);
        categoriesViewModel = mock(CategoriesViewModel.class);
        messagesViewModel = new MessagesViewModel(userViewModel, timeSetViewModel, categoriesViewModel);
    }

    @Before
    public void setUpLists(){
        testTried.add("A");
        testTried.add("B");
        testTried.add("C");

        testMostRecent.add("B");
        testMostRecent.add("C");
        testMostRecent.add("D");
    }

    @Before
    public void setUpTimeSetList(){
        timeSetList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 1, 1);
        TimeSet testTimeSetA = new TimeSet("A", 10000,
                11000,
                ddmmyyytFormat.format(calendar.getTime()), "A");

        calendar.set(2000, 2, 1);
        TimeSet testTimeSetB = new TimeSet("B", 10000,
                11000,
                ddmmyyytFormat.format(calendar.getTime()), "B");

        calendar.set(2001, 2,1);
        TimeSet testTimeSetC = new TimeSet("C", 10000,
                11000,
                ddmmyyytFormat.format(calendar.getTime()), "C");

        timeSetList.add(testTimeSetA);
        timeSetList.add(testTimeSetB);
        timeSetList.add(testTimeSetC);
    }

    List<TimeSet> improvedTimesets;

    @Before
    public void setUpImprovedTimesetList(){
        improvedTimesets = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 1, 1);
        TimeSet testTimeSetA = new TimeSet("A", 10000,
                11000,
                ddmmyyytFormat.format(calendar.getTime()), "A");

        calendar.set(2000, 2, 1);
        TimeSet testTimeSetB = new TimeSet("B", 10000,
                11000,
                ddmmyyytFormat.format(calendar.getTime()), "B");

        calendar.set(2001, 2,1);
        TimeSet testTimeSetC = new TimeSet("C", 11000,
                10000,
                ddmmyyytFormat.format(calendar.getTime()), "C");

        improvedTimesets.add(testTimeSetA);
        improvedTimesets.add(testTimeSetB);
        improvedTimesets.add(testTimeSetC);
    }

    @Test
    public void checkGetImprovedTimes(){
        assertFalse(messagesViewModel.getExercisesWithImprovedTimes(
                messagesViewModel.findMostRecentlyTriedExercises(improvedTimesets), improvedTimesets)
                .isEmpty());
        assertEquals(1, messagesViewModel.getExercisesWithImprovedTimes(
                messagesViewModel.findMostRecentlyTriedExercises(improvedTimesets), improvedTimesets)
                .size());
    }

    @Test
    public void testCheckForDateStreakTrue(){
        Long time = System.currentTimeMillis() - MILLISECONDS.convert(24, HOURS);
        Date dateFromTime = new Date(time);
        assertTrue(messagesViewModel.checkForDateStreak(dateFromTime));
    }

    @Test
    public void testCheckForDateStreakFalse(){
        Long time = System.currentTimeMillis() - MILLISECONDS.convert(2, DAYS);
        Date dateFromTime = new Date(time);
        assertFalse(messagesViewModel.checkForDateStreak(dateFromTime));
    }

    @Test
    public void checkOneWeekdayApartSatSun(){
        assertTrue(messagesViewModel.oneWeekdayApart(Calendar.SUNDAY, Calendar.SATURDAY));
    }

    @Test
    public void testForDateStreakAWeekApart(){
        Long time = System.currentTimeMillis() - MILLISECONDS.convert(8, DAYS);
        Date dateFromTime = new Date(time);
        assertFalse(messagesViewModel.checkForDateStreak(dateFromTime));
    }

    @Test
    public void testFindMostRecentlyTriedExercises(){
        assertEquals(1, messagesViewModel.findMostRecentlyTriedExercises(timeSetList).size());
        assertTrue(messagesViewModel.findMostRecentlyTriedExercises(timeSetList).contains("C"));
    }

    @Test
    public void testFindMostRecentlyTriedExercisesReturnsMultiple(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, 2,1);
        TimeSet testTimeSetD = new TimeSet("D", 10000,
                11000,
                ddmmyyytFormat.format(calendar.getTime()), "D");
        timeSetList.add(testTimeSetD);
        assertEquals(2, messagesViewModel.findMostRecentlyTriedExercises(timeSetList).size());
        assertTrue(messagesViewModel.findMostRecentlyTriedExercises(timeSetList).contains("C"));
        assertTrue(messagesViewModel.findMostRecentlyTriedExercises(timeSetList).contains("D"));
    }

    @Test
    public void testGetUntriedExercises(){
        assertEquals(1, messagesViewModel.getUntriedExercises(testTried, testMostRecent).size());
        assertTrue(messagesViewModel.getUntriedExercises(testTried, testMostRecent).contains("D"));
    }

    @Test
    public void testGetImprovedTimes(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, 2,1);
        TimeSet testTimeSetD = new TimeSet("D", 12000,
                11000,
                ddmmyyytFormat.format(calendar.getTime()), "D");
        timeSetList.add(testTimeSetD);

        List<String> improvedMostRecent = new ArrayList<>();
        improvedMostRecent.addAll(testMostRecent);
       // when(timeSetViewModel.getAllTimeSets().getValue()).thenReturn(timeSetList);
        List<String> exerciseStrings = new ArrayList<>();
        exerciseStrings.add("Ankle Stretch");
        List<TimeSet> result = messagesViewModel.getExercisesWithImprovedTimes(improvedMostRecent, timeSetList);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("D", result.get(0).getExercisename());
    }

    @Test
    public void testGreaterIntensityLowMid(){
        assertTrue(messagesViewModel.greaterIntensityThanTheLast("low","mid"));
    }

    @Test
    public void testGreaterIntensityMidHigh(){
        assertTrue(messagesViewModel.greaterIntensityThanTheLast("mid","high"));
    }

    @Test
    public void testGreaterIntensityHighHigh(){
        assertFalse(messagesViewModel.greaterIntensityThanTheLast("high","high"));
    }

    @Test
    public void testAtLeastADayHasNotPaseed(){
        assertFalse(messagesViewModel.atLeastADayHasPassed(System.currentTimeMillis()));
    }

    @Test
    public void testGetSuitableCategories(){
        Category[] categories = new Category[] {
                new Category(1, "Arms", false),
                new Category(2, "Legs", false),
                new Category(3, "Back", false),
                new Category(4, "Sitting", false),
                new Category(5, "Strength", false),
                new Category(6, "Flexibility", false),
                new Category(7, "Balance", false),
                new Category(8, "Aerobic", false),
                new Category(9, "Stretch", true)
        };
        List<Category> categoryList = Arrays.asList(categories);
        List<String> suitableCategories = messagesViewModel.getSuitableCategories(categoryList);
        assertTrue(suitableCategories.contains("Stretch"));
        assertEquals(1, suitableCategories.size());
    }

    @Test
    public void testGetSuitableMessages(){
        String[] suitableCategories = new String[]{"Strength"};
        List<Messages> allMessages = Arrays.asList(Messages.getAllMessages());
        List<String> allSuitableMessages = new ArrayList<>();
        for(Messages messages : allMessages) {
            if (ListAssist.convertStringToListOf(messages.getCategories()).contains("All") ||
            ListAssist.convertStringToListOf(messages.getCategories()).contains("Strength")){
                allSuitableMessages.add(messages.getMessage());
            }
        }
        assertEquals(allSuitableMessages.size(), messagesViewModel.getAllSuitableMessages(Arrays.asList(suitableCategories),false).size());
    }

    @Test
    public void testGetTogetherMessages(){
        List<String> suitableCategories = new ArrayList<>();
        List<Messages> allMessages = Arrays.asList(Messages.getAllMessages());
        List<String> allSuitableMessages = new ArrayList<>();
        for(Messages messages : allMessages) {
            if(ListAssist.convertStringToListOf(messages.getCategories()).contains("All") ||
                    ListAssist.convertStringToListOf(messages.getCategories()).contains("Together")){
                allSuitableMessages.add(messages.getMessage());
            }
        }
        assertEquals(allSuitableMessages.size(), messagesViewModel.getAllSuitableMessages(suitableCategories,true).size());
    }

}
