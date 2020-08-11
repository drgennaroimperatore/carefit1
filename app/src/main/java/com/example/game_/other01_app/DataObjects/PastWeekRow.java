package com.example.game_.other01_app.DataObjects;

public class PastWeekRow
{
    private String mStartDate, mEndDate;
    private int mPlannedCardios, mPlannedMuscleBalances, mPlannedCompendiums;
    private int mCompletedCardios, mCompletedMuscleBalances, mCompletedCompendiums;

    public int getmCompletedCardios() {
        return mCompletedCardios;
    }

    public int getmCompletedCompendiums() {
        return mCompletedCompendiums;
    }

    public int getmCompletedMuscleBalances() {
        return mCompletedMuscleBalances;
    }

    public void setmCompletedCardios(int mCompletedCardios) {
        this.mCompletedCardios = mCompletedCardios;
    }

    public void setmCompletedCompendiums(int mCompletedCompendiums) {
        this.mCompletedCompendiums = mCompletedCompendiums;
    }

    public void setmCompletedMuscleBalances(int mCompletedMuscleBalances) {
        this.mCompletedMuscleBalances = mCompletedMuscleBalances;
    }

    public void setmPlannedCardios(int mPlannedCardios) {
        this.mPlannedCardios = mPlannedCardios;
    }

    public void setmPlannedCompendiums(int mPlannedCompendiums) {
        this.mPlannedCompendiums = mPlannedCompendiums;
    }

    public void setmPlannedMuscleBalances(int mPlannedMuscleBalances) {
        this.mPlannedMuscleBalances = mPlannedMuscleBalances;
    }

    public int getmPlannedCardios() {
        return mPlannedCardios;
    }

    public int getmPlannedCompendiums() {
        return mPlannedCompendiums;
    }

    public int getmPlannedMuscleBalances() {
        return mPlannedMuscleBalances;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }
}
