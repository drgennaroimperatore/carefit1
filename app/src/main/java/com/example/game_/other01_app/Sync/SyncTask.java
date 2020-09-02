package com.example.game_.other01_app.Sync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.UsageDB.ActivitiesStats;
import com.example.game_.other01_app.Database.UsageDB.UsageDB;
import com.example.game_.other01_app.Database.UsageDB.UsageDBDao;

public class SyncTask extends AsyncTask<Void, Void, Void> {
    private Context mContext;
    public SyncTask(Context context)
    {
        mContext = context;
    }


    @Override
    protected Void doInBackground(Void... voids)
    {

        UsageDBDao usageDBDao = UsageDB.getInstance(mContext).usageDBDao();


       ActivitiesStats activitiesStats = usageDBDao.getActivitiesStats();
       if(activitiesStats==null)
       {
           usageDBDao.InsertActivityStats(ActivitiesStats.generateUpdatedInstance(mContext));
           activitiesStats = usageDBDao.getActivitiesStats();
           activitiesStats.sync();
       }
       else {
           ActivitiesStats oldActivityStats = usageDBDao.getActivitiesStats();
           ActivitiesStats newActivityStats = ActivitiesStats.generateUpdatedInstance(mContext);
           if (!oldActivityStats.equals(newActivityStats))
        {
            usageDBDao.UpdateActivityStats(newActivityStats);

           }
          String response = newActivityStats.sync();

           Log.i("SyncResult",response);
       }
        return null;
    }
}
