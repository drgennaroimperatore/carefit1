package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

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
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.entities.CompendiumActivities;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.CompendiumActivitiesAutoComplete;
import com.example.game_.other01_app.ViewModels.CompendiumActivitiesViewModel;

import java.util.ArrayList;
import java.util.List;

public class CompendiumActivitiesDialog extends Dialog implements LifecycleOwner {
    LifecycleRegistry mLifecycleRegistry;
    ArrayList<CompendiumActivities> compendiumActivitiesList = new ArrayList<>();
    private Context mContext;
    public CompendiumActivitiesDialog(@NonNull Context context) {
        super(context, R.style.Theme_Design_Light);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_other);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        mLifecycleRegistry.handleLifecycleEvent(LifecycleRegistry.Event.ON_CREATE);


       final CompendiumActivitiesAutoComplete autoComplete = findViewById(R.id.dialog_add_other_search_autocomplete);


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
