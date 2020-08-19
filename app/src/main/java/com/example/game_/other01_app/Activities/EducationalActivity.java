package com.example.game_.other01_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.game_.other01_app.Database.entities.EducationalList;
import com.example.game_.other01_app.Fragments.EducationalFragment;
import com.example.game_.other01_app.Fragments.EducationalListFragment;
import com.example.game_.other01_app.R;

public class EducationalActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.educational, EducationalListFragment.newInstance(1,this))
                    .commitNow();
        }

    }

    public void goToContentFragment()
    {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.educational, new EducationalFragment(), "EducationalFragment");
        ft.commitNow();

    }
}