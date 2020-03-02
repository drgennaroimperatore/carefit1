package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.Database.entities.TimeSet;
import com.example.game_.other01_app.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.RecordViewHolder> {

    private final Context mContext;

    class RecordViewHolder extends RecyclerView.ViewHolder {

        private final TextView exerciseNameTextView;
        private final TextView exerciseTimeTextView;
        private final ImageView exerciseImageView;


        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseNameTextView = itemView.findViewById(R.id.record_exercise_name);
            exerciseTimeTextView = itemView.findViewById(R.id.record_exercise_time);
            exerciseImageView = itemView.findViewById(R.id.record_image_view);
        }
    }

    private final LayoutInflater mInflater;
    private List<TimeSet> mTimeSets; //Cached copy of timesets for records.

    public RecordListAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecordListAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_records, parent,false);
        return new RecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordListAdapter.RecordViewHolder holder, int position) {
        if (mTimeSets != null) {
            TimeSet current = mTimeSets.get(position);
            holder.exerciseNameTextView.setText(current.getExercisename());
            if(current.getRecentTime() > current.getBestTime()){
                holder.exerciseTimeTextView.setText(DateTimeAssist.longToTimerString(current.getRecentTime()));
            } else {
                holder.exerciseTimeTextView.setText(DateTimeAssist.longToTimerString(current.getBestTime()));
            }
            holder.exerciseImageView.setImageResource(mContext.getResources().getIdentifier(
                    current.getImage(),
                    "drawable",
                    mContext.getPackageName()
            ));
        } else {
            //Covers the case of data not being ready yet.
            holder.exerciseNameTextView.setText(R.string.no_records_text);
        }
    }

    public void setRecords(List<TimeSet> timeSets) {
        mTimeSets = timeSets;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mTimeSets has not been updated (means initially, it's null and we can't return null!)
    @Override
    public int getItemCount() {
        if(mTimeSets != null)
            return mTimeSets.size();
        return 0;
    }


}
