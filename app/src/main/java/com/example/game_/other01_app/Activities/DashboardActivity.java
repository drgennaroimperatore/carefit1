package com.example.game_.other01_app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.R;

public class DashboardActivity extends Activity
{
    ImageView m_imageViewGoToPlanner,
            m_imageViewGoToQuickEx,
            m_imageViewGoToSettings,
            m_imageviewGoToProfile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        m_imageViewGoToPlanner = findViewById(R.id.dashboard_go_to_planner);
        m_imageviewGoToProfile = findViewById(R.id.dashboard_go_to_profile);

        m_imageviewGoToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(DashboardActivity.this, ProfileScreenActivity.class);
                startActivityForResult(intent4,1);
            }
        });

       m_imageViewGoToPlanner.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent3 = new Intent(DashboardActivity.this, PlannerActivity.class);
               startActivity(intent3);
           }
       });
        m_imageViewGoToQuickEx = findViewById(R.id.dashboard_go_to_quick);

        m_imageViewGoToQuickEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(DashboardActivity.this, ExerciseListActivity.class);
                startActivity(intent3);

            }
        });
    }
}
