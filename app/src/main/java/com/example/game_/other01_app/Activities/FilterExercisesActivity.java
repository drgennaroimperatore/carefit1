package com.example.game_.other01_app.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import com.example.game_.other01_app.AssistanceClasses.ListAssist;
import com.example.game_.other01_app.Fragments.ExercisesFragment;
import com.example.game_.other01_app.R;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FilterExercisesActivity extends AppCompatActivity {

    public static final int FILTER_ACTIVITY_REQUEST_CODE = 3;
    public static final String FILTER_REPLY = "filterReply";

    private ExercisesFragment exercisesFragment;
    private SharedPreferences mSharedPreferences;

    private boolean tutorial;
    private Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_exercises_page);

        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        tutorial = mSharedPreferences.getBoolean("needsTutorial", true);

        setTitle("Search");

        Bundle args = new Bundle();
        args.putBoolean("firstTime", false);

        exercisesFragment = new ExercisesFragment();
        exercisesFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.filterPage_FragHolder, exercisesFragment).commit();

        if(tutorial) {
            showTutorial();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filter, menu);
        this.menu = menu;
        if (tutorial) {
            disableButtons();
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void disableButtons(){
        menu.findItem(R.id.filterbar_back_btn).setEnabled(false);
    }

    private void enableButtons(){
        menu.findItem(R.id.filterbar_back_btn).setEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterbar_back_btn:
                goBackToMainPage(false, null);
                return true;
            case R.id.filterbar_filter_btn:
                ArrayList<String> interested = exercisesFragment.getInterested();
                enableButtons();
                goBackToMainPage(true, interested);
                return true;
            case R.id.filterbar_help_btn:
                showTutorial();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goBackToMainPage(boolean filtersSet, ArrayList<String> interested) {
        Intent replyIntent = new Intent();
        if(filtersSet && interested != null){
            replyIntent.putExtra(FILTER_REPLY, ListAssist.convertListToString(interested));
            setResult(RESULT_OK, replyIntent);
        } else {
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    private void showTutorial(){
        final Dialog dialog = new Dialog(FilterExercisesActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Follow the instructions on the page. If you feel stuck, try searching for " +
                "Legs and Strength. Press SET to finish.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
