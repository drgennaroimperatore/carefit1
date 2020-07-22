package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_.other01_app.Database.entities.DailyActivity;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.PopupDialogs.AddActivityDialog;
import com.example.game_.other01_app.R;

import java.util.ArrayList;
import java.util.List;

public class WeeklyPlannerDailyActivityRecyclerViewAdapter extends RecyclerView.Adapter  {
    Context mContext;
    List<DailyActivity> mData = new ArrayList<>();


   public WeeklyPlannerDailyActivityRecyclerViewAdapter(Context context)
   {
       mContext = context;
       mData.add(new DailyActivity());
   }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.weekly_planner_list_add,null);
      WeklyPlannerDailyActivityOnClickListener listener = (view, pos) -> {
          AddActivityDialog dialog = new AddActivityDialog(mContext, pos, this); dialog.show();};

        return new DailyActivityReciclerViewHolder(v,mContext,listener,this );
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

       DailyActivityReciclerViewHolder h = (DailyActivityReciclerViewHolder)holder;
       DailyActivity activity = mData.get(position);

        Button addActivityButton = ((DailyActivityReciclerViewHolder) holder).mAddActivityButton;
        TextView activityCounterTV = ((DailyActivityReciclerViewHolder) holder).mTextViewActivityCounter;
        activityCounterTV.setText("Activity "+String.valueOf(position+1));
      if(activity.assigned) {

          switch (activity.type)
          {
              case CARDIO:
                  addActivityButton.setBackgroundResource(R.drawable.custom_button_dist);
                  addActivityButton.setText("Cardio");
                  break;
              case DISTANCE:
                  addActivityButton.setBackgroundResource(R.drawable.custom_button_dist);
                  addActivityButton.setText("Distance");
                  break;
              case MUSCLE:
                  addActivityButton.setBackgroundResource(R.drawable.custom_button_str);
                  addActivityButton.setText("Muscle");
                  break;
              case OTHER:
                  addActivityButton.setBackgroundResource(R.drawable.custom_button);
                  addActivityButton.setText("Other");
                  break;


          }


      }



    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void assignActivity (ExerciseTypes t, int pos)
    {
     mData.get(pos).assigned = true;
     mData.get(pos).type = t;
     if(mData.size()<3)
        mData.add(new DailyActivity());
     notifyDataSetChanged();

    }

    public void deleteActivity()
    {

    }

}



class DailyActivityReciclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Button mAddActivityButton;
    public TextView mTextViewActivityCounter;
    public DailyActivityReciclerViewHolder(@NonNull View itemView) {
        super(itemView);


    }

    private Context mContext;
    public WeklyPlannerDailyActivityOnClickListener mListener;
    public WeeklyPlannerDailyActivityRecyclerViewAdapter mAdapter;

    public DailyActivityReciclerViewHolder (@NonNull View itemView, Context context,
                                            WeklyPlannerDailyActivityOnClickListener listener,
                                            WeeklyPlannerDailyActivityRecyclerViewAdapter adapter)
    {
        super(itemView);

        mContext =context;
        mListener =listener;
        mAdapter = adapter;
        mAddActivityButton = itemView.findViewById(R.id.weekly_planner_dailyactivity_addActivity_button);
        mTextViewActivityCounter = itemView.findViewById(R.id.weekly_planner_list_add_activity_count_textView);
        mAddActivityButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
       // mAdapter.assignActivity(ExerciseTypes.CARDIO, getAdapterPosition());
        AddActivityDialog dialog = new AddActivityDialog(mContext,getAdapterPosition(),mAdapter);
        dialog.show();

    }
}

interface WeklyPlannerDailyActivityOnClickListener
{
    void onClick(View view, int position);


}
