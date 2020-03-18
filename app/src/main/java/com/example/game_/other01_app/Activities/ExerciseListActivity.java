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
import android.widget.Toast;

import com.example.game_.other01_app.Adapters.ExerciseListAdapter;
import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.CategoriesViewModel;
import com.example.game_.other01_app.ViewModels.ExerciseListViewModel;
import com.example.game_.other01_app.ViewModels.MessagesViewModel;
import com.example.game_.other01_app.ViewModels.TimeSetViewModel;
import com.example.game_.other01_app.ViewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This is the com.example.game_.other01_app.Activities.UserInterface.Exercise Screen ACTIVITY
 */
public class ExerciseListActivity extends AppCompatActivity {

    private ExerciseListViewModel mExerciseViewModel;
    private UserViewModel mUserViewModel;
    private CategoriesViewModel mCategoriesViewModel;
    private MessagesViewModel messagesViewModel;
    private TimeSetViewModel mTimeSetViewModel;
    private ExerciseListAdapter exListAdapter;
    private String filters = "";
    private User user;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private boolean firstTimeBoxRequired;

    private Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise_list);
       // setContentView(R.layout.fragment_home);
        setTitle("CareFit");

        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        firstTimeBoxRequired = mSharedPreferences.getBoolean("needsTutorial", false);

        if(getIntent().getExtras() != null){
            firstTimeBoxRequired = getIntent().getBooleanExtra("firstTimeBox", false);
        }


        //Creating the recycler view for the exercises
        RecyclerView exRecyclerView = findViewById(R.id.exercise_recyclerview);
        exListAdapter = new ExerciseListAdapter(this);
        exRecyclerView.setAdapter(exListAdapter);
        exRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //ViewModel creation
        mExerciseViewModel = ViewModelProviders.of(this).get(ExerciseListViewModel.class);
        observeExercises();
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mCategoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        mTimeSetViewModel = ViewModelProviders.of(this).get(TimeSetViewModel.class);

        user = null;
        try {
            user = mUserViewModel.getUserNotLive();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(user != null) {
            //Working with the MessagesViewModel
            if (firstTimeBoxRequired) {
                //do something
                showPopup();
            } else {
                messagesViewModel = new MessagesViewModel(mUserViewModel, mTimeSetViewModel, mCategoriesViewModel);
                if (messagesViewModel.buildNotification()) {
                    openMessageDialog(messagesViewModel.getPraiseList());
                }
            }
        }  else {
        }

//        BottomNavigationView bar = findViewById(R.id.bottom_nav);
//
//        bar.setOnNavigationItemSelectedListener(item -> {
//            //Fragment fragment=null;
//            switch (item.getItemId()) {
//                case R.id.home:
//                    Toast.makeText(ExerciseListActivity.this, "home", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(this, MainActivity.class);
//                    startActivity(intent);
//                    break;
//
//                case R.id.progress:
//                    Toast.makeText(ExerciseListActivity.this, "progress", Toast.LENGTH_SHORT).show();
//                    break;
//
//                case R.id.exercise_list:
//                    Toast.makeText(ExerciseListActivity.this, "exercise", Toast.LENGTH_SHORT).show();
//
//                    break;
//
//                case R.id.profile:
//                    Toast.makeText(ExerciseListActivity.this, "profile", Toast.LENGTH_SHORT).show();
//                    break;
//
//
//            }
//            return true;
//        });




    }

    @Override
    public void onBackPressed() {
        //Do nothing.
    }

    @Override
    protected void onResume() {
        super.onResume();
        messagesViewModel = new MessagesViewModel(mUserViewModel, mTimeSetViewModel, mCategoriesViewModel);
        if (messagesViewModel.buildNotification()) {
            openMessageDialog(messagesViewModel.getPraiseList());
        }
    }

    private void openMessageDialog(List<String> messageList) {
        final Dialog dialog = new Dialog(ExerciseListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_daily_message);
        TextView text = dialog.findViewById(R.id.daily_achievement);
        text.setText("Hi, " + user.getUserName() + "! " + messageList.get(0));
        TextView tipText = dialog.findViewById(R.id.daily_tip);
        tipText.setText(messageList.get(1));
        Button dialogBtn = dialog.findViewById(R.id.daily_okay_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    /**
     * Alerts observers when list of exercises has changed
     */
    private void observeExercises(){
        mExerciseViewModel.getAllExercises().removeObservers(this);
        mExerciseViewModel.getAllExercises().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                //Update the cached copy of the exercises in the adapter.
                exercises = mExerciseViewModel.filterExercises(exercises, filters);
                exListAdapter.setExercises(exercises);
                TextView warningTextView = findViewById(R.id.no_items_text_view);
                if(exercises.isEmpty()) {
                    warningTextView.setVisibility(View.VISIBLE);
                    warningTextView.setText(R.string.no_exercise_data_text);
                } else {
                    warningTextView.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        this.menu = menu;
        enableEverything();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                startFilterActivity();
                return true;
            case R.id.action_preferences:
                startPreferencesActivity();
                return true;
            case R.id.action_records:
                startRecordsActivity();
                return true;
            case R.id.action_reminders:
                startRemindersActivity();
                return true;
            case R.id.action_help:
                showPopup();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startFilterActivity() {
        Intent intent = new Intent(this, FilterExercisesActivity.class);
        startActivityForResult(intent, FilterExercisesActivity.FILTER_ACTIVITY_REQUEST_CODE);
    }

    private void startPreferencesActivity() {
        Intent intent = new Intent(this, ChangePreferencesActivity.class);
        startActivityForResult(intent, 1);
    }

    private void startRemindersActivity() {
        Intent intent = new Intent(this, RemindersListActivity.class);
        startActivityForResult(intent, RemindersListActivity.REMINDERS_LIST_REQUEST_CODE);
    }

    private void startRecordsActivity() {
        Intent intent = new Intent(this, RecordsActivity.class);
        startActivityForResult(intent, RecordsActivity.RECORDS_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ChangePreferencesActivity.CHANGE_PREFERENCES_ACTIVITY_REQUEST_CODE &&
        resultCode == RESULT_OK) {
            ArrayList<String> interested = Objects.requireNonNull(data.getExtras()).getStringArrayList(ChangePreferencesActivity.INTERESTED_LIST_REPLY);
            ArrayList<String> notInterested = data.getExtras().getStringArrayList(ChangePreferencesActivity.NOT_INTERESTED_LIST_REPLY);
            mCategoriesViewModel.setPreferences(interested, notInterested);
            User user = Objects.requireNonNull(data).getParcelableExtra(ChangePreferencesActivity.USER_REPLY);
            mUserViewModel.createOrUpdateUser(user);
            if(firstTimeBoxRequired) {
                showAfterChangePreferences();
            }
        } else if (requestCode == ExerciseInstructionsActivity.EXERCISE_INSTRUCTIONS_ACTIVITY_REQUEST_CODE &&
        resultCode == RESULT_OK) {
            enableEverything();
            mTimeSetViewModel.updateTimeSet(
                    Objects.requireNonNull(data).getStringExtra(ExerciseInstructionsActivity.EXERCISE_NAME_REPLY),
                    data.getLongExtra(ExerciseInstructionsActivity.EXERCISE_LONG_REPLY, 0)
            );
            mUserViewModel.increaseTodaysTotal(
                    data.getLongExtra(ExerciseInstructionsActivity.EXERCISE_LONG_REPLY, 0),
                    data.getStringExtra(ExerciseInstructionsActivity.EXERCISE_INTENSITY_REPLY));
            if(firstTimeBoxRequired) {
                showAfterExercise(
                        data.getLongExtra(ExerciseInstructionsActivity.EXERCISE_LONG_REPLY, 0),
                        Objects.requireNonNull(data).getStringExtra(ExerciseInstructionsActivity.EXERCISE_NAME_REPLY));
            }
        } else if (requestCode == FilterExercisesActivity.FILTER_ACTIVITY_REQUEST_CODE &&
        resultCode == RESULT_OK) {
            Toast.makeText(
                    getApplicationContext(),
                    "Filtering Complete.",
                    Toast.LENGTH_LONG).show();
            filters = Objects.requireNonNull(data).getStringExtra(FilterExercisesActivity.FILTER_REPLY);
            observeExercises();
            if(firstTimeBoxRequired) {
                showAfterFilters();
            }
        } else if (requestCode == RecordsActivity.RECORDS_ACTIVITY_REQUEST_CODE &&
        resultCode == RESULT_OK) {
            if(firstTimeBoxRequired) {
                showAfterRecords();
            }
        } else if(requestCode == RemindersListActivity.REMINDERS_LIST_REQUEST_CODE &&
        resultCode == RESULT_OK) {
            if(firstTimeBoxRequired) {
                showAfterReminders();
            }
        }
    }

    private void showAfterChangePreferences() {
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();
            final Dialog dialog = new Dialog(ExerciseListActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_first_exercise);
            TextView text = dialog.findViewById(R.id.custom_exercise_text);
            text.setText("That's it! Enjoy using Carefit.\n\nIf you get stuck at any point, look for " +
                    "the HELP button on any page.");
            Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
            dialogBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
            firstTimeBoxRequired = false;
            mEditor.putBoolean("needsTutorial", false);
            mEditor.commit();
            enableEverything();
            dialog.show();
    }

    private void enableEverything(){
        menu.findItem(R.id.overflow).setEnabled(true);
        menu.findItem(R.id.action_preferences).setEnabled(true);
        menu.findItem(R.id.action_reminders).setEnabled(true);
        menu.findItem(R.id.action_filter).setEnabled(true);
        menu.findItem(R.id.action_records).setEnabled(true);
        menu.findItem(R.id.action_help).setEnabled(true);
    }

    private void showPopup() {
        final Dialog dialog = new Dialog(ExerciseListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_opening_message);
        TextView text = dialog.findViewById(R.id.custom_opening_text);
        text.setText("Hi there, " + user.getUserName() + ", welcome to CareFit.\n" +
                "CareFit is designed to help you fit in more exercises while you look" +
                " after " + user.getCareName() + ".\n");
        Button dialogBtn = dialog.findViewById(R.id.custom_opening_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                showDisclaimer();
            }
        });
        dialog.show();
    }

    private void showDisclaimer(){
        final Dialog dialog = new Dialog(ExerciseListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_disclaimer);
        Button dialogBtn = dialog.findViewById(R.id.disclaimer_dialog_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        if(firstTimeBoxRequired){
            disableMenu();
        }
        dialog.show();
    }

    private void disableMenu(){
        menu.findItem(R.id.overflow).setEnabled(false);
    }

    private void showAfterExercise(Long exerciseTime, String exerciseName){
        final Dialog dialog = new Dialog(ExerciseListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Good job, you spent\n" + DateTimeAssist.longToTimerString(exerciseTime) +
                "\nperforming " + exerciseName + ". You can view your exercise times on the " +
                "Records screen under the MENU. Let's go there next.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        if(firstTimeBoxRequired){
            disableAfterExercise();
        }
        dialog.show();
    }

    private void disableAfterExercise(){
        menu.findItem(R.id.overflow).setEnabled(true);
        menu.findItem(R.id.action_preferences).setEnabled(false);
        menu.findItem(R.id.action_reminders).setEnabled(false);
        menu.findItem(R.id.action_filter).setEnabled(false);
        menu.findItem(R.id.action_help).setEnabled(false);
    }

    private void showAfterRecords(){
        final Dialog dialog = new Dialog(ExerciseListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Now let's set your first reminder to use the app. Go to the Reminders page " +
                "in the MENU.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        if(firstTimeBoxRequired){
            disableAfterRecords();
        }
        dialog.show();
    }

    private void disableAfterRecords(){
        menu.findItem(R.id.overflow).setEnabled(true);
        menu.findItem(R.id.action_preferences).setEnabled(false);
        menu.findItem(R.id.action_reminders).setEnabled(true);
        menu.findItem(R.id.action_filter).setEnabled(false);
        menu.findItem(R.id.action_records).setEnabled(false);
        menu.findItem(R.id.action_help).setEnabled(false);
    }

    private void showAfterReminders(){
        final Dialog dialog = new Dialog(ExerciseListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Now let's Search for an exercise that fits some of the categories from before. " +
                "Go to Search in the MENU.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        if(firstTimeBoxRequired){
            disableAfterReminders();
        }
        dialog.show();
    }

    private void disableAfterReminders(){
        menu.findItem(R.id.overflow).setEnabled(true);
        menu.findItem(R.id.action_preferences).setEnabled(false);
        menu.findItem(R.id.action_reminders).setEnabled(false);
        menu.findItem(R.id.action_filter).setEnabled(true);
        menu.findItem(R.id.action_records).setEnabled(false);
        menu.findItem(R.id.action_help).setEnabled(false);
    }

    private void showAfterFilters(){
        final Dialog dialog = new Dialog(ExerciseListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Finally, to change your name and the exercise preferences you saw at the start, " +
                "go to Change Preferences in the MENU.\n\n Let's go there now.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        if(firstTimeBoxRequired){
            disableAfterFilters();
        }
        dialog.show();
    }

    private void disableAfterFilters(){
        menu.findItem(R.id.overflow).setEnabled(true);
        menu.findItem(R.id.action_preferences).setEnabled(true);
        menu.findItem(R.id.action_reminders).setEnabled(false);
        menu.findItem(R.id.action_filter).setEnabled(false);
        menu.findItem(R.id.action_records).setEnabled(false);
        menu.findItem(R.id.action_help).setEnabled(false);
    }






}
