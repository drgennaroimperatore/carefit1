package com.example.game_.other01_app.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.game_.other01_app.Activities.EducationalActivity;
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.entities.EducationalList;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.EducationalListReader;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A fragment representing a list of Items.
 */
public class EducationalListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<EducationalList> mEducationalList;
    private EducationalActivity mActivity;

    public EducationalListFragment() {}

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EducationalListFragment(EducationalActivity mActivity) {
        this.mActivity = mActivity;
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EducationalListFragment newInstance(int columnCount, EducationalActivity activity) {
        EducationalListFragment fragment = new EducationalListFragment(activity);

        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        fragment.mActivity = activity;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_educational_list_list,null);

       try {
            mEducationalList = new EducationalListReader(AppDatabase.getDatabase(getContext()).educationalDao()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Set the adapter


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       ListView listView = view.findViewById(R.id.educational_list_listView);
        ArrayAdapter<EducationalList> educationalListArrayAdapter = new ArrayAdapter<EducationalList>
                (getContext(), R.layout.fragment_educational_list,mEducationalList);
       listView.setAdapter(educationalListArrayAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            EducationalActivity activity = (EducationalActivity) getActivity();
           int stage= educationalListArrayAdapter.getItem(i).stageNumber;
           if(stage==1)
               stage=0;
            activity.goToContentFragment(stage);
           }
       });

    }
}