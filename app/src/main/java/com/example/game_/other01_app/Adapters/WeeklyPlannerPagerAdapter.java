package com.example.game_.other01_app.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.game_.other01_app.Fragments.CurrentWeekFragment;
import com.example.game_.other01_app.Fragments.NextWeekFragment;
import com.example.game_.other01_app.Fragments.PreviousWeekFragment;

public class WeeklyPlannerPagerAdapter extends FragmentPagerAdapter {
    private final int mNOfTabs =3;
    public WeeklyPlannerPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        CurrentWeekFragment cwf = new CurrentWeekFragment();
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
