package com.example.game_.other01_app.Fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.FragmentChangeListener;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.UserViewModel;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class SetNamesFragment extends Fragment {

    private UserViewModel mUserViewModel;
    private EditText mEditYourName;
    private EditText mEditTheirName;
    private CheckBox mEditCheckbox;

    private TextView title;
    private TextView message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.user_recyclerview_item, container, false);
        //Get The essential views from the rootview
        title = rootView.findViewById(R.id.welcomeSurveyTextView);
        title.setText(R.string.welcome_title);

        message = rootView.findViewById(R.id.welcomeSurveyNamesReminder);
        message.setText(R.string.change_preferences_message);

        mEditYourName = rootView.findViewById(R.id.enter_your_name);
        mEditTheirName = rootView.findViewById(R.id.enter_their_name);
        mEditCheckbox = rootView.findViewById(R.id.together_checkbox);
        //Programmatically add the next button for this fragment
        LinearLayout layout = rootView.findViewById(R.id.userView_Layout);
        Button nextBtn = new Button (rootView.getContext());
        nextBtn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        nextBtn.setText(R.string.next_btn_txt);
        nextBtn.setId(R.id.set_names_next);
        nextBtn.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.addView(nextBtn);
        //Get the ViewModel
        mUserViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(UserViewModel.class);
        //Add a new on click listener for the button that adds the U/N to the db
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do not show the next page if the essential info hasnt
                //been filled in!!
                if(yourNameIsFilledIn() && theirNameIsFilledIn()) {
                    User user = new User(
                            1,
                            getUserName(),
                            getPatientName(),
                            getExerciseTogether(),
                            System.currentTimeMillis(),
                            0L, 0L, "low",
                            "low", "", 0
                    );
                    mUserViewModel.createOrUpdateUser(user);
                    layout.removeView(nextBtn);
                    showNextFragment();
                }
            }
        });
        return rootView;
    }

    private boolean theirNameIsFilledIn() {
        if(getPatientName().trim().matches("")){
            Toast.makeText(getActivity(), R.string.patient_name_error,
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean yourNameIsFilledIn() {
        if(getUserName().trim().matches("")){
            Toast.makeText(getActivity(), R.string.your_name_error,
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean getExerciseTogether() {
        return mEditCheckbox.isChecked();
    }

    private String getPatientName() {
        return mEditTheirName.getText().toString();
    }

    private String getUserName() {
        return mEditYourName.getText().toString();
    }

    private void showNextFragment() {
        Fragment fr = new ExercisesFragment();
        FragmentChangeListener fc = (FragmentChangeListener)getActivity();
        Objects.requireNonNull(fc).replaceFragment(fr);
    }

}
