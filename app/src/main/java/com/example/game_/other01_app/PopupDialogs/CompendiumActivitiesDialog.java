package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.game_.other01_app.Adapters.CompendiumActivitiesAutoCompleteAdapter;
import com.example.game_.other01_app.Adapters.FavouiriteCompendiumAdapter;
import com.example.game_.other01_app.Adapters.WeeklyPlannerDailyActivityRecyclerViewAdapter;
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.entities.CompendiumActivities;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.CompendiumActivitiesAutoComplete;
import com.example.game_.other01_app.ViewModels.CompendiumActivitiesViewModel;

import java.util.ArrayList;
import java.util.List;

public class CompendiumActivitiesDialog extends Dialog implements LifecycleOwner {
    LifecycleRegistry mLifecycleRegistry;
    ArrayList<CompendiumActivities> compendiumActivitiesList = new ArrayList<>();
    FavouiriteCompendiumAdapter favouiriteCompendiumAdapter;
    private Context mContext;
    private WeeklyPlannerDailyActivityRecyclerViewAdapter mAdapter;
    private int mPos;
    public CompendiumActivitiesDialog(@NonNull Context context, WeeklyPlannerDailyActivityRecyclerViewAdapter adapter, int pos) {
        super(context, R.style.Theme_Design_Light);
        mContext = context;
        mAdapter =adapter;
        mPos = pos;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_other);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        mLifecycleRegistry.handleLifecycleEvent(LifecycleRegistry.Event.ON_CREATE);
        favouiriteCompendiumAdapter = new FavouiriteCompendiumAdapter(mContext);
        final TextView noFavTextView = findViewById(R.id.dialog_add_other_noFav_textview);

        ImageView addCompendiumButton = findViewById(R.id.dialog_add_other_add_compendium);
        addCompendiumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAdapter.assignActivity("Compendium", "",ExerciseTypes.OTHER,mPos);
                dismiss();

            }
        });


        ListView favouritesListview = findViewById(R.id.add_activity_dialog_listView);
        favouritesListview.setAdapter(favouiriteCompendiumAdapter);
        favouiriteCompendiumAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                if (favouiriteCompendiumAdapter.getCount()>0)
                {
                    noFavTextView.setVisibility(View.GONE);
                    favouritesListview.setVisibility(View.VISIBLE);
                }
            }
        });


       final CompendiumActivitiesAutoComplete autoComplete = findViewById(R.id.dialog_add_other_search_autocomplete);

       autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               CompendiumActivities compendiumActivities =(CompendiumActivities) adapterView.getItemAtPosition(i);
               String name = compendiumActivities.name;
               favouiriteCompendiumAdapter.addCompendium(compendiumActivities);
           }
       });


       /*autoComplete.setVisibility(View.VISIBLE);
       ArrayList<CompendiumActivities> compendiumActivities = (ArrayList<CompendiumActivities>) AppDatabase.getDatabase(mContext).compendiumsDao().getCompendiumsSync();

       CompendiumActivitiesAutoCompleteAdapter adapter = new CompendiumActivitiesAutoCompleteAdapter(mContext, compendiumActivities);
       autoComplete.setAdapter(adapter);*/
       LiveData<List<CompendiumActivities>> liveData = AppDatabase.getDatabase(mContext).compendiumsDao().getAllCompendiums();
        liveData.removeObservers(this);
         liveData.observe(this, new Observer<List<CompendiumActivities>>() {
             @Override
             public void onChanged(List<CompendiumActivities> activities) {
                 if(android.os.Debug.isDebuggerConnected())
                     android.os.Debug.waitForDebugger();
                 compendiumActivitiesList = (ArrayList<CompendiumActivities>)activities;
                 CompendiumActivitiesAutoCompleteAdapter adapter = new CompendiumActivitiesAutoCompleteAdapter(mContext, compendiumActivitiesList);
                 autoComplete.setAdapter(adapter);
                 autoComplete.setThreshold(0);
                 autoComplete.setVisibility(View.VISIBLE);
             }
         });



       int i =  compendiumActivitiesList.size();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        mLifecycleRegistry.handleLifecycleEvent(LifecycleRegistry.Event.ON_START);
    }

    @Override
    protected void onStop() {
        mLifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
        mLifecycleRegistry.handleLifecycleEvent(LifecycleRegistry.Event.ON_STOP);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
