package com.example.game_.other01_app.Activities;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game_.other01_app.Adapters.ExerciseListAdapter;
import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.Fragments.ExercisesFragment;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.CategoriesViewModel;
import com.example.game_.other01_app.ViewModels.ExerciseListViewModel;
import com.example.game_.other01_app.ViewModels.MessagesViewModel;
import com.example.game_.other01_app.ViewModels.TimeSetViewModel;
import com.example.game_.other01_app.ViewModels.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;

import java.util.List;


/**
 * This is the com.example.game_.other01_app.Activities.UserInterface.Home Screen ACTIVITY
 */

public class HomeScreenActivity extends AppCompatActivity {
    private ExerciseListViewModel mExerciseViewModel;
    private UserViewModel mUserViewModel;
    private CategoriesViewModel mCategoriesViewModel;
    private MessagesViewModel messagesViewModel;
    private TimeSetViewModel mTimeSetViewModel;
    private ExerciseListAdapter exListAdapter;
    private String filters = "";
    private User user;

    private ActionBar actionBar;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private boolean firstTimeBoxRequired;

    private Menu menu;

    public static final int FILTER_ACTIVITY_REQUEST_CODE = 3;
    public static final String FILTER_REPLY = "filterReply";
    private ExercisesFragment exercisesFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable((new ColorDrawable(Color.parseColor("#FDECE4"))));

//        TextView title = (TextView) findViewById(R.id.hometext);
//        title.setText("HOME PAGE");


        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        firstTimeBoxRequired = mSharedPreferences.getBoolean("needsTutorial", false);

        if(getIntent().getExtras() != null){
            firstTimeBoxRequired = getIntent().getBooleanExtra("firstTimeBox", false);
        }

        Bundle args = new Bundle();
        args.putBoolean("firstTime", true);
        exercisesFragment = new ExercisesFragment();
        exercisesFragment.setArguments(args);

        BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.bottomNavView_bar);

        Menu menu = bottomView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

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
            // }  else {
        }

        bottomView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override

                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                //openHome();
                                break;
                            case R.id.navigation_progress:
                                openProgress();
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
        Intent intent1 = new Intent(HomeScreenActivity.this, HomeScreenActivity.class);
        startActivity(intent1);
    }

    public void openProgress(){
        Intent intent2 = new Intent(HomeScreenActivity.this, RecordsActivity.class);
        startActivity(intent2);
    }

    public void openExercise(){
        Intent intent3 = new Intent(HomeScreenActivity.this, ExerciseListActivity.class);
        startActivity(intent3);
    }

    public void openProfile(){
        Intent intent4 = new Intent(HomeScreenActivity.this, ProfileScreenActivity.class);
        startActivityForResult(intent4,1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                //startFilterActivity();
                return true;
            case R.id.action_preferences:
                //startPreferencesActivity();
                return true;
            case R.id.action_records:
                //startRecordsActivity();
                return true;
            case R.id.action_reminders:
                //startRemindersActivity();
                return true;
            case R.id.action_help:
                showPopup();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void openMessageDialog(List<String> messageList) {
        final Dialog dialog = new Dialog(HomeScreenActivity.this);
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

    private void showPopup() {
        final Dialog dialog = new Dialog(HomeScreenActivity.this);
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
        final Dialog dialog = new Dialog(HomeScreenActivity.this);
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
          //  disableMenu();
        }
        dialog.show();
    }
}