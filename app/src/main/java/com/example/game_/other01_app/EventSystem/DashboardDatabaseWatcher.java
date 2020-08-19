package com.example.game_.other01_app.EventSystem;

import com.example.game_.other01_app.Activities.DashboardActivity;

public class DashboardDatabaseWatcher extends Watcher {
    private DashboardActivity mDashboardActivity;

    public DashboardDatabaseWatcher()
    {

    }
    public void initialiseWatcher(DashboardActivity activity)
    {
        mDashboardActivity = activity;
    }


    @Override
    public void update(DatabaseEvents events) throws WatcherNotInitialised {

        if(mDashboardActivity==null)
            throw new WatcherNotInitialised("Initialise the watcher with an instance of the dashboard activity");

        switch (events)
        {
            case EDUCATIONAL_TABLE_CREATION_STARTED:
                mDashboardActivity.disableEducationalButton();
                break;
            case EDUCATIONAL_TABLE_CREATION_ENDED:
                mDashboardActivity.enableEducationalButton();
                break;
        }

    }
}
