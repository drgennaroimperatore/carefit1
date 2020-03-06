package com.example.game_.other01_app.Activities;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.game_.other01_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * This is the Home Screen ACTIVITY
 */

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        openHome();
                        return true;
                    case R.id.progress:
                        openProgress();
                        return true;
                    case R.id.exercise_list:
                        openExercise();
                        return true;
                    case R.id.profile:
                        openProfile();
                        return true;
                }
                return HomeScreenActivity.super.onContextItemSelected(item);

            }
        });
    }

    public void openHome(){
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }

    public void openProgress(){
        Intent intent = new Intent(this, ProgressScreenActivity.class);
        startActivity(intent);
    }

    public void openExercise(){
        Intent intent = new Intent(this, ExerciseListActivity.class);
        startActivity(intent);
    }

    public void openProfile(){
        Intent intent = new Intent(this, ProfileScreenActivity.class);
        startActivityForResult(intent, 1);
    }



    @Override
    public void onBackPressed() {
        //Do nothing.
    }
}






//      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.home:
//                        Toast.makeText(HomeScreenActivity.this, "Home Screen clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.progress:
//                        Toast.makeText(HomeScreenActivity.this, "Progress screen clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.exercise_list:
//                        Toast.makeText(HomeScreenActivity.this, "Exercise screen clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.profile:
//                        Toast.makeText(HomeScreenActivity.this, "Profile screen clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return true;
//            }

