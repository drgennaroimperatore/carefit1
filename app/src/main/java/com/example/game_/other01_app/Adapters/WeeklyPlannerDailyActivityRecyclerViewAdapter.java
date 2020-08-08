package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyActivity;
import com.example.game_.other01_app.Database.entities.DailyActivityStatus;
import com.example.game_.other01_app.Database.entities.DailyPlan;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.PopupDialogs.AddActivityDialog;
import com.example.game_.other01_app.PopupDialogs.ExcerciseDescriptionDialog;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.DailyActivityCreator;
import com.example.game_.other01_app.Utility.DailyActivityDeleter;
import com.example.game_.other01_app.Utility.DailyActivityReader;
import com.example.game_.other01_app.Utility.DailyActivityUpdater;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class WeeklyPlannerDailyActivityRecyclerViewAdapter extends RecyclerView.Adapter {
    Context mContext;


   ArrayList<DailyActivity> mData = null;
   DailyPlan mDailyPlan =null;
    WeeklyPlanDao mDao;


    public WeeklyPlannerDailyActivityRecyclerViewAdapter(Context context, DailyPlan plan)
   {

       mContext = context;
       mDailyPlan = plan;
    mDao = AppDatabase.getDatabase(mContext).weeklyPlanDao();


           mData = new ArrayList<>();

       try {
           mData = (ArrayList<DailyActivity>) new DailyActivityReader(mDao, plan.id).execute().get();
           if(mData==null)
           {
               mData = new ArrayList<>();
               addNewActivity();
           }
           else {
               if(mData.size()==0)
                   addNewActivity();
           }

       } catch (ExecutionException e) {
           e.printStackTrace();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }


       //  mData.add(new DailyActivity(mDailyPlan.id)); // placeholder for assigning activities
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

        if(activity.status== DailyActivityStatus.NOT_ASSIGNED)
        {
            addActivityButton.setBackgroundResource(R.drawable.ic_action_plus);
        }

      if(activity.status == DailyActivityStatus.ASSIGNED) {

          switch (activity.type)
          {
              case CARDIO:
                  addActivityButton.setBackgroundResource(R.drawable.custom_button_dist);
                  addActivityButton.setText("");
                  break;
              case DISTANCE:
                  addActivityButton.setBackgroundResource(R.drawable.custom_button_dist);
                  addActivityButton.setText("");
                  break;
              case MUSCLE:
                  addActivityButton.setBackgroundResource(R.drawable.custom_button_str);
                  addActivityButton.setText("");
                  break;
              case OTHER:
                  addActivityButton.setBackgroundResource(R.drawable.custom_button);
                  addActivityButton.setText("");
                  break;
          }

      }
      else if(activity.status == DailyActivityStatus.COMPLETED)
      {
          Drawable completedIcon = mContext.getDrawable(R.drawable.ic_complete);
          addActivityButton.setBackgroundColor(R.color.colorBlack);
          addActivityButton.setCompoundDrawablesWithIntrinsicBounds(null,completedIcon,null,null);

      }
      else if (activity.status == DailyActivityStatus.PARTIALLY_COMPLETED)
      {
          addActivityButton.setBackgroundResource(R.drawable.ic_partial);
      }

    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void addNewActivity()
    {
            DailyActivity firstActivity = new DailyActivity(mDailyPlan.id);
            try {
                firstActivity.id = new DailyActivityCreator(mDao).execute(firstActivity).get().intValue();
                mData.add(firstActivity);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();


        }


    }

    private void removeActivity(DailyActivity removee)
    {
        new DailyActivityDeleter(mDao).execute(removee);

    }

    private void updateActivity(DailyActivity updatee)
    {
        new DailyActivityUpdater(mDao).execute(updatee);
    }


    public void assignActivity (ExerciseTypes t, int pos)
    {

     mData.get(pos).status = DailyActivityStatus.ASSIGNED;
     mData.get(pos).type = t;
     updateActivity(mData.get(pos));

     if(mData.size()<3 && pos<=mData.size()-1)
        addNewActivity();
     notifyDataSetChanged();

    }

    public void markActivityAsComplete (int pos)
    {
        mData.get(pos).status = DailyActivityStatus.COMPLETED;
        notifyDataSetChanged();
        updateActivity(mData.get(pos));

    }

    public void markActivityAsPartialComplete(int pos)
    {
        mData.get(pos).status = DailyActivityStatus.PARTIALLY_COMPLETED;
        notifyDataSetChanged();
        updateActivity(mData.get(pos));
    }

    public void unassignActivity(int pos)
    {
        DailyActivity da = mData.get(pos);
        da.type = ExerciseTypes.UNASSIGNED;
        da.status = DailyActivityStatus.NOT_ASSIGNED;
        updateActivity(da);
        notifyDataSetChanged();
    }

    public void deleteActivity(int pos)
    {
        mData.remove(pos);
        removeActivity(mData.get(pos));
        notifyDataSetChanged();

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
        if(mAdapter.mData.get(getAdapterPosition()).status == DailyActivityStatus.NOT_ASSIGNED) {
            AddActivityDialog dialog = new AddActivityDialog(mContext, getAdapterPosition(), mAdapter);
            dialog.show();
        }
        else if(mAdapter.mData.get(getAdapterPosition()).status == DailyActivityStatus.ASSIGNED)
        {
            Bundle args = new Bundle();
            args.putInt("pos", getAdapterPosition());
            args.putString("type", mAdapter.mData.get(getAdapterPosition()).type.toString());
            args.putString("status",mAdapter.mData.get(getAdapterPosition()).status.toString());
            ExcerciseDescriptionDialog edd = new ExcerciseDescriptionDialog(mContext,null,args,mAdapter);
            edd.show();
        }

    }
}

interface WeklyPlannerDailyActivityOnClickListener
{
    void onClick(View view, int position);

}
