package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.Database.entities.CompletedDailyActivities;
import com.example.game_.other01_app.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProgressOverviewDialogAdapter extends ArrayAdapter<CompletedDailyActivities> {

    Context mContext;

    public ProgressOverviewDialogAdapter(@NonNull Context context, int resource, ArrayList items) {
        super(context, resource, items);
        context = mContext;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (convertView==null)
            convertView = inflater.inflate(R.layout.dialog_progress_overview_row,null);

        CompletedDailyActivities item = getItem(position);

        TextView activityNameTV = convertView.findViewById(R.id.dialog_overview_progress_row_name_textView);
        activityNameTV.setText(item.name);

        TextView activityDateTV = convertView.findViewById(R.id.dialog_overview_progress_row_date_textView);
        activityDateTV.setText(DateTimeAssist.convertDateToPreferredFormat(item.dayOfWeek));

        return convertView;
    }
}
