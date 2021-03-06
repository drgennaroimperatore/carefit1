package com.example.game_.other01_app.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.Fragments.ChangeNamesFragment;
import com.example.game_.other01_app.Fragments.ExercisesFragment;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
/**
 * This is the profile activity - the profile screen for the app
 */
public class ProfileScreenActivity extends AppCompatActivity {
    ActionBar actionBar;
    public static final String USER_REPLY = "preferenceUserReply";

    public static final int CHANGE_PREFERENCES_ACTIVITY_REQUEST_CODE = 1;
    public static final String INTERESTED_LIST_REPLY = "interestedReply";
    public static final String NOT_INTERESTED_LIST_REPLY = "notInterestedReply";

    private UserViewModel mUserViewModel;
    private ChangeNamesFragment changeNamesFragment;
    private ExercisesFragment exercisesFragment;
    private User oldUser = null;
    private SharedPreferences mSharedPreferences;
    private boolean tutorial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable((new ColorDrawable(Color.parseColor("#FEC282"))));

        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        tutorial = mSharedPreferences.getBoolean("needsTutorial", true);
        
        setTitle("Change Preferences");
        BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.bottomNavView_bar);

        Menu menu = bottomView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        try {
            oldUser = mUserViewModel.getUserNotLive();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        changeNamesFragment = new ChangeNamesFragment();
        exercisesFragment = new ExercisesFragment();

        Bundle args = new Bundle();
        args.putBoolean("firstTime", false);
        args.putBoolean("changingPreferences", true);
        exercisesFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.preferencePage_fragHolder, changeNamesFragment).commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.preferencePage_fragHolder, exercisesFragment).commit();

        if(tutorial) {
            showTutorial();
        }

        bottomView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                openHome();
                                break;
                            case R.id.navigation_progress:
                                openProgress();
                                break;
                            case R.id.navigation_exercise:
                                openExercise();
                                break;
                            case R.id.navigation_profile:
                                //openProfile();
                                break;
                        }
                        return false;

                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_change_preferences, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.preferencePage_help_btn:
                showTutorial();
                return true;
            case R.id.preferencePage_save_btn:
                saveDecisions();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void openHome(){
        Intent intent1 = new Intent(ProfileScreenActivity.this, HomeScreenActivity.class);
        startActivity(intent1);
    }

    public void openProgress(){
        Intent intent2 = new Intent(ProfileScreenActivity.this, RecordsActivity.class);
        startActivity(intent2);
    }

    public void openExercise(){
        Intent intent3 = new Intent(ProfileScreenActivity.this, ExerciseListActivity.class);
        startActivity(intent3);
    }

    public void openProfile(){
        Intent intent4 = new Intent(ProfileScreenActivity.this, ProfileScreenActivity.class);
        startActivityForResult(intent4,1);
    }



    private void saveDecisions(){
        Intent replyIntent = new Intent();
        if(changeNamesFragment.getUserName().trim().isEmpty()){
            Toast.makeText(
                    getApplicationContext(),
                    R.string.your_name_error,
                    Toast.LENGTH_LONG
            ).show();
        } else if (changeNamesFragment.getPatientName().trim().isEmpty()) {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.patient_name_error,
                    Toast.LENGTH_LONG
            ).show();
        } else if(exercisesFragment.getInterested().isEmpty()){
            Toast.makeText(
                    getApplicationContext(),
                    R.string.select_one_text,
                    Toast.LENGTH_LONG
            ).show();                }
        else {
            replyIntent.putStringArrayListExtra(INTERESTED_LIST_REPLY, exercisesFragment.getInterested());
            replyIntent.putStringArrayListExtra(NOT_INTERESTED_LIST_REPLY, exercisesFragment.getNotInterested());
            User user = createUserEntity();
            mUserViewModel.createOrUpdateUser(user);
            replyIntent.putExtra(USER_REPLY, user);
            setResult(RESULT_OK, replyIntent);
            finish();
        }
    }

    /**
     * Creates a 'new' User object to save over the old info.
     * @return the created User object
     */
    private User createUserEntity() {
        User newUser = null;
        if(oldUser == null){
            newUser = new User(
                    1,
                    changeNamesFragment.getUserName(),
                    changeNamesFragment.getPatientName(),
                    changeNamesFragment.getExerciseTogether(),
                    System.currentTimeMillis(),
                    0L, 0L, "low",
                    "low", "", 0
                    );
        } else {
            newUser = new User(
                    1,
                    changeNamesFragment.getUserName(),
                    changeNamesFragment.getPatientName(),
                    changeNamesFragment.getExerciseTogether(),
                    oldUser.getLast_log_in(),
                    oldUser.getRecentTotalExerciseTime(),
                    oldUser.getBestTotalExerciseTime(),
                    oldUser.getBestHighestIntensity(),
                    oldUser.getRecentHighestIntensity(),
                    oldUser.getTriedExercises(),
                    oldUser.getStreak()
                    );
        }
        return newUser;
    }

    private void showTutorial(){
        final Dialog dialog = new Dialog(ProfileScreenActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("These preferences affect the kind of daily tips and recommendations you receive " +
                "when you use the app.\n\nPress SAVE to exit.");
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
