package com.example.game_.other01_app.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.game_.other01_app.Adapters.WeeklyPlannerListAdapter;
import com.example.game_.other01_app.Adapters.WeeklyPlannerPagerAdapter;
import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.DataObjects.WeeklyPlanCreatorResult;
import com.example.game_.other01_app.DataObjects.WeeklyPlannerObject;
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.CompendiumActivities;
import com.example.game_.other01_app.Database.entities.DailyActivity;
import com.example.game_.other01_app.Database.entities.DailyPlan;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.DailyPlanCreator;
import com.example.game_.other01_app.Utility.DailyPlanReader;
import com.example.game_.other01_app.Utility.WeeklyPlanCreator;
import com.example.game_.other01_app.Utility.WeeklyPlanReader;
import com.example.game_.other01_app.ViewModels.CompendiumActivitiesViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.game_.other01_app.AssistanceClasses.DateTimeAssist.getWeekDayDate;

public class PlannerActivity extends AppCompatActivity {

    List<WeeklyPlan> mWeeklyPlans = new ArrayList<>();
    WeeklyPlan mCurrentWeeklyPlan = null;
    WeeklyPlan mPastWeeklyPlan = null;
    WeeklyPlan mFutureWeeklyPlan = null;
    ArrayList<DailyPlan> mDailyPlans =null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_planner);

        mWeeklyPlanDao = AppDatabase.getDatabase(this).weeklyPlanDao();


        try {
            WeeklyPlanCreatorResult wpcr = getOrCreateCurrentWeeklyPlan(new Date());
            mCurrentWeeklyPlan = wpcr.getmWeeklyPlan();
            mDailyPlans = (ArrayList<DailyPlan>) wpcr.getmDailyPlans();

        } catch (Exception e) {
            Log.e("weekly plan creation", e.getMessage());
        }


        TabLayout tabLayout = findViewById(R.id.weekly_planner_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Prev Week"));
        tabLayout.addTab(tabLayout.newTab().setText("Current Week"));
        tabLayout.addTab(tabLayout.newTab().setText("Next Week"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = findViewById(R.id.weekly_planner_view_pager);
        WeeklyPlannerPagerAdapter pagerAdapter =
                new WeeklyPlannerPagerAdapter(getSupportFragmentManager(),
                        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                        mPastWeeklyPlan,
                        mCurrentWeeklyPlan,
                        mFutureWeeklyPlan,
                        mDailyPlans);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    WeeklyPlanDao mWeeklyPlanDao;

    private WeeklyPlanCreatorResult getOrCreateCurrentWeeklyPlan(Date startDate) throws InterruptedException, ExecutionException {
        List<WeeklyPlan> weeklyPlans = new WeeklyPlanReader(mWeeklyPlanDao).execute().get();
        if (weeklyPlans != null)
            for (WeeklyPlan w : weeklyPlans) {
                if (DateTimeAssist.isDateInCurrentWeek(w.startDate)) {
                    List<DailyPlan> plans = new DailyPlanReader(mWeeklyPlanDao,w.id).execute().get();
                    return new WeeklyPlanCreatorResult(w, plans);
                }
            }
        Date[] dates = DateTimeAssist.getWeekDates(startDate);
        WeeklyPlan wp = new WeeklyPlan();
        wp.startDate = startDate;
        wp.endDate = dates[dates.length - 1];
        new WeeklyPlanCreator(this, mWeeklyPlanDao).execute(wp).get();

        List<DailyPlan> plansForWeek = new ArrayList<>();
        for (Date d : dates) {
            DailyPlan dailyPlan = new DailyPlan();
            dailyPlan.dayOfWeek = d;
            dailyPlan.weeklyPlanID = wp.id;

            dailyPlan.id = new DailyPlanCreator(this, mWeeklyPlanDao, dailyPlan).execute().get().intValue();
            plansForWeek.add(dailyPlan);
        }
        WeeklyPlanCreatorResult wpcr = new WeeklyPlanCreatorResult(wp, plansForWeek);

        return wpcr;

    }

    /*private ArrayList<DailyPlan> getOrCreateDailyPlansForWeeklyPlan(WeeklyPlan weeklyPlan) throws ExecutionException, InterruptedException {
        ArrayList<DailyPlan> plansForWeek = new ArrayList<>();

        Date[] dates = DateTimeAssist.getWeekDates(weeklyPlan.startDate);

        plansForWeek = (ArrayList<DailyPlan>) new DailyPlanReader(mWeeklyPlanDao, weeklyPlan.id).execute().get();
        if (plansForWeek == null || plansForWeek.size() == 0) {
            for (Date d : dates) {
                DailyPlan dailyPlan = new DailyPlan();
                dailyPlan.dayOfWeek = d;
                dailyPlan.weeklyPlanID = weeklyPlan.id;

                dailyPlan.id = new DailyPlanCreator(this, mWeeklyPlanDao, dailyPlan).execute().get().intValue();
                plansForWeek.add(dailyPlan);
            }
        }

return plansForWeek;
    }*/
}