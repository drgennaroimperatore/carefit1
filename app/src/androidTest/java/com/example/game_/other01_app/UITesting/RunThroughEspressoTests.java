package com.example.game_.other01_app.UITesting;

import android.view.View;
import android.widget.TimePicker;

import com.example.game_.other01_app.Activities.SetPreferencesActivity;
import com.example.game_.other01_app.BuildConfig;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.RecyclerViewMatcher;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Calendar;

import androidx.appcompat.widget.MenuPopupWindow;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class RunThroughEspressoTests {

    private static final String NOTIFICATION_TITLE = "CareFit Reminder";
    private static final long TIMEOUT = 60 * 3000 ;
    private static final long SHORT_TIMEOUT = 30 * 1000;
    private static final String NOTIFICATION_TEXT = "Time to do some exercises!";
    private UiDevice mDevice;

    @Rule
    public ActivityTestRule<SetPreferencesActivity> mActivityTestRule = new ActivityTestRule<>(SetPreferencesActivity.class);

    @Before
    public void setUp() throws Exception {
        mDevice =
                UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    }

    //Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final  int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    /**
     * Ensure all tutorial boxes show up in the right places. And that tasks assigned
     * during the tutorial can be completed.
     */
    @Test
    public void A_testTutorialRunThrough(){
        onView(withId(R.id.set_names_next)).perform(click());
        onView(withText(R.string.your_name_error)).inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        onView(withId(R.id.enter_your_name)).perform(replaceText("testName"));
        onView(withId(R.id.set_names_next)).perform(click());
        onView(withText(R.string.patient_name_error)).inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        onView(withId(R.id.enter_their_name)).perform(replaceText("testCare"));
        onView(withId(R.id.together_checkbox)).perform(click());
        onView(withId(R.id.set_names_next)).perform(click());
        onView(withText("Next")).perform(click());
        onView(withText("Next")).perform(click());
        onView(withText("Strength")).perform(click());
        onView(withText("Arms")).perform(click());
        onView(withText("Next")).perform(click());
        onView(withText("Welcome!")).check(matches(isDisplayed()));
        onView(withText("Okay")).perform(click());
        onView(withText("Disclaimer:")).check(matches(isDisplayed()));
        onView(withText("Okay")).perform(click());

        onView(withId(R.id.overflow)).check(matches(not(isEnabled())));
        onView(withRecyclerView(R.id.exercise_recyclerview).atPosition(0)).perform(click());
        onView(withText("Okay")).perform(click());
        onView(withText("Okay")).perform(click());
        onView(withText("Okay")).perform(click());
        onView(withText("Finish")).perform(click());

        onView(withText("Okay")).perform(click());

        onView(withId(R.id.overflow)).check(matches(isEnabled()));
        onView(withId(R.id.overflow)).perform(click());
        onData(CoreMatchers.anything())
                .inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.<View>instanceOf(MenuPopupWindow.MenuDropDownListView.class))
                .atPosition(0).check(matches(not(isEnabled())));


        checkMenuItemDisabledAt(0);
        checkMenuItemDisabledAt(1);
        checkMenuItemDisabledAt(2);
        checkMenuItemDisabledAt(4);
        clickMenuItemAt(3);

        onView(withText("Show Message")).perform(click());
        onView(withText("Welcome back!")).check(matches(isDisplayed()));
        onView(withText("Okay")).perform(click());
        onView(withText("Okay")).perform(click());
        onView(withText("com/example/game_/other01_app/Activities/UserInterface/Home")).perform(click());


        onView(withText("Okay")).perform(click());

        onView(withId(R.id.overflow)).check(matches(isEnabled()));
        onView(withId(R.id.overflow)).perform(click());
        checkMenuItemDisabledAt(0);
        checkMenuItemDisabledAt(2);
        checkMenuItemDisabledAt(3);
        checkMenuItemDisabledAt(4);
        clickMenuItemAt(1);

        onView(withText("Okay")).perform(click());
        onView(withId(R.id.reminder_back_btn)).check(matches(not(isEnabled())));
        onView(withText("Add")).perform(click());

        onView(withText("Okay")).perform(click());
        onView(withId(R.id.new_reminder_back_btn)).check(matches(not(isEnabled())));
        onView(withId(R.id.new_reminder_save_btn)).perform(click());
        onView(withText(R.string.error_select_day)).inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        onView(withId(R.id.mondayCheck)).perform(click());
        onView(withId(R.id.new_reminder_save_btn)).perform(click());

        onView(withText("Okay")).perform(click());
        onView(withText("Okay")).perform(click());
        onView(withId(R.id.reminder_list_item_delete_btn)).perform(click());

        onView(withId(R.id.reminder_back_btn)).perform(click());

        onView(withText("Okay")).perform(click());

        onView(withId(R.id.overflow)).check(matches(isEnabled()));
        onView(withId(R.id.overflow)).perform(click());
        checkMenuItemDisabledAt(0);
        checkMenuItemDisabledAt(1);
        checkMenuItemDisabledAt(3);
        checkMenuItemDisabledAt(4);
        clickMenuItemAt(2);

        onView(withText("Okay")).perform(click());
        onView(withText("Strength")).perform(click());
        onView(withText("Legs")).perform(click());
        onView(withId(R.id.filterbar_back_btn)).check(matches(not(isEnabled())));
        onView(withId(R.id.filterbar_filter_btn)).perform(click());

        onView(withText("Okay")).perform(click());

        onView(withId(R.id.overflow)).check(matches(isEnabled()));
        onView(withId(R.id.overflow)).perform(click());
        checkMenuItemDisabledAt(1);
        checkMenuItemDisabledAt(2);
        checkMenuItemDisabledAt(3);
        checkMenuItemDisabledAt(4);
        clickMenuItemAt(0);

        onView(withText("Okay")).perform(click());
        onView(withId(R.id.preferencePage_save_btn)).perform(click());

        onView(withText("Okay")).perform(click());

        onView(withId(R.id.overflow)).check(matches(isEnabled()));
        onView(withId(R.id.overflow)).perform(click());
        checkMenuItemEnabledAt(0);
        checkMenuItemEnabledAt(1);
        checkMenuItemEnabledAt(2);
        checkMenuItemEnabledAt(3);
        checkMenuItemEnabledAt(4);
    }

    private void checkMenuItemEnabledAt(int itemNum){
        onData(CoreMatchers.anything())
                .inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.<View>instanceOf(MenuPopupWindow.MenuDropDownListView.class))
                .atPosition(itemNum).check(matches(isEnabled()));
    }

    private void checkMenuItemDisabledAt(int itemNum){
        onData(CoreMatchers.anything())
                .inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.<View>instanceOf(MenuPopupWindow.MenuDropDownListView.class))
                .atPosition(itemNum).check(matches(not(isEnabled())));
    }

    private void clickMenuItemAt(int itemNum){
        onData(CoreMatchers.anything())
                .inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.<View>instanceOf(MenuPopupWindow.MenuDropDownListView.class))
                .atPosition(itemNum).perform(click());
    }

    /**
     * Ensure preferences are actually changed via the Change Preferences Page
     */
    @Test
    public void B_checkChangePreferencesWorksAsItShould(){
        onView(withId(R.id.overflow)).check(matches(isEnabled()));
        onView(withId(R.id.overflow)).perform(click());
        clickMenuItemAt(0);
        onView(withId(R.id.enter_your_name)).perform(replaceText(""));
        onView(withId(R.id.preferencePage_save_btn)).perform(click());
        onView(withText(R.string.your_name_error)).inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        onView(withId(R.id.enter_your_name)).perform(replaceText("changed"));
        onView(withId(R.id.enter_their_name)).perform(replaceText(""));
        onView(withId(R.id.preferencePage_save_btn)).perform(click());
        onView(withText(R.string.patient_name_error)).inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        onView(withId(R.id.enter_their_name)).perform(replaceText("name"));
        onView(withText("Strength")).perform(click());
        onView(withText("Arms")).perform(click());
        onView(withId(R.id.preferencePage_save_btn)).perform(click());
        onView(withText("Legs")).perform(click());
        onView(withId(R.id.preferencePage_save_btn)).perform(click());
        onView(withId(R.id.overflow)).check(matches(isEnabled()));
        onView(withId(R.id.overflow)).perform(click());
        clickMenuItemAt(0);
        onView(withId(R.id.enter_your_name)).check(matches(withText("changed")));
        onView(withId(R.id.enter_their_name)).check(matches(withText("name")));
        onView(withText("Legs")).check(matches(isChecked()));
    }

    /**
     * Ensure Alarm can be set, edited, saved and deleted after
     */
    @Test
    public void C_ensureAlarmCanBeEditedAndSaved(){
        onView(withId(R.id.overflow)).perform(click());
        clickMenuItemAt(1);

        onView(withId(R.id.reminder_add_btn)).perform(click());
        onView(withId(R.id.time_picker)).check(matches(isDisplayed()));
        onView(withClassName(equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(5, 15));
        onView(withId(R.id.mondayCheck)).perform(click());
        onView(withId(R.id.new_reminder_save_btn)).perform(click());
        onView(withId(R.id.reminder_list_item_edit_btn)).perform(click());
        onView(withText("Edit Reminder")).check(matches(isDisplayed()));
        onView(withClassName(equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(7, 35));
        onView(withId(R.id.tuesdayCheck)).perform(click());
        onView(withId(R.id.new_reminder_save_btn)).perform(click());
        onView(withRecyclerView(R.id.reminder_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Tuesday"))));
        onView(withRecyclerView(R.id.reminder_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("07:35"))));
        onView(withId(R.id.reminder_list_item_delete_btn)).perform(click());
    }

    /**
     * Ensure when edit of alarm is not saved the original does not change
     */
    @Test
    public void D_ensureAlarmCanBeEditedAndNotSaved(){
        onView(withId(R.id.overflow)).perform(click());
        clickMenuItemAt(1);

        onView(withId(R.id.reminder_add_btn)).perform(click());
        onView(withId(R.id.time_picker)).check(matches(isDisplayed()));
        onView(withClassName(equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(5, 15));
        onView(withId(R.id.mondayCheck)).perform(click());
        onView(withId(R.id.new_reminder_save_btn)).perform(click());
        onView(withId(R.id.reminder_list_item_edit_btn)).perform(click());
        onView(withText("Edit Reminder")).check(matches(isDisplayed()));
        onView(withClassName(equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(7, 35));
        onView(withId(R.id.tuesdayCheck)).perform(click());
        onView(withId(R.id.new_reminder_back_btn)).perform(click());
        onView(withRecyclerView(R.id.reminder_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Monday"))));
        onView(withRecyclerView(R.id.reminder_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("05:15"))));
        onView(withRecyclerView(R.id.reminder_recycler_view).atPosition(0))
                .check(matches(IsNot.not(hasDescendant(withText("Tuesday")))));
        onView(withRecyclerView(R.id.reminder_recycler_view).atPosition(0))
                .check(matches(IsNot.not(hasDescendant(withText("07:35")))));
        onView(withId(R.id.reminder_list_item_delete_btn)).perform(click());
    }

    /**
     * Ensure that set alarm activates
     */
    @Test
    public void E_ensureAlarmCanGoOff(){
        onView(withId(R.id.overflow)).perform(click());
        clickMenuItemAt(1);

        onView(withId(R.id.reminder_add_btn)).perform(click());

        onView(withId(R.id.mondayCheck)).perform(click());
        onView(withId(R.id.tuesdayCheck)).perform(click());
        onView(withId(R.id.wednesdayCheck)).perform(click());
        onView(withId(R.id.thursdayCheck)).perform(click());
        onView(withId(R.id.fridayCheck)).perform(click());
        onView(withId(R.id.saturdayCheck)).perform(click());
        onView(withId(R.id.sundayCheck)).perform(click());

        Calendar now = Calendar.getInstance();
        int currentHour24hFormat = now.get(Calendar.HOUR_OF_DAY);
        int minuteToSet = now.get(Calendar.MINUTE) + 2;
        onView(withId(R.id.time_picker)).check(matches(isDisplayed()));
        onView(withClassName(equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(currentHour24hFormat,
                        minuteToSet));

        onView(withId(R.id.new_reminder_save_btn)).perform(click());

        mDevice.wait(Until.hasObject(By.text(NOTIFICATION_TITLE)), TIMEOUT);
        UiObject2 title = mDevice.findObject(By.text(NOTIFICATION_TITLE));
        UiObject2 text = mDevice.findObject(By.text(NOTIFICATION_TEXT));
        assertEquals(NOTIFICATION_TITLE, title.getText());
        assertEquals(NOTIFICATION_TEXT, text.getText());

        mDevice.waitForWindowUpdate(BuildConfig.APPLICATION_ID, SHORT_TIMEOUT);

        onView(withId(R.id.reminder_list_item_delete_btn)).perform(click());
    }

    /**
     * Ensure that an edited al
     */
    @Test
    public void E_ensureEditedAlarmCanGoOff(){
        onView(withId(R.id.overflow)).perform(click());
        clickMenuItemAt(1);

        onView(withId(R.id.reminder_add_btn)).perform(click());

        onView(withClassName(equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(5, 15));
        onView(withId(R.id.mondayCheck)).perform(click());
        onView(withId(R.id.new_reminder_save_btn)).perform(click());
        onView(withId(R.id.reminder_list_item_edit_btn)).perform(click());
        onView(withText("Edit Reminder")).check(matches(isDisplayed()));

        onView(withId(R.id.mondayCheck)).perform(click());
        onView(withId(R.id.tuesdayCheck)).perform(click());
        onView(withId(R.id.wednesdayCheck)).perform(click());
        onView(withId(R.id.thursdayCheck)).perform(click());
        onView(withId(R.id.fridayCheck)).perform(click());
        onView(withId(R.id.saturdayCheck)).perform(click());
        onView(withId(R.id.sundayCheck)).perform(click());

        Calendar now = Calendar.getInstance();
        int currentHour24hFormat = now.get(Calendar.HOUR_OF_DAY);
        int minuteToSet = now.get(Calendar.MINUTE) + 2;
        onView(withId(R.id.time_picker)).check(matches(isDisplayed()));
        onView(withClassName(equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(currentHour24hFormat,
                        minuteToSet));

        onView(withId(R.id.new_reminder_save_btn)).perform(click());

        mDevice.wait(Until.hasObject(By.text(NOTIFICATION_TITLE)), TIMEOUT);
        UiObject2 title = mDevice.findObject(By.text(NOTIFICATION_TITLE));
        UiObject2 text = mDevice.findObject(By.text(NOTIFICATION_TEXT));
        assertEquals(NOTIFICATION_TITLE, title.getText());
        assertEquals(NOTIFICATION_TEXT, text.getText());

        mDevice.waitForWindowUpdate(BuildConfig.APPLICATION_ID, SHORT_TIMEOUT);

        onView(withId(R.id.reminder_list_item_delete_btn)).perform(click());
    }

}
