package com.example.game_.other01_app.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TimePicker;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.AssistanceClasses.ListAssist;
import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreateReminderFragment extends Fragment {

    private TimePicker timePicker;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int id = 0;
        String oldTime = "";
        List<String> daysRepeating;
        boolean editing = false;

        Intent intent = getActivity().getIntent();
        if(intent.hasExtra("id")){
            id = intent.getIntExtra("id", 0);
        }
        if(intent.hasExtra("daysRepeating")){
            daysRepeating = ListAssist.convertStringToListOf(
                    intent.getStringExtra("daysRepeating")
            );
        }
        if(intent.hasExtra("oldTime")){
            oldTime = intent.getStringExtra("oldTime");
        }
        if (intent.hasExtra("editing")){
            editing = intent.getBooleanExtra("editing", false);
        }

        //Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_reminder, container, false);
        timePicker = rootView.findViewById(R.id.time_picker);

        if (editing){
            try {
                timePicker.setCurrentHour(DateTimeAssist.getHourFromString(oldTime));
                timePicker.setCurrentMinute(DateTimeAssist.getMinuteFromString(oldTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return rootView;
    }

    /**
     * @return a new Reminder created using info from the timepicker and checkboxes
     */
    public Reminder createReminderEntitiy(){
        String dates = getCheckedDays();
        int timeHrs = timePicker.getCurrentHour();
        int timeMins = timePicker.getCurrentMinute();

        return new Reminder(
                timeHrs,
                timeMins,
                dates
        );
    }

    /**
     * @return a List of weekdays that have been checked in String format
     */
    public String getCheckedDays() {
        List<String> selectedDays = new ArrayList<>();
        List<CheckBox> checkBoxes = getListOfCheckBoxes();
        for(CheckBox checkBox : checkBoxes){
            if(checkBox.isChecked()){
                selectedDays.add(checkBox.getText().toString());
            }
        }
        return ListAssist.convertListToString(selectedDays);
    }

    private List<CheckBox> getListOfCheckBoxes() {
        List<CheckBox> checkBoxes = new ArrayList<>();
        CheckBox monday = rootView.findViewById(R.id.mondayCheck);
        checkBoxes.add(monday);
        CheckBox tuesday = rootView.findViewById(R.id.tuesdayCheck);
        checkBoxes.add(tuesday);
        CheckBox wednesday = rootView.findViewById(R.id.wednesdayCheck);
        checkBoxes.add(wednesday);
        CheckBox thursday = rootView.findViewById(R.id.thursdayCheck);
        checkBoxes.add(thursday);
        CheckBox friday = rootView.findViewById(R.id.fridayCheck);
        checkBoxes.add(friday);
        CheckBox saturday = rootView.findViewById(R.id.saturdayCheck);
        checkBoxes.add(saturday);
        CheckBox sunday = rootView.findViewById(R.id.sundayCheck);
        checkBoxes.add(sunday);
        return checkBoxes;
    }


}
