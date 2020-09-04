package com.example.game_.other01_app.Database.UsageDB;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;
import com.example.game_.other01_app.Sync.SyncManager;
import com.example.game_.other01_app.Sync.Syncable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class ActivitiesStats extends Syncable {

    @PrimaryKey
    public int id;
    public int cardioActivitiesStarted;
    public int muscleBalanceActivitiesStarted;
    public int compendiumActivitiesStarted;
    public int cardioActivitiesCompleted;
    public int muscleBalanceActivitiesCompleted;
    public int compendiumActivitiesCompleted;
    @Ignore
    public String uid;

    public ActivitiesStats() {}

    @Ignore
    public ActivitiesStats(String uid)
    {
        super();
        this.uid = uid;
    }


    @Override
    public String sync() {
        Map<String, Object> activitiesStats = new LinkedHashMap<>();
        activitiesStats.put("cardioActivitiesStarted",cardioActivitiesStarted);
        activitiesStats.put("compendiumActivitiesStarted",compendiumActivitiesStarted);
        activitiesStats.put(" cardioActivitiesCompleted",cardioActivitiesCompleted);
        activitiesStats.put("muscleBalanceActivitiesCompleted",muscleBalanceActivitiesCompleted);
        activitiesStats.put("compendiumActivitiesCompleted",compendiumActivitiesCompleted);
        activitiesStats.put("muscleBalanceActivitiesStarted",muscleBalanceActivitiesStarted);
        activitiesStats.put("userid", uid);
        return new SyncManager().sendPost("syncActivityStats.php",activitiesStats);
    }

    public static ActivitiesStats generateUpdatedInstance(Context context)

    {
        ActivitiesStats returnVal =
                new ActivitiesStats(context.getSharedPreferences("PREFERENCE",Context.MODE_PRIVATE).getString("UUID",""));
       WeeklyPlanDao dao = AppDatabase.getDatabase(context).weeklyPlanDao();
       returnVal.muscleBalanceActivitiesCompleted = dao.getCompletedActivityCountByType(ExerciseTypes.MUSCLE);
       returnVal.compendiumActivitiesCompleted =dao.getCompletedActivityCountByType(ExerciseTypes.OTHER);
       returnVal.cardioActivitiesCompleted =dao.getCompletedActivityCountByType(ExerciseTypes.CARDIO);
       returnVal.cardioActivitiesStarted = dao.getPlannedActivityCountByType(ExerciseTypes.CARDIO);
       returnVal.muscleBalanceActivitiesStarted = dao.getPlannedActivityCountByType(ExerciseTypes.MUSCLE);
        returnVal.compendiumActivitiesStarted= dao.getPlannedActivityCountByType(ExerciseTypes.OTHER);
        return returnVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivitiesStats that = (ActivitiesStats) o;
        return cardioActivitiesStarted == that.cardioActivitiesStarted &&
                muscleBalanceActivitiesStarted == that.muscleBalanceActivitiesStarted &&
                compendiumActivitiesStarted == that.compendiumActivitiesStarted &&
                cardioActivitiesCompleted == that.cardioActivitiesCompleted &&
                muscleBalanceActivitiesCompleted == that.muscleBalanceActivitiesCompleted &&
                compendiumActivitiesCompleted == that.compendiumActivitiesCompleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardioActivitiesStarted, muscleBalanceActivitiesStarted, compendiumActivitiesStarted, cardioActivitiesCompleted, muscleBalanceActivitiesCompleted, compendiumActivitiesCompleted);
    }
}
