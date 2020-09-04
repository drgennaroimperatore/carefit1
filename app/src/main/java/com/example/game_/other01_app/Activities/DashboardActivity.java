package com.example.game_.other01_app.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.EventSystem.DashboardDatabaseWatcher;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.EducationalListReader;

import java.util.UUID;

public class DashboardActivity extends Activity
{
    ImageView m_imageViewGoToPlanner,
            m_imageViewGoToEducational,
            m_imageViewGoToSettings,
            m_imageviewGoToProfile;

    @Override
    protected void onStart() {
        super.onStart();

    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String UUIDstr = UUID.randomUUID().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        if(!sharedPreferences.contains("UUID")) {
            spEditor.putString("UUID", UUIDstr);
            spEditor.commit();
        }

        new EducationalListReader(AppDatabase.getDatabase(this).educationalDao()).execute();// force db creation

        DashboardDatabaseWatcher watcher = new DashboardDatabaseWatcher();
        watcher.initialiseWatcher(this);
        AppDatabase.getDatabase(this).addDatabaseWatcher(watcher);



        m_imageViewGoToPlanner = findViewById(R.id.dashboard_go_to_planner);
        m_imageviewGoToProfile = findViewById(R.id.dashboard_go_to_profile);
        m_imageViewGoToEducational = findViewById(R.id.dashboard_go_to_educational);
      //  disableEducationalButton();

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


        m_imageViewGoToEducational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(DashboardActivity.this, EducationalActivity.class);
                startActivity(intent3);

            }
        });

        //Adding two test buttons to try out the Qualtrics surveys - these should be automated then removed
        final Button button1 = (Button) findViewById(R.id.qtest_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                QualtricsActivity.setup(QualtricsActivity.WELCOME_SURVEY,"test-uuid");
                Intent k = new Intent(DashboardActivity.this, QualtricsActivity.class);
                startActivity(k);
            }
        });
        final Button button2 = (Button) findViewById(R.id.qtest_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                QualtricsActivity.setup(QualtricsActivity.END_SURVEY,"test-uuid");
                Intent k = new Intent(DashboardActivity.this, QualtricsActivity.class);
                startActivity(k);
            }
        });

    }

    public void disableEducationalButton()
    {
        m_imageViewGoToEducational.setVisibility(View.GONE);

    }

    public void enableEducationalButton()
    {
        m_imageViewGoToEducational.setVisibility(View.VISIBLE);
    }
}
