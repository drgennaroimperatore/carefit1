package com.example.game_.other01_app.Activities;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
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
 * This is the Profile Screen ACTIVITY
 */

public class ProfileScreenActivity extends AppCompatActivity {
    private UserViewModel mUserViewModel;
    private CategoriesViewModel mCategoriesViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
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