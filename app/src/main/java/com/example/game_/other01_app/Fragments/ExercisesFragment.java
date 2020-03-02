package com.example.game_.other01_app.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game_.other01_app.Activities.ChangePreferencesActivity;
import com.example.game_.other01_app.Activities.ExerciseListActivity;
import com.example.game_.other01_app.Adapters.CategoriesListAdapter;
import com.example.game_.other01_app.Database.entities.Category;
import com.example.game_.other01_app.FragmentChangeListener;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.CategoriesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class ExercisesFragment extends Fragment {

    private CategoriesViewModel mCategoriesViewModel;
    private CategoriesListAdapter adapter;

    private TextView textView;
    private TextView title;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean firstTime = false;
        boolean changingPrefernces = false;

        mSharedPreferences = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        //Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_exercise_preferences, container, false);
        //Get the ViewModel
        mCategoriesViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(CategoriesViewModel.class);
        //Add the RecyclerView
        RecyclerView categoryRecyclerView = rootView.findViewById(R.id.categoryRecyclerView);

        textView = rootView.findViewById(R.id.exercise_welcome_textView);
        title = rootView.findViewById(R.id.exercise_preferences_title);

        final Bundle args = getArguments();
        if(args != null){
            firstTime = args.getBoolean("firstTime");
            if(firstTime) {
                setUpBackAndNextButtons(rootView);
            } else {
                TextView title = rootView.findViewById(R.id.exercise_preferences_title);
                title.setVisibility(View.GONE);
            }
            changingPrefernces = args.getBoolean("changingPreferences");
            if(changingPrefernces){
                changeTextToChangePreferences();
            }
        }


        adapter = new CategoriesListAdapter(getActivity(), changingPrefernces);
        categoryRecyclerView.setAdapter(adapter);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //Add an observer for the LiveData returned by getAllCategories
        mCategoriesViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                // Update the cached copy of the words in the adapter.
                adapter.setmCategories(categories);
            }
        });
        //Check arguments to see if this is the beginning of the app or not
        return rootView;
    }
    private void setUpBackAndNextButtons(View rootView) {

        TextView text = rootView.findViewById(R.id.filter_text);
        text.setText("Select the kinds of exercises you want to receive recommendations about.");

        //Get The Layout area
        LinearLayout layout = rootView.findViewById(R.id.exercise_pref_btn_zone);
        //Get the back button
        Button backButton = new Button(rootView.getContext());
        backButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        backButton.setText(R.string.back_btn_txt);
        backButton.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.addView(backButton);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPreviousFragment();
            }
        });

        //Get the next button
        Button nextButton = new Button(rootView.getContext());
        nextButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        nextButton.setText(R.string.next_btn_txt);
        nextButton.setGravity(Gravity.CENTER_HORIZONTAL);;
        layout.addView(nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getInterested().isEmpty()){
                    Toast toast = Toast.makeText(getContext().getApplicationContext(),
                            "You must select at least one.",
                            Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    mCategoriesViewModel.setPreferences(getInterested(),
                            getNotInterested());
                    mEditor.putBoolean("isFirstTime", false);
                    mEditor.putBoolean("needsTutorial", true);
                    mEditor.commit();
                    goToMainPage();
                }

            }
        });

        textView.setText(R.string.change_preferences_message);
        title.setText(R.string.exercise_preferences_title);
    }
    private void showPreviousFragment(){
        Objects.requireNonNull(getFragmentManager()).popBackStack();
    }


    public ArrayList<String> getInterested(){
        return adapter.getInterested();
    }
    public ArrayList<String> getNotInterested(){
        return adapter.getNotInterested();
    }

    private void goToMainPage() {
        Intent intent = new Intent(getActivity(), ExerciseListActivity.class);
        intent.putExtra("firstTimeBox", true);
        startActivity(intent);
    }

    public void changeTextToChangePreferences() {
        TextView text = rootView.findViewById(R.id.filter_text);
        text.setText("Select the kinds of exercises you want to receive recommendations about.");
    }
}
