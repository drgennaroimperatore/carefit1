package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_.other01_app.Database.entities.DailyActivity;
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
       mData.add(null);
   }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.weekly_planner_list_add,null);
      WeklyPlannerDailyActivityOnClickListener listener = (view, pos) -> {
          AddActivityDialog dialog = new AddActivityDialog(mContext, pos, this); dialog.show();};

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

    public void assignActivity ()
    {
     //   mData.add(w);
    }

    public void deleteActivity()
    {

    }

}



class DailyActivityReciclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageButton mAddActivityButton;
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
        mAddActivityButton = itemView.findViewById(R.id.weekly_planner_dailyactivity_addActivity_button);
        mAddActivityButton.setOnClickListener(this);




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
