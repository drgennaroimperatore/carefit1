package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.game_.other01_app.DataObjects.PastWeekRow;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.PopupDialogs.ProgressOverviewDialog;
import com.example.game_.other01_app.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PastWeekRowAdapter extends ArrayAdapter<PastWeekRow> {

    private Context mContext;
    public PastWeekRowAdapter(@NonNull Context context, int resource, ArrayList<PastWeekRow> data) {
        super(context, resource,data);
        mContext = context;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.past_week_row, null);
        }
        PastWeekRow row = getItem(position);

        Bundle dialogArgs = new Bundle();


        TextView startDateTV = convertView.findViewById(R.id.past_week_row_start_date_text_view);
        startDateTV.setText(row.getmStartDate());
        TextView endDateTV = convertView.findViewById(R.id.past_week_row_end_date_text_view);
        endDateTV.setText(row.getmEndDate());

        TextView cardioCountTv  = convertView.findViewById(R.id.past_week_row_cardio_count_text_view);
        cardioCountTv.setText(String.valueOf(row.getmCompletedCardios()));

        cardioCountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogArgs.putString("type",ExerciseTypes.CARDIO.toString());
                ProgressOverviewDialog dialog = new ProgressOverviewDialog(mContext, dialogArgs);
                dialog.show();
            }
        });

        TextView muscleCount = convertView.findViewById(R.id.past_week_row_muscle_count_text_view);
        muscleCount.setText(String.valueOf(row.getmCompletedMuscleBalances()));

        muscleCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogArgs.putString("type",ExerciseTypes.MUSCLE.toString());
                ProgressOverviewDialog dialog = new ProgressOverviewDialog(mContext, dialogArgs);
                dialog.show();

            }
        });

        TextView otherCountTV = convertView.findViewById(R.id.past_week_row_other_count_text_view);
        otherCountTV.setText(String.valueOf(row.getmCompletedCompendiums()));

        otherCountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogArgs.putString("type",ExerciseTypes.OTHER.toString());
                ProgressOverviewDialog dialog = new ProgressOverviewDialog(mContext, dialogArgs);
                dialog.show();
            }
        });


        return convertView;
    }
}
