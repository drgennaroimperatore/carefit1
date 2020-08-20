package com.example.game_.other01_app.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.game_.other01_app.Adapters.PastWeekRowAdapter;
import com.example.game_.other01_app.DataObjects.PastWeekRow;
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.PastWeekReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreviousWeekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviousWeekFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<PastWeekRow> mPastWeeks;

    public PreviousWeekFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreviousWeekFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreviousWeekFragment newInstance(String param1, String param2) {
        PreviousWeekFragment fragment = new PreviousWeekFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        try {
            mPastWeeks = new PastWeekReader(AppDatabase.getDatabase(getContext()).weeklyPlanDao()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_week, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView pastWeeksListView = view.findViewById(R.id.previous_week_list_view);

        TextView noProgressHeaderTV = view.findViewById(R.id.fragment_prev_week_no_progress_message_textview);
        if(mPastWeeks.size()==0)
            noProgressHeaderTV.setVisibility(View.VISIBLE);
        else
            if(mPastWeeks.size()>0)
                noProgressHeaderTV.setVisibility(View.GONE);

        PastWeekRowAdapter pastWeekRowAdapter = new PastWeekRowAdapter(getContext(),0, (ArrayList<PastWeekRow>) mPastWeeks);
        pastWeeksListView.setAdapter(pastWeekRowAdapter);
    }
}