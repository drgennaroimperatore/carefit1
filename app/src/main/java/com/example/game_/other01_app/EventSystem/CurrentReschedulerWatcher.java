package com.example.game_.other01_app.EventSystem;

import com.example.game_.other01_app.Adapters.WeeklyPlannerListAdapter;

public class CurrentReschedulerWatcher extends Watcher {

        WeeklyPlannerListAdapter mAdapter;

        public static CurrentReschedulerWatcher mInstance;
    private CurrentReschedulerWatcher()
    {

    }


    public static CurrentReschedulerWatcher getInstance()
    {
        if (mInstance ==null)
        {
            mInstance = new CurrentReschedulerWatcher();
        }
        return mInstance;

    }



    public void initialise(WeeklyPlannerListAdapter adapter)
    {
        mAdapter= adapter;
    }
    @Override
    public void update(DatabaseEvents event) throws WatcherNotInitialised {
        if(mAdapter== null) throw new WatcherNotInitialised("Initialise adapter");
        mAdapter.notifyDataSetChanged();


    }
}
