package com.example.game_.other01_app.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.game_.other01_app.Adapters.WeeklyPlannerListAdapter;
import com.example.game_.other01_app.DataObjects.WeeklyPlannerObject;
import com.example.game_.other01_app.Database.entities.CompendiumActivities;
import com.example.game_.other01_app.Database.entities.DailyActivity;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.CompendiumActivitiesViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentWeekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentWeekFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CompendiumActivitiesViewModel compendiumActivitiesViewModel;
    public static List<CompendiumActivities> mCompendiumActivities = new ArrayList<>();



    public CurrentWeekFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentWeekFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentWeekFragment newInstance(String param1, String param2) {
        CurrentWeekFragment fragment = new CurrentWeekFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_week, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView weeklyListView = view.findViewById(R.id.weekly_planner_listVoew);
        WeeklyPlannerListAdapter adapter = new WeeklyPlannerListAdapter(getContext(),0);
        adapter.add(new WeeklyPlannerObject("Mon", new DailyActivity()));
        adapter.add(new WeeklyPlannerObject("Tue", new DailyActivity()));
        adapter.add(new WeeklyPlannerObject("Wed", new DailyActivity()));
        adapter.add(new WeeklyPlannerObject("Thu", new DailyActivity()));
        adapter.add(new WeeklyPlannerObject("Fri", new DailyActivity()));
        weeklyListView.setAdapter(adapter);

//        compendiumActivitiesViewModel =  ViewModelProviders.of(this).get(CompendiumActivitiesViewModel.class);
       /* compendiumActivitiesViewModel.getAllCompendiums().observe(this, new Observer<List<CompendiumActivities>>() {
            @Override
            public void onChanged(List<CompendiumActivities> activities) {

                //mCompendiumActivities = new ArrayList<>();
                //mCompendiumActivities.addAll(activities);

            }

        });
        compendiumActivitiesViewModel.getAllCompendiums().getValue();*/

    }
}