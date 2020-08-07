package com.example.game_.other01_app.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.game_.other01_app.Database.entities.WeeklyPlan;
import com.example.game_.other01_app.Fragments.CurrentWeekFragment;
import com.example.game_.other01_app.Fragments.NextWeekFragment;
import com.example.game_.other01_app.Fragments.PreviousWeekFragment;

public class WeeklyPlannerPagerAdapter extends FragmentPagerAdapter {
    private final int mNOfTabs =3;
    private WeeklyPlan mPastWeeklyPlan, mPresentWeeklyPlan, mFutureWeeklyPlan;
    public WeeklyPlannerPagerAdapter(@NonNull FragmentManager fm, int behavior, WeeklyPlan past, WeeklyPlan present, WeeklyPlan future) {
        super(fm, behavior);
        mPresentWeeklyPlan = present;
        mPastWeeklyPlan = past;
        mFutureWeeklyPlan = future;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        CurrentWeekFragment cwf = new CurrentWeekFragment(mPresentWeeklyPlan);
        switch (position)
        {
            case 0:
                return new PreviousWeekFragment();
            case 1:
                return cwf;
            case 2:
                return new NextWeekFragment();


        }
        return cwf;

    }

    @Override
    public int getCount() {
        return mNOfTabs;
    }
}