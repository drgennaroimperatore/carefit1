package com.example.game_.other01_app.Activities;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.CategoriesViewModel;
import com.example.game_.other01_app.ViewModels.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;


/**
 * This is the profileFragment Screen ACTIVITY
 */

public class ProfileScreenActivity extends AppCompatActivity {
    private UserViewModel mUserViewModel;
    private CategoriesViewModel mCategoriesViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.bottomNavView_bar);

        Menu menu = bottomView.getMenu();
        MenuItem menuItem = menu.getItem(3);
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


    public void openHome(){
        Intent intent1 = new Intent(ProfileScreenActivity.this, HomeScreenActivity.class);
        startActivity(intent1);
    }

    public void openProgress(){
        Intent intent2 = new Intent(ProfileScreenActivity.this, ProgressScreenActivity.class);
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


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ChangePreferencesActivity.CHANGE_PREFERENCES_ACTIVITY_REQUEST_CODE &&
                resultCode == RESULT_OK) {
            ArrayList<String> interested = Objects.requireNonNull(data.getExtras()).getStringArrayList(ChangePreferencesActivity.INTERESTED_LIST_REPLY);
            ArrayList<String> notInterested = data.getExtras().getStringArrayList(ChangePreferencesActivity.NOT_INTERESTED_LIST_REPLY);
            mCategoriesViewModel.setPreferences(interested, notInterested);
            User user = Objects.requireNonNull(data).getParcelableExtra(ChangePreferencesActivity.USER_REPLY);
            mUserViewModel.createOrUpdateUser(user);

        }
    }
}