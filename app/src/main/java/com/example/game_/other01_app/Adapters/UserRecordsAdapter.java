package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserRecordsAdapter extends RecyclerView.Adapter<UserRecordsAdapter.UserRecordsViewHolder> {

    class UserRecordsViewHolder extends RecyclerView.ViewHolder {

        private final TextView bestTimeValue;
        private final TextView highestIntensityValue;
        private final TextView streakValue;
        private final TextView todaysDate;

        public UserRecordsViewHolder(@NonNull View itemView) {
            super(itemView);
            todaysDate = itemView.findViewById(R.id.todays_date_value);
            bestTimeValue = itemView.findViewById(R.id.best_time_value);
            highestIntensityValue = itemView.findViewById(R.id.highest_intensity_value);
            streakValue = itemView.findViewById(R.id.streak_value);
        }
    }


        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());


    private final LayoutInflater mInflater;
    private User mUser; //Cached copy of the user

    public UserRecordsAdapter(Context context) { mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public UserRecordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.user_recordsview_item, parent, false);
        return new UserRecordsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecordsViewHolder holder, int position) {
        if(mUser != null) {
            User user = mUser;
            if(user.getRecentTotalExerciseTime() > user.getBestTotalExerciseTime()){
                holder.bestTimeValue.setText(DateTimeAssist.longToTimerString(user.getRecentTotalExerciseTime()));
            } else {
                holder.bestTimeValue.setText(DateTimeAssist.longToTimerString(user.getBestTotalExerciseTime()));
            }
            holder.highestIntensityValue.setText(user.getRecentHighestIntensity());
            holder.streakValue.setText(Integer.toString(user.getStreak()));
            holder.todaysDate.setText(date);
        }
    }

    public String getDate() {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        return date;
    }

    @Override
    public int getItemCount() {
        if(mUser != null)
            return 1;
        return 0;
    }

    public void setUser(User user) {
        mUser = user;
        notifyDataSetChanged();
    }
}
