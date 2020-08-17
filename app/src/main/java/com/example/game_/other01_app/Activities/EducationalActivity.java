package com.example.game_.other01_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.game_.other01_app.Fragments.EducationalFragment;
import com.example.game_.other01_app.Fragments.EducationalListFragment;
import com.example.game_.other01_app.R;

public class EducationalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_educational_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new EducationalListFragment())
                    .commitNow();
        }
    }
}