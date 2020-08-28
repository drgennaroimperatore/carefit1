package com.example.game_.other01_app.EventSystem;

import com.example.game_.other01_app.Adapters.WeeklyPlannerListAdapter;

public class FutureRescheduleWatcher extends Watcher {

        WeeklyPlannerListAdapter mAdapter;

        public static FutureRescheduleWatcher mInstance;
    private FutureRescheduleWatcher()
    {

    }


    public static FutureRescheduleWatcher getInstance()
    {
        if (mInstance ==null)
        {
            mInstance = new FutureRescheduleWatcher();
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
