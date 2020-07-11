package com.example.game_.other01_app.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.game_.other01_app.Adapters.RecordListAdapter;
import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.Database.entities.TimeSet;
import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.TimeSetViewModel;
import com.example.game_.other01_app.ViewModels.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * This is the records activity - the records page for the app
 */
public class RecordsActivity extends AppCompatActivity {
    ActionBar actionBar;
    public static int RECORDS_ACTIVITY_REQUEST_CODE = 4;
    private TimeSetViewModel mTimeSetViewModel;
    private UserViewModel mUserViewModel;
    private SharedPreferences mSharedPreferences;

    private boolean tutorial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_page);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FA8305")));




        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        tutorial = mSharedPreferences.getBoolean("needsTutorial", true);


        //get the viewmodel from the viewmodel provider
        mTimeSetViewModel = ViewModelProviders.of(this).get(TimeSetViewModel.class);
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

//        //Create the user recycler view
//        RecyclerView userRecyclerView = findViewById(R.id.user_records_recycler_view);
//        final UserRecordsAdapter userAdapter = new UserRecordsAdapter(this);
//        userRecyclerView.setAdapter(userAdapter);
//        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mUserViewModel.getUser().observe(this, new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                //Update the cached copy of the user in the adapter.
//                userAdapter.setUser(user);
//            }
//        });

        //Create the recycler view
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

        //set up the bottom navigation for each page
        BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.bottomNavView_bar);

        Menu menu = bottomView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomView.setOnNavigationItemSelectedListener(

                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override

                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                openHome();
                                break;
                            case R.id.navigation_progress:
                                //openProgress();
                                break;
                            case R.id.navigation_exercise:
                                openExercise();
                                break;
                            case R.id.navigation_profile:
                                openProfile();
                                break;
                        }
                        return false;

                    }
                });
    }


    public void openHome(){
        Intent intent1 = new Intent(RecordsActivity.this, HomeScreenActivity.class);
        startActivity(intent1);
    }

    public void openProgress(){
        Intent intent2 = new Intent(RecordsActivity.this, RecordsActivity.class);
        startActivity(intent2);
    }

    public void openExercise(){
        Intent intent3 = new Intent(RecordsActivity.this, ExerciseListActivity.class);
        startActivity(intent3);
    }

    public void openProfile(){
        Intent intent4 = new Intent(RecordsActivity.this, ProfileScreenActivity.class);
        startActivityForResult(intent4,1);
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
        text.setText("Here, you can see your records for each exercise " +
                "\nThis info is used to highlight your exercise achievements");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setText("Okay");
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
               // showSampleMessage();
            }
        });
        dialog.show();
    }

}
