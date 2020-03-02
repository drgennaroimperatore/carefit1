package com.example.game_.other01_app.Activities;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.game_.other01_app.Adapters.RecordListAdapter;
import com.example.game_.other01_app.Adapters.UserRecordsAdapter;
import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.Database.entities.TimeSet;
import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.TimeSetViewModel;
import com.example.game_.other01_app.ViewModels.UserViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecordsActivity extends AppCompatActivity {

    public static int RECORDS_ACTIVITY_REQUEST_CODE = 4;
    private TimeSetViewModel mTimeSetViewModel;
    private UserViewModel mUserViewModel;
    private SharedPreferences mSharedPreferences;

    private boolean tutorial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_page);

        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        tutorial = mSharedPreferences.getBoolean("needsTutorial", true);

        setTitle("Best Times");

        //get the viewmodel from the viewmodel provider
        mTimeSetViewModel = ViewModelProviders.of(this).get(TimeSetViewModel.class);
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        //Create the user recycler view
        RecyclerView userRecyclerView = findViewById(R.id.user_records_recycler_view);
        final UserRecordsAdapter userAdapter = new UserRecordsAdapter(this);
        userRecyclerView.setAdapter(userAdapter);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUserViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //Update the cached copy of the user in the adapter.
                userAdapter.setUser(user);
            }
        });

        //Create the recylcer view
        RecyclerView recordsRecyclerView = findViewById(R.id.record_recycler_view);
        final RecordListAdapter adapter = new RecordListAdapter(this);
        recordsRecyclerView.setAdapter(adapter);
        recordsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Add an observer for the LiveData returned by getAllRecords()
        mTimeSetViewModel.getAllTimeSets().observe(this, new Observer<List<TimeSet>>() {
            @Override
            public void onChanged(List<TimeSet> timeSets) {
                //Update the cached copy of the timesets in the adapter.
                adapter.setRecords(timeSets);
            }
        });

        if(tutorial) {
            showTutorialText();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_records, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.records_back:
                setResult(RESULT_OK);
                finish();
                return true;
            case R.id.records_help:
                showTutorialText();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showTutorialText(){
        final Dialog dialog = new Dialog(RecordsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Here, you can see your best exercises times, the highest " +
                "level of exercise you have tried, and how many days in a row you've been using " +
                "the app.\n\nThis info is used to highlight your exercise achievements with a " +
                "new message once a day.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setText("Show Message");
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                showSampleMessage();
            }
        });
        dialog.show();
    }

    private void showSampleMessage(){
        User user = null;
        try {
            user = mUserViewModel.getUserNotLive();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Dialog dialog = new Dialog(RecordsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_daily_message);
        TextView text = dialog.findViewById(R.id.daily_achievement);
        text.setText("Hi, " + user.getUserName() + " last time you improved your total exercise time by " +
                DateTimeAssist.longToTimerString(user.getRecentTotalExerciseTime()) + ". Well done!");
        TextView tipText = dialog.findViewById(R.id.daily_tip);
        tipText.setText("You will receive messages like this once per day when you start up CareFit.");
        Button dialogBtn = dialog.findViewById(R.id.daily_okay_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                showFinalText();
            }
        });
        dialog.show();
    }

    private void showFinalText(){
        final Dialog dialog = new Dialog(RecordsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Press HOME to go back.");
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
