package com.example.game_.other01_app.Activities;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.example.game_.other01_app.ViewModels.HomeScreenViewModel;
import com.example.game_.other01_app.ViewModels.MessagesViewModel;
import com.example.game_.other01_app.ViewModels.TimeSetViewModel;
import com.example.game_.other01_app.ViewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This is the Home Screen ACTIVITY
 */

public class HomeScreenActivity extends AppCompatActivity {

    private HomeScreenViewModel mHomeScreenViewModel;
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
        setContentView(R.layout.home_screen);
        setTitle("CareFit");

        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        firstTimeBoxRequired = mSharedPreferences.getBoolean("needsTutorial", false);

        if (getIntent().getExtras() != null) {
            firstTimeBoxRequired = getIntent().getBooleanExtra("firstTimeBox", false);
        }


        //Creating the recycler view for the exercises x_x
        RecyclerView exRecyclerView = findViewById(R.id.exercise_recyclerview);
        exListAdapter = new ExerciseListAdapter(this);
        exRecyclerView.setAdapter(exListAdapter);
        exRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        //ViewModel creation
        mHomeScreenViewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
        //observeExercises();
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
//
//        if (user != null) {
//            //Working with the MessagesViewModel
//            if (firstTimeBoxRequired) {
//                //do something
//                showPopup();
//            } else {
//                messagesViewModel = new MessagesViewModel(mUserViewModel, mTimeSetViewModel, mCategoriesViewModel);
//                if (messagesViewModel.buildNotification()) {
//                    openMessageDialog(messagesViewModel.getPraiseList());
//                }
//            }
//        } else {
 //       }
    }

    @Override
    public void onBackPressed() {
        //Do nothing.
    }

}