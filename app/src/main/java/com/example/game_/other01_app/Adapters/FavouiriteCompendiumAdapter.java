package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.game_.other01_app.Database.entities.CompendiumActivities;
import com.example.game_.other01_app.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FavouiriteCompendiumAdapter extends BaseAdapter {

    private Context mContext;
    private final int maxSize =4;

    LinkedList<CompendiumActivities> mData = new LinkedList<>();

    public FavouiriteCompendiumAdapter(Context context)
    {
        mContext = context;
    }


    public void addCompendium (CompendiumActivities ca)
    {
        if(getCount()== maxSize)
        {
            mData.removeLast();
            mData.addFirst(ca);
        }
        else if(getCount()<maxSize)
        {
            mData.addFirst(ca);
        }
        notifyDataSetChanged();
    }

    public void removeCompendium(CompendiumActivities ca)
    {
        mData.remove(ca);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null )
            {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(R.layout.compendium_adapter_row, null);
            }
        TextView compendiumNameTV = view.findViewById(R.id.compendium_activity_row_name_textview);
        compendiumNameTV.setText(mData.get(i).name);
        TextView compendiumMetsTV = view.findViewById(R.id.compendium_activity_row_name_mets);
        compendiumMetsTV.setText("METs:"+String.valueOf(mData.get(i).mets));

        return view;
    }
}
