package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.EducationalDao;
import com.example.game_.other01_app.Database.entities.EducationalList;

import java.util.List;

public class EducationalListReader extends AsyncTask<Void, Void, List<EducationalList>> {

    private EducationalDao mDao;
    public EducationalListReader (EducationalDao dao)
    {
        mDao = dao;
    }
    @Override
    protected List<EducationalList> doInBackground(Void... voids) {
      return  mDao.getEducationalList();

    }
}
