package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.game_.other01_app.Database.entities.CompendiumActivities;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.CompendiumActivitiesAutoComplete;

import java.util.ArrayList;

public class CompendiumActivitiesAutoCompleteAdapter extends ArrayAdapter<CompendiumActivities>
{
    private Context mContext;

    public CompendiumActivitiesAutoCompleteAdapter(Context context, ArrayList<CompendiumActivities> data)
    {
        super(context, 0,data);
        mContext = context;
       // addAll(data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.compendium_adapter_row,null);
        }

        TextView compendiumNameTextView = convertView.findViewById(R.id.compendium_activity_row_name_textview);
        TextView compendiumMets = convertView.findViewById(R.id.compendium_activity_row_name_mets);

        compendiumNameTextView.setText(getItem(position).name);
        compendiumMets.setText("METs: " + String.valueOf(getItem(position).mets));


        return convertView;

    }
}
