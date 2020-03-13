package com.example.game_.other01_app.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.game_.other01_app.Fragments.ExercisesFragment;
import com.example.game_.other01_app.Fragments.exerciseFragment;
import com.example.game_.other01_app.Fragments.homeFragment;
import com.example.game_.other01_app.Fragments.profileFragment;
import com.example.game_.other01_app.Fragments.progressFragment;
import com.example.game_.other01_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new homeFragment();
                    break;

                case R.id.progress:
                    fragment = new progressFragment();
                    break;

                case R.id.exercise_list:
                    fragment = new exerciseFragment();
                    break;

                case R.id.profile:
                    fragment = new profileFragment();
                    break;


            }
            return LoadFragment(fragment);
        }
    };

    private boolean LoadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadFragment(new homeFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



}
