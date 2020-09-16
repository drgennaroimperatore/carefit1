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
        EducationalFragment educationalFragment = new EducationalFragment();
        Bundle args = new Bundle();
        args.putInt("stage",0);
        educationalFragment.setArguments(args);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.educational, educationalFragment)
                    .commitNow();
        }

    }
    public void goToContentFragment(int i)
    {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        EducationalFragment educationalFragment = new EducationalFragment();
        Bundle args = new Bundle();
        args.putInt("stage",i);
        educationalFragment.setArguments(args);
        ft.replace(R.id.educational, educationalFragment, "EducationalFragment");
        ft.commitNow();

    }
}