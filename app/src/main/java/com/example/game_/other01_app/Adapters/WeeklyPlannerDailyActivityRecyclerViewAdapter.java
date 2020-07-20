package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_.other01_app.Database.entities.Workout;
import com.example.game_.other01_app.PopupDialogs.MuscleBalanceDialog;
import com.example.game_.other01_app.R;

import java.util.ArrayList;
import java.util.List;

public class WeeklyPlannerDailyActivityRecyclerViewAdapter extends RecyclerView.Adapter  {
    Context mContext;
    List<Workout> mData = new ArrayList<>();


   public WeeklyPlannerDailyActivityRecyclerViewAdapter(Context context)
   {
       mContext = context;
       mData.add(new Workout());
   }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.weekly_planner_list_add,null);
      WeklyPlannerDailyActivityOnClickListener listener = (view, pos) -> {MuscleBalanceDialog dialog = new MuscleBalanceDialog(mContext); dialog.show();};

        return new DailyActivityReciclerViewHolder(v,mContext,listener );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

       DailyActivityReciclerViewHolder h = (DailyActivityReciclerViewHolder)holder;


    }



    @Override
    public int getItemCount() {
        return mData.size();
    }



}

class DailyActivityReciclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Button mAddCardioButton, mAddBalanceButton, mAddMuscleButton, mAddOtherButton;
    public DailyActivityReciclerViewHolder(@NonNull View itemView) {
        super(itemView);

    }

    private Context mContext;
    public WeklyPlannerDailyActivityOnClickListener mListener;

    public DailyActivityReciclerViewHolder (@NonNull View itemView, Context context, WeklyPlannerDailyActivityOnClickListener listener)
    {
        super(itemView);

        mContext =context;
        mListener =listener;
        mAddCardioButton = itemView.findViewById(R.id.weekly_planner_dailyactivity_addCardio_button);
        mAddBalanceButton = itemView.findViewById(R.id.weekly_planner_dailyactivity_addBalance_button);
        mAddMuscleButton = itemView.findViewById(R.id.weekly_planner_dailyactivity_addMuscle_button);
        mAddMuscleButton.setOnClickListener(this);
        mAddOtherButton = itemView.findViewById(R.id.weekly_planner_dailyactivity_addOther_button);

    }


    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());

    }
}

interface WeklyPlannerDailyActivityOnClickListener
{
    void onClick(View view, int position);


}
